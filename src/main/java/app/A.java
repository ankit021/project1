package app;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class A {

	public static void main(String[] args) {
		
	        String hostname = "192.168.1.24";
	        String username = "malviyaa";
	        String password = "Welcome1";
	        String fileLocation = "C:\\Users\\malviyaa\\Desktop\\New folder\\scm";
	        String remoteDir = "/home/malviyaa/test/"; 
	        //String fileName="RunWithSCM.java";
	        JSch jsch = new JSch();
	        Session session = null;
	        System.out.println("Trying to connect.....");
	        try {
	            session = jsch.getSession(username, hostname, 22);
	            session.setConfig("StrictHostKeyChecking", "no");
	            
	            session.setPassword(password);
	            session.connect(); 
	            Channel channel = session.openChannel("sftp");
	            channel.connect();
	            ChannelSftp sftpChannel = (ChannelSftp) channel; 
	            sftpChannel.put(fileLocation, remoteDir);
	  	      
	            sftpChannel.exit();
	            session.disconnect();
	        } catch (JSchException e) {
	            e.printStackTrace();  
	        } catch (SftpException e) {
	            e.printStackTrace();
	        }
	        System.out.println("Done !!");
	    }
	}

