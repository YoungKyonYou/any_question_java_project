package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.GridBagConstraints;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.TextArea;

import javax.swing.JEditorPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.sql.*;
import javax.swing.JScrollBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;

public class StuChat extends JFrame {

   private JPanel contentPane;
   private JButton sendButton;
   private JTextField searchField;
   private JList allClass;
   private JList myClass;
   private JEditorPane editorPane;
   private int IdNumber;  ////
   private String tabelName;
   private TextArea textPane;
   private JLabel lglbl;
   private JLabel studentlbl;
   private JButton refreshbtn;
   private String communication_number;
   private Connection connection=mysqlConnection.dbConnector();
   private JButton startChatbtn;
   private static int flag=0;
   private ProThread pt;
   private JButton btnNewButton;
   public static void main(String[] args)  
   {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
            	StuChat frame = new StuChat(7);
               frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }
   public static int getFlag()
   {
		//System.out.println("Flag:"+flag);
	   return flag;
   }
   public String getClassname() {
	   String target = ":";
	   String listName = (String)allClass.getSelectedValue();
	   String Classname = listName.substring(0,listName.indexOf(target)-2);

	   return Classname;
   }
   
   public String getClassPro() {
	   String target = ":";
	   String listName = (String)allClass.getSelectedValue();
	   String ClassPro = listName.substring(listName.indexOf(target)+1,listName.length());
	   
	   return ClassPro;
   }
   
   public String getClassProToMyList() {
	   try
	   {
		   String target = ":";
		   String listName = (String)myClass.getSelectedValue();
		   String ClassPro = listName.substring(listName.indexOf(target)+1,listName.length());
		   
		   return ClassPro;
	   }
	   catch(NullPointerException e)
	   {
		   JOptionPane.showMessageDialog(null, "Please Check the Class before sending a Message");
		   return null;
	   }
   }
   
   public String getClassnameToMyList() {
	   String target = ":";
	   String listName = (String)myClass.getSelectedValue();
	   String Classname = listName.substring(0,listName.indexOf(target)-2);

	   return Classname;
   }
   
   public String getClassno() throws SQLException {
	   PreparedStatement st;
	  // Connection conn = null;
	   ResultSet rs = null;
	   String query = "SELECT * FROM Class WHERE Classname= ? AND Class_pro= ? ";
	   
	   String result = null;
	   
	   //System.out.println(query);
	   try {
	   	//  conn = DriverManager.getConnection("jdbc:mysql://admin.cys6irkoowji.ap-northeast-2.rds.amazonaws.com/LoginInfo",
	   			//  "admin", "jayyou!3204");
	   	  
	   	  st = connection.prepareStatement(query);
	   	  st.setString(1,getClassname());
	   	  st.setString(2,getClassPro());
	   	  rs = st.executeQuery();
	   	  //System.out.println(rs);
	   	  if(rs.next()) {
	   		result = rs.getString("Class_No");
	   	  }
	
	   	  } catch (SQLException ex) {
	   			JOptionPane.showMessageDialog(null, "error error");
	   	  }
	 
	   return result;
   }
   
   public String getClassnoToMyList() throws SQLException {
	   PreparedStatement st;
	  // Connection conn = null;
	   ResultSet rs = null;
	   String query = "SELECT * FROM Class WHERE Classname= ? AND Class_pro= ? ";
	   
	   String result = null;
	   
	   //System.out.println(query);
	   try {
	   //	  conn = DriverManager.getConnection("jdbc:mysql://admin.cys6irkoowji.ap-northeast-2.rds.amazonaws.com/LoginInfo",
	   		//	  "admin", "jayyou!3204");
	   	  
	   	  st = connection.prepareStatement(query);
	   	  st.setString(1,getClassnameToMyList());
	   	  st.setString(2,getClassProToMyList());
	   	  rs = st.executeQuery();
	   	  //System.out.println(rs);
	   	  if(rs.next()) {
	   		result = rs.getString("Class_No");
	   	  }
	
	   	  } catch (SQLException ex) {
	   			JOptionPane.showMessageDialog(null, "error error");
	   	  }
	 
	   return result;
   }
   
