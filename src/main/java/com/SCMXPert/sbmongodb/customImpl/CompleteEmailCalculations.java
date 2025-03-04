
package com.SCMXPert.sbmongodb.customImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.DoubleSummaryStatistics;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.pdfbox.util.PDFMergerUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.SCMXPert.sbmongodb.controller.MailSenderService;
import com.SCMXPert.sbmongodb.document.DeviceDataStream;
import com.SCMXPert.sbmongodb.document.Devicedatatemp;
import com.SCMXPert.sbmongodb.document.PDFEmail;
import com.SCMXPert.sbmongodb.document.Shipments;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.text.DocumentException;

@Service
public class CompleteEmailCalculations {

	@Autowired
	MongoOperations mongeoOperation;

	@Autowired
	MongoTemplate mongoTemplate;

//	@Autowired
//	private PDFEmail pdfEmail;
	
	@Autowired
	private MailSenderService sendMail;

	@Autowired
	private PDFGenerator generatePdf;
	
	@Value("${pdf1FileName}")
	private String pdf1FileName;

//	@Value("${pdf11FileName}")
//	private String pdf11FileName;

	@Value("${imagefile}")
	private String imagefile;

	@Value("${mergepdf}")
	private String mergepdf;
	
	@Value("${excelFilePath}")
	private String excelPath;

