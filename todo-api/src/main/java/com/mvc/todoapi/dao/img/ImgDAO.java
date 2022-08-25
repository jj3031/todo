package com.mvc.todoapi.dao.img;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;

import com.mvc.todoapi.domain.Img;


@Mapper
public interface ImgDAO {
	
	public void addImg(Img img) throws Exception;
	
	public List<Img>getImgList(String contentsNo)  throws Exception;
	
	public void deleteImg(String contentsNo) throws Exception;
}
