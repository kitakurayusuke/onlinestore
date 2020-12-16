<%@page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員情報変更</title>
<link rel="stylesheet" type="text/css" href="mart.css">
</head>
<body class="Body01">
<%@page import="javax.servlet.http.HttpSession,java.sql.Connection,bean.Riyosya,java.util.List,
							bean.Shohin,java.util.LinkedHashMap,java.util.Set" %>

	<header class="Header01">
		<p>| 加盟店オーナー募集 | 採用情報 | アルバイト情報 | CSR | お問い合わせ |</p>
		<h1>１０☆１７Ｍａｒｔ</h1>
		<h4>ネットで注文　迅速配達</h4>
	</header>

	<div class="main01">

<h1 class="AA011">会員情報変更</h1>
<hr>
<%// セッションに接続
HttpSession Session = request.getSession(); %>
<% // セッション属性からログイン情報を取得
Object objLogin = session.getAttribute("login");%>
<% // セッション属性から変更前会員情報を取得
List<Riyosya> loginList = (List<Riyosya>)session.getAttribute("rLoginList");%>
<% //セッション属性から変更済会員情報を取得
List<Riyosya> henkousumiList = (List<Riyosya>)session.getAttribute("henkousumiList");%>
<%// セッション属性からカートの情報を取得
List<Shohin> cart = (List<Shohin>)session.getAttribute("cart"); %>
<% // セッション属性から変更済フラグを取得
Object objFlag = session.getAttribute("flag"); %>
<!-- カートの中身によって行先を変える -->
<%if(cart != null){ %>
<table class="AA012">
	<tr><td></td></tr>
	<tr>
		<td><a href="kaikei.jsp"><input class="AA002" type="button" value="ひとつ前に戻る"></a></td>
	</tr>
</table>
<% }else{ %>
<table class="AA012">
	<tr><td></td></tr>
	<tr>
		<td><a href="top.jsp"><input class="AA002" type="button" value="ひとつ前に戻る"></a></td>
	</tr>
</table>
<% } %>
<%if(objFlag == null){ %>
	<h4>会員情報の変更を行います。変更内容を入力してください。</h4>
	<!-- 情報はDBから取得して表示 -->
	<%Riyosya r = loginList.get(0); %>
	<form action="kaiinhenkou" method="post">
	<table>
		<tr>
			<td>会員番号</td>
			<td><input class="AA010" type="text" name="kainumber" value="<%=r.getKainumber() %>" size="50" readonly></td>
			<td></td>
		</tr>
		<tr>
			<td>氏名</td>
			<td><input class="AA010" type="text" name="rname" maxlength="20"  value="<%=r.getRname() %>" size="50" required></td>
			<td></td>
		</tr>
		<tr>
			<td>住所</td>
			<td><input class="AA010" type="text" name="jusho" maxlength="50" value="<%=r.getJusho() %>" size="50" required></td>
			<td></td>
		</tr>
		<tr>
			<td>メールアドレス</td>
			<td><input class="AA010" type="email" name="adress" maxlength="50" value="<%=r.getAdress() %>" size="50"></td>
			<td></td>
		</tr>
		<tr>
		<td>パスワード</td>
		<td><input class="AA010" type="text" name="rpassward" value="<%=r.getRpassward() %>" maxlength="8" size="50" required></td>
		<td><input class="AA002" type="submit" value="変更"></td>
		</tr>
	</table>
	</form>
<!-- メッセージの表示欄 条件分岐で完了/エラーで分ける -->
<% }else if("henkousumi".equals(objFlag)){ %>
	<%Riyosya r = henkousumiList.get(0); %>
	<table class="AA010">
		<tr>
			<td>会員番号</td>
			<td><input class="AA010" type="text" name="kainumber" value="<%=r.getKainumber() %>" size="50" disabled></td>
		</tr>
		<tr>
			<td>氏名</td>
			<td><input class="AA010" type="text" name="rname" maxlength="20" value="<%=r.getRname() %>" size="50" disabled></td>
		</tr>
		<tr>
			<td>住所</td>
			<td><input class="AA010" type="text" name="jusho" maxlength="50" value="<%=r.getJusho() %>" size="50" disabled></td>
		</tr>
		<tr>
			<td>メールアドレス</td>
			<td><input class="AA010" type="email" name="adress" maxlength="50" value="<%=r.getAdress() %>" size="50" disabled></td>
		</tr>
		<tr>
			<td>パスワード</td>
			<td><input class="AA010" type="text" name="rpassward" value="<%=r.getRpassward() %>" maxlength="8" size="50" disabled></td>
	</table>
		<h4>上記内容で変更しました。</h4>

		<%session.removeAttribute("flag"); %>

<% }else if("error".equals(objFlag)){ %>
<p class="AA008">入力値に誤りがあります。再入力してください。</p>
	<%Riyosya r = loginList.get(0);  %>
	<form action="kaiinhenkou" method="post">
	<table class="AA010">
		<tr>
			<td>会員番号</td>
			<td><input class="AA010" type="text" name="kainumber" value="<%=r.getKainumber() %>" size="50" required></td>
			<td></td>
		</tr>
		<tr>
			<td>氏名</td>
			<td><input class="AA010" type="text" name="rname" maxlength="20" value="<%=r.getRname() %>" size="50" required></td>
			<td></td>
		</tr>
		<tr>
			<td>住所</td>
			<td><input class="AA010" type="text" name="jusho" maxlength="50" value="<%=r.getJusho() %>" size="50" required></td>
			<td></td>
		</tr>
		<tr>
			<td>メールアドレス</td>
			<td><input class="AA010" type="email" name="adress" maxlength="50" value="<%=r.getAdress() %>" size="50" ></td>
			<td></td>
		</tr>
		<tr>
		<td>パスワード</td>
		<td><input class="AA010" type="text" name="rpassward" value="<%=r.getRpassward() %>" required maxlength="8" size="50"></td>
		<td><input class="AA002" type="submit" value="変更"></td>
		</tr>
	</table>
	</form>
	<% } %>
	<%session.removeAttribute("flag"); %>

</div>

	<footer class="Footer01">
      <p>株式会社１０☆１７Ｍａｒｔ</p>
      <p>Copyright (C) 10-17MART CO.,LTD. All Rights Reserved.</p>
    </footer>

</body>
</html>