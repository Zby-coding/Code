package cn.jeefast.system.controller;

import cn.jeefast.system.entity.SysUserRole;
import cn.jeefast.system.service.SysUserRoleService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

import cn.jeefast.common.utils.R;
import cn.jeefast.common.utils.ShiroUtils;
import cn.jeefast.system.entity.SysUser;
import cn.jeefast.system.service.SysUserService;
import cn.jeefast.system.service.SysUserTokenService;

import org.apache.commons.io.IOUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 登录相关
 *
 */
@RestController

public class SysLoginController {
	@Autowired
	private Producer producer;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserTokenService sysUserTokenService;
	@Autowired
	private SysUserRoleService sysUserRoleService;


	@RequestMapping("captcha.jpg")
	public void captcha(HttpServletResponse response)throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");

		//生成文字验证码
		String text = producer.createText();
		//生成图片验证码jian
		BufferedImage image = producer.createImage(text);
		//保存到shiro session
		ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);

		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
		IOUtils.closeQuietly(out);
	}

	/**
	 * 注册
	 */
	@RequestMapping(value = "/sys/regsave", method = RequestMethod.POST)
	public Map<String, Object> regsave(String sxh, String sname,
									   String xzraol, String susername, String spassword, String zcspassword
			, String college, String major, String classinfo, String grade)throws IOException {
		if(!spassword.equals(zcspassword)){
			return R.error().put("msg","两次密码输入不一直");
		}


		SysUser sysUser = new SysUser();
		sysUser.setUsername(susername);
		sysUser.setPassword(spassword);
		sysUser.setRealname(sname);
		sysUser.setStadynum(sxh);
		sysUser.setCollege(college);
		sysUser.setMajor(major);
		sysUser.setClassinfo(classinfo);
		sysUser.setGrade(grade);
		sysUser.setStatus(1);

		List<Long> a = new ArrayList<>();
		a.add((long)2);
//		if(xzraol.equals("1")){
//			a.add((long)1);
////			sysUserRole.setRoleId((long)1);
//		}else if(xzraol.equals("2")){
//			a.add((long)2);
////			sysUserRole.setRoleId((long)2);
//		}else if(xzraol.equals("3")){
//			a.add((long)3);
//		}else if(xzraol.equals("4")){
//			a.add((long)4);
//		}else if(xzraol.equals("5")){
//			a.add((long)5);
//		}else {
//			a.add((long)8);
//		}
		sysUser.setRoleIdList(a);


		sysUserService.save(sysUser);

//
//		List<SysUser> newsysUsers = sysUserService.selectList(new EntityWrapper<SysUser>().eq("username",susername));
//		SysUserRole sysUserRole = new SysUserRole();
//		sysUserRole.setUserId(newsysUsers.get(0).getUserId());
//		System.out.println("xzraolxzraolxzraol"+xzraol);
//		if(xzraol.equals("1")){
//			sysUserRole.setRoleId((long)1);
//		}else if(xzraol.equals("2")){
//			sysUserRole.setRoleId((long)2);
//		}else if(xzraol.equals("3")){
//			sysUserRole.setRoleId((long)3);
//		}else if(xzraol.equals("4")){
//			sysUserRole.setRoleId((long)4);
//		}else if(xzraol.equals("5")){
//			sysUserRole.setRoleId((long)5);
//		}
//		System.out.println("sysUserRolesysUserRolesysUserRole"+sysUserRole);
//		sysUserRoleService.insert(sysUserRole);

		return R.ok().put("msg","注册成功");
	}

	/**
	 * 登录
	 */
	@RequestMapping(value = "/sys/login", method = RequestMethod.POST)
	public Map<String, Object> login(String username, String password, String captcha)throws IOException {
		System.out.println("usernameusernameusername"+username);
		System.out.println("passwordpasswordpassword"+password);
		System.out.println("captchacaptchacaptchacaptcha"+captcha);
		String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
		if(!captcha.equalsIgnoreCase(kaptcha)){
			return R.error("验证码不正确");
		}

		//用户信息
		SysUser user = sysUserService.queryByUserName(username);
		System.out.println("useruseruseruser"+user);
		//账号不存在、密码错误
		if(user == null || !user.getPassword().equals(new Sha256Hash(password, user.getSalt()).toHex())) {
			return R.error("账号或密码不正确");
		}

		//账号锁定
		if(user.getStatus() == 0){
			return R.error("账号已被锁定,请联系管理员");
		}

		//生成token，并保存到数据库
		System.out.println("getUserIdgetUserIdgetUserId"+user.getUserId());
		R r = sysUserTokenService.createToken(user.getUserId());
		System.out.println(user.getUserId()+"rrrrrrrrrrrrrr"+r);
		return r;
	}
	
}
