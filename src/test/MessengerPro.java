package test;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.sql.*;
public class MessengerPro extends JFrame implements ActionListener{

	private JPanel contentPane;
	protected JTextField textField;
	protected JTextArea textArea;
	DatagramSocket socket;
	private int idnum;
	ServerSocket server;;
	DatagramPacket packet;
	InetAddress address = null;
	final int myPort = 6001; // �닔�떊�슜 �룷�듃 踰덊샇
	final int otherPort = 5000; // �넚�떊�슜 �룷�듃 踰덊샇
	private JLabel lblImA;
	private static String name;
	private JButton btnNewButton;
	private Connection conn=mysqlConnection.dbConnector();
	private Statement state=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MessengerPro frame = new MessengerPro("Leena",14);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws UnknownHostException 
	 * @throws SocketException 
	 */
	public MessengerPro(String name,int idnum)  {
		super("Professor");
		this.idnum=idnum;
		this.name= name;
		try
		{
			address = InetAddress.getByName("localhost");
			socket = new DatagramSocket(myPort);
		}
		catch(IOException e)
		{
			socket.close();
		}
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 390, 484);
	     contentPane = new JPanel();
	     contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	     setContentPane(contentPane);
	     contentPane.setLayout(null);
	     
		
		textField = new JTextField();
		textField.setBounds(12, 374, 352, 24);
		textField.setColumns(20);
		textField.addActionListener(this);
		
		textArea = new JTextArea();
		textArea.setBackground(Color.BLACK);
		textArea.setForeground(Color.LIGHT_GRAY);
		textArea.setEditable(false);
		textArea.setBounds(12, 34, 352, 330);
		
		btnNewButton = new JButton("Close Chat Room");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String query="Update LoginInfo_Pro set Chat_Flag=0 where Id_number=?";
				ResultSet rs=null;
				try {
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setInt(1,idnum);
					pst.executeUpdate();
				} catch (SQLException e) {
					socket.close();
					e.printStackTrace();
				}
				dispose();
				try {
					server=new ServerSocket(6001);
					server.close();
					socket.close();
				} catch (IOException e) {
					socket.close();
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Bookman Old Style", Font.BOLD, 15));
		btnNewButton.setBounds(78, 408, 230, 23);
		contentPane.add(btnNewButton);
		contentPane.add(textField);
		contentPane.add(textArea);
		
		lblImA = new JLabel("i'm a Professor");
		lblImA.setForeground(new Color(0, 0, 0));
		lblImA.setBounds(12, 10, 93, 15);
		contentPane.add(lblImA);
		
		setVisible(true);
	}
	public void process() {
		while (true)
		{
			try {
				byte[] buf = new byte[256];
				packet = new DatagramPacket(buf, buf.length);
				socket.receive(packet); 
				textArea.append(new String(buf) + "\n"); //받음
			} catch (IOException ioException) {
				socket.close();
				break;
			}
		}
	}
	public void actionPerformed(ActionEvent evt) {
		String s = name+": "+textField.getText();
		byte[] buffer = null;
		try {
			buffer = s.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			socket.close();
			e1.printStackTrace();
		}
		DatagramPacket packet;

		packet = new DatagramPacket(buffer, buffer.length, address,
				otherPort);
		try {
			socket.send(packet); 
		} catch (IOException e) {
			socket.close();
			e.printStackTrace();
		}
		textArea.append(s + "\n"); // 보냄
		textField.selectAll();
		textArea.setCaretPosition(textArea.getDocument().getLength());
		textField.setText("");
	}

}
