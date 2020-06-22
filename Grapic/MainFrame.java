package Grapic;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class MainFrame extends JFrame {

	private LoginPanel loginPanel;
	public MainFrame () throws Exception {
		super("HearthStone");
		  Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
         setCursor(cursor);  
		setLayout(new BorderLayout());
		MenuPanel menupanel=new MenuPanel(this); 
		this.loginPanel=new LoginPanel(this,menupanel);
		add(loginPanel, BorderLayout.CENTER);
		setSize(new Dimension(1800, 1000));
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
		setVisible(true);
		///	set icon
		BufferedImage image = null; 
		try{ 
			File input_file = new File("stone.png"); 
			image = ImageIO.read(input_file); 
		} 
		catch(IOException e) 
		{	}
		setIconImage(image);
	}
}