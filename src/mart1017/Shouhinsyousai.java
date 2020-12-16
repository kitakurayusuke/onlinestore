package mart1017;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Review;
import bean.Shohin;
import dao.ReviewDAO;
import dao.ShohinDAO;

public class Shouhinsyousai extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		try{
			// リクエストパラメータ値を取得
			String strSyouhin = request.getParameter("shoid");
			// セッションに接続
			HttpSession session = request.getSession();
			Set<Shohin> hset = (Set<Shohin>) session.getAttribute("hashset");

			//商品の情報を取得
			ShohinDAO dao = new ShohinDAO();
			List<Shohin> list;
			list = dao.searchSID(strSyouhin);
			// 商品の情報をセッションに設定
			session.setAttribute("slist",list );

			// 商品レビューを取得
			ReviewDAO rdao = new ReviewDAO();
			List<Review> reviewList;
			reviewList = rdao.searchVW(strSyouhin);
			// 商品レビューをセッションに設定
			session.setAttribute("reviewList",reviewList );
			// 最近見た商品機能
			if(hset == null){
				hset = new LinkedHashSet<Shohin>();
			}
			hset.add(list.get(0));
			if(hset.size() == 4){
				hset.remove(hset.toArray()[0]);
			}

			session.setAttribute("hashset",hset );
			// 商品詳細表示画面へフォワード
			request.getRequestDispatcher("syouhinsyousai.jsp").forward(request, response);

		}catch(Exception e){
			e.printStackTrace(out);
		}
	}
}
