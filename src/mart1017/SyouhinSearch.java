package mart1017;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Shohin;
import dao.ShohinDAO;

public class SyouhinSearch extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		String URL = "kanrisyouhinkensakukekka.jsp";
		String error = "";
		HttpSession session = request.getSession();
		session.removeAttribute("error");
		String sid = request.getParameter("sid");
		try {

			ShohinDAO dao = new ShohinDAO();
			List<Shohin> list =dao.searchSID(sid);
			if (list.size() == 0) {
				error = "商品ID"+sid+"は存在しません";
				URL = "syouhinkensaku.jsp";

			}
			session.setAttribute("slist", list);


		}  catch (NamingException e) {
			// データベースにアクセスできなかった場合
			error = "商品検索に失敗しました";
			URL = "riyousyakanrimenu.jsp";
			e.printStackTrace();

		} catch (SQLException e) {
			// 検索結果が取得できなかった場合
			error = "商品検索に失敗しました";
			URL = "riyousyakanrimenu.jsp";
			e.printStackTrace();
		}
		if (error != null) {
			request.setAttribute("error", error);
		}
		RequestDispatcher rd = request.getRequestDispatcher(URL);
		rd.forward(request, response);
	}
	public void doGet(HttpServletRequest request, HttpServletResponse
			response) throws IOException, ServletException {
		doPost(request, response);
	}
}
