<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@page import="bean.Konyu,dao.KonyuDAO,bean.Shohin,dao.ShohinDAO,java.util.*"%>
<title>購入履歴一覧</title>
<link rel="stylesheet" type="text/css" href="mart.css">
</head>
<body class="Body02">
	<header class="Header02">
		<p>１０☆１７Ｍａｒｔ</p>
	</header>

	<div class="main02">

		<p class="ka001 ka002 ka003 ka004">購入履歴一覧</p>
<%
		//セッション属性から情報を取得
		String strRNum=(String)session.getAttribute("rNum");
		String error=(String)request.getAttribute("error");
		List<Konyu> klist = (List<Konyu>) session.getAttribute("klist");
		//DAOクラスのオブジェクトを生成
		ShohinDAO sdao = new ShohinDAO();
		//購入履歴検索をするメソッドの呼び出し
		List<Shohin> slist=new ArrayList<>();
		String sName="";
		String daib="";
		String chub="";
		String shob="";
		String sid="";
		String firstDay="";
		String lastDay="";
		int listNum=klist.size();
		for (int i = 0; i < klist.size(); i++) {
			firstDay=klist.get(0).getKonyuday();
			lastDay=klist.get(listNum-1).getKonyuday();
		}
%>
		<h4 class="ka001 ka005"><%=strRNum%>：<%=session.getAttribute("rName")%>様</h4>

		<!-- CSVの出力期間を入力する -->
		<form>
			<table class="ka023">
				<tr>
					<td class="ka020"><input type="text"name="fdate" class="ka024 ka025"
							value="<%=firstDay%>"></td>
					<td class="ka019 ka024">～</td>
					<td class="ka020"><input type="text"name="ldate" class="ka024 ka025"
							value="<%=lastDay%>"></td>
					<td class="ka020">の購入履歴</td>
					<td class="ka013 ka020">
						<button type="submit"formaction="csv"formmethod="POST"class="ka031">CSVへ出力</button></td>
					<td class="ka013 ka020">
						<button type="submit"formaction="csv" name="kakunin" value="hyouji"formmethod="POST"class="ka031">指定期間の表示</button></td>
				</tr>
			</table>
		</form>
		<br>

		<table class="ka018">
			<tr><th class="ka040 ka022">購入日</th>
				<th class="ka019 ka022">商品ID</th>
				<th class="ka020 ka022">商品名</th>
				<th class="ka019 ka022">大分類</th>
				<th class="ka019 ka022">中分類</th>
				<th class="ka019 ka022">小分類</th>
				<th class="ka019 ka013">販売単価</th>
				<th class="ka019 ka013">購入数</th>
			</tr>
<!-- リストの中身から購入履歴を表示する -->
<%		for (Konyu konyu : klist) {
			sid=konyu.getRirekisid();
			slist = sdao.searchSID(sid);
			for (Shohin shohin : slist) {
				sName=shohin.getSname();
				daib=shohin.getDaib();
				chub=shohin.getChub();
				shob=shohin.getShob();
			}
%>

		<tr><td><%=konyu.getKonyuday()%></td>
			<td><%=konyu.getRirekisid()%></td>
			<td><%=sName%></td>
			<td class="ka021"><%=daib%></td>
			<td class="ka021"><%=chub%></td>
			<td class="ka021"><%=shob%></td>
			<td class="ka013"><%=konyu.getTanka()%></td>
			<td class="ka013"><%=konyu.getKonyusu()%></td>
		</tr>

<%		}	%>

	</table>

<!-- エラーの場合にエラーメッセージを表示する -->
<%		if (error != null) {	%>
			<p class="ka001 ka004 ka005 ka007"><%=error%></p>
<%		}	%>

		<br>
		<p class="ka001 ka006 ka008"><a href="riyousyakanrimenu.jsp" class="ka030">
			利用者管理メニューに戻る</a></6>

	</div>

	<footer class="Footer02">
		<p>株式会社１０☆１７Ｍａｒｔ</p>
		<p>Copyright (C) 10-17MART CO.,LTD. All Rights Reserved.</p>
	</footer>

</body>
</html>