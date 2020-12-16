package mart1017;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Shohin;
import dao.ShohinDAO;

public class ShohinSearch extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		try{
			// リクエストパラメータ値を取得
			String strBunrui = request.getParameter("bunrui");
			String strKeyword = request.getParameter("keyword");
			// getSessionメソッドを使ってHttpSessionオブジェクトを取得
			HttpSession session = request.getSession();
			//DAOクラスのオブジェクトを生成
			ShohinDAO dao = new ShohinDAO();
			// 戻り値を格納する変数を宣言
			List<Shohin> list;
			// キーワードの入力チェック
			if("".equals(strKeyword)){
				strKeyword = "%";
			}
				//商品検索をするメソッドの呼び出し
				list = dao.searchS(strBunrui,strKeyword);
				// セッション属性にデータを設定
				session.setAttribute("list",list );
				session.setAttribute("bunrui",strBunrui );
				session.setAttribute("keyword",strKeyword );

				if((strBunrui.equals("%")) && (strKeyword.equals("%"))){
					String strAll = "全ての商品";
					session.setAttribute("subete",strAll);
				}
				// 結果表示画面へフォワード
				request.getRequestDispatcher("syouhinkensakukekka.jsp").forward(request, response);

		}catch(Exception e){
			e.printStackTrace(out);
		}
	}
}
