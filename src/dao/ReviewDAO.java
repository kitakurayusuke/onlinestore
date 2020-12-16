package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.Review;

public class ReviewDAO extends DAO {

	//searchVW
	//指定された商品ＩＤのレコードを抽出する
	//引数：商品ＩＤ
	//戻り値：review型のArrayList
	public ArrayList<Review> searchVW(String keyword) throws Exception {

		ArrayList<Review> list=new ArrayList<>();

		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"select * from review where reviewsid = ?");

		st.setString(1, keyword);
		ResultSet rs=st.executeQuery();

		while (rs.next()) {
			Review p=new Review();

			p.setViewid(rs.getInt("viewid"));
			p.setReviewsid(rs.getString("reviewsid"));
			p.setReview(rs.getString("review"));
			list.add(p);
		}
		st.close();
		con.close();

		return list;
	}


	//insertVW
	//レビューをＤＢへ登録する
	//引数：review型のArrayList
	//戻り値：int型（登録レコード件数。常に１件）
	public int insertVW(ArrayList<Review> list) throws Exception {

		//引数をrへ格納
		Review r = list.get(0);

		//商品レビューDB接続
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
				"insert into review value(null,?,?)");

		st.setString(1, r.getReviewsid());
		st.setString(2, r.getReview());

		//SQL結果をlineに格納
		int line = st.executeUpdate();

		st.close();
		con.close();

		return line;
	}

}
