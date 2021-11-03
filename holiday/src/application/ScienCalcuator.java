package application;

public class ScienCalcuator {
    public int angle_metric = 0;

    public ScienCalcuator() {
    }

    String fPower(String con_str) {
        while(con_str.contains("^")) {
            int midIndex = con_str.lastIndexOf("^");
            int leftIndex = midIndex - 1;
            double leftNum;
            String leftStr;
            if (con_str.charAt(leftIndex) == ')') {
                int i = leftIndex - 1;
                int LeftBrackSum = 0;

                for(int RightBrackSum = 1; LeftBrackSum != RightBrackSum; --i) {
                    if (con_str.charAt(i) == '(') {
                        ++LeftBrackSum;
                    }

                    if (con_str.charAt(i) == ')') {
                        ++RightBrackSum;
                    }
                }

                String subLeftMath = con_str.substring(i + 2, leftIndex);
                if (!subLeftMath.contains("sin") && !subLeftMath.contains("^") && !subLeftMath.contains("cos") && !subLeftMath.contains("tan") && !subLeftMath.contains("ln") && !subLeftMath.contains("log") && !subLeftMath.contains("!") && !subLeftMath.contains("abs")) {
                    leftNum = Double.parseDouble(this.fBaseCalcuator(subLeftMath));
                } else {
                    leftNum = Double.parseDouble(this.fScienceCalcuator(subLeftMath));
                }

                if (leftNum == 1.7976931348623157E308D) {
                    return String.valueOf(1.7976931348623157E308D);
                }

                leftStr = "(" + subLeftMath + ")";
            } else {
                while(leftIndex >= 0 && !this.isOper(con_str.charAt(leftIndex))) {
                    --leftIndex;
                }

                leftStr = con_str.substring(leftIndex + 1, midIndex);
                leftNum = Double.parseDouble(leftStr);
            }

            int rightIndex = midIndex + 1;
            double rightNum;
            String rightStr;
            if (con_str.charAt(rightIndex) == '(') {
                int i = rightIndex + 1;
                int LeftBrackSum = 1;

                for(int RightBrackSum = 0; LeftBrackSum != RightBrackSum; ++i) {
                    if (con_str.charAt(i) == '(') {
                        ++LeftBrackSum;
                    }

                    if (con_str.charAt(i) == ')') {
                        ++RightBrackSum;
                    }
                }

                String subRightMath = con_str.substring(rightIndex + 1, i - 1);
                if (!subRightMath.contains("sin") && !subRightMath.contains("cos") && !subRightMath.contains("^") && !subRightMath.contains("tan") && !subRightMath.contains("ln") && !subRightMath.contains("log") && !subRightMath.contains("!") && !subRightMath.contains("abs")) {
                    rightNum = Double.parseDouble(this.fBaseCalcuator(subRightMath));
                } else {
                    rightNum = Double.parseDouble(this.fScienceCalcuator(subRightMath));
                }

                if (rightNum == 1.7976931348623157E308D) {
                    return String.valueOf(1.7976931348623157E308D);
                }

                rightStr = "(" + subRightMath + ")";
            } else {
                while(rightIndex < con_str.length() && !this.isOper(con_str.charAt(rightIndex))) {
                    ++rightIndex;
                }

                rightStr = con_str.substring(midIndex + 1, rightIndex);
                rightNum = Double.parseDouble(rightStr);
            }

            String wholeMath = leftStr + "^" + rightStr;
            double result = Math.pow(leftNum, rightNum);
            con_str = con_str.replace(wholeMath, String.valueOf(result));
        }

        return con_str;
    }

