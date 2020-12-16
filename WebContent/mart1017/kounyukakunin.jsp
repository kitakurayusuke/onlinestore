<%@page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購入確認</title>
<link rel="stylesheet" type="text/css" href="mart.css">
</head>
<body class="Body01">
<%@page import="javax.servlet.http.HttpSession,java.sql.Connection,bean.Shohin,
						java.util.HashMap,java.util.Map,java.util.List,dao.ShohinDAO,bean.Riyosya,dao.RiyosyaDAO" %>
<header class="Header01">
		<p>| 加盟店オーナー募集 | 採用情報 | アルバイト情報 | CSR | お問い合わせ |</p>
		<h1>１０☆１７Ｍａｒｔ</h1>
		<h4>ネットで注文　迅速配達</h4>
	</header>

	<div class="main01">
<%HttpSession Session = request.getSession(); %>
<%List<Shohin> cart = (List<Shohin>)session.getAttribute("cart"); %>
<%Map<String,Integer> map = (Map<String,Integer>)session.getAttribute("map"); %>
<% // セッション属性から会員情報を取得
List<Riyosya> loginList = (List<Riyosya>)session.getAttribute("rLoginList"); %>
<%Riyosya r = loginList.get(0); %>
<table>
	<tr>
		<td>会員番号</td>
		<td><input type="text" name="id" value="<%=r.getKainumber() %>" size="66" disabled></td>
	</tr>
	<tr>
		<td>氏名</td>
		<td><input type="text" name="name" value="<%=r.getRname() %>" size="66" disabled></td>
	</tr>
	<tr>
		<td>お届け先</td>
		<td><textarea name="jusho" cols="50" maxlength="50" disabled><%=r.getJusho() %></textarea></td>
	</tr>
	<tr>
		<td>メールアドレス</td>
		<td><input type="text" name="mail" value="<%=r.getAdress() %>" size="66" disabled></td>
	</tr>
	<tr><td>　　</td></tr>
</table>
<!-- カートの中身を表示 -->
<%int intMax = 5; %>
<%int intAllBuy = 0; %>
<%int intCount = 0; %>
<%for(Shohin c : cart){ %>
<table>
	<tr>
		<td class="AA013">
			<!-- 商品名：商品説明 -->
			<%=c.getSname() %>
		</td>
		<td class="AA014">
			<!-- 単価 -->
			１個：<%=c.getStanka() %>円
		</td>
		<td class="AA015">
			<!-- 数量  -->
			<%=map.get(c.getShoid()) %>個
		</td>
		<td class="AA014">
			<%int intBuy = map.get(c.getShoid()); %>
			<%int intPrice = c.getStanka(); %>
			<%int buyPrice = intPrice * intBuy; %>
			<%=buyPrice %>円
		</td>
		<td>
			<form action="reviewjyunbi" method="post">
			<input type="hidden" name="kuchikomi" value="<%=c.getShoid() %>">
			<input type="submit" value="商品レビューを書く">
			</form>
		</td>
		</tr>
</table>
<% intAllBuy += buyPrice; %>
<% } %>
<hr>
<%int intSouryou = 100; %>
<%int intShiharai = intAllBuy + intSouryou; %>
<table class="AA016">
	<tr>
		<td class="AA018">商品購入価格</td>
		<td class="AA018"><%=intAllBuy %>円</td>
		<td class="AA017"></td>
	</tr>
	<tr>
		<td>送料</td>
		<td><%=intSouryou %>円</td>
		<td></td>
	</tr>
	<tr>
		<td>お支払い金額</td>
		<td><%=intShiharai %>円</td>
		<td></td>
	</tr>
</table>

<h3>上記注文を承りました。商品の到着をお待ちください。</h3>
<table class="AA012">
	<tr>
		<td><a href="logout"><input class="AA002" type="button" value="ログアウト"></a></td>
		<td><a href="top.jsp"><input class="AA002" type="button" value="トップページに戻る"></a></td>
	<tr>
</table>
</div>

	<footer class="Footer01">
      <p>株式会社１０☆１７Ｍａｒｔ</p>
      <p>Copyright (C) 10-17MART CO.,LTD. All Rights Reserved.</p>
    </footer>


</body>
</html>