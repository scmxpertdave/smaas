package com.SCMXPert.sbmongodb.controller;

import java.awt.image.BufferedImage;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

import javax.imageio.ImageIO;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.SCMXPert.sbmongodb.document.BusinessPartner;
import com.SCMXPert.sbmongodb.document.CompleteShipment;
import com.SCMXPert.sbmongodb.document.CompleteShipmentDto;
import com.SCMXPert.sbmongodb.document.Countries;
import com.SCMXPert.sbmongodb.document.CreateNewShipmentDto;
import com.SCMXPert.sbmongodb.document.CreateShipment;
import com.SCMXPert.sbmongodb.document.CustomBP;
import com.SCMXPert.sbmongodb.document.Customer;
import com.SCMXPert.sbmongodb.document.DeviceData;
import com.SCMXPert.sbmongodb.document.DeviceDataStream;
import com.SCMXPert.sbmongodb.document.DropDownDto;
import com.SCMXPert.sbmongodb.document.DropDownShipmentDetails;
import com.SCMXPert.sbmongodb.document.FiltersData;
import com.SCMXPert.sbmongodb.document.MailDto;
import com.SCMXPert.sbmongodb.document.Mailinfo;
import com.SCMXPert.sbmongodb.document.Sendmail;
import com.SCMXPert.sbmongodb.document.ShipmentDetails;
import com.SCMXPert.sbmongodb.document.ShipmentDraftDto;
import com.SCMXPert.sbmongodb.document.ShipmentDraftPartialGet;
import com.SCMXPert.sbmongodb.document.ShipmentDrafts;
import com.SCMXPert.sbmongodb.document.ShipmentStatusCount;
import com.SCMXPert.sbmongodb.document.ShipmentTransactions;
import com.SCMXPert.sbmongodb.document.Shipments;
import com.SCMXPert.sbmongodb.document.SimpleMail;
import com.SCMXPert.sbmongodb.document.UpdateEvent;
import com.SCMXPert.sbmongodb.document.UpdateEventGet;
import com.SCMXPert.sbmongodb.document.UpdateShipmentEvent;
import com.SCMXPert.sbmongodb.document.UserDetails;
import com.SCMXPert.sbmongodb.document.UserTotalDetails;
import com.SCMXPert.sbmongodb.repository.BusinessPartnerRepository;
import com.SCMXPert.sbmongodb.repository.CompleteShipmentRepo;
import com.SCMXPert.sbmongodb.repository.CountriesRepository;
import com.SCMXPert.sbmongodb.repository.CustomerRepository;
import com.SCMXPert.sbmongodb.repository.DeviceDataStreamRepository;
import com.SCMXPert.sbmongodb.repository.DevicesRepository;
import com.SCMXPert.sbmongodb.repository.DropDownRepo;
import com.SCMXPert.sbmongodb.repository.DropDownShipmentDetailsRepo;
import com.SCMXPert.sbmongodb.repository.SaveDraftsRepo;
import com.SCMXPert.sbmongodb.repository.ShipmentDraftsRepo;
import com.SCMXPert.sbmongodb.repository.ShipmentSaveDraftRepo;
import com.SCMXPert.sbmongodb.repository.ShipmentTransactionsRepository;
import com.SCMXPert.sbmongodb.repository.ShipmentsRepository;
import com.SCMXPert.sbmongodb.repository.UpdateEventGetRepo;
import com.SCMXPert.sbmongodb.repository.UserDetailsRepository;
import com.SCMXPert.sbmongodb.repository.UserTotalDetailsRepository;
import com.SCMXPert.sbmongodb.sequence.dao.EvenIdSequenceDao;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.result.UpdateResult;

import software.amazon.awssdk.services.s3.model.S3Exception;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bson.Document;
import org.json.JSONObject;


@Controller
@RequestMapping("/SCMXPert")
@CrossOrigin(origins = {  "https://www.smaas-lb.de:8080/","https://www.smaas.live","https://smaas.live","http://172.17.211.224:3000","http://127.0.0.1:8081","https://www.smaas.org" })

public class DashboardController {

	@Autowired
	private MailSenderService senderService;

	@Autowired
	private ShipmentsRepository shiprepo;

	@Autowired
	private CountriesRepository countiresRepo;

	@Autowired
	private CompleteShipmentRepo completeShipRepo;

	@Autowired
	private ShipmentTransactionsRepository shiptransrepo;

	@Autowired
	private UserDetailsRepository userdetailsrepo;

	@Autowired
	private UserTotalDetailsRepository usertotaldetailsrepo;

	@Autowired
	private CustomerRepository customerepo;

	@Autowired
	private DeviceDataStreamRepository devicedatarepo;

	@Autowired
	private DevicesRepository devicerepo;

	@Autowired
	private DropDownRepo dprepo;

	@Autowired
	private ShipmentDraftsRepo shipmentDraftsRepo;

	@Autowired
	private EvenIdSequenceDao evendiddao;

	@Autowired
	MongoOperations mongoOperation;

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	private DropDownShipmentDetailsRepo ddrepo1;

	@Autowired
	private BusinessPartnerRepository bussinesRepo;

	@Autowired
	private ShipmentSaveDraftRepo savedraftRepo;

	@Autowired
	private SaveDraftsRepo saveShipDraftsRepo;

	@Autowired
	private UpdateEventGetRepo updateeventgetrepo;

	private static final String HOSTING_SEQ_KEY = "hosting";

