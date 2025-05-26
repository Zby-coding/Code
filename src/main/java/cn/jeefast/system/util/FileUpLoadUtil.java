package cn.jeefast.system.util;


import cn.jeefast.system.entity.TMaterialFile;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class FileUpLoadUtil {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	private static SimpleDateFormat FORMAT = new SimpleDateFormat("yyyyMMdd");
	
//	@Value("${custom.fileUplpadURL}")
//	private static String PATH;
	
	
	
	
	/**
	 *
	 * @Title: upload   
	 * @Description: TODO(多文件)   
	 * @param: @param request1
	 * @param: @param request
	 * @param: @return
	 * @param: @throws IOException      
	 * @return: Object      
	 * @throws
	 */
	public Object upload(HttpServletRequest request1, MultipartHttpServletRequest request) throws IOException {
		String ctxPath = request1.getSession().getServletContext().getRealPath("/");
		List<MultipartFile> files = request.getFiles("file");
		if (files.size() > 0) {
			for (MultipartFile multipartFile : files) {
				handleFileUpload(ctxPath, multipartFile);
			}
		}
		return "";
	}
	
	/**
	 * @param dataPath
	 * @Title: handleFileUpload   
	 * @Description: TODO(单文件)   
	 * @param: @param file
	 * @param: @param request
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String handleFileUpload(String dataPath, MultipartFile file) {
		if (!file.isEmpty()) {
			OutputStream os = null;
			InputStream in = null;
			
			//以日期作为文件夹
			String str = FORMAT.format(new Date());
            try {
            	// 2、保存到临时文件
    			// 1K的数据缓冲
    			byte[] bs = new byte[1024];

    			// 读取到的数据长度
    			int len;
    			
    			// 输出的文件流保存到本地文件
    			File tempFile = new File(dataPath + File.separator + str);
    			if (!tempFile.exists()) {
    				tempFile.mkdirs();
    			}
//    			String str1 = tempFile.getPath() + File.separator  + file.getOriginalFilename();
    			os = new FileOutputStream(tempFile.getPath() + File.separator  + file.getOriginalFilename());
    			in = file.getInputStream();
    					
    			// 开始读取
    			while ((len = in.read(bs)) != -1) {
    				os.write(bs, 0, len);
    			}
    			
    			os.close();
    			in.close();
    			
                return str + "/" + file.getOriginalFilename();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "上传失败,";
            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败,";
            }
        } else {
            return "上传失败，因为文件为空.";
        }
	}
	
	/**
	 * @throws Exception 
	 * @param path
	 * @Title: downloadAttachments   
	 * @Description: TODO(下载)   
	 * @param: @param fileName
	 * @param: @param request
	 * @param: @param response
	 * @param: @throws IOException      
	 * @return: void      
	 * @throws
	 */
	public static void downloadAttachments(String plainpass, String path, String fileName, String filePath, TMaterialFile tMaterialFile, HttpServletResponse response)
			throws Exception {

		// 设置响应头，控制浏览器下载该文件
		response.setContentType("application/octet-stream");
		response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
		// 读取要下载的文件，保存到文件输入流
		System.out.println("pathpathpath"+path +"/"+fileName);
		FileInputStream in = new FileInputStream(path +"/"+fileName);
		// 创建输出流
		OutputStream out = response.getOutputStream();
//		// 创建缓冲区
		byte buffer[] = new byte[1024];
		int len = 0;
		// 循环将输入流中的内容读取到缓冲区当中
		while ((len = in.read(buffer)) > 0) {
			out.write(buffer, 0, len);
		}

		in.close();
		out.flush();
		out.close();
	}


	public static String downloadFromUrl(String url,String dir) {

		try {
			URL httpurl = new URL(url);
			String fileName = getFileNameFromUrl(url);
			System.out.println(fileName);
			File f = new File(dir + fileName);
			FileUtils.copyURLToFile(httpurl, f);
		} catch (Exception e) {
			e.printStackTrace();
			return "Fault!";
		}
		return "Successful!";
	}

	public static String getFileNameFromUrl(String url){
		String name = new Long(System.currentTimeMillis()).toString() + ".X";
		int index = url.lastIndexOf("/");
		if(index > 0){
			name = url.substring(index + 1);
			if(name.trim().length()>0){
				return name;
			}
		}
		return name;
	}



//	public static void downloadAttachmenttbScmsPwdDeviceBatchFiles(String path, String fileName, String filePath,TbScmsPwdDeviceBatchFile tMaterialFile, HttpServletResponse response,String addressParaDownload)
//			throws Exception {
////		String ste = path +"/"+ filePath;
//
//		File file = new File(path +"/"+ fileName);
//		// 如果文件不存在
//		if (!file.exists()) {
//			return;
//		}
//		// 设置响应头，控制浏览器下载该文件
//		response.setContentType("application/octet-stream");
//		response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
//		// 读取要下载的文件，保存到文件输入流
//		FileInputStream in = null;
////		if(.equals("801")) {//非密文件
////			in = new FileInputStream(path +"/"+fileName);
////		}else {//加密文件
////			TMaterialFile tMaterialFile2 =
////		}
//		if(tMaterialFile.getMbfklj().equals("801")) {//非密文件
//			in = new FileInputStream(path +"/"+fileName);
//		}else {//加密文件
//
//			String mibaoinfo = tMaterialFile.getMbinfo();
//			JSONArray jsonArray = JSONArray.parseArray(mibaoinfo);
//			String filename = "";
//			for(int i=0;i<jsonArray.size();i++){
//				JSONObject job = jsonArray.getJSONObject(i); // 遍历 jsonarray 数组，把每一个对象转成 json 对象
////				[{"fieldname":"files","originalname":"2d2a17c5-8c0b-409f-b41c-5ec420d4bcc8.txt","encoding":"binary","mimetype":"application/octet-stream","filename":"69d21485-d035-4517-a4e4-be746e880f89.txt","path":"temp\\69d21485-d035-4517-a4e4-be746e880f89.txt","size":3840}]
//				filename = job.getString("filename");
//			}
//			SecurityUtility impl = new SecurityUtility();
////			String url= "http://127.0.0.1:1257/api/v1/frame/download/temp/";
//			String url=addressParaDownload;
//
//			impl.downloadFile(url+filename, "d:/'"+filename+"'");
//			in = new FileInputStream("d:/'"+filename+"'");
//
//		}
//
//		// 创建输出流
//		OutputStream out = response.getOutputStream();
//		// 创建缓冲区
//		byte buffer[] = new byte[1024];
//		int len = 0;
//		// 循环将输入流中的内容读取到缓冲区当中
//		while ((len = in.read(buffer)) > 0) {
//			out.write(buffer, 0, len);
//		}
//
//		in.close();
//		out.flush();
//		out.close();
//	}
//
//
//	/* Tbscmsinstfile */
//	public static void downloadAttachmentTbscmsinstfiles(String path, String fileName, String filePath,Tbscmsinstfile tMaterialFile, HttpServletResponse response,String addressParaDownload)
//			throws Exception {
////		String ste = path +"/"+ filePath;
//
//		File file = new File(path +"/"+ fileName);
//		// 如果文件不存在
//		if (!file.exists()) {
//			return;
//		}
//		// 设置响应头，控制浏览器下载该文件
//		response.setContentType("application/octet-stream");
//		response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
//		// 读取要下载的文件，保存到文件输入流
//		FileInputStream in = null;
////		if(.equals("801")) {//非密文件
////			in = new FileInputStream(path +"/"+fileName);
////		}else {//加密文件
////			TMaterialFile tMaterialFile2 =
////		}
//		if(tMaterialFile.getMbfklj().equals("801")) {//非密文件
//			in = new FileInputStream(path +"/"+fileName);
//		}else {//加密文件
//
//			String mibaoinfo = tMaterialFile.getMbinfo();
//			JSONArray jsonArray = JSONArray.parseArray(mibaoinfo);
//			String filename = "";
//			for(int i=0;i<jsonArray.size();i++){
//				JSONObject job = jsonArray.getJSONObject(i); // 遍历 jsonarray 数组，把每一个对象转成 json 对象
////				[{"fieldname":"files","originalname":"2d2a17c5-8c0b-409f-b41c-5ec420d4bcc8.txt","encoding":"binary","mimetype":"application/octet-stream","filename":"69d21485-d035-4517-a4e4-be746e880f89.txt","path":"temp\\69d21485-d035-4517-a4e4-be746e880f89.txt","size":3840}]
//				filename = job.getString("filename");
//			}
//			SecurityUtility impl = new SecurityUtility();
////			String url= "http://127.0.0.1:1257/api/v1/frame/download/temp/";
//			String url=addressParaDownload;
//
//			impl.downloadFile(url+filename, "d:/'"+filename+"'");
//			in = new FileInputStream("d:/'"+filename+"'");
//
//		}
//
//		// 创建输出流
//		OutputStream out = response.getOutputStream();
//		// 创建缓冲区
//		byte buffer[] = new byte[1024];
//		int len = 0;
//		// 循环将输入流中的内容读取到缓冲区当中
//		while ((len = in.read(buffer)) > 0) {
//			out.write(buffer, 0, len);
//		}
//
//		in.close();
//		out.flush();
//		out.close();
//	}
}
