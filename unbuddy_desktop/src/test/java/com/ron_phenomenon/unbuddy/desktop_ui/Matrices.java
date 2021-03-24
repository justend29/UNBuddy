package LoginPage;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JScrollBar;

public class Matrices extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private static DefaultTableModel tableModel;
	private static int columnNumber = 1;

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
		DefaultTableModel tableModel = new DefaultTableModel();
		
		JButton btnNewButton = new JButton("Add Program");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(37, 218, 100, 48);
		contentPane.add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBounds(168, 31, 372, 182);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollBar scrollBar_1 = new JScrollBar(JScrollBar.HORIZONTAL);
		scrollBar_1.setBounds(0, 141, 372, 17);
		panel.add(scrollBar_1);
		
		JButton btnNewButton_1 = new JButton("Add Term");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(21, 159, 144, 23);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Disenroll From Program");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1_1.setBounds(193, 159, 169, 23);
		panel.add(btnNewButton_1_1);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(10, 31, 17, 479);
		contentPane.add(scrollBar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(168, 269, 372, 182);
		contentPane.add(panel_1);
		
		JScrollBar scrollBar_1_1 = new JScrollBar(JScrollBar.HORIZONTAL);
		scrollBar_1_1.setBounds(0, 141, 372, 17);
		panel_1.add(scrollBar_1_1);
		
		JButton btnNewButton_1_2 = new JButton("Add Term");
		btnNewButton_1_2.setBounds(21, 159, 144, 23);
		panel_1.add(btnNewButton_1_2);
		
		JButton btnNewButton_1_1_1 = new JButton("Disenroll From Program");
		btnNewButton_1_1_1.setBounds(193, 159, 169, 23);
		panel_1.add(btnNewButton_1_1_1);
		
		table = new JTable();
		table.setBounds(147, 5, 411, 505);
		contentPane.add(table);
		
		JButton btnAddTerm = new JButton("Add Term");
		btnAddTerm.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		btnAddTerm.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				 for (;;) {
			            SwingUtilities.invokeLater(new Runnable() {
			                @Override
			                public void run() {
			                    tableModel.addColumn("Column #" + columnNumber++);
			                }
			            });
			            try {
							Thread.sleep(2000);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			        }
			
			

		}
		
	});
	
}
}
