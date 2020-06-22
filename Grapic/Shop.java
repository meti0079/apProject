package Grapic;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import CArds.Cards;
import GAME.Gamestate;
import GAME.Logger;

public class Shop extends JPanel{
	private Gamestate game;
	private infoPanel inf;
	private Logger log;
	private ArrayList<JLabel> current=new ArrayList<>();
	public Shop(MainFrame f) throws Exception {
		setLayout(null);
		log=Logger.liginsist();
		inf = infoPanel.getinsist(f);
		game=Gamestate.getinsist();
		setPreferredSize(new Dimension(1800, 1000));
		infoPanel inf=infoPanel.getinsist(f);
		inf.setBounds(0, 0, 1800, 100);
		add(inf);
		collection_herospanel s1= new collection_herospanel("sell");
		s1.setPreferredSize(new Dimension(1800, 2000));
		s1.setBounds(0, 100, 1800, 2000);
		JScrollPane pp=new JScrollPane(s1);

		StorePanel s=new StorePanel(f, this,s1);
		s.setBounds(0, 100, 1800, 900);
		JScrollPane p=new JScrollPane(s);
		JTabbedPane tp =new JTabbedPane();

		tp.add(p,"buy");
		tp.add(pp, "sell");
		setcard(s1, s);
		tp.setBounds(0, 100, 1800, 900);
		add(tp);
	}
	public void setcard(JPanel x, StorePanel y) {
		int sum= current.size();
		for (int i = sum-1; i >=0; i--) {
			x.remove(current.get(i));
			current.remove(i);
			x.repaint();
			x.revalidate();
		}
		for(Cards s:game.getPlayer().get_myCards()) {
			ImageIcon icon=new ImageIcon(System.getProperty("user.dir")+"\\src\\card image\\"+s.get_Name()+".png");
			final	JLabel lb1=new JLabel(icon);
			lb1.addMouseListener(new MouseListener() {
				@Override
				public void mouseReleased(MouseEvent arg0) {
				}
				@Override
				public void mousePressed(MouseEvent arg0) {
					int j=JOptionPane.showConfirmDialog(null, "Do you want sell this card??"
							+ "\n you give "+GemCost(s)+" gem t. \n "
							+ "this card for "+s.get_Class()+" class.\n"
							+"and rarity is : "+s.get_Rarity(), "Confirm", JOptionPane.OK_CANCEL_OPTION);
					if(j==JOptionPane.OK_OPTION) {
						if(game.getPlayer().sellacard(s)) {			
							game.getPlayer().Gem+=GemCost(s);
							x.remove(lb1);
							setcard(x, y);
							x.repaint();	
							inf.repaint();
							y.update();
							x.revalidate();
							y.revalidate();
							y.repaint();
							try {
								log.log(game.getPlayer().get_name(), "sell card", s.get_Name());
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}else {
							try {
								log.log(game.getPlayer().get_name(), "error", "cant sell card : "+s.get_Name());
							} catch (IOException e1) {						
								e1.printStackTrace();
							}
						}
					}
				}
				@Override
				public void mouseExited(MouseEvent arg0) {						
				}
				@Override
				public void mouseEntered(MouseEvent arg0) {
				}
				@Override
				public void mouseClicked(MouseEvent arg0) {
				}
			});			 
			x.add(lb1);	
			current.add(lb1);
		}
	}
	private int GemCost(Cards a) {
		if(a.get_Rarity().equalsIgnoreCase("rare"))
			return 2;
		if(a.get_Rarity().equalsIgnoreCase("epic"))
			return 3;
		if(a.get_Rarity().equalsIgnoreCase("legendary"))
			return 4;
		if(a.get_Rarity().equalsIgnoreCase("common"))
			return 1;
		return 1;
	}
}