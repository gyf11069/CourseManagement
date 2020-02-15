package util;

import java.sql.*;

/**
 * @author 高云飞
 * @site WWW.java.com
 * @company 致远有限公司
 * @create 2019-12-27 9:50
 */
public class JDBCConnection {

    //数据库地址
    private static  String URL = "jdbc:mysql://localhost:3306/course";
    //用户名
    private static  String USERNAME = "root";
    //密码
    private static  String PASSWORD = "root";
    /**
     * 获取连接
     * @return
     * @throws SQLException
     */
    public static Connection getConnection()throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(URL,USERNAME,PASSWORD);
        return con;
    }
    /**
     * 关闭连接
     * @param
     */
    public static void close(PreparedStatement pstmt, Connection con) {
        if( null != con) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if( null != pstmt) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }



    }
    public static void close1(Connection con){
        if( null != con) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

