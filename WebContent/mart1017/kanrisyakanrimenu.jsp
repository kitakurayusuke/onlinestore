<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@page import="bean.Kanrisya,dao.KanrisyaDAO,java.util.*"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理者管理メニュー</title>
<link rel="stylesheet" type="text/css" href="mart.css">
</head>
<%
	String error="";
	if(session.getAttribute("error")!=null){
		error=(String)session.getAttribute("error");
	}
	String kInsert=(String)session.getAttribute("kInsert");
	String kUpdate=(String)session.getAttribute("kUpdate");

	KanrisyaDAO dao = new KanrisyaDAO();
	List<Kanrisya> list =dao.searchK();
%>
<body class="Body02">
	<header class="Header02">
		<p>１０☆１７Ｍａｒｔ</p>
	</header>

	<div class="main02">

<%		if(kInsert == "OK"&&session.getAttribute("insertOK") == null){
			session.removeAttribute("insertOK");
%>
			<br>
			<p><span class="ka001 ka002 ka004 ka009">《管理者情報追加エリア》</span>
				<span class="ka001 ka032 ka035">　　　以下の項目を入力し、
										追加ボタンを押下してください</span>
			</p>

			<form action="kanrisyaInsert"method="post">
				<p class="ka001 ka002">氏名：<input type="text"name="kname" class="ka016"maxlength="20">
					　　PASS：<input type="text"name="kpass"maxlength="8">
				</p>

				<table class="ka036">
					<tr>
						<th>利用者管理権限</th>
						<td>追加<input type="checkbox" name="rkanri" value="insert" disabled="disabled"></td>
						<td>更新<input type="checkbox" name="rkanri" value="update"></td>
						<td>削除<input type="checkbox" name="rkanri" value="delete"></td>
						<td></td>
					</tr>

					<tr>
						<th>商品管理権限</th>
						<td>追加<input type="checkbox" name="skanri" value="insert"></td>
						<td>更新<input type="checkbox" name="skanri" value="update"></td>
						<td>削除<input type="checkbox" name="skanri" value="delete"></td>
						<td></td>
					</tr>

					<tr>
						<th>管理者管理権限</th>
						<td>追加<input type="checkbox" name="kkanri" value="insert"></td>
						<td>更新<input type="checkbox" name="kkanri" value="update"></td>
						<td>削除<input type="checkbox" name="kkanri" value="delete" disabled="disabled"></td>
						<td><input type="submit" value="追加" class="ka031"></td>
					</tr>
				</table>
			</form>
<%		 } %>

<%		if (error != null) {	%>
			<p class="ka001 ka004 ka005 ka007"><%=error%></p>
<%		}	%>

<%		if(kUpdate == "OK"){ %>
			<br><br>
			<p><span class="ka001 ka002 ka004 ka009">《管理者情報変更エリア》</span>
				<span class="ka001 ka032 ka035">　　　権限の変更は１名ずつ行ってください</span>
			</p>

<%			for (Kanrisya kanrisya : list) {	%>
				<form action="kanrisyaUpdate"method="post">
					<input type="hidden" name="kanriNum" value="<%= kanrisya.getKanrinumber() %>">
  					<input type="hidden" name="kpass" value="<%= kanrisya.getKpassward() %>">
					<table class="ka037">
					<tr><td class="ka039"><%=kanrisya.getKanrinumber()%></td>
						<td class="ka039"><%=kanrisya.getKpassward()%></td>
						<td></td>
						<td colspan="3" class="ka004 ka009 ka024 ka032">利用者管理権限</td>
						<td colspan="3" class="ka004 ka009 ka024 ka032">商品管理権限</td>
						<td colspan="3" class="ka004 ka009 ka024 ka032">管理者管理権限</td>
						<td></td>
					</tr>

					<tr><td colspan="2" class="ka020 ka029 ka039"><%=kanrisya.getKname()%></td>
						<td class="ka019 ka029 ka032">
							在籍<input type="checkbox" name="zaiseki" value="zaiseki"
								<% if (kanrisya.getKlogin()==1){ %>checked<% } %>></td>
						<td class="ka029 ka032 ka019 ka013">
							追加<input type="checkbox" name="rkanri" value="insert" disabled="disabled"></td>
						<td class="ka024 ka029 ka032 ka038">
							更新<input type="checkbox" name="rkanri" value="update"
								<% if (kanrisya.getRupdate()==1){ %>checked<% } %>></td>
						<td class="ka029 ka032 ka019 ka022">
							削除<input type="checkbox" name="rkanri" value="delete"
								<% if (kanrisya.getRdelete()==1){ %>checked<% } %>></td>
						<td class="ka029 ka032 ka019 ka013">
							追加<input type="checkbox" name="skanri" value="insert"
								<% if (kanrisya.getSinsert()==1){ %>checked<% } %>></td>
						<td class="ka024 ka029 ka032 ka038">
							更新<input type="checkbox" name="skanri" value="update"
								<% if (kanrisya.getSupdate()==1){ %>checked<% } %>></td>
						<td class="ka029 ka032 ka019 ka022">
							削除<input type="checkbox" name="skanri" value="delete"
								<% if (kanrisya.getSdelete()==1){ %>checked<% } %>></td>
						<td class="ka029 ka032 ka019 ka013">
							追加<input type="checkbox" name="kkanri" value="insert"
								<% if (kanrisya.getKinsert()==1){ %>checked<% } %>></td>
						<td class="ka024 ka029 ka032 ka038">
							更新<input type="checkbox" name="kkanri" value="update"
								<% if (kanrisya.getKupdate()==1){ %>checked<% } %>></td>
						<td class="ka029 ka032 ka019 ka022">
							削除<input type="checkbox" name="kkanri" value="delete" disabled="disabled"></td>
						<td class="ka027 ka029"><input type="submit" value="変更" class="ka031"></td>
					</tr>
				</form>
			</table>
<%			}
		 }		%>

		<br>
		<p class="ka001 ka006 ka008"><a href="kanrimenu.jsp" class="ka030">
			管理メニューに戻る</a></p>

	</div>

	<footer class="Footer02">
		<p>株式会社１０☆１７Ｍａｒｔ</p>
		<p>Copyright (C) 10-17MART CO.,LTD. All Rights Reserved.</p>
	</footer>

</body>
</html>