/** 
 * projectName: gray-blog 
 * fileName: AbstractUploader.java 
 * packageName: com.blog.gray.fileserver.uploader 
 * date: Nov 17, 20214:54:41 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.fileserver.uploader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import com.blog.gray.fileserver.util.ExceptionUtil;
import com.blog.gray.fileserver.util.ExceptionUtil.ErrorCodeEnum;
import com.blog.gray.fileserver.util.FileUtil;

/**
 * @title: AbstractUploader.java
 * @package com.blog.gray.fileserver.uploader
 * @description: 抽象 文件上传服务
 * @author: Zjh
 * @date: Nov 17, 2021 4:54:41 PM 
 * @version: V1.0   
 */
public abstract class AbstractUploader implements Uploader {
	
	public AbstractUploader() {	}
	
	/**   
	 * @title: upload
	 * @description: 上传文件
	 * @param file 文件
	 * @throws  
	 * @see com.blog.gray.fileserver.uploader.Uploader#upload(org.springframework.web.multipart.MultipartFile)     
	 */
	@Override
	public String upload(MultipartFile file) {
		if (!checkFileFormat(file))
			throw ExceptionUtil.toException(ErrorCodeEnum.ERROR_FILETYPE);
		
		String rootPath = getRootPath();
        
	    // 文件夹路径不存在则需要创建文件夹路径
	    File dir = new File(rootPath);
	    if (!dir.exists()) {
	        dir.mkdirs();
	    }
	
	    // 最终文件名
	    String fileRename = FileUtil.renameToUUID(file.getOriginalFilename());
	    File realFile = new File(rootPath + File.separator + fileRename);

	    try {
			FileUtils.copyInputStreamToFile(file.getInputStream(), realFile);
		} catch (IOException e) {
			throw ExceptionUtil.toException(ErrorCodeEnum.CANNOT_SAVE);
		}
        
        return fileRename;
	}

	/**   
	 * @title: upload
	 * @description: 上传多个文件
	 * @param files 文件数组
	 * @see com.blog.gray.fileserver.uploader.Uploader#upload(org.springframework.web.multipart.MultipartFile[])     
	 */
	@Override
	public List<String> upload(MultipartFile[] files) {
		List<String> filenames = new ArrayList<>();
		
		for (MultipartFile file : files)
			filenames.add(upload(file));
		
		return filenames;
	}

	
	/**
	 *@title: checkFileFormat 
	 *@description: 检查文件格式
	 *@param file 文件
	 *@return true:合法  false:不合法
	 */
	protected abstract boolean checkFileFormat(MultipartFile file);
	
	/**
	 *@title: getRootPath 
	 *@description: 获取文件保存目录
	 *@return 路径
	 */
	protected abstract String getRootPath();
}
