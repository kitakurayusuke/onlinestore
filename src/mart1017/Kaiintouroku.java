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

public class Kaiintouroku extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		try{
			// リクエストパラメータ値を取得
			String strRname = request.getParameter("rname");
			String strJusho = request.getParameter("jusho");
			String strAdress = request.getParameter("adress");
			String strRpassward = request.getParameter("rpassward");

			//DAOクラスのオブジェクトを生成
			RiyosyaDAO dao = new RiyosyaDAO();
			// getSessionメソッドを使ってHttpSessionオブジェクトを取得
			HttpSession session = request.getSession();

			// パスワードの入力文字チェックの準備
			Pattern p = Pattern.compile("^([a-z0-9]{8})$");
			Matcher m = p.matcher(strRpassward);

			// 入力チェック 空文字チェック
			if("".equals(strRname) || "".equals(strJusho) ||"".equals(strRpassward)){
				String strFlag = "error";
				session.setAttribute("flag",strFlag);
				// 会員登録画面へフォワード
				request.getRequestDispatcher("kaiintouroku.jsp").forward(request, response);
			// 入力チェック 文字数チェック
			}else if(strRpassward.length() != 8 || strRname.length() > 20 || strJusho.length() > 50 || strAdress.length() > 50){
				String strFlag = "error";
				session.setAttribute("flag",strFlag);
				// 会員登録画面へフォワード
				request.getRequestDispatcher("kaiintouroku.jsp").forward(request, response);
			}else if(m.find()){
			// 引数として渡すArrayListを作成
			ArrayList<Riyosya> insertList = new ArrayList<Riyosya>();
			Riyosya r = new Riyosya();
			r.setKainumber(null);
			r.setRname(strRname);
			r.setJusho(strJusho);
			r.setAdress(strAdress);
			r.setRpassward(strRpassward);
			insertList.add(r);

			// DBに登録をするメソッドの呼び出し
			int[] value = dao.insertR(insertList);
			// 登録が出来たらの処理
				if(value[0] == 1){
					// IDの取得
					int intId = value[1];
					// 会員番号の生成
					String strKainumber = ("AA" + String.format("%06d", intId));
					// 引数として渡すArrayListを作成
					ArrayList<Riyosya> updateList = new ArrayList<Riyosya>();
					r.setRseiid(intId);
					r.setKainumber(strKainumber);
					r.setRname(strRname);
					r.setJusho(strJusho);
					r.setAdress(strAdress);
					r.setRpassward(strRpassward);
					updateList.add(r);
					// DBを更新するメソッドの呼び出し
					int intLine = dao.updateR(updateList);
					if(intLine == 1){
						// ログインを実行
						List<Riyosya> rLoginList;
						rLoginList = dao.searchKP(strKainumber, strRpassward);
						if(rLoginList.size() == 1){
							session.setAttribute("rLoginList",rLoginList);
						}
						// ログイン情報をセッションに設定
						String strLogin = "login";
						session.setAttribute("login",strLogin );
						// セッション属性にデータを設定
						session.setAttribute("updateList",updateList );
						String strFlag = "tourokusumi";
						session.setAttribute("flag",strFlag);

						// 会員登録画面へフォワード
						request.getRequestDispatcher("kaiintouroku.jsp").forward(request, response);
					}
				}else{
					String strFlag = "error";
					session.setAttribute("flag",strFlag);
					// 会員登録画面へフォワード
					request.getRequestDispatcher("kaiintouroku.jsp").forward(request, response);
				}
			}else {
				String strFlag = "error";
				session.setAttribute("flag",strFlag);
				// 会員登録画面へフォワード
				request.getRequestDispatcher("kaiintouroku.jsp").forward(request, response);
			}

		}catch(Exception e){
			e.printStackTrace(out);
		}
	}
}