   public String getProNo() throws SQLException {
	   PreparedStatement st;
	  // Connection conn = null;
	   ResultSet rs = null;
	   String query = "SELECT * FROM Class WHERE Class_No= ? ";
	   String result = null;
	   
	   try {
	   //	  conn = DriverManager.getConnection("jdbc:mysql://admin.cys6irkoowji.ap-northeast-2.rds.amazonaws.com/LoginInfo",
	   	//		  "admin", "jayyou!3204");
	   	  
	   	  st = connection.prepareStatement(query);
	   	  st.setString(1,getClassno());
	   	  
	   	  rs = st.executeQuery();
	   	  //System.out.println(rs);
	   	  if(rs.next()) {
	   		result = rs.getString("Class_pro");
	   	  }
	   	  
	   	  query = "SELECT * FROM LoginInfo_Pro WHERE Name= ? ";
	   	  st = connection.prepareStatement(query);
	   	  st.setString(1,result);
	   	  rs = st.executeQuery();
	   	  
	   	  if(rs.next()) {
	   		result = rs.getString("Id_number");
	   	  }
	   	
	   	  } catch (SQLException ex) {
	   			JOptionPane.showMessageDialog(null, "error error"); 
	   	  }
	 
	   return result;
   }
   
   public boolean findByClassNo(int no) {
	   PreparedStatement st;
       ResultSet rs;
      // Connection conn;
       String query = "SELECT Class_No FROM ? WHERE Class_No = ?";
       try {
       	//conn=DriverManager.getConnection("jdbc:mysql://admin.cys6irkoowji.ap-northeast-2.rds.amazonaws.com/LoginInfo","admin", "jayyou!3204");
           st = connection.prepareStatement(query);
           st.setString(1, tabelName);
           st.setString(2, Integer.toString(no));
           rs = st.executeQuery();
           if(rs.next())
           {
               JOptionPane.showMessageDialog(null, "Already Exists", "Message", JOptionPane.WARNING_MESSAGE);
               return false;
           }
           
       } catch (SQLException ex) {
       	JOptionPane.showMessageDialog(null, "error error");
       }
       return true;
   }
   
