package Heroooo;

import java.util.ArrayList;


import Heroooo.Heros;
import CArds.Cards;
import CArds.Minion;

public class Mage extends Heros{

	public Mage() {
		this.name="Mage";
		this.SpecialPower=" Spend 2 mana lesser than others for spells";
		this.Set_SpecialPower("Spend 2 mana and damage 1 any enemy");

	}
public void UseHeroPower(Minion s) {
	s.Set_HP(s.getHP()-1);
	this.Use_Hero_power--;
}
public void UseHeroPower(Heros s) {
	s.HP--;
	this.Use_Hero_power--;
}

}
