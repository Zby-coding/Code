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
 * 类型表
 * </p>
 *
 */
@TableName("sys_type")
public class SysType extends Model<SysType> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
	@TableId(type = IdType.UUID)
	private String id;
    /**
     * 分类名称
     */
	private String name;
    /**
     * 创建时间
     */
	private Date createtime;
    /**
     * 创建人员
     */
	private String createuser;
    /**
     * 更新人员
     */
	private String updateuser;
    /**
     * 更新时间
     */
	private Date updatetime;
    /**
     * 排序
     */
	private Integer xh;

	private String iswzyc;

	public String getIswzyc() {
		return iswzyc;
	}

	public void setIswzyc(String iswzyc) {
		this.iswzyc = iswzyc;
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

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public Integer getXh() {
		return xh;
	}

	public void setXh(Integer xh) {
		this.xh = xh;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SysType{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", createtime=" + createtime +
				", createuser='" + createuser + '\'' +
				", updateuser='" + updateuser + '\'' +
				", updatetime=" + updatetime +
				", xh=" + xh +
				", iswzyc='" + iswzyc + '\'' +
				'}';
	}
}
