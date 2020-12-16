package mart1017;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Konyu;
import bean.Riyosya;
import bean.Shohin;
import dao.KonyuDAO;
import dao.ShohinDAO;

public class Kounyuu extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		try{
			HttpSession session = request.getSession();
			// カートの中身List
			List<Shohin> cart = (List<Shohin>)session.getAttribute("cart");
			// 購入情報Map
			Map<String,Integer> map = (Map<String,Integer>)session.getAttribute("map");
			// ログイン情報
			List<Riyosya> loginList = (List<Riyosya>)session.getAttribute("rLoginList");

			ShohinDAO sdao = new ShohinDAO();
			ArrayList<Shohin> newCart = new ArrayList<Shohin>();
			ArrayList<Shohin> errorList = new ArrayList<Shohin>();
			int intLine = 0;

			KonyuDAO kdao = new KonyuDAO();
			Riyosya r = loginList.get(0);
			int shukoFlag = 0;
			int iLine = 0;
			int intGzaiko = 0;
			int intBuy = 0;

			// 最新の在庫数を取得
			for(Shohin c : cart){
				List<Shohin> list;
				list = sdao.searchSID(c.getShoid());
				Shohin nc = list.get(0);
				intGzaiko = nc.getZaikosuu();

				// 最新の商品情報を取得・カートの中身を更新
				nc.setShoid(nc.getShoid());
				nc.setDaib(nc.getDaib());
				nc.setSname(nc.getSname());
				nc.setSshokai(nc.getSshokai());
				nc.setStanka(nc.getStanka());
				nc.setZaikosuu(nc.getZaikosuu());
				newCart.add(nc);


				// 購入数を取得
				intBuy = map.get(c.getShoid());

				// 在庫数より購入数が多い場合
				if(intGzaiko < intBuy){
					// エラーフラグを設定
					String strError = "error";
					session.setAttribute("error",strError );
					// エラーリストに追加
					Shohin s = new Shohin();
					s.setSname(nc.getSname());
					errorList.add(s);
					// 購入数を現在庫数に置き換え
					map.replace(nc.getShoid(), intGzaiko);
				}
			}
			if(errorList.size() != 0){
				// セッションに情報を設定
				session.setAttribute("cart",newCart );
				session.setAttribute("map",map );
				session.setAttribute("errorList",errorList );
				request.getRequestDispatcher("kaatonakami.jsp").forward(request, response);
			}else if(errorList.size() == 0){
				for(Shohin c : cart){
				// 商品DBから在庫を減らす
				intLine = sdao.zaikoS(c.getShoid(), map.get(c.getShoid()));

				// 購入履歴DBに購入履歴を追加
					if(intLine == 1){
						ArrayList<Konyu> kList = new ArrayList<Konyu>();
						Konyu k = new Konyu();
						java.util.Date udate = new Date();
						java.sql.Date date = new java.sql.Date(udate.getTime());

						k.setRirekikainumber(r.getKainumber());
						k.setKonyuday(date.toString());
						k.setRirekisid(c.getShoid());
						k.setKonyusu(map.get(c.getShoid()));
						k.setTanka(c.getStanka());
						k.setShuko(shukoFlag);
						kList.add(k);

						iLine = kdao.insertKou(kList);
					}
				}
				if(iLine == 1){
					session.setAttribute("cart",newCart );
					// 正常終了で購入確認画面へ移動
					request.getRequestDispatcher("kounyukakunin.jsp").forward(request, response);
				}
			}
		}catch(Exception e){
			e.printStackTrace(out);
		}
	}
}

