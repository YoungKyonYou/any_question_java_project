package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EmailLogin extends JFrame {

	private JPanel contentPane;
	private JPasswordField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//EmailLogin frame = new EmailLogin();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public EmailLogin(String myEmail,String proEmail) {
		setBounds(100, 100, 215, 117);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblYourEmail = new JLabel("your email password");
		lblYourEmail.setBounds(12, 10, 145, 15);
		contentPane.add(lblYourEmail);
		
		textField = new JPasswordField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new email(myEmail,proEmail,String.valueOf(textField.getPassword()));
				dispose();
			}
		});
		textField.setBounds(12, 34, 175, 21);
		textField.setEchoChar('*');
		contentPane.add(textField);
		textField.setColumns(10);
		setVisible(true);
	}
}