    String fTraingle(String con_str) {
        while(con_str.contains("sin") || con_str.contains("cos") || con_str.contains("tan") || con_str.contains("ln") || con_str.contains("log") || con_str.contains("abs")) {
            String tempor = null;
            int beginIndex = 0;
            String temp = "";
            int leftIndex;
            int i;
            int LeftBrackSum;
            if (con_str.contains("sin") || con_str.contains("cos") || con_str.contains("tan") || con_str.contains("ln") || con_str.contains("log") || con_str.contains("abs")) {
                if (con_str.contains("sin")) {
                    tempor = "sin";
                    beginIndex = con_str.lastIndexOf(tempor) + 3;
                } else if (con_str.contains("cos")) {
                    tempor = "cos";
                    beginIndex = con_str.lastIndexOf(tempor) + 3;
                } else if (con_str.contains("tan")) {
                    tempor = "tan";
                    beginIndex = con_str.lastIndexOf(tempor) + 3;
                } else {
                    int RightBrackSum;
                    if (con_str.contains("ln")) {
                        tempor = "ln";
                        beginIndex = con_str.lastIndexOf(tempor) + 2;
                        if (beginIndex - 2 > 0) {
                            if (this.isNum(con_str.charAt(beginIndex - 3)) || con_str.charAt(beginIndex - 3) == '.') {
                                for(leftIndex = con_str.lastIndexOf(tempor) - 1; (this.isNum(con_str.charAt(leftIndex)) || con_str.charAt(leftIndex) == '.') && leftIndex != 0; --leftIndex) {
                                }

                                if (leftIndex > 0) {
                                    temp = con_str.substring(leftIndex + 1, con_str.lastIndexOf(tempor));
                                } else {
                                    temp = con_str.substring(leftIndex, con_str.lastIndexOf(tempor));
                                }
                            }

                            if (con_str.charAt(beginIndex - 3) == ')') {
                                leftIndex = beginIndex - 3;
                                i = leftIndex - 1;
                                LeftBrackSum = 0;

                                for(RightBrackSum = 1; LeftBrackSum != RightBrackSum; --i) {
                                    if (con_str.charAt(i) == '(') {
                                        ++LeftBrackSum;
                                    }

                                    if (con_str.charAt(i) == ')') {
                                        ++RightBrackSum;
                                    }
                                }

                                temp = con_str.substring(i + 1, leftIndex + 1);
                                System.out.println(temp + "templn");
                            }
                        }
                    } else if (con_str.contains("abs")) {
                        tempor = "abs";
                        beginIndex = con_str.lastIndexOf(tempor) + 3;
                    } else if (con_str.contains("log")) {
                        tempor = "log";
                        beginIndex = con_str.lastIndexOf(tempor) + 3;
                        if (beginIndex - 3 > 0) {
                            if (this.isNum(con_str.charAt(beginIndex - 4)) || con_str.charAt(beginIndex - 4) == '.') {
                                for(leftIndex = con_str.lastIndexOf(tempor) - 1; (this.isNum(con_str.charAt(leftIndex)) || con_str.charAt(leftIndex) == '.') && leftIndex != 0; --leftIndex) {
                                }

                                if (leftIndex > 0) {
                                    temp = con_str.substring(leftIndex + 1, con_str.lastIndexOf(tempor));
                                } else {
                                    temp = con_str.substring(leftIndex, con_str.lastIndexOf(tempor));
                                }
                            }

                            if (con_str.charAt(beginIndex - 4) == ')') {
                                leftIndex = beginIndex - 4;
                                i = leftIndex - 1;
                                LeftBrackSum = 0;

                                for(RightBrackSum = 1; LeftBrackSum != RightBrackSum; --i) {
                                    if (con_str.charAt(i) == '(') {
                                        ++LeftBrackSum;
                                    }

                                    if (con_str.charAt(i) == ')') {
                                        ++RightBrackSum;
                                    }
                                }

                                temp = con_str.substring(i + 1, leftIndex + 1);
                            }
                        }
                    }
                }
            }

            if (con_str.charAt(beginIndex) == '(') {
                leftIndex = beginIndex + 1;
                i = 1;

                for(LeftBrackSum = 0; i != LeftBrackSum; ++leftIndex) {
                    if (con_str.charAt(leftIndex) == '(') {
                        ++i;
                    }

                    if (con_str.charAt(leftIndex) == ')') {
                        ++LeftBrackSum;
                    }
                }

                String subMath = con_str.substring(beginIndex + 1, leftIndex - 1);
                double subResult;
                if (!subMath.contains("sin") && !subMath.contains("cos") && !subMath.contains("^") && !subMath.contains("tan") && !subMath.contains("ln") && !subMath.contains("log") && !subMath.contains("!") && !subMath.contains("abs")) {
                    subResult = Double.parseDouble(this.fBaseCalcuator(subMath));
                } else {
                    subResult = Double.parseDouble(this.fScienceCalcuator(subMath));
                }

                if (subResult == 1.7976931348623157E308D) {
                    return String.valueOf(1.7976931348623157E308D);
                }

                double tempResult = 0.0D;
                int DEG = 0;
                byte var16 = -1;
                switch(tempor.hashCode()) {
                case 3458:
                    if (tempor.equals("ln")) {
                        var16 = 3;
                    }
                    break;
                case 96370:
                    if (tempor.equals("abs")) {
                        var16 = 5;
                    }
                    break;
                case 98695:
                    if (tempor.equals("cos")) {
                        var16 = 1;
                    }
                    break;
                case 107332:
                    if (tempor.equals("log")) {
                        var16 = 4;
                    }
                    break;
                case 113880:
                    if (tempor.equals("sin")) {
                        var16 = 0;
                    }
                    break;
                case 114593:
                    if (tempor.equals("tan")) {
                        var16 = 2;
                    }
                }

                String tempMath;
                switch(var16) {
                case 0:
                    tempMath = "sin(" + subMath + ")";
                    if (this.angle_metric == DEG) {
                        tempResult = Math.sin(subResult / 180.0D * 3.141592653589793D);
                    } else {
                        tempResult = Math.sin(subResult);
                    }

                    con_str = con_str.replace(tempMath, String.valueOf(tempResult));
                    break;
                case 1:
                    tempMath = "cos(" + subMath + ")";
                    if (this.angle_metric == DEG) {
                        tempResult = Math.cos(subResult / 180.0D * 3.141592653589793D);
                    } else {
                        tempResult = Math.cos(subResult);
                    }

                    con_str = con_str.replace(tempMath, String.valueOf(tempResult));
                    break;
                case 2:
                    tempMath = "tan(" + subMath + ")";
                    if (this.angle_metric == DEG) {
                        if (subResult == 90.0D) {
                            con_str = "error";
                        } else {
                            tempResult = Math.tan(subResult / 180.0D * 3.141592653589793D);
                        }
                    } else if (subResult == 1.5707963267948966D) {
                        con_str = "error";
                    } else {
                        tempResult = Math.tan(subResult);
                    }

                    con_str = con_str.replace(tempMath, String.valueOf(tempResult));
                    break;
                case 3:
                    tempMath = temp + "ln(" + subMath + ")";
                    if (!temp.equals("")) {
                        temp = this.fScienceCalcuator(temp);
                        subResult = Math.pow(subResult, Double.parseDouble(temp));
                    }

                    tempResult = Math.log(subResult);
                    con_str = con_str.replace(tempMath, String.valueOf(tempResult));
                    break;
                case 4:
                    tempMath = temp + "log(" + subMath + ")";
                    if (!temp.equals("")) {
                        temp = this.fScienceCalcuator(temp);
                        subResult = Math.pow(subResult, Double.parseDouble(temp));
                    }

                    tempResult = Math.log10(subResult);
                    con_str = con_str.replace(tempMath, String.valueOf(tempResult));
                    break;
                case 5:
                    tempMath = "abs(" + subMath + ")";
                    tempResult = Math.abs(subResult);
                    con_str = con_str.replace(tempMath, String.valueOf(tempResult));
                }
            }
        }

        return con_str;
    }

