package application;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Calculator1 extends JPanel {
    private JButton jbtNumber0=new JButton("0");
    private JButton jbtNumber1=new JButton("1");
    private JButton jbtNumber2=new JButton("2");
    private JButton jbtNumber3=new JButton("3");
    private JButton jbtNumber4=new JButton("4");
    private JButton jbtNumber5=new JButton("5");
    private JButton jbtNumber6=new JButton("6");
    private JButton jbtNumber7=new JButton("7");
    private JButton jbtNumber8=new JButton("8");
    private JButton jbtNumber9=new JButton("9");
    private JButton jbtAdd=new JButton("+");
    private JButton jbtEqual=new JButton("=");
    private JButton jbtMinus=new JButton("-");
    private JButton jbtMultiply=new JButton("x");
    private JButton jbtDiv=new JButton("÷");
    private JButton jbtReset=new JButton("C");
    private JButton jbtDel=new JButton("DEL");
    private JButton jbtFloat=new JButton(".");
    private JLabel label1=new JLabel("请输入数据");


    public static final int MaxNumber=7;
    private double add;
    private double added;
    private double sum=0;
    private static double errorSum;//异常时保存sum的值
    private double total;
    private boolean flagAdd;
    private boolean flagMinus;
    private boolean flagMultiply;
    private boolean flagDiv;
    private boolean flagEqual;
    private JButton[] jbt={jbtNumber0,jbtNumber1,jbtNumber2,jbtNumber3,jbtNumber4,jbtNumber5,jbtNumber6,
    jbtNumber7,jbtNumber8,jbtNumber9};
    private JButton[] jbt2={jbtAdd,jbtMinus,jbtMultiply,jbtDiv};
    private String[] jbt3={"+","-","x","÷"};
    int c=0;//负责往count数组里面添加数字
    int p=0;//接受c，负责执行pow里面的次数
    double[] count=new double[7];//最多支持十万级别的数字操作

   //private Boolean[] flag={flagAdd,flagMinus,flagMultiply,flagDiv,flagEqual};

    public Calculator1(){
        JPanel panel=new JPanel();
        setLayout(null);
        ButtonListener listener=new ButtonListener();
        jbt2[0].setBounds(2,150,70,60);
		jbt2[1].setBounds(72,150,70,60);
		jbt2[2].setBounds(142,150,70,60);
		jbtDel.setBounds(212,150,70,60);
		
		jbt[7].setBounds(2,210,70,60);
		jbt[8].setBounds(72,210,70,60);
		jbt[9].setBounds(142,210,70,60);
		jbt2[3].setBounds(212,210,70,60);
		
		jbt[4].setBounds(2,270,70,60);
		jbt[5].setBounds(72,270,70,60);
		jbt[6].setBounds(142,270,70,60);
		jbtEqual.setBounds(212,270,70,180);
		
		jbt[1].setBounds(2,330,70,60);
		jbt[2].setBounds(72,330,70,60);
		jbt[3].setBounds(142,330,70,60);
		
		jbtReset.setBounds(2,390,70,60);
		jbt[0].setBounds(72,390,70,60);
		jbtFloat.setBounds(142,390,70,60);
		
        for (int i = 0; i < jbt.length; i++) {
        	jbt[i].addActionListener(listener);
        	add(jbt[i]);
        }
        //等号、服务、删除键额外添加
        jbtEqual.addActionListener(listener);
        jbtReset.addActionListener(listener);
        jbtDel.addActionListener(listener);
        jbtFloat.addActionListener(listener);
        for (int i = 0; i <jbt2.length ; i++) {
            jbt2[i].addActionListener(listener);
            add(jbt2[i]);
        }
        add(jbtEqual);
        add(jbtReset);
        add(jbtDel);
        add(jbtFloat);
        
        label1.setBounds(2,0,280,150);
        add(label1);
        add(panel);
    }
    class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {


            for (int i = 0; i <jbt.length; i++) {
                if(e.getSource()==jbt[i]){
                    if (flagAdd||flagMinus||flagMultiply||flagDiv){//如果是后一个数字
                        added=accumulation(added,i);
                        sum=0;
                    }

                    if (!flagAdd&!flagMinus&!flagMultiply&!flagDiv){  //如果是前一个数字
                        add=accumulation(add,i); // 
                        label1.setText(""+add);
                        sum=0;
                    }
                    if(flagAdd){
                        label1.setText(add+"+"+added);
                        //flagMinus=flagMultiply=flagDiv=false;
                    }
                    else if(flagMinus){
                        label1.setText(add+"-"+added);
                       // flagAdd=flagMultiply=flagDiv=false;
                    }
                    else if(flagMultiply){
                        label1.setText(add+"x"+added);
                        //flagMinus=flagDiv=flagAdd=false;
                    }
                    else if(flagDiv){
                        label1.setText(add+"÷"+added);
                        //flagMinus=flagMultiply=flagAdd=false;

                    }
                    break;
                }
            }

            if(e.getSource()==jbtEqual){
                if(flagAdd){
                    total=add+added;
                    add=total;
                }
                else if(flagMinus){
                    total=add-added;
                    add=total;
                }
                else if(flagMultiply){
                    total=add*added;
                    add=total;
                }
                else if(flagDiv){
                    total=add/added;
                    add=total;
                }
                label1.setText(""+total);
             }
             else if(e.getSource()==jbtReset){
                add=0;
                added=0;
                flagAdd=false;
                flagMinus=false;
                flagMultiply=false;
                flagDiv=false;
                clearCount();
                label1.setText("请输入数据:");
             }
             else if(e.getSource()==jbtAdd){ //只输入加减乘除时只设置一个
                 flagAdd=true;
                 label1.setText(add+"+");
                 flagMinus=flagMultiply=flagDiv=false;
                 clearCount();
             }
             else if(e.getSource()==jbtMinus){
                 flagMinus=true;
                 label1.setText(add+"-");
                 flagDiv=flagMultiply=flagAdd=false;
                 clearCount();
             }
             else if(e.getSource()==jbtMultiply){
                 flagMultiply=true;
                 label1.setText(add+"x");
                 flagMinus=flagDiv=flagAdd=false;
                 clearCount();
             }
            else if(e.getSource()==jbtDiv){
                flagDiv=true;
                label1.setText(add+"÷");
                flagMinus=flagMultiply=flagAdd=false;
                clearCount();
            }


        }
    }
        //负责数组清空
    void clearCount(){
        for (int i = 0; i < count.length; i++) {
            count[i]=0;
        }
        sum=0;
        c=0;
    }
    @SuppressWarnings("finally")
	//负责把按钮的数组都累加起来，超出范围会报错
    double accumulation(double add1,int i){
        try{
            add1=i;
            count[c]=add1;
            c++;
            p=c;
            double v;//存放pow后数据的临时变量
            //输入的c位数可以作为pow的权值，因为需要自减故赋给p
            if (c!=MaxNumber) {
                for (int j = 0; j < count.length; j++) {
                    v = count[j] * Math.pow(10, p - 1);
                    sum += v;
                    p--;
                }
                errorSum = sum;

                return sum;
            }
        }catch(Exception e){
            System.out.println("最大支持"+(MaxNumber-1)+"位数字操作");
        }finally{
            System.out.println(errorSum);
            return errorSum;
        }


    }

    public static void main(String[] args) {
//        Calculator1 frame=new Calculator1();
//        frame.setSize(400,200);
//        frame.setVisible(true);

    }
}