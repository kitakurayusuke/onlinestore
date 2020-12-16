package mart1017;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Riyosya;
import dao.RiyosyaDAO;

public class Kaiinhenkou extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		try{
			// リクエストパラメータ値を取得
			String strKainumber = request.getParameter("kainumber");
			String strRname = request.getParameter("rname");
			String strJusho = request.getParameter("jusho");
			String strAdress = request.getParameter("adress");
			String strRpassward = request.getParameter("rpassward");
			//DAOクラスのオブジェクトを生成
			RiyosyaDAO dao = new RiyosyaDAO();
			// getSessionメソッドを使ってHttpSessionオブジェクトを取得
			HttpSession session = request.getSession();
			// 利用者IDの取得
			List<Riyosya> loginList = (List<Riyosya>)session.getAttribute("rLoginList");
			int intId = 0;
			Riyosya b = loginList.get(0);
			intId = b.getRseiid();

			// パスワードの入力文字チェックの準備
			Pattern p = Pattern.compile("^([a-z0-9]{8})$");
			Matcher m = p.matcher(strRpassward);

			// 引数として渡すArrayListを作成
			ArrayList<Riyosya> updateList = new ArrayList<Riyosya>();

			//入力チェック
			if("".equals(strRname) || "".equals(strJusho) || "".equals(strRpassward)){
				String strError = "error";
				session.setAttribute("flag",strError);
				request.getRequestDispatcher("kaiinjyouhouhenkou.jsp").forward(request, response);
			}else if(strKainumber.length() > 9 || strRname.length() > 20 ||strJusho.length() > 50 || strAdress.length() > 50 ||strRpassward.length() != 8 ){
				String strError = "error";
				session.setAttribute("flag",strError);
				request.getRequestDispatcher("kaiinjyouhouhenkou.jsp").forward(request, response);
			}else if(m.find()){
				Riyosya r = new Riyosya();
				r.setRseiid(intId);
				r.setKainumber(strKainumber);
				r.setRname(strRname);
				r.setJusho(strJusho);
				r.setAdress(strAdress);
				r.setRpassward(strRpassward);
				updateList.add(r);
			}else{
				String strError = "error";
				session.setAttribute("flag",strError);
				request.getRequestDispatcher("kaiinjyouhouhenkou.jsp").forward(request, response);
			}
			//会員情報を変更するメソッドの呼び出し updateR(updateList);この形で呼び出しに変更
				int henkouLine = dao. updateR(updateList);
			//会員情報変更出来た場合の処理
				if(henkouLine == 1){
					//変更後の会員情報を取得
					List<Riyosya> henkousumiList = dao.searchKP(strKainumber, strRpassward);
					//取得した情報をセッションに設定
					session.setAttribute("henkousumiList",henkousumiList);
					//変更済フラグをセッション属性に設定
					String strHenkousumi = "henkousumi";
					session.setAttribute("flag",strHenkousumi);
					session.setAttribute("rLoginList",henkousumiList);
				}else{
					String strError = "error";
					session.setAttribute("flag",strError);
					request.getRequestDispatcher("kaiinjyouhouhenkou.jsp").forward(request, response);
				}
			// 会員情報変更画面へフォワード
			request.getRequestDispatcher("kaiinjyouhouhenkou.jsp").forward(request, response);

		}catch(Exception e){
			e.printStackTrace(out);
		}
	}
}
