package cn.jeefast.system.entity;

import java.io.Serializable;

import java.util.Date;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 科目管理
 * </p>
 *
 */
@TableName("sys_kemu")
public class SysKemu extends Model<SysKemu> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
	@TableId(type = IdType.UUID)
	private String id;
    /**
     * 科目
     */
	private String name;
    /**
     * 创建时间
     */
	private Date createtime;
    /**
     * 更新时间
     */
	private Date updatetime;
    /**
     * 创建人员
     */
	private String createuser;
    /**
     * 更新人员
     */
	private String updateuser;

	private String jiaoshiuser;

	@TableField(exist = false)
	private JSONArray files;

	public JSONArray getFiles() {
		return files;
	}

	public void setFiles(JSONArray files) {
		this.files = files;
	}

	public String getJiaoshiuser() {
		return jiaoshiuser;
	}

	public void setJiaoshiuser(String jiaoshiuser) {
		this.jiaoshiuser = jiaoshiuser;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public String getUpdateuser() {
		return updateuser;
	}

	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SysKemu{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", createtime=" + createtime +
				", updatetime=" + updatetime +
				", createuser='" + createuser + '\'' +
				", updateuser='" + updateuser + '\'' +
				", jiaoshiuser='" + jiaoshiuser + '\'' +
				", files=" + files +
				'}';
	}
}
