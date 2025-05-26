package cn.jeefast.system.entity;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 发帖信息表
 * </p>
 */
@TableName("sys_yqfatie")
public class SysYqfatie extends Model<SysYqfatie> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
	@TableId(type = IdType.UUID)
	private String id;
    /**
     * 标题
     */
	private String name;
    /**
     * 详细内容
     */
	private String content;
    /**
     * 更新时间
     */
	private Date updatetime;
    /**
     * 更新人
     */
	private String updateuser;
    /**
     * 创建时间
     */
	private Date createtime;
    /**
     * 创建人员
     */
	private String createuser;


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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getUpdateuser() {
		return updateuser;
	}

	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SysYqfatie{" +
			", id=" + id +
			", name=" + name +
			", content=" + content +
			", updatetime=" + updatetime +
			", updateuser=" + updateuser +
			", createtime=" + createtime +
			", createuser=" + createuser +
			"}";
	}
}
