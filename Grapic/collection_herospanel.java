package Grapic;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class collection_herospanel extends JPanel {
	private String na="";
	public collection_herospanel(String name) {
		setPreferredSize(new Dimension(1500,1500));
		na+=name;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon icon =new ImageIcon("src\\backgrund image\\"+na+".jpg");
		g.drawImage(icon.getImage(), 0, 0, null);
	}
}
