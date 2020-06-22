package GAME;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import CArds.Cards;
import CArds.Minion;
import CArds.Spell;
import CArds.Weapon;
import Heroooo.Heros;
import Heroooo.Mage;

public class Login  {
	private Gamestate game;
	public Login(players player) throws Exception {
		game=Gamestate.getinsist();	
		Random ran=new Random();
		Heros first=new Mage();
		player.addHero(first);
		Decks dec=new Decks();
		dec.setName("first deck");
		dec.setHeroDeck(first.getname());
		player.adddeck(dec);
		player.setMyDeck(dec);
		Gson j=new GsonBuilder().setPrettyPrinting().create();
		File fe=new File(System.getProperty("user.dir")+"\\src\\CArds\\spells");

		File[] dir=fe.listFiles();
		if(dir!=null) {
			int i=0;
			for(File ch:dir) {
				Scanner sca=new Scanner(ch);
				String t1="";
				while(sca.hasNext()) {
					t1+=sca.nextLine();
				}
				Spell m=j.fromJson(t1, Spell.class);
				if(m.get_Class().equals("Mage")  && i<=1) {
					player.get_mydeck().add(m);
					player.Add_card(m);	
					i++;

				}else {
					player.getMystore().getBuyCard().add(m);
				}
			}

		}



		File fa=new File(System.getProperty("user.dir")+"\\src\\CArds\\weapons");

		File[] dirr=fa.listFiles();
		if(dirr!=null) {
			for(File ch:dirr) {
				Scanner sca=new Scanner(ch);
				String t1="";
				while(sca.hasNext()) {
					t1+=sca.nextLine();
				}
				Weapon m=j.fromJson(t1, Weapon.class);
				if(m.get_Class().equals("Mage") ) {
					player.get_mydeck().add(m);
					player.Add_card(m);
				}else {
					player.getMystore().getBuyCard().add(m);

				}
			}
		}

		File f2=new File(System.getProperty("user.dir")+"\\src\\CArds\\minions");
		File[] dirr2=f2.listFiles();
		if(dirr2!=null) {
			for(File ch:dirr2) {
				Scanner sca=new Scanner(ch);
				String t1="";
				while(sca.hasNext()) {
					t1+=sca.nextLine();
				}
				Minion m=j.fromJson(t1, Minion.class);
				player.getMystore().getBuyCard().add(m);

			}




			while(player.get_mydeck().size()<15) {	
				int shans=ran.nextInt(player.getMystore().getBuyCard().size()-1);
				Cards m=player.getMystore().getBuyCard().get(shans);
				if(m.get_Class().equalsIgnoreCase("Mage") && player.get_mydeck().size()<=15) {
					player.get_mydeck().add(m);
					player.Add_card(m);
					player.getMystore().getBuyCard().remove(shans);
					continue;
				}
				if(!m.get_Class().equals("Mage")
						&&  !m.get_Class().equals("Rouge")
						&&  !m.get_Class().equals("Warlock") 
						&&  !m.get_Class().equals("Hunter") 
						&&  !m.get_Class().equals("Priest") 
						&&  player.get_mydeck().size()<=15){
					player.get_mydeck().add(m);
					player.Add_card(m);
					player.getMystore().getBuyCard().remove(shans);
				}
			}
		}



		


	}
}