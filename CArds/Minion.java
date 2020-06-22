package CArds;

import GAME.Board;

public class Minion extends Cards{
	private int HP;
	private int Attack;
//	final String type="Minion";
//	public Minion() {
//		this.setType("Minion");
//	
//	}
	public void Set_HP(int a) {
		HP=a;
	}
	
	public void Set_Attack(int a) {
		Attack=a;
	}
	public int getAttack() {
		return this.Attack;
	}
	public int getHP() {
		return this.HP;
	}
//	public String get_Type() {
//		return this.type;
//	}
	
}
