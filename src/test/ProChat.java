package test;
import javax.swing.text.html.HTMLEditorKit;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.text.*;
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
import java.awt.Font;

public class ProChat extends JFrame {

	public JLabel requestlbl;
	private int flag=0;
	private String CN;
	private JPanel contentPane;
	private JButton sendButton;
	private TextArea textPane;
	private JTextField searchField;
	private JList allClass;
	private JList myClass;
	private JList myClassList;
	private JList stuList;
	private JEditorPane editorPane;
	private static int IdNumber;
	private String tabelName;
	private String communication_number;
	private Connection connection=mysqlConnection.dbConnector();
	
	public ProThread pt;
   
   public static void main(String[] args)  
   {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
            	ProChat frame = new ProChat(19);
               frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }
   public void setFlag(int f)
   {
	   flag=f;
   }
   
   public String getProname() {
	   PreparedStatement st;
	  // Connection conn = null;
	   ResultSet rs = null;
	   String query = "SELECT * FROM LoginInfo_Pro WHERE Id_number= ?";
	   String result = null;
	   
	   try {
	   	 // conn = DriverManager.getConnection("jdbc:mysql://admin.cys6irkoowji.ap-northeast-2.rds.amazonaws.com/LoginInfo",
	   			//  "admin", "jayyou!3204");
	   	  
	   	  st = connection.prepareStatement(query);
	   	  st.setString(1, Integer.toString(IdNumber));
	   	  rs = st.executeQuery();
	   	  
	   	  if(rs.next()) {
	   		result = rs.getString("Name");
	   	  }
	
	   	  } catch (SQLException ex) {
	   			JOptionPane.showMessageDialog(null, "error error");
	   	  }
	 
	   return result;
   }
   
   
   public String getClassname(){
	   String listName = (String)allClass.getSelectedValue();
	   return listName;
   }
   
   public String getClassnameToMyClassList(){
	   String listName = (String)myClassList.getSelectedValue();
	   return listName;
   }
   
   public String findClassNo() {
	   PreparedStatement st;
       ResultSet rs;
       //Connection conn;
       String query = "SELECT Class_No FROM Class WHERE Classname = ? And Class_Pro = ?";
       try {
      // 	conn=DriverManager.getConnection("jdbc:mysql://admin.cys6irkoowji.ap-northeast-2.rds.amazonaws.com/LoginInfo","admin", "jayyou!3204");
           st = connection.prepareStatement(query);
           st.setString(1, getClassname());
           st.setString(2, getProname());
           rs = st.executeQuery();
           if(rs.next())
           {
               return rs.getString("Class_No");
           }
           
       } catch (SQLException ex) {
       	JOptionPane.showMessageDialog(null, "error error");
       }
       return null;
   }
   
   public String findClassNoToMyClassList() {
	   PreparedStatement st;
       ResultSet rs;
     //  Connection conn;
       String query = "SELECT Class_No FROM Class WHERE Classname = ? And Class_Pro = ?";
       try {
       //	connection=DriverManager.getConnection("jdbc:mysql://admin.cys6irkoowji.ap-northeast-2.rds.amazonaws.com/LoginInfo","admin", "jayyou!3204");
           st = connection.prepareStatement(query);
           st.setString(1, getClassnameToMyClassList());
           st.setString(2, getProname());
           rs = st.executeQuery();
           if(rs.next())
           {
               return rs.getString("Class_No");
           }
           
       } catch (SQLException ex) {
       	JOptionPane.showMessageDialog(null, "error error");
       }
       return null;
   }
   
   /**
    * Create the frame.
    */
  
