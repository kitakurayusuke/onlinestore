<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品追加</title>
<link rel="stylesheet" type="text/css" href="mart.css">
</head>

<body class="Body02">
	<header class="Header02">
		<p>１０☆１７Ｍａｒｔ</p>
	</header>

	<div class="main02">

		<p class="ka001 ka002 ka003 ka004">商品追加</p>
		<p class="ka001 ka005 ka032">商品の追加登録処理を行います。下記内容を入力してください。</p>
<%
	String error = (String) request.getAttribute("error");
	String sid = request.getParameter("sid");
	String sname = request.getParameter("sname");
	String price = request.getParameter("price");
	String zaiko = request.getParameter("zaiko");
	String dai = request.getParameter("dai");
	String tyuu = request.getParameter("tyuu");
	String syou = request.getParameter("syou");
	String shokai = request.getParameter("shokai");
	String setsumei = request.getParameter("setsumei");
	String photo = request.getParameter("photo");
%>

		<form action="syouhinInsert" method="post">
			<table class="ka010">
				<tr>
					<th class="ka011 ka012">商品ID</th>
					<td class="ka011 ka015"><input type="text" name="sid"
						maxlength="7" class="ka015" <%if (sid != null) {%> value="<%=sid%>"
						<%}%>></td>
				</tr>
				<tr>
					<th class="ka011 ka012">商品名</th>
					<td class="ka011 ka015"><input type="text" name="sname"
						maxlength="20" class="ka015 ka016" <%if (sname != null) {%>
						value="<%=sname%>" <%}%>></td>
				</tr>
				<tr>
					<th class="ka011 ka012">単価</th>
					<td class="ka011 ka015"><input type="text" name="price"
						maxlength="10" class="ka015" <%if (price != null) {%>
						value="<%=price%>" <%}%>></td>
				</tr>
				<tr>
					<th class="ka011 ka012">在庫数</th>
					<td class="ka011 ka015"><input type="text" name="zaiko"
						maxlength="5" class="ka015" <%if (zaiko != null) {%>
						value="<%=zaiko%>" <%}%>></td>
				</tr>
				<tr>
					<th class="ka011 ka012">大分類</th>
					<td class="ka011 ka015"><select name="dai">
							<option value="食品" <%if (dai == "食品") {%> selected <%}%>>食品</option>
							<option value="飲料" <%if (dai == "飲料") {%> selected <%}%>>飲料</option>
							<option value="日用品" <%if (dai == "日用品") {%> selected <%}%>>日用品</option>
							<option value="ヘルス・ビューティ用品" <%if (dai == "ヘルス・ビューティ用品") {%> selected
								<%}%>>ヘルス・ビューティ用品</option>
							<option value="マタニティ・ベビー用品" <%if (dai == "マタニティ・ベビー用品") {%> selected
								<%}%>>マタニティ・ベビー用品</option>
					</select></td>
				</tr>
				<tr>
					<th class="ka011 ka012">中分類</th>
					<td class="ka011 ka015"><select name="tyuu">
							<option value="食品" <%if (tyuu == "食品") {%> selected <%}%>>食品</option>
							<option value="飲料" <%if (tyuu == "飲料") {%> selected <%}%>>飲料</option>
							<option value="日用品" <%if (tyuu == "日用品") {%> selected <%}%>>日用品</option>
							<option value="ヘルス・ビューティ用品" <%if (tyuu == "ヘルス・ビューティ用品") {%>
								selected <%}%>>ヘルス・ビューティ用品</option>
							<option value="マタニティ・ベビー用品" <%if (tyuu == "マタニティ・ベビー用品") {%>
								selected <%}%>>マタニティ・ベビー用品</option>
					</select></td>
				</tr>
				<tr>
					<th class="ka011 ka012">小分類</th>
					<td class="ka011 ka015"><select name="syou">
							<option value="食品" <%if (syou == "食品") {%> selected <%}%>>食品</option>
							<option value="飲料" <%if (syou == "飲料") {%> selected <%}%>>飲料</option>
							<option value="日用品" <%if (syou == "日用品") {%> selected <%}%>>日用品</option>
							<option value="ヘルス・ビューティ用品" <%if (syou == "ヘルス・ビューティ用品") {%>
								selected <%}%>>ヘルス・ビューティ用品</option>
							<option value="マタニティ・ベビー用品" <%if (syou == "マタニティ・ベビー用品") {%>
								selected <%}%>>マタニティ・ベビー用品</option>
					</select></td>
				</tr>
				<tr>
					<th class="ka011 ka012">商品紹介</th>
					<td class="ka011 ka015"><input type="text" name="shokai"
						maxlength="200" class="ka015 ka017" <%if (shokai != null) {%>
						value="<%=shokai%>" <%}%>></td>
				</tr>
				<tr>
					<th class="ka011 ka012">商品説明</th>
					<td class="ka011 ka015"><input type="text" name="setsumei"
						maxlength="200" class="ka015 ka017" <%if (setsumei != null) {%>
						value="<%=setsumei%>" <%}%>></td>
				</tr>
				<tr>
					<th class="ka011 ka012">写真パス</th>
					<td class="ka011 ka015"><input type="text" name="photo"
						maxlength="15" class="ka015" <%if(photo!=null){%>
						value="<%=photo%>" <%}%>></td>
				</tr>
				<tr>
					<th></th>
					<td class="ka013"><input type="submit" value="登録"
						class="ka031"></td>
				</tr>
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