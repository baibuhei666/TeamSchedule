package com.atguigu.team.service;
/**
 * 
* @ClassName: TeamException.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: Administrator
* @date: 2025年1月6日 21:10:40
 */
public class TeamException extends Exception{

	static final long serialVersionUID = -3387516993124222025L;
	
	public TeamException() {
		super();
	}
	
	public TeamException(String message) {
		super(message);
	}	
}
