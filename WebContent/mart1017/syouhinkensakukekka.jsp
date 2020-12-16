<%@page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品一覧</title>
<link rel="stylesheet" type="text/css" href="mart.css">
</head>
<body class="Body01">
<%@page import="javax.servlet.http.HttpSession,java.sql.Connection,bean.Shohin,
						java.util.List,java.util.LinkedHashSet,java.util.Set" %>

	<header class="Header01">
		<p>| 加盟店オーナー募集 | 採用情報 | アルバイト情報 | CSR | お問い合わせ |</p>
		<h1>１０☆１７Ｍａｒｔ</h1>
		<h4>ネットで注文　迅速配達</h4>
	</header>

	<div class="main01">



<hr>
<%// セッションに接続
HttpSession Session = request.getSession(); %>
<% // セッション属性から商品情報を取得
List<Shohin> kList = (List<Shohin>)session.getAttribute("list");
List<Shohin> sortList = (List<Shohin>)session.getAttribute("sortlist");
Object objBunrui = session.getAttribute("bunrui");
Object objKeyword = session.getAttribute("keyword");
Object objSubete = session.getAttribute("subete");
Set<Shohin> hSet = (Set<Shohin>)session.getAttribute("hashset");
Object obrSort = session.getAttribute("sort");
%>


<!-- ○○には検索した分類やキーワードが入る -->
<%if(objSubete != null){%>
	<p align="center"><font size="5"><%=objSubete %>の検索結果
<% }else if(!("%".equals(objBunrui)) && "%".equals(objKeyword)){ %>
	<p align="center"><font size="5"><%=objBunrui %>の検索結果
<% }else if(!("%".equals(objKeyword)) && "%".equals(objBunrui)){ %>
	<p align="center"><font size="5"><%=objKeyword %>の検索結果
<% }else if(!("%".equals(objBunrui)) && (!("%".equals(objKeyword)))){ %>
	<p align="center"><font size="5"><%=objBunrui %>：<%=objKeyword %>の検索結果
<% } %>

<%if(sortList == null){ %>
	<!-- 並べ替え用セレクトボックス -->
	<form action="shohinsort" method="post"><font size="3">
		<table class="AA022">
			<tr>
				<td>
				<select  class="AA001" name="sort" required>
				<option value="">並べ替え</option>
				<option  value="kakakusyoujyun">価格の安い順</option>
				<option  value="kakakukoujyun">価格の高い順</option>
				<option  value="zaikosyoujyun">在庫少ない順</option>
				<option  value="zaikokoujyun">在庫多い順</option>
				<option  value="namaesyoujyun">商品名昇順</option>
				<option  value="namaekoujyun">商品名降順</option>
				</select>
				</td>
				<td><input type="submit" value="並べ替え"></td>
			</tr>
		</table>
	</form>

	<!-- 商品名の前に小さい商品画像が入る
	拡張for文で検索結果を全て表示-->
	<div class="AA021">
	<%for(Shohin s : kList){ %>
		<form action="shouhinsyousai" method="post">
		<input type="hidden" name="shoid" value="<%=s.getShoid() %>">
			<div class="AA019">
				<table>
					<tr>
						<td><img src="../image/<%=s.getShashin() %>.jpg" alt="<%=s.getShoid() %>" width="320" height="240"></td>
					</tr>
					<tr>
						<td class="AA020"><%=s.getSname() %></td>
					</tr>
					<tr>
						<td  class="AA027"><%=s.getSshokai() %></td>
					</tr>
					<tr>
						<td class="AA020"><a href="shouhinsyousai"><input type="submit"class="AA002" value="詳細ページへ"></a></td>
					</tr>
				</table>
			</div>
		</form>
	<% } %>
	</div>
<% } %>
<%if(sortList != null){ %>
	<form action="shohinsort" method="post"><font size="3">
		<table class="AA022">
			<tr>
				<td>
				<select  class="AA001" name="sort" required>
				<option value=""><%=obrSort %></option>
				<option  value="kakakusyoujyun">価格の安い順</option>
				<option  value="kakakukoujyun">価格の高い順</option>
				<option  value="zaikosyoujyun">在庫少ない順</option>
				<option  value="zaikokoujyun">在庫多い順</option>
				<option  value="namaesyoujyun">商品名昇順</option>
				<option  value="namaekoujyun">商品名降順</option>
				</select>
				</td>
				<td><input type="submit" value="並べ替え"></td>
			</tr>
		</table>
	</form>
<div class="AA021">
<%for(Shohin s : sortList){ %>
<form action="shouhinsyousai" method="post">
<input type="hidden" name="shoid" value="<%=s.getShoid() %>">
	<div class="AA019">
		<table>
			<tr>
				<td><img src="../image/<%=s.getShashin() %>.jpg" alt="<%=s.getShoid() %>" width="320" height="240"></td>
			</tr>
			<tr>
				<td class="AA020"><%=s.getSname() %></td>
			</tr>
			<tr>
				<td  class="AA027"><%=s.getSshokai() %></td>
			</tr>
			<tr>
				<td class="AA020"><a href="shouhinsyousai"><input type="submit"class="AA002" value="詳細ページへ"></a></td>
			</tr>
		</table>
	</div>
	</form>
<% } %>
<% } %>
</div>
<!-- 画像と商品名が表示される 画像と商品名へ詳細ページへのリンク -->
<h1 class="AA020">最近見た商品</h1>
<div class="AA021">
<%if(hSet != null){ %>
	<%for(Shohin h : hSet){ %>
	<form action="shouhinsyousai" method="post">
	<input type="hidden" name="shoid" value="<%=h.getShoid() %>">
	<div class="AA019">
		<table>
			<tr><td><input type="image" src="../image/<%=h.getShashin() %>.jpg" alt="<%=h.getShoid() %>" width="320" height="240"></td></tr>
			<tr><td class="AA020"><%=h.getSname() %></td></tr>
		</table>
	</div>
	</form>
	<% } %>
<% } %>
</div>
<!-- トップページに戻るボタン トップ画面へ移動 -->
<table class="AA016">
	<tr><td><a href="top.jsp"><input type="button"class="AA002" value="トップページに戻る"></a></td></tr>
</table>




</div>

	<footer class="Footer01">
      <p>株式会社１０☆１７Ｍａｒｔ</p>
      <p>Copyright (C) 10-17MART CO.,LTD. All Rights Reserved.</p>
    </footer>



</body>
</html>
