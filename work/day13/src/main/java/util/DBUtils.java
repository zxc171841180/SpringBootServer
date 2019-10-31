package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

public class DBUtils {
	private static BasicDataSource dataSource;
	static {
		//�������Զ���
		Properties prop = new Properties();
		//�õ��ļ�������
		InputStream ips = DBUtils.class.getClassLoader()
					.getResourceAsStream("jdbc.properties");
		try {
			prop.load(ips);
			//��ȡ���ݿ�������Ϣ
			String driver = prop.getProperty("driver");
			String url = prop.getProperty("url");
			String username = prop.getProperty("username");
			String password = prop.getProperty("password");
			//��������Դ����
			dataSource = new BasicDataSource();
			dataSource.setDriverClassName(driver);
			dataSource.setUrl(url);
			dataSource.setUsername(username);
			dataSource.setPassword(password);
			dataSource.setInitialSize(3);
			dataSource.setMaxActive(3);
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	//��ȡ����
	public static Connection getConn() throws SQLException {
		return dataSource.getConnection();
	}
	//�ر���Դ
	public static void close(Connection conn,Statement stat,
			ResultSet rs) {
		try {
			if(rs!=null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(stat!=null) {
				stat.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(conn!=null) {
				//���Զ��ύ
				conn.setAutoCommit(true);
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}





