package GAME;
import org.json.simple.JSONObject;

import java.awt.List;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import CArds.Cards;
import Heroooo.Heros;

import org.json.simple.parser.JSONParser;
public class players {
	private String Name;
	private String Password;
	public  int Gem;
	private Store mystore;
	private ArrayList<Decks> My_Decks=new ArrayList<>();
	private  ArrayList<Cards> My_Cards =new ArrayList<>();
	private ArrayList<Heros> My_Heros=new ArrayList<>();
	private int plays;
	private Decks myDeck;

	public Decks getMyDeck() {
		return myDeck;
	}

	public void setMyDeck(Decks myDeck) {
		this.myDeck = myDeck;
	}

	public players(String name,String pass,int g) {
		Name=name;
		Password=pass;
		Gem=g;
		setMystore(new Store());
	}

	public void addHero(Heros s) {
		My_Heros.add(s);
	}
	public void Change_Name(String s) {
		Name=s;
	}
	public void Change_Password(String s) {
		Password=s;
	}
	public void Add_card(Cards s) {
		My_Cards.add(s);
	}
	public void reduce_gem(int s) {
		if(Gem-s>0)
			Gem=Gem-s;
	}
	public void increase_gem(int s) {
		Gem=Gem+s;
	}
	public String get_pass() {
		return Password;	
	}
	public String get_name() {
		return Name;	
	}
	public Heros get_hero() {
		return myDeck.getHeroDeck();
	}
	public ArrayList<Cards> get_mydeck() {
		return myDeck.getDeck();

	}
	public ArrayList<Cards> get_myCards() {
		return My_Cards;	
	}
	public ArrayList<Heros> get_myheros() {
		return My_Heros;	
	}
	public Store getMystore() {
		return mystore;
	}
	public void setMystore(Store mystore) {
		this.mystore = mystore;
	}
	public void buyacard(Cards s) {
		My_Cards.add(this.mystore.GetCard(s));
		mystore.getBuyCard().remove(s);		
	}
	public boolean sellacard(Cards s) {
		for(Decks q: My_Decks) {
			for(Cards w : q.getDeck())
				if(w.get_Name().equals(s.get_Name())) {
					JOptionPane.showConfirmDialog(null, "cant sell because this card used in deck   :  "+q.getName()
					+"\n remove this from our deck", "cant sell",JOptionPane.ERROR_MESSAGE);
					return false;
				}	
		}
		My_Cards.remove(s);
		mystore.getBuyCard().add(s);
		return true;
	}
	public int getPlays() {
		return plays;
	}
	public void addPlays() {
		this.plays++;
	}
	public void adddeck(Decks a) {
		this.My_Decks.add(a);
	}
	public ArrayList<Decks> getalldeck() {
		return My_Decks;
	}

}

//	public void Add_to_Deck(Cards s) {
//		if(MyHeros.get_HeroCard().size()<15) {
//			if(s.get_Class().equals(MyHeros.getname())||s.get_Class().equals("Neutral")) {
//				MyHeros.get_HeroCard().add(s);							
//			}else {
//				System.out.println("you cant add this card to deck because this card not for your hero");
//			}
//		}else {
//			System.out.println("you cant add cards to deck .please remove a card from your deck and try again");
//		}
//	}