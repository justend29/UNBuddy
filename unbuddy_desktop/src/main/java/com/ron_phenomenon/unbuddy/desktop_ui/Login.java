package com.ron_phenomenon.unbuddy.desktop_ui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.lang.*;

public class Login {

	private JFrame frame;
	private JTextField txtEmailAddress;
	private JTextField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setForeground(Color.WHITE);
		frame.setBounds(100, 100, 577, 552);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/UNB_Engineering_Fredericton_RGB_K.png")).getImage();
		frame.getContentPane().setLayout(null);
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(0,386,553,102);

		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Login");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 60));
		lblNewLabel_1.setBounds(196, 11, 157, 84);
		frame.getContentPane().add(lblNewLabel_1);
		
		txtEmailAddress = new JTextField();
		txtEmailAddress.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		txtEmailAddress.setForeground(Color.GRAY);
		txtEmailAddress.setText("Email Address");
		txtEmailAddress.setBounds(156, 131, 220, 31);
		frame.getContentPane().add(txtEmailAddress);
		txtEmailAddress.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		txtPassword.setText("Password");
		txtPassword.setForeground(Color.GRAY);
		txtPassword.setColumns(10);
		txtPassword.setBounds(156, 211, 220, 31);
		frame.getContentPane().add(txtPassword);
		
		JLabel lblNewLabel_2 = new JLabel("Email Address");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(156, 114, 107, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Password");
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblNewLabel_2_1.setBounds(156, 192, 107, 14);
		frame.getContentPane().add(lblNewLabel_2_1);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				Matrices matrix = new Matrices();
				matrix.setVisible(true);
				
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		btnNewButton.setBounds(196, 285, 142, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		btnCreateAccount.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				//Splash splash = new Splash();
				//splash.setVisible(true);
				
				//try	{
					//Thread.sleep(20);
					
					
				//}	catch(Exception e)	{
					
				//}
				
				//check user email and password
				//try	{
					//String query = "select * from userInfo where email = ? and password = ?";
					//PreparedStatement pst = connection.prepareStatement(query);
					//pst.setString(1, txtEmailAddress.getText());
					//pst.setString(2, txtPassword.getText());
					
					//ResultSet rs = pst.executeQuery();
					
					//int count = 0;
					//while(rs.next())	{
					//	count = count + 1;
					//
					//}
					//if(count == 1)	{
					//	JOptionPane.showMessageDialog(null, "Email and Password are correct.");
						frame.dispose();
						CreateAccount acc = new CreateAccount();
						acc.setVisible(true);
					//}
					//else if(count > 1)	{
					// JOptionPane.showMessageDialog(null, "Duplicate Email and Password.");
					//}
					//else	{
					//	JOptionPane.showMessageDialog(null, "Email and Password are not correct, Please Try Again");
					
					//rs.close();
					//pst.close();
					
				//} catch (Exception e1)	{
					
					//JOptionPane.showMessageDialog(null, e1);
				//}
				
				//finally	{
					
				//}
					//try{	
				
				//}	catch(Exception e1)	{
					
					//JOptionPane.showMessageDialog(null, e1);
					
				//}
				
			}
		});
		
		btnCreateAccount.setBounds(196, 343, 142, 23);
		frame.getContentPane().add(btnCreateAccount);
		
	}
}
