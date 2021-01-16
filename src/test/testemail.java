package test;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class testemail {
	public testemail() {
		
	}

	
	public static void main(String[] args) {

		String host     = "smtp.naver.com";
		final String user   = "qkralsrnr206";
		final String password  = "lp0117";

		String to     = "qkralsrnr206@naver.com";

  
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
			message.setSubject("[Subject] Java Mail Test");
   
   // Text
			message.setText("Simple mail test..");

   // send the message
			Transport.send(message);
			System.out.println("message sent successfully...");
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}