package cn.jeefast.system.util;

import cn.jeefast.system.entity.SysZnhd;
import com.alibaba.fastjson.JSON;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class WyNewsCrawler {
    private static final Logger logger = LoggerFactory.getLogger(WyNewsCrawler.class);
    private static final int TIMEOUT = 30000;
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36";

    public static List<SysZnhd> crawlNewsToZnhd(String username, String keyword, String type) {
        List<SysZnhd> znhdList = new ArrayList<>();
        try {
            if (type == null || type.isEmpty() || "全部".equals(type)) {
                // 默认爬取所有频道
                crawlChannel("https://news.163.com", znhdList, keyword, "新闻", username);
                crawlChannel("https://ent.163.com", znhdList, keyword, "娱乐", username);
                crawlChannel("https://sports.163.com", znhdList, keyword, "体育", username);
                crawlChannel("https://tech.163.com", znhdList, keyword, "科技", username);
                crawlChannel("https://money.163.com", znhdList, keyword, "财经", username);
                crawlChannel("https://henan.163.com", znhdList, keyword, "河南", username);
                crawlChannel("https://culture.163.com", znhdList, keyword, "文化", username);

            } else {
                switch (type) {
                    case "科技":
                        crawlChannel("https://tech.163.com", znhdList, keyword, "科技", username);
                        break;
                    case "新闻":
                        crawlChannel("https://news.163.com", znhdList, keyword, "新闻", username);
                        break;
                    case "娱乐":
                        crawlChannel("https://ent.163.com", znhdList, keyword, "娱乐", username);
                        break;
                    case "体育":
                        crawlChannel("https://sports.163.com", znhdList, keyword, "体育", username);
                        break;
                    case "财经":
                        crawlChannel("https://money.163.com", znhdList, keyword, "财经", username);
                    case "河南":
                        crawlChannel("https://henan.163.com", znhdList, keyword, "财经", username);
                    case "文化":
                        crawlChannel("https://culture.163.com", znhdList, keyword, "财经", username);
                    default:
                        logger.warn("未知频道类型: {}", type);
                }
            }
        } catch (Exception e) {
            logger.error("爬取新闻失败", e);
        }
        return znhdList;
    }

    private static void crawlChannel(String channelUrl, List<SysZnhd> znhdList, String keyword, String type, String username) {
        try {
            Document doc = getDocument(channelUrl);
            if (doc != null) {
                Elements newsElements = doc.select("div.news_title a, div.news_item a, h3.news_title a");
                processNewsElements(newsElements, znhdList, keyword, type, username);
            }
        } catch (IOException e) {
            logger.error("爬取频道失败: " + channelUrl, e);
        }
    }

    private static Document getDocument(String url) throws IOException {
        return Jsoup.connect(url)
                .timeout(TIMEOUT)
                .userAgent(USER_AGENT)
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                .header("Accept-Language", "zh-CN,zh;q=0.9")
                .header("Connection", "keep-alive")
                .ignoreContentType(true)
                .get();
    }

    private static void processNewsElements(Elements elements, List<SysZnhd> znhdList, String keyword, String type, String username) {
        for (Element element : elements) {
            try {
                String title = element.text().trim();
                String href = element.absUrl("href");

                if (isValidNews(title, href)) {
                    SysZnhd znhd = new SysZnhd();
                    znhd.setId(UUID.randomUUID().toString());
                    znhd.setName(title);
                    znhd.setPcurl(href);
                    znhd.setContent(title);
                    znhd.setType(type);
                    znhd.setCreatetime(new Date());
                    znhd.setUpdatetime(new Date());
                    znhd.setCreateuser(username);
                    znhd.setUpdateuser(username);
                    znhd.setZylx(type);
                    znhd.setZylxname(type);

                    znhdList.add(znhd);
                    logger.info("添加新闻: {}", title);
                }
            } catch (Exception e) {
                logger.warn("处理新闻元素时出错", e);
            }
        }
    }

    private static boolean isValidNews(String title, String href) {
        return title != null &&
                !title.isEmpty() &&
                href != null &&
                !href.isEmpty() &&
                href.contains("163.com") &&
                title.length() >= 4;
    }

}
