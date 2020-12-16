<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品検索</title>
<link rel="stylesheet" type="text/css" href="mart.css">
<%
String error=(String)request.getAttribute("error");
%>
</head>

<body class="Body02">
	<header class="Header02">
		<p>１０☆１７Ｍａｒｔ</p>
	</header>

	<div class="main02">

		<p class="ka001 ka002 ka003 ka004">商品検索画面</p>
		<p class="ka001 ka005 ka006">商品IDを入力してください</p>
		<form action="syouhinSearch"method="post">
			<p class="ka001 ka005">商品ID
				<input type="text"name="sid">
				<input type="submit" value="検索" class="ka031"></p>
		</form>

<%		if (error != null) {	%>
			<p class="ka001 ka004 ka005 ka007"><%=error%></p>
<%		}	%>

		<br>
		<p class="ka001 ka006 ka008"><a href="syouhinkanrimenu.jsp" class="ka030">
			商品管理メニューに戻る</a></p>

	</div>

	<footer class="Footer02">
		<p>株式会社１０☆１７Ｍａｒｔ</p>
		<p>Copyright (C) 10-17MART CO.,LTD. All Rights Reserved.</p>
	</footer>

</body>
</html>