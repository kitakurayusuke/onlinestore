<%@page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品詳細</title>
<link rel="stylesheet" type="text/css" href="mart.css">
<link href="https://cdnjs.cloudflare.com/ajax/libs/lightbox2/2.7.1/css/lightbox.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/lightbox2/2.7.1/js/lightbox.min.js" type="text/javascript"></script>
</head>
<body class="Body01">
<%@page import="javax.servlet.http.HttpSession,java.sql.Connection,bean.Shohin,java.util.List,bean.Review" %>
<header class="Header01">
		<p>| 加盟店オーナー募集 | 採用情報 | アルバイト情報 | CSR | お問い合わせ |</p>
		<h1>１０☆１７Ｍａｒｔ</h1>
		<h4>ネットで注文　迅速配達</h4>
	</header>

	<div class="main01">

<hr>
<%// セッションに接続
HttpSession Session = request.getSession(); %>
<%List<Shohin> sList = (List<Shohin>)session.getAttribute("slist"); %>
<%List<Review> reviewList = (List<Review>)session.getAttribute("reviewList"); %>
<%int intMax = 5; %>
<!-- 商品名：見出しはセッションで検索してDBからデータを取得 -->
<%Shohin s = sList.get(0); %>
<h1><%=s.getSname() %></h1>
<table class="AA518">
	<tr>
		<td><a href="../image/<%=s.getShashin() %>.jpg" data-lightbox="group">
		<img src="../image/<%=s.getShashin() %>.jpg" width="320"></a></td>
	</tr>
	<tr>
		<td>画像をクリックで拡大します</td>
	</tr>
</table>
<table>
	<tr>
		<td><%=s.getSshokai() %></td>
	</tr>
	<tr>
		<td><%=s.getSsetsumei() %></td>
	</tr>
	<tr>
		<td>1個<%=s.getStanka() %>円</td>
	</tr>
</table>
<!-- セレクトボックスのオプションは在庫数または5個までの制御を入れる -->
<%if(s.getZaikosuu() > intMax){ %>
	<%if(s.getZaikosuu() > 0){ %>
		<form action="cartadd" method="post">
		<table>
			<tr>
				<td>数量を選択してください</td>
				<td><select name="suryou">
				<%for(int i = 1; i <= intMax; i++){ %>
					<option value="<%= i %>"><%= i %></option>
				<% } %>
				</select></td>
			</tr>
			<tr>
				<td><input class="AA002" type="submit" value="カートに入れる"></td>
			</tr>
		</table>
		</form>
	<% }else{ %>
		<form action="cartadd" method="post">
		<table>
			<tr>
				<td>数量を選択してください</td>
				<td><select name="suryou" disabled>
				<%for(int i = 1; i <= intMax; i++){ %>
					<option value="<%= i %>"><%= i %></option>
				<% } %>
				</select></td>
			<tr>
				<td><input class="AA002" type="submit" value="カートに入れる" disabled></td>
			</tr>
		</table>
		</form>
	<% } %>
<% }else{ %>
	<%if(s.getZaikosuu() > 0){ %>
		<form action="cartadd" method="post">
		<table>
			<tr>
				<td>数量を選択してください</td>
				<td><select name="suryou">
				<%for(int i = 1; i <= s.getZaikosuu(); i++){ %>
					<option value="<%= i %>"><%= i %></option>
				<% } %></select></td>
			</tr>
			<tr>
				<td><input class="AA002" type="submit" value="カートに入れる"></td>
			</tr>
		</table>
		</form>
	<% }else{ %>
		<form action="cartadd" method="post">
		<table>
			<tr>
				<td>数量を選択してください</td>
				<td><select name="suryou" disabled>
				<%for(int i = 1; i <= s.getZaikosuu(); i++){ %>
					<option value="<%= i %>"><%= i %></option>
				<% } %></select></td>
			</tr>
			<tr>
				<td><input class="AA002" type="submit" value="カートに入れる" disabled></td>
			</tr>
		</table>
		</form>
	<% } %>
<% } %>
<a href="syouhinkensakukekka.jsp"><input class="AA002" type="button" value="検索結果へ戻る"></a>
<%if(reviewList.size() != 0){ %>
<table>
	<tr>
		<td><h2>みんなのクチコミ</h2></td>
			<!-- DBから商品レビューを表示 レビューの無いものは「クチコミがありません」と表示させる -->
			<%for(Review r : reviewList){ %>
	</tr>
	<tr>
			<td><%=r.getReview() %></td>
	</tr>
			<% } %>
		<% } %>
</table>

<table class="AA022">
	<tr><td></td></tr>
	<tr>
		<td
		><a href="top.jsp"><input class="AA002" type="button" value="トップ画面へ戻る"></a></td>
	</tr>
</table>

</div>

	<footer class="Footer01">
      <p>株式会社１０☆１７Ｍａｒｔ</p>
      <p>Copyright (C) 10-17MART CO.,LTD. All Rights Reserved.</p>
    </footer>
</body>
</html>