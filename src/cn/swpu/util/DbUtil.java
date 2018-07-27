package cn.swpu.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DbUtil {
//    private String dbUrl = "jdbc:mysql://118.126.110.120:3306/SCLife";
//    private String dbUserName = "root";
//    private String dbPassword = "zb104207";
//    private String jdbcName ="com.mysql.jdbc.Driver";
//    
//    public Connection getCon() throws ClassNotFoundException, SQLException {
//        Class.forName(jdbcName);
//        Connection con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
//        return con;
//    }
 
    public void closeCon(Connection con) throws SQLException {
        if (con != null) {
            con.close();
        }
    }
//    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		new DbUtil().getCon();
//	}
    
    public Connection getCon() throws ClassNotFoundException, SQLException {
    	Connection con=null;
		InputStream in=null;//input流
		
		try {
			in=this.getClass().getResourceAsStream("/jdbc.properties");//从properties文件获取url，username，password
			Properties prop=new Properties();
			prop.load(in);
			/**
			 * 获取properties内容存入此类中的变量
			 */
			String driver=prop.getProperty("driver");
			String url=prop.getProperty("url");
			String userName=prop.getProperty("userName");
			String password=prop.getProperty("password");
			
			Class.forName(driver);//加载动态driver类
			con=DriverManager.getConnection(url,userName,password);//实例化对象，注册到DriverManager
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		return con;
    }
}
