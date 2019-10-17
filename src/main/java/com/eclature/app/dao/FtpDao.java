package com.eclature.app.dao;

import java.util.List;

import com.eclature.bean.FtpBean;

public interface FtpDao {
	public void upload(String fileLocation, FtpBean fb) throws Exception; 
		
	public List<FtpBean> getFtpDetails(int projectId ,int envId );
	
	public int addFtpDetails(FtpBean fb);
	
	public int updateFtpDetails(FtpBean fb);
	
	public int deleteFtpDetails(int projectId , int envId);
}
