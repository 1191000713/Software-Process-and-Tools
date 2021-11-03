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
	
	private JFrame frame = new JFrame("������");		//���崰��
	private int xx, yy;								//��������	
	private boolean isDraging = false;				//�����ƶ�����
	
	/*******������ɫ&�����ʽ*******/
	private Color fcolor = new Color(241,241,241);	//���ڲ˵���ɫ
	private Color bcolor1 = new Color(212,212,212);	//����������ɫ
	private Color bcolor2 = new Color(241,241,241);	//���ְ�����ɫ
	private Color bcolor3 = new Color(229,229,229);	//����������ɫ
	private Color tcolor = new Color(62,64,76);		//����������ɫ
	private Color ocolor1 = new Color(219,219,219);
	private Color ocolor2 = new Color(172,182,194);	//��ѧ�������������ɫ 	
	private Font fmenu = new Font("΢���ź�",Font.PLAIN,35);//�˵������ʽ
	private Font fsubmenu = new Font("΢���ź�",Font.PLAIN,22);//�Ӳ˵������ʽ
	
	/*******��������*******/
	private JPanel panel0 = new JPanel();		//��׼�����������ʾ������	
	private JPanel panel = new JPanel();		//����������
	private JPanel panel1 = new JPanel();		//��׼��������������
	private JPanel sfpanel1 = new JPanel();		//��ѧ��������������
	
	/*******��ʾ��*******/
	private JTextField jtxt1 = new JTextField("0");		//��׼�������������ı���
	private JLabel jlb2 = new JLabel(" ",JLabel.RIGHT);	//����
	
	/*******����ʹ�ñ���*******/
	private boolean firstnum = true;					//�ж��Ƿ��ǵ�һ������
	private char operator = '=';						//���ڵȺ��жϽ��е���������
	private String strresult1 = "0";					//���水���ּ����ı������ֵ
	private String strresult2 = "0";					//�������ǰ�ı������ֵ
	private double result1 = 0.0;						//���м���������
	private double result2 = 0.0;						//���������ֱ���
	private double result3 = 0.0;						//����result2
	private double M = 0.0;								//�洢����
	
	/******���ñ�׼����������*****/
	private final JButton sdsbtn[]={new JButton("MC"), new JButton("MR"),new JButton("M+"), new JButton("M-"),
									new JButton("%"),  new JButton("��"), new JButton("x^2"),new JButton("1/x"),
									new JButton("CE"), new JButton("C"), new JButton("��"),  new JButton("��"),
									new JButton("7"),  new JButton("8"), new JButton("9"),  new JButton("��"),
									new JButton("4"),  new JButton("5"), new JButton("6"),  new JButton("-"),
									new JButton("1"),  new JButton("2"), new JButton("3"),  new JButton("+"),
									new JButton("Esc"),new JButton("0"), new JButton("."),  new JButton("=")};
	
	/******���ÿ�ѧ����������*****/
	private final JButton sfsbtn[]= {new JButton("MC"), new JButton("MR"),     new JButton("M+"),   new JButton("M-"),    new JButton("Mod"),
									 new JButton("x^2"), new JButton("x^y"),   new JButton("Sin"),  new JButton("Cos"),   new JButton("Tan"),
									 new JButton("��"),  new JButton("10^x"),   new JButton("Bin"),  new JButton("Log"),   new JButton("Exp"),
									 new JButton("ln"), new JButton("CE"),     new JButton("C"),    new JButton("��"),     new JButton("��"),
									 new JButton("��"),  new JButton("7"),      new JButton("8"),    new JButton("9"),     new JButton("��"),
									 new JButton("n!"), new JButton("4"),      new JButton("5"),    new JButton("6"),     new JButton("-"),
									 new JButton("��"),  new JButton("1"),  	   new JButton("2"),    new JButton("3"),     new JButton("+"),
									 new JButton("Esc"),new JButton("Hex"),    new JButton("0"),    new JButton("."),     new JButton("=")};
	
	/******���ò˵�*****/
	private JMenuBar jmb = new JMenuBar();		//�˵������
	private JMenu menu1;						//�˵�
	private JMenuItem item1,item2;				//�˵���
	
	/******ʵ����������*****/
	public Calculator() {				
		UI();
	}
	
	
	/******UI�������*****/
public void UI() {
		
	
	/**********�˵�***********/
	menu1 = new JMenu("�л�����(S)");	//�˵�������
	menu1.setToolTipText("������ļ���������");
	menu1.setMnemonic(KeyEvent.VK_S);				//���Ƿ�
	item1 = new JMenuItem("��׼(Standard)  ");
	item1.addActionListener(new ActionListener() {				//ͨ���˵��л���������ʽ
		public void actionPerformed(ActionEvent e) {
			frame.setSize(431,680);								//���ô��ڴ�С
			panel.remove(sfpanel1);
			panel.add(panel1,BorderLayout.CENTER);
			panel.validate();
			jmb.setBackground(bcolor3);
			menu1.setForeground(tcolor);
		}
	});
	item2 = new JMenuItem("��ѧ(Scientific)");
	item2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			frame.setSize(440,750);		//���ô��ڴ�С
			panel.remove(panel1);
			panel.add(sfpanel1,BorderLayout.CENTER);
			panel.validate();
			jmb.setBackground(tcolor);
			menu1.setForeground(bcolor3);
		}
	});
	menu1.add(item1);					//��Ӳ˵���˵���
	menu1.add(item2);
	jmb.add(menu1);						//���˵��������˵���	
	jmb.setBackground(fcolor);			//���ò˵�����ɫ
	menu1.setBackground(fcolor);		//���ò˵���ɫ
	menu1.setFont(fmenu);				//���ò˵������ʽ	
	jmb.setFont(fmenu);					//���ò˵��������ʽ
	item1.setFont(fsubmenu);			//�����Ӳ˵������ʽ
	item2.setFont(fsubmenu);
	frame.setJMenuBar(jmb);				//����˵�	
	
	/******��ӿ�ѧ������*****/
	sfpanel1.setLayout(new GridLayout(8,5, 4,4)); 	//���밴�� 
	for(int i = 0 ; i < sfsbtn.length ; i++)
	{
		sfpanel1.add(sfsbtn[i]);			//���밴�����
		sfsbtn[i].setFont(new Font("΢���ź�",Font.PLAIN,20));
		sfsbtn[i].setForeground(tcolor);	//���ð���������ɫ
		sfsbtn[i].setBackground(bcolor1);	//����ȫ��������ɫ
		sfsbtn[i].setBorderPainted(false);//���ð����ޱ߿�
		sfsbtn[i].addActionListener(this);//��Ӽ�����
	}
	for(int i = 0 ; i < sfsbtn.length ; i++) //Ϊ������ɫ
	{
		if(i == 21 || i == 22 || i == 23 || i == 26 || i == 27 || i == 28 || i == 31 || i == 32 || i == 33 || i == 37 )
		{
			sfsbtn[i].setBackground(bcolor2);
			sfsbtn[i].setFont(new Font("΢���ź�",Font.PLAIN,30));
		}
		if(i == 0 || i == 1 || i == 2 || i ==3 || i == 4)
		{
			sfsbtn[i].setBackground(fcolor);
		}
		if(i == 5 || i == 6 || i == 7 || i == 8 || i == 9 || i == 10 || i == 11 || i == 12 || i ==13 || i == 14)
		{
			sfsbtn[i].setBackground(bcolor3);
			sfsbtn[i].setFont(new Font("΢���ź�",Font.PLAIN,15));
		}
		
		if(i == 39)
			sfsbtn[i].setBackground(ocolor2);
	}
	
	sfsbtn[35].addActionListener(new ActionListener() {		//�˳�����
		public void actionPerformed(ActionEvent e) {
			System.exit(1);
		}
	});
	

	/*********�ı���***********/
	jtxt1.setHorizontalAlignment(JTextField.RIGHT);	//�������Ҷ���
	jtxt1.setEditable(false); 						//���ò��ɱ༭
	jtxt1.setFont(new Font("΢���ź�",Font.PLAIN,68));//���ý����ʾ�ı�����ʽ
	jtxt1.setBorder(null);							//�����ޱ߿�
	jlb2.setFont(new Font("΢���ź�",Font.PLAIN,30));
	
	jtxt1.addKeyListener(new KeyListener() {		//��ֹ������Χ
		public void keyTyped(KeyEvent e) {
			if (jtxt1.getText().length() >= 12)
			{
				jtxt1.setFont(new Font("΢���ź�",Font.PLAIN,38));		//��С�ı��������С		
			}
		}
		public void keyReleased(KeyEvent e) {
		}
		public void keyPressed(KeyEvent e) {
		}
	});
	
	/******��ӱ�׼������*****/
//	mainpanel.setLayout(new BorderLayout(5,5));
	panel.setLayout(new BorderLayout(5,5));			//����BorderLayout���ֹ�������������5��5
	panel0.setLayout(new BorderLayout(5,5));
	panel0.add(jlb2,BorderLayout.NORTH);			//ʹ����һ������װ�ı���	
	panel0.add(jtxt1,BorderLayout.CENTER);
	panel.add(panel0,BorderLayout.NORTH);			//��ӽ���ı��򵽱�����	
	frame.add(panel);
	panel.setVisible(true);
	panel1.setLayout(new GridLayout(7,4, 4,4)); 	//���밴�� 
	for(int i = 0 ; i < sdsbtn.length ; i++)
	{
		panel1.add(sdsbtn[i]);			//���밴�����
		sdsbtn[i].setFont(new Font("΢���ź�",Font.PLAIN,19));
		sdsbtn[i].setForeground(tcolor);	//���ð���������ɫ
		sdsbtn[i].setBackground(bcolor1);	//����ȫ��������ɫ
		sdsbtn[i].setBorderPainted(false);//���ð����ޱ߿�
		sdsbtn[i].addActionListener(this);//��Ӽ�����
	}
	for(int i = 0 ; i < sdsbtn.length ; i++) {	
		if(i == 12 || i ==13 || i == 14 || i == 16 || i == 17 || i == 18 || i == 20 || i == 21 || i == 22 || i == 25)//���ּ���ɫ
		{
			sdsbtn[i].setFont(new Font("΢���ź�",Font.PLAIN,30));
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
	sdsbtn[24].addActionListener(new ActionListener() {		//�˳�����
		public void actionPerformed(ActionEvent e) {
			System.exit(1);
		}
	});
	
	/*************
	 * **����ӳ�䰴��**
	 * **************/
	jtxt1.addKeyListener(new KeyAdapter() {
		public void keyTyped(KeyEvent arg0) {
			/***********��׼������***********/
			if (arg0.getKeyChar() == KeyEvent.VK_0) 	//����0
				sdsbtn[25].doClick();
			if (arg0.getKeyChar() == KeyEvent.VK_1) 	//����1
				sdsbtn[20].doClick();
			if (arg0.getKeyChar() == KeyEvent.VK_2) 	//����2
				sdsbtn[21].doClick();
			if (arg0.getKeyChar() == KeyEvent.VK_3) 	//����3
				sdsbtn[22].doClick();
			if (arg0.getKeyChar() == KeyEvent.VK_4) 	//����4
				sdsbtn[16].doClick();
			if (arg0.getKeyChar() == KeyEvent.VK_5) 	//����5
				sdsbtn[17].doClick();
			if (arg0.getKeyChar() == KeyEvent.VK_6) 	//����6
				sdsbtn[18].doClick();
			if (arg0.getKeyChar() == KeyEvent.VK_7) 	//����7
				sdsbtn[12].doClick();
			if (arg0.getKeyChar() == KeyEvent.VK_8) 	//����8
				sdsbtn[13].doClick();
			if (arg0.getKeyChar() == KeyEvent.VK_9) 	//����9
				sdsbtn[14].doClick();
//			if (arg0.getKeyChar() == KeyEvent.VK_DECIMAL)//С����
//				sdsbtn[26].doClick();
//			if (arg0.getKeyChar() == KeyEvent.VK_PLUS) 	//+��
//				sdsbtn[23].doClick();
//			if (arg0.getKeyChar() == KeyEvent.VK_SUBTRACT)//-��
//				sdsbtn[19].doClick();
//			if (arg0.getKeyChar() == KeyEvent.VK_DIVIDE) 	///��
//				sdsbtn[11].doClick();
//			if (arg0.getKeyChar() == KeyEvent.VK_ENTER) 	//=��
//				sdsbtn[27].doClick();
			if (arg0.getKeyChar() == KeyEvent.VK_BACK_SPACE) 	//backspace
				sdsbtn[10].doClick();
			if (arg0.getKeyChar() == KeyEvent.VK_ESCAPE) 	//Esc
				sdsbtn[24].doClick();
			
		}
	}) ;
	
	
	/******���ô���*****/
	frame.setUndecorated(true);	//���ô����ޱ߿�
	frame.setBackground(fcolor);//���ô�����ɫ
	frame.setSize(431,680);		//���ô��ڴ�С
	frame.setLocationRelativeTo(null);//���ô��ھ�����ʾ
	frame.setVisible(true);		//���ô��ڿɼ�
	frame.setResizable(false); 	//���ô��ڲ�������
	frame.setOpacity(0.95f);
	frame.addMouseListener(new MouseAdapter() {			//���ڱ��洰������
	public void mousePressed(MouseEvent e) {
		isDraging = true;  
		xx = e.getX();					//�����������
		yy = e.getY(); 
	}
	public void mouseReleased(MouseEvent e) {
		isDraging = false;				//�ɿ����
	}
	});
	frame.addMouseMotionListener(new MouseMotionAdapter() {	//�ƶ�����
		public void mouseDragged(MouseEvent e) {
			if (isDraging) {
				int left = frame.getLocation().x;
				int top = frame.getLocation().y; 
				frame.setLocation(left+e.getX()-xx , top+e.getY()-yy); //�ƶ�����
				} 
			}
		});
	}	

	/********���ü��̼���*****/

	public void actionPerformed(ActionEvent e) {			//������������
		
		jtxt1.setFont(new Font("΢���ź�",Font.PLAIN,68));	//�ָ��ı��������ʽ
		
		if (!jlb2.getText().equals(" ")  || jtxt1.getText() != null)  //���ı���Ϊ��ʱ������
			{
				strresult1 = jtxt1.getText();
				strresult2 = jtxt1.getText();	//�����ּ��󱣴����ֵ��ַ���strresult1
			}
		
		if (firstnum)			//�ж��Ƿ��ǵ�һ������
		{
			jtxt1.setText("");
			if (jtxt1.getText() == null)
			{
				jtxt1.setText(strresult2);	//���ǰ������һ����������֣���ֹ���ַ���Exception,������������
				
			}
		}
		
		
													 /***************************************
													 * *************��׼������***************
													 * *************************************/
		
		/****
		 * ���ּ�
		 *****/
		if (e.getSource() == sdsbtn[25])			//����0�����ⵥ���ж�
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
		else if (e.getSource() == sdsbtn[26])			//С����
			point();
		
		/****
		 * ɾ����
		 *****/
		
		if (e.getSource() == sdsbtn[10])					//�˸��
			backspace();
		
		if (e.getSource() == sdsbtn[8])						//��������
			ce();
		
		if (e.getSource() == sdsbtn[9])						//�������
			c();
		
		/****
		 * ���㰴��
		 *****/
		
		if (e.getSource() == sdsbtn[27])					//�Ⱥ�
			result();
	
		if (e.getSource() == sdsbtn[23])					//�ӷ�
			add();
		
		if (e.getSource() == sdsbtn[19])					//����
			sub();		
		
		if (e.getSource() == sdsbtn[15])					//�˷�
			multi();
		
		if (e.getSource() == sdsbtn[11])					//����
			division();
		
		if (e.getSource() == sdsbtn[4])						//%������
			percent();
			
		if (e.getSource() == sdsbtn[5])						//��������
			sroot();
		
		if (e.getSource() == sdsbtn[6])						//ƽ������
			square();
		
		if (e.getSource() == sdsbtn[7])						//1/x����
			btnnum();			
		
		/****
		 * ���水��
		 *****/
		if (e.getSource() == sdsbtn[1]) 					//MR���������浱ǰ���
			mr();		
		
		if (e.getSource() == sdsbtn[0]) 					//MC���������
			mc();
		
		if (e.getSource() == sdsbtn[2]) 					//M+����������M
			mplus();
		
		if (e.getSource() == sdsbtn[3]) 					//M-������M��ȥ���ֵ
			msub();
		
											 /***************************************
											 * *************��ѧ������***************
											 * *************************************/
		/****
		 * ���㰴��
		 *****/
		
		if (e.getSource() == sfsbtn[39])					//�Ⱥ�
			result();
	
		if (e.getSource() == sfsbtn[34])					//�ӷ�
			add();
		
		if (e.getSource() == sfsbtn[29])					//����
			sub();		
		
		if (e.getSource() == sfsbtn[24])					//�˷�
			multi();
		
		if (e.getSource() == sfsbtn[19])					//����
			division();
		
		if (e.getSource() == sfsbtn[10])					//��������
			sroot();
		
		if (e.getSource() == sfsbtn[5])						//ƽ������
			square();
		
		if (e.getSource() == sfsbtn[30])					//������
			reverst();
		
		if (e.getSource() == sfsbtn[20])					//Pi
			getPi();
		
		if (e.getSource() == sfsbtn[25])					//����׳�
			fact();
		
		if (e.getSource() == sfsbtn[15])					//����ln
			ln();
		
		if (e.getSource() == sfsbtn[4])						//��������
			mod();
		
		if (e.getSource() == sfsbtn[14])					//��N*10��M�η�
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
		
		if (e.getSource() == sfsbtn[12])					//binʮ����ת��Ϊ������
			bin();
			
		if (e.getSource() == sfsbtn[36])					//Hexʮ����ת��Ϊʮ������
			hex();
		
		if (e.getSource() == sfsbtn[6])
			xy();
			
		/****
		 * ���水��
		 *****/
		if (e.getSource() == sfsbtn[1]) 					//MR���������浱ǰ���
			mr();		
		
		if (e.getSource() == sfsbtn[0]) 					//MC���������
			mc();
		
		if (e.getSource() == sfsbtn[2]) 					//M+����������M
			mplus();
		
		if (e.getSource() == sfsbtn[3]) 					//M-������M��ȥ���ֵ
			msub();
		
		/****
		 * ���ּ�
		 *****/
		if (e.getSource() == sfsbtn[37])			//����0�����ⵥ���ж�
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
		else if (e.getSource() == sfsbtn[38])					//С����
			point();
		
		/****
		 * ɾ����
		 *****/
		
		if (e.getSource() == sfsbtn[18])						//�˸��
			backspace();
		
		if (e.getSource() == sfsbtn[16])						//��������
			ce();
		
		if (e.getSource() == sfsbtn[17])						//�������
			c();
		
		
		length();												//��ֹ���ֳ��ȹ���
				
	}
	
	
	/*******�㷨********/
	
	
	private void xy() {
		
		if (jlb2.getText().equals(" ") && result2 == 0.0)	//�ж��Ƿ��ǵ�һ����
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
		result2 = Double.parseDouble(strresult1);	//����������󽫸����ִ浽����Ĵ�����
		result3 = result2;								
		firstnum = true;								//��һ������Ϊ�µ�����
		result1 = Math.tan(result2);			/****����*****/
		jtxt1.setText(String.valueOf(result1));
		jlb2.setText(" ");
		result2 = 0.0;
	}
	
	private void cos() {
		result2 = Double.parseDouble(strresult1);	//����������󽫸����ִ浽����Ĵ�����
		result3 = result2;								
		firstnum = true;								//��һ������Ϊ�µ�����
		result1 = Math.cos(result2);			/****����*****/
		jtxt1.setText(String.valueOf(result1));
		jlb2.setText(" ");
		result2 = 0.0;
	}
	
	private void sin() {
		result2 = Double.parseDouble(strresult1);	//����������󽫸����ִ浽����Ĵ�����
		result3 = result2;								
		firstnum = true;								//��һ������Ϊ�µ�����
		result1 = Math.sin(result2);			/****����*****/
		jtxt1.setText(String.valueOf(result1));
		jlb2.setText(" ");
		result2 = 0.0;
	}
	
	private void get10x() {							//10^x
		result2 = Double.parseDouble(strresult1);	//����������󽫸����ִ浽����Ĵ�����
		result3 = result2;								
		firstnum = true;								//��һ������Ϊ�µ�����
		result1 = Math.pow(10,result2);					/****����*****/
		jtxt1.setText(String.valueOf(result1));
		result2 = 0.0;
	}
	
	private void bin() {
		result2 = Integer.parseInt(strresult1);	//����������󽫸����ִ浽����Ĵ�����	
		firstnum = true;						//��һ������Ϊ�µ�����
		jtxt1.setText(String.valueOf(Integer.toBinaryString((int)result2)));
		result2 = 0.0;
	} 
	
	
	private void hex() {
		result2 = Integer.parseInt(strresult1);	//����������󽫸����ִ浽����Ĵ�����	
		firstnum = true;						//��һ������Ϊ�µ�����
		jtxt1.setText(String.valueOf(Integer.toHexString((int)result2)));
		result2 = 0.0;
	}

	
	private void log() {
		result2 = Double.parseDouble(strresult1);	//����������󽫸����ִ浽����Ĵ�����	
		firstnum = true;								//��һ������Ϊ�µ�����
		result1 = log(result2,10);					/****����*****/
		result3 = result1;
		jtxt1.setText(String.valueOf(result1));
		result2 = 0.0;
		operator = 'l';
	}
	
	private double log(double value,double base) {			//log���㺯��
		return Math.log(value)/Math.log(base);
	}
	
	private void exp() {									//exp����
		if (jlb2.getText().equals(" ") && result2 == 0.0)	//�ж��Ƿ��ǵ�һ������						
		{
			result1 = Double.parseDouble(strresult1);
			jlb2.setText(strresult1+"E");
			firstnum = true;
			operator = 'E';
		}else {
			result2 = firstnum?result3:Double.parseDouble(strresult1);	//����������󽫸����ִ浽����Ĵ�����
			result3 = result2;								//����result2��������
			firstnum = true;								//��һ������Ϊ�µ�����
			result1 = result1 * Math.pow(10,result2);					/****����*****/
			jlb2.setText(result1+"E");		//��������������м�¼
			result2 = 0.0;
			operator = 'E';
		}
	}
	
	private void mod() {								//mod ��������
		if (operator == '%' && strresult1.equals("0"))		//�жϱ������Ƿ���0					
		{
			jtxt1.setText("�޷�����");
			firstnum = true;
			operator = '%';
			System.out.println("�����Ǳ�������0");
		}else {
			if (result2 == 0.0 && operator == '=')	 //�ж��Ƿ��Ǳ�������
			{
				result1 = Double.parseDouble(strresult1);
				result1 = result1 % (result1*10);
				jlb2.setText(result1+" "+sfsbtn[4].getText());
				firstnum = true;	
				operator = '%';
				System.out.println("�������ж��Ƿ��ǳ���");
			}else if (operator == '%'){
				result2 = firstnum?result3:Double.parseDouble(strresult1);	//����������󽫸����ִ浽����Ĵ�����
				result3 = result2;								//����result2����������
				result1 = result1 % result2;					/****����*****/
				jlb2.setText(result1+" "+sfsbtn[4].getText());		//��������������м�¼
				firstnum = true;								//��һ������Ϊ�µ�����
				result2 = 0.0;
				operator = '%';
				System.out.println("����������");
			}
		}
	}
	
	
	private void ln() {								//ln��������
		result2 = Double.parseDouble(strresult1);	//����������󽫸����ִ浽����Ĵ�����
		result3 = result2;	
		if (result2 >= 0) {
			firstnum = true;				//��һ������Ϊ�µ�����
			result1 =Math.log(result2);		/****����*****/
			jtxt1.setText(String.valueOf(result1));
			result2 = 0.0;
			operator = '^';
		}
	}
	
	private void fact() {							//����׳�
		result2 = Double.parseDouble(strresult1);	//����������󽫸����ִ浽����Ĵ�����
		result3 = result2;								
		firstnum = true;								//��һ������Ϊ�µ�����
		result1 = 1;							/****����*****/
		for (int i = 1 ; i <= (int)result2 ; i++)
			result1 *= i;
		jtxt1.setText(String.valueOf(result1));
		result2 = 0.0;
	}
	
	private void getPi() {							//���PI
		firstnum = false;
		jtxt1.setText(String.valueOf(Math.PI));
	}
	
	private void reverst() {						//ȡ����ֵ
		result2 = Double.parseDouble(strresult1);	//����������󽫸����ִ浽����Ĵ�����
		result3 = result2;
		result1 = result2 < 0 ? Math.abs(result2):0-result2;
		firstnum = true;								//��һ������Ϊ�µ�����
		jtxt1.setText(String.valueOf(result1));
		result2 = 0.0;
		operator = '��';
	}
	
	private void point(){
		if (jtxt1.getText().indexOf(".") < 0) {		// �ж�֮ǰ����С����
			jtxt1.setText(jtxt1.getText() + ".");
		}
		if (firstnum)								//��ֹ0.xx�����
		{
			jtxt1.setText("0.");
			firstnum = false;						//��ȫС��
		}
	}
	
	private void mr() {									//MR��
		M = Double.parseDouble(strresult1);
		jtxt1.setText(strresult1);
		firstnum = true;
	}
	
	private void mc() {									//MC��
		M = 0.0;
	}
	
	private void mplus() {								//M+��
		jlb2.setText(" ");
		jtxt1.setText(String.valueOf(M+Double.parseDouble(strresult1)));
	}
	
	private void msub() {								//M-��
		jlb2.setText(" ");
		jtxt1.setText(String.valueOf(Double.parseDouble(strresult1)-M));
	}
	
	
	private void backspace() {							//�˸��
		if (strresult1.length() > 1 /*&& operator != '='**��������а��µȺ�*/)
		{
			strresult1 = strresult1.substring(0, strresult1.length()-1);
			jtxt1.setText(strresult1);
		}
		else 											//���ﵽ���һ�����������
		{
			jtxt1.setText("0");
			firstnum = true;
			operator = '=';
		}	
	}
	
	private void ce() {									//CE��
		jtxt1.setText("0");
		firstnum = true;
	}
	
	private void c() {									//��ռ�
		jtxt1.setText("0");
		jlb2.setText(" ");
		firstnum = true;
		operator = '=';
		result1 = result2 = 0.0;
	}
		
	
	private void result() {
		result2 = Double.parseDouble(strresult1);		//����������ź����������
		switch(operator) {
		case '+': jtxt1.setText(String.valueOf(result1+result2));break;
		case '-': jtxt1.setText(String.valueOf(result1-result2));break;
		case '*': jtxt1.setText(String.valueOf(result1*result2));break;
		case '/': result2=Double.parseDouble(strresult1);if (operator == '/' && strresult1.equals("0")){divi();break;}else {jtxt1.setText(String.valueOf(result1/result2));break;}
		case '��': jtxt1.setText(String.valueOf(result1));break;
		case '@': jtxt1.setText(String.valueOf(result1));break;
		case '^': jtxt1.setText(String.valueOf(result1));break;
		case '&': jtxt1.setText(String.valueOf(result2));break;
		case '��': jtxt1.setText(String.valueOf(result2));break;
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
	
	private void add() {								//�ӷ�
		if (jlb2.getText().equals(" ") && result2 == 0.0)	//�ж��Ƿ��ǵ�һ������
		{
			result1 = Double.parseDouble(strresult1);
			jlb2.setText(strresult1+sdsbtn[23].getText());
			firstnum = true;
			operator = '+';
		}else if (jlb2.getText().length() > 1){
			result2 = firstnum?result3:Double.parseDouble(strresult1);	//����������󽫸����ִ浽����Ĵ�����
			result3 = result2;								//����result2��������
			firstnum = true;								//��һ������Ϊ�µ�����
			result1 = result1 + result2;					/****����*****/
			jlb2.setText(result1+sdsbtn[23].getText());		//��������������м�¼
			result2 = 0.0;
			operator = '+';
		}
	} 
	
	private void sub() {								//����
		if (jlb2.getText().equals(" ") && result2 == 0.0)	//�ж��Ƿ��Ǽ���
		{
			result1 = Double.parseDouble(strresult1);
			jlb2.setText(strresult1+sdsbtn[19].getText());
			firstnum = true;
			operator = '-';
		}
		else if(jlb2.getText().length() > 1){
			result2 = firstnum?result3:Double.parseDouble(strresult1);	//����������󽫸����ִ浽����Ĵ�����
			result3 = result2;								//����result2��������
			firstnum = true;								//��һ������Ϊ�µ�����
			result1 = result1 - result2;					/****����*****/
			jlb2.setText(result1+sdsbtn[19].getText());		//��������������м�¼
			result2 = 0.0;
			operator = '-';
		}
	}
	
	
	private void multi() {									//�˷�
		if (jlb2.getText().equals(" ") && result2 == 0.0)	//�ж��Ƿ��ǵ�һ������						
		{
			result1 = Double.parseDouble(strresult1);
			jlb2.setText(strresult1+sdsbtn[15].getText());
			firstnum = true;
			operator = '*';
		}else {
			result2 = firstnum?result3:Double.parseDouble(strresult1);	//����������󽫸����ִ浽����Ĵ�����
			result3 = result2;								//����result2��������
			firstnum = true;								//��һ������Ϊ�µ�����
			result1 = result1 * result2;					/****����*****/
			jlb2.setText(result1+sdsbtn[15].getText());		//��������������м�¼
			result2 = 0.0;
			operator = '*';
		}
	}
	
	
	private void division() {								//����
		if (operator == '/' && strresult1.equals("0"))		//�жϱ������Ƿ���0					
		{
			jtxt1.setText("�Ƿ���������");
			firstnum = true;
			operator = '/';									//���ڱ�������
		}else {
			if (result2 == 0.0 && operator == '=')	 		//�ж��Ƿ��ǳ���
			{
				result1 = Double.parseDouble(strresult1);
				jlb2.setText(result1+sdsbtn[11].getText());
				firstnum = true;	
				operator = '/';
			}else if (operator == '/'){
				result2 = firstnum?result3:Double.parseDouble(strresult1);//����������󱣴�����
				result3 = result2;								//����result2��������
				result1 = result1 / result2;					/****����*****/
				jlb2.setText(result1+sdsbtn[11].getText());		//��������������м�¼
				firstnum = true;								//��һ������Ϊ�µ�����
				result2 = 0.0;
				operator = '/';
			}
		}
		
	}
	
	private void divi() {
		jtxt1.setText("�Ƿ���������");
		firstnum = true;
		operator = '/';
		System.out.println("�����Ǳ�������0");
	}
	
	private void percent() {							//�ٷֺ�����
		result2 = Double.parseDouble(strresult1);	//����������󽫸����ִ浽����Ĵ�����
		result3 = result2;								
		firstnum = true;								//��һ������Ϊ�µ�����
		result1 = result2 / 100;					/****����*****/
		jtxt1.setText(String.valueOf(result1));
		result2 = 0.0;
	}
	
	
	private void sroot() {								//��������
		result2 =Double.parseDouble(strresult1);	//����������󽫸����ִ浽����Ĵ�����
		result3 = result2;								
		firstnum = true;								//��һ������Ϊ�µ�����
		result1 = Math.sqrt(result2);					/****����*****/
		jtxt1.setText(String.valueOf(result1));
		result2 = 0.0;
		operator = '��';
	}
	
	private void square() {								//ƽ������
		result2 = Double.parseDouble(strresult1);		//����������󽫸����ִ浽����Ĵ�����
		result3 = result2;	
		firstnum = true;								//��һ������Ϊ�µ�����
		result1 = Math.pow(result2,2);					/****����*****/
		jtxt1.setText(String.valueOf(result1));
		result2 = 0.0;
		operator = '^';
	}

	
	private void btnnum() {								//1/x����
		result2 = Double.parseDouble(strresult2);		//����������󽫸����ִ浽����Ĵ�����
		result3 = result2;	
		if (result2 !=0) {
			result1 = (1/result2);                 /****����*****/
			jtxt1.setText(String.valueOf(result1));
			result2 = 0.0;
			operator = '&';
			firstnum = true;								//��һ������Ϊ�µ�����
		}else jtxt1.setText("�޷�����"); 
	}
	
	
	
	private void length() {				//�����ı�����
		jtxt1.requestFocus();			//�������Ƶ��ı���
		try{
		new Robot().keyPress(KeyEvent.VK_ENTER);//�����س��������ı��򳤶ȼ���
		}catch(Exception e){
		}
	}


	public static void main(String args[]){	
			new Calculator();
	}
}