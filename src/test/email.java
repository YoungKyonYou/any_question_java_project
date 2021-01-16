package test;

import java.awt.BorderLayout;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import java.sql.*;
import java.util.Properties;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
public class email extends JFrame {

	private JPanel contentPane;
	private String proEmail;
	private String myEmail;
	private String pwd;
	private JTextField titleField;
	private JTextArea msgArea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//email frame = new email();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public email(String myEmail,String proEmail,String pwd) {
		this.myEmail = myEmail;
		this.proEmail = proEmail;
		this.pwd = pwd;
		setBounds(100, 100, 548, 593);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 41, 508, 454);
		contentPane.add(scrollPane);
		
		msgArea = new JTextArea();
		scrollPane.setViewportView(msgArea);
		
		JButton btnSendEmail = new JButton("send email");
		btnSendEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(proEmail+" "+myEmail+" "+pwd);
				sendMail();
				dispose();
			}
		});
		btnSendEmail.setFont(new Font("Bookman Old Style", Font.BOLD, 17));
		btnSendEmail.setBounds(322, 501, 200, 50);
		contentPane.add(btnSendEmail);
		
		JLabel lblNewLabel = new JLabel(proEmail);
		lblNewLabel.setBounds(12, 505, 298, 46);
		contentPane.add(lblNewLabel);
		
		titleField = new JTextField();
		titleField.setBounds(156, 10, 364, 21);
		contentPane.add(titleField);
		titleField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("TITLE");
		lblNewLabel_1.setBounds(64, 10, 68, 21);
		contentPane.add(lblNewLabel_1);
		setVisible(true);
	}
	
	public void sendMail() {
		String host     = "smtp.naver.com";
		final String user   = myEmail;
		final String password  = pwd;

		String to     = proEmail;

  
  // Get the session object
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

  // Compose the message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

   // Subject
			message.setSubject(titleField.getText());
   
   // Text
			message.setText(msgArea.getText());

   // send the message
			Transport.send(message);
			System.out.println("message sent successfully...");
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	
}
