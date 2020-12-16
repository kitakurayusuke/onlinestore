<%@page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ショッピングカート</title>
<link rel="stylesheet" type="text/css" href="mart.css">
</head>
<body class="Body01">

<%@page import="javax.servlet.http.HttpSession,java.sql.Connection,bean.Shohin,java.util.HashMap,
				java.util.Map,java.util.List,dao.ShohinDAO,java.util.ArrayList" %>

<header class="Header01">
		<p>| 加盟店オーナー募集 | 採用情報 | アルバイト情報 | CSR | お問い合わせ |</p>
		<h1>１０☆１７Ｍａｒｔ</h1>
		<h4>ネットで注文　迅速配達</h4>
	</header>

	<div class="main01">




<%// セッションに接続
HttpSession Session = request.getSession(); %>
<%List<Shohin> cart = (List<Shohin>)session.getAttribute("cart"); %>
<%Map<String,Integer> map = (Map<String,Integer>)session.getAttribute("map"); %>
<%Object objError = session.getAttribute("error"); %>
<%List<Shohin> errorList = (List<Shohin>)session.getAttribute("errorList"); %>
<% // セッション属性からログイン情報を取得
Object objLogin = session.getAttribute("login");%>
<%int intMax = 5; %>
<%int intAllBuy = 0; %>
<h1 class="AA500">ショッピングカート</h1>
<%for(Shohin s : cart){ %>
<table class="AA025">
	<tr>
		<td class="AA023"><%=s.getSname() %></td>
		<td class="AA024">１個：<%=s.getStanka() %>円</td>
		<%if(s.getZaikosuu() > intMax){ %>
			<%if(s.getZaikosuu() <= 0){ %>
			<td class="AA024">
			<form action="cartupdate" method="post">
				<select name="henkou" disabled>
				<%for(int i = 1; i <= intMax; i++){ %>
					<%if(map.get(s.getShoid()) == (Integer) i){ %>
						<option value="<%= i %>" selected ><%= i %></option>
					<% }else{ %>
						<option value="<%= i %>"><%= i %></option>
					<% } %>
				<% } %></select>
				<input type="hidden" name="hg" value="<%=s.getShoid() %>">
				<input type="submit" value="変更" disabled>
				</form>
			</td>
			<% }else{ %>
			<td class="AA024">
				<form action="cartupdate" method="post">
				<select name="henkou">
				<%for(int i = 1; i <= intMax; i++){ %>
					<%if(map.get(s.getShoid()) == (Integer) i){ %>
						<option value="<%= i %>" selected ><%= i %></option>
					<% }else{ %>
						<option value="<%= i %>"><%= i %></option>
					<% } %>
				<% } %></select>
				<input type="hidden" name="hg" value="<%=s.getShoid() %>">
				<input type="submit" value="変更">
				</form>
			</td>
			<% } %>
		<% }else{ %>
			<%if(s.getZaikosuu() <= 0){ %>
			<td class="AA024">
				<form action="cartupdate" method="post">
				<select name="henkou" disabled>
				<%for(int i = 1; i <= s.getZaikosuu(); i++){ %>
					<%if(map.get(s.getShoid()) == (Integer) i){ %>
						<option value="<%= i %>" selected ><%= i %></option>
					<% }else{ %>
						<option value="<%= i %>"><%= i %></option>
					<% } %>
				<% } %></select>
				<input type="hidden" name="hg" value="<%=s.getShoid() %>">
				<input type="submit" value="変更" disabled>
				</form>
			</td>
			<% }else{ %>
			<td class="AA024">
				<form action="cartupdate" method="post">
				<select name="henkou">
				<%for(int i = 1; i <= s.getZaikosuu(); i++){ %>
					<%if(map.get(s.getShoid()) == (Integer) i){ %>
						<option value="<%= i %>" selected ><%= i %></option>
					<% }else{ %>
						<option value="<%= i %>"><%= i %></option>
					<% } %>
				<% } %></select>
				<input type="hidden" name="hg" value="<%=s.getShoid() %>">
				<input type="submit" value="変更">
				</form>
			</td>
			<% } %>
		<% } %>
			<td class="AA024">
				<%int intBuy = map.get(s.getShoid()); %>
				<%int intPrice = s.getStanka(); %>
				<%int buyPrice = intPrice * intBuy; %>
				金額: <%=buyPrice %>円
			</td>
			<td class="AA024">
				<form action="cartdelete" method="post">
				<input type="hidden" name="sakujyo" value="<%=s.getShoid() %>">
				<input type="submit" value="カートから削除">
				</form>
			</td>
	</tr>
</table>
<% intAllBuy += buyPrice; %>
<% } %>
<hr>
<table class="AA025">
	<tr>
		<td class="AA023"></td>
		<td class="AA024"></td>
		<td class="AA024"></td>
		<td class="AA024">小計：<%=intAllBuy %>円</td>
		<td class="AA024"></td>
	</tr>
</table>

<%if("error".equals(objError)){ %>
	<%for(Shohin s : errorList){ %>
		<p class="AA008">申し訳ございません。<%=s.getSname() %> は在庫数量に達しております、もう一度数量を入力してください。</p>
	<% } %>
<% } %>
<table class="AA026">
	<tr>
		<td><a href="top.jsp"><input type="button" class="AA002" value="買い物を続ける" ></a></td>
		<!-- ログインしているかどうかで行先が変わる
		ログインしていなければログイン画面へ移動 -->
		<td>
			<%if("login".equals(objLogin)){ %>
			<a href="kaikei.jsp"><input type="button" class="AA002" value="レジへ"></a>
			<% }else{ %>
			<a href="login.jsp"><input type="button"class="AA002" value="レジへ"></a>
			<% } %>
		</td>
	</tr>
</table>
<table class="AA012">
	<tr><td></td></tr>
	<tr>
		<td><a href="top.jsp"><input class="AA002" type="button" value="トップ画面へ戻る"></a></td>
	</tr>
</table>
	</div>

	<footer class="Footer01">
      <p>株式会社１０☆１７Ｍａｒｔ</p>
      <p>Copyright (C) 10-17MART CO.,LTD. All Rights Reserved.</p>
    </footer>


</body>
</html>