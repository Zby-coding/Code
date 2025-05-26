package cn.jeefast.system.service.impl;

import cn.jeefast.system.dao.TMaterialFileDao;
import cn.jeefast.system.entity.SysUser;
import cn.jeefast.system.entity.TMaterialFile;
import cn.jeefast.system.service.TMaterialFileService;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 */
@Service
public class TMaterialFileServiceImpl extends ServiceImpl<TMaterialFileDao, TMaterialFile> implements TMaterialFileService {

	@Autowired
	private TMaterialFileDao tMaterialFileDao;
	
	@Autowired
	private TMaterialFileService tMaterialFileService;

	@Value("${upload.flespath}")
	public String flespath;

	/**
	 * 设置文件爹id
	 * @param filesOld
	 * @param parentid
	 */
	@Override
	public void setTMaterialFilePrintId(JSONArray filesOld, String parentid){
		if(!filesOld.isEmpty()){
			for(int i=0;i<filesOld.size();i++) {
				Map<String,Object> files = filesOld.getJSONObject(i);
				TMaterialFile tMaterialFile = getTMaterialFile(files.get("filePath")+"");
				tMaterialFile.setParentid(parentid);
				if(tMaterialFile != null){
					tMaterialFileService.updateById(tMaterialFile);
				}
			}
		}
	}


	public TMaterialFile getTMaterialFile(String filePath){
		TMaterialFile tMaterialFile = tMaterialFileService.selectOne(new EntityWrapper<TMaterialFile>().eq("sfilename",filePath));
		return tMaterialFile;
	}

	@Override
	public Page<TMaterialFile> queryPageList(Page<TMaterialFile> page, Map<String, Object> map) {
		page.setRecords(tMaterialFileDao.queryPageList(page, map));
		return page;
	}

	@Override
	public void deleteBatch(String[] ids) {
		for(String id : ids) {
			deletePreachFile(id);
		}
		
		tMaterialFileDao.deleteBatch(ids);
	}

	@Override
	public String uploadFile(MultipartFile file) {
//		String dataPath = sysSwitchService.selectOne(new EntityWrapper<SysSwitch>()).getFileuplpadurl();
//		return FileUpLoadUtil.handleFileUpload(dataPath, file);
		return null;
	}

