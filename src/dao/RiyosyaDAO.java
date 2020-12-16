package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import bean.Riyosya;

public class RiyosyaDAO extends DAO {


	//searchRK
	//利用者DBから、指定された会員番号のレコードを抽出する。
	//引数：会員番号
	//戻り値：riyosya型のArrayList
	public ArrayList<Riyosya> searchRK(String kno) throws Exception {

		//結果格納エリアを確保
		ArrayList<Riyosya> list=new ArrayList<>();

		//利用者DB接続
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
				"select * from riyosya where kainumber = ?");

		st.setString(1, kno);
		ResultSet rs=st.executeQuery();

		while (rs.next()) {
			Riyosya p=new Riyosya();

			p.setRseiid(rs.getInt("rseiid"));
			p.setKainumber(rs.getString("kainumber"));
			p.setRname(rs.getString("rname"));
			p.setJusho(rs.getString("jusho"));
			p.setAdress(rs.getString("adress"));
			p.setRpassward(rs.getString("rpassward"));
			list.add(p);
		}
		st.close();
		con.close();

		return list;
	}


	//searchKP
	//利用者DBから、指定された会員番号と利用者パスワードのレコードを抽出する。
	//引数：会員番号，利用者パスワード
	//戻り値：riyosya型のArrayList
	public ArrayList<Riyosya> searchKP(String kno, String pass) throws Exception {

		//結果格納エリアを確保
		ArrayList<Riyosya> list=new ArrayList<>();

		//利用者DB接続
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
				"select * from riyosya where kainumber = ? and rpassward = ?");

		st.setString(1, kno);
		st.setString(2, pass);
		ResultSet rs=st.executeQuery();

		while (rs.next()) {
			Riyosya p=new Riyosya();

			p.setRseiid(rs.getInt("rseiid"));
			p.setKainumber(rs.getString("kainumber"));
			p.setRname(rs.getString("rname"));
			p.setJusho(rs.getString("jusho"));
			p.setAdress(rs.getString("adress"));
			p.setRpassward(rs.getString("rpassward"));
			list.add(p);
		}
		st.close();
		con.close();

		return list;
	}


	//insertR
	//会員情報を利用者DBへ登録する。
	//引数：riyosya型のArrayList
	//戻り値：int型の配列（[0]更新レコード件数。[1]id）
	public int[] insertR(ArrayList<Riyosya> list) throws Exception {

		//戻り値格納場所を準備
		int value[] = new int[2];

		//引数をrへ格納
		Riyosya r = list.get(0);

		//利用者DB接続
		Connection con=getConnection();

		String sql = "insert into riyosya value(null,null,?,?,?,?)";
		PreparedStatement st=con.prepareStatement(
				sql,Statement.RETURN_GENERATED_KEYS);

		st.setString(1, r.getRname());
		st.setString(2, r.getJusho());
		st.setString(3, r.getAdress());
		st.setString(4, r.getRpassward());

		//SQL結果をvalue[0]に格納
		value[0] = st.executeUpdate();
		//自動生成後のidをvalue[1]に格納
		ResultSet rs = st.getGeneratedKeys();
		if (rs.next()) {
			value[1] = rs.getInt(1);
		}

		st.close();
		con.close();

		return value;
	}


	//updateR
	//利用者DB内の会員情報を更新する。
	//引数：riyosya型のArrayList
	//戻り値：int型（更新レコード件数。常に１）
	public int updateR(ArrayList<Riyosya> list) throws Exception {

		//引数をrへ格納
		Riyosya r = list.get(0);

		//管理者DB接続
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
				"update riyosya set kainumber=?,rname=?,jusho=?,adress=?,rpassward=? where rseiid = ?");

		st.setString(1, r.getKainumber());
 		st.setString(2, r.getRname());
		st.setString(3, r.getJusho());
		st.setString(4, r.getAdress());
		st.setString(5, r.getRpassward());
		st.setInt(6, r.getRseiid());

		//SQL結果をlineに格納
		int line = st.executeUpdate();

		st.close();
		con.close();

		return line;
	}


	//deleteR
	//利用者DBから、指定された会員番号のレコードを削除する。
	//引数：会員番号
	//戻り値：int型（登録レコード件数。常に１）
	public int deleteR(String kno) throws Exception {

		//利用者DB接続
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
				"delete from riyosya where kainumber = ?");

		st.setString(1, kno);

		//SQL結果をlineに格納
		int line = st.executeUpdate();

		st.close();
		con.close();

		return line;
	}


}