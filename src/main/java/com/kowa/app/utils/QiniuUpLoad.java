package com.kowa.app.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.kowa.app.config.ProjectConfig;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

public class QiniuUpLoad {
	
	public static void upLoadForQiniu(MultipartFile file,CallBack call){
		//构造一个带指定Zone对象的配置类
		Configuration cfg = new Configuration(Zone.zone1());
		//...其他参数参考类注释
		UploadManager uploadManager = new UploadManager(cfg);
		//默认不指定key的情况下，以文件内容的hash值作为文件名
		String key = System.currentTimeMillis()+"";
		Auth auth = Auth.create(ProjectConfig.AccessKey, ProjectConfig.SecretKey);
		String upToken = auth.uploadToken(ProjectConfig.SpaceName);
		try {
		    Response response = uploadManager.put(file.getInputStream(),key,upToken,null, file.getContentType());
		    //解析上传成功的结果
		    DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
		    System.out.println(putRet.key);
		    System.out.println(putRet.hash);
		    call.success(ProjectConfig.imageBaseUrl+putRet.key);
		} catch (QiniuException ex) {
		    Response r = ex.response;
		    call.faild(r.toString());
		    System.err.println(r.toString());
		    try {
		        System.err.println(r.bodyString());
		        call.faild(r.toString());
		    } catch (QiniuException ex2) {
		        //ignore
		    	call.faild(ex2.toString());
		    }
		} catch (IOException e) {
			call.faild(e.toString());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	public interface CallBack{
	       void	success(String url);
	       void faild(String errorInfo);
	}
}
