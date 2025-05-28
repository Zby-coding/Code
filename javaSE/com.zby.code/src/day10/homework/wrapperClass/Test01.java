package day10.homework.wrapperClass;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Scanner;

public class Test01 {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(400, 300);
        jFrame.setTitle("猜数字");
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(3);
        jFrame.setLayout(null);
        JLabel jLabel = new JLabel("系统产生了一个1-100的数字，请猜一猜");
        jLabel.setBounds(70, 50, 350, 20);
        jFrame.add(jLabel);
        JTextField jTextField = new JTextField();
        jTextField.setBounds(120, 100, 150, 20);
        jFrame.add(jTextField);
        JButton jButton = new JButton("猜一猜");
        jButton.setBounds(150, 150, 100, 20);
        jFrame.add(jButton);
        System.out.println("系统产生了一个1-100的数字，请尝试猜一猜:");
        Random random = new Random();
        int num = random.nextInt(100) + 1;
        System.out.println(num);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = jTextField.getText();
                if(Integer.parseInt(s)>num) System.out.println("对不起，您输入的数字大了");
                if(Integer.parseInt(s)<num) System.out.println("对不起，您输入的数字小了");
                else System.out.println("恭喜您猜对了");
            }
        });
        jFrame.setVisible(true);

    }
}

