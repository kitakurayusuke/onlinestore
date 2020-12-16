<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>未納商品一覧</title>
<link rel="stylesheet" type="text/css" href="mart.css">
<%@page import="bean.Konyu,dao.KonyuDAO,bean.Shohin,dao.ShohinDAO,java.util.*"%>
</head>
<body class="Body02">
	<header class="Header02">
		<p>１０☆１７Ｍａｒｔ</p>
	</header>

	<div class="main02">

		<p class="ka001 ka002 ka003 ka004">未納商品一覧</p>
<%
		//セッション属性から情報を取得
		String strRNum=(String)session.getAttribute("rNum");
		String error=(String)request.getAttribute("error");
		//DAOクラスのオブジェクトを生成
		KonyuDAO dao = new KonyuDAO();
		ShohinDAO sdao = new ShohinDAO();
		//購入履歴検索をするメソッドの呼び出し
		List<Konyu> list =dao.searchKou(strRNum);
		//変数の宣言
		List<Shohin> slist=new ArrayList<>();
		List<Konyu> minouklist=new ArrayList<Konyu>();
		String sName="";
		String daib="";
		String chub="";
		String shob="";
		String sid="";
		//リストの中身から未納のみをリストに入れる
		for (Konyu konyu : list) {
			Konyu k=new Konyu();
			if(konyu.getShuko()==0){
				k.setKonyuid(konyu.getKonyuid());
				k.setKonyuday(konyu.getKonyuday());
				k.setRirekisid(konyu.getRirekisid());
				k.setKonyusu(konyu.getKonyusu());
				k.setTanka(konyu.getTanka());
				minouklist.add(k);
			}
		}
%>
		<p class="ka001 ka005"><%=strRNum%>：<%=session.getAttribute("rName")%>様</p>

		<table class="ka018">
		<tr><th class="ka040 ka022">購入日</th>
			<th class="ka019 ka022">商品ID</th>
			<th class="ka020 ka022">商品名</th>
			<th class="ka019 ka022">大分類</th>
			<th class="ka019 ka022">中分類</th>
			<th class="ka019 ka022">小分類</th>
			<th class="ka019 ka013">販売単価</th>
			<th class="ka019 ka013">購入数</th>
			<th class="ka019"></th>
		</tr>

<!-- リストの中身から未納商品を表示する -->
<%
		for (Konyu konyu : minouklist) {
			//String kid= Integer.toString(konyu.getKonyuid());
			//System.out.println(kid);
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
			<td class="ka013"><form action="minou"method="post">
			<!-- 購入IDを隠して送る -->
 				<input type="hidden" name="kid" value="<%= konyu.getKonyuid() %>">
				<input type="submit" value="出荷" class="ka031">
				</form></td>
		</tr>
<%		}	%>
		</table>

<!-- エラーの場合にエラーメッセージを表示する -->
<%		if (error != null) {	%>
			<p  class="ka001 ka004 ka005 ka007"><%=error%></p>
<%		}	%>

		<br>
		<p class="ka001 ka006 ka008"><a href="riyousyakanrimenu.jsp" class="ka030">
			利用者管理メニューに戻る</a></p>

	</div>

	<footer class="Footer02">
		<p>株式会社１０☆１７Ｍａｒｔ</p>
		<p>Copyright (C) 10-17MART CO.,LTD. All Rights Reserved.</p>
	</footer>

</body>
</html>