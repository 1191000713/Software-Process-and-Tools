import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator  implements ActionListener {
	
	private JFrame frame = new JFrame("计算器");		//定义窗口
	private int xx, yy;								//保存坐标	
	private boolean isDraging = false;				//用以移动窗口
	
	/*******界面颜色&字体格式*******/
	private Color fcolor = new Color(241,241,241);	//窗口菜单颜色
	private Color bcolor1 = new Color(212,212,212);	//其他按键颜色
	private Color bcolor2 = new Color(241,241,241);	//数字按键颜色
	private Color bcolor3 = new Color(229,229,229);	//函数按键颜色
	private Color tcolor = new Color(62,64,76);		//界面字体颜色
	private Color ocolor1 = new Color(219,219,219);
	private Color ocolor2 = new Color(172,182,194);	//科学计算器运算符颜色 	
	private Font fmenu = new Font("微软雅黑",Font.PLAIN,35);//菜单字体格式
	private Font fsubmenu = new Font("微软雅黑",Font.PLAIN,22);//子菜单字体格式
	
	/*******界面容器*******/
	private JPanel panel0 = new JPanel();		//标准计算器结果显示框容器	
	private JPanel panel = new JPanel();		//设置主容器
	private JPanel panel1 = new JPanel();		//标准计算器按键容器
	private JPanel sfpanel1 = new JPanel();		//科学计算器按键容器
	
	/*******显示框*******/
	private JTextField jtxt1 = new JTextField("0");		//标准计算器计算结果文本框
	private JLabel jlb2 = new JLabel(" ",JLabel.RIGHT);	//上行
	
	/*******运算使用变量*******/
	private boolean firstnum = true;					//判断是否是第一个数字
	private char operator = '=';						//用于等号判断进行的运算类型
	private String strresult1 = "0";					//保存按数字键后文本框的数值
	private String strresult2 = "0";					//保存清空前文本框的数值
	private double result1 = 0.0;						//上行计算结果保存
	private double result2 = 0.0;						//输入栏数字保存
	private double result3 = 0.0;						//保存result2
	private double M = 0.0;								//存储功能
	
	/******设置标准计算器按键*****/
	private final JButton sdsbtn[]={new JButton("MC"), new JButton("MR"),new JButton("M+"), new JButton("M-"),
									new JButton("%"),  new JButton("√"), new JButton("x^2"),new JButton("1/x"),
									new JButton("CE"), new JButton("C"), new JButton("←"),  new JButton("÷"),
									new JButton("7"),  new JButton("8"), new JButton("9"),  new JButton("×"),
									new JButton("4"),  new JButton("5"), new JButton("6"),  new JButton("-"),
									new JButton("1"),  new JButton("2"), new JButton("3"),  new JButton("+"),
									new JButton("Esc"),new JButton("0"), new JButton("."),  new JButton("=")};
	
	/******设置科学计算器按键*****/
	private final JButton sfsbtn[]= {new JButton("MC"), new JButton("MR"),     new JButton("M+"),   new JButton("M-"),    new JButton("Mod"),
									 new JButton("x^2"), new JButton("x^y"),   new JButton("Sin"),  new JButton("Cos"),   new JButton("Tan"),
									 new JButton("√"),  new JButton("10^x"),   new JButton("Bin"),  new JButton("Log"),   new JButton("Exp"),
									 new JButton("ln"), new JButton("CE"),     new JButton("C"),    new JButton("←"),     new JButton("÷"),
									 new JButton("π"),  new JButton("7"),      new JButton("8"),    new JButton("9"),     new JButton("×"),
									 new JButton("n!"), new JButton("4"),      new JButton("5"),    new JButton("6"),     new JButton("-"),
									 new JButton("±"),  new JButton("1"),  	   new JButton("2"),    new JButton("3"),     new JButton("+"),
									 new JButton("Esc"),new JButton("Hex"),    new JButton("0"),    new JButton("."),     new JButton("=")};
	
	/******设置菜单*****/
	private JMenuBar jmb = new JMenuBar();		//菜单条组件
	private JMenu menu1;						//菜单
	private JMenuItem item1,item2;				//菜单项
	
	/******实例化计算器*****/
	public Calculator() {				
		UI();
	}
	
	
	/******UI界面设计*****/
public void UI() {
		
	
	/**********菜单***********/
	menu1 = new JMenu("切换类型(S)");	//菜单项详情
	menu1.setToolTipText("点击更改计算器类型");
	menu1.setMnemonic(KeyEvent.VK_S);				//助记符
	item1 = new JMenuItem("标准(Standard)  ");
	item1.addActionListener(new ActionListener() {				//通过菜单切换计算器样式
		public void actionPerformed(ActionEvent e) {
			frame.setSize(431,680);								//设置窗口大小
			panel.remove(sfpanel1);
			panel.add(panel1,BorderLayout.CENTER);
			panel.validate();
			jmb.setBackground(bcolor3);
			menu1.setForeground(tcolor);
		}
	});
	item2 = new JMenuItem("科学(Scientific)");
	item2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			frame.setSize(440,750);		//设置窗口大小
			panel.remove(panel1);
			panel.add(sfpanel1,BorderLayout.CENTER);
			panel.validate();
			jmb.setBackground(tcolor);
			menu1.setForeground(bcolor3);
		}
	});
	menu1.add(item1);					//添加菜单项到菜单上
	menu1.add(item2);
	jmb.add(menu1);						//将菜单加入至菜单栏	
	jmb.setBackground(fcolor);			//设置菜单条颜色
	menu1.setBackground(fcolor);		//设置菜单颜色
	menu1.setFont(fmenu);				//设置菜单字体格式	
	jmb.setFont(fmenu);					//设置菜单条字体格式
	item1.setFont(fsubmenu);			//设置子菜单字体格式
	item2.setFont(fsubmenu);
	frame.setJMenuBar(jmb);				//加入菜单	
	
	/******添加科学计算器*****/
	sfpanel1.setLayout(new GridLayout(8,5, 4,4)); 	//加入按键 
	for(int i = 0 ; i < sfsbtn.length ; i++)
	{
		sfpanel1.add(sfsbtn[i]);			//加入按键组件
		sfsbtn[i].setFont(new Font("微软雅黑",Font.PLAIN,20));
		sfsbtn[i].setForeground(tcolor);	//设置按键字体颜色
		sfsbtn[i].setBackground(bcolor1);	//设置全部按键颜色
		sfsbtn[i].setBorderPainted(false);//设置按键无边框
		sfsbtn[i].addActionListener(this);//添加监听器
	}
	for(int i = 0 ; i < sfsbtn.length ; i++) //为按键上色
	{
		if(i == 21 || i == 22 || i == 23 || i == 26 || i == 27 || i == 28 || i == 31 || i == 32 || i == 33 || i == 37 )
		{
			sfsbtn[i].setBackground(bcolor2);
			sfsbtn[i].setFont(new Font("微软雅黑",Font.PLAIN,30));
		}
		if(i == 0 || i == 1 || i == 2 || i ==3 || i == 4)
		{
			sfsbtn[i].setBackground(fcolor);
		}
		if(i == 5 || i == 6 || i == 7 || i == 8 || i == 9 || i == 10 || i == 11 || i == 12 || i ==13 || i == 14)
		{
			sfsbtn[i].setBackground(bcolor3);
			sfsbtn[i].setFont(new Font("微软雅黑",Font.PLAIN,15));
		}
		
		if(i == 39)
			sfsbtn[i].setBackground(ocolor2);
	}
	
	sfsbtn[35].addActionListener(new ActionListener() {		//退出按键
		public void actionPerformed(ActionEvent e) {
			System.exit(1);
		}
	});
	

	/*********文本框***********/
	jtxt1.setHorizontalAlignment(JTextField.RIGHT);	//设置向右对齐
	jtxt1.setEditable(false); 						//设置不可编辑
	jtxt1.setFont(new Font("微软雅黑",Font.PLAIN,68));//设置结果显示文本框样式
	jtxt1.setBorder(null);							//设置无边框
	jlb2.setFont(new Font("微软雅黑",Font.PLAIN,30));
	
	jtxt1.addKeyListener(new KeyListener() {		//防止超出范围
		public void keyTyped(KeyEvent e) {
			if (jtxt1.getText().length() >= 12)
			{
				jtxt1.setFont(new Font("微软雅黑",Font.PLAIN,38));		//缩小文本框字体大小		
			}
		}
		public void keyReleased(KeyEvent e) {
		}
		public void keyPressed(KeyEvent e) {
		}
	});
	
	/******添加标准计算器*****/
