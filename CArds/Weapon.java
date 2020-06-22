package CArds;

public class Weapon extends Cards{

	private int Durability;
	private int attack;
//   String type="Weapon";
//	public Weapon() {
//		setType("Weapon");
//	}

//	public String get_Type() {
//		return this.type;
//	}

	public int getAttack() {
		return attack;
	}


	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDurability() {
		return Durability;
	}
	public void setDurability(int durability) {
		Durability = durability;
	}
}
