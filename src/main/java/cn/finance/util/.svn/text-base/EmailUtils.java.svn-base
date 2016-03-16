package cn.finance.util;

import java.util.List;
import java.util.Properties;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import cn.finance.model.User;

public class EmailUtils {

	/**
	 * @param args
	 */
	public static void sendSimpleEmail(String applyAddress, String applyUser, List<User> mailUsers, List<User> ccs, String subject, String message){
		Properties props = System.getProperties();
		props.put("mail.smtp.localhost", "localhost");
		HtmlEmail email = new HtmlEmail();                
		email.setCharset("UTF-8"); 
		email.setHostName("smtp.darwinmarketing.com");                
		email.setSmtpPort(25);          
		email.setAuthentication("noreply@darwinmarketing.com", "huangpi@shanghai#darwin303");	   
		try {
			for (User mailUser: mailUsers){
				email.addTo(mailUser.getEmail(), mailUser.getName());
			}
			email.setFrom("noreply@darwinmarketing.com", "Contract System");
			if (ccs!=null){
				for (User cc: ccs){
					email.addCc(cc.getEmail(), cc.getName());
				}	
			}
			email.setSubject(subject);                
			email.setMsg(message);   
			email.send(); 
		}
		catch (EmailException e1) {
			e1.printStackTrace();
		}
	}
	/**
	 * @param args
	 */
	public static void sendEmail(Integer status, String applyAddress, String applyUser, List<User> mailUsers, 
			String projectName, String type, List<User> ccs){
		//Email 
		Properties props = System.getProperties();
		props.put("mail.smtp.localhost", "localhost");
		HtmlEmail email = new HtmlEmail();                
		email.setCharset("UTF-8"); 
		email.setHostName("smtp.darwinmarketing.com");                
		email.setSmtpPort(25);          
		email.setAuthentication("noreply@darwinmarketing.com", "huangpi@shanghai#darwin303");	 

		try {
			for (User mailUser: mailUsers){
				email.addTo(mailUser.getEmail(), mailUser.getName());
			}
			email.setFrom("noreply@darwinmarketing.com", "Contract System");
			for (User cc: ccs){
				email.addCc(cc.getEmail(), cc.getName());
			}
			if ("apply".equals(type)){
				if (status==11){
					email.setSubject("★☆请领取合同："+projectName);            
				} else if (status==10){
					email.setSubject("Please approve the contract："+projectName);       
				} else {
					StringBuffer sb = new StringBuffer();
					sb.append("★☆ 请");
					if (status ==9){
						sb.append("客户");
					}
					sb.append("审批合同:").append(projectName);
					email.setSubject(sb.toString());               
				}
				if (status==11){
					email.setHtmlMsg(projectName + "项目的合同审核通过，请BD同事去财务处领取盖好章的合同。");
				} else if (status==10){
					email.setHtmlMsg("The contract of " + projectName + " requires approval per logging in Contract Management Systegm.");
				} else {
					email.setHtmlMsg(projectName + " 项目的合同需要审批，请登录系统。 ");                
				}
			} else if ("deny".equals(type)){
				email.setSubject("★☆ " + projectName+" 合同有改动，已经被退回");                
				email.setMsg(projectName + "项目的合同有改动，已经被退回。 ");   
			}
			email.send(); 
		} 
		catch (EmailException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Properties props = System.getProperties();
		props.put("mail.smtp.localhost", "localhost");
		HtmlEmail email = new HtmlEmail();                
		email.setCharset("UTF-8"); 
		email.setHostName("smtp.darwinmarketing.com");                
		email.setSmtpPort(25);          
		email.setAuthentication("noreply@darwinmarketing.com", "huangpi@shanghai#darwin303");	   
		try {
			email.addTo("harry.zhu@darwinmarketing.com", "Harry");
			email.setFrom("contract@darwinmarketing.com", "Contract System");
			email.setSubject("test");                
			email.setMsg("message");   
			email.send(); 
		}
		catch (EmailException e1) {
			e1.printStackTrace();
		}
	}
}