	@Bean
	public HttpFirewall defaultHttpFirewall() {
		return new DefaultHttpFirewall();
	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/getShipmentTransaction/{Customer_Id}")
	public List<ShipmentTransactions> getshipmentstrans(@PathVariable String Customer_Id) {

		/*
		 * Shipments ship = shiprepo.findByCustomer_Id(Customer_Id.trim());
		 * System.out.println("Delivered Status "+ship.getShipment_Status() +
		 * "  Delivered_Date.Date  "+ ship.getDelivered_Date().getDate()
		 * +"  Comments "+ship.getComments().toString() ); ShipmentTransactions shipTran
		 * = shiptransrepo.findBySingleCustomer_Id(Customer_Id.trim()); Devices device =
		 * devicerepo.findByBP_Id(shipTran.getBP_Id());
		 * System.out.println("\n "+device.getImeiNumber()); DeviceDataStream
		 * deviceStream = devicedatarepo.findByModem_IMEI(device.getImeiNumber());
		 * System.out.println("\n "+deviceStream.getSensorId());
		 */
		List<ShipmentTransactions> sp = shiptransrepo.findByCustomer_Id(Customer_Id.trim());

		return sp;
	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("userdetails/{bp_Id}")
	public UserDetails getUserdata(@PathVariable String bp_Id) {
		return userdetailsrepo.findByUserid(bp_Id.trim());

	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("userTotaldetails/{bp_Id}")
	public UserTotalDetails getUsertotaldata(@PathVariable String bp_Id) {
		System.out.println(bp_Id +" bp_Id ");
		return usertotaldetailsrepo.findByBP_Id(bp_Id.trim());

	}

	// Filters Rest EndPoints
	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/getFiltersData/{Customer_Id}")
	public FiltersData getFilters(@PathVariable(value = "Customer_Id") String Customer_id) {

		/*
		 * System.out.println("Coming"); Customer cus =
		 * customerepo.findByCustomer_id(Customer_id.trim()); FiltersData fd = new
		 * FiltersData(); fd.setBusiness_Partner_Id(cus.getBusiness_Partner_Id());
		 * fd.setDepartments(cus.getDepartments()); fd.setDevice_Id(cus.getDevice_Id());
		 * fd.setGoods(fd.getGoods());
		 */
		System.out.println("In getFiltersData");
		return customerepo.findByCustomer_id(Customer_id.trim());
	}

	// Get Shipments RestEndpoints
	/*
	 * @ResponseBody
	 * 
	 * @GetMapping("/getShipments/{Customer_Id}/{Created_By}") public
	 * List<ShipmentDetails> getShipmentsInfo(@PathVariable(value = "Customer_Id")
	 * String Customer_id,
	 * 
	 * @PathVariable(value = "Created_By") String Created_By) {
	 * 
	 * List<ShipmentDetails> spd = new ArrayList<ShipmentDetails>(); List<Shipments>
	 * sp = shiprepo.getShipments(Customer_id.trim(), Created_By.trim()); for
	 * (Shipments shp : sp) {
	 * 
	 * ShipmentDetails innerSpd = new ShipmentDetails();
	 * innerSpd.setCustomer_Id(shp.getCustomer_Id());
	 * innerSpd.setCreated_By(shp.getCreated_By());
	 * innerSpd.setShipment_Id(shp.getShipment_Id());
	 * innerSpd.setShipment_Num(shp.getShipment_Num());
	 * innerSpd.setRoute_From(shp.getRoute_From());
	 * innerSpd.setRoute_To(shp.getRoute_To());
	 * innerSpd.setCreated_Date(shp.getCreated_Date());
	 * innerSpd.setEstimated_Delivery_Date(shp.getEstimated_Delivery_Date());
	 * innerSpd.setDelivered_Date(shp.getDelivered_Date());
	 * innerSpd.setDelivered_Status(shp.getShipment_Status());
	 * 
	 * List<ShipmentTransactions> innerSPT =
	 * shiptransrepo.findByShipment_Id(shp.getShipment_Id().trim());
	 * innerSpd.setDevice_Id(innerSPT.get(0).getDevice_Id());
	 * innerSpd.setEvent_Status(shiptransrepo.event_status(shp.getShipment_Id().trim
	 * ()));
	 * 
	 * innerSpd.setWayPoints(shiptransrepo.getlatLong(innerSPT.get(0).getDevice_Id()
	 * , shp.getCreated_Date())); spd.add(innerSpd);
	 * 
	 * }
	 * 
	 * return spd; }
	 */
	/*
	 * @GetMapping("/mail/{to}/{content}/{subject}") public void
	 * retrieveDetailsForCourse(@PathVariable String to,
	 * 
	 * @PathVariable String content,@PathVariable String subject) {
	 * System.out.println(to); System.out.println(content);
	 * System.out.println(subject); }
	 * 
	 * @ResponseBody
	 * 
	 * @PostMapping("/MailSender") public String sendmail(@RequestBody SimpleMail
	 * mail) {
	 * 
	 * //boolean flag =false; System.out.println(mail.getTo());
	 * System.out.println(mail.getContent()); System.out.println(mail.getSubject());
	 * 
	 * 
	 * 
	 * 
	 * try {
	 * senderService.sendSimpleMail(mail.getTo(),mail.getSubject(),mail.getContent()
	 * ); String[] username = view.getTo(); System.out.println("Mails:::"+username);
	 * 
	 * }catch(Exception e){
	 * 
	 * }
	 * 
	 * return "thank you";
	 * 
	 * 
	 * }
	 */

	///// MailSender/////
	// @RequestMapping(value = "/controller/path/saveCanvasImage", method =
	///// RequestMethod.POST)
	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@PostMapping("/saveCanvasImage")
	public Map<String, Object> saveCanvasImage(
			@RequestParam(value = "imageBase64", defaultValue = "") String imageBase64,
			@RequestParam(value = "filename", defaultValue = "") String filename) {
		Map<String, Object> res = new HashMap<String, Object>();
		System.out.println("dfsgfg" + filename);
		File imageFile = new File("C:/Users/Administrator/Desktop/apache-tomcat-8.5.12/webapps/ROOT/WebContent/Shipment_Data/Tables"+ filename+".png");
		// File imageFile = new File("D:/SCM/images/canvasImage.png");  C:/Users/Administrator/Desktop/apache-tomcat-8.5.12/webapps/ROOT/Shipment_DataTables
		// File imageFile = new File("127.0.0.1:8081/shares/");

		try {
			byte[] decodedBytes = DatatypeConverter.parseBase64Binary(imageBase64.replaceAll("data:image/.+;base64,", ""));
			BufferedImage bfi = ImageIO.read(new ByteArrayInputStream(decodedBytes));
			ImageIO.write(bfi, "png", imageFile);
			bfi.flush();
			res.put("ret", 0);
		} catch (Exception e) {
			res.put("ret", -1);
			res.put("msg", "Cannot process due to the image processing error.");
			return res;
		}

		return res;
	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@PostMapping("/saveCanvasGraphImage")
	public Map<String, Object> saveCanvasGraphImage(
			@RequestParam(value = "imageBase64", defaultValue = "") String imageBase64,
			@RequestParam(value = "filename", defaultValue = "") String filename) {
		Map<String, Object> res = new HashMap<String, Object>();
		System.out.println("dfsgfg" + filename);
		File imageFile = new File(
				"C:/Users/Administrator/Desktop/apache-tomcat-8.5.12/webapps/ROOT/WebContent/Shipment_Data/Graph/"
						+ filename + ".png");
		// File imageFile = new File("D:/SCM/images/canvasImage.png");
		// File imageFile = new File("127.0.0.1:8081/shares/");

		try {
			byte[] decodedBytes = DatatypeConverter
					.parseBase64Binary(imageBase64.replaceAll("data:image/.+;base64,", ""));
			BufferedImage bfi = ImageIO.read(new ByteArrayInputStream(decodedBytes));
			ImageIO.write(bfi, "png", imageFile);
			bfi.flush();
			res.put("ret", 0);
		} catch (Exception e) {
			res.put("ret", -1);
			res.put("msg", "Cannot process due to the image processing error.");
			return res;
		}

		return res;
	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/getcountryinfo/{name}")
	public Countries getcountryinfo(@PathVariable String name) {
		System.out.println("name " + name);
		List<Countries> crp = countiresRepo.findByname(name.trim());

		Countries crnt = new Countries();

		for (Countries cr : crp) {
			crnt.setName(cr.getName());
			crnt.setStates(cr.getStates());
		}

		return crnt;
	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/getAllCountries")
	public List<String> getCountries() {
		List<Countries> crp = countiresRepo.findAll();
		String countries = null;
		Countries crnt = new Countries();
		List<String> countrieslist = new ArrayList<String>();
		for (Countries cr : crp) {
			System.out.println("cr.getName() " + cr.getName());
			countries = cr.getName();
			countrieslist.add(countries);
		}
		return countrieslist;
	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@PostMapping("/saveupdateImage")
	public Map<String, Object> saveUpdateImage(
			@RequestParam(value = "imageBase64", defaultValue = "") String imageBase64,
			@RequestParam(value = "filename", defaultValue = "") String filename,
			@RequestParam(value = "shipment_id", defaultValue = "") String Shipment_Id,
			@RequestParam(value = "event_id", defaultValue = "") String Event_Id) {
		Map<String, Object> res = new HashMap<String, Object>();
		System.out.println("dfsgfg" + filename);
		//File imageFile = new File("C:/Users/Administrator/Desktop/apache-tomcat-8.5.12/webapps/ROOT/Shipment_Data/"+ Shipment_Id + "/" + Event_Id + "/" + filename + ".png");
		File imageFile = new File("C:/Users/Administrator/Desktop/apache-tomcat-8.5.12/webapps/ROOT/Shipment_Data/"+ Shipment_Id + "/" + Event_Id + "/" + filename + ".png");
		if (!imageFile.exists()) {
			if (imageFile.mkdirs()) {
				System.out.println("Multiple directories are created!");
			} else {
				System.out.println("Failed to create multiple directories!");
			}
		}
		// File imageFile = new File("D:/SCM/images/canvasImage.png");
		// File imageFile = new File("127.0.0.1:8081/shares/");

		try {
			byte[] decodedBytes = DatatypeConverter
					.parseBase64Binary(imageBase64.replaceAll("data:image/.+;base64,", ""));
			BufferedImage bfi = ImageIO.read(new ByteArrayInputStream(decodedBytes));
			ImageIO.write(bfi, "png", imageFile);
			bfi.flush();
			res.put("ret", 0);
		} catch (Exception e) {
			res.put("ret", -1);
			res.put("msg", "Cannot process due to the image processing error.");
			return res;
		}

		return res;
	}
/////
	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@PostMapping("/saveupdatePdf")
	public Map<String, Object> saveUpdatePdf(
			@RequestParam(value = "imageData", defaultValue = "") String imageData,
			@RequestParam(value = "filename", defaultValue = "") String filename,
			@RequestParam(value = "shipment_id", defaultValue = "") String Shipment_Id,
			@RequestParam(value = "event_id", defaultValue = "") String Event_Id) {
		Map<String, Object> res = new HashMap<String, Object>();
		System.out.println("dfsgfg" + filename);
		System.out.println("pdf is " + imageData);
		//File imageFile = new File("C:/Users/Administrator/Desktop/apache-tomcat-8.5.12/webapps/ROOT/Shipment_Data/"+ Shipment_Id + "/" + Event_Id + "/" + filename + ".png");
		//File imageFile = new File("C:/Users/Administrator/Desktop/apache-tomcat-8.5.12/webapps/ROOT/Shipment_Data/"+ Shipment_Id + "/" + Event_Id + "/" + filename + ".pdf");
		  File imageFile = new File("d:/newPdf.pdf");
//		if (!imageFile.exists()) {
//			if (imageFile.mkdirs()) {
//				System.out.println("Multiple directories are created!");
//			} else {
//				System.out.println("Failed to create multiple directories!");
//			}
//		}
		// File imageFile = new File("D:/SCM/images/canvasImage.png");
		// File imageFile = new File("127.0.0.1:8081/shares/");
		
		 String b64=	imageData.replace("data:application/pdf;base64,", "").replace("\"","").replace(" ","+");
		 System.out.println("b64 " + b64);
		 try ( FileOutputStream fos = new FileOutputStream(imageFile); ) {
		      // To be short I use a corrupted PDF string, so make sure to use a valid one if you want to preview the PDF file
		     // String b64 = "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ULN9be3bb78tLCxcvHixuRZ548aNuXPnqpA//PCD7S42UaYjiA18E2Xr1683y0Rnu/6p+M0f6NhXwXR0UGnV2NZcUxtmp2bNmqXwt2/qtclDE1vL65ChjagGVFda96effvrzn/+c2Wzv7Oz84osvbKniA5cPFPjfffdd0iIBGP0Gm+3xxCP6EmX7999/H+wqkY0bN7pjy63QoYazZ8+2xwJtf8WKFXaWSdeOjg7FcvBdFi5cqGNEPIVsV8SZkSQeWxvBkZyGDj22GR9MbO2jjoOhK2Yw283naEYiuXTg1uE7aakAjHJDyHb3Bh8pKyszY+cSZbsawOZeHhPRCrorV64sX75cUxLd73n79u3Vq1eb5NH/9dq9Cyk+EMtfffXVzJkztcBnn31mxmwr4U2pzP1WmqgTCjNIPp5Ctsd/fU+W/r9u3Tr33qUXL16YDea/udPn4sWLesfS0lL3DCWY2FrR3jBlbolK594lI/1sD9bzsmXLdMZkT68AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAARPg/sPwZDQplbmRzdHJlYW0KZW5kb2JqCjExIDAgb2JqCjw8L0JpdHNQZXJDb21wb25lbnQgOC9Db2xvclNwYWNlL0RldmljZUdyYXkvRmlsdGVyL0ZsYXRlRGVjb2RlL0hlaWdodCA0MDAvTGVuZ3RoIDIxNy9TdWJ0eXBlL0ltYWdlL1R5cGUvWE9iamVjdC9XaWR0aCA1MDA+PnN0cmVhbQp4nO3BMQEAAADCoP6pZwZ/oAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACA1wAU0GBXCmVuZHN0cmVhbQplbmRvYmoKeHJlZgowIDEyCjAwMDAwMDAwMDAgNjU1MzUgZiAKMDAwMDAwMTQyOSAwMDAwMCBuIAowMDAwMDAxODEwIDAwMDAwIG4gCjAwMDAwMDE0NzQgMDAwMDAgbiAKMDAwMDAwMDc3NiAwMDAwMCBuIAowMDAwMDAwMDE1IDAwMDAwIG4gCjAwMDAwMDE3MjEgMDAwMDAgbiAKMDAwMDAwMTYzMSAwMDAwMCBuIAowMDAwMDAxMjY0IDAwMDAwIG4gCjAwMDAwMDA5MTggMDAwMDAgbiAKMDAwMDAwMTg2NyAwMDAwMCBuIAowMDAwMDEwMzMzIDAwMDAwIG4gCnRyYWlsZXIKPDwvSUQgWzw0NDUxMzE1MDg5NGQwZTBmNWNhYmE2M2FiYzY1M2ZkOD48NDQ1MTMxNTA4OTRkMGUwZjVjYWJhNjNhYmM2NTNmZDg+XS9JbmZvIDMgMCBSL1Jvb3QgMSAwIFIvU2l6ZSAxMj4+CiVpVGV4dC03LjEuOQpzdGFydHhyZWYKMTA3MDcKJSVFT0YK";
		      byte[] decoder = Base64.getDecoder().decode(b64);

		      fos.write(decoder);
		      System.out.println("PDF File Saved");
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		  

		return res;
	}

	
	@Autowired
	private AmazonS3 amazonS3Client;
	
	@Value("${application.bucket.name}")
	private String bucketName;
	
	@Value("${s3.bucket2}")
	private String archivedbucketName;
	
//    private static class UniqueIdGenerator {
//        private AtomicInteger counter = new AtomicInteger(0);

//        public String generateUniqueId() {
//            int id = counter.incrementAndGet();
//            String uniqueId = String.format("D%08d", id); // Format the ID as "D0000001"
//            return uniqueId;
//        }       
        private String generateUniqueId(String previousId) {
          AtomicInteger counter = new AtomicInteger(0);
            // Generate new ID logic
            int id = counter.incrementAndGet();
            String uniqueId = String.format("D%08d", id);
    		System.out.println("uniqueId above if  ");
    		System.out.println(uniqueId);
            // Compare previousId and increment accordingly
            // ...
            if(previousId == null || previousId == "" || previousId.isEmpty()) {
            //if(previousId == null) {
        		System.out.println("uniqueId  ");
        		System.out.println(uniqueId);
            	return uniqueId;
            }
         //   else if(previousId != null) {
            else {
//            	String incrementedPreviousId = "";
//            	String incrementedId = previousId+1;
//            	incrementedPreviousId = incrementedPreviousId+incrementedId; 
//        		System.out.println("incrementedPreviousId  ");
//        		System.out.println(incrementedPreviousId);
        		
        		String receivedId = previousId;
        		// Extract the numeric part by removing the leading non-digit characters
        		String numericPart = receivedId.replaceAll("\\D", "");
        		// Convert the numeric part to an integer and increment it
        		int incrementedIdValue = Integer.parseInt(numericPart) + 1;
        		// Format the incremented value back to the original length with leading zeros
        		String incrementedIdval = String.format("D%08d", incrementedIdValue);
        		System.out.println("incrementedPreviousId  ");
        		System.out.println(incrementedIdval);
        		
            	return incrementedIdval;
            }

         //    return "newId"; // Return the generated new ID
        }  
        
        
  //  }

 //   private UniqueIdGenerator idGenerator = new UniqueIdGenerator();
       
	   
	@ResponseBody
	@PostMapping("/uploadEvidencetoS3")
//	public ResponseEntity<String> uploadFile(@RequestParam("fileName") String fileName,
//	public Map<String, Object> uploadFile(@RequestParam("fileName") String fileName,
    public List<String> uploadFile(@RequestParam("fileName") String fileName,
			                       @RequestParam("file") List<MultipartFile> files,
			                       @RequestParam("data") String jsonData
			                              ) throws IOException {
	    System.out.println("files size   " + files.size());
//		System.out.println("string json data " + jsonData);	
//    	Map<String, Object> response = new HashMap<>();
		JSONObject eventObj = new JSONObject(jsonData);
		System.out.println("::::: JSONObject ::::::::" );	
		System.out.println(jsonData);	
//		System.out.println("typeOfReference  " + eventObj.getString("typeOfReference"));
//		System.out.println("eventStatus is   " + eventObj.getString("eventStatus"));
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = formatter.format(date);		
/////   ArrayList<String> evidenceUrlList = new ArrayList<>(); 	    
  //    Generate unique ID
    //  String previousId = shiptransrepo.getLastId(); // list.get last dms id by using index -1 
    //  String newId = generateUniqueId(previousId);	
		
		List<String> urlList = new ArrayList<>(); 
		List<String> fileNameList = new ArrayList<>();
		List<String> typeofEvidenceList = new ArrayList<>(); 
		List<String> evidenceDescList = new ArrayList<>();
		List<String> evidenceForList = new ArrayList<>();
		List<String> dateList = new ArrayList<>();
		List<String> docIdList = new ArrayList<>();
		
		ShipmentTransactions trans = new ShipmentTransactions();
		List<ShipmentTransactions> list = shiptransrepo.findByShipment_Id(eventObj.getString("shipment_Number"));    //shipment_Number is shipment_Id(T000000027)
		List<ShipmentTransactions> listforMultipleInvoice = shiptransrepo.findByShipment_Num(eventObj.getString("shipment_Num"));
		Shipments shipment = new Shipments();
		List<Shipments> shipmentlist = shiprepo.findAll();
		
		System.out.println("ShipmentTransactions list  " + list);
		System.out.println(list);		
		System.out.println("ShipmentTransactions list  " + listforMultipleInvoice);
		
		System.out.println("urlList");
//		System.out.println(urlList);
		System.out.println("fileNameList");
//		System.out.println(fileNameList);
		 String previousId = null;			
		for(ShipmentTransactions list1 : list) {
//			String previousId = list1.getDocId().toString();  // list.get last dms id by using index -1 
//		    String newId = generateUniqueId(previousId);
//		      System.out.println("newId  for  dms  ");	
//		      System.out.println(newId);	
			  System.out.println("for-loop, i=" + list1);			
	//		if(list1.getEvent_Name().equals(eventObj.get("eventType"))) {
			  System.out.println(list1.getEvent_Name());	
			if(list1.getEvent_Name().equals("Upload Documents")) {	
				 System.out.println(":::::::::: Inside the condition :::::::");	
				 System.out.println(":::::::::: Partner  :::::::");	
				 System.out.println(eventObj.get("partner"));	
//				if (list1.getPartner().equals(eventObj.get("partner"))) {					
					if(eventObj.get("evidence_For").equals("Shipment Id") || eventObj.get("evidence_For").equals("Delivery")) {
						
						if(list1.getEvidence_URL() != null) {
							urlList.addAll(Arrays.asList(list1.getEvidence_URL()));
						}
						if(list1.getEvidenceList() != null) {
							fileNameList.addAll(Arrays.asList(list1.getEvidenceList()));							
						}
						if(list1.getTypeOfReference() != null) {
							typeofEvidenceList.addAll(Arrays.asList(list1.getTypeOfReference()));							
						}
						if(list1.getEvidence_Description() != null) {
							evidenceDescList.addAll(Arrays.asList(list1.getEvidence_Description()));							
						}
						if(list1.getEvidence_For() != null) {
							evidenceForList.addAll(Arrays.asList(list1.getEvidence_For()));
						}	
						if(list1.getDocCreatedDate() != null) {
							dateList.addAll(Arrays.asList(list1.getDocCreatedDate()));						
						}
						if(list1.getDocId() != null) {
							docIdList.addAll(Arrays.asList(list1.getDocId()));							
						}
						
						String[] listDocsId = list1.getDocId();
						
						for (MultipartFile file : files) {
							
//							if(list1.getEvidence_URL() == null) {
//								System.out.println("Evidence URL is null "+list1.getEvidence_URL());
//							}
//							else {
//								urlList.addAll(Arrays.asList(list1.getEvidence_URL()));		

							
							System.out.println("for-loop for ship id and delivery, i= " + file);							
							String fileNames = file.getOriginalFilename();
							int lastIndexOfDot = fileNames.lastIndexOf(".");
							//System.out.println("lastIndexOfDot");
							//System.out.println(lastIndexOfDot);
							if (lastIndexOfDot != -1) {
								 String fileNameWithoutExtension = fileNames.substring(0, lastIndexOfDot);
								 String appendInvoice_No  = eventObj.get("shipment_Num").toString();
								 String appendBatch_id = eventObj.get("batch_Id").toString();
							   //fileNames = fileNameWithoutExtension+"_"+appendInvoice_No+"_"+appendBatch_id+fileNames.substring(lastIndexOfDot);
								 System.out.println(" extension of fileName");
								 System.out.println(fileNames.substring(lastIndexOfDot));
								 fileNames = appendInvoice_No+"_"+fileNameWithoutExtension+fileNames.substring(lastIndexOfDot);
							}
							System.out.println("fileNames_without_extension");
							System.out.println(fileNames);
							System.out.println("fileNames of files Uploaded to AWS S3:::::: in if  " +fileNames);
					//		fileNameList.add(fileNames);
							//typeofEvidenceList.add(fileName);
							System.out.println("fileName list::::::in if   " +fileNameList);
							
							try {
								ObjectMetadata metadata = new ObjectMetadata();
								metadata.setContentLength(file.getSize());
								System.out.println(" ContentLength  ");	
								System.out.println(file.getSize());	
					//  The commented lines below returns as File uploaded: with the file name.		
//								amazonS3Client.putObject(bucketName, keyName, file.getInputStream(), metadata);			
//								return "File uploaded: " + keyName;
					//  The lines below returns the url to access the uploaded docs in s3.		
								 amazonS3Client.putObject(
										   new PutObjectRequest(bucketName, fileNames, file.getInputStream(), metadata)
										      .withCannedAcl(CannedAccessControlList.PublicRead));
								 System.out.println(":::::: Completed uploading to Evidence bucket  ");	
								 amazonS3Client.getUrl(bucketName, fileNames).toString();
								 
								 amazonS3Client.putObject(
										   new PutObjectRequest(archivedbucketName, fileNames, file.getInputStream(), metadata)
										      .withCannedAcl(CannedAccessControlList.PublicRead));
								 System.out.println(":::::: Completed uploading to Archived Evidence bucket  ");	
//								 String publicURL = service.uploadFile(fileName, file);
				///				 String publicURL = amazonS3Client.getUrl(bucketName, fileNames).toString();
								 String publicURL = amazonS3Client.getUrl(archivedbucketName, fileNames).toString();
																 
				///				 String[] listDocsId = list1.getDocId();
								 System.out.println("listDocsId:::::::: ");
				///				 System.out.println(listDocsId);
				///				 String previousId = null;
								 if (listDocsId != null && listDocsId.length > 0 && previousId == null) {
								   previousId = listDocsId[listDocsId.length - 1];
								 }
							//   String previousId = list1.getDocId(); // list.get last dms id by using index // -1
								 System.out.println("previousId:::::::: ");
								 System.out.println(previousId);
								
							     String newId = generateUniqueId(previousId);
						//	     previousId = newId;
								 System.out.println("newId  for  dms  ");
								 System.out.println(newId);
								     
								 urlList.add(publicURL);	
								 fileNameList.add(fileNames);	
								 
								 typeofEvidenceList.add(eventObj.get("typeOfReference").toString());
								 evidenceDescList.add(eventObj.get("evidence_Description").toString());
								 evidenceForList.add(eventObj.get("evidence_For").toString());
								 dateList.add(strDate);
								 docIdList.add(newId);
								 
								 System.out.println(" AWS S3 url  " +urlList);								 
///								 response.put("evidenceURL", publicURL);
///								 evidenceUrlList.add(publicURL);
								 
								 System.out.println("Evidence File uploaded successfully to AWS S3 " + urlList);
								 System.out.println("asdads" + eventObj.getString("eventType"));
									
//									Query query = new Query();
//									query.addCriteria(
//											new Criteria().andOperator(Criteria.where("Shipment_Id").is(eventObj.get("shipment_Number")),
//													Criteria.where("Partner").is(eventObj.get("partner")),
//													Criteria.where("Event_Name").is(eventObj.get("eventType"))
//													));
//									System.out.println("query here  " + query);
									
									//System.out.println(list1.getEvidence_URL());
									//System.out.println(Arrays.asList(list1.getEvidence_URL()));
									

																		
									// Use a HashSet to remove duplicates
									Set<String> setUrls = new HashSet<>(urlList);
									List<String> listWithoutDuplicateUrls = new ArrayList<>(setUrls);
									// Print the original and new ArrayLists
									System.out.println("Original ArrayList with duplicates: " + urlList);
									System.out.println("ArrayList without duplicates: " + listWithoutDuplicateUrls);
									
									// Use a HashSet to remove duplicates
									Set<String> setFileNames = new HashSet<>(fileNameList);
									List<String> listWithoutDuplicateFilenames = new ArrayList<>(setFileNames);									
									// Print the original and new ArrayLists
									System.out.println("Original ArrayList with duplicates: " + fileNameList);
									System.out.println("ArrayList without duplicates: " + listWithoutDuplicateFilenames);
									
									Set<String> setTypeofEvidence = new HashSet<>(typeofEvidenceList);
									List<String> listWithoutDuplicateTypeofEvidence = new ArrayList<>(setTypeofEvidence);	
									
									Map<String, String> fileMap = new HashMap<>();
									
									for (int i = 0; i < listWithoutDuplicateFilenames.size(); i++) {
										fileMap.put(listWithoutDuplicateFilenames.get(i),
												listWithoutDuplicateUrls.get(i));
									}

									System.out.println("fileMap::::::::::  ");
									System.out.println(fileMap);

									List<String> rearrangedUrls = new ArrayList<>();
									for (String fileNamesss : listWithoutDuplicateFilenames) {
										String url = fileMap.get(fileNamesss);
										rearrangedUrls.add(url);
									}

									System.out.println("rearrangedUrls ::::::::::  ");
									System.out.println(rearrangedUrls);
									
//									Update update = new Update();
//									update.set("Evidence_URL", urlList);
//									update.set("EvidenceList", fileNameList);									
//									update.set("TypeOfReference", typeofEvidenceList);									
//									update.set("Evidence_For", evidenceForList);
//									update.set("Evidence_Description", evidenceDescList);
//									update.set("DocCreatedDate", dateList);
//									update.set("DocId", docIdList);
//									mongoTemplate.upsert(query, update, "ShipmentTransactions");
								//  update.set("Evidence_URL", urlList);
								/// update.set("Evidence_URL", listWithoutDuplicateUrls);
								//  update.set("EvidenceList", listWithoutDuplicateFilenames);
////							    update.set("EvidenceList", eventObj.get("evidencelist"));
								//  update.set("EvidenceList", fileNameList);
							////    update.set("TypeOfReference", listWithoutDuplicateTypeofEvidence);

									//mongoTemplate.updateMulti(query, update, "ShipmentTransactions");
									//mongoTemplate.updateFirst(query, update, "ShipmentTransactions");									
								previousId = newId;		
								 								 										
							} catch (IOException ioe) {
	//							logger.error("IOException: " + ioe.getMessage());
								System.out.println("IOException in AWS S3 Evidence Upload" + ioe.getMessage());
							} catch (AmazonServiceException serviceException) {
		//						logger.info("AmazonServiceException: " + serviceException.getMessage());
								System.out.println("AmazonServiceException in AWS S3 Evidence Upload" +serviceException.getMessage());
								throw serviceException;
							} catch (AmazonClientException clientException) {
			//					logger.info("AmazonClientException Message: " + clientException.getMessage());
								System.out.println("AmazonClientException in AWS S3 Evidence Upload" +clientException.getMessage());
								throw clientException;
							}
						}
																							
						Query query = new Query();
						query.addCriteria(
								new Criteria().andOperator(Criteria.where("Shipment_Id").is(eventObj.get("shipment_Number")),
//										Criteria.where("Partner").is(eventObj.get("partner")),
										Criteria.where("Event_Name").is("Upload Documents")
										//Criteria.where("Event_Name").is(eventObj.get("eventType"))
										));
						System.out.println("query here  " + query);
						
						Update update = new Update();
						update.set("Evidence_URL", urlList);
						update.set("EvidenceList", fileNameList);									
						update.set("TypeOfReference", typeofEvidenceList);									
						update.set("Evidence_For", evidenceForList);
						update.set("Evidence_Description", evidenceDescList);
						update.set("DocCreatedDate", dateList);
						update.set("DocId", docIdList);
						System.out.println(" going to updated mongoTemplate");
						mongoTemplate.updateMulti(query, update, "ShipmentTransactions");
						//mongoTemplate.upsert(query, update, "ShipmentTransactions");
						System.out.println(" :::::::::::::::::::::::::::::::::::::::::::::::::::::::::: ");
						System.out.println(" mongoTemplate updated ");
						System.out.println(" :::::::::::::::::::::::::::::::::::::::::::::::::::::::::: ");
//						 continue;
					}
					
			if(eventObj.get("evidence_For").equals("Invoice")) {
						System.out.println("Inside condition for Invoice to upload Evidence:::::::");		
//						int i = 0;
						if(list1.getEvidence_URL() != null) {
							urlList.addAll(Arrays.asList(list1.getEvidence_URL()));
						}
						if(list1.getEvidenceList() != null) {
							fileNameList.addAll(Arrays.asList(list1.getEvidenceList()));							
						}
						if(list1.getTypeOfReference() != null) {
							typeofEvidenceList.addAll(Arrays.asList(list1.getTypeOfReference()));							
						}
						if(list1.getEvidence_Description() != null) {
							evidenceDescList.addAll(Arrays.asList(list1.getEvidence_Description()));							
						}
						if(list1.getEvidence_For() != null) {
							evidenceForList.addAll(Arrays.asList(list1.getEvidence_For()));
						}	
						if(list1.getDocCreatedDate() != null) {
							dateList.addAll(Arrays.asList(list1.getDocCreatedDate()));						
						}
						if(list1.getDocId() != null) {
							docIdList.addAll(Arrays.asList(list1.getDocId()));							
						}
						
						String[] listDocsId = list1.getDocId();						
						
						for (MultipartFile file : files) {
//							i++;
							System.out.println("for-loop for invoice, i=" + file);							
						    //String fileNames = file.getOriginalFilename();							
							String fileNames = file.getOriginalFilename();
							int lastIndexOfDot = fileNames.lastIndexOf(".");
							System.out.println("lastIndexOfDot");
							System.out.println(lastIndexOfDot);
							if (lastIndexOfDot != -1) {
								 String fileNameWithoutExtension = fileNames.substring(0, lastIndexOfDot);
								 String appendInvoice_No  = eventObj.get("shipment_Num").toString();
								 String appendBatch_id = eventObj.get("batch_Id").toString();
							   //fileNames = fileNameWithoutExtension+"_"+appendInvoice_No+"_"+appendBatch_id+fileNames.substring(lastIndexOfDot);								 
							     System.out.println(" extension of fileName");
								 System.out.println(fileNames.substring(lastIndexOfDot));
								 fileNames = appendInvoice_No+"_"+fileNameWithoutExtension+fileNames.substring(lastIndexOfDot);
							}
							System.out.println("fileNames_without_extension");
							System.out.println(fileNames);

							System.out.println("fileNames of files Uploaded to AWS S3::::in else if  " +fileNames);
					      //fileNameList.add(fileNames);
							System.out.println("fileName list::::::in else if  " +fileNameList);
							
							try {
								 ObjectMetadata metadata = new ObjectMetadata();
								 metadata.setContentLength(file.getSize());
								 System.out.println(" ContentLength  ");	
								 System.out.println(file.getSize());	
					//  The commented lines below returns as File uploaded: with the file name.		
//								amazonS3Client.putObject(bucketName, keyName, file.getInputStream(), metadata);			
//								return "File uploaded: " + keyName;
					//  The lines below returns the url to access the uploaded docs in s3.		
								 amazonS3Client.putObject(
										   new PutObjectRequest(bucketName, fileNames, file.getInputStream(), metadata)
										      .withCannedAcl(CannedAccessControlList.PublicRead));
								 
								 amazonS3Client.putObject(
										   new PutObjectRequest(archivedbucketName, fileNames, file.getInputStream(), metadata)
										      .withCannedAcl(CannedAccessControlList.PublicRead));
								 
								 amazonS3Client.getUrl(bucketName, fileNames).toString();
//								 System.out.println("Uploaded file " + i + ": " + fileNames);
//								 String publicURL = service.uploadFile(fileName, file);
				///				 String publicURL = amazonS3Client.getUrl(bucketName, fileNames).toString();
								 String publicURL = amazonS3Client.getUrl(archivedbucketName, fileNames).toString();
								 
								 System.out.println(" for listdoc :::::::: ");
				///				 String[] listDocsId = list1.getDocId();
				///				 System.out.println(" for listdoc :::::::: "+listDocsId);
				///				 String previousId = null;
								 System.out.println(" above if for previousId:::::::: ");
								 if (listDocsId != null && listDocsId.length > 0 && previousId == null) {
								   System.out.println(" inside if for previousId:::::::: ");
								   previousId = listDocsId[listDocsId.length - 1];
								 }
																 
							//   String previousId = list1.getDocId(); // list.get last dms id by using index // -1
									System.out.println("previousId:::::::: ");
									System.out.println(previousId);
							     String newId = generateUniqueId(previousId);
									System.out.println("newId  for  dms  ");
									System.out.println(newId);
															 
								 							 
								 urlList.add(publicURL);
								 System.out.println(" AWS S3 url list :::::: in else if   " +urlList);
								 fileNameList.add(fileNames);	
								 
								 typeofEvidenceList.add(eventObj.get("typeOfReference").toString());
								 evidenceDescList.add(eventObj.get("evidence_Description").toString());
								 evidenceForList.add(eventObj.get("evidence_For").toString());
								 dateList.add(strDate);
								 docIdList.add(newId);
															 
////							response.put("evidenceURL", publicURL);
////							evidenceUrlList.add(publicURL);
								 System.out.println("evidenceUrlList  " + urlList);
								 System.out.println("Evidence File uploaded successfully to AWS S3 " + urlList);
								 
								 System.out.println("asdads" + eventObj.getString("eventType"));
									
//									Query query = new Query();
//									query.addCriteria(
//											//new Criteria().andOperator(Criteria.where("Shipment_Num").is(eventObj.get("shipment_Num")),
//											  new Criteria().andOperator(Criteria.where("Invoice_Number").is(eventObj.get("shipment_Num")),
//													Criteria.where("Partner").is(eventObj.get("partner")),
//													Criteria.where("Event_Name").is(eventObj.get("eventType"))
//													));
	///								System.out.println("query in else if of evidence upload s3  " + query);
	///								System.out.println(query);
									System.out.println("EvidenceList  " + eventObj.get("evidencelist"));
									System.out.println("EvidenceList as string  " + eventObj.get("evidencelist").toString());
									
//									for(ShipmentTransactions queryt : listforMultipleInvoice) {
//										if(list1.getEvent_Name().equals(eventObj.get("eventType"))) {
//											
//										}
//									}
									
//									if(list1.getEvidence_URL() == null) {
//										System.out.println("Evidence URL is null "+list1.getEvidence_URL());
//									}
//									else {
//									     urlList.addAll(Arrays.asList(list1.getEvidence_URL()));
//									}
//									if(list1.getEvidenceList() == null) {
//										System.out.println("Evidence List is null "+list1.getEvidenceList());
//									}
//									else {
//									     fileNameList.addAll(Arrays.asList(list1.getEvidenceList()));
//									}
																											
									
									// Use a HashSet to remove duplicates
									Set<String> setUrls = new HashSet<>(urlList);
									List<String> listWithoutDuplicateUrls = new ArrayList<>(setUrls);
									// Print the original and new ArrayLists
									System.out.println("Original ArrayList with duplicates: " + urlList);
									System.out.println("ArrayList without duplicates: " + listWithoutDuplicateUrls);
									
									// Use a HashSet to remove duplicates
									Set<String> setFileNames = new HashSet<>(fileNameList);
									List<String> listWithoutDuplicateFilenames = new ArrayList<>(setFileNames);									
									// Print the original and new ArrayLists
									System.out.println("Original ArrayList with duplicates: " + fileNameList);
									System.out.println("ArrayList without duplicates: " + listWithoutDuplicateFilenames);
									
									Set<String> setTypeofEvidence = new HashSet<>(typeofEvidenceList);
									List<String> listWithoutDuplicateTypeofEvidence = new ArrayList<>(setTypeofEvidence);
									
									Map<String, String> fileMap = new HashMap<>();

									for (int i = 0; i < listWithoutDuplicateFilenames.size(); i++) {
										fileMap.put(listWithoutDuplicateFilenames.get(i),
												listWithoutDuplicateUrls.get(i));
									}

									System.out.println("fileMap::::::::::  ");
									System.out.println(fileMap);

									List<String> rearrangedUrls = new ArrayList<>();
									for (String fileNamesss : listWithoutDuplicateFilenames) {
										String url = fileMap.get(fileNamesss);
										rearrangedUrls.add(url);
									}

									System.out.println("rearrangedUrls::::::::::  ");
									System.out.println(rearrangedUrls);
														
									
///									Update update = new Update();
								/// update.set("Evidence_URL", urlList);
/////								update.set("EvidenceList", eventObj.get("evidencelist").toString());
								///	update.set("EvidenceList", fileNameList);		
		////							update.set("Evidence_URL", rearrangedUrls);
		////							update.set("EvidenceList", listWithoutDuplicateFilenames);
		////							update.set("TypeOfReference", eventObj.get("typeOfReference"));
		////							update.set("Evidence_For", eventObj.get("evidence_For"));
		////							update.set("Evidence_Description", eventObj.get("evidence_Description"));																		
//									update.set("Evidence_URL", urlList);
//									update.set("EvidenceList", fileNameList);
//									update.set("TypeOfReference", typeofEvidenceList);
//									update.set("Evidence_For", evidenceForList);
//									update.set("Evidence_Description", evidenceDescList);
//									update.set("DocCreatedDate", dateList);
//									update.set("DocId", docIdList);																
//									mongoTemplate.updateMulti(query, update, "ShipmentTransactions");
									//mongoTemplate.upsert(query, update, "ShipmentTransactions");
//									mongoTemplate.updateFirst(query, update, "ShipmentTransactions");
									
									previousId = newId;
							} 
							catch (IOException ioe) {
				//				logger.error("IOException: " + ioe.getMessage());
								System.out.println("IOException in AWS S3 Evidence Upload" + ioe.getMessage());
							} catch (AmazonServiceException serviceException) {
					//			logger.info("AmazonServiceException: " + serviceException.getMessage());
								System.out.println("AmazonServiceException in AWS S3 Evidence Upload" +serviceException.getMessage());
								throw serviceException;
							} catch (AmazonClientException clientException) {
						//		logger.info("AmazonClientException Message: " + clientException.getMessage());
								System.out.println("AmazonClientException in AWS S3 Evidence Upload" +clientException.getMessage());
								throw clientException;
							}
						}	
						
						Query query = new Query();
						query.addCriteria(
								//new Criteria().andOperator(Criteria.where("Shipment_Num").is(eventObj.get("shipment_Num")),
								  new Criteria().andOperator(Criteria.where("Invoice_Number").is(eventObj.get("shipment_Num")),
//										Criteria.where("Partner").is(eventObj.get("partner")),
										Criteria.where("Event_Name").is("Upload Documents")
									//	Criteria.where("Event_Name").is(eventObj.get("eventType"))
										));
						
						Update update = new Update();
						update.set("Evidence_URL", urlList);
						update.set("EvidenceList", fileNameList);
						update.set("TypeOfReference", typeofEvidenceList);
						update.set("Evidence_For", evidenceForList);
						update.set("Evidence_Description", evidenceDescList);
						update.set("DocCreatedDate", dateList);
						update.set("DocId", docIdList);	
						mongoTemplate.updateMulti(query, update, "ShipmentTransactions");
						//mongoTemplate.upsert(query, update, "ShipmentTransactions");
						
	//					continue;						
					}
//				}
				
			}
		}	
				
//		return "File not uploaded: " + response;	
//		return new response(publicURL, HttpStatus.CREATED);		
//	    return new ResponseEntity<>(service.uploadFile(fileName, file), HttpStatus.OK);
		return urlList;	
	}	
	
//	@Async
	@ResponseBody
	@PostMapping("/uploadEvidencefromMassLoad")
    public List<String> uploadFiles(@RequestParam("fileName") String fileName,
			                        @RequestParam("file") List<MultipartFile> files,
			                        @RequestParam("data") String jsonData
			                              ) throws IOException {
		
		JSONObject eventObj = new JSONObject(jsonData);
		
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = formatter.format(date);
		
		List<String> urlList = new ArrayList<>(); 
		List<String> fileNameList = new ArrayList<>();
		List<String> typeofEvidenceList = new ArrayList<>(); 
		List<String> evidenceDescList = new ArrayList<>();
		List<String> evidenceForList = new ArrayList<>();
		List<String> dateList = new ArrayList<>();
		List<String> docIdList = new ArrayList<>();
		
		List<ShipmentTransactions> listTransac = null;
		
		if(eventObj.getString("invoice_Num").toString() != "") {
			 listTransac = shiptransrepo.findByInvoice_Number(eventObj.getString("invoice_Num")); //shipment_Num is Invoice Number
		}
		if(eventObj.getString("invoice_Num").toString().equals("")) {
			listTransac = shiptransrepo.findByShipment_Num(eventObj.getString("deliveryNumber"));
		}
		
	    String previousId = null;
		for(ShipmentTransactions invoiceTransac : listTransac) {			
			if(invoiceTransac.getEvent_Name().equals("Upload Documents")) {
				if(eventObj.get("evidence_For").equals("Invoice")) {
					System.out.println("Inside condition for Invoice to upload Evidence from MassLoad :::::::");		

					String[] listDocsId = invoiceTransac.getDocId();
					
				 if(invoiceTransac.getEvidence_URL() != null) {
						urlList.addAll(Arrays.asList(invoiceTransac.getEvidence_URL()));
					}
				 if(invoiceTransac.getEvidenceList() != null) {
						fileNameList.addAll(Arrays.asList(invoiceTransac.getEvidenceList()));						
					}
				 if(invoiceTransac.getTypeOfReference() != null) {
						typeofEvidenceList.addAll(Arrays.asList(invoiceTransac.getTypeOfReference()));						
					}
				 if(invoiceTransac.getEvidence_Description() != null) {
						evidenceDescList.addAll(Arrays.asList(invoiceTransac.getEvidence_Description()));
						
					}
				 if(invoiceTransac.getEvidence_For() != null) {
						evidenceForList.addAll(Arrays.asList(invoiceTransac.getEvidence_For()));
					}	
				 if(invoiceTransac.getDocCreatedDate() != null) {
						dateList.addAll(Arrays.asList(invoiceTransac.getDocCreatedDate()));					
					}
				 if(invoiceTransac.getDocId() != null) {
						docIdList.addAll(Arrays.asList(invoiceTransac.getDocId()));						
					}
				 System.out.println(" docIdList");	
				 System.out.println(docIdList);								
					for (MultipartFile file : files) {

						System.out.println("for-loop for invoice, i=" + file);							
					    //String fileNames = file.getOriginalFilename();							
						String fileNames = file.getOriginalFilename();
						int lastIndexOfDot = fileNames.lastIndexOf(".");
						System.out.println("lastIndexOfDot");
						System.out.println(lastIndexOfDot);
						if (lastIndexOfDot != -1) {
							 String fileNameWithoutExtension = fileNames.substring(0, lastIndexOfDot);
							 String appendInvoice_No  = null;
							 if(eventObj.getString("invoice_Num") != "") {
								 appendInvoice_No  = eventObj.get("invoice_Num").toString();
							 }
							 if(eventObj.getString("invoice_Num").toString().equals("")) {
								// appendInvoice_No  = eventObj.get("deliveryNumber").toString();
								 appendInvoice_No = invoiceTransac.getInvoice_Number();
							 }
						   //String appendBatch_id = eventObj.get("batch_Id").toString();
						   //fileNames = fileNameWithoutExtension+"_"+appendInvoice_No+"_"+appendBatch_id+fileNames.substring(lastIndexOfDot);
								System.out.println(" extension of fileName");
								System.out.println(fileNames.substring(lastIndexOfDot));
							 fileNames = appendInvoice_No+"_"+fileNameWithoutExtension+fileNames.substring(lastIndexOfDot);
						}
						System.out.println("fileNames_without_extension");
						System.out.println(fileNames);
					
						System.out.println("fileNames of files Uploaded to AWS S3::::in else if  " +fileNames);
				      //fileNameList.add(fileNames);
						System.out.println("fileName list::::::in else if  " +fileNameList);
						
						try {
							 ObjectMetadata metadata = new ObjectMetadata();
							 metadata.setContentLength(file.getSize());
							 System.out.println(" ContentLength  ");	
							 System.out.println(file.getSize());	
				//  The commented lines below returns as File uploaded: with the file name.		
//							amazonS3Client.putObject(bucketName, keyName, file.getInputStream(), metadata);			
//							return "File uploaded: " + keyName;
				//  The lines below returns the url to access the uploaded docs in s3.		
							 amazonS3Client.putObject(
									   new PutObjectRequest(bucketName, fileNames, file.getInputStream(), metadata)
									      .withCannedAcl(CannedAccessControlList.PublicRead));														 
							 amazonS3Client.getUrl(bucketName, fileNames).toString();
							 
							 amazonS3Client.putObject(
									   new PutObjectRequest(archivedbucketName, fileNames, file.getInputStream(), metadata)
									      .withCannedAcl(CannedAccessControlList.PublicRead));
//							 System.out.println("Uploaded file " + i + ": " + fileNames);
//							 String publicURL = service.uploadFile(fileName, file);
					///		 String publicURL = amazonS3Client.getUrl(bucketName, fileNames).toString();
							 String publicURL = amazonS3Client.getUrl(archivedbucketName, fileNames).toString();
							 
							 System.out.println(" for listdoc :::::::: ");
			///				 String[] listDocsId = invoiceTransac.getDocId();
							 System.out.println(" for listdoc :::::::: "+listDocsId);
	   		///			     String previousId = null;
							 System.out.println(" above if for previousId:::::::: ");
							 if (listDocsId != null && listDocsId.length > 0 && previousId == null) {
							   System.out.println(" inside if for previousId:::::::: ");
							   previousId = listDocsId[listDocsId.length - 1];
							 }
															 
						//   String previousId = list1.getDocId(); // list.get last dms id by using index // -1
						     System.out.println("previousId:::::::: ");
					 	     System.out.println(previousId);
						     String newId = generateUniqueId(previousId);
					      	 System.out.println("newId  for  dms  ");
						     System.out.println(newId);
						     
						     										     						    					     						   							 							 
							 urlList.add(publicURL);
							 System.out.println(" AWS S3 url list :::::: in else if   " +urlList);
							 fileNameList.add(fileNames);	
							 
							 typeofEvidenceList.add(eventObj.get("typeOfReference").toString());
							 evidenceDescList.add(eventObj.get("evidence_Description").toString());
							 evidenceForList.add(eventObj.get("evidence_For").toString());
							 dateList.add(strDate);
							 docIdList.add(newId);
														 
////						 response.put("evidenceURL", publicURL);
////						 evidenceUrlList.add(publicURL);
							 System.out.println("evidenceUrlList  " + urlList);
							 System.out.println("Evidence File uploaded successfully to AWS S3 " + urlList);
							 
							 //System.out.println("asdads" + eventObj.getString("eventType"));
								
//							 Query query = new Query();
//							 query.addCriteria(
//							       new Criteria().andOperator(Criteria.where("Invoice_Number").is(eventObj.get("shipment_Num")),
//												Criteria.where("Event_Name").is("Upload Documents")
//												));
								
			///				 System.out.println("query in else if of evidence upload s3  " + query);
			///				 System.out.println(query);
							 System.out.println("EvidenceList  " + eventObj.get("evidencelist"));
							 System.out.println("EvidenceList as string  " + eventObj.get("evidencelist").toString());
															
																	
								
								// Use a HashSet to remove duplicates
								Set<String> setUrls = new HashSet<>(urlList);
								List<String> listWithoutDuplicateUrls = new ArrayList<>(setUrls);
								// Print the original and new ArrayLists
								System.out.println("Original ArrayList with duplicates: " + urlList);
								System.out.println("ArrayList without duplicates: " + listWithoutDuplicateUrls);
								
								// Use a HashSet to remove duplicates
								Set<String> setFileNames = new HashSet<>(fileNameList);
								List<String> listWithoutDuplicateFilenames = new ArrayList<>(setFileNames);									
								// Print the original and new ArrayLists
								System.out.println("Original ArrayList with duplicates: " + fileNameList);
								System.out.println("ArrayList without duplicates: " + listWithoutDuplicateFilenames);
								
								Set<String> setTypeofEvidence = new HashSet<>(typeofEvidenceList);
								List<String> listWithoutDuplicateTypeofEvidence = new ArrayList<>(setTypeofEvidence);
								
								Map<String, String> fileMap = new HashMap<>();

								for (int i = 0; i < listWithoutDuplicateFilenames.size(); i++) {
									fileMap.put(listWithoutDuplicateFilenames.get(i),
											listWithoutDuplicateUrls.get(i));
								}

								System.out.println("fileMap::::::::::  ");
								System.out.println(fileMap);

								List<String> rearrangedUrls = new ArrayList<>();
								for (String fileNamesss : listWithoutDuplicateFilenames) {
									String url = fileMap.get(fileNamesss);
									rearrangedUrls.add(url);
								}

								System.out.println("rearrangedUrls::::::::::  ");
								System.out.println(rearrangedUrls);
																					
//								Update update = new Update();																		
//								update.set("Evidence_URL", urlList);
//								update.set("EvidenceList", fileNameList);
//								update.set("TypeOfReference", typeofEvidenceList);
//								update.set("Evidence_For", evidenceForList);
//								update.set("Evidence_Description", evidenceDescList);
//								update.set("DocCreatedDate", dateList);
//								update.set("DocId", docIdList);													
//								mongoTemplate.updateMulti(query, update, "ShipmentTransactions");
							  //mongoTemplate.upsert(query, update, "ShipmentTransactions");
							  //mongoTemplate.updateFirst(query, update, "ShipmentTransactions");	
								previousId = newId;
						} 
						catch (IOException ioe) {
			//				logger.error("IOException: " + ioe.getMessage());
							System.out.println("IOException in AWS S3 Evidence Upload ");
							ioe.getMessage();
							ioe.printStackTrace();
						} catch (AmazonServiceException serviceException) {
				//			logger.info("AmazonServiceException: " + serviceException.getMessage());
							System.out.println("AmazonServiceException in AWS S3 Evidence Upload ");
							throw serviceException;
						} catch (AmazonClientException clientException) {
					//		logger.info("AmazonClientException Message: " + clientException.getMessage());
							System.out.println("AmazonClientException in AWS S3 Evidence Upload ");
							throw clientException;
						}
						
					}	
					 System.out.println("::: Started querying :::");	
					 Query query = null;
					 if(eventObj.getString("invoice_Num") != "") {	
						 query = new Query();
						 query.addCriteria(
							       new Criteria().andOperator(Criteria.where("Invoice_Number").is(eventObj.get("invoice_Num")),
												Criteria.where("Event_Name").is("Upload Documents")
												)); 
					 }
					 if(eventObj.getString("invoice_Num").toString().equals("")) {
						 query = new Query();
						 query.addCriteria(
							       new Criteria().andOperator(Criteria.where("Shipment_Num").is(eventObj.get("deliveryNumber")),
												Criteria.where("Event_Name").is("Upload Documents")
												)); 
					 }

					
					Update update = new Update();																		
					update.set("Evidence_URL", urlList);
					update.set("EvidenceList", fileNameList);
					update.set("TypeOfReference", typeofEvidenceList);
					update.set("Evidence_For", evidenceForList);
					update.set("Evidence_Description", evidenceDescList);
					update.set("DocCreatedDate", dateList);
					update.set("DocId", docIdList);
					mongoTemplate.updateMulti(query, update, "ShipmentTransactions");
					
					break;
				}
				
			}
		}
			
		return urlList;		
}
	
	
//	@ResponseBody
////	@PostMapping("/deleteEvidencefromS3/{filename}")
//	@PostMapping("/deleteEvidencefromS3")
////	public ResponseEntity<String> deleteFile(@PathVariable("filename") String filename) throws S3Exception {
//	public ResponseEntity<String> deleteFile(@RequestParam("fileName") List<String> filename,
//			                                 @RequestParam("shipmentNum") String shipmentNum,
//			                                 @RequestParam("evidenceUrl") String evidenceUrl)throws S3Exception {	
//		System.out.println("  in deleteEvidencefromS3 api and got filename as   " +filename);
//		
/////		for(ShipmentTransactions iterateTransac : transactionsList) {
//		List<ShipmentTransactions> transactionsList = shiptransrepo.findByShipment_Num(shipmentNum);
//		for(int i = 0; i < transactionsList.size(); i++) {
//		try {
////			List<ShipmentTransactions> transactionsList = shiptransrepo.findByShipment_Num(shipmentNum);
//			System.out.println("  ShipmentTransactions ");
//			System.out.println(transactionsList.size());
//		// Delete the file from S3
//			DeleteObjectsRequest deleteRequest = new DeleteObjectsRequest(bucketName)
//                    .withKeys(filename.toArray(new String[0]));
//			amazonS3Client.deleteObjects(deleteRequest);
//			
//		//amazonS3Client.deleteObject(bucketName, filename);
//		System.out.println("deleted object in S3 and got filename as   " +filename);
//				
///////////		for(ShipmentTransactions iterateTransac : transactionsList) {
//	//	for(int i = 0; i < transactionsList.size(); i++) {
//				
////			}
//	    Query queryTransac = new Query();
//	    queryTransac.addCriteria(
//				new Criteria().andOperator(Criteria.where("Shipment_Num").is(shipmentNum),
//						Criteria.where("Evidence_URL").is(evidenceUrl)
//						));		
//	    // Find the document
//	    System.out.println(" queryTransac   " +queryTransac);
//	    ShipmentTransactions document = mongoTemplate.findOne(queryTransac, ShipmentTransactions.class);
//////	    ShipmentTransactions document = mongoTemplate.find(queryTransac, ShipmentTransactions.class);
//	  //  mongoTemplate.find(queryTransac, ShipmentTransactions.class);
//	    System.out.println(" document   " +document);
//	    // Modify the array value
//	    //document.getArrayField()[index] = null;
//	    //document.getEvidence_URL()[0] = null;
////	    List<Object> arrayField = document.getEvidence_URL();
//
////	    String[] arrayField = document.getEvidence_URL();
//	    List<String> arrayField = Arrays.asList(document.getEvidence_URL());
//	    System.out.println("arrayField   " +arrayField);
//	    int index = arrayField.indexOf(evidenceUrl);
//	    System.out.println("index   " +index);
//	    if (index != -1) {
//	        //arrayField.set(index, null);
//	        arrayField.remove(index);
//	    }
//	    
//	    // Save the changes back to MongoDB
//	    mongoTemplate.save(document);
//	   	    		
//	}
//	//	return ResponseEntity.ok().body("Deleted File "+filename+" successfully");	
//		catch (Exception e) {
////			//logger.error("IOException: " + e.getMessage());
//////			return ResponseEntity.status(HttpStatus.FORBIDDEN)
//////		             .body("File not Deleted: "+filename);
//			System.out.println(" File got Deleted but loop continued to iterate so it returned a null document ");
//			System.out.println(" File not Deleted: "+filename + "Exception is: "+e);
//		}
//		
//	}
////	    return ResponseEntity.ok().body("File not Deleted: "+filename);		
////		return ResponseEntity.status(HttpStatus.FORBIDDEN)
////				             .body("File not Deleted: "+filename);
////      return new ResponseEntity<>(service.deleteFile(filename), HttpStatus.OK);
//	return ResponseEntity.ok().body("Deleted File "+filename+" successfully");
//}
//	
	
	
	@ResponseBody
	@PostMapping("/deleteEvidencefromS3")
//	@DeleteMapping("/deleteEvidencefromS3")
	public ResponseEntity<String> deleteFile(@RequestParam("fileName") String[] filename,
			 								 @RequestParam("docId") String[] docid,
			                                 @RequestParam("shipmentNum") String shipmentNum,
			                                 @RequestParam("invoiceNumber") String invoiceNumber
			                                )throws S3Exception {	
//	public ResponseEntity<String> deleteFile(@RequestBody ShipmentTransactions transac 	 						
//           )throws S3Exception {	
		System.out.println("  in deleteEvidencefromS3 api and the filename is   " +filename);
					
	  try {					
		   for (String fileName : filename) {
		    	amazonS3Client.deleteObject(new DeleteObjectRequest(bucketName, fileName));	  
		    }	
		   for (String archivefilename : filename) {
		    	amazonS3Client.deleteObject(new DeleteObjectRequest(archivedbucketName, archivefilename));		    	
		    }
		   System.out.println(" File Deleted successfully ");	   		   
	   }
	  catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.ok().body("Failed to Deleted File from AWS S3"+filename+" ");
	  }
	     
	  try {
		    Query query = new Query();
	        query.addCriteria(Criteria.where("DocId").in(docid).and("Shipment_Num").is(shipmentNum));
	        
		    Query queryTransac = new Query();
		    queryTransac.addCriteria(
					new Criteria().andOperator(Criteria.where("DocId").in(docid),
					    Criteria.where("Invoice_Number").is(invoiceNumber),
					    Criteria.where("EvidenceList").in(filename)
							));	
		 		 
//		    Query query = new Query();
//			query.addCriteria(
//					new Criteria().andOperator(Criteria.where("Shipment_Num").is(shipmentNum),
//					    Criteria.where("DocId").is(docid)
//							));	
	        List<ShipmentTransactions> transacDocuments = mongoTemplate.find(query, ShipmentTransactions.class);		 
	        for (ShipmentTransactions document : transacDocuments) {
	        	
	            List<String> docIds = new ArrayList<String>(Arrays.asList(document.getDocId()));	            		
	            List<String> docCreatedDates = new ArrayList<String>(Arrays.asList(document.getDocCreatedDate()));
	            List<String> evidenceUrls = new ArrayList<String>(Arrays.asList(document.getEvidence_URL()));
	            List<String> evidenceList = new ArrayList<String>(Arrays.asList(document.getEvidenceList()));
	            List<String> evidenceDesc = new ArrayList<String>(Arrays.asList(document.getEvidence_Description()));
	            List<String> typeOfEvidence = new ArrayList<String>(Arrays.asList(document.getTypeOfReference()));
	            List<String> evidenceFor = new ArrayList<String>(Arrays.asList(document.getEvidence_For()));
	      	            
	            // ... get other string arrays
	            for (String docId : docid) {
	                int index = docIds.indexOf(docId);
	                if (index != -1) {	                	
	                	docIds.remove(index);
	                	docCreatedDates.remove(index);
	                	evidenceUrls.remove(index);
	                	evidenceList.remove(index);
	                	evidenceDesc.remove(index);
	                	typeOfEvidence.remove(index);
	                	evidenceFor.remove(index);	                	     	                	
	                    // ... remove from other string arrays
	                }
	            }
	            document.setDocId(docIds.toArray(new String[0]));
	            document.setDocCreatedDate(docCreatedDates.toArray(new String[0]));
	            document.setEvidence_URL(evidenceUrls.toArray(new String[0]));
	            document.setEvidenceList(evidenceList.toArray(new String[0]));
	            document.setEvidence_Description(evidenceDesc.toArray(new String[0]));
	            document.setTypeOfReference(typeOfEvidence.toArray(new String[0]));
	            document.setEvidence_For(evidenceFor.toArray(new String[0]));	    	            
	            System.out.println("  Document after deleting index elements   " );
	            System.out.println(document);

	            mongoTemplate.save(document);
	      }
	        System.out.println(" Elements deleted successfully " );	       	        	        		 		 		        		        		    		       		 			 			 		      	    	   	    		
	}
    catch (Exception e) {
    	    System.out.println(" Elements not updated in the List ");
    	    e.printStackTrace();
			return ResponseEntity.ok().body("Failed to update the list");
	 }		
	return ResponseEntity.ok().body("Deleted File "+filename.toString()+" successfully");
}
	
		
	
//	@ResponseBody
//	@PostMapping("/deleteEvidencefromS3")
//	public ResponseEntity<String> deleteFile(@RequestParam("fileName") String[] filename,
//			  							     @RequestParam("docId") String[] docid,
//			                                 @RequestParam("shipmentNum") String shipmentNum,
//			                                 @RequestParam("typeOfEvidence") String[] typeOfEvidence,	                       
//	                                         @RequestParam("evidenceFor") String[] evidenceFor,
//											 @RequestParam("docsCreatedDate") String[] docsCreatedDate,
//			                                 @RequestParam("evidenceDescription") String[] evidenceDescription)throws S3Exception
//			                                 //@RequestParam("evidenceUrl") String evidenceUrl)throws S3Exception
//{	
//		System.out.println("  in deleteEvidencefromS3 api and got filename as   " +filename);
//		
/////		for(ShipmentTransactions iterateTransac : transactionsList) {
//	    List<String> deletedURLs = new ArrayList<>();
//	    List<String> deletedEvidenceList = new ArrayList<>();
//	    List<String> deletedDocIdsList = new ArrayList<>();
//	    List<String> deletedDateList = new ArrayList<>();
//	    List<String> deletedEvidenceTypeList = new ArrayList<>();
//	    List<String> deletedEvidenceDescList = new ArrayList<>();
//	    List<String> deletedEvidenceForList = new ArrayList<>();
//	    
//	    List<String> remainingEvidenceURLs = new ArrayList<>();
//	    List<String> remainingEvidenceList = new ArrayList<>();
//	    List<String> remainingDocIdList = new ArrayList<>();
//	    List<String> remainingDateList = new ArrayList<>();
//	    List<String> remainingEvidenceType = new ArrayList<>();
//	    List<String> remainingEvidenceDesc = new ArrayList<>();	   
//	    List<String> remainingEvidenceFor = new ArrayList<>();	    
//				
//		List<ShipmentTransactions> transactionsList = shiptransrepo.findByShipment_Num(shipmentNum);
//				
//	    // Delete files from S3 bucket and get the deleted URLs
//	    for (String fileName : filename) {
//	    	amazonS3Client.deleteObject(new DeleteObjectRequest(bucketName, fileName));
//	        deletedURLs.add(amazonS3Client.getUrl(bucketName, fileName).toString());
//	        deletedEvidenceList.add(fileName);	        
//	        //deletedFilenamesList.addAll(Arrays.asList(filename));
//	        System.out.println("   deletedURLs  ");
//	        System.out.println(deletedURLs);
//	        System.out.println("  in deletedFilenamesList   " );
//	        System.out.println(deletedEvidenceList);
//	    }
//	    for(String docsid : docid) {
//	    	deletedDocIdsList.add(docsid); 
//	    	 
//	    }
//	    for(String docsDate : docsCreatedDate) {
//	    	deletedDateList.add(docsDate); 	    	
//	    }
//	    for(String evidenceType : typeOfEvidence) {
//	    	deletedEvidenceTypeList.add(evidenceType);
//	    	
//	        System.out.println("  in evidenceType   " );
//	        System.out.println(deletedEvidenceTypeList);
//	    }
//	    for(String evidenceDesc : evidenceDescription) {
//	    	 deletedEvidenceDescList.add(evidenceDesc); 
//	    }
//	    for(String evidencefor : evidenceFor) {
//	    	deletedEvidenceForList.add(evidencefor); 
//	    }
//	    
//	    // Update MongoDB to remove the deleted URLs from EvidenceURL array
//	    Query query = new Query();
//	    //(Criteria.where("Evidence_URL").in(deletedURLs));
//	    query.addCriteria(
//				new Criteria().andOperator(Criteria.where("Shipment_Num").is(shipmentNum),
//						Criteria.where("Evidence_URL").in(deletedURLs),
//						Criteria.where("EvidenceList").in(deletedEvidenceList),
//						Criteria.where("TypeOfReference").in(deletedEvidenceTypeList),
//						Criteria.where("Evidence_Description").in(deletedEvidenceDescList),						
//						Criteria.where("Evidence_For").in(deletedEvidenceForList),
//						Criteria.where("DocId").in(deletedDocIdsList),
//						Criteria.where("DocCreatedDate").in(deletedDateList)						
//						));
//	    
//	    List<ShipmentTransactions> docsToUpdate = mongoTemplate.find(query, ShipmentTransactions.class);
//	    
//	    for (ShipmentTransactions doc : docsToUpdate) {
//	    	
//	    	String[] evidenceURLs = doc.getEvidence_URL();
//	        String[] evidenceList = doc.getEvidenceList();	        
//	        String[] docidss = doc.getEvidence_URL();
//	        if (docidss == null) {
//              continue;
//         }
//	        String[] docdate = doc.getEvidence_URL();	       
//	        String[] evidenceDesc = doc.getEvidence_Description();
//	        String[] evidenceType = doc.getTypeOfReference();
//	        String[] evidencefor = doc.getEvidence_URL();
//	        
//	        for (int i = 0; i < evidenceURLs.length; i++) {
//	            if (!deletedURLs.contains(evidenceURLs[i])) {
//	            	
//	                remainingEvidenceList.add(evidenceList[i]);
//	                remainingEvidenceURLs.add(evidenceURLs[i]);
//	                remainingDocIdList.add(docidss[i]);
//	                remainingDateList.add(docdate[i]);
//	                remainingEvidenceType.add(evidenceType[i]);
//	                remainingEvidenceDesc.add(evidenceDesc[i]);	               
//	                remainingEvidenceFor.add(evidencefor[i]);
//	            }
//	        }	 
//	        
//	        System.out.println("  in remainingEvidenceList   " );
//	        System.out.println(remainingEvidenceList.toArray(new String[0]));
//	        
//	        System.out.println("  in remainingEvidenceURLs   " );
//	        System.out.println(remainingEvidenceURLs.toArray(new String[0]));
//	        
//	        doc.setEvidenceList(remainingEvidenceList.toArray(new String[0]));
//	        doc.setEvidence_URL(remainingEvidenceURLs.toArray(new String[0]));
//	        doc.setEvidence_Description(remainingEvidenceDesc.toArray(new String[0]));
//	        doc.setTypeOfReference(remainingEvidenceType.toArray(new String[0]));	        
//	        doc.setEvidence_For(remainingEvidenceFor.toArray(new String[0]));
//	        doc.setDocId(remainingDocIdList.toArray(new String[0]));
//	        doc.setDocCreatedDate(remainingDateList.toArray(new String[0]));
//	        
//	        System.out.println("  doc to template   " );
//	        System.out.println(doc);
//	        
//	        mongoTemplate.save(doc);
//	    
//   } 
//	    return ResponseEntity.ok().body("Deleted File "+filename+" successfully");
// }
//	
	
//	@ResponseBody
//	@PostMapping("/deleteEvidencefromS3")
//	public ResponseEntity<String> deleteFile(@RequestParam("fileName") String[] filename,
//											 @RequestParam("docId") String[] docid,
//			                                 @RequestParam("shipmentNum") String shipmentNum,
//			                                 @RequestParam("typeOfEvidence") String[] typeOfEvidence,
//			                                 @RequestParam("evidenceDescription") String[] evidenceDescription)throws S3Exception
//			                                 //@RequestParam("evidenceUrl") String evidenceUrl)throws S3Exception
//{	
//		System.out.println("  in deleteEvidencefromS3 api and got filename as   " +filename);		
////		List<String> list = Arrays.asList(docid);
//		
//////		List<ShipmentTransactions> queryDocument = shiptransrepo.findByDocsId(docid.toString());
//		
//		// Delete files from S3 bucket and get the deleted URLs
//	    for (String fileName : filename) {
//	    	amazonS3Client.deleteObject(new DeleteObjectRequest(bucketName, fileName));	     
//	    }
//	    
////	    List<ShipmentTransactions> queryDocumentss = null;
////	    for (String docidss : docid) {
////	    	 queryDocumentss = shiptransrepo.findByDocsId(docidss);
////	     
////	    }
////	    System.out.println("  queryDocumentss::::::::::::   " );	
////	    System.out.println(queryDocumentss);	
//		
////		List<ShipmentTransactions> queryDocument = mongoTemplate.findByDocsId(docid, ShipmentTransactions.class);
//				
////	    Query query = new Query();
////	    query.addCriteria(
////				new Criteria().andOperator(Criteria.where("DocId").is(docid),
////						Criteria.where("Shipment_Num").is(shipmentNum)
////						));
//	    System.out.println("  test  " );
//	    List<ShipmentTransactions> queryDocumentss = null;
//	    for (String docidss : docid) {
//	    	  System.out.println("  test inside loop  " );
//		    Query query = new Query();
//		    query.addCriteria(
//				//	new Criteria().andOperator(Criteria.where("DocId").is(docidss),
//		    		new Criteria().andOperator(Criteria.where("Shipment_Num").is(shipmentNum)
//							//Criteria.where("Shipment_Num").is(shipmentNum)
//							));
//	    	 queryDocumentss = mongoTemplate.find(query, ShipmentTransactions.class);
//	     
//	    }
//	    System.out.println("  queryDocumentss::::::::::::   " );	
//	    System.out.println(queryDocumentss);
//	    
//	    
/////	    List<ShipmentTransactions> docsToUpdate = mongoTemplate.find(query, ShipmentTransactions.class);				
//		
//	    for (ShipmentTransactions document : queryDocumentss) {
//       // if (!queryDocumentss.isEmpty()) {
//        //	ShipmentTransactions document = queryDocumentss.get(0);
//	    	
//        	
//    	    System.out.println("  document  ::::::::::::   " );	
//    	    System.out.println(document);
//        	
//            String[] docIds = document.getDocId();
//            // Skip iteration if docsId is null
//            if (docIds == null) {
//                continue;
//            }
//            
//            String[] date = document.getDocCreatedDate();
//            String[] evidenceType = document.getTypeOfReference();
//            String[] evidenceFor = document.getEvidence_For();
//            String[] description = document.getEvidence_Description();
//            String[] urls = document.getEvidence_URL();
//            String[] fileNames = document.getEvidenceList();
//
//            List<String> updatedDocsId = new ArrayList<>();
//            List<String> updatedDate = new ArrayList<>();
//            List<String> updatedEvidenceType = new ArrayList<>();
//            List<String> updatedEvidenceFor = new ArrayList<>();
//            List<String> updatedDescription = new ArrayList<>();
//            List<String> updatedUrls = new ArrayList<>();
//            List<String> updatedFileNames = new ArrayList<>();
//
//            for (int i = 0; i < docIds.length; i++) {
//                if (!docIds[i].equals(docid.toString())) {
//                	updatedDocsId.add(docIds[i]);
//                	updatedDate.add(date[i]);
//                	updatedEvidenceType.add(evidenceType[i]);
//                	updatedEvidenceFor.add(evidenceFor[i]);
//                	updatedDescription.add(description[i]);                 
//                    updatedUrls.add(urls[i]);
//                    updatedFileNames.add(fileNames[i]);
//                }
//            }
//            
//            document.setDocId(updatedDocsId.toArray(new String[0]));
//            document.setDocCreatedDate(updatedDate.toArray(new String[0]));
//            document.setTypeOfReference(updatedEvidenceType.toArray(new String[0]));
//            document.setEvidence_For(updatedEvidenceFor.toArray(new String[0]));
//            document.setEvidence_Description(updatedDescription.toArray(new String[0]));
//            document.setEvidence_URL(updatedUrls.toArray(new String[0]));
//            document.setEvidenceList(updatedFileNames.toArray(new String[0]));
//            
//            System.out.println("  document to save in db  ::::::::::::   " );	
//    	    System.out.println(document);
//
//    	    mongoTemplate.save(document);
//        }
//    
//	    return ResponseEntity.ok().body("Deleted File "+filename+" successfully");
// }
			    
		
//		for(int i = 0; i < transactionsList.size(); i++) {
//		try {
////			List<ShipmentTransactions> transactionsList = shiptransrepo.findByShipment_Num(shipmentNum);
//			System.out.println("  ShipmentTransactions ");
//			System.out.println(transactionsList.size());
//		// Delete the file from S3
//			DeleteObjectsRequest deleteRequest = new DeleteObjectsRequest(bucketName)
//                    .withKeys(filename.toArray(new String[0]));
//			amazonS3Client.deleteObjects(deleteRequest);
//			
//		//amazonS3Client.deleteObject(bucketName, filename);
//		System.out.println("deleted object in S3 and got filename as   " +filename);
//				
///////////		for(ShipmentTransactions iterateTransac : transactionsList) {
//	//	for(int i = 0; i < transactionsList.size(); i++) {
//				
////			}
//	    Query queryTransac = new Query();
//	    queryTransac.addCriteria(
//				new Criteria().andOperator(Criteria.where("Shipment_Num").is(shipmentNum),
//						Criteria.where("Evidence_URL").is(evidenceUrl)
//						));		
//	    // Find the document
//	    System.out.println(" queryTransac   " +queryTransac);
//	    ShipmentTransactions document = mongoTemplate.findOne(queryTransac, ShipmentTransactions.class);
//////	    ShipmentTransactions document = mongoTemplate.find(queryTransac, ShipmentTransactions.class);
//	  //  mongoTemplate.find(queryTransac, ShipmentTransactions.class);
//	    System.out.println(" document   " +document);
//	    // Modify the array value
//	    //document.getArrayField()[index] = null;
//	    //document.getEvidence_URL()[0] = null;
////	    List<Object> arrayField = document.getEvidence_URL();
//
////	    String[] arrayField = document.getEvidence_URL();
//	    List<String> arrayField = Arrays.asList(document.getEvidence_URL());
//	    System.out.println("arrayField   " +arrayField);
//	    int index = arrayField.indexOf(evidenceUrl);
//	    System.out.println("index   " +index);
//	    if (index != -1) {
//	        arrayField.set(index, null);
//	    }
//	    
//	    // Save the changes back to MongoDB
//	    mongoTemplate.save(document);
//	   	    		
//	}
//	//	return ResponseEntity.ok().body("Deleted File "+filename+" successfully");	
//		catch (Exception e) {
////			//logger.error("IOException: " + e.getMessage());
//////			return ResponseEntity.status(HttpStatus.FORBIDDEN)
//////		             .body("File not Deleted: "+filename);
//			System.out.println(" File got Deleted but loop continued to iterate so it returned a null document ");
//			System.out.println(" File not Deleted: "+filename + "Exception is: "+e);
//		}
//		
//	}
////	    return ResponseEntity.ok().body("File not Deleted: "+filename);		
////		return ResponseEntity.status(HttpStatus.FORBIDDEN)
////				             .body("File not Deleted: "+filename);
////      return new ResponseEntity<>(service.deleteFile(filename), HttpStatus.OK);
//	return ResponseEntity.ok().body("Deleted File "+filename+" successfully");
//}
//	
	
	
	
	////
	// @ResponseBody
	/*
	 * @GetMapping("/saveExcel") public static void main(String[] args) throws
	 * FileNotFoundException, IOException { XSSFWorkbook workbook = new
	 * XSSFWorkbook(); XSSFSheet sheet = workbook.createSheet("Java Books");
	 * System.out.println("fff");
	 * 
	 * Object[][] bookData = { {"Head First Java", "Kathy Serria", 79},
	 * {"Effective Java", "Joshua Bloch", 36}, {"Clean Code", "Robert martin", 42},
	 * {"Thinking in Java", "Bruce Eckel", 35}, };
	 * 
	 * List<String> currencyRates = new ArrayList<>();
	 * //[{"Delivery No.":"38554880","Device Id":"046046044234","Event Name"
	 * :"Unload From Truck","BP Id":"BP0002","Internal Temp."
	 * :"32.120F","Event Status":"Completed","Date":"5/14/2018, 6:51:55 PM"
	 * ,"Mode Of Transport":"Truck"},{"Delivery No.":"38554880","Device Id"
	 * :"046046044234","Event Name":"Inspection","BP Id":"BP0002","Internal Temp."
	 * :"32.120F","Event Status":"Completed","Date":"5/14/2018, 6:51:55 PM"
	 * ,"Mode Of Transport":"Truck"},{"Delivery No.":"38554880","Device Id"
	 * :"046046044234","Event Name":"Inspection","BP Id":"BP0001","Internal Temp."
	 * :"32.120F","Event Status":"Completed","Date":"6/11/2019, 8:59:04 PM"
	 * ,"Mode Of Transport":"Air"},{"Delivery No.":"38554880","Device Id"
	 * :"046046044234","Event Name":"Loading","BP Id":"BP0001","Internal Temp."
	 * :"32.120F","Event Status":"QUEUED","Date":"6/11/2019, 9:09:42 PM"
	 * ,"Mode Of Transport":"Air"},{"Delivery No.":"38554880","Device Id"
	 * :"046046044234","Event Name":"Unload From Truck","BP Id"
	 * :"BP0001","Internal Temp.":"32.120F","Event Status":"INITIALIZED",
	 * "Date":"6/11/2019, 9:10:16 PM","Mode Of Transport":"Air"},{"Delivery No."
	 * :"38554880","Device Id":"046046044234","Event Name":"Unload From Truck"
	 * ,"BP Id":"BP0001","Internal Temp.":"32.120F","Event Status":"INITIALIZED",
	 * "Date":"6/11/2019, 9:25:10 PM","Mode Of Transport":"Road"},{"Delivery No."
	 * :"38554880","Device Id":"046046044234","Event Name":"Loading","BP Id"
	 * :"BP0001","Internal Temp.":"32.120F","Event Status":"QUEUED",
	 * "Date":"5/14/2018, 6:51:55 PM","Mode Of Transport":"Truck"},{"Delivery No."
	 * :"38774880","Device Id":"046046044234","Event Name":"Loading to flight"
	 * ,"BP Id":"BP0002","Internal Temp.":"32.120F","Event Status":"Completed",
	 * "Date":"1/24/2018, 6:51:55 PM","Mode Of Transport":"Air"},{"Delivery No."
	 * :"38774880","Device Id":"046046044234","Event Name":"Final Receipt","BP Id"
	 * :"BP0002","Internal Temp.":"32.120F","Event Status":"Queued",
	 * "Date":"1/24/2018, 6:51:55 PM","Mode Of Transport":"Road"},{"Delivery No."
	 * :"39775881","Device Id":"046046044234","Event Name":"Initial Inspection"
	 * ,"BP Id":"BP0001","Internal Temp.":"32.120F","Event Status":"Completed",
	 * "Date":"5/14/2019, 6:51:55 PM","Mode Of Transport":"Truck"}] Object[][]
	 * bookData = { {"Head First Java", "Kathy Serria", 79}, {"Effective Java",
	 * "Joshua Bloch", 36}, {"Clean Code", "Robert martin", 42},
	 * {"Thinking in Java", "Bruce Eckel", 35}, }; int rowCount = 0;
	 * 
	 * for (Object[] aBook : bookData) { Row row = sheet.createRow(++rowCount);
	 * 
	 * int columnCount = 0;
	 * 
	 * for (Object field : aBook) { Cell cell = row.createCell(++columnCount); if
	 * (field instanceof String) { cell.setCellValue((String) field); } else if
	 * (field instanceof Integer) { cell.setCellValue((Integer) field); } }
	 * 
	 * }
	 * 
	 * 
	 * try (FileOutputStream outputStream = new
	 * FileOutputStream("C:/Users/EXA5/Desktop/JavaBooks.xlsx")) {
	 * 
	 * workbook.write(outputStream); System.out.println("ffsdf"); }
	 * 
	 * // TODO Auto-generated method stub
	 * 
	 * }
	 */

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@PostMapping("/MailSender")	
	public boolean MailSender(@RequestBody Mailinfo mailinfo) {

		boolean flag = false;

		// List<Sendmail> sendmaillist = new ArrayList<>();
		System.out.println("to "+mailinfo.getTo());
		System.out.println("subject "+mailinfo.getSubject());
		System.out.println("content "+mailinfo.getContent());

		try {

			System.out.println("MAil sending vlaues");
			Sendmail sm = new Sendmail();
			// sm.setShipment_id(mailinfo.getShipment_id());
			sm.setTo(mailinfo.getTo());
			sm.setContent(mailinfo.getContent());
			sm.setSubject(mailinfo.getSubject());

			senderService.sendSimpleMail(sm.getTo(), sm.getSubject(), sm.getContent());

			flag = true;

			return flag;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;

	}

	/*
	 * @ResponseBody
	 * 
	 * @PostMapping("/deleteGood/{Customer_Id}/{Goods_Id}") public boolean
	 * deleteDraft(@PathVariable(value = "Customer_Id") String Customer_Id,
	 * 
	 * @PathVariable(value = "Goods_Id") String Goods_Id) {
	 * System.out.println("came in delete" + Customer_Id); boolean flag = false; try
	 * {
	 * 
	 * Query query = new Query();
	 * query.addCriteria(Criteria.where("Customer_Id").is(Customer_Id)
	 * .and("Goods").elemMatch(Criteria.where("Goods.Goods_Id").is(Goods_Id)));
	 * System.out.println("middle"); Update update = new Update();
	 * update.pull("Goods", new
	 * Query().addCriteria(Criteria.where("Goods.Goods_Id").is(Goods_Id)));
	 * FindAndModifyOptions options = FindAndModifyOptions.options();
	 * options.returnNew(true); System.out.println("last");
	 * System.out.println("query "+query); System.out.println("update "+update);
	 * mongoTemplate.findAndModify(query, update, options, Customer.class);
	 * 
	 * Query removeQuery = Query.query(new
	 * Criteria().andOperator(Criteria.where("Customer_Id").is(Customer_Id),
	 * Criteria.where("Goods.Goods_Id").in(Goods_Id)));
	 * 
	 * UpdateResult wc = mongoTemplate.upsert(removeQuery, new
	 * Update().pull("Goods", new Document("Goods_Id", Goods_Id)), Customer.class);
	 * // Query removeQuery = //
	 * Query.query(Criteria.where("Goods.Goods_Id").in(Goods_Id));
	 * 
	 * // mongoTemplate.remove(customer); System.out.println("removed");
	 * 
	 * flag = true; return flag; } catch (Exception e) { // TODO: handle exception
	 * System.out.println("in catch"); } return flag; }
	 */

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/deleteRoute/{Customer_Id}/{Route_Id}")
	public boolean deleteroute(@PathVariable(value = "Customer_Id") String Customer_Id,
			@PathVariable(value = "Route_Id") String Route_Id) {
		System.out.println("came in delete" + Customer_Id);
		boolean flag = false;
		try {
			/*
			 * Query query = new Query();
			 * query.addCriteria(Criteria.where("Customer_Id").is(Customer_Id)
			 * .and("Goods").elemMatch(Criteria.where("Goods.Goods_Id").is(Goods_Id)));
			 * System.out.println("middle"); Update update = new Update();
			 * update.pull("Goods", new
			 * Query().addCriteria(Criteria.where("Goods.Goods_Id").is(Goods_Id)));
			 * FindAndModifyOptions options = FindAndModifyOptions.options();
			 * options.returnNew(true); System.out.println("last");
			 * System.out.println("query "+query); System.out.println("update "+update);
			 * mongoTemplate.findAndModify(query, update, options, Customer.class);
			 */
			Query removeQuery = Query.query(new Criteria().andOperator(Criteria.where("Customer_Id").is(Customer_Id),
					Criteria.where("Route.Route_Id").in(Route_Id)));

			UpdateResult wc = mongoTemplate.upsert(removeQuery,
					new Update().pull("Route", new Document("Route_Id", Route_Id)), Customer.class);
			// Query removeQuery =
			// Query.query(Criteria.where("Goods.Goods_Id").in(Goods_Id));

			// mongoTemplate.remove(customer);
			System.out.println("removed");

			flag = true;
			return flag;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("in catch");
		}
		return flag;
	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/deleteGood/{Customer_Id}/{Goods_Id}")
	public boolean deleteGoodsList(@PathVariable(value = "Customer_Id") String Customer_Id,
			@PathVariable(value = "Goods_Id") String Goods_Id) {
		System.out.println("came in delete" + Customer_Id);
		boolean flag = false;
		try {
			/*
			 * Query query = new Query();
			 * query.addCriteria(Criteria.where("Customer_Id").is(Customer_Id)
			 * .and("Goods").elemMatch(Criteria.where("Goods.Goods_Id").is(Goods_Id)));
			 * System.out.println("middle"); Update update = new Update();
			 * update.pull("Goods", new
			 * Query().addCriteria(Criteria.where("Goods.Goods_Id").is(Goods_Id)));
			 * FindAndModifyOptions options = FindAndModifyOptions.options();
			 * options.returnNew(true); System.out.println("last");
			 * System.out.println("query "+query); System.out.println("update "+update);
			 * mongoTemplate.findAndModify(query, update, options, Customer.class);
			 */
			Query removeQuery = Query.query(new Criteria().andOperator(Criteria.where("Customer_Id").is(Customer_Id),
					Criteria.where("Goods.Goods_Id").in(Goods_Id)));

			UpdateResult wc = mongoTemplate.upsert(removeQuery,
					new Update().pull("Goods", new Document("Goods_Id", Goods_Id)), Customer.class);
			// Query removeQuery =
			// Query.query(Criteria.where("Goods.Goods_Id").in(Goods_Id));

			// mongoTemplate.remove(customer);
			System.out.println("removed");

			flag = true;
			return flag;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("in catch");
		}
		return flag;
	}
	
	
	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/getShipments/{Customer_Id}/{Created_By}")
	public List<ShipmentDetails> getShipmentsInfo(@PathVariable(value = "Customer_Id") String Customer_id,
			@PathVariable(value = "Created_By") String Created_By) {

		List<ShipmentDetails> spd = new ArrayList<ShipmentDetails>();
		List<Shipments> sp = shiprepo.getShipments(Customer_id.trim(), Created_By.trim());
		if (sp == null) {

			return spd;

		} else {
			for (Shipments shp : sp) {

				ShipmentDetails innerSpd = new ShipmentDetails();
				innerSpd.setCustomer_Id(shp.getCustomer_Id());
				innerSpd.setCreated_By(shp.getCreated_By());
				innerSpd.setShipment_Id(shp.getShipment_Id());
				innerSpd.setShipment_Num(shp.getShipment_Num());
				innerSpd.setRoute_From(shp.getRoute_From());
				innerSpd.setRoute_To(shp.getRoute_To());
				innerSpd.setCreated_Date(shp.getCreated_Date());
				innerSpd.setEstimated_Delivery_Date(shp.getEstimated_Delivery_Date());
				innerSpd.setDelivered_Date(shp.getDelivered_Date());
				innerSpd.setDelivered_Status(shp.getShipment_Status());
				innerSpd.setGoods_Desc(shp.getGoods_Desc());
				innerSpd.setType_Of_Reference(shp.getType_Of_Reference());
				innerSpd.setTemp(shp.getTemp());

				List<ShipmentTransactions> innerSPT = shiptransrepo.findByShipment_Id(shp.getShipment_Id().trim());
				innerSpd.setDevice_Id(innerSPT.get(0).getDevice_Id());
				innerSpd.setEvent_Status(shiptransrepo.event_status(shp.getShipment_Id().trim()));

				innerSpd.setWayPoints(shiptransrepo.getlatLang(innerSPT.get(0).getDevice_Id(), shp.getCreated_Date()));
				spd.add(innerSpd);

			}

			return spd;
		}

	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/getShipmentsList/{Customer_Id}")
	public List<ShipmentDetails> getShipmentsInfo(@PathVariable(value = "Customer_Id") String Customer_id) {

		List<ShipmentDetails> spd = new ArrayList<ShipmentDetails>();
		List<Shipments> sp = shiprepo.findByCustomerId(Customer_id.trim());
		for (Shipments shp : sp) {

			ShipmentDetails innerSpd = new ShipmentDetails();
			innerSpd.setCustomer_Id(shp.getCustomer_Id());
			innerSpd.setCreated_By(shp.getCreated_By());
			innerSpd.setShipment_Id(shp.getShipment_Id());
			innerSpd.setShipment_Num(shp.getShipment_Num());
			innerSpd.setRoute_From(shp.getRoute_From());
			innerSpd.setRoute_To(shp.getRoute_To());
			innerSpd.setCreated_Date(shp.getCreated_Date());
			innerSpd.setEstimated_Delivery_Date(shp.getEstimated_Delivery_Date());
			innerSpd.setDelivered_Date(shp.getDelivered_Date());
			innerSpd.setDelivered_Status(shp.getShipment_Status());
			innerSpd.setGoods_Desc(shp.getGoods_Desc());
			innerSpd.setType_Of_Reference(shp.getType_Of_Reference());
			innerSpd.setTemp(shp.getTemp());
			innerSpd.setComments(shp.getComments());

			List<ShipmentTransactions> innerSPT = shiptransrepo.findByShipment_Id(shp.getShipment_Id().trim());
			innerSpd.setDevice_Id(innerSPT.get(0).getDevice_Id());
			innerSpd.setEvent_Status(shiptransrepo.event_status(shp.getShipment_Id().trim()));

			innerSpd.setWayPoints(shiptransrepo.getlatLang(innerSPT.get(0).getDevice_Id(), shp.getCreated_Date()));
			spd.add(innerSpd);

		}

		return spd;
	}
	
	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/getShipmentsListDelivered/{Customer_Id}/{shipment_status}")
	public List<ShipmentDetails> getShipmentsInfoDelivered(@PathVariable(value = "Customer_Id") String Customer_id,@PathVariable(value = "shipment_status") String Shipment_Status) {

		List<ShipmentDetails> spd = new ArrayList<ShipmentDetails>();
		List<Shipments> sp = shiprepo.findByCustomerIdShipments(Customer_id.trim(),Shipment_Status);
		for (Shipments shp : sp) {

			ShipmentDetails innerSpd = new ShipmentDetails();
			innerSpd.setCustomer_Id(shp.getCustomer_Id());
			innerSpd.setCreated_By(shp.getCreated_By());
			innerSpd.setShipment_Id(shp.getShipment_Id());
			innerSpd.setShipment_Num(shp.getShipment_Num());
			innerSpd.setRoute_From(shp.getRoute_From());
			innerSpd.setRoute_To(shp.getRoute_To());
			innerSpd.setCreated_Date(shp.getCreated_Date());
			innerSpd.setEstimated_Delivery_Date(shp.getEstimated_Delivery_Date());
			innerSpd.setDelivered_Date(shp.getDelivered_Date());
			innerSpd.setDelivered_Status(shp.getShipment_Status());
			innerSpd.setGoods_Desc(shp.getGoods_Desc());
			innerSpd.setType_Of_Reference(shp.getType_Of_Reference());
			innerSpd.setTemp(shp.getTemp());

			List<ShipmentTransactions> innerSPT = shiptransrepo.findByShipment_Id(shp.getShipment_Id().trim());
			innerSpd.setDevice_Id(innerSPT.get(0).getDevice_Id());
			innerSpd.setEvent_Status(shiptransrepo.event_status(shp.getShipment_Id().trim()));

			innerSpd.setWayPoints(shiptransrepo.getlatLang(innerSPT.get(0).getDevice_Id(), shp.getCreated_Date()));
			spd.add(innerSpd);

		}

		return spd;
	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/getShipmentsListAll")
	public List<ShipmentDetails> getShipmentsInfo() {

		List<ShipmentDetails> spd = new ArrayList<ShipmentDetails>();
		List<Shipments> sp = shiprepo.findAll();		// Used previously and now we modified it to get only shipments in live i.e., status not eq to delivered.
		System.out.println("sp");
		System.out.println(sp);
		///		List<Shipments> sp = shiprepo.findShipmentsNotDelivered();
		//List<ShipmentTransactions> spTran = shiptransrepo.findAll();
		for (Shipments shp : sp) {
			System.out.println(":::::::::: Fetching All Shipments List ::::::::::");
			ShipmentDetails innerSpd = new ShipmentDetails();
			innerSpd.setCustomer_Id(shp.getCustomer_Id());
			innerSpd.setCreated_By(shp.getCreated_By());
			innerSpd.setShipment_Id(shp.getShipment_Id());
			innerSpd.setShipment_Num(shp.getShipment_Num());
			innerSpd.setRoute_From(shp.getRoute_From());
			innerSpd.setRoute_To(shp.getRoute_To());
			innerSpd.setCreated_Date(shp.getCreated_Date());
			innerSpd.setEstimated_Delivery_Date(shp.getEstimated_Delivery_Date());
			innerSpd.setDelivered_Date(shp.getDelivered_Date());
			innerSpd.setDelivered_Status(shp.getShipment_Status());
			innerSpd.setGoods_Desc(shp.getGoods_Desc());
			innerSpd.setType_Of_Reference(shp.getType_Of_Reference());
			innerSpd.setTemp(shp.getTemp());
			
			innerSpd.setComments(shp.getComments());

			List<ShipmentTransactions> innerSPT = shiptransrepo.findByShipment_Id(shp.getShipment_Id().trim());
			System.out.println(innerSPT.get(0).getDevice_Id());
			innerSpd.setDevice_Id(innerSPT.get(0).getDevice_Id());
			innerSpd.setEvent_Status(shiptransrepo.event_status(shp.getShipment_Id().trim()));

			innerSpd.setWayPoints(shiptransrepo.getlatLang(innerSPT.get(0).getDevice_Id(), shp.getCreated_Date()));
			spd.add(innerSpd);

		}

		return spd;
	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/getShipmentsListByShipment_Id/{Shipment_Id}")
	public List<ShipmentDetails> getShipmentsInfoByShipmentId(@PathVariable(value = "Shipment_Id") String Shipment_Id) {

		List<Shipments> sp = shiprepo.getShipmentsList(Shipment_Id.trim());
		List<ShipmentDetails> spd = new ArrayList<ShipmentDetails>();
		for (Shipments shp : sp) {

			ShipmentDetails innerSpd = new ShipmentDetails();
			innerSpd.setCustomer_Id(shp.getCustomer_Id());
			innerSpd.setCreated_By(shp.getCreated_By());
			innerSpd.setShipment_Id(shp.getShipment_Id());
			innerSpd.setShipment_Num(shp.getShipment_Num());
			innerSpd.setRoute_From(shp.getRoute_From());
			innerSpd.setRoute_To(shp.getRoute_To());
			innerSpd.setCreated_Date(shp.getCreated_Date());
			innerSpd.setEstimated_Delivery_Date(shp.getEstimated_Delivery_Date());
			innerSpd.setDelivered_Date(shp.getDelivered_Date());
			innerSpd.setDelivered_Status(shp.getShipment_Status());
			innerSpd.setGoods_Desc(shp.getGoods_Desc());
			innerSpd.setType_Of_Reference(shp.getType_Of_Reference());

			List<ShipmentTransactions> innerSPT = shiptransrepo.findByShipment_Id(shp.getShipment_Id().trim());
			innerSpd.setDevice_Id(innerSPT.get(0).getDevice_Id());
			innerSpd.setEvent_Status(shiptransrepo.event_status(shp.getShipment_Id().trim()));

			innerSpd.setWayPoints(shiptransrepo.getlatLang(innerSPT.get(0).getDevice_Id(), shp.getCreated_Date()));
			spd.add(innerSpd);

		}

		return spd;
	}
	
	@ResponseBody
    @GetMapping("/getPartnerAndShipmentNumAndTypeOfReferenceAndDeviceId/{internalShipmentId}")
    public Shipments getPartnerAndShipmentNumAndTypeOfReferenceAndDeviceId(@PathVariable String internalShipmentId)
    {
        Shipments findByShipment_Id = shiprepo.findByShipment_Id(internalShipmentId);
        Shipments shipments = new Shipments();
        shipments.setDevice_Id(findByShipment_Id.getDevice_Id());
        shipments.setType_Of_Reference(findByShipment_Id.getType_Of_Reference());
        shipments.setShipment_Num(findByShipment_Id.getShipment_Num());
        shipments.setPlant(findByShipment_Id.getPlant());
       
        return shipments;
    }
	
	
	
	@ResponseBody
    @PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
    @GetMapping("/getShipmentCount")
    public ShipmentStatusCount getShipmentStatusCounts() {
        Query queryToFindLiveShipments = new Query(Criteria.where("Shipment_Status").ne("Delivered"));
        Query queryToFindDeliveredShipments = new Query(Criteria.where("Shipment_Status").is("Delivered"));  
        return new ShipmentStatusCount(mongoTemplate.count(queryToFindDeliveredShipments, Shipments.class), mongoTemplate.count(queryToFindLiveShipments, Shipments.class));
  }
	
	
	/*
	 * // DeviceData RestEndPoint
	 * 
	 * @ResponseBody
	 * 
	 * @GetMapping("/getDeviceData/{Device_Id}/{UTC}") public List<DeviceData>
	 * getDeviceData(@PathVariable(value = "Device_Id") String Device_Id,
	 * 
	 * @PathVariable(value = "UTC") String UTC) {
	 * 
	 * List<DeviceData> deviceData = new ArrayList<DeviceData>();
	 * List<DeviceDataStream> deviceDataStream =
	 * devicedatarepo.getDeviceDataStream(Device_Id.trim(), UTC.trim());
	 * 
	 * for (DeviceDataStream dds : deviceDataStream) {
	 * 
	 * DeviceData dd = new DeviceData(); dd.setAltitude(dds.getAltitude());
	 * dd.setBAT(dds.getBAT()); dd.setDevice_Id(dds.getDevice_Id());
	 * dd.setDistance(dds.getDistance());
	 * dd.setInternal_Temperature(dds.getInternal_temperature());
	 * dd.setLatitude(dds.getLatitude()); dd.setLongitude(dds.getLongitude());
	 * dd.setMessage_Number(dds.getMessage_Number());
	 * dd.setReport_type(dds.getReport_type()); dd.setSensor_id(dds.getSensor_id());
	 * dd.setSpeed(dds.getSpeed()); dd.setTemp_Measurment(dds.getTemp_Measurment());
	 * dd.setUTC(dds.getUTC());
	 * 
	 * deviceData.add(dd);
	 * 
	 * }
	 * 
	 * return deviceData; }
	 */

	
//	@Autowired
//    KafkaTemplate<String, JsonNode> kafkaTemplate;
//    
//    @PostMapping("/sendDataToTopic")
//    public ResponseEntity<?> sendDataToTopic(@RequestBody JsonNode jsonNode) throws InterruptedException, ExecutionException
//    {
//        kafkaTemplate.send("TagDevice_Data", jsonNode);
//        return new ResponseEntity<String>("Data Published to the Topic", HttpStatus.OK);
//    }
	
}
