<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品一覧</title>
<%@page import="bean.Shohin,dao.ShohinDAO,java.util.List"%>
<link rel="stylesheet" type="text/css" href="mart.css">
</head>

<body class="Body02">
	<header class="Header02">
		<p>１０☆１７Ｍａｒｔ</p>
	</header>

	<div class="main02">
		<p class="ka001 ka002 ka003 ka004">商品一覧</p>
<%
		ShohinDAO dao = new ShohinDAO();
		List<Shohin> list = dao.searchAllS();
%>
		<table class="ka025">
			<tr>
				<th class="ka022">商品ID</th>
				<th class="ka013">単価</th>
				<th class="ka013">在庫数</th>
				<th></th>
				<th rowspan="2" class="ka022 ka026 ka027 ka028">商品紹介</th>
				<th rowspan="2" class="ka022 ka026 ka027 ka028">商品説明</th>
				<th class="ka022">大分類</th>
				<th class="ka022">中分類</th>
				<th class="ka022">小分類</th>
			</tr>
			<tr>
				<th colspan="4" class="ka022 ka026 ka028">商品名</th>
				<th colspan="3" class="ka022 ka028">写真パス</th>
			</tr>

<%			for (Shohin shohin : list) {	%>
			<tr>
				<td class="ka022"><%=shohin.getShoid()%></td>
				<td class="ka013"><%=shohin.getStanka()%></td>
				<td class="ka013"><%=shohin.getZaikosuu()%></td>
				<td></td>
				<td rowspan="2" class="ka021 ka022 ka029"><%=shohin.getSshokai()%></td>
				<td rowspan="2" class="ka021 ka022 ka029"><%=shohin.getSsetsumei()%></td>
				<td class="ka021 ka022"><%=shohin.getDaib()%></td>
				<td class="ka021 ka022"><%=shohin.getChub()%></td>
				<td class="ka021 ka022"><%=shohin.getShob()%></td>
			</tr>
			<tr>
				<td colspan="4" class="ka004 ka009 ka022 ka029 ka032"><%=shohin.getSname()%></td>
				<td colspan="3" class="ka022 ka029"><%=shohin.getShashin()%></td>
			</tr>
<%			}	%>
		</table>

		<p class="ka001 ka006 ka008">
			<a href="syouhinkanrimenu.jsp" class="ka030">商品管理メニューに戻る</a>
		</p>
	</div>

	<footer class="Footer02">
		<p>株式会社１０☆１７Ｍａｒｔ</p>
		<p>Copyright (C) 10-17MART CO.,LTD. All Rights Reserved.</p>
	</footer>

</body>
</html>