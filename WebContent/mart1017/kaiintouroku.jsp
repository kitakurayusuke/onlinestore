<%@page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員登録</title>
<link rel="stylesheet" type="text/css" href="mart.css">
</head>
<body class="Body01">
<%@page import="javax.servlet.http.HttpSession,java.sql.Connection,bean.Riyosya,java.util.List,
						bean.Shohin,java.util.LinkedHashMap,java.util.Map" %>
	<header class="Header01">
		<p>| 加盟店オーナー募集 | 採用情報 | アルバイト情報 | CSR | お問い合わせ |</p>
		<h1>１０☆１７Ｍａｒｔ</h1>
		<h4>ネットで注文　迅速配達</h4>
	</header>

	<div class="main01">
<hr>
<%// セッションに接続
HttpSession Session = request.getSession(); %>
<% // セッション属性から会員情報を取得
List<Riyosya> updateList = (List<Riyosya>)session.getAttribute("updateList");%>
<%// セッション属性からカートの情報を取得
List<Shohin> cart = (List<Shohin>)session.getAttribute("cart"); %>
<%//入力エラー情報を取得
Object objFlag= session.getAttribute("flag"); %>
<h1 class="AA011">新規会員登録</h1>
<!-- カートの中身によって行先を変える -->
<%if(cart != null){ %>
<table class="AA012">
	<tr><td></td></tr>
	<tr>
		<td><a href="kaikei.jsp"><input class="AA002" type="button" value="進む"></a></td>
	</tr>
</table>
<% }else{ %>
<table class="AA012">
	<tr><td></td></tr>
	<tr>
	<tr>
		<td><a href="top.jsp"><input class="AA002" type="button" value="進む"></a></td>
	</tr>
</table>
<% } %>
<%if(updateList == null && objFlag == null){ %>
	<p>新規会員登録を行います。下記内容を入力してください。</p>
	<!-- 会員番号 登録前は無効、登録後表示される
	条件分岐で登録後/前を分ける メールアドレスは空白でも可-->
	<form action="kaiintouroku" method="post">
	<%//if会員登録前 %>
	<table>
		<tr>
			<td>会員番号</td>
			<td><input class="AA010" type="text" name="kainumber" size="66" disabled></td>
			<td></td>
		</tr>
		<tr>
			<td>氏名</td>
			<td><input class="AA010" type="text" name="rname" maxlength="20" size="66" required></td>
			<td></td>
		</tr>
		<tr>
			<td>住所</td>
			<td><textarea name="jusho" cols="50" maxlength="50" required></textarea></td>
			<td></td>
		</tr>
		<tr>
			<td>メールアドレス</td>
			<td><input class="AA010" type="email" name="adress" maxlength="50" size="66"></td>
			<td></td>
		</tr>
		<tr>
		<td>パスワード</td>
		<td><input class="AA010" type="text" name="rpassward" required maxlength="8" size="66"></td>
		<td><input class="AA002" type="submit" value="登録"></td>
	</table>
	</form>
<% }else if("error".equals(objFlag)){ %>
	<p class="AA008">入力値に誤りがあります。再度入力をしてください。</p>
	<form action="kaiintouroku" method="post">
		<table>
			<tr>
				<td>会員番号</td>
				<td><input class="AA010" type="text" name="kainumber" size="66" disabled></td>
				<td></td>
			</tr>
			<tr>
				<td>氏名</td>
				<td><input class="AA010" type="text" name="rname" maxlength="20" size="66" required></td>
				<td></td>
			</tr>
			<tr>
				<td>住所</td>
				<td><textarea name="jusho" cols="50" maxlength="50" required></textarea></td>
				<td></td>
			</tr>
			<tr>
				<td>メールアドレス</td>
				<td><input class="AA010" type="email" name="adress" maxlength="50" size="66"></td>
				<td></td>
			</tr>
			<tr>
				<td>パスワード</td>
				<td><input class="AA010" type="text" name="rpassward" required maxlength="8" size="66"></td>
				<td><input class="AA002" type="submit" value="登録"></td>
			</tr>
		</table>
	</form>
	<%session.removeAttribute("flag"); %>
<% }else if("tourokusumi".equals(objFlag)){ %>
	<!-- メッセージの表示欄 条件分岐で完了/エラーで分ける
	会員登録後は初期値が登録内容となる処理を書く -->
	<%Riyosya r = updateList.get(0); %>
	<table>
		<tr>
			<td>会員番号</td>
			<td><input class="AA010" type="text" name="kainumber"
								value="<%=r.getKainumber() %>" size="66" disabled></td>
		</tr>
		<tr>
			<td>氏名</td>
			<td><input class="AA010" type="text" name="rname" maxlength="20"
								value="<%=r.getRname() %> " size="66" disabled></td>
		</tr>
		<tr>
			<td>住所</td>
			<td><textarea name="jusho" cols="50" maxlength="50" disabled><%=r.getJusho() %></textarea></td>
		</tr>
		<tr>
			<td>メールアドレス</td>
			<td><input class="AA010" type="email" name="adress" maxlength="50"
								value="<%=r.getAdress() %>" size="66" disabled></td>
		</tr>
		<tr>
			<td>パスワード</td>
			<td><input class="AA010" type="text" name="rpassward"
								value="<%=r.getRpassward() %>" size="66" disabled></td>
		</tr>
	</table>
	上記内容で登録しました。
	<%session.removeAttribute("updateList"); %>
	<%session.removeAttribute("flag"); %>
<% } %>


</div>

	<footer class="Footer01">
      <p>株式会社１０☆１７Ｍａｒｔ</p>
      <p>Copyright (C) 10-17MART CO.,LTD. All Rights Reserved.</p>
    </footer>

</body>
</html>