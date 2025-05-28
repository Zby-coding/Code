package day09.homework.gui;

import javax.swing.*;

public class UserLoginDemo {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(400, 300);
        jFrame.setTitle("用户登录");
        // 设置窗体居中
        jFrame.setLocationRelativeTo(null);
        // 设置关闭窗口的默认操作
        jFrame.setDefaultCloseOperation(3);
        // 设置为jFrame.setLayout(null);不使用任何布局管理器
        jFrame.setLayout(null);
        // 设置jLabel
        JLabel jLabel1 = new JLabel("用户名");
        jLabel1.setBounds(50, 50, 50, 20);
        jFrame.add(jLabel1);
        JLabel jLabel2 = new JLabel("密码");
        jLabel2.setBounds(50, 100, 50, 20);
        jFrame.add(jLabel2);
        // 设置JTextField
        JTextField jTextField1 = new JTextField();
        jTextField1.setBounds(150, 50, 180, 20);
        jFrame.add(jTextField1);
        JTextField jTextField2 = new JTextField();
        jTextField2.setBounds(150, 100, 180, 20);
        jFrame.add(jTextField2);
        JButton jButton = new JButton("登录");
        jButton.setBounds(50, 200, 280, 20);
        jFrame.add(jButton);
        jFrame.setVisible(true);


    }
}
