<%@page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<link rel="stylesheet" type="text/css" href="mart.css">
</head>
<body class="Body01">
<%@page import="javax.servlet.http.HttpSession" %>
<header class="Header01">
		<p>| 加盟店オーナー募集 | 採用情報 | アルバイト情報 | CSR | お問い合わせ |</p>
		<h1>１０☆１７Ｍａｒｔ</h1>
		<h4>ネットで注文　迅速配達</h4>
	</header>

	<div class="main01">

<hr>
<%// セッションに接続
HttpSession Session = request.getSession(); %>
<% // セッション属性からログイン情報を取得
Object objError = session.getAttribute("error");%>
<h1 class="AA011">ログイン</h1>
<!-- 新規登録ボタン 会員登録画面へ移動 -->
<table class="AA001">
	<tr>
		<td><a href="kaiintouroku.jsp"><input class="AA002" type="button" value="新規登録はこちら"></a></td>
	</tr>
</table>
<table class="AA012">
	<tr>
		<td><a href="top.jsp"><input class="AA002" type="button" value="トップページに戻る"></a></td>
	</tr>
</table>


<h3>会員番号・パスワードを入力してください。</h3>

<%if("error".equals(objError)){ %>
<h4 class="AA008">ログインできません。再度入力してください。</h4>
<% } %>

<form action="login" method="post">
<table>
	<tr>
		<td>会員番号</td>
		<td><input type="text" name="kainumber" maxlength="8" required></td>
	</tr>
	<tr>
		<td>パスワード</td>
		<td><input type="password" name="passward" maxlength="8" required></td>
		<td><input class="AA002" type="submit" value="ログイン" ></td>
	</tr>
</table>


</form>

</div>

	<footer class="Footer01">
      <p>株式会社１０☆１７Ｍａｒｔ</p>
      <p>Copyright (C) 10-17MART CO.,LTD. All Rights Reserved.</p>
    </footer>

</body>
</html>