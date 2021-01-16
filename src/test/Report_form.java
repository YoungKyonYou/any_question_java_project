package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Report_form extends JFrame {

	private JPanel contentPane;
	public JTextField textField;
	public String num;
	  private Connection connection=mysqlConnection.dbConnector();
	private String communication_number;
	private JButton btnCancel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Report_form frame = new Report_form("15");
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
	public Report_form(String cn) {
		communication_number=cn;
		setBounds(100, 100, 450, 187);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Type Student ID Who You want to Report");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Bookman Old Style", Font.BOLD, 17));
		lblNewLabel.setBounds(26, 12, 379, 35);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(149, 59, 116, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				JOptionPane.showMessageDialog(null, "Report has been Sent!");
				 PreparedStatement st;
		            String query;   
		            query="Insert into report (student_ID, communication_number) values (?,?)";
		            System.out.println("text:"+textField.getText()+"communication_number:"+communication_number);
	       		try {
						st = connection.prepareStatement(query);
						st.setString(1,textField.getText());
		          		st.setString(2,communication_number);
		          		st.executeUpdate();
		      	
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}  
			}
		});
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("Bookman Old Style", Font.BOLD, 17));
		btnNewButton.setBounds(77, 93, 116, 27);
		contentPane.add(btnNewButton);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setForeground(Color.BLACK);
		btnCancel.setFont(new Font("Bookman Old Style", Font.BOLD, 17));
		btnCancel.setBounds(239, 93, 116, 27);
		contentPane.add(btnCancel);
	}
}
