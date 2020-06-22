package Grapic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.google.gson.Gson;

import GAME.Gamestate;
import GAME.Logger;
import GAME.Passive;

public class PassivePanel extends JPanel{
	private Gamestate game;
	private Logger log;
	private Random ran=new Random();
	public PassivePanel(MainFrame f) throws Exception {
		setLayout(null);
		setPreferredSize(new Dimension(1800, 900));
		game=Gamestate.getinsist();
		log=Logger.liginsist();
		setPassives();
		JButton go=new JButton("go");
		go.setBounds(1400, 700, 100, 100);
		go.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					log.log(game.getPlayer().get_name(), "go to play game", "");
					playShow p= new playShow((MainFrame)f);
					f.remove(PassivePanel.this);
					f.setContentPane(p);
					f.revalidate();
					f.repaint();
					f.pack();
					f.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}
		});
		add(go);
	}
	@Override
	protected void paintComponent(Graphics g) {
		ImageIcon image = new ImageIcon("src\\passiva image\\passiva.png"); 
		g.drawImage(image.getImage(),0,0,null);
		g.setFont(new Font("Tahoma", Font.BOLD, 50));
		g.setColor(Color.WHITE);
		g.drawString("Choose a treasure !!	", 600, 100);
	}
	public void setPassives() throws FileNotFoundException{
		Gson j= new Gson();
		File f3=new File(System.getProperty("user.dir")+"\\src\\passive");
		File[] dirr3=f3.listFiles();
		if(dirr3!=null) {
			for(File ch:dirr3) {
				Scanner sca=new Scanner(ch);
				String t1="";
				while(sca.hasNext()) {
					t1+=sca.nextLine();
				}
				Passive s= j.fromJson(t1, Passive.class);
				game.addPassives(s);
			}
		}
		ArrayList<Integer > a=new ArrayList<>();
		while (a.size()!=3) {
			int x=(ran.nextInt(7));
			if(!a.contains(x)&&x>=0&&x<=7)
				a.add(x);
		}
		for(int i=0 ;i<3 ; i++) {
			final JLabel lp1 =new JLabel(new ImageIcon( System.getProperty("user.dir")+"\\src\\passiva image\\"+game.getPassives().get(a.get(i)).getName()+".png"));
			lp1.setBounds(500+i*300, 200, 300, 400);
			add(lp1);
		}
	}	
}