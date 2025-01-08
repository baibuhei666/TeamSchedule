package com.atguigu.team.service;
/**
 * 
* @ClassName: TeamService.java
* @Description: 关于开发团队成员的管理：添加、删除等。
*
* @version: v1.0.0
* @author: Administrator
* @date: 2025年1月6日 22:38:57
 */

import com.atguigu.team.domain.Architect;
import com.atguigu.team.domain.Designer;
import com.atguigu.team.domain.Employee;
import com.atguigu.team.domain.Programmer;
import com.atguigu.team.view.TSUtility;

public class TeamService {
	/**
	 * 用来为开发团队新增成员自动生成团队中的唯一ID，即memberId。
	 * （提示：应使用增1的方式）
	 */
	private static int counter = 1;
	
	private final int MAX_MEMBER = 5;//表示开发团队最大成员数
	
	//team数组：用来保存当前团队中的各成员对象
	private Programmer[] team = new Programmer[MAX_MEMBER];
	
	//total：记录团队成员的实际人数
	private int total = 0;
	
	public TeamService() {
		super();
	}

	/**
	 * 
	* @Function: TeamService.java
	* @Description: 返回当前团队的所有对象。
	* 数组大小与成员人数一致。
	* @version: v1.0.0
	* @author: Administrator
	* @date: 2025年1月7日 17:04:30
	 */
	public Programmer[] getTeam() {
		Programmer[] team = new Programmer[total];
		for(int i=0;i<team.length;i++) {
			team[i]=this.team[i];
		}
		return team;
	}
	
	/**
	 * 
	* @Function: TeamService.java
	* @Description: 向团队中添加成员.
	* @param 待添加成员的对象。
	* @exception 添加失败， TeamException中包含了失败原因。
	* @version: v1.0.0
	* @author: Administrator
	* @date: 2025年1月7日 17:06:17
	 */
	public void addMember(Employee e) throws TeamException{

//		 成员已满，无法添加
		if(total>= MAX_MEMBER) {
			throw new TeamException("成员已满，无法添加");
		}
//		 该成员不是开发人员，无法添加
		if(!(e instanceof Programmer)) {
			throw new TeamException("该成员不是开发人员，无法添加");
		}
//		 该员工已在本开发团队中
		if(isExist(e)) {
			throw new TeamException("该员工已在本开发团队中");
		}
//		 该员工已是某团队成员		
//		 该员正在休假，无法添加
		Programmer p = (Programmer)e;//一定不会出现ClassCastException
		if("BUSY".equals(p.getSatatus().getNAME())) {
			throw new TeamException("该员工已是某团队成员");
		}else if("VACARION".equals(p.getSatatus().getNAME())) {
			throw new TeamException("该员正在休假，无法添加");
		}
//		 团队中至多只能有一名架构师
//		 团队中至多只能有两名设计师
//		 团队中至多只能有三名程序员	
		//获取team已有成员中架构师，设计师，程序员的人数
		int numOfArch = 0,numOfDes = 0,numOfPro = 0;
		for(int i=0;i < total;i++) {
			if(team[i] instanceof Architect) {
				numOfArch++;
			}else if(team[i] instanceof Designer) {
				numOfDes++;
			}else {
				numOfPro++;
			}
		}
		
		if(p instanceof Architect) {
			if(numOfArch >= 1) {
				throw new TeamException("团队中至多只能有一名架构师");
			}
		}else if(p instanceof Designer) {
			if(numOfDes >= 2) {
				throw new TeamException("团队中至多只能有两名设计师");
			}
		}else {
			if(numOfPro >= 3) {
				throw new TeamException("团队中至多只能有三名程序员");
			}
		}
		
		//将p(或e)添加到现有的team中
		team[total++] = p;
		//p的属性赋值
		p.setStatus(Status.BUSY);
		p.setMemberId(counter++);
	}
	
	/**
	 * 
	* @Function: TeamService.java
	* @Description 用于判断：指定添加的员工，是否已经存在于开发团队中。
	* @return 如果存在，返回true。否则，返回false。
	* @version: v1.0.0
	* @author: Administrator
	* @date: 2025年1月7日 22:38:26
	 */
	private boolean isExist(Employee e) {
		
		for(int i=0;i<total;i++) {
			if(team[i].getId()==e.getId()) {
				return true;
			}
		}
		return false;
	}
	
	

	/**
	 * 
	* @Function: TeamService.java
	* @Description: 从团队中删除成员。
	* @param 待删除成员的memberId
	* @exception 找不到指定memberId的员工，删除失败。
	* @version: v1.0.0
	* @author: Administrator
	* @date: 2025年1月7日 17:07:55
	 */
	 public void removeMember(int memberId) throws TeamException{
		 TSUtility tsu = new TSUtility();
		 System.out.print("请输入要删除员工的TID：");
		 tsu.readInt();
		 System.out.print("确认是否删除(Y/N)：");
		 char yOrN = tsu.readConfirmSelection();
		 switch(yOrN) {
		 case 'N':
			 break;
		 case 'Y':
			 int i=0;
			 for(;i < total;i++) {
				 if(team[i].getMemberId()==memberId) {
					  team[i].setStatus(Status.FREE);
					  break;
				 }
			 }
			 //未找到制定memberId的情况
			 if(i==total) {
				 throw new TeamException("删除失败，原因：找不到该成员，无法删除");
			 }
			 
			 //后一个元素覆盖前一个元素，实现删除操作
			 for(int j=i+1;j<total;j++) {
				team[j-1]=team[j]; 
			 }
//			 team[total-1]=null;
//			 total--;
			 team[--total] = null;
			 counter--;
		 }
	 }
	
}
