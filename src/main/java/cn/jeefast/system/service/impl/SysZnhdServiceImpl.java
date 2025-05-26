package cn.jeefast.system.service.impl;

import cn.jeefast.common.utils.PageUtils;
import cn.jeefast.common.utils.Query;
import cn.jeefast.system.service.DataDeduplicationService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import cn.jeefast.system.entity.SysZnhd;
import cn.jeefast.system.dao.SysZnhdDao;
import cn.jeefast.system.service.SysZnhdService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 智能回答管理 服务实现类
 * </p>
 */
@Service
public class SysZnhdServiceImpl extends ServiceImpl<SysZnhdDao, SysZnhd> implements SysZnhdService {
    @Autowired
    private SysZnhdDao sysZnhdDao;
    @Autowired
    private DataDeduplicationService dataDeduplicationService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveNewsToZnhd(List<SysZnhd> znhdList) {
        znhdList.forEach(znhd -> {
            // 确保每个条目都有新ID
            if (StringUtils.isBlank(znhd.getId())) {
                znhd.setId(UUID.randomUUID().toString());
            }
            // 设置必要字段
            znhd.setCreatetime(new Date());
            znhd.setUpdatetime(znhd.getCreatetime());
            znhd.setCreateuser(SecurityUtils.getSubject().getPrincipal().toString());
        });
        // 批量插入
        this.insertBatch(znhdList);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        // 参数校验与默认值处理
        int pageNum = 1;
        int pageSize = 10;
        try {
            if (params.containsKey("page") && params.get("page") != null) {
                pageNum = Math.max(Integer.parseInt(params.get("page").toString()), 1); // 最小为1
            }
            if (params.containsKey("limit") && params.get("limit") != null) {
                pageSize = Math.max(Integer.parseInt(params.get("limit").toString()), 1); // 最小为1
                pageSize = Math.min(pageSize, 100); // 最大限制100条/页
            }
        } catch (NumberFormatException e) {
            // 参数格式错误时使用默认值
            throw new RuntimeException("参数格式错误");
        }

        // 创建 Page 对象
        Page<SysZnhd> page = new Page<>(pageNum, pageSize);

        // 创建 EntityWrapper 对象
        EntityWrapper<SysZnhd> wrapper = new EntityWrapper<>();
        wrapper.orderBy("createtime", false);

        // 添加查询条件
        String keyword = (String) params.get("keyword");
        String type = (String) params.get("type");
        if (StringUtils.isNotBlank(keyword)) {
            wrapper.like("name", keyword);
        }
        if (StringUtils.isNotBlank(type)) {
            wrapper.eq("type", type);
        }

        // 执行查询
        Page<SysZnhd> iPage = this.selectPage(page, wrapper);

        // 返回结果
        return new PageUtils((int) iPage.getTotal(), iPage.getRecords());
    }

    @Override
    public List<SysZnhd> queryByType(String type) {
        EntityWrapper<SysZnhd> wrapper = new EntityWrapper<>();
        wrapper.eq("type", type)
                .orderBy("createtime", false);
        return this.selectList(wrapper);
    }

    @Override
    public void saveOrUpdate(SysZnhd znhd) {

        // 统一处理新增逻辑
        if (StringUtils.isBlank(znhd.getId())) {
            znhd.setId(UUID.randomUUID().toString());
            sysZnhdDao.insert(znhd);
            return;
        }

        // 更新前重新查询确保数据存在
        SysZnhd existing = sysZnhdDao.selectById(znhd.getId());
        if (existing != null) {
            sysZnhdDao.updateById(znhd);
        } else {
            // 处理ID已传入但不存在的情况
            znhd.setId(UUID.randomUUID().toString());
            sysZnhdDao.insert(znhd);
        }
    }


    @Override
    public Page<SysZnhd> queryPageList(Page<SysZnhd> page, Map<String, Object> map) {
        EntityWrapper<SysZnhd> wrapper = new EntityWrapper<>();
        // 动态查询条件
        if (map.containsKey("type")) {
            wrapper.eq("type", map.get("type"));
        }
        if (map.containsKey("keyword")) {
            wrapper.like("name", map.get("keyword").toString());
        }
        // 排序处理
        wrapper.orderBy(map.getOrDefault("sidx", "createtime").toString(),
                "desc".equalsIgnoreCase(map.get("order").toString()));

        return this.selectPage(page, wrapper);
    }


    @Override
    public void deleteBatch(String[] znhdIds) {
        sysZnhdDao.deleteBatch(znhdIds);
    }
}
