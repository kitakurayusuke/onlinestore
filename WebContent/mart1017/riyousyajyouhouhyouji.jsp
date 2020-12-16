<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>利用者情報表示</title>
<link rel="stylesheet" type="text/css" href="mart.css">
<%@page import="bean.Riyosya,dao.RiyosyaDAO,java.util.List"%>
</head>

<body class="Body02">
	<header class="Header02">
		<p>１０☆１７Ｍａｒｔ</p>
	</header>

	<div class="main02">

		<p class="ka001 ka002 ka003 ka004">利用者情報表示</p>
<%
		//セッション属性から管理者権限を取得
		String rUpdate=(String)session.getAttribute("rUpdate");
		String rDelete=(String)session.getAttribute("rDelete");
		String strRNum=(String)session.getAttribute("rNum");
		String error=(String)request.getAttribute("error");
		RiyosyaDAO dao = new RiyosyaDAO();

		List<Riyosya> list = dao.searchRK(strRNum);
%>
		<p><span class="ka001 ka005">会員番号：<%=strRNum%></span>
			<span class="ka041 ka006 ka008">
				<a href="minousyouhin.jsp" class="ka030">未納商品を表示</a></span></p>
		<p><span class="ka001 ka004 ka005 ka009">＝＝＝　登録内容　削除エリア　＝＝＝</span>
			<span class="ka041 ka006 ka008">
				<a href="kounyurireki.jsp" class="ka030">購入履歴を表示</a></span></p>

		<table class="ka010">
<%			for (Riyosya riyosya : list) {	%>
				<tr><th class="ka011 ka012">氏名</th>
					<td class="ka011"><%=riyosya.getRname()%></td></tr>
				<tr><th class="ka011 ka012">パスワード</th>
					<td class="ka011"><%=riyosya.getRpassward()%></td></tr>
				<tr><th class="ka011 ka012 ka014">住所</th>
					<td class="ka011"><%=riyosya.getJusho()%></td></tr>
				<tr><th class="ka011 ka012">メールアドレス</th>
					<td class="ka011"><%=riyosya.getAdress()%></td></tr>
				<tr><th></th>
				<%if(rDelete == "OK"){ %>
					<td class="ka013">
						<form action="riyousyaDelete"method="post">
							<input class="ka031" type="submit" value="削除">
						</form>
					</td>
				<% } %>
				</tr>
<%			}	%>
		</table>
<%if(rUpdate == "OK"){ %>
		<p class="ka001 ka004 ka005 ka009">＝＝＝　登録内容　更新エリア　＝＝＝</p>

		<form action="riyousyaEdit"method="post">
			<table class="ka010">
<%			for (Riyosya riyosya : list) {	%>
				<tr><th class="ka011 ka012">氏名</th>
					<td class="ka011 ka015"><input type="text" name="rname" maxlength="20" class="ka015 ka016"
							value="<%=riyosya.getRname()%>"></td></tr>
				<tr><th class="ka011 ka012">パスワード</th>
					<td class="ka011 ka015"><input type="text" name="rpass" size="8" maxlength="8" class="ka015"
							value="<%=riyosya.getRpassward()%>"></td></tr>
				<tr><th class="ka011 ka012 ka014">住所</th>
					<td class="ka011 ka015"><input type="text" name="jusho" class="ka015 ka017"
							value="<%=riyosya.getJusho()%>"maxlength="50"></td></tr>
				<tr><th class="ka011 ka012">メールアドレス</th>
					<td class="ka011 ka015"><input type="text"name="address" class="ka015"
							value="<%=riyosya.getAdress()%>"maxlength="50"></td></tr>
				<tr><th></th>
					<td class="ka013">
						<input class="ka031" type="submit" value="更新">
					</td>
				</tr>
<%			}	%>
			</table>

		</form>
<% } %>
<%		if (error != null) {	%>
			<p class="ka001 ka004 ka005 ka007"><%=error%></p>
<%		}	%>

		<p class="ka001 ka006 ka008"><a href="riyousyakanrimenu.jsp" class="ka030">
				利用者管理メニューに戻る</a></p>

	</div>

	<footer class="Footer02">
		<p>株式会社１０☆１７Ｍａｒｔ</p>
		<p>Copyright (C) 10-17MART CO.,LTD. All Rights Reserved.</p>
	</footer>

</body>
</html>