package com.SCMXPert.sbmongodb.controller;

import java.util.ArrayList;


import java.util.Collections;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;



import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.SCMXPert.sbmongodb.document.AlertDropdown;
import com.SCMXPert.sbmongodb.document.AlertMaster;
import com.SCMXPert.sbmongodb.document.AlertProfile;

import com.SCMXPert.sbmongodb.document.Customer;
import com.SCMXPert.sbmongodb.document.Goods;
import com.SCMXPert.sbmongodb.document.Route;
import com.SCMXPert.sbmongodb.document.ShipmentTransactions;

import com.SCMXPert.sbmongodb.repository.AlertProfileRepository;
//import com.SCMXPert.sbmongodb.repository.AlertProfileRepository;
import com.SCMXPert.sbmongodb.repository.AlertRepository;
import com.SCMXPert.sbmongodb.repository.CustomerRepository;
import com.SCMXPert.sbmongodb.repository.ShipmentTransactionsRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/SCMXPert")
@CrossOrigin(origins = {  "https://www.smaas.live","https://smaas.live","http://172.17.211.224:3000","http://127.0.0.1:8081","https://www.smaas.org" })
public class AlertsController {

	@Autowired
	private AlertRepository alertrepo;

	@Autowired
	private AlertProfileRepository alertProfileRepo;

	@Autowired
	private ShipmentTransactionsRepository shptansrepo;

	@Autowired
	private CustomerRepository cust;

