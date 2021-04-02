package com.ron_phenomenon.unbuddy.desktop_ui;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.AbstractAction;
import javax.swing.AbstractCellEditor;
import javax.swing.Action;
import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.Component;

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
		
		
	    
		Object[][] data = {
			    {"CS3383", "Details"},
			    {"CS1303", "Details"},
			    {"CS1073", "Details"},
			    {"CS1083", "Details"},
			    {"CS2383", "Details"},
			    {"CS2383", "Details"},
			    {"CS2333", "Details"},
			    {"CS3413", "Details"},
			    {"CS1103", "Details"},
			    {"CS4419", "Details"},
			    {"ECE2214", "Details"},
			    {"ECE2215", "Details"},
			    {"ECE1813", "Details"},
			    {"ECE3221", "Details"},
			    {"ECE3232", "Details"},
			    {"ECE4242", "Details"},
			    {"SWE4403", "Details"},
			    {"SWE4203", "Details"},
			};
		
		String[] columnNames = {"Courses", "More Information"};
		JTable table = new JTable(data, columnNames);
		table.setBounds(1, 26, 450, 288);
		contentPane.add(table);
		
		table.getColumnModel().getColumn(1).setCellRenderer(new ButtonRenderer());
		table.getColumnModel().getColumn(1).setCellEditor(new ButtonEditor(new JTextField()));
		
		Action move = new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        JTable table = (JTable)e.getSource();
		        int modelRow = Integer.valueOf( e.getActionCommand() );
		        
		        String o = (String) table.getValueAt(modelRow, 0);
		        Detail det = new Detail(o);
		        det.setVisible(true);
		    }
		};

		ButtonColumn buttonColumn = new ButtonColumn(table, move, 1);
		buttonColumn.setMnemonic(KeyEvent.VK_D);
		
		JScrollPane pane = new JScrollPane(table);
		getContentPane().add(pane);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
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
		
		JButton btnNewButton = new JButton("Return");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				Matrices matrix = new Matrices();
				matrix.setVisible(true);
				
			}
		});
		btnNewButton.setBounds(91, 475, 133, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Logout");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				Login.main(new String[0]);
				
			}
		});
		btnNewButton_1.setBounds(336, 475, 133, 29);
		contentPane.add(btnNewButton_1);
		
	}
}


