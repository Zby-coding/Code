package cn.jeefast.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import cn.jeefast.system.entity.SysUserToken;

/**
 * <p>
  * 系统用户Token Mapper 接口
 * </p>
 */
public interface SysUserTokenDao extends BaseMapper<SysUserToken> {

	SysUserToken queryByToken(String token);
    
}