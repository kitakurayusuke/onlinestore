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
public class SyouhinDelete extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String URL = "syouhinjyouhousyorikekka.jsp";
		String error = "";
		HttpSession session = request.getSession();
		List<Shohin> slist = (List<Shohin>) session.getAttribute("slist");
		String sid="";
		for(Shohin shohin : slist){
			sid=shohin.getShoid();
		}
		session.removeAttribute("error");


		try{

			ShohinDAO dao = new ShohinDAO();

			int line = dao.deleteS(sid);

			if (line == 0) {
				error = "既に削除されています";
				URL = "syouhinsakujyo.jsp";
			}
		} catch (NamingException e) {
			error = "商品削除に失敗しました";
			e.printStackTrace();
			// 検索結果が取得できなかった場合
		} catch (SQLException e) {
			error = "商品削除に失敗しました";
			e.printStackTrace();
		}
		session.setAttribute("kakunin","delete");

		RequestDispatcher rd = request.getRequestDispatcher(URL);
		rd.forward(request, response);

	}
	public void doGet(HttpServletRequest request, HttpServletResponse
			response) throws IOException, ServletException {
		doPost(request, response);
	}
}

