package mart1017;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Shohin;
import dao.ShohinDAO;

public class CartDelete extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		try{
			String strDel = request.getParameter("sakujyo");

			HttpSession session = request.getSession();
			// 購入情報を保持するMap
			Map<String,Integer> map = (Map<String,Integer>)session.getAttribute("map");
			// カートの中身になるList
			List<Shohin> cart = (List<Shohin>)session.getAttribute("cart");

			// カートから削除ボタンが押された時の処理
			if(strDel != null){
				// listから削除
				ShohinDAO dao = new ShohinDAO();
				List<Shohin> list;
				list = dao.searchSID(strDel);
				Shohin s = list.get(0);
				int intDelete = cart.indexOf(s);
				cart.remove(cart.get(intDelete));

				// mapから削除
				map.remove(strDel);
			}

			session.setAttribute("cart",cart);
			session.setAttribute("map",map);

			request.getRequestDispatcher("kaatonakami.jsp").forward(request, response);

		}catch(Exception e){
			e.printStackTrace(out);
		}
	}
}
