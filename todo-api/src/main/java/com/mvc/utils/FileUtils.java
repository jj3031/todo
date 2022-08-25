package com.mvc.utils;



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mvc.todoapi.dao.img.ImgDAO;
import com.mvc.todoapi.domain.Img;



@Service
public class FileUtils {
	
	
	@Value("${file.path}")
	private String fileRealPath;
	
	@Autowired
	private ImgDAO imgDAO;
	
    public String uploadFiles(List<MultipartFile> multipartFiles, String id, String flag) throws Exception {
    	
        
    	System.out.println("uploadFiles start");
    	
    	// 파일 업로드 경로 생성
    	List<String> list = new ArrayList<String>();
        for (MultipartFile multipartFile : multipartFiles) {
        	
        	System.out.println(multipartFile.getOriginalFilename());
            String origFilename = multipartFile.getOriginalFilename();
            if (origFilename == null || "".equals(origFilename)) continue;
            String fileName = FileNameUtils.fileNameConvert(origFilename);
            
            Img img = new Img();
            img.setContentsFlag(flag);
            img.setContentsNo(id);
            img.setFileName(fileName);
            imgDAO.addImg(img);
            list.add(fileName);
            try {
                File file = new File(fileRealPath+"uploadFiles/", fileName);
                multipartFile.transferTo(file);

                // 파일 권한 설정(쓰기, 읽기)
                file.setWritable(true);
                file.setReadable(true);

            } catch (IOException e) {
            	e.printStackTrace();
            } 
            
            
        }//for 문 end 
       System.out.println(list);
       String mainImg = list.get(0);
       return mainImg;
    }//uploadFiles end
    
    
	public List<Img> getImgList(String todoId) throws Exception{

		return imgDAO.getImgList(todoId);
	}
	

	
	public void deleteImg(String todoId) throws Exception{

		imgDAO.deleteImg(todoId);
	}
    
    
}//class end