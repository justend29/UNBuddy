package com.ron_phenomenon.unbuddy.desktop_ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.EventObject;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.Box;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Component;

public class Matrices extends JFrame {

	private JPanel contentPane;
	private static DefaultTableModel tableModel;
	private static int columnNumber = 2;
	private static int count = 0;
	private JScrollPane JScroll;
	private final JScrollPane scrollPane = new JScrollPane();
	JPanel [] programs = new JPanel[50];
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
		panel.setBounds(168, 31, 572, 182);
		contentPane.add(panel);
		
		JScrollBar scrollBar_1 = new JScrollBar(JScrollBar.HORIZONTAL);
		scrollBar_1.setBounds(0, 141, 572, 17);
		

		
		panel.setLayout(null);
		
		Object[][] data = {
			    {""},

		
			};
		
		String[] columnNames = {"Term 1"};
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 352, 125);
		panel.add(scrollPane_1);
		
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		table_1 = new JTable(model);
		scrollPane_1.setViewportView(table_1);
	

		
		JButton btnNewButton_1 = new JButton("Add Course");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CourseInfo course = new CourseInfo();
				course.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(96, 135, 168, 23);
		panel.add(btnNewButton_1);
		
		
		JButton btnNewButton1 = new JButton("Add Term");
		btnNewButton1.setBounds(0, 159, 179, 23);
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
		btnNewButton_1_1.setBounds(195, 159, 177, 23);
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel model = (DefaultTableModel) table_1.getModel();
				model.setRowCount(1);
				model.setColumnCount(1);
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
		getContentPane().add(panel_2, BorderLayout.SOUTH);
        panel_2.setLayout(new BorderLayout(0, 0));
        
        JScrollPane scrollPane = new JScrollPane();
        panel_2.add(scrollPane);
		panel_1.add(scrollPane);

		JButton btnNewButton = new JButton("Add Program");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				count++;
				
				if(count > 0)	{
					
					JPanel childPanel = new JPanel();
					childPanel = panel;
					
					childPanel.setBounds(168, 31, 572, 182);
					
					add(childPanel);
					
					revalidate();
				}
				
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

class ButtonRenderer extends JButton implements TableCellRenderer {

	  public ButtonRenderer() {
	    setOpaque(true);
	  }

	  public Component getTableCellRendererComponent(JTable table, Object value,
	      boolean isSelected, boolean hasFocus, int row, int column) {
	    if (isSelected) {
	      setForeground(table.getSelectionForeground());
	      setBackground(table.getSelectionBackground());
	    } else {
	      setForeground(table.getForeground());
	      setBackground(UIManager.getColor("Button.background"));
	    }
	    setText((value == null) ? "" : value.toString());
	    return this;
	  }
	}

	/**
	 * @version 1.0 11/09/98
	 */

class ButtonEditor extends DefaultCellEditor
{
  protected JButton btn;
   private String lbl;
   private Boolean clicked;

   public ButtonEditor(JTextField txt) {
    super(txt);

    btn=new JButton();
    btn.setOpaque(true);

    //WHEN BUTTON IS CLICKED
    btn.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {

        fireEditingStopped();
      }
    });
  }

   //OVERRIDE A COUPLE OF METHODS
   @Override
  public Component getTableCellEditorComponent(JTable table, Object obj,
      boolean selected, int row, int col) {

      //SET TEXT TO BUTTON,SET CLICKED TO TRUE,THEN RETURN THE BTN OBJECT
     lbl=(obj==null) ? "":obj.toString();
     btn.setText(lbl);
     clicked=true;
    return btn;
  }

  //IF BUTTON CELL VALUE CHNAGES,IF CLICKED THAT IS
   @Override
  public Object getCellEditorValue() {

     if(clicked)
      {
      //SHOW US SOME MESSAGE
        JOptionPane.showMessageDialog(btn, lbl+" Clicked");
      }
    //SET IT TO FALSE NOW THAT ITS CLICKED
    clicked=false;
    return new String(lbl);
  }

   @Override
  public boolean stopCellEditing() {

         //SET CLICKED TO FALSE FIRST
      clicked=false;
    return super.stopCellEditing();
  }

   @Override
  protected void fireEditingStopped() {
    // TODO Auto-generated method stub
    super.fireEditingStopped();
  }
}

