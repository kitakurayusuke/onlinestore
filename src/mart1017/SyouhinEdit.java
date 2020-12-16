package mart1017;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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

public class SyouhinEdit extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		String URL = "syouhinjyouhousyorikekka.jsp";
		String error = "";

		String sid = request.getParameter("sid");
		String sname = request.getParameter("sname");
		String price = request.getParameter("price");
		String zaiko = request.getParameter("zaiko");
		String dai = request.getParameter("dai");
		String tyuu = request.getParameter("tyuu");
		String syou = request.getParameter("syou");
		String shokai = request.getParameter("shokai");
		String setsumei = request.getParameter("setsumei");
		String photo = request.getParameter("photo");

		HttpSession session = request.getSession();
		session.removeAttribute("error");

		try {
			if("".equals(sid)||"".equals(sname)||"".equals(price)||"".equals(zaiko)||"".equals(shokai)||"".equals(setsumei)||"".equals(photo)){
				error = "商品名を入力してください";
				URL = "syouhinhenkou.jsp";
			}else{
				int intPrice=Integer.parseInt(price);
				int intZaiko=Integer.parseInt(zaiko);
				ShohinDAO dao = new ShohinDAO();
				Shohin s=new Shohin();
				s.setShoid(sid);
				s.setSname(sname);
				s.setStanka(intPrice);
				s.setZaikosuu(intZaiko);
				s.setDaib(dai);
				s.setChub(tyuu);
				s.setShob(syou);
				s.setSshokai(shokai);
				s.setSsetsumei(setsumei);
				s.setShashin(photo);

				ArrayList<Shohin> slist=new ArrayList<>();
				slist.add(s);

					int line = dao.updateS(slist);

				if (line == 0) {
					error = "更新に失敗しました";
					URL = "syouhinhenkou.jsp";
				}
				List<Shohin> list =dao.searchSID(sid);
				session.setAttribute("kakunin","edit");
				session.setAttribute("slist", list);
			}
		} catch (NamingException e) {
			// データベースにアクセスできなかった場合
			URL = "syouhinhenkou.jsp";
			error = "商品の更新に失敗しました";
			e.printStackTrace();

		} catch (NumberFormatException e) {
			// 文字列をint型に変換できない場合
			URL = "syouhinhenkou.jsp";
			error = "単価、在庫数は数字を入力してください";
			e.printStackTrace();
		} catch (SQLException e) {
			// 検索結果が取得できなかった場合
			URL = "syouhinhenkou.jsp";
			error = "商品の更新に失敗しました";
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		if (error != null) {
			request.setAttribute("error", error);
		}

		// JSPに遷移する
		RequestDispatcher rd = request.getRequestDispatcher(URL);
		rd.forward(request, response);

	}


	public void doGet(HttpServletRequest request, HttpServletResponse
			response) throws IOException, ServletException {
		doPost(request, response);
	}
}