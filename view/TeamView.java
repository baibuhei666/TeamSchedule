package com.atguigu.team.view;

import java.security.Provider.Service;

import com.atguigu.team.domain.Employee;
import com.atguigu.team.domain.Programmer;
import com.atguigu.team.service.NameListService;
import com.atguigu.team.service.TeamException;
import com.atguigu.team.service.TeamService;

/**
 * 
* @ClassName: TeamView.java
* @Description: 软件的操作界面视图。
*
* @version: v1.0.0
* @author: Administrator
* @date: 2025年1月8日 21:57:37
 */
public class TeamView {
	
	public static void main(String[] args) {
		
		TeamView view = new TeamView();
		TeamService service = new TeamService();
		
		view.enterMainMenu();
	}
	
	//listSvc和teamSvc属性：供类中的方法使用
	private NameListService listSvc = new NameListService();
	private TeamService teamSvc = new TeamService();
	private Employee Employee;
	
	/**
	 * 
	* @Function: TeamView.java
	* @Description: 主界面显示及控制方法。
	* 
	* @version: v1.0.0
	* @author: Administrator
	* @date: 2025年1月8日 22:02:11
	 */
	public void enterMainMenu() {
		
		boolean loopFlag = true;
		
		while(loopFlag) {

			listAllEmployees();
			
			System.out.print("1-团队列表  2-添加团队成员  3-删除团队成员 4-退出   请选择(1-4)：");
			
			char menu = TSUtility.readMenuSelection();
			switch(menu) {
			case '1':
				getTeam();
				break;
			case '2':
				addMember();
				break;
			case '3':
				deleteMenber();
				break;
			case '4':
				System.out.print("确认是否退出(Y/N)：");
				char isExit = TSUtility.readConfirmSelection();
				if(isExit == 'Y') {
					loopFlag = false;
				}
				break;
			}
		}
	}
	
	//******以下方法仅供enterMainMenu()方法调用******
	/**
	 * 
	* @Function: TeamView.java
	* @Description: 以表格形式列出公司所有成员
	* 
	* @version: v1.0.0
	* @author: Administrator
	* @date: 2025年1月8日 22:03:27
	 */
	private void listAllEmployees () {
		System.out.println("-------------------------------开发团队调度软件--------------------------------\n");
		System.out.println("ID\t姓名\t年龄\t工资\t职位\t状态\t奖金\t股票\t领用设备");
		Employee[] employees = listSvc.getAllEmployees();
		
		for(int i=0;i<employees.length;i++) {
			System.out.println(employees[i]);
		}
		System.out.println("-------------------------------------------------------------------------------");
	}
	
	/**
	 * 
	* @Function: TeamView.java
	* @Description: 显示团队成员列表操作
	* 
	* @version: v1.0.0
	* @author: Administrator
	* @date: 2025年1月8日 22:05:39
	 */
	private void getTeam() {
//		System.out.println("查看开发团队情况");
		System.out.println("--------------------团队成员列表---------------------\n");
		Programmer[] team = teamSvc.getTeam();
		if(team.length==0) {
			System.out.println("开发团队目前没有成员！");
		}else {	
			System.out.println("TID/ID\t姓名\t年龄\t工资\t职位\t奖金\t股票");
			for(int i=0;i<team.length;i++) {
				System.out.println(team[i].getDetailsForTeam());
			}
		}
		
		System.out.println("-----------------------------------------------------");
		System.out.print("1-团队列表  2-添加团队成员  3-删除团队成员 4-退出   请选择(1-4)：");
	}
	
	/**
	 * 
	* @Function: TeamView.java
	* @Description: 实现添加成员操作
	* 
	* @version: v1.0.0
	* @author: Administrator
	 * @throws TeamException 
	* @date: 2025年1月8日 22:06:03
	 */
	private void addMember(){
//		System.out.println("添加成员操作");
		System.out.println("---------------------添加成员---------------------");
		System.out.print("请输入要添加的员工ID：");
		int num = TSUtility.readInt();
		try {
			Employee e = listSvc.getEmployee(num);
			teamSvc.addMember(e);
			System.out.println("添加成功");
		} catch (TeamException e) {
			System.out.println("添加失败，原因：" + e.getMessage());	
		}
		//按回车键继续...
		TSUtility.readReturn();
	}
	
	/**
	 * 
	* @Function: TeamView.java
	* @Description: 实现删除成员操作
	* 
	* @version: v1.0.0
	* @author: Administrator
	* @date: 2025年1月8日 22:06:52
	 */
	private void deleteMenber() {
//		System.out.println("删除成员操作");
		System.out.println("---------------------删除成员---------------------");
		System.out.print("请输入要删除员工的TID：");
		int num = TSUtility.readInt();
		System.out.print("确认是否删除(Y/N)：");
		char isDelete = TSUtility.readConfirmSelection();
		if(isDelete == 'N') {
			return;
		}
		try {
			teamSvc.removeMember(num);
			System.out.println("删除成功");
		} catch (TeamException e) {
			System.out.println(e.getMessage());
		}
		//按回车键继续...
		TSUtility.readReturn();
	}
}
