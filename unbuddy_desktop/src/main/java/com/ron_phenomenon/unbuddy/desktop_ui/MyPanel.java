package com.ron_phenomenon.unbuddy.desktop_ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import java.awt.Component;

public class MyPanel extends JPanel	{
	
	public MyPanel()	{
		
		super();
		JPanel contentPane = new JPanel();
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		JScrollBar scrollBar_1 = new JScrollBar(JScrollBar.HORIZONTAL);
		scrollBar_1.setBounds(0, 141, 372, 17);
		panel.add(scrollBar_1);
		
		JButton btnNewButton_1 = new JButton("Add Term");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//tableModel = (DefaultTableModel) table.getModel();
			}
		});
		btnNewButton_1.setBounds(21, 159, 144, 23);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Disenroll From Program");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				remove(panel);
				revalidate();
				repaint();
				
			}
		});
		btnNewButton_1_1.setBounds(193, 159, 169, 23);
		panel.add(btnNewButton_1_1);
		
		JTable table = new JTable();
		table.setBounds(0, 0, 372, 130);
		panel.add(table);
	}

	/**
	 * @wbp.factory
	 * @wbp.factory.parameter.source comp scrollBar_1
	 * @wbp.factory.parameter.source comp_1 btnNewButton_1
	 * @wbp.factory.parameter.source comp_2 btnNewButton_1_1
	 * @wbp.factory.parameter.source comp_3 table
	 */
	public static JPanel createJPanel(Component comp, Component comp_1, Component comp_2, Component comp_3) {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.add(comp);
		panel.add(comp_1);
		panel.add(comp_2);
		panel.add(comp_3);
		return panel;
	}
}
