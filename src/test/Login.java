package test;
import java.awt.Color;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Image;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.awt.SystemColor;
public class Login {

	private JFrame frame;


	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
					try {
						Login window = new Login();
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		});
	}
	public JFrame getFrame() {
		return frame;
	}
	Connection connection=null;
	private JTextField stu_username;
	private JPasswordField stu_password;
	private RegisterForm rf;
	private JButton btnRegistration;
	private JLabel main_bg;
	private JTextField pro_username;
	private JPasswordField pro_password;
	private JButton pro_Loginbutton;
	private ResultSet rs2=null;
	private Statement state=null;
	/**
	 * Create the application.
	 */
	public Login() {
			initialize();
			connection=mysqlConnection.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 896, 699);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
			
			btnRegistration = new JButton("Registration");
			btnRegistration.setForeground(Color.BLACK);
			btnRegistration.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					btnRegistration.setText("Register");
					btnRegistration.setFont(new Font("Cambria", Font.BOLD, 30));
					btnRegistration.setBackground(new Color(176, 224, 230));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					btnRegistration.setText("Regisration");
					btnRegistration.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 15));
				}
			});
			btnRegistration.setBackground(new Color(176, 224, 230));
			btnRegistration.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					frame.dispose();
					rf=new RegisterForm();
					rf.setVisible(true);
					rf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
				});
			
			pro_username = new JTextField();
			pro_username.setColumns(10);
			pro_username.setBounds(180, 448, 229, 34);
			frame.getContentPane().add(pro_username);
			
			pro_password = new JPasswordField();
			pro_password.setColumns(10);
			pro_password.setBounds(180, 492, 229, 34);
			frame.getContentPane().add(pro_password);
			
			//*****Professor Login***////
			pro_Loginbutton = new JButton("Login");
			pro_Loginbutton.addActionListener(new ActionListener() {// login 
				public void actionPerformed(ActionEvent e) 
				{
					try
					{
			        	String uname=pro_username.getText();
			        	String pass=String.valueOf(pro_password.getPassword());
						String query = "SELECT Username, password FROM LoginInfo_Pro WHERE Username=? and password=?"; ////
						PreparedStatement pst=mysqlConnection.dbConnector().prepareStatement(query);  //////
						pst.setString(1,uname);//for username  /////
						pst.setString(2,pass);//for password  /////
						ResultSet rs=pst.executeQuery();   ////
						int count=0;
						while(rs.next()) 
						{
							count=count+1;
						}
						if(count==1)
						{
							JOptionPane.showMessageDialog(null, "Username and password is correct");
							frame.dispose();
							
							String q="Select Id_number from `LoginInfo_Pro` where Username=\"";
							q=q+uname+"\"";
							state=connection.createStatement();
							rs2=state.executeQuery(q);

							int idnum=0;
							while(rs2.next())
							{
									idnum=rs2.getInt("Id_number");
									break;
							}
							
							ProChat c3=new ProChat(idnum);//professor
							c3.setVisible(true);
							
						}
						else if(count>1)
						{
							JOptionPane.showMessageDialog(null, "Duplicate Username and password");
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Username and password is not correct Try Again..");
						}
						rs.close();
						pst.close();
						
					}
					catch (SQLException sqex) 
					{
						JOptionPane.showMessageDialog(null, sqex);
					}
				}
				});
			pro_Loginbutton.setOpaque(false);
			pro_Loginbutton.setForeground(Color.BLACK);
			pro_Loginbutton.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 15));
			pro_Loginbutton.setBackground(new Color(0, 139, 139));
			pro_Loginbutton.setBounds(236, 593, 155, 40);
			frame.getContentPane().add(pro_Loginbutton);
			btnRegistration.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 15));
			btnRegistration.setBounds(14, 590, 195, 47);
			frame.getContentPane().add(btnRegistration);
		
		
		
		JLabel label_1 = new JLabel("Password");
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 15));
		label_1.setBounds(14, 492, 89, 32);
		frame.getContentPane().add(label_1);
		
		JLabel label = new JLabel("UserName");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 15));
		label.setBounds(14, 448, 105, 32);
		frame.getContentPane().add(label);
		
		JLabel Login_icon = new JLabel("");
		Login_icon.setBounds(727, 309, 36, 32);
		frame.getContentPane().add(Login_icon);
		
		JLabel lblNewLabel = new JLabel("UserName");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel.setBounds(464, 448, 105, 32);
		frame.getContentPane().add(lblNewLabel);
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_1.setBounds(464, 492, 89, 32);
		frame.getContentPane().add(lblNewLabel_1);
		
		stu_username = new JTextField();
		stu_username.setBounds(643, 448, 228, 34);
		frame.getContentPane().add(stu_username);
		stu_username.setColumns(10);
		
		JButton btnLogin = new JButton("Login");// ******login button*****
		btnLogin.setForeground(Color.BLACK);
		btnLogin.setBackground(new Color(0, 139, 139));
		btnLogin.setOpaque(false);
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {//login
				btnLogin.setText("Start!");
				btnLogin.setFont(new Font("Cambria", Font.BOLD, 30));
				btnLogin.setBackground(new Color(0, 139, 139));
			}
			@Override
			public void mouseExited(MouseEvent e) {//login
				btnLogin.setText("Login");
				btnLogin.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 15));
			}
		});
		btnLogin.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 15));
		Image img1=new ImageIcon(this.getClass().getResource("/login_icon.png")).getImage();
		btnLogin.setIcon(new ImageIcon(img1));
		btnLogin.addActionListener(new ActionListener(){//login
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
		        	String uname=stu_username.getText();
		        	String pass=String.valueOf(stu_password.getPassword());
					String query = "SELECT Username, password FROM LoginInfo WHERE Username=? and password=?"; //
					PreparedStatement pst=mysqlConnection.dbConnector().prepareStatement(query);  //
					pst.setString(1,uname);//for username  
					pst.setString(2,pass);//for password
					ResultSet rs=pst.executeQuery();
					int count=0;
					while(rs.next()) 
					{
						count=count+1;
					}
					if(count==1)
					{
						JOptionPane.showMessageDialog(null, "Username and password is correct");
						frame.dispose();
						String q="Select Id_number from `LoginInfo` where Username=\"";
						q=q+uname+"\"";
						state=connection.createStatement();
						rs2=state.executeQuery(q);

						int idnum=0;
						while(rs2.next())
						{
								idnum=rs2.getInt("Id_number");
								break;
						}
						StuChat c=new StuChat(idnum);//Student
						c.setVisible(true);
					}
					else if(count>1)
					{
						JOptionPane.showMessageDialog(null, "Duplicate Username and password");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Username and password is not correct Try Again..");
					}
					rs.close();
					pst.close();
					
				}
				catch (SQLException sqex) 
				{
					JOptionPane.showMessageDialog(null, sqex);
				} 
			}
			});
		btnLogin.setBounds(672, 593, 155, 40);
		frame.getContentPane().add(btnLogin);
		
		stu_password = new JPasswordField();
		stu_password.setEchoChar('*');
		stu_password.setBounds(643, 496, 228, 34);
		frame.getContentPane().add(stu_password);
		Image img4=new ImageIcon(this.getClass().getResource("/login_icon.png")).getImage();
		btnRegistration.setIcon(new ImageIcon(img4));
		main_bg = new JLabel("");
		main_bg.setBounds(0, 0, 886, 663);
		frame.getContentPane().add(main_bg);
		Image img5=new ImageIcon(this.getClass().getResource("/main.png")).getImage();
		main_bg.setIcon(new ImageIcon(img5));
		}
}


