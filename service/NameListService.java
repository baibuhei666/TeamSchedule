package com.atguigu.team.service;
import com.atguigu.team.domain.Architect;
import com.atguigu.team.domain.Designer;
import com.atguigu.team.domain.Employee;
import com.atguigu.team.domain.Equipment;
import com.atguigu.team.domain.NoteBook;
import com.atguigu.team.domain.PC;
import com.atguigu.team.domain.Printer;
import com.atguigu.team.domain.Programmer;

import static com.atguigu.team.service.Data.*;
/*
 * 功能：负责将Data中的数据封装到Employee[]数组中，同时提供相关操作Employee[]的方法。
 * 
 * 说明：
 * 	employees用来保存公司所有员工对象
 * 	NameListService()构造器：
 * 		1. 根据项目提供的Data类构建相应大小的employees数组。
 * 		2. 再根据Data类中的数据构建不同的对象，包括Employee、Programmer、Designer
 * 		   和Architect对象，以及相关联的Equipment子类的对象。
 * 		3. 将对象存于数组中。
 * 		4. Data类位于com.atguigu.team.service包中。
 */

/**
 * 
* @ClassName: NameListService.java
* @Description: 负责将Data中的数据封装到Employee[]数组中，同时提供相关操作Employee[]的方法。
*
* @version: v1.0.0
* @author: Administrator
* @date: 2025年1月6日 11:26:00
 */
public class NameListService {
	
	public static void main(String[] args) {
		NameListService namelist = new NameListService();
	}
	
	/**
	 * employees：用来保存公司所有员工对象
	 */
	private Employee[] employees;
	
	/**
	 * 
	* @Function: NameListService.java
	* @Description: 给employees及数组元素进行初始化
	*
	* @param:参数描述
	* @version: v1.0.0
	* @author: Administrator
	* @date: 2025年1月6日 11:32:20
	 */
	public NameListService() {
//		1. 根据项目提供的Data类构建相应大小的employees数组
//		2. 再根据Data类中的数据构建不同的对象，包括Employee、Programmer、Designer和Architect对象，以及相关联的Equipment子类的对象
//		3. 将对象存于数组中
		employees = new Employee[EMPLOYEES.length];
		
		for(int i = 0;i < employees.length;i++) {
			//获取员工类型
			int type = Integer.parseInt(EMPLOYEES[i][0]);
			
			//获取Employee的4个基本信息
			int id = Integer.parseInt(EMPLOYEES[i][1]);
			String name = EMPLOYEES[i][2];
			int age = Integer.parseInt(EMPLOYEES[i][3]);
			double salary = Double.parseDouble(EMPLOYEES[i][4]);
			
			Equipment equipment;
			double bonus;
			int stock;
			
			switch(type) {
				case EMPLOYEE:
					employees[i] = new Employee(id, name, age, salary);
					break;
				case PROGRAMMER:
					equipment = createEquipment(i);
					employees[i] = new Programmer(id, name, age, salary, equipment);
					break;
				case DESIGNER:
					equipment = createEquipment(i);
					bonus = Double.parseDouble(EMPLOYEES[i][5]);
					employees[i] = new Designer(id, name, age, salary, equipment, bonus);
					break;
				case ARCHITECT:
					equipment = createEquipment(i);
					bonus = Double.parseDouble(EMPLOYEES[i][5]);
					stock = Integer.parseInt(EMPLOYEES[i][6]);
					employees[i] = new Architect(id, name, age, salary, equipment, bonus, stock);
					break;
			}
		}
	}
	
	/**
	 * 
	* @Function: NameListService.java
	* @Description: 获取指定index上的员工的设备 
	* @param index
	* @version: v1.0.0
	* @author: Administrator
	* @date: 2025年1月6日 15:50:34
	 */
	private Equipment createEquipment(int index) {
		int type = Integer.parseInt(EQUIPMENTS[index][0]);
		
		String modelOrName = EQUIPMENTS[index][1];
		switch(type) {
			case PC://21
				return new PC(modelOrName, EQUIPMENTS[index][2]);
			case NOTEBOOK://22
				return new NoteBook(modelOrName, Double.parseDouble(EQUIPMENTS[index][2]));
			case PRINTER://23
				return new Printer(modelOrName,EQUIPMENTS[index][2]);
		}
		return null;
	}

	/**
	 * 
	* @Function: NameListService.java
	* @Description: 获取当前所有员工。
	* @return: 包含所有员工对象的数组。
	* @version: v1.0.0
	* @author: Administrator
	* @date: 2025年1月6日 17:47:45
	 */
	public Employee[] getAllEmployees() {
		return employees;
	}
	
	/**
	 * 
	* @Function: NameListService.java
	* @Description: 获取指定ID的员工对象。
	* @参数:指定员工的ID
	* @return 指定员工对象。
	* @exception 找不到指定的员工。
	* @version: v1.0.0
	* @author: Administrator
	* @date: 2025年1月6日 17:49:55
	 */
	public  Employee getEmployee(int id) throws TeamException{
		for(int i = 0;i < employees.length;i++) {
			if(employees[i].getId() == id) {
				return employees[i];
			}
		}
		throw new TeamException("找不到指定的员工！");
	}
}



