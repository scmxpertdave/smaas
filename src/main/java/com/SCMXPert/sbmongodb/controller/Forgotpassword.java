package com.SCMXPert.sbmongodb.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.SCMXPert.sbmongodb.document.Response;
import com.SCMXPert.sbmongodb.document.User;
import com.SCMXPert.sbmongodb.repository.UserRepository;

//import jakarta.servlet.http.HttpServletRequest;

//import com.SCMXPert.sbmongodb.repository.impl.MailSenderService;

//import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.MissingServletRequestParameterException;



@Controller

@RequestMapping("/SCMXPert")
@CrossOrigin(origins = { "https://www.smaas.live", "https://smaas.live","http://172.17.211.224:3000","http://127.0.0.1:8081","https://www.smaas.org" })

public class Forgotpassword {

	@Autowired
	private UserRepository userService;
	
	@Autowired
	private MailSenderService emailService;
	

//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//		return bCryptPasswordEncoder;
//	}

	
	
@ResponseBody
@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
@PostMapping("/Forgot")
	public Response processForgotPasswordForm(@RequestBody User tr, HttpServletRequest request) {
	
	Response resp = new Response();
	
	User user = userService.findByEmail(tr.getEmail());
	SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
	if(!user.equals(null)) {
		
		//User user1  = new User();
		user.setResettoken(UUID.randomUUID().toString());
		userService.save(user);
		
	//	String appUrl = request.getScheme() + "://" + request.getServerName();
		String appUrl="www.smaas-lb.de";
		
		// Email message
					
					//passwordResetEmail.setFrom("zahirbashafz@gmail.com");
					passwordResetEmail.setTo(user.getEmail());
					passwordResetEmail.setSubject("Password Reset Request");
					passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl
							+ "/reset?token=" + user.getResettoken());
					
					emailService.sendSimpleMailByString(user.getEmail(),"Password Reset Request",
							"To reset your password, click the link below:\n" + appUrl
							+ "/Smaas/PasswordReset.jsp?token=" + user.getResettoken());
					
					resp.setMessage("Link sent to your registered email address");
					resp.setStatus(true);
}else {
	
	resp.setMessage("Email_Id doesn't exists");
	resp.setStatus(false);
}
	return resp;
}


@ResponseBody
@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
@PostMapping("/reset")

public Response setNewPassword(@RequestBody User requestParams,RedirectAttributes redir) {
	System.out.println("requestParams "+requestParams);
	Response resp = new Response();
	User user2 = userService.findByresettoken(requestParams.getResettoken());
	//System.out.println("reset controller"+requestParams.getPassword());
	if(!user2.equals(null)) {
		System.out.println("reset controller if");
		//User resetuser = new User();
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String p = bCryptPasswordEncoder.encode(requestParams.getPassword());
		System.out.println("password"+p);
		user2.setPassword(p);
		user2.setResettoken("");
		
		userService.save(user2);	
		
		resp.setMessage("You have successfully reset your password.  You may now login.");
		resp.setStatus(true);
		
		return resp;
		
	}
	else {
		System.out.println("reset controller else");
		resp.setMessage("The User does'nt exists");
		resp.setStatus(false);
		
	}
	return resp;
}
 
}

