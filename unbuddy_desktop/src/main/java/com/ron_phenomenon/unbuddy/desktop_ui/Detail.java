package com.ron_phenomenon.unbuddy.desktop_ui;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;

public class Detail extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private static String course;
	private JComboBox comboBox = new JComboBox();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Detail frame = new Detail(course);
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
	public Detail(String course) {
		
		this.course = course;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 577, 552);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Add Program");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 11));
		
		JPanel panel = new JPanel();
		panel.setBounds(58, 144, 450, 288);
		contentPane.add(panel);
		contentPane.setLayout(null);
		
		comboBox.addItem("Enroll");
		comboBox.addItem("Disenroll");
		
		Object[][] data = {
			    {"Fall 2021", "Select"},
			    {"Winter 2022", "Select"},
			    {"Summer 2022", "Select"},
			    {"Fall 2022", "Select"},
			    {"Winter 2023", "Select"},
			    {"Summer 2023", "Select"},

			};
		
		String[] columnNames = {"Semesters", "Course Selection"};
		JTable table = new JTable(data, columnNames);
		table.setBounds(58, 144, 450, 288);
		contentPane.add(table);
		
		panel.add(new JScrollPane(table));
			
		table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
	    renderer.setToolTipText("Click for combo box");
	    table.getColumnModel().getColumn(1).setCellRenderer(renderer);
	    
	    
	    ActionListener cbActionListener = new ActionListener() {
	    	
		    public void actionPerformed(ActionEvent e)
		    {
		    	
		    	String selected  = (String) comboBox.getSelectedItem();
		    	
		    	switch (selected) {
		    	
		    	case "Enroll":
		    		Matrices.addRow((Object)course);
		    		break;
		    	}
		    			
		    }
		};
		
	    comboBox.addActionListener(cbActionListener);
	    	
	    
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 2, 2);
		contentPane.add(scrollPane);
		
		
		
		JLabel lblNewLabel = new JLabel(course);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 60));
		lblNewLabel.setBounds(145, 11, 304, 102);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("Return");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				
			}
		});
		btnNewButton_1.setBounds(100, 441, 139, 43);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Logout");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				Login.main(new String[0]);
				
			}
		});
		btnNewButton_1_1.setBounds(335, 443, 139, 43);
		contentPane.add(btnNewButton_1_1);
		
	}
	
	

}

