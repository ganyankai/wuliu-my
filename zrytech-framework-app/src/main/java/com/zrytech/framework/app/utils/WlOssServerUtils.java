package com.zrytech.framework.app.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.GetObjectRequest;
import com.zrytech.framework.common.config.OssConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;

/***
 * 
 * @author codeLife
 * 
 * 阿里云OSS管理工具
 *
 */
@Component
@Slf4j
public class WlOssServerUtils {

     private static OssConfiguration ossConfiguration;

	@Autowired
	public void setOssConfiguration(OssConfiguration ossConfiguration) {
		this.ossConfiguration = ossConfiguration;
	}

	public static void deleteFile(String objectKey) throws ClientException,OSSException {
		OSSClient ossClient = new OSSClient(ossConfiguration.getEnd_point(), ossConfiguration.getAccess_key_id(), ossConfiguration.getAccess_key_secret());
		boolean exists = ossClient.doesObjectExist(ossConfiguration.getBucket_name(), objectKey);
		if(exists) {
			ossClient.deleteObject(ossConfiguration.getBucket_name(), objectKey);
			ossClient.shutdown();
		}
	}
	
	public static void uploadFile(String objectKey,File file)throws ClientException,OSSException {
		OSSClient ossClient = new OSSClient(ossConfiguration.getEnd_point(), ossConfiguration.getAccess_key_id(), ossConfiguration.getAccess_key_secret());
		ossClient.putObject(ossConfiguration.getBucket_name(), objectKey, file);
		ossClient.shutdown();
	}
	public static void uploadFileInputStream(String objectKey,InputStream stream)throws ClientException,OSSException {
		OSSClient ossClient = new OSSClient(ossConfiguration.getEnd_point(), ossConfiguration.getAccess_key_id(), ossConfiguration.getAccess_key_secret());
		ossClient.putObject(ossConfiguration.getBucket_name(), objectKey, stream);
		ossClient.shutdown();
	}
	
	public static void downloadFile(String objectKey) throws ClientException,OSSException {
		OSSClient ossClient = new OSSClient(ossConfiguration.getEnd_point(), ossConfiguration.getAccess_key_id(), ossConfiguration.getAccess_key_secret());
		ossClient.getObject(new GetObjectRequest(ossConfiguration.getBucket_name(),objectKey), new File("tempfile/"+objectKey));
		ossClient.shutdown();
	}
	
    private static File createSampleFile() throws IOException {
        File file = File.createTempFile("oss-java-sdk-", ".txt");
        file.deleteOnExit();
        Writer writer = new OutputStreamWriter(new FileOutputStream(file));
        writer.write("abcdefghijklmnopqrstuvwxyz\n");
        writer.write("0123456789011234567890\n");
        writer.close();

        return file;
    }
	
	public static void main(String [] args) {
		    //uploadFile("oss-java-sdk-123.txt",createSampleFile());
			//downloadFile("oss-java-sdk-123.txt");
			deleteFile("13797095517/01379709551798E7F587634E15246501125010.jpeg");
	}
	public static String uploadVideo(String imgUrl) {
		String fileType=imgUrl.substring(imgUrl.lastIndexOf("."));
		StringBuffer urlPath = new StringBuffer();
		urlPath.append("https://");
		urlPath.append(ossConfiguration.getBucket_name());
		urlPath.append(".");
		urlPath.append(ossConfiguration.getEnd_point());
		urlPath.append("/");
		//CodeUtils codeUtils = new CodeUtils();
		String objectKey = "video/"+System.currentTimeMillis()+fileType;
		try {
			InputStream inputStream = new FileInputStream(imgUrl);
			com.zrytech.framework.common.util.OssServerUtils.uploadFileInputStream(objectKey,inputStream);
			urlPath.append(objectKey);
		}catch(OSSException | ClientException |IOException ex) {
			log.info("上传文件失败，请检查。"+ ex.getMessage());
		}
		log.info("path>>>>>>>>>>>>>"+urlPath.toString());
		return urlPath.toString();
	}
}