	@Override
	public void deletePreachFile(String id) {
//		String dataPath = sysSwitchService.selectOne(new EntityWrapper<SysSwitch>()).getFileuplpadurl();
		String dataPath = "";
		try {
			TMaterialFile tMaterialFile = tMaterialFileDao.selectById(id);
			String path = tMaterialFile.getSfilename();

			File file = new File(dataPath + "/" + path);
			
			 // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
			if (file.exists() && file.isFile()) {
				file.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void downFile(String id, HttpServletResponse response) {
////		String dataPath = sysSwitchService.selectOne(new EntityWrapper<SysSwitch>()).getFileuplpadurl();
//		String dataPath ="";
////		SysSwitch addressParaDownload = sysSwitchService.selectOne(new EntityWrapper<SysSwitch>());
////		String plainpass = addressParaDownload.getPlainpass();//密标路径地址
//		TMaterialFile tMaterialFile = tMaterialFileDao.selectById(id);
//		if(!StringUtils.isEmpty(tMaterialFile.getSfilename())) {
//			try {
//				FileUpLoadUtil.downloadAttachments(plainpass,dataPath, tMaterialFile.getSfilename(), tMaterialFile.getSfilename(),tMaterialFile, response,addressParaDownload);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}

	@Override
	public Map<String, String> importPsot(HttpServletRequest request) {
//		String dataPath = sysSwitchService.selectOne(new EntityWrapper<SysSwitch>()).getFileuplpadurl();
		String dataPath = flespath;
		Map<String, String> map = null;
		boolean ff = false;
		// 得到上传文件的保存目录，将上传文件存放在WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
		String prefix = new SimpleDateFormat("yyyyMMdd").format(new Date());
		File dir = new File(dataPath + "/" + prefix);
		File saveFileDir = new File(dataPath);
		if (!saveFileDir.exists()) {
			// 创建目录
			saveFileDir.mkdirs();
			if (!dir.exists()) {
				dir.mkdirs();
			}
		} else {
			if (!dir.exists()) {
				dir.mkdirs();
			}
		}
		try {
			// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
					request.getSession().getServletContext());
			// 检查form中是否有enctype="multipart/form-data"
			if (multipartResolver.isMultipart(request)) {
				// 将request变成多部分request
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				// 获取multiRequest 中所有的文件名
				List<MultipartFile> multipartFiles = multiRequest.getFiles("fileList");
				System.out.println("multipartFilesmultipartFiles"+multipartFiles);
				for(MultipartFile file : multipartFiles) {
					System.out.println("getOriginalFilenamegetOriginalFilename"+file.getOriginalFilename());
					// 一次遍历所有文件
					if (file != null) {
						String fileName = file.getOriginalFilename();
						String fileid = UUID.randomUUID().toString().replace("-", "");
						 //          System.out.println("fileNamefileName"+fileName);
						// 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
						fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
						// 得到存文件的扩展名
						String suffix  = makeFileName(fileName);
						String saveFileName = UUID.randomUUID() + suffix;
						String path = prefix + "/" + saveFileName;
						file.transferTo(new File(dir + "/" + saveFileName));
						/* 存储文件信息 */
						map = new HashMap<>();
						map.put("fileName", fileName);
						map.put("path", path);
						map.put("ids", saveFileName);
						map.put("id", fileid);
						map.put("dir", dir+"");
						map.put("mbfklj", mbfkljToName(request.getParameter("fileList111")));
						setFiles(path, dataPath + "/", file,request.getParameter("fileList111"),fileid );

						ff = true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.clear();
//			map.put("mibiaotest", "密标程序未启动");
			return map;
		}
		return map;
	}
	
	private String makeFileName(String fileName) {
		int lastIndex = fileName.lastIndexOf(".");

        if (lastIndex != -1) {
            return fileName.substring(lastIndex);
        } else {
            return "";
        }
	}
	
	private void setFiles(String sfilename,String sfilelength,MultipartFile files,String mbfklj,String fileid) throws Exception {
		TMaterialFile file =new TMaterialFile();
		file.setId(fileid);
		file.setSfilename(sfilename);
		file.setSfilelength(sfilelength);
		file.setSaccessoryname(files.getOriginalFilename());
		System.out.println("bbbbbbbbbbbbbbbb"+file);
		Integer a = tMaterialFileDao.insert(file);
		System.out.println("aaaaaaaaaa"+a);

	}

	@Override
	public void deleteFile(String filePath) {
//		String dataPath = sysSwitchService.selectOne(new EntityWrapper<SysSwitch>()).getFileuplpadurl();
		String dataPath = "";
		try {
			
			Wrapper<TMaterialFile> wrapper = new EntityWrapper<TMaterialFile>();
			wrapper.eq("sfilename", filePath);
			tMaterialFileDao.delete(wrapper);
			
			File file = new File(dataPath + "/" + filePath);
			 // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
			if (file.exists() && file.isFile()) {
				file.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void deleteFileByid(String id) {
//		String dataPath = sysSwitchService.selectOne(new EntityWrapper<SysSwitch>()).getFileuplpadurl();
		String dataPath = "";
		TMaterialFile tMaterialFile = tMaterialFileService.selectById(id);
		if(tMaterialFile != null) {
			try {
				
				Wrapper<TMaterialFile> wrapper = new EntityWrapper<TMaterialFile>();
				wrapper.eq("id", id);
				tMaterialFileDao.delete(wrapper);
				
				File file = new File(dataPath + "/" + tMaterialFile.getSfilename());
				 // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
				if (file.exists() && file.isFile()) {
					file.delete();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	public Map<String, String> importPsotfiles(HttpServletRequest request) {
//		String dataPath = sysSwitchService.selectOne(new EntityWrapper<SysSwitch>()).getFileuplpadurl();
		String dataPath = "";
		Map<String, String> map = null;
		boolean ff = false;
		// 得到上传文件的保存目录，将上传文件存放在WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
		String prefix = new SimpleDateFormat("yyyyMMdd").format(new Date());
		File dir = new File(dataPath + "/" + prefix);
		File saveFileDir = new File(dataPath);
		if (!saveFileDir.exists()) {
			// 创建目录
			saveFileDir.mkdirs();
			if (!dir.exists()) {
				dir.mkdirs();
			}
		} else {
			if (!dir.exists()) {
				dir.mkdirs();
			}
		}
		try {
			// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
					request.getSession().getServletContext());
			// 检查form中是否有enctype="multipart/form-data"
			if (multipartResolver.isMultipart(request)) {
				// 将request变成多部分request
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				// 获取multiRequest 中所有的文件名
				List<MultipartFile> multipartFiles = multiRequest.getFiles("fileListfiles");
				for(MultipartFile file : multipartFiles) {
					// 一次遍历所有文件
					if (file != null) {
						String fileName = file.getOriginalFilename();
						String fileid = UUID.randomUUID().toString().replace("-", "");
						 //          System.out.println("fileNamefileName"+fileName);
						// 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
						fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
						// 得到存文件的扩展名
						String suffix  = makeFileName(fileName);
						String saveFileName = UUID.randomUUID() + suffix;
						String path = prefix + "/" + saveFileName;
						file.transferTo(new File(dir + "/" + saveFileName));
						/* 存储文件信息 */
						map = new HashMap<>();
						map.put("fileName", fileName);
						map.put("path", path);
						map.put("ids", saveFileName);
						map.put("id", fileid);
						map.put("dir", dir+"");
						setFiles(path, dataPath + "/", file,request.getParameter("fileList111"),fileid );
					
						ff = true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@Override
	public String getIsoldFilesname(String id,String smodeltype) {
		String oldfeilsname = "";
		Wrapper<TMaterialFile> wrapper = new EntityWrapper<TMaterialFile>();
		wrapper.eq("smodelid", id);
		wrapper.eq("smodeltype", smodeltype);//分类条件旧数据防止重复id
		List<TMaterialFile> tbscmsfiles = selectList(wrapper);
		if(tbscmsfiles.size() >0) {
			for(int i=0;i<tbscmsfiles.size();i++) {
				String strname = tbscmsfiles.get(i).getSaccessoryname();//原附件名
				oldfeilsname += strname+',';
			}
		}
		return oldfeilsname;
	}
	@Override
	public String getIsoldFilespath(String id,String smodeltype) {
		String oldfilespath = ""; 
		Wrapper<TMaterialFile> wrapper = new EntityWrapper<TMaterialFile>();
		wrapper.eq("smodelid", id);
		wrapper.eq("smodeltype", smodeltype);//分类条件旧数据防止重复id
		List<TMaterialFile> tbscmsfiles = selectList(wrapper);
		if(tbscmsfiles.size() >0) {
			for(int i=0;i<tbscmsfiles.size();i++) {
				String strpath = tbscmsfiles.get(i).getSfilename();//附件路径及文件名
				oldfilespath += strpath+',';
			}
		}
		return oldfilespath;
	}
	
	/**
	 * 密标转换
	 * @param mbfklj
	 * @return
	 */
	@Override
	public String mbfkljToName(String mbfklj){
//		if (mbfklj.equals("801")) {
//			return "非密";
//		}else{
//			Map<String, Object>map=new HashMap<>();
//			map.put("skey", mbfklj);
//			return ubDictionaryDao.selectByMap(map).get(0).getSvalue();
//		}
		return null;
		
	}

	@Override
	public Boolean verification(String mbbjk,SysUser sysUser) {
//		//顺序越大等级越高
//		Wrapper<UbDictionary>wrapper=new EntityWrapper<>();
//		wrapper.eq("stype", 27);
//		wrapper.orderBy("iorder",true);
//		List<UbDictionary>mb=ubDictionaryDao.selectList(wrapper);
//		List<String>list=new ArrayList<>();
//		list.add("801");
//		Integer filter=1;//为0就添加进集合
//		for (UbDictionary ubDictionary : mb) {
//			if (ubDictionary.getSkey().equals(sysUser.getIdoctype())||filter==0) {
//				filter=0;
//				list.add(ubDictionary.getSkey());
//			}
//		}
//		if (list.contains(mbbjk)) {
//			return true;
//		}
		
		return false;
	}
	
	
	
	
	
	
	
}
