package com.SCMXPert.sbmongodb.controller;
 
 
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
//import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;

//import com.SCMXPert.sbmongodb.document.SimpleMail;
import java.io.File;
import java.io.InputStream;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

//import it.ozimov.springboot.mail.model.Email;
import org.springframework.core.io.FileSystemResource;

@Service
public class MailSenderService {

	
    @Autowired
    private JavaMailSender mailSender;
    
    @Value("${imagepath}")
    private String imagepath;
    
	@Autowired
	private AmazonS3 amazonS3Client;
	
//    @Value("${s3.bucket2}")
//    private String bucket2;
    
	@Value("${application.bucket.name}")
	private String bucketName;
	
	@Value("${s3.bucket2}")
	private String archivedbucketName;  
    
    public void sendSimpleMail(String[] Email,String Subject,String Content) {
    	MimeMessage mimeMessage = mailSender.createMimeMessage();
    	//MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
    	try {
    		 
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			/*
			 * String to = Email; InternetAddress[] parse = InternetAddress.parse(to ,
			 * true); mimeMessage.setRecipients(javax.mail.Message.RecipientType.TO, parse);
			 */
            mimeMessageHelper.setTo(Email); 
            mimeMessageHelper.setSubject(Subject);
  		    mimeMessageHelper.setText(Content,true);
			/*
			 * mimeMessageHelper.setSubject(mail.getMailSubject());
			 * mimeMessageHelper.setFrom(mail.getMailFrom());
			 * mimeMessageHelper.setTo(mail.getMailTo());
			 * mail.setMailContent(geContentFromTemplate(mail.getModel()));
			 * mimeMessageHelper.setText(mail.getMailContent(), true);
			 */
 
            mailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
		  //SimpleMailMessage message = new SimpleMailMessage();
		  
		  
		  
		  //mailSender.send(message);
		 
        
		/*
		 * mailSender.send(new MimeMessagePreparator() { public void prepare(MimeMessage
		 * mimeMessage) throws MessagingException { MimeMessageHelper message = new
		 * MimeMessageHelper(mimeMessage, true, "UTF-8");
		 * message.setFrom("smaas.live@scmxpert.com"); message.setTo(Email);
		 * message.setSubject("my subject"); message.
		 * setText("my text <img src='https://www.w3schools.com/tags/smiley.gif'>",
		 * true); message.addInline("myLogo", new
		 * ClassPathResource("https://www.w3schools.com/tags/smiley.gif"));
		 * message.addAttachment("myDocument.pdf", new
		 * ClassPathResource("D:/DOWNLOAD/RoutesList-1568721754628")); } });
		 */
    }
    
    public void sendSimpleMailByString(String Email,String Subject,String Content) {
    	MimeMessage mimeMessage = mailSender.createMimeMessage();
    	//MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
    	try {
    		 
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			/*
			 * String to = Email; InternetAddress[] parse = InternetAddress.parse(to ,
			 * true); mimeMessage.setRecipients(javax.mail.Message.RecipientType.TO, parse);
			 */
            mimeMessageHelper.setTo(Email); 
            mimeMessageHelper.setSubject(Subject);
  		    mimeMessageHelper.setText(Content,true);
			/*
			 * mimeMessageHelper.setSubject(mail.getMailSubject());
			 * mimeMessageHelper.setFrom(mail.getMailFrom());
			 * mimeMessageHelper.setTo(mail.getMailTo());
			 * mail.setMailContent(geContentFromTemplate(mail.getModel()));
			 * mimeMessageHelper.setText(mail.getMailContent(), true);
			 */
 
            mailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
		  //SimpleMailMessage message = new SimpleMailMessage();
		  
		  
		  
		  //mailSender.send(message);
		 
        
		/*
		 * mailSender.send(new MimeMessagePreparator() { public void prepare(MimeMessage
		 * mimeMessage) throws MessagingException { MimeMessageHelper message = new
		 * MimeMessageHelper(mimeMessage, true, "UTF-8");
		 * message.setFrom("smaas.live@scmxpert.com"); message.setTo(Email);
		 * message.setSubject("my subject"); message.
		 * setText("my text <img src='https://www.w3schools.com/tags/smiley.gif'>",
		 * true); message.addInline("myLogo", new
		 * ClassPathResource("https://www.w3schools.com/tags/smiley.gif"));
		 * message.addAttachment("myDocument.pdf", new
		 * ClassPathResource("D:/DOWNLOAD/RoutesList-1568721754628")); } });
		 */
    }
//    public void sendMailWithAttachment(String to, String subject, String body, String fileToAttach) 
//    {
//    	System.out.println("here");
//    	MimeMessagePreparator preparator = new MimeMessagePreparator() 
//    	{
//            public void prepare(MimeMessage mimeMessage) throws Exception 
//            {
//                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
//               // mimeMessage.setFrom(new InternetAddress("exafluenceind@gmail.com"));
//               // mimeMessage.setSubject(subject);
//               // mimeMessage.setText("Hi");
//               // pdfGenerator();
//                FileSystemResource file = new FileSystemResource(new File("d:\\Test.pdf"));
//                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//             //   helper.adsetRecipient(Message.RecipientType.TO, new InternetAddress(to));
//                helper.setFrom("exafluenceind@gmail.com");
//                helper.setText("<html><body>html Data</body></html>", true);
//                
//                helper.addAttachment(file.getFilename(), file);
//               // helper.addAttachment("Piccolo Family User Guide.pdf",file);
//            System.out.println("helper "+helper);
//            }
//        };
//        
//        try {
//            mailSender.send(preparator);
//        }
//        catch (MailException ex) {
//            // simply log it and go on...
//            System.err.println(ex.getMessage());
//        }
//    }
//    
    
    public void sendMailWithAttachment(String to[], String subject, String body, String fileToAttach, File excelFile) 
    {
    	System.out.println("here");
    	MimeMessagePreparator preparator = new MimeMessagePreparator() 
    	{
            public void prepare(MimeMessage mimeMessage) throws Exception 
            {
            	String shipnum=subject;
            	System.out.println("ship num in sendEmail "+shipnum);
               // mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
               // mimeMessage.setFrom(new InternetAddress("exafluenceind@gmail.com"));
               // mimeMessage.setSubject(subject);
               // mimeMessage.setText("Hi");
              //  pdfGenerator();
            	
                FileSystemResource file = new FileSystemResource(new File(fileToAttach));
           //     FileSystemResource excelFile = new FileSystemResource(new File(excelFile));
                // Uploading the file to AWS S3
///                String fileName = file.getFilename();
                                           
//                ObjectMetadata metadata = new ObjectMetadata();
//    			metadata.setContentLength(file.contentLength());
//    			
//    			amazonS3Client.putObject(
// 					   new PutObjectRequest(bucketName, fileName, file.getInputStream(), metadata)
// 					      .withCannedAcl(CannedAccessControlList.PublicRead));
//    			
//				amazonS3Client.putObject(
//					   new PutObjectRequest(archivedbucketName, fileName, file.getInputStream(), metadata)
//						  .withCannedAcl(CannedAccessControlList.PublicRead));
    			
    			
         //       FileSystemResource file2 = new FileSystemResource(new File(file2TOAttach));
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                helper.setTo(to); 
             //   helper.adsetRecipient(Message.RecipientType.TO, new InternetAddress(to));
            //    helper.setFrom("exafluenceind@gmail.com");
           //     helper.setText("<html><body>Dr.Reddy's Shipment Logger Data</body></html>", true);
                helper.setSubject(subject+ " Shipment Details");
             //   helper.setText("<html><body><img src='cid:identifier1234'></body></html>", true);
                helper.setText(body,true); 
                //FileSystemResource res = new FileSystemResource(new File("c:/SMAASMailAttachments/ShipmentImages/"+shipnum+".png"));
               FileSystemResource res = new FileSystemResource(new File(imagepath +shipnum+ ".png"));	///remove for omiting png /// 
               helper.addInline("identifier1234", res);					/// remove for omiting png///  
               // helper.setText("<html><body><img src=\"https://quickchart.io/chart/render/zm-8a34a3a8-e63c-4100-b3f8-665b3bf1600d?f=.png\"/></body></html>", true);
                
                            
                          
                helper.addAttachment(file.getFilename(), file);
                helper.addAttachment(excelFile.getName(), excelFile);
        //        helper.addAttachment(file2.getFilename(), file2);
               // helper.addAttachment("Piccolo Family User Guide.pdf",file);
            System.out.println("helper "+helper);
            }
        };
        
        try {
            mailSender.send(preparator);
        }
        catch (MailException ex) {
            // simply log it and go on...
        	System.out.println(" :::::::::: Exception in sendMailWithAttachment method of MailSenderService while sending Email :::::::::: ");
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

 
}