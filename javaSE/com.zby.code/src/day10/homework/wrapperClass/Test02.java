package day10.homework.wrapperClass;

import day10.homework.DateChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class Test02 {
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
        DateChooser instance1 = DateChooser.getInstance();
        instance1.register(jTextField1);
        jFrame.add(jTextField1);
        JTextField jTextField2 = new JTextField();
        jTextField2.setBounds(250, 100, 100, 20);
        DateChooser instance2 = DateChooser.getInstance();
        instance2.register(jTextField2);
        jFrame.add(jTextField2);
        JButton jButton = new JButton("确定");
        jButton.setBounds(250, 180, 60, 20);
        jFrame.add(jButton);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String startTime = jTextField1.getText();
                String endTime = jTextField1.getText();
                System.out.println("开始时间：" + startTime + "\n结束时间：" +endTime);
            }
        });
        jFrame.setVisible(true);

    }
}
