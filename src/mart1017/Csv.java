package mart1017;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Date;
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

import bean.Konyu;
import bean.Shohin;
import dao.KonyuDAO;
import dao.ShohinDAO;

public class Csv  extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		String URL = "kounyurireki.jsp";
		String error = "";

		String fdate = request.getParameter("fdate");
		String ldate = request.getParameter("ldate");
		String kakunin=request.getParameter("kakunin");
		HttpSession session = request.getSession();
		session.removeAttribute("error");
		try{
			if(fdate.equals("")||ldate.equals("")){
				error = "期間を入力してください";
				URL = "kounyurireki.jsp";
			}else{
				Date dateFdate= Date.valueOf(fdate);
				Date dateLdate= Date.valueOf(ldate);

				Date dateKara=Date.valueOf("2000-01-01");

				if(dateFdate.after(dateLdate)){
					dateKara=dateFdate;
					dateFdate=dateLdate;
					dateLdate=dateKara;
				}
				String strKonyuday="";
				String sName="";
				String daib="";
				String chub="";
				String shob="";
				List<Konyu> csvKlist=new ArrayList<Konyu>();
				KonyuDAO dao = new KonyuDAO();
				ShohinDAO sdao = new ShohinDAO();
				String strRNum=(String)session.getAttribute("rNum");
				List<Konyu> list =dao.searchKou(strRNum);
				List<Shohin> csvSlist=new ArrayList<>();
				for (Konyu konyu : list) {
					strKonyuday=konyu.getKonyuday();
					Date dateKdate=Date.valueOf(strKonyuday);
					if(dateKdate.compareTo(dateFdate)==0){
						Konyu k=new Konyu();
						k.setKonyuday(konyu.getKonyuday());
						k.setRirekisid(konyu.getRirekisid());
						k.setKonyusu(konyu.getKonyusu());
						k.setTanka(konyu.getTanka());
						csvKlist.add(k);
					}
					else if(dateKdate.compareTo(dateLdate)==0){
						Konyu k=new Konyu();
						k.setKonyuday(konyu.getKonyuday());
						k.setRirekisid(konyu.getRirekisid());
						k.setKonyusu(konyu.getKonyusu());
						k.setTanka(konyu.getTanka());
						csvKlist.add(k);
					}
					else if(dateKdate.after(dateFdate)){
					if(dateKdate.before(dateLdate)){
						Konyu k=new Konyu();
						k.setKonyuday(konyu.getKonyuday());
						k.setRirekisid(konyu.getRirekisid());
						k.setKonyusu(konyu.getKonyusu());
						k.setTanka(konyu.getTanka());
						csvKlist.add(k);
						}
					}

				}
				session.setAttribute("klist", csvKlist);
				if(kakunin==null){

				PrintWriter p = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream("C:\\csv\\test.csv", false),"Shift-JIS")));
				p.print("購入日");
				p.print(",");
				p.print("商品ID");
				p.print(",");
				p.print("商品名");
				p.print(",");
				p.print("大分類");
				p.print(",");
				p.print("中分類");
				p.print(",");
				p.print("小分類");
				p.print(",");
				p.print("販売単価");
				p.print(",");
				p.print("購入数");
				p.println();
				for (Konyu konyu : csvKlist) {
					String sid=konyu.getRirekisid();
					csvSlist = sdao.searchSID(sid);
					for (Shohin shohin : csvSlist) {
						sName=shohin.getSname();
						daib=shohin.getDaib();
						chub=shohin.getChub();
						shob=shohin.getShob();
					}
					p.print(konyu.getKonyuday());
					p.print(",");
					p.print(konyu.getRirekisid());
					p.print(",");
					p.print(sName);
					p.print(",");
					p.print(daib);
					p.print(",");
					p.print(chub);
					p.print(",");
					p.print(shob);
					p.print(",");
					p.print(konyu.getTanka());
					p.print(",");
					p.print(konyu.getKonyusu());
					p.println();
				}
				p.close();
				error="csv出力に成功しました";
				}else{

				}
			}
			}catch (NamingException e) {
				// データベースにアクセスできなかった場合
				URL = "syouhinhenkou.jsp";
				error = "CSV出力に失敗しました";
				e.printStackTrace();
			} catch (SQLException e) {
				// 検索結果が取得できなかった場合
				URL = "syouhinhenkou.jsp";
				error = "CSV出力に失敗しました";
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
		public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
			doPost(request, response);
		}
}