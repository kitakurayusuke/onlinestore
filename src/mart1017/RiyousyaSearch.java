package mart1017;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Konyu;
import bean.Riyosya;
import dao.KonyuDAO;
import dao.RiyosyaDAO;

public class RiyousyaSearch extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 正しく処理された時の遷移先
		String URL = "riyousyajyouhouhyouji.jsp";
		// 処理できなかった時のエラーメッセージ
		String error = "";

		String strKaiName="";
		// セッションを開始
		HttpSession session = request.getSession();

		// リクエストパラメータ値を取得
		String strKaiNum = request.getParameter("kaiNum");

		try {
			//DAOクラスのオブジェクトを生成
			RiyosyaDAO dao = new RiyosyaDAO();
			//利用者検索をするメソッドの呼び出し
			List<Riyosya> list = dao.searchRK(strKaiNum);
			// 検索が成功しているかのチェック
			if (list.size() == 0) {

				error = "会員番号"+strKaiNum+"は存在しません";
				URL = "riyousyakanrimenu.jsp";

			}
			//リストから会員の名前を取り出す
			for (Riyosya riyosya : list) {
				strKaiName=riyosya.getRname();
			}
			KonyuDAO kdao = new KonyuDAO();
			List<Konyu> klist =kdao.searchKou(strKaiNum);
			// セッション属性にデータを設定
			session.setAttribute("rlist", list);
			session.setAttribute("klist", klist);
			session.setAttribute("rNum", strKaiNum);
			session.setAttribute("rName", strKaiName);
		}  catch (NamingException e) {
			// データベースにアクセスできなかった場合
			error = "利用者検索に失敗しました";
			URL = "riyousyakanrimenu.jsp";
			e.printStackTrace();

		} catch (SQLException e) {
			// 検索結果が取得できなかった場合
			error = "利用者検索に失敗しました";
			URL = "riyousyakanrimenu.jsp";
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