   public ProChat(int id) {
	   IdNumber=id;
	   tabelName = "Pro"+Integer.toString(IdNumber)+"_Class";
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
    	  //******message send*******************************************************************
      	public void actionPerformed(ActionEvent arg0) {

      		    PreparedStatement st;
	            ResultSet rs = null;
	            String query;   
	            query="Insert into ";
	            String query2="communication_"+communication_number;
	            String query3="(Student_id, content, Professor_id) values (?,?,?)";
	            query=query+query2+query3;
	            String test;
	            System.out.println("query test:"+query);
	            System.out.println("pane content:"+editorPane.getText());
          		try {
					st = connection.prepareStatement(query);
					st.setInt(1,0);
	          		st.setString(2,(String)editorPane.getText());
	          		st.setInt(3,IdNumber);
	          	  
	          		st.executeUpdate();
	      	
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
          		
          		
          		Statement state2=null;
          		//******************************************************************
          		 try {
					state2=connection.createStatement();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
          		 
          		 
          		 String date=null;
          		Statement state=null;
          		try {
          			//conn = DriverManager.getConnection("jdbc:mysql://admin.cys6irkoowji.ap-northeast-2.rds.amazonaws.com/LoginInfo",
  	          		//	  "admin", "jayyou!3204");
         		     ResultSet rs2=null;
      	         state=connection.createStatement();
      	         String sql;
      	         sql="SELECT * FROM ";
      	         String sql3="`communication_"+findClassNoToMyClassList()+"`";
      	         sql=sql+sql3;
      	         rs2=state.executeQuery(sql);
      	        while(rs2.next())
   	        	{
      	        	date=rs2.getString("date");
   	        	}
          		}catch(SQLException e)
          		{
          			
          		}
          		
          		 
          		textPane.append(String.format("Professor:\n%s\n%s\n\n", editorPane.getText(),date)); 
          		editorPane.selectAll();
          		
          		
          		
      		    editorPane.setText("");
      	}
      	});
      
      JLabel prolbl = new JLabel("");
      Image img7=new ImageIcon(this.getClass().getResource("/professor.png")).getImage();
      
      JButton refreshbtn = new JButton("Refresh");
      refreshbtn.setFont(new Font("Bookman Old Style", Font.BOLD, 17));
      //************************************
      refreshbtn.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent arg0) {
      		textPane.setText("");
      		PreparedStatement st;
	          //  Connection conn = null;
	            ResultSet rs = null;
	            String query;     	            
	            try {
	            	//conn = DriverManager.getConnection("jdbc:mysql://admin.cys6irkoowji.ap-northeast-2.rds.amazonaws.com/LoginInfo",
	          			//  "admin", "jayyou!3204");
	          		query = "SELECT * FROM Stu_Info WHERE Class_No = ? And Pro_No = ?";
	          		st = connection.prepareStatement(query);      	      
	          		st.setString(1,findClassNoToMyClassList());
	          		st.setString(2,Integer.toString(IdNumber));
	          		
	          		
	          		DefaultListModel listModel = new DefaultListModel();
	          		rs = st.executeQuery();
	          		while(rs.next()) {
	          			String Stu_ID = ("Student_"+rs.getString("Stu_ID"));
	          			listModel.addElement(Stu_ID);
	          		}
	          	stuList.setModel(listModel);
	          textPane.setText("");
    		Statement state=null;
    		
    		
    		
    		
    		 ResultSet rs2=null;
 	         state=connection.createStatement();
 	         String sql;
 	         sql="SELECT * FROM ";
 	         String sql3="`communication_"+communication_number+"`";
 	         sql=sql+sql3;
 	         rs2=state.executeQuery(sql);
 	        while(rs2.next())
	        	{
     	         //System.out.println("heelo");
		        		 int msg_num=rs2.getInt("msg_num");
		        		 
		        		 String Student_id=rs2.getString("Student_id");
		        		 String content=rs2.getString("content");
		        		 String date=rs2.getString("date");
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
      refreshbtn.setBounds(741, 535, 125, 33);
      contentPane.add(refreshbtn);
      
      JButton btnReportStuent = new JButton("Report Student");
      btnReportStuent.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		Report_form rf=new Report_form(communication_number);
      		rf.setVisible(true);
      		
      	}
      });
      btnReportStuent.setFont(new Font("Bookman Old Style", Font.BOLD, 16));
      btnReportStuent.setBounds(741, 10, 187, 27);
      contentPane.add(btnReportStuent);
      
