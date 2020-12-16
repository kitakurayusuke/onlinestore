<%@page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トップページ</title>
<link rel="stylesheet" type="text/css" href="mart.css">
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body class="Body01">
<%@page import="javax.servlet.http.HttpSession,java.sql.Connection,bean.Riyosya,java.util.List,bean.Kanrisya" %>

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
Object objLogin = session.getAttribute("login");%>
<% // セッションから情報を取得
List<Riyosya> rloginList = (List<Riyosya>)session.getAttribute("rLoginList"); %>
<%List<Kanrisya> kloginlist = (List<Kanrisya>)session.getAttribute("kLoginList"); %>
<%  session.removeAttribute("bunrui");
	session.removeAttribute("keyword");
	session.removeAttribute("subete");
	session.removeAttribute("sortlist");%>
	<!-- ログインボタン ログイン画面へ移動 -->
<%if(rloginList == null){ %>
	<table class="AA001">
		<tr>
			<td><a href="login.jsp" class="AA002">ログイン/新規登録</a></td>
		</tr>
	</table>
<% }else if("login".equals(objLogin)){ %>
<!-- ログイン時のみ表示。苗字はDB参照。 -->
	<%if(rloginList != null){ %>
	<table class="AA001">
		<tr>
	<% Riyosya r = rloginList.get(0); %>
			<td><h4>いらっしゃいませ、<%=r.getRname() %>さん！</h4></td>
		</tr>
		<tr>
			<td><a href="logout"><input class="AA002" type="button" value="ログアウト"></a></td>
		</tr>
		<tr>
			<td><a href="kaiinjyouhouhenkou.jsp"><input class="AA002" type="button" value="会員情報変更"></a></td>
		</tr>
	</table>
	<% } %>

<% } %>
<form action="shohinSearch" method="post">
<select class="AA003" name="bunrui">
<option value="%">全て</option>
<option value="食品">食品</option>
<option value="日用品">日用品</option>
<option value="飲料">飲料</option>
<option value="ヘルス・ビューティ用品">ヘルス・ビューティ用品</option>
<option value="マタニティ・ベビー用品">マタニティ・ベビー用品</option>
</select>
<input class="AA004" type="search" name="keyword">
<input class="AA005" type="submit" value="検索">
</form>
<!-- 商品の写真と商品名。写真と商品名がリンクになる。 -->
<table class="AA006">
	<tr>
		<td>
			<p><img src="../image/S000002.jpg" alt="S000002" width="320" height="240"></p>
			<p>ホットコーヒーR 豊かな香りと、深い味わい</p>
		</td>
		<td>
			<p><img src="../image/S000026.jpg" alt="S000026" width="320" height="240"></p>
			<p>がっつり牛焼肉＆豚スタミナ弁当</p>
		</td>
			<td>
			<p><img src="../image/S000034.jpg" alt="S000034" width="320" height="240"></p>
			<p>まんぷく若鶏のチキンステーキ弁当</p>
		</td>
	</tr>
</table>



	</div>

	<footer class="Footer01">
      <p>株式会社１０☆１７Ｍａｒｔ</p>
      <p>Copyright (C) 10-17MART CO.,LTD. All Rights Reserved.</p>
    </footer>

</body>
</html>