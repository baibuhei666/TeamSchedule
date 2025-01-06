package com.atguigu.team.domain;

import com.atguigu.team.service.Status;

public class Programmer extends Employee{
	
	private int memberId; //用来记录成员加入开发团队后在团队中的ID
	private Status satatus;//表示员工的状态
	private Equipment equipment; //表示该成员领用的设备

	public Programmer() {
		super();
	}

	public Programmer(int id, String name, int age, double salary, Equipment equipment) {
		super(id, name, age, salary);
		this.equipment = equipment;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public Status getSatatus() {
		return satatus;
	}

	public void setSatatus(Status satatus) {
		this.satatus = satatus;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}	
	
}
