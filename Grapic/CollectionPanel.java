package Grapic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MenuBar;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.event.AncestorListener;

import CArds.Cards;
import GAME.Gamestate;
import GAME.Logger;

public class CollectionPanel extends JPanel {
	private JButton newDeck;
	private JButton ok;
	private JTabbedPane tp;
	private collection_herospanel Mage;
	private collection_herospanel Hunter;
	private collection_herospanel Priest;
	private collection_herospanel Rouge;
	private collection_herospanel Warlock;
	private collection_herospanel netural;
	private Gamestate game;
	private collection_herospanel deckp;
	private collection_deck deckbord;
	private ArrayList<JLabel > current = new ArrayList<>();
	private collection_search serchp;
	private Logger log;

	public CollectionPanel(MainFrame f) throws Exception {
		game=Gamestate.getinsist();
		log=Logger.liginsist();
		setPreferredSize(new Dimension(1800,990));
		tp=new JTabbedPane();
		Mage=new collection_herospanel("mage");
		setcard("Mage", Mage,f);
		JScrollPane m=new JScrollPane(Mage);
		Mage.setPreferredSize(new Dimension(1500, 2000));
		Hunter=new  collection_herospanel("hunter");
		setcard("Hunter", Hunter,f);
		JScrollPane hu=new JScrollPane(Hunter);
		Hunter.setPreferredSize(new Dimension(1500, 2000));
		Priest=new collection_herospanel("priest");
		setcard("Priest", Priest,f);
		JScrollPane p=new JScrollPane(Priest);
		Priest.setPreferredSize(new  Dimension(1500, 2000));
		Warlock=new collection_herospanel("warlock");
		setcard("Warlock", Warlock,f);
		JScrollPane w=new JScrollPane(Warlock);
		Warlock.setPreferredSize(new  Dimension(1500, 2000));
		Rouge=new collection_herospanel("rogue");
		setcard("Rouge", Rouge,f);
		JScrollPane ru=new JScrollPane(Rouge);
		Rouge.setPreferredSize(new  Dimension(1500, 2000));

		netural=new collection_herospanel("netural");
		setcard("neutral", netural,f);
		JScrollPane ne=new JScrollPane(netural);
		netural.setPreferredSize(new  Dimension(1500, 2500));

		deckp=new collection_herospanel("deck");
		setdeck();
		deckp.setPreferredSize(new Dimension(1500,2000));
		JScrollPane de=new JScrollPane(deckp);

		deckbord =new collection_deck(deckp,this);

		serchp=new collection_search(f);
		serchp.setPreferredSize(new Dimension(1500, 2000));
		JScrollPane ser=new JScrollPane(serchp);
		/////add to tabpane
		tp.add(m,"Mage");
		tp.add(hu,"Hunter");
		tp.add(p,"Priest");
		tp.add(w,"Warlock");
		tp.add(ru,"Rouge");
		tp.add(ne,"Neutral");
		tp.add(ser,"search");
		tp.add(de,"My Deck");
		tp.setPreferredSize(new Dimension(1500, 790));

		setpanel(f);
	}

	public void setcard(String name,JPanel p,MainFrame f) {
		for(Cards s : game.getPlayer().get_myCards()) {
			if(s.get_Class().equalsIgnoreCase(name)) {
				final JLabel lp =new JLabel(new ImageIcon( System.getProperty("user.dir")+"\\src\\card image\\"+s.get_Name()+".png"));
				lp.addMouseListener(new MouseListener() {
					@Override
					public void mouseReleased(MouseEvent arg0) {
					}

					@Override
					public void mousePressed(MouseEvent arg0) {
						int x=JOptionPane.showConfirmDialog(null, "do you want to add this to your deck",
								"add card to deck", JOptionPane.OK_CANCEL_OPTION);
						if(x==JOptionPane.OK_OPTION) {
							game.getPlayer().getMyDeck().addUsethisDeck(0);
							game.getPlayer().getMyDeck().addWin(0);
							if(game.getPlayer().getMyDeck().addCardToDeck(s)){
								try {
									log.log(game.getPlayer().get_name(), "add card to deck", s.get_Name());
								} catch (IOException e1) {
									e1.printStackTrace();
								}
								setdeck();
								deckp.repaint();
								deckp.revalidate();
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
				p.add(lp);
			}
		}
		for(Cards s2 : game.getPlayer().getMystore().getBuyCard()) {
			if(s2.get_Class().equalsIgnoreCase(name)) {
				final JLabel lp1 =new JLabel(new ImageIcon( System.getProperty("user.dir")+"\\src\\card image\\"+s2.get_Name()+"1.png"));
				lp1.addMouseListener(new MouseListener() {
					@Override
					public void mouseReleased(MouseEvent arg0) {
					}
					@Override
					public void mousePressed(MouseEvent arg0) {
						int x=JOptionPane.showConfirmDialog(null, "you dont have this card!! do yo want to buy this??",
								"confirm", JOptionPane.OK_CANCEL_OPTION);
						if(x==JOptionPane.OK_OPTION) {
							try {
								log.log(game.getPlayer().get_name(), "go to shop to buy card", "card is : "+s2.get_Name());
								Shop sh=new Shop((MainFrame)f);
								f.remove(CollectionPanel.this);	
								f.setContentPane(sh);
								f.repaint();
								f.revalidate();
							} catch (Exception e) {
								e.printStackTrace();
							}
							f.pack();
							f.setLocationRelativeTo(null);
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
				p.add(lp1);
			}
		}
	}

	public void setdeck() {
		int sum= current.size();
		for (int i = sum-1; i >=0; i--) {
			deckp.remove(current.get(i));
			current.remove(i);
			deckp.repaint();
			deckp.revalidate();
		}
		for(Cards s : game.getPlayer().get_mydeck()) {			
			final JLabel lp =new JLabel(new ImageIcon( System.getProperty("user.dir")+"\\src\\card image\\"+s.get_Name()+".png"));
			lp.addMouseListener(new MouseListener() {
				@Override
				public void mouseReleased(MouseEvent e) {
				}
				@Override
				public void mousePressed(MouseEvent e) {
					int x=JOptionPane.showConfirmDialog(null, "do you want to remove this from your deck",
							"remove from deck", JOptionPane.OK_CANCEL_OPTION);
					if(x==JOptionPane.OK_OPTION) {
						try {
							game.getPlayer().getMyDeck().addUsethisDeck(0);
							game.getPlayer().getMyDeck().addWin(0);
							log.log(game.getPlayer().get_name(), "remove card from deck", s.get_Name());
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						game.getPlayer().getMyDeck().getDeck().remove(s);
						deckp.remove(lp);
						deckp.repaint();
						deckp.revalidate();
					}
				}
				@Override
				public void mouseExited(MouseEvent e) {
				}
				@Override
				public void mouseEntered(MouseEvent e) {
				}
				@Override
				public void mouseClicked(MouseEvent e) {
				}
			});
			deckp.add(lp);
			current.add(lp);
		}
	}
	public void setpanel(MainFrame f) throws Exception {
		setLayout(new BorderLayout());
		infoPanel inf=new infoPanel(f);
		add(inf,BorderLayout.NORTH);
		add(tp,BorderLayout.CENTER);
		add(deckbord,BorderLayout.EAST);
	}
}