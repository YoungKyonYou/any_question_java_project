package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class ChatToWho extends JFrame {

	private JPanel contentPane;
	private Connection connection=mysqlConnection.dbConnector();
	private JList list;
	private int IdNumber;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatToWho frame = new ChatToWho(7);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public String getClassPro() {
		   String listName = (String)list.getSelectedValue();
		
		   
		   return listName;
	   }
	
	public String getClassno() throws SQLException {
		   PreparedStatement st;
		   ResultSet rs = null;
		   String query = "SELECT * FROM LoginInfo_Pro WHERE Name= ?";
		   
		   String result = null;
		   
		   
		   try {
		   	  
		   	  st = connection.prepareStatement(query);
		   	  st.setString(1,getClassPro());
		   	  rs = st.executeQuery();
		   	  
		   	  if(rs.next()) {
		   		result = rs.getString("Id_number");
		   	  }
		
		   	  } catch (SQLException ex) {
		   			JOptionPane.showMessageDialog(null, "error error");
		   	  }
		 
		   return result;
	   }

	/**
	 * Create the frame.
	 */
	public ChatToWho(int IdNumber) {
		this.IdNumber=IdNumber;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 258, 414);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name of Professor");
		lblNewLabel.setBounds(12, 10, 121, 20);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 47, 218, 297);
		contentPane.add(scrollPane);
		
		list = new JList();
		scrollPane.setViewportView(list);
		list.addMouseListener(new MouseAdapter() {
	      	@Override
	      	public void mouseClicked(MouseEvent e) {
	      		if(e.getClickCount()==2) {
	      			int cf = JOptionPane.showConfirmDialog(null, "진자 이교수닌과 대활르 할 것인가?", "Message", JOptionPane.OK_CANCEL_OPTION);
	      				if(cf==0) {
	      					try {
	      						System.out.println(getClassno());
	      						StuThread st=new StuThread(Integer.parseInt(getClassno()));
		      					st.start();
	      						ReceiveThread r=new ReceiveThread(Integer.toString(IdNumber),getClassPro());
	      						r.start();
	      					} catch (IOException e1) {
	      						// TODO Auto-generated catch block
	      						JOptionPane.showMessageDialog(null, "Professor is talking with another Student");
	      						e1.printStackTrace();
	      					} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
	      					dispose();
	      				}
	      	      	}
	      		}
	      });
		
		PreparedStatement st;
	    
	    ResultSet rs = null;
	    String query = "select * from LoginInfo_Pro";
	    DefaultListModel listModel = new DefaultListModel();
	      try {
	    	  st = connection.prepareStatement(query);
	    	  rs = st.executeQuery();
			  
	    	  while(rs.next()) {
	    		  listModel.addElement(rs.getString("Name"));
			  }
	    	  list.setModel(listModel);
	      }catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
	      }
	}
}
