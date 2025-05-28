package day10.homework.string;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        JButton clear = new JButton("清空聊天");
        clear.setBounds(280,230,100,20);
        jFrame.add(clear);
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextArea.setText("");
            }
        });
        JTextField jTextField = new JTextField();
        jTextField.setBounds(10,230,180,20);
        jFrame.add(jTextField);
        JButton send = new JButton("发送");
        send.setBounds(200,230,70,20);
        jFrame.add(send);

        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取去除空格的字符串
                String text = jTextField.getText().trim();
                jTextArea.append(text + "\n");
                jTextField.setText("");
            }
        });
        jFrame.setVisible(true);

    }
}
