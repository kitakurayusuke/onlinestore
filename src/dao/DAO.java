package dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * データベースに接続するクラス
 *
 * @author TEACHER
 * @version 2019/10/31
 */
public class DAO {

	// フィールド定数
	static DataSource ds;

	/**
	 * コネクションを取得する
	 *
	 * @return コネクション
	 * @throws NamingException
	 * @throws SQLException
	 */
	public Connection getConnection() throws NamingException, SQLException {

		if (ds == null) {
			InitialContext ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:/comp/env/jdbc/mysql");
			// ds = (DataSource)ic.lookup("java:/comp/env/jdbc/book");
		}

		return ds.getConnection();
	}
}