//	mainpanel.setLayout(new BorderLayout(5,5));
	panel.setLayout(new BorderLayout(5,5));			//设置BorderLayout布局管理器，组件间距5，5
	panel0.setLayout(new BorderLayout(5,5));
	panel0.add(jlb2,BorderLayout.NORTH);			//使用另一个容器装文本框	
	panel0.add(jtxt1,BorderLayout.CENTER);
	panel.add(panel0,BorderLayout.NORTH);			//添加结果文本框到北区域	
	frame.add(panel);
	panel.setVisible(true);
	panel1.setLayout(new GridLayout(7,4, 4,4)); 	//加入按键 
	for(int i = 0 ; i < sdsbtn.length ; i++)
	{
		panel1.add(sdsbtn[i]);			//加入按键组件
		sdsbtn[i].setFont(new Font("微软雅黑",Font.PLAIN,19));
		sdsbtn[i].setForeground(tcolor);	//设置按键字体颜色
		sdsbtn[i].setBackground(bcolor1);	//设置全部按键颜色
		sdsbtn[i].setBorderPainted(false);//设置按键无边框
		sdsbtn[i].addActionListener(this);//添加监听器
	}
	for(int i = 0 ; i < sdsbtn.length ; i++) {	
		if(i == 12 || i ==13 || i == 14 || i == 16 || i == 17 || i == 18 || i == 20 || i == 21 || i == 22 || i == 25)//数字键上色
		{
			sdsbtn[i].setFont(new Font("微软雅黑",Font.PLAIN,30));
			sdsbtn[i].setBackground(bcolor2);
			
		}
		if(i == 0 || i == 1 || i == 2 || i ==3 )
		{
			sdsbtn[i].setBackground(fcolor);
		}
		if(i == 15 || i == 19 || i == 23 || i == 24 || i == 26 || i == 27)
			sdsbtn[i].setBackground(ocolor1);
	}
	
	panel.add(panel1,BorderLayout.CENTER);
	sdsbtn[24].addActionListener(new ActionListener() {		//退出按键
		public void actionPerformed(ActionEvent e) {
			System.exit(1);
		}
	});
	
	/*************
	 * **键盘映射按键**
	 * **************/
	jtxt1.addKeyListener(new KeyAdapter() {
		public void keyTyped(KeyEvent arg0) {
			/***********标准计算器***********/
			if (arg0.getKeyChar() == KeyEvent.VK_0) 	//按键0
				sdsbtn[25].doClick();
			if (arg0.getKeyChar() == KeyEvent.VK_1) 	//按键1
				sdsbtn[20].doClick();
			if (arg0.getKeyChar() == KeyEvent.VK_2) 	//按键2
				sdsbtn[21].doClick();
			if (arg0.getKeyChar() == KeyEvent.VK_3) 	//按键3
				sdsbtn[22].doClick();
			if (arg0.getKeyChar() == KeyEvent.VK_4) 	//按键4
				sdsbtn[16].doClick();
			if (arg0.getKeyChar() == KeyEvent.VK_5) 	//按键5
				sdsbtn[17].doClick();
			if (arg0.getKeyChar() == KeyEvent.VK_6) 	//按键6
				sdsbtn[18].doClick();
			if (arg0.getKeyChar() == KeyEvent.VK_7) 	//按键7
				sdsbtn[12].doClick();
			if (arg0.getKeyChar() == KeyEvent.VK_8) 	//按键8
				sdsbtn[13].doClick();
			if (arg0.getKeyChar() == KeyEvent.VK_9) 	//按键9
				sdsbtn[14].doClick();
//			if (arg0.getKeyChar() == KeyEvent.VK_DECIMAL)//小数点
//				sdsbtn[26].doClick();
//			if (arg0.getKeyChar() == KeyEvent.VK_PLUS) 	//+号
//				sdsbtn[23].doClick();
//			if (arg0.getKeyChar() == KeyEvent.VK_SUBTRACT)//-号
//				sdsbtn[19].doClick();
//			if (arg0.getKeyChar() == KeyEvent.VK_DIVIDE) 	///号
//				sdsbtn[11].doClick();
//			if (arg0.getKeyChar() == KeyEvent.VK_ENTER) 	//=号
//				sdsbtn[27].doClick();
			if (arg0.getKeyChar() == KeyEvent.VK_BACK_SPACE) 	//backspace
				sdsbtn[10].doClick();
			if (arg0.getKeyChar() == KeyEvent.VK_ESCAPE) 	//Esc
				sdsbtn[24].doClick();
			
		}
	}) ;
	
	
	/******设置窗口*****/
	frame.setUndecorated(true);	//设置窗口无边框
	frame.setBackground(fcolor);//设置窗口颜色
	frame.setSize(431,680);		//设置窗口大小
	frame.setLocationRelativeTo(null);//设置窗口居中显示
	frame.setVisible(true);		//设置窗口可见
	frame.setResizable(false); 	//设置窗口不可缩放
	frame.setOpacity(0.95f);
	frame.addMouseListener(new MouseAdapter() {			//用于保存窗口坐标
	public void mousePressed(MouseEvent e) {
		isDraging = true;  
		xx = e.getX();					//保存鼠标坐标
		yy = e.getY(); 
	}
	public void mouseReleased(MouseEvent e) {
		isDraging = false;				//松开鼠标
	}
	});
	frame.addMouseMotionListener(new MouseMotionAdapter() {	//移动窗口
		public void mouseDragged(MouseEvent e) {
			if (isDraging) {
				int left = frame.getLocation().x;
				int top = frame.getLocation().y; 
				frame.setLocation(left+e.getX()-xx , top+e.getY()-yy); //移动窗口
				} 
			}
		});
	}	

	/********设置键盘监听*****/

	public void actionPerformed(ActionEvent e) {			//按键监听方法
		
		jtxt1.setFont(new Font("微软雅黑",Font.PLAIN,68));	//恢复文本框字体格式
		
		if (!jlb2.getText().equals(" ")  || jtxt1.getText() != null)  //当文本框不为空时保存它
			{
				strresult1 = jtxt1.getText();
				strresult2 = jtxt1.getText();	//按数字键后保存数字到字符串strresult1
			}
		
		if (firstnum)			//判断是否是第一个数字
		{
			jtxt1.setText("");
			if (jtxt1.getText() == null)
			{
				jtxt1.setText(strresult2);	//清空前保存上一次输入的数字，防止空字符串Exception,便于连续操作
				
			}
		}
		
		
													 /***************************************
													 * *************标准计算器***************
													 * *************************************/
		
		/****
		 * 数字键
		 *****/
		if (e.getSource() == sdsbtn[25])			//数字0较特殊单独判断
		{
			if(firstnum)
				jtxt1.setText("0");
			else {
				firstnum = false;
				jtxt1.setText(jtxt1.getText()+sdsbtn[25].getText());
			}
		}
		else if (e.getSource() == sdsbtn[20])	
		{
			firstnum = false;
			jtxt1.setText(jtxt1.getText()+sdsbtn[20].getText());
		}
		else if (e.getSource() == sdsbtn[21])
		{
			firstnum = false;
			jtxt1.setText(jtxt1.getText()+sdsbtn[21].getText());
		}
		else if (e.getSource() == sdsbtn[22])
		{
			firstnum = false;
			jtxt1.setText(jtxt1.getText()+sdsbtn[22].getText());
		}
		else if (e.getSource() == sdsbtn[16])
		{
			firstnum = false;
			jtxt1.setText(jtxt1.getText()+sdsbtn[16].getText());
		}
		else if (e.getSource() == sdsbtn[17])
		{
			firstnum = false;
			jtxt1.setText(jtxt1.getText()+sdsbtn[17].getText());
		}
		else if (e.getSource() == sdsbtn[18])
		{
			firstnum = false;
			jtxt1.setText(jtxt1.getText()+sdsbtn[18].getText());
		}
		else if (e.getSource() == sdsbtn[12])
		{
			firstnum = false;
			jtxt1.setText(jtxt1.getText()+sdsbtn[12].getText());
		}
		else if (e.getSource() == sdsbtn[13])
		{
			firstnum = false;
			jtxt1.setText(jtxt1.getText()+sdsbtn[13].getText());
		}
		else if (e.getSource() == sdsbtn[14])
		{
			firstnum = false;
			jtxt1.setText(jtxt1.getText()+sdsbtn[14].getText());
		}
		else if (e.getSource() == sdsbtn[26])			//小数点
			point();
		
		/****
		 * 删除键
		 *****/
		
		if (e.getSource() == sdsbtn[10])					//退格键
			backspace();
		
		if (e.getSource() == sdsbtn[8])						//清空输入框
			ce();
		
		if (e.getSource() == sdsbtn[9])						//清空所有
			c();
		
		/****
		 * 运算按键
		 *****/
		
		if (e.getSource() == sdsbtn[27])					//等号
			result();
	
		if (e.getSource() == sdsbtn[23])					//加法
			add();
		
		if (e.getSource() == sdsbtn[19])					//减法
			sub();		
		
		if (e.getSource() == sdsbtn[15])					//乘法
			multi();
		
		if (e.getSource() == sdsbtn[11])					//除法
			division();
		
		if (e.getSource() == sdsbtn[4])						//%号运算
			percent();
			
		if (e.getSource() == sdsbtn[5])						//根号运算
			sroot();
		
		if (e.getSource() == sdsbtn[6])						//平方运算
			square();
		
		if (e.getSource() == sdsbtn[7])						//1/x运算
			btnnum();			
		
		/****
		 * 保存按键
		 *****/
		if (e.getSource() == sdsbtn[1]) 					//MR按键，保存当前结果
			mr();		
		
		if (e.getSource() == sdsbtn[0]) 					//MC按键，清除
			mc();
		
		if (e.getSource() == sdsbtn[2]) 					//M+按键，加上M
			mplus();
		
		if (e.getSource() == sdsbtn[3]) 					//M-按键，M减去面板值
			msub();
		
											 /***************************************
											 * *************科学计算器***************
											 * *************************************/
		/****
		 * 运算按键
		 *****/
		
		if (e.getSource() == sfsbtn[39])					//等号
			result();
	
		if (e.getSource() == sfsbtn[34])					//加法
			add();
		
		if (e.getSource() == sfsbtn[29])					//减法
			sub();		
		
		if (e.getSource() == sfsbtn[24])					//乘法
			multi();
		
		if (e.getSource() == sfsbtn[19])					//除法
			division();
		
		if (e.getSource() == sfsbtn[10])					//根号运算
			sroot();
		
		if (e.getSource() == sfsbtn[5])						//平方运算
			square();
		
		if (e.getSource() == sfsbtn[30])					//正负号
			reverst();
		
		if (e.getSource() == sfsbtn[20])					//Pi
			getPi();
		
		if (e.getSource() == sfsbtn[25])					//计算阶乘
			fact();
		
		if (e.getSource() == sfsbtn[15])					//计算ln
			ln();
		
		if (e.getSource() == sfsbtn[4])						//计算求余
			mod();
		
		if (e.getSource() == sfsbtn[14])					//求N*10的M次方
			exp();
		
		if (e.getSource() == sfsbtn[13])					//log
			log();
		
		if (e.getSource() == sfsbtn[11])					//10^x			
			get10x();
		
		if (e.getSource() == sfsbtn[7])						//sin
			sin();
		
		if (e.getSource() == sfsbtn[8])						//cos
			cos();
		
		if (e.getSource() == sfsbtn[9])						//tan
			tan();
		
		if (e.getSource() == sfsbtn[12])					//bin十进制转化为二进制
			bin();
			
		if (e.getSource() == sfsbtn[36])					//Hex十进制转化为十六进制
			hex();
		
		if (e.getSource() == sfsbtn[6])
			xy();
			
		/****
		 * 保存按键
		 *****/
		if (e.getSource() == sfsbtn[1]) 					//MR按键，保存当前结果
			mr();		
		
		if (e.getSource() == sfsbtn[0]) 					//MC按键，清除
			mc();
		
		if (e.getSource() == sfsbtn[2]) 					//M+按键，加上M
			mplus();
		
		if (e.getSource() == sfsbtn[3]) 					//M-按键，M减去面板值
			msub();
		
		/****
		 * 数字键
		 *****/
		if (e.getSource() == sfsbtn[37])			//数字0较特殊单独判断
		{
			if(firstnum)
				jtxt1.setText("0");
			else {
				firstnum = false;
				jtxt1.setText(jtxt1.getText()+sfsbtn[37].getText());
			}
		}
		else if (e.getSource() == sfsbtn[31])	
		{
			firstnum = false;
			jtxt1.setText(jtxt1.getText()+sfsbtn[31].getText());
		}
		else if (e.getSource() == sfsbtn[32])
		{
			firstnum = false;
			jtxt1.setText(jtxt1.getText()+sfsbtn[32].getText());
		}
		else if (e.getSource() == sfsbtn[33])
		{
			firstnum = false;
			jtxt1.setText(jtxt1.getText()+sfsbtn[33].getText());
		}
		else if (e.getSource() == sfsbtn[26])
		{
			firstnum = false;
			jtxt1.setText(jtxt1.getText()+sfsbtn[26].getText());
		}
		else if (e.getSource() == sfsbtn[27])
		{
			firstnum = false;
			jtxt1.setText(jtxt1.getText()+sfsbtn[27].getText());
		}
		else if (e.getSource() == sfsbtn[28])
		{
			firstnum = false;
			jtxt1.setText(jtxt1.getText()+sfsbtn[28].getText());
		}
		else if (e.getSource() == sfsbtn[21])
		{
			firstnum = false;
			jtxt1.setText(jtxt1.getText()+sfsbtn[21].getText());
		}
		else if (e.getSource() == sfsbtn[22])
		{
			firstnum = false;
			jtxt1.setText(jtxt1.getText()+sfsbtn[22].getText());
		}
		else if (e.getSource() == sfsbtn[23])
		{
			firstnum = false;
			jtxt1.setText(jtxt1.getText()+sfsbtn[23].getText());
		}
		else if (e.getSource() == sfsbtn[38])					//小数点
			point();
		
		/****
		 * 删除键
		 *****/
		
		if (e.getSource() == sfsbtn[18])						//退格键
			backspace();
		
		if (e.getSource() == sfsbtn[16])						//清空输入框
			ce();
		
		if (e.getSource() == sfsbtn[17])						//清空所有
			c();
		
		
		length();												//防止数字长度过长
				
	}
	
	
	/*******算法********/
	
	
	private void xy() {
		
		if (jlb2.getText().equals(" ") && result2 == 0.0)	//判断是否是第一个数
		{
			result1 = Double.parseDouble(strresult1);
			jlb2.setText(strresult1+"^");
			firstnum = true;
			operator = '$';
		}else {
			y();
			}				
	}
	
	private void y() {
		result2 = Double.parseDouble(strresult1);
		firstnum = true;
		jlb2.setText(jlb2.getText()+result2);
		result1 = Math.pow(result2, result1);
		jtxt1.setText(String.valueOf(result1));
		strresult2 = String.valueOf(result1);
		result2 = 0.0;
		operator = '=';  
	}
	
	private void tan() {
		result2 = Double.parseDouble(strresult1);	//按下运算符后将该数字存到结果寄存器内
		result3 = result2;								
		firstnum = true;								//下一个数字为新的数字
		result1 = Math.tan(result2);			/****运算*****/
		jtxt1.setText(String.valueOf(result1));
		jlb2.setText(" ");
		result2 = 0.0;
	}
	
	private void cos() {
		result2 = Double.parseDouble(strresult1);	//按下运算符后将该数字存到结果寄存器内
		result3 = result2;								
		firstnum = true;								//下一个数字为新的数字
		result1 = Math.cos(result2);			/****运算*****/
		jtxt1.setText(String.valueOf(result1));
		jlb2.setText(" ");
		result2 = 0.0;
	}
	
	private void sin() {
		result2 = Double.parseDouble(strresult1);	//按下运算符后将该数字存到结果寄存器内
		result3 = result2;								
		firstnum = true;								//下一个数字为新的数字
		result1 = Math.sin(result2);			/****运算*****/
		jtxt1.setText(String.valueOf(result1));
		jlb2.setText(" ");
		result2 = 0.0;
	}
	
	private void get10x() {							//10^x
		result2 = Double.parseDouble(strresult1);	//按下运算符后将该数字存到结果寄存器内
		result3 = result2;								
		firstnum = true;								//下一个数字为新的数字
		result1 = Math.pow(10,result2);					/****运算*****/
		jtxt1.setText(String.valueOf(result1));
		result2 = 0.0;
	}
	
	private void bin() {
		result2 = Integer.parseInt(strresult1);	//按下运算符后将该数字存到结果寄存器内	
		firstnum = true;						//下一个数字为新的数字
		jtxt1.setText(String.valueOf(Integer.toBinaryString((int)result2)));
		result2 = 0.0;
	} 
	
	
	private void hex() {
		result2 = Integer.parseInt(strresult1);	//按下运算符后将该数字存到结果寄存器内	
		firstnum = true;						//下一个数字为新的数字
		jtxt1.setText(String.valueOf(Integer.toHexString((int)result2)));
		result2 = 0.0;
	}

	
	private void log() {
		result2 = Double.parseDouble(strresult1);	//按下运算符后将该数字存到结果寄存器内	
		firstnum = true;								//下一个数字为新的数字
		result1 = log(result2,10);					/****运算*****/
		result3 = result1;
		jtxt1.setText(String.valueOf(result1));
		result2 = 0.0;
		operator = 'l';
	}
	
	private double log(double value,double base) {			//log运算函数
		return Math.log(value)/Math.log(base);
	}
	
	private void exp() {									//exp运算
		if (jlb2.getText().equals(" ") && result2 == 0.0)	//判断是否是第一个数字						
		{
			result1 = Double.parseDouble(strresult1);
			jlb2.setText(strresult1+"E");
			firstnum = true;
			operator = 'E';
		}else {
			result2 = firstnum?result3:Double.parseDouble(strresult1);	//按下运算符后将该数字存到结果寄存器内
			result3 = result2;								//保存result2用于连乘
			firstnum = true;								//下一个数字为新的数字
			result1 = result1 * Math.pow(10,result2);					/****运算*****/
			jlb2.setText(result1+"E");		//将运算过程在上行记录
			result2 = 0.0;
			operator = 'E';
		}
	}
	
	private void mod() {								//mod 符号运算
		if (operator == '%' && strresult1.equals("0"))		//判断被除数是否是0					
		{
			jtxt1.setText("无法输入");
			firstnum = true;
			operator = '%';
			System.out.println("这里是被除数是0");
		}else {
			if (result2 == 0.0 && operator == '=')	 //判断是否是被求余数
			{
				result1 = Double.parseDouble(strresult1);
				result1 = result1 % (result1*10);
				jlb2.setText(result1+" "+sfsbtn[4].getText());
				firstnum = true;	
				operator = '%';
				System.out.println("这里是判断是否是除数");
			}else if (operator == '%'){
				result2 = firstnum?result3:Double.parseDouble(strresult1);	//按下运算符后将该数字存到结果寄存器内
				result3 = result2;								//保存result2用于连求余
				result1 = result1 % result2;					/****运算*****/
				jlb2.setText(result1+" "+sfsbtn[4].getText());		//将运算过程在上行记录
				firstnum = true;								//下一个数字为新的数字
				result2 = 0.0;
				operator = '%';
				System.out.println("这里是求余");
			}
		}
	}
	
	
	private void ln() {								//ln符号运算
		result2 = Double.parseDouble(strresult1);	//按下运算符后将该数字存到结果寄存器内
		result3 = result2;	
		if (result2 >= 0) {
			firstnum = true;				//下一个数字为新的数字
			result1 =Math.log(result2);		/****运算*****/
			jtxt1.setText(String.valueOf(result1));
			result2 = 0.0;
			operator = '^';
		}
	}
	
	private void fact() {							//计算阶乘
		result2 = Double.parseDouble(strresult1);	//按下运算符后将该数字存到结果寄存器内
		result3 = result2;								
		firstnum = true;								//下一个数字为新的数字
		result1 = 1;							/****运算*****/
		for (int i = 1 ; i <= (int)result2 ; i++)
			result1 *= i;
		jtxt1.setText(String.valueOf(result1));
		result2 = 0.0;
	}
	
	private void getPi() {							//输出PI
		firstnum = false;
		jtxt1.setText(String.valueOf(Math.PI));
	}
	
	private void reverst() {						//取正负值
		result2 = Double.parseDouble(strresult1);	//按下运算符后将该数字存到结果寄存器内
		result3 = result2;
		result1 = result2 < 0 ? Math.abs(result2):0-result2;
		firstnum = true;								//下一个数字为新的数字
		jtxt1.setText(String.valueOf(result1));
		result2 = 0.0;
		operator = '±';
	}
	
	private void point(){
		if (jtxt1.getText().indexOf(".") < 0) {		// 判断之前有无小数点
			jtxt1.setText(jtxt1.getText() + ".");
		}
		if (firstnum)								//防止0.xx的情况
		{
			jtxt1.setText("0.");
			firstnum = false;						//补全小数
		}
	}
	
	private void mr() {									//MR键
		M = Double.parseDouble(strresult1);
		jtxt1.setText(strresult1);
		firstnum = true;
	}
	
	private void mc() {									//MC键
		M = 0.0;
	}
	
	private void mplus() {								//M+键
		jlb2.setText(" ");
		jtxt1.setText(String.valueOf(M+Double.parseDouble(strresult1)));
	}
	
	private void msub() {								//M-键
		jlb2.setText(" ");
		jtxt1.setText(String.valueOf(Double.parseDouble(strresult1)-M));
	}
	
	
	private void backspace() {							//退格键
		if (strresult1.length() > 1 /*&& operator != '='**计算过程中按下等号*/)
		{
			strresult1 = strresult1.substring(0, strresult1.length()-1);
			jtxt1.setText(strresult1);
		}
		else 											//若达到最后一个数字则清空
		{
			jtxt1.setText("0");
			firstnum = true;
			operator = '=';
		}	
	}
	
	private void ce() {									//CE键
		jtxt1.setText("0");
		firstnum = true;
	}
	
	private void c() {									//清空键
		jtxt1.setText("0");
		jlb2.setText(" ");
		firstnum = true;
		operator = '=';
		result1 = result2 = 0.0;
	}
		
	
	private void result() {
		result2 = Double.parseDouble(strresult1);		//保存输入符号后输入的数字
		switch(operator) {
		case '+': jtxt1.setText(String.valueOf(result1+result2));break;
		case '-': jtxt1.setText(String.valueOf(result1-result2));break;
		case '*': jtxt1.setText(String.valueOf(result1*result2));break;
		case '/': result2=Double.parseDouble(strresult1);if (operator == '/' && strresult1.equals("0")){divi();break;}else {jtxt1.setText(String.valueOf(result1/result2));break;}
		case '√': jtxt1.setText(String.valueOf(result1));break;
		case '@': jtxt1.setText(String.valueOf(result1));break;
		case '^': jtxt1.setText(String.valueOf(result1));break;
		case '&': jtxt1.setText(String.valueOf(result2));break;
		case '±': jtxt1.setText(String.valueOf(result2));break;
		case '%': result2=firstnum?1.0:Double.parseDouble(strresult1);jtxt1.setText(String.valueOf(result1%result2));break;
		case 'E': result2=firstnum?result3:Double.parseDouble(strresult1);jtxt1.setText(String.valueOf(result1*Math.pow(10, result2)));break;
		case 'l': jtxt1.setText(String.valueOf(result3));break;
		case 'L': jtxt1.setText(String.valueOf(result1));break;
		case '$': y();break;
		case '=': jtxt1.setText(String.valueOf(strresult2));break;
		default : jtxt1.setText(String.valueOf(result1));break;
		}
		jlb2.setText(" ");
		result1 = result2 = result3 =0.0;
		firstnum = true;
		operator = '=';
		length();
		
	}
	
	private void add() {								//加法
		if (jlb2.getText().equals(" ") && result2 == 0.0)	//判断是否是第一个数字
		{
			result1 = Double.parseDouble(strresult1);
			jlb2.setText(strresult1+sdsbtn[23].getText());
			firstnum = true;
			operator = '+';
		}else if (jlb2.getText().length() > 1){
			result2 = firstnum?result3:Double.parseDouble(strresult1);	//按下运算符后将该数字存到结果寄存器内
			result3 = result2;								//保存result2用于连加
			firstnum = true;								//下一个数字为新的数字
			result1 = result1 + result2;					/****运算*****/
			jlb2.setText(result1+sdsbtn[23].getText());		//将运算过程在上行记录
			result2 = 0.0;
			operator = '+';
		}
	} 
	
	private void sub() {								//减法
		if (jlb2.getText().equals(" ") && result2 == 0.0)	//判断是否是减数
		{
			result1 = Double.parseDouble(strresult1);
			jlb2.setText(strresult1+sdsbtn[19].getText());
			firstnum = true;
			operator = '-';
		}
		else if(jlb2.getText().length() > 1){
			result2 = firstnum?result3:Double.parseDouble(strresult1);	//按下运算符后将该数字存到结果寄存器内
			result3 = result2;								//保存result2用于连减
			firstnum = true;								//下一个数字为新的数字
			result1 = result1 - result2;					/****运算*****/
			jlb2.setText(result1+sdsbtn[19].getText());		//将运算过程在上行记录
			result2 = 0.0;
			operator = '-';
		}
	}
	
	
	private void multi() {									//乘法
		if (jlb2.getText().equals(" ") && result2 == 0.0)	//判断是否是第一个数字						
		{
			result1 = Double.parseDouble(strresult1);
			jlb2.setText(strresult1+sdsbtn[15].getText());
			firstnum = true;
			operator = '*';
		}else {
			result2 = firstnum?result3:Double.parseDouble(strresult1);	//按下运算符后将该数字存到结果寄存器内
			result3 = result2;								//保存result2用于连乘
			firstnum = true;								//下一个数字为新的数字
			result1 = result1 * result2;					/****运算*****/
			jlb2.setText(result1+sdsbtn[15].getText());		//将运算过程在上行记录
			result2 = 0.0;
			operator = '*';
		}
	}
	
	
	private void division() {								//除法
		if (operator == '/' && strresult1.equals("0"))		//判断被除数是否是0					
		{
			jtxt1.setText("非法被除数！");
			firstnum = true;
			operator = '/';									//用于标记运算符
		}else {
			if (result2 == 0.0 && operator == '=')	 		//判断是否是除数
			{
				result1 = Double.parseDouble(strresult1);
				jlb2.setText(result1+sdsbtn[11].getText());
				firstnum = true;	
				operator = '/';
			}else if (operator == '/'){
				result2 = firstnum?result3:Double.parseDouble(strresult1);//按下运算符后保存数字
				result3 = result2;								//保存result2用于连除
				result1 = result1 / result2;					/****运算*****/
				jlb2.setText(result1+sdsbtn[11].getText());		//将运算过程在上行记录
				firstnum = true;								//下一个数字为新的数字
				result2 = 0.0;
				operator = '/';
			}
		}
		
	}
	
	private void divi() {
		jtxt1.setText("非法被除数！");
		firstnum = true;
		operator = '/';
		System.out.println("这里是被除数是0");
	}
	
	private void percent() {							//百分号运算
		result2 = Double.parseDouble(strresult1);	//按下运算符后将该数字存到结果寄存器内
		result3 = result2;								
		firstnum = true;								//下一个数字为新的数字
		result1 = result2 / 100;					/****运算*****/
		jtxt1.setText(String.valueOf(result1));
		result2 = 0.0;
	}
	
	
	private void sroot() {								//根号运算
		result2 =Double.parseDouble(strresult1);	//按下运算符后将该数字存到结果寄存器内
		result3 = result2;								
		firstnum = true;								//下一个数字为新的数字
		result1 = Math.sqrt(result2);					/****运算*****/
		jtxt1.setText(String.valueOf(result1));
		result2 = 0.0;
		operator = '√';
	}
	
	private void square() {								//平方运算
		result2 = Double.parseDouble(strresult1);		//按下运算符后将该数字存到结果寄存器内
		result3 = result2;	
		firstnum = true;								//下一个数字为新的数字
		result1 = Math.pow(result2,2);					/****运算*****/
		jtxt1.setText(String.valueOf(result1));
		result2 = 0.0;
		operator = '^';
	}

	
	private void btnnum() {								//1/x运算
		result2 = Double.parseDouble(strresult2);		//按下运算符后将该数字存到结果寄存器内
		result3 = result2;	
		if (result2 !=0) {
			result1 = (1/result2);                 /****运算*****/
			jtxt1.setText(String.valueOf(result1));
			result2 = 0.0;
			operator = '&';
			firstnum = true;								//下一个数字为新的数字
		}else jtxt1.setText("无法输入"); 
	}
	
	
	
	private void length() {				//控制文本长度
		jtxt1.requestFocus();			//将焦点移到文本框
		try{
		new Robot().keyPress(KeyEvent.VK_ENTER);//单机回车键触发文本框长度监听
		}catch(Exception e){
		}
	}


	public static void main(String args[]){	
			new Calculator();
	}
}