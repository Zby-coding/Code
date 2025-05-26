package cn.jeefast.system.entity;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 */
@TableName("tb_scms_file")
public class TMaterialFile extends Model<TMaterialFile> {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.UUID)
    private String id;

    //存储状态
    private Integer smodelidstate;
    //附件名
    private String sfilename;
    //附件路径
    private String sfilelength;
    //存储源文件名
    private String saccessoryname;
    //新表父id
    private String parentid;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Integer getSmodelidstate() {
		return smodelidstate;
	}

	public void setSmodelidstate(Integer smodelidstate) {
		this.smodelidstate = smodelidstate;
	}
	
	public String getSfilename() {
		return sfilename;
	}

	public void setSfilename(String sfilename) {
		this.sfilename = sfilename;
	}
	
	public String getSfilelength() {
		return sfilelength;
	}

	public void setSfilelength(String sfilelength) {
		this.sfilelength = sfilelength;
	}
	
	public String getSaccessoryname() {
		return saccessoryname;
	}

	public void setSaccessoryname(String saccessoryname) {
		this.saccessoryname = saccessoryname;
	}
	
	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	
	

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TMaterialFile{" +
				"id='" + id + '\'' +
				", smodelidstate=" + smodelidstate +
				", sfilename='" + sfilename + '\'' +
				", sfilelength='" + sfilelength + '\'' +
				", saccessoryname='" + saccessoryname + '\'' +
				", parentid='" + parentid + '\'' +
				'}';
	}


}
