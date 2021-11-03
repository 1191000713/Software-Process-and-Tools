package application;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Calcuator extends JFrame implements ActionListener {
    TextArea text = new TextArea("", 2, 10, 2);
    JPanel CardPanel = new JPanel();
    CardLayout cards = new CardLayout();
    JButton record = new JButton("历史记录");
    JButton mermory = new JButton("  内存  ");
    String ReSetrecordArea = "历史记录\n三角函数默认以角度制进行计算";
    TextArea recordArea;
    TextArea mermoryArea;
    String mathPast;
    String mathNow;
    String showNow;
    private String m;
    private String result;
    String temp;
    private int equal_flag;
    private int TrigCalculMethod;
    ImageIcon squareimg;
    ImageIcon cubeimg;
    ImageIcon powerimg;
    ImageIcon readicationimg;
    ImageIcon walkpaper;
    Image squareimage;
    Image cubeimage;
    Image powerimage;
    Image readicationimage;
    Image walkpaperimage;
    Image squaresmallImage;
    Image cubesmallImage;
    Image powersmallImage;
    Image readictionsmallImage;
    ImageIcon smallIcon;
    ImageIcon cmallIcon;
    ImageIcon pmallIcon;
    ImageIcon rmallIcon;
    JButton squarebuttion;
    JButton cubebuttion;
    JButton powerbuttion;
    JButton readictionbuttion;

    public Calcuator() {
        super("计算器");
        this.recordArea = new TextArea(this.ReSetrecordArea, 37, 26);
        this.mermoryArea = new TextArea("内存", 37, 26);
        this.mathPast = "";
        this.mathNow = "";
        this.showNow = "";
        this.result = "";
        this.temp = "";
        this.equal_flag = 0;
        this.TrigCalculMethod = 0;
        this.squareimg = new ImageIcon("src\\Calcuator\\x的平方.png");
        this.cubeimg = new ImageIcon("src\\Calcuator\\x的三次方.png");
        this.powerimg = new ImageIcon("src\\Calcuator\\x的y次方.png");
        this.readicationimg = new ImageIcon("src\\Calcuator\\x开y次方.png");
        this.walkpaper = new ImageIcon("src\\Calcuator\\walkpaper.jpeg");
        this.squareimage = this.squareimg.getImage();
        this.cubeimage = this.cubeimg.getImage();
        this.powerimage = this.powerimg.getImage();
        this.readicationimage = this.readicationimg.getImage();
        this.walkpaperimage = this.walkpaper.getImage();
        this.squaresmallImage = this.squareimage.getScaledInstance(25, 25, 2);
        this.cubesmallImage = this.cubeimage.getScaledInstance(25, 25, 2);
        this.powersmallImage = this.powerimage.getScaledInstance(25, 25, 2);
        this.readictionsmallImage = this.readicationimage.getScaledInstance(30, 30, 2);
        this.smallIcon = new ImageIcon(this.squaresmallImage);
        this.cmallIcon = new ImageIcon(this.cubesmallImage);
        this.pmallIcon = new ImageIcon(this.powersmallImage);
        this.rmallIcon = new ImageIcon(this.readictionsmallImage);
        this.squarebuttion = new JButton(this.smallIcon);
        this.cubebuttion = new JButton(this.cmallIcon);
        this.powerbuttion = new JButton(this.pmallIcon);
        this.readictionbuttion = new JButton(this.rmallIcon);
        this.setBounds(400, 250, 900, 680);
        this.setDefaultCloseOperation(3);
        GridBagLayout gr = new GridBagLayout();
        GridBagConstraints gc = new GridBagConstraints();
        this.setLayout(gr);
        JPanel jp1 = this.createPanel1();
        JPanel jp2 = this.createPanel2();
        gc.weightx = 90.0D;
        gc.weighty = 68.0D;
        gc.fill = 1;
        gc.gridwidth = 7;
        gc.gridheight = 10;
        gc.gridx = 0;
        gc.gridy = 0;
        gr.setConstraints(jp1, gc);
        this.add(jp1);
        gc.gridwidth = 3;
        gc.gridheight = 10;
        gc.gridx = 7;
        gc.gridy = 0;
        gr.setConstraints(jp2, gc);
        this.add(jp2);
        this.setVisible(true);
    }

    private JPanel createPanel1() {
        JPanel panel = new JPanel();
        JPanel keyPanel = new JPanel();
        panel.setLayout(new BorderLayout());
        keyPanel.setLayout(new GridLayout(6, 7, 2, 2));
        Calcuator.ButtonAction ba = new Calcuator.ButtonAction();
        final JButton[][] button = new JButton[][]{{new JButton("("), new JButton(")"), new JButton("1/X"), new JButton("mc"), new JButton("m+"), new JButton("m-"), new JButton("mr")}, {this.squarebuttion, this.cubebuttion, this.powerbuttion, new JButton("C"), new JButton("/"), new JButton("*"), new JButton("ms")}, {new JButton("X!"), new JButton("√"), this.readictionbuttion, new JButton("7"), new JButton("8"), new JButton("9"), new JButton("←")}, {new JButton("e"), new JButton("ln"), new JButton("log"), new JButton("4"), new JButton("5"), new JButton("6"), new JButton("-")}, {new JButton("sin"), new JButton("cos"), new JButton("tan"), new JButton("1"), new JButton("2"), new JButton("3"), new JButton("+")}, {new JButton("Deg"), new JButton("|x|"), new JButton("π"), new JButton("%"), new JButton("0"), new JButton("."), new JButton("=")}};
        Font font = new Font("仿宋", 0, 56);
        this.text.setFont(font);
        this.text.setEditable(false);
        panel.setLayout(new BorderLayout());
        Font keyfont = new Font("Dialog", 1, 14);

        int i;
        int j;
        for(i = 0; i < 6; ++i) {
            for(j = 0; j < 7; ++j) {
                if (j < 3) {
                    button[i][j].setFont(keyfont);
                }

                keyPanel.add(button[i][j]);
            }
        }

        panel.add("North", this.text);
        panel.add("Center", keyPanel);

        for(i = 0; i < 6; ++i) {
            for(j = 0; j < 7; ++j) {
                button[i][j].addActionListener(ba);
            }
        }

        button[5][0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = e.getActionCommand();
                if (input.equals("Deg")) {
                    button[5][0].setText("Rad");
                    Calcuator.this.TrigCalculMethod = 1;
                } else if (input.equals("Rad")) {
                    button[5][0].setText("Deg");
                    Calcuator.this.TrigCalculMethod = 0;
                }

            }
        });
        return panel;
    }

    private JPanel createPanel2() {
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        this.CardPanel.setBackground(Color.pink);
        panel1.setLayout(new BorderLayout());
        panel2.add(this.record);
        panel2.add(this.mermory);
        panel1.add("North", panel2);
        panel1.add("Center", this.CardPanel);
        Font font1 = new Font("Verdana", 0, 12);
        this.recordArea.setFont(font1);
        this.mermoryArea.setFont(font1);
        this.recordArea.setEditable(false);
        this.mermoryArea.setEditable(false);
        this.CardPanel.setLayout(this.cards);
        this.recordArea.setBackground(Color.gray);
        this.mermoryArea.setBackground(Color.gray);
        this.CardPanel.add(this.recordArea, "card1");
        this.CardPanel.add(this.mermoryArea, "card2");
        this.record.addActionListener(this);
        this.mermory.addActionListener(this);
        return panel1;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.record) {
            this.cards.first(this.CardPanel);
        }

        if (e.getSource() == this.mermory) {
            this.cards.last(this.CardPanel);
        }

    }

    private void ferror() {
        int i;
        if (this.mathNow.length() < this.text.getColumns()) {
            this.text.append("\n");

            for(i = 0; i < this.text.getColumns() - 5; ++i) {
                this.text.append("      ");
            }

            this.text.append("error");
        } else if (this.mathNow.length() > this.text.getColumns()) {
            this.text.append("\n");

            for(i = 0; i < this.mathNow.length() - 4; ++i) {
                this.text.append("  ");
            }

            this.text.append("error");
        }

    }

    private void fms() {
        this.m = this.result;
        this.mermoryArea.append("m=" + this.m + "\n");
    }

    private void fmr() {
        this.mathNow = this.m;
        this.text.setText(this.mathNow);
    }

    private void fmadd() {
        this.m = String.valueOf(Double.parseDouble(this.m) + Double.parseDouble(this.result));
        this.mermoryArea.append("m=" + this.m + "\n");
    }

    private void fmdecreae() {
        this.m = String.valueOf(Double.parseDouble(this.m) - Double.parseDouble(this.result));
        this.mermoryArea.append("m=" + this.m);
    }

    private void fmc() {
        this.m = "";
        this.mermoryArea.setText("已刷新1\n");
    }

    private void fc() {
        this.equal_flag = 0;
        this.mathNow = "";
        this.mathPast = "";
        this.showNow = "";
        this.text.setText("");
        this.result = "";
        this.recordArea.setText(this.ReSetrecordArea);
        this.mermoryArea.setText("内存");
    }

    private void fbtn_del() {
        if (this.mathNow.length() != 0) {
            this.mathNow = this.mathNow.substring(0, this.mathNow.length() - 1);
            this.showNow = this.showNow.substring(0, this.showNow.length() - 1);
            this.text.setText(this.showNow);
        }

    }

    private void feual() {
        if (this.equal_flag == 1) {
            this.text.append("");
        } else {
            this.mathNow = this.fparentthesis(this.mathNow);
            this.showNow = this.fparentthesis(this.showNow);
            this.mathNow = this.fDeltePare(this.mathNow);
            this.showNow = this.fDeltePare(this.showNow);
            this.mathPast = this.mathNow;
            this.mathNow = "";
            ScienCalcuator sr = new ScienCalcuator();
            sr.angle_metric = this.TrigCalculMethod;
            this.result = sr.fScienceCalcuator(this.mathPast);
            if (!this.result.equals("null")) {
                this.fVisResuable(this.showNow);
            }

            this.recordArea.append("\n" + this.showNow + "=" + this.result);
            this.equal_flag = 1;
        }

    }

    private boolean isChar(char con_char) {
        return con_char == '+' || con_char == '-' || con_char == '*' || con_char == '/' || con_char == 215 || con_char == 247;
    }

    void fVisResuable(String con_str) {
        if (con_str.length() < 18) {
            this.text.append("\n");
            this.text.append("=" + this.result);
        } else {
            this.text.append("\n");

            for(int i = 0; i < con_str.length() - this.result.length(); ++i) {
                this.text.append("  ");
            }

            this.text.append("=" + this.result);
            PrintStream var10000 = System.out;
            int var10001 = this.text.getColumns();
            var10000.println(var10001 + "\n" + con_str.length());
        }

    }

    private boolean isNum(char con_char) {
        return con_char == '0' || con_char == '1' || con_char == '2' || con_char == '3' || con_char == '4' || con_char == '5' || con_char == '6' || con_char == '7' || con_char == '8' || con_char == '9';
    }

    private boolean hasPoint(String con_str) {
        return con_str.indexOf(46) >= 0;
    }

    private String fDeltePare(String con_str) {
        for(int i = 0; i < con_str.length(); ++i) {
            int Index = 1;
            if (con_str.charAt(i) == '(') {
                String mind_str;
                String temp_str;
                int j;
                int LeftBrackSum;
                int RightBrackSum;
                if (i == 0) {
                    j = i + 1;
                    LeftBrackSum = 1;

                    for(RightBrackSum = 0; LeftBrackSum != RightBrackSum; ++j) {
                        if (con_str.charAt(j) == '(') {
                            ++LeftBrackSum;
                        }

                        if (con_str.charAt(j) == ')') {
                            ++RightBrackSum;
                        }
                    }

                    mind_str = con_str.substring(i + 1, j - 1);
                    temp_str = "(" + mind_str + ")";
                    if (mind_str.contains("(")) {
                        mind_str = this.fDeltePare(mind_str);
                    }

                    if (!mind_str.contains("(") && !mind_str.contains("+") && !mind_str.contains("-") && !mind_str.contains("*") && !mind_str.contains("/") && !mind_str.contains("%")) {
                        con_str = con_str.replace(temp_str, mind_str);
                    }
                } else if (i != 0 && this.isChar(con_str.charAt(i - 1))) {
                    j = i + 1;
                    LeftBrackSum = 1;

                    for(RightBrackSum = 0; LeftBrackSum != RightBrackSum; ++j) {
                        if (con_str.charAt(j) == '(') {
                            ++LeftBrackSum;
                        }

                        if (con_str.charAt(j) == ')') {
                            ++RightBrackSum;
                        }
                    }

                    mind_str = con_str.substring(i + 1, j - 1);
                    temp_str = "(" + mind_str + ")";
                    if (mind_str.contains("(")) {
                        mind_str = this.fDeltePare(mind_str);
                    }

                    if (!mind_str.contains("(") && !mind_str.contains("+") && !mind_str.contains("-") && !mind_str.contains("*") && !mind_str.contains("/") && !mind_str.contains("%")) {
                        con_str = con_str.replace(temp_str, mind_str);
                    }
                }
            }
        }

        return con_str;
    }

    private String fparentthesis(String con_mathnow) {
        int leftNum = 0;
        int rightNum = 0;

        int i;
        for(i = 0; i < con_mathnow.length(); ++i) {
            if (con_mathnow.charAt(i) == '(') {
                ++leftNum;
            }

            if (con_mathnow.charAt(i) == ')') {
                ++rightNum;
            }
        }

        for(i = leftNum - rightNum; i > 0; --i) {
            con_mathnow = con_mathnow + ")";
        }

        return con_mathnow;
    }

    public static void main(String[] args) {
        new Calcuator();
    }

    private class ButtonAction implements ActionListener {
        private ButtonAction() {
        }

        public void actionPerformed(ActionEvent e) {
            String input = e.getActionCommand();
            Calcuator var10000;
            if (input.equals("0") || input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4") || input.equals("5") || input.equals("6") || input.equals("7") || input.equals("8") || input.equals("9") || input.equals(".") || input.equals("(") || input.equals(")") || input.equals("e") || input.equals("π") || input.equals("%")) {
                if (Calcuator.this.equal_flag == 1) {
                    if (input.equals(".")) {
                        Calcuator.this.mathNow = "0" + input;
                        Calcuator.this.showNow = "0" + input;
                        Calcuator.this.temp = "0.";
                    } else {
                        if (!input.equals("%")) {
                            Calcuator.this.mathNow = input;
                            Calcuator.this.showNow = input;
                            Calcuator.this.temp = input;
                        }

                        if (input.equals("%")) {
                            Calcuator.this.mathNow = Calcuator.this.result + "*0.01";
                            Calcuator.this.showNow = Calcuator.this.result + "%";
                            Calcuator.this.temp = input;
                        }
                    }

                    Calcuator.this.mathPast = "";
                    Calcuator.this.result = "";
                    Calcuator.this.text.setText(Calcuator.this.showNow);
                    Calcuator.this.equal_flag = 0;
                } else {
                    if (!input.equals(".")) {
                        if (!input.equals("e") && !input.equals("π") && !input.equals("(") && !input.equals(")")) {
                            var10000 = Calcuator.this;
                            var10000.temp = var10000.temp + input;
                        }

                        if (input.equals("%")) {
                            if (Calcuator.this.mathNow.equals("")) {
                                Calcuator.this.mathNow = "";
                                Calcuator.this.showNow = "";
                            }

                            if (!Calcuator.this.isChar(Calcuator.this.mathNow.charAt(Calcuator.this.mathNow.length() - 1))) {
                                var10000 = Calcuator.this;
                                var10000.mathNow = var10000.mathNow + "*0.01";
                                var10000 = Calcuator.this;
                                var10000.showNow = var10000.showNow + "%";
                            }
                        } else {
                            var10000 = Calcuator.this;
                            var10000.mathNow = var10000.mathNow + input;
                            var10000 = Calcuator.this;
                            var10000.showNow = var10000.showNow + input;
                        }
                    } else {
                        if (Calcuator.this.mathNow.length() == 0) {
                            var10000 = Calcuator.this;
                            var10000.mathNow = var10000.mathNow + "0.";
                            var10000 = Calcuator.this;
                            var10000.showNow = var10000.showNow + "0.";
                        } else if (Calcuator.this.isChar(Calcuator.this.mathNow.charAt(Calcuator.this.mathNow.length() - 1))) {
                            var10000 = Calcuator.this;
                            var10000.mathNow = var10000.mathNow + "0.";
                            var10000 = Calcuator.this;
                            var10000.showNow = var10000.showNow + "0.";
                        } else if (Calcuator.this.isNum(Calcuator.this.mathNow.charAt(Calcuator.this.mathNow.length() - 1)) && !Calcuator.this.hasPoint(Calcuator.this.temp)) {
                            var10000 = Calcuator.this;
                            var10000.mathNow = var10000.mathNow + ".";
                            var10000 = Calcuator.this;
                            var10000.showNow = var10000.showNow + ".";
                        }
//                        } else {
//                            var10000 = Calcuator.this;
//                            var10000.mathNow = var10000.mathNow.makeConcatWithConstants<invokedynamic>(var10000.mathNow);
//                            var10000 = Calcuator.this;
//                            var10000.showNow = var10000.showNow.makeConcatWithConstants<invokedynamic>(var10000.showNow);
//                        }

                        var10000 = Calcuator.this;
                        var10000.temp = var10000.temp + input;
                    }

                    if (input.equals(".") && Calcuator.this.hasPoint(Calcuator.this.temp)) {
                        Calcuator.this.text.setText(Calcuator.this.showNow);
                    } else {
                        Calcuator.this.text.append(input);
                    }
                }
            }

            if (input.equals("+") || input.equals("-") || input.equals("*") || input.equals("/")) {
                Calcuator.this.temp = "";
                if (Calcuator.this.equal_flag == 1) {
                    Calcuator.this.mathNow = Calcuator.this.result + input;
                    Calcuator.this.showNow = Calcuator.this.result + input;
                    Calcuator.this.mathPast = "";
                    Calcuator.this.result = "";
                    Calcuator.this.text.setText(Calcuator.this.showNow);
                    Calcuator.this.equal_flag = 0;
                } else {
                    if (Calcuator.this.mathNow.equals("") && input.equals("-")) {
                        var10000 = Calcuator.this;
                        var10000.mathNow = var10000.mathNow + input;
                        var10000 = Calcuator.this;
                        var10000.showNow = var10000.showNow + input;
                    } else if (!Calcuator.this.mathNow.equals("")) {
                        if (Calcuator.this.mathNow.charAt(Calcuator.this.mathNow.length() - 1) == '(' && input.equals("-")) {
                            var10000 = Calcuator.this;
                            var10000.showNow = var10000.showNow + input;
                            var10000 = Calcuator.this;
                            var10000.mathNow = var10000.mathNow + input;
                            Calcuator.this.text.append(input);
                        }

                        if (Calcuator.this.isNum(Calcuator.this.mathNow.charAt(Calcuator.this.mathNow.length() - 1)) || Calcuator.this.mathNow.charAt(Calcuator.this.mathNow.length() - 1) == '!' || Calcuator.this.mathNow.charAt(Calcuator.this.mathNow.length() - 1) == ')' || Calcuator.this.mathNow.charAt(Calcuator.this.mathNow.length() - 1) == 960 || Calcuator.this.mathNow.charAt(Calcuator.this.mathNow.length() - 1) == 'e' || Calcuator.this.mathNow.charAt(Calcuator.this.mathNow.length() - 1) == '%') {
                            var10000 = Calcuator.this;
                            var10000.mathNow = var10000.mathNow + input;
                        }

                        var10000 = Calcuator.this;
                        var10000.showNow = var10000.showNow + input;
                    }

                    if (Calcuator.this.result.equals("")) {
                        if (Calcuator.this.mathNow.equals("-") && input.equals("-")) {
                            Calcuator.this.text.setText("-");
                        } else if (!Calcuator.this.isChar(Calcuator.this.text.getText().charAt(Calcuator.this.text.getText().length() - 1)) && Calcuator.this.text.getText().charAt(Calcuator.this.text.getText().length() - 1) != '(') {
                            Calcuator.this.text.append(input);
                        }
                    }
                }
            }

            if (input.equals("1/X") || e.getSource() == Calcuator.this.squarebuttion || e.getSource() == Calcuator.this.cubebuttion || e.getSource() == Calcuator.this.powerbuttion || input.equals("X!") || input.equals("√") || e.getSource() == Calcuator.this.readictionbuttion || input.equals("ln") || input.equals("log") || input.equals("sin") || input.equals("cos") || input.equals("tan") || input.equals("|x|")) {
                Calcuator.this.temp = "";
                if (Calcuator.this.equal_flag == 1) {
                    if (input.equals("|x|")) {
                        Calcuator.this.mathNow = "abs(";
                        Calcuator.this.showNow = "abs(";
                    } else if (!input.equals("1/X") && !input.equals("√") && !input.equals("X!") && e.getSource() != Calcuator.this.squarebuttion && e.getSource() != Calcuator.this.cubebuttion && e.getSource() != Calcuator.this.powerbuttion && e.getSource() != Calcuator.this.readictionbuttion) {
                        Calcuator.this.mathNow = input + "(";
                        Calcuator.this.showNow = input + "(";
                        Calcuator.this.mathPast = "";
                    } else {
                        Calcuator.this.mathNow = "";
                        Calcuator.this.showNow = "";
                    }

                    Calcuator.this.result = "";
                    Calcuator.this.text.setText(Calcuator.this.showNow);
                    Calcuator.this.equal_flag = 0;
                } else if (input.equals("1/X") && (Calcuator.this.isNum(Calcuator.this.mathNow.charAt(Calcuator.this.mathNow.length() - 1)) || Calcuator.this.mathNow.charAt(Calcuator.this.mathNow.length() - 1) == ')')) {
                    var10000 = Calcuator.this;
                    var10000.mathNow = var10000.mathNow + "^(-1)";
                    var10000 = Calcuator.this;
                    var10000.showNow = var10000.showNow + "^(-1)";
                    Calcuator.this.text.append("^(-1)");
                } else if (e.getSource() == Calcuator.this.squarebuttion && (Calcuator.this.isNum(Calcuator.this.mathNow.charAt(Calcuator.this.mathNow.length() - 1)) || Calcuator.this.mathNow.charAt(Calcuator.this.mathNow.length() - 1) == ')')) {
                    var10000 = Calcuator.this;
                    var10000.mathNow = var10000.mathNow + "^(2)";
                    var10000 = Calcuator.this;
                    var10000.showNow = var10000.showNow + "^(2)";
                    Calcuator.this.text.append("^(2)");
                } else if (e.getSource() != Calcuator.this.cubebuttion || !Calcuator.this.isNum(Calcuator.this.mathNow.charAt(Calcuator.this.mathNow.length() - 1)) && Calcuator.this.mathNow.charAt(Calcuator.this.mathNow.length() - 1) != ')') {
                    if (e.getSource() == Calcuator.this.powerbuttion && (Calcuator.this.isNum(Calcuator.this.mathNow.charAt(Calcuator.this.mathNow.length() - 1)) || Calcuator.this.mathNow.charAt(Calcuator.this.mathNow.length() - 1) == ')')) {
                        var10000 = Calcuator.this;
                        var10000.mathNow = var10000.mathNow + "^(";
                        var10000 = Calcuator.this;
                        var10000.showNow = var10000.showNow + "^(";
                        Calcuator.this.text.append("^(");
                    } else if (input.equals("X!") && (Calcuator.this.isNum(Calcuator.this.mathNow.charAt(Calcuator.this.mathNow.length() - 1)) || Calcuator.this.mathNow.charAt(Calcuator.this.mathNow.length() - 1) == ')')) {
                        var10000 = Calcuator.this;
                        var10000.mathNow = var10000.mathNow + "!";
                        var10000 = Calcuator.this;
                        var10000.showNow = var10000.showNow + "!";
                        Calcuator.this.text.append("!");
                    } else if (input.equals("√") && (Calcuator.this.isNum(Calcuator.this.mathNow.charAt(Calcuator.this.mathNow.length() - 1)) || Calcuator.this.mathNow.charAt(Calcuator.this.mathNow.length() - 1) == ')')) {
                        var10000 = Calcuator.this;
                        var10000.mathNow = var10000.mathNow + "^(1/2)";
                        var10000 = Calcuator.this;
                        var10000.showNow = var10000.showNow + "^(1/2";
                        Calcuator.this.text.append("^(1/2)");
                    } else if (e.getSource() != Calcuator.this.readictionbuttion || !Calcuator.this.isNum(Calcuator.this.mathNow.charAt(Calcuator.this.mathNow.length() - 1)) && Calcuator.this.mathNow.charAt(Calcuator.this.mathNow.length() - 1) != ')') {
                        if (input.equals("ln")) {
                            var10000 = Calcuator.this;
                            var10000.mathNow = var10000.mathNow + "ln(";
                            var10000 = Calcuator.this;
                            var10000.showNow = var10000.showNow + "ln(";
                            Calcuator.this.text.append("ln(");
                        } else if (input.equals("log")) {
                            var10000 = Calcuator.this;
                            var10000.mathNow = var10000.mathNow + "log(";
                            var10000 = Calcuator.this;
                            var10000.showNow = var10000.showNow + "log(";
                            Calcuator.this.text.append("log(");
                        } else if (input.equals("sin")) {
                            var10000 = Calcuator.this;
                            var10000.mathNow = var10000.mathNow + "sin(";
                            var10000 = Calcuator.this;
                            var10000.showNow = var10000.showNow + "sin(";
                            Calcuator.this.text.append("sin(");
                        } else if (input.equals("cos")) {
                            var10000 = Calcuator.this;
                            var10000.mathNow = var10000.mathNow + "cos(";
                            var10000 = Calcuator.this;
                            var10000.showNow = var10000.showNow + "cos(";
                            Calcuator.this.text.append("cos(");
                        } else if (input.equals("tan")) {
                            var10000 = Calcuator.this;
                            var10000.mathNow = var10000.mathNow + "tan(";
                            var10000 = Calcuator.this;
                            var10000.showNow = var10000.showNow + "tan(";
                            Calcuator.this.text.append("tan(");
                        } else if (input.equals("|x|")) {
                            var10000 = Calcuator.this;
                            var10000.mathNow = var10000.mathNow + "abs(";
                            var10000 = Calcuator.this;
                            var10000.showNow = var10000.showNow + "abs(";
                            Calcuator.this.text.append("abs(");
                        }
                    } else {
                        var10000 = Calcuator.this;
                        var10000.mathNow = var10000.mathNow + "^(1/";
                        var10000 = Calcuator.this;
                        var10000.showNow = var10000.showNow + "^(1/";
                        Calcuator.this.text.append("^(1/");
                    }
                } else {
                    var10000 = Calcuator.this;
                    var10000.mathNow = var10000.mathNow + "^(3)";
                    var10000 = Calcuator.this;
                    var10000.showNow = var10000.showNow + "^(3)";
                    Calcuator.this.text.append("^(3)");
                }
            }

            if (input.equals("mc") || input.equals("m+") || input.equals("m-") || input.equals("mr") || input.equals("ms") || input.equals("←") || input.equals("=") || input.equals("deg") || input.equals("C")) {
                if (input.equals("C")) {
                    Calcuator.this.fc();
                } else if (input.equals("ms")) {
                    Calcuator.this.fms();
                } else if (input.equals("mr")) {
                    Calcuator.this.fmr();
                } else if (input.equals("m+")) {
                    Calcuator.this.fmadd();
                } else if (input.equals("mc")) {
                    Calcuator.this.fmc();
                } else if (input.equals("m-")) {
                    Calcuator.this.fmdecreae();
                } else if (input.equals("←")) {
                    Calcuator.this.fbtn_del();
                } else if (input.equals("=")) {
                    Calcuator.this.feual();
                }
            }

        }
    }
}