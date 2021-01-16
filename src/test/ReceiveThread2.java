package test;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class ReceiveThread2 extends Thread{
	int idnum;
	MessengerPro m;
	public ReceiveThread2(String name,int idnum) throws IOException
	{
		this.idnum=idnum;
		m=new MessengerPro(name,this.idnum);
		System.out.println("after messengerPro");
	}
	@Override
	public void run() 
	{

		super.run();
		m.process();

	}


}
