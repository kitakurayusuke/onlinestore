<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品変更</title>
<%@page import="bean.Shohin,java.util.List"%>
<link rel="stylesheet" type="text/css" href="mart.css">
</head>

<body class="Body02">
	<header class="Header02">
		<p>１０☆１７Ｍａｒｔ</p>
	</header>

	<div class="main02">

		<p class="ka001 ka002 ka003 ka004">商品変更</p>
		<p class="ka001 ka005 ka032">商品登録内容の変更処理を行います。下記内容を変更してください。</p>
<%
		List<Shohin> list = (List<Shohin>) session.getAttribute("slist");
		String error=(String)request.getAttribute("error");
%>
		<form action="syouhinEdit"method="post">
			<table class="ka010">
<%			for (Shohin shohin : list) {	%>
				<tr><th class="ka011 ka012">商品ID</th>
					<td class="ka011 ka015"><input type="text" name="sid" class="ka015"
						value="<%=shohin.getShoid()%>" readonly></td></tr>
				<tr><th class="ka011 ka012">商品名</th>
					<td class="ka011 ka015"><input type="text" name="sname" class="ka015 ka016"
						value="<%=shohin.getSname()%>"></td></tr>
				<tr><th class="ka011 ka012">単価</th>
					<td class="ka011 ka015"><input type="text" name="price" class="ka015"
						value="<%=shohin.getStanka()%>"></td></tr>
				<tr><th class="ka011 ka012">在庫数</th>
					<td class="ka011 ka015"><input type="text" name="zaiko" class="ka015"
						value="<%=shohin.getZaikosuu()%>"></td></tr>
				<tr><th class="ka011 ka012">大分類</th>
					<td class="ka011 ka015"><select name="dai">
						<option value="食品" <% if ("食品".equals(shohin.getDaib())){ %>selected<% } %>>食品</option>
						<option value="飲料"<% if ("飲料".equals(shohin.getDaib())){ %>selected<% } %>>飲料</option>
						<option value="日用品" <% if ("日用品".equals(shohin.getDaib())){ %>selected<% } %>>日用品</option>
						<option value="ヘルス・ビューティ用品"<% if ("ヘルス・ビューティ用品".equals(shohin.getDaib())){ %>selected<% } %>>ヘルス・ビューティ用品</option>
						<option value="マタニティ・ベビー用品" <% if ("マタニティ・ベビー用品".equals(shohin.getDaib())){ %>selected<% } %>>マタニティ・ベビー用品</option>
						</select>
					</td></tr>
				<tr><th class="ka011 ka012">中分類</th>
					<td class="ka011 ka015"><select name="tyuu">
						<option value="食品"<% if ("食品".equals(shohin.getChub())){ %>selected<% } %>>食品</option>
						<option value="飲料"<% if ("飲料".equals(shohin.getChub())){ %>selected<% } %>>飲料</option>
						<option value="日用品"<% if ("日用品".equals(shohin.getChub())){ %>selected<% } %>>日用品</option>
						<option value="ヘルス・ビューティ用品"<% if ("ヘルス・ビューティ用品".equals(shohin.getChub())){ %>selected<% } %>>ヘルス・ビューティ用品用具</option>
						<option value="マタニティ・ベビー用品" <% if ("マタニティ・ベビー用品".equals(shohin.getChub())){ %>selected<% } %>>マタニティ・ベビー用品</option>
						</select>
					</td></tr>
				<tr><th class="ka011 ka012">小分類</th>
					<td class="ka011 ka015"><select name="syou">
						<option value="食品"<% if ("食品".equals(shohin.getShob())){ %>selected<% } %>>食品</option>
						<option value="飲料"<% if ("飲料".equals(shohin.getShob())){ %>selected<% } %>>飲料</option>
						<option value="日用品"<% if ("日用品".equals(shohin.getShob())){ %>selected<% } %>>日用品</option>
						<option value="ヘルス・ビューティ用品"<% if ("ヘルス・ビューティ用品".equals(shohin.getShob())){ %>selected<% } %>>ヘルス・ビューティ用品用具</option>
						<option value="マタニティ・ベビー用品" <% if ("マタニティ・ベビー用品".equals(shohin.getShob())){ %>selected<% } %>>マタニティ・ベビー用品</option>
						</select>
					</td></tr>
				<tr><th class="ka011 ka012">商品紹介</th>
					<td class="ka011 ka015"><input type="text" name="shokai" class="ka015 ka017"
						value="<%=shohin.getSshokai()%>" class="ka010"></td></tr>
				<tr><th class="ka011 ka012">商品説明</th>
					<td class="ka011 ka015"><input type="text" name="setsumei"  class="ka015 ka017"
						value="<%=shohin.getSsetsumei()%>" class="ka010"></td></tr>
				<tr><th class="ka011 ka012">写真パス</th>
					<td class="ka011 ka015"><input type="text" name="photo"  class="ka015"
						value="<%=shohin.getShashin()%>"></td></tr>
				<tr><th></th>
					<td class="ka013"><input type="submit" value="更新" class="ka031"></td></tr>

<%			}	%>
			</table>

		</form>

<%		if (error != null) {	%>
			<p class="ka001 ka004 ka005 ka007"><%=error%></p>
<%		}	%>

		<p class="ka001 ka006 ka008"><a href="syouhinkanrimenu.jsp" class="ka030">
				商品管理メニューに戻る</a></p>

	</div>

	<footer class="Footer02">
		<p>株式会社１０☆１７Ｍａｒｔ</p>
		<p>Copyright (C) 10-17MART CO.,LTD. All Rights Reserved.</p>
	</footer>

</body>
</html>