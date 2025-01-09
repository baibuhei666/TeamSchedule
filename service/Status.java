package com.atguigu.team.service;
/**
 * Status枚举类
 * 表示员工的状态
 * FREE-空闲
 * BUSY-已加入开发团队
 * VOCATION-正在休假
 */
public class Status {
	
	private final String NAME;
	private Status(String name) {
		NAME = name;
	}
	
	public static final Status FREE = new Status("FREE");
	public static final Status BUSY = new Status("BUSY");
	public static final Status VOCATION = new Status("VOCATION");
	
	public String getNAME() {
		return NAME;
	}

	@Override
	public String toString() {
		return NAME;
	}
}
