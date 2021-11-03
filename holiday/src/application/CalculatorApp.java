package application;

import javax.swing.*;
import java.awt.*;

public class CalculatorApp {

    public static void main(String[] args) {
        JFrame jf = new JFrame("计算器");
        jf.setBounds(100, 100, 304, 520);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //这个是让组件能够在按×的时候自动退出
        jf.setLocationRelativeTo(null);  //这个可以让面板显示在正中

        // 创建选项卡面板
        final JTabbedPane tabbedPane = new JTabbedPane();


//        // 创建第 1 个选项卡（选项卡只包含 标题）
//        tabbedPane.addTab("Tab01", createTextPanel("TAB 01"));
        Calculator1 normal = new Calculator1();
        Calcuator scientific = new Calcuator();
//        normal.getContentPane();
        // 创建第 2 个选项卡（选项卡包含 标题 和 图标）
        tabbedPane.addTab("普通", new ImageIcon("bb.jpg"), normal);

        // 创建第 3 个选项卡（选项卡包含 标题、图标 和 tip提示）
//        tabbedPane.addTab("科学", new ImageIcon("bb.jpg"), createTextPanel("科学"), "This is a tab.");
        tabbedPane.addTab("科学", new ImageIcon("bb.jpg"), scientific);
        

//        // 添加选项卡选中状态改变的监听器
//        tabbedPane.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                System.out.println("当前选中的选项卡: " + tabbedPane.getSelectedIndex());
//            }
//        });

        // 设置默认选中的选项卡
        tabbedPane.setSelectedIndex(0);

        jf.setContentPane(tabbedPane);
        jf.setVisible(true);
    }

    /**
     * 创建一个面板，面板中心显示一个标签，用于表示某个选项卡需要显示的内容
     */
    private static JComponent createTextPanel(String text) {
        // 创建面板, 使用一个 1 行 1 列的网格布局（为了让标签的宽高自动撑满面板）
        JPanel panel = new JPanel(new GridLayout(1, 1));

        // 创建标签
        JLabel label = new JLabel(text);
        label.setFont(new Font(null, Font.PLAIN, 50));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        // 添加标签到面板
        panel.add(label);

        return panel;
    }

}

