package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import Entity.User;

public class UserDao extends BaseDao {

	public User searchByUserNameAndPwd(User searchUser) {
		User u = null;
		Connection conn = null;
		try {

			getStatement();
			ResultSet rs = stat
					.executeQuery("select * from users where username='"
							+ searchUser.getUsername() + "' and pwd='"
							+ searchUser.getPwd() + "'");

			if (rs.next()) {
				u = new User();
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setPwd(rs.getString("pwd"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return u;
	}
	public User searchByName(String name) {
		User u = null;
		Connection conn = null;
		try {

			getStatement();
			ResultSet rs = stat
					.executeQuery("select * from users where username='"
							+ name + "'");

			if (rs.next()) {
				u = new User();
				u.setId(rs.getInt("id"));
				u.setTime(rs.getTimestamp("time"));
				u.setUsername(rs.getString("username"));
				

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return u;
	}

	public User add(User user) {

		User u = null;

		try {

			String sql = "insert into users(username,time) values(?,?)";
			Connection conn = getConnection();

			PreparedStatement pstat = conn.prepareStatement(sql);
			pstat.setString(1, user.getUsername());
		//	pstat.setString(2, user.getPwd());
			
			pstat.setTimestamp(2, user.getTime());
			int result = pstat.executeUpdate();
			if (result > 0) {
				sql = "select last_insert_id()";
				ResultSet rs = pstat.executeQuery(sql);

				while (rs.next()) {
					u = new User();
					u.setId(rs.getInt(1));
					u.setUsername(user.getUsername());

				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return u;

	}
	
	public User update(User user) {

		User u = null;

		try {

			String sql = "update users set pwd=? where id=?";
			Connection conn = getConnection();

			PreparedStatement pstat = conn.prepareStatement(sql);
			pstat.setString(1, user.getPwd());
			pstat.setInt(2, user.getId());			
			
			int result = pstat.executeUpdate();
			if (result > 0) {
				u=user;

			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return u;

	}

}
