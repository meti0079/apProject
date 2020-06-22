package GAME;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import CArds.Cards;

public class Gamestate {
	Scanner sca=new Scanner(System.in);
	private	players player;
	private Store stor;
	private	Logger lg=new Logger();
	private	Gson json=new GsonBuilder().setPrettyPrinting().create();
	public static Gamestate game;
	private ArrayList<Passive> passives;
	private String backCard="ca.png";
	private String backBattleground="nattle.jpg";


	public String getBackCard() {
		return backCard;
	}
	public void setBackCard(String backCard) {
		this.backCard = backCard;
	}
	public String getBackBattleground() {
		return backBattleground;
	}
	public void setBackBattleground(String backBattleground) {
		this.backBattleground = backBattleground;
	}
	public Gamestate() throws Exception{
		passives=new ArrayList<>();
		
	}

	public ArrayList<Passive> getPassives() {
		return passives;
	}

	public void addPassives(Passive a) {
		passives.add(a);
	}



	public static Gamestate getinsist() throws Exception {
		if(game==null) {
			game=new Gamestate();
		}
		return game;
	}





	public players getPlayer() {
		return player;
	}

	public void setPlayer(players player) {	
		this.player = player;
	}

	public Store getStor() {
		return stor;
	}

	public void setStor(Store stor) {
		this.stor = stor;
	}



	@Override
	public String toString() {
		String na=System.getProperty("user.dir")+"\\src\\pll\\"+player.get_name();
		return na;
	}
	public void makeprofile() throws IOException {
		FileWriter f=new FileWriter(toString());
		String se=json.toJson(player);
		f.write(se);
		f.close();
	}

	public Boolean checkname(String name ,String pass) throws IOException {
		File file=new File(System.getProperty("user.dir")+"\\src\\PLAYERSNAME\\playersname.txt");
		Scanner s=new Scanner(file);
		boolean isther=false;
		while (s.hasNext()) {
			String line=s.nextLine();
			if(line.startsWith(name)) {
				String pa=s.nextLine();
				if(pa.startsWith(pass))
					isther=true;
			}
		}
		s.close();
		return isther;
	}

	public boolean checkvalid(String s) throws IOException {
		boolean  re=false;
		File file=new File(System.getProperty("user.dir")+"\\src\\PLAYERSNAME\\playersname.txt");
		Scanner ss=new Scanner(file);

		while (ss.hasNext()) {
			String line=ss.nextLine();
			if(line.startsWith(s) ) {
				re=true;
			}
		}
		ss.close();
		return re;
	}

	public void writename(String name ,String pass) throws IOException {
		FileWriter file=new FileWriter(System.getProperty("user.dir")+"\\src\\PLAYERSNAME\\playersname.txt",true);
		file.write(name+"\n");
		file.write(pass+"\n");
		file.close();
	}
	public void readplayer(String name) throws IOException {
		File f=new File(System.getProperty("user.dir")+"\\src\\pll\\"+name);
		Scanner s=new Scanner(f);
		String se="";
		while(s.hasNext()) {
			se+=s.nextLine(); 
		}
		player=json.fromJson(se, players.class);	
		//		lg.log(player.get_name(), "sign in ", "");
	}


	public void SelectHero(String name) throws Exception {
		for (int i = 0; i < player.get_myheros().size(); i++) {
			String me=player.get_myheros().get(i).getname();
			if(me.contains(name)) {
				player.getMyDeck().setHeroDeck((player.get_myheros().get(i).getname()));
				break;
			}
		}
		//	lg.log(player.get_name(), "select hero", " selected"+player.get_hero().getname());
	}


}
