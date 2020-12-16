
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

import bean.Kanrisya;
import bean.Riyosya;
import bean.Shohin;
import dao.KanrisyaDAO;
import dao.RiyosyaDAO;

public class Login extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		try{
			HttpSession session = request.getSession();
			// リクエストパラメータ値を取得
			String strkainumber = request.getParameter("kainumber");
			String strPassward = request.getParameter("passward");

			// 入力チェック
			if("".equals(strkainumber) || "".equals(strPassward) || strkainumber == null || strPassward == null){
				// エラー情報をセッションに設定
				String strError = "error";
				session.setAttribute("error",strError );
				// ログイン画面へフォワード
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			// パスワードの入力文字チェックの準備
			Pattern p = Pattern.compile("^([a-z0-9]{8})$");
			Matcher m = p.matcher(strPassward);
			//DAOクラスのオブジェクトを生成
			RiyosyaDAO rDao = new RiyosyaDAO();
			KanrisyaDAO kDao = new KanrisyaDAO();

			// getSessionメソッドを使ってHttpSessionオブジェクトを取得
			List<Shohin> cart = (List<Shohin>)session.getAttribute("cart");
			// AAとKAを比較するため文字列を切り取る
			String strHikaku = strkainumber.substring(0, 2);

			if(m.find()){
				if("AA".equals(strHikaku)){
					// 戻り値を格納する変数を宣言
					List<Riyosya> rLoginList;
					// 会員番号とパスワードが一致しているかどうかのチェック
					rLoginList = rDao.searchKP(strkainumber, strPassward);
						// 一致した行が1行ある場合の処理
					if(rLoginList.size() == 1){
						// ログイン情報をセッションに設定
						String strLogin = "login";
						session.setAttribute("login",strLogin );
						// 取得した会員情報をセッションに設定
						session.setAttribute("rLoginList",rLoginList);
						// カートの中身の有無でフォワード先を変更
						if(cart != null){
							request.getRequestDispatcher("kaikei.jsp").forward(request, response);
						}else{
							// トップ画面へフォワード
							request.getRequestDispatcher("top.jsp").forward(request, response);
						}
					// 一致しなかった場合の処理
					}else{
						// エラー情報をセッションに設定
						String strError = "error";
						session.setAttribute("error",strError );
						// ログイン画面へフォワード
						request.getRequestDispatcher("login.jsp").forward(request, response);
					}
				}else if("KA".equals(strHikaku)){
					String klogin="NG";
					String rInsert="NG";
					String rUpdate="NG";
					String rDelete="NG";
					String sInsert="NG";
					String sUpdate="NG";
					String sDelete="NG";
					String kInsert="NG";
					String kUpdate="NG";
					String kDelete="NG";
					// 戻り値を格納する変数を宣言
					ArrayList<Kanrisya> klist=new ArrayList<>();
					// 管理番号とパスワードが一致しているかどうかのチェック
					klist=kDao.searchKKP(strkainumber,strPassward);
					for (Kanrisya kanrisya : klist) {

						if(kanrisya.getKlogin()==1){
							klogin="OK";
						}
						if(kanrisya.getRinsert()==1){
							rInsert="OK";
						}
						if(kanrisya.getRupdate()==1){
							rUpdate="OK";
						}
						if(kanrisya.getRdelete()==1){
							rDelete="OK";
						}
						if(kanrisya.getSinsert()==1){
							sInsert="OK";
						}
						if(kanrisya.getSupdate()==1){
							sUpdate="OK";
						}
						if(kanrisya.getSdelete()==1){
							sDelete="OK";
						}
						if(kanrisya.getKinsert()==1){
							kInsert="OK";
						}
						if(kanrisya.getKupdate()==1){
							kUpdate="OK";
						}
						if(kanrisya.getKdelete()==1){
							kDelete="OK";
						}
					}
					if(klist.size() == 1){
						// ログイン情報をセッションに設定
						String strLogin = "login";
						session.setAttribute("login",strLogin );
						// 取得した会員情報をセッションに設定
						session.setAttribute("kLoginList",klist);
						// ログインした管理者の権限をセッションに設定
						session.setAttribute("rInsert",rInsert);
						session.setAttribute("rUpdate",rUpdate);
						session.setAttribute("rDelete",rDelete);
						session.setAttribute("sInsert",sInsert);
						session.setAttribute("sUpdate",sUpdate);
						session.setAttribute("sDelete",sDelete);
						session.setAttribute("kInsert",kInsert);
						session.setAttribute("kUpdate",kUpdate);
						session.setAttribute("kDelete",kDelete);
						session.removeAttribute("login");

						// トップ画面へフォワード
						request.getRequestDispatcher("kanrimenu.jsp").forward(request, response);
					// 一致しなかった場合の処理
					}else{
						// エラー情報をセッションに設定
						String strError = "error";
						session.setAttribute("error",strError );
						// ログイン画面へフォワード
						request.getRequestDispatcher("login.jsp").forward(request, response);
					}
				}else{
					String strError = "error";
					session.setAttribute("error",strError );
					// ログイン画面へフォワード
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}

			}else{
				// エラー情報をセッションに設定
				String strError = "error";
				session.setAttribute("error",strError );
				// ログイン画面へフォワード
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}

		}catch(Exception e){
			e.printStackTrace(out);
		}
	}
}
