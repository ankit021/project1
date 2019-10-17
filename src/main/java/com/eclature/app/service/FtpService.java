/**
 * 
 */
package com.eclature.app.service;

import java.util.List;

import com.eclature.bean.FtpBean;

/**
 * @author malviyaa
 *
 */
public interface FtpService {
	
	public void upload(String fileLocation, FtpBean fb) throws Exception; 
	
	public List<FtpBean> getFtpDetails(int projectId ,int envId );
	
	public int addFtpDetails(FtpBean fb);
	
	public int editFtpDetails(FtpBean fb);
	
	public int removeFtpDetails(int projectId , int envId);

}
