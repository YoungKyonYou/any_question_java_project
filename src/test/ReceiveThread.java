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
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ReceiveThread extends Thread{


	MessengerStu m;
	
	public ReceiveThread(String IdNumber,String name) throws IOException
	{
		m=new MessengerStu(IdNumber,name);
	}
	@Override
	public void run()
	{
		super.run();
		m.process();
	}

}
