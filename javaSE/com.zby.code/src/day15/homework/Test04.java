package day15.homework;

import java.io.*;

/*
分析以下需求，并用代码实现
	项目根路径下有个questions.txt文件内容如下：
	5+5
	150-25
	155*155
	2555/5
	要求：读取内容计算出结果，将结果写入到results.txt文件中
	5+5=10

 */
public class Test04 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("E:\\OneDrive\\Desktop\\MD\\code\\javaSE\\com.zby.code\\src\\day15\\homework\\questions.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("E:\\OneDrive\\Desktop\\MD\\code\\javaSE\\com.zby.code\\src\\day15\\homework\\results.txt"));
        // 读取数据
        String calcResult = "";
        String line;
        while ((line = br.readLine()) != null) {
            // 判断符号
            if (line.contains("+")) {
                String[] split = line.split("\\+");
                int result = Integer.parseInt(split[0]) + Integer.parseInt(split[1]);
                calcResult = line + "=" + result;
            } else if (line.contains("-")) {
                String[] split = line.split("\\-");
                int result = Integer.parseInt(split[0]) - Integer.parseInt(split[1]);
                calcResult = line + "=" + result;
            } else if (line.contains("*")) {
                String[] split = line.split("\\*");
                int result = Integer.parseInt(split[0]) * Integer.parseInt(split[1]);
                calcResult = line + "=" + result;
            } else {
                String[] split = line.split("/");
                int result = Integer.parseInt(split[0]) / Integer.parseInt(split[1]);
                calcResult = line + "=" + result;
            }
            bw.write(calcResult);
            bw.flush();
            bw.newLine();
        }
        br.close();
        bw.close();
    }

}
