<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品検索結果</title>
<%@page import="bean.Shohin,java.util.List"%>
<link rel="stylesheet" type="text/css" href="mart.css">
</head>

<body class="Body02">
	<header class="Header02">
		<p>１０☆１７Ｍａｒｔ</p>
	</header>

	<div class="main02">

		<p class="ka001 ka002 ka003 ka004">商品検索結果</p>
<%
		List<Shohin> list = (List<Shohin>) session.getAttribute("slist");
		String sUpdate=(String)session.getAttribute("sUpdate");
		String sDelete=(String)session.getAttribute("sDelete");
%>
		<table class="ka034">
<%		for (Shohin shohin : list) {	%>
			<tr><th class="ka011 ka012">商品ID</th>
				<td colspan="3" class="ka011"><%=shohin.getShoid()%></td></tr>
			<tr><th class="ka011 ka012">商品名</th>
				<td colspan="3" class="ka011"><%=shohin.getSname()%></td></tr>
			<tr><th class="ka011 ka012">大分類</th>
				<td colspan="3" class="ka011"><%=shohin.getDaib()%></td></tr>
			<tr><th class="ka011 ka012">中分類</th>
				<td colspan="3" class="ka011"><%=shohin.getChub()%></td></tr>
			<tr><th class="ka011 ka012">小分類</th>
				<td colspan="3" class="ka011"><%=shohin.getShob()%></td></tr>
			<tr><th class="ka011 ka012">単価</th>
				<td colspan="3" class="ka011"><%=shohin.getStanka()%></td></tr>
			<tr><th class="ka011 ka012">在庫数</th>
				<td colspan="3" class="ka011"><%=shohin.getZaikosuu()%></td></tr>
			<tr><th class="ka011 ka012">商品紹介</th>
				<td colspan="3" class="ka011 ka021"><%=shohin.getSshokai()%></td></tr>
			<tr><th class="ka011 ka012">商品説明</th>
				<td colspan="3" class="ka011 ka021"><%=shohin.getSsetsumei()%></td></tr>
			<tr><th class="ka011 ka012">写真のパス</th>
				<td colspan="3" class="ka011"><%=shohin.getShashin()%></td></tr>
			<tr><th></th>
				<td></td>
				<td class="ka013 ka040">
<%					if(sUpdate == "OK"){	%>
						<form action="syouhinhenkou.jsp" method="post">
							<input type="submit" value="変更" class="ka031">
						</form>
<%					}	%>
				</td>
				<td class="ka013 ka040">
<%					if(sDelete == "OK"){	%>
						<form action="syouhinsakujyo.jsp" method="post">
							<input type="submit" value="削除" class="ka031">
						</form>
<%					}	%>
				</td>
			</tr>
<%		}	%>
		</table>


		<p class="ka001 ka006 ka008"><a href="syouhinkensaku.jsp" class="ka030">
			商品検索に戻る</a></p>

	</div>

	<footer class="Footer02">
		<p>株式会社１０☆１７Ｍａｒｔ</p>
		<p>Copyright (C) 10-17MART CO.,LTD. All Rights Reserved.</p>
	</footer>

</body>
</html>