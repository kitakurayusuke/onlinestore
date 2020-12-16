package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import bean.Kanrisya;

public class KanrisyaDAO  extends DAO {

	//searchK
	//管理者DBに登録されている管理者情報を取得する。
	//引数：なし
	//戻り値：kanrisya型のArrayList
	public ArrayList<Kanrisya> searchK() throws Exception {

		//結果格納エリアを確保
		ArrayList<Kanrisya> list=new ArrayList<Kanrisya>();

		//管理者DB接続
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
				"SELECT * FROM kanrisya");

		//SQL結果をrsに格納
		ResultSet rs=st.executeQuery();

		//結果格納エリアに格納
		while (rs.next()) {
			Kanrisya s=new Kanrisya();

			s.setKseiid(rs.getInt("kseiid"));
			s.setKanrinumber(rs.getString("kanrinumber"));
			s.setKpassward(rs.getString("kpassward"));
			s.setKname(rs.getString("kname"));
			s.setKlogin(rs.getInt("klogin"));
			s.setRinsert(rs.getInt("rinsert"));
			s.setRupdate(rs.getInt("rupdate"));
			s.setRdelete(rs.getInt("rdelete"));
			s.setSinsert(rs.getInt("sinsert"));
			s.setSupdate(rs.getInt("supdate"));
			s.setSdelete(rs.getInt("sdelete"));
			s.setKinsert(rs.getInt("kinsert"));
			s.setKupdate(rs.getInt("kupdate"));
			s.setKdelete(rs.getInt("kdelete"));

			list.add(s);
		}

		st.close();
		con.close();

		return list;
	}


	//insertK
	//管理者情報を管理者DBへ登録する。
	//引数：kanrisya型のArrayList
	//戻り値：int型の配列（[0]更新レコード件数。[1]id）
	public int[] insertK(ArrayList<Kanrisya> list) throws Exception {

		//戻り値格納場所を準備
		int value[] = new int[2];

		//引数をrへ格納
		Kanrisya r = list.get(0);

		//管理者DB接続
		Connection con=getConnection();

		String sql = "insert into kanrisya value(null,null,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement st=con.prepareStatement(
				sql,Statement.RETURN_GENERATED_KEYS);

		st.setString(1, r.getKpassward());
		st.setString(2, r.getKname());
		st.setInt(3, r.getKlogin());
		st.setInt(4, r.getRinsert());
		st.setInt(5, r.getRupdate());
		st.setInt(6, r.getRdelete());
		st.setInt(7, r.getSinsert());
		st.setInt(8, r.getSupdate());
		st.setInt(9, r.getSdelete());
		st.setInt(10, r.getKinsert());
		st.setInt(11, r.getKupdate());
		st.setInt(12, r.getKdelete());

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


	//updateK
	//管理者DB内の管理者情報を更新する。
	//引数：kanrisya型のArrayList
	//戻り値：int型（更新レコード件数。常に１）
	public int updateK(ArrayList<Kanrisya> list) throws Exception {

		//引数をrへ格納
		Kanrisya r = list.get(0);

		//管理者DB接続
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
				"update kanrisya set kanrinumber=?,kpassward=?,kname=?,klogin=?,rinsert=?,rupdate=?,rdelete=?,sinsert=?,supdate=?,sdelete=?,kinsert=?,kupdate=?,kdelete=? where kseiid = ?");

		st.setString(1, r.getKanrinumber());
		st.setString(2, r.getKpassward());
		st.setString(3, r.getKname());
		st.setInt(4, r.getKlogin());
		st.setInt(5, r.getRinsert());
		st.setInt(6, r.getRupdate());
		st.setInt(7, r.getRdelete());
		st.setInt(8, r.getSinsert());
		st.setInt(9, r.getSupdate());
		st.setInt(10, r.getSdelete());
		st.setInt(11, r.getKinsert());
		st.setInt(12, r.getKupdate());
		st.setInt(13, r.getKdelete());
		st.setInt(14, r.getKseiid());

		//SQL結果をlineに格納
		int line = st.executeUpdate();

		st.close();
		con.close();

		return line;

	}


	//searchKKP
	//管理者DBから、指定された管理者番号と管理者パスワードのレコードを抽出する。
	//引数：管理者番号，管理者パスワード
	//戻り値：kanrisya型のArrayList
	public ArrayList<Kanrisya> searchKKP(String kno, String pass) throws Exception {

		//結果格納エリアを確保
		ArrayList<Kanrisya> list=new ArrayList<>();

		//管理者DB接続
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
				"select * from kanrisya where kanrinumber = ? and kpassward = ?");

		st.setString(1, kno);
		st.setString(2, pass);
		ResultSet rs=st.executeQuery();

		while (rs.next()) {
			Kanrisya p=new Kanrisya();

			p.setKseiid(rs.getInt("kseiid"));
			p.setKanrinumber(rs.getString("kanrinumber"));
			p.setKpassward(rs.getString("kpassward"));
			p.setKname(rs.getString("kname"));
			p.setKlogin(rs.getInt("klogin"));
			p.setRinsert(rs.getInt("rinsert"));
			p.setRupdate(rs.getInt("rupdate"));
			p.setRdelete(rs.getInt("rdelete"));
			p.setSinsert(rs.getInt("sinsert"));
			p.setSupdate(rs.getInt("supdate"));
			p.setSdelete(rs.getInt("sdelete"));
			p.setKinsert(rs.getInt("kinsert"));
			p.setKupdate(rs.getInt("kupdate"));
			p.setKdelete(rs.getInt("kdelete"));
			list.add(p);
		}
		st.close();
		con.close();

		return list;
	}

}
