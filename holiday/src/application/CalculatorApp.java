package application;

import javax.swing.*;
import java.awt.*;

public class CalculatorApp {

    public static void main(String[] args) {
        JFrame jf = new JFrame("������");
        jf.setBounds(100, 100, 304, 520);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //�����������ܹ��ڰ�����ʱ���Զ��˳�
        jf.setLocationRelativeTo(null);  //��������������ʾ������

        // ����ѡ����
        final JTabbedPane tabbedPane = new JTabbedPane();


//        // ������ 1 ��ѡ���ѡ�ֻ���� ���⣩
//        tabbedPane.addTab("Tab01", createTextPanel("TAB 01"));
        Calculator1 normal = new Calculator1();
        Calcuator scientific = new Calcuator();
//        normal.getContentPane();
        // ������ 2 ��ѡ���ѡ����� ���� �� ͼ�꣩
        tabbedPane.addTab("��ͨ", new ImageIcon("bb.jpg"), normal);

        // ������ 3 ��ѡ���ѡ����� ���⡢ͼ�� �� tip��ʾ��
//        tabbedPane.addTab("��ѧ", new ImageIcon("bb.jpg"), createTextPanel("��ѧ"), "This is a tab.");
        tabbedPane.addTab("��ѧ", new ImageIcon("bb.jpg"), scientific);
        

//        // ���ѡ�ѡ��״̬�ı�ļ�����
//        tabbedPane.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                System.out.println("��ǰѡ�е�ѡ�: " + tabbedPane.getSelectedIndex());
//            }
//        });

        // ����Ĭ��ѡ�е�ѡ�
        tabbedPane.setSelectedIndex(0);

        jf.setContentPane(tabbedPane);
        jf.setVisible(true);
    }

    /**
     * ����һ����壬���������ʾһ����ǩ�����ڱ�ʾĳ��ѡ���Ҫ��ʾ������
     */
    private static JComponent createTextPanel(String text) {
        // �������, ʹ��һ�� 1 �� 1 �е����񲼾֣�Ϊ���ñ�ǩ�Ŀ���Զ�������壩
        JPanel panel = new JPanel(new GridLayout(1, 1));

        // ������ǩ
        JLabel label = new JLabel(text);
        label.setFont(new Font(null, Font.PLAIN, 50));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        // ��ӱ�ǩ�����
        panel.add(label);

        return panel;
    }

}

