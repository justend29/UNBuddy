package com.ron_phenomenon.unbuddy.desktop_ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import java.awt.Color;

public class Matrices extends JFrame {

	private JPanel contentPane;
	private static DefaultTableModel tableModel;
	private static int columnNumber = 2;
	private static int vert = 100;
	private JScrollPane JScroll;
	private final JScrollPane scrollPane = new JScrollPane();
	private ArrayList<JPanel> programs = new ArrayList<JPanel>();
	private ArrayList<String> courses = new ArrayList<String>();
	private JTable table_1;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Matrices frame = new Matrices();
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
	public Matrices() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 577, 552);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		DefaultTableModel tableModel;
		
		JPanel panel = new JPanel();
		panel.setBounds(168, 31, 372, 182);
		contentPane.add(panel);
		
		JScrollBar scrollBar_1 = new JScrollBar(JScrollBar.HORIZONTAL);
		scrollBar_1.setBounds(0, 141, 372, 17);
		

		
		panel.setLayout(null);
		
		Object[][] data = {
			    {"CS3383"},
			    {"CS1303"},
			    {"CS1073"},
			    {"CS1083"},
		
			};
		
		String[] columnNames = {"Term 1"};
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 352, 125);
		panel.add(scrollPane_1);
		
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		table_1 = new JTable(model);
		scrollPane_1.setViewportView(table_1);
		
		JButton btnNewButton1 = new JButton("Add Term");
		btnNewButton1.setBounds(30, 159, 149, 23);
		btnNewButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				model.addColumn("Term " + columnNumber++);
				
				
			}
		});
		panel.add(btnNewButton1);

		
		
		scrollPane_1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
		    public void adjustmentValueChanged(AdjustmentEvent e) {  
		        e.getAdjustable().setValue(e.getAdjustable().getMaximum());  
		    }
		});;
		
		JButton btnNewButton_1_1 = new JButton("Disenroll From Program");
		btnNewButton_1_1.setBounds(195, 159, 149, 23);
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				remove(panel);
				revalidate();
				repaint();
				
			}
		});
		panel.add(btnNewButton_1_1);

		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(156, 11, 397, 479);
		contentPane.add(panel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 563, 2000);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		scrollPane.setBounds(10, 11, 20, 482);
		panel_1.add(scrollPane);
		
		table_1 = new JTable(model);
		scrollPane_1.setViewportView(table_1);
		
		JButton btnNewButton = new JButton("Add Program");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
			}
		});
		btnNewButton.setBounds(37, 218, 109, 148);
		contentPane.add(btnNewButton);
		
		scrollPane.getViewport().setViewPosition(new Point(0,0));
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
		    public void adjustmentValueChanged(AdjustmentEvent e) {  
		        e.getAdjustable().setValue(e.getAdjustable().getMaximum());  
		    }
		});;
	
	}
}
