package com.atguigu.team.domain;

public class Architect extends Designer{
	
	private int stock;//公司奖励的股票数量

	public Architect() {
		super();
	}

	public Architect(int id, String name, int age, double salary, Equipment equipment, double bonus, int stock) {
		super(id, name, age, salary, equipment, bonus);
		this.stock = stock;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	@Override
	public String toString() {
		return getDetails() + "\t架构师\t" + getSatatus() + "\t" + getBonus() + "\t" + stock + "\t" + super.getEquipment().getDescription();
	}
	
	public String getDetailsForTeam() {
		return getSomeDetails() + "\t架构师\t" + super.getBonus() + "\t" + stock;
	}
}
