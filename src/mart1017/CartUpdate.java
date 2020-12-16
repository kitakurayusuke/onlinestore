package mart1017;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CartUpdate extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		try{
			// リクエストパラメータ値を取得
			int intHenkouBuy = Integer.parseInt(request.getParameter("henkou"));
			String strSId = request.getParameter("hg");

			// セッションから情報を取得
			HttpSession session = request.getSession();
			Map<String,Integer> map = (Map<String,Integer>)session.getAttribute("map");

			// カートの中身の数量変更
			map.replace(strSId, intHenkouBuy);

			request.getRequestDispatcher("kaatonakami.jsp").forward(request, response);

		}catch(Exception e){
			e.printStackTrace(out);
		}
	}
}
