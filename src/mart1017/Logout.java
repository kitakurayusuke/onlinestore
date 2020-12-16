package mart1017;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		try{
			// getSessionメソッドを使ってHttpSessionオブジェクトを取得
			HttpSession session = request.getSession();
			// セッション属性からログイン情報を取得
			Object objLogin = session.getAttribute("login");
			if("login".equals(objLogin)){
				session.invalidate();
				// トップ画面へフォワード
				request.getRequestDispatcher("top.jsp").forward(request, response);
			}
			request.getRequestDispatcher("top.jsp").forward(request, response);

		}catch(Exception e){
			e.printStackTrace(out);
		}
	}
}
