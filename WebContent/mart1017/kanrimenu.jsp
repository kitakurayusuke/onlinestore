<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理メニュー</title>
<link rel="stylesheet" type="text/css" href="mart.css">
<%
// セッション属性から管理者権限を取得
String rUpdate=(String)session.getAttribute("rUpdate");
String rDelete=(String)session.getAttribute("rDelete");
String sInsert=(String)session.getAttribute("sInsert");
String sUpdate=(String)session.getAttribute("sUpdate");
String sDelete=(String)session.getAttribute("sDelete");
String kInsert=(String)session.getAttribute("kInsert");
String kUpdate=(String)session.getAttribute("kUpdate");
%>
</head>

<body class="Body02">
	<header class="Header02">
		<p>１０☆１７Ｍａｒｔ</p>
	</header>

	<div class="main02">
<!-- 管理権限がOKの場合にリンクを表示する -->

		<p class="ka001 ka002 ka003 ka004">管理メニュー</p>

		<%
			if (rUpdate == "OK" || rDelete == "OK") {
		%>
		<p class="ka001 ka005 ka006">
		<%
			}
		%>
			<a href="riyousyakanrimenu.jsp" class="ka030">利用者管理メニュー</a>
		</p>
		<%if(sInsert == "OK"||sUpdate == "OK"||sDelete == "OK"){ %>
		<p class="ka001 ka005 ka006">
			<a href="syouhinkanrimenu.jsp" class="ka030">商品管理メニュー</a>
		</p>
		<%
			}
		%>
		<%if(kInsert == "OK"||kUpdate == "OK"){ %>
		<p class="ka001 ka005 ka006">
			<a href="kanrisyakanrimenu.jsp" class="ka030">管理者管理メニュー</a>
		</p>
		<%
			}
		%>

		<p class="ka001 ka005 ka006">
			<a href="kanriLogout" class="ka030">ログアウト</a></p>

	</div>

	<footer class="Footer02">
		<p>株式会社１０☆１７Ｍａｒｔ</p>
		<p>Copyright (C) 10-17MART CO.,LTD. All Rights Reserved.</p>
	</footer>
</body>
</html>