package day11.code;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class PictureFrame extends JFrame {

    private int[][] datas = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};

    private int[][] successData = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};

    int x0;
    int y0;
    private JButton shang;
    private JButton xia;
    private JButton zuo;
    private JButton you;
    private JButton help;
    private JButton reset;
    private JPanel jPanel;

    public PictureFrame() {
        initFrame();

        initData();

        initContainer();

        listener();

        this.setVisible(true);
    }

    private void initFrame() {
        this.setSize(960, 565);
        this.setTitle("动漫拼图");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);
        this.setAlwaysOnTop(true);
        this.setLayout(null);
    }

    //绘制窗体组件
    private void initContainer() {
        JLabel title = new JLabel(new ImageIcon("E:\\OneDrive\\Desktop\\MD\\code\\javaSE\\com.zby.code\\src\\day11\\code\\images\\title.png"));
        title.setBounds(354, 27, 232, 57);
        this.add(title);

        JLabel success = new JLabel(new ImageIcon("E:\\OneDrive\\Desktop\\MD\\code\\javaSE\\com.zby.code\\src\\day11\\code\\images\\canzhaotu.png"));
        success.setBounds(574, 114, 122, 121);
        this.add(success);

        jPanel = new JPanel();
        jPanel.setBounds(150, 114, 360, 360);
        jPanel.setLayout(null);

        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[i].length; j++) {
                JLabel jLabel = new JLabel(new ImageIcon("E:\\OneDrive\\Desktop\\MD\\code\\javaSE\\com.zby.code\\src\\day11\\code\\images\\" + datas[i][j] + ".png"));
                jLabel.setBounds(j * 90, i * 90, 90, 90);
                jPanel.add(jLabel);
            }
        }
        this.add(jPanel);

        //设置按钮
        shang = new JButton(new ImageIcon("E:\\OneDrive\\Desktop\\MD\\code\\javaSE\\com.zby.code\\src\\day11\\code\\images\\shang.png"));
        shang.setBounds(732, 265, 57, 57);
        this.add(shang);

        xia = new JButton(new ImageIcon("E:\\OneDrive\\Desktop\\MD\\code\\javaSE\\com.zby.code\\src\\day11\\code\\images\\xia.png"));
        xia.setBounds(732, 347, 57, 57);
        this.add(xia);

        zuo = new JButton(new ImageIcon("E:\\OneDrive\\Desktop\\MD\\code\\javaSE\\com.zby.code\\src\\day11\\code\\images\\zuo.png"));
        zuo.setBounds(650, 347, 57, 57);
        this.add(zuo);

        you = new JButton(new ImageIcon("E:\\OneDrive\\Desktop\\MD\\code\\javaSE\\com.zby.code\\src\\day11\\code\\images\\you.png"));
        you.setBounds(813, 347, 57, 57);
        this.add(you);

        help = new JButton(new ImageIcon("E:\\OneDrive\\Desktop\\MD\\code\\javaSE\\com.zby.code\\src\\day11\\code\\images\\qiuzhu.png"));
        help.setBounds(626, 444, 108, 45);
        this.add(help);

        reset = new JButton(new ImageIcon("E:\\OneDrive\\Desktop\\MD\\code\\javaSE\\com.zby.code\\src\\day11\\code\\images\\chongzhi.png"));
        reset.setBounds(786, 444, 108, 45);
        this.add(reset);

        JLabel backGroup = new JLabel(new ImageIcon("E:\\OneDrive\\Desktop\\MD\\code\\javaSE\\com.zby.code\\src\\day11\\code\\images\\background.png"));
        backGroup.setBounds(0, 0, 960, 530);
        this.add(backGroup);
    }

    //图片打乱,把16张图片用0来替换掉
    private void initData() {
        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[i].length; j++) {
                Random random = new Random();

                int x = random.nextInt(datas.length);
                int y = random.nextInt(datas[i].length);

                int temp = datas[i][j];
                datas[i][j] = datas[x][y];
                datas[x][y] = temp;
            }
        }

        wc:
        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[i].length; j++) {
                if (datas[i][j] == 0) {
                    x0 = i;
                    y0 = j;
                    break wc;
                }
            }
        }
    }

    //重新绘制图片
    private void rePaintView(){
        jPanel.removeAll();

        jPanel = new JPanel();
        jPanel.setBounds(150, 114, 360, 360);
        jPanel.setLayout(null);
        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[i].length; j++) {
                JLabel jLabel = new JLabel(new ImageIcon("E:\\OneDrive\\Desktop\\MD\\code\\javaSE\\com.zby.code\\src\\day11\\code\\images\\" + datas[i][j] + ".png"));
                jLabel.setBounds(j * 90, i * 90, 90, 90);
                jPanel.add(jLabel);
            }
        }
        this.add(jPanel);
        jPanel.repaint();
    }
    //添加注册事件
    private void listener() {
        shang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println("上");
                //边界判断
                if (x0 == 3) {
                    return;
                }

                datas[x0][y0] = datas[x0 + 1][y0];
                datas[x0 + 1][y0] = 0;
                x0 = x0 + 1;

                if(isSuccess()){
                    success();
                }
                rePaintView();
            }
        });
        xia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println("下");

                if (x0 == 0) {
                    return;
                }

                datas[x0][y0] = datas[x0 - 1][y0];
                datas[x0 -1][y0] = 0;
                x0 = x0 - 1;

                if(isSuccess()){
                    success();
                }
                rePaintView();
            }
        });
        zuo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println("左");
                if (y0 == 3) {
                    return;
                }

                datas[x0][y0] = datas[x0][y0+1];
                datas[x0 ][y0+1] = 0;
                y0 = y0 + 1;

                if(isSuccess()){
                    success();
                }
                rePaintView();
            }
        });
        you.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println("右");
                if (y0 == 0) {
                    return;
                }

                datas[x0][y0] = datas[x0][y0-1];
                datas[x0 ][y0-1] = 0;
                y0 = y0 - 1;

                if(isSuccess()){
                    success();
                }
                rePaintView();
            }
        });
        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

//                System.out.println("求助");
                success();
                rePaintView();
            }
        });
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println("重置");
                datas = new int[][]{
                        {1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}
                };
                initData();
                rePaintView();
                shang.setEnabled(true);
                xia.setEnabled(true);
                zuo.setEnabled(true);
                you.setEnabled(true);
            }
        });
    }

    //成功图片
    private void success(){
        datas = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        shang.setEnabled(false);
        xia.setEnabled(false);
        zuo.setEnabled(false);
        you.setEnabled(false);
    }

    //判断是否成功
    private boolean isSuccess(){
        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[i].length; j++) {
              if(datas[i][j] != successData[i][j]){
                  return false;
              }
            }
        }
        return true;
    }
}
