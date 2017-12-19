package com.myh.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.UIManager;
import java.awt.Component;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class ViewMain extends JFrame {
	

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewMain frame = new ViewMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * constructor
	 */
	public ViewMain() {
		init();
	}
	/**
	 * init
	 */
	private void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 733, 403);
		//初始化一个panel，容纳内容
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new BorderLayout(1, 1));
		setContentPane(contentPanel);
		
		JPanel panel_selector = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_selector.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_selector.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
		JPanel panel_text = new JPanel();
		panel_text.setPreferredSize(new Dimension(20, 30));
		panel_text.setAlignmentY(Component.TOP_ALIGNMENT);
		panel_text.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_text.setBorder(UIManager.getBorder("Button.border"));
		//FlowLayout flowLayout = (FlowLayout) panel_text.getLayout();
		
		contentPanel.add(panel_selector, BorderLayout.NORTH);
		contentPanel.add(panel_text, BorderLayout.CENTER);
		panel_text.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("原始数据");
		lblNewLabel.setBounds(16, 16, 54, 15);
		panel_text.add(lblNewLabel);
		
		JTextArea soruceText = new JTextArea(10,10);
		soruceText.setBounds(80, 12, 542, 113);
		soruceText.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel_text.add(soruceText);
		
		JTextArea destText = new JTextArea(10,10);
		destText.setBounds(80, 135, 542, 169);
		panel_text.add(destText);
		
		
		JLabel lblNewLabel_1 = new JLabel("加工数据");
		lblNewLabel_1.setBounds(16, 139, 54, 15);
		panel_text.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("格式化");
		btnNewButton.setBounds(632, 12, 75, 23);
		panel_text.add(btnNewButton);
		panel_selector.setPreferredSize(new Dimension(0, 40));
		
		JButton JsonButton = new JButton("JSON");
		JsonButton.setAlignmentY(Component.TOP_ALIGNMENT);
		panel_selector.add(JsonButton);
		JButton XmlButton = new JButton("XML");
		panel_selector.add(XmlButton);
		
		

	}
}
