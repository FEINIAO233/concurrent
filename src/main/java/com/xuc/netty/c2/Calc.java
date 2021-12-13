package com.xuc.netty.c2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

/**
 * @author xuchang
 * @date 2021/12/9
 */
public class Calc {
    public static void main(String[] args) {
        new Calcu().context();
    }

    static class Calcu extends JFrame {
        private JPanel panelText, panelContext1;//⾯板
        private JTextField text;//⽂本
        private String data = "";//⽂本内容
        private JButton button;
        private double left, right;//左右字符
        private boolean isLeft;//给哪个字符赋值
        private String prevOperaotor = "";

        //窗体的初始化⽅法
        void windows() {
            //显示
            this.setVisible(true);
            this.setSize(235, 320);
            //居中
            this.setLocationRelativeTo(null);
            //不可拖动⼤⼩
// this.setResizable(false);
            //设置布局为BorderLayout
            this.setLayout(new BorderLayout());
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.validate();
        }

        void context() {
            windows();
            //⽂本⾯板
            panelText = new JPanel();
            panelText.setBackground(Color.black);
            //⽂本对象
            text = new JTextField();
            text.setText("0");
            //⽂本对象前景⾊
            text.setForeground(Color.black);
            //将⽂本对象的背景⾊调成⽩⾊
            text.setBackground(Color.white);
            //设置⽂本对象的宽度和⾼度
            text.setPreferredSize(new Dimension(250, 45));
            //⽂本右边对⻬
            text.setHorizontalAlignment(JTextField.RIGHT);
            //⽂本不可编辑
            text.setEnabled(false);
            text.setFont(new Font("⿊体", Font.BOLD, 40));
            //将⽂本对象添加到⽂本⾯板中
            panelText.add(text);
            //按钮⾯板
            panelContext1 = new JPanel(new GridLayout(5, 4));
            //按钮，因为要创建的按钮过多，则创建了⼀个字符串类型的数组
            String[] arry = {"AC", "+/-", "%", "/", "*", "8", "7", "6", "-", "5", "4", "3", "+", "2", "1", "0", ".", "="};
            //通过for循环从0开始依次创建button
            for (String s : arry) {
                button = new JButton(s);
                //将创建好的按钮添加到按钮⾯板中
                panelContext1.add(button);
                //给每个按钮添加监听器
                button.addActionListener(new ListenerNumber());
            }
            //将⽂本⾯板添加到容器中
            this.add(panelText, BorderLayout.NORTH);
            this.add(panelContext1, BorderLayout.CENTER);
        }

        //每个按钮监听器
        private class ListenerNumber implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                //操作符
                String oper = "";
                //获取到当前点击按钮的值
                String butNum = ((JButton) e.getSource()).getText();
                System.out.println(butNum);
                //判断如果点击的是1
                if (butNum.equals("1")) {
                    //可能要多次累加进⾏计算，所以每点⼀次则⾃增它⾃身
                    data += "1";
                    //将其赋值给⽂本框
                    text.setText(data);
                } else if (butNum.equals("2")) {
                    data += "2";
                    text.setText(data);
                } else if (butNum.equals("3")) {
                    data += "3";
                    text.setText(data);
                } else if (butNum.equals("4")) {
                    data += "4";
                    text.setText(data);
                } else if (butNum.equals("5")) {
                    data += "5";
                    text.setText(data);
                } else if (butNum.equals("6")) {
                    data += "6";
                    text.setText(data);
                } else if (butNum.equals("7")) {
                    data += "7";
                    text.setText(data);
                } else if (butNum.equals("8")) {
                    data += "8";
                    text.setText(data);
                } else if (butNum.equals("9")) {
                    data += "9";
                    text.setText(data);
                } else if (butNum.equals("0")) {
                    data += "0";
                    text.setText(data);
                } else if (butNum.equals("AC")) {//当点击AC时
                    //将data的值为空
                    data = "";
                    //结束运算⽅法
                    isLeft = false;
                    //将⽂本框的值设置为0
                    text.setText("0");
                } else if (butNum.equals(".")) {
                    data += ".";
                    text.setText(data);
                } else if (butNum.equals("+/-")) {
                    // 找不到负号为-1，代表这是正数
                    if (data.indexOf("-") < 0) {
                        data = "-" + data;
                    } else {
                        //从索引1开始取值
                        data = data.substring(1);
                    }
                    text.setText(data);
                } else if (butNum.equals("%")) {
                    //因为data是字符串类型⽆法进⾏操作符的运算那么就将其转换为双精度类型double
                    //⼜因为得出的值是double，所以再次强转为字符串类型，并且赋值给data
                    data = Double.parseDouble(data) / 100 + "";
                    //将得出的结果赋值给⽂本框
                    text.setText(data);
                    //清空
                    data = "";
                } else if (butNum.equals("+")) {//点击+号时
                    //将+号赋给操作符变量，⽤于之后的运算
                    oper = "+";
                    //将操作符带⼊计算⽅法
                    cal(oper);
                } else if (butNum.equals("-")) {
                    oper = "-";
                    cal(oper);
                } else if (butNum.equals("*")) {
                    oper = "*";
                    cal(oper);
                } else if (butNum.equals("/")) {
                    oper = "/";
                    cal(oper);
                } else if (butNum.equals("=")) {
                    oper = "=";
                    cal(oper);
                }
            }
        }

        //计算
        void cal(String oper) {
            //如果data为空则不运算
            // if(data.equals("")){
            // //不管输⼊了啥，只要点击了等于则结束本次运算
            // if(data.equals("=")){
            // text.setText("0");
            // isLeft=false;
            // }
            // return;
            // }
            //⽤于判断是给left赋值，还是给right赋值
            //因为left 运算符 right=结果，所以从左⾄右先给left赋值，所以isLeft变量默认为false
            if (isLeft) {
                right = Double.parseDouble(data);
                System.out.println(right);

                if (prevOperaotor.equals("+")) {
                    left = left + right;
                } else if (prevOperaotor.equals("-")) {
                    left = left - right;
                } else if (prevOperaotor.equals("*")) {
                    left = left * right;
                } else if (prevOperaotor.equals("/")) {
                    left = left / right;
                }
                //将得出的结果转换为int类型
                if (left == (int) left) {
                    left = (int) left;
                }
                data = Integer.toString((int) left);
            } else {
                //将第⼀次点击的数赋值给left
                left = Double.parseDouble(data);
                //将data设置为空
                data = "";
                isLeft = true;
            }
            //将传来的操作符赋值prevOperaotor，⽤于两者之间的判断
            prevOperaotor = oper;
            //将算出来的结果赋值给res，有因为int类型不能直接赋值给string类型则需要强转
            String res = left + "";
            //最后将算出的结果赋值给⽂本框中
            text.setText(textFormat(res));
        }

        //格式化⽂本
        private String textFormat(String s) {
            //创建⼀个格式化数字的对象
            NumberFormat nf = NumberFormat.getInstance();
            //格式化
            String fomatResult = nf.format(Double.parseDouble(s));
            return fomatResult;
        }
    }
}
