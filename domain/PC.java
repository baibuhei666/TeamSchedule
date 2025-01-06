package com.atguigu.team.domain;

public class PC implements Equipment{
	
	private String model;//表示机器的型号
	private String display;//表示显示器名称

	@Override
	public String getDescription() {
		return model + "(" + display + ")";
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}
	
	public PC(String model,String display) {
		this.model = model;
		this.display = display;
	}

	public PC() {
		super();
	}

	@Override
	public String toString() {
		return "PC [model=" + model + ", display=" + display + "]";
	}
	
}