      requestlbl = new JLabel("No Chat Request");  ///////////////////////////////////////////////////////////////////////////
      requestlbl.setFont(new Font("Bookman Old Style", Font.BOLD, 17));
      requestlbl.setBounds(751, 492, 272, 18);
      contentPane.add(requestlbl);
      pt=new ProThread(requestlbl,IdNumber);
      pt.start();
      JButton chatStartbtn = new JButton("Start Chat");
      chatStartbtn.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent arg0) {
  			try {
				ReceiveThread2 rt2=new ReceiveThread2(getProname(),IdNumber);
				rt2.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
      	}
      });
      chatStartbtn.setFont(new Font("Bookman Old Style", Font.BOLD, 17));
      chatStartbtn.setBounds(906, 535, 132, 30);
      contentPane.add(chatStartbtn);
      
      JLabel snowlbl = new JLabel("");
     // Image img9=new ImageIcon(this.getClass().getResource("/snow.gif")).getImage();
     // snowlbl.setIcon(new ImageIcon(img9));
      snowlbl.setBounds(741, 22, 309, 546);
      contentPane.add(snowlbl);
      prolbl.setIcon(new ImageIcon(img7));
      prolbl.setBounds(741, 189, 297, 280);
      contentPane.add(prolbl);
      
      JLabel lglbl = new JLabel("");
      Image img8=new ImageIcon(this.getClass().getResource("/logo2.png")).getImage();
      lglbl.setIcon(new ImageIcon(img8));
      lglbl.setBounds(758, 43, 280, 75);
      contentPane.add(lglbl);
      sendButton.setBounds(620, 487, 105, 81);
      contentPane.add(sendButton);
      
      Image img4=new ImageIcon(this.getClass().getResource("/cloud.png")).getImage();
      sendButton.setIcon(new ImageIcon(img4));
      
      textPane = new TextArea();
      textPane.setBackground(new Color(224, 255, 255));
      textPane.setForeground(new Color(0, 0, 0));
      
      textPane.setBounds(321, 10, 404, 463);
      contentPane.add(textPane);
      JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

      
      scrollPane.setBounds(323, 487, 285, 81);
      contentPane.add(scrollPane);
      
      editorPane = new JEditorPane();
     
      scrollPane.setViewportView(editorPane);
      
      JButton search = new JButton("Search");
      
      search.setBounds(12, 10, 91, 23);
      contentPane.add(search);
      
      searchField = new JTextField();
      
      searchField.setBounds(122, 11, 187, 21);
      contentPane.add(searchField);
      searchField.setColumns(10);
      
      
      JLabel lblMyClass = new JLabel("MY CLASS");
      lblMyClass.setBounds(12, 210, 163, 15);
      contentPane.add(lblMyClass);
      
      JScrollPane allClassScroll = new JScrollPane();
      allClassScroll.setBounds(12, 43, 297, 139);
      contentPane.add(allClassScroll);
      
      allClass = new JList();
      allClass.setBackground(new Color(255, 204, 204));
      allClass.addMouseListener(new MouseAdapter() {
      	@Override
      	public void mouseClicked(MouseEvent e) {
      		if(e.getClickCount()==2) {
      			int cf = JOptionPane.showConfirmDialog(null, "Will you add this Lecture to My Class?", "Message", JOptionPane.OK_CANCEL_OPTION);
      			if(cf==0) {
      				PreparedStatement st;
      	           // Connection conn = null;
      	            ResultSet rs = null;
      	            String query;     	            
      	            try {
      	            	//conn = DriverManager.getConnection("jdbc:mysql://admin.cys6irkoowji.ap-northeast-2.rds.amazonaws.com/LoginInfo",
      	          			//  "admin", "jayyou!3204");
      	          		query = "INSERT INTO Class(Classname,Class_pro) VALUES (?,?)";
      	          		st = connection.prepareStatement(query);      	      
      	          		st.setString(1,getClassname());
      	          		st.setString(2,getProname());
      	          	  
      	          		st.executeUpdate();
  
      	          		query = "INSERT INTO "+tabelName+" VALUES (?,?)";
      	          		st = connection.prepareStatement(query); 
      	          		st.setInt(1,Integer.parseInt(findClassNo()));
      	          		st.setString(2,getClassname());
      	            	
      	          		st.executeUpdate();
      	          		
      	          		DefaultListModel listModel = new DefaultListModel();
      	          		query = "SELECT * FROM "+tabelName;
      	          		st = connection.prepareStatement(query);
      	          		rs = st.executeQuery();
  	    	  
      	          		while(rs.next()) {
      	          			String Classname = (rs.getString("Classname"));
      	          			listModel.addElement(Classname);
      	          		}
      	          		myClassList.setModel(listModel);
      	          		
      	          		
      	          		
      	          		////////////////////***************************************************?/////////////////
      	          	Statement s=connection.createStatement();
      	            String query2="Create table ";
    				String concat_query2="msg_num int(11) not null auto_increment, Student_id int(11), content char(80), "
    						+ "Professor_id int(11), date DATETIME default current_timestamp, primary key(msg_num))";
    				String p_k="communication_"+(findClassNo());
    				query2=query2+p_k+"("+concat_query2;
    				s.execute(query2);
      	          		
      	          		
      	          		
      	            } catch (SQLException ex) {
      	      	  			JOptionPane.showMessageDialog(null, "Can't add anymore lecture");
      	      	  		}
      	      	}
      		}
      	}
      });
      allClassScroll.setViewportView(allClass);
      
      JScrollPane scrollPane_1 = new JScrollPane();
      scrollPane_1.setBounds(12, 235, 297, 139);
      contentPane.add(scrollPane_1);

      
      myClassList = new JList();
      myClassList.setBackground(new Color(204, 255, 204));
      myClassList.setForeground(new Color(0, 0, 0));
      myClassList.addMouseListener(new MouseAdapter() {
      	@Override
      	public void mouseClicked(MouseEvent e) {
      		if(e.getClickCount()==2) {
      		  
      			PreparedStatement st;
  	            //Connection conn = null;
  	            ResultSet rs = null;
  	            String query;     	            
  	            try {
  	            //	conn = DriverManager.getConnection("jdbc:mysql://admin.cys6irkoowji.ap-northeast-2.rds.amazonaws.com/LoginInfo",
  	          			//  "admin", "jayyou!3204");
  	          		query = "SELECT * FROM Stu_Info WHERE Class_No = ? And Pro_No = ?";
  	          		st = connection.prepareStatement(query);      	      
  	          		st.setString(1,findClassNoToMyClassList());
  	          		st.setString(2,Integer.toString(IdNumber));
  	          		
  	          		
  	          		DefaultListModel listModel = new DefaultListModel();
  	          		rs = st.executeQuery();
  	          		while(rs.next()) {
  	          			String Stu_ID = ("Student_"+rs.getString("Stu_ID"));
  	          			listModel.addElement(Stu_ID);
  	          		}
  	          	stuList.setModel(listModel);
  	          textPane.setText("");
        		Statement state=null;
        		
        		
        		communication_number=findClassNoToMyClassList();
        		
        		 ResultSet rs2=null;
     	         state=connection.createStatement();
     	         String sql;
     	         sql="SELECT * FROM ";
     	         String sql3="`communication_"+findClassNoToMyClassList()+"`";
     	         sql=sql+sql3;
     	         rs2=state.executeQuery(sql);
     	        while(rs2.next())
  	        	{
  		        		 int msg_num=rs2.getInt("msg_num");
  		        		 
  		        		 String Student_id=rs2.getString("Student_id");
  		        		 String content=rs2.getString("content");
  		        		 String date=rs2.getString("date");
  		        		 if(!Student_id.equals("0"))
  		        		 {	
  		        			 String s=String.format("anonymous%s:\n%s \n%s\n\n",Student_id, content,date);
  		       		     	 textPane.append(s); 
  		       		     	 
  		        		 }
  		  
  		        		 else
  		        		 {
    		       		      textPane.append(String.format("Professor:\n%s\n%s\n\n", content,date)); 
  		        		 }
  	        	}
  	          	
  	            } catch (SQLException ex) {
  	      	  			JOptionPane.showMessageDialog(null, "error error 2");
  	      	  		}
      			
      		}
      		
      	}
      });
      scrollPane_1.setViewportView(myClassList);
      
      JLabel lblstudent = new JLabel("STUDENT LIST");
      lblstudent.setBounds(12, 398, 91, 15);
      contentPane.add(lblstudent);
      
      JScrollPane scrollPane_2 = new JScrollPane();
      scrollPane_2.setBounds(12, 423, 297, 145);
      contentPane.add(scrollPane_2);
      
      stuList = new JList();
      stuList.setBackground(new Color(255, 255, 204));
      scrollPane_2.setViewportView(stuList);
      
      PreparedStatement st;
      Connection conn = null;
      ResultSet rs = null;
      
      String query;
      DefaultListModel listModel = new DefaultListModel();
      DefaultListModel listMode2 = new DefaultListModel();
      
      try {
    //	  conn = DriverManager.getConnection("jdbc:mysql://admin.cys6irkoowji.ap-northeast-2.rds.amazonaws.com/LoginInfo",
    			//  "admin", "jayyou!3204");
    	  query = "SELECT * FROM Subject";
    	  st = connection.prepareStatement(query);
    	  rs = st.executeQuery();
		  
    	  while(rs.next()) {
    		  String Subname = (rs.getString("Subname"));
    		  listModel.addElement(Subname);
		  }
    	  allClass.setModel(listModel);
    	  
    	//  conn = DriverManager.getConnection("jdbc:mysql://admin.cys6irkoowji.ap-northeast-2.rds.amazonaws.com/LoginInfo",
    			//  "admin", "jayyou!3204");
    	  query = "SELECT * FROM "+tabelName;
    	  st = connection.prepareStatement(query);
    	  rs = st.executeQuery();
		  
    	  while(rs.next()) {
    		  String Classname = (rs.getString("Classname"));
    		  listMode2.addElement(Classname);
		  }
    	  myClassList.setModel(listMode2);
    	  
    	  
    	  
      		} catch (SQLException ex) {
	  			JOptionPane.showMessageDialog(null, "error error");
	  		}

   }
}