<%@page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品レビュー</title>
<link rel="stylesheet" type="text/css" href="mart.css">
</head>
<body class="Body01">
<%@page import="javax.servlet.http.HttpSession,java.sql.Connection,bean.Shohin,java.util.List,bean.Review" %>
	<header class="Header01">
		<p>| 加盟店オーナー募集 | 採用情報 | アルバイト情報 | CSR | お問い合わせ |</p>
		<h1>１０☆１７Ｍａｒｔ</h1>
		<h4>ネットで注文　迅速配達</h4>
	</header>

	<div class="main01">
<% HttpSession Session = request.getSession(); %>
<%List<Shohin> rList = (List<Shohin>)session.getAttribute("rList"); %>
<%Object objFlag = session.getAttribute("Flag"); %>
<h1 class="AA011">みんなのクチコミ</h1>
<!-- 商品名を取得して表示 -->
<%Shohin s = rList.get(0); %>
<h3 class="AA011"><%=s.getSname() %></h3>
<div class="AA011">
<img src="../image/<%=s.getShashin() %>.jpg" width="320">
</div>
<form action="reviewinsert" method="post">
<table class="AA025">
	<tr>
		<td><textarea name="review" cols="40" rows="5" maxlength="200" required></textarea></td>
	</tr>
	<tr>
		<td class="AA007"><input type="submit" value="投稿"></td>
	</tr>
</table>
</form>
<!-- 投稿の処理が正常終了したら表示 -->
<%if("error".equals(objFlag)){ %>
<p class="AA008">クチコミに失敗しました。再度入力してください。</p>
<% }else if("success".equals(objFlag)){ %>
<p class="AA011">クチコミできました！</p>
<% } %>
<table class="AA009">
	<tr>
		<td><a href="kounyukakunin.jsp"><input class="AA002" type="button" value="続けてレビューを書く"></a></td>
		<td><a href="logout"><input class="AA002" type="button" value="ログアウト"></a></td>
		<td><a href="top.jsp"><input class="AA002" type="button" value="トップページに戻る"></a></td>
	</tr>
</table>

	<footer class="Footer01">
      <p>株式会社１０☆１７Ｍａｒｔ</p>
      <p>Copyright (C) 10-17MART CO.,LTD. All Rights Reserved.</p>
    </footer>


</body>
</html>