<%@page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会計</title>
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
<h1>お会計</h1>
<%Riyosya r = loginList.get(0); %>
<table>
	<tr>
		<td>会員番号</td>
		<td><input type="text" name="id" value="<%=r.getKainumber() %>" size="60" disabled></td>
	</tr>
	<tr>
		<td>氏名</td>
		<td><input type="text" name="name" value="<%=r.getRname() %>" size="60" disabled></td>
	</tr>
	<tr>
		<td>お届け先</td>
		<td><textarea name="address" cols="46"  disabled><%=r.getJusho() %></textarea></td>
	</tr>
	<tr>
		<td>メールアドレス</td>
		<td><input type="text" name="mail" value="<%=r.getAdress() %>" size="60" disabled></td>
		<td><a href="kaiinjyouhouhenkou.jsp"><input type="button" value="会員情報変更はこちら"></a></td>
	</tr>
	<tr><td>　　</td></tr>
</table>
<!-- カートの中身を表示 -->
<%int intMax = 5; %>
<%int intAllBuy = 0; %>
<%int intCount = 0; %>
<%for(Shohin s : cart){ %>
<table>
	<tr>
		<td class="AA013"><%=s.getSname() %></td>
		<td class="AA014">１個：<%=s.getStanka() %>円</td>
		<td class="AA015"><%=map.get(s.getShoid()) %>個</td>
		<td class="AA014">
			<%int intBuy = map.get(s.getShoid()); %>
			<%int intPrice = s.getStanka(); %>
			<%int buyPrice = intPrice * intBuy; %>
			<%=buyPrice %>円
		</td>
		<td> </td>
		</tr>
</table>
<% intAllBuy += buyPrice; %>
<% } %>
<hr>
<table>
	<tr>
		<td class="AA013"> </td>
		<td class="AA015"> </td>
		<td class="AA014">商品購入価格</td>
		<td><%=intAllBuy %>円</td>
	</tr>
	<tr>
		<td class="AA013"> </td>
		<td class="AA015"> </td>
		<td class="AA015">送料</td>
		<td>100円</td>
	</tr>
	<tr>
		<%int intSouryou = 100; %>
		<%int intShiharai = intAllBuy + intSouryou; %>
		<td class="AA013"> </td>
		<td class="AA015"> </td>
		<td class="AA014">お支払い金額</td>
		<td><%=intShiharai %>円</td>
	</tr>
</table>
<table class="AA025">
	<tr>
		<td class="AA013"><a href="top.jsp"><input class="AA002" type="button" value="お買い物を続ける"></a></td>
		<td class="AA013"><a href="kaatonakami.jsp"><input class="AA002" type="button" value="カートに戻る"></a></td>
		<td class="AA013"><a href="kounyuu"><input class="AA002" type="button" value="購入確定"></a></td>
	</tr>
</table>
<table class="AA016">
	<tr>
		<td><a href="top.jsp"><input class="AA002" type="button" value="トップページに戻る"></a></td>
	</tr>
</table>

</div>

	<footer class="Footer01">
      <p>株式会社１０☆１７Ｍａｒｔ</p>
      <p>Copyright (C) 10-17MART CO.,LTD. All Rights Reserved.</p>
    </footer>

</body>
</html>