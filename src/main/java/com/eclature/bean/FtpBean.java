package com.eclature.bean;

/**
 * @author malviyaa
 *
 */

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class FtpBean  {

	private int ftpId;
	private int projId;
	private int envId;
	private int portNo;
	private String fileLocation; //where the project is located
	private String ipAddr;
	private String userName;
	private String password;
	private String script;
	private String scriptLocation; // for filename.sh is located
	private String createdBy;
	private Timestamp createdOn;
	private String modlastBy;
	private Timestamp modLastOn;
	
	
	
	
}
