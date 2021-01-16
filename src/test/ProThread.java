package test;

import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProThread extends Thread {
	private JLabel lbl;
	private int idx = 0;
	private int idnum;
	private Statement state = null;
	private Connection conn = mysqlConnection.dbConnector();

	public ProThread(JLabel lbl, int idnum) {
		this.idnum = idnum;
		this.lbl = lbl;
	}

	@Override
	public void run() {
		super.run();
		while (true) {
			int flag = 0;
			String flag_query = "Select Chat_Flag from LoginInfo_Pro where Id_number=";
			flag_query = flag_query + Integer.toString(idnum);
			ResultSet rs = null;
			try {
				state = conn.createStatement();
				rs = state.executeQuery(flag_query);
				while (rs.next()) {
					flag = rs.getInt("Chat_Flag");
					break;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			if (flag == 1) {
				lbl.setText("Chat Requested From Student");
			} else {
				lbl.setText("No Chat Request");
			}
		}
	}
}
