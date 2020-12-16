<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>利用者情報の更新・削除確認</title>
<link rel="stylesheet" type="text/css" href="mart.css">
<%@page import="bean.Riyosya,dao.RiyosyaDAO,java.util.List"%>
</head>
<body class="Body02">
	<header class="Header02">
		<p>１０☆１７Ｍａｒｔ</p>
	</header>

	<div class="main02">

		<p class="ka001 ka002 ka003 ka004">利用者情報の更新・削除確認</p>
<%
		RiyosyaDAO dao = new RiyosyaDAO();
		String strRNum=(String)session.getAttribute("rNum");
		List<Riyosya> list = (List<Riyosya>) session.getAttribute("rlist");
		List<Riyosya> kakuninList = dao.searchRK(strRNum);
%>
		<p class="ka001 ka005">会員番号：<%=strRNum%></p>

		<table class="ka010">
<%			for (Riyosya riyosya : list) {	%>
				<tr><th class="ka011 ka012">氏名</th>
					<td class="ka011"><%=riyosya.getRname()%></td></tr>
				<tr><th class="ka011 ka012">パスワード</th>
					<td class="ka011"><%=riyosya.getRpassward()%></td></tr>
				<tr><th class="ka011 ka012">住所</th>
					<td class="ka011"><%=riyosya.getJusho()%></td></tr>
				<tr><th class="ka011 ka012">メールアドレス</th>
					<td class="ka011"><%=riyosya.getAdress()%></td></tr>
<%			}	%>
		</table>

<%		if(kakuninList.size() == 0){	%>
			<p class="ka001 ka004 ka005">上記内容で削除を行いました。</p>
<%		 	session.setAttribute("rNum", "");
			session.setAttribute("rName", "");
%>
			<br>
			<h2 class="ka001 ka006 ka008"><a href="riyousyakanrimenu.jsp" class="ka030">
				利用者管理メニューに戻る</a></h2>
<%		}else{	%>
			<p class="ka001 ka004 ka005">上記内容で更新を行いました。</p>
			<br>
			<p class="ka001 ka006 ka008"><a href="riyousyajyouhouhyouji.jsp" class="ka030">
				利用者情報表示に戻る</a></p>
<%		}	%>




	</div>

	<footer class="Footer02">
		<p>株式会社１０☆１７Ｍａｒｔ</p>
		<p>Copyright (C) 10-17MART CO.,LTD. All Rights Reserved.</p>
	</footer>

</body>
</html>