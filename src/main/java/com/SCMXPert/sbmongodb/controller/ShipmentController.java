package com.SCMXPert.sbmongodb.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.DoubleSummaryStatistics;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import java.util.Set;
import java.util.Timer;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;
import javax.mail.util.ByteArrayDataSource;
import javax.activation.DataSource;
import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;

//import javax.validation.Valid;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.SCMXPert.sbmongodb.customImpl.CompleteEmailCalculations;
import com.SCMXPert.sbmongodb.customImpl.PDFGenerator;
import com.SCMXPert.sbmongodb.document.AddEvent;
import com.SCMXPert.sbmongodb.document.AlertMaster;
import com.SCMXPert.sbmongodb.document.AlertProfile;
import com.SCMXPert.sbmongodb.document.AllEvents;
import com.SCMXPert.sbmongodb.document.BPList;
import com.SCMXPert.sbmongodb.document.BusinessPartner;
import com.SCMXPert.sbmongodb.document.Calculations;
import com.SCMXPert.sbmongodb.document.CanCompleteShipmentDto;
import com.SCMXPert.sbmongodb.document.CompleteShipment;
import com.SCMXPert.sbmongodb.document.CompleteShipmentDto;
import com.SCMXPert.sbmongodb.document.CompleteShipmentTrigger;
import com.SCMXPert.sbmongodb.document.CopyAddEvent;
import com.SCMXPert.sbmongodb.document.CreateNewShipmentDto;
import com.SCMXPert.sbmongodb.document.CreateShipment;
import com.SCMXPert.sbmongodb.document.CustomBP;
import com.SCMXPert.sbmongodb.document.Customer;
import com.SCMXPert.sbmongodb.document.DeviceDataStream;
import com.SCMXPert.sbmongodb.document.DeviceDataTempDto;
import com.SCMXPert.sbmongodb.document.DeviceDropDown;
import com.SCMXPert.sbmongodb.document.Devicedatatemp;
import com.SCMXPert.sbmongodb.document.Devices;
import com.SCMXPert.sbmongodb.document.DropDownDto;
import com.SCMXPert.sbmongodb.document.DropDownShipmentDetails;
import com.SCMXPert.sbmongodb.document.ErrorExcel;
import com.SCMXPert.sbmongodb.document.EventId;
import com.SCMXPert.sbmongodb.document.Events;
import com.SCMXPert.sbmongodb.document.FiltersData;
import com.SCMXPert.sbmongodb.document.Goods;
import com.SCMXPert.sbmongodb.document.Response;
import com.SCMXPert.sbmongodb.document.Search;
import com.SCMXPert.sbmongodb.document.ShipmentDraftDto;
import com.SCMXPert.sbmongodb.document.ShipmentDraftPartialGet;
import com.SCMXPert.sbmongodb.document.ShipmentDrafts;
import com.SCMXPert.sbmongodb.document.ShipmentTransactionDeviceData;
import com.SCMXPert.sbmongodb.document.ShipmentTransactions;
import com.SCMXPert.sbmongodb.document.ShipmentWayinfo;
import com.SCMXPert.sbmongodb.document.Shipments;
import com.SCMXPert.sbmongodb.document.UpdateEvent;
import com.SCMXPert.sbmongodb.document.UpdateEventMassLoad;
import com.SCMXPert.sbmongodb.document.UpdateNewPlusEventDto;
import com.SCMXPert.sbmongodb.document.UpdateShipmentEvent;
import com.SCMXPert.sbmongodb.repository.AlertProfileRepository;
import com.SCMXPert.sbmongodb.repository.AlertRepository;
import com.SCMXPert.sbmongodb.repository.BusinessPartnerRepository;
import com.SCMXPert.sbmongodb.repository.CompleteShipmentRepo;
import com.SCMXPert.sbmongodb.repository.CompleteShipmentTriggerRepo;
import com.SCMXPert.sbmongodb.repository.CopyAddEventRepo;
import com.SCMXPert.sbmongodb.repository.CustomerRepository;
import com.SCMXPert.sbmongodb.repository.DeviceDataStreamRepository;
import com.SCMXPert.sbmongodb.repository.DeviceDropDownRepo;
import com.SCMXPert.sbmongodb.repository.DevicesRepository;
import com.SCMXPert.sbmongodb.repository.DropDownRepo;
import com.SCMXPert.sbmongodb.repository.DropDownShipmentDetailsRepo;
import com.SCMXPert.sbmongodb.repository.SaveDraftsRepo;
import com.SCMXPert.sbmongodb.repository.ShipmentDraftsRepo;
import com.SCMXPert.sbmongodb.repository.ShipmentSaveDraftRepo;
import com.SCMXPert.sbmongodb.repository.ShipmentTransactionsRepository;
import com.SCMXPert.sbmongodb.repository.ShipmentsRepository;
import com.SCMXPert.sbmongodb.repository.UpdateEventGetRepo;
import com.SCMXPert.sbmongodb.sequence.dao.EvenIdSequenceDao;
import com.itextpdf.text.DocumentException;
import com.mongodb.BasicDBObject;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.InvocationType;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.amazonaws.services.lambda.model.ServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;
import java.util.regex.Matcher;
//import jakarta.validation.Valid;

@Controller
@RequestMapping("/SCMXPert")
@CrossOrigin(origins = { "https://www.smaas.live", "https://smaas.live", "http://172.17.211.224:3000",
		"http://127.0.0.1:8081","https://136.233.44.146:8081","https://www.smaas.org" })

public class ShipmentController {

	@Autowired
	private ShipmentsRepository shiprepo;

	@Autowired
	private CompleteShipmentRepo completeShipRepo;

	@Autowired
	private ShipmentTransactionsRepository shiptransrepo;

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
	private DeviceDropDownRepo devicesDropDownrepo;

	@Autowired
	private BusinessPartnerRepository bussinesRepo;

	@Autowired
	private ShipmentSaveDraftRepo savedraftRepo;

	@Autowired
	private SaveDraftsRepo saveShipDraftsRepo;

	@Autowired
	private UpdateEventGetRepo updateeventgetrepo;

	@Autowired
	private CopyAddEventRepo copyaddrepo;

	@Autowired
	private AlertProfileRepository alertprofilerepo;

	@Autowired
	private AlertRepository alertmasterrepo;

	@Autowired
	private MailSenderService mailsend;

	@Autowired
	private PDFGenerator genratePDF;
	
	@Autowired
	private CompleteEmailCalculations cec;
	
	@Autowired
	private CompleteShipmentTriggerRepo completeShipTriggerRepo;

	private static final String HOSTING_SEQ_KEY = "hosting";

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//// The Below Rest End Points are The actual data Model for the Shipment

	public static <T> List<T> convertArrayTOList(T array[]) {
		List<T> list = new ArrayList<>();
		for (T t : array) {
			list.add(t);
		}
		return list;
	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@PostMapping("/addUpdateNewEvent")
	public boolean addUpdateNewEvent(@Validated @RequestBody UpdateNewPlusEventDto dto) {

		boolean flag = false;
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		String strDate = formatter.format(date);
		try {
			ShipmentTransactions shipmentTxns1 = new ShipmentTransactions();
			shipmentTxns1.setCustomer_Id(dto.getCustomerId());
			shipmentTxns1.setShipment_Id(dto.getShipment_Number());
			shipmentTxns1.setShipment_Num(dto.getShipment_Num());
			shipmentTxns1.setDevice_Id(dto.getDeviceId());
			shipmentTxns1.setMode_of_Transport(dto.getMode());
			shipmentTxns1.setPartner_From(dto.getParnterFrom());
			shipmentTxns1.setEvent_Id(dto.getEvent_Id());
			shipmentTxns1.setPartner_To(dto.getPartnerTo());
			shipmentTxns1.setEvent_Name(dto.getEventName());
			// dto.getDateAndTime()
			shipmentTxns1.setEvent_Exec_Date("");
			shipmentTxns1.setComments(dto.getComments());
			//List<@Valid ShipmentTransactions> shpt = shiptransrepo.findByShipment_Id(dto.getShipment_Num());
			List<ShipmentTransactions> shpt = shiptransrepo.findByShipment_Id(dto.getShipment_Number());
			
			int index = IntStream.range(0, shpt.size()) // .filter(i -> shpt.get(i).getStringProperty().equals(valueToFind))
					.filter(i -> shpt.get(i).getEvent_Name().equals("Goods Receipt"))
					.findFirst()
					.orElse(-1);
            ShipmentTransactions goodReceiptDoc = shpt.get(index);
            
			System.out.println("mode ");
			System.out.println(dto.getMode());
			long seqid = 0;
			for (ShipmentTransactions shp : shpt) {
				seqid = shp.getEvent_SNo();
			}
		//	shipmentTxns1.setEvent_SNo(seqid + 1);
			shipmentTxns1.setEvent_SNo(goodReceiptDoc.getEvent_SNo());	
			shipmentTxns1.setEvent_Status("Queued");
			shipmentTxns1.setExpected_Date_At_BP(strDate);
			shipmentTxns1.setShip_Date_From_BP(strDate);
			shipmentTxns1.setEvidence("");
			shipmentTxns1.setPartner(dto.getPartner());
			shipmentTxns1.setEvidence_URL(dto.getEvidence_URL());
			shipmentTxns1.setEvidenceList(dto.getEvidenceList());
			
			for(ShipmentTransactions shipment:shpt)
            {
                if(shipment.getInvoice_Number()!=null)
                {
                    shipmentTxns1.setInvoice_Number(shipment.getInvoice_Number());
                }
                
                if(shipment.getBatch_Id()!=null)
                {
                    System.out.println(shipment.getBatch_Id());
                    shipmentTxns1.setBatch_Id(shipment.getBatch_Id());
                }
                
                if(shipment.getComments().length!=0)
                {
                    if(shipment.getComments()[0].equals("")==false)
                    {
                        shipmentTxns1.setComments(new String[] {shipment.getComments()[0]});
                        System.out.println(shipment.getComments()[0]+"   length of array is : "+shipment.getComments().length);
                        
                    }
                }
                
                if(shipment.getMaterial_number()!=null)
                {
                    shipmentTxns1.setMaterial_number(shipment.getMaterial_number());
                }
            }
			
			shiptransrepo.insert(shipmentTxns1);
			
			Query query = new Query();
			query.addCriteria(
					new Criteria().andOperator(Criteria.where("Shipment_Id").is(dto.getShipment_Number()),
							Criteria.where("Event_Name").is("Goods Receipt")
							));
			
			Update update = new Update().set("Event_SNo", goodReceiptDoc.getEvent_SNo()+1); 
			
			mongoTemplate.updateFirst(query, update, ShipmentTransactions.class);
			
			Query query2 = new Query();
			query2.addCriteria(
					new Criteria().andOperator(Criteria.where("Shipment_Id").is(dto.getShipment_Number()),
							Criteria.where("Event_Name").is("Additional Documents")
							));
			
			Update update2 = new Update().set("Event_SNo", goodReceiptDoc.getEvent_SNo()+2); 
			
			mongoTemplate.updateFirst(query2, update2, ShipmentTransactions.class);
			
			flag = true;
			return flag;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return flag;
	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@PostMapping("/addNewEvent")
	public boolean addEvent(@Validated @RequestBody  AddEvent event) {
		boolean flag = false;
		List<@Valid Events> eveList = new ArrayList<>();
		try {
			Events events = new Events();
			BusinessPartner partner = bussinesRepo.findByBP_Id(event.getBussines_Id());
			if (partner.getBP_Id().equals(event.getBussines_Id())) {
				eveList.addAll(partner.getEvents());
				for (Events ev : partner.getEvents()) {
					if (!ev.getEvent_Id().equals(event.getEvent_Id())
							&& !ev.getEvent_Status().equals(event.getEventStatus())) {
						events.setEvent_Id(event.getEvent_Id());
						events.setEvent_Status(event.getEventStatus());
					} else {
						System.out.println("Event alredy exists");
						flag = false;
						return flag;
					}
				}
				eveList.add(events);
			}
			Query query1 = new Query();
			query1.addCriteria(new Criteria().andOperator(Criteria.where("BP_Id").is(event.getBussines_Id())));
			Update update1 = new Update();
			update1.set("Events", eveList);
			mongoTemplate.updateMulti(query1, update1, "BusinessPartner");
			flag = true;
			return flag;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return flag;

	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@PostMapping("/addCopyEvent")
	public boolean addCopyEvent(@Validated @RequestBody CopyAddEvent addeventdraft) {
		boolean flag = false;
		try {
			CopyAddEvent cae = copyaddrepo.findByEvent_Id(addeventdraft.getEvent_Id());
			if (cae.getEvent_Id().equals(addeventdraft.getEvent_Id())) {
				flag = false;
				return flag;
			}
		} catch (Exception e) {
		}
		copyaddrepo.insert(addeventdraft);
		flag = true;
		return flag;
	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/getCopyEvent/{EventId}")
	public CopyAddEvent getCopyEvent(@PathVariable(value = "EventId") String EventId) {
		return copyaddrepo.findByEvent_Id(EventId);
	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/getDropdowndata/{Customer_Id}")
	public BusinessPartner DropDownBusinessPartner(@PathVariable(value = "Customer_Id") String Customer_Id) {
		return bussinesRepo.findByBP_Id(Customer_Id.trim());

	}

	/*
	 * @ResponseBody
	 * 
	 * @GetMapping("/getDDDataBP/{Customer_Id}") public DropDownBusinessPartner
	 * getDDDataBP(@PathVariable(value = "Customer_Id") String Customer_id) throws
	 * Exception {
	 * 
	 * 
	 * 
	 * List<CustomBP> listbp = new ArrayList<>(); BusinessPartner partner = null;
	 * CustomBP custBpp = null; DropDownShipmentDetails dp =
	 * ddrepo1.findByCustomer_id(Customer_id.trim()); String[] list3 =
	 * dp.getBusiness_Partner_Id(); List<String> list4 = convertArrayTOList(list3);
	 * System.out.println(list4); for (String ar : list4) { custBpp = new
	 * CustomBP(); partner = bussinesRepo.findByBP_id(ar.trim());
	 * System.out.println(partner.getBP_Id()); if (partner.getBP_Id().equals(ar) &&
	 * !listbp.contains(partner.getBP_Id())) { if
	 * (listbp.contains(partner.getBP_Id())) { break; } else {
	 * custBpp.setBP_Id(partner.getBP_Id());
	 * custBpp.setCompany_Name(partner.getCompany_Name());
	 * custBpp.setEvents(partner.getEvents()); listbp.add(custBpp); } } }
	 * System.out.println(listbp); dp.setBussinesPartnersDetails(listbp);
	 * dp.setInternalShipmentId(generatedInternalShipmentId); return dp; }
	 */

	/*
	 * @ResponseBody
	 * 
	 * @PostMapping("/createNewShipment") public boolean
	 * createNewShipment(@RequestBody CreateNewShipmentDto dto) { boolean flag =
	 * false; Date date = new Date(); SimpleDateFormat formatter = new
	 * SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"); String strDate =
	 * formatter.format(date);
	 * 
	 * // CreateNewShipmentDto dto1 = null; Shipments shipment = new Shipments();
	 * ShipmentTransactions shipmentTxns = new ShipmentTransactions(); Shipments
	 * shipments = shiprepo.findByShipment_Id(dto.getShipment_Number()); //
	 * List<ShipmentTransactions> shipmentTxnsList = //
	 * shiptransrepo.findByShipment_Id(dto.getShipment_Number()); try { if
	 * (shipments.getShipment_Id().equals(dto.getShipment_Number())) {
	 * shipmentTxns.setCustomer_Id(dto.getCustomerId());
	 * shipmentTxns.setShipment_Id(dto.getShipment_Number());
	 * shipmentTxns.setDevice_Id(dto.getDeviceId());
	 * shipmentTxns.setPartner_From(dto.getParnterFrom());
	 * shipmentTxns.setPartner_To(dto.getPartnerTo());
	 * shipmentTxns.setEvent_Name(dto.getEventName());
	 * shipmentTxns.setEvent_Exec_Date(dto.getDateAndTime());
	 * shipmentTxns.setComments(dto.getComments());
	 * shipmentTxns.setEvent_SNo(evendiddao.getNextSequenceId(HOSTING_SEQ_KEY));
	 * shipmentTxns.setEvent_Status("INITIALIZED");
	 * shipmentTxns.setExpected_Date_At_BP(strDate);
	 * shipmentTxns.setShip_Date_From_BP(strDate);
	 * shiptransrepo.insert(shipmentTxns); flag = true; System.out.
	 * println("shipment id alredy exists so saved the Events in shipment Txns Collection"
	 * ); return flag; } } catch (Exception e) {
	 * 
	 * }
	 * 
	 * shipment.setCustomer_Id(dto.getCustomerId());
	 * shipment.setShipment_Id(dto.getShipment_Number());
	 * shipment.setType_Of_Reference(dto.getTypeOfReference());
	 * shipment.setComments(dto.getComments());
	 * shipment.setRoute_Id(dto.getRouteId());
	 * shipment.setRoute_From(dto.getRouteFrom());
	 * shipment.setRoute_To(dto.getRouteTo());
	 * shipment.setGoods_Id(dto.getGoodsId());
	 * shipment.setGoods_Desc(dto.getGoodsType());
	 * shipment.setEstimated_Delivery_Date(dto.getEstimatedDeliveryDate());
	 * shipment.setCreated_By(dto.getParnterFrom());
	 * shipment.setDevice_Id(dto.getDeviceId()); shipment.setCreated_Date(strDate);
	 * shipment.setInternalShipmentId(dto.getInternalShipmentId());
	 * shiprepo.insert(shipment);
	 * System.out.println("Data persisted in Shipment Collections");
	 * 
	 * shipmentTxns.setCustomer_Id(dto.getCustomerId());
	 * shipmentTxns.setShipment_Id(dto.getShipment_Number());
	 * shipmentTxns.setDevice_Id(dto.getDeviceId());
	 * shipmentTxns.setPartner_From(dto.getParnterFrom());
	 * shipmentTxns.setPartner_To(dto.getPartnerTo());
	 * shipmentTxns.setEvent_Name(dto.getEventName());
	 * shipmentTxns.setEvent_Exec_Date(dto.getDateAndTime());
	 * shipmentTxns.setComments(dto.getComments());
	 * shipmentTxns.setEvent_SNo(evendiddao.getNextSequenceId(HOSTING_SEQ_KEY));
	 * shipmentTxns.setEvent_Status("INITIALIZED");
	 * shipmentTxns.setExpected_Date_At_BP(strDate);
	 * shipmentTxns.setShip_Date_From_BP(strDate);
	 * shiptransrepo.insert(shipmentTxns);
	 * System.out.println("data persisted in ShipmentTrans collection"); flag =
	 * true; return flag;
	 * 
	 * }
	 */
	/*
	 * @ResponseBody
	 * 
	 * @PostMapping("/createNewShipment") public boolean
	 * createNewShipment(@RequestBody CreateNewShipmentDto dto) { boolean flag =
	 * false; Date date = new Date(); SimpleDateFormat formatter = new
	 * SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"); String strDate =
	 * formatter.format(date); Shipments shipment = new Shipments();
	 * ShipmentTransactions shipmentTxns1; ShipmentTransactions shipmentTxns2;
	 * Shipments shipments = shiprepo.findByShipment_Id(dto.getShipment_Number());
	 * try { if (shipments.getShipment_Id().equals(dto.getShipment_Number())) { for
	 * (AllEvents events : dto.getAllEvents()) { shipmentTxns2 = new
	 * ShipmentTransactions(); shipmentTxns2.setCustomer_Id(dto.getCustomerId());
	 * shipmentTxns2.setShipment_Id(dto.getShipment_Number());
	 * shipmentTxns2.setDevice_Id(dto.getDeviceId());
	 * shipmentTxns2.setPartner_From(events.getPartner());
	 * shipmentTxns2.setEvent_Id(events.getEvent_Id());
	 * shipmentTxns2.setPartner_To(dto.getPartnerTo());
	 * shipmentTxns2.setEvent_Name(events.getEvent());
	 * shipmentTxns2.setEvent_Exec_Date(dto.getDateAndTime());
	 * shipmentTxns2.setComments(dto.getComments());
	 * shipmentTxns2.setEvent_SNo(evendiddao.getNextSequenceId(HOSTING_SEQ_KEY));
	 * shipmentTxns2.setEvent_Status("INITIALIZED");
	 * shipmentTxns2.setExpected_Date_At_BP(strDate);
	 * shipmentTxns2.setShip_Date_From_BP(strDate);
	 * shiptransrepo.insert(shipmentTxns2); flag = true; System.out.
	 * println("shipment id alredy exists so saved the Events in shipment Txns Collection"
	 * ); // return flag;
	 * 
	 * }
	 * 
	 * } } catch (Exception e) {
	 * 
	 * } shipment.setCustomer_Id(dto.getCustomerId());
	 * shipment.setShipment_Id(dto.getShipment_Number());
	 * shipment.setType_Of_Reference(dto.getTypeOfReference());
	 * shipment.setComments(dto.getComments());
	 * shipment.setRoute_Id(dto.getRouteId());
	 * shipment.setRoute_From(dto.getRouteFrom());
	 * shipment.setRoute_To(dto.getRouteTo());
	 * shipment.setGoods_Id(dto.getGoodsId());
	 * shipment.setGoods_Desc(dto.getGoodsType());
	 * shipment.setEstimated_Delivery_Date(dto.getEstimatedDeliveryDate());
	 * shipment.setCreated_By(dto.getParnterFrom());
	 * shipment.setDevice_Id(dto.getDeviceId());
	 * shipment.setIncoTerms(dto.getIncoTerms()); shipment.setMode(dto.getMode());
	 * shipment.setTemp(dto.getTemp()); shipment.setRH(dto.getRH());
	 * shipment.setCreated_Date(strDate);
	 * shipment.setShipment_Num(dto.getShipment_Num()); shiprepo.insert(shipment);
	 * System.out.println("Data persisted in Shipment Collections");
	 * 
	 * for (AllEvents events : dto.getAllEvents()) { shipmentTxns1 = new
	 * ShipmentTransactions(); shipmentTxns1.setCustomer_Id(dto.getCustomerId());
	 * shipmentTxns1.setShipment_Id(dto.getShipment_Number());
	 * shipmentTxns1.setDevice_Id(dto.getDeviceId());
	 * shipmentTxns1.setPartner_From(events.getPartner());
	 * shipmentTxns1.setEvent_Id(events.getEvent_Id());
	 * shipmentTxns1.setPartner_To(dto.getPartnerTo());
	 * shipmentTxns1.setEvent_Name(events.getEvent());
	 * shipmentTxns1.setEvent_Exec_Date(dto.getDateAndTime());
	 * shipmentTxns1.setComments(dto.getComments());
	 * shipmentTxns1.setEvent_SNo(evendiddao.getNextSequenceId(HOSTING_SEQ_KEY));
	 * shipmentTxns1.setEvent_Status("INITIALIZED");
	 * shipmentTxns1.setExpected_Date_At_BP(strDate);
	 * shipmentTxns1.setShip_Date_From_BP(strDate);
	 * shiptransrepo.insert(shipmentTxns1); flag = true; System.out.
	 * println("shipment id alredy exists so saved the Events in shipment Txns Collection"
	 * ); // return flag;
	 * 
	 * } return flag; }
	 */

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@PostMapping("/createNewShipment")
	public Response createNewShipment(@Validated @RequestBody CreateNewShipmentDto dto) throws Exception {
		Response resp = new Response();
		// boolean flag = false;
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		String strDate = formatter.format(date);
		Shipments shipment = new Shipments();
		ShipmentTransactions shipmentTxns1;
		ShipmentTransactions shipmentTxns2;
		Shipments shipments = shiprepo.findByShipment_Id(dto.getShipment_Number());
		
		List<Shipments> shipmentRepo = shiprepo.findAll();
		
		System.out.println("shipmentRepo");
		System.out.println(shipmentRepo);
				
		System.out.println("Shipment Num");
		System.out.println(dto.getShipment_Num());
		System.out.println("Shipment Number");
		System.out.println(dto.getShipment_Number());
		
		System.out.println("in condition 1 ");
		System.out.println(!shipmentRepo.toString().contains(dto.getShipment_Num()));
		if (!shipmentRepo.toString().contains(dto.getShipment_Num())) {		
		try {
			  if (shipments.getShipment_Id().equals(dto.getShipment_Number())) {
				for (AllEvents events : dto.getAllEvents()) {
					shipmentTxns2 = new ShipmentTransactions();
					shipmentTxns2.setCustomer_Id(dto.getCustomerId());
					shipmentTxns2.setShipment_Id(dto.getShipment_Number());
					shipmentTxns2.setShipment_Num(dto.getShipment_Num());
					shipmentTxns2.setDevice_Id(dto.getDeviceId());
					shipmentTxns2.setSecondDevice(dto.getSecondDevice());
					shipmentTxns2.setThirdDevice(dto.getThirdDevice());
					shipmentTxns2.setFourthDevice(dto.getFourthDevice());
					shipmentTxns2.setFifthDevice(dto.getFifthDevice());
					shipmentTxns2.setNumberOfDevices(dto.getNumberOfDevices());
					shipmentTxns2.setPreviousDelivery(dto.getPreviousDelivery());
					shipmentTxns2.setPreviousInvoice(dto.getPreviousInvoice());
					shipmentTxns2.setPreviousPlant(dto.getPreviousPlant());
					shipmentTxns2.setShipmentModel(dto.getShipmentModel());
					shipmentTxns2.setPartner(events.getBp_Id());
					shipmentTxns2.setPartner_From(events.getPartner());
					shipmentTxns2.setEvent_Id(events.getEvent_Id());
					shipmentTxns2.setPartner_To(dto.getPartnerTo());
					shipmentTxns2.setEvent_Name(events.getEvent());
					shipmentTxns2.setEvent_Exec_Date(dto.getDateAndTime());
					shipmentTxns2.setComments(dto.getComments());
					shipmentTxns2.setMode_of_Transport(dto.getMode());
					shipmentTxns2.setEvent_SNo(evendiddao.getNextSequenceId(HOSTING_SEQ_KEY));
					System.out.println("Event status in Transac2 ");
					System.out.println(events.getEvent());
				//	try 
					//{
						if(events.getEvent().equals("Shipment Created")) { 
					    System.out.println("Event status in Transac2 " +events.getEvent());
						shipmentTxns2.setEvent_Status("Completed");
						shipmentTxns2.setEvent_Exec_Date(strDate);
						}
						else {
							System.out.println("event status in Transac2 " +events.getEvent()); 
							shipmentTxns2.setEvent_Status(events.getEvent_Status()); 
						}	
					//	}
					//catch(Exception e){
						//e.printStackTrace();
						//System.out.println("Exception in shipment status ");
						//}
			////	shipmentTxns2.setEvent_Status(""); ////remove here if you comment above try catch
					shipmentTxns2.setExpected_Date_At_BP(strDate);
					shipmentTxns2.setShip_Date_From_BP(strDate);
					//shipmentTxns2.setEvidence_URL(dto.getEvidence_URL());
					//shipmentTxns2.setEvidenceList(dto.getEvidenceList());					
					shiptransrepo.insert(shipmentTxns2);
					resp.setMessage("shipment id alredy exists so saved the Events in shipment Txns Collection");
					resp.setStatus(true);

					return resp;

				}
			}
		} catch (Exception e) {
            e.printStackTrace();
		}
		shipment.setCustomer_Id(dto.getCustomerId());
		shipment.setInternalShipmentId(dto.getInternalShipmentId());
		shipment.setShipment_Id(dto.getShipment_Number());
		shipment.setShipment_Num(dto.getShipment_Num());
		shipment.setType_Of_Reference(dto.getTypeOfReference());
		shipment.setComments(dto.getComments());
		shipment.setRoute_Id(dto.getRouteId());
		shipment.setCustRouteId(dto.getCustRouteId()); 
		shipment.setCustGoodId(dto.getCustGoodId());
		shipment.setRoute_From(dto.getRouteFrom());
		shipment.setRoute_To(dto.getRouteTo());
		shipment.setGoods_Id(dto.getGoodsId());
		shipment.setGoods_Desc(dto.getGoodsType());
		shipment.setEstimated_Delivery_Date(dto.getEstimatedDeliveryDate());
		shipment.setCreated_By(dto.getParnterFrom());
		shipment.setDevice_Id(dto.getDeviceId());
		shipment.setIncoTerms(dto.getIncoTerms());
		shipment.setMode(dto.getMode());
		shipment.setTemp(dto.getTemp());
		shipment.setRH(dto.getRH());
		shipment.setPartner(dto.getPartner());
		shipment.setShipment_Status(dto.getEvent());
//		shipment.setDelivered_Date(dto.getDatee());
		shipment.setDelivered_Date("");
		shipment.setCreated_Date(strDate);
		shipment.setPo_Number(dto.getPo_Number());
		shipment.setPoItmNumber(dto.getPoItmNumber());
		shipment.setNdc_Number(dto.getNdc_Number());
		shipment.setInvoice_Number(dto.getInvoice_Number());
		shipment.setShipper_Number(dto.getShipper_Number());
		shipment.setSerial_Number_of_goods(dto.getSerial_Number_of_goods());
		shipment.setBatch_Id(dto.getBatch_Id());
		shipment.setCmo_Ref_Number(dto.getCmo_Ref_Number());
		
		shipment.setMaterial_number(dto.getMaterial_number());
		shipment.setPlant(dto.getPlant());
		
		shipment.setSecondDevice(dto.getSecondDevice());
		shipment.setThirdDevice(dto.getThirdDevice());
		shipment.setFourthDevice(dto.getFourthDevice());
		shipment.setFifthDevice(dto.getFifthDevice());
		shipment.setNumberOfDevices(dto.getNumberOfDevices());
		shipment.setPreviousDelivery(dto.getPreviousDelivery());
		shipment.setPreviousInvoice(dto.getPreviousInvoice());
		shipment.setPreviousPlant(dto.getPreviousPlant());
		shipment.setShipmentModel(dto.getShipmentModel());
		
		try {
			if (!dto.getInternalShipmentId().equals(null)) {
				shipment.setInternalShipmentId(generateInternalShipmentId());
			}
		} catch (NullPointerException e) {
			shipment.setInternalShipmentId(generateInternalShipmentId());
		}
		shiprepo.insert(shipment);
		Query query = new Query();
		query.addCriteria(new Criteria().andOperator(Criteria.where("Device_Id").is(dto.getDeviceId())));
		Update update = new Update();
		update.set("DeviceStatusReferred", "Attached To Shipment");
		mongoTemplate.updateMulti(query, update, "Devices");
		System.out.println("Data persisted in Shipment Collections" + dto.getAllEvents());

		for (AllEvents events : dto.getAllEvents()) {
			System.out.println("Events::::" + events.getEvent_Id());

			shipmentTxns1 = new ShipmentTransactions();
			shipmentTxns1.setCustomer_Id(dto.getCustomerId());
			shipmentTxns1.setShipment_Id(dto.getShipment_Number());
			shipmentTxns1.setShipment_Num(dto.getShipment_Num());
			shipmentTxns1.setDevice_Id(dto.getDeviceId());
			
			shipmentTxns1.setSecondDevice(dto.getSecondDevice());
			shipmentTxns1.setThirdDevice(dto.getThirdDevice());
			shipmentTxns1.setFourthDevice(dto.getFourthDevice());
			shipmentTxns1.setFifthDevice(dto.getFifthDevice());
			
			shipmentTxns1.setNumberOfDevices(dto.getNumberOfDevices());
			shipmentTxns1.setPreviousDelivery(dto.getPreviousDelivery());
			shipmentTxns1.setPreviousInvoice(dto.getPreviousInvoice());
			shipmentTxns1.setPreviousPlant(dto.getPreviousPlant());
			shipmentTxns1.setShipmentModel(dto.getShipmentModel());
			
			shipmentTxns1.setPartner_From(events.getPartner());
			shipmentTxns1.setEvent_Id(events.getEvent_Id());

			shipmentTxns1.setPartner_To(dto.getPartnerTo());
			shipmentTxns1.setEvent_Name(events.getEvent());
			shipmentTxns1.setPartner(events.getBp_Id());
			shipmentTxns1.setEvidence(events.getEvidence());
			// shipmentTxns1.setEvent_Statusa(events.getEvent_Statusa());
			shipmentTxns1.setEvent_Exec_Date(dto.getDateAndTime());
			shipmentTxns1.setComments(dto.getComments());
			shipmentTxns1.setMode_of_Transport(dto.getMode());
			shipmentTxns1.setEvent_SNo(evendiddao.getNextSequenceId(HOSTING_SEQ_KEY));
		///	shipmentTxns1.setEvent_Status(events.getEvent_Status()); ////remove here if you comment above try catch
			System.out.println("Event status in Transac1 " +events.getEvent());
			//try 
			//{ 
				if(events.getEvent().equals("Shipment Created")) 
				{ 
					System.out.println("event status Transac1" +events.getEvent()); 
					shipmentTxns1.setEvent_Status("Completed");
					shipmentTxns1.setEvent_Exec_Date(strDate);
				  } 
				else 
				{ 
					System.out.println("event status in else for Transac1" +events.getEvent()); 
					shipmentTxns1.setEvent_Status(events.getEvent_Status()); 
				} 
			//} 
			//catch(Exception e)
			//{ 
				System.out.println("Exception in setting shipment status "); 
			//}
			shipmentTxns1.setExpected_Date_At_BP(strDate);
			shipmentTxns1.setShip_Date_From_BP(strDate);
			shipmentTxns1.setInvoice_Number(dto.getInvoice_Number());
			shipmentTxns1.setBatch_Id(dto.getBatch_Id());
			shipmentTxns1.setMaterial_number(dto.getMaterial_number());
			shipmentTxns1.setPlant(dto.getPlant());
			//shipmentTxns1.setEvidence_URL(dto.getEvidence_URL());
			//shipmentTxns1.setEvidenceList(dto.getEvidenceList());
			shiptransrepo.insert(shipmentTxns1);

			resp.setMessage("Shipment and Events Created Successfully");
			resp.setStatus(true);

		}
	}
//		else {
//			List<Shipments> shipmentsList = shiprepo.findByShipment_Num(dto.getShipment_Num());
//			
//			System.out.println("shipments record");
//			System.out.println(shipmentsList);
//			
//			 if (shipmentsList != null) {
//				 for(Shipments shpList: shipmentsList) {
//					 if(shpList.) {
//						 
//					 }
//					 
//				 }
//				 
//			 }
//		}
	   return resp;
	}

	/*
	 * @ResponseBody
	 * 
	 * @PostMapping("/createNewShipment") public Response
	 * createNewShipment(@RequestBody CreateNewShipmentDto dto) throws Exception {
	 * 
	 * Response resp = new Response(); // resp.setStatus(false);
	 * 
	 * // String Status ="";
	 * 
	 * Date date = new Date(); SimpleDateFormat formatter = new
	 * SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"); String strDate =
	 * formatter.format(date); Shipments shipment = new Shipments();
	 * ShipmentTransactions shipmentTxns1; ShipmentTransactions shipmentTxns2;
	 * Shipments shipments = shiprepo.findByShipment_Id(dto.getShipment_Number());
	 * 
	 * try { if (shipments.getShipment_Id().equals(dto.getShipment_Number())) {
	 * System.out.println("Hello Events"); for (AllEvents events :
	 * dto.getAllEvents()) { shipmentTxns2 = new ShipmentTransactions();
	 * shipmentTxns2.setCustomer_Id(dto.getCustomerId());
	 * shipmentTxns2.setShipment_Id(dto.getShipment_Number());
	 * shipmentTxns2.setShipment_Num(dto.getShipment_Num());
	 * shipmentTxns2.setDevice_Id(dto.getDeviceId());
	 * shipmentTxns2.setPartner_From(events.getPartner());
	 * shipmentTxns2.setEvent_Id(events.getEvent_Id());
	 * shipmentTxns2.setPartner_To(dto.getPartnerTo());
	 * shipmentTxns2.setEvidence(events.getEvidence());
	 * shipmentTxns2.setEvent_Name(events.getEvent());
	 * shipmentTxns2.setEvent_Exec_Date(dto.getDateAndTime());
	 * shipmentTxns2.setComments(dto.getComments());
	 * shipmentTxns2.setEvent_SNo(evendiddao.getNextSequenceId(HOSTING_SEQ_KEY));
	 * shipmentTxns2.setEvent_Status("");
	 * shipmentTxns2.setExpected_Date_At_BP(strDate);
	 * shipmentTxns2.setShip_Date_From_BP(strDate);
	 * shiptransrepo.insert(shipmentTxns2);
	 * 
	 * resp.setStatus(true); resp.setMessasge("Created Shipment Successfully");
	 * 
	 * return resp; // flag = true; // System.out.println("shipment id alredy exists
	 * so saved the Events in shipment // Txns Collection"); // return flag;
	 * 
	 * } } } catch (Exception e) {
	 * 
	 * // resp.setStatus(false); resp.setMessasge("ShipmentID Already exists");
	 * 
	 * // return resp;
	 * 
	 * } try { shipment.setCustomer_Id(dto.getCustomerId());
	 * shipment.setShipment_Id(dto.getInternalShipmentId());
	 * shipment.setShipment_Num(dto.getShipment_Num());
	 * shipment.setType_Of_Reference(dto.getTypeOfReference());
	 * shipment.setComments(dto.getComments());
	 * shipment.setRoute_Id(dto.getRouteId());
	 * shipment.setRoute_From(dto.getRouteFrom());
	 * shipment.setRoute_To(dto.getRouteTo());
	 * shipment.setGoods_Id(dto.getGoodsId());
	 * shipment.setGoods_Desc(dto.getGoodsType());
	 * shipment.setEstimated_Delivery_Date(dto.getEstimatedDeliveryDate());
	 * shipment.setCreated_By(dto.getParnterFrom());
	 * shipment.setDevice_Id(dto.getDeviceId());
	 * shipment.setIncoTerms(dto.getIncoTerms()); shipment.setMode(dto.getMode());
	 * shipment.setTemp(dto.getTemp()); shipment.setRH(dto.getRH());
	 * shipment.setPartner(dto.getPartner());
	 * shipment.setShipment_Status(dto.getEvent());
	 * shipment.setDelivered_Date(dto.getDatee());
	 * shipment.setCreated_Date(strDate); try { if
	 * (dto.getInternalShipmentId().equals("")) {
	 * shipment.setInternalShipmentId(generateInternalShipmentId()); } } catch
	 * (NullPointerException e) {
	 * shipment.setInternalShipmentId(generateInternalShipmentId()); }
	 * shiprepo.insert(shipment); Query query = new Query(); query.addCriteria(new
	 * Criteria().andOperator(Criteria.where("Device_Id").is(dto.getDeviceId())));
	 * Update update = new Update(); update.set("DeviceStatusReferred",
	 * "Attached To Shipment"); mongoTemplate.updateMulti(query, update, "Devices");
	 * System.out.println("Data persisted in Shipment Collections");
	 * System.out.println("hjskdgfsafksadf"+dto.getAllEvents()); for (AllEvents
	 * events : dto.getAllEvents()) { shipmentTxns1 = new ShipmentTransactions();
	 * shipmentTxns1.setCustomer_Id(dto.getCustomerId());
	 * shipmentTxns1.setShipment_Id(dto.getShipment_Number());
	 * shipmentTxns1.setShipment_Num(dto.getShipment_Num());
	 * shipmentTxns1.setDevice_Id(dto.getDeviceId());
	 * shipmentTxns1.setPartner_From(events.getPartner());
	 * shipmentTxns1.setEvent_Id(events.getEvent_Id());
	 * shipmentTxns1.setPartner_To(dto.getPartnerTo());
	 * shipmentTxns1.setEvent_Name(events.getEvent());
	 * shipmentTxns1.setEvidence(events.getEvidence());
	 * shipmentTxns1.setEvent_Statusa(events.getEvent_Statusa());
	 * shipmentTxns1.setEvent_Exec_Date(dto.getDateAndTime());
	 * shipmentTxns1.setComments(dto.getComments());
	 * shipmentTxns1.setEvent_SNo(evendiddao.getNextSequenceId(HOSTING_SEQ_KEY));
	 * shipmentTxns1.setEvent_Status("");
	 * shipmentTxns1.setExpected_Date_At_BP(strDate);
	 * shipmentTxns1.setShip_Date_From_BP(strDate);
	 * shiptransrepo.insert(shipmentTxns1); resp.setStatus(true);
	 * resp.setMessasge("Created Shipment and Events Successfully"); System.out.
	 * println("shipment id alredy exists so saved the Events in shipment Txns Collection"
	 * ); return resp;
	 * 
	 * } } catch (Exception e) {
	 * 
	 * // TODO: handle exception } return resp; }
	 */
	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/getEventId/{BP_Id}/{EventType}")
	public EventId getEventId(@PathVariable(value = "BP_Id") String BpId,
			@PathVariable(value = "EventType") String EventType) {
		EventId evId = new EventId();
		List<BusinessPartner> ev = new ArrayList<>();
		Criteria crt = new Criteria();
		crt.andOperator(Criteria.where("BP_Id").is(BpId));
		Query query = new Query(crt);
		ev = mongoTemplate.find(query, BusinessPartner.class);
		for (BusinessPartner bp : ev) {
			for (Events e : bp.getEvents()) {
				if (e.getEvent_Status().equals(EventType)) {
					evId.setEvent_Id(e.getEvent_Id());
					evId.setEvent_Status(e.getEvent_Status());
				}
			}
		}
		return evId;
	}

	/*
	 * @ResponseBody
	 * 
	 * @PostMapping("/saveDraft") public boolean saveShipmentDraft(@RequestBody
	 * ShipmentDraftDto dto) { boolean flag = false; ShipmentDraftDto draft = null;
	 * 
	 * try { ShipmentDraftDto internalId =
	 * saveShipDraftsRepo.findByInternal_Shipment_id(dto.getInternal_Shipment_Id());
	 * if
	 * (internalId.getInternal_Shipment_Id().equals(dto.getInternal_Shipment_Id()))
	 * { System.out.println("Draft with the Internal Shipmet Id alredy exists");
	 * flag = false; return flag; } } catch (Exception e) {
	 * System.out.println(e.getMessage()); } draft = new ShipmentDraftDto();
	 * draft.setInternal_Shipment_Id(dto.getInternal_Shipment_Id());
	 * draft.setCustomerId(dto.getCustomerId());
	 * draft.setShipment_Number(dto.getShipment_Number());
	 * draft.setTypeOfReference(dto.getTypeOfReference());
	 * draft.setComments(dto.getComments()); draft.setRouteId(dto.getRouteId());
	 * draft.setRouteFrom(dto.getRouteFrom()); draft.setRouteTo(dto.getRouteTo());
	 * draft.setGoodsId(dto.getGoodsId()); draft.setGoodsType(dto.getGoodsType());
	 * draft.setDeviceId(dto.getDeviceId());
	 * draft.setEstimatedDeliveryDate(dto.getEstimatedDeliveryDate());
	 * draft.setParnterFrom(dto.getParnterFrom());
	 * draft.setPartnerTo(dto.getPartnerTo());
	 * draft.setEventName(dto.getEventName());
	 * draft.setDateAndTime(dto.getDateAndTime());
	 * draft.setIncoterms(dto.getIncoterms()); savedraftRepo.insert(draft); flag =
	 * true;
	 * 
	 * return flag; }
	 */
	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@PostMapping("/saveDraft")
	public boolean saveShipmentDraft(@Validated @RequestBody ShipmentDraftDto dto) {
		boolean flag = false;
		ShipmentDraftDto draft = null;

		try {
			ShipmentDraftDto internalId = saveShipDraftsRepo.findByInternal_Shipment_id(dto.getInternal_Shipment_Id());
			if (internalId.getInternal_Shipment_Id().equals(dto.getInternal_Shipment_Id())) {
				System.out.println("Draft with the Internal Shipmet Id alredy exists");
				flag = false;
				return flag;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		draft = new ShipmentDraftDto();
		draft.setInternal_Shipment_Id(dto.getInternal_Shipment_Id());
		draft.setCustomer_Id(dto.getCustomer_Id());
		draft.setShipment_Number(dto.getShipment_Number());
		draft.setTypeOfReference(dto.getTypeOfReference());
		draft.setComments(dto.getComments());
		draft.setRouteId(dto.getRouteId());
		draft.setRouteFrom(dto.getRouteFrom());
		draft.setRouteTo(dto.getRouteTo());
		draft.setGoodsId(dto.getGoodsId());
		draft.setGoodsType(dto.getGoodsType());
		// draft.setDeviceId(dto.getDeviceId());
		draft.setEstimatedDeliveryDate(dto.getEstimatedDeliveryDate());
		draft.setParnterFrom(dto.getParnterFrom());
		draft.setPartnerTo(dto.getPartnerTo());
		draft.setEventName(dto.getEventName());
		draft.setDateAndTime(dto.getDateAndTime());
		draft.setInco(dto.getInco());

		draft.setRouteInfo(dto.getRouteInfo());
		draft.setMode(dto.getMode());
		draft.setSelectEventId(dto.getSelectEventId());
		draft.setPo_Number(dto.getPo_Number());
		draft.setNdc_Number(dto.getNdc_Number());
		draft.setInvoice_Number(dto.getInvoice_Number());
		draft.setShipper_Number(dto.getShipper_Number());
		draft.setSerial_Number_of_goods(dto.getSerial_Number_of_goods());
		draft.setBatch_Id(dto.getBatch_Id());
		savedraftRepo.insert(draft);
		flag = true;

		return flag;
	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@PostMapping("/deleteDraft/{Internal_Shipment_Id}")
	public boolean deleteDraft(@PathVariable(value = "Internal_Shipment_Id") String Internal_Shipment_Id) {
		boolean flag = false;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("Internal_Shipment_Id").is(Internal_Shipment_Id));
			mongoTemplate.findAndRemove(query, ShipmentDraftDto.class);
			System.out.println("removed");
			flag = true;
			return flag;

		} catch (Exception e) {
			// TODO: handle exception
		}
		return flag;
	}

	/*
	 * @ResponseBody
	 * 
	 * @PostMapping("/deleteGoodItem/{Customer_Id}") public Customer
	 * deleteGoodItem(@PathVariable(value = "Customer_Id") String
	 * Customer_Id,@PathVariable(value = "Good_Id") Goods Good_Id) {
	 * 
	 * // Update update = new Update().pull("Goods", new //
	 * BasicDBObject("Goods.Goods_Id",Good_Id)); // mongoTemplate.updateMulti(new
	 * Query(), update, Goods.class);
	 * 
	 * 
	 * Query query = new Query();
	 * query.addCriteria(Criteria.where("Customer_Id").is(Good_Id));
	 * mongoTemplate.findAndRemove(query, Goods.class);
	 * System.out.println("removed");
	 * 
	 * return customerepo.findByCustomerId(Customer_Id.trim());
	 * 
	 * 
	 * }
	 */
	// public GetService to get the DetailsOfTheAllTheShipmentTransaction which
	// are associated with ShipmentId and BuspartId

	/*
	 * @ResponseBody
	 * 
	 * @GetMapping("/getDeviceDataTemp/{Shipment_Id}") public List<DeviceDataStream>
	 * getDeviceDataTemp(@PathVariable(value = "Shipment_Id") String Shipment_Id) {
	 * Shipments shipment = shiprepo.findByShipment_Id(Shipment_Id); String deviceId
	 * = shipment.getDevice_Id();
	 * 
	 * String createdDate = shipment.getCreated_Date();
	 * System.out.println(deviceId); List<DeviceDataStream> deviceDataStrem = null;
	 * try { deviceDataStrem = devicedatarepo.getDeviceDataStream(deviceId.trim(),
	 * createdDate.trim()); System.out.println(deviceDataStrem); } catch (Exception
	 * e) { } return deviceDataStrem; }
	 */
	/*
	 * @ResponseBody
	 * 
	 * @GetMapping("/getDeviceDataTemp/{Shipment_Id}") public List<Devicedatatemp>
	 * getDeviceDataTemp(@PathVariable(value = "Shipment_Id") String Shipment_Id) {
	 * Shipments shipment = shiprepo.findByShipment_Id(Shipment_Id); String deviceId
	 * = shipment.getDevice_Id(); System.out.println(deviceId);
	 * List<DeviceDataStream> deviceDataStrem1 = null; List<Devicedatatemp> devlist
	 * = new ArrayList<>(); String Humidity = null; // String
	 * Internal_Temperature=null; String deviceDataStream1 = ""; try {
	 * 
	 * deviceDataStrem1 = devicedatarepo.findByDevice_id(deviceId);
	 * 
	 * for (DeviceDataStream dv : deviceDataStrem1) { Devicedatatemp temphum = new
	 * Devicedatatemp();
	 * 
	 * if (dv.getMessageType().equals("sensor")) {
	 * System.out.println("shadgasjhgdfjasg" + dv.getUTC());
	 * temphum.setHumidity(dv.getHumidity_1()); temphum.setUTC(dv.getUTC());
	 * temphum.setDevice_Id(dv.getDevice_Id());
	 * temphum.setInternal_Temperature(dv.getInternal_temperature());
	 * devlist.add(temphum);
	 * 
	 * } }
	 * 
	 * // deviceDataStrem = devicedatarepo.getInternalTemp(deviceId) //
	 * System.out.println(deviceDataStrem); } catch (Exception e) { } return
	 * devlist; }
	 */

	/*
	 * @ResponseBody
	 * 
	 * @GetMapping("/getDeviceDataTemp/{Shipment_Id}") public List<Devicedatatemp>
	 * getDeviceDataTemp(@PathVariable(value = "Shipment_Id") String Shipment_Id) {
	 * Shipments shipment = shiprepo.findByShipment_Id(Shipment_Id); String deviceId
	 * = shipment.getDevice_Id(); String created_date = shipment.getCreated_Date();
	 * System.out.println("Created Date::::" + created_date);
	 * System.out.println(deviceId); List<DeviceDataStream> deviceDataStrem1 = null;
	 * List<Devicedatatemp> devlist = new ArrayList<>(); String Humidity = null; //
	 * String Internal_Temperature=null; String deviceDataStream1 = ""; try {
	 * 
	 * deviceDataStrem1 = devicedatarepo.findByDevice_id(deviceId);
	 * 
	 * for (DeviceDataStream dv : deviceDataStrem1) { Devicedatatemp temphum = new
	 * Devicedatatemp();
	 * 
	 * if (dv.getMessageType().equals("sensor")) { if
	 * (dv.getSensorUTC().compareTo(created_date) > 0) {
	 * System.out.println("DeviceUTC::::" + dv.getSensorUTC());
	 * 
	 * temphum.setUTC(dv.getSensorUTC()); // System.out.println("shadgasjhgdfjasg");
	 * temphum.setHumidity(dv.getHumidity_1());
	 * temphum.setInternal_Temperature(dv.getInternal_temperature());
	 * 
	 * devlist.add(temphum); }
	 * 
	 * } }
	 * 
	 * // deviceDataStrem = devicedatarepo.getInternalTemp(deviceId) //
	 * System.out.println(deviceDataStrem); } catch (Exception e) { } return
	 * devlist; }
	 */
	/*
	 * @ResponseBody
	 * 
	 * @PostMapping("/completeNewShipment") public boolean
	 * completeNewShipment(@RequestBody CompleteShipment shipment) { boolean flag =
	 * false; Date date = new Date(); SimpleDateFormat formatter = new
	 * SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"); String strDate =
	 * formatter.format(date); List<ShipmentTransactions> list =
	 * shiptransrepo.findByPartner_from(shipment.getPartnerFrom()); try { for
	 * (ShipmentTransactions list1 : list) { if
	 * (list1.getShipment_Id().equals(shipment.getShipment_Number())) { if
	 * (list1.getPartner_From().equals(shipment.getPartnerFrom())) { if
	 * (list1.getEvent_Name().equals(shipment.getEventType())) { Query query = new
	 * Query(); query.addCriteria(new Criteria().andOperator(
	 * Criteria.where("Shipment_Id").is(shipment.getShipment_Number()),
	 * Criteria.where("Partner_From").is(shipment.getPartnerFrom()),
	 * Criteria.where("Event_Name").is(shipment.getEventType()))); Update update =
	 * new Update(); update.set("Partner_To", shipment.getReceivingLocation());
	 * update.set("EventReferenceNumber", shipment.getReceivingReferenceNumber());
	 * update.set("TypeOfReference", shipment.getTypeOfReference());
	 * mongoTemplate.updateMulti(query, update, "ShipmentTransactions"); System.out.
	 * println("Completed Shipment with Receiving Refrence Number Updated : " +
	 * list1.getEvent_SNo()); flag = true; }
	 * 
	 * } } } } catch (Exception e) { // TODO: handle exception } // Updating Device
	 * Return Location in Shipments if (flag == true) { Query query = new
	 * Query(Criteria.where("Shipment_Id").is(shipment.getShipment_Number()));
	 * Update update = new Update(); update.set("DeviceReturnLocation",
	 * shipment.getDeviceReturnLocation()); update.set("Delivered_Date", strDate);
	 * update.set("Type_Of_Reference", shipment.getTypeOfReference());
	 * mongoTemplate.updateMulti(query, update, "Shipments"); System.out.
	 * println("Updated Device Return Location in the Shipments Collection"); return
	 * flag = true; } return flag; }
	 */
	/*
	 * @ResponseBody
	 * 
	 * @GetMapping("/getDeviceDataTemp/{Shipment_Id}") public List<Devicedatatemp>
	 * getDeviceDataTemp(@PathVariable(value = "Shipment_Id") String Shipment_Id) {
	 * Shipments shipment = shiprepo.findByShipment_Id(Shipment_Id); String
	 * deliveryNo = shipment.getShipment_Num(); String deviceId =
	 * shipment.getDevice_Id(); String created_date = shipment.getCreated_Date();
	 * List<DeviceDataStream> deviceDataStrem1 = null; List<Devicedatatemp> devlist
	 * = new ArrayList<>(); String Humidity = null;
	 * 
	 * String deviceDataStream1 = ""; try {
	 * 
	 * deviceDataStrem1 = devicedatarepo.findByDevice_id(deviceId);
	 * 
	 * for (DeviceDataStream dv : deviceDataStrem1) { Devicedatatemp temphum = new
	 * Devicedatatemp();
	 * 
	 * if (dv.getMessageType().equals("sensor")) { if
	 * (dv.getSensorUTC().compareTo(created_date) > 0) {
	 * 
	 * temphum.setDevice_Id(dv.getDevice_Id()); temphum.setShipment_Num(deliveryNo);
	 * temphum.setUTC(dv.getSensorUTC()); temphum.setHumidity(dv.getHumidity_1());
	 * temphum.setInternal_Temperature(dv.getInternal_temperature());
	 * 
	 * devlist.add(temphum); }
	 * 
	 * } }
	 * 
	 * } catch (Exception e) { } return devlist; }
	 */
	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/getDeviceDataTemp/{Shipment_Id}")
	public List<Devicedatatemp> getDeviceDataTemp(@PathVariable(value = "Shipment_Id") String Shipment_Id) throws ParseException {
		Shipments shipment = shiprepo.findByShipment_Id(Shipment_Id);
		String deliveryNo = shipment.getShipment_Num();
		String deviceId = shipment.getDevice_Id();
		System.out.println("Deviceidssssss::::" + deviceId);
		String created_date = shipment.getCreated_Date();
		String delivered_date = shipment.getDelivered_Date();
		// List<DeviceDataStream> deviceDataStrem1 = null;
		List<Devicedatatemp> devlist = new ArrayList<>();
		
		List<ShipmentTransactions> listshpmtTransac = shiptransrepo.findByShipment_Id(Shipment_Id);
		String deviceAttachedDate = null;
		for(ShipmentTransactions transac: listshpmtTransac) {
			if(transac.getEvent_Name().equals("Attach Device and Start")) {
				deviceAttachedDate = transac.getEvent_Exec_Date();
			}			
		}
		
		
		// List<DeviceDataStream> response = new ArrayList<>();
		String Humidity = null;
		AggregationResults<DeviceDataStream> results = null;
		String deviceDataStream1 = "";
	//	try {

			SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy-MM-dd");

///			Date date = sdfSource.parse(created_date.split("T")[0]);
///			Date deliveredDate = sdfSource.parse(delivered_date.split("T")[0]);

			// create SimpleDateFormat object with desired date format
			SimpleDateFormat sdfDestination = new SimpleDateFormat("MM/dd/yy");
			// deviceDataStrem1 = devicedatarepo.findByDevice_id(deviceId);
			System.out.println("Device id " + deviceId);
			// AggregationOperation match1 =
			// Aggregation.match(Criteria.where("Device_Id").is(deviceId));
//			AggregationOperation match1 = Aggregation.match(
//					Criteria.where("Device_Id").is(deviceId).andOperator(Criteria.where("Message_Type").is("sensor")).orOperator(Criteria.where("Message_Type").is("GPS MESSAGE")));
//
//			AggregationOperation sort = Aggregation.sort(Sort.Direction.ASC, "current_terminal_date");
//		//	AggregationOperation sort1 = Aggregation.sort(Sort.Direction.ASC, "current_terminal_time");
			
//			AggregationOperation match1 = Aggregation.match(Criteria.where("Device_Id").is(deviceId)
//					.andOperator((Criteria.where("current_terminal_date").gte(sdfDestination.format(date))),
//							(Criteria.where("current_terminal_date").lte(sdfDestination.format(deliveredDate)))));
			
//			AggregationOperation match1 = Aggregation.match(Criteria.where("Device_Id").is(deviceId)
//					.andOperator((Criteria.where("SensorUTC").gte(created_date)),
//							(Criteria.where("SensorUTC").lte(delivered_date))));
			
//			AggregationOperation match1 = Aggregation.match(Criteria.where("Device_Id").is(deviceId)
//					.andOperator((Criteria.where("SensorUTC").gte(deviceAttachedDate)),
//							(Criteria.where("SensorUTC").lte(delivered_date))));
			Criteria dateCriteria;
			
			if(delivered_date != null && !delivered_date.isEmpty()) {				
				System.out.println("Started querying with delivered_date:");
			    dateCriteria = Criteria.where("SensorUTC")
			            .gte(deviceAttachedDate)
			            .lte(delivered_date);				
			}
			else {
//				String estimatedDeliveredDate = shipment.getEstimated_Delivery_Date();				
//				System.out.println("Started querying with estimatedDeliveredDate:");
//				System.out.println(estimatedDeliveredDate);
				System.out.println("Started querying with deviceAttachedDate:");
			    dateCriteria = Criteria.where("SensorUTC")
			            .gte(deviceAttachedDate);
			      //      .lte(estimatedDeliveredDate);	
			}
			
			AggregationOperation match1 = Aggregation.match(Criteria.where("Device_Id").is(deviceId)
					.andOperator(dateCriteria));
			
			// System.out.println("sort " + sort);

			Aggregation aggregation = Aggregation.newAggregation(match1);
			System.out.println("aggregation " + aggregation);
			results = mongoTemplate.aggregate(aggregation, "DeviceDataStream", DeviceDataStream.class);
//System.out.println("results "+results);
			String temAddress = "";
			String maxTempThresh=shipment.getTemp().split("-")[1].trim().replace(" c", "");
			String minTempThresh=shipment.getTemp().split("-")[0];
			for (DeviceDataStream dv : results) {
				Devicedatatemp temphum = new Devicedatatemp();
//System.out.println("in device data "+dv.getCurrent_terminal_date());
//System.out.println("dv.getMessage_Type() "+dv.getMessage_Type());
				if (dv.getMessage_Type().equals("GPS MESSAGE")) {
					temAddress = dv.getAddress();
				//	System.out.println("inside gps");
				} else if (dv.getMessage_Type().equals("sensor")) {
				//	System.out.println("inside sensor");
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
					LocalDate date1 = LocalDate.parse(dv.getCurrent_terminal_date(), formatter);
					String dateTime = date1.toString() + "T" + dv.getCurrent_terminal_time() + ".000Z";
					if (dateTime.compareTo(created_date) > 0) {
						// System.out.println("after sensor utc comp :::::::::::"+dv);
						// System.out.println("Comparison " + dateTime + ":::::::" + created_date);
						temphum.setDevice_Id(dv.getDevice_Id());
						temphum.setShipment_Num(deliveryNo);
						temphum.setUTC(dateTime);
						temphum.setUtcforAlarmzone(dateTime);
						temphum.setHumidity(dv.getHumidity_1());
						temphum.setInternal_Temperature(dv.getFirst_Sensor_temperature());
						temphum.setSensorTemp(dv.getFirst_Sensor_temperature());
						temphum.setMaxTempThresh(maxTempThresh);
						temphum.setMinTempThresh(minTempThresh);
						if (!dv.getAddress().isEmpty()) {
							temAddress=dv.getAddress();
							//temphum.setAddress(temAddress);
						}
						temphum.setAddress(temAddress);
						devlist.add(temphum);
					}
				}

			}

			Collections.sort(devlist, Devicedatatemp.uTCComparator);

			// }
//		}
//
//		catch (Exception e) {
//			System.out.println("exception e "+e);
//		}
		return devlist;
	}
	
	
	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/getDeviceData/{Shipment_Id}")
	public List<Devicedatatemp> getDeviceData(@PathVariable(value = "Shipment_Id") String Shipment_Id)
			throws ParseException {
		
		Shipments shipment = shiprepo.findByShipment_Id(Shipment_Id);
		String deliveryNo = shipment.getShipment_Num();
		String deviceId = shipment.getDevice_Id();
		String numberOfDevices = shipment.getNumberOfDevices();
		
        List<String> deviceIdList = new ArrayList<>();
        // Add new strings to the list
        deviceIdList.add(shipment.getDevice_Id());
        deviceIdList.add(shipment.getSecondDevice());
        deviceIdList.add(shipment.getThirdDevice());
        deviceIdList.add(shipment.getFourthDevice());
        deviceIdList.add(shipment.getFifthDevice());
        // Remove null values from the list
        deviceIdList = deviceIdList.stream()
                               .filter(s -> s != "")  // Filter out nulls
                               .collect(Collectors.toList());  // Collect back into a list
        System.out.println("Devices List after removing the nulls");
        System.out.println(deviceIdList);
        
		System.out.println("Deviceidssssss:::: "+deviceId);
		String created_date = shipment.getCreated_Date();
		String delivered_date = shipment.getDelivered_Date();
		// List<DeviceDataStream> deviceDataStrem1 = null;
		List<Devicedatatemp> devlist = new ArrayList<>();
		
		List<ShipmentTransactions> listshpmtTransac = shiptransrepo.findByShipment_Id(Shipment_Id);
		String deviceAttachedDate = null;
		for(ShipmentTransactions transac: listshpmtTransac) {
			if(transac.getEvent_Name().equals("Attach Device and Start")) {
				deviceAttachedDate = transac.getEvent_Exec_Date();
			}			
		}		
		// List<DeviceDataStream> response = new ArrayList<>();
		String Humidity = null;
		AggregationResults<DeviceDataStream> results = null;
		String deviceDataStream1 = "";
			SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy-MM-dd");

			SimpleDateFormat sdfDestination = new SimpleDateFormat("MM/dd/yy");

			System.out.println("Device id " + deviceId);

			Criteria dateCriteria;
			
			if(delivered_date != null && !delivered_date.isEmpty()) {				
				System.out.println("Started querying with delivered_date:");
			    dateCriteria = Criteria.where("SensorUTC")
			            .gte(deviceAttachedDate)
			            .lte(delivered_date);				
			}
			else {
				System.out.println("Started querying with deviceAttachedDate:");
			    dateCriteria = Criteria.where("SensorUTC")
			            .gte(deviceAttachedDate);
			      //      .lte(estimatedDeliveredDate);	
			}			
			AggregationOperation match1 = Aggregation.match(Criteria.where("Device_Id").is(deviceId)
					.andOperator(dateCriteria));
			Aggregation aggregation = Aggregation.newAggregation(match1);			
			System.out.println("aggregation " + aggregation);
			results = mongoTemplate.aggregate(aggregation, "DeviceDataStream", DeviceDataStream.class);
			
			String temAddress = "";
			String maxTempThresh=shipment.getTemp().split("-")[1].trim().replace(" c", "");
			String minTempThresh=shipment.getTemp().split("-")[0];
			for (DeviceDataStream dv : results) {
				Devicedatatemp temphum = new Devicedatatemp();
				if (dv.getMessage_Type().equals("GPS MESSAGE")) {
					temAddress = dv.getAddress();
				} else if (dv.getMessage_Type().equals("sensor")) {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
					LocalDate date1 = LocalDate.parse(dv.getCurrent_terminal_date(), formatter);
					String dateTime = date1.toString() + "T" + dv.getCurrent_terminal_time() + ".000Z";
					if (dateTime.compareTo(created_date) > 0) {
						temphum.setDevice_Id(dv.getDevice_Id());
						temphum.setShipment_Num(deliveryNo);
						temphum.setUTC(dateTime);
						temphum.setUtcforAlarmzone(dateTime);
						temphum.setHumidity(dv.getHumidity_1());
						temphum.setInternal_Temperature(dv.getFirst_Sensor_temperature());
						temphum.setSensorTemp(dv.getFirst_Sensor_temperature());
						temphum.setMaxTempThresh(maxTempThresh);
						temphum.setMinTempThresh(minTempThresh);
						if (!dv.getAddress().isEmpty()) {
							temAddress=dv.getAddress();
						}
						temphum.setAddress(temAddress);
						devlist.add(temphum);
					}
				}
			}
		Collections.sort(devlist, Devicedatatemp.uTCComparator);
		return devlist;
	}
	
	
	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/getDeviceDataTemperature/{Shipment_Id}")
	public Map<String, List<Devicedatatemp>> getDeviceDataTemperature(@PathVariable(value = "Shipment_Id") String Shipment_Id)
	        throws ParseException {
	    
	    Shipments shipment = shiprepo.findByShipment_Id(Shipment_Id);
	    String deliveryNo = shipment.getShipment_Num();
	    
	    List<String> deviceIdList = new ArrayList<>();
	    deviceIdList.add(shipment.getDevice_Id());
	    deviceIdList.add(shipment.getSecondDevice());
	    deviceIdList.add(shipment.getThirdDevice());
	    deviceIdList.add(shipment.getFourthDevice());
	    deviceIdList.add(shipment.getFifthDevice());
	    
	    // Remove null or empty values
	    deviceIdList = deviceIdList.stream().filter(s -> s != null && !s.isEmpty()).collect(Collectors.toList());
	    System.out.println("deviceIdList");
	    System.out.println(deviceIdList);
	    List<ShipmentTransactions> listshpmtTransac = shiptransrepo.findByShipment_Id(Shipment_Id);
	    String deviceAttachedDate = null;
	    for (ShipmentTransactions transac : listshpmtTransac) {
	        if ("Attach Device and Start".equals(transac.getEvent_Name())) {
	            deviceAttachedDate = transac.getEvent_Exec_Date();
	        }
	    }
	    
	    String delivered_date = shipment.getDelivered_Date();
	    String created_date = shipment.getCreated_Date();
	    String maxTempThresh = shipment.getTemp().split("-")[1].trim().replace(" c", "");
	    String minTempThresh = shipment.getTemp().split("-")[0];
	    
	    Map<String, List<Devicedatatemp>> deviceDataMap = new HashMap<>();
	    
	    for (String deviceId : deviceIdList) {
	        List<Devicedatatemp> devlist = new ArrayList<>();
	        Criteria dateCriteria;
	        
	        if (delivered_date != null && !delivered_date.isEmpty()) {
	            dateCriteria = Criteria.where("SensorUTC").gte(deviceAttachedDate).lte(delivered_date);
	        } else {
	            dateCriteria = Criteria.where("SensorUTC").gte(deviceAttachedDate);
	        }
	        
	        Aggregation aggregation = Aggregation.newAggregation(
	            Aggregation.match(Criteria.where("Device_Id").is(deviceId).andOperator(dateCriteria))
	        );
	        
	        AggregationResults<DeviceDataStream> results = mongoTemplate.aggregate(aggregation, "DeviceDataStream", DeviceDataStream.class);
	        System.out.println(results);
	        String temAddress = "";
	        for (DeviceDataStream dv : results) {
	            Devicedatatemp temphum = new Devicedatatemp();
	            if ("GPS MESSAGE".equals(dv.getMessage_Type())) {
	                temAddress = dv.getAddress();
	            } else if ("sensor".equals(dv.getMessage_Type())) {
	                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
	                LocalDate date1 = LocalDate.parse(dv.getCurrent_terminal_date(), formatter);
	                String dateTime = date1.toString() + "T" + dv.getCurrent_terminal_time() + ".000Z";
	                if (dateTime.compareTo(created_date) > 0) {
	                    temphum.setDevice_Id(dv.getDevice_Id());
	                    temphum.setShipment_Num(deliveryNo);
	                    temphum.setUTC(dateTime);
	                    temphum.setUtcforAlarmzone(dateTime);
	                    temphum.setHumidity(dv.getHumidity_1());
	                    temphum.setInternal_Temperature(dv.getFirst_Sensor_temperature());
	                    temphum.setSensorTemp(dv.getFirst_Sensor_temperature());
	                    temphum.setMaxTempThresh(maxTempThresh);
	                    temphum.setMinTempThresh(minTempThresh);
	                    if (!dv.getAddress().isEmpty()) {
	                        temAddress = dv.getAddress();
	                    }
	                    temphum.setAddress(temAddress);
	                    devlist.add(temphum);
	                }
	            }
	        }
	        
	        Collections.sort(devlist, Devicedatatemp.uTCComparator);
	        deviceDataMap.put(deviceId, devlist);
	    }
	    
	    return deviceDataMap;
	}
	
	
	/*
	 * @ResponseBody
	 * 
	 * @PostMapping("/completeNewShipment") public Response
	 * completeNewShipment(@RequestBody CompleteShipment shipment) { Response resp =
	 * new Response(); // resp.setStatus(false);
	 * 
	 * boolean flag = false; Date date = new Date(); SimpleDateFormat formatter =
	 * new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"); String strDate =
	 * formatter.format(date); List<ShipmentTransactions> list =
	 * shiptransrepo.findByPartner_from(shipment.getPartnerFrom()); try { for
	 * (ShipmentTransactions list1 : list) { if
	 * (list1.getShipment_Id().equals(shipment.getShipment_Number())) { if
	 * (list1.getPartner_From().equals(shipment.getPartnerFrom())) { if
	 * (list1.getEvent_Name().equals(shipment.getEventType())) { Query query = new
	 * Query(); query.addCriteria(new Criteria().andOperator(
	 * Criteria.where("Shipment_Id").is(shipment.getShipment_Number()),
	 * Criteria.where("Partner_From").is(shipment.getPartnerFrom()),
	 * Criteria.where("Event_Name").is(shipment.getEventType()))); Update update =
	 * new Update(); update.set("Partner_To", shipment.getReceivingLocation());
	 * update.set("EventReferenceNumber", shipment.getReceivingReferenceNumber());
	 * update.set("TypeOfReference", shipment.getTypeOfReference());
	 * update.set("Event_Status","Completed"); mongoTemplate.updateMulti(query,
	 * update, "ShipmentTransactions"); System.out.
	 * println("Completed Shipment with Receiving Refrence Number Updated : " +
	 * list1.getEvent_SNo()); flag = true; // resp.setStatus(true); }
	 * 
	 * } } } } catch (Exception e) { // TODO: handle exception } // Updating Device
	 * Return Location in Shipments if (flag == true) { Query query = new
	 * Query(Criteria.where("Shipment_Id").is(shipment.getShipment_Number()));
	 * Update update = new Update(); update.set("DeviceReturnLocation",
	 * shipment.getDeviceReturnLocation()); update.set("Delivered_Date", strDate);
	 * update.set("Type_Of_Reference", shipment.getTypeOfReference());
	 * update.set("Shipment_Status", "Delivered");
	 * 
	 * mongoTemplate.updateMulti(query, update, "Shipments"); System.out.
	 * println("Updated Device Return Location in the Shipments Collection");
	 * resp.setMessage("Completed the Shipment successfully"); return resp; }
	 * resp.setMessage("Shipment Not yet Completed"); return resp; }
	 */
	/*
	 * @ResponseBody
	 * 
	 * @PostMapping("/completeNewShipment") public Response
	 * completeNewShipment(@RequestBody CompleteShipment shipment) { Response resp =
	 * new Response(); //resp.setStatus(false);
	 * 
	 * boolean flag = false; Date date = new Date(); SimpleDateFormat formatter =
	 * new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"); String strDate =
	 * formatter.format(date); List<ShipmentTransactions> list =
	 * shiptransrepo.findByPartner_from(shipment.getPartnerFrom()); try { for
	 * (ShipmentTransactions list1 : list) { if
	 * (list1.getShipment_Id().equals(shipment.getShipment_Number())) { if
	 * (list1.getPartner_From().equals(shipment.getPartnerFrom())) { if
	 * (list1.getEvent_Name().equals(shipment.getEventType())) { Query query = new
	 * Query(); query.addCriteria(new Criteria().andOperator(
	 * Criteria.where("Shipment_Id").is(shipment.getShipment_Number()),
	 * Criteria.where("Partner_From").is(shipment.getPartnerFrom()),
	 * Criteria.where("Event_Name").is(shipment.getEventType()))); Update update =
	 * new Update(); update.set("Partner_To", shipment.getReceivingLocation());
	 * update.set("EventReferenceNumber", shipment.getReceivingReferenceNumber());
	 * update.set("TypeOfReference", shipment.getTypeOfReference());
	 * update.set("Event_Status", "Completed"); mongoTemplate.updateMulti(query,
	 * update, "ShipmentTransactions"); System.out.
	 * println("Completed Shipment with Receiving Refrence Number Updated : " +
	 * list1.getEvent_SNo());
	 * 
	 * flag = true; //resp.setStatus(true); }
	 * 
	 * } } } } catch (Exception e) { // TODO: handle exception } // Updating Device
	 * Return Location in Shipments if (flag == true) { Query query = new
	 * Query(Criteria.where("Shipment_Id").is(shipment.getShipment_Number()));
	 * Update update = new Update(); update.set("DeviceReturnLocation",
	 * shipment.getDeviceReturnLocation()); update.set("Delivered_Date", strDate);
	 * update.set("Type_Of_Reference", shipment.getTypeOfReference());
	 * update.set("Shipment_Status", "Delivered"); mongoTemplate.updateMulti(query,
	 * update, "Shipments"); System.out.
	 * println("Updated Device Return Location in the Shipments Collection");
	 * resp.setMessage("Completed the Shipment successfully"); resp.setStatus(true);
	 * return resp; } resp.setMessage("Shipment Not yet Completed");
	 * resp.setStatus(false); return resp; }
	 */
    private static double calculatePercentage(int dataPoints, int percentage) {
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("Percentage must be between 0 and 100.");
        }

        return (percentage / 100.0) * dataPoints;
    }
    
    
	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@PostMapping("/completeNewShipment")
	public Response completeNewShipment(@Validated @RequestBody CompleteShipment shipment)
			throws ParseException, FileNotFoundException, IOException, DocumentException {
		Response resp = new Response();
		// resp.setStatus(false);

		boolean flag = false;
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		String strDate = formatter.format(date);
		
//		String countOfDevices = "";		
//		List<Shipments> shipmentsList = shiprepo.findByShipment_Num(shipment.getShipmentNum());
//		for(Shipments shipments: shipmentsList) {
//			countOfDevices = shipments.getNumberOfDevices();			
//		}
				
		List<Map<String, String>> graphUrls = shipment.getGraphUrl();
//		graphUrls.forEach(pair -> pair.forEach((key, value) -> 
//        System.out.println("Key: " + key + ", Value: " + value)
//        ));
	try {
		for(Map<String, String> urlsList : graphUrls) {
			
			CompleteShipmentTrigger completeTrigger = new CompleteShipmentTrigger();
			completeTrigger.setShipmentId(shipment.getShipment_Number());	// Shipment_Number is Shipment_Id
			completeTrigger.setDeliveryNumber(shipment.getShipmentNum());	// ShipmentNum is Delivery number
			completeTrigger.setShipmentCompletionDate(strDate);
			completeTrigger.setInvoiceNumber(shipment.getInvoiceNumber());			
			completeTrigger.setTimeZone("UTC");
			completeTrigger.setStatus("");
			completeTrigger.setDependencyStatus("");
			completeTrigger.setProcessedDateTime("");
			//completeTrigger.setDeviceId(shipment.getConenctedDevice());
			//completeTrigger.setGraphImage(shipment.getGraphImage());
			completeTrigger.setDeviceId(urlsList.get("device_id"));
			completeTrigger.setGraphImage(urlsList.get("graph_url"));
			completeTrigger.setPartner(shipment.getPartner());
			completeTrigger.setPartnerFrom(shipment.getPartnerFrom());
			completeShipTriggerRepo.insert(completeTrigger);			
		}
	}
		catch(Exception e) {
			System.out.println("Exception occured while creating and inserting object into trigger collection");
			e.printStackTrace();			
	}
				
//		CompleteShipmentTrigger completeTrigger = new CompleteShipmentTrigger();
//		completeTrigger.setShipmentId(shipment.getShipment_Number());	// Shipment_Number is Shipment_Id
//		completeTrigger.setDeliveryNumber(shipment.getShipmentNum());	// ShipmentNum is Delivery number
//		completeTrigger.setShipmentCompletionDate(strDate);
//		completeTrigger.setTimeZone("UTC");
//		completeTrigger.setStatus("");
//		completeTrigger.setProcessedDateTime("");
//		completeTrigger.setDeviceId(shipment.getConenctedDevice());
//		completeTrigger.setPartner(shipment.getPartner());
//		completeTrigger.setPartnerFrom(shipment.getPartnerFrom());
//		completeTrigger.setGraphImage(shipment.getGraphImage());
//		completeShipTriggerRepo.insert(completeTrigger);
//		mongoTemplate.save(completeTrigger);
		
		System.out.println("strDate ----- shipment completion date");
		System.out.println(strDate);
				
//		 Document doc = Jsoup.connect("http://ab6afe8280bcd42c599dd9495567aa0e-1267532222.us-east-1.elb.amazonaws.com:8081/Dashboard.jsp").get();
//		 Elements imgTags = doc.getElementsByTag("img");
						
		List<ShipmentTransactions> list = shiptransrepo.findByPartner_from(shipment.getPartnerFrom());
			        
//        if(sizeofDevicedata >= percentageValue){ 				
		//System.out.println(list);
//          System.out.println("::::: Check confirmed on Data points, started Completing shipment :::::");
		try {
			for (ShipmentTransactions list1 : list) {
				if (list1.getShipment_Id().equals(shipment.getShipment_Number())) {
					System.out.println(shipment.getShipment_Number());
					if (list1.getPartner().equals(shipment.getPartner())) {
						if (list1.getEvent_Name().equals(shipment.getEvent())) {
							Query query = new Query();
							query.addCriteria(new Criteria().andOperator(
									Criteria.where("Shipment_Id").is(shipment.getShipment_Number()),
									Criteria.where("Partner").is(shipment.getPartner()),
									Criteria.where("Event_Name").is(shipment.getEvent())));
							Update update = new Update();
							update.set("Partner_To", shipment.getReceivingLocation());
							update.set("EventReferenceNumber", shipment.getReceivingReferenceNumber());
							update.set("TypeOfReference", shipment.getTypeOfReference());
							update.set("Event_Exec_Date", strDate);
							update.set("Evidence", shipment.getEvidence());
							update.set("EvidenceList", shipment.getEvidenceList());
							
//							update.set("Partner", shipment.getPartner());
//							update.set("Partner_From", shipment.getPartnerFrom());
							/*
							 * System.out.println("comments in transactions"+list1.getComments()[0]);
							 * System.out.println("comments in transactions"+list1.getComments()[1]);
							 */
							String[] cmnt = list1.getComments();
							List<String> cmnts = new ArrayList<String>();						
							for (int i = 0; i < cmnt.length; i++) {	 
								if (i < cmnt.length) {
									 cmnts.add(cmnt[i]);
								}								
							}
		//					System.out.println("the new comments are added"+   String.valueOf(shipment.getComments()));
							if(String.valueOf(shipment.getComments()).length() > 2 ) {
		//						System.out.println("Checking String Length "+String.valueOf(shipment.getComments()).length());
								cmnts.addAll(shipment.getComments());
							}
							update.set("Comments", cmnts);
			//				System.out.println("this is out of loop " +cmnts);														
							update.set("Event_Status", "Completed");
							mongoTemplate.updateMulti(query, update, "ShipmentTransactions");// to be
							// removed
							System.out.println("Completed Shipment with Receiving Refrence Number Updated : "
									+ list1.getEvent_SNo());

							flag = true;
							// resp.setStatus(true);
						}

					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// Updating Device Return Location in Shipments
		if (flag == true) {
			Shipments shipfrom = shiprepo.findByShipment_Id(shipment.getShipment_Number());
			Query query = new Query(Criteria.where("Shipment_Id").is(shipment.getShipment_Number()));
			Update update = new Update();
			update.set("DeviceReturnLocation", shipment.getDeviceReturnLocation());
			update.set("Delivered_Date", strDate);
			update.set("Type_Of_Reference", shipment.getTypeOfReference());
			update.set("Shipment_Status", "Delivered");

			mongoTemplate.updateMulti(query, update, "Shipments");// to be removed
		//	cec.tempDataCalculator(shipfrom, strDate);
			//below method calls pdf email
	//		cec.tempDataCalculator(shipfrom, strDate,shipment.getGraphImage());
//			String pathFile="C:\\SMAASMailAttachments\\ShipmentImages\\"+shipment.getShipment_Number()+".png";
//			String base64url=shipment.getGraphImage().substring(shipment.getGraphImage().indexOf(",")+1);
					
			Query query1 = new Query(Criteria.where("Device_Id").is(shipment.getConenctedDevice()));

			Update update1 = new Update();

			System.out.println("ghfvjgh" + shipment.getDeviceReturnLocation());
			if (shipment.getDeviceReturnLocation().equals("Same Location")) {
				System.out.println(shipment.getDeviceReturnLocation());
				update1.set("Device_Location", shipfrom.getRoute_To());
				update1.set("DeviceStatusReferred", "Available");
				mongoTemplate.updateMulti(query1, update1, "Devices");// to be removed

				System.out.println("Updated Device Return Location in the Shipments Collection");
				resp.setMessage("Completed the Shipment successfully");
				resp.setStatus(true);
				return resp;
			} else {
				update1.set("DeviceStatusReferred", "Detached");
				mongoTemplate.updateMulti(query1, update1, "Devices"); // to be removed
				System.out.println("Updated Device Return Location in the Shipments Collection");
				resp.setMessage("Completed the Shipment successfully");
				resp.setStatus(true);
				return resp;
			}
		}
//	}
		resp.setMessage("Shipment Not yet Completed");
		resp.setStatus(false);
		return resp;
	}

//************* Refer to below commented completeNewShipment which was used earlier **************//	
	

//	@ResponseBody
//	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
//	@PostMapping("/completeNewShipment")
//	public Response completeNewShipment(@Validated @RequestBody CompleteShipment shipment)
//			throws ParseException, FileNotFoundException, IOException, DocumentException {
//		Response resp = new Response();
//		// resp.setStatus(false);
//
//		boolean flag = false;
//		Date date = new Date();
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
//		String strDate = formatter.format(date);
////		CompleteShipmentTrigger com = new CompleteShipmentTrigger();
////		com.setShipmentId(shipment.getShipment_Number());
//		System.out.println("strDate ----- shipment completion date");
//		System.out.println(strDate);
//		List<ShipmentTransactions> list = shiptransrepo.findByPartner_from(shipment.getPartnerFrom());
//		
//		List<ShipmentTransactions> transacList = shiptransrepo.findByShipment_Id(shipment.getShipment_Number());
//		String deviceAttachedDate = null;
//		String mode = null;
//		String[] comments = null;
//		for(ShipmentTransactions transac: transacList) {
//			if(transac.getEvent_Name().equals("Attach Device and Start")) {
//				deviceAttachedDate = transac.getEvent_Exec_Date();
//				mode = transac.getMode_of_Transport();
//				comments = transac.getComments();
//			}			
//		}
//		System.out.println(" deviceAttachedDate ");
//		System.out.println(deviceAttachedDate);
//		System.out.println(" mode ");
//		System.out.println(mode);
//		System.out.println(" comments ");
//		System.out.println(comments);
//		
//		AggregationResults<DeviceDataStream> results = null;
//		
//		AggregationOperation matchPipeline = Aggregation.match(Criteria.where("Device_Id").is(shipment.getConenctedDevice())
//				.orOperator((Criteria.where("Message_Type").is("sensor")),
//						Criteria.where("Message_Type").is("GPS MESSAGE"))
//				.andOperator(Criteria.where("SensorUTC")
//			            .gte(deviceAttachedDate)
//			            .lte(strDate)));
//		
//		Aggregation aggregation = Aggregation.newAggregation(matchPipeline);
//		System.out.println("aggregation " + aggregation);
//		results = mongoTemplate.aggregate(aggregation, "DeviceDataStream", DeviceDataStream.class);
//	
//      //System.out.println("results "+results);
//		List<DeviceDataStream> resultList = results.getMappedResults();
//		int sizeofDevicedata = resultList.size();
//		System.out.println("Size of results: " + sizeofDevicedata);
//		
//		
//        DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE_TIME;
//        LocalDateTime deviceStartdate = LocalDateTime.parse(deviceAttachedDate, dateFormatter);
//        LocalDateTime shpEnddate = LocalDateTime.parse(strDate, dateFormatter);
//
//        long minutesDifference = Duration.between(deviceStartdate, shpEnddate).toMinutes();
//        int dataPoints = (int) Math.round((double) minutesDifference / 10);
//
//        System.out.println("Total number of data points: " + dataPoints);
//        
//        double percentageValue = calculatePercentage(dataPoints, 100);
//        System.out.println("100% of " + dataPoints + " is: " + percentageValue);
//        
////        if(sizeofDevicedata >= percentageValue){ 				
//		//System.out.println(list);
////          System.out.println("::::: Check confirmed on Data points, started Completing shipment :::::");
//		try {
//			for (ShipmentTransactions list1 : list) {
//				if (list1.getShipment_Id().equals(shipment.getShipment_Number())) {
//					System.out.println(shipment.getShipment_Number());
//					if (list1.getPartner().equals(shipment.getPartner())) {
//						if (list1.getEvent_Name().equals(shipment.getEvent())) {
//							Query query = new Query();
//							query.addCriteria(new Criteria().andOperator(
//									Criteria.where("Shipment_Id").is(shipment.getShipment_Number()),
//									Criteria.where("Partner").is(shipment.getPartner()),
//									Criteria.where("Event_Name").is(shipment.getEvent())));
//							Update update = new Update();
//							update.set("Partner_To", shipment.getReceivingLocation());
//							update.set("EventReferenceNumber", shipment.getReceivingReferenceNumber());
//							update.set("TypeOfReference", shipment.getTypeOfReference());
//							update.set("Event_Exec_Date", strDate);
//							update.set("Evidence", shipment.getEvidence());
//							update.set("EvidenceList", shipment.getEvidenceList());
//							
////							update.set("Partner", shipment.getPartner());
////							update.set("Partner_From", shipment.getPartnerFrom());
//							/*
//							 * System.out.println("comments in transactions"+list1.getComments()[0]);
//							 * System.out.println("comments in transactions"+list1.getComments()[1]);
//							 */
//							String[] cmnt = list1.getComments();
//							List<String> cmnts = new ArrayList<String>();						
//							for (int i = 0; i < cmnt.length; i++) {	 
//								if (i < cmnt.length) {
//									 cmnts.add(cmnt[i]);
//								}								
//							}
//		//					System.out.println("the new comments are added"+   String.valueOf(shipment.getComments()));
//							if(String.valueOf(shipment.getComments()).length() > 2 ) {
//		//						System.out.println("Checking String Length "+String.valueOf(shipment.getComments()).length());
//								cmnts.addAll(shipment.getComments());
//							}
//							update.set("Comments", cmnts);
//			//				System.out.println("this is out of loop " +cmnts);														
//							update.set("Event_Status", "Completed");
//							mongoTemplate.updateMulti(query, update, "ShipmentTransactions");// to be
//							// removed
//							System.out.println("Completed Shipment with Receiving Refrence Number Updated : "
//									+ list1.getEvent_SNo());
//
//							flag = true;
//							// resp.setStatus(true);
//						}
//
//					}
//				}
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		// Updating Device Return Location in Shipments
//		if (flag == true) {
//			Shipments shipfrom = shiprepo.findByShipment_Id(shipment.getShipment_Number());
//			Query query = new Query(Criteria.where("Shipment_Id").is(shipment.getShipment_Number()));
//			Update update = new Update();
//			update.set("DeviceReturnLocation", shipment.getDeviceReturnLocation());
//			update.set("Delivered_Date", strDate);
//			update.set("Type_Of_Reference", shipment.getTypeOfReference());
//			update.set("Shipment_Status", "Delivered");
//
//			mongoTemplate.updateMulti(query, update, "Shipments");// to be removed
//		//	cec.tempDataCalculator(shipfrom, strDate);
//			//below method calls pdf email
//	//		cec.tempDataCalculator(shipfrom, strDate,shipment.getGraphImage());
////			String pathFile="C:\\SMAASMailAttachments\\ShipmentImages\\"+shipment.getShipment_Number()+".png";
////			String base64url=shipment.getGraphImage().substring(shipment.getGraphImage().indexOf(",")+1);
//
//			try  {
//				
//				//below method calls pdf email
//				cec.tempDataCalculator(shipfrom, strDate,shipment.getGraphImage(), deviceAttachedDate);
//			} 
//			catch (Exception e) {
//				
//				System.out.println("error in tempdatacalculator " + e);
//			}
//
//			/********    below method triggers AWS Lambda Function for Outbound XML (data summary) file      *******/
//			
//		    final String AWS_ACCESS_KEY_ID = "AKIA23OBXAWH6GWFGYHN";
//		    final String AWS_SECRET_ACCESS_KEY = "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";
//
//		    AWSCredentials credentials = new BasicAWSCredentials(AWS_ACCESS_KEY_ID, AWS_SECRET_ACCESS_KEY);
//
//		    // ARN
//		    String functionName = "arn:aws:lambda:us-east-2:746119955855:function:SCMXpert-OutboundFiles-Test";
//
//		    //This will convert object to JSON String
////		    String inputJSON = new Gson().toJson(userActivity);
//		    String inputJSON = "{\r\n"
//		    		+ "  \"body\": {\r\n"
//		    		+ "    \"internalShipmentNum\": \""+ shipment.getShipment_Number() +"\"\r\n"
//		    		+ "  }\r\n"
//		    		+ "}";
//		    /*******    for static internal Shipment Number use the below string   ********/
////		    String inputJSON = "{\r\n"
////		    		+ "  \"body\": {\r\n"
////		    		+ "    \"internalShipmentNum\": \"T00000003\"\r\n"
////		    		+ "  }\r\n"
////		    		+ "}";
//
////		    String[] inputJSON = {"\"internalShipmentNum\": \"" + shipment.getShipment_Number() + "\",\r\n"
////		    		};			    
//		    InvokeRequest lmbRequest = new InvokeRequest()
//		            .withFunctionName(functionName)
//		            .withPayload(inputJSON);
//		    
//		    lmbRequest.setInvocationType(InvocationType.RequestResponse);
//		    
//		    InvokeResult lmbResult = null;
//            try {
//            	
//		          AWSLambda lambda = AWSLambdaClientBuilder.standard()
//		            .withRegion(Regions.US_EAST_2)
//		            .withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
//            //		.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:3001/","us-east-2"))
//            //		.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("https://hgqefpcazjcktbwc6yehvshmvi0dxogj.lambda-url.us-east-2.on.aws/","us-east-2"))
//		        
//		          lmbResult = lambda.invoke(lmbRequest);
//
//		          String resultJSON = new String(lmbResult.getPayload().array(), Charset.forName("UTF-8"));
//		          //write out the return value
//		          System.out.println("this is aws Lambda result" + resultJSON);
//              }
//				catch (ServiceException e) {
//					
//					System.out.println("eror in Lambda Method " + e);
//					e.printStackTrace();
//				}
//            
//				System.out.println("Lambda Method Status Code " + lmbResult.getStatusCode());
//				
//       /***********     Lambda Request and Result/Response ends here      *************/
//					
//			Query query1 = new Query(Criteria.where("Device_Id").is(shipment.getConenctedDevice()));
//
//			Update update1 = new Update();
//
//			System.out.println("ghfvjgh" + shipment.getDeviceReturnLocation());
//			if (shipment.getDeviceReturnLocation().equals("Same Location")) {
//				System.out.println(shipment.getDeviceReturnLocation());
//				update1.set("Device_Location", shipfrom.getRoute_To());
//				update1.set("DeviceStatusReferred", "Available");
//				mongoTemplate.updateMulti(query1, update1, "Devices");// to be removed
//
//				System.out.println("Updated Device Return Location in the Shipments Collection");
//				resp.setMessage("Completed the Shipment successfully");
//				resp.setStatus(true);
//				return resp;
//			} else {
//				update1.set("DeviceStatusReferred", "Detached");
//				mongoTemplate.updateMulti(query1, update1, "Devices"); // to be removed
//				System.out.println("Updated Device Return Location in the Shipments Collection");
//				resp.setMessage("Completed the Shipment successfully");
//				resp.setStatus(true);
//				return resp;
//			}
//		}
////	}
//       if(sizeofDevicedata >= percentageValue){
//    	System.out.println("::::: Check confirmed on Data points :::::");
//		System.out.println("::::: Mismatch in Data points so started creating Raise Exception event :::::");
//		
//		UpdateNewPlusEventDto UpdateNewPlusEventDto = new UpdateNewPlusEventDto();
//		UpdateNewPlusEventDto.setCustomerId("S004");
//		UpdateNewPlusEventDto.setShipment_Number(shipment.getShipment_Number());
//		UpdateNewPlusEventDto.setShipment_Num(shipment.getShipmentNum());
//		UpdateNewPlusEventDto.setDeviceId(shipment.getConenctedDevice());
//		UpdateNewPlusEventDto.setMode(mode);
//		UpdateNewPlusEventDto.setParnterFrom(shipment.getPartnerFrom());
//		UpdateNewPlusEventDto.setEvent_Id("E0045");
//		UpdateNewPlusEventDto.setPartnerTo("");
//		UpdateNewPlusEventDto.setEventName("Raise Exception");
//		UpdateNewPlusEventDto.setComments(comments);
//		UpdateNewPlusEventDto.setPartner(shipment.getPartner());
//				
//		boolean exceptionEvent = addUpdateNewEvent(UpdateNewPlusEventDto);
//		System.out.println(" flag from addUpdateNewEvent ");
//		System.out.println(exceptionEvent);
//			
//		resp.setMessage("Shipment not completed due to mismatch in Data points so Created Raise Exception Event");
//		resp.setStatus(false);
//		return resp;
//	}
//		resp.setMessage("Shipment Not yet Completed");
//		resp.setStatus(false);
//		return resp;
//	}
	
	
	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/getCompleteCalculations/{Shipment_Id}")
	public Calculations CompleteCalculations(@PathVariable(value = "Shipment_Id") String Shipment_Id) throws ParseException
	{
		Response resp = new Response();
		
		Calculations cal = new Calculations();
		Shipments shipment = shiprepo.findByShipment_Id(Shipment_Id);
				
		try {

			if (shipment == null) {
				// Handle case where shipment is not found
				return null;
			}
		cal.setCustomerName(shipment.getPartner());
		cal.setPartnerName("SCMXpert");
		cal.setShipmentNo(shipment.getShipment_Num());
		cal.setShipmentDesc((shipment.getComments() == null
				? shipment.getGoods_Desc() + ", " + shipment.getRoute_From() + "-" + shipment.getRoute_To()
				: shipment.getComments()[0]));
		cal.setDeviceNo(shipment.getDevice_Id());
		cal.setRouteDetails(shipment.getRoute_From() + "-" + shipment.getRoute_To() + ", " + shipment.getMode());
		
		cal.setCmo_Ref_Number(shipment.getCmo_Ref_Number());
			
//		String poNumberString = shipment.getPo_Number();
//		String poNumber = poNumberString.substring(0, 10);
//		String poItemNumber = poNumberString.substring(10);
		
///		cal.setPo_Number(poNumber);
///		cal.setPoItmNumber(poItemNumber);
		
		cal.setPo_Number(shipment.getPo_Number());
		cal.setPoItmNumber(shipment.getPoItmNumber());
		
		cal.setNdc_Number(shipment.getNdc_Number()); 
		cal.setBatch_Id(shipment.getBatch_Id()); 
		cal.setSerial_Number_of_goods(shipment.getSerial_Number_of_goods()); 
		cal.setShipment_Id(shipment.getShipment_Id());
		
		cal.setDelivery_Number(shipment.getInvoice_Number()); 
		cal.setContainer_Number(shipment.getShipper_Number());
		
		cal.setRoute_Id(shipment.getCustRouteId()); 
		cal.setGoods_Id(shipment.getCustGoodId()); 
		cal.setGoods_Type(shipment.getGoods_Desc());
		
		//cal.setStorage_Condition(shipment.getGoods_Desc());
		cal.setStorage_Condition(shipment.getCustGoodId());
		
		cal.setMandt("730");	//For test server
//		cal.setMandt("999");	//For Production 
		cal.setMandt_number("200"); 
		//cal.setDocComplete("Y"); 
		//cal.setzException("N");
	
		try {
		// Remove leading zeros using regular expression
		String materialNo = shipment.getMaterial_number();
        String materialResult = materialNo.replaceFirst("^0+(?!$)", "");
		cal.setMaterial_number(materialResult);
		}
		catch(Exception e) {
			System.out.println("Exception in setting Material_number in getCompleteCalculations API");
			e.printStackTrace();
		}

	try {			
		List<ShipmentTransactions> transactions = shiptransrepo.findByShipment_Id(Shipment_Id);
		System.out.println(":::::: transactions list ::::::::" );
		System.out.println(transactions);

		for(ShipmentTransactions transac : transactions) {			
		  if(transac.getEvent_Name().equals("Upload Documents") && 
				  transac.getEvent_Status().equals("Completed")) {
			  System.out.println("event as yes for docs" );
			  cal.setDocComplete("Y");		
		   }
		  if(transac.getEvent_Name().equals("Upload Documents") && 
				  !transac.getEvent_Status().equals("Completed")) {
			  cal.setDocComplete("N");		
		   }
//		  if(transac.getEvent_Name().equals("Raise Exception") && 
//				  transac.getEvent_Status().equals("Completed")) {
//			  cal.setzException("Y"); 
//		   }
//		  if((transac.getEvent_Name().equals("Raise Exception") && 
//					  !transac.getEvent_Status().equals("Completed"))
//				  || !transac.getEvent_Name().contains("Raise Exception")) {
//			  cal.setzException("N"); 
//		    }
		  if(transac.getEvent_Name().equals("Raise Exception") && 
				  transac.getEvent_Status().equals("Queued")) {
			  System.out.println("Inside Raise Exe as Y");
			  cal.setzException("Y"); 
		   }
		  if((transac.getEvent_Name().equals("Raise Exception") && 
					  transac.getEvent_Status().equals("Completed"))
				  || !transac.getEvent_Name().contains("Raise Exception")) {
			  System.out.println("Inside Raise Exe as N");
			  cal.setzException("N"); 
		    }
		  if(transac.getEvent_Name().equals("FDA may proceed") && 
				  transac.getEvent_Status().equals("Completed")) {
			  cal.setFda("Y");			  
		   }
		   if(transac.getEvent_Name().equals("FDA may proceed") && 
					  !transac.getEvent_Status().equals("Completed")) {	
			  cal.setFda("N");			  
			}
	   }
	}
		catch(Exception e) {
			System.out.println("Exception in setting status of Docs/FDA/RaiseException for Outbound ");
            e.printStackTrace();        
		}
	
		//cal.setTempUnitsofmeasure("\u2103");
		cal.setTempUnitsofmeasure("C");

		String dateString = shipment.getEstimated_Delivery_Date(); 
		SimpleDateFormat sdf_expected = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"); 	
		Date date_expected = sdf_expected.parse(dateString); 	
		sdf_expected = new SimpleDateFormat("yyyy-MM-dd"); 
		dateString = sdf_expected.format(date_expected); 
		
		cal.setExpected_Delivery_Date(dateString);
					
		String deviceId = shipment.getDevice_Id();
		String created_date = shipment.getCreated_Date();
				
		String deliveredDate = shipment.getDelivered_Date();		
		SimpleDateFormat dateFormatterr = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"); 	
		Date shpEndDate = dateFormatterr.parse(deliveredDate); 	
		dateFormatterr = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"); 
		deliveredDate = dateFormatterr.format(shpEndDate); 
				
		System.out.println(" created_date ");
		System.out.println(created_date);
					
		List<ShipmentTransactions> transacList = shiptransrepo.findByShipment_Id(Shipment_Id);
		String deviceAttachedDate = null;
		for(ShipmentTransactions transac: transacList) {
			if(transac.getEvent_Name().equals("Attach Device and Start")) {
				deviceAttachedDate = transac.getEvent_Exec_Date();
			}			
		}
		System.out.println(" deviceAttachedDate ");
		System.out.println(deviceAttachedDate);
		
		String shipmentDeliveredDate = shipment.getDelivered_Date();
		
		System.out.println(" delivered_date ");
		System.out.println(shipmentDeliveredDate);
		

		
		Date datec = new Date();
		SimpleDateFormat formatterc = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		String strDate = formatterc.format(datec);		
		String endDate = strDate;
			
//		String endDate = "2022-06-08T15:55:39.000Z";
// End Date should be within 365 days (because calculation in the method findDifference is written for 365 days) otherwise 
//the calculations would be wrong
		
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
		
//		AggregationOperation match1 = Aggregation.match(Criteria.where("Device_Id").is(deviceId)
//				.orOperator((Criteria.where("Message_Type").is("sensor")),
//						Criteria.where("Message_Type").is("GPS MESSAGE"))
//				.andOperator((Criteria.where("SensorUTC").gte(deviceAttachedDate)),
//						(Criteria.where("SensorUTC").lte(shipmentDeliveredDate))));
		
		Criteria dateCriteria;
		
		if(shipmentDeliveredDate != null && !shipmentDeliveredDate.isEmpty()) {
			System.out.println("Started querying with shipmentDeliveredDate:");
		    dateCriteria = Criteria.where("SensorUTC")
		            .gte(deviceAttachedDate)
		            .lte(shipmentDeliveredDate);
		}
		else {			
//			String estimatedDeliveredDate = shipment.getEstimated_Delivery_Date();			
//			System.out.println("Started querying with estimatedDeliveredDate:");
//			System.out.println(estimatedDeliveredDate);			
		    dateCriteria = Criteria.where("SensorUTC")
		            .gte(deviceAttachedDate);
		      //      .lte(estimatedDeliveredDate);
		}
		AggregationOperation match1 = Aggregation.match(Criteria.where("Device_Id").is(deviceId)
				.orOperator((Criteria.where("Message_Type").is("sensor")),
						Criteria.where("Message_Type").is("GPS MESSAGE"))
				.andOperator(dateCriteria));
		

		Aggregation aggregation = Aggregation.newAggregation(match1);
		System.out.println("aggregation " + aggregation);
		results = mongoTemplate.aggregate(aggregation, "DeviceDataStream", DeviceDataStream.class);
	
      //System.out.println("results "+results);
		
		List<DeviceDataStream> resultList = results.getMappedResults();
		int size = resultList.size();
		System.out.println("Size of results: " + size);
		
//        String startDateStr = "2024-01-25T12:12:36.775Z";
//        String endDateStr = "2024-02-02T18:08:33.592Z";

        DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime devicestartDate = LocalDateTime.parse(deviceAttachedDate, dateFormatter);
        LocalDateTime shpendDate = LocalDateTime.parse(shipmentDeliveredDate, dateFormatter);

        long minutesDifference = Duration.between(devicestartDate, shpendDate).toMinutes();
        int dataPoints = (int) Math.round((double) minutesDifference / 10);

        System.out.println("Total number of data points: " + dataPoints);
        
        double percentageValue = calculatePercentage(dataPoints, 98);
        System.out.println("98% of " + dataPoints + " is: " + percentageValue);
		
        
		List<Float> list = new ArrayList<Float>();
		List<Devicedatatemp> devDataList = new ArrayList<Devicedatatemp>();
//		System.out.println("devDataList "+devDataList);
		List<String> batteryVal = new ArrayList<>();
		String maxTempThres = shipment.getTemp().split(" ")[2];
		float maxTempThresF = Float.valueOf(shipment.getTemp().split(" ")[2].replace(" c", ""));
		 System.out.println("maxTempThres "+maxTempThresF);
		String minTempThes = shipment.getTemp().split(" ")[0];
		float minTempF = Float.valueOf(shipment.getTemp().split(" ")[0]);
		 System.out.println("minTempThes "+minTempF);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yy");
		String tempAddress = "";		
		for (DeviceDataStream dv : results) {
			// System.out.println("dv.getMessage_Type() "+dv.getMessage_Type());
//System.out.println("dv.getCurrent_terminal_date() "+dv.getCurrent_terminal_date());
///			System.out.println("dv.getCurrent_terminal_date() "+dv.getCurrent_terminal_date());
			LocalDate date1 = null;

			date1 = LocalDate.parse(dv.getCurrent_terminal_date(), formatter);

			String dateTime = date1.toString() + "T" + dv.getCurrent_terminal_time() + ".000Z";

			if (dv.getMessage_Type().equals("sensor") || dv.getMessage_Type().equals("GPS MESSAGE")) {
				if (dateTime.compareTo(shipment.getCreated_Date()) > 0) {

					Devicedatatemp devData = new Devicedatatemp();
					devData.setSensorTemp(dv.getFirst_Sensor_temperature());
//					devData.setUTC(dv.getCurrent_terminal_date() + " " + dv.getCurrent_terminal_time());
					devData.setUTC(dateTime);
					// System.out.println("dv.getDevice_Id() "+dv.getDevice_Id());
					// System.out.println("dv.getReporting_Date() + \"T\" + dv.getReporting_time()
					// "+dv.getReporting_Date() + "T" + dv.getReporting_time() );
					devData.setReportDateTime(dv.getReporting_Date() + "T" + dv.getReporting_time() + "Z");

					devData.setMessage_Type(dv.getMessage_Type());	
//					cal.setMessage_Type(dv.getMessage_Type());
					devData.setLongitude(dv.getLongitude());
					devData.setLatitude(dv.getLatitude());
					devData.setDevice_Id(dv.getDevice_Id());
	//				System.out.println("dv.getAddress() "+dv.getAddress());
					tempAddress = dv.getAddress();
					if (!dv.getAddress().equals("")) {
	//					System.out.println("in not null");
						devData.setAddress(dv.getAddress());
		//				tempAddress = dv.getAddress();
					} else {
	//					System.out.println("in null");
						devData.setAddress(tempAddress);
					}
					batteryVal.add(dv.getBattery_Level());
					devDataList.add(devData);
																															
				}
			}
		}
		Collections.sort(devDataList, Devicedatatemp.uTCComparator);
		String currentDate = null;
//		String pdf2FileName = "c:\\SMAASMailAttachments\\SCMPDF\\Shipment " + shipment.getShipment_Num()
//				+ " All_Data_points.pdf";
		
		cal.setDevDataList(devDataList);
		
		System.out.println("::::::::::::::::: devDataList ::::::::::::");
		System.out.println(devDataList);
		
		List<Devicedatatemp> bat = new ArrayList<>();
		bat.add(devDataList.get(0));
		bat.add(devDataList.get(devDataList.size() - 1));
		
		String maxValueString = "4.2";
		float batteryLevelStart = Float.parseFloat(batteryVal.get(0));
		float maxValue = Float.parseFloat(maxValueString);
		float percentageStart = (batteryLevelStart / maxValue) * 100;
		float batteryLevelEnd = Float.parseFloat(batteryVal.get(batteryVal.size() - 1));
		float percentageEnd = (batteryLevelEnd / maxValue) * 100;
		
//		String batteryStart = String.valueOf(100 * Float.valueOf(batteryVal.get(0)) - 318);
//		String batterEnd = String.valueOf(100 * Float.valueOf(batteryVal.get(batteryVal.size() - 1)) - 318);
		
		String batteryStart = String.valueOf(Math.round(percentageStart * 100.0) / 100.0);
		String batterEnd = String.valueOf(Math.round(percentageEnd * 100.0) / 100.0);

		for (Devicedatatemp dv : devDataList) {
			// System.out.println("dv.getMessage_Type() " + dv.getMessage_Type());
			if (dv.getMessage_Type().equals("sensor")) {
				Float s = 0F; //before it is given as dv.getSensorTemp() !=""
	//			if (dv.getSensorTemp() != "" || dv.getSensorTemp() != null || !dv.getSensorTemp().isEmpty()) {
				if (!dv.getSensorTemp().equals("") || !dv.getSensorTemp().equals(null) || !dv.getSensorTemp().isEmpty()) {
					// System.out.println(dv.getSensorTemp());
					s = Float.valueOf(dv.getSensorTemp().replace(" C", ""));
					String nextDate = dv.getUTC();
					if (currentDate == null) {
						currentDate = nextDate;
						continue;
					}
					
					currentDate = nextDate;
					

					list.add(s);

				}

			}
		}

	/**********   for calculating no.of incursion & excursions and time out of Threshold    **********/

	int count = 0;
	int hours = 0;
	int min = 0;
	int sec = 0;
//	String duration = "";
	boolean temp = false;
	String startTime = "";
	String endTime = "";
	String lastUTC = "";
	long finalDuration = 0;
//	SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
//	SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
	int startTimeCount = 0;
	for (Devicedatatemp dv : devDataList) {
///		System.out.println(dv);

		if (dv.getMessage_Type().equals("sensor")) {
			Float s = 0F; // before it is given as dv.getSensorTemp() !=""
			// if (dv.getSensorTemp() != "" || dv.getSensorTemp() != null ||
			// !dv.getSensorTemp().isEmpty()) {
			if (!dv.getSensorTemp().equals("") || !dv.getSensorTemp().equals(null) || !dv.getSensorTemp().isEmpty()) {
				// System.out.println(dv.getSensorTemp());
				s = Float.valueOf(dv.getSensorTemp().replace(" C", ""));
				// String nextDate = dv.getUTC();
				System.out.println(dv.getSensorTemp());

				if (s >= maxTempThresF + 0.5 || s <= minTempF - 0.5) {
					temp = true;
					// strattime
					if(startTimeCount == 0)
					startTime = dv.getUTC();	
					startTimeCount = startTimeCount+1;
					lastUTC = dv.getUTC();
					System.out.println("startTime :" +startTime);
					
				} else {

					if (temp) {
//								String endTime = dv.getUTC;
						// endtime
						count++;
						temp = false;
						endTime = dv.getUTC();
						  Date d1 = null;
						    Date d2 = null;
						    try {
						    	System.out.println("startTime in else:" +startTime);
						        d1 = format.parse(startTime);
						        d2 = format.parse(endTime);
						        System.out.println("d1 :" +d1.getTime());
						        System.out.println("d2 :" +d2.getTime());
						    } catch (ParseException e) {
						    	System.out.println("Exception occured while calculating d1:startTime and d2:endTime ");
						        e.printStackTrace();
						    }
						    
//						Date startDate = // Set start date
//								Date endDate   = // Set end date

								long duration  = d2.getTime() - d1.getTime();
								System.out.println("endTime :" +endTime);
								System.out.println("duration :" +duration);
								 finalDuration = finalDuration+duration;						
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
		    	System.out.println("Exception occured while calculating startTime and endTime ");
		        e.printStackTrace();
		    }
		    
//		Date startDate = // Set start date
//				Date endDate   = // Set end date

				long duration  = d2.getTime() - d1.getTime();
				System.out.println("duration :" +duration);
				finalDuration = finalDuration+duration;
		System.out.println("endTime :" +endTime);

	}
	System.out.println(count);
	System.out.println(startTimeCount);
	
//	duration = "h" + hours + "m" + min + "s" + sec;
	 
			long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(finalDuration);
			System.out.println("diffInSeconds :" +diffInSeconds);
			long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(finalDuration);
			System.out.println("diffInMinutes :" +diffInMinutes);
			long diffInHours = TimeUnit.MILLISECONDS.toHours(finalDuration);
			System.out.println("diffInHours :" +diffInHours);
			long diffInDays = TimeUnit.MILLISECONDS.toDays(finalDuration);
		System.out.println("diffInDays :" +diffInDays);

	cal.setNumber_of_Excursion_and_Incursion(String.valueOf(count));
///	cal.setTime_out_of_threshold(String.valueOf(diffInHours) + " h");

	System.out.println("i am here");

//		cal.setTime_out_of_threshold(total_time);
/////////////////////////////////////////////////////////////////////////////////		
		
		/*
		 * String tabEnd = "</font>\r\n"+"</table>\r\n" +
		 * "    <br><br><br>        			    \r\n" + "         \r\n" +
		 * "   			      \r\n" + "            			    \r\n" +
		 * "            			    </body>  \r\n" + "\r\n" + "\r\n"; alaramZone =
		 * alaramZone + tabEnd;
		 */

		// System.out.println("Max " + Collections.max(list));
		// System.out.println("Min " + Collections.min(list));
		DoubleSummaryStatistics summaryStats = list.stream().mapToDouble((a) -> a).summaryStatistics();
		// System.out.println("Average of a List = " + summaryStats.getAverage());
		
	
		// System.out.println("list "+list);
		float maxTemp = Collections.max(list);
		float minTemp = Collections.min(list);
//		cal.setHighestTemp(maxTemp);
		String degreeCSymbol = "\u2103"; // Using escape sequence
		
//		cal.setHighestTemp(String.valueOf(Float.valueOf(maxTemp)) + " C");		
//		cal.setLowestTemp(String.valueOf(Float.valueOf(minTemp)) + " C");
///		cal.setHighestTemp(String.valueOf(Float.valueOf(maxTemp)) + degreeCSymbol);		
///		cal.setLowestTemp(String.valueOf(Float.valueOf(minTemp)) + degreeCSymbol);
		cal.setHighestTemp(String.valueOf(Float.valueOf(maxTemp)));		
		cal.setLowestTemp(String.valueOf(Float.valueOf(minTemp)));
		
		double avgTemp = Math.round(summaryStats.getAverage() * Math.pow(10, 2)) / Math.pow(10, 2);

//		cal.setAvgTemp(avgTemp);
		cal.setAvgTemp(String.valueOf(Double.valueOf(avgTemp)));
///		cal.setAvgTemp(String.valueOf(Double.valueOf(avgTemp)) + degreeCSymbol);
		

///		Date shipCreatedDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(created_date);
///		Date shipEndDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(endDate);
		Date deviceattachedDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(deviceAttachedDate);
		Date shipEndDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(deliveredDate);
		System.out.println(deviceattachedDate+" checking dates"+shipEndDate);
		String elapsedTime = findDifference(deviceattachedDate, shipEndDate);
		Double mktValue = mktCalculator(avgTemp);
		mktValue = Math.round(mktValue * Math.pow(10, 4)) / Math.pow(10, 4);
		
		
//		cal.setMktValue(mktValue);
		cal.setMktValue(String.valueOf(Double.valueOf(mktValue)));
///		cal.setMktValue(String.valueOf(Double.valueOf(mktValue)) + degreeCSymbol);
		
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
	        if (roundedHoursAboveThres > 999.99) {			//this is added to eliminate the higher value to help out a bug in CPI which stops xml from getting consumed.
	            roundedHoursAboveThres = 999.99;
	        }
	        	        
	        long mtoHours = TimeUnit.MILLISECONDS.toHours(millisecondss);
	        System.out.println(":::::::::::::: mtohours :::::::::::::::");
	        System.out.println(mtoHours);
	        			        
	        long milliseconds = finalDurationsBelow; //example value, represents 1 hour in milliseconds
	        double hoursforBelowThres = (double) milliseconds / (1000 * 60 * 60); //convert milliseconds to hours
	        double hoursss = hoursforBelowThres; //example value
	        double roundedHoursBelowThres = (double) Math.round(hoursss * 100) / 100;
	        System.out.println("roundedHoursBelowThres ");
	        System.out.println(roundedHoursBelowThres);
	        if (roundedHoursBelowThres > 999.99) {		//this is added to eliminate the higher value to help out a bug in CPI which stops xml from getting consumed. 
	        	roundedHoursBelowThres = 999.99;
	        }
	        	
	    	System.out.println("Size of results: " + size);
	        System.out.println("Total number of data points: " + dataPoints);	        
	        System.out.println("98% of " + dataPoints + " is: " + percentageValue);
	        
//	    	cal.setTemperature24Max(String.valueOf(roundedHoursAboveThres) + " h");
//	    	cal.setTemperature24Min(String.valueOf(roundedHoursBelowThres) + " h");
	        cal.setTemperature24Max(String.valueOf(roundedHoursAboveThres));
	    	cal.setTemperature24Min(String.valueOf(roundedHoursBelowThres));
	    	
	        double totalTimeOutOfThreshold = roundedHoursAboveThres + roundedHoursBelowThres;
	    	cal.setTime_out_of_threshold(String.valueOf(totalTimeOutOfThreshold) + " h");
	   
	    } 
		catch (Exception e) {
			// Handling errors, e.g. logging the error or throw a custom exception
			System.out.println("Exception in Calculations while calculating Time out of threshold");
			e.printStackTrace();
		}
										
///		cal.setStartTime(shipCreatedDate.toString());	
///		cal.setEndTime(shipEndDate.toString());			
		cal.setStartTime(deviceattachedDate.toString());	// Device Attached date i.e., the date of completion of the event Attach Device and Start
		cal.setEndTime(shipEndDate.toString());				// Date of Trigger of 944 xml 			
		cal.setElapsedTime(elapsedTime);
		cal.setDataPoints(String.valueOf(list.size()));
		cal.setBatterystart(batteryStart+" %");
		cal.setBatteryend(batterEnd+" %");
		cal.setTemperature_Max(maxTempThres);
		cal.setTemperature_Min(minTempThes);
///		cal.setTemperature_Max(maxTempThres + degreeCSymbol);
///		cal.setTemperature_Min(minTempThes + degreeCSymbol);
				
///		cal.setDelivery_Number(shipment.getInvoice_Number());
///		cal.setCmo_Ref_Number(shipment.getCmo_Ref_Number());
///		cal.setContainer_Number(shipment.getShipper_Number());
	//	cal.setRoute_Id(shipment.getRoute_Id());
	//	cal.setGoods_Id(shipment.getGoods_Id());
///		cal.setRoute_Id(shipment.getCustRouteId());
///     cal.setGoods_Id(shipment.getCustGoodId());
///		cal.setGoods_Type(shipment.getGoods_Desc());
///		cal.setStorage_Condition(shipment.getGoods_Desc());
		
//		String dateString = shipment.getEstimated_Delivery_Date();
//	    SimpleDateFormat sdf_expected = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
//	    Date date_expected = sdf_expected.parse(dateString);
//	    sdf_expected = new SimpleDateFormat("yyyy-MM-dd");
//	    dateString = sdf_expected.format(date_expected);
//	    
//		cal.setExpected_Delivery_Date(dateString);

//// remove below commented code for shipment start date to be in yyyy-MM-dd	
		
//		String shp_startDate = shipCreatedDate.toString(); 
//		SimpleDateFormat sdf_shpCreated = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy"); 
//		Date date_shpCreated = sdf_shpCreated.parse(shp_startDate); 
//		sdf_shpCreated = new SimpleDateFormat("yyyy-MM-dd"); 
//		shp_startDate = sdf_shpCreated.format(date_shpCreated); 
//		
//		cal.setStartTime(shp_startDate); 
		
	//// remove below commented code for shipment end date to be in yyyy-MM-dd	
		
//		String shp_endDate = shipEndDate.toString(); 
//		SimpleDateFormat sdf_shpEnd = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy"); 
//		Date dateShpEnd = sdf_shpEnd.parse(shp_endDate); 
//		sdf_shpEnd = new SimpleDateFormat("yyyy-MM-dd"); 
//		shp_endDate = sdf_shpEnd.format(dateShpEnd); 
//		
//		cal.setEndTime(shp_endDate);
		
///		cal.setPo_Number(shipment.getPo_Number());
///		cal.setNdc_Number(shipment.getNdc_Number());
///		cal.setBatch_Id(shipment.getBatch_Id());
///		cal.setSerial_Number_of_goods(shipment.getSerial_Number_of_goods());
//		cal.setShipment_Id(shipment.getShipment_Id());
			
		String dateStr = shipment.getCreated_Date();
	    SimpleDateFormat sdf_created = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	    Date date_created = sdf_created.parse(dateStr);
	    sdf_created = new SimpleDateFormat("yyyy-MM-dd");
	    dateStr = sdf_created.format(date_created);
	    
		String shp_endDate = shipEndDate.toString(); 
		SimpleDateFormat sdf_shpEnd = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy"); 
		Date dateShpEnd = sdf_shpEnd.parse(shp_endDate); 
		sdf_shpEnd = new SimpleDateFormat("yyyy-MM-dd"); 
		shp_endDate = sdf_shpEnd.format(dateShpEnd); 
		
		///cal.setDate(dateStr);
	    cal.setDate(shp_endDate);
				
		System.out.println(shipment);
		Query query = new Query(Criteria.where("Shipment_Id").is(Shipment_Id));
		System.out.println(query);
		}
		catch (Exception e) {
			resp.setMessage("There is no data for this Shipment ");
			e.printStackTrace();
			//	System.out.println("catch");
				// TODO: handle exception
			
		}

		return cal;
	}
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
			 System.out.println("Difference " + "between two dates is: "+difference_In_Time);
		}

		// Catch the Exception
		catch (Exception e) {
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
	
	
	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/getCanCompleteShipment/{Shipment_Id}/{Login_Bp}")
	public CanCompleteShipmentDto CanCompleteShipment(@PathVariable(value = "Shipment_Id") String Shipment_Id,
			@PathVariable(value = "Login_Bp") String Login_Bp) {
		CanCompleteShipmentDto dto = new CanCompleteShipmentDto();
		List<ShipmentTransactions> sp = new ArrayList<>();
		Criteria crt = new Criteria();
		crt.andOperator(Criteria.where("Shipment_Id").is(Shipment_Id));
		Query query = new Query(crt);
		sp = mongoTemplate.find(query, ShipmentTransactions.class);
		for (ShipmentTransactions tr : sp) {
			if (tr.getEvent_Name().equals("Final Receipt") && tr.getPartner_From().equals(Login_Bp)) {
				dto.setCanComplete(true);
			}
		}
		System.out.println(dto.isCanComplete());
		if ("false".equals(dto.isCanComplete())) {
			dto.setCanComplete(false);
		}
		return dto;
	}

	/*
	 * @ResponseBody
	 * 
	 * @GetMapping("/getAllTxns/{Shipment_Id}") public List<ShipmentTransactions>
	 * getAllEventTxnsShipmentId(
	 * 
	 * @PathVariable(value = "Shipment_Id") String Shipment_Id) {
	 * 
	 * List<ShipmentTransactions> sp = new ArrayList<>(); Criteria crt = new
	 * Criteria(); crt.andOperator(Criteria.where("Shipment_Id").is(Shipment_Id));
	 * Query query = new Query(crt); sp = mongoTemplate.find(query,
	 * ShipmentTransactions.class); return sp; }
	 */
	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/getAllTxns/{Shipment_Id}")
	public List<ShipmentTransactions> getAllEventTxnsShipmentId(
			@PathVariable(value = "Shipment_Id") String Shipment_Id) {

		List<ShipmentTransactions> sp = new ArrayList<>();
		Criteria crt = new Criteria();
		crt.andOperator(Criteria.where("Shipment_Id").is(Shipment_Id));
		Query query = new Query(crt);
		sp = mongoTemplate.find(query, ShipmentTransactions.class);
		List<BusinessPartner> ev = new ArrayList<>();
		for (ShipmentTransactions txn : sp) {
			Criteria crt1 = new Criteria();
			crt1.andOperator(Criteria.where("BP_Id").is(txn.getPartner_From()));
			Query query1 = new Query(crt1);
			ev = mongoTemplate.find(query1, BusinessPartner.class);
			for (BusinessPartner bp : ev) {
				for (Events e : bp.getEvents()) {
					for (ShipmentTransactions tr : sp) {
						if (e.getEvent_Status().equals(tr.getEvent_Name())) {
							tr.setEvent_Id(tr.getEvent_Id());
						}
					}
				}
			}
		}
		//return sp;
		List<ShipmentTransactions> collect = sp.stream().sorted((shipmentTransaction1, shipmentTransaction2) -> shipmentTransaction1.getEvent_Id().compareTo(shipmentTransaction2.getEvent_Id())).collect(Collectors.toList());
		return collect;
	}

	/*
	 * @ResponseBody
	 * 
	 * @GetMapping("/getAllEventTxns/{Shipment_Id}/{Partner_From}") public
	 * List<ShipmentTransactions> getAllTxnsBasedOnShipmentId(
	 * 
	 * @PathVariable(value = "Shipment_Id") String Shipment_Id,
	 * 
	 * @PathVariable(value = "Partner_From") String Partner_From) {
	 * 
	 * List<ShipmentTransactions> sp = new ArrayList<>(); Criteria crt = new
	 * Criteria(); crt.andOperator(Criteria.where("Shipment_Id").is(Shipment_Id),
	 * Criteria.where("Partner_From").is(Partner_From)); Query query = new
	 * Query(crt); sp = mongoTemplate.find(query, ShipmentTransactions.class);
	 * 
	 * List<BusinessPartner> ev = new ArrayList<>(); Criteria crt1 = new Criteria();
	 * crt1.andOperator(Criteria.where("BP_Id").is(Partner_From)); Query query1 =
	 * new Query(crt1); ev = mongoTemplate.find(query1, BusinessPartner.class); for
	 * (BusinessPartner bp : ev) { for (Events e : bp.getEvents()) { for
	 * (ShipmentTransactions tr : sp) { if
	 * (e.getEvent_Status().equals(tr.getEvent_Name())) {
	 * tr.setEvent_Id(e.getEvent_Id()); } } } } return sp; }
	 */
	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@PostMapping("/deleteEventShip")
	public boolean deleteNewEvent(@Validated @RequestBody  UpdateEvent event) {
		boolean flag = false;
		List<ShipmentTransactions> list = shiptransrepo.findByPartner_from(event.getPartnerFrom());
		try {
			for (ShipmentTransactions list1 : list) {
				if (list1.getShipment_Id().equals(event.getShipment_Number())) {
					if (list1.getPartner_From().equals(event.getPartnerFrom())) {
						if (list1.getEvent_Name().equals(event.getEventType())) {
							Query query = new Query();
							query.addCriteria(new Criteria().andOperator(
									Criteria.where("Shipment_Id").is(event.getShipment_Number()),
									Criteria.where("Partner_From").is(event.getPartnerFrom()),
									Criteria.where("Event_Name").is(event.getEventType())));
							Update update = new Update();
							update.set("Event_Status", "EVENT DELETED");
							mongoTemplate.updateMulti(query, update, "ShipmentTransactions");
							System.out.println("Deleted Event with EventSNo : " + list1.getEvent_SNo());
							return flag = true;
						}

					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return flag;
	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/getShipfromandto/{Customer_Id}")
	public Set<String> getfromto(@PathVariable(value = "Customer_Id") String Customer_Id) {

		List<Shipments> shipment = shiprepo.findByCustomer_id(Customer_Id);

		List<String> shipfromto = new ArrayList<>();

		for (Shipments shp : shipment) {

			shipfromto.add(shp.getRoute_From());
			shipfromto.add(shp.getRoute_To());

		}

		Set<String> devicelist = new LinkedHashSet<String>(shipfromto);
		return devicelist;

	}

	/*
	 * @ResponseBody
	 * 
	 * @PostMapping("/updateEventShip") public boolean
	 * updateNewShipment(@RequestBody UpdateEvent event) { boolean flag = false;
	 * ShipmentTransactions trans = new ShipmentTransactions();
	 * List<ShipmentTransactions> list =
	 * shiptransrepo.findByPartner_from(event.getPartnerFrom()); try { for
	 * (ShipmentTransactions list1 : list) { if
	 * (list1.getShipment_Id().equals(event.getShipment_Number())) { if
	 * (list1.getPartner_From().equals(event.getPartnerFrom())) { if
	 * (list1.getEvent_Name().equals(event.getEventType())) { Query query = new
	 * Query(); query.addCriteria(new Criteria().andOperator(
	 * Criteria.where("Shipment_Id").is(event.getShipment_Number()),
	 * Criteria.where("Partner_From").is(event.getPartnerFrom()),
	 * Criteria.where("Event_Name").is(event.getEventType()))); Update update = new
	 * Update();
	 * 
	 * // update.set("Event_Name", event.getEventType()); Date date = new Date();
	 * SimpleDateFormat formatter = new
	 * SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"); String updateDate =
	 * formatter.format(date); update.set("EventReferenceNumber",
	 * event.getEventReferenceNumber()); update.set("Event_Status", "Completed");
	 * update.set("TypeOfReference", event.getTypeOfReference());
	 * update.set("Event_Exec_Date", updateDate); mongoTemplate.updateMulti(query,
	 * update, "ShipmentTransactions");
	 * System.out.println("Updated Event with EventSNo : " + list1.getEvent_SNo());
	 * return flag = true;
	 * 
	 * }
	 * 
	 * } } }
	 * 
	 * } catch (Exception e) { // TODO: handle exception }
	 * 
	 * return flag;
	 * 
	 * }
	 */
	/*
	 * @ResponseBody
	 * 
	 * @PostMapping("/updateEventShip") public Response
	 * updateNewShipment(@RequestBody UpdateEvent event) {
	 * 
	 * Response resp = new Response();
	 * 
	 * // resp.setStatus(false); ShipmentTransactions trans = new
	 * ShipmentTransactions(); List<ShipmentTransactions> list =
	 * shiptransrepo.findByPartner_from(event.getPartnerFrom()); Shipments shipment
	 * = new Shipments(); List<Shipments> shipmentlist = shiprepo.findAll();
	 * 
	 * try { for (ShipmentTransactions list1 : list) { if
	 * (list1.getShipment_Id().equals(event.getShipment_Number())) { if
	 * (list1.getPartner_From().equals(event.getPartnerFrom())) { if
	 * (list1.getEvent_Name().equals(event.getEventType())) { Query query = new
	 * Query(); query.addCriteria(new Criteria().andOperator(
	 * Criteria.where("Shipment_Id").is(event.getShipment_Number()),
	 * Criteria.where("Partner_From").is(event.getPartnerFrom()),
	 * Criteria.where("Event_Name").is(event.getEventType()))); Update update = new
	 * Update(); // update.set("Event_Name", event.getEventType());
	 * update.set("EventReferenceNumber", event.getEventReferenceNumber());
	 * update.set("Event_Status", "Completed"); update.set("TypeOfReference",
	 * event.getTypeOfReference()); update.set("EvidenceList",
	 * event.getEvidencelist());
	 * 
	 * mongoTemplate.updateMulti(query, update, "ShipmentTransactions");
	 * 
	 * 
	 * 
	 * resp.setStatus(true); resp.setMessasge("Event Updated Successfully"); return
	 * resp;
	 * 
	 * } // Query query1 = new Query(); // Update update1 = new Update(); //
	 * query1.addCriteria(Criteria.where("Shipment_Id").is(event.getShipment_Number(
	 * ))); // update1.set("Shipment_Status", list1.getEvent_Name()); //
	 * mongoTemplate.(query1, update1, "Shipments");
	 * 
	 * System.out.println("Updated Event with EventSNo : " + list1.getEvent_SNo());
	 * 
	 * } } }
	 * 
	 * } catch (Exception e) { resp.setMessasge("Event Failed to update");
	 * System.out.println("catch"); // TODO: handle exception }
	 * 
	 * return resp; }
	 */

	/*
	 * @ResponseBody
	 * 
	 * @PostMapping("/updateEventShip") public Response
	 * updateNewShipment(@RequestBody UpdateEvent event) {
	 * 
	 * Response resp= new Response();
	 * 
	 * //resp.setStatus(false); ShipmentTransactions trans = new
	 * ShipmentTransactions(); List<ShipmentTransactions> list =
	 * shiptransrepo.findByPartner_from(event.getPartnerFrom()); Shipments shipment
	 * = new Shipments(); List<Shipments> shipmentlist =shiprepo.findAll();
	 * 
	 * try { for (ShipmentTransactions list1 : list) { if
	 * (list1.getShipment_Id().equals(event.getShipment_Number())) { if
	 * (list1.getPartner_From().equals(event.getPartnerFrom())) { if
	 * (list1.getEvent_Name().equals(event.getEventType())) { Query query = new
	 * Query(); query.addCriteria(new Criteria().andOperator(
	 * Criteria.where("Shipment_Id").is(event.getShipment_Number()),
	 * Criteria.where("Partner_From").is(event.getPartnerFrom()),
	 * Criteria.where("Event_Name").is(event.getEventType()))); Update update = new
	 * Update(); // update.set("Event_Name", event.getEventType());
	 * update.set("EventReferenceNumber", event.getEventReferenceNumber());
	 * update.set("Event_Status", "Completed"); update.set("TypeOfReference",
	 * event.getTypeOfReference());
	 * update.set("Event_Exec_Date",event.getEvent_Exec_Date());
	 * 
	 * mongoTemplate.updateMulti(query, update, "ShipmentTransactions");
	 * 
	 * Query query1 = new Query(); Update update1 = new Update();
	 * query1.addCriteria(Criteria.where("Shipment_Id").is(event.getShipment_Number(
	 * ))); update1.set("Shipment_Status", list1.getEvent_Name());
	 * mongoTemplate.updateFirst(query1, update1, "Shipments");
	 * 
	 * 
	 * System.out.println("Updated Event with EventSNo : " + list1.getEvent_SNo());
	 * 
	 * resp.setStatus(true); resp.setMessasge("Event Updated Successfully"); return
	 * resp;
	 * 
	 * }
	 * 
	 * } } }
	 * 
	 * } catch (Exception e) { resp.setMessasge("Event Failed to update");
	 * System.out.println("catch"); // TODO: handle exception }
	 * 
	 * return resp; }
	 */
	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@PostMapping("/updateEventShip")
	public Response updateNewShipment(@Validated @RequestBody UpdateEvent event) {
		System.out.println("Long valueeeee::::" + event);
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		String strDate = formatter.format(date);
		Response resp = new Response();

		// resp.setStatus(false);
		ShipmentTransactions trans = new ShipmentTransactions();
		List<ShipmentTransactions> list = shiptransrepo.findByShipment_Id(event.getShipment_Number());
		Shipments shipment = new Shipments();
		List<Shipments> shpList = shiprepo.findByShipmentId(event.getShipment_Number());
//		List<Shipments> shipmentlist = shiprepo.findAll();
//		List<Shipments> shipmentlist = shiprepo.findByShipmentId(event.getShipment_Number());

		try {
			for (ShipmentTransactions list1 : list) {
				System.out.println("list1 ::::");
				System.out.println(list1);
				System.out.println("::::: Event name ::::  "+list1.getEvent_Name()+"  ::::: Event Type :::: "+event.getEventType());
				if (list1.getEvent_Name().equals(event.getEventType())) {
///					if (list1.getPartner().equals(event.getPartner())) {
						System.out.println("asdads " + event.getShipment_Number());
						System.out.println("asdads " + event.getPartnerFrom());
						System.out.println("asdads " + event.getEventType());					
		//if (list1.getEvent_Name().equals("Attach Device")) {
		 // if (list1.getEvent_Name().equals("Upload Invoice or Device Number")) {
		if (list1.getEvent_Name().equals("Attach Device and Start")) {
			System.out.println(" :::::::::::::  In if ::::::::::::: ");
			for (ShipmentTransactions list2 : list) {
				if (!list2.getEvent_Name().equals("Attach Device and Start")) {
					System.out.println(":::::::: in not of Attach Device ::::::::");
					System.out.println(event.getDevice_Id());
					System.out.println(list1.getDevice_Id()); // is getting null
			//	  if (!list2.getEvent_Name().equals("Upload Invoice or Device Number")) {
					Query query = new Query();
					query.addCriteria(
							new Criteria().andOperator(Criteria.where("Shipment_Id").is(event.getShipment_Number())));
///									Criteria.where("Partner").is(event.getPartner())));
					// Criteria.where("Device_Id").is(event.getDevice_Id());

					Update update = new Update();
			//		update.set("Device_Id", event.getDevice_Id());
					if(list1.getDevice_Id().equals("") || list1.getDevice_Id().equals(null)) {
						System.out.println(":::::::: in if for not Attach Device ::::::::");
					    update.set("Device_Id", event.getDevice_Id());
						}
					else {
						System.out.println(":::::::: in else for not Attach Device ::::::::");
							update.set("Device_Id", list1.getDevice_Id());
						}
					mongoTemplate.updateMulti(query, update, "ShipmentTransactions");

				}
			else {
				System.out.println("Device Id from List :::: " + list1.getDevice_Id());
				System.out.println("Device Id from UI :::: " +  event.getDevice_Id());
				
				List<Shipments> shipmentsList = shiprepo.findByShipmentId(event.getShipment_Number());
				
				System.out.println(":::::::: shipmentsList ::::::::");
				System.out.println(shipmentsList);
				
				for(Shipments shplist: shipmentsList) {
					if(shplist.getDevice_Id().equals("") || shplist.getDevice_Id().equals(null)) {
						System.out.println(event.getDevice_Id());
						shplist.setDevice_Id(event.getDevice_Id());
						System.out.println(":::::::: in if for setting device id in shipments ::::::::");
					}
					else {
						shplist.setDevice_Id(shplist.getDevice_Id());
					//	shplist.setDevice_Id(event.getDevice_Id());
						System.out.println(":::::::: in else for setting device id in shipments ::::::::");
					}
				}
				shiprepo.saveAll(shipmentsList);
							
				Query query = new Query();
				query.addCriteria(new Criteria().andOperator(Criteria.where("Device_Id").is(event.getDevice_Id())));
				Update update = new Update();
				update.set("DeviceStatusReferred", "Attached To Shipment");
				mongoTemplate.updateMulti(query, update, "Devices");
				System.out.println(":::::::: completed updating ::::::::");
			  }
			}
			Query query = new Query();
			query.addCriteria(new Criteria().andOperator(Criteria.where("Shipment_Id").is(event.getShipment_Number()),
///					Criteria.where("Partner").is(event.getPartner()),
					Criteria.where("Event_Name").is(event.getEventType())));
			// Criteria.where("Device_Id").is(event.getDevice_Id());

			Update update = new Update();
			update.set("Event_Name", event.getEventType());
			update.set("EventReferenceNumber", event.getEventReferenceNumber());
			update.set("Event_Status", "Completed");
///			update.set("TypeOfReference", event.getTypeOfReference());
			update.set("Event_Exec_Date", strDate);
			update.set("Partner", event.getPartner());
			update.set("Partner_From", event.getPartnerFrom());
			update.set("Evidence", event.getEvidence());
///			update.set("EvidenceList", event.getEvidencelist());
///			update.set("Evidence_URL", event.getEvidenceURL());
///			update.set("Evidence_For", event.getEvidence_For());
			if (list1.getDevice_Id().equals("") || list1.getDevice_Id().equals(null)) {
			update.set("Device_Id", event.getDevice_Id());
			}

			mongoTemplate.updateMulti(query, update, "ShipmentTransactions");
			
			for (Shipments shipments : shpList) {							
				if(shipments.getShipment_Status().equals("Delivered")) {	
					shipments.setShipment_Status("Delivered");
					shipments.setDevice_Id(event.getDevice_Id());
				}
				else {
				shipments.setShipment_Status("Goods Receipt");
				shipments.setDevice_Id(event.getDevice_Id());
				}
			}												
			shiprepo.saveAll(shpList);
			
			resp.setMessage("Event Updated Successfully");
			resp.setStatus(true);

			// generateemail(event.getShipment_Number());
			return resp;
		} 	
		
		else {
			System.out.println(" :::::::::::::  In else ::::::::::::: ");
			Query query = new Query();

			query.addCriteria(new Criteria().andOperator(Criteria.where("Shipment_Id").is(event.getShipment_Number()),
///					Criteria.where("Partner").is(event.getPartner()),
					Criteria.where("Event_Name").is(event.getEventType())));
			System.out.println(":::: Query ::::");
			System.out.println(query);

			Update update = new Update();
			update.set("Event_Name", event.getEventType());
			update.set("EventReferenceNumber", event.getEventReferenceNumber());
			update.set("Event_Status", "Completed");
///			update.set("TypeOfReference", event.getTypeOfReference());
			update.set("Event_Exec_Date", strDate);
			update.set("Partner", event.getPartner());
			update.set("Partner_From", event.getPartnerFrom());
			update.set("Evidence", event.getEvidence());
///			update.set("EvidenceList", event.getEvidencelist());
///			update.set("Evidence_URL", event.getEvidenceURL());
///			update.set("Evidence_For", event.getEvidence_For());
			System.out.println(":::: Update Criteria ::::");
			System.out.println(update);

			mongoTemplate.updateMulti(query, update, "ShipmentTransactions");
			
			for (Shipments shipments : shpList) {							
				if(shipments.getShipment_Status().equals("Delivered")) {	
					shipments.setShipment_Status("Delivered");
				}
				else {
				shipments.setShipment_Status("Goods Receipt");
				}
			}												
			shiprepo.saveAll(shpList);
					
			resp.setMessage("Event Updated Successfully");
			resp.setStatus(true);

			// generateemail(event.getShipment_Number());
			return resp;
		}
///	}
   }						
//				else {
//
//					System.out.println("event_statussssss::::" + list1.getEvent_Status());
//		//			System.out.println("event_statussssss::::" + event.getEventStatus());
//
//		///			if(!list1.getEvent_Status().equals("Completed")) {
//	   ///			if(list1.getEvent_Status().equals("Initialized")) {
//					if(event.getEventStatus().equals("Initialized")) {
//						System.out.println("i am coming to complete the event");
//
//						Query query4 = new Query();
//
//						query4.addCriteria(
//								new Criteria().andOperator(Criteria.where("Shipment_Id").is(list1.getShipment_Id()),
//										Criteria.where("Partner").is(list1.getPartner()),
//										Criteria.where("Event_Name").is(list1.getEvent_Name())));
//						       //         Criteria.where("Device_Id").is(event.getDevice_Id());						 
//						Update update3 = new Update();
//						update3.set("Event_Name", list1.getEvent_Name());
//						update3.set("EventReferenceNumber", event.getEventReferenceNumber());
//						update3.set("Event_Status", "Completed");
/////						update3.set("TypeOfReference", event.getTypeOfReference());
//						update3.set("Event_Exec_Date", strDate);
//						update3.set("Evidence", event.getEvidence());
/////						update3.set("Evidence_URL", event.getEvidenceURL());
/////						update3.set("Evidence_For", event.getEvidence_For());
//		//				update3.set("Device_Id", event.getDevice_Id());
//						mongoTemplate.updateMulti(query4, update3, "ShipmentTransactions");
//						System.out.println("updated mongo collection also");
//				
//					}
//
//				}
			}
		} catch (Exception e) {
			resp.setMessage("Event Failed to update");
			System.out.println("catch");
			// TODO: handle exception
		}
		return resp;
	}

//	@ResponseBody
//	@PostMapping("/updateEventShip")
//	public Response updateNewShipment(@RequestBody UpdateEvent event) {
//
//	Response resp = new Response();
//
//	// resp.setStatus(false);
//	ShipmentTransactions trans = new ShipmentTransactions();
//	List<ShipmentTransactions> list = shiptransrepo.findByShipment_Id(event.getShipment_Number());
//	Shipments shipment = new Shipments();
//	List<Shipments> shipmentlist = shiprepo.findAll();
//
//	try {
//	for (ShipmentTransactions list1 : list) {
//
//	if (list1.getEvent_Name().equals(event.getEventType())) {
//	if (list1.getPartner().equals(event.getPartner())) {
//
//	Query query = new Query();
//	System.out.println("before query");
//	query.addCriteria(
//	new Criteria().andOperator(Criteria.where("Shipment_Id").is(event.getShipment_Number()),
//	Criteria.where("Partner").is(event.getPartner()),
//	Criteria.where("Event_Name").is(event.getEventType())));
//
//	Update update = new Update();
//	update.set("Event_Name", event.getEventType());
//	update.set("EventReferenceNumber", event.getEventReferenceNumber());
//	update.set("Event_Status", "Completed");
//	update.set("TypeOfReference", event.getTypeOfReference());
//	mongoTemplate.updateMulti(query, update, "ShipmentTransactions");
//	System.out.println("after query");
//	long eventslno = Long.valueOf((list1.getEvent_SNo() + 1));
//	System.out.println("Long valueeeee::::" + eventslno);
//	ShipmentTransactions eventsno = shiptransrepo.findByEvent_Sno(eventslno,event.getShipment_Number());
//	String reqval = eventsno.getEvent_Name();
//	System.out.println("Reqval::::" + reqval);
//	System.out.println("Eventss updating to initailization" + eventsno);
//	System.out.println("query displaying for criteria" + eventsno.getShipment_Id() + "-------------"
//	+ eventsno.getEvent_SNo());
//	Query query2 = new Query();
//	Update update2 = new Update();
//
//	query2.addCriteria(
//	new Criteria().andOperator(Criteria.where("Shipment_Id").is(eventsno.getShipment_Id()),
//	Criteria.where("Event_SNo").is(eventsno.getEvent_SNo())));
//
//	System.out.println("query displaying for criteria" + eventsno.getShipment_Id() + "-------------"
//	+ eventsno.getEvent_SNo());
//
//	update2.set("Event_Status", "Initialized");
//	mongoTemplate.updateMulti(query2, update2, "ShipmentTransactions");
//
//	Query query1 = new Query();
//	Update update1 = new Update();
//	query1.addCriteria(Criteria.where("Shipment_Id").is(event.getShipment_Number()));
//	update1.set("Shipment_Status", reqval);
//	// update1.set("Partner", eventsno.get)
//	mongoTemplate.updateFirst(query1, update1, "Shipments");
//
//	resp.setStatus(true);
//	resp.setMessage("Event Updated Successfully");
//	return resp;
//
//	}
//
//	} else {
//
//	System.out.println("event_statussssss::::" + list1.getEvent_Status());
//
//	if (!list1.getEvent_Status().equals("Completed")) {
//
//	System.out.println("i am coming to complete the event");
//
//	Query query4 = new Query();
//
//	query4.addCriteria(
//	new Criteria().andOperator(Criteria.where("Shipment_Id").is(list1.getShipment_Id()),
//	Criteria.where("Partner").is(list1.getPartner()),
//	Criteria.where("Event_Name").is(list1.getEvent_Name())));
//	Update update3 = new Update();
//	update3.set("Event_Name", list1.getEvent_Name());
//	update3.set("EventReferenceNumber", event.getEventReferenceNumber());
//	update3.set("Event_Status", "Completed");
//	update3.set("TypeOfReference", event.getTypeOfReference());
//	mongoTemplate.updateMulti(query4, update3, "ShipmentTransactions");
//	System.out.println("update mongo collection also");
//	}
//
//	}
//	}
//
//	} catch (Exception e) {
//	resp.setMessage("Event Failed to update");
//	System.out.println("catch");
//	// TODO: handle exception
//	}
//
//	return resp;
//	}

	public void generateemail(String shipmentId) {
		List<AlertProfile> alertproifle = alertprofilerepo.findAll();
		System.out.println("in for loop" + alertproifle);
		System.out.println("in alert profile");

		for (AlertProfile alerts : alertproifle) {
			// System.out.println("in forlopp" + alerts.getRoute_Id());

			if (alerts.getAlert_Type().equals("Event")) {
				try {
					// System.out.println("alerts.getAlert_Id() "+alerts.getAlert_Id());
					event(alerts.getRoute_Id(), alerts.getEvent_Name(),

							alerts.getGoods_Type(), alerts.getCustomer_Id(), alerts.getAlert_Id(), shipmentId);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

	}

	public void event(String routeId, String eventName, String goodsType, String customerId, String alertId,
			String shipmentId) throws InterruptedException {

		List<Shipments> shipmentlists = shiprepo.getShipmentsList(shipmentId);

		List<ShipmentTransactions> shipmenttransactionslist = null;

		for (Shipments shipment : shipmentlists) {
			if (shipment.getRoute_Id().equals(routeId) && shipment.getCustomer_Id().equals(customerId)) {

				if (shipment.getGoods_Desc().equals(goodsType)) {
					String deviceid = shipment.getDevice_Id();
					String Shipment_Id = shipment.getShipment_Id();

					shipmenttransactionslist =

							shiptransrepo.findByShipment_Id(Shipment_Id);

					for (ShipmentTransactions transactions : shipmenttransactionslist) {

						if (transactions.getEvent_Name().equals(eventName)
								&& transactions.getDevice_Id().equals(deviceid)) {
							if (transactions.getEvent_Status().equals("Initialized")) {

								System.out.println("Shipment Initialized email");
								List<AlertMaster> altm = alertmasterrepo.findByCustomer_Id(customerId);
								System.out.println("altm:::::" + altm);
								for (AlertMaster alerts : altm) {
									if (alerts.getAlert_Id().equals(alertId)) {
										String[] emails = alerts.getEmail_Addresses();
										System.out.println("emails for initialized " + emails[0]);
										mailsend.sendSimpleMail(alerts.getEmail_Addresses(), alerts.getEmail_Subject(),
												transactions.getEvent_Name() + " event for Shipment Id:" + Shipment_Id
														+ " has been Initialized.");
									}
								}
							}

							else if (transactions.getEvent_Status().equals("Completed")) {
								System.out.println("Shipment Completed email");
								List<AlertMaster> altm = alertmasterrepo.findByCustomer_Id(customerId);
								System.out.println("altm:::::" + altm);
								for (AlertMaster alerts : altm) {
									if (alerts.getAlert_Id().equals(alertId)) {
										String[] emails = alerts.getEmail_Addresses();
										System.out.println("emails for completed " + emails[0]);
										mailsend.sendSimpleMail(alerts.getEmail_Addresses(), alerts.getEmail_Subject(),
												transactions.getEvent_Name() + " event for Shipment Id:" + Shipment_Id
														+ " has been completed.");
									}
								}

							}

						}

					}
				}
			}
		}
	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/getInternalShipmentId/{BP_Id}")
	public String generateInternalShipmentId(@PathVariable(value = "BP_Id") String BP_Id) throws Exception {
		String sid = null;
		String afterSplit = null;
		String addZeros = null;
		List<String> shipIds = new ArrayList<>();
		List<Shipments> shiplist = shiprepo.findByBP_Id(BP_Id);

		// List<Shipments> ships = shiprepo.findAll();
		System.out.println("xkjcgh" + shiplist);
		if (shiplist == null || shiplist.isEmpty()) {

			Collections.reverse(shipIds);
			String lastId = "T00000000";
//	String lastId = shipIds.get(0);
			String[] splitLastId = lastId.split("T");
			for (String s : splitLastId) {
				afterSplit = s;
			}
			Integer splitInt = Integer.parseInt(afterSplit);
			Integer increment = splitInt + 1;
			String incrementString = increment.toString();
			System.out.println(incrementString);
			if (incrementString.length() == 1) {
				addZeros = "0000000";
			} else if (incrementString.length() == 2) {
				addZeros = "0000000";
			} else if (incrementString.length() == 3) {
				addZeros = "000000";
			} else if (incrementString.length() == 4) {
				addZeros = "00000";
			} else if (incrementString.length() == 5) {
				addZeros = "0000";
			} else if (incrementString.length() == 6) {
				addZeros = "000";
			} else if (incrementString.length() == 6) {
				addZeros = "00";
			} else if (incrementString.length() == 7) {
				addZeros = "0";
			} else if (incrementString.length() == 8) {
				throw new Exception("Its beyong the Limit of the Present One");
			}
			String finalString = "T";
			finalString = finalString.concat(addZeros).concat(incrementString);
			System.out.println(finalString);
			return finalString;
		} else {

			for (Shipments sh : shiplist) {
				sid = sh.getShipment_Id();
				shipIds.add(sid);
			}
			Collections.reverse(shipIds);
			String lastId = shipIds.get(0);
			String[] splitLastId = lastId.split("T");
			for (String s : splitLastId) {
				afterSplit = s;
			}
			Integer splitInt = Integer.parseInt(afterSplit);
			Integer increment = splitInt + 1;
			String incrementString = increment.toString();
			System.out.println(incrementString);
			if (incrementString.length() == 1) {
				addZeros = "0000000";
			} else if (incrementString.length() == 2) {
				addZeros = "000000";
			} else if (incrementString.length() == 3) {
				addZeros = "00000";
			} else if (incrementString.length() == 4) {
				addZeros = "0000";
			} else if (incrementString.length() == 5) {
				addZeros = "000";
			} else if (incrementString.length() == 6) {
				addZeros = "00";
			} else if (incrementString.length() == 7) {
				addZeros = "0";
			} else if (incrementString.length() == 8) {
				throw new Exception("Its beyong the Limit of the Present One");
			}
			String finalString = "T";
			finalString = finalString.concat(addZeros).concat(incrementString);

			// finalString = addZeros.concat(incrementString);
			System.out.println(finalString);

			return finalString;
		}
	}

//	public String generateInternalShipmentId() throws Exception {
//		String sid = null;
//		String afterSplit = null;
//		String addZeros = null;
//		List<String> shipIds = new ArrayList<>();
//		List<Shipments> ships = shiprepo.findAll();
//
//		System.out.println("Sssss::::" + ships);
//		if (ships.isEmpty()) {
//
//			System.out.println("In IF Condition");
//			Collections.reverse(shipIds);
//			String lastId = "T0000000";
//			String[] splitLastId = lastId.split("T");
//			for (String s : splitLastId) {
//				afterSplit = s;
//			}
//			Integer splitInt = Integer.parseInt(afterSplit);
//			Integer increment = splitInt + 1;
//			String incrementString = increment.toString();
//			System.out.println(incrementString);
//			if (incrementString.length() == 1) {
//				addZeros = "0000000";
//			} else if (incrementString.length() == 2) {
//				addZeros = "000000";
//			} else if (incrementString.length() == 3) {
//				addZeros = "00000";
//			} else if (incrementString.length() == 4) {
//				addZeros = "0000";
//			} else if (incrementString.length() == 5) {
//				addZeros = "000";
//			} else if (incrementString.length() == 6) {
//				addZeros = "00";
//			} else if (incrementString.length() == 7) {
//				addZeros = "0";
//			} else if (incrementString.length() == 8) {
//				throw new Exception("Its beyong the Limit of the Present One");
//			}
//			String finalString = "T";
//			finalString = finalString.concat(addZeros).concat(incrementString);
//			System.out.println(finalString);
//			return finalString;
//		} else {
//
//			for (Shipments sh : ships) {
//				sid = sh.getShipment_Id();
//				shipIds.add(sid);
//			}
//			Collections.reverse(shipIds);
//			String lastId = shipIds.get(0);
//			String[] splitLastId = lastId.split("T");
//			for (String s : splitLastId) {
//				afterSplit = s;
//			}
//			Integer splitInt = Integer.parseInt(afterSplit);
//			Integer increment = splitInt + 1;
//			String incrementString = increment.toString();
//			System.out.println(incrementString);
//			if (incrementString.length() == 1) {
//				addZeros = "0000000";
//			} else if (incrementString.length() == 2) {
//				addZeros = "0000000";
//			} else if (incrementString.length() == 3) {
//				addZeros = "000000";
//			} else if (incrementString.length() == 4) {
//				addZeros = "00000";
//			} else if (incrementString.length() == 5) {
//				addZeros = "0000";
//			} else if (incrementString.length() == 6) {
//				addZeros = "000";
//			} else if (incrementString.length() == 6) {
//				addZeros = "00";
//			} else if (incrementString.length() == 7) {
//				addZeros = "0";
//			} else if (incrementString.length() == 8) {
//				throw new Exception("Its beyong the Limit of the Present One");
//			}
//			String finalString = "T";
//			finalString = finalString.concat(addZeros).concat(incrementString);
//			System.out.println(finalString);
//			return finalString;
//		}
//	}
	
	
	public String generateInternalShipmentId() throws Exception 
    {
        List<Shipments> ships = shiprepo.findAll();
        
        if (ships.isEmpty())
        {           
            return "T000000001";
        } 
        else 
        {
            Comparator<Shipments> comparator = (Shipment1, Shipment2) -> Shipment2.getShipment_Id().compareTo(Shipment1.getShipment_Id());
            Collections.sort(ships, comparator);
            int end_digit = Integer.parseInt(ships.get(0).getShipment_Id().split("T")[1]);
            if(end_digit>=9999999999L)
            {
                throw new Exception("Its beyond the Limit of the Present One");
            }
            else
            {
                return String.format("T%09d", end_digit+1);
            }
        }
    }
	
	
	

	/*
	 * @ResponseBody
	 * 
	 * @GetMapping("/getDDData/{Customer_Id}") public DropDownShipmentDetails
	 * getDDData(@PathVariable(value = "Customer_Id") String Customer_id) throws
	 * Exception {
	 * 
	 * String generatedInternalShipmentId = generateInternalShipmentId();
	 * System.out.println(generatedInternalShipmentId);
	 * 
	 * List<CustomBP> listbp = new ArrayList<>(); BusinessPartner partner = null;
	 * CustomBP custBpp = null; DropDownShipmentDetails dp =
	 * ddrepo1.findByCustomer_id(Customer_id.trim()); String[] list3 =
	 * dp.getBusiness_Partner_Id(); List<String> list4 = convertArrayTOList(list3);
	 * System.out.println(list4); for (String ar : list4) { custBpp = new
	 * CustomBP(); partner = bussinesRepo.findByBP_Id(ar.trim());
	 * System.out.println(partner.getBP_Id()); if (partner.getBP_Id().equals(ar) &&
	 * !listbp.contains(partner.getBP_Id())) { if
	 * (listbp.contains(partner.getBP_Id())) { break; } else {
	 * custBpp.setBP_Id(partner.getBP_Id());
	 * custBpp.setCompany_Name(partner.getCompany_Name());
	 * custBpp.setEvents(partner.getEvents()); listbp.add(custBpp); } } }
	 * System.out.println(listbp); dp.setBussinesPartnersDetails(listbp);
	 * dp.setInternalShipmentId(generatedInternalShipmentId); return dp; }
	 */
	/*
	 * @ResponseBody
	 * 
	 * @GetMapping("/getDDData/{Customer_Id}") public DropDownShipmentDetails
	 * getDDData(@PathVariable(value = "Customer_Id") String Customer_id) throws
	 * Exception {
	 * 
	 * String generatedInternalShipmentId = generateInternalShipmentId();
	 * System.out.println(generatedInternalShipmentId);
	 * 
	 * List<CustomBP> listbp = new ArrayList<>(); BusinessPartner partner = null;
	 * CustomBP custBpp = null; DropDownShipmentDetails dp = null; String dms=null;
	 * 
	 * 
	 * 
	 * // System.out.println("Device_Id"+dp.getDevice_Id()[1]); // String[]
	 * Devicelist =dp.getDevice_Id(); dp =
	 * ddrepo1.findByCustomer_id(Customer_id.trim());
	 * 
	 * List<String> Devicelist =dp.getDevice_Id(); List<String> deviceIDs = new
	 * ArrayList<String>();
	 * 
	 * 
	 * //for(String cus: deviceIDs) { for (int i=0;i<Devicelist.size();i++) {
	 * 
	 * Devices dev = devicerepo.findByDevice_Id(Devicelist.get(i));
	 * //System.out.println("List Values:::"+dev.getDeviceStatusReferred());
	 * 
	 * 
	 * 
	 * 
	 * String status=dev.getDeviceStatusReferred();
	 * 
	 * 
	 * if(status.equals("Available")) {
	 * 
	 * dms = Devicelist.get(i);
	 * 
	 * deviceIDs.add(dms);
	 * 
	 * 
	 * 
	 * System.out.println("after else"); String[] list3 =
	 * dp.getBusiness_Partner_Id(); List<String> list4 = convertArrayTOList(list3);
	 * //System.out.println("Number:::"+list4); for (String ar : list4) { custBpp =
	 * new CustomBP(); partner = bussinesRepo.findByBP_Id(ar.trim());
	 * //System.out.println("BusinessPartner:::"+partner.getBP_Id()); if
	 * (partner.getBP_Id().equals(ar) && !listbp.contains(partner.getBP_Id())) { if
	 * (listbp.contains(partner.getBP_Id())) { break; } else {
	 * custBpp.setBP_Id(partner.getBP_Id());
	 * custBpp.setCompany_Name(partner.getCompany_Name());
	 * custBpp.setEvents(partner.getEvents()); listbp.add(custBpp); } } }
	 * //System.out.println(listbp); dp.setBussinesPartnersDetails(listbp);
	 * dp.setInternalShipmentId(generatedInternalShipmentId);
	 * 
	 * 
	 * 
	 * 
	 * //String[] listdev = dms.split(","); dp.setDevice_Id(deviceIDs);
	 * 
	 * }else { System.out.println("not in available"); }
	 * 
	 * } return dp; }
	 */
	/*
	 * @ResponseBody
	 * 
	 * @GetMapping("/getDDData/{Customer_Id}") public DropDownShipmentDetails
	 * getDDData(@PathVariable(value = "Customer_Id") String Customer_id) throws
	 * Exception {
	 * 
	 * String generatedInternalShipmentId = generateInternalShipmentId();
	 * System.out.println(generatedInternalShipmentId);
	 * 
	 * List<CustomBP> listbp = new ArrayList<>(); BusinessPartner partner = null;
	 * CustomBP custBpp = null; DropDownShipmentDetails dp = null;
	 * 
	 * String dms = null;
	 * 
	 * // System.out.println("Device_Id"+dp.getDevice_Id()[1]); // String[]
	 * Devicelist =dp.getDevice_Id(); dp =
	 * ddrepo1.findByCustomer_id(Customer_id.trim());
	 * 
	 * List<String> Devicelist = dp.getDevice_Id(); List<String> deviceIDs = new
	 * ArrayList<String>();
	 * 
	 * // for(String cus: deviceIDs) { for (int i = 0; i < Devicelist.size(); i++) {
	 * 
	 * Devices dev = devicerepo.findByDevice_Id(Devicelist.get(i));
	 * //System.out.println("List Values:::" + dev.getDeviceStatusReferred());
	 * String status = dev.getDeviceStatusReferred();
	 * 
	 * 
	 * System.out.println("status " + status); if (status.equals("Available")) {
	 * 
	 * dms = Devicelist.get(i); System.out.println("adfr " + status);
	 * deviceIDs.add(dms); System.out.println("ajdhbfjda " + deviceIDs);
	 * 
	 * } else {
	 * 
	 * System.out.println("not in available"); dp.setDevice_Id(null); }
	 * 
	 * System.out.println("after else"); String[] list3 =
	 * dp.getBusiness_Partner_Id(); List<String> list4 = convertArrayTOList(list3);
	 * // System.out.println("Number:::"+list4); for (String ar : list4) { custBpp =
	 * new CustomBP(); partner = bussinesRepo.findByBP_Id(ar.trim()); //
	 * System.out.println("BusinessPartner:::"+partner.getBP_Id()); if
	 * (partner.getBP_Id().equals(ar) && !listbp.contains(partner.getBP_Id())) { if
	 * (listbp.contains(partner.getBP_Id())) { break; } else {
	 * custBpp.setBP_Id(partner.getBP_Id());
	 * custBpp.setCompany_Name(partner.getCompany_Name());
	 * custBpp.setEvents(partner.getEvents()); listbp.add(custBpp); } } } //
	 * System.out.println(listbp); dp.setBussinesPartnersDetails(listbp);
	 * dp.setInternalShipmentId(generatedInternalShipmentId);
	 * dp.setDevice_Id(deviceIDs);
	 * 
	 * // String[] listdev = dms.split(",");
	 * 
	 * } return dp; }
	 */
	
/* The below getDDData API is used till 23-07-2024 in both dev and production environment and now it is changed to get optimized response */	
	
//	@ResponseBody
//	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
//	@GetMapping("/getDDData/{Customer_Id}")
//	public DropDownShipmentDetails getDDData(@PathVariable(value = "Customer_Id") String Customer_id) throws Exception {
//
//		String generatedInternalShipmentId = generateInternalShipmentId();
//		System.out.println(generatedInternalShipmentId);
//
//		List<CustomBP> listbp = new ArrayList<>();
//		BusinessPartner partner = null;
//		CustomBP custBpp = null;
//		DropDownShipmentDetails dp = null;
//
//		String dms = null;
//
//		// System.out.println("Device_Id"+dp.getDevice_Id()[1]);
//		// String[] Devicelist =dp.getDevice_Id();
//		dp = ddrepo1.findByCustomer_id(Customer_id.trim());
//		System.out.println("kjaksd" + dp);
//		List<String> Devicelist = dp.getDevice_Id();
//		List<String> deviceIDs = new ArrayList<String>();
//		System.out.println("dfgdfg" + Devicelist);
//		if (Devicelist == null) {
//			System.out.println("::::::::::: Inside If condition in getDDData :::::::::::::");
//			dp.setDevice_Id(null);
//
//			System.out.println("If Devices Not Available");
//			List<BPList> list4 = dp.getBusinessPartner();
//			// List<String> list4 = convertArrayTOList(list3);
//			System.out.println("Number:::" + list4);
//			for (BPList ar : list4) {
//				System.out.println("::::::::::: Inside the For loop in getDDData :::::::::::::");
//				custBpp = new CustomBP();
//				partner = bussinesRepo.findByBP_Id(ar.getBP_Id());
//				System.out.println("BusinessPartner:::" + partner.getBP_Id());
//				System.out.println("BusinessPartner:::" + ar.getBP_Id());
//
//				if (partner.getBP_Id().equals(ar.getBP_Id()) && !listbp.contains(partner.getBP_Id())) {
//
//					System.out.println("if condition");
//
//					if (listbp.contains(partner.getBP_Id())) {
//						break;
//					} else {
//						custBpp.setBP_Id(partner.getBP_Id());
//						custBpp.setCompany_Name(partner.getCompany_Name());
//						custBpp.setEvents(partner.getEvents());
//						listbp.add(custBpp);
//					}
//				}
//
//			}
//			// System.out.println(listbp);
//			dp.setBussinesPartnersDetails(listbp);
//			dp.setInternalShipmentId(generatedInternalShipmentId);
//
//			// dp.setDevice_Id(deviceIDs);
//
//		} else {
//			System.out.println("::::::::::: Inside Else condition in getDDData :::::::::::::");
//			// for(String cus: deviceIDs) {
//			for (int i = 0; i < Devicelist.size(); i++) {
//				System.out.println("sjm,fbdsjhf" + Devicelist.get(i));
//				Devices dev = devicerepo.findByDevice_Id(Devicelist.get(i));
//
//				System.out.println("List Values:::" + dev);
//
//				String status = dev.getDeviceStatusReferred();
//
//				System.out.println("Status::::" + status);
//				if (status.equals("Available")) {
//
//					dms = Devicelist.get(i);
//
//					deviceIDs.add(dms);
//
//				} else {
//
//					System.out.println("not in available");
//					dp.setDevice_Id(null);
//				}
//
//				System.out.println("after else");
////				List<BPList> list3 = dp.getBusinessPartner();
////				// List<String> list4 = convertArrayTOList(list3);
////				// System.out.println("Number:::"+list4);
////				for (BPList ar : list3) {
////					custBpp = new CustomBP();
////					System.out.println("BusinessPartner:::" + ar.getBP_Id());
////					partner = bussinesRepo.findByBP_Id(ar.getBP_Id());
////					System.out.println("BusinessPartner:::" + partner.getBP_Id());
////					if (partner.getBP_Id().equals(ar.getBP_Id()) && !listbp.contains(partner.getBP_Id())) {
////						if (listbp.contains(partner.getBP_Id())) {
////							break;
////						} else {
////							custBpp.setBP_Id(partner.getBP_Id());
////							custBpp.setCompany_Name(partner.getCompany_Name());
////							custBpp.setEvents(partner.getEvents());
////							listbp.add(custBpp);
////						}
////					}
////				}
////				// System.out.println(listbp);
////				dp.setBussinesPartnersDetails(listbp);
////				dp.setInternalShipmentId(generatedInternalShipmentId);
//
//				List<String> deviceIdWithBatteryLevel = new LinkedList<>();
//				for(String deviceId : deviceIDs)
//				{
//				    AggregationOperation match = Aggregation.match(Criteria.where("Device_Id").is(deviceId));
//				    SortOperation sort = Aggregation.sort(Sort.by(Sort.Order.desc("SensorUTC")));
//				    AggregationOperation limit = Aggregation.limit(1);
//
//				    Aggregation aggregation = Aggregation.newAggregation(match, sort, limit);
//				    System.out.println(aggregation);
//
//				    List<DeviceDataStream> mappedResults = mongoTemplate.aggregate(aggregation, "DeviceDataStream", DeviceDataStream.class).getMappedResults();
//				    System.out.println(mappedResults);
//				    if(!mappedResults.isEmpty())
//				    {
//				        DeviceDataStream deviceDataStream = mappedResults.get(0);
////				        deviceIdWithBatteryLevel.add(deviceId+" - "+String.valueOf(100 * Float.valueOf(deviceDataStream.getBattery_Level()) - 318)+"%");
//						String maxValueString = "4.02";
//						float batteryLevel = Float.parseFloat(deviceDataStream.getBattery_Level());
//						float maxValue = Float.parseFloat(maxValueString);
//						float percentage = (batteryLevel / maxValue) * 100;
//						
//				        deviceIdWithBatteryLevel.add(deviceId+" - "+String.valueOf(Math.round(percentage * 100.0) / 100.0)+"%");
//				    }
//				    else
//				    {
//				        deviceIdWithBatteryLevel.add(deviceId);
//				    }
//				}
//				dp.setDevice_Id(deviceIdWithBatteryLevel);				
//			///	dp.setDevice_Id(deviceIDs);
//
//				// String[] listdev = dms.split(",");
//
//			}
//			List<BPList> list3 = dp.getBusinessPartner();
//			// List<String> list4 = convertArrayTOList(list3);
//			// System.out.println("Number:::"+list4);
//			for (BPList ar : list3) {
//				custBpp = new CustomBP();
//				System.out.println("BusinessPartner:::" + ar.getBP_Id());
//				partner = bussinesRepo.findByBP_Id(ar.getBP_Id());
//				System.out.println("BusinessPartner:::" + partner.getBP_Id());
//				if (partner.getBP_Id().equals(ar.getBP_Id()) && !listbp.contains(partner.getBP_Id())) {
//					if (listbp.contains(partner.getBP_Id())) {
//						break;
//					} else {
//						custBpp.setBP_Id(partner.getBP_Id());
//						custBpp.setCompany_Name(partner.getCompany_Name());
//						custBpp.setEvents(partner.getEvents());
//						listbp.add(custBpp);
//					}
//				}
//			}
//			// System.out.println(listbp);
//			dp.setBussinesPartnersDetails(listbp);
//			dp.setInternalShipmentId(generatedInternalShipmentId);
//
//		}
//		return dp;
//	}
	
	 @Autowired
	 private BusinessPartnerService businessPartnerService;
	
	
	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/getDDData/{Customer_Id}")
	public DropDownShipmentDetails getDDData(@PathVariable(value = "Customer_Id") String Customer_id) throws Exception {

		String generatedInternalShipmentId = generateInternalShipmentId();
		System.out.println(generatedInternalShipmentId);

//		List<CustomBP> listbp = new ArrayList<>();		//The listbp, partner and custBpp are commented because they are not in use and are used in previous development
//		BusinessPartner partner = null;
//		CustomBP custBpp = null;
		DropDownShipmentDetails dp = null;

//		String dms = null;	//Commented because it is not in use

		// System.out.println("Device_Id"+dp.getDevice_Id()[1]);
		// String[] Devicelist =dp.getDevice_Id();
		dp = ddrepo1.findByCustomer_id(Customer_id.trim());
		System.out.println("kjaksd" + dp);
		List<String> Devicelist = dp.getDevice_Id();
//		List<String> deviceIDs = new ArrayList<String>();	//Commented because it is not in use
		System.out.println("dfgdfg" + Devicelist);
		
		if (Devicelist == null) {
			System.out.println("::::::::::: Inside If condition in getDDData :::::::::::::");
			
			dp.setDevice_Id(null);

			System.out.println("If Devices Not Available");
			
			List<BPList> listBpIds = dp.getBusinessPartner();

			// Fetch all partner BP_Ids in a single query using findAllByBP_IdIn
			List<String> bpIds = listBpIds.stream().map(BPList::getBP_Id).collect(Collectors.toList());
			System.out.println(bpIds);

			List<CustomBP> listOfBpDetails = businessPartnerService.getBusinessPartnersDetails(bpIds);
			System.out.println("listOfBpDetails inside if condition");
			System.out.println(listOfBpDetails);

			dp.setBussinesPartnersDetails(listOfBpDetails);
			dp.setInternalShipmentId(generatedInternalShipmentId);

			// dp.setDevice_Id(deviceIDs);

		} else {
			System.out.println("::::::::::: Inside Else condition in getDDData :::::::::::::");

			List<String> listOfAvailableDevices = deviceService.getAllAvailableDevices();
			List<String> listOfDevicesWithPercentage = deviceBatteryPercentService
					.getLatestDeviceData(listOfAvailableDevices);

			System.out.println("Devices List");
			System.out.println(listOfDevicesWithPercentage);

			dp.setDevice_Id(listOfDevicesWithPercentage);
			//The above code is for getting devices with batter percentage and the below one from here is to set the BussinesPartnersDetails. 			
	        List<BPList> listBpIds = dp.getBusinessPartner();

	        // Fetch all partner BP_Ids in a single query using findAllByBP_IdIn
	        List<String> bpIds = listBpIds.stream()
	                                  .map(BPList::getBP_Id)
	                                  .collect(Collectors.toList());
			System.out.println(bpIds);	
	        
	        List<CustomBP> listOfBpDetails = businessPartnerService.getBusinessPartnersDetails(bpIds);
	        System.out.println("listOfBpDetails inside else condition");
			System.out.println(listOfBpDetails);

		///	dp.setBussinesPartnersDetails(listbp);
			dp.setBussinesPartnersDetails(listOfBpDetails);			
			dp.setInternalShipmentId(generatedInternalShipmentId);

		}
		return dp;
	}

	
	
//	@ResponseBody
//	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
//	@GetMapping("/getDDData/{Customer_Id}")
//	public DropDownShipmentDetails getDDData(@PathVariable(value = "Customer_Id") String customerId) throws Exception {
//
//	    String generatedInternalShipmentId = generateInternalShipmentId();
//	    System.out.println(generatedInternalShipmentId);
//
//	    DropDownShipmentDetails dp = ddrepo1.findByCustomer_id(customerId.trim());
//
//	    if (dp == null) {
//	        // Handle the case where dp is null
//	        // You may return an appropriate response or throw an exception
//	    }
//
//	    List<String> deviceIds = dp.getDevice_Id();
//	    List<String> deviceIdsWithBatteryLevel = new ArrayList<>();
//
//	    for (String deviceId : deviceIds) {
//	        Devices device = devicerepo.findByDevice_Id(deviceId);
//
//	        if ("Available".equals(device.getDeviceStatusReferred())) {
//	            List<DeviceDataStream> latestData = getLatestDataForDevice(deviceId);
//
//	            String batteryLevelInfo = latestData.isEmpty()
//	                    ? deviceId
//	                    : deviceId + " - " + String.valueOf(100 * Float.valueOf(latestData.get(0).getBattery_Level()) - 318) + "%";
//
//	            deviceIdsWithBatteryLevel.add(batteryLevelInfo);
//	        }
//	    }
//
//	    dp.setDevice_Id(deviceIdsWithBatteryLevel);
//	    dp.setBussinesPartnersDetails(getBusinessPartnersDetails(dp.getBusinessPartner()));
//	    dp.setInternalShipmentId(generatedInternalShipmentId);
//
//	    return dp;
//	}
//
//	private List<CustomBP> getBusinessPartnersDetails(List<BPList> bpList) {
//	    List<CustomBP> businessPartnersDetails = new ArrayList<>();
//	    Set<String> processedBPIds = new HashSet<>();
//
//	    for (BPList bp : bpList) {
//	        if (!processedBPIds.contains(bp.getBP_Id())) {
//	            BusinessPartner partner = bussinesRepo.findByBP_Id(bp.getBP_Id());
//	         //   BusinessPartnerDetails businessPartnerDetails = new BusinessPartnerDetails();
//	            CustomBP custBpp = new CustomBP();
//	            custBpp.setBP_Id(partner.getBP_Id());
//	            custBpp.setCompany_Name(partner.getCompany_Name());
//	            custBpp.setEvents(partner.getEvents());
//
//	            businessPartnersDetails.add(custBpp);
//	            processedBPIds.add(bp.getBP_Id());
//	        }
//	    }
//
//	    return businessPartnersDetails;
//	}
//
//	private List<DeviceDataStream> getLatestDataForDevice(String deviceId) {
//	    AggregationOperation match = Aggregation.match(Criteria.where("Device_Id").is(deviceId));
//	    SortOperation sort = Aggregation.sort(Sort.by(Sort.Order.desc("SensorUTC")));
//	    AggregationOperation limit = Aggregation.limit(1);
//
//	    Aggregation aggregation = Aggregation.newAggregation(match, sort, limit);
//
//	    return mongoTemplate.aggregate(aggregation, "DeviceDataStream", DeviceDataStream.class).getMappedResults();
//	}
	
	
	
		
	 @ResponseBody
	 @PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	 @GetMapping("/getDevicesWithBatteryPercentage/{Customer_Id}")
	 public DeviceDropDown getDevicesWithBatteryPercentage(@PathVariable(value = "Customer_Id") String Customer_id) throws Exception {

	  DeviceDropDown dp = null;
	  dp = devicesDropDownrepo.findByCustomer_id(Customer_id.trim());
	  
	  System.out.println("DeviceDropDown Object" + dp);
	  
	  List<String> Devicelist = dp.getDevice_Id();
	  
	  List<String> deviceIDs = new ArrayList<String>();
	  
	  System.out.println("Devicelist from " + Devicelist);

	  String dms = null;

	  // System.out.println("Device_Id"+dp.getDevice_Id()[1]);
	  // String[] Devicelist =dp.getDevice_Id(); 
	  
	  if (Devicelist == null) {
		  
	   dp.setDevice_Id(null);	   
	   return dp;
	  } 
	  else {
	   // for(String cus: deviceIDs) {
	   for (int i = 0; i < Devicelist.size(); i++) {
	    System.out.println("sjm,fbdsjhf" + Devicelist.get(i));
	    Devices dev = devicerepo.findByDevice_Id(Devicelist.get(i));

	    System.out.println("List Values:::" + dev);

	    String status = dev.getDeviceStatusReferred();

	    System.out.println("Status::::" + status);
	    if (status.equals("Available")) {

	     dms = Devicelist.get(i);

	     deviceIDs.add(dms);

	    } else {

	     System.out.println("not in available");
	     dp.setDevice_Id(null);
	    }

	    List<String> deviceIdWithBatteryLevel = new LinkedList<>();
	    for(String deviceId : deviceIDs)
	    {
	        AggregationOperation match = Aggregation.match(Criteria.where("Device_Id").is(deviceId));
	        SortOperation sort = Aggregation.sort(Sort.by(Sort.Order.desc("SensorUTC")));
	        AggregationOperation limit = Aggregation.limit(1);

	        Aggregation aggregation = Aggregation.newAggregation(match, sort, limit);
	        System.out.println(aggregation);

	        List<DeviceDataStream> mappedResults = mongoTemplate.aggregate(aggregation, "DeviceDataStream", DeviceDataStream.class).getMappedResults();
	        System.out.println(mappedResults);
	        if(!mappedResults.isEmpty())
	        {
	            DeviceDataStream deviceDataStream = mappedResults.get(0);
	            deviceIdWithBatteryLevel.add(deviceId+" - "+String.valueOf(100 * Float.valueOf(deviceDataStream.getBattery_Level()) - 318)+"%");
	        }
	        else
	        {
	            deviceIdWithBatteryLevel.add(deviceId);
	        }
	    }
	    dp.setDevice_Id(deviceIdWithBatteryLevel);    
	   /// dp.setDevice_Id(deviceIDs);

	    // String[] listdev = dms.split(",");

	   }

	  }
	  return dp;
}
	 
	 @Autowired
	 private DeviceService deviceService;
	 
	 @Autowired
	 private DeviceBatteryPercentService deviceBatteryPercentService;
	 	 	 
	 @ResponseBody
	 @PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	 @GetMapping("/getAvailableDevicesWithPercentage/{Customer_Id}")
	 //public List<String> getAllAvailableDevices() {
	 public DeviceDropDown getAllAvailableDevices(@PathVariable(value = "Customer_Id") String Customer_id) throws Exception {
		
			DeviceDropDown dp = null;
			dp = devicesDropDownrepo.findByCustomer_id(Customer_id.trim());
			
			// The below list gets all the devices which have Available status in Devices collection. 
			List<String> listOfAvailableDevices = deviceService.getAllAvailableDevices();
			// The below list is a list of devices along with percentage of all the available devices.
			List<String> listOfDevicesWithPercentage = deviceBatteryPercentService
					.getLatestDeviceData(listOfAvailableDevices);

			System.out.println("Devices List");
			System.out.println(listOfDevicesWithPercentage);

			dp.setDevice_Id(listOfDevicesWithPercentage);
			// return deviceService.getAllAvailableDevices();
			return dp;
	 }
	 
     
	 @ResponseBody
	 @PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	 @GetMapping("/availableDeviceIdsForPlant/{plant}")
	 public ResponseEntity<List<String>> getAvailableDeviceIds(@PathVariable(value = "plant") String plant) {
	 //public ResponseEntity<List<String>> getAvailableDeviceIds(@RequestParam("plant") String plant) {
			// Step 1: Retrieve Device IDs related to the selected plant
			List<String> deviceIds = getdeviceIdsByPlant(plant);
			
			System.out.println("deviceIds");
			System.out.println(deviceIds);

			if (!deviceIds.isEmpty()) {
				// Step 2: Filter IoT IDs with "Available" status
				List<String> availableDeviceIds = findAvailableDeviceIds(deviceIds);
				
				List<String> listOfDevicesWithPercentage = deviceBatteryPercentService
						.getLatestDeviceData(availableDeviceIds);
				return ResponseEntity.ok(listOfDevicesWithPercentage);
			}
			
			// If no IoT IDs found, return an empty list
			return ResponseEntity.ok(Collections.emptyList());
	 }

	private List<String> getdeviceIdsByPlant(String plant) {
			// Use aggregation to directly retrieve the IoT IDs array for the selected plant
			Aggregation aggregation = Aggregation.newAggregation(Aggregation.unwind("plantDevicesMapping"),
					Aggregation.match(Criteria.where("plantDevicesMapping.plantCode").is(plant)),					
					Aggregation.unwind("plantDevicesMapping.Device_Ids"),
					Aggregation.project("plantDevicesMapping.Device_Ids")
					 .andExclude("_id"));

			AggregationResults<DeviceIdsOnly> results = mongoTemplate.aggregate(aggregation, "Customer",
					DeviceIdsOnly.class);
			System.out.println(results);
			
			return results.getMappedResults().stream()
		            .map(DeviceIdsOnly::getDevice_Ids)
		            .collect(Collectors.toList());                      			 
		}
		
		private static class DeviceIdsOnly {
		    		    	    		    		  		  	    
		    public String getDevice_Ids() {
				return Device_Ids;
			}

			public void setDevice_Ids(String device_Ids) {
				Device_Ids = device_Ids;
			}

			private String Device_Ids;
		    		    
		}

		private List<String> findAvailableDeviceIds(List<String> deviceIds) {
			
			System.out.println("deviceIds in findAvailableDeviceIds ");
			System.out.println(deviceIds);
		    // Define the aggregation pipeline
		    Aggregation aggregation = Aggregation.newAggregation(
		        // Match IoT IDs and filter by "Available" status
		        Aggregation.match(Criteria.where("Device_Id").in(deviceIds).and("DeviceStatusReferred").is("Available")),

		        // Project only the _id field
		        Aggregation.project("Device_Id").andExclude("_id")
		    );
		    
		 // Execute the aggregation query
		    AggregationResults<AvailableDeviceIds> results = mongoTemplate.aggregate(aggregation, "Devices", AvailableDeviceIds.class);

		    // Extract and return the available IoT IDs
		    return results.getMappedResults().stream()
		            .map(AvailableDeviceIds::getDevice_Id) // Use the DTO's getter
		            .collect(Collectors.toList());
		}
		
		private static class AvailableDeviceIds {
			
		    public String getDevice_Id() {
				return Device_Id;
			}

			public void setDevice_Id(String device_Id) {
				Device_Id = device_Id;
			}
			
			private String Device_Id;
		}
	 
	 	 
	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/getSavedDraft/{Internal_Shipment_Id}")
	public ShipmentDraftDto getSavedDraft(@PathVariable(value = "Internal_Shipment_Id") String Internal_Shipment_id) {
		return savedraftRepo.findByInternal_Shipment_id(Internal_Shipment_id);
	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/customerGetSavedDraft/{Customer_Id}")
	public List<ShipmentDraftDto> customerGetSavedDraft(@PathVariable(value = "Customer_Id") String Customer_Id) {
		return savedraftRepo.findByCustomer_Id(Customer_Id);
	}
	/*
	 * @ResponseBody
	 * 
	 * @GetMapping("/getAllDrafts") public List<ShipmentDraftDto>
	 * getAllSavedDrafts() { return savedraftRepo.findAll(); }
	 */

	//////////////////////////// These are the Wrong Data Model for the Shipment
	//////////////////////////// --- For the Initial Requirement
	//////////////////////////// so Please exempt its ////////////////////

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/getDropdowndata/{Dp_Id}")
	public DropDownDto getddData(@PathVariable(value = "Dp_Id") String Dp_id) {
		return dprepo.findByDp_id(Dp_id.trim());

	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@PostMapping("/createShipmentDraft")
	public boolean createShipmentDrafts(@Validated @RequestBody  ShipmentDrafts drafts) {

		System.out.println();
		boolean flag = false;
		ShipmentDrafts sd;
		try {
			// Form Validation should be done at Frontend
			if (drafts.getInternal_Shipment_Id() != null) {
				System.out.println("Finding weather the Shipment Number Exists or Not");
				List<ShipmentDrafts> Slist = shipmentDraftsRepo.findAll();
				for (ShipmentDrafts duList : Slist) {
					if (duList.getShipment_Number().equals(drafts.getShipment_Number())) {
						System.out.println("The Shipment Number already exists .. so Please another Shipment Number");
						flag = false;
						return flag;
					}
				}
				System.out.println("data validated going to create Shipment");
				sd = shipmentDraftsRepo.insert(drafts);
				System.out.println("data persisted");
				flag = true;
			} else {
				System.out.println("Please send the valid data");
				flag = false;
			}
		} catch (Exception e) {
		}
		return flag;

	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/getDraftData/{Internal_Shipment_Id}")
	public ShipmentDraftPartialGet getPartiallySavedData(
			@PathVariable(value = "Internal_Shipment_Id") String Internal_Shipment_id) {
		return shipmentDraftsRepo.findByInternal_Shipment_id(Internal_Shipment_id);
	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@PostMapping("/updateShipmentEvent")
	public boolean updateShipmentEvent(@Validated @RequestBody UpdateShipmentEvent updateEvent) {
		boolean flag = false;
		try {
			List<CreateShipment> list1 = completeShipRepo.findAll();
			for (CreateShipment ships : list1) {
				if (ships.getShipment_Number().equals(updateEvent.getShipment_Number())) {
					System.out.println("Shipment exists");
				}
			}
			System.out.println("Going to Update");
			Query query = new Query(Criteria.where("Shipment_Number").is(updateEvent.getShipment_Number()));
			Update update = new Update();
			update.set("TypeOfReference", updateEvent.getTypeOfReference());
			update.set("ShipmentDescription", updateEvent.getShipmentDescription());
			update.set("EventId", updateEvent.getEventId());
			update.set("EventType", updateEvent.getEventType());
			update.set("PartnerFrom", updateEvent.getPartnerFrom());
			update.set("EventReferenceNumber", updateEvent.getEventReferenceNumber());
			update.set("EventDescription", updateEvent.getEventDescription());
			System.out.println(update);
			mongoTemplate.updateMulti(query, update, "CreateShipment");
			flag = true;

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return flag;
	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@PostMapping("/completeShipment")
	public boolean completeShipment(@Validated @RequestBody CompleteShipmentDto completeDto) {
		boolean flag = false;
		try {
			List<CreateShipment> list1 = completeShipRepo.findAll();
			for (CreateShipment ships : list1) {
				if (ships.getShipment_Number().equals(completeDto.getShipment_Number())) {
					System.out.println("Shipment exists");
				}
			}
			System.out.println("Going to Update");
			Query query = new Query(Criteria.where("Shipment_Id").is(completeDto.getShipment_Number()));
			Update update = new Update();
			update.set("TypeOfReference", completeDto.getTypeOfReference());
			update.set("ReceivingLocation", completeDto.getReceivingLocation());
			update.set("ReceivingReferenceNumber", completeDto.getReceivingReferenceNumber());
			update.set("ReceivingDescription", completeDto.getReceivingDescription());
			update.set("DeviceReturnLocation", completeDto.getDeviceReturnLocation());
			System.out.println(update);
			mongoTemplate.updateMulti(query, update, "CreateShipment");
			flag = true;

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return flag;
	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@PostMapping("/createFinalShipment")
	public boolean createShipment(@Validated @RequestBody ShipmentDrafts event) {

		CreateShipment fullShip = new CreateShipment();
		boolean flag = false;
		try {
			List<CreateShipment> drafts = completeShipRepo.findAll();
			for (CreateShipment ships : drafts) {
				if (ships.getShipment_Number().equals(event.getShipment_Number())) {
					System.out.println("Shipment Already exists");
					flag = false;
					return flag;
				}
			}
			fullShip.setInternal_Shipment_Id(event.getInternal_Shipment_Id());
			fullShip.setCustomerName(event.getCustomerName());
			fullShip.setPartnerName(event.getPartnerName());
			fullShip.setTempRange(event.getTempRange());
			fullShip.setRhrange(event.getRhrange());
			fullShip.setMode(event.getMode());
			fullShip.setInco(event.getInco());
			fullShip.setShipment_Number(event.getShipment_Number());
			fullShip.setTypeOfReference(event.getTypeOfReference());
			fullShip.setRouteDetails(event.getRouteDetails());
			fullShip.setDestination(event.getDestination());
			fullShip.setGoodsType(event.getGoodsType());
			fullShip.setExpectedDelDate(event.getExpectedDelDate());
			fullShip.setAddTagInfo(event.getAddTagInfo());

			// Update event Shipment
			fullShip.setEventId("");
			fullShip.setEventType("");
			fullShip.setPartnerFrom("");
			fullShip.setEventReferenceNumber("");
			fullShip.setEventDescription("");

			// Complete Shipment
			fullShip.setReceivingLocation("");
			fullShip.setReceivingReferenceNumber("");
			fullShip.setReceivingDescription("");
			fullShip.setDeviceReturnLocation("");
			completeShipRepo.insert(fullShip);
			flag = true;

		} catch (Exception e) {
			System.out.println("please enter valid data");
		}

		return flag;
	}

	// getShipmentTransaction and deviceDatastream data
	// DeviceData RestEndPoint
	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/getShipmentTransaction1/{Customer_Id}")
	public List<ShipmentTransactionDeviceData> getShipmentTransactionDeviceData(
			@PathVariable(value = "Customer_Id") String Customer_Id) {
		System.out.println("Customer_ID" + Customer_Id);
		List<ShipmentTransactionDeviceData> shiptranDevData = new ArrayList<ShipmentTransactionDeviceData>();
		List<ShipmentTransactions> shipTrans = new ArrayList<ShipmentTransactions>();
		List<DeviceDataStream> Aldds = new ArrayList<DeviceDataStream>();

		shipTrans = shiptransrepo.findByCustomer_Id(Customer_Id);

		Aldds = devicedatarepo.getShipmentTransactionDeviceData(shipTrans.get(0).getDevice_Id().trim());
		System.out.println("SSSS:::" + Aldds);
		for (DeviceDataStream Alds : Aldds) {

			for (ShipmentTransactions shpTran : shipTrans) {

				ShipmentTransactionDeviceData shpTranDD = new ShipmentTransactionDeviceData();
				shpTranDD.setShipment_Id(shpTran.getShipment_Id());
				shpTranDD.setShipment_Num(shpTran.getShipment_Num());
				shpTranDD.setEvent_Name(shpTran.getEvent_Name());
				shpTranDD.setMode_of_Transport(shpTran.getMode_of_Transport());
				shpTranDD.setDevice_Id(Alds.getDevice_Id());
				shpTranDD.setBatteryLevel(Alds.getBattery_Level());// shpTranDD.setAltitude(Alds.getAltitude());
//	shpTranDD.setBAT(Alds.getBattery_Level());
//	shpTranDD.setDistance(Alds.getDistance());
//	shpTranDD.setInternal_temperature(Alds.getInternal_temperature());
//	shpTranDD.setLatitude(Alds.getLatitude_in_space());
//	shpTranDD.setLongitude(Alds.getLongitude());
//	shpTranDD.setMessage_Number(Alds.getMessage_Number());
//	shpTranDD.setReport_type(Alds.getReport_type());
//	shpTranDD.setSensor_id(Alds.getSensor_id());
//	shpTranDD.setSpeed(Alds.getSpeed());
//	shpTranDD.setTemp_Measurment(Alds.getTemp_Measurment());
				shpTranDD.setUTC(Alds.getCurrent_terminal_time());
				shpTranDD.setInternal_temperature(Alds.getInternal_temperature());
				shiptranDevData.add(shpTranDD);
			}

		}

		return shiptranDevData;
	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/getShipmentTransactionhistory/{Shipment_Id}")
	public List<ShipmentTransactions> getshipmentstranshistory(@PathVariable String Shipment_Id) {

		List<ShipmentTransactions> sp = shiptransrepo.findByShipment_Id(Shipment_Id.trim());
		 System.out.println(" ShipmentTransactions  list   ");
		 System.out.println(sp);
		List<ShipmentTransactions> shiptranDevData = new ArrayList<ShipmentTransactions>();
		ShipmentTransactions shiphisto = new ShipmentTransactions();
		for (ShipmentTransactions shpTran : sp) {
			shiphisto.setShipment_Id(shpTran.getShipment_Id());
			shiphisto.setShipment_Num(shpTran.getShipment_Num());
			shiphisto.setEvent_Name(shpTran.getEvent_Name());
			shiphisto.setMode_of_Transport(shpTran.getMode_of_Transport());
			shiphisto.setCustomer_Id(shpTran.getCustomer_Id());
			shiphisto.setEvent_SNo(shpTran.getEvent_SNo());
			shiphisto.setDevice_Id(shpTran.getDevice_Id());
			shiphisto.setShip_Date_From_BP(shpTran.getShip_Date_From_BP());
			shiphisto.setExpected_Date_At_BP(shpTran.getExpected_Date_At_BP());
			shiphisto.setPartner_From(shpTran.getPartner_From());
			shiphisto.setPartner_To(shpTran.getPartner_To());
			shiphisto.setEvent_Status(shpTran.getEvent_Status());
			shiphisto.setComments(shpTran.getComments());
			shiphisto.setEvent_Exec_Date(shpTran.getEvent_Exec_Date());
			shiphisto.setTypeOfReference(shpTran.getTypeOfReference());
			shiphisto.setEventReferenceNumber(shpTran.getEventReferenceNumber());
			shiphisto.setEvent_Description(shpTran.getEvent_Description());
			shiphisto.setEvidence_Description(shpTran.getEvidence_Description());
			shiphisto.setInvoice_Number(shpTran.getInvoice_Number());
			shiphisto.setBatch_Id(shpTran.getBatch_Id());
			shiphisto.setEvidence_For(shpTran.getEvidence_For());
			shiphisto.setMaterial_number(shpTran.getMaterial_number());
			shiphisto.setDocCreatedDate(shpTran.getDocCreatedDate());
			shiphisto.setDocId(shpTran.getDocId());
			//shiphisto.setShipment_Num(shpTran.getShipment_Num());
			shiphisto.setPlant(shpTran.getPlant());
			shiptranDevData.add(shiphisto);
		}
		// return sp;
		List<ShipmentTransactions> collect = sp.stream().sorted((shipmentTransaction1, shipmentTransaction2) -> shipmentTransaction1.getEvent_Id().compareTo(shipmentTransaction2.getEvent_Id())).collect(Collectors.toList());
		return collect;
	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/getShipmentTransactionDeviceDataLast/{Shipment_Id}")
	public List<ShipmentTransactionDeviceData> getShipmentTransactionLast(
			@PathVariable(value = "Shipment_Id") String Shipment_Id) {

		List<ShipmentTransactionDeviceData> shiptranDevData = new ArrayList<ShipmentTransactionDeviceData>();		
		List<ShipmentTransactions> shipTrans = new ArrayList<ShipmentTransactions>();		
		DeviceDataStream dds = new DeviceDataStream();

		shipTrans = shiptransrepo.findByShipment_Id(Shipment_Id.trim());
		Shipments shipment = shiprepo.findByShipment_Id(Shipment_Id);
		
		
		if (shipTrans.size() != 0) {

			dds = devicedatarepo.getDeviceDataStreamSingleDocumentDate(shipTrans.get(0).getDevice_Id().trim());
			ShipmentTransactionDeviceData shpTranDD = new ShipmentTransactionDeviceData();
			for (ShipmentTransactions shpTran : shipTrans) {
				System.out.println("ShipmentTransactions -----complete::::     " + shpTran);
//				if (shpTran.getEvent_Status().equals("Initialized")) {
//
//					break;
//				}

				shpTranDD.setShipment_Id(shpTran.getShipment_Id());
				shpTranDD.setShipment_Num(shpTran.getShipment_Num());
				shpTranDD.setEvent_Name(shpTran.getEvent_Name());
				shpTranDD.setEvent_SNo(shpTran.getEvent_SNo());
				shpTranDD.setEvent_Status(shpTran.getEvent_Status());
				shpTranDD.setEvent_Exec_Date(shpTran.getEvent_Exec_Date());
				shpTranDD.setMode_of_Transport(shpTran.getMode_of_Transport());
				
				shpTranDD.setPo_Number(shipment.getPo_Number());
				shpTranDD.setPoItmNumber(shipment.getPoItmNumber());
				shpTranDD.setNdc_Number(shipment.getNdc_Number());
				shpTranDD.setInvoice_Number(shipment.getInvoice_Number());
				shpTranDD.setShipper_Number(shipment.getShipper_Number());
				shpTranDD.setSerial_Number_of_goods(shipment.getSerial_Number_of_goods());
				shpTranDD.setBatch_Id(shipment.getBatch_Id());
				shpTranDD.setCmo_Ref_Number(shipment.getCmo_Ref_Number());
				
				shpTranDD.setComments(shipment.getComments());
				shpTranDD.setMaterial_number(shipment.getMaterial_number());				
				shpTranDD.setFromPlant(shpTran.getPlant());							
//				shpTranDD.setReport_type(dds.getReport_type());			
//				shpTranDD.setSpeed(dds.getSpeed_in_mph());
				
				try {	
					shpTranDD.setInternal_temperature(dds.getInternal_temperature());
					shpTranDD.setDistance(dds.getReportDistance());
					shpTranDD.setLatitude(dds.getLatitude());
					shpTranDD.setLongitude(dds.getLongitude());
					shpTranDD.setMessage_Number(dds.getMessage_Type());
					shpTranDD.setReport_type(dds.getReport_type());
					shpTranDD.setSensor_id(dds.getSensorUTC());
					shpTranDD.setSpeed(dds.getSpeed_in_mph());
					shpTranDD.setTemp_Measurment("C");
					shpTranDD.setUTC(dds.getSensorUTC());
					shpTranDD.setPartner_From(shpTran.getPartner_From());
					shpTranDD.setPartner_To(shpTran.getPartner_To());
					shpTranDD.setAddress(dds.getAddress());

				}
				catch(Exception e) {
					System.out.println("Exception in Device Data- getShipmentTransactionDeviceDataLast API");
					e.printStackTrace();
				}
				
//		        List<String> possibleFormats = Arrays.asList(
//		                "yyyy/MM/dd", "MM/dd/yyyy", "dd/MM/yyyy", "yyyy-MM-dd", "MM-dd-yyyy", "dd-MM-yyyy",
//		                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "yyyy-MM-dd HH:mm:ss"
//		            );
		     String dateStr = null;
	///	     for (String format : possibleFormats) {
				try {
	///			     dateStr = shipment.getEstimated_Delivery_Date();
//			         SimpleDateFormat sdf_created = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	//			     SimpleDateFormat sdf_created = new SimpleDateFormat(format);
	///		         Date date_created = sdf_created.parse(dateStr);
	//		         sdf_created = new SimpleDateFormat("yyyy-MM-dd");
		//	         dateStr = sdf_created.format(date_created);
			    
//				     shpTranDD.setEstimated_Delivery_Date(dateStr);
			         shpTranDD.setEstimated_Delivery_Date(shipment.getEstimated_Delivery_Date());
			//         break;
				}
				catch (Exception e){
					 System.out.println("::: Parse Exception in Estimated Delivery Date - try to check in getShipmentTransactionDeviceDataLast API :::");
					 e.printStackTrace();
				}
	///	    }
		    if (dateStr == null) {
		            System.out.println("Unable to parse date: " + dateStr);
		        }		     
				shpTranDD.setRoute_Details(shipment.getRoute_From()+"-"+shipment.getRoute_To()+","+shipment.getMode());
				shpTranDD.setGoods_Type(shipment.getGoods_Desc());
				try {
					String maxValueString = "4.2";
					float batteryLevel = Float.parseFloat(dds.getBattery_Level());
					float maxValue = Float.parseFloat(maxValueString);
					float percentage = (batteryLevel / maxValue) * 100;
					if(!dds.getBattery_Level().equals("") || !dds.getBattery_Level().equals(null)) {
					shpTranDD.setBatteryLevel(String.valueOf(Math.round(percentage * 100.0) / 100.0));
					}
//				shpTranDD.setBatteryLevel(String.valueOf(100 * Float.valueOf(dds.getBattery_Level()) - 318));
				}
				catch(Exception e) {
					System.out.println(e+ "  Null pointer Exception in Battery Level");
					e.printStackTrace();
				}
				//shpTranDD.setDevice_Id(shpTran.getDevice_Id());

				if(!shpTran.getDevice_Id().equals(null) || !shpTran.getDevice_Id().equals("")) {
					
					shpTranDD.setDevice_Id(shpTran.getDevice_Id());
					break;			
				}else {
					shpTranDD.setDevice_Id("Device is not Attached");
					
				}
		
			//  shpTranDD.setAltitude(dds.getAltitude());
		//	    shpTranDD.setBatteryLevel(dds.getBattery_Level());
//				shpTranDD.setBatteryLevel(String.valueOf(100 * Float.valueOf(dds.getBattery_Level()) - 318));
				System.out.println("Batterylevel::::" + dds.getBattery_Level());
					
			//  shpTranDD.setTemp_Measurment(dds.getTemp_Measurment());

//				shpTranDD.setPo_Number(shipment.getPo_Number());
//				shpTranDD.setNdc_Number(shipment.getNdc_Number());
//				shpTranDD.setInvoice_Number(shipment.getInvoice_Number());
//				shpTranDD.setShipper_Number(shipment.getShipper_Number());
//				shpTranDD.setSerial_Number_of_goods(shipment.getSerial_Number_of_goods());
//				shpTranDD.setBatch_Id(shipment.getBatch_Id());
//				shpTranDD.setCmo_Ref_Number(shipment.getCmo_Ref_Number());
			}
			shiptranDevData.add(shpTranDD);
		

		}
		System.out.println("returns::::" + shiptranDevData);
		return shiptranDevData;
		

	}
	/*
	 * @ResponseBody
	 * 
	 * @GetMapping("/getShipmentTransactionDeviceData/{Shipment_Id}") public
	 * List<ShipmentTransactionDeviceData>
	 * getShipmentTransaction(@PathVariable(value = "Shipment_Id") String
	 * Shipment_Id) {
	 * 
	 * List<ShipmentTransactionDeviceData> shiptranDevData = new
	 * ArrayList<ShipmentTransactionDeviceData>(); List<ShipmentTransactions>
	 * shipTrans = new ArrayList<ShipmentTransactions>(); List<DeviceDataStream>dds=
	 * new ArrayList<DeviceDataStream>(); //List<DeviceDataStream> dds = new Arra
	 * DeviceDataStream();
	 * 
	 * shipTrans = shiptransrepo.findByShipment_Id(Shipment_Id.trim());
	 * 
	 * if(shipTrans.size()!=0){
	 * 
	 * 
	 * dds =
	 * devicedatarepo.getShipmentTransactionDeviceData(shipTrans.get(0).getDevice_Id
	 * ().trim());
	 * 
	 * for(ShipmentTransactions shpTran : shipTrans){ for(DeviceDataStream
	 * devicestream :dds) {
	 * 
	 * 
	 * 
	 * 
	 * ShipmentTransactionDeviceData shpTranDD = new
	 * ShipmentTransactionDeviceData();
	 * 
	 * shpTranDD.setShipment_Id(shpTran.getShipment_Id());
	 * shpTranDD.setShipment_Num(shpTran.getShipment_Num());
	 * shpTranDD.setEvent_Name(shpTran.getEvent_Name());
	 * shpTranDD.setEvent_SNo(shpTran.getEvent_SNo());
	 * shpTranDD.setMode_of_Transport(shpTran.getMode_of_Transport());
	 * shpTranDD.setDevice_Id(shpTran.getDevice_Id()); //
	 * shpTranDD.setAltitude(dds.getAltitude()); // shpTranDD.setBAT(dds.getBAT());
	 * // shpTranDD.setDistance(dds.getDistance());
	 * shpTranDD.setInternal_temperature(devicestream.getInternal_temperature());
	 * shpTranDD.setLatitude(devicestream.getLatitude());
	 * shpTranDD.setLongitude(devicestream.getLongitude()); //
	 * shpTranDD.setMessage_Number(dds.getMessage_Number()); //
	 * shpTranDD.setReport_type(dds.getReport_type()); //
	 * shpTranDD.setSensor_id(dds.getSensor_id()); //
	 * shpTranDD.setSpeed(dds.getSpeed());
	 * //shpTranDD.setTemp_Measurment(dds.getTemp_Measurment());
	 * shpTranDD.setUTC(devicestream.getUTC());
	 * shpTranDD.setAddress(devicestream.getAddress());
	 * shpTranDD.setPartner_From(shpTran.getPartner_From());
	 * shpTranDD.setPartner_To(shpTran.getPartner_To());
	 * shiptranDevData.add(shpTranDD); } }
	 * 
	 * }
	 * 
	 * return shiptranDevData;
	 * 
	 * }
	 */
	// getShipmentTransaction and deviceDatastream data
	// DeviceData RestEndPoint

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/getShipmentTransactionDeviceData/{Shipment_Id}")
	public List<ShipmentTransactionDeviceData> getShipmentTransaction(
			@PathVariable(value = "Shipment_Id") String Shipment_Id) {

		List<ShipmentTransactionDeviceData> shiptranDevData = new ArrayList<ShipmentTransactionDeviceData>();
		List<ShipmentTransactions> shipTrans = new ArrayList<ShipmentTransactions>();
		DeviceDataStream dds = new DeviceDataStream();

		shipTrans = shiptransrepo.findByShipment_Id(Shipment_Id.trim());
		Shipments shipment = shiprepo.findByShipment_Id(Shipment_Id);

		if (shipTrans.size() != 0) {

			dds = devicedatarepo.getDeviceDataStreamSingleDocumentDate(shipTrans.get(0).getDevice_Id().trim());
			System.out.println("smndjbfks" + shipTrans.get(0).getDevice_Id().trim());
//			System.out.println("smndjbfks" + dds.getLatitude());
			ShipmentTransactionDeviceData shpTranDD = new ShipmentTransactionDeviceData();
			for (ShipmentTransactions shpTran : shipTrans) {
//				System.out.println("sk,dfj" + dds.getLatitude());
//				ShipmentTransactionDeviceData shpTranDD = new ShipmentTransactionDeviceData();
				shpTranDD.setShipment_Id(shpTran.getShipment_Id());
				shpTranDD.setShipment_Num(shpTran.getShipment_Num());
				shpTranDD.setEvent_Name(shpTran.getEvent_Name());
				shpTranDD.setEvent_SNo(shpTran.getEvent_SNo());
				shpTranDD.setEvent_Status(shpTran.getEvent_Status());
				shpTranDD.setEvent_Exec_Date(shpTran.getEvent_Exec_Date());
				shpTranDD.setMode_of_Transport(shpTran.getMode_of_Transport());
				shpTranDD.setDevice_Id(shpTran.getDevice_Id()); //
				
				shpTranDD.setPo_Number(shipment.getPo_Number());
				shpTranDD.setPoItmNumber(shipment.getPoItmNumber());
				shpTranDD.setNdc_Number(shipment.getNdc_Number());
				shpTranDD.setInvoice_Number(shipment.getInvoice_Number());
				shpTranDD.setShipper_Number(shipment.getShipper_Number());
				shpTranDD.setSerial_Number_of_goods(shipment.getSerial_Number_of_goods());
				
				shpTranDD.setBatch_Id(shipment.getBatch_Id());
				shpTranDD.setMaterial_number(shipment.getMaterial_number());
				shpTranDD.setRoute_Details(shipment.getRoute_From()+"-"+shipment.getRoute_To()+","+shipment.getMode());
				shpTranDD.setSpeed(dds.getSpeed_in_mph());
				shpTranDD.setTemp_Measurment("C");
				shpTranDD.setGoods_Type(shipment.getGoods_Desc());
				shpTranDD.setInternal_temperature(dds.getInternal_temperature());
				shpTranDD.setReport_type(dds.getReport_type());
				shpTranDD.setEstimated_Delivery_Date(shipment.getEstimated_Delivery_Date());
				shpTranDD.setComments(shipment.getComments());
				shpTranDD.setEvent_Exec_Date(shpTran.getEvent_Exec_Date());
				shpTranDD.setCmo_Ref_Number(shipment.getCmo_Ref_Number());
				
				// shpTranDD.setAltitude(dds.getAltitude());
				// shpTranDD.setBAT(dds.getBAT());
				// shpTranDD.setDistance(dds.getReportDistance());
				// shpTranDD.setInternal_temperature(dds.getInternal_temperature());
				try {
				shpTranDD.setLatitude(dds.getLatitude());
				shpTranDD.setLongitude(dds.getLongitude());
				// shpTranDD.setMessage_Number(dds.getMessageType()); //
				shpTranDD.setReport_type(dds.getReport_type());
				shpTranDD.setSensor_id(dds.getSensorUTC());
				shpTranDD.setSpeed(dds.getSpeed_in_mph()); //
				// shpTranDD.setTemp_Measurment(dds.getTemp_Measurment());
				shpTranDD.setUTC(dds.getSensorUTC());
				shpTranDD.setPartner_From(shpTran.getPartner_From());
				shpTranDD.setPartner_To(shpTran.getPartner_To());
				shpTranDD.setAddress(dds.getAddress());
				try {
					String maxValueString = "4.2";
					float batteryLevel = Float.parseFloat(dds.getBattery_Level());
					float maxValue = Float.parseFloat(maxValueString);
					float percentage = (batteryLevel / maxValue) * 100;
				
				shpTranDD.setBatteryLevel(String.valueOf(Math.round(percentage * 100.0) / 100.0));
//				shpTranDD.setBatteryLevel(String.valueOf(100 * Float.valueOf(dds.getBattery_Level()) - 318));
				}
				catch(Exception e) {
					System.out.println("Exception in Battery Level in getShipmentTransactionDeviceData API "+ e);
					e.printStackTrace();
				}
			}
				catch(Exception e) {
					System.out.println("Exception in Device Data- getShipmentTransactionDeviceData");
					e.printStackTrace();
				}
//				shpTranDD.setBatteryLevel(String.valueOf(100 * Float.valueOf(dds.getBattery_Level()) - 318));			

											
//				shiptranDevData.add(shpTranDD);
			}
			shiptranDevData.add(shpTranDD);
		}

		return shiptranDevData;

	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/getWayInfo/{Shipment_Id}")
	public Set<ShipmentWayinfo> getWayInfo(@PathVariable(value = "Shipment_Id") String Shipment_Id) {

		List<ShipmentWayinfo> devicdatalist = new ArrayList<>();

		List<DeviceDataStream> deviceinfo = new ArrayList<>();

		List<String[]> arraydata = null;
		Shipments shipdetails = shiprepo.findByShipment_Id(Shipment_Id.trim());

		String Device_id = shipdetails.getDevice_Id();
		String Created_Date = shipdetails.getCreated_Date();

		deviceinfo = devicedatarepo.getShipmentTransactionDeviceData(Device_id);

		arraydata = devicedatarepo.getlatLong(Device_id, Created_Date);

		for (String[] datastream : arraydata) {

			ShipmentWayinfo shiptranDevData = new ShipmentWayinfo();
			shiptranDevData.setDevice_Id(datastream[4]);
			shiptranDevData.setAddress(datastream[2]);
			shiptranDevData.setShipment_Num(shipdetails.getShipment_Num());
			shiptranDevData.setSensorUTC(datastream[3]);

			// devicelist.add(devicdatalist);

			String latlong = datastream[0] + "," + datastream[1];

			// String[] s=latlong.split(",");

			shiptranDevData.setWayPoints(latlong);
			devicdatalist.add(shiptranDevData);

		}

		Set<ShipmentWayinfo> devicelist = new LinkedHashSet<ShipmentWayinfo>(devicdatalist);

		/*
		 * @ResponseBody
		 * 
		 * @GetMapping("/getCanCompleteShipment/{Shipment_Id}/{Login_Bp}") public
		 * CanCompleteShipmentDto CanCompleteShipment(@PathVariable(value =
		 * "Shipment_Id") String Shipment_Id,
		 * 
		 * @PathVariable(value = "Login_Bp") String Login_Bp) { CanCompleteShipmentDto
		 * dto = new CanCompleteShipmentDto(); List<ShipmentTransactions> sp = new
		 * ArrayList<>(); Criteria crt = new Criteria();
		 * crt.andOperator(Criteria.where("Shipment_Id").is(Shipment_Id)); Query query =
		 * new Query(crt); sp = mongoTemplate.find(query, ShipmentTransactions.class);
		 * for (ShipmentTransactions tr : sp) { if
		 * (tr.getEvent_Name().equals("Final Receipt") &&
		 * tr.getPartner_From().equals(Login_Bp)) { dto.setCanComplete(true); } }
		 * System.out.println(dto.isCanComplete()); if
		 * ("false".equals(dto.isCanComplete())) { dto.setCanComplete(false); } return
		 * dto; }
		 */

		return devicelist;

	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@PostMapping("/filterParam")
	public Response filterParameters(@Validated @RequestBody Search searchdto) throws Exception {

		Response response = new Response();
		Search searchparam = new Search();
		Customer customer = customerepo.findByCustomerId(searchdto.getCustomer_Id());

		try {

			if (customer.getCustomer_Id().equals(searchdto.getCustomer_Id())) {
				searchparam.setDeliveryNumber(searchdto.getDeliveryNumber());
				searchparam.setFrom(searchdto.getFrom());
				searchparam.setTo(searchdto.getTo());
				searchparam.setGoods(searchdto.getGoods());
				searchparam.setCustomer_Id(searchdto.getCustomer_Id());
				searchparam.setReference(searchdto.getReference());
				searchparam.setShipDate(searchdto.getShipDate());
				searchparam.setDevice(searchdto.getDevice());
				searchparam.setDept(searchdto.getDept());

				response.setStatus(true);
				response.setMessage("Passing the Parameters");
				return response;
			} else {
				response.setStatus(false);
				response.setMessage("please check your customer_Id");
				return response;
			}
		}

		catch (Exception e) {

		}

		return response;

	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/searchfilter")
	public List<Shipments> searchfilter(@RequestParam Map<String, String> searchvalues) {

		System.out.println("In list" + searchvalues.get("from"));
		System.out.println("In list" + searchvalues.get("to"));

		List<Shipments> shipmentsdetails = new ArrayList<Shipments>();

		// List<Shipments>
		// searchdata=shiprepo.findByRoute_FromandRoute_To(Route_From,Route_To,Goods_Desc);
		Query query = new Query();

		if (searchvalues.get("from") != null) {
			Criteria nameCriteria = Criteria.where("Route_From")
					.is(Pattern.compile(searchvalues.get("from"), Pattern.CASE_INSENSITIVE));
			query.addCriteria(nameCriteria);
		}

		if (searchvalues.get("to") != null) {
			Criteria nameCriteria1 = Criteria.where("Route_To")
					.is(Pattern.compile(searchvalues.get("to"), Pattern.CASE_INSENSITIVE));
			query.addCriteria(nameCriteria1);
		}

		if (searchvalues.get("goods") != null) {
			Criteria nameCriteria2 = Criteria.where("Goods_Desc")
					.is(Pattern.compile(searchvalues.get("goods"), Pattern.CASE_INSENSITIVE));

			query.addCriteria(nameCriteria2);
		}
		if (searchvalues.get("Device_Id") != null) {
			Criteria nameCriteria3 = Criteria.where("Device_Id").is(searchvalues.get("Device_Id"));
			query.addCriteria(nameCriteria3);
		}
		
		 //Previously shown as Delivery No. in UI and stored as Shipment_Num in backend
		 //Now in UI it will be Invoice No. and backend search is also on Invoice
//		if (searchvalues.get("Shipment_Num") != null) {
//			Criteria nameCriteria4 = Criteria.where("Shipment_Num").is(searchvalues.get("Shipment_Num"));
//			query.addCriteria(nameCriteria4);
//		}
        if (searchvalues.get("Invoice_Number") != null) {
            Criteria nameCriteria4 = Criteria.where("Invoice_Number").is(searchvalues.get("Invoice_Number"));
            query.addCriteria(nameCriteria4);
        }		
		if (searchvalues.get("created_Date") != null) {
			//String date[] = searchvalues.get("created_Date").split("T");

//			Criteria nameCriteria5 = Criteria.where("Created_Date")/* .is(date[0]+"T00:00:00.000Z") */
//					.gte(date[0] + "T00:00:00.000Z");
			Criteria nameCriteria5 = Criteria.where("Created_Date")/* .is(date[0]+"T00:00:00.000Z") */
					.gte(searchvalues.get("created_Date"));
			query.addCriteria(nameCriteria5);

		}
		/*
		 * if (searchvalues.get("Created_Date") != null) { Criteria nameCriteria5 =
		 * Criteria.where("Created_Date").is(searchvalues.get("Created_Date"));
		 * query.addCriteria(nameCriteria5); }
		 */
		if (searchvalues.get("Customer_Id") != null) {
			Criteria nameCriteria6 = Criteria.where("Customer_Id").is(searchvalues.get("Customer_Id"));
			query.addCriteria(nameCriteria6);
		}
//		if (searchvalues.get("reference") != null) {
//			Criteria nameCriteria6 = Criteria.where("Type_Of_Reference").is(searchvalues.get("reference"));
//			query.addCriteria(nameCriteria6);
//		}
        if (searchvalues.get("Shipment_Num") != null) {
            Criteria nameCriteria6 = Criteria.where("Shipment_Num").is(searchvalues.get("Shipment_Num"));
            query.addCriteria(nameCriteria6);
        }
        if (searchvalues.get("Shipment_Status") != null) {
            Criteria nameCriteria6 = Criteria.where("Shipment_Status").is(searchvalues.get("Shipment_Status"));
            //Criteria nameCriteria6 = Criteria.where("Shipment_Status").is("Preparation");
            query.addCriteria(nameCriteria6);
        }
		if (searchvalues.get("plant") != null) {
			Criteria fromPlantCriteria = Criteria.where("plant").is(searchvalues.get("plant"));
			query.addCriteria(fromPlantCriteria);
		}
		List<Shipments> response = new ArrayList<>();
		response = mongoTemplate.find(query, Shipments.class, "Shipments");
		System.out.println("response " + response);

		for (Shipments ship : response) {

			List<String[]> device = devicedatarepo.getlatLang(ship.getDevice_Id(), ship.getCreated_Date());
			ship.setWayPoints(device);
			ship.setEvent_Status(shiptransrepo.event_status(ship.getShipment_Id().trim()));
			ship.setDelivered_Status(ship.getShipment_Status());
			shipmentsdetails.add(ship);

		}

		return shipmentsdetails;
	}
	
	
	@ResponseBody
	@PostMapping("/updateDeviceandEventStatus")
	public boolean updateDeviceandEventStatus(@Validated @RequestBody UpdateEventMassLoad eventMassLoad) throws Exception {	
//	public boolean updateDeviceandEventStatus(@RequestParam("invoiceNumber") String invoiceNumber,
//			@RequestParam("deliveryNumber") String deliveryNumber,
//			@RequestParam("deviceId") String deviceId,
//			@RequestParam("partner") String partner,
//			@RequestParam("partnerFrom") String partnerFrom,
//			@RequestParam("eventName") String eventName,
//			@RequestParam("eventStatus") String eventStatus) throws Exception {


	//	Response resp = new Response();
		boolean flag = false;
		
		Date date = new Date();
		SimpleDateFormat formattr = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		String strDate = formattr.format(date);
		
		System.out.println("Invoice Number ");
		System.out.println(eventMassLoad.getInvoiceNumber());
		System.out.println(eventMassLoad.getDeliveryNumber());
		System.out.println(eventMassLoad.getDeviceId());
		System.out.println(eventMassLoad.getSecondDevice());
		System.out.println(eventMassLoad.getThirdDevice());
		System.out.println(eventMassLoad.getFourthDevice());
		System.out.println(eventMassLoad.getEventName());
		System.out.println(eventMassLoad.getEventStatus());
//		System.out.println(invoiceNumber);
//		System.out.println(deliveryNumber);
//		System.out.println(deviceId);
//		System.out.println(eventName);
//		System.out.println(eventStatus);
		
        List<String> deviceIdList = new ArrayList<>();
        // Add new strings to the list
        deviceIdList.add(eventMassLoad.getDeviceId());
        deviceIdList.add(eventMassLoad.getSecondDevice());
        deviceIdList.add(eventMassLoad.getThirdDevice());
        deviceIdList.add(eventMassLoad.getFourthDevice());
        deviceIdList.add(eventMassLoad.getFifthDevice());
        // Remove null values from the list
        deviceIdList = deviceIdList.stream()
                               .filter(s -> s != "")  // Filter out nulls
                               .collect(Collectors.toList());  // Collect back into a list
        System.out.println("Devices List after removing the nulls");
        System.out.println(deviceIdList);
		
	if(eventMassLoad.getInvoiceNumber()!= "" && eventMassLoad.getDeliveryNumber() == "") {
		
		List<Shipments> shpList = shiprepo.findByInvoice_Number(eventMassLoad.getInvoiceNumber());
		System.out.println("Shipments List");
		System.out.println(shpList);
		
		List<ShipmentTransactions> list = shiptransrepo.findByInvoice_Number(eventMassLoad.getInvoiceNumber());
		System.out.println("Shipment Transactions List");
		System.out.println(list);
	    try {
	    	
		if (!list.isEmpty()) {
			
//			String Shipment_Id = null;
//			for (Shipments sh : shpList) {
//				Shipment_Id = sh.getShipment_Id();
//				System.out.println("Shipment_Id - " + sh.getShipment_Id());
//			}
//			System.out.println("shpmntID"+ Shipment_Id);
			
	//	    Query queryTransac = new Query(Criteria.where("Invoice_Number").is(invoiceNumber));
		  //Query queryTransac = new Query(Criteria.where("Shipment_Id").is(Shipment_Id));
	//		Update updateTransac = new Update();
								
			for (ShipmentTransactions transacList : list) {								
				if (transacList.getEvent_Name().equals(eventMassLoad.getEventName())
						&& (transacList.getEvent_Status().equals("Initialized") || transacList.getEvent_Status().equals("Queued"))) {

				Query query_trans = new Query();
				query_trans.addCriteria(new Criteria().andOperator((Criteria.where("Invoice_Number").is(eventMassLoad.getInvoiceNumber())),
	                    Criteria.where("Event_Name").is(eventMassLoad.getEventName())));
//						Criteria.where("Event_Status").is("Initialized")));
				                       
						Update update_trans = new Update();						
						update_trans.set("Event_Status", eventMassLoad.getEventStatus());						
						update_trans.set("EventReferenceNumber", "");
						update_trans.set("Event_Exec_Date", strDate);
						update_trans.set("Partner", eventMassLoad.getPartner());
						update_trans.set("Partner_From", eventMassLoad.getPartnerFrom());
						System.out.println(query_trans);
						System.out.println(update_trans);
						mongoTemplate.updateMulti(query_trans, update_trans, "ShipmentTransactions");
//						mongoTemplate.updateFirst(query_trans, update_trans, "ShipmentTransactions");
						
						
//						long eventslno = Long.valueOf((transacList.getEvent_SNo() + 1));
//						System.out.println("Long valueeeee::::" + eventslno);
//						ShipmentTransactions eventsno = shiptransrepo.findByEvent_Sno(eventslno);
//						String reqval = eventsno.getEvent_Name();
//						System.out.println("Reqval::::" + reqval);
//						System.out.println("Eventss updating to initailization" + eventsno);
//						System.out.println("query displaying for criteria" + eventsno.getShipment_Id() + "-------------"
//								+ eventsno.getEvent_SNo());
//						Query query2 = new Query();
//						Update update2 = new Update();
//
//						query2.addCriteria(
//								new Criteria().andOperator(Criteria.where("Shipment_Id").is(eventsno.getShipment_Id()),
//										Criteria.where("Event_SNo").is(eventsno.getEvent_SNo())));
//
//						System.out.println("query displaying for criteria" + eventsno.getShipment_Id() + "-------------"
//								+ eventsno.getEvent_SNo());
//
//						update2.set("Event_Status", "Initialized");
//						mongoTemplate.updateMulti(query2, update2, "ShipmentTransactions");
																		
						for (Shipments shipments : shpList) {
//							shipments.setShipment_Status(reqval);
							if(shipments.getShipment_Status().equals("Delivered")) {	
								shipments.setShipment_Status("Delivered");
							}
							else {
							shipments.setShipment_Status("Goods Receipt");
							}
						}												
						shiprepo.saveAll(shpList);

//						Query query1 = new Query();
//						Update update1 = new Update();
//						query1.addCriteria(Criteria.where("Shipment_Id").is(Shipment_Id));
//						update1.set("Shipment_Status", reqval);
//						mongoTemplate.updateFirst(query1, update1, "Shipments");
						
				//	}
						break;		
			 }
//				else {
//					System.out.println("Event Status is not updated ");
//					resp.setMessage(eventName+" Event is not Initialized, so status is not updated");
//					resp.setStatus(false);
//					return resp;
//				}
				
				try {
					if (transacList.getDevice_Id().equals("") || transacList.getDevice_Id().isEmpty()) {										
					Query queryTransac = new Query(Criteria.where("Invoice_Number").is(eventMassLoad.getInvoiceNumber()));	
					
					Update updateTransac = new Update();	
					updateTransac.set("Device_Id", eventMassLoad.getDeviceId());
					updateTransac.set("secondDevice", eventMassLoad.getSecondDevice());
					updateTransac.set("thirdDevice", eventMassLoad.getThirdDevice());
					updateTransac.set("fourthDevice", eventMassLoad.getFourthDevice());
					updateTransac.set("fifthDevice", eventMassLoad.getFifthDevice());			
					updateTransac.set("numberOfDevices", eventMassLoad.getNumberOfDevices());
					
					System.out.println("queryTransac");
					System.out.println(queryTransac);
					System.out.println("updateTransac");
					System.out.println(updateTransac);
					
					mongoTemplate.updateMulti(queryTransac, updateTransac, "ShipmentTransactions");
					System.out.println("mongoTemplate got updated for ShipmentTransactions");
//					mongoTemplate.updateMulti(queryTransac, updateTransac, "Shipments");
//					System.out.println("mongoTemplate got updated for Shipments");
					for (Shipments shipments : shpList) {
						shipments.setDevice_Id(eventMassLoad.getDeviceId());
						shipments.setSecondDevice(eventMassLoad.getSecondDevice());
						shipments.setThirdDevice(eventMassLoad.getThirdDevice());
						shipments.setFourthDevice(eventMassLoad.getFourthDevice());
						shipments.setFifthDevice(eventMassLoad.getFifthDevice());
						shipments.setNumberOfDevices(eventMassLoad.getNumberOfDevices());
					}
					shiprepo.saveAll(shpList);
					System.out.println("mongoTemplate got updated for Shipments");
								        					
					Query query = new Query();
					query.addCriteria(new Criteria().andOperator(Criteria.where("Device_Id").in(deviceIdList)));				
			        //query.addCriteria(new Criteria().andOperator(Criteria.where("Device_Id").is(eventMassLoad.getDeviceId())));
			        Update update = new Update();
			        update.set("DeviceStatusReferred", "Attached To Shipment");
			        mongoTemplate.updateMulti(query, update, "Devices");
			        System.out.println("Status of Device- "+eventMassLoad.getDeviceId()+" is updated in Devices collection");
					
				}															
//					else {
//						System.out.println("Device_id is not updated");
//						resp.setMessage("Shipment with Invoice no. "+invoiceNumber+" is already attached with Device_Id");
//						resp.setStatus(false);
//						return resp;
//					}
//					mongoTemplate.updateMulti(queryTransac, updateTransac, "ShipmentTransactions");
//					mongoTemplate.updateMulti(queryTransac, updateTransac, "Shipments");
				}
				catch(Exception e) {
					e.printStackTrace();
					System.out.println("Exception when updating Device_Id "+e);		
				}
				
				
				if (eventMassLoad.getEventStatus().equals("Cancel")
				&& transacList.getEvent_Name().equals(eventMassLoad.getEventName())) {
					
					transacList.setEvent_Status("Initialized");
					transacList.setEvent_Exec_Date("");
					
					System.out.println("Document after Cancellation of updating Event Status " );
		            System.out.println(transacList);
					
					mongoTemplate.save(transacList);
					
	//			if(!deviceId.isEmpty()) {
					Query queryTransac = new Query(Criteria.where("Invoice_Number").is(eventMassLoad.getInvoiceNumber()));

					Update updateTransac = new Update();
					updateTransac.set("Device_Id", "");

					mongoTemplate.updateMulti(queryTransac, updateTransac, "ShipmentTransactions");
					mongoTemplate.updateMulti(queryTransac, updateTransac, "Shipments");

					Query query = new Query();
					query.addCriteria(
					new Criteria().andOperator(Criteria.where("Device_Id").is(transacList.getDevice_Id())));
					Update update = new Update();
					update.set("DeviceStatusReferred", "Available");
					mongoTemplate.updateMulti(query, update, "Devices");
					System.out.println("Status of Device- " + eventMassLoad.getDeviceId() + " is updated in Devices collection");
										
	//			}
											
			}												
				
		}
			
	}
			flag = true;
			return flag;
      }
		    
	    
		   catch(Exception e) {
		    	e.printStackTrace();
		    	System.out.println("Exception when updating Device and or EventStatus & EventName "+e);			 		
	}
}
	
	
	if(eventMassLoad.getDeliveryNumber() != "" && eventMassLoad.getInvoiceNumber() == "") {
		 try {
		// Delivery Number is the Shipment_Num in ShipmentTransactions
		
		// Update Event_Status in ShipmentTransactions collection
		Query eventUpdateQuery = new Query();
        eventUpdateQuery.addCriteria(Criteria.where("Shipment_Num").is(eventMassLoad.getDeliveryNumber())
                .and("Event_Name").is(eventMassLoad.getEventName())
                .and("Event_Status").in("Initialized", "Queued"));
		
        Update eventUpdate = new Update()
                .set("Event_Status", eventMassLoad.getEventStatus())
                .set("EventReferenceNumber", "")
                .set("Event_Exec_Date", strDate)
                .set("Partner", eventMassLoad.getPartner())
                .set("Partner_From", eventMassLoad.getPartnerFrom());
        mongoTemplate.updateMulti(eventUpdateQuery, eventUpdate, "ShipmentTransactions");
        
        // Update Shipment_Status in Shipments collection
        ///List<Shipments> shipments = mongoTemplate.find(new Query(Criteria.where("Shipment_Num").is(deliveryNumber)), Shipments.class);
    	List<Shipments> shipments = shiprepo.findByShipment_Num(eventMassLoad.getDeliveryNumber());
        if (!shipments.isEmpty()) {
            for (Shipments shipment : shipments) {
                if ("Delivered".equalsIgnoreCase(shipment.getShipment_Status())) {
                    shipment.setShipment_Status("Delivered");
                } else {
                    shipment.setShipment_Status("Goods Receipt");
                }
            }
            shiprepo.saveAll(shipments);
        }
        
        // Update Device_Id if missing in ShipmentTransactions
        Query missingDeviceQuery = new Query(Criteria.where("Shipment_Num").is(eventMassLoad.getDeliveryNumber())
                .orOperator(Criteria.where("Device_Id").exists(false), Criteria.where("Device_Id").is("")));
        Update missingDeviceUpdate = new Update()
        		.set("Device_Id", eventMassLoad.getDeviceId())
          		.set("secondDevice", eventMassLoad.getSecondDevice())
          		.set("thirdDevice", eventMassLoad.getThirdDevice())
          		.set("fourthDevice", eventMassLoad.getFourthDevice())
                .set("fifthDevice", eventMassLoad.getFifthDevice())
                .set("numberOfDevices", eventMassLoad.getNumberOfDevices());
        mongoTemplate.updateMulti(missingDeviceQuery, missingDeviceUpdate, "ShipmentTransactions");
        
    	for (Shipments shpments : shipments) {
    		shpments.setDevice_Id(eventMassLoad.getDeviceId());
    		shpments.setSecondDevice(eventMassLoad.getSecondDevice());
    		shpments.setThirdDevice(eventMassLoad.getThirdDevice());
    		shpments.setFourthDevice(eventMassLoad.getFourthDevice());
    		shpments.setFifthDevice(eventMassLoad.getFifthDevice());
    		shpments.setNumberOfDevices(eventMassLoad.getNumberOfDevices());
		}
		shiprepo.saveAll(shipments);
		
		// Update DeviceStatusReferred in Devices collection
        //Query deviceStatusQuery = new Query(Criteria.where("Device_Id").is(eventMassLoad.getDeviceId()));
        Query deviceStatusQuery = new Query(Criteria.where("Device_Id").in(deviceIdList));
        Update deviceStatusUpdate = new Update().set("DeviceStatusReferred", "Attached To Shipment");
        mongoTemplate.updateMulti(deviceStatusQuery, deviceStatusUpdate, "Devices");
        
        // Handle event cancellation logic
        if ("Cancel".equalsIgnoreCase(eventMassLoad.getEventStatus())) {
            Query cancelQuery = new Query(Criteria.where("Shipment_Num").is(eventMassLoad.getDeliveryNumber())
                    .and("Event_Name").is(eventMassLoad.getEventName()));
            Update cancelUpdate = new Update()
                    .set("Event_Status", "Initialized")
                    .set("Event_Exec_Date", "");

            mongoTemplate.updateMulti(cancelQuery, cancelUpdate, "ShipmentTransactions");
            
            Query cancelDeviceQuery = new Query(Criteria.where("Shipment_Num").is(eventMassLoad.getDeliveryNumber()));
            Update clearDeviceUpdate = new Update().set("Device_Id", "");
            mongoTemplate.updateMulti(cancelDeviceQuery, clearDeviceUpdate, "ShipmentTransactions");
            mongoTemplate.updateMulti(cancelDeviceQuery, clearDeviceUpdate, "Shipments");

            Update deviceAvailableUpdate = new Update().set("DeviceStatusReferred", "Available");
            mongoTemplate.updateMulti(deviceStatusQuery, deviceAvailableUpdate, "Devices");
                                 
        }
        flag = true;
		return flag;
	}
		   catch(Exception e) {
			    System.out.println("Exception when updating Device and or EventStatus & EventName in case of using Delivery Number"+e);	
		    	e.printStackTrace();		    			 		
	}
}
//		resp.setMessage("Device_Id or Event_Status updated Successfully");
//		resp.setStatus(true);
//		return resp;
		return flag;
	}
		
	@ResponseBody
	@PostMapping("/excelfileupload")
	public  List<ErrorExcel> excelfile(@RequestParam("file") MultipartFile reapExcelDataFile,
			@RequestParam("partnerId") String partnerId,
			@RequestParam("partnerFrom") String partnerFrom) throws Exception {
		Date date = new Date();
		SimpleDateFormat formattr = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		String strDate = formattr.format(date);
		
		List<ErrorExcel> errorlist = new ArrayList<ErrorExcel>();			
		ErrorExcel errorexcel = new ErrorExcel();
		
    	ShipmentTransactions trans = null;	
		List<Shipments> shipmentsList = new ArrayList<Shipments>();			

		XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
		XSSFSheet worksheet = workbook.getSheetAt(0);
//		XSSFRow row1 = worksheet.getRow(1);
//		Iterator<Cell> cells = row1.cellIterator();
		DataFormatter formatter = new DataFormatter();

		for(int i=1; i<worksheet.getPhysicalNumberOfRows() ;i++) {
			    errorexcel = new ErrorExcel();
			    trans = new ShipmentTransactions();
			    Shipments shpmtList = new Shipments();
		            
		        XSSFRow row = worksheet.getRow(i);
		        System.out.println(" no of physical cells in a row     "+row.getPhysicalNumberOfCells());
//		        System.out.println(" got columnNum'th cell in a row     "+row.getCell(columnNum));
		        
		        System.out.println(formatter.formatCellValue(row.getCell(0)));
		        System.out.println(formatter.formatCellValue(row.getCell(1)));
		        System.out.println(formatter.formatCellValue(row.getCell(2)));
		        System.out.println(formatter.formatCellValue(row.getCell(3)));
		        System.out.println(formatter.formatCellValue(row.getCell(4)));
		        
		        String shipment_Num ="";
				try {
			        String shipmentNo = formatter.formatCellValue(row.getCell(0));
			        System.out.println("Shipment_Num/DeliveryNum from Excel file is  "+shipmentNo);
					 // number is already 10 digits long, use it as-it is
					if (shipmentNo.length() == 10) {
						shipment_Num = shipmentNo;
						System.out.println("Shipment_Num/DeliveryNum to query is  "+shipment_Num);
					}
					if (shipmentNo.length() == 9) {
						 //shipmentNo = String.format("%010d", Integer.parseInt(shipmentNo));
						 shipmentNo = "0"+ shipmentNo;
						 shipment_Num = shipmentNo;
						 System.out.println("Shipment_Num/DeliveryNum to query is  "+shipment_Num);
					}
					 else {
				      // append two zeros at the beginning of the number
						 shipmentNo = String.format("%010d", Integer.parseInt(shipmentNo));
						 shipment_Num = shipmentNo;
						 System.out.println("shipmentNum/DeliveryNum to query is  "+shipment_Num);
					}
				}
				catch(Exception e){
					System.out.println("Exception in shipmentNum/DeliveryNum");
				}		        		        		        		     		        
		
		        if (row.getPhysicalNumberOfCells() == 5) {

		        System.out.println(formatter.formatCellValue(row.getCell(0)));
		        System.out.println(formatter.formatCellValue(row.getCell(1)));
		        System.out.println("00"+formatter.formatCellValue(row.getCell(2)));
		        System.out.println(formatter.formatCellValue(row.getCell(3)));
		        System.out.println(formatter.formatCellValue(row.getCell(4)));

		    	Query query_spid = new Query();
		    	query_spid.addCriteria(
						//new Criteria().andOperator(Criteria.where("Invoice_Number").is(formatter.formatCellValue(row.getCell(0)))));
		    			//new Criteria().andOperator(Criteria.where("Shipment_Num").is(formatter.formatCellValue(row.getCell(0)))));
		    	          new Criteria().andOperator(Criteria.where("Shipment_Num").is(shipment_Num)));
				List<Shipments> shpid = mongoTemplate.find(query_spid, Shipments.class);
				
				System.out.println("list shpid  ");
				System.out.println(shpid.isEmpty());
				System.out.println("getting shpid list  ");
				System.out.println(shpid);
				
				if(!shpid.isEmpty()) {
		        for(Shipments shpList : shpid) {
		        	if(formatter.formatCellValue(row.getCell(0)).equals(shpList.getInvoice_Number())) {		
		        				        				    		   
		        	}		        	
		        }
		       		      		
				String Shipment_Id = null;
				for (Shipments sh : shpid) {
					Shipment_Id = sh.getShipment_Id();
					System.out.println("result - " + sh.getShipment_Id());
				}
				System.out.println("shpmntID"+ Shipment_Id);
				
				Query query_tran = new Query(Criteria.where("Shipment_Id").is(Shipment_Id));
				Update update_tran = new Update();
				
				String deviceId = "";
				try {
					String device_id = formatter.formatCellValue(row.getCell(2));
					String numAtZero = Character.toString(device_id.charAt(0));
					System.out.println(numAtZero);
//					char charAtZero = device_id.charAt(0);
//					System.out.println(charAtZero);
					if (device_id.length() == 12) {
						System.out.println("in if "+device_id);
						deviceId = device_id;
						update_tran.set("Device_Id", deviceId);
					}
					else if(device_id.length() == 10) {
						deviceId = "00" + device_id;
						update_tran.set("Device_Id", deviceId);
						//update_tran.set("Device_Id", "00" + formatter.formatCellValue(row.getCell(2)));	
					}
					else if(device_id.length() == 9) {
						deviceId = "000" + device_id;
						update_tran.set("Device_Id", deviceId);
						//update_tran.set("Device_Id", "000" + formatter.formatCellValue(row.getCell(2)));	
					}
					else {
						update_tran.set("Device_Id", device_id);
						//update_tran.set("Device_Id", formatter.formatCellValue(row.getCell(2)));
					}
//					if(numAtZero == "0") {
//						System.out.println("in if "+formatter.formatCellValue(row.getCell(2)));
//						update_tran.set("Device_Id", formatter.formatCellValue(row.getCell(2)));
//						
//					}
//					else if (numAtZero != "0" || numAtZero == "1"){
//						System.out.println("in else if "+formatter.formatCellValue(row.getCell(2)));
//						update_tran.set("Device_Id", "00" + formatter.formatCellValue(row.getCell(2)));	
//					}
//					else {
//						System.out.println("in else "+formatter.formatCellValue(row.getCell(2)));
//						update_tran.set("Device_Id", formatter.formatCellValue(row.getCell(2)));
//					}
				}
				catch(Exception e){
					System.out.println("Exception in Device Id");
					e.printStackTrace();
				}
				
				update_tran.set("Invoice_Number", formatter.formatCellValue(row.getCell(1)));
				//update_tran.set("Shipment_Num", formatter.formatCellValue(row.getCell(1)));
//				update_tran.set("Device_Id", "00" + formatter.formatCellValue(row.getCell(2)));	
				mongoTemplate.updateMulti(query_tran, update_tran, "ShipmentTransactions");
///				mongoTemplate.updateMulti(query_tran, update_tran, "Shipments");
								
				List<ShipmentTransactions> list = shiptransrepo.findByShipment_Id(Shipment_Id);
				System.out.println(list);
				for (ShipmentTransactions transactions : list) {
					if (transactions.getEvent_Name().equals(formatter.formatCellValue(row.getCell(3)))
							&& transactions.getShipment_Id().equals(Shipment_Id)) {
						if (transactions.getEvent_Status().equals("Initialized") || transactions.getEvent_Status().equals("Queued")) {
							
							Query query_trans = new Query(); 
							query_trans.addCriteria(
									new Criteria().andOperator((Criteria.where("Shipment_Id").is(Shipment_Id)),
											Criteria.where("Event_Name").is(formatter.formatCellValue(row.getCell(3)))));
										//	Criteria.where("Event_Status").is("Initialized")));
							
							Update update_trans = new Update();							
							update_trans.set("Event_Status", formatter.formatCellValue(row.getCell(4)));						
							update_trans.set("EventReferenceNumber", "");
							update_trans.set("Event_Exec_Date", strDate);
							update_trans.set("Partner", partnerId);
							update_trans.set("Partner_From", partnerFrom);
							//update_trans.set("Event_Status", "Completed");
							//update_trans.set("TypeOfReference", "");			    
							//update_trans.set("EvidenceList", null);
										
						  mongoTemplate.updateMulti(query_trans, update_trans, "ShipmentTransactions");
						//mongoTemplate.updateFirst(query_trans, update_trans, "ShipmentTransactions");
						System.out.println(update_trans);
						
						for(Shipments shipments : shpid) {
							if(shipments.getShipment_Status().equals("Delivered")) {	
								shipments.setShipment_Status("Delivered");
								shipments.setDevice_Id(deviceId);
								shipments.setInvoice_Number(formatter.formatCellValue(row.getCell(1)));
							}
							else {
							shipments.setShipment_Status("Goods Receipt");
							shipments.setDevice_Id(deviceId);
							shipments.setInvoice_Number(formatter.formatCellValue(row.getCell(1)));
							}							
						}
						shiprepo.saveAll(shpid);
						
						Query queryDeviceId = new Query();
						queryDeviceId.addCriteria(new Criteria().andOperator(Criteria.where("Device_Id").is(deviceId)));
				        Update updateStatus = new Update();
				        updateStatus.set("DeviceStatusReferred", "Attached To Shipment");
				        mongoTemplate.updateMulti(queryDeviceId, updateStatus, "Devices");
				        System.out.println("Status of Device- "+deviceId+" is updated in Devices collection");
						
//						long eventslno = Long.valueOf((transactions.getEvent_SNo() + 1));
//						System.out.println("Long valueeeee::::" + eventslno);
//						ShipmentTransactions eventsno = shiptransrepo.findByEvent_Sno(eventslno);
//						String reqval = eventsno.getEvent_Name();
//						System.out.println("Reqval::::" + reqval);
//						System.out.println("Eventss updating to initailization" + eventsno);
//						System.out.println("query displaying for criteria" + eventsno.getShipment_Id() + "-------------"
//								+ eventsno.getEvent_SNo());
//						Query query2 = new Query();
//						Update update2 = new Update();
//
//						query2.addCriteria(
//								new Criteria().andOperator(Criteria.where("Shipment_Id").is(eventsno.getShipment_Id()),
//										Criteria.where("Event_SNo").is(eventsno.getEvent_SNo())));
//
//						System.out.println("query displaying for criteria" + eventsno.getShipment_Id() + "-------------"
//								+ eventsno.getEvent_SNo());
//
//						update2.set("Event_Status", "Initialized");
//						mongoTemplate.updateMulti(query2, update2, "ShipmentTransactions");
//
//						Query query1 = new Query();
//						Update update1 = new Update();
//						query1.addCriteria(Criteria.where("Shipment_Id").is(Shipment_Id));
//						update1.set("Shipment_Status", reqval);
//						mongoTemplate.updateFirst(query1, update1, "Shipments");
						
						
					  }
					}																				
				}								
		}	
				else {
					 System.out.println("shpmntID is null so now in else   ");
					 errorexcel.setDelivery_number(formatter.formatCellValue(row.getCell(0)));
					 errorexcel.setInvoice_number(formatter.formatCellValue(row.getCell(1)));
					 errorexcel.setDevice_Id(formatter.formatCellValue(row.getCell(2)));
					 errorexcel.setEvent_name(formatter.formatCellValue(row.getCell(3)));
					 errorexcel.setEvent_status(formatter.formatCellValue(row.getCell(4)));											
					 mongoTemplate.insert(errorexcel, "ErrorExcel"); 
					 errorlist.add(errorexcel);
				}
				
				continue; 
		}									     				
				 if(row.getPhysicalNumberOfCells() == 4) {
	
				        System.out.println(formatter.formatCellValue(row.getCell(0)));
				        System.out.println("00"+formatter.formatCellValue(row.getCell(1)));
				        System.out.println(formatter.formatCellValue(row.getCell(2)));
				        System.out.println(formatter.formatCellValue(row.getCell(3)));
				        System.out.println("Empty cell    "+formatter.formatCellValue(row.getCell(4)));

		        		Query query_spid = new Query();
						query_spid.addCriteria(
								//new Criteria().andOperator(Criteria.where("Invoice_Number").is(formatter.formatCellValue(row.getCell(0)))));
								//new Criteria().andOperator(Criteria.where("Shipment_Num").is(formatter.formatCellValue(row.getCell(0)))));
						          new Criteria().andOperator(Criteria.where("Shipment_Num").is(shipment_Num)));
						List<Shipments> shpid = mongoTemplate.find(query_spid, Shipments.class);
						System.out.println("list shpid  ");
						System.out.println(shpid.isEmpty());
						System.out.println("getting shpid list  ");
						System.out.println(shpid);
						
						if(!shpid.isEmpty()) {
						
				        for(Shipments shpList : shpid) {
				        	if(formatter.formatCellValue(row.getCell(0)).equals(shpList.getInvoice_Number())) {		
				        				        				    		   
				        	}		        	
				        }
						
						String Shipment_Id = null;
						for (Shipments sh : shpid) {
							Shipment_Id = sh.getShipment_Id();
					    System.out.println("result - " + sh.getShipment_Id());
						}
						System.out.println("shpmntID"+ Shipment_Id);
						
						Query query_tran = new Query(Criteria.where("Shipment_Id").is(Shipment_Id));						
						Update update_tran = new Update();
						
						String deviceId = "";
						try {
							String device_id = formatter.formatCellValue(row.getCell(1));
							String numAtZero = Character.toString(device_id.charAt(0));
							System.out.println("getting char at zero index");
							System.out.println(numAtZero);
							
							if (device_id.length() == 12) {
								System.out.println("in if "+device_id);
								deviceId = device_id;
								update_tran.set("Device_Id", deviceId);
							}
							else if(device_id.length() == 10) {
								deviceId = "00" + device_id;
								update_tran.set("Device_Id", deviceId);	
							}
							else if(device_id.length() == 9) {
								deviceId = "000" + device_id;
								update_tran.set("Device_Id", deviceId);
							}
							else {
								update_tran.set("Device_Id", device_id);
							}							
//							if(numAtZero == "0") {
//								System.out.println("in if "+formatter.formatCellValue(row.getCell(1)));
//								update_tran.set("Device_Id", formatter.formatCellValue(row.getCell(1)));
//								
//							}
//							else if (numAtZero != "0" || numAtZero == "1"){
//								System.out.println("in else if "+formatter.formatCellValue(row.getCell(1)));
//								update_tran.set("Device_Id", "00" + formatter.formatCellValue(row.getCell(1)));	
//							}
//							else {
//								System.out.println("in else "+formatter.formatCellValue(row.getCell(1)));
//								update_tran.set("Device_Id", formatter.formatCellValue(row.getCell(1)));
//							}
						}
						catch(Exception e){
							System.out.println("Exception in Device Id");
						}					
//						update_tran.set("Device_Id", "00" + formatter.formatCellValue(row.getCell(1)));
						mongoTemplate.updateMulti(query_tran, update_tran, "ShipmentTransactions");
//						mongoTemplate.updateMulti(query_tran, update_tran, "Shipments");
										
						List<ShipmentTransactions> list = shiptransrepo.findByShipment_Id(Shipment_Id);
						System.out.println(list);
						for (ShipmentTransactions transactions : list) {
							if (transactions.getEvent_Name().equals(formatter.formatCellValue(row.getCell(2)))
									&& transactions.getShipment_Id().equals(Shipment_Id)) {
								if (transactions.getEvent_Status().equals("Initialized") || transactions.getEvent_Status().equals("Queued")) {
																	
									Query query_trans = new Query();
									query_trans.addCriteria(
											new Criteria().andOperator((Criteria.where("Shipment_Id").is(Shipment_Id)),
													Criteria.where("Event_Name").is(formatter.formatCellValue(row.getCell(2)))));
												//	Criteria.where("Event_Status").is("Initialized")));
									Update update_trans = new Update();
									update_trans.set("Event_Status", formatter.formatCellValue(row.getCell(3)));
									update_trans.set("EventReferenceNumber", "");
									update_trans.set("Event_Exec_Date", strDate);
									update_trans.set("Partner", partnerId);
									update_trans.set("Partner_From", partnerFrom);
									//update_trans.set("TypeOfReference", "");				    
									//update_trans.set("EvidenceList", null);
//								}
														
								//mongoTemplate.updateFirst(query_trans, update_trans, "ShipmentTransactions");
								mongoTemplate.updateMulti(query_trans, update_trans, "ShipmentTransactions");
								System.out.println(update_trans);
								
								for(Shipments shipments : shpid) {
									if(shipments.getShipment_Status().equals("Delivered")) {	
										shipments.setShipment_Status("Delivered");
										shipments.setDevice_Id(deviceId);
									}
									else {
									shipments.setShipment_Status("Goods Receipt");
									shipments.setDevice_Id(deviceId);
									}							
								}
								shiprepo.saveAll(shpid);
								
								Query queryDeviceId = new Query();
								queryDeviceId.addCriteria(new Criteria().andOperator(Criteria.where("Device_Id").is(deviceId)));
						        Update updateStatus = new Update();
						        updateStatus.set("DeviceStatusReferred", "Attached To Shipment");
						        mongoTemplate.updateMulti(queryDeviceId, updateStatus, "Devices");
						        System.out.println("Status of Device- "+deviceId+" is updated in Devices collection");
								
//								long eventslno = Long.valueOf((transactions.getEvent_SNo() + 1));
//								System.out.println("Long valueeeee::::" + eventslno);
//								ShipmentTransactions eventsno = shiptransrepo.findByEvent_Sno(eventslno);
//								String reqval = eventsno.getEvent_Name();
//								System.out.println("Reqval::::" + reqval);
//								System.out.println("Eventss updating to initailization" + eventsno);
//								System.out.println("query displaying for criteria" + eventsno.getShipment_Id() + "-------------"
//										+ eventsno.getEvent_SNo());
//								Query query2 = new Query();
//								Update update2 = new Update();
//
//								query2.addCriteria(
//										new Criteria().andOperator(Criteria.where("Shipment_Id").is(eventsno.getShipment_Id()),
//												Criteria.where("Event_SNo").is(eventsno.getEvent_SNo())));
//
//								System.out.println("query displaying for criteria" + eventsno.getShipment_Id() + "-------------"
//										+ eventsno.getEvent_SNo());
//
//								update2.set("Event_Status", "Initialized");
//								mongoTemplate.updateMulti(query2, update2, "ShipmentTransactions");
//
//								Query query1 = new Query();
//								Update update1 = new Update();
//								query1.addCriteria(Criteria.where("Shipment_Id").is(Shipment_Id));
//								update1.set("Shipment_Status", reqval);
//								mongoTemplate.updateFirst(query1, update1, "Shipments");
							}
						  }																				
						}
					}
					else {
							 System.out.println("shpmntID is null so now in else  ");
							 errorexcel.setDelivery_number(formatter.formatCellValue(row.getCell(0)));
							 errorexcel.setDevice_Id(formatter.formatCellValue(row.getCell(1)));
							 errorexcel.setEvent_name(formatter.formatCellValue(row.getCell(2)));
							 errorexcel.setEvent_status(formatter.formatCellValue(row.getCell(3)));						
							 errorexcel.setInvoice_number("");
							 mongoTemplate.insert(errorexcel, "ErrorExcel"); 
							 errorlist.add(errorexcel);
						}						
						continue; 
				}	
				 
				 if(row.getPhysicalNumberOfCells() == 3) {

				        System.out.println(formatter.formatCellValue(row.getCell(0)));
				        System.out.println(formatter.formatCellValue(row.getCell(1)));
				        System.out.println(formatter.formatCellValue(row.getCell(2)));
				        System.out.println("Empty cell  "+formatter.formatCellValue(row.getCell(3)));
				        System.out.println("Empty cell  "+formatter.formatCellValue(row.getCell(4)));
				        
		        		Query query_spid = new Query();
						query_spid.addCriteria(
								//new Criteria().andOperator(Criteria.where("Invoice_Number").is(formatter.formatCellValue(row.getCell(0)))));
								//new Criteria().andOperator(Criteria.where("Shipment_Num").is(formatter.formatCellValue(row.getCell(0)))));
						          new Criteria().andOperator(Criteria.where("Shipment_Num").is(shipment_Num)));
						List<Shipments> shpid = mongoTemplate.find(query_spid, Shipments.class);
						System.out.println("list shpid  ");
						System.out.println(shpid.isEmpty());
						System.out.println("getting shpid list  ");
						System.out.println(shpid);
						 
					if(!shpid.isEmpty()) {						
						
				        for(Shipments shpList : shpid) {
				        	if(formatter.formatCellValue(row.getCell(0)).equals(shpList.getInvoice_Number())) {		
				        		  System.out.println("in if condition  "+formatter.formatCellValue(row.getCell(0)));
							      System.out.println("in if condition  "+formatter.formatCellValue(row.getCell(1)));
							      System.out.println("in if condition  "+formatter.formatCellValue(row.getCell(2)));
				        	
				        	}	
				        	
			        }
						String Shipment_Id = null;
						for (Shipments sh : shpid) {
							Shipment_Id = sh.getShipment_Id();
					    System.out.println("result - " + sh.getShipment_Id());
						}
						System.out.println("shpmntID  "+Shipment_Id);						
																												
						List<ShipmentTransactions> list = shiptransrepo.findByShipment_Id(Shipment_Id);
						System.out.println("list of Transactions");
						System.out.println(list);
						for (ShipmentTransactions transactions : list) {
							if (transactions.getEvent_Name().equals(formatter.formatCellValue(row.getCell(1)))
									&& transactions.getShipment_Id().equals(Shipment_Id)) {
								if (transactions.getEvent_Status().equals("Initialized") || transactions.getEvent_Status().equals("Queued")) {	
									
									Query query_tran = new Query();
									query_tran.addCriteria(
											new Criteria().andOperator((Criteria.where("Shipment_Id").is(Shipment_Id)),
													Criteria.where("Event_Name").is(formatter.formatCellValue(row.getCell(1)))));
												//	Criteria.where("Event_Status").is("Initialized")));
									
									Update update_trans = new Update();				       
							        update_trans.set("Event_Status", formatter.formatCellValue(row.getCell(2)));
									update_trans.set("EventReferenceNumber", "");
									update_trans.set("Event_Exec_Date", strDate);
									update_trans.set("Partner", partnerId);
									update_trans.set("Partner_From", partnerFrom);
									 //update_trans.set("Event_Status", "Completed");
									//update_trans.set("TypeOfReference", "");											    
									//update_trans.set("EvidenceList", null);
//								}
//							}
								System.out.println("update_trans::::");
								System.out.println(update_trans);
			//					mongoTemplate.updateFirst(query_tran, update_trans, "ShipmentTransactions");
								mongoTemplate.updateMulti(query_tran, update_trans, "ShipmentTransactions");
								
								for(Shipments shipments : shpid) {
									if(shipments.getShipment_Status().equals("Delivered")) {	
										shipments.setShipment_Status("Delivered");
									}
									else {
									shipments.setShipment_Status("Goods Receipt");
									}							
								}
								shiprepo.saveAll(shpid);
												
//								long eventslno = Long.valueOf((transactions.getEvent_SNo() + 1));
//								System.out.println("Long valueeeee::::" + eventslno);
//								ShipmentTransactions eventsno = shiptransrepo.findByEvent_Sno(eventslno);
//								String reqval = eventsno.getEvent_Name();
//								System.out.println("Reqval::::" + reqval);
//								System.out.println("Eventss updating to initailization" + eventsno);
//								System.out.println("query displaying for criteria" + eventsno.getShipment_Id() + "-------------"
//										+ eventsno.getEvent_SNo());
//								Query query2 = new Query();
//								Update update2 = new Update();
//
//								query2.addCriteria(
//										new Criteria().andOperator(Criteria.where("Shipment_Id").is(eventsno.getShipment_Id()),
//												Criteria.where("Event_SNo").is(eventsno.getEvent_SNo())));
//
//								System.out.println("query displaying for criteria" + eventsno.getShipment_Id() + "-------------"
//										+ eventsno.getEvent_SNo());
//
//								update2.set("Event_Status", "Initialized");
//								mongoTemplate.updateMulti(query2, update2, "ShipmentTransactions");
//
//								Query query1 = new Query();
//								Update update1 = new Update();
//								query1.addCriteria(Criteria.where("Shipment_Id").is(Shipment_Id));
//								update1.set("Shipment_Status", reqval);
//								mongoTemplate.updateFirst(query1, update1, "Shipments");	
								
								}
							}																				
						}						  								 	
		          }
						else {
							 System.out.println("shpmntID is null so now in else   ");
							 errorexcel.setDelivery_number(formatter.formatCellValue(row.getCell(0)));
							 errorexcel.setEvent_name(formatter.formatCellValue(row.getCell(1)));
							 errorexcel.setEvent_status(formatter.formatCellValue(row.getCell(2)));
							 errorexcel.setDevice_Id("");
							 errorexcel.setInvoice_number("");
							 mongoTemplate.insert(errorexcel, "ErrorExcel");
							 errorlist.add(errorexcel);
						}
						 continue;   
				 	
				 }	
		    }
		return errorlist;
	}
	
	
  //private static final String HOST = "outlook.office365.com";
 ///private static final String HOST = "smtp.gmail.com";
  //private static final String HOST = "smtp-mail.outlook.com";
  //private static final int PORT = 587;
 ///private static final int PORT = 993;
  //private static final String USERNAME = "abid@exafluence.com";
  //private static final String PASSWORD = "";
    
//	@Value("${spring.mail.host}")
//	private String host;
	
	@Value("${spring.mail.properties.mail.imap.host}")
	private String host;
	
	@Value("${spring.mail.properties.mail.imap.port}")
	private int port;			
    
//	@Value("${spring.mail.username}")
//	private String userName;
//	
//	@Value("${spring.mail.password}")
//	private String password;
	
	@Value("${spring.mail.properties.mail.imap.username}")
	private String userName;
	
	@Value("${spring.mail.properties.mail.imap.password}")
	private String password;
	
	@Value("${application.bucket.name}")
	private String bucketName;
	
	@Value("${s3.bucket2}")
	private String archivedbucketName;
	
	@Autowired
	private AmazonS3 amazonS3Client;
	
//	@Autowired
//	private AmazonS3 amazonS3Client2;
		
	private Set<String> processedAttachments = new HashSet<>();
    
    @PostConstruct
    public void startEmailFetching() {
        // Schedule the email fetching process to run periodically
        // Modify the delay and interval as per your requirements
        Timer timer = new Timer();
        timer.schedule(new EmailFetchingTask(), 0, 20000);
        System.out.println(":::::::: Timer scheduled for Fetching Email :::::::::");
    }
        
    private String generateUniqueId(String previousId) {
        AtomicInteger counter = new AtomicInteger(0);
     // Generate new ID logic
        int id = counter.incrementAndGet();
        String uniqueId = String.format("D%08d", id);
  		System.out.println("uniqueId above if  ");
  		System.out.println(uniqueId);
     // Compare previousId and increment accordingly
        if(previousId == null || previousId == "" || previousId.isEmpty()) {
          //if(previousId == null) {
      		System.out.println("uniqueId  ");
      		System.out.println(uniqueId);
          	return uniqueId;
          }
          //   else if(previousId != null) {
        else {   		
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
       
    private class EmailFetchingTask extends TimerTask {
        @Override
        public void run() {
        	System.out.println(":::::::: Started Fetching Email :::::::::");
       //     int maxRetries = 3;
       //     int retryCount = 0;
       //  while (retryCount < maxRetries) {
        	 //System.out.println(":::::::: Thread started for Email Fetching :::::::::");
           try {
                Properties props = new Properties();
                props.put("mail.store.protocol", "imaps");
               
                Session session = Session.getDefaultInstance(props, null);
                Store store = session.getStore("imaps");
                store.connect(host, port, userName, password);
              //store.connect(HOST, PORT, USERNAME, PASSWORD);
                
                Folder inbox = store.getFolder("INBOX");
                inbox.open(Folder.READ_WRITE);
              //inbox.open(Folder.READ_ONLY);
                
            	Date date = new Date();
        		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        		String strDate = formatter.format(date);	
                
                Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
             // Message[] messages = inbox.getMessages();
                for (Message message : messages) {
                	try {
                	//message.setFlag(Flags.Flag.SEEN, true);		
                	
                    String subject = message.getSubject();
                    
//                    String invoiceNo = null;
//                    Pattern pattern = Pattern.compile("Invoice No\\. (\\d+)");
//                    Matcher matcher = pattern.matcher(subject);
//                    
//                    if (matcher.find()) {
//                    	invoiceNo = matcher.group(1);
//                    }
                    
                 // Initialize variables to store the extracted invoiceNo
                    String invoiceNo = null;
                    
 //                 Pattern invoiceNoPatternRegex = Pattern.compile("Invoice No\\. (\\d+)", Pattern.CASE_INSENSITIVE);
//                  Pattern fdampPatternRegex = Pattern.compile("FDAMP: (\\d+)", Pattern.CASE_INSENSITIVE);
                    Pattern invoiceNoPatternRegex = Pattern.compile("Invoice\\s*No\\.?\\s*(\\d+)", Pattern.CASE_INSENSITIVE); // matches only "Invoice No." from the subject by neglecting dots and spaces and same is the case with below FDA.
                    Pattern fdampPatternRegex = Pattern.compile("FDAMP\\s*:?\\s*(\\d+)", Pattern.CASE_INSENSITIVE);
                                 
                 // Extract invoiceNo using regular expression for "Info No."
                    Matcher invoiceNoMatcher = invoiceNoPatternRegex.matcher(subject);
              ///      System.out.println(" at invoiceNoMatcher");
              /// 	   System.out.println(invoiceNoMatcher.find());
                    boolean invoiceNum = false;
                    boolean fdamp = false;
                    if (invoiceNoMatcher.find()) {
//                    	invoiceNo = invoiceNoMatcher.group().split("\\. ")[1];   //Used when considering dots and spaces.
                    	invoiceNo = invoiceNoMatcher.group(1);
                    	System.out.println("Extracted Invoice Number from PreAlert Email: " + invoiceNo);
                    	invoiceNum= true;
                    }
                    
                 // If invoiceNo is not found using "Info No." pattern, try with "FDAMP" pattern
                    if (invoiceNo == null) {
                        Matcher fdampMatcher = fdampPatternRegex.matcher(subject);
               ///        System.out.println(" at fdampMatcher");
               ///    	  System.out.println(fdampMatcher.find());
                //        System.out.println(fdampMatcher.toString());
                        if (fdampMatcher.find()) {
//                        	invoiceNo = fdampMatcher.group().split(": ")[1];    //Used when considering dots and spaces.
                        	invoiceNo = fdampMatcher.group(1);
                        	System.out.println("Extracted Invoice Number from FDA Email: " + invoiceNo);
                        	fdamp= true;
                        }
                    }
                                                          
                    System.out.println(" invoice  ");
	                System.out.println(invoiceNo);
                    
                    System.out.println(" subject  ");
	                System.out.println(subject);
	                
	                String extractedCompleted = null;
	                Pattern completedPatternRegex = Pattern.compile("\\bCompleted\\b", Pattern.CASE_INSENSITIVE);
	                Matcher completedMatcher = completedPatternRegex.matcher(subject);
	                boolean completedEvent = false;
	                if (completedMatcher.find()) {
	                    extractedCompleted = completedMatcher.group();
	                    completedEvent = true;
	                }
	                System.out.println("Status: " + extractedCompleted);
	                
	                					
					String shipment_Num = null;
					String regex = "\\b\\d{10}\\b"; // Assumes a 10-digit consignment number
					Pattern shipmentNumPattern = Pattern.compile(regex);	 // Create a Pattern object
					Matcher matcher = shipmentNumPattern.matcher(subject);
					
					boolean shpmtNum = false;
					// Check if the pattern is found in the subject line
			        if (matcher.find()) {
			            // Extract and return the matched shipment number
			        	shipment_Num =  matcher.group();
			            shpmtNum = true;
			        }
			        System.out.println("::::::::::: shipment_Num ::::::::::::");
	                System.out.println(shipment_Num);
			        
					
					List<ShipmentTransactions> listshpmtTransac = shiptransrepo.findByShipment_Num(shipment_Num);
					System.out.println(" shipmentTransactions list using shipment_Number ");
					System.out.println(listshpmtTransac);
					
	            	List<ShipmentTransactions> list = shiptransrepo.findByInvoice_Number(invoiceNo);
					System.out.println("Transactions list using Invoice_Number");
					System.out.println(list);
				
					List<String> urlList = new ArrayList<>();
					List<String> fileNameList = new ArrayList<>();
					List<String> typeofEvidenceList = new ArrayList<>();
					List<String> evidenceDescList = new ArrayList<>();
					List<String> evidenceForList = new ArrayList<>();
					List<String> dateList = new ArrayList<>();
					List<String> docIdList = new ArrayList<>();
					
					String previousId = null;
					
			for(ShipmentTransactions list1 : list) {
				 if(list1.getEvent_Name().equals("Upload Documents")) {
					 System.out.println("list1 in case of upload docs");
					 System.out.println(list1);
	                
	                if (message.getContent() instanceof MimeMultipart) {
	                	
	                    MimeMultipart multipart = (MimeMultipart) message.getContent();
	                    
	                    if (list1.getEvidence_URL() != null) {
							urlList.addAll(Arrays.asList(list1.getEvidence_URL()));
							
							System.out.println(" the previous lists ");
							System.out.println(urlList);
						}
						if (list1.getEvidenceList() != null) {
							fileNameList.addAll(Arrays.asList(list1.getEvidenceList()));
						}
						 if (list1.getTypeOfReference() != null) {
							typeofEvidenceList.addAll(Arrays.asList(list1.getTypeOfReference()));				
						}
						 if (list1.getEvidence_Description() != null) {
							evidenceDescList.addAll(Arrays.asList(list1.getEvidence_Description()));
						}
						 if (list1.getEvidence_For() != null) {
							evidenceForList.addAll(Arrays.asList(list1.getEvidence_For()));
						 }
						 if (list1.getDocCreatedDate() != null) {
							dateList.addAll(Arrays.asList(list1.getDocCreatedDate()));
						}
						 if (list1.getDocId() != null) {
								docIdList.addAll(Arrays.asList(list1.getDocId()));
						}
						 
						String[] listDocsId = list1.getDocId();
	                    	                    	           	                    
	                    for (int i = 0; i < multipart.getCount(); i++) {
	                        BodyPart bodyPart = multipart.getBodyPart(i);
	                        if (Part.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition())) {
	                        	MimeBodyPart attachmentPart = (MimeBodyPart) bodyPart;
	                         // Save the attachment file
	                            String fileName = bodyPart.getFileName();
	                            System.out.println(" file name  ");
	                            System.out.println(fileName);
	                         if (!processedAttachments.contains(fileName)) {
	                            
	                         // Process the attachment file as needed
                             // (e.g., store it, read its contents, etc.)							
                                                               
     ///                           if(!list.isEmpty()) {                                	
                     //             for(ShipmentTransactions list1 : list) {
                     //           	 if(list1.getEvent_Name().equals("Upload Documents")) {
                                		                                                   		
           							    int lastIndexOfDot = fileName.lastIndexOf(".");
            							System.out.println("lastIndexOfDot");
            							System.out.println(lastIndexOfDot);
            							if (lastIndexOfDot != -1) {
            								 String fileNameWithoutExtension = fileName.substring(0, lastIndexOfDot);
            								 String appendInvoice_No = invoiceNo;
            							   //String appendBatch_id = "";   // yet to decide
            							   //fileName = fileNameWithoutExtension+"_"+appendInvoice_No+"_"+appendBatch_id+fileName.substring(lastIndexOfDot);
                 							 System.out.println(" extension of fileName");
                 							 System.out.println(fileName.substring(lastIndexOfDot));
            								 fileName = appendInvoice_No+"_"+fileNameWithoutExtension+fileName.substring(lastIndexOfDot);
            								 System.out.println("fileNames_without_extension");
                 							 System.out.println(fileNameWithoutExtension);

            							}
            							System.out.println("fileNames of files Uploaded to AWS S3:::: ");
            							System.out.println(fileName);
            					      //fileNameList.add(fileName);
            				           			
										try {
											// InputStream attachmentStream = bodyPart.getInputStream();
											// long attachmentLength = bodyPart.getSize();

											// Buffer the attachment input stream to calculate content length
											ByteArrayOutputStream buffer = new ByteArrayOutputStream();
											attachmentPart.getDataHandler().writeTo(buffer);
											byte[] attachmentBytes = buffer.toByteArray();
											int contentLength = attachmentBytes.length;
											InputStream attachmentInputStream = new ByteArrayInputStream(
													attachmentBytes);

											ObjectMetadata metadata = new ObjectMetadata();
											metadata.setContentLength(contentLength);

											String s3Key = fileName;
//											PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, s3Key,
//													attachmentInputStream, metadata);
											
		//									PutObjectRequest archiveputObjectRequest = new PutObjectRequest(archivedbucketName, s3Key,
		//											attachmentInputStream, metadata);
											// use the below put request when the above put request doesn't work and
											// this one works by throwing a warning.
											// PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,
											// s3Key, attachmentInputStream, metadata);  // put metadata as null
//											amazonS3Client.putObject(putObjectRequest
//													.withCannedAcl(CannedAccessControlList.PublicRead));
//
//											PutObjectRequest archiveputObjectRequest = new PutObjectRequest(archivedbucketName, s3Key,
//													attachmentInputStream, metadata);
//											
//											amazonS3Client.putObject(archiveputObjectRequest
//													.withCannedAcl(CannedAccessControlList.PublicRead));
											
											 amazonS3Client.putObject(
													   new PutObjectRequest(bucketName, s3Key, attachmentPart.getInputStream(), metadata)
													      .withCannedAcl(CannedAccessControlList.PublicRead));
											 amazonS3Client.putObject(
													   new PutObjectRequest(archivedbucketName, s3Key, attachmentPart.getInputStream(), metadata)
													      .withCannedAcl(CannedAccessControlList.PublicRead));
											
//											amazonS3Client2.putObject(archiveputObjectRequest
//													.withCannedAcl(CannedAccessControlList.PublicRead));
											
									//		CopyObjectRequest copyRequest = new CopyObjectRequest(bucketName, s3Key, archivedbucketName, s3Key);
									//		amazonS3Client.copyObject(copyRequest);
																												
								///			String publicURL = amazonS3Client.getUrl(bucketName, fileName).toString();
											String publicURL = amazonS3Client.getUrl(archivedbucketName, fileName).toString();						
																			
											System.out.println(" file url  ");
											System.out.println(publicURL);
											
			///								 String[] listDocsId = list1.getDocId();
											 System.out.println("DocsId from the list :::::::: ");
											 System.out.println(list1.getDocId());
			///								 System.out.println(listDocsId);
											
//											 if(previousId != null) {
//												 previousId = previousId;
//											 }
											 if (listDocsId != null && listDocsId.length > 0 && previousId == null) {
											   previousId = listDocsId[listDocsId.length - 1];
											 }
											 System.out.println("previousId  :::::::: ");
											 System.out.println(previousId);
											
											 String newId = generateUniqueId(previousId);
											 //previousId = newId;
										     System.out.println("newId  for  dms  ");
											 System.out.println(newId);
											 
											 urlList.add(publicURL);	
											 fileNameList.add(fileName);
											 System.out.println(" invoiceNoMatcher");
											 System.out.println(invoiceNoMatcher);
											 System.out.println(fdampPatternRegex.matcher(subject));	
											// if (invoiceNoMatcher.toString() != null){
										     if(invoiceNum) {
											 typeofEvidenceList.add("Pre Alert");    //Certificate of Analysis  // need to modify
											 evidenceDescList.add("COA");            // need to modify
											 }
											// if(invoiceNoMatcher.toString() == null || invoiceNoMatcher.toString() == "") {
											 if(fdamp) {	 
											 typeofEvidenceList.add("FDAMP");         //FDA MAY Proceed
											 evidenceDescList.add("FDA may proceed"); // need to modify
											 }	
											 evidenceForList.add("Invoice");         // need to modify
											 dateList.add(strDate);
											 docIdList.add(newId);
											 
											 System.out.println(urlList);
											 System.out.println(fileNameList);
											 System.out.println(typeofEvidenceList);
											 System.out.println(evidenceDescList);
											 System.out.println(evidenceForList);
											 System.out.println(dateList);
											 System.out.println("docIdList  ");
											 System.out.println(docIdList);
					
											previousId = newId;
										} catch (IOException ioe) {
											// logger.error("IOException: " + ioe.getMessage());
											System.out.println(
													"IOException in AWS S3 Evidence Upload ");
											ioe.printStackTrace();
										}
										catch (AmazonServiceException serviceException) {
											// logger.info("AmazonServiceException: " + serviceException.getMessage());
											System.out.println("AmazonServiceException in AWS S3 Evidence Upload ");
											throw serviceException;
										} catch (AmazonClientException clientException) {
											// logger.info("AmazonClientException Message: " +
											// clientException.getMessage());
											System.out.println("AmazonClientException in AWS S3 Evidence Upload ");
											throw clientException;
										}     													           		            		                    		
        ///closed if                        	}	    
                                 }
	                              processedAttachments.add(fileName);
                                }
                               continue;
	                        }   
	                    }
//	               else if (bodyPart.getContent() instanceof MimeMultipart) {
//	                            MimeMultipart nestedMultipart = (MimeMultipart) bodyPart.getContent();
//	                            //processMultipart(nestedMultipart);
//	                        	System.out.println(" in else if for multipart ");
//	                    }
	                  break;
	                }
				 												 								 				 
	             }
	
			Query query = new Query();
			query.addCriteria(new Criteria()
					.andOperator(Criteria.where("Invoice_Number").is(invoiceNo),
					 Criteria.where("Event_Name").is("Upload Documents")
					));	
			
			Update update = new Update();
			update.set("Evidence_URL", urlList);
			update.set("EvidenceList", fileNameList);
			update.set("TypeOfReference", typeofEvidenceList);
			update.set("Evidence_For", evidenceForList);
			update.set("Evidence_Description", evidenceDescList);
			update.set("DocCreatedDate", dateList);
			update.set("DocId", docIdList);
			System.out.println("update  ");
			System.out.println(update);
			
			mongoTemplate.updateMulti(query, update, "ShipmentTransactions");
			
		    System.out.println("mongoTemplate updated  ");
		    
			for(ShipmentTransactions transac : listshpmtTransac) {
				 if(transac.getEvent_Name().equals("Upload Documents")) {
					    System.out.println(" in for each loop for uploading final pdf ");
					 if (message.getContent() instanceof MimeMultipart) {
						 MimeMultipart multipart = (MimeMultipart) message.getContent();
					
					    if (transac.getEvidence_URL() != null) {
							urlList.addAll(Arrays.asList(transac.getEvidence_URL()));
							
							System.out.println(" the previous lists ");
							System.out.println(urlList);
						}
						if (transac.getEvidenceList() != null) {
							fileNameList.addAll(Arrays.asList(transac.getEvidenceList()));
						}
						 if (transac.getTypeOfReference() != null) {
							typeofEvidenceList.addAll(Arrays.asList(transac.getTypeOfReference()));				
						}
						 if (transac.getEvidence_Description() != null) {
							evidenceDescList.addAll(Arrays.asList(transac.getEvidence_Description()));
						}
						 if (transac.getEvidence_For() != null) {
							evidenceForList.addAll(Arrays.asList(transac.getEvidence_For()));
						 }
						 if (transac.getDocCreatedDate() != null) {
							dateList.addAll(Arrays.asList(transac.getDocCreatedDate()));
						}
						 if (transac.getDocId() != null) {
								docIdList.addAll(Arrays.asList(transac.getDocId()));
						}
						 
						String[] docsId = transac.getDocId();
						
				        for (int part = 0; part < multipart.getCount(); part++) {
	                        BodyPart bodyPart = multipart.getBodyPart(part);
	                        if (Part.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition())) {
	                        	MimeBodyPart attachmentPart = (MimeBodyPart) bodyPart;
	                        	
	                         // Save the attachment file
	                            String fileName = bodyPart.getFileName();
	                            System.out.println(" file name  ");
	                            System.out.println(fileName);
	        //                 if (!processedAttachments.contains(fileName)) {
	                            
	                         // Process the attachment file as needed
                             // (e.g., store it, read its contents, etc.)							
                                                                                               		                                                   		
           							    int lastIndexOfDot = fileName.lastIndexOf(".");
            							System.out.println("lastIndexOfDot");
            							System.out.println(lastIndexOfDot);
            							if (lastIndexOfDot != -1) {
            								 String fileNameWithoutExtension = fileName.substring(0, lastIndexOfDot);
            								 String appendInvoice_No = transac.getInvoice_Number();
            							   //String appendBatch_id = "";   // yet to decide
            							   //fileName = fileNameWithoutExtension+"_"+appendInvoice_No+"_"+appendBatch_id+fileName.substring(lastIndexOfDot);
                 							 System.out.println(" extension of fileName");
                 							 System.out.println(fileName.substring(lastIndexOfDot));
            								 fileName = appendInvoice_No+"_"+fileNameWithoutExtension+fileName.substring(lastIndexOfDot);
            								 System.out.println("fileNames_without_extension");
                 							 System.out.println(fileNameWithoutExtension);

            							}
            							System.out.println("fileNames of files Uploaded to AWS S3:::: ");
            							System.out.println(fileName);
            					      //fileNameList.add(fileName);
            				           			
										try {

											ByteArrayOutputStream buffer = new ByteArrayOutputStream();
											attachmentPart.getDataHandler().writeTo(buffer);
											byte[] attachmentBytes = buffer.toByteArray();
											int contentLength = attachmentBytes.length;
											InputStream attachmentInputStream = new ByteArrayInputStream(
													attachmentBytes);

											ObjectMetadata metadata = new ObjectMetadata();
											metadata.setContentLength(contentLength);

											String s3Key = fileName;
//											PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, s3Key,
//													attachmentInputStream, metadata);
//											
//											amazonS3Client.putObject(putObjectRequest
//													.withCannedAcl(CannedAccessControlList.PublicRead));
//																						
//											CopyObjectRequest copyRequest = new CopyObjectRequest(bucketName, s3Key, archivedbucketName, s3Key);
//											amazonS3Client.copyObject(copyRequest);
											
											
											amazonS3Client.putObject(
													   new PutObjectRequest(bucketName, s3Key, attachmentPart.getInputStream(), metadata)
													      .withCannedAcl(CannedAccessControlList.PublicRead));
											amazonS3Client.putObject(
													   new PutObjectRequest(archivedbucketName, s3Key, attachmentPart.getInputStream(), metadata)
													      .withCannedAcl(CannedAccessControlList.PublicRead));
																												
									///		String publicURL = amazonS3Client.getUrl(bucketName, fileName).toString();
											String publicURL = amazonS3Client.getUrl(archivedbucketName, fileName).toString();
																			
											System.out.println(" file url  ");
											System.out.println(publicURL);
											
			///								 String[] listDocsId = list1.getDocId();
											 System.out.println("DocsId from the list :::::::: ");
											 System.out.println(transac.getDocId());
			///								 System.out.println(listDocsId);
											
											 if (docsId != null && docsId.length > 0 && previousId == null) {
											   previousId = docsId[docsId.length - 1];
											 }
											 System.out.println("previousId  :::::::: ");
											 System.out.println(previousId);
											
											 String newId = generateUniqueId(previousId);
											 //previousId = newId;
										     System.out.println("newId  for  dms  ");
											 System.out.println(newId);
											 
											 urlList.add(publicURL);	
											 fileNameList.add(fileName);        
											 if(shpmtNum) {	 
											 typeofEvidenceList.add("Summary Pdf");         //FDA MAY Proceed
											 evidenceDescList.add("Final report"); // need to modify
											 }	
											 evidenceForList.add("Delivery");         // need to modify
											 dateList.add(strDate);
											 docIdList.add(newId);
											 
											 System.out.println(urlList);
											 System.out.println(fileNameList);
											 System.out.println(typeofEvidenceList);
											 System.out.println(evidenceDescList);
											 System.out.println(evidenceForList);
											 System.out.println(dateList);
											 System.out.println("docIdList  ");
											 System.out.println(docIdList);
					
											previousId = newId;
										}
										catch (IOException ioe) {
											System.out.println(" IOException in AWS S3 Evidence Upload ");
											ioe.printStackTrace();
										}
										catch (AmazonServiceException serviceException) {
											System.out.println(" AmazonServiceException in AWS S3 Evidence Upload ");
											throw serviceException;
										}
										catch (AmazonClientException clientException) {
											System.out.println(" AmazonClientException in AWS S3 Evidence Upload ");
											throw clientException;
										}     													           		            		                    		
        ///closed if                  }	    
                 //                }
                                }
                               continue;
	                        }   										 					 
					 } 
				   break;
				 }
			}
			
			Query queryTransac = new Query();
			queryTransac.addCriteria(new Criteria()
					.andOperator(Criteria.where("Shipment_Num").is(shipment_Num),
					 Criteria.where("Event_Name").is("Upload Documents")
					));
			
			System.out.println(" query for final pdf upload");
			System.out.println(queryTransac);
			
			Update updateTransac = new Update();
			updateTransac.set("Evidence_URL", urlList);
			updateTransac.set("EvidenceList", fileNameList);
			updateTransac.set("TypeOfReference", typeofEvidenceList);
			updateTransac.set("Evidence_For", evidenceForList);
			updateTransac.set("Evidence_Description", evidenceDescList);
			updateTransac.set("DocCreatedDate", dateList);
			updateTransac.set("DocId", docIdList);
			
			System.out.println(" update query for final pdf upload");
			System.out.println(updateTransac);
			
			mongoTemplate.updateMulti(queryTransac, updateTransac, "ShipmentTransactions");			
		    System.out.println("mongoTemplate updated for final pdf");
		    
		    		  		    
		    List<Shipments> shpList = shiprepo.findByInvoice_Number(invoiceNo);
			String Shipment_Id = null;
			boolean shp_id = false;
			for (Shipments sh : shpList) {
				Shipment_Id = sh.getShipment_Id();
				shp_id = true;		
				System.out.println("Shipment_Id - " + sh.getShipment_Id());
			}
				
			 System.out.println("shpmntID"+ Shipment_Id);
		    
		    for(ShipmentTransactions transacList : list) {
		    	
				 //System.out.println("completedEvent "+ completedEvent);
				 System.out.println("invoiceNum "+ invoiceNum);
				 System.out.println("fdamp "+ fdamp);
				 				 
				 if(completedEvent && invoiceNum) {
					 System.out.println("in first if for invoice");
					 System.out.println("eve name  "+ transacList.getEvent_Name());
   				     System.out.println("eve status "+ transacList.getEvent_Status());
		    			if (transacList.getEvent_Name().equals("Upload Documents")
								&& (transacList.getEvent_Status().equals("Initialized") || transacList.getEvent_Status().equals("Queued"))) {
		    				System.out.println("::::: coming to Complete the Upload Documents Event ");	    			
		    				Query query_trans = new Query();
							query_trans.addCriteria(
									new Criteria().andOperator((Criteria.where("Invoice_Number").is(invoiceNo)),
											Criteria.where("Event_Name").is("Upload Documents")));
										///	Criteria.where("Event_Status").is("Initialized")));
				 
							Update update_trans = new Update();
							update_trans.set("Event_Status", "Completed");
							update_trans.set("EventReferenceNumber", "");
							update_trans.set("Event_Exec_Date", strDate);
							System.out.println(query_trans);
							System.out.println(update_trans);
							mongoTemplate.updateMulti(query_trans, update_trans, "ShipmentTransactions");

//							System.out.println(" Event_SNo ::::" + transacList.getEvent_SNo());
//							long eventslno = Long.valueOf((transacList.getEvent_SNo() + 1));
//							System.out.println("Long valueeeee::::" + eventslno);
//							ShipmentTransactions eventsno = shiptransrepo.findByEvent_Sno(eventslno);
//							String reqval = eventsno.getEvent_Name();
//							System.out.println("Reqval::::" + reqval);
//							System.out.println("Eventss updating to initailization" + eventsno);
//							System.out.println("query displaying for criteria" + eventsno.getShipment_Id()
//									+ "-------------" + eventsno.getEvent_SNo());
//							
//							long incrementedEventSNo = Long.valueOf((transacList.getEvent_SNo() + 2));
//							System.out.println("incrementedEventSNo Long value::::" + incrementedEventSNo);
//							ShipmentTransactions eventsnoRaiseExc = shiptransrepo.findByEvent_Sno(incrementedEventSNo);
//							
//							Query query2 = new Query();
//							Update update2 = new Update();
//
////							query2.addCriteria(new Criteria().andOperator(
////									Criteria.where("Shipment_Id").is(eventsno.getShipment_Id()),
////									Criteria.where("Event_SNo").is(eventsno.getEvent_SNo())));
//							if(eventsnoRaiseExc.getEvent_Name().equals("Raise Exception")) {
//								query2.addCriteria(new Criteria().andOperator(
//										Criteria.where("Invoice_Number").is(invoiceNo),
//										Criteria.where("Event_Name").is("Raise Exception")));
//							}
//							else {
//								query2.addCriteria(new Criteria().andOperator(
//										Criteria.where("Invoice_Number").is(invoiceNo),
//										Criteria.where("Event_Name").is("FDA may proceed")));
//							}
//							
//							System.out.println("query for updating status of next event");
//							System.out.println(query2);
//
//							System.out.println("query displaying for criteria" + eventsno.getShipment_Id()
//									+ "-------------" + eventsno.getEvent_SNo());
////							if(eventsno.getEvent_Name().equals("FDA may proceed") && eventsno.getEvent_Status().equals("Queued")) {
////								System.out.println(" ::::::::::::: fda is queued ::::::::::::: ");
////							    update2.set("Event_Status", "Initialized");
////							}
////							else if(eventsno.getEvent_Name().equals("FDA may proceed") && eventsno.getEvent_Status().equals("Initialized")) {
////								System.out.println(" ::::::::::::: fda is Initialized ::::::::::::: ");
////								update2.set("Event_Status", "Initialized");	
////							}
////							else {
////								System.out.println(" ::::::::::::: fda in else ::::::::::::: ");
////							    update2.set("Event_Status", "Completed");
////							}
//							if((eventsno.getEvent_Name().equals("Attach Device and Start") || eventsno.getEvent_Name().equals("FDA may proceed") || eventsno.getEvent_Name().equals("Goods Receipt")  || eventsno.getEvent_Name().equals("Additional Documents") || eventsno.getEvent_Name().equals("Raise Exception")) && eventsno.getEvent_Status().equals("Initialized")) {
//								System.out.println(" ::::::::::::: Event is Initialized ::::::::::::: ");
//							    update2.set("Event_Status", "Initialized");
//							}
//							else if((eventsno.getEvent_Name().equals("Attach Device and Start") || eventsno.getEvent_Name().equals("FDA may proceed") || eventsno.getEvent_Name().equals("Goods Receipt") || eventsno.getEvent_Name().equals("Additional Documents") || eventsno.getEvent_Name().equals("Raise Exception")) && eventsno.getEvent_Status().equals("Queued")) {
//							System.out.println(" ::::::::::::: Event is Queued ::::::::::::: ");		
//							update2.set("Event_Status", "Initialized");		
//							}
//							else {
//							System.out.println(" ::::::::::::: Event in else ::::::::::::: ");
//						    update2.set("Event_Status", "Completed");
//							}
//							
//							System.out.println("update criteria for updating status of next event");
//							System.out.println(update2);
//														
//							mongoTemplate.updateMulti(query2, update2, "ShipmentTransactions");

							for (Shipments shipments : shpList) {
//								if(shipments.getShipment_Status().equals("Goods Receipt")) {									
//									shipments.setShipment_Status("Goods Receipt");
//								}
//								else {
//									shipments.setShipment_Status(reqval);
//								}
								if(shipments.getShipment_Status().equals("Delivered")) {	
									shipments.setShipment_Status("Delivered");
								}
								else {
								shipments.setShipment_Status("Goods Receipt");
								}
							}												
							shiprepo.saveAll(shpList);
							
//							Query query1 = new Query();
//							Update update1 = new Update();
//							query1.addCriteria(Criteria.where("Shipment_Id").is(Shipment_Id));
//							update1.set("Shipment_Status", reqval);
//							mongoTemplate.updateFirst(query1, update1, "Shipments");
							break;			    						    				
		    			}
				 }
				
				 if(completedEvent && fdamp) {
					 System.out.println("in first if for Fdamp");
					 System.out.println("eve name in Fdamp "+ transacList.getEvent_Name());
   				     System.out.println("eve status in Fdamp "+ transacList.getEvent_Status());
		    			if (transacList.getEvent_Name().equals("FDA may proceed") && (transacList.getEvent_Status().equals("Initialized") || transacList.getEvent_Status().equals("Queued"))) {
		    				System.out.println("::::: coming to Complete the FDA Event ");	    			
		    				Query query_trans = new Query();
							query_trans.addCriteria(
									new Criteria().andOperator((Criteria.where("Invoice_Number").is(invoiceNo)),
											Criteria.where("Event_Name").is("FDA may proceed")));
									//		Criteria.where("Event_Status").is("Initialized")));	

							Update update_trans = new Update();
							update_trans.set("Event_Status", "Completed");
							update_trans.set("EventReferenceNumber", "");
							update_trans.set("Event_Exec_Date", strDate);
							System.out.println(query_trans);
							System.out.println(update_trans);
							mongoTemplate.updateMulti(query_trans, update_trans, "ShipmentTransactions");
//							mongoTemplate.updateFirst(query_trans, update_trans, "ShipmentTransactions");

//							System.out.println(" Event_SNo ::::" + transacList.getEvent_SNo());
//							long eventslno = Long.valueOf((transacList.getEvent_SNo() + 1));
//							System.out.println("Long valueeeee::::" + eventslno);
//							ShipmentTransactions eventsno = shiptransrepo.findByEvent_Sno(eventslno);
//							String reqval = eventsno.getEvent_Name();
//							System.out.println("Reqval::::" + reqval);
//							System.out.println("Eventss updating to initailization" + eventsno);
//							System.out.println("query displaying for criteria" + eventsno.getShipment_Id()
//									+ "-------------" + eventsno.getEvent_SNo());
//							
//							long incrementedEventSNo = Long.valueOf((transacList.getEvent_SNo() + 2));
//							System.out.println("incrementedEventSNo Long value::::" + incrementedEventSNo);
//							ShipmentTransactions eventsnoRaiseExc = shiptransrepo.findByEvent_Sno(incrementedEventSNo);
//							
//							
//							Query query2 = new Query();
//							Update update2 = new Update();
//
////							query2.addCriteria(new Criteria().andOperator(
////									Criteria.where("Shipment_Id").is(eventsno.getShipment_Id()),
////									Criteria.where("Event_SNo").is(eventsno.getEvent_SNo())));
//							if(eventsnoRaiseExc.getEvent_Name().equals("Raise Exception")) {
//								query2.addCriteria(new Criteria().andOperator(
//										Criteria.where("Invoice_Number").is(invoiceNo),
//										Criteria.where("Event_Name").is("Raise Exception")));
//							}
//							else {
//								query2.addCriteria(new Criteria().andOperator(
//										Criteria.where("Invoice_Number").is(invoiceNo),
//										Criteria.where("Event_Name").is("Goods Receipt")));
//							}
//						
//							System.out.println("query for updating status of next event");
//							System.out.println(query2);
//							
//							System.out.println("query displaying for criteria" + eventsno.getShipment_Id()
//									+ "-------------" + eventsno.getEvent_SNo());
//
////							if(eventsno.getEvent_Name().equals("Goods Receipt") && eventsno.getEvent_Status().equals("Queued")) {
////								System.out.println(" ::::::::::::: Goods Receipt is queued ::::::::::::: ");
////							    update2.set("Event_Status", "Initialized");
////							}
////							else if(eventsno.getEvent_Name().equals("Goods Receipt") && eventsno.getEvent_Status().equals("Initialized")) {
////								System.out.println(" ::::::::::::: Goods Receipt is Initialized ::::::::::::: ");
////								update2.set("Event_Status", "Initialized");	
////							}
////							else {
////								System.out.println(" ::::::::::::: fda in else ::::::::::::: ");
////							    update2.set("Event_Status", "Completed");
////							}
//							System.out.println(" ::::::::::::: Event Name --- eventsno ::::::::::::: ");
//							System.out.println(eventsno.getEvent_Name());
//							
//							if((eventsno.getEvent_Name().equals("Attach Device and Start") || eventsno.getEvent_Name().equals("Upload Documents") || eventsno.getEvent_Name().equals("Goods Receipt")  || eventsno.getEvent_Name().equals("Additional Documents") || eventsno.getEvent_Name().equals("Raise Exception")) && eventsno.getEvent_Status().equals("Initialized")) {
//								System.out.println(" ::::::::::::: Event is Initialized ::::::::::::: ");
//							    update2.set("Event_Status", "Initialized");
//							}
//						    else if((eventsno.getEvent_Name().equals("Attach Device and Start") || eventsno.getEvent_Name().equals("Upload Documents") || eventsno.getEvent_Name().equals("Goods Receipt") || eventsno.getEvent_Name().equals("Additional Documents") || eventsno.getEvent_Name().equals("Raise Exception")) && eventsno.getEvent_Status().equals("Queued")) {
//							System.out.println(" ::::::::::::: Event is Queued ::::::::::::: ");		
//							update2.set("Event_Status", "Initialized");		
//						    }
//						    else {
//							System.out.println(" ::::::::::::: Event in else ::::::::::::: ");
//						    update2.set("Event_Status", "Completed");
//						    }
//									
//							System.out.println("update criteria for updating status of next event");
//							System.out.println(update2);
//							
//							mongoTemplate.updateMulti(query2, update2, "ShipmentTransactions");

							for (Shipments shipments : shpList) {							
//								if(shipments.getShipment_Status().equals("Goods Receipt")) {
//									shipments.setShipment_Status("Goods Receipt");
//								}
//								else {
//									shipments.setShipment_Status(reqval);
//								}
								if(shipments.getShipment_Status().equals("Delivered")) {	
									shipments.setShipment_Status("Delivered");
								}
								else {
								shipments.setShipment_Status("Goods Receipt");
								}
							}												
							shiprepo.saveAll(shpList);
							
//							Query query1 = new Query();
//							Update update1 = new Update();
//							query1.addCriteria(Criteria.where("Shipment_Id").is(Shipment_Id));
//							update1.set("Shipment_Status", reqval);
//							mongoTemplate.updateFirst(query1, update1, "Shipments");
		    				break;		    						    				
		    			}
				 	}		    			    	
		    	}
             }	
		    catch (Exception e) {
                // Handle exceptions for individual emails, but continue fetching others
		    	System.out.println(":::::::: Exception occured while fetching email ::::::::: ");
                e.printStackTrace();               
            }
               message.setFlag(Flags.Flag.SEEN, true);
               System.out.println(":::::::: Message fetched from email is marked as seen ::::::::: ");
         }

                inbox.close(false);
                store.close();
                System.out.println(":::::::: Inbox from email is closed ::::::::: ");
                //break;
       } catch (MessagingException e) {
//    	        retryCount++;
//    	        if (retryCount >= maxRetries) {
//                    // Handling the exception or logging an error message
//                    System.out.println(":::::::: Failed to fetch email after maximum retries :::::::::");
//                } else {
//                    // Wait for a short delay before retrying
//                    try {
//                        Thread.sleep(2000); // 2 second delay
//                    } catch (InterruptedException ex) {
//                        Thread.currentThread().interrupt();
//                        System.out.println(":::::::: Failed to Interrupt Thread / Thread did not sleep :::::::::");
//                    }
//                }
    	        System.out.println(":::::::: Failed to fetch email ::::::::: ");
                e.printStackTrace();
       }
            
 //    } /// while for timer      
   }
 }

//    @GetMapping("/fetch")
//    public ResponseEntity<String> fetchEmails() {
//        // The email fetching process is scheduled to run periodically,
//        // so there's no need to trigger it manually here.
//        // However, you can include additional logic if needed.
//
//        return ResponseEntity.status(HttpStatus.OK).body("Email fetching process started.");
//    }  
    @GetMapping("/fetchEmails")
    public void fetchEmails() {
        // The email fetching process is scheduled to run periodically,
        // so there's no need to trigger it manually here.
        // However, you can include additional logic if needed.

        //return ResponseEntity.status(HttpStatus.OK).body("Email fetching process started.");
    	System.out.println(":::::::: API triggered for Fetching Emails  :::::::::");
    }  

    
//    @Autowired
//    private DataStreamBatchService dataStreamBatchService;
    
    
	@Autowired
	DeviceDataStreamRepository devicedatastreamrepo;
	
	@Autowired
	ShipmentsRepository shipmentsrepo;
    
    @PostConstruct
    public void processBatchJob() {
        // Schedule the email fetching process to run periodically
        // Modify the delay and interval as per your requirements
        Timer timer = new Timer();
        timer.schedule(new DataFetchingTask(), 0, 600000);	// here 20000 is in milliseconds which is 20 sec
        													// 10 minutes is 600 seconds, 600 seconds is 600000 milliseconds
        System.out.println(":::::::: Timer scheduled for Fetching Device Data :::::::::");
    }
        
    private class DataFetchingTask extends TimerTask {
        @Override
        public void run() {
        	System.out.println(":::::::: Started checking DeviceDataStream to insert missing data :::::::::");
        	
        	Date date = new Date();
    		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    		String dateTime = formatter.format(date);
    		
    		LocalDateTime currentTime = LocalDateTime.now(ZoneOffset.UTC);
    		
        	System.out.println(":::::::: Started Fetching Data in DataStream batch Job ::::::::: "+dateTime);
        	System.out.println(":::::::: Current time in UTC in DataStream batch Job ::::::::: "+currentTime);
             	      	
    		String sensorUtcLatest  = null;	
    		String temperatureLatest  = null;
            String sensorUtcSecondLatest = null;
            String temperatureSecondLatest  = null;
            
            String latestTimestamp = null;
            
//            List<Devices> devices = mongoTemplate.findAll(Devices.class);
            List<Devices> devices = devicerepo.findAll();
                             
            System.out.println("List devices - in data insert batch job");
            //System.out.println(devices);
            
        	for(Devices dev: devices) {
        		 System.out.println("device id");
        		 System.out.println(dev.getDeviceId());
        		 
                List<DeviceDataStream> latestDataStreams = mongoTemplate.find(
                         Query.query(Criteria.where("Device_Id").is(dev.getDeviceId())
                        		 .andOperator(Criteria.where("Message_Type").is("sensor"))), 
                         DeviceDataStream.class);
                
                Collections.sort(latestDataStreams, Comparator.comparing(DeviceDataStream::getSensorUTC).reversed());
        		 
///        		List<DeviceDataStream> latestDataStreams = devicedatastreamrepo.findByDevice_id(dev.getDeviceId());
        		//Collections.sort(latestDataStreams, (ds1, ds2) -> ds1.getSensorUTC().compareTo(ds2.getSensorUTC()));       		
///        		Collections.sort(latestDataStreams, Comparator.comparing(DeviceDataStream::getSensorUTC).reversed());
        		                                       
        		if (!latestDataStreams.isEmpty()) {
                    // Get current_terminal_time of the latest document
                     sensorUtcLatest = latestDataStreams.get(0).getSensorUTC();

                    // Get Sensor_temperature of the latest document
                     temperatureLatest = latestDataStreams.get(0).getFirst_Sensor_temperature();


                    if (latestDataStreams.size() > 1) {  
                        // Get current_terminal_time of the second-to-latest document
                    	sensorUtcSecondLatest = latestDataStreams.get(1).getSensorUTC();
                        // Get Sensor_temperature of the second-to-latest document
                    	temperatureSecondLatest = latestDataStreams.get(1).getFirst_Sensor_temperature();
                    }
                    
    				// Process the retrieved data as needed
    				System.out.println("Latest sensorUTC: " + sensorUtcLatest);
    				System.out.println("Latest Sensor_temperature: " + temperatureLatest);
    				System.out.println("Second latest sensorUTC: " + sensorUtcSecondLatest);
    				System.out.println("Second latest Sensor_temperature: " + temperatureSecondLatest);

                }
        		else {
                    System.out.println("No data found in the DeviceDataStream collection.");
                }
        		
        	try {
                Instant instant1 = Instant.parse(sensorUtcSecondLatest);
                Instant instant2 = Instant.parse(sensorUtcLatest);
                Duration difference = Duration.between(instant1, instant2);
                
                System.out.println("Time difference: " + difference);
                System.out.println("Time difference in Minutes: " + difference.toMinutes());
                
        		if(difference.toMinutes() > 11) {
        			
        			LocalDateTime utcTime = LocalDateTime.parse(sensorUtcSecondLatest, DateTimeFormatter.ISO_DATE_TIME);
        			LocalDateTime incrementedTime = utcTime.plusMinutes(10);
        			String incrementedSensorUtc = incrementedTime+".00Z";
        			
        			System.out.println("Sensor utc incrementedTime");
                    System.out.println(incrementedTime);
        			
        			LocalTime time = LocalTime.parse(latestDataStreams.get(1).getCurrent_terminal_time(), DateTimeFormatter.ofPattern("HH:mm:ss"));
        			LocalTime incrementedcurrentterminalTime = time.plusMinutes(10);
        			       			        			
                    DeviceDataStream newDataStream = new DeviceDataStream();                                                      
                    newDataStream.setBattery_Level(latestDataStreams.get(0).getBattery_Level());
//                  newDataStream.setDevice_Id("0001211156");
                    newDataStream.setDevice_Id(latestDataStreams.get(0).getDevice_Id());
                    newDataStream.setLocationType(latestDataStreams.get(0).getLocationType());
                    newDataStream.setReportPeriod(latestDataStreams.get(0).getReportPeriod());
                    newDataStream.setMaxHumidityThreshold(latestDataStreams.get(0).getMaxHumidityThreshold());
                    newDataStream.setReporting_zone(latestDataStreams.get(0).getReporting_zone());
                    newDataStream.setIccid(latestDataStreams.get(0).getIccid());
                    newDataStream.setModem_IMEI(latestDataStreams.get(0).getModem_IMEI());
                    newDataStream.setShakeThreshold(latestDataStreams.get(0).getShakeThreshold());
                    newDataStream.setMinTempThreshold(latestDataStreams.get(0).getMinTempThreshold());
                    newDataStream.setSensorPhySampleCycle(latestDataStreams.get(0).getSensorPhySampleCycle());
                    newDataStream.setHumdity_2(latestDataStreams.get(0).getHumdity_2());
                    newDataStream.setMileage(latestDataStreams.get(0).getMileage());
                    newDataStream.setAirpressure(latestDataStreams.get(0).getAirpressure());
                    newDataStream.setSensorReportCycle(latestDataStreams.get(0).getSensorReportCycle());
                    newDataStream.setHumidity_1(latestDataStreams.get(0).getHumidity_1());
                    newDataStream.setActive(latestDataStreams.get(0).getActive());
                    newDataStream.setDevice_slno(latestDataStreams.get(0).getDevice_slno());
                    newDataStream.setMessage_Type(latestDataStreams.get(0).getMessage_Type());
                    newDataStream.setAddress(latestDataStreams.get(0).getAddress());
                    newDataStream.setLongitude(latestDataStreams.get(0).getLongitude());
                    
                    newDataStream.setTiltAngle(latestDataStreams.get(0).getTiltAngle());
                    newDataStream.setInternal_temperature(latestDataStreams.get(0).getInternal_temperature());
                    newDataStream.setReporting_time(latestDataStreams.get(0).getReporting_time());
                    newDataStream.setMinHumidityThreshold(latestDataStreams.get(0).getMinHumidityThreshold());
                    newDataStream.setSpeed_in_mph(latestDataStreams.get(0).getSpeed_in_mph());
                    newDataStream.setCurrent_terminal_time(incrementedcurrentterminalTime.toString());
                    newDataStream.setMaxTempThreshold(latestDataStreams.get(0).getMaxTempThreshold());
                    newDataStream.setLatitude(latestDataStreams.get(0).getLatitude());
                    newDataStream.setDevice_oem(latestDataStreams.get(0).getDevice_oem());
                    
                    newDataStream.setCurrent_terminal_date(latestDataStreams.get(1).getCurrent_terminal_date());                   
                    newDataStream.setReporting_Date(latestDataStreams.get(0).getReporting_Date());                   
                    newDataStream.setSensorUTC(incrementedSensorUtc);
                    // unit_type is not added                   
                    newDataStream.setFirst_Sensor_temperature(latestDataStreams.get(0).getFirst_Sensor_temperature());
                                                                                                                     
                    System.out.println("newDataStream object");
                    //System.out.println(newDataStream);
                   
                    mongoTemplate.save(newDataStream);
                    System.out.println("MongoTemplate got updated for missed time frequency.");

        		}
        	}
        		catch(Exception e){
        			e.printStackTrace();
        			System.out.println("Exception in building object for missing frequency in device data");
        	}	
        		             		
//        		for (DeviceDataStream dataStream : dataStreams) {
//                    processRecord(dataStream);
//                }
        		
//        		 processRecord(dataStream);
//        		 Collections.sort(dataStreams, DeviceDataStream.class);
        	}
        	             
     //  }     	
        }
    }
    

    @GetMapping("/processDeviceData")
    public void processBatchJobforDevicedata() {
      //  dataStreamBatchService.processBatchJob();
        System.out.println("Batch job for Device Data completed successfully.");
    }
    
    	
	@Value("${cloud.aws.credentials.accessKey}")
	private String accessKey;

	@Value("${cloud.aws.credentials.secretKey}")
	private String secretKey;
		
	@Value("${awsfunctionName}")
	private String ouboundFunctionName;
    
    
    ///@PostConstruct
    @Scheduled(fixedDelay = 180000) // Run the method every 3 minutes
    public void processShipmentCompletionJob() {
    	// Run the method every 3 minutes = 180000 millisec
    	// Run the method every 10 minutes = 600000 millisec
    	
        // Schedule the email fetching process to run periodically
        // Modify the delay and interval as per your requirements
 ///       Timer completionTimer = new Timer();
 ///       completionTimer.schedule(new ShipmentCompletionTask(), 0, 600000);	// here 20000 is in milliseconds which is 20 sec
        													// 10 minutes is 600 seconds, 600 seconds is 600000 milliseconds
        System.out.println("************* Timer scheduled for Completing Shipment ************");
        ShipmentCompletionTask();
    }
  ///  private class ShipmentCompletionTask extends TimerTask {
 ///       @Override
 ///       public void run() {
    
     public void ShipmentCompletionTask() {
    	 try {
        	   
        	System.out.println(":::::::: Started Batch Job for Shipment Completion :::::::::");
        	Date date = new Date();
    		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    		String dateTime = formatter.format(date);
    		System.out.println(":::::::: Started Fetching Data for Shipment Completion ::::::::: "+dateTime);
    		
    		LocalDateTime currentTime = LocalDateTime.now(ZoneOffset.UTC);  // this is anytime to UTC    		       	
        	System.out.println(":::::::: Current time in UTC for Shipment Completion::::::::: "+currentTime);
             	      	
    		String latestSensorUtc  = null;
    		String shpCompletionDate = null;
            
//          List<Devices> devices = mongoTemplate.findAll(Devices.class);
     //     List<CompleteShipmentTrigger> completionTriggerList = completeShipTriggerRepo.findAll();            
///            List<CompleteShipmentTrigger> completionTriggerList = completeShipTriggerRepo.findByStatus("");            
            List<CompleteShipmentTrigger> completionTriggerList = new CopyOnWriteArrayList<>(completeShipTriggerRepo.findByStatus(""));
            
            Map<String, List<CompleteShipmentTrigger>> groupedByDeliveryList = completionTriggerList.stream()
            	    .collect(Collectors.groupingBy(CompleteShipmentTrigger::getDeliveryNumber));
            System.out.println(groupedByDeliveryList);
            
            for (Map.Entry<String, List<CompleteShipmentTrigger>> entry : groupedByDeliveryList.entrySet()) {
                String deliveryNum = entry.getKey();
                List<CompleteShipmentTrigger> groupedTriggerList = entry.getValue();
                
                System.out.println(deliveryNum);
                System.out.println(groupedTriggerList);
                
                for(CompleteShipmentTrigger trigger: groupedTriggerList) {
                	
                    System.out.println(trigger.getShipmentCompletionDate());
                    System.out.println(trigger.getDeviceId());
                    
                    List<DeviceDataStream> latestdeviceDataStream = mongoTemplate.find(
                            Query.query(Criteria.where("Device_Id").is(trigger.getDeviceId())
                           		 .orOperator((Criteria.where("Message_Type").is("sensor")))), DeviceDataStream.class);                                                                                             
                    
                    Collections.sort(latestdeviceDataStream, Comparator.comparing(DeviceDataStream::getSensorUTC).reversed());
                    
                    latestSensorUtc =  latestdeviceDataStream.get(0).getSensorUTC();                   
                    shpCompletionDate = trigger.getShipmentCompletionDate();
                    
                    System.out.println(":::::::: latestSensorUtc ::::::::: ");
                    System.out.println(latestSensorUtc);
                                 
//                    LocalDateTime completionDate = LocalDateTime.parse(shpCompletionDate);
//                    LocalDateTime latestDeviceUTC = LocalDateTime.parse(latestSensorUtc);
                    OffsetDateTime completionDate = OffsetDateTime.parse(shpCompletionDate, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                    OffsetDateTime latestDeviceUTC = OffsetDateTime.parse(latestSensorUtc, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                                                   
                    if(latestDeviceUTC.isAfter(completionDate)) {                                      
                    	trigger.setDependencyStatus("ready");
                    	completeShipTriggerRepo.save(trigger);
                    }
                    if(latestDeviceUTC.isBefore(completionDate)) {
                    	trigger.setDependencyStatus("waiting");
                    	completeShipTriggerRepo.save(trigger);
                    }
                
                if (groupedTriggerList.size() > 1) {
                    boolean allMatch = groupedTriggerList.stream()
                            .allMatch(t -> t.getStatus() == null && "ready".equals(t.getDependencyStatus()));

                    if (allMatch) {
                        // Proceed with the trigger activity
                      ///  triggerActivity(deliveryNumber, deliveryTriggers);
                    }
                }

            }       
        }
            
            System.out.println(":::::::: completionTriggerList ::::::::: ");
            //System.out.println(completionTriggerList);
            
            System.out.println(":::::::: Size of the Completion Trigger List  ::::::::: ");
            System.out.println(completionTriggerList.size());
            
            if (completionTriggerList == null || completionTriggerList.isEmpty()) {
                // Handle empty list case
            	System.out.println("No completion triggers to process.");
                return;
            }       
        	for(CompleteShipmentTrigger completionLoop : completionTriggerList) {	        		
        	   if(completionLoop.getStatus().isEmpty() || completionLoop.getStatus().equals("")) {
        		   System.out.println(":::::::: inside the loop ::::::::: ");
        		   System.out.println(completionLoop.getDeviceId());  
        		   
                    List<DeviceDataStream> latestdeviceDataStream = mongoTemplate.find(
                            Query.query(Criteria.where("Device_Id").is(completionLoop.getDeviceId())
                           		 .orOperator((Criteria.where("Message_Type").is("sensor")),
                           		 Criteria.where("Message_Type").is("GPS MESSAGE"))), DeviceDataStream.class);                                                                                             
                    
                    Collections.sort(latestdeviceDataStream, Comparator.comparing(DeviceDataStream::getSensorUTC).reversed());
                    
                    latestSensorUtc =  latestdeviceDataStream.get(0).getSensorUTC();                   
                    shpCompletionDate = completionLoop.getShipmentCompletionDate();
                    
                    System.out.println(":::::::: latestSensorUtc ::::::::: ");
                    System.out.println(latestSensorUtc);
                                 
//                    LocalDateTime completionDate = LocalDateTime.parse(shpCompletionDate);
//                    LocalDateTime latestDeviceUTC = LocalDateTime.parse(latestSensorUtc);
                    OffsetDateTime completionDate = OffsetDateTime.parse(shpCompletionDate, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                    OffsetDateTime latestDeviceUTC = OffsetDateTime.parse(latestSensorUtc, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                   
                                
                    if(latestDeviceUTC.isAfter(completionDate)) {
                    	//Execute completion logic here                    	
                		List<ShipmentTransactions> transacList = shiptransrepo.findByShipment_Id(completionLoop.getShipmentId());
                		
                		String deviceAttachedDate = null;
                		String mode = null;
                		String[] comments = null;
                		List<String> eventIds = new ArrayList<>();                		
                		for(ShipmentTransactions transac: transacList) {
                			if(transac.getEvent_Name().equals("Attach Device and Start")) {
                				deviceAttachedDate = transac.getEvent_Exec_Date();
                				mode = transac.getMode_of_Transport();
                				comments = transac.getComments();
                			}
                			eventIds.add(transac.getEvent_Id());
                		}
                		System.out.println(" deviceAttachedDate ");
                		System.out.println(deviceAttachedDate);
                		System.out.println(" mode ");
                		System.out.println(mode);
                		System.out.println(" comments ");
                		System.out.println(comments);
                		                    		                		
                		AggregationResults<DeviceDataStream> results = null;		
                		AggregationOperation matchPipeline = Aggregation.match(Criteria.where("Device_Id").is(completionLoop.getDeviceId())
                				.orOperator((Criteria.where("Message_Type").is("sensor")),
                						Criteria.where("Message_Type").is("GPS MESSAGE"))
                				.andOperator(Criteria.where("SensorUTC")
                			            .gte(deviceAttachedDate)
                			            .lte(completionLoop.getShipmentCompletionDate())));
                		
                		Aggregation aggregation = Aggregation.newAggregation(matchPipeline);
                		System.out.println("aggregation " + aggregation);
                		results = mongoTemplate.aggregate(aggregation, "DeviceDataStream", DeviceDataStream.class);
                	
                      //System.out.println("results "+results);
                		List<DeviceDataStream> resultList = results.getMappedResults();
                		int sizeofDevicedata = resultList.size();
                		System.out.println("Size of results: " + sizeofDevicedata);
                		
                		
                        DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE_TIME;
                        LocalDateTime deviceStartdate = LocalDateTime.parse(deviceAttachedDate, dateFormatter);
                        LocalDateTime shpEnddate = LocalDateTime.parse(completionLoop.getShipmentCompletionDate(), dateFormatter);

                        long minutesDifference = Duration.between(deviceStartdate, shpEnddate).toMinutes();
                        int actualDataPoints = (int) Math.round((double) minutesDifference / 10);
                        //actualDataPoints-=5;
                        actualDataPoints-=25;

                        System.out.println("Total number of data points: " + actualDataPoints);
                        System.out.println("Updated points after removing tolerance points: " + actualDataPoints);
                        
                        double percentageValue = calculatePercentage(actualDataPoints, 100);
                        System.out.println("100% of " + actualDataPoints + " is: " + percentageValue);
                        
                        
						/********
						 * below method triggers AWS Lambda Function for Outbound XML (data summary) file
						 *******/

					//	final String AWS_ACCESS_KEY_ID = "AKIA23OBXAWH6GWFGYHN";
					//	final String AWS_SECRET_ACCESS_KEY = "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";
                        
                   //   String functionName = "arn:aws:lambda:us-east-2:746119955855:function:SCMXpert-OutboundFiles-Test";
						
						final String AWS_ACCESS_KEY_ID = accessKey;
						final String AWS_SECRET_ACCESS_KEY = secretKey;

						AWSCredentials credentials = new BasicAWSCredentials(AWS_ACCESS_KEY_ID,
								AWS_SECRET_ACCESS_KEY);

						// ARN
						String functionName = ouboundFunctionName;

						// This will convert object to JSON String
						// String inputJSON = new Gson().toJson(userActivity);
						String inputJSON = "{\r\n" + "  \"body\": {\r\n" + "    \"internalShipmentNum\": \""
								+ completionLoop.getShipmentId() + "\"\r\n" + "  }\r\n" + "}";
						/******* for static internal Shipment Number use the below string ********/
						//		    String inputJSON = "{\r\n"
						//		    		+ "  \"body\": {\r\n"
						//		    		+ "    \"internalShipmentNum\": \"T00000003\"\r\n"
						//		    		+ "  }\r\n"
						//		    		+ "}";

						//		    String[] inputJSON = {"\"internalShipmentNum\": \"" + shipment.getShipment_Number() + "\",\r\n"
						//		    		};			    
						InvokeRequest lmbRequest = new InvokeRequest().withFunctionName(functionName)
								.withPayload(inputJSON);

						lmbRequest.setInvocationType(InvocationType.RequestResponse);

						InvokeResult lmbResult = null;
                                                                                                                      		                                     	                                   	          			
            			 if(sizeofDevicedata >= actualDataPoints){
            				 //Generate and send the XML to SAP, with Exception event marked as blank
                         	Shipments shipfrom = shiprepo.findByShipment_Id(completionLoop.getShipmentId());
                        	
                			try  {           				
                				//below method calls pdf email
                				cec.tempDataCalculator(shipfrom, completionLoop.getShipmentCompletionDate(),completionLoop.getGraphImage(), deviceAttachedDate);
//                				cec.tempDataCalculator(shipfrom, strDate,shipment.getGraphImage(), deviceAttachedDate);
                			} 
                			catch (Exception e) {            				
                				System.out.println("Error in tempdatacalculator method to generate final PDF "+e);
                				e.printStackTrace();
                			}           				            				            				 
								try {

									AWSLambda lambda = AWSLambdaClientBuilder.standard().withRegion(Regions.US_EAST_2)
											.withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
									// .withEndpointConfiguration(new
									// AwsClientBuilder.EndpointConfiguration("http://localhost:3001/","us-east-2"))
									// .withEndpointConfiguration(new
									// AwsClientBuilder.EndpointConfiguration("https://hgqefpcazjcktbwc6yehvshmvi0dxogj.lambda-url.us-east-2.on.aws/","us-east-2"))

									lmbResult = lambda.invoke(lmbRequest);

									String resultJSON = new String(lmbResult.getPayload().array(),
											Charset.forName("UTF-8"));
									// write out the return value
									System.out.println("This is aws Lambda result" + resultJSON);
								} catch (ServiceException e) {

									System.out.println("Eror in Lambda Method " + e);
									e.printStackTrace();
								}

								System.out.println("Lambda Method Status Code " + lmbResult.getStatusCode());

								/*********** Lambda Request and Result/Response ends here *************/
								
								try{
									Query query = new Query();
									query.addCriteria(new Criteria().andOperator(Criteria.where("shipmentId").is(completionLoop.getShipmentId())));
									Update update = new Update();
									update.set("status", "Completed");
									update.set("processedDateTime", dateTime);										
									mongoTemplate.updateMulti(query, update, "CompleteShipmentTrigger");
									
								}
								catch(Exception e) {
									e.printStackTrace();
									System.out.println("Failed to update the Status in CompleteShipmentTrigger");;
								}
            				 
            			 }
							else {
	                         	Shipments shipfrom = shiprepo.findByShipment_Id(completionLoop.getShipmentId());
	                        	
	                			try  {           				
	                				//below method calls pdf email
	                				cec.tempDataCalculator(shipfrom, completionLoop.getShipmentCompletionDate(),completionLoop.getGraphImage(), deviceAttachedDate);
//	                				cec.tempDataCalculator(shipfrom, strDate,shipment.getGraphImage(), deviceAttachedDate);
	                			} 
	                			catch (Exception e) {            				
	                				System.out.println("Error in tempdatacalculator method to generate final PDF "+e);
	                				e.printStackTrace();
	                			}  
								
								// create Raise Exception
						//		List<String> eventNames = new ArrayList<>();
						//		eventNames.add(transaction.getEvent_Name());
								
								Query query = new Query();
								query.addCriteria(new Criteria().andOperator(Criteria.where("Shipment_Id").is(completionLoop.getShipmentId()),
										Criteria.where("Event_Name").is("Raise Exception")));
						        List<ShipmentTransactions> transactions = mongoTemplate.find(query, ShipmentTransactions.class);
						        
						        if(transactions.isEmpty()) {	
						    		System.out.println("::::: Mismatch in Data points so started creating Raise Exception event :::::");
						    		
						    		UpdateNewPlusEventDto UpdateNewPlusEventDto = new UpdateNewPlusEventDto();
						    		UpdateNewPlusEventDto.setCustomerId("S004");
						    		UpdateNewPlusEventDto.setShipment_Number(completionLoop.getShipmentId());
						    		UpdateNewPlusEventDto.setShipment_Num(completionLoop.getDeliveryNumber());
						    		UpdateNewPlusEventDto.setDeviceId(completionLoop.getDeviceId());
						    		UpdateNewPlusEventDto.setMode(mode);
						    		UpdateNewPlusEventDto.setParnterFrom(completionLoop.getPartnerFrom());
						    		if(eventIds.contains("E0045")){
						    		UpdateNewPlusEventDto.setEvent_Id("E0055");
						    		}
						    		else {
						    		UpdateNewPlusEventDto.setEvent_Id("E0045");
						    		}
						    		UpdateNewPlusEventDto.setPartnerTo("");
						    		UpdateNewPlusEventDto.setEventName("Raise Exception");
						    		UpdateNewPlusEventDto.setComments(comments);
						    		UpdateNewPlusEventDto.setPartner(completionLoop.getPartner());
						    				
						    		boolean exceptionEvent = addUpdateNewEvent(UpdateNewPlusEventDto);
						    		System.out.println("Flag from addUpdateNewEvent");
						    		System.out.println(exceptionEvent);
						    		System.out.println("***** Completed creating Exception Event *****");
						    								    		
									try {

										AWSLambda lambda = AWSLambdaClientBuilder.standard().withRegion(Regions.US_EAST_2)
												.withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
										// .withEndpointConfiguration(new
										// AwsClientBuilder.EndpointConfiguration("http://localhost:3001/","us-east-2"))
										// .withEndpointConfiguration(new
										// AwsClientBuilder.EndpointConfiguration("https://hgqefpcazjcktbwc6yehvshmvi0dxogj.lambda-url.us-east-2.on.aws/","us-east-2"))

										lmbResult = lambda.invoke(lmbRequest);

										String resultJSON = new String(lmbResult.getPayload().array(),
												Charset.forName("UTF-8"));
										// write out the return value
										System.out.println("This is aws Lambda result" + resultJSON);
									} catch (ServiceException e) {

										System.out.println("Eror in Lambda Method " + e);
										e.printStackTrace();
									}

									System.out.println("Lambda Method Status Code " + lmbResult.getStatusCode());

									/*********** Lambda Request and Result/Response ends here *************/
						    		
						        }
						        if(!transactions.isEmpty()) {
						        	for(ShipmentTransactions transac: transactions) {
						        		transac.setEvent_Status("Queued");
						        	}
						        	shiptransrepo.saveAll(transactions);
						        	try {

										AWSLambda lambda = AWSLambdaClientBuilder.standard().withRegion(Regions.US_EAST_2)
												.withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
										// .withEndpointConfiguration(new
										// AwsClientBuilder.EndpointConfiguration("http://localhost:3001/","us-east-2"))
										// .withEndpointConfiguration(new
										// AwsClientBuilder.EndpointConfiguration("https://hgqefpcazjcktbwc6yehvshmvi0dxogj.lambda-url.us-east-2.on.aws/","us-east-2"))

										lmbResult = lambda.invoke(lmbRequest);

										String resultJSON = new String(lmbResult.getPayload().array(),
												Charset.forName("UTF-8"));
										// write out the return value
										System.out.println("This is aws Lambda result" + resultJSON);
									} catch (ServiceException e) {

										System.out.println("Eror in Lambda Method " + e);
										e.printStackTrace();
									}

									System.out.println("Lambda Method Status Code " + lmbResult.getStatusCode());

									/*********** Lambda Request and Result/Response ends here *************/						        							        							        						        							        	
						        }
						        
								try{
									Query queryTrigger = new Query();
									queryTrigger.addCriteria(new Criteria().andOperator(Criteria.where("shipmentId").is(completionLoop.getShipmentId())));
									System.out.println(queryTrigger);
									Update updateTrigger = new Update();
									updateTrigger.set("status", "Completed");
									updateTrigger.set("processedDateTime", dateTime);	
									mongoTemplate.updateMulti(queryTrigger, updateTrigger, "CompleteShipmentTrigger");
									System.out.println("Updated Mongo for Partially Completed in CompleteShipmentTrigger");
									
								}
								catch(Exception e) {
									e.printStackTrace();
									System.out.println("Failed to update the Status in CompleteShipmentTrigger when Raising Exception");
								}
																						
						}           			           			
                    }
                    if(latestDeviceUTC.isBefore(completionDate)) {
                    	// hold completion logic
                    	System.out.println(":::::***** Device Data Timestamp is not matching with the Shipment Completion date *****:::::");
                    	System.out.println("Waiting for Device data");
                    }                                                         
        		}       		       		
        	}        	
    	 }//try closed
    	 catch(Exception e) {
    		 e.printStackTrace();
    		 System.out.println("There is a Failure in CompletionTrigger Batch Job");
    	 }
      }
 ///    }
 
            
    
}