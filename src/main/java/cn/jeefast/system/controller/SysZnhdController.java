
package cn.jeefast.system.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import cn.jeefast.common.utils.Query;
import cn.jeefast.common.utils.R;
import cn.jeefast.common.utils.StringUtils;
import cn.jeefast.common.validator.ValidatorUtils;
import cn.jeefast.system.entity.SysUser;
import cn.jeefast.system.entity.SysZnhd;
import cn.jeefast.system.entity.TMaterialFile;
import cn.jeefast.system.service.TMaterialFileService;
import cn.jeefast.system.util.JavaCrawl;
import cn.jeefast.common.utils.PageUtils;
import cn.jeefast.system.util.WyNewsCrawler;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.plugins.Page;

import org.springframework.web.bind.annotation.RequestMapping;


import cn.jeefast.system.service.SysZnhdService;
import org.springframework.web.bind.annotation.RestController;
import cn.jeefast.common.base.BaseController;

/**
 * <p>
 * 智能回答管理 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/sys/znhd")
public class SysZnhdController extends BaseController {
    @Autowired
    private SysZnhdService sysZnhdService;

    @Autowired
    private TMaterialFileService tMaterialFileService;

    /**
     * 根据类型分页查询（前端展示用）
     */
    @GetMapping("/listForWeb")
    public R listForWeb(@RequestParam Map<String, Object> params) {
        params.put("type", "2"); // 固定查询爬取的数据
        Query query = new Query(params);
        Page<SysZnhd> pageUtil = new Page<>(query.getPage(), query.getLimit());
        pageUtil.setOrderByField("updatetime");
        pageUtil.setAsc(false);
        Page<SysZnhd> page = sysZnhdService.queryPageList(pageUtil, query);
        return R.ok().put("page", page);
    }

    /**
     * 爬取新闻并保存
     */
    @GetMapping("/crawlNews")
    public R crawlNews(@RequestParam String name, @RequestParam String type) {
        try {
            Subject currentUser = SecurityUtils.getSubject();
            Object principal = currentUser.getPrincipal();
            String username;
            if (principal instanceof SysUser) {
                username = ((SysUser) principal).getUsername();
            } else {
                throw new RuntimeException("用户未登录");
            }
            List<SysZnhd> znhdList = WyNewsCrawler.crawlNewsToZnhd(username, name, type);
            //添加ID生成逻辑
            znhdList.forEach(item -> {
                item.setId(UUID.randomUUID().toString().replace("-", ""));
            });
            if (!znhdList.isEmpty()) {
                sysZnhdService.saveNewsToZnhd(znhdList);
            }
            return R.ok().put("count", znhdList.size());
        } catch (Exception e) {
            return R.error("爬取失败" + e.getMessage());
        }
    }

    @RequestMapping("/list")
    @RequiresPermissions("sys:znhd:list")
    public R list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        // 添加排序参数到查询对象
        query.put("sidx", "createtime");
        query.put("order", "desc");

        Page<SysZnhd> pageUtil = new Page<>(query.getPage(), query.getLimit());

        // 使用 EntityWrapper 替换 QueryWrapper
        EntityWrapper<SysZnhd> wrapper = new EntityWrapper<>();

        // 根据需要添加查询条件
        String keyword = (String) params.get("keyword");
        String type = (String) params.get("type");
        if (StringUtils.isNotBlank(keyword)) {
            wrapper.like("name", keyword);
        }
        if (StringUtils.isNotBlank(type)) {
            wrapper.eq("type", type);
        }

        Page<SysZnhd> page = sysZnhdService.selectPage(pageUtil, wrapper);

        return R.ok().put("page", page);
    }

    /**
     * 根据类型查询
     */
    @GetMapping("/listByType/{type}")
    public R listByType(@PathVariable("type") String type) {
        List<SysZnhd> list = sysZnhdService.queryByType(type);
        return R.ok().put("list", list);
    }

    /**
     * 智能回答管理信息
     */
    @RequestMapping("/info/{znhdId}")
    @RequiresPermissions("sys:znhd:info")
    public R info(@PathVariable("znhdId") String znhdId) {
        SysZnhd znhd = sysZnhdService.selectById(znhdId);
        //获取附件列表
        List<TMaterialFile> tMaterialFiles = tMaterialFileService.selectList(new EntityWrapper<TMaterialFile>().eq("parentid", znhd.getId()));
        List<Map<String, Object>> mapList = new ArrayList<>();
        if (!tMaterialFiles.isEmpty()) {
            for (TMaterialFile tMaterialFile : tMaterialFiles) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", tMaterialFile.getId());
                map.put("filePath", tMaterialFile.getSfilename());
                map.put("fileName", tMaterialFile.getSaccessoryname());
                mapList.add(map);
            }

        }
        JSONArray json = (JSONArray) JSONArray.toJSON(mapList);

        znhd.setFiles(json);
        return R.ok().put("znhd", znhd);
    }

    /**
     * 获取所有智能回答管理
     */
    @RequestMapping("/getZnhds")
    public R getZnhds(@RequestParam(value = "limit", required = false) Integer limit) {
        if (limit == null || limit <= 0) {
            limit = 100; // 默认显示100条数据
        }

        // 按更新时间降序排序并限制返回数量
        Page<SysZnhd> page = new Page<>(1, limit);
        page.setOrderByField("updatetime");
        page.setAsc(false);

        Page<SysZnhd> pageResult = sysZnhdService.selectPage(page);
        List<SysZnhd> znhds = pageResult.getRecords();

        return R.ok().put("znhds", znhds).put("total", pageResult.getTotal());
    }

    /**
     * 保存智能回答管理
     */
    //@Log("保存智能回答管理")
    @RequestMapping("/save")
    @RequiresPermissions("sys:znhd:save")
    public R save(@RequestBody SysZnhd znhd) {
        ValidatorUtils.validateEntity(znhd);
        znhd.setCreatetime(new Date());
        znhd.setCreateuser(getUser().getUsername());
        znhd.setUpdatetime(new Date());
        znhd.setUpdateuser(getUser().getUsername());
        sysZnhdService.insert(znhd);

        if (znhd.getFiles() != null) {
            tMaterialFileService.setTMaterialFilePrintId(znhd.getFiles(), znhd.getId());
        }
        return R.ok();
    }

    /**
     * 修改智能回答管理
     */
    //@Log("修改智能回答管理")
    @RequestMapping("/update")
    @RequiresPermissions("sys:znhd:update")
    public R update(@RequestBody SysZnhd znhd) {
        ValidatorUtils.validateEntity(znhd);
        znhd.setUpdatetime(new Date());
        znhd.setUpdateuser(getUser().getUsername());
        sysZnhdService.updateById(znhd);
        if (znhd.getFiles() != null) {
            tMaterialFileService.setTMaterialFilePrintId(znhd.getFiles(), znhd.getId());
        }
        return R.ok();
    }

    /**
     * 删除智能回答管理
     */
    //@Log("删除智能回答管理")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:znhd:delete")
    public R delete(@RequestBody String[] znhdIds) {
        sysZnhdService.deleteBatch(znhdIds);
        return R.ok();
    }


    private final static String url_prefix = "http://top.news.sina.com.cn/ws/GetTopDataList.php?top_type=day&top_cat=www_www_all_suda_suda&top_time=";
    private final static String url_suffix = "&top_show_num=100&top_order=DESC&js_var=all_1_data01";
    private final static String start_Date = "2020-11-01";
    private final static String end_Date = "2024-02-30";

    /**
     * 数据爬虫
     */
    //@Log("删除考研新闻信息")
    @RequestMapping("/sjpq")
    @RequiresPermissions("sys:znhd:sjpq")
    public R sjpq(@RequestBody JSONObject param) {
//        JavaCrawl.DateIncrease();
//        return R.ok();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //起始日期
        try {
            Date startDate = sdf.parse(start_Date);
            //结束日期
            Date endDate = sdf.parse(end_Date);
            Date tempDate = startDate;

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);

            //循环
            while (tempDate.getTime() < endDate.getTime()) {
                tempDate = calendar.getTime();
                String date = sdf.format(tempDate);
                date = date.replace("-", "");
                //2020-03-28    20200328
                String url = url_prefix + date + url_suffix;
                List<Map> maps = JavaCrawl.doGet(url);
                if (maps.size() > 0) {
                    for (int i = 0; i < maps.size(); i++) {
                        Map map = maps.get(i);
                        SysZnhd sysZnhd = new SysZnhd();
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                        sysZnhd.setName(map.get("title") + "");
                        sysZnhd.setContent(null);
                        sysZnhd.setCreatetime(format.parse(map.get("rq") + ""));
                        sysZnhd.setUpdatetime(format.parse(map.get("rq") + ""));
                        sysZnhd.setCreateuser("admin");
                        sysZnhd.setUpdateuser("admin");
                        sysZnhd.setType("2");
                        sysZnhd.setPcurl(map.get("url") + "");

                        sysZnhdService.insert(sysZnhd);
                    }
                }
                calendar.add(Calendar.DAY_OF_MONTH, 1);

            }
        } catch (ParseException e) {
            e.printStackTrace();
            return R.error("爬取错误");
        }
        return R.ok();
    }
}