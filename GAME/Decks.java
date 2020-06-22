package GAME;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import CArds.Cards;
import Heroooo.Heros;

public class Decks {
	private  Heros heroDeck;
	private ArrayList<Cards> deck;
	private String name;
	private int win;
	private int usethisDeck;

	public Decks() throws Exception {

		this.deck=new ArrayList<>();
		win=0;
		usethisDeck=0;

	}

	public float GetAverage() {
		int sum=0;
		for(Cards a:deck)
			sum+=a.get_Mana();
		if(deck.size()==0)
			return 0;
		return sum/deck.size();
	}








	public Heros getHeroDeck() {
		return heroDeck;
	}
	public void setHeroDeck(String name) throws Exception {
		Gamestate game=Gamestate.getinsist();
		for(Heros s:game.getPlayer().get_myheros())
			if(s.getname().equalsIgnoreCase(name)) {
				this.heroDeck =s ;

			}
		for(Cards a:deck) {
			if(!a.get_Class().equalsIgnoreCase("Neutral"))
				deck.remove(a);
		}
	}
	public ArrayList<Cards> getDeck() {
		return deck;
	}
	public boolean addCardToDeck(Cards e) {
		int sum=0;
		for(Cards ss:deck) {
			if(ss.get_Name().equalsIgnoreCase(e.get_Name()))
				sum++;
		}

		if(sum<2) {
			if(e.get_Class().equals(heroDeck.getname()) || e.get_Class().equals("Neutral")) {
				if(deck.size()<15) {
					this.deck.add(e);
					return true;
				}else {
					JOptionPane.showConfirmDialog(null, "Cant add because this card is full  , \n remove a card from deck"
							, "error", JOptionPane.OK_CANCEL_OPTION);
				}
			}else {
				JOptionPane.showConfirmDialog(null, "Cant add because this card is for a diffrent hero , \n remove a card from deck"
						, "error", JOptionPane.OK_CANCEL_OPTION);
			}
		}else {
			JOptionPane.showConfirmDialog(null, "Cant add because you have this in your deck , \n remove a card from deck"
					, "error", JOptionPane.OK_CANCEL_OPTION);
		}
		return false;
	}
	public void removeCardFromDeck(Cards e) {
		this.deck.remove(e);
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getWin() {
		return win;
	}
	public void addWin() {
		this.win++;
	}
	public void addWin(int a) {
		this.win=a;
	}
	public int getUsethisDeck() {
		return usethisDeck;
	}
	public void addUsethisDeck() {
		this.usethisDeck ++;
	}
	public void addUsethisDeck(int a) {
		this.usethisDeck =a;
	}

}
