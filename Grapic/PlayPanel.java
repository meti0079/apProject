package Grapic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import CArds.Cards;
import CArds.Weapon;
import GAME.Gamestate;
import GAME.Logger;

public class PlayPanel extends JPanel{
	private int currentgem=1;
	private int previousgem=1;
	private JButton manabut;	
	private ArrayList<Cards> hand;
	private ArrayList<Cards> battleground;
	private ArrayList<Cards> deck;
	private Gamestate game;	
	private Random rand=new Random();
	private JLabel next;
	private ArrayList<JLabel> currenthand=new ArrayList<>();
	private ArrayList<JLabel> currentbattleground=new ArrayList<>();
	private int round=0;
	private ArrayList<Cards> myweapon=new ArrayList<>();
	private int roundGame=0;
	private Logger log;
	private TextArea text;
	private JLabel turndo;
	
	public PlayPanel(MainFrame f, TextArea t) throws Exception {
		text=t;
		log=Logger.liginsist();
		game=Gamestate.getinsist();
		deck=(ArrayList<Cards>) game.getPlayer().get_mydeck().clone();
		battleground=new ArrayList<>();
		hand=new ArrayList<>();
		setPreferredSize(new Dimension(1800, 1000));
		setLayout(null);
		manabut= new JButton();
		manabut.setBounds(1280, 400, 120, 80);
		manabut.setBorder(BorderFactory.createEmptyBorder());
		manabut.setContentAreaFilled(false);
		manabut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					log.log(game.getPlayer().get_name(), "clicked end turn ", "");
					finish(f);
				} catch (Exception e) {	
					e.printStackTrace();
				}
				turndo.setText((26-roundGame)+"");
				roundGame++;
				addToHand();
				setcard();
				manaset();
				if(next!=null)
					next.setVisible(false);
				repaint();
			}
		});
		add(manabut);
		addToHand();
		addToHand();
		addToHand();
		setcard();
		next=new JLabel("click end turn");
		next.setForeground(Color.RED);
		next .setBounds(1280, 540, 120, 10);
		next.setVisible(false);
		add(next);
		turndo=new JLabel();
		turndo.setForeground(Color.RED);
		turndo.setBounds(1380, 690, 120, 30);
		turndo.setFont(new Font("Tahoma", Font.BOLD, 30));
	add(turndo);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(new ImageIcon("src\\play image\\"+game.getBackBattleground()).getImage(), 0, 0, null);
		g.drawImage(new ImageIcon("src\\play image\\"+game.getBackCard()).getImage(), 660, 10, null);
		g.drawImage(new ImageIcon("src\\play image\\"+game.getBackCard()).getImage(), 725, 10, null);
		g.drawImage(new ImageIcon("src\\play image\\"+game.getBackCard()).getImage(), 790, 10, null);
		drawGem(g);
		drawhero(g,game.getPlayer().getMyDeck().getHeroDeck().getname());
	}

	public void manaset() {
		if(previousgem==10) {
			currentgem=10;
			return;
		}
		previousgem++;
		if(currentgem+previousgem>10) {
			currentgem=10;
		}else {
			currentgem+=previousgem;			
		}
	}
	public void drawGem(Graphics g) {	
		for(int i=0;i<currentgem;i++) {
			g.drawImage(new ImageIcon("src\\button image\\gem"+(i+1)+".png").getImage(), 1090+33*i, 910, null);
		}
	}
	private void addToHand() {
		if(deck.size()==0&&round==0) {
			deck=(ArrayList<Cards>) game.getPlayer().get_mydeck().clone();
			round=1;
		}
		if(hand.size()<12) {
			int x=0;
			if(deck.size()==0) {
				x=0;
			}else {
				x=rand.nextInt(deck.size());						
			}
			hand.add(deck.get(Math.abs(x)));
			deck.remove(x);
		}
	}

	private void addTobattleground(Cards s) {
		if(s.get_Mana()<=currentgem) {
			if(s.getType().equalsIgnoreCase("minion")) {
				if(battleground.size()<=6) {
					hand.remove(s);
					battleground.add(s);	
					String se=game.getPlayer().get_name()+"  played  "+ s.get_Name()+"\n";
					text.append(se);
					currentgem-=s.get_Mana();
				}else {
					JOptionPane.showConfirmDialog(null, "your battleground if full play a card or click next turn","cant plan",JOptionPane.CLOSED_OPTION);
				}						
			}else if(s.getType().equalsIgnoreCase("Spell")) {
				String se=game.getPlayer().get_name()+"  played  "+ s.get_Name()+"\n";
				text.append(se);
				adduse(s);
				hand.remove(s);			
				currentgem-=s.get_Mana();
			}else {
				if(myweapon.size()==0) {
					String se=game.getPlayer().get_name()+"  played  "+ s.get_Name()+"\n";
					text.append(se);
					adduse(s);
					myweapon.add(s);
					addWeapon(s);
					hand.remove(s);
					currentgem-=s.get_Mana();
				}else {		
					return;
				}
			}
		}else {
			next.setVisible(true);
		}
	}
	private void addWeapon(Cards s) {
		final JLabel we=new JLabel(new ImageIcon("src\\play image\\"+s.get_Name()+".png"));
		we.setBounds(560, 690, 100, 150);
		we.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(currentgem==previousgem) {
					myweapon.remove(s);
					remove(we);					
					try {
						log.log(game.getPlayer().get_name(), game.getPlayer().get_name() ," played  "+ s.get_Name());
						String se=game.getPlayer().get_name()+"  played  "+ s.get_Name();
						text.append(se);
					} catch (IOException e) {
						e.printStackTrace();
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
		add(we);
	}
	public void setcard() {
		for(int i=currentbattleground.size()-1;i>=0;i--) {
			remove(currentbattleground.get(i));
			currentbattleground.remove(currentbattleground.get(i));
		}
		for(int i=currenthand.size()-1;i>=0;i--) {
			remove(currenthand.get(i));
			currenthand.remove(currenthand.get(i));
		}
		int[] g=new int[7];g[0]=0;g[1]=-1;g[2]=1;g[3]=-2;g[4]=2;g[5]=-3;g[6]=3;
		int y =0;
		for(Cards s : battleground) {
			final JLabel x=new JLabel(new ImageIcon("src\\play image\\"+s.get_Name()+".png"));
			currentbattleground.add(x);
			currenthand.remove(x);
			x.setBounds(700+(g[y]*100),500, 100, 150);
			add(x);
			y++;
		}
		int	j=-1;
		for(Cards s : hand) {
			final JLabel x=new JLabel(new ImageIcon("src\\play image\\"+s.get_Name()+".png"));
			x.addMouseListener(new MouseListener() {
				@Override
				public void mouseReleased(MouseEvent e) {	
				}
				@Override
				public void mousePressed(MouseEvent e) {
					adduse(s);
					addTobattleground(s);
					setcard();
					revalidate();
					repaint();
					try {
						log.log(game.getPlayer().get_name(), game.getPlayer().get_name(), s.get_Name());
					} catch (IOException e1) {
						e1.printStackTrace();
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
			currenthand.add(x);
			currentbattleground.remove(x);
			x.setBounds(1000+(j*100), 850, 100, 150);
			add(x);
			j--;
		}	
	}

	private void drawhero(Graphics g, String hero) {
		g.drawImage(new ImageIcon("src\\play image\\"+hero+".png").getImage(), 658,660, null);
	}
	private  void finish(MainFrame f) throws Exception {
		if(roundGame==30) {
			String se=game.getPlayer().get_name()+"  win the match!!!!!  \n";
			text.append(se);
			JOptionPane.showConfirmDialog(null, "YOU WON !!!!", "game finished", JOptionPane.OK_CANCEL_OPTION);
			log.log(game.getPlayer().get_name(), game.getPlayer().get_name()+"  won the match", "");
			game.getPlayer().addPlays();
			game.getPlayer().getMyDeck().addWin();
			game.getPlayer().getMyDeck().addUsethisDeck();
			MenuPanel m=new MenuPanel(f);
			f.remove(PlayPanel.this);
			f.setContentPane(m);
			f.revalidate();
			f.repaint();
			f.pack();
			f.setLocationRelativeTo(null);
		}
	}
	private void adduse(Cards s) {
		for(Cards q: game.getPlayer().getMyDeck().getDeck())
			if(s.get_Name().equals(s.get_Name()))
				q.addUse();
	}
}