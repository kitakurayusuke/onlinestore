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

public class Shohinsort extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		try{
			String strSort = request.getParameter("sort");

			HttpSession session = request.getSession();
			String strSortBunrui = (String) session.getAttribute("bunrui");
			String strSortKeyword = (String) session.getAttribute("keyword");

			// 検索メソッドを呼び出す準備
			ShohinDAO dao = new ShohinDAO();
			List<Shohin> sortlist;

		// リクエストパラメータ値によって呼ぶメソッドを変える
		if("kakakusyoujyun".equals(strSort)){
			sortlist = dao.searchS1(strSortBunrui, strSortKeyword);
			session.setAttribute("sortlist",sortlist );
			strSort = "価格の安い順";
			session.setAttribute("sort",strSort );
		}else if("kakakukoujyun".equals(strSort)){
			sortlist = dao.searchS2(strSortBunrui, strSortKeyword);
			session.setAttribute("sortlist",sortlist );
			strSort = "価格の高い順";
			session.setAttribute("sort",strSort );
		}else if("zaikosyoujyun".equals(strSort)){
			sortlist = dao.searchS3(strSortBunrui, strSortKeyword);
			session.setAttribute("sortlist",sortlist );
			strSort = "在庫少ない順";
			session.setAttribute("sort",strSort );
		}else if("zaikokoujyun".equals(strSort)){
			sortlist = dao.searchS4(strSortBunrui, strSortKeyword);
			session.setAttribute("sortlist",sortlist );
			strSort = "在庫多い順";
			session.setAttribute("sort",strSort );
		}else if("namaesyoujyun".equals(strSort)){
			sortlist = dao.searchS5(strSortBunrui, strSortKeyword);
			session.setAttribute("sortlist",sortlist );
			strSort = "商品名昇順";
			session.setAttribute("sort",strSort );
		}else if("namaekoujyun".equals(strSort)){
			sortlist = dao.searchS6(strSortBunrui, strSortKeyword);
			session.setAttribute("sortlist",sortlist );
			strSort = "商品名降順";
			session.setAttribute("sort",strSort );
		}
			// 結果表示画面へフォワード
			request.getRequestDispatcher("syouhinkensakukekka.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace(out);
		}
	}
}

