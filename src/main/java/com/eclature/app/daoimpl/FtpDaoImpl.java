/**
 * 
 */
package com.eclature.app.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.eclature.app.dao.FtpDao;
import com.eclature.bean.FtpBean;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * @author malviyaa
 *
 */
@Repository
public class FtpDaoImpl implements FtpDao {

	
	@Autowired
	private JdbcTemplate template;
		
	@Override
	public void upload(String fileLocation ,FtpBean fb) throws Exception {
        String hostname = fb.getIpAddr();//"192.168.1.24";
        
        String username = fb.getUserName();//"malviyaa";
        String password =fb.getPassword();//"Welcome1"; 
        int portNo= fb.getPortNo();//22
        Session session = null;
        //String fileLocation = "C:\\Users\\malviyaa\\Desktop\\New folder\\artifacts.xml";
        String remoteDir = "/home/malviyaa/test/"; 
        JSch jsch = new JSch();
        
        
        System.out.println("Trying to connect.....");
        try {
            session = jsch.getSession(username, hostname, portNo);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(password);
            session.connect(); 
            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel; 
            sftpChannel.put(fileLocation, remoteDir );
  	      
            sftpChannel.exit();
            session.disconnect();
        } catch (JSchException e) {
            e.printStackTrace();  
        } catch (SftpException e) {
            e.printStackTrace();
        }
        System.out.println("Done !!");
	}
				
	
	@Override
	public List<FtpBean> getFtpDetails(int projectId ,int envId) {
		
		String getQuery="SELECT  destip , ftpuser ,ftppass , ftpport ,destloc, scriptloc  " + 
				"  FROM  ftp_details where project_id="+projectId+ " and env_id="+envId;
		
		return template.query(getQuery,new RowMapper<FtpBean>() {
            
			public FtpBean mapRow(ResultSet rs,
                    int rowNum) throws SQLException {
              FtpBean fb = new FtpBean();
                fb.setIpAddr(rs.getString(1)); 
                fb.setUserName(rs.getString(2)); 
                fb.setPassword(rs.getString(3));
              	fb.setPortNo(rs.getInt(4)); 
              	fb.setFileLocation(rs.getString(5));
              	fb.setScriptLocation(rs.getString(6)); 
              	return fb;
            }
          });
		
	}

	/* (non-Javadoc)
	 * @see com.eclature.app.dao.FtpDao#addFtpDetails(com.eclature.bean.FtpBean)
	 */
	@Override
	public int addFtpDetails(FtpBean fb) {
		String addQuery="insert into ftp_details(destip,ftpuser,ftppass,ftpport,destloc,script,scriptloc,createdby ,createdon) values(?,?,?,?,?,?,?,?,?)";
		
		return template.update(addQuery , new Object[] {fb.getIpAddr(),fb.getUserName(), fb.getPassword() ,fb.getPortNo(), 
						fb.getFileLocation(), fb.getScript(),fb.getScriptLocation(),fb.getCreatedBy(),fb.getCreatedOn()});
	}

	/* (non-Javadoc)
	 * @see com.eclature.app.dao.FtpDao#updateFtpDetails(com.eclature.bean.FtpBean)
	 */
	@Override
	public int updateFtpDetails(FtpBean fb) {
		String updateSql="UPDATE ftp_details SET destip  = ?, ftpuser  = ?, ftppass  = ?,ftpport  = ?, " + 
				" destloc  = ?, script  = ?, scriptloc  = ?, WHERE  project_id  = ? and env_id=?";
		return template.update(updateSql,fb.getIpAddr(),fb.getUserName(), fb.getPassword() ,fb.getPortNo(), 
						fb.getFileLocation(), fb.getScript(),fb.getScriptLocation());
	}

	/* (non-Javadoc)
	 * @see com.eclature.app.dao.FtpDao#deleteFtpDetails(com.eclature.bean.FtpBean)
	 */
	@Override
	public int deleteFtpDetails(int projectId , int envId) {
		String deleteSql="delete from ftp_details where project_id= ? and env_id=?";
		return template.update(deleteSql,projectId,envId);
	}

	

}
