package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.User;
import util.DBUtils;

public class UserDAO {
	/**
	 * �����û������Ҷ�Ӧ���û�������
	 * �û���������Ϣ��
	 */
	public User find(String username) throws SQLException{
		User user = null;
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConn();
			stat = conn.prepareStatement(
				"SELECT * FROM t_user WHERE username=?");
			stat.setString(1, username);
			rs = stat.executeQuery();
			if(rs.next()){
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(username);
				user.setPwd(rs.getString("password"));
				user.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally{
			DBUtils.close(conn, stat, rs);
		}
		return user;
	}
	
	
	
	/**
	 * ɾ��ָ��id���û�
	 */
	public void delete(int id) throws SQLException {
		Connection conn = null;
		PreparedStatement stat = null;
		try {
			conn = DBUtils.getConn();
			stat = conn.prepareStatement(
					"DELETE FROM t_user WHERE id=?");
			stat.setInt(1, id);
			stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			DBUtils.close(conn, stat, null);
		}
		
	}
	
	/**
	 * ���û���Ϣ���뵽���ݿ�(t_user��)
	 */
	public void save(User user) throws SQLException {
		Connection conn = null;
		PreparedStatement stat = null;
		try {
			conn = DBUtils.getConn();
			stat = conn.prepareStatement(
				"INSERT INTO t_user VALUES(null,?,?,?)");
			stat.setString(1, user.getUsername());
			stat.setString(2, user.getPwd());
			stat.setString(3, user.getEmail());
			stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			DBUtils.close(conn, stat, null);
		}
	}
	

	/**
	 * ��ѯt_user���������û���Ϣ��ѯ������
	 */
	public List<User>  findAll() throws SQLException{
		List<User> users = new ArrayList<User>();
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConn();
			stat = conn.prepareStatement(
				"SELECT * FROM t_user");
			rs = stat.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String username = 
						rs.getString("username");
				String pwd = 
						rs.getString("password");
				String email = 
						rs.getString("email");
				User user = new User();
				user.setId(id);
				user.setUsername(username);
				user.setPwd(pwd);
				user.setEmail(email);
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			DBUtils.close(conn, stat, rs);
		}
		return users;
	}
}






