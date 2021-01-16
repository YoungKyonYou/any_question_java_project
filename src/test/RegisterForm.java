package test;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.sql.*;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class RegisterForm extends JFrame {

	private JPanel first_name_var;
	private JTextField txtName;
	private JTextField txtEmail;
	private JTextField txtUser;
	private Statement state=null;
	private ResultSet rs2=null;
	private static int primary_key=3;//어디서 시작할지 기본키 숫자 지정해 주기1!!!!!
	private JTextField txtStudentID;
	private JTextField txtStuDep;
	private JPasswordField txtPw;
	private JPasswordField txtConfirmpw;
	//static private RegisterForm frame;
	/**
	 * Launch the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				RegisterForm frame;
				try {
					frame = new RegisterForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
public boolean checkUsername(String username){
        
        PreparedStatement st;
        ResultSet rs;
        boolean username_exist = false;
        Connection conn;
        String query = "SELECT * FROM `LoginInfo` WHERE `Username` = ?";
        
        try {
        	conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/logininfo?useUnicode=true&passwordCharacterEncoding=utf-8","jspbook", "jayyou!3204");
            st = conn.prepareStatement(query);
            st.setString(1, username);
            rs = st.executeQuery();
        
            if(rs.next())
            {
                username_exist = true;
                JOptionPane.showMessageDialog(null, "This Username is Already Taken, Choose Another One", "Username Failed", 2);
            }
            
        } catch (SQLException ex) {
        	JOptionPane.showMessageDialog(null, "error error error");
        }
        
        return username_exist;
    }
	/**
	 * Create the frame.
	 */
	public RegisterForm() {
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 398, 768);
		first_name_var = new JPanel();
		first_name_var.setBackground(Color.WHITE);
		first_name_var.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(first_name_var);
		first_name_var.setLayout(null);
		
		JLabel label = new JLabel("(Student Only)");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Bookman Old Style", Font.BOLD, 15));
		label.setBounds(220, 476, 153, 27);
		first_name_var.add(label);
		
		txtConfirmpw = new JPasswordField();
		txtConfirmpw.setBounds(72, 423, 283, 39);
		first_name_var.add(txtConfirmpw);
		
		txtPw = new JPasswordField();
		txtPw.setBounds(72, 334, 283, 39);
		first_name_var.add(txtPw);
		
		JLabel lblstudentOnly = new JLabel("(Student Only)");
		lblstudentOnly.setForeground(Color.WHITE);
		lblstudentOnly.setFont(new Font("Bookman Old Style", Font.BOLD, 15));
		lblstudentOnly.setBounds(220, 568, 153, 27);
		first_name_var.add(lblstudentOnly);
		
		JLabel lblStudentDepartment = new JLabel("Student Department");
		lblStudentDepartment.setForeground(Color.WHITE);
		lblStudentDepartment.setFont(new Font("Bookman Old Style", Font.BOLD, 15));
		lblStudentDepartment.setBounds(45, 568, 183, 27);
		first_name_var.add(lblStudentDepartment);
		
		JLabel lblUn = new JLabel("Username");
		lblUn.setForeground(Color.WHITE);
		lblUn.setFont(new Font("Bookman Old Style", Font.BOLD, 15));
		lblUn.setBounds(45, 210, 82, 27);
		first_name_var.add(lblUn);
		
		JLabel lblStudentId = new JLabel("Student ID");
		lblStudentId.setForeground(Color.WHITE);
		lblStudentId.setFont(new Font("Bookman Old Style", Font.BOLD, 15));
		lblStudentId.setBounds(45, 476, 153, 27);
		first_name_var.add(lblStudentId);
		
		JLabel lblYM = new JLabel("Your Email");
		lblYM.setForeground(Color.WHITE);
		lblYM.setFont(new Font("Bookman Old Style", Font.BOLD, 15));
		lblYM.setBounds(45, 122, 104, 27);
		first_name_var.add(lblYM);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setForeground(Color.WHITE);
		lblConfirmPassword.setFont(new Font("Bookman Old Style", Font.BOLD, 15));
		lblConfirmPassword.setBounds(45, 387, 165, 27);
		first_name_var.add(lblConfirmPassword);
		
		JLabel lblPw = new JLabel("Password");
		lblPw.setForeground(Color.WHITE);
		lblPw.setFont(new Font("Bookman Old Style", Font.BOLD, 15));
		lblPw.setBounds(45, 297, 82, 27);
		first_name_var.add(lblPw);
		
		JLabel lblYN = new JLabel("Your Name");
		lblYN.setForeground(Color.WHITE);
		lblYN.setFont(new Font("Bookman Old Style", Font.BOLD, 15));
		lblYN.setBounds(45, 35, 104, 27);
		first_name_var.add(lblYN);
		
		txtStudentID = new JTextField();
		txtStudentID.setColumns(10);
		txtStudentID.setBounds(73, 509, 283, 39);
		first_name_var.add(txtStudentID);
		
		JLabel lblProfessor = new JLabel("Professor");
		lblProfessor.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 17));
		lblProfessor.setForeground(Color.WHITE);
		lblProfessor.setBounds(28, 657, 99, 18);
		first_name_var.add(lblProfessor);
		
		JButton pro_register = new JButton("Register");
		pro_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//frame.dispose();
				dispose();
				Connection conn=null;
				if(!checkUsername(txtName.getText()))
				{
					try {

						
						
						String query="INSERT INTO `LoginInfo_Pro`(`Username`, `password`,`Email`,`Name`) VALUES (?,?,?,?)";
						conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/logininfo?useUnicode=true&passwordCharacterEncoding=utf-8","jspbook", "jayyou!3204");
						
						  try {
							  PreparedStatement pst=conn.prepareStatement(query);
			                   // PreparedStatement pst2=conn.prepareStatement(query2);
							 // pst=conn.prepareStatement(query2);
								pst.setString(1, txtName.getText());
								pst.setString(2, txtPw.getText());
								pst.setString(3, txtEmail.getText());
								pst.setString(4, txtUser.getText());
								
								Statement s=conn.createStatement();
								 String q="Select Id_number from `LoginInfo_Pro` where Username=\"";
									q=q+txtName.getText()+"\"";
									System.out.println("username:"+txtName.getText());
									System.out.println("q:"+q);
								  state=conn.createStatement();
			                     // save the image as blob in the database
								 if(pst.executeUpdate() != 0){
									 
									 rs2=state.executeQuery(q);
										int idnum=0;
										while(rs2.next())
										{
												idnum=rs2.getInt("Id_number");
												break;
										}
								
										String query2="Create table `";
										String concat_query2="Class_No int(255) not null, Classname char(20) unique, primary key(Class_No))";
										String p_k="Pro"+Integer.toString(idnum);
										query2=query2+p_k+"_Class`("+concat_query2;
										s.execute(query2);
									 
									 
									 
								     JOptionPane.showMessageDialog(null, "Your Account Has Been Created");
								 }else{
								     JOptionPane.showMessageDialog(null, "Error: Check Your Information");
								 }
			                     
			                 } catch (SQLException ex) {
		                    	 JOptionPane.showMessageDialog(null, ex);
			                 }
						Login n=new Login();
						n.getFrame().setVisible(true);
					}
					catch(SQLException sqex) {
						JOptionPane.showMessageDialog(null, sqex);
					}
				}
			}
		});
		pro_register.setFont(new Font("Cambria Math", Font.BOLD, 18));
		pro_register.setBounds(14, 687, 135, 27);
		first_name_var.add(pro_register);
		Image img4=new ImageIcon(this.getClass().getResource("/regis_form.png")).getImage();
		
		txtName = new JTextField();
		txtName.setDisabledTextColor(Color.BLACK);
		txtName.setForeground(Color.BLACK);
		txtName.setBounds(73, 246, 283, 39);
		first_name_var.add(txtName);
		txtName.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(73, 157, 283, 39);
		first_name_var.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtUser = new JTextField();
		txtUser.setBounds(73, 69, 283, 39);
		first_name_var.add(txtUser);
		txtUser.setColumns(10);
		
		
		JButton register_btn_var = new JButton("Register");
		register_btn_var.setFont(new Font("Cambria Math", Font.BOLD, 18));
		register_btn_var.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//frame.dispose();
				dispose();
				Connection conn=null;
				if(!checkUsername(txtName.getText()))
				{
					try {
						String query="INSERT INTO `LoginInfo`(`Username`, `password`,`Email`,`Name`,`Student_ID`,`Department`) VALUES (?,?,?,?,?,?)";
						conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/logininfo?useUnicode=true&passwordCharacterEncoding=utf-8","jspbook", "jayyou!3204");
						  try {
							  PreparedStatement pst=conn.prepareStatement(query);
								pst.setString(1, txtName.getText());
								pst.setString(2, txtPw.getText());
								pst.setString(3, txtEmail.getText());
								pst.setString(4, txtUser.getText());
								pst.setString(5, txtStudentID.getText());
								pst.setString(6, txtStuDep.getText());
								Statement s=conn.createStatement();
								 String q="Select Id_number from `LoginInfo` where Username=\"";
									q=q+txtName.getText()+"\"";
									System.out.println("username:"+txtName.getText());
									System.out.println("q:"+q);
								  state=conn.createStatement();
					
								
								
								primary_key++;//만든 다음에 기본키 아이디 증가
			                     // save the image as blob in the database
								 if(pst.executeUpdate() != 0){
									 
									 rs2=state.executeQuery(q);
										int idnum=0;
										while(rs2.next())
										{
												idnum=rs2.getInt("Id_number");
												break;
										}
								
										String query2="Create table `";
										String concat_query2="Class_No int(255) not null, Classname char(20) unique, Class_pro char(20), primary key(Class_No))";
										String p_k=Integer.toString(idnum);
										query2=query2+p_k+"_Class`("+concat_query2;
										s.execute(query2);
									 
									 
									 
								     JOptionPane.showMessageDialog(null, "Your Account Has Been Created");
								 }else{
								     JOptionPane.showMessageDialog(null, "Error: Check Your Information");
								 }
			                     
			                 
						  }  catch (SQLException ex) {
				
		                    	 JOptionPane.showMessageDialog(null, ex);
			                 }
						Login n=new Login();
						n.getFrame().setVisible(true);
					}
					catch(SQLException sqex) {
						JOptionPane.showMessageDialog(null, sqex);
					}
				}

			}
		});
		register_btn_var.setBounds(220, 687, 135, 27);
		first_name_var.add(register_btn_var);
		
		JLabel Student_label = new JLabel("Student");
		Student_label.setForeground(Color.WHITE);
		Student_label.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 17));
		Student_label.setBounds(236, 657, 99, 18);
		first_name_var.add(Student_label);
		
		txtStuDep = new JTextField();
		txtStuDep.setColumns(10);
		txtStuDep.setBounds(72, 604, 283, 39);
		first_name_var.add(txtStuDep);
		
		JLabel regis_bg_var = new JLabel("");
		regis_bg_var.setIcon(new ImageIcon(img4));//
		regis_bg_var.setBounds(0, 0, 389, 739);
		first_name_var.add(regis_bg_var);

	}
}
