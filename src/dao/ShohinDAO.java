package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import bean.Shohin;

public class ShohinDAO extends DAO {



	//〇商品DBから大分類を抽出し、ユニークにする。昇順に並べ替える。　
public ArrayList<String> searchnbk() throws Exception {

		//結果格納エリアを確保
		ArrayList<String> list=new ArrayList<String>();

		//商品DB接続
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
		"SELECT DISTINCT daib FROM shohin ORDER BY daib ASC");

//		st.setString(1, keyword);		test
		//SQL結果をrsに格納
		ResultSet rs=st.executeQuery();
		//結果格納エリアに格納
		while (rs.next()) {
			list.add(rs.getString("daib"));
		}

		st.close();
		con.close();

		return list;
	}






	//トップ画面から分類＋キーワードが入力

	//key1.key2は画面から入力された値が入る。
	//〇商品DBから指定された商品分類（すべてを含む）と
	//商品名（すべてを含む）のレコードを抽出し、商品IDを昇順に並べ替える。メソッド
	//何も入力されなかった場合、key1,key2には%が入る。（サーブレット側で％が入る処理が行われる）
	public ArrayList<Shohin> searchS(String key1, String key2) throws Exception {


		//結果格納エリアを確保
		ArrayList<Shohin> list=new ArrayList<Shohin>();

		//商品DB接続
		Connection con=getConnection();


		PreparedStatement st=con.prepareStatement(
		"select * from goods.shohin where daib like ? and sname like ?order by shoid ASC");
		st.setString(1,"%"+key1+"%");
		st.setString(2,"%"+key2+"%");


		//'食品'は分類が選択された値（引数）
		//		st.setString(1, keyword);	test
		//SQL結果をrsに格納
		ResultSet rs=st.executeQuery();

		//結果格納エリアに格納
		while (rs.next()){
		Shohin s=new Shohin();
		s.setShoid(rs.getString("shoid"));
		s.setDaib(rs.getString("daib"));
		s.setChub(rs.getString("chub"));
		s.setShob(rs.getString("shob"));
		s.setSname(rs.getString("sname"));
		s.setSshokai(rs.getString("sshokai"));
		s.setSsetsumei(rs.getString("ssetsumei"));
		s.setStanka(rs.getInt("stanka"));
		s.setZaikosuu(rs.getInt("zaikosuu"));
		s.setShashin(rs.getString("shashin"));
		list.add(s);

		}

		st.close();
		con.close();

		return list;
	}

	//トップ画面から分類＋キーワードが入力

	//key1.key2は画面から入力された値が入る。（仮でkey1,key2と入れてある)
	//〇商品単価を「昇順」に並べ替えて表示させるメソッド
	public ArrayList<Shohin> searchS1(String key1, String key2) throws Exception {


		//結果格納エリアを確保
		ArrayList<Shohin> list=new ArrayList<Shohin>();

		//商品DB接続
		Connection con=getConnection();


		PreparedStatement st=con.prepareStatement(
		"select * from goods.shohin where daib like ? and sname like ?order by stanka ASC");
		st.setString(1,"%"+key1+"%");
		st.setString(2,"%"+key2+"%");


		//SQL結果をrsに格納
		ResultSet rs=st.executeQuery();

		//結果格納エリアに格納
		while (rs.next()){
		Shohin s=new Shohin();
		s.setShoid(rs.getString("shoid"));
		s.setDaib(rs.getString("daib"));
		s.setChub(rs.getString("chub"));
		s.setShob(rs.getString("shob"));
		s.setSname(rs.getString("sname"));
		s.setSshokai(rs.getString("sshokai"));
		s.setSsetsumei(rs.getString("ssetsumei"));
		s.setStanka(rs.getInt("stanka"));
		s.setZaikosuu(rs.getInt("zaikosuu"));
		s.setShashin(rs.getString("shashin"));
		list.add(s);

		}

		st.close();
		con.close();

		return list;
	}


	//トップ画面から分類＋キーワードが入力

	//key1.key2は画面から入力された値が入る。
	//〇商品単価を「降順」に並べ替えて表示させるメソッド
	public ArrayList<Shohin> searchS2(String key1, String key2) throws Exception {


		//結果格納エリアを確保
		ArrayList<Shohin> list=new ArrayList<Shohin>();

		//商品DB接続
		Connection con=getConnection();


		PreparedStatement st=con.prepareStatement(
		"select * from goods.shohin where daib like ? and sname like ?order by stanka DESC ");
		st.setString(1,"%"+key1+"%");
		st.setString(2,"%"+key2+"%");


		//'食品'は分類が選択された値（引数）
		//		st.setString(1, keyword);	test
		//SQL結果をrsに格納
		ResultSet rs=st.executeQuery();

		//結果格納エリアに格納
		while (rs.next()){
		Shohin s=new Shohin();
		s.setShoid(rs.getString("shoid"));
		s.setDaib(rs.getString("daib"));
		s.setChub(rs.getString("chub"));
		s.setShob(rs.getString("shob"));
		s.setSname(rs.getString("sname"));
		s.setSshokai(rs.getString("sshokai"));
		s.setSsetsumei(rs.getString("ssetsumei"));
		s.setStanka(rs.getInt("stanka"));
		s.setZaikosuu(rs.getInt("zaikosuu"));
		s.setShashin(rs.getString("shashin"));
		list.add(s);

		}

		st.close();
		con.close();

		return list;
	}


	//トップ画面から分類＋キーワードが入力

	//key1.key2は画面から入力された値が入る。
	//〇在庫数を「昇順」に並べ替えて表示させるメソッド
	public ArrayList<Shohin> searchS3(String key1, String key2) throws Exception {


		//結果格納エリアを確保
		ArrayList<Shohin> list=new ArrayList<Shohin>();

		//商品DB接続
		Connection con=getConnection();


		PreparedStatement st=con.prepareStatement(
		"select * from goods.shohin where daib like ? and sname like ?order by zaikosuu ASC ");
		st.setString(1,"%"+key1+"%");
		st.setString(2,"%"+key2+"%");


		//'食品'は分類が選択された値（引数）
		//		st.setString(1, keyword);	test
		//SQL結果をrsに格納
		ResultSet rs=st.executeQuery();

		//結果格納エリアに格納
		while (rs.next()){
		Shohin s=new Shohin();
		s.setShoid(rs.getString("shoid"));
		s.setDaib(rs.getString("daib"));
		s.setChub(rs.getString("chub"));
		s.setShob(rs.getString("shob"));
		s.setSname(rs.getString("sname"));
		s.setSshokai(rs.getString("sshokai"));
		s.setSsetsumei(rs.getString("ssetsumei"));
		s.setStanka(rs.getInt("stanka"));
		s.setZaikosuu(rs.getInt("zaikosuu"));
		s.setShashin(rs.getString("shashin"));
		list.add(s);

		}

		st.close();
		con.close();

		return list;
	}

	//トップ画面から分類＋キーワードが入力

		//key1.key2は画面から入力された値が入る。
		//〇在庫数を「降順」に並べ替えて表示させるメソッド
		public ArrayList<Shohin> searchS4(String key1, String key2) throws Exception {


			//結果格納エリアを確保
			ArrayList<Shohin> list=new ArrayList<Shohin>();

			//商品DB接続
			Connection con=getConnection();


			PreparedStatement st=con.prepareStatement(
			"select * from goods.shohin where daib like ? and sname like ?order by zaikosuu DESC ");
			st.setString(1,"%"+key1+"%");
			st.setString(2,"%"+key2+"%");


			//'食品'は分類が選択された値（引数）
			//		st.setString(1, keyword);	test
			//SQL結果をrsに格納
			ResultSet rs=st.executeQuery();

			//結果格納エリアに格納
			while (rs.next()){
			Shohin s=new Shohin();
			s.setShoid(rs.getString("shoid"));
			s.setDaib(rs.getString("daib"));
			s.setChub(rs.getString("chub"));
			s.setShob(rs.getString("shob"));
			s.setSname(rs.getString("sname"));
			s.setSshokai(rs.getString("sshokai"));
			s.setSsetsumei(rs.getString("ssetsumei"));
			s.setStanka(rs.getInt("stanka"));
			s.setZaikosuu(rs.getInt("zaikosuu"));
			s.setShashin(rs.getString("shashin"));
			list.add(s);

			}

			st.close();
			con.close();

			return list;
		}


				//トップ画面から分類＋キーワードが入力

				//key1.key2は画面から入力された値が入る。
				//〇商品名を「昇順」に並べ替えて表示させるメソッド
				public ArrayList<Shohin> searchS5(String key1, String key2) throws Exception {


					//結果格納エリアを確保
					ArrayList<Shohin> list=new ArrayList<Shohin>();

					//商品DB接続
					Connection con=getConnection();


					PreparedStatement st=con.prepareStatement(
					"select * from goods.shohin where daib like ? and sname like ?order by sname ASC ");
					st.setString(1,"%"+key1+"%");
					st.setString(2,"%"+key2+"%");


					//'食品'は分類が選択された値（引数）
					//		st.setString(1, keyword);	test
					//SQL結果をrsに格納
					ResultSet rs=st.executeQuery();

					//結果格納エリアに格納
					while (rs.next()){
					Shohin s=new Shohin();
					s.setShoid(rs.getString("shoid"));
					s.setDaib(rs.getString("daib"));
					s.setChub(rs.getString("chub"));
					s.setShob(rs.getString("shob"));
					s.setSname(rs.getString("sname"));
					s.setSshokai(rs.getString("sshokai"));
					s.setSsetsumei(rs.getString("ssetsumei"));
					s.setStanka(rs.getInt("stanka"));
					s.setZaikosuu(rs.getInt("zaikosuu"));
					s.setShashin(rs.getString("shashin"));
					list.add(s);

					}

					st.close();
					con.close();

					return list;
				}


				//トップ画面から分類＋キーワードが入力

				//key1.key2は画面から入力された値が入る。
				//〇商品名を「降順」に並べ替えて表示させるメソッド
				public ArrayList<Shohin> searchS6(String key1, String key2) throws Exception {


					//結果格納エリアを確保
					ArrayList<Shohin> list=new ArrayList<Shohin>();

					//商品DB接続
					Connection con=getConnection();


					PreparedStatement st=con.prepareStatement(
					"select * from goods.shohin where daib like ? and sname like ?order by sname DESC ");
					st.setString(1,"%"+key1+"%");
					st.setString(2,"%"+key2+"%");


					//'食品'は分類が選択された値（引数）
					//		st.setString(1, keyword);	test
					//SQL結果をrsに格納
					ResultSet rs=st.executeQuery();

					//結果格納エリアに格納
					while (rs.next()){
					Shohin s=new Shohin();
					s.setShoid(rs.getString("shoid"));
					s.setDaib(rs.getString("daib"));
					s.setChub(rs.getString("chub"));
					s.setShob(rs.getString("shob"));
					s.setSname(rs.getString("sname"));
					s.setSshokai(rs.getString("sshokai"));
					s.setSsetsumei(rs.getString("ssetsumei"));
					s.setStanka(rs.getInt("stanka"));
					s.setZaikosuu(rs.getInt("zaikosuu"));
					s.setShashin(rs.getString("shashin"));
					list.add(s);

					}

					st.close();
					con.close();

					return list;
				}




	//〇商品IDからそのデータを取り出すメソッド
	//管理者の商品管理画面で使うメソッド
	//key1は画面から入力された値が入る
	public ArrayList<Shohin> searchSID(String key1) throws NamingException, SQLException {

		// gakusei型のlist配列
		ArrayList<Shohin> list = new ArrayList<>();

		Connection con = null;
		PreparedStatement st = null;

		try {

			// データベースに接続
			con = getConnection();

			// SQL文を準備・実行
			st = con.prepareStatement(
					"select * from shohin where shoid=?");
			st.setString(1,key1);
			ResultSet rs = st.executeQuery();

			// リターン用にインスタンスを準備
			Shohin d = new Shohin();
			while (rs.next()) {
				d.setShoid(rs.getString("shoid"));
				d.setDaib(rs.getString("daib"));
				d.setChub(rs.getString("chub"));
				d.setShob(rs.getString("shob"));
				d.setSname(rs.getString("sname"));
				d.setSshokai(rs.getString("sshokai"));
				d.setSsetsumei(rs.getString("ssetsumei"));
				d.setStanka(rs.getInt("stanka"));
				d.setZaikosuu(rs.getInt("zaikosuu"));
				d.setShashin(rs.getString("shashin"));
				list.add(d);

			}
		} finally {
			if (st != null) {
				st.close();
			}
			if (con != null) {
				con.close();
			}
		}
		// 検索結果を戻す
		return list;

	}

	//〇商品の追加メソッド　　
	public int insertS(ArrayList<Shohin> list) throws Exception {


		//引数をrへ格納
				Shohin s = list.get(0);


		Connection con =getConnection();




			// データベースに接続
			con = getConnection();

			// SQL文を準備

			PreparedStatement st = con.prepareStatement(
					"insert into shohin value(?, ?, ?, ?, ?, ? ,?, ?, ?, ?)");

					st.setString(1,s.getShoid());
					st.setString(2, s.getDaib());
					st.setString(3, s.getChub());
					st.setString(4, s.getShob());
					st.setString(5,s.getSname());
					st.setString(6, s.getSshokai());
					st.setString(7, s.getSsetsumei());
					st.setInt(8, s.getStanka());
					st.setInt(9, s.getZaikosuu());
					st.setString(10, s.getShashin());

			// SQL文を実行
			int line = st.executeUpdate();

			st.close();
			con.close();

		// 追加できた件数を返す
		return line;
	}

	//〇商品の変更を行うメソッド 未完成

	public int updateS(ArrayList<Shohin> list) throws Exception{


		Shohin u = list.get(0);

		Connection con =getConnection();


			// データベースに接続
			con = getConnection();


			PreparedStatement st = con.prepareStatement
					("update shohin set shoid=?, daib=?, chub=?, shob=?,sname=?, sshokai=?, ssetsumei=?, stanka=?, zaikosuu=?, shashin=? where shoid=?;");

			st.setString(1,u.getShoid());
			st.setString(2,u.getDaib());
			st.setString(3,u.getChub());
			st.setString(4,u.getShob());
			st.setString(5,u.getSname());
			st.setString(6,u.getSshokai());
			st.setString(7,u.getSsetsumei());
			st.setInt(8,u.getStanka());
			st.setInt(9,u.getZaikosuu());
			st.setString(10,u.getShashin());
			st.setString(11,u.getShoid());


			// SQL文を実行
			int line = st.executeUpdate();



				st.close();

				con.close();



		// 追加した件数を戻す
		return line;
}



