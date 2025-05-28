package day09.homework.gui;

import javax.swing.*;

public class GuessNumberDemo {
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
        jFrame.setVisible(true);

    }
}