   /**
    * Create the frame.
    */
   public StuChat(int id) {
	   IdNumber=id;
	   tabelName = Integer.toString(IdNumber)+"_Class";
	   System.out.println("test idnumber: "+IdNumber);
	   System.out.println("test tabelName: "+tabelName);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 1078, 627);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);
      
      sendButton = new JButton("");
      sendButton.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent arg0) {
      		PreparedStatement st;
         //   Connection conn = null;
            ResultSet rs = null;
            String query;   
            try {
			//	conn = DriverManager.getConnection("jdbc:mysql://admin.cys6irkoowji.ap-northeast-2.rds.amazonaws.com/LoginInfo",
				//		  "admin", "jayyou!3204");
			
            query="Insert into ";
            String query2;
			query2 = "communication_"+getClassnoToMyList();
            String query3="(Student_id, content, Professor_id) values (?,?,?)";
            query=query+query2+query3;
            String test;
            System.out.println("query test:"+query);
      		
				st = connection.prepareStatement(query);
				st.setInt(1,IdNumber);
          		st.setString(2,editorPane.getText());
          		st.setInt(3,0);
          	  
          		st.executeUpdate();
      	
			
      		
      		
      		Statement state2=null;
      		
			state2=connection.createStatement();
		
      		 
      		 String date=null;
      		Statement state=null;
      		
      	//	conn = DriverManager.getConnection("jdbc:mysql://admin.cys6irkoowji.ap-northeast-2.rds.amazonaws.com/LoginInfo",
	          //			  "admin", "jayyou!3204");
     		    ResultSet rs2=null;
  	         state=connection.createStatement();
  	         String sql;
  	         sql="SELECT * FROM ";
  	         String sql3="`communication_"+communication_number+"`";
  	         sql=sql+sql3;
  	         rs2=state.executeQuery(sql);
  	        while(rs2.next())
	        	
  	        date=rs2.getString("date");
	        	
      		
      			
   
      		
      		 
      		textPane.append(String.format("anonymous:\n%s\n%s\n\n", editorPane.getText(),date)); 
      		editorPane.selectAll();
      		
            } catch (SQLException e1) {
				e1.printStackTrace();
			}
  		    editorPane.setText("");
      	}
      });
      
      lglbl = new JLabel("");
      Image img7=new ImageIcon(this.getClass().getResource("/logo2.png")).getImage();
      
      refreshbtn = new JButton("Refresh");
      refreshbtn.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		textPane.setText("");
      		PreparedStatement st;
  		//	Connection conn = null;
  			ResultSet rs = null;
            String query;
            try {
	          //	  conn = DriverManager.getConnection("jdbc:mysql://admin.cys6irkoowji.ap-northeast-2.rds.amazonaws.com/LoginInfo",
	          		//	  "admin", "jayyou!3204");
	          	Statement state=null;
	          	state=connection.createStatement();
    	         String sql;
    	         sql="SELECT * FROM ";
    	         String sql3="`communication_"+communication_number+"`";
    	         System.out.println("test class no:"+getClassnoToMyList());
    	         sql=sql+sql3;
    	         rs=state.executeQuery(sql);
    	        while(rs.next())
 	        	{
 		        		 int msg_num=rs.getInt("msg_num");
 		        		 
 		        		 String Student_id=rs.getString("Student_id");
 		        		 String content=rs.getString("content");
 		        		 String date=rs.getString("date");
 		        		 if(!Student_id.equals("0"))
 		        			 textPane.append(String.format("anonymous%s:\n%s \n%s\n\n",Student_id, content,date)); 
 		        		 else
 		        			textPane.append(String.format("Professor:\n%s\n%s\n\n", content,date)); 
 	        	}
	            	} catch (SQLException ex) {
	      	  			JOptionPane.showMessageDialog(null, "error error");
	      	  		}
      	}
      	
      });
      refreshbtn.setFont(new Font("Bookman Old Style", Font.BOLD, 17));
      refreshbtn.setBounds(741, 534, 116, 34);
      contentPane.add(refreshbtn);
      lglbl.setIcon(new ImageIcon(img7));
      lglbl.setBounds(758, 43, 280, 75);
      contentPane.add(lglbl);
      
      studentlbl = new JLabel("");
      Image img8=new ImageIcon(this.getClass().getResource("/student.png")).getImage();
      studentlbl.setIcon(new ImageIcon(img8));
      studentlbl.setBounds(741, 195, 297, 280);
      contentPane.add(studentlbl);
      sendButton.setBounds(620, 487, 105, 81);
      contentPane.add(sendButton);
      
      Image img4=new ImageIcon(this.getClass().getResource("/cloud.png")).getImage();
      sendButton.setIcon(new ImageIcon(img4));
      JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      scrollPane.setBounds(323, 487, 285, 81);
      contentPane.add(scrollPane);
      
      editorPane = new JEditorPane();
      scrollPane.setViewportView(editorPane);
      
      JButton search = new JButton("Search");
      search.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent arg0) {
      		PreparedStatement st;
        //    Connection conn = null;
            ResultSet rs = null;
            String query = "SELECT * FROM Class WHERE Classname LIKE ? ";
            
            DefaultListModel listModel = new DefaultListModel();
            try {
          	//onn = DriverManager.getConnection("jdbc:mysql://admin.cys6irkoowji.ap-northeast-2.rds.amazonaws.com/LoginInfo",
          		//	  "admin", "jayyou!3204");
          	  st = connection.prepareStatement(query);
          	  st.setString(1,"%"+searchField.getText()+"%");
          	  
          	  rs = st.executeQuery();
      		  
          	  while(rs.next()) {
          		  String Classname = (rs.getString("Classname")+
      						" P:"+rs.getString("Class_pro"));
          		  listModel.addElement(Classname);
      		  }
          	  allClass.setModel(listModel);
      		  
            		} catch (SQLException ex) {
      	  			JOptionPane.showMessageDialog(null, "error error");
      	  		}
            
      	
      	}
      	
      });
      search.setBounds(12, 10, 91, 23);
      contentPane.add(search);
      //*****************************************
      searchField = new JTextField();
      searchField.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		PreparedStatement st;
           // Connection conn = null;
            ResultSet rs = null;
            String query = "SELECT * FROM Class WHERE Classname LIKE ? ";
            
            DefaultListModel listModel = new DefaultListModel();
            try {
          	//  conn = DriverManager.getConnection("jdbc:mysql://admin.cys6irkoowji.ap-northeast-2.rds.amazonaws.com/LoginInfo",
          	//		  "admin", "jayyou!3204");
          	  st = connection.prepareStatement(query);
          	  st.setString(1,"%"+searchField.getText()+"%");
          	  
          	  rs = st.executeQuery();
      		  
          	  while(rs.next()) {
          		  String Classname = (rs.getString("Classname")+
      						" P:"+rs.getString("Class_pro"));
          		  listModel.addElement(Classname);
      		  }
          	  allClass.setModel(listModel);
      		  
          	  
          	  
            	} catch (SQLException ex) {
      	  			JOptionPane.showMessageDialog(null, "error error");
      	  		}
            searchField.selectAll();
      	}
      });
      searchField.setBounds(122, 11, 187, 21);
      contentPane.add(searchField);
      searchField.setColumns(10);
      
      JLabel lblMyClass = new JLabel("MY CLASS");
      lblMyClass.setBounds(12, 204, 163, 15);
      contentPane.add(lblMyClass);
      
      myClass = new JList();
      myClass.setBackground(new Color(204, 255, 204));
      myClass.addMouseListener(new MouseAdapter() {
      	@Override
      	public void mouseClicked(MouseEvent e) {//if clicked then goes to that professor's class
      		textPane.setText("");
      			PreparedStatement st;
      			//Connection conn = null;
      			ResultSet rs = null;
	            String query;
	            try {
    	         // 	  conn = DriverManager.getConnection("jdbc:mysql://admin.cys6irkoowji.ap-northeast-2.rds.amazonaws.com/LoginInfo",
    	          	//		  "admin", "jayyou!3204");
    	          	Statement state=null;
    	          	state=connection.createStatement();
        	         String sql;
        	         communication_number=getClassnoToMyList();
        	         sql="SELECT * FROM ";
        	         String sql3="`communication_"+getClassnoToMyList()+"`";
        	         System.out.println("communication num:"+getClassnoToMyList());
        	         sql=sql+sql3;
        	         rs=state.executeQuery(sql);
        	        while(rs.next())
     	        	{
     		        		 int msg_num=rs.getInt("msg_num");
     		        		 
     		        		 String Student_id=rs.getString("Student_id");
     		        		 String content=rs.getString("content");
     		        		 String date=rs.getString("date");
     		        		 if(!Student_id.equals("0"))
     		        			 textPane.append(String.format("anonymous%s:\n%s \n%s\n\n",Student_id, content,date)); 
     		        		 else
     		        			textPane.append(String.format("Professor:\n%s\n%s\n\n", content,date)); 
     	        	}
    	            	} catch (SQLException ex) {
    	      	  			JOptionPane.showMessageDialog(null, "error error");
    	      	  		}
	            
      	}
      });
      myClass.setBounds(12, 229, 297, 339);
      contentPane.add(myClass);
      
      JScrollPane allClassScroll = new JScrollPane();
      allClassScroll.setBounds(12, 43, 297, 139);
      contentPane.add(allClassScroll);
      
      allClass = new JList();
      allClass.setBackground(new Color(255, 255, 204));
      allClass.addMouseListener(new MouseAdapter() {
      	@Override
      	public void mouseClicked(MouseEvent e) {
      		if(e.getClickCount()==2) {
      			int cf = JOptionPane.showConfirmDialog(null, "Will you add this Lecture to My Class?", "Message", JOptionPane.OK_CANCEL_OPTION);
      			if(cf==0) {
      				PreparedStatement st;
      	           // Connection conn = null;
      	            ResultSet rs = null;
      	            String query = "INSERT INTO "+tabelName+"(Class_No, Classname,Class_pro) VALUES (?,?,?)";
      	            
      	            try {
      	          	//  conn = DriverManager.getConnection("jdbc:mysql://admin.cys6irkoowji.ap-northeast-2.rds.amazonaws.com/LoginInfo",
      	          	//		  "admin", "jayyou!3204");
      	          	  
      	          	  st = connection.prepareStatement(query);
      	          	  //st.setString(1, tabelName);
      	          	  st.setInt(1,Integer.parseInt(getClassno()));
      	          	  st.setString(2,getClassname());
      	          	  st.setString(3,getClassPro());
      	          	  
      	          	  st.executeUpdate();
      	          	  
      	          	  DefaultListModel listModel = new DefaultListModel();
    	          	  query = "SELECT * FROM "+tabelName;
    	          	  st = connection.prepareStatement(query);
    	          	  rs = st.executeQuery();
    	          	  
    	          	  while(rs.next()) {
    	          		  String Classname = (rs.getString("Classname")+
    							" P:"+rs.getString("Class_pro"));
    	          		  listModel.addElement(Classname);
    	          	  }
    	          	  myClass.setModel(listModel);
    	          	  
    	          	  query = "INSERT INTO Stu_Info(Stu_ID, Class_No, Pro_No) VALUES (?,?,?)";
    	          	  st = connection.prepareStatement(query);
    	          	  st.setInt(1,IdNumber);
    	          	  st.setInt(2,Integer.parseInt(getClassno()));
    	          	  st.setInt(3,Integer.parseInt(getProNo()));
    	          	  st.executeUpdate();
      	            	} catch (SQLException ex) {
      	      	  			JOptionPane.showMessageDialog(null, "error error");
      	      	  		}
      	      	}
      		}
      	}
      	
      });
  allClassScroll.setViewportView(allClass);
      
      PreparedStatement st;
    //  Connection conn = null;
      ResultSet rs = null;
      String query = "SELECT * FROM Class";
      DefaultListModel listModel1 = new DefaultListModel();
      DefaultListModel listModel2 = new DefaultListModel();
      try {
    	//  conn = DriverManager.getConnection("jdbc:mysql://admin.cys6irkoowji.ap-northeast-2.rds.amazonaws.com/LoginInfo",
    	//		  "admin", "jayyou!3204");
    	  st = connection.prepareStatement(query);
    	  rs = st.executeQuery();
		  
    	  while(rs.next()) {
    		  String Classname = (rs.getString("Classname")+
						" P:"+rs.getString("Class_pro"));
    		  listModel1.addElement(Classname);
		  }
    	  allClass.setModel(listModel1);
    	  
    	  query = "SELECT * FROM "+tabelName;
    	  st = connection.prepareStatement(query);
    	  rs = st.executeQuery();
    	  
    	  while(rs.next()) {
    		  String Classname = (rs.getString("Classname")+
						" P:"+rs.getString("Class_pro"));
    		  listModel2.addElement(Classname);
		  }
    	  myClass.setModel(listModel2);
    	  
    	  textPane = new TextArea();
    	  textPane.setBackground(new Color(224, 255, 255));
    	  textPane.setBounds(323, 10, 402, 465);
    	  contentPane.add(textPane);
    	  
    	  startChatbtn = new JButton("Request a Chat");
    	  startChatbtn.addActionListener(new ActionListener() {
    	  	public void actionPerformed(ActionEvent e) {
    	  		ChatToWho ctw=new ChatToWho(IdNumber);
    	  		ctw.setVisible(true);
    	  	}
    	  });
    	  startChatbtn.setFont(new Font("Bookman Old Style", Font.BOLD, 17));
    	  startChatbtn.setBounds(871, 534, 175, 32);
    	  contentPane.add(startChatbtn);
    	  
    	  btnNewButton = new JButton("Send Email");
    	  btnNewButton.setFont(new Font("Bookman Old Style", Font.BOLD, 17));
    	  btnNewButton.setBounds(741, 490, 305, 34);
    	  contentPane.add(btnNewButton);
    	  btnNewButton.addActionListener(new ActionListener() {  /////////////////////////email
			@Override
			public void actionPerformed(ActionEvent e) {
				PreparedStatement st;
   	           // Connection conn = null;
   	            ResultSet rs = null;
   	            String query;
   	            
   	            String myEmail = null;
   	            String proEmail = null;
   	            
   	            try {
   	            	query = "select * from LoginInfo where Id_number ="+Integer.toString(IdNumber);
					st = connection.prepareStatement(query);
					rs = st.executeQuery();
					while(rs.next()) {
						myEmail = rs.getString("Email");
					  }
					String classno=getClassProToMyList();
					if(classno==null)
						return;
					query = "select * from LoginInfo_Pro where Name = '"+classno+"'";
					st = connection.prepareStatement(query);
					rs = st.executeQuery();
					while(rs.next()) {
						proEmail = rs.getString("Email");
					  }
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				new EmailLogin(myEmail,proEmail);
			}
		});
    	  
      		} catch (SQLException ex) {
	  			JOptionPane.showMessageDialog(null, "error error");
	  		}
   }
   
   
   
   /*class ThreadTest extends Thread
   {
	   @Override
		public void run() {
			super.run();
			if(flag==1)
			{
				System.out.println("here");
				ProThread.flag=1;
				//ProThread pt=ProChat.getProThread();
			//	pt.testSetLabel();
			}
		}
   }*/
}