    private String fBrackAddZero(String con_str) {
        for(int i = 0; i < con_str.length(); ++i) {
            if (con_str.charAt(i) == '(') {
                int j = i + 1;
                if (con_str.charAt(j) == '-') {
                    int LeftBrackSum = 1;

                    for(int RightBrackSum = 0; LeftBrackSum != RightBrackSum; ++j) {
                        if (con_str.charAt(j) == '(') {
                            ++LeftBrackSum;
                        }

                        if (con_str.charAt(j) == ')') {
                            ++RightBrackSum;
                        }
                    }

                    String mind_str = con_str.substring(i + 1, j - 1);
                    con_str = con_str.replace(mind_str, "0" + mind_str);
                }
            }
        }

        return con_str;
    }

    private String fBaseCalcuator(String con_str) {
        BaseCalcuator sc = new BaseCalcuator();
        con_str = this.fBrackAddZero(con_str);
        sc.math = con_str;
        sc.fCalcuate();
        String result = sc.restult;
        BaseCalcuator.data.clear();
        BaseCalcuator.stack.clear();
        return result;
    }

    public String fScienceCalcuator(String con_str) {
        con_str = con_str.replace(" ", "");
        con_str = con_str.replaceAll("π", String.valueOf(3.141592653589793D));

        for(con_str = con_str.replaceAll("e", String.valueOf(Math.exp(1.0D))); con_str.contains("^") || con_str.contains("sin") || con_str.contains("!") || con_str.contains("cos") || con_str.contains("tan") || con_str.contains("ln") || con_str.contains("log") || con_str.contains("abs"); con_str = this.fPower(con_str)) {
            if (con_str.contains("!")) {
                con_str = this.fpreactorial(con_str);
            }

            con_str = this.fTraingle(con_str);
        }

        return this.fBaseCalcuator(con_str);
    }

    private boolean isOper(char con_char) {
        return con_char == '+' || con_char == '-' || con_char == '*' || con_char == '/';
    }

    private boolean isNum(char con_char) {
        return con_char == '0' || con_char == '1' || con_char == '2' || con_char == '3' || con_char == '4' || con_char == '5' || con_char == '6' || con_char == '7' || con_char == '8' || con_char == '9';
    }

    private String fpreactorial(String con_str) {
        while(con_str.contains("!")) {
            int Index = con_str.lastIndexOf("!");
            int RightIndex = Index - 1;
            String PreactorialResult;
            String left_str;
            int i;
            if (con_str.charAt(RightIndex) == ')') {
                i = RightIndex - 1;
                int LeftBrackSum = 0;

                for(int RightBrackSum = 1; LeftBrackSum != RightBrackSum; --i) {
                    if (con_str.charAt(i) == '(') {
                        ++LeftBrackSum;
                    }

                    if (con_str.charAt(i) == ')') {
                        ++RightBrackSum;
                    }
                }

                String subLeftMath = con_str.substring(i + 2, RightIndex);
                System.out.println(subLeftMath + "开头处理");
                left_str = "(" + subLeftMath + ")!";
                String LeftSum;
                if (!con_str.contains("^") && !con_str.contains("sin") && !con_str.contains("cos") && !con_str.contains("tan") && !con_str.contains("!") && !con_str.contains("ln") && !con_str.contains("log")) {
                    LeftSum = this.fBaseCalcuator(subLeftMath);
                } else {
                    LeftSum = this.fScienceCalcuator(subLeftMath);
                }

                if (LeftSum == String.valueOf(1.7976931348623157E308D)) {
                    return String.valueOf(1.7976931348623157E308D);
                }

                PreactorialResult = this.factorial(LeftSum);
                con_str = con_str.replace(left_str, PreactorialResult);
            } else if (this.isNum(con_str.charAt(RightIndex))) {
                System.out.println(con_str + " " + RightIndex);
                i = RightIndex;

                String leftMath;
                for(leftMath = null; i >= 0 && !this.isOper(con_str.charAt(i)); --i) {
                }

                leftMath = con_str.substring(i + 1, Index);
                System.out.println(leftMath + "阶乘开头处理");
                left_str = leftMath + "!";
                PreactorialResult = this.factorial(leftMath);
                con_str = con_str.replace(left_str, PreactorialResult);
            }
        }

        return con_str;
    }

    private String factorial(String con_str) {
        if (!con_str.contains("^") && !con_str.contains("sin") && !con_str.contains("cos") && !con_str.contains("tan") && !con_str.contains("ln") && !con_str.contains("log") && !con_str.contains("abs")) {
            con_str = this.fBaseCalcuator(con_str);
        } else {
            con_str = this.fScienceCalcuator(con_str);
        }

        if (Double.parseDouble(con_str) % 1.0D != 0.0D) {
            return "error";
        } else if (con_str.equals("0")) {
            return "1";
        } else {
            double sum = 1.0D;

            for(int i = 1; (double)i <= Double.parseDouble(con_str); ++i) {
                sum *= (double)i;
            }

            return String.valueOf(sum);
        }
    }
}
