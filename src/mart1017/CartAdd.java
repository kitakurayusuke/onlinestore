package mart1017;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Shohin;

public class CartAdd extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		try{
			// リクエストパラメータ値を取得
			int intBuy = Integer.parseInt(request.getParameter("suryou"));

			// セッションから情報を取得
			HttpSession session = request.getSession();
			// カートに入れた商品の情報
			List<Shohin> sList = (List<Shohin>)session.getAttribute("slist");
			// カートの中身になるList
			List<Shohin> cart = (List<Shohin>)session.getAttribute("cart");
			// 購入情報を保持するMap
			Map<String,Integer> map = (Map<String,Integer>)session.getAttribute("map");

			// List,Mapを作成
			if(cart == null){
				cart = new ArrayList<Shohin>();
				map = new LinkedHashMap<String,Integer>();
			}
			// カートに入れた商品情報の取り出し
			Shohin s = sList.get(0);

			// 重複チェック
			if(cart.contains(s)){
				// 重複していた場合は購入数を増やす
				int intMaeBuy = map.get(s.getShoid());
				int intAtoBuy = intBuy + intMaeBuy;
				int intMax = 5;
				if(intAtoBuy > s.getZaikosuu()){
					map.replace(s.getShoid(), s.getZaikosuu());
				}else if(intAtoBuy <= intMax){
					map.replace(s.getShoid(), intAtoBuy);
				}else if(intAtoBuy > intMax){
					map.replace(s.getShoid(), intMax);
				}
				// カート中身画面へフォワード
				request.getRequestDispatcher("kaatonakami.jsp").forward(request, response);
			}else{
				// 購入情報をMapにput
				map.put(s.getShoid(), intBuy);

				// カートに商品情報をadd
				s.setShoid(s.getShoid());
				s.setDaib(s.getDaib());
				s.setSname(s.getSname());
				s.setSshokai(s.getSshokai());
				s.setStanka(s.getStanka());
				s.setZaikosuu(s.getZaikosuu());
				cart.add(s);
			}

			// セッションに設定
			session.setAttribute("map",map);
			session.setAttribute("cart",cart);

			// カート中身画面へフォワード
			request.getRequestDispatcher("kaatonakami.jsp").forward(request, response);

		}catch(Exception e){
			e.printStackTrace(out);
		}
	}
}
