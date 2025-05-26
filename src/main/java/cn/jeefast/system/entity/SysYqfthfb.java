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
 * 疫情发帖回复表
 * </p>
 */
@TableName("sys_yqfthfb")
public class SysYqfthfb extends Model<SysYqfthfb> {

    private static final long serialVersionUID = 1L;

	@TableId(type = IdType.UUID)
	private String id;
    /**
     * 关联发帖表id
     */
	private String yqfatieid;
    /**
     * 回复内容
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

	public String getYqfatieid() {
		return yqfatieid;
	}

	public void setYqfatieid(String yqfatieid) {
		this.yqfatieid = yqfatieid;
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
		return "SysYqfthfb{" +
			", id=" + id +
			", yqfatieid=" + yqfatieid +
			", content=" + content +
			", updatetime=" + updatetime +
			", updateuser=" + updateuser +
			", createtime=" + createtime +
			", createuser=" + createuser +
			"}";
	}
}
