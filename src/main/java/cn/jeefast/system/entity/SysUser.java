package cn.jeefast.system.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.enums.IdType;

import cn.jeefast.common.validator.group.AddGroup;
import cn.jeefast.common.validator.group.UpdateGroup;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 系统用户
 * </p>
 */
@TableName("sys_user")
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

	@TableId(value="user_id", type= IdType.AUTO)
	private Long userId;
    /**
     * 用户名
     */
	@NotBlank(message="用户名不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String username;
    /**
     * 密码
     */
	@NotBlank(message="密码不能为空", groups = AddGroup.class)
	private String password;
    /**
     * 盐
     */
	private String salt;
    /**
     * 邮箱
     */
//	@NotBlank(message="邮箱不能为空", groups = {AddGroup.class, UpdateGroup.class})
//	@Email(message="邮箱格式不正确", groups = {AddGroup.class, UpdateGroup.class})
	private String email;
    /**
     * 手机号
     */
	private String mobile;
    /**
     * 状态  0：禁用   1：正常
     */
	private Integer status;
    /**
     * 班级ID
     */
	@TableField("dept_id")
//	@NotNull(message="班级不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private Long deptId;
	
	/**
	 * 班级名称
	 */
	@TableField(exist=false)
	private String deptName;
	
	/**
	 * 角色ID列表
	 */
	@TableField(exist=false)
	private List<Long> roleIdList;
	
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
	
	/**
	 * 创建者ID
	 */
	@TableField(exist=false)
	private Long createUserId;

	/**
	 * 人员角色
	 */
	@TableField(exist=false)
	private String roalArraystr;

	/**
	 * 头像路径
	 */
	@TableField(exist=false)
	private String photopath;
	
	/**
	 * qq
	 */
	private String qq;
	
	/**
	 * 身份证号
	 */
	private String idnumb;
	
	/**
	 * 微信号
	 */
	private String wxnub;
	
	/**
	 * 出生日期
	 */
	private String birthday;
	
	/**
	 * 政治面貌
	 */
	private String political;
	
	/**
	 * 个人简历
	 */
	private String ramark;
	
	/**
	 * 会费
	 */
	private BigDecimal membership;

	private String stadynum;//学号/工号
	private String realname;//真名
	private String college;//学院
	private String major;//专业
	private String classinfo;//班级
	private String grade;//年级
	private String photo;//个人照片
	private String type;//人员类型

	private String address;

	private String xb;

	private String zzmm;

	private String jtzz;

	private String bzr;

	public String getBzr() {
		return bzr;
	}

	public void setBzr(String bzr) {
		this.bzr = bzr;
	}

	public String getJtzz() {
		return jtzz;
	}

	public void setJtzz(String jtzz) {
		this.jtzz = jtzz;
	}

	public String getZzmm() {
		return zzmm;
	}

	public void setZzmm(String zzmm) {
		this.zzmm = zzmm;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhotopath() {
		return photopath;
	}

	public void setPhotopath(String photopath) {
		this.photopath = photopath;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getStadynum() {
		return stadynum;
	}

	public void setStadynum(String stadynum) {
		this.stadynum = stadynum;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getClassinfo() {
		return classinfo;
	}

	public void setClassinfo(String classinfo) {
		this.classinfo = classinfo;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public BigDecimal getMembership() {
		return membership;
	}

	public void setMembership(BigDecimal membership) {
		this.membership = membership;
	}

	public String getRamark() {
		return ramark;
	}

	public void setRamark(String ramark) {
		this.ramark = ramark;
	}
	
	public String getPolitical() {
		return political;
	}

	public void setPolitical(String political) {
		this.political = political;
	}
	
	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	public String getWxnub() {
		return wxnub;
	}

	public void setWxnub(String wxnub) {
		this.wxnub = wxnub;
	}
	
	public String getIdnumb() {
		return idnumb;
	}

	public void setIdnumb(String idnumb) {
		this.idnumb = idnumb;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	public List<Long> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<Long> roleIdList) {
		this.roleIdList = roleIdList;
	}
	
	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public String getRoalArraystr() {
		return roalArraystr;
	}

	public void setRoalArraystr(String roalArraystr) {
		this.roalArraystr = roalArraystr;
	}

	@Override
	protected Serializable pkVal() {
		return this.userId;
	}

	@Override
	public String toString() {
		return "SysUser{" +
				"userId=" + userId +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", salt='" + salt + '\'' +
				", email='" + email + '\'' +
				", mobile='" + mobile + '\'' +
				", status=" + status +
				", deptId=" + deptId +
				", deptName='" + deptName + '\'' +
				", roleIdList=" + roleIdList +
				", createTime=" + createTime +
				", createUserId=" + createUserId +
				", roalArraystr='" + roalArraystr + '\'' +
				", photopath='" + photopath + '\'' +
				", qq='" + qq + '\'' +
				", idnumb='" + idnumb + '\'' +
				", wxnub='" + wxnub + '\'' +
				", birthday='" + birthday + '\'' +
				", political='" + political + '\'' +
				", ramark='" + ramark + '\'' +
				", membership=" + membership +
				", stadynum='" + stadynum + '\'' +
				", realname='" + realname + '\'' +
				", college='" + college + '\'' +
				", major='" + major + '\'' +
				", classinfo='" + classinfo + '\'' +
				", grade='" + grade + '\'' +
				", photo='" + photo + '\'' +
				", type='" + type + '\'' +
				", address='" + address + '\'' +
				", xb='" + xb + '\'' +
				", zzmm='" + zzmm + '\'' +
				", jtzz='" + jtzz + '\'' +
				", bzr='" + bzr + '\'' +
				", files=" + files +
				'}';
	}
}
