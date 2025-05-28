package com.zby.mapper;

import com.zby.pojo.User;

import java.util.List;

public interface UserMapper {
    // 映射文件命名空间要与接口名保持一致 返回值类型也要保持一致
    List<User> selectAll();
}
