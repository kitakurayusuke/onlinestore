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

import dao.KonyuDAO;

public class Minou extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		// 正しく処理された時の遷移先
		String URL = "minousyouhin.jsp";
		// 処理できなかった時のエラーメッセージ
		String error = "";
		// リクエストパラメータ値を取得
		String kid= request.getParameter("kid");
		// セッションを開始
		HttpSession session = request.getSession();
		session.removeAttribute("error");
		try {
			int intKid=Integer.parseInt(kid);
			//DAOクラスのオブジェクトを生成
			KonyuDAO dao = new KonyuDAO();
			//未納を出荷するメソッドの呼び出し
			int line = dao.updateKou(intKid,1);
			if (line == 0) {
				error = "更新に失敗しました";
				URL = "minousyouhin.jsp";
			}
		} catch (NumberFormatException e) {
			// 文字列をint型に変換できない場合

			e.printStackTrace();
		} catch (NamingException e) {
			// データベースにアクセスできなかった場合
			URL = "minousyouhin.jsp";
			error = "更新に失敗しました";
			e.printStackTrace();

		} catch (SQLException e) {
			// 検索結果が取得できなかった場合
			URL = "minousyouhin.jsp";
			error = "更新に失敗しました";
			e.printStackTrace();
		} catch (Exception e) {
			// エラーが起きた場合
			URL = "minousyouhin.jsp";
			error = "更新に失敗しました";
			e.printStackTrace();
		}
		//エラーメッセージが存在する場合にセッションにセットする
		if (error != null) {
			request.setAttribute("error", error);
		}

		// JSPに遷移する
		RequestDispatcher rd = request.getRequestDispatcher(URL);
		rd.forward(request, response);

	}
	public void doGet(HttpServletRequest request, HttpServletResponse
			response) throws IOException, ServletException {
		doPost(request, response);
	}
}
