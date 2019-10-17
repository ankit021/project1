/**
 * 
 */
package com.eclature.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eclature.app.service.FtpService;
import com.eclature.bean.FtpBean;

/**
 * @author malviyaa
 *
 */

@RestController
public class FtpController {

	@Autowired
	protected FtpService fs;
	
	@RequestMapping(value="/upload-ftpfile", method = RequestMethod.POST, consumes = {"application/json"})
	public void upload(@RequestParam("fileLocation")String filelocation ,@RequestBody FtpBean fb ) throws Exception {
		
		fs.upload(filelocation , fb );
		
		
	}
	
	@GetMapping("/get-ftpdetails")
	public List<FtpBean> showDetails(@RequestParam("projId")int projectId ,@RequestParam("envId")int envId) {
		return fs.getFtpDetails(projectId, envId);
	}
	
	@RequestMapping(value="/add-ftpdetails", method = RequestMethod.POST, consumes = {"application/json"})
	public int addFtpDetails(@RequestBody FtpBean fb) {
	
		return fs.addFtpDetails(fb);
		
		
	// edit and remove controller pending...	
		
		
	}		
}
