package test;

import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class MessengerStu extends JFrame implements ActionListener {
	protected JPanel contentPane;
	private String name;
	protected JTextField textField;
	protected JTextArea textArea;
	protected DatagramSocket socket;
	protected DatagramPacket packet;
	private ServerSocket server;
	protected InetAddress address = null;
	protected static String IdNumber;
	private Connection conn=mysqlConnection.dbConnector();
	private Statement state=null;
		// �뙣�궥�쓣 �깮�꽦�븳�떎.
		//packet = new DatagramPacket(buffer, buffer.length, address,
	final int myPort = 5000; // �닔�떊�슜 �룷�듃 踰덊샇
	final int otherPort = 6001; // �넚�떊�슜 �룷�듃 踰덊샇
	private JButton button;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MessengerStu frame = new MessengerStu("Park","NoYoungJoo");
					//frame.process();
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
	public MessengerStu(String IdNumber,String name)  {
	
		super("Student");
		this.name=name;
		this.IdNumber =IdNumber;
		try
		{
				address = InetAddress.getByName("localhost");
				socket = new DatagramSocket(myPort);

		}
		catch(IOException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Professor is already Chatting with another Student");
			socket.close();
		}
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 390, 477);
	     contentPane = new JPanel();
	     contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	     setContentPane(contentPane);
	     contentPane.setLayout(null);
	     
		
		textField = new JTextField();
		textField.setBounds(12, 374, 352, 24);
		textField.setColumns(20);
		textField.addActionListener(this);
		
		textArea = new JTextArea();
		textArea.setForeground(Color.LIGHT_GRAY);
		textArea.setBackground(Color.BLACK);
		textArea.setEditable(false);
		textArea.setBounds(12, 34, 352, 330);
		contentPane.add(textField);
		
		button = new JButton("Close Chat Room");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String query="Update LoginInfo_Pro set Chat_Flag=0 where Name=\"";
				query=query+name+"\"";
				ResultSet rs=null;
				try {
					PreparedStatement pst=conn.prepareStatement(query);
					pst.executeUpdate();
				} catch (SQLException e1) {
					socket.close();
					e1.printStackTrace();
				}
				try {
					server=new ServerSocket(myPort);
					server.close();
					socket.close();
				} catch (IOException e1) {
					socket.close();
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				dispose();
			}
		});
		button.setFont(new Font("Bookman Old Style", Font.BOLD, 15));
		button.setBounds(74, 408, 230, 23);
		contentPane.add(button);
		contentPane.add(textArea);
		
		JLabel lblNewLabel = new JLabel("And you're a student");
		lblNewLabel.setBounds(12, 10, 118, 15);
		contentPane.add(lblNewLabel);
		setVisible(true);
	}
	public void process() {
		while (true) {
			try {
				byte[] buf = new byte[256];
				packet = new DatagramPacket(buf, buf.length);
				socket.receive(packet); 
				textArea.append(new String(buf) + "\n");
			} catch (IOException ioException) {
				socket.close();
				break;
				//ioException.printStackTrace();
			}
		}
	}
	public void actionPerformed(ActionEvent evt) {
		String s = "Student_"+IdNumber+": "+textField.getText();
		byte[] buffer = null;
		try {
			buffer = s.getBytes("MS949");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DatagramPacket packet;
			
		// �뙣�궥�쓣 �깮�꽦�븳�떎.
		packet = new DatagramPacket(buffer, buffer.length, address,
				otherPort);
		try {
			socket.send(packet); // �뙣�궥�쓣 蹂대궦�떎.
		} catch (IOException e) {
			e.printStackTrace();
		}
		textArea.append(s + "\n");
		textField.selectAll();
		textArea.setCaretPosition(textArea.getDocument().getLength());
		textField.setText("");
	}
}
