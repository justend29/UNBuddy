package com.ron_phenomenon.unbuddy.desktop_ui;


import java.awt.EventQueue;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import java.awt.Color;

public class CourseInfo extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CourseInfo frame = new CourseInfo();
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
	public CourseInfo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 577, 552);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		

		
		
	
		
		JComboBox<String> comboBox = new JComboBox<>();
	    comboBox.addItem("This");
	    comboBox.addItem("is");
	    comboBox.addItem("a");
	    comboBox.addItem("Sample program");
	    
		Object[][] data = {
			    {"CS3383", comboBox},
			    {"CS1303", comboBox},
			    {"CS1073", comboBox},
			    {"CS1083", comboBox},
			    {"CS2383", comboBox},
			    {"CS2383", comboBox},
			    {"CS2333", comboBox},
			    {"CS3413", comboBox},
			    {"CS1103", comboBox},
			    {"CS4419", comboBox},
			    {"ECE2214", comboBox},
			    {"ECE2215", comboBox},
			    {"ECE1813", comboBox},
			    {"ECE3221", comboBox},
			    {"ECE3232", comboBox},
			    {"ECE4242", comboBox},
			    {"SWE4403", comboBox},
			    {"SWE4203", comboBox},
			};
		
		String[] columnNames = {"First Name", "Information"};
		JTable table = new JTable(data, columnNames);
		table.setBounds(1, 26, 450, 288);
		contentPane.add(table);
		

		revalidate();
		repaint();

		
		JLabel lblNewLabel = new JLabel("Term Information");
		lblNewLabel.setBounds(41, 11, 543, 91);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 60));
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(51, 113, 463, 351);
		contentPane.add(panel);
		contentPane.setLayout(null);
		
		panel.add(new JScrollPane(table));
		
	}
}