//〇商品削除メソッド
	public int deleteS(String key1) throws NamingException, SQLException {

		Connection con = null;
		PreparedStatement st = null;
		int line=0;


			// データベースに接続
			con = getConnection();

			// SQL文を準備・実行
			st = con.prepareStatement("delete from shohin where shoid=?");
			st.setString(1, key1);
			line = st.executeUpdate();





		// 更新された本数を返す
		return line;
	}



	//searchAllS
	//商品DBに登録されている商品情報を取得する。(全件表示）
	//引数：なし
	//戻り値：shohin型のArrayList
	public ArrayList<Shohin> searchAllS() throws Exception {

		//結果格納エリアを確保
		ArrayList<Shohin> list=new ArrayList<Shohin>();

		//商品DB接続
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
				"SELECT * FROM shohin");

		//SQL結果をrsに格納
		ResultSet rs=st.executeQuery();

		//結果格納エリアに格納
		while (rs.next()) {
			Shohin s=new Shohin();

			s.setShoid(rs.getString("shoid"));
			s.setDaib(rs.getString("daib"));
			s.setChub(rs.getString("chub"));
			s.setShob(rs.getString("shob"));
			s.setSname(rs.getString("sname"));
			s.setSshokai(rs.getString("sshokai"));
			s.setSsetsumei(rs.getString("ssetsumei"));
			s.setStanka(rs.getInt("stanka"));
			s.setZaikosuu(rs.getInt("zaikosuu"));
			s.setShashin(rs.getString("shashin"));
			list.add(s);
		}
		st.close();
		con.close();

		return list;
	}




	//〇商品DBの在庫数を購入数分減算するメソッド
	//shoidとkonyuuは仮の値
	public int zaikoS(String shoid, int kounyuu) throws Exception{

		Connection con = null;
		PreparedStatement st = null;
		 int line=0;



			// データベースに接続
			con = getConnection();


			st = con.prepareStatement
					("update goods.shohin set zaikosuu = zaikosuu - ? where shoid= ?;");
			st.setInt(1,kounyuu);
			st.setString(2,shoid);


			// SQL文を実行
			 line = st.executeUpdate();



				st.close();

				con.close();

				return line;
	}

	}


