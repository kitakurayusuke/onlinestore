package mart1017;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Review;
import bean.Shohin;
import dao.ReviewDAO;

public class ReviewInsert extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		try{
			String strReview = request.getParameter("review");

			HttpSession session = request.getSession();
			List<Shohin> rList = (List<Shohin>)session.getAttribute("rList");

			String strFlag = null;

			// 入力チェック
			if("".equals(strReview)){
				strFlag = "error";
				session.setAttribute("Flag",strFlag);
				request.getRequestDispatcher("syouhinreview.jsp").forward(request, response);
			}else if(strReview.length() > 201){
				strFlag = "error";
				session.setAttribute("Flag",strFlag);
				request.getRequestDispatcher("syouhinreview.jsp").forward(request, response);
			}
			// レビュー情報追加メソッドに渡す引数の作成
			ArrayList<Review> reList = new ArrayList<Review>();
			Shohin s = rList.get(0);

			Review r = new Review();
			r.setReviewsid(s.getShoid());
			r.setReview(strReview);
			reList.add(r);

			// レビューをDBに追加
			ReviewDAO rdao = new ReviewDAO();
			int line = rdao.insertVW(reList);

			// 追加ができたかどうかでフラグを設定
			if(line == 0){
				strFlag = "error";
				session.setAttribute("Flag",strFlag);
			}else{
				strFlag = "success";
				session.setAttribute("Flag",strFlag);
			}

			request.getRequestDispatcher("syouhinreview.jsp").forward(request, response);

		}catch(Exception e){
			e.printStackTrace(out);
		}
	}
}