	@Autowired
	MongoTemplate mongoTemplate;

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@PostMapping("/alerts")
	public boolean alertMaster(@RequestBody @Validated AlertMaster alerts) throws NullPointerException {
		boolean flag = false;
		AlertMaster altm = new AlertMaster();

		List<AlertMaster> customer = alertrepo.findByCustomer_Id(alerts.getCustomer_Id());

		for (AlertMaster alt : customer) {

			if (alt.getAlert_Id().equals(alerts.getAlert_Id())) {
				System.out.println("AlertID already exists");
				flag = false;

				return flag;
			}
		}

		if (alerts.getAlert_Id().equals(null) || alerts.getAlert_Id().equals("")) {
			flag = false;
			return flag;
		} else {

			altm.setAlert_Contact(alerts.getAlert_Contact());
			altm.setAlert_Escallation(alerts.getAlert_Escallation());
			altm.setAlert_Frequency(alerts.getAlert_Frequency());
			altm.setAlert_Id(alerts.getAlert_Id());
			altm.setAlert_Mode(alerts.getAlert_Mode());
			altm.setAlert_Name(alerts.getAlert_Name());
			altm.setAlert_Priority(alerts.getAlert_Priority());
			altm.setAlert_Remediation(alerts.getAlert_Remediation());
			altm.setAlert_Type(alerts.getAlert_Type());
			altm.setChatbot_Id(alerts.getChatbot_Id());
			altm.setCustomer_Id(alerts.getCustomer_Id());
			altm.setCustomer_Name(alerts.getCustomer_Name());
			altm.setEmail_Addresses(alerts.getEmail_Addresses());
			altm.setEmail_Message(alerts.getEmail_Message());
			altm.setEmail_Subject(alerts.getEmail_Subject());
			altm.setEscallation_Time(alerts.getEscallation_Time());
			altm.setPartner_Name(alerts.getPartner_Name());
			altm.setPhone(alerts.getPhone());
			altm.setMessage(alerts.getMessage());
			altm.setSupport_Center(alerts.getSupport_Center());

			altm.setEDI_Message(alerts.getEDI_Message());
			altm.setEDI_Reason(alerts.getEDI_Reason());
			altm.setEDI_Service(alerts.getEDI_Service());
			altm.setWebService(alerts.getWebService());
			altm.setJSON_Format(alerts.getJSON_Format());
			altm.setJSON_Path(alerts.getJSON_Path());
			altm.setPartner_service(alerts.getPartner_service());
			altm.setBlockChain(alerts.getBlockChain());
			altm.setBlockchain_key(alerts.getBlockchain_key());

			alertrepo.save(altm);
			flag = true;
		}

		return flag;

	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/getAlerts/{Customer_Id}")
	public List<AlertMaster> getAlerts(@PathVariable(value = "Customer_Id") String Customer_Id) {

		AlertMaster altm = new AlertMaster();
		List<AlertMaster> alert = alertrepo.findByCustomer_Id(Customer_Id);

		return alert;

	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@PostMapping("/updateAlerts")
	public boolean updateAlerts(@RequestBody @Validated  AlertMaster alerts) {
		boolean flag = false;

		try {
			Query query = new Query();
			query.addCriteria(new Criteria().andOperator(Criteria.where("Alert_Id").is(alerts.getAlert_Id()),
					(Criteria.where("Customer_Id").is(alerts.getCustomer_Id()))));

			Update update1 = new Update();

			update1.set("Alert_Contact", alerts.getAlert_Contact());
			update1.set("Alert_Escallation", alerts.getAlert_Escallation());
			update1.set("Alert_Frequency", alerts.getAlert_Frequency());
			update1.set("Alert_Id", alerts.getAlert_Id());
			update1.set("Alert_Mode", alerts.getAlert_Mode());
			update1.set("Alert_Name", alerts.getAlert_Name());
			update1.set("Alert_Priority", alerts.getAlert_Priority());
			update1.set("Alert_Remediation", alerts.getAlert_Remediation());
			update1.set("Alert_Type", alerts.getAlert_Type());
			update1.set("Chatbot_Id", alerts.getChatbot_Id());
			update1.set("Customer_Name", alerts.getCustomer_Name());
			update1.set("Email_Addresses", alerts.getEmail_Addresses());
			update1.set("Email_Message", alerts.getEmail_Message());
			update1.set("Email_Subject", alerts.getEmail_Subject());
			update1.set("Escallation_Time", alerts.getEscallation_Time());
			update1.set("Partner_Name", alerts.getPartner_Name());
			update1.set("Phone", alerts.getPhone());
			update1.set("Support_Center", alerts.getSupport_Center());
			update1.set("Voice_Phone", alerts.getVoice_Phone());
			update1.set("EDI_Message", alerts.getEDI_Message());
			update1.set("EDI_Reason", alerts.getEDI_Reason());
			update1.set("EDI_Service", alerts.getEDI_Service());
			update1.set("WebService", alerts.getWebService());
			update1.set("JSON_Format", alerts.getJSON_Format());
			update1.set("JSON_Path", alerts.getJSON_Path());
			update1.set("Partner_service", alerts.getPartner_service());
			update1.set("BlockChain", alerts.getBlockChain());
			update1.set("Blockchain_key", alerts.getBlockchain_key());

			mongoTemplate.updateMulti(query, update1, "AlertMaster");
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return flag;
	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/getIncrementedalertId/{Customer_Id}")
	public String getIncrementedALT_ID(@PathVariable(value = "Customer_Id") String Customer_Id) {
		String lastBPId = null;
		String bpid = null;
		String splittedval = null;
		String addZeros = null;
		List<String> listBPIds = new ArrayList<>();

		List<AlertMaster> alert = alertrepo.findByCustomer_Id(Customer_Id);
		System.out.println("Alert::::" + alert);

		// Customer cust = customerRepo.findByCustomerId(Customer_Id);
		// System.out.println("CustomerRepo"+cust);

		List<String> alertlist = new ArrayList<>();
		String als = null;

		for (AlertMaster altr : alert) {

			als = altr.getAlert_Id();

		}
		alertlist.add(als);

		// System.out.println("alertlist " + alertlist.get(0));

		// System.out.println(BPList);
		if (alert.isEmpty()) {
			System.out.println("alertlist in null" + alertlist);
			String incrementString1 = "ALT001";
			/*
			 * System.out.println("IncrementedString:::::" + incrementString1.length());
			 * 
			 * if (incrementString1.length() == 1) { addZeros = "000"; } String finalString1
			 * = "BP"; finalString1 =
			 * finalString1.concat(addZeros).concat(incrementString1);
			 * System.out.println("sdgajghdga:::::" + finalString1);
			 */
			return incrementString1;

		} else {
			Collections.reverse(alertlist);
			System.out.println("alertlist in else " + alertlist);
			String lastId = alertlist.get(0);
			System.out.println("lastId " + lastId);
			/* Integer lasttoInt = Integer.parseInt(lastId); */
			/*
			 * Integer incInt = lasttoInt + 1; String incString = incInt.toString(); return
			 * incString;
			 */
			String[] splitLastId = lastId.split("ALT");
			for (String bps : splitLastId) {
				splittedval = bps;
			}
			Integer splitInteger = Integer.parseInt(splittedval);
			System.out.println("IncrementedString:::::" + splitInteger);
			Integer increment1 = splitInteger + 1;
			String incrementString1 = increment1.toString();
			System.out.println("IncrementedString:::::" + incrementString1);
			if (incrementString1.length() == 1) {
				addZeros = "000";
			} else if (incrementString1.length() == 2) {
				addZeros = "000";

			} else if (incrementString1.length() == 3) {
				addZeros = "00";
			} else if (incrementString1.length() == 4) {
				addZeros = "0";
			} else if (incrementString1.length() == 5) {
				return "Its beyond the Limit of the Present One";
			}

			String finalString1 = "ALT";
			/*
			 * Integer lasttoInt = Integer.parseInt(lastId); Integer incInt = lasttoInt + 1;
			 * String incString = incInt.toString(); return incString;
			 */

			String finalString2 = finalString1 + addZeros.concat(incrementString1);
			System.out.println("sdgajghdga:::::" + finalString2);

			return finalString2;
		}
	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/getAlertsID/{Customer_Id}/{Alert_Id}")
	public List<AlertMaster> getAlertsparticular(@PathVariable(value = "Customer_Id") String Customer_Id,
			@PathVariable(value = "Alert_Id") String Alert_Id) {
		System.out.println("Alert_Id " + Alert_Id);
		AlertMaster altm = new AlertMaster();
		List<AlertMaster> alert = null;

		List<AlertMaster> customer = alertrepo.findByCustomer_Id(Customer_Id);

		for (AlertMaster alt : customer) {

			if (alt.getAlert_Id().equals(Alert_Id)) {

				alert = alertrepo.findByAlert_id(alt.getAlert_Id());
				System.out.println("Alert is for the Customer");
			} else {

				System.out.println("ALert Id doent not exists");
			}
		}
		return alert;
	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@PostMapping("/alertsexcelfileupload")
	public AlertMaster alertsexcel(@RequestParam("file") MultipartFile reapExcelDataFile) throws Exception {
		AlertMaster tempStudent = null;
		List<AlertMaster> tempStudentList = new ArrayList<AlertMaster>();

		XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
		XSSFSheet worksheet = workbook.getSheetAt(0);
		XSSFRow row1 = worksheet.getRow(1);
		Iterator<Cell> cells = row1.cellIterator();

		// There is no data in this row, handle as needed
		for (int i = 1; i < worksheet.getPhysicalNumberOfRows() - 1; i++) {
			tempStudent = new AlertMaster();
			XSSFRow row = worksheet.getRow(i);

			String nullss = "";
			for (int colNum = 0; colNum < row.getLastCellNum(); colNum++) {
				Cell cell = row.getCell(colNum, MissingCellPolicy.CREATE_NULL_AS_BLANK);

				System.out.println("colNum " + colNum + " cell " + cell);

				System.out.println("nulls " + nullss);

				switch (colNum) {
				case 1:
					tempStudent.setCustomer_Id(cell.getStringCellValue());
					break;
				case 2:
					tempStudent.setCustomer_Name(cell.getStringCellValue());
					break;
				case 3:
					tempStudent.setPartner_Name(cell.getStringCellValue());
					break;
				case 4:
					tempStudent.setAlert_Id(cell.getStringCellValue());
					break;
				case 5:
					tempStudent.setAlert_Name(cell.getStringCellValue());
					break;
				case 6:
					tempStudent.setAlert_Type(cell.getStringCellValue());
					break;
				case 7:
					tempStudent.setAlert_Priority(cell.getStringCellValue());
					break;
				case 8:
					tempStudent.setAlert_Mode(cell.getStringCellValue());
					break;

				case 9:
					String email[] = cell.getStringCellValue().split(",");
					tempStudent.setEmail_Addresses(email);
					break;

				case 10:
					tempStudent.setPhone(cell.getStringCellValue());
					break;
				case 11:
					tempStudent.setVoice_Phone(cell.getStringCellValue());
					break;
				case 12:
					tempStudent.setSupport_Center(cell.getStringCellValue());
					break;
				case 13:
					tempStudent.setChatbot_Id(cell.getStringCellValue());
					break;
				case 14:
					tempStudent.setAlert_Contact(cell.getStringCellValue());
					break;
				case 15:
					tempStudent.setAlert_Frequency(cell.getStringCellValue());
					break;
				case 16:
					tempStudent.setAlert_Remediation(cell.getStringCellValue());
					break;
				case 17:
					tempStudent.setAlert_Escallation(cell.getStringCellValue());
					break;
				case 18:
					tempStudent.setEscallation_Time(cell.getStringCellValue());
					break;
				case 19:
					tempStudent.setEDI_Message(cell.getStringCellValue());
					break;
				case 20:
					tempStudent.setEDI_Reason(cell.getStringCellValue());
					break;
				case 21:
					tempStudent.setEDI_Service(cell.getStringCellValue());
					break;
				case 22:
					tempStudent.setWebService(cell.getStringCellValue());
					break;
				case 23:
					tempStudent.setJSON_Format(cell.getStringCellValue());
					break;
				case 24:
					tempStudent.setJSON_Path(cell.getStringCellValue());
					break;
				case 25:
					tempStudent.setPartner_service(cell.getStringCellValue());
					break;
				case 26:
					tempStudent.setBlockChain(cell.getStringCellValue());
					break;
				case 27:
					tempStudent.setBlockchain_key(cell.getStringCellValue());
					break;
				}

			}
			mongoTemplate.insert(tempStudent);
		}

		return tempStudent;
	}

	////////////////////////////////////////// Alerts
	////////////////////////////////////////// profile///////////////////////////////////////////////////////////////

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@PostMapping("/alertsProfile")
	public boolean alertProfile(@Validated @RequestBody List<@Valid AlertProfile> alertsprofo) throws NullPointerException {

		boolean flag = false;
		for (AlertProfile alertsprofile : alertsprofo) {

			AlertProfile altprf = new AlertProfile();
			altprf.setAlert_Id(alertsprofile.getAlert_Id());
			altprf.setAlert_Profile(alertsprofile.getAlert_Profile());
			altprf.setAlert_Type(alertsprofile.getAlert_Type());
			// altprf.setBuff(alertsprofile.getBuff());

			altprf.setCustomer_Id(alertsprofile.getCustomer_Id());
			altprf.setCustomer_Name(alertsprofile.getCustomer_Name());
			altprf.setEvent_Name(alertsprofile.getEvent_Name());
			altprf.setGeo_Fence(alertsprofile.getGeo_Fence());
			altprf.setGoods_Type(alertsprofile.getGoods_Type());
			altprf.setPartner_Name(alertsprofile.getPartner_Name());
			altprf.setProfile_Desc(alertsprofile.getProfile_Desc());
			altprf.setRoute_Id(alertsprofile.getRoute_Id());
			altprf.setSequence(alertsprofile.getSequence());

			alertProfileRepo.save(altprf);
			flag = true;
		}
		return flag;

	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/getAlertsProfile/{Customer_Id}")
	public List<AlertProfile> getAlertsProfile(@PathVariable(value = "Customer_Id") String Customer_Id) {

		AlertMaster altm = new AlertMaster();
		List<AlertProfile> alertprofo = alertProfileRepo.findByCustomer_Id(Customer_Id);

		return alertprofo;

	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@PostMapping("/updateAlertProfile")
	public boolean updateAlertProfile(@Validated @RequestBody List<@Valid AlertProfile> alertsProfile) {
		for (AlertProfile alerts : alertsProfile) {
			
			Query query = new Query();
			query.addCriteria(new Criteria().andOperator(Criteria.where("Alert_Id").is(alerts.getAlert_Id()),
					(Criteria.where("Customer_Id").is(alerts.getCustomer_Id()))));

			Update update1 = new Update();

			update1.set("Alert_Profile", alerts.getAlert_Profile());
			update1.set("Sequence", alerts.getSequence());
			update1.set("Profile_Desc", alerts.getProfile_Desc());

			update1.set("Route_Id", alerts.getRoute_Id());
			update1.set("Goods_Type", alerts.getGoods_Type());
			update1.set("Event_Name", alerts.getEvent_Name());
			update1.set("Alert_Type", alerts.getAlert_Type());
			// update1.set("Buff", alerts.getBuff());
			update1.set("Geo_Fence", alerts.getGeo_Fence());
			update1.set("Alert_Id", alerts.getAlert_Id());
			update1.set("Customer_Id", alerts.getCustomer_Id());
			update1.set("Customer_Name", alerts.getCustomer_Name());
			update1.set("Partner_Name", alerts.getPartner_Name());

			mongoTemplate.updateMulti(query, update1, "AlertProfile");
		}
		return false;
	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@PostMapping("/alertsprofileexcelfileupload")
	public AlertProfile alertProfileExcel(@RequestParam("file") MultipartFile reapExcelDataFile) throws Exception {
		AlertProfile alertprofile = null;
		List<AlertProfile> tempStudentList = new ArrayList<AlertProfile>();

		XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
		XSSFSheet worksheet = workbook.getSheetAt(0);
		XSSFRow row1 = worksheet.getRow(1);
		Iterator<Cell> cells = row1.cellIterator();

		// There is no data in this row, handle as needed
		for (int i = 1; i < worksheet.getPhysicalNumberOfRows() - 1; i++) {
			alertprofile = new AlertProfile();
			XSSFRow row = worksheet.getRow(i);

			String nullss = "";
			for (int colNum = 0; colNum < row.getLastCellNum(); colNum++) {
				Cell cell = row.getCell(colNum, MissingCellPolicy.CREATE_NULL_AS_BLANK);

				System.out.println("colNum " + colNum + " cell " + cell);

				System.out.println("nulls " + nullss);

				switch (colNum) {
				case 1:
					alertprofile.setCustomer_Id(cell.getStringCellValue());
					break;

				case 2:
					alertprofile.setCustomer_Name(cell.getStringCellValue());
					break;
				case 3:
					alertprofile.setPartner_Name(cell.getStringCellValue());
					break;
				case 4:
					alertprofile.setAlert_Profile(cell.getStringCellValue());

					break;
				case 5:
					alertprofile.setSequence(cell.getStringCellValue());
					break;
				case 6:
					alertprofile.setProfile_Desc(cell.getStringCellValue());
					break;
				case 7:
					alertprofile.setRoute_Id(cell.getStringCellValue());
					break;
				case 8:
					alertprofile.setGoods_Type(cell.getStringCellValue());
					break;
				case 9:
					alertprofile.setEvent_Name(cell.getStringCellValue());
					break;
				case 10:
					alertprofile.setAlert_Type(cell.getStringCellValue());
					break;

//				case 11:
//					alertprofile.setBuff(cell.getStringCellValue());
//					break;
				case 12:
					alertprofile.setGeo_Fence(cell.getStringCellValue());
					break;

				case 13:
					alertprofile.setAlert_Id(cell.getStringCellValue());
					break;

				}

			}
			mongoTemplate.insert(alertprofile);
		}

		return alertprofile;
	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/getAlertsProfileDDData/{Customer_Id}")
	public List<AlertDropdown> getAlertsProfileData(@PathVariable(value = "Customer_Id") String Customer_Id) {

		List<AlertDropdown> alertdropdown = new ArrayList<AlertDropdown>();
		AlertDropdown altd = new AlertDropdown();
		List<AlertMaster> alerts = alertrepo.findByCustomer_Id(Customer_Id);
		List<String> alrtid = new ArrayList<>();
		List<String> alrttyp = new ArrayList<>();

		for (AlertMaster alertid : alerts) {

			alrtid.add(alertid.getAlert_Id());
			altd.setAlertId(alrtid);
			alrttyp.add(alertid.getAlert_Type());
			List<String> eventids = new ArrayList<>(new HashSet<>(alrttyp));
			altd.setAlertType(eventids);
		}

		Customer customer = cust.findByCustomerId(Customer_Id);

		List<Route> routelist = customer.getRoute();

		List<String> routid = new ArrayList<>();
		for (Route rout : routelist) {
			routid.add(rout.getRoute_Id());

			altd.setRouteId(routid);

		}

		List<String> goodsid = new ArrayList<>();
		List<String> goodsType = new ArrayList<>();
		for (Goods good : customer.getGoods()) {
			goodsid.add(good.getGoods_Id());
			altd.setGoodsId(goodsid);
			goodsType.add(good.getGoods_Item());
			altd.setGoodsType(goodsType);
		}

		List<ShipmentTransactions> events = shptansrepo.findByCustomer_Id(Customer_Id);
		List<String> eventnm = new ArrayList<String>();
		for (ShipmentTransactions shpt : events) {
			//System.out.println("shpt.getEvent_Name() "+shpt.getEvent_Name());
			eventnm.add(shpt.getEvent_Name());
			List<String> eventnms = new ArrayList<>(new HashSet<>(eventnm));
			altd.setEventId(eventnms);
		}

		alertdropdown.add(altd);

		return alertdropdown;

	}

}