	public void tempDataCalculator(Shipments shipment, String endDate, String base64Image, String deviceAttacheddate)
			throws ParseException, FileNotFoundException, IOException, DocumentException {

		// try {
		System.out.println("::::::::::: In tempDataCalculator for CompleteEmailCalculations :::::::::::::");
		
		String deviceId = shipment.getDevice_Id();
		String created_date = shipment.getCreated_Date();
		AggregationResults<DeviceDataStream> results = null;

		SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy-MM-dd");

		// parse the string into Date object
		Date date = sdfSource.parse(created_date.split("T")[0]);

		// create SimpleDateFormat object with desired date format
		SimpleDateFormat sdfDestination = new SimpleDateFormat("MM/dd/yy");

		// parse the date into another format
		// strDate = sdfDestination.format(date);
		System.out.println(" sdfDestination.format(date) " + sdfDestination.format(date));
		// deviceDataStrem1 = devicedatarepo.findByDevice_id(deviceId);
		System.out.println("Device id " + deviceId);// .andOperator(Criteria.where("Message_Type").is("sensor")
		
//		AggregationOperation match1 = Aggregation.match(Criteria.where("Device_Id").is(deviceId)
//				.orOperator((Criteria.where("Message_Type").is("sensor")),
//						Criteria.where("Message_Type").is("GPS MESSAGE"))
//				.andOperator(Criteria.where("current_terminal_date").gte(sdfDestination.format(date))));
		
		AggregationOperation match1 = Aggregation.match(Criteria.where("Device_Id").is(deviceId)
				.orOperator((Criteria.where("Message_Type").is("sensor")),
						Criteria.where("Message_Type").is("GPS MESSAGE"))
				.andOperator((Criteria.where("SensorUTC").gte(deviceAttacheddate)),
						(Criteria.where("SensorUTC").lte(endDate))));

		Aggregation aggregation = Aggregation.newAggregation(match1);
		System.out.println("aggregation " + aggregation);
		results = mongoTemplate.aggregate(aggregation, "DeviceDataStream", DeviceDataStream.class);
//System.out.println("results "+results);
		List<Float> list = new ArrayList<Float>();
		List<Devicedatatemp> devDataList = new ArrayList<Devicedatatemp>();
		List<String> batteryVal = new ArrayList<>();
		String maxTempThres = shipment.getTemp().split(" ")[2];
		float maxTempThresF = Float.valueOf(shipment.getTemp().split(" ")[2].replace(" c", ""));
		// System.out.println("maxTempThres "+maxTempThresF);
		String minTempThes = shipment.getTemp().split(" ")[0];
		float minTempF = Float.valueOf(shipment.getTemp().split(" ")[0]);
		// System.out.println("minTempThes "+minTempF);

		String alaramZone = " <body> " 
		        + " <h2> <font size = \"4\">Incursion and Excursion logs during Transit </font></h2>" 
				+ " </body> "
				+ "	<font size=\"1\" >"
				+ "<table style=\"width:100%\">\r\n" + "  <tr>\r\n"				
				+ "    						<th>Alarm Zone</th>   \r\n"
				+ "            			    <th>Alarm Start Time</th>   \r\n"
				+ "            			    <th>Alarm End Time</th>   \r\n"

				+ "                         <th>Duration</th>\r\n"
	      //	+ "                         <th>Delay</th>\r\n"
			    + "                         <th>Address</th>\r\n"
				+ "                                 \r\n" + "  </tr>\r\n";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yy");
		String tempAddress = "";
		if(results!=null) {
		for (DeviceDataStream dv : results) {
			try {
			// System.out.println("dv.getMessage_Type() "+dv.getMessage_Type());
//System.out.println("dv.getCurrent_terminal_date() "+dv.getCurrent_terminal_date());

			LocalDate date1 = null;
//if(dv.getMessage_Type().equals("GPS MESSAGE")) {	
//	System.out.println("dv.getCurrent_terminal_date()" +dv.getCurrent_terminal_date());
//	date1=  LocalDate.parse(dv.getCurrent_terminal_date(), formatter2);
//}
//else {
//	//System.out.println("dv.getCurrent_terminal_date() "+dv.getCurrent_terminal_date());
//	 date1 = LocalDate.parse(dv.getCurrent_terminal_date(), formatter);
//
//}
//System.out.println("dv.getCurrent_terminal_date() "+dv.getCurrent_terminal_date());
			date1 = LocalDate.parse(dv.getCurrent_terminal_date(), formatter);

			String dateTime = date1.toString() + "T" + dv.getCurrent_terminal_time() + ".000Z";

			if (dv.getMessage_Type().equals("sensor") || dv.getMessage_Type().equals("GPS MESSAGE")) {
				if (dateTime.compareTo(shipment.getCreated_Date()) > 0) {

					Devicedatatemp devData = new Devicedatatemp();
					devData.setSensorTemp(dv.getFirst_Sensor_temperature());
//					devData.setUTC(dv.getCurrent_terminal_date() + " " + dv.getCurrent_terminal_time());
					devData.setUTC(dateTime);
					devData.setUtcforAlarmzone(dv.getCurrent_terminal_date() + " " + dv.getCurrent_terminal_time());	// setting this for Alaram Zone in pdf
					// System.out.println("dv.getDevice_Id() "+dv.getDevice_Id());
					// System.out.println("dv.getReporting_Date() + \"T\" + dv.getReporting_time()
///					System.out.println("::::::::::: Reporting Date and Reporting Time :::::::::::::");
///					System.out.println(dv.getReporting_Date() + "T" + dv.getReporting_time());
					// "+dv.getReporting_Date() + "T" + dv.getReporting_time() );
					devData.setReportDateTime(dv.getReporting_Date() + "T" + dv.getReporting_time() + "Z");
					// if reporting_date issue solved remove comments and comment if condition
//				if(dv.getReporting_Date()==null) {
//					devData.setReportDateTime(dv.getReporting_date2()+ "T" + dv.getReporting_time() + "Z");
//				}else {
//					System.out.println("not null");
//					devData.setReportDateTime(dv.getReporting_Date() + "T" + dv.getReporting_time() + "Z");
//				}
					devData.setMessage_Type(dv.getMessage_Type());
					devData.setLongitude(dv.getLongitude());
					devData.setLatitude(dv.getLatitude());
					devData.setDevice_Id(dv.getDevice_Id());
			///		System.out.println("dv.getAddress() "+dv.getAddress());
					if (!dv.getAddress().equals("")) {
			///			System.out.println("in not null");
						devData.setAddress(dv.getAddress());
						tempAddress = dv.getAddress();
					} else {
///						System.out.println("in null ---- in tempDataCalculator method");
					//	System.out.println("tempAddress");
					//	System.out.println(tempAddress);
						devData.setAddress(tempAddress);
					}
					batteryVal.add(dv.getBattery_Level());
					devDataList.add(devData);
				
																											
				}
			}
		}	
			catch (Exception e) {
		        // Handle exceptions for individual items
				e.printStackTrace();
		        System.err.println("Error processing list of results in CompleteEmailCalculation: " + dv + " - " + e.getMessage());
		        System.out.println("::: There is exception in for each loop in complete email calculation where we operate on results list to build devDataList");
		    }
		}
		Collections.sort(devDataList, Devicedatatemp.uTCComparator);
		System.out.println("Completed building devDataList in CompleteEmailCalculation");
		String currentDate = null;
//		String pdf2FileName = "c:\\SMAASMailAttachments\\SCMPDF\\Shipment " + shipment.getShipment_Num()
//				+ " All_Data_points.pdf";
		String pdf2FileName = pdf1FileName +"_"+shipment.getShipment_Num()
		+ "_All_Data_points.pdf";
		
		generatePdf.generateAllPdfPoints(devDataList, shipment.getShipment_Num(), pdf2FileName, maxTempThresF, minTempF, shipment.getDevice_Id());
		List<Devicedatatemp> bat = new ArrayList<>();
		bat.add(devDataList.get(0));
		bat.add(devDataList.get(devDataList.size() - 1));

		// String batteryStart = String.valueOf(100 * Integer.valueOf(batteryVal.get(0))
		// - 318);
		// String batterEnd = String.valueOf(100 *
		// Integer.valueOf(batteryVal.get(batteryVal.size() - 1)) - 318);
		
		String maxValueString = "4.2";
		float maxValue = Float.parseFloat(maxValueString);
		float batteryLevelStart = Float.parseFloat(batteryVal.get(0));
		float batteryLevelEnd = Float.parseFloat(batteryVal.get(batteryVal.size() - 1));
		float percentageStart = (batteryLevelStart / maxValue) * 100;
		float percentageEnd = (batteryLevelEnd / maxValue) * 100;
		
//		String batteryStart = String.valueOf(100 * Float.valueOf(batteryVal.get(0)) - 318);
//		String batterEnd = String.valueOf(100 * Float.valueOf(batteryVal.get(batteryVal.size() - 1)) - 318);
		String batteryStart = String.valueOf(Math.round(percentageStart * 100.0) / 100.0);
		String batterEnd = String.valueOf(Math.round(percentageEnd * 100.0) / 100.0);

		// bat.stream().forEach(x->System.out.println("battery "+x.getBattery()));

		// System.out.println("before for");
		for (Devicedatatemp dv : devDataList) {
			// System.out.println("dv.getMessage_Type() " + dv.getMessage_Type());
			if (dv.getMessage_Type().equals("sensor")) {
				Float s = 0F; //before it is given as dv.getSensorTemp() !=""
	//			if (dv.getSensorTemp() != "" || dv.getSensorTemp() != null || !dv.getSensorTemp().isEmpty()) {
				if (!dv.getSensorTemp().equals("") || !dv.getSensorTemp().equals(null) || !dv.getSensorTemp().isEmpty()) {
					// System.out.println(dv.getSensorTemp());
					s = Float.valueOf(dv.getSensorTemp().replace(" C", ""));
//					String nextDate = dv.getUTC();		// UTC and getUtcforAlarmzone are same but differ in formats.
					String nextDate = dv.getUtcforAlarmzone();
					if (currentDate == null) {
						currentDate = nextDate;
						continue;
					}
					/////
					// System.out.println("s "+s);

					if (s >= maxTempThresF+0.5) {
						// System.out.println("current date "+currentDate+":::::next date "+nextDate);
						Date startTime = new SimpleDateFormat("MM/dd/yy HH:mm:ss").parse(currentDate);
						Date endTime = new SimpleDateFormat("MM/dd/yy HH:mm:ss").parse(nextDate);
				//		System.out.println("dv.getReportDateTime()" + dv.getReportDateTime());
						Date reportTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'")
								.parse(dv.getReportDateTime());

						String duration = findDifference(startTime, endTime);
						String delay = findDifference(startTime, reportTime);
						// String delay="";
						String high = " <tr>\r\n" + "<td>" + s + " C (Max)</td>\r\n" + "    <td>" + currentDate
								+ "</td>\r\n" + "<td>" + nextDate + "</td><td>" + duration + "</td><td style=\"width:20%\">" + dv.getAddress()
								+ "</td>\r\n" + "  </tr>\r\n";
						alaramZone = alaramZone + high;

					} else if (s <= minTempF-0.5) {
						Date startTime = new SimpleDateFormat("MM/dd/yy HH:mm:ss").parse(currentDate);
						Date endTime = new SimpleDateFormat("MM/dd/yy HH:mm:ss").parse(nextDate);
				//		System.out.println("dv.getReportDateTime()" + dv.getReportDateTime());
						Date reportTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'")
								.parse(dv.getReportDateTime());

						String duration = findDifference(startTime, endTime);
						String delay = findDifference(startTime, reportTime);
						String low = " <tr>\r\n" + "<td>" + s + " C (Min)</td>\r\n" + "    <td>" + currentDate
								+ "</td>\r\n" + "<td>" + nextDate + "</td><td>" + duration + "</td><td style=\"width:20%\">" + dv.getAddress()
								+ "</td>\r\n" + "  </tr>\r\n";
						alaramZone = alaramZone + low;
					}
					currentDate = nextDate;
					// devData.setSensorTemp(dv.getFirst_Sensor_temperature());
					// devData.setUTC(dv.getCurrent_terminal_date()+"
					// "+dv.getCurrent_terminal_time());
					list.add(s);

				}

			}
		}

		String tabEnd =  "</font>\r\n"+"</table>\r\n" + "    <br><br><br>        			    \r\n" + "         \r\n"
				+ "   			      \r\n" + "            			    \r\n"
				+ "            			    </body>  \r\n" + "\r\n" + "\r\n";
		alaramZone = alaramZone + tabEnd;

		// System.out.println("Max " + Collections.max(list));
		// System.out.println("Min " + Collections.min(list));
		DoubleSummaryStatistics summaryStats = list.stream().mapToDouble((a) -> a).summaryStatistics();
		// System.out.println("Average of a List = " + summaryStats.getAverage());
		PDFEmail pdfEmail = new PDFEmail();
		pdfEmail.setCustomerName("DR Reddy's");
		pdfEmail.setPartnerName("SCMXPert");
		pdfEmail.setInvoiceNum(shipment.getInvoice_Number());
		pdfEmail.setShipmentNo(shipment.getShipment_Num());
		pdfEmail.setShipmentDesc((shipment.getComments() == null
				? shipment.getGoods_Desc() + ", " + shipment.getRoute_From() + "-" + shipment.getRoute_To()
				: shipment.getComments()[0]));
		pdfEmail.setPoNumer(shipment.getPo_Number());
		pdfEmail.setDeviceNo(shipment.getDevice_Id());
		// pdfEmail.setRouteDetails(shipment.getRoute_From() + "-" +
		// shipment.getRoute_To());
		pdfEmail.setStorageCondition(shipment.getGoods_Desc());
		pdfEmail.setRouteDetails(shipment.getRoute_From() + "-" + shipment.getRoute_To() + ", " + shipment.getMode());

		pdfEmail.setMaxTempThres(maxTempThres);
		pdfEmail.setMinTempThres(minTempThes + " c");
		// System.out.println("list "+list);		
		float maxTemp = Collections.max(list);
		float minTemp = Collections.min(list);
		pdfEmail.setHighestTemp(maxTemp);
		pdfEmail.setLowestTemp(minTemp);
		double avgTemp = Math.round(summaryStats.getAverage() * Math.pow(10, 2)) / Math.pow(10, 2);

		pdfEmail.setAvgTemp(avgTemp);

//		Date shipCreatedDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(created_date);
//		Date shipEndDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(endDate);
//		String elapsedTime = findDifference(shipCreatedDate, shipEndDate);
		Date deviceattachedDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(deviceAttacheddate);
		Date shipEndDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(endDate);
		String elapsedTime = findDifference(deviceattachedDate, shipEndDate);
		Double mktValue = mktCalculator(avgTemp);
		mktValue = Math.round(mktValue * Math.pow(10, 4)) / Math.pow(10, 4);
		pdfEmail.setMktValue(mktValue);
//		pdfEmail.setStartTime(shipCreatedDate.toString());	// Commented because from start date means the Device Attached date
		pdfEmail.setStartTime(deviceattachedDate.toString());
		pdfEmail.setEndTime(shipEndDate.toString());
		pdfEmail.setElapsedTime(elapsedTime);
		pdfEmail.setDataPoints(String.valueOf(list.size()));
		pdfEmail.setBatterystart(batteryStart);
		pdfEmail.setBatteryend(batterEnd);

	/*************  for calculating no.of incursion & excursions and time out of Threshold   *************/
		
  //    List<Devicedatatemp> devDataList3 = new ArrayList<Devicedatatemp>();

  //    System.out.println(devDataList3);
		int count = 0;
		int hours = 0;
		int min = 0;
		int sec = 0;
		// String duration = "";
		boolean temp = false;
		String startTime = "";
		String endTime = "";
		String lastUTC = "";
		long finalDuration = 0;
//		SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
		int startTimeCount = 0;
		for (Devicedatatemp dv : devDataList) {
			System.out.println(dv);

			if (dv.getMessage_Type().equals("sensor")) {
				Float s = 0F; // before it is given as dv.getSensorTemp() !=""
				// if (dv.getSensorTemp() != "" || dv.getSensorTemp() != null ||
				// !dv.getSensorTemp().isEmpty()) {
				if (!dv.getSensorTemp().equals("") || !dv.getSensorTemp().equals(null)
						|| !dv.getSensorTemp().isEmpty()) {
					// System.out.println(dv.getSensorTemp());
					s = Float.valueOf(dv.getSensorTemp().replace(" C", ""));
					// String nextDate = dv.getUTC();
					System.out.println(dv.getSensorTemp());

					if (s >= maxTempThresF + 0.5 || s <= minTempF - 0.5) {
						temp = true;
						// strattime
						if (startTimeCount == 0)
							startTime = dv.getUTC();
						startTimeCount = startTimeCount + 1;
						lastUTC = dv.getUTC();
						System.out.println("startTime :" + startTime);

						/// calculate duration here
						// String time = dv.getUTC;
						/// String[] sp =
						/// time.replace("d","").replace("h","").replace("m","").replace("s","").split(",");
						// hours = hours+ Integer.valueOf(sp[1]);
						// min = min+ Integer.valueOf(sp[1]);
						// sec = sec+ Integer.valueOf(sp[1]);

					} else {

						if (temp) {
							// String endTime = dv.getUTC;
							// endtime
							count++;
							temp = false;
							endTime = dv.getUTC();
							Date d1 = null;
							Date d2 = null;
							try {
								d1 = format.parse(startTime);
								d2 = format.parse(endTime);
							} catch (ParseException e) {
								e.printStackTrace();
							}

                          //Date startDate = // Set start date
                         //		Date endDate   = // Set end date

							long duration = d2.getTime() - d1.getTime();
							System.out.println("duration :" + duration);
							finalDuration = finalDuration + duration;
							System.out.println("endTime :" + endTime);
						}
					}

					// list2.add(s);
				}
			}
		}
		if (temp) {
			count++;
			temp = false;
			//// last record utc----endtime
			endTime = lastUTC;
			Date d1 = null;
			Date d2 = null;
			try {
				d1 = format.parse(startTime);
				d2 = format.parse(endTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			// Date startDate = // Set start date
			// Date endDate = // Set end date

			long duration = d2.getTime() - d1.getTime();
			System.out.println("duration :" + duration);
			finalDuration = finalDuration + duration;
			System.out.println("endTime :" + endTime);

		}
		System.out.println(count);

//      duration = "h" + hours + "m" + min + "s" + sec;

		long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(finalDuration);
		System.out.println("diffInSeconds :" + diffInSeconds);
		long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(finalDuration);
		System.out.println("diffInMinutes :" + diffInMinutes);
		long diffInHours = TimeUnit.MILLISECONDS.toHours(finalDuration);
		System.out.println("diffInHours :" + diffInHours);
		long diffInDays = TimeUnit.MILLISECONDS.toDays(finalDuration);
		System.out.println("diffInDays :" + diffInDays);

		pdfEmail.setNumber_of_excurson_and_incursion(String.valueOf(count));
//		pdfEmail.setTime_out_of_threshold(String.valueOf(diffInHours) + " h");

//      pdfEmail.setNumber_of_excurson_and_incursion(String.valueOf(number));
////    pdfEmail.setNumber_of_excurson_and_incursion(String.valueOf(count));
	//	System.out.println("i am here");	
		
		
//		try {
//			String startAbove  = null;
//			String startBelow  = null; 
//			
//			//long finalDurations = 0;
//			long finalDurationAbove = 0;
//			long finalDurationsBelow = 0;	
//			
//			SimpleDateFormat formater = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
//			
//			String endTimes = "";
//			String lastUTCs = "";									
//			
//		for (Devicedatatemp dv : devDataList) {
//				
//			if (dv.getMessage_Type().equals("sensor")) {
//					Float s = 0F; // before it is given as dv.getSensorTemp() !=""
//					// if (dv.getSensorTemp() != "" || dv.getSensorTemp() != null ||
//					// !dv.getSensorTemp().isEmpty()) {
//			 if (!dv.getSensorTemp().equals("") || !dv.getSensorTemp().equals(null) || !dv.getSensorTemp().isEmpty()) {
//						// System.out.println(dv.getSensorTemp());
//						s = Float.valueOf(dv.getSensorTemp().replace(" C", ""));
//						// String nextDate = dv.getUTC();
//						System.out.println("sensor temp");
//						System.out.println(dv.getSensorTemp());
//						
//	            if (s >= maxTempThresF + 0.5) {
//	                if (startAbove == null) {
//	                    startAbove = dv.getUTC();
//	                    lastUTCs = dv.getUTC();
//	                    System.out.println("startAbove ::::::");
//						System.out.println(startAbove);
//	                }
//	            }
//	            else if (s <= minTempF - 0.5) {
//	                if (startBelow == null) {
//	                    startBelow = dv.getUTC();
//	                    lastUTCs =  dv.getUTC();
//	                    System.out.println("startBelow :::::::");
//						System.out.println(startBelow);
//	                }
//	            } 
//	            else {
//	                if (startAbove != null) {			                	
////	                    Duration duration = Duration.between(startAbove, formater.parse(dv.getUTC()));
////	                    do something with duration, e.g. add to a list
////	                    startAbove = null;
//						endTimes = dv.getUTC();
//						Date d1 = null;
//						Date d2 = null;
//						try {
//							d1 = formater.parse(startAbove);
//							d2 = formater.parse(endTimes);
//						} catch (ParseException e) {
//							e.printStackTrace();
//							System.out.println(
//									"Exception in Parsing startTime and endTime for Above Thresholds in else " +e);
//						}
//
//						long durations = d2.getTime() - d1.getTime();
//						System.out.println("duration in else for above thres :" + durations);
//						finalDurationAbove = finalDurationAbove + durations;
//						System.out.println("endTimes in else for above thres ::::::   " + endTimes);
//						System.out.println(
//								"finalDurationAbove in else for above thres :::::::::  " + finalDurationAbove);
//						// System.out.println("endTime :" + endTime);
//
//						startAbove = null;
//	                 }
//	                
//	                if (startBelow != null) {
////	                    Duration duration = Duration.between(startBelow, reading.getTime());
////	                    do something with duration, e.g. add to a list
////	                    startBelow = null;
//						endTimes = dv.getUTC();
//						Date d1 = null;
//						Date d2 = null;
//						try {
//							d1 = formater.parse(startBelow);
//							d2 = formater.parse(endTimes);
//						} catch (ParseException e) {
//							e.printStackTrace();
//							System.out.println(
//									"Exception in Parsing startTime and endTime for Below Thresholds in else" +e);
//						}
//
//						long durations = d2.getTime() - d1.getTime();
//						System.out.println("duration in else for below thres :" + durations);
//						finalDurationsBelow = finalDurationsBelow + durations;
//						System.out.println("endTimes in else for below thres :::::::" + endTimes);
//						System.out.println(
//								"finalDurationsBelow in else for below thres :::::::" + finalDurationsBelow);
//						// System.out.println("endTime :" + endTime);
//
//						startBelow = null;			                    
//	                }			                
//	               }
//				 }
//			   }			            
//	        }
//	        if (startAbove != null) {
////	            // Handle the case where the last reading exceeded the MAX_THRESHOLD
////	            Duration duration = Duration.between(startAbove, readings.get(readings.size() - 1).getTime());
////	            // do something with duration, e.g. add to a list			       			        	
//				endTimes = lastUTCs;
//				Date d1 = null;
//				Date d2 = null;
//				try {
//					d1 = formater.parse(startAbove);
//					d2 = formater.parse(endTimes);
//				} catch (ParseException e) {
//					System.out.println(
//							"Exception in Parsing startTime and endTime for Above Thresholds in if-out of for loop ");
//					e.printStackTrace();					
//				}
//
//				System.out.println(" d1 ");
//				System.out.println(d1.getTime());
//				System.out.println(" d2 ");
//				System.out.println(d2.getTime());
//
//				long durations = d2.getTime() - d1.getTime();
//				System.out.println("duration in if :" + durations);
//				finalDurationAbove = finalDurationAbove + durations;
//				System.out.println("endTimes in iff ::::  ");
//				System.out.println(endTimes);
//				System.out.println("finalDurationAbove in iff ::::" + finalDurationAbove);
//	            
//			    //startAbove = null;  
//	        }
//	        
//	        if (startBelow != null) {
////	            // Handling the case where the last reading fell below the MIN_THRESHOLD
////	            Duration duration = Duration.between(startBelow, readings.get(readings.size() - 1).getTime());
////	            // do something with duration, e.g. add to a list
//				endTimes = lastUTCs;
//
//				Date d1 = null;
//				Date d2 = null;
//				try {
//					d1 = formater.parse(startBelow);
//					d2 = formater.parse(endTimes);
//				} catch (ParseException e) {
//					System.out.println(
//							"Exception in Parsing startTime and endTime for Below Thresholds in if-out of for loop ");
//					e.printStackTrace();
//				}
//
//				System.out.println(" d1 :");
//				System.out.println(d1.getTime());
//				System.out.println(" d2 :");
//				System.out.println(d2.getTime());
//
//				long durationBelow = d2.getTime() - d1.getTime();
//				System.out.println("durationBelow in iff :" + durationBelow);
//				finalDurationsBelow = finalDurationsBelow + durationBelow;
//				System.out.println("endTimes in iff ::::::::" + endTimes);
//				System.out.println("finalDurationsBelow in iff:::::: :" + finalDurationsBelow);
//				    
//				//startBelow = null;
//	        }
//
//	        long millisecondss = finalDurationAbove; //example value, represents 1 hour in milliseconds
//	        double hoursforAboveThres = (double) millisecondss / (1000 * 60 * 60); //convert milliseconds to hours			        
//	        double hourss = hoursforAboveThres; //example value
//	        double roundedHoursAboveThres = (double) Math.round(hourss * 100) / 100;
//	        System.out.println("roundedHoursAboveThres ");
//	        System.out.println(roundedHoursAboveThres);
//	        			        
//	        long milliseconds = finalDurationsBelow; //example value, represents 1 hour in milliseconds
//	        double hoursforBelowThres = (double) milliseconds / (1000 * 60 * 60); //convert milliseconds to hours
//	        double hoursss = hoursforBelowThres; //example value
//	        double roundedHoursBelowThres = (double) Math.round(hoursss * 100) / 100;
//	        System.out.println("roundedHoursBelowThres ");
//	        System.out.println(roundedHoursBelowThres);
//	        			        
////	    	pdfEmail.setTemperature24Max(String.valueOf(roundedHoursAboveThres) + " h");
////	    	pdfEmail.setTemperature24Min(String.valueOf(roundedHoursBelowThres) + " h");	        
//	        pdfEmail.setTemperature24Max(String.valueOf(roundedHoursAboveThres));
//	        pdfEmail.setTemperature24Min(String.valueOf(roundedHoursBelowThres));
//	   
//	    } 
//		catch (Exception e) {
//			// Handling errors, e.g. logging the error or throw a custom exception
//			System.out.println("Exception in Calculations ");
//			e.printStackTrace();
//		}
		
		try {
			String startAbove  = null;
			String startBelow  = null; 
			
			//long finalDurations = 0;
			long finalDurationAbove = 0;
			long finalDurationsBelow = 0;	
			
//			SimpleDateFormat formater = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
			
			String endTimes = "";
			String lastUTCs = "";									
			
		for (Devicedatatemp dv : devDataList) {
				
			if (dv.getMessage_Type().equals("sensor")) {
					Float s = 0F; // before it is given as dv.getSensorTemp() !=""
					// if (dv.getSensorTemp() != "" || dv.getSensorTemp() != null ||
					// !dv.getSensorTemp().isEmpty()) {
			 if (!dv.getSensorTemp().equals("") || !dv.getSensorTemp().equals(null) || !dv.getSensorTemp().isEmpty()) {
						// System.out.println(dv.getSensorTemp());
						s = Float.valueOf(dv.getSensorTemp().replace(" C", ""));
						// String nextDate = dv.getUTC();
						System.out.println("sensor temp");
						System.out.println(dv.getSensorTemp());
//						System.out.println("values in S :::: ");
//						System.out.println(s);
						
	            if (s >= maxTempThresF + 0.5) {
	                if (startAbove == null) {
	                    startAbove = dv.getUTC();
	                    lastUTCs = dv.getUTC();
	                    System.out.println(":::::: startAbove ::::::");
						System.out.println(startAbove);
	                }
	            }
	            else if (s <= minTempF - 0.5) {
	                if (startBelow == null) {
	                    startBelow = dv.getUTC();
	                    lastUTCs =  dv.getUTC();
	                    System.out.println("::::::: startBelow :::::::");
						System.out.println(startBelow);
	                }
	            } 
	            else {
	                if (startAbove != null) {			                	
//	                    Duration duration = Duration.between(startAbove, formater.parse(dv.getUTC()));
//	                    do something with duration, e.g. add to a list
//	                    startAbove = null;
						endTimes = dv.getUTC();
						Date d1 = null;
						Date d2 = null;
						try {
							d1 = formater.parse(startAbove);
							d2 = formater.parse(endTimes);
							
							long durationMillis = d2.getTime() - d1.getTime();
							System.out.println("Duration in milliseconds: " + durationMillis);
							double durationHours = durationMillis / (1000.0 * 60.0 * 60.0);
							System.out.println("Duration between utc1 and utc2 is: " + durationHours + " hours.");
						} catch (ParseException e) {
							e.printStackTrace();
							System.out.println(
									"Exception in Parsing startTime and endTime for Above Thresholds in else " +e);
						}

						long durations = d2.getTime() - d1.getTime();
						System.out.println("duration in else for above thres :" + durations);
						finalDurationAbove = finalDurationAbove + durations;
						System.out.println("endTimes in else for above thres ::::::   " + endTimes);
						System.out.println(
								"finalDurationAbove in else for above thres :::::::::  " + finalDurationAbove);
						// System.out.println("endTime :" + endTime);

						startAbove = null;
	                 }
	                
	                if (startBelow != null) {
//	                    Duration duration = Duration.between(startBelow, reading.getTime());
//	                    do something with duration, e.g. add to a list
//	                    startBelow = null;
						endTimes = dv.getUTC();
						Date d1 = null;
						Date d2 = null;
						try {
							d1 = formater.parse(startBelow);
							d2 = formater.parse(endTimes);
						} catch (ParseException e) {
							e.printStackTrace();
							System.out.println(
									"Exception in Parsing startTime and endTime for Below Thresholds in else" +e);
						}

						long durations = d2.getTime() - d1.getTime();
						System.out.println("duration in else for below thres :" + durations);
						finalDurationsBelow = finalDurationsBelow + durations;
						System.out.println("endTimes in else for below thres :::::::" + endTimes);
						System.out.println(
								"finalDurationsBelow in else for below thres :::::::" + finalDurationsBelow);
						// System.out.println("endTime :" + endTime);

						startBelow = null;			                    
	                }			                
	               }
				 }
			   }			            
	        }
	        if (startAbove != null) {
//	            // Handle the case where the last reading exceeded the MAX_THRESHOLD
//	            Duration duration = Duration.between(startAbove, readings.get(readings.size() - 1).getTime());
//	            // do something with duration, e.g. add to a list			       			        	
				endTimes = lastUTCs;
				Date d1 = null;
				Date d2 = null;
				try {
					d1 = formater.parse(startAbove);
					d2 = formater.parse(endTimes);
					System.out.println(" d1 inside if ");
					System.out.println(d1);
					System.out.println(" d2 inside if ");
					System.out.println(d2);
				} catch (ParseException e) {
					System.out.println(
							"Exception in Parsing startTime and endTime for Above Thresholds in if-out of for loop ");
					e.printStackTrace();					
				}

				System.out.println(" d1 ");
				System.out.println(d1.getTime());
				System.out.println(" d2 ");
				System.out.println(d2.getTime());

				long durations = d2.getTime() - d1.getTime();
				System.out.println("duration in if :" + durations);
				finalDurationAbove = finalDurationAbove + durations;
				System.out.println("endTimes in iff ::::  ");
				System.out.println(endTimes);
				System.out.println("finalDurationAbove in iff ::::" + finalDurationAbove);	            
			    //startAbove = null;  
	        }
	        
	        if (startBelow != null) {
//	            // Handling the case where the last reading fell below the MIN_THRESHOLD
//	            Duration duration = Duration.between(startBelow, readings.get(readings.size() - 1).getTime());
//	            // do something with duration, e.g. add to a list
				endTimes = lastUTCs;

				Date d1 = null;
				Date d2 = null;
				try {
					d1 = formater.parse(startBelow);
					d2 = formater.parse(endTimes);
				} catch (ParseException e) {
					System.out.println(
							"Exception in Parsing startTime and endTime for Below Thresholds in if-out of for loop ");
					e.printStackTrace();
				}

				System.out.println(" d1 :");
				System.out.println(d1.getTime());
				System.out.println(" d2 :");
				System.out.println(d2.getTime());

				long durationBelow = d2.getTime() - d1.getTime();
				System.out.println("durationBelow in iff :" + durationBelow);
				finalDurationsBelow = finalDurationsBelow + durationBelow;
				System.out.println("endTimes in iff ::::::::" + endTimes);
				System.out.println("finalDurationsBelow in iff:::::: :" + finalDurationsBelow);				    
				//startBelow = null;
	        }

	        long millisecondss = finalDurationAbove; //example value, represents 1 hour in milliseconds
	        double hoursforAboveThres = (double) millisecondss / (1000 * 60 * 60); //convert milliseconds to hours			        
	        double hourss = hoursforAboveThres; //example value
	        double roundedHoursAboveThres = (double) Math.round(hourss * 100) / 100;
	        System.out.println("roundedHoursAboveThres ");
	        System.out.println(roundedHoursAboveThres);
	        	        
	        long mtoHours = TimeUnit.MILLISECONDS.toHours(millisecondss);
	        System.out.println(":::::::::::::: mtohours :::::::::::::::");
	        System.out.println(mtoHours);
	        			        
	        long milliseconds = finalDurationsBelow; //example value, represents 1 hour in milliseconds
	        double hoursforBelowThres = (double) milliseconds / (1000 * 60 * 60); //convert milliseconds to hours
	        double hoursss = hoursforBelowThres; //example value
	        double roundedHoursBelowThres = (double) Math.round(hoursss * 100) / 100;
	        System.out.println("roundedHoursBelowThres ");
	        System.out.println(roundedHoursBelowThres);
	        		        	    	
	        pdfEmail.setTemperature24Max(String.valueOf(roundedHoursAboveThres));
	        pdfEmail.setTemperature24Min(String.valueOf(roundedHoursBelowThres));
	    	
	        double totalTimeOutOfThreshold = roundedHoursAboveThres + roundedHoursBelowThres;
			pdfEmail.setTime_out_of_threshold(String.valueOf(totalTimeOutOfThreshold) + " h");
	   
	    } 
		catch (Exception e) {
			System.out.println("Exception while calculating Time out of threshold in tempDataCalculator method");
			e.printStackTrace();
		}
		
							
//		String pdfFileName = "c:\\SMAASMailAttachments\\SCMPDF\\Shipment " + shipment.getShipment_Num()
//				+ " Details.pdf";
		
		String pdfFileName = pdf1FileName +"_"+shipment.getShipment_Num()
		+"_Details.pdf";
		
		// String[] mails = { "zahirbashafz@gmail.com", "madithatikusuma@gmail.com",
		// "hemantrathour36@gmail.com",
		// "abidbasha.abid@gmail.com", "banusekar9@gmail.com",

		// "vijaytangent12@gmail.com" };

///		String[] mails = { "kusuma@exafluence.com", "abid@exafluence.com", "drlnag_shipment_monitoring@drreddys.com", "drreddys_support@scmxpert.com" }; // This is for production.  
		String[] mails = { "kusuma@exafluence.com", "abid@exafluence.com", "drreddys_qa@scmxpert.com", "spottem@drreddys.com" };  // This is for Test Server.
///		String[] mails = { "madithatikusuma@gmail.com", "abidbasha.abid@gmail.com" };
///	    String[] mails = { "madithatikusuma@gmail.com", "abidbasha.abid@gmail.com", "drreddys_support@scmxpert.com", "sauravjha@drreddys.com" };
	//	 String[] mails = { "abidbasha.abid@gmail.com", "vijaytangent12@gmail.com", "madithatikusuma@gmail.com" };
		
//		String[] mails = { "sauravjha@drreddys.com", "adwitkacharya@drreddys.com", "sreenivasuluuppari@drreddys.com",
//		"kirankumarreddy@drreddys.com", "konidanabhaskar@drreddys.com", "anilksamineedi@drreddys.com", 
//		"amitkumarp@drreddys.com", "vvkkumar@drreddys.com", "prakhyaub@drreddys.com", 
//		"smadhusudhanarao@drreddys.com", "sriramm@drreddys.com", "ravindrar@drreddys.com", 
//		"rpulagam@drreddys.com", "sinta@drreddys.com", "sandhyat@drreddys.com", 
//		"hcambero@drreddys.com", "srinivasyg@drreddys.com", "madithatikusuma@gmail.com" };

		// String mailBody = generatePdf.pdfGenerator(pdfEmail, pdfFileName,
		// alaramZone);
		try {
//			String pathFile = "C:\\SMAASMailAttachments\\ShipmentImages\\" + shipment.getShipment_Num() + ".png";
			String pathFile = imagefile + shipment.getShipment_Num() + ".png";
			String base64url = base64Image.substring(base64Image.indexOf(",") + 1);

			@SuppressWarnings("resource")
			FileOutputStream imageOutFile = new FileOutputStream(pathFile);
			byte[] imageByteArray = Base64.getDecoder().decode(base64url);
			imageOutFile.write(imageByteArray);

		} catch (Exception e) {
			System.out.println("Exception In Image Generation " + e);
			e.printStackTrace();
		}
		
		try {
			String mailBody = generatePdf.pdfGenerator(pdfEmail, pdfFileName, alaramZone);
			// generatePdf.generateAllPdfPoints(devDataList,pdfEmail,pdf2FileName);
			
			String excelFilePath = excelPath +"_"+shipment.getShipment_Num()+"_All_Data_points.xlsx";
			generatePdf.generateExcelFile(devDataList, shipment.getShipment_Num(), excelFilePath, maxTempThresF, minTempF, shipment.getDevice_Id());
			File fileExcel = new File(excelFilePath);
						
			File file1 = new File(pdfFileName);
			File file2 = new File(pdf2FileName);

			// Instantiating PDFMergerUtility class
			PDFMergerUtility PDFmerger = new PDFMergerUtility();
//			String mergedPDF = "c:\\SMAASMailAttachments\\SCMPDF\\MergedPDF\\Shipment " + shipment.getShipment_Num()
//					+ "_ALL_Alerts.pdf";
			
//			String mergedPDF = mergepdf + shipment.getShipment_Num()  ///remove here for old mergedPDF
//			+ "_ALL_Alerts.pdf";
			
			Date shpEndDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(endDate);
			SimpleDateFormat formatt = new SimpleDateFormat("ddMMyyyyHHmmss");
			String crnt_timestamp = formatt.format(shpEndDate);
			
			String mergedPDF = mergepdf +"_"+shipment.getShipment_Num()
			+"_"+ crnt_timestamp +".pdf";
			
			// Setting the destination file
			PDFmerger.setDestinationFileName(mergedPDF);

			// adding the source files
			PDFmerger.addSource(file1);
			PDFmerger.addSource(file2);

			PDFmerger.mergeDocuments();
			System.out.println("Documents merged");
			// sendMail.sendMailWithAttachment(mails, shipment.getShipment_Num(), mailBody,
			// pdfFileName,pdf2FileName);
			sendMail.sendMailWithAttachment(mails, shipment.getShipment_Num(), mailBody, mergedPDF, fileExcel);
		} catch (Exception e) {
			System.out.println("Exception In PDF Generation " + e);
			e.printStackTrace();
			// TODO: handle exception
		}
		}
	}

	// }
	// catch(Exception e)
	// {
	// System.out.println("Exception in completeEmail " + e);
//	}
	// TODO Auto-generated catch block

	// }

	static String findDifference(Date d1, Date d2) {
		String elapsedTime = "";
//System.out.print("Date 1 "+d1+":::::Date 2"+d2);
		try {

			long difference_In_Time = d2.getTime() - d1.getTime();

			long difference_In_Seconds = (difference_In_Time / 1000) % 60;

			long difference_In_Minutes = (difference_In_Time / (1000 * 60)) % 60;

			long difference_In_Hours = (difference_In_Time / (1000 * 60 * 60)) % 24;

			long difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;

			elapsedTime = difference_In_Days + " d, " + difference_In_Hours + " h, " + difference_In_Minutes + " m, "
					+ difference_In_Seconds + " s";
			// System.out.println("Difference " + "between two dates is: "+elapsedTime);
		}
	
		// Catch the Exception
		catch (Exception e) {
			System.out.println(" Exception in findDifference method while calculating difference in time");
			e.printStackTrace();
		}
	
		return elapsedTime;
	}

	static Double mktCalculator(double avgTemp) {

		double avg = avgTemp;

		double kelvinTemp = 0;

		double coef1 = 0;

		double coef2 = 0;
		double clog = 0, TK = 0, MKT = 0;

		kelvinTemp = avg + 273;

		coef1 = -10000 / kelvinTemp;

		coef2 = Math.exp(coef1);

		clog = (Math.log(coef2)) * -1;

		TK = 10000 / clog;

		MKT = TK - 273;
		return MKT;
	}

}
