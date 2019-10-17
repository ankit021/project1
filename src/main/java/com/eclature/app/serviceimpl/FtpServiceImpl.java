/**
 * 
 */
package com.eclature.app.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eclature.app.dao.FtpDao;
import com.eclature.app.service.FtpService;
import com.eclature.bean.FtpBean;

/**
 * @author malviyaa
 *
 */

@Service
public class FtpServiceImpl implements FtpService {

	@Autowired
	protected FtpDao fd; 
	
	/* (non-Javadoc)
	 * @see com.eclature.app.service.FtpService#upload(java.lang.String)
	 */
	@Override
	public void upload(String fileLocation, FtpBean fb) throws Exception  {
			fd.upload(fileLocation , fb);
	}

	/* (non-Javadoc)
	 * @see com.eclature.app.service.FtpService#getFtpDetails()
	 */
	@Override
	public List<FtpBean> getFtpDetails(int projectId ,int envId) {
		return fd.getFtpDetails(projectId, envId);
	}

	/* (non-Javadoc)
	 * @see com.eclature.app.service.FtpService#addFtpDetails(com.eclature.bean.FtpBean)
	 */
	@Override
	public int addFtpDetails(FtpBean fb) {
		return fd.addFtpDetails(fb);
	}

	/* (non-Javadoc)
	 * @see com.eclature.app.service.FtpService#editFtpDetails(com.eclature.bean.FtpBean)
	 */
	@Override
	public int editFtpDetails(FtpBean fb) {
		// TODO Auto-generated method stub
		return fd.updateFtpDetails(fb);
	}

	/* (non-Javadoc)
	 * @see com.eclature.app.service.FtpService#removeFtpDetails(com.eclature.bean.FtpBean)
	 */
	@Override
	public int removeFtpDetails(int projectId , int envId) {
		
		return fd.deleteFtpDetails(projectId, envId);
	}

}
