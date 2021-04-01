package com.ron_phenomenon.unbuddy.desktop_ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateAccount extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtEmailAddress;
	private JTextField txtPassword;
	private JTextField txtPasswordConfirmation;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateAccount frame = new CreateAccount();
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
	public CreateAccount() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 577, 552);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Create Account");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 60));
		lblNewLabel.setBounds(68, 11, 415, 50);
		contentPane.add(lblNewLabel);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Professor");
		rdbtnNewRadioButton.setBackground(Color.WHITE);
		rdbtnNewRadioButton.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		rdbtnNewRadioButton.setBounds(71, 115, 111, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnStudent = new JRadioButton("Student");
		rdbtnStudent.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		rdbtnStudent.setBackground(Color.WHITE);
		rdbtnStudent.setBounds(71, 141, 111, 23);
		contentPane.add(rdbtnStudent);
		
		ButtonGroup or = new ButtonGroup();
		or.add(rdbtnStudent);
		or.add(rdbtnNewRadioButton);
		
		JLabel lblNewLabel_1 = new JLabel("Account Type");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_1.setBounds(71, 94, 94, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Name");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_2.setBounds(71, 186, 49, 14);
		contentPane.add(lblNewLabel_2);
		
		txtName = new JTextField();
		txtName.setForeground(Color.GRAY);
		txtName.setText("Name");
		txtName.setBounds(71, 207, 135, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Email Address");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_3.setBounds(311, 94, 111, 14);
		contentPane.add(lblNewLabel_3);
		
		txtEmailAddress = new JTextField();
		txtEmailAddress.setText("Email Address");
		txtEmailAddress.setForeground(Color.GRAY);
		txtEmailAddress.setColumns(10);
		txtEmailAddress.setBounds(311, 116, 135, 20);
		contentPane.add(txtEmailAddress);
		
		JLabel lblNewLabel_3_1 = new JLabel("Password");
		lblNewLabel_3_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_3_1.setBounds(311, 145, 111, 14);
		contentPane.add(lblNewLabel_3_1);
		
		txtPassword = new JTextField();
		txtPassword.setText("Password");
		txtPassword.setForeground(Color.GRAY);
		txtPassword.setColumns(10);
		txtPassword.setBounds(311, 168, 135, 20);
		contentPane.add(txtPassword);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Password Confirmation");
		lblNewLabel_3_1_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_3_1_1.setBounds(311, 198, 135, 14);
		contentPane.add(lblNewLabel_3_1_1);
		
		txtPasswordConfirmation = new JTextField();
		txtPasswordConfirmation.setText("Password");
		txtPasswordConfirmation.setForeground(Color.GRAY);
		txtPasswordConfirmation.setColumns(10);
		txtPasswordConfirmation.setBounds(311, 223, 135, 20);
		contentPane.add(txtPasswordConfirmation);
		
		JButton btnNewButton = new JButton("Create Account");
		btnNewButton.setBounds(172, 290, 147, 32);
		contentPane.add(btnNewButton);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				Login.main(new String[0]);
				
			}
		});
		btnReturn.setBounds(172, 347, 147, 32);
		contentPane.add(btnReturn);
	}
}
