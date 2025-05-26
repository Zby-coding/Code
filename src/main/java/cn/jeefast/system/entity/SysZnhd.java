package cn.jeefast.system.entity;

import java.io.Serializable;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 智能回答管理
 * </p>
 *
 */
@TableName("sys_znhd")
public class SysZnhd extends Model<SysZnhd> {

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

	private String type;
	private String pcurl;

	private String zylx;

	@TableField(exist = false)
	private String zylxname;


	public String getZylx() {
		return zylx;
	}

	public void setZylx(String zylx) {
		this.zylx = zylx;
	}

	public String getZylxname() {
		return zylxname;
	}

	public void setZylxname(String zylxname) {
		this.zylxname = zylxname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPcurl() {
		return pcurl;
	}

	public void setPcurl(String pcurl) {
		this.pcurl = pcurl;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	/**
	* 附件信息
	*/
	@TableField(exist = false)
	private JSONArray files;

	public JSONArray getFiles() {
		return files;
	}

	public void setFiles(JSONArray files) {
		this.files = files;
	}



	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SysZnhd{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", content='" + content + '\'' +
				", createtime=" + createtime +
				", updatetime=" + updatetime +
				", createuser='" + createuser + '\'' +
				", updateuser='" + updateuser + '\'' +
				", type='" + type + '\'' +
				", pcurl='" + pcurl + '\'' +
				", zylx='" + zylx + '\'' +
				", zylxname='" + zylxname + '\'' +
				", files=" + files +
				'}';
	}
}
