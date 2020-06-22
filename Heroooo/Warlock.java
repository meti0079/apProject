package Heroooo;

import java.util.ArrayList;



import CArds.Minion;


public class Warlock  extends Heros{
	public Warlock() {
		this.SpecialPower=" have 35 HP";
		this.HP=35;
		this.name="Warlock";
		this.Hero_power="spend 2 mana and do: 1.increase 1 attack and hp to a card  2,add 1 card to player cards";

		
	}
	public void UseHeroPower(Minion s) {
		s.Set_HP(s.getHP()+1);
		s.Set_Attack(s.getAttack()+1);
		this.HP-=2;
		this.Use_Hero_power--;
	}
//	use hero power 2 nazadam
	
}
// emtiazi zadam