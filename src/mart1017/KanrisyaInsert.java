package mart1017;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Kanrisya;
import dao.KanrisyaDAO;

public class KanrisyaInsert extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		String URL = "kanrisyakanrimenu.jsp";
		String error = "";


		String kname = request.getParameter("kname");
		String kpass = request.getParameter("kpass");
		HttpSession session = request.getSession();
		session.removeAttribute("error");

		String rkanri[] = request.getParameterValues("rkanri");
		String skanri[] = request.getParameterValues("skanri");
		String kkanri[] = request.getParameterValues("kkanri");
		int rInsert=0;
		int rUpdate=0;
		int rDelete=0;
		int sInsert=0;
		int sUpdate=0;
		int sDelete=0;
		int kInsert=0;
		int kUpdate=0;
		int kDelete=0;
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

		try {
			// パスワードの入力文字チェックの準備
						Pattern p = Pattern.compile("^([a-z0-9]{8})$");
						Matcher m = p.matcher(kpass);
						// 入力チェック
						if("".equals(kname) || "".equals(kpass)){
							error = "名前とパスワードを入力してください";

						}else if(m.find()){
			KanrisyaDAO dao = new KanrisyaDAO();
			Kanrisya k=new Kanrisya();
			k.setKpassward(kpass);
			k.setKname(kname);
			k.setKlogin(1);
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


			int value[] = new int[2];
			value = dao.insertK(klist);
			String strKanriNumber = ("KA" + String.format("%06d", value[1]));

			ArrayList<Kanrisya> kuplist=new ArrayList<>();

			Kanrisya kup=new Kanrisya();
			kup.setKpassward(kpass);
			kup.setKname(kname);
			kup.setKlogin(1);
			kup.setRinsert(rInsert);
			kup.setRupdate(rUpdate);
			kup.setRdelete(rDelete);
			kup.setSinsert(sInsert);
			kup.setSupdate(sUpdate);
			kup.setSdelete(sDelete);
			kup.setKinsert(kInsert);
			kup.setKupdate(kUpdate);
			kup.setKdelete(kDelete);
			kup.setKseiid(value[1]);
			kup.setKanrinumber(strKanriNumber);
			kuplist.add(kup);
			int line=dao.updateK(kuplist);
			if(value[0]==0||line==0){
				error = "管理者の追加に失敗しました";
			}else{
				session.setAttribute("insertOK","insertOK");
			}
			}else{
				error = "パスワードは小文字の英数字で8文字入力してください";
			}


		}  catch (NamingException e) {
			// データベースにアクセスできなかった場合
			error = "追加に失敗しました";
			URL = "kanrisyakanrimenu.jsp";
			e.printStackTrace();

		} catch (SQLException e) {
			// 検索結果が取得できなかった場合
			error = "追加に失敗しました";
			URL = "kanrisyakanrimenu.jsp";
			e.printStackTrace();
		}catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		if (error != null) {
			session.setAttribute("error", error);
		}
		RequestDispatcher rd = request.getRequestDispatcher(URL);
		rd.forward(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse
			response) throws IOException, ServletException {
		doPost(request, response);
	}
}
