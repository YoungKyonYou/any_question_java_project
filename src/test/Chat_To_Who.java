package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;
import java.awt.event.ActionEvent;

public class Chat_To_Who extends JFrame {
	private int IdNumber;
	private Statement state=null;
	String name;
	private int idnum=0;
	int Pro_ID_Number;
	private JPanel contentPane;
	private JTextField textField;
	private Connection conn=mysqlConnection.dbConnector();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Chat_To_Who frame = new Chat_To_Who(2);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Chat_To_Who(int idNum) {
		this.IdNumber=idNum;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name of Professor");
		lblNewLabel.setBounds(12, 128, 149, 15);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(150, 110, 195, 51);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Chat Start");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ResultSet rs=null;
				name=textField.getText();
				String query="Select Id_number from LoginInfo_Pro where Name=\"";
				query=query+name+"\"";
				System.out.println("query:"+query);
				try {
					state=conn.createStatement();
					rs=state.executeQuery(query);

					while(rs.next())
					{
							idnum=rs.getInt("Id_number");
							break;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("id:"+idnum);
				
		
				StuThread st=new StuThread(idnum);
				st.start();
				
				
				
				
				try {
					ReceiveThread r=new ReceiveThread(Integer.toString(IdNumber),name);
					r.start();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Professor is talking with another Student");
					e1.printStackTrace();
				}
				dispose();
			}
		});
		btnNewButton.setBounds(176, 183, 149, 33);
		contentPane.add(btnNewButton);
	}
}
