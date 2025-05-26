package cn.jeefast.system.util;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Slf4j
@Component
public class JavaCrawl {

    // 改造为可配置参数（原静态常量修改为实例变量）
    private static String URL_PREFIX;
    private static String URL_SUFFIX;
    private static String START_DATE;
    private static String END_DATE;
    private static String FILE_PATH;
    private static String KEYWORDS;  // 新增关键词配置
    private static int connectTimeout;

    // 通过setter方法注入配置值
    @Value("${crawler.url-prefix}")
    public void setUrlPrefix(String urlPrefix) {
        URL_PREFIX = urlPrefix;
    }

    @Value("${crawler.url-suffix}")
    public void setUrlSuffix(String urlSuffix) {
        URL_SUFFIX = urlSuffix;
    }

    @Value("${crawler.start-date}")
    public void setStartDate(String startDate) {
        START_DATE = startDate;
    }

    @Value("${crawler.end-date}")
    public void setEndDate(String endDate) {
        END_DATE = endDate;
    }

    @Value("${crawler.file-path}")
    public void setFilePath(String filePath) {
        FILE_PATH = filePath;
    }

    @Value("${crawler.keywords}")
    public void setKeywords(String keywords) {
        KEYWORDS = keywords;
    }

    // 添加setter方法实现配置注入
    @Value("${crawler.connection.connect-timeout}")
    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public static void main(String[] args) {
        DateIncrease();
    }

    public static void writeFile(String content) {
        FileWriter fileWriter = null;

        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            fileWriter = new FileWriter(file, true);
            fileWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void DateIncrease() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //起始日期
        try {
            Date startDate = sdf.parse(START_DATE);
            //结束日期
            Date endDate = sdf.parse(END_DATE);
            Date tempDate = startDate;

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);

            //循环
            while (tempDate.getTime() < endDate.getTime()) {
                tempDate = calendar.getTime();
                String date = sdf.format(tempDate);
                date = date.replace("-", "");
                //2020-03-28	20200328
                String url = URL_PREFIX + date + URL_SUFFIX;
                List<Map> maps = doGet(url);
                if (maps.size() > 0) {
                    // 新增文件写入逻辑
                    StringBuilder sb = new StringBuilder();
                    sb.append("=== Date: ").append(sdf.format(tempDate)).append(" ===\n");
                    for (int i = 0; i < maps.size(); i++) {
                        Map<?,?> item = maps.get(i);
                        // 构建CSV格式数据行
                        String line = String.format("%s\t%s\t%s\t%s\n",
                                item.get("title"),
                                item.get("rq"),
                                item.get("media"),
                                item.get("url"));
                        sb.append(line);
                    }
                    // 写入当日数据到文件
                    writeFile(sb.toString());
                }
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static List<Map> doGet(String url) {
        // 访问url，获取从网页解析并过滤后的新闻数据。
        List<Map> kyxg = new ArrayList<>();
        Document document = null;
        try {
            // 发送HTTP GET请求获取网页文档
            document = Jsoup.connect(url)
                    .timeout(30 * 1000)
                    .get(); // 设置为30秒

        } catch (IOException e) {
            e.printStackTrace();
        }

        // 筛选出有用数据
        String body = document.select("body").html();
        // 截取有效JSON字符串（去除JS变量声明）
        String subString = body.substring(19, body.length() - 1);

        // 将子字符串解析为JSON对象
        JSONObject json = JSONObject.parseObject(subString);
        // 获取包含新闻数据的JSON数组
        JSONArray jsonArray = json.getJSONArray("data");

        if (jsonArray != null && jsonArray.size() > 0) {
            // 遍历每个新闻条目
            for (Object object : jsonArray) {
                JSONObject news = (JSONObject) object;
                // 提取新闻标题
                String title = news.getString("title");

                // 过滤包含"资源"关键词的新闻
                if (title.contains(KEYWORDS)) {
                    // 解析新闻元数据
                    String date = news.getString("create_date");
                    String time = news.getString("create_time");
                    String media = news.getString("media");
                    String urlstr = news.getString("url");

                    // 构建新闻数据字典
                    Map map = new HashMap();
                    map.put("title", title);       // 新闻标题
                    map.put("rq", date + " " + time);  // 合并发布日期时间
                    map.put("media", media);       // 发布媒体
                    map.put("url", urlstr);        // 新闻详情页URL

                    kyxg.add(map);  // 添加到返回列表
                }
            }
        }
        return kyxg;  // 返回符合条件的新闻数据列表
    }

}
