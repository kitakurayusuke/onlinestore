package mart1017;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Shohin;
import dao.ShohinDAO;

public class ReviewJyunbi extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		try{
			String strSId = request.getParameter("kuchikomi");

			HttpSession session = request.getSession();
			// カートの中身になるList
			List<Shohin> cart = (List<Shohin>)session.getAttribute("cart");
			// 購入情報を保持するMap
			Map<String,Integer> map = (Map<String,Integer>)session.getAttribute("map");

			ShohinDAO dao = new ShohinDAO();
			List<Shohin> list;
			List<Shohin> rList = new ArrayList<Shohin>();

			// レビューする商品を選択
			list = dao.searchSID(strSId);
			Shohin s = list.get(0);
			rList.add(s);

			session.setAttribute("rList",rList);

			request.getRequestDispatcher("syouhinreview.jsp").forward(request, response);

		}catch(Exception e){
			e.printStackTrace(out);
		}
	}
}
