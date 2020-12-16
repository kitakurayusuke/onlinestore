package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.util.ArrayList;

import bean.Konyu;

public class KonyuDAO extends DAO {

	//searchKou
	//購入履歴ＤＢから、指定された会員番号のレコードを抽出する。購入日の昇順に並べ替える。
	//引数：会員番号
	//戻り値：konyu型のArrayList
	public ArrayList<Konyu> searchKou(String no) throws Exception {

		//結果格納エリアを確保
		ArrayList<Konyu> list=new ArrayList<Konyu>();

		//購入履歴DB接続
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
				"SELECT * FROM konyu where rirekikainumber = ? ORDER BY konyuday ASC");

		st.setString(1, no);
		//SQL結果をrsに格納
		ResultSet rs=st.executeQuery();

		//結果格納エリアに格納
		String StrDay;
		while (rs.next()) {
			Konyu s=new Konyu();

			s.setKonyuid(rs.getInt("konyuid"));
			s.setRirekikainumber(rs.getString("rirekikainumber"));

//			s.setKonyuday(rs.getString("konyuday"));
			StrDay = rs.getString("konyuday");
			s.setKonyuday(StrDay);

			s.setRirekisid(rs.getString("rirekisid"));
			s.setKonyusu(rs.getInt("konyusu"));
			s.setTanka(rs.getInt("tanka"));
			s.setShuko(rs.getInt("shuko"));

			list.add(s);
		}
		st.close();
		con.close();

		return list;
	}


	//insertKou
	//購入した商品を購入履歴ＤＢへ登録する。
	//引数：konyu型のArrayList
	//戻り値：int型（更新レコード件数。常に１）
	public int insertKou(ArrayList<Konyu> list) throws Exception {

		//引数をkへ格納
		Konyu k = list.get(0);

		//購入履歴DB接続
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
				"insert into konyu value(null,?,?,?,?,?,?)");

		st.setString(1, k.getRirekikainumber());
		st.setString(2, k.getKonyuday());
		st.setString(3, k.getRirekisid());
		st.setInt(4, k.getKonyusu());
		st.setInt(5, k.getTanka());
		st.setInt(6, k.getShuko());

		//SQL結果をlineに格納
		int line = st.executeUpdate();

		st.close();
		con.close();

		return line;
	}
	//updateKou
			//購入履歴ＤＢ内の商品出庫状況を変更する。
			//引数：購入履歴用番号，商品出庫状況
			//戻り値：int型（登録レコード件数。常に１）
			public int updateKou(int id,int shukko) throws Exception {

				//購入履歴DB接続
				Connection con=getConnection();

				PreparedStatement st=con.prepareStatement(
						"update konyu set shuko=? where konyuid = ?");

		 		st.setInt(1,shukko);
				st.setInt(2,id);

				//SQL結果をlineに格納
				int line = st.executeUpdate();

				st.close();
				con.close();

				return line;
			}


}
