package mart1017;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class KanriLogout extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		try{
			// getSessionメソッドを使ってHttpSessionオブジェクトを取得
			HttpSession session = request.getSession();
			//セッションにある全ての要素名を取得する
			Enumeration vals = session.getAttributeNames();

			//取得した要素名をループ処理で全て削除する
			while(vals.hasMoreElements()){
			  String nm = (String)vals.nextElement();
			  session.removeAttribute(nm);
			}
				// トップ画面へフォワード

			request.getRequestDispatcher("top.jsp").forward(request, response);

		}catch(Exception e){
			e.printStackTrace(out);
		}
	}
}
