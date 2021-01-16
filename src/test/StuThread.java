package test;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
public class StuThread extends Thread{
	private JLabel lbl;
	public static int flag=0;
	private int idnum;
	private Connection conn=mysqlConnection.dbConnector();
	private Statement state=null;
	public StuThread(int idnum)
	{
		this.idnum=idnum;
	}

	@Override
	public void run() 
	{
		super.run();
		int flag=0;
		String query="Update LoginInfo_Pro set Chat_Flag=1 where Id_number=?";
		String flag_query="Select Chat_Flag from LoginInfo_Pro where Id_number=";
		flag_query=flag_query+Integer.toString(idnum);
		ResultSet rs=null;
		try 
		{
			state=conn.createStatement();
			rs=state.executeQuery(flag_query);
			while(rs.next())
			{
					flag=rs.getInt("Chat_Flag");
					break;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		System.out.println("Flag:"+flag);
		if(flag==0)
		{
			System.out.println("Flag inside");
			try {
				PreparedStatement pst=conn.prepareStatement(query);
				pst.setInt(1,idnum);
				pst.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		
		
		
	}
}



















