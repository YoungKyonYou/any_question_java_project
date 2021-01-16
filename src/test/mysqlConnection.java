package test;
import java.sql.*;
import javax.swing.*;
public class mysqlConnection {
	Connection conn=null;
	static String url = "jdbc:mysql://localhost:3306/logininfo?useUnicode=true&passwordCharacterEncoding=utf-8";
	static String user = "jspbook";
	static String password = "jayyou!3204";
	public static Connection dbConnector() {
		try {
			Connection con = null;
			//con = DriverManager.getConnection("jdbc:mysql://admin.cys6irkoowji.ap-northeast-2.rds.amazonaws.com/LoginInfo","admin", "jayyou!3204");
			con = DriverManager.getConnection(url, user, password);
			//JOptionPane.showMessageDialog(null, "Connection Successful");
			return con;
		} catch (SQLException sqex) {
			JOptionPane.showMessageDialog(null, sqex);
			return null;
		}
	}

}
