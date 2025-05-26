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
 * 视频資源信息表
 * </p>
 */
@TableName("sys_shiyan")
public class SysShiyan extends Model<SysShiyan> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
	@TableId(type = IdType.UUID)
	private String id;
    /**
     * 视频資源标题
     */
	private String name;
    /**
     * 视频資源内容
     */
	private String content;
    /**
     * 所属科目
     */
	private String type;
    /**
     * 任课老师
     */
	private String teacher;
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

	/**
	 * 附件信息
	 */
	@TableField(exist = false)
	private JSONArray files;

	/**
	 * 附件信息
	 */
	@TableField(exist = false)
	private  String video;


	@TableField(exist = false)
	private  String typename;

	private  String zuozhe;


	public String getZuozhe() {
		return zuozhe;
	}

	public void setZuozhe(String zuozhe) {
		this.zuozhe = zuozhe;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public JSONArray getFiles() {
		return files;
	}

	public void setFiles(JSONArray files) {
		this.files = files;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
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
		return "SysShiyan{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", content='" + content + '\'' +
				", type='" + type + '\'' +
				", teacher='" + teacher + '\'' +
				", createtime=" + createtime +
				", updatetime=" + updatetime +
				", createuser='" + createuser + '\'' +
				", updateuser='" + updateuser + '\'' +
				", files=" + files +
				", video='" + video + '\'' +
				", typename='" + typename + '\'' +
				", zuozhe='" + zuozhe + '\'' +
				'}';
	}
}
