package mart1017;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.RiyosyaDAO;

public class RiyousyaDelete  extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 正しく処理された時の遷移先
		String URL = "riyousyajyouhoukoushinsakujyo.jsp";
		// 処理できなかった時のエラーメッセージ
		String error = "";
		// セッションを開始
		HttpSession session = request.getSession();
		session.removeAttribute("error");
		// セッション属性から利用者番号を取得
		String strKaiNum=(String)session.getAttribute("rNum");
		try{
			//DAOクラスのオブジェクトを生成
			RiyosyaDAO dao = new RiyosyaDAO();
			//利用者削除をするメソッドの呼び出し
			int line = dao.deleteR(strKaiNum);
			if (line == 0) {
				error = "既に削除されています";
				URL = "riyousyajyouhouhyouji.jsp";
			}
		} catch (NamingException e) {
			error = "削除に失敗しました";
			e.printStackTrace();
			// 検索結果が取得できなかった場合
		} catch (SQLException e) {
			error = "削除に失敗しました";
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		//エラーメッセージが存在する場合にセッションにセットする
				if (error != null) {
					request.setAttribute("error", error);
				}
		// 結果表示画面へフォワード
		RequestDispatcher rd = request.getRequestDispatcher(URL);
		rd.forward(request, response);

	}
	public void doGet(HttpServletRequest request, HttpServletResponse
			response) throws IOException, ServletException {
		doPost(request, response);
	}
}

