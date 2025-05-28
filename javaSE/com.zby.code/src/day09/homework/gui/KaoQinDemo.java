package day09.homework.gui;

import javax.swing.*;

public class KaoQinDemo {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setTitle("考勤查询");
        jFrame.setSize(400, 300);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(3);
        jFrame.setLayout(null);
        JLabel jLabel = new JLabel("考勤日期");
        jLabel.setBounds(50, 20, 100, 20);
        jFrame.add(jLabel);
        JLabel start = new JLabel("开始时间");
        start.setBounds(50, 70, 100, 20);
        jFrame.add(start);
        JLabel end = new JLabel("结束时间");
        end.setBounds(250, 70, 100, 20);
        jFrame.add(end);
        JTextField jTextField1 = new JTextField();
        jTextField1.setBounds(50, 100, 100, 20);
        jFrame.add(jTextField1);
        JTextField jTextField2 = new JTextField();
        jTextField2.setBounds(250, 100, 100, 20);
        jFrame.add(jTextField2);
        JButton jButton = new JButton("确定");
        jButton.setBounds(250, 180, 60, 20);
        jFrame.add(jButton);
        jFrame.setVisible(true);

    }
}
