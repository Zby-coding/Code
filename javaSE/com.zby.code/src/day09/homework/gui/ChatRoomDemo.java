package day09.homework.gui;

import javax.swing.*;

public class ChatRoomDemo {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(400, 300);
        jFrame.setTitle("聊天室");
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(3);
        jFrame.setLayout(null);
        JTextArea jTextArea = new JTextArea();
        jTextArea.setBounds(10,10,360,200);
        jFrame.add(jTextArea);
        JButton send = new JButton("发送");
        send.setBounds(200,230,70,20);
        jFrame.add(send);
        JButton clear = new JButton("清空聊天");
        clear.setBounds(280,230,100,20);
        jFrame.add(clear);
        JTextField jTextField = new JTextField();
        jTextField.setBounds(10,230,180,20);
        jFrame.add(jTextField);
        jFrame.setVisible(true);

    }
}
