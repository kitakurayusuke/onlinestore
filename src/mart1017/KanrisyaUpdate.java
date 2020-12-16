package mart1017;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Kanrisya;
import dao.KanrisyaDAO;

public class KanrisyaUpdate extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		String URL = "kanrisyakanrimenu.jsp";
		String error = "";

		String kanriNum = request.getParameter("kanriNum");
		String kpass = request.getParameter("kpass");
		String zaiseki=request.getParameter("zaiseki");
		String rkanri[] = request.getParameterValues("rkanri");
		String skanri[] = request.getParameterValues("skanri");
		String kkanri[] = request.getParameterValues("kkanri");
		int klogin=0;
		int rInsert=0;
		int rUpdate=0;
		int rDelete=0;
		int sInsert=0;
		int sUpdate=0;
		int sDelete=0;
		int kInsert=0;
		int kUpdate=0;
		int kDelete=0;
		int kid=0;
		String kname="";
		if(zaiseki!=null){
			klogin=1;
		}
		if(rkanri!=null){
		for(int i=0;i<rkanri.length;i++){
			if(rkanri[i].equals("insert")){
				rInsert=1;
			}
			if(rkanri[i].equals("update")){
				rUpdate=1;
			}
			if(rkanri[i].equals("update")){
				rDelete=1;
			}
		}
		}
		if(skanri!=null){
		for(int i=0;i<skanri.length;i++){
			if(skanri[i].equals("insert")){
				sInsert=1;
			}
			if(skanri[i].equals("update")){
				sUpdate=1;
			}
			if(skanri[i].equals("update")){
				sDelete=1;
			}
		}
		}
		if(kkanri!=null){
		for(int i=0;i<kkanri.length;i++){
			if(kkanri[i].equals("insert")){
				kInsert=1;
			}
			if(kkanri[i].equals("update")){
				kUpdate=1;
			}
			if(kkanri[i].equals("update")){
				kDelete=1;
			}
		}
		}
		HttpSession session = request.getSession();
		session.removeAttribute("error");

		try {

			KanrisyaDAO dao = new KanrisyaDAO();
			ArrayList<Kanrisya> list=new ArrayList<>();
			list=dao.searchKKP(kanriNum,kpass);
			for(Kanrisya kanrisya : list){
				kname=kanrisya.getKname();
				kid=kanrisya.getKseiid();
			}
			Kanrisya k=new Kanrisya();
			k.setKseiid(kid);
			k.setKanrinumber(kanriNum);
			k.setKpassward(kpass);
			k.setKname(kname);
			k.setKlogin(klogin);
			k.setRinsert(rInsert);
			k.setRupdate(rUpdate);
			k.setRdelete(rDelete);
			k.setSinsert(sInsert);
			k.setSupdate(sUpdate);
			k.setSdelete(sDelete);
			k.setKinsert(kInsert);
			k.setKupdate(kUpdate);
			k.setKdelete(kDelete);

			ArrayList<Kanrisya> klist=new ArrayList<>();
			klist.add(k);

			int line=dao.updateK(klist);
			if(line==0){
				error = "アップデートに失敗しました";
			}

		}  catch (NamingException e) {
			// データベースにアクセスできなかった場合
			error = "管理者の更新に失敗しました";
			URL = "kanrisyakanrimenu.jsp";
			e.printStackTrace();

		} catch (SQLException e) {
			// 検索結果が取得できなかった場合
			error = "管理者の更新に失敗しました";
			URL = "kanrisyakanrimenu.jsp";
			e.printStackTrace();
		}catch (Exception e) {
			// TODO 自動生成された catch ブロック
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
