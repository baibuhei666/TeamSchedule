# 项目介绍
## 目标
- 模拟实现一个基于文本界面的《开发团队调度软件》
- 熟悉Java面向对象的高级特性，进一步掌握编程技巧和调试技巧
- **主要涉及以下知识点：**

  - 类的继承性和多态性
  - 对象的值传递、接口
  - static和final修饰符
  - 特殊类的使用：包装类、抽象类、内部类
  - 异常处理
  - 数组、对象数组

## 需求说明
- **该软件实现以下功能：**

  - 软件启动时，根据给定的数据创建公司部分成员列表（数组）
  - 根据菜单提示，基于现有的公司成员，组建一个开发团队以开发一个新的项目
  - 组建过程包括将成员插入到团队中，或从团队中删除某成员，还可以列出团队中现有成员的列表
  - 开发团队成员包括架构师、设计师和程序员

### 界面
- 本软件采用单级菜单方式工作。当软件运行时，主界面显示公司成员的列表，如下：  
![主界面](https://img.picui.cn/free/2025/01/06/677bfb414dabd.jpg)

- 当选择“**添加团队成员**”菜单时，将执行从列表中添加指定（通过ID）成员到<u>开发团队</u>的功能：  
![添加团队成员菜单](https://img.picui.cn/free/2025/01/06/677bfd8114834.png)  
添加成功后，按回车键将重新显示主界面。  
开发人员组成要求：  
&emsp;1. 最多一名架构师  
&emsp;2. 最多两名设计师  
&emsp;3. 最多三名程序员  

- 如果添加操作因某种原因失败，将显示类似以下信息（失败原因视具体原因而不同）：  
![添加失败操作显示信息](https://img.picui.cn/free/2025/01/07/677bff12818ea.png)  
失败信息包含以下几种：  
&emsp;1. 成员已满，无法添加  
&emsp;2. 该成员不是开发人员，无法添加  
&emsp;3. 该员工已在本开发团队中  
&emsp;4. 该员工已是某团队成员  
&emsp;5. 该员正在休假，无法添加  
&emsp;6. 团队中至多只能有一名架构师  
&emsp;7. 团队中至多只能有两名设计师  
&emsp;8. 团队中至多只能有三名程序员  

- 当选择“**删除团队成员**”菜单时，将执行从开发团队中删除指定（通过TeamID）成员的功能：  
![删除团队成员界面](https://img.picui.cn/free/2025/01/07/677c009314fbb.png)  
删除成功后，按回车键将重新显示主界面。  

- 当选择“**团队列表**”菜单时，将列出开发团队中的现有成员，例如：  
![团队列表显示信息](https://img.picui.cn/free/2025/01/07/677c00feb7b56.png)  

## 软件设计结构  
![软件设计结构的三个模块](https://img.picui.cn/free/2025/01/07/677c01f8d001d.png)  
- 该软件由以下三个模块组成：
  - view模块为主控模块，负责菜单的显示和处理用户操作。
  - service模块为实体对象（Employee及其子类如程序员等）的管理模块， NameListService和TeamService类分别用各自的数组来管理公司员工和开发团队成员对象。
  - domain模块为Employee及其子类等JavaBean类所在的包。  

- com.atguigu.team.domain模块中包含了所有实体类：  
![domain模块中包含了所有实体类](https://img.picui.cn/free/2025/01/07/677c02dc0f188.png)  
- 其中程序员(Programmer)及其子类，均会领用某种电子设备(Equipment)。  

## 创建基本组件  
- 完成以下工作：  
  1. 创建TeamSchedule项目
  2. 按照设计要求，创建所有包
  3. 将项目提供的几个类复制到相应的包中(view包中：TSUtility.java;   service包中：Data.java)  
- 按照设计要求，domain包中，创建Equipment接口及其各实现子类代码。
- 按照设计要求，在domain包中，创建Employee类及其各子类代码。
- 检验代码的正确性。  
### 键盘访问的实现  
- 项目view包中提供了TSUtility.java类，可用来方便地实现键盘访问。  
### Equipment接口及其实现子类的设计
- Equipment接口含有的方法： + getDescription () : String  
- Equipment的实现子类：PC、NoteBook、Printer
  - PC类  
    \-  model: String  
    \-  display: String  
    \+ PC(model: String, display: String)  
    
  - NoteBook类  
    \- model: String  
    \- price: double  
    \+ NoteBook(model: String, price: double)  

  - Printer类  
    \- name: String  
    \- type: String  
    \+ Printer(name: String, type: String)  
- 说明：model 表示机器的型号，display 表示显示器名称，type 表示机器的类型。
- 根据需要提供各属性的get/set方法以及重载构造器。
- 实现类 实现接口的方法，返回各自属性的信息。  
### Employee类及其子类的设计  
- Employee类：
  - private的属性（int型的id和age;String型的name;double型的salary）
  - public的构造器Employee(id: int , name: String, age: int, salary: double)
- Employee类的 子类：
  - Programmer类
    - private的属性（memberId: int ; status: Status = FREE ;  equipment: Equipment）
    - public的Programmer(id: int , name: String, age: int, salary: double,equipment: Equipment)
    - memberId 用来记录成员加入开发团队后在团队中的ID。
    - Status是项目service包下自定义的类，声明三个对象属性，分别表示成员的状态（FREE-空闲，BUSY-已加入开发团队，VOCATION-正在休假）。Status枚举类位于service包中，封装员工的状态。
    - equipment 表示该成员领用的设备。
    - 可根据需要为类提供各属性的get/set方法以及重载构造器。  
  - Designer类
    - privatte的属性：double bonus;
    - public的Designer(id: int , name: String, age: int, salary: double, equipment: Equipment, bonus : double)
    - bonus 表示奖金。
    - 可根据需要为类提供各属性的get/set方法以及重载构造器
  - Architect类
    - privatte的属性：int stock;
    - public的Architect (id: int , name: String, age: int, salary: double, equipment: Equipment, bonus : double, stock : int)
    - stock 表示公司奖励的股票数量。
    - 可根据需要为类提供各属性的get/set方法以及重载构造器。  
## 实现service包中的类  
1. 按照设计要求编写NameListService类。
2. 在NameListService类中临时添加一个main方法中，作为单元测试方法。
3. 在方法中创建NameListService对象，然后分别用模拟数据调用该对象的各个方法，以测试是否正确。  
  注：测试应细化到包含了所有非正常的情况，以确保方法完全正确。
4. 重复1-3步，完成TeamService类的开发。  
### NameListService类的设计  
- 功能：负责将Data中的数据封装到Employee[]数组中，同时提供相关操作Employee[]的方法。
- 要求有一个private的属性 employees 用来保存公司所有员工对象。
- NameListService()构造器：
  - 根据项目提供的Data类构建相应大小的employees数组。
  - 再根据Data类中的数据构建不同的对象，包括Employee、Programmer、Designer和Architect对象，以及相关联的Equipment子类的对象。
  - 将对象存于数组中。
  - Data类位于service包中。  
- getAllEmployees ()方法：获取当前所有员工。返回：包含所有员工对象的数组。
- getEmployee(id : int)方法：获取指定ID的员工对象。参数：指定员工的ID。返回：指定员工对象。异常：找不到指定的员工。
- 在service子包下提供自定义异常类：TeamException
- 另外，可根据需要自行添加其他方法或重载构造器。  
### TeamService类的设计  
- 功能：关于开发团队成员的管理：添加、删除等。  
![TeamService](https://img.picui.cn/free/2025/01/07/677ce57de31c9.png)    
- 说明：
  - counter为静态变量，用来为开发团队新增成员自动生成团队中的唯一ID，即memberId。（提示：应使用增1的方式）
  - MAX_MEMBER：表示开发团队最大成员数
  - team数组：用来保存当前团队中的各成员对象
  - total：记录团队成员的实际人数
  - getTeam()方法：返回当前团队的所有对象。返回：包含所有成员对象的数组，数组大小与成员人数一致。
  - addMember(e: Employee)方法：向团队中添加成员。
    - 参数：待添加成员的对象
    - 异常：添加失败， TeamException中包含了失败原因  
  - removeMember(memberId: int)方法：从团队中删除成员。
    - 参数：待删除成员的memberId
    - 异常：找不到指定memberId的员工，删除失败  
  - 另外，可根据需要自行添加其他方法或重载构造器。  
## 实现view包中类  
1. 按照设计要求编写TeamView类，逐一实现各个方法，并编译。
2. 执行main方法中，测试软件全部功能。  
### TeamView类的设计  
![TeamView](https://img.picui.cn/free/2025/01/07/677ce741a2cb7.png)  

  - listSvc和teamSvc属性：供类中的方法使用。
  - enterMainMenu ()方法：主界面显示及控制方法。
  - 以下方法仅供enterMainMenu()方法调用：
    - listAllEmployees ()方法：以表格形式列出公司所有成员
    - getTeam()方法：显示团队成员列表操作
    - addMember ()方法：实现添加成员操作
    - deleteMember ()方法：实现删除成员操作
