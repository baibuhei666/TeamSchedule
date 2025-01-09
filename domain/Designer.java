package com.atguigu.team.domain;

public class Designer extends Programmer{
	
	private double bonus;//奖金

	public Designer() {
		super();
	}

	public Designer(int id, String name, int age, double salary, Equipment equipment, double bonus) {
		super(id, name, age, salary, equipment);
		this.bonus = bonus;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	@Override
	public String toString() {
		return getDetails() + "\t设计师\t" + getSatatus() + "\t" + bonus + "\t\t" + super.getEquipment().getDescription();
	}
	
	public String getDetailsForTeam() {
		return getSomeDetails() + "\t设计师\t" + bonus;
	}
}
