package com.ebay.ecg.convert;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.dom4j.DocumentException;

/**
 * 
* GUI.java Create on 2014-2-24    
*     
* Copyright (c) 2014-2-24  
*     
* @author doxie@ebay.com   
* @version 1.0
*
 */
public class GUI extends JFrame{
	
	
	protected JTextField filename;

	private JLabel jl1;
	private JLabel jl2;
	private JLabel jl3;

	private JButton bu1;
	private JButton bu2;
	
	private JRadioButton jb1;
	private JRadioButton jb2;
	private ButtonGroup bg;

	public GUI() {
		this.setTitle("TestlinkConvert");
		init();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		
		this.setBounds(0, 0, 355, 150);
	//	Image image = new ImageIcon(getClass().getClassLoader().getResource("image/logo.jpg").getFile()).getImage();
	//.setIconImage(image);
	//	
		this.setResizable(false);
		
		this.setLocationRelativeTo(null);

		this.setVisible(true);
	}

	public void init() {
		final Container con = this.getContentPane();
		jl1 = new JLabel("File");
		jl1.setBounds(20, 40, 70, 20);
		
		jl2 = new JLabel("Choose file to Convert:");
		jl2.setBounds(15, 5, 300, 20);
		
		jl3 = new JLabel("Done !");
		jl3.setBounds(210, 40, 80, 20);
		jl3.setVisible(false);
		
		filename = new JTextField();
		filename.setBounds(50, 40, 150, 20);
		
		jb1 = new JRadioButton("xml->xls",true);
		jb1.setBounds(40, 75, 80, 20);
		jb2 = new JRadioButton("xls->xml");
		jb2.setBounds(120, 75, 80, 20);
		bg = new ButtonGroup();
		bg.add(jb1);
		bg.add(jb2);

		bu1 = new JButton("Choose");
		bu1.setBounds(250, 40, 80, 20);
		bu2 = new JButton("Execute");
		bu2.setBounds(250, 75, 80, 20);

		change();
		
		con.add(jb1);
		con.add(jb2);
		con.add(jl1);
		con.add(jl2);
		con.add(jl3);
		con.add(bu2);
		con.add(bu1);
		con.add(filename);

	}

	public static void main(String[] args) {
		GUI convertor = new GUI();
	}

	
	
	public void change(){
	
		bu1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("Clink and Select");
				JFileChooser chooser = new JFileChooser();
				if(jb1.isSelected()){
				FileNameExtensionFilter filter = new FileNameExtensionFilter("xml", "xml");
				chooser.setFileFilter(filter);
				}
				else
				{FileNameExtensionFilter filter = new FileNameExtensionFilter("xls", "xls");
				chooser.setFileFilter(filter);}
				
				int returnVal = chooser.showOpenDialog(new JPanel());
					if(returnVal == JFileChooser.APPROVE_OPTION){
						System.out.println("The file you choose"+chooser.getSelectedFile().getAbsolutePath());
						filename.setText(chooser.getSelectedFile().getAbsolutePath());
					}
			}
		});
		
		
		
		bu2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(jb1.isSelected()){
				String path = filename.getText();
				try {
					new CreatExcel().creat(new ReadXml().Read(path), path);
					System.out.println("succeed");
					
					jl3.setVisible(true);
					JOptionPane.showMessageDialog(null, "the conversion was successful!");
					
				} catch (RowsExceededException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (WriteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
				else{
					String path = filename.getText();
					
					new CreatXml().create(path);
					System.out.println("succeed");
					
					jl3.setVisible(true);
					JOptionPane.showMessageDialog(null, "the conversion was successful!");
				}	
			}
		});
		}
		
	}


	
	

	