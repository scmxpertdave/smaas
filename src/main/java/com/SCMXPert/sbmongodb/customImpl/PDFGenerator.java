
package com.SCMXPert.sbmongodb.customImpl;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.SCMXPert.sbmongodb.document.Devicedatatemp;
import com.SCMXPert.sbmongodb.document.PDFEmail;

import com.itextpdf.html2pdf.HtmlConverter;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfDocument;


@Service
public class PDFGenerator {
	
	@Value("${imagepath}")
	private String imagepath;
	
//	@Value("${imagefile}")
//	private String imagefile;
	
	 public  String pdfGenerator(PDFEmail pdfEmail,String pdfFileName,String alaramZone) throws FileNotFoundException, IOException, DocumentException {
	    	
	    	
	    	
	    	////////////////HTML Body//////////////////////////
	    	
	    	
	    	  String mailBody= "<!DOCTYPE html>   \n"
		 		 		+ "            			    <html>   \n"
		 		 		+ "            			    <head>  \n"
		 		 		+ "            			   \n"
		 		 		+ "                            <style>  \n"
		 		 		+ "                          table, th, td {\n"
		 		 		+ "  border: 1px solid black;\n"
		 		 		+ "  border-collapse: collapse;\n"
		 		 		+ "}\n"
		 		 		+ "th {\n"
		 		 		+ "  padding:0px;\n"
		 		 		+ "}\n"
		 		 		+ "td\n"
		 		 		+ "{\n"
		 		 		+ "padding:0px;\n"
		 		 		+ "width:5%\n"
		 		 		+ "}\n"
		 		 		
		 		 		+ "\n"
		 		 		
		 		 		
		 		 		+ "th {\n"
		 		 		+ "  text-align: left;\n"
		 		 		+ "}\n"
		 		 		+ "                            \n"
		 		 		+ "\n"
		 		 		+ "h2 {\n"
		 		 		+"  text-align: center;\n"
		 		 		+"}\n"
		 		 		+ "            			    </style>   \n"
		 		 		+ "                            \n"
		 		 		+ "            			    </head>   \n"
		 		 		+ "            			    <body>  \n"
		 		 		+ " <font size=\"1\"> \n"
		 		 		+ " <h2> <font size=\"4\">Shipment Details for - "+pdfEmail.getShipmentNo()+"</font></h2>"
//		 		 		+ "                            <p>Customer Name:  "+pdfEmail.getCustomerName()+" </p></p>\n"
//		 		 		+ "                            <p>Partner  Name:  "+pdfEmail.getPartnerName()+"</p>\n"
//		 		 		+ "                            <p>Shipment/Invoice Number:  "+pdfEmail.getShipmentNo()+"</p>\n"
//		 		 		+ "                            <p>Shipment Description:   "+pdfEmail.getShipmentDesc()+"</p>\n"
//		 		 //		+ "                            <p>PO Number:   "+pdfEmail.getPoNumer()+"</p>\n"
//		 		 		+ "                            <p>Device NO:   "+pdfEmail.getDeviceNo()+"</p>\n"
//		 		 		+ "                            <p>Route Details:   "+pdfEmail.getRouteDetails()+"</p>\n"
//		 		 		+ "                            <p>Temperature Max:   "+pdfEmail.getMaxTempThres()+" c</p>\n"
//		 		 		+ "                            <p>Temperature Min:   "+pdfEmail.getMinTempThres()+"</p>\n"
		 		 				+ "                            <p>Customer Name:  "+pdfEmail.getCustomerName()+"<br> "
				 		 		+ "                            Device Partner  Name:  "+pdfEmail.getPartnerName()+" <br>"
				 		 		+ "                            Invoice Number:  "+pdfEmail.getInvoiceNum()+"<br>"
				 		 		+ "                            Delivery/Shipment Number:  "+pdfEmail.getShipmentNo()+"<br>"
				 		 		+ "                            Shipment Description:   "+pdfEmail.getShipmentDesc()+"<br>"
				 		 //		+ "                            <p>PO Number:   "+pdfEmail.getPoNumer()+"</p>\n"
				 		 		+ "                            Device No:   "+pdfEmail.getDeviceNo()+"<br>"
				 		 		+ "                            Route Details:   "+pdfEmail.getRouteDetails()+"<br>"
				 		 		+ "                            Storage Condition:   "+pdfEmail.getStorageCondition()+"<br>"
				 		 		+ "                            Temperature Max:   "+pdfEmail.getMaxTempThres()+" c <br>"
				 		 		+ "                            Temperature Min:   "+pdfEmail.getMinTempThres()+"</p>\n"
		 		 		+ "                            <h3>Logging Summary:</h3>\n"
		 		 		+ "            			     <table style=\"width:100%\">\n"
		 		 		+ "            			     \n"
		 		 		+ "                              \n"
		 		 		+ "                              \n"
		 		 		+ "                              \n"
		 		 		+ "                              \n"
		 		 		+ "            			      <tr>                              \n"
		 		 		+ "            			          \n"
		 		 		+ "                                \n"
		 		 		+ "                                <td id=\\  highest_temperature\\  >Highest Temperature: </td>   \n"
		 		 		+ "                    <td> "+pdfEmail.getHighestTemp()+" C</td>   \n"
		 		 		+ "                                            \n"
		 		 		+ "                                  \n"
		 		 		+ "                                \n"
		 		 		+ "            			        \n"
		 		 		+ "            			        <td id=\\start_time\\  >Shipment Start time:</td>\n"
		 		 		+ "                                <td>"+pdfEmail.getStartTime()+" </td>\n"
		 		 		+ "                                \n"
		 		 		+ "            			      </tr>   \n"
		 		 		+ "            			      <tr>   \n"
		 		 		+ "            			        <td id=\\  lowest_temperature\\  >Lowest\n"
		 		 		+ "                                Temperature:   </td>   \n"
		 		 		+ "            			          <td> "+pdfEmail.getLowestTemp()+" C</td>\n"
		 		 		+ "            			        <td id=\\end_time\\  >Shipment End Time:</td> \n"
		 		 		+ "                                <td>"+pdfEmail.getEndTime()+"</td>\n"
		 		 		+ "            			      </tr>   \n"
		 		 		+ "            			      <tr>   \n"
		 		 		+ "            			        <td id=\\  average_temperature\\  >Average Temperature:</td>   \n"
		 		 		+ "            			           <td>"+pdfEmail.getAvgTemp()+" C</td>\n"
		 		 		+ "            			        <td id=\\  elapsed_time\\  >Elapsed Time:</td>   								 <td>"+pdfEmail.getElapsedTime()+"</td>\n"
		 		 		+ "            			      </tr>   \n"
		 		 		+ "            			      <tr>   \n"
		 		 		+ "            			      <td id=\\  mkt\\  >MKT:</td>   <td> "+pdfEmail.getMktValue()+" C</td> \n"
		 		 		+ "            			      <td id=\\  data_points\\  >Data Points:  </td> \n"
		 		 		+ "                               <td>  "+pdfEmail.getDataPoints()+"  </td>\n"
		 		 		+ "            			      </tr>   \n"
		 		 		+ "            			              			     \n"
		 		 		+ "   <tr>                              \n"
			 		    + "            			          \n"
			            + "                                    \n"
		 		 		+ "            			      <td id=\\  temp24Max\\  >Temperature24Max: </td>   <td> "+pdfEmail.getTemperature24Max()+ " h</td> \n"
		 		 		+ "            			      <td id=\\  battery_at_start_of_shipment\\  >Battery At Start of Shipment:  </td> \n"
		 		 		+ "                               <td>  "+pdfEmail.getBatterystart()+ " %</td>\n"
		 		 		+ "            			      </tr>   \n"
		 		 		+ "            			              			     \n"
		 		 		+ "   <tr>                              \n"
			 		    + "            			          \n"
			            + "                                    \n"
			            
			 		 	+ "        <td id=\\  temp24Min \\  >Temperature24Min: </td>   \n"
			 		 	+ "        <td > " +pdfEmail.getTemperature24Min()+ " h</td>   \n"
			 		 	+ "                                            \n"
			 		 	+ "                                          \n"
			 		 	+ "                                         \n"
			 		 	+ "            			        \n"
			 		 	+ "        <td id=\\ battery_at_end_of_shipment \\  >Battery At End of Shipment: </td>  \n"
			 		 	+ "        <td > " +pdfEmail.getBatteryend()+ " %</td>\n"
			 		 	+ "                                \n"
			 		 	+ "   </tr>   \n"
			 		 	+ "   <tr>                              \n"
			 		    + "            			          \n"
			            + "                                    \n"
			 		 	+ "        <td id=\\  number_of_incursion_and _excursion \\  >Number of Incursion and Excursion: </td>   \n"
			 		 	+ "        <td > " +pdfEmail.getNumber_of_excurson_and_incursion()+ " </td>   \n"
			 		 	+ "                                            \n"
			 		 	+ "                                          \n"
			 		 	+ "                                         \n"
			 		 	+ "            			        \n"
			 		 	+ "        <td id=\\ time_out_of_threshold \\  >Time out of Threshold: </td>  \n"
			 		 	+ "        <td > " +pdfEmail.getTime_out_of_threshold()+ " </td>\n"
			 		 	+ "                                \n"
			 		 	+ "   </tr>   \n"						 		 				 		 	
			 		 	+ " </font> "
		 		 		+ "            			   </table>\n"
		 		 		+ "                           \n"
		 		 		+ "                           <br><br>\n"
		 		 		+ "            			    \n"
		 	//	 	    +"   <img src=\"https://quickchart.io/chart/render/zm-8966f3ba-9856-4f69-ae80-90b8d6139908?f=.png\"  width=\"460\" height=\"245\">\n"

//		 		 		+ " <br><br>\n \n <img src='C:/SMAASMailAttachments/ShipmentImages/"+pdfEmail.getShipmentNo()+".png' title=\"Graph\" style=\"width:100%; object-fit: cover;\"> <img src='cid:identifier1234'  title=\"Graph\" style=\"width:75%; object-fit: cover;\">  \n \n \n    <br><br>         \n";
		 		 		+ " <br><br>\n \n <img src='"+imagepath+pdfEmail.getShipmentNo()+".png' title=\"Graph\" style=\"width:100%; object-fit: cover;\"> <img src='cid:identifier1234' title=\"Graph\" style=\"width:75%; object-fit: cover;\"> \n \n \n <br><br> \n";
	    	
	    	
	    		    	
	    	mailBody=mailBody+alaramZone+ "  </html>  \r\n";
	    	
	    	 	
	    	HtmlConverter.convertToPdf(mailBody, new FileOutputStream(pdfFileName));

	 
	    	
				//generatePDFFromHTML("d:\\source.html");
			
	    	return mailBody;
	 }
		public  String generateAllPdfPoints(List<Devicedatatemp> devDataList,String shipmentnum,String pdf2FileName, float maxTempThresF, float minTempF, String deviceId) throws FileNotFoundException, IOException {

			String mailBody1= "<!DOCTYPE html>   \n"
	 		 		+ "            			    <html>   \n"
	 		 		+ "            			    <head>  \n"
	 		 		+ "            			   \n"
	 		 		+ "                            <style type=\"text/css\"> \n"
//	 		 		+" @page { \r\n"
//	 		 		+ "        size: landscape;\r\n"
//	 		 		+ "    }\r\n"
//	 		 		+ "    body { \r\n"
//	 		 		+ "        writing-mode: tb-rl;\r\n"
//	 		 		+ "    }"
	 		 		+ "                          table, th, td {\n"
	 		 		+ "  border: 1px solid black;\n"	 		 		
	 		 		+ "  border-collapse: collapse;\n"	 		 		
	 		 		+ "}\n"
	 		 		+ "th {\n"
	 		 		+ "  padding:0px;\n"
	 		 		+ "}\n"
	 		 		+ "td\n"
	 		 		+ "{\n"
	 		 		+ "padding:0px;\n"
	 		 		+ "width:5%\n"
	 		 		+ "}\n"
	 		 		
	 		 		+ "\n"
	 		 			 		 		
	 		 		+ "th {\n"
	 		 		+ "  text-align: left;\n"
	 		 		+ "}\n"
	 		 		+ "                            \n"
	 		 		+ "\n"
	 		 		+ "h2 {\n"
	 		 		+"  text-align: center;\n"
	 		 		+"}\n"
	 		 		+ "            			    </style>   \n"
	 		 		+ "                            \n"
	 		 		+ "            			    </head>   \n"
	 		 		+ "            			    <body>  \n"	 			 		 	
					+ "<h2> <font size = \"4\">Shipment Details for shipment - "+shipmentnum+"</font> / <font size = \"4\">Device_Id - "+deviceId+" </font><br/> </h2>" 
					
	 		 	    +"	<font size=\"1\"  >"
	 		        + " <table border = \"1\" style=\"width:100%\">\r\n" 	 		    
	                                        + "     <tr>\r\n"
	                                     
			     //        + "        <th>Device_Id</th>   \r\n"
               //	       + "        <th>Temperature</th>\r\n";
			               + "        <th>S.No.</th> \r\n"
			               + "        <th>Message_Type</th>   \r\n"
			               + "        <th>Temperature</th>\r\n"			              
			               + "        <th>Latitude</th>\r\n"
			               + "        <th>Longitude</th>   \r\n"			                
	                       + "        <th>SensorUTC</th>\r\n"			              
	                       + "        <th>Address</th>    \r\n";			        
	           
			               
			String rows="";
int i = 1;
String gpsAddress = "";
			for (Devicedatatemp dv : devDataList) {	
								     										
				String row= "";
				if (dv.getMessage_Type().equals("sensor")) {
					Float s = 0F; //before it is given as dv.getSensorTemp() !=""		
						// System.out.println(dv.getSensorTemp());
						s = Float.valueOf(dv.getSensorTemp().replace(" C", ""));
						String nextDate = dv.getUTC();
				//		if (currentDate == null) {
				//			currentDate = nextDate;
				//			continue;
							if (s > maxTempThresF) {						
								 row="     <tr style=\"background-color:#CD5C5C\"> \r\n\r"
										+ "                                               <td>"+String.valueOf(i)+"  </td> \r\n"
					//	                + "                                               <td> "+dv.getDevice_Id()+" </td> \r\n"
										+ "                                               <td> "+dv.getMessage_Type()+"  </td> \r\n"
										+ "                                               <td> "+dv.getSensorTemp()+"   </td> \r\n"
									    + "                                               <td>"+dv.getLatitude()+"  </td> \r\n"
									    + "                                               <td>  "+dv.getLongitude()+"  </td> \r\n"	
									    + "                                               <td style=\"width:20%\">"+dv.getUTC()+"    </td> \r\n"
						//				+ "                                               <td style=\"width:80%\">"+dv.getAddress()+"</td>  \r\n"
										+ "                                               <td style=\"width:80%\">"+gpsAddress+"    </td>  \r\n"
									    + "                                         </tr> \r\n";
								
																			
								}
							else if (s < minTempF) {
								 row="     <tr style=\"background-color:#4682B4\"> \r\n\r"
										+ "                                               <td>"+String.valueOf(i)+"  </td> \r\n"
                       //			    + "                                               <td> "+dv.getDevice_Id()+" </td> \r\n"
										+ "                                               <td> "+dv.getMessage_Type()+"  </td> \r\n"
										+ "                                               <td> "+dv.getSensorTemp()+"   </td> \r\n"
									    + "                                               <td>"+dv.getLatitude()+"  </td> \r\n"
									    + "                                               <td>  "+dv.getLongitude()+"  </td> \r\n"
									    + "                                               <td style=\"width:20%\">"+dv.getUTC()+"    </td> \r\n"
									//	+ "                                               <td style=\"width:80%\">"+dv.getAddress()+"</td>  \r\n"
										+ "                                               <td style=\"width:80%\">"+gpsAddress+"</td>  \r\n " 
									    + "                                         </tr> \r\n";
							}
							
							else {
								 row="     <tr> \r\n\r"
										+ "                                               <td>"+String.valueOf(i)+"  </td> \r\n"
                 //						+ "                                               <td> "+dv.getDevice_Id()+" </td> \r\n"
										+ "                                               <td> "+dv.getMessage_Type()+"  </td> \r\n"
										+ "                                               <td> "+dv.getSensorTemp()+"   </td> \r\n"										
										+ "                                               <td>"+dv.getLatitude()+"  </td> \r\n"
										+ "                                               <td>  "+dv.getLongitude()+"  </td> \r\n"										
										+ "                                               <td style=\"width:20%\">"+dv.getUTC()+"    </td> \r\n"	
//										+ "                                               <td style=\"width:80%\">"+dv.getAddress()+"</td>  \r\n"
										+ "                                               <td style=\"width:80%\">"+""+"    </td>  \r\n"
									    + "                                         </tr> \r\n";																 	
							}

							
	
			}
				else {
					 gpsAddress = dv.getAddress();
					 row="     <tr> \r\n\r"
							+ "                                               <td>"+String.valueOf(i)+"  </td> \r\n"
         //				    + "                                               <td>"+dv.getDevice_Id()+" </td> \r\n"
							+ "                                               <td>"+dv.getMessage_Type()+"  </td> \r\n"
							+ "                                               <td>"+dv.getSensorTemp()+"   </td> \r\n"							
							+ "                                               <td>"+dv.getLatitude()+"  </td> \r\n"
							+ "                                               <td>"+dv.getLongitude()+"  </td> \r\n"							
							+ "                                               <td style=\"width:20%\">"+dv.getUTC()+"    </td> \r\n"
//							+ "                                               <td style=\"width:80%\">"+dv.getAddress()+"    </td>  \r\n"
							+ "                                               <td style=\"width:80%\">"+gpsAddress+"</td>  \r\n"							
						    + "                                         </tr> \r\n";
					 
				}
				rows=rows+row;
				i+=1;	
			
			}
					
			String footer="     </tr> "
					+ "                        \r\n"
					+ "                  </font>\r\n"
					+ " 		 		 </table> \n\r\n"
					+ "                                                </body>  \n\r\n"
					+ " 		 		                           \n\r\n"
					+ " 		 		                          <br><br>\n\r\n"
					+ " 		 		            			    \n\r\n"
					+ "                                          </html>  \r\n";
			
			
			String pdf2= mailBody1+rows+footer;
			
			HtmlConverter.convertToPdf(pdf2, new FileOutputStream(pdf2FileName));
			return "PDF2Generated";					
		}
		
		
//	 private static void generatePDFFromHTML(String filename) throws DocumentException, IOException {
//		 System.out.println("generatePDFFromHTML::::::::::::::::::::::::::::::::::::::::::: ");
//		    Document document = new Document();
//		    PdfWriter writer = PdfWriter.getInstance(document,
//		      new FileOutputStream("d:\\html.pdf"));
//		    document.open();
//		    XMLWorkerHelper.getInstance().parseXHtml(writer, document,
//		      new FileInputStream(filename));
//		    document.close();
//		}

  public String generateExcelFile(List<Devicedatatemp> devDataList, String shipmentnum, String excelFilePath, float maxTempThresF, float minTempF, String deviceId)throws FileNotFoundException, IOException  {
		 
	        try (Workbook workbook = new XSSFWorkbook()) {
	        	System.out.println("::::::::::::::::::: Started Creating Sheet :::::::::::::::::");
	            Sheet sheet = workbook.createSheet("Shipment-"+shipmentnum+"_"+"Device_Id"+deviceId+"");

	            // Create header row
	            Row headerRow = sheet.createRow(0);
	            headerRow.createCell(0).setCellValue("S.No.");
	            headerRow.createCell(1).setCellValue("Date in UTC");
	            headerRow.createCell(2).setCellValue("Time in UTC");
	            headerRow.createCell(3).setCellValue("Message_Type");
	            headerRow.createCell(4).setCellValue("Temperature in C");
	            headerRow.createCell(5).setCellValue("Latitude");
	            headerRow.createCell(6).setCellValue("Logitude");
	            headerRow.createCell(7).setCellValue("Address");	      
	            //headerRow.createCell(5).setCellValue("Sensor UTC");      
	   ///      Row dataRow = sheet.createRow(rowNum++);
	            
	            // Populate data rows from the data object
	            int rowNum = 1;
	            int i = 1;
	            String gpsAddress = "";	            	            
	    		for (Devicedatatemp dv : devDataList) {
	    			 Row dataRow = sheet.createRow(rowNum++);						
					if (dv.getMessage_Type().equals("sensor")) {
						Float s = 0F; //before it is given as dv.getSensorTemp() !=""		
							// System.out.println(dv.getSensorTemp());
							s = Float.valueOf(dv.getSensorTemp().replace(" C", ""));
							String nextDate = dv.getUTC();
					//		if (currentDate == null) {
					//			currentDate = nextDate;
					//			continue;
						        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MM/dd/yy HH:mm:ss");
						        DateTimeFormatter outputDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

///						        LocalDateTime dateTime = LocalDateTime.parse(dv.getUTC(), inputFormatter);
						        LocalDateTime dateTime = LocalDateTime.parse(dv.getUtcforAlarmzone(), inputFormatter);	// dv.getUTC() and dv.getUtcforAlarmzone() are same with different formats.

						        LocalDate date = dateTime.toLocalDate();
						        LocalTime time = dateTime.toLocalTime();

						        String formattedDate = date.format(outputDateFormatter);
						        String formattedTime = time.toString();
							
								if (s > maxTempThresF) {						
									   dataRow.createCell(0).setCellValue(String.valueOf(i));
									   dataRow.createCell(1).setCellValue(formattedDate);
									   dataRow.createCell(2).setCellValue(formattedTime);
						               dataRow.createCell(3).setCellValue(dv.getMessage_Type());
						               dataRow.createCell(4).setCellValue(dv.getSensorTemp());
						               dataRow.createCell(5).setCellValue(dv.getLatitude());
						               dataRow.createCell(6).setCellValue(dv.getLongitude());
						               dataRow.createCell(7).setCellValue(gpsAddress);
						               //dataRow.createCell(5).setCellValue(dv.getUTC());       
									}
								else if (s < minTempF) {
									   dataRow.createCell(0).setCellValue(String.valueOf(i));
									   dataRow.createCell(1).setCellValue(formattedDate);
									   dataRow.createCell(2).setCellValue(formattedTime);
						               dataRow.createCell(3).setCellValue(dv.getMessage_Type());
						               dataRow.createCell(4).setCellValue(dv.getSensorTemp());
						               dataRow.createCell(5).setCellValue(dv.getLatitude());
						               dataRow.createCell(6).setCellValue(dv.getLongitude());
						               dataRow.createCell(7).setCellValue(gpsAddress);
								}								
								else {
									   dataRow.createCell(0).setCellValue(String.valueOf(i));
									   dataRow.createCell(1).setCellValue(formattedDate);
									   dataRow.createCell(2).setCellValue(formattedTime);
						               dataRow.createCell(3).setCellValue(dv.getMessage_Type());
						               dataRow.createCell(4).setCellValue(dv.getSensorTemp());
						               dataRow.createCell(5).setCellValue(dv.getLatitude());
						               dataRow.createCell(6).setCellValue(dv.getLongitude());
						               dataRow.createCell(7).setCellValue("");														 	
								}										
					}
					else {
						 gpsAddress = dv.getAddress();
						 
							DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MM/dd/yy HH:mm:ss");
							DateTimeFormatter outputDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

///							LocalDateTime dateTime = LocalDateTime.parse(dv.getUTC(), inputFormatter);
							LocalDateTime dateTime = LocalDateTime.parse(dv.getUtcforAlarmzone(), inputFormatter);

							LocalDate date = dateTime.toLocalDate();
							LocalTime time = dateTime.toLocalTime();

							String formattedDate = date.format(outputDateFormatter);
							String formattedTime = time.toString();
						 
						   dataRow.createCell(0).setCellValue(String.valueOf(i));
						   dataRow.createCell(1).setCellValue(formattedDate);
						   dataRow.createCell(2).setCellValue(formattedTime);
			               dataRow.createCell(3).setCellValue(dv.getMessage_Type());
			               dataRow.createCell(4).setCellValue(dv.getSensorTemp());
			               dataRow.createCell(5).setCellValue(dv.getLatitude());
			               dataRow.createCell(6).setCellValue(dv.getLongitude());
			               dataRow.createCell(7).setCellValue(gpsAddress);				 
					}
					i+=1;					
				}
	            // Write the workbook to a file
	            try (FileOutputStream fileOut = new FileOutputStream(excelFilePath)){
	                workbook.write(fileOut);
	            }
	        } catch (IOException e) {
	        	System.out.println("::::: Exception occured while generating Excel for All Data Points :::::");
	            e.printStackTrace();
	        }
	 return "ExcelFileGenerated";
  	}
		
	 
}

						