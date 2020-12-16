package mart1017;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Riyosya;
import dao.RiyosyaDAO;

public class RiyousyaEdit extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		// 正しく処理された時の遷移先
		String URL = "riyousyajyouhoukoushinsakujyo.jsp";
		// 処理できなかった時のエラーメッセージ
		String error = "";
		// リクエストパラメータ値を取得
		String strRName=request.getParameter("rname");
		String jusho = request.getParameter("jusho");
		String rpass = request.getParameter("rpass");
		String address = request.getParameter("address");

		// セッションを開始
		HttpSession session = request.getSession();
		session.removeAttribute("error");
		String strRNum=(String)session.getAttribute("rNum");

		int rid=0;
		try {
			if(jusho.equals("")||rpass.equals("")){
				error = "更新必須項目が未入力です";
				URL = "riyousyajyouhouhyouji.jsp";
			}else{
				//DAOクラスのオブジェクトを生成
				RiyosyaDAO dao = new RiyosyaDAO();
				ArrayList<Riyosya> idlist=new ArrayList<>();
				idlist=dao.searchRK(strRNum);
				for (Riyosya riyosya : idlist){
					rid=riyosya.getRseiid();
				}
				// 引数として渡すArrayListを作成
				Riyosya r=new Riyosya();
				r.setRseiid(rid);
				r.setKainumber(strRNum);
				r.setRname(strRName);
				r.setJusho(jusho);
				r.setAdress(address);
				r.setRpassward(rpass);
				ArrayList<Riyosya> rlist=new ArrayList<>();
				rlist.add(r);
				//利用者更新をするメソッドの呼び出し
				int line = dao.updateR(rlist);
				if (line == 0) {
					error = "更新に失敗しました";
					URL = "riyousyajyouhouhyouji.jsp";
				}
				List<Riyosya> list =dao.searchRK(strRNum);
				//セッションに情報をセットする
				session.setAttribute("rlist", list);
				for (Riyosya riyosya : list) {
					session.setAttribute("rName", riyosya.getRname());
				}

			}
		} catch (NamingException e) {
			// データベースにアクセスできなかった場合
			URL = "riyousyajyouhouhyouji.jsp";
			error = "更新に失敗しました";
			e.printStackTrace();

		} catch (SQLException e) {
			// 検索結果が取得できなかった場合
			URL = "riyousyajyouhouhyouji.jsp";
			error = "更新に失敗しました";
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
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