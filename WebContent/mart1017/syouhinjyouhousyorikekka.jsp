<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品情報処理結果表示</title>
<%@page import="bean.Shohin,java.util.List"%>
<link rel="stylesheet" type="text/css" href="mart.css">
</head>

<body class="Body02">
	<header class="Header02">
		<p>１０☆１７Ｍａｒｔ</p>
	</header>

	<div class="main02">

		<p class="ka001 ka002 ka003 ka004">商品情報処理結果表示</p>
<%
		List<Shohin> list = (List<Shohin>) session.getAttribute("slist");
		String kakunin=(String)session.getAttribute("kakunin");
%>
		<table class="ka033">
<%		for (Shohin shohin : list) {	%>
			<tr><th class="ka011 ka012">商品ID</th>
				<td class="ka011"><%=shohin.getShoid()%></td></tr>
			<tr><th class="ka011 ka012">商品名</th>
				<td class="ka011"><%=shohin.getSname()%></td></tr>
			<tr><th class="ka011 ka012">大分類</th>
				<td class="ka011"><%=shohin.getDaib()%></td></tr>
			<tr><th class="ka011 ka012">中分類</th>
				<td class="ka011"><%=shohin.getChub()%></td></tr>
			<tr><th class="ka011 ka012">小分類</th>
				<td class="ka011"><%=shohin.getShob()%></td></tr>
			<tr><th class="ka011 ka012">単価</th>
				<td class="ka011"><%=shohin.getStanka()%></td></tr>
			<tr><th class="ka011 ka012">在庫数</th>
				<td class="ka011"><%=shohin.getZaikosuu()%></td></tr>
			<tr><th class="ka011 ka012">商品紹介</th>
				<td class="ka011 ka021"><%=shohin.getSshokai()%></td></tr>
			<tr><th class="ka011 ka012">商品説明</th>
				<td class="ka011 ka021"><%=shohin.getSsetsumei()%></td></tr>
			<tr><th class="ka011 ka012">写真のパス</th>
				<td class="ka011"><%=shohin.getShashin()%></td></tr>
<%		}	%>
		</table>

		<p class="ka001 ka004 ka005">上記内容で
<%			session.removeAttribute("slist");
			switch(kakunin){
			case "insert":	%>
				<%="追加"%>
<%				break;
			case "edit":	%>
				<%="更新"%>
<%				break;
			case "delete":	%>
				<%="削除"%>
<%				break;
			}	%>
			を行いました</p>

		<p class="ka001 ka006 ka008"><a href="syouhinkensaku.jsp" class="ka030">
			変更・削除を続ける</a></p>
		<p class="ka001 ka006 ka008"><a href="syouhintuika.jsp" class="ka030">
			追加を続ける</a></p>
		<p class="ka001 ka006 ka008"><a href="kanrimenu.jsp" class="ka030">
			商品管理メニューに戻る</a></p>

	</div>

	<footer class="Footer02">
		<p>株式会社１０☆１７Ｍａｒｔ</p>
		<p>Copyright (C) 10-17MART CO.,LTD. All Rights Reserved.</p>
	</footer>

</body>
</html>