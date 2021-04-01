package com.ron_phenomenon.unbuddy.desktop_ui;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;

public class Detail extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Detail frame = new Detail();
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
	public Detail() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 577, 552);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Add Program");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 11));
		
		String[] columnNames = {"Available Term", "Enrolling Options"};
		Object[][] data = {{"Fall 2020", btnNewButton},
						   {"Winter 2021", "Enroll"},
						   {"Summer 2021", "Enroll"}
				
		};
		
		
		
		JLabel lblNewLabel = new JLabel("(Selected Course)");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 60));
		lblNewLabel.setBounds(56, 11, 468, 102);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(56, 140, 440, 364);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTable table = new JTable(data, columnNames);
		table.setBounds(0, 5, 440, 359);
		panel.add(table);
		
	}

}
