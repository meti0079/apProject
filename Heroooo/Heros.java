package Heroooo;

import java.util.ArrayList;

import CArds.Cards;

public class Heros {

	public int HP=30;
	public String Hero_power;
	public String SpecialPower;
	public int Use_Hero_power=1;
	public ArrayList<Cards> Hero_Card;
	public int HeroPowerMana=2;
	public String name;
	public String getname() {
		return name;
	}
	public void setname(String n) {
		name=n;
	}
	public void Set_SpecialPower(String s) {
		SpecialPower=s;
	}
	public void Set_HeroCard(Cards s) {
		Hero_Card.add(s);
	}
	public void Extra_Hero_Power() {
		Use_Hero_power++;
	}
	public void Extra_HP(int a) {
		HP+=a;
	}

	public String get_SpecialPower() {
		return SpecialPower;
	}
	public ArrayList<Cards> get_HeroCard() {
		return Hero_Card;
	}
	public int get_HP() {
		return	HP;
	}
	public int get_useheroPower() {
		return	Use_Hero_power;
	}
	public void changeHeroPowerMana(int s) {
		HeroPowerMana=s;
	}
	public int getHeroPowerMana() {
		return HeroPowerMana;
	}
}
