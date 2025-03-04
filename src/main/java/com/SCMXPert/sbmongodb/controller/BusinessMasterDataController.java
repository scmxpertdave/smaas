package com.SCMXPert.sbmongodb.controller;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.SCMXPert.sbmongodb.document.AddGood;
import com.SCMXPert.sbmongodb.document.Addresses;
import com.SCMXPert.sbmongodb.document.BPList;
import com.SCMXPert.sbmongodb.document.BusinessPartner;
import com.SCMXPert.sbmongodb.document.BusinessPartnerDto;
import com.SCMXPert.sbmongodb.document.Contact;
import com.SCMXPert.sbmongodb.document.Customer;
import com.SCMXPert.sbmongodb.document.DefaultEvents;
import com.SCMXPert.sbmongodb.document.EventId;
import com.SCMXPert.sbmongodb.document.Events;
import com.SCMXPert.sbmongodb.document.Goods;
import com.SCMXPert.sbmongodb.document.Phone;
import com.SCMXPert.sbmongodb.document.Role;
import com.SCMXPert.sbmongodb.document.Roles;
import com.SCMXPert.sbmongodb.document.User;
import com.SCMXPert.sbmongodb.document.UserDto;
import com.SCMXPert.sbmongodb.document.Users;
import com.SCMXPert.sbmongodb.repository.BusinessPartnerRepository;
import com.SCMXPert.sbmongodb.repository.CustomerRepository;
import com.SCMXPert.sbmongodb.repository.RoleRepository;
import com.SCMXPert.sbmongodb.repository.UserRepositary;
import com.SCMXPert.sbmongodb.repository.UserRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/SCMXPert")
@CrossOrigin(origins = {  "https://www.smaas-lb.de:8080/","https://www.smaas.live","https://smaas.live","http://172.17.211.224:3000","http://127.0.0.1:8081","https://www.smaas.org" })

public class BusinessMasterDataController {

	@Autowired
	CustomerRepository customerRepo;

	@Autowired
	BusinessPartnerRepository businessRepo;

	@Autowired
	UserRepositary userrepo;

	@Autowired
	UserRepository userrepository;

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	private RoleRepository rolerepo;

//////////////////////////Business Partner Master///////////////////////////
	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/getAllBusinessPartner/{Customer_Id}")
	public List<String> getAllBusinessPartners(@PathVariable(value = "Customer_Id") String Customer_Id){
	List<String> busiIdList = new ArrayList<>();
	String bpid = null;
		/*
		 * List<BusinessPartner> businesspart = new ArrayList<>(); businesspart =
		 * businessRepo.findByCustomer_Id(Customer_Id);
		 */
	//Customer customer = new ArrayList<>();
	Customer customer = customerRepo.findByCustomer_Id(Customer_Id);
	System.out.println("List Of Business Partners "+customer);
	if(customer.equals(null) ||customer.equals("")) {
	busiIdList.add("");

	}else {
		System.out.println("dslhjmfbg"+customer.getBusinessPartner());
		List<BusinessPartner> businesspartner = new ArrayList<>();
		for(BPList c : customer.getBusinessPartner()) {
			bpid = c.getBP_Id()+'-'+c.getBP_Name(); 
			  busiIdList.add(bpid);
		}
			/*
			 * for (Customer c : customer) { // bpid = c.getBP_Id(); //
			 * busiIdList.add(bpid); }
			 */
	return busiIdList;
	}
	return busiIdList;

	}
	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN')")
	@GetMapping("/getAllBusinessPartner")
	public List<String> getAllBusinessPartners() {
		List<String> busiIdList = new ArrayList<>();
		String bpid = null;
		List<BusinessPartner> businesspart = new ArrayList<>();
		businesspart = businessRepo.findAll();
		for (BusinessPartner c : businesspart) {
			bpid = c.getBP_Id();
			busiIdList.add(bpid);
		}
		return busiIdList;

	}

	/*
	 * @ResponseBody
	 * 
	 * @PostMapping("/createNewBusinessPartnerMaster") public boolean
	 * createBusinessPartnerMaster(@RequestBody BusinessPartnerDto businesspartner,
	 * UserDto userdt) throws NullPointerException { boolean flag = false;
	 * BusinessPartner bpm = new BusinessPartner(); Users user = new Users(); User
	 * userr = new User();
	 * 
	 * List<Contact> contactList = new ArrayList<>(); List<Phone> phoneList = new
	 * ArrayList<>(); List<Events> eventList = new ArrayList<>(); Contact contact =
	 * new Contact(); Phone phone1 = new Phone(); Phone phone2 = new Phone();
	 * EventId eventId = new EventId(); BusinessPartner bpss =
	 * businessRepo.findByBP_Id(businesspartner.getBP_Id());
	 * 
	 * try { List<BusinessPartner> cut = businessRepo.findAll();
	 * System.out.println("cut" + cut); for (BusinessPartner cu : cut) {
	 * 
	 * if (cu.getBP_Id().equals(businesspartner.getBP_Id())) {
	 * 
	 * System.out.println("Businesspartner already exists"); flag = false; return
	 * flag; } } if (businesspartner.getBP_Id().equals(null)) { flag = false; return
	 * flag; } else {
	 * 
	 * System.out.println("i am in Else IF Loop"); try { // General Info
	 * System.out.println("Businesspartner::::" + businesspartner.getBP_Id());
	 * System.out.println("Businesspartner::::" + userdt.getEmail());
	 * bpm.setBP_Id(businesspartner.getBP_Id());
	 * bpm.setCompany_Name(businesspartner.getCompany_Name());
	 * bpm.setName1(businesspartner.getName1());
	 * bpm.setName2(businesspartner.getName2());
	 * bpm.setCommunication_Method(businesspartner.getCommunication_Method());
	 * bpm.setExternal_Number(businesspartner.getExternal_Number());
	 * contact.setEmail_Id(businesspartner.getEmail_Id());
	 * phone1.setType("TelephoneNumber");
	 * phone1.setNumber(businesspartner.getTelephoneNumber());
	 * phone2.setType("CellPhone");
	 * phone2.setNumber(businesspartner.getCellPhoneNumber());
	 * phoneList.add(phone1); phoneList.add(phone2); contact.setPhone(phoneList);
	 * contactList.add(contact); bpm.setContact(contactList);
	 * 
	 * 
	 * for (Events events : businesspartner.getAllEvent()) { Events Even = new
	 * Events(); Even.setEvent_Id(events.getEvent_Id());
	 * Even.setEvent_Status(events.getEvent_Status()); eventList.add(Even); }
	 * 
	 * bpm.setEvents(eventList);
	 * 
	 * businessRepo.save(bpm);
	 * 
	 * user.setUserId(businesspartner.getUserId());
	 * user.setPassword(businesspartner.getPassword());
	 * user.setLanguage(businesspartner.getLanguage());
	 * user.setTime_Zone(businesspartner.getTime_Zone());
	 * user.setDate_Format(businesspartner.getDate_Format());
	 * user.setUserBP_Id(businesspartner.getBP_Id());
	 * user.setCustomer_Id(businesspartner.getCustomer_Id());
	 * user.setPartner_Role("BUSINESSPARTNER");
	 * user.setUserName(businesspartner.getName1());
	 * user.setAdminName(businesspartner.getAdminName());
	 * user.setEmail(businesspartner.getEmail());
	 * user.setCommunication_Method(businesspartner.getCommunication_Method());
	 * userrepo.save(user);
	 * 
	 * userr.setUserid(businesspartner.getUserId());
	 * userr.setEmail(businesspartner.getEmail().toLowerCase());
	 * BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	 * String p = bCryptPasswordEncoder.encode(businesspartner.getPassword());
	 * userr.setPassword(p); userr.setEnabled(true); // Role userRole =
	 * rolerepo.findByRole(businesspartner.getALLRoles()); // userr.setRoles(new
	 * HashSet<>(Arrays.asList(userRole)));
	 * 
	 * //Role userRole = rolerepo.findByRole(businesspartner.getRolevalue());
	 * 
	 * userr.setListrole(businesspartner.getRolevalue());
	 * System.out.println("hello "+businesspartner.getRole()); Role userRole =
	 * rolerepo.findByRole("BUSINESSPARTNER"); if(userRole == null) {
	 * 
	 * }else { userr.setRoles(new HashSet<>(Arrays.asList(userRole))); }
	 * //System.out.println("kjsdfbhjdsfg"+userRole);
	 * 
	 * // userrepo.save(user);
	 * 
	 * userrepository.save(userr);
	 * 
	 * 
	 * 
	 * User userr = new User(); UserDto dto = new UserDto();
	 * userr.setUserid(dto.getUserid()); BCryptPasswordEncoder bCryptPasswordEncoder
	 * = new BCryptPasswordEncoder(); String p =
	 * bCryptPasswordEncoder.encode(dto.getPassword()); userr.setPassword(p);
	 * userr.setEmail(dto.getEmail()); //userr.setRole("ROLE_BUSINESSPARTNER");
	 * userr.setBP_Id(dto.getBP_Id()); System.out.println(userr);
	 * userrepository.save(userr);
	 * 
	 * 
	 * 
	 * BPList rt = new BPList(); Customer cust =
	 * customerRepo.findByCustomerId(businesspartner.getCustomer_Id()); List<BPList>
	 * BPList = cust.getBusinessPartner(); if (BPList == null) { System.out.println(
	 * "Route are Not there for the Pirticular Customer so...Creating the Routes with Route Details"
	 * ); BPList = new ArrayList<>(); rt.setBP_Id(businesspartner.getBP_Id());
	 * rt.setBP_Name(businesspartner.getName1());
	 * rt.setBP_Company(businesspartner.getCompany_Name()); BPList.add(rt); Query
	 * query = new Query(); query.addCriteria(new Criteria()
	 * .andOperator(Criteria.where("Customer_Id").is(businesspartner.getCustomer_Id(
	 * )))); Update update1 = new Update(); update1.set("BusinessPartner", BPList);
	 * mongoTemplate.updateMulti(query, update1, "Customer"); flag = true; return
	 * flag; } else { for (BPList r : BPList) { if
	 * (r.getBP_Id().equals(businesspartner.getBP_Id())) { flag = false;
	 * System.out.println("Please change the Route Id ..."); // return flag; } }
	 * rt.setBP_Id(businesspartner.getBP_Id());
	 * rt.setBP_Name(businesspartner.getName1());
	 * rt.setBP_Company(businesspartner.getCompany_Name()); BPList.add(rt); Query
	 * query = new Query(); query.addCriteria(new Criteria()
	 * .andOperator(Criteria.where("Customer_Id").is(businesspartner.getCustomer_Id(
	 * )))); Update update1 = new Update(); update1.set("BusinessPartner", BPList);
	 * mongoTemplate.updateMulti(query, update1, "Customer"); flag = true; return
	 * flag;
	 * 
	 * } } catch (Exception e) { // TODO: handle exception } } } catch (Exception e)
	 * { // TODO: handle exception }
	 * 
	 * return flag; }
	 */
	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN')")
	@PostMapping("/createNewBusinessPartnerMaster")
	public boolean createBusinessPartnerMaster( @RequestBody @Validated BusinessPartnerDto businesspartner, UserDto userdt)
	throws NullPointerException {
	boolean flag = false;
	BusinessPartner bpm = new BusinessPartner();
	Users user = new Users();
	User userr = new User();
	List<Addresses> addrsList = new ArrayList<>();
	List<Contact> contactList = new ArrayList<>();
	List<Phone> phoneList = new ArrayList<>();
	List<@Valid Events> eventList = new ArrayList<>();
	Addresses address1 = new Addresses();
	Contact contact = new Contact();
	Phone phone1 = new Phone();
	Phone phone2 = new Phone();
	EventId eventId = new EventId();
	BusinessPartner bpss = businessRepo.findByBP_Id(businesspartner.getBP_Id());

	try {
	List<BusinessPartner> cut = businessRepo.findAll();
	System.out.println("cut" + cut);
	for (BusinessPartner cu : cut) {

	if (cu.getBP_Id().equals(businesspartner.getBP_Id())) {

	System.out.println("Businesspartner already exists");
	flag = false;
	return flag;
	}
	}
	if (businesspartner.getBP_Id().equals(null)) {
	flag = false;
	return flag;
	} else {

	System.out.println("i am in Else IF Loop");
	try {
	// General Info
	System.out.println("Businesspartner::::" + businesspartner.getBP_Id());

	bpm.setBP_Id(businesspartner.getBP_Id());
	bpm.setCustomer_Id(businesspartner.getCustomer_Id());
	bpm.setCompany_Name(businesspartner.getCompany_Name());
	bpm.setName1(businesspartner.getName1());
	bpm.setName2(businesspartner.getName2());
	bpm.setCommunication_Method(businesspartner.getCommunication_Method());
	bpm.setExternal_Number(businesspartner.getExternal_Number());
	bpm.setPartner_Type(businesspartner.getPartner_Type());
	bpm.setLocation(businesspartner.getLocation());
	bpm.setPartner_Status(businesspartner.getStatus());
	bpm.setEscallation_Manager(businesspartner.getEscallation_Manager());
	
	contact.setEmail_Id(businesspartner.getEmail_Id());
	phone1.setType("TelephoneNumber");
	phone1.setNumber(businesspartner.getTelephoneNumber());
	phone2.setType("CellPhone");
	phone2.setNumber(businesspartner.getCellPhoneNumber());
	phoneList.add(phone1);
	phoneList.add(phone2);
	contact.setPhone(phoneList);
	contactList.add(contact);
	bpm.setContact(contactList);

	// for (EventId events : businesspartner.getAllEvent()) {
	// EventId Even = new EventId();
	// Even.setEvent_Id(events.getEvent_Id());
	// Even.setEvent_Status(events.getEvent_Status());
	// eventList.add(Even);
	// }
	for (Events events : businesspartner.getAllEvent()) { Events Even = new  Events(); Even.setEvent_Id(events.getEvent_Id());
			  Even.setEvent_Status(events.getEvent_Status()); eventList.add(Even); }

	bpm.setEvents(eventList);
	bpm.setListrole(businesspartner.getRolevalue());
	businessRepo.save(bpm);

	user.setUserId(businesspartner.getUserId());
	  user.setPassword(businesspartner.getPassword());
	  user.setLanguage(businesspartner.getLanguage());
	  user.setTime_Zone(businesspartner.getTime_Zone());
	  user.setDate_Format(businesspartner.getDate_Format());
	  user.setUserBP_Id(businesspartner.getUserBP_Id());
	  user.setCustomer_Id(businesspartner.getCustomer_Id());
	  user.setCustomer_Name(businesspartner.getCustomer_Name());
	  user.setPartner_Role("BUSINESSPARTNER");
	  user.setUserName(businesspartner.getName1());
	  user.setAdminName(businesspartner.getAdminName());
	  user.setEmail(businesspartner.getEmail());
	  user.setCommunication_Method(businesspartner.getCommunication_Method());
	userrepo.save(user);

	userr.setUserid(businesspartner.getUserId());
	userr.setUserBP_Id(businesspartner.getBP_Id());
	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	String p = bCryptPasswordEncoder.encode(businesspartner.getPassword());
	userr.setPassword(p);
	userr.setEmail(businesspartner.getEmail());
	userr.setEnabled(true);
					/*
					 * userr.setRole(businesspartner.getRole());
					 * System.out.println("Businesspartner::::" + userr.getEmail()); List<Role> csk
					 * = new ArrayList<>(); Role userRole = null; String[] roles =
					 * businesspartner.getRoles(); HashSet<Role> set1=new HashSet(); for (int i = 0;
					 * i < roles.length; i++) { System.out.println("roles[i] " + roles[i]); userRole
					 * = rolerepo.findByRole(roles[i].toString());
					 * 
					 * set1.add(userRole); } System.out.println("USERROLE" + csk);
					 */

	/*
	* HashSet<Role> set = new HashSet(); for (Role rew : csk) { set.add(rew); //
	* HashSet<List> ss=new HashSet<>(Arrays.asList(rew));
	*
	* }
	*/
	//userr.setRoles(set1);

	// Role userRole = rolerepo.findByRole(businesspartner.getALLRoles());
	// userr.setRoles(new HashSet<>(Arrays.asList(userRole)));

	// Role userRole = rolerepo.findByRole(businesspartner.getRolevalue());

	// userr.setListrole(businesspartner.getRolevalue());
	userr.setListrole(businesspartner.getRolevalue());
	  System.out.println("hello "+businesspartner.getRole());
	  Role userRole = rolerepo.findByRole("BUSINESSPARTNER"); 
	  if(userRole == null) {
	  
	  }else { userr.setRoles(new HashSet<>(Arrays.asList(userRole))); }
	userrepository.save(userr);

	/*
	* User userr = new User(); UserDto dto = new UserDto();
	* userr.setUserid(dto.getUserid()); BCryptPasswordEncoder bCryptPasswordEncoder
	* = new BCryptPasswordEncoder(); String p =
	* bCryptPasswordEncoder.encode(dto.getPassword()); userr.setPassword(p);
	* userr.setEmail(dto.getEmail()); //userr.setRole("ROLE_BUSINESSPARTNER");
	* userr.setBP_Id(dto.getBP_Id()); System.out.println(userr);
	* userrepository.save(userr);
	*
	*/

	BPList rt = new BPList();
	Customer cust = customerRepo.findByCustomerId(businesspartner.getCustomer_Id());
	List<BPList> BPList = cust.getBusinessPartner();
	if (BPList == null) {
	System.out.println(
	"Route are Not there for the Pirticular Customer so...Creating the Routes with Route Details");
	BPList = new ArrayList<>();
	rt.setBP_Id(businesspartner.getBP_Id());
	rt.setBP_Name(businesspartner.getName1());
	rt.setBP_Company(businesspartner.getCompany_Name());
	BPList.add(rt);
	Query query = new Query();
	query.addCriteria(new Criteria()
	.andOperator(Criteria.where("Customer_Id").is(businesspartner.getCustomer_Id())));
	Update update1 = new Update();
	update1.set("BusinessPartner", BPList);
	mongoTemplate.updateMulti(query, update1, "Customer");
	flag = true;
	return flag;
	} else {
	for (BPList r : BPList) {
	if (r.getBP_Id().equals(businesspartner.getBP_Id())) {
	flag = false;
	System.out.println("Please change the Route Id ...");
	// return flag;
	}
	}
	rt.setBP_Id(businesspartner.getBP_Id());
	rt.setBP_Name(businesspartner.getName1());
	rt.setBP_Company(businesspartner.getCompany_Name());
	BPList.add(rt);
	Query query = new Query();
	query.addCriteria(new Criteria()
	.andOperator(Criteria.where("Customer_Id").is(businesspartner.getCustomer_Id())));
	Update update1 = new Update();
	update1.set("BusinessPartner", BPList);
	mongoTemplate.updateMulti(query, update1, "Customer");
	flag = true;
	return flag;

	}
	} catch (Exception e) {
	// TODO: handle exception
		e.printStackTrace();
	}
	}
	} catch (Exception e) {
	// TODO: handle exception
		e.printStackTrace();
	}

	return flag;
	}
	public String generateBPID() throws Exception {
		String bpid = null;
		String splittedval = null;
		String addZeros = null;

		List<String> bpIds = new ArrayList<>();
		List<BusinessPartner> bpps = businessRepo.findAll();
		for (BusinessPartner bp : bpps) {
			bpid = bp.getBP_Id();
			bpIds.add(bpid);
		}
		Collections.reverse(bpIds);
		String lastId = bpIds.get(0);
		String[] splitLastId = lastId.split("BP");
		for (String bps : splitLastId) {
			splittedval = bps;
		}
		Integer splitInteger = Integer.parseInt(splittedval);
		Integer increment1 = splitInteger + 1;
		String incrementString1 = increment1.toString();
		System.out.println("IncrementedString:::::" + incrementString1);
		if (incrementString1.length() == 2) {
			addZeros = "00";
		} else if (incrementString1.length() == 3) {
			addZeros = "00";
		} else if (incrementString1.length() == 4) {
			addZeros = "0";
		} else if (incrementString1.length() == 5) {
			throw new Exception("Its beyong the Limit of the Present One");
		}
		String finalString1 = "B";
		finalString1 = finalString1.concat(addZeros).concat(incrementString1);
		System.out.println("sdgajghdga:::::" + finalString1);
		return finalString1;
	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN')")
	@GetMapping("/getBusinessMaster/{BP_Id}")
	public BusinessPartnerDto getByBusinessPartner(@PathVariable(value = "BP_Id") String BP_Id) {

		BusinessPartnerDto dto = new BusinessPartnerDto();
		User userr = new User();
		List<Events> Eventlist = new ArrayList<>();
		List<Contact> contactList = new ArrayList<>();
		List<Phone> phoneList = new ArrayList<>();
		String roleslist[] = null;
		Users user2 = userrepo.findByBP_id(BP_Id);
		
		User userrroles = userrepository.findByUserBp_Id(BP_Id);
		 
	    System.out.println("RoleValue"+userrroles);

		BusinessPartner businesspartnerval = businessRepo.findByBP_Id(BP_Id);
        System.out.println("jksdfknsd"+businesspartnerval);
		dto.setBP_Id(businesspartnerval.getBP_Id());
		dto.setPartner_Type(businesspartnerval.getPartner_Type());
		dto.setLocation(businesspartnerval.getLocation());
		dto.setStatus(businesspartnerval.getPartner_Status());
		dto.setEscallation_Manager(businesspartnerval.getEscallation_Manager());
		dto.setCompany_Name(businesspartnerval.getCompany_Name());
		dto.setName1(businesspartnerval.getName1());
		dto.setName2(businesspartnerval.getName2());
		dto.setCommunication_Method(businesspartnerval.getCommunication_Method());
		dto.setExternal_Number(businesspartnerval.getExternal_Number());
		contactList.addAll(businesspartnerval.getContact());
		for (Contact cont : contactList) {
			dto.setEmail_Id(cont.getEmail_Id());
			phoneList.addAll(cont.getPhone());
			for (Phone ph : phoneList) {
				if (ph.getType().equals("TelephoneNumber")) {
					dto.setTelephoneNumber(ph.getNumber());
				} else if (ph.getType().equals("CellPhone")) {
					dto.setCellPhoneNumber(ph.getNumber());
				}
			}
		}
		dto.setLanguage(user2.getLanguage());
		dto.setUserId(user2.getUserId());
		dto.setPassword(user2.getPassword());
		dto.setEmail_Id(user2.getEmail());
		dto.setTime_Zone(user2.getTime_Zone());
		dto.setDate_Format(user2.getDate_Format());
		dto.setUserBP_Id(user2.getUserBP_Id());
		dto.setAllEvent(businesspartnerval.getEvents());
		
		//Eventlist.addAll(businesspartnerval.getEvents());
		System.out.println("jkzdfkj"+Eventlist);
		/*
		 * for(Events eveid: Eventlist) { dto.setEvent_Id(eveid.getEvent_Id());
		 * dto.setEvent_Status(eveid.getEvent_Status()); }
		 */
		System.out.println("nmhjzdsfb"+userr.getListrole());
//		dto.setRolevalue(userr.getListrole());
//		//dto.setEnabled(userr.isEnabled());
		
		dto.setRolevalue(userrroles.getListrole());
		
		//dto.setEnabled(userrroles.isEnabled());

		return dto;

	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN')")
	@PostMapping("/updateBusinessPartnerMaster")
	public boolean updateBusinessPartner(@RequestBody @Validated BusinessPartnerDto businesspartnerUpdate) {

		boolean flag = false;
		Phone ph1 = new Phone();
		Phone ph2 = new Phone();
		List<Phone> phList = new ArrayList<>();
		List<Contact> contactList = new ArrayList<>();
		List<EventId>EventList = new ArrayList<>();
		List<Events> eventList = new ArrayList<>();

		try {

			Contact cont = new Contact();

			cont.setEmail_Id(businesspartnerUpdate.getEmail_Id());
			ph1.setNumber(businesspartnerUpdate.getTelephoneNumber());
			ph1.setType("TelephoneNumber");
			ph2.setNumber(businesspartnerUpdate.getCellPhoneNumber());
			ph2.setType("CellPhone");
			phList.add(ph1);
			phList.add(ph2);
			cont.setPhone(phList);

			contactList.add(cont);
			User user = new User();
			user.setListrole(businesspartnerUpdate.getRolevalue());
			/*
			 * EventId events = new EventId();
			 * events.setEvent_Id(businesspartnerUpdate.getEvent_Id());
			 * events.setEvent_Status(businesspartnerUpdate.getEvent_Status());
			 */
			for (Events events : businesspartnerUpdate.getAllEvent()) { Events Even = new  Events(); Even.setEvent_Id(events.getEvent_Id());
			  Even.setEvent_Status(events.getEvent_Status()); eventList.add(Even); }

//			EventList.add(events);
			Query query1 = new Query();
			query1.addCriteria(
					new Criteria().andOperator(Criteria.where("BP_Id").is(businesspartnerUpdate.getBP_Id())));
			Update updatenew = new Update();
			updatenew.set("BP_Id", businesspartnerUpdate.getBP_Id());

			updatenew.set("Company_Name", businesspartnerUpdate.getCompany_Name());
			updatenew.set("Name1", businesspartnerUpdate.getName1());
			updatenew.set("Name2", businesspartnerUpdate.getName2());
			updatenew.set("Partner_Type", businesspartnerUpdate.getPartner_Type());
			updatenew.set("Location", businesspartnerUpdate.getLocation());
			updatenew.set("Partner_Status", businesspartnerUpdate.getStatus());
			updatenew.set("Escalation_Manager", businesspartnerUpdate.getEscallation_Manager());
			updatenew.set("External_Number", businesspartnerUpdate.getExternal_Number());
			updatenew.set("Communication_Method", businesspartnerUpdate.getCommunication_Method());
			updatenew.set("contact", contactList);
			updatenew.set("Events", eventList);
			
			mongoTemplate.updateMulti(query1, updatenew, "BusinessPartner");
			flag = true;

			Query query2 = new Query();
			query2.addCriteria(
					new Criteria().andOperator(Criteria.where("UserBP_Id").is(businesspartnerUpdate.getBP_Id())));
			Update updatenew1 = new Update();

			updatenew1.set("UserId", businesspartnerUpdate.getUserId());
			updatenew1.set("UserBP_Id", businesspartnerUpdate.getBP_Id());
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			String p = bCryptPasswordEncoder.encode(businesspartnerUpdate.getPassword());
			updatenew1.set("Password", businesspartnerUpdate.getPassword());
			updatenew1.set("Email", businesspartnerUpdate.getEmail_Id());
			updatenew1.set("CellPhoneNumber", businesspartnerUpdate.getCellPhoneNumber());
			updatenew1.set("TelephoneNumber", businesspartnerUpdate.getTelephoneNumber());
			updatenew1.set("Language", businesspartnerUpdate.getLanguage());
			updatenew1.set("Time_Zone", businesspartnerUpdate.getTime_Zone());
			updatenew1.set("Date_Format", businesspartnerUpdate.getDate_Format());
			updatenew1.set("Customer_Name", businesspartnerUpdate.getCustomer_Name());

			mongoTemplate.updateMulti(query2, updatenew1, "Users");
			flag = true;

			Query query3 = new Query();
			query3.addCriteria(
					new Criteria().andOperator(Criteria.where("userid").is(businesspartnerUpdate.getUserId())));
			Update updatenew11 = new Update();
System.out.println(""+businesspartnerUpdate.getUserId());
			updatenew11.set("userid", businesspartnerUpdate.getUserId());
			updatenew11.set("UserBP_Id",businesspartnerUpdate.getBP_Id());
			updatenew11.set("email", businesspartnerUpdate.getEmail_Id());
			updatenew11.set("password", p);
			//updatenew11.set("fullname", businesspartnerUpdate.getFullname());
			//updatenew11.set("enabled", "true");
			updatenew11.set("listrole", businesspartnerUpdate.getRolevalue());

			mongoTemplate.updateMulti(query3, updatenew11, "User");
			flag = true;

			return flag;

		} catch (Exception e) {

		}

		return flag;

	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN')")
	@GetMapping("/getIncrementedBP_ID/{Customer_Id}")
	public String getIncrementedBP_ID(@PathVariable(value = "Customer_Id") String Customer_Id) {
		String lastBPId = null;
		String bpid = null;
		String splittedval = null;
		String addZeros = null;
		List<String> listBPIds = new ArrayList<>();

		Customer cust = customerRepo.findByCustomerId(Customer_Id);
	//	 System.out.println("CustomerRepo"+cust.getBusinessPartner());
		List<BPList> BPList = cust.getBusinessPartner();
		//System.out.println("kljdfhglkdfjghkdsfg"+BPList);
		if (BPList == null) {
			String incrementString1 = "1";
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
			for (BPList r : BPList) {
				lastBPId = r.getBP_Id();
				System.out.println(lastBPId);
				listBPIds.add(lastBPId);
			}
			Collections.reverse(listBPIds);
			String lastId = listBPIds.get(0);
			/* Integer lasttoInt = Integer.parseInt(lastId); */
			/*
			 * Integer incInt = lasttoInt + 1; String incString = incInt.toString(); return
			 * incString;
			 */
			String[] splitLastId = lastId.split("BP");
			for (String bps : splitLastId) {
				splittedval = bps;
			}
			Integer splitInteger = Integer.parseInt(splittedval);
			// System.out.println("IncrementedString:::::" + splitInteger);
			Integer increment1 = splitInteger + 1;
			String incrementString1 = increment1.toString();
			// System.out.println("IncrementedString:::::" + incrementString1);
			if (incrementString1.length() == 1) {
				addZeros = "00";
			} else if (incrementString1.length() == 2) {
				addZeros = "00";
			} else if (incrementString1.length() == 3) {
				addZeros = "0";
			} else if (incrementString1.length() == 5) {
				return "Its beyong the Limit of the Present One";
			}
			// String finalString1 = "BP";
			/*
			 * Integer lasttoInt = Integer.parseInt(lastId); Integer incInt = lasttoInt + 1;
			 * String incString = incInt.toString(); return incString;
			 */
			/*
			 * Integer finalString1 = addZeros.concat(incrementString1);
			 * System.out.println("sdgajghdga:::::" + finalString1);
			 */
			return incrementString1;
		}
	}
//	@ResponseBody
//	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN')")
//	@GetMapping("/getBpId")
//	public String generateBpId() throws Exception {
//		String bPid = null;
//		String Splittedadmval = null;
//		String addZeros = null;
//		List<String> bPIds = new ArrayList<>();
//		List<BusinessPartner> bpl = businessRepo.findAll();
//		System.out.println("List Users"+bpl);
//		
//		List<String> listbp = new ArrayList<String>();
//		
//		for (BusinessPartner bps : bpl) {
//			
//		bPid = bps.getBP_Id();
//		System.out.println("adminid:::"+bPid);
//		bPIds.add(bPid);
//		}
//		
//		System.out.println("List2"+bPIds);
//	
//
//		    List<String> list = new ArrayList<String>();
//
//		    for(String s : bPIds) {
//		       if(s != null && s.length() > 0) {
//		          list.add(s);
//		       }
//		    }
//
//		    bPIds = list;
//		Collections.reverse(bPIds);
//		
//		if (bPIds.isEmpty()) {
//			String incrementString2 = "B001";
//			return incrementString2;
//
//		} else {
//			String lastId = bPIds.get(0);
//			
//			String[] splitLastId = lastId.split("B");
//			for (String s : splitLastId) {
//				Splittedadmval = s;
//				
//			}
//			}
//		
//	
//		System.out.println("Sysadmin::::"+Splittedadmval);
//		Integer splitInteger = Integer.parseInt(Splittedadmval);
//		Integer increment = splitInteger + 1;
//		String incrementString2 = increment.toString();
//		System.out.println(incrementString2);
//		if (incrementString2.length() == 1) {
//		addZeros = "00";
//		} else if (incrementString2.length() == 2) {
//		addZeros = "00";
//		} else if (incrementString2.length() == 3) {
//		addZeros = "00";
//		}else if (incrementString2.length() == 4) {
//		addZeros = "0";
//		}
//		else if (incrementString2.length() == 5) {
//		throw new Exception("Its beyong the Limit of the Present One");
//		}
//		System.out.println(incrementString2);
//
//		String finalString = "B";
//		finalString = finalString.concat(addZeros).concat(incrementString2);
//		System.out.println(finalString);
//		return finalString;
//		}
	
	@ResponseBody
    @PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN')")
    @GetMapping("/getBpId")
    public String generateBpId() throws Exception 
    {
        List<BusinessPartner> bpl = businessRepo.findAll();
        if(bpl.isEmpty())
        {
            return "B001";
        }
        else
        {
            Comparator<BusinessPartner> comparator = (BusinessPartner1 , BusinessPartner2) -> Integer.parseInt(BusinessPartner2.getBP_Id().split("B")[1]) -Integer.parseInt(BusinessPartner1.getBP_Id().split("B")[1]);
            Collections.sort(bpl, comparator);
            return "B00" + (Integer.parseInt(bpl.get(0).getBP_Id().split("B")[1])+1);
        }
    }
	
	
	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/getBusinessdropdown/{Customer_Id}")
	public List<String> getBusinessdropdown(@PathVariable(value = "Customer_Id") String Customer_id) throws Exception {

	List<String> eventnames = null;
	List<String> bpidlist = new ArrayList<>();
	List<BusinessPartner> businesspart = new ArrayList<>();
	businesspart=businessRepo.findByCustomer_Id(Customer_id.trim());

	List<String> eventlist = new ArrayList<>();

	for(BusinessPartner bp :businesspart) {

	for(Events eb :bp.getEvents()) {

	eventlist.add(eb.getEvent_Status());
	}
	eventnames = new ArrayList<>(new HashSet<>(eventlist));

	}

	for(String eve:eventnames) {
		System.out.println("check event"+businesspart);
	for(BusinessPartner bpid:businesspart) {

	for(Events eb :bpid.getEvents()) {
		//System.out.println("eve "+eve);
		
		System.out.println("bpid "+bpid.getBP_Id());
	if(eb.getEvent_Status().equals(eve)) {
	bpidlist.add(bpid.getBP_Id());
	}
	}

	bpid.getBP_Id();
	}
	}
	return eventnames;
	}
	
	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/getEventBpid/{Customer_Id}/{Event_Status}")
	public List<DefaultEvents> getEventBpid(@PathVariable(value = "Event_Status") String Event_Status,@PathVariable(value = "Customer_Id") String Customer_id) throws Exception {


	List<String> bpidlist = new ArrayList<>();
	List<String> partnername = new ArrayList<>();
	List<String> eventid = new ArrayList<>();
	List<String> eventstatus = new ArrayList<>();
	List<BusinessPartner> businesspart = new ArrayList<>();
	businesspart=businessRepo.findByCustomer_Id(Customer_id.trim());
	
	System.out.println("Events: "+businesspart);
	List<@Valid DefaultEvents> eventlist = new ArrayList<>();

	
	for(BusinessPartner bpid:businesspart) {
		System.out.println(bpid.getBP_Id());
	for(Events eb :bpid.getEvents()) {
		System.out.println("Event Statsu"+eb.getEvent_Status());
		System.out.println("Event Statsu get"+Event_Status);
	if(eb.getEvent_Status().equals(Event_Status)) {
//	bpidlist.add(bpid.getBP_Id());
//	partnername.add(bpid.getCompany_Name());
//	eventid.add(eb.getEvent_Id());
//	eventstatus.add(eb.getEvent_Status());
		DefaultEvents eventdefault = new DefaultEvents();
	eventdefault.setBp_Id(bpid.getBP_Id());
	eventdefault.setPartner_Name(bpid.getCompany_Name());
	eventdefault.setEvent_Id(eb.getEvent_Id());
	eventdefault.setEvent_Name(eb.getEvent_Status());
	eventlist.add(eventdefault);
	}
	}

	

	}




	return eventlist;



	}
	
	

	///////////// Goods Master/////////////

	/*
	 * @ResponseBody
	 * 
	 * @GetMapping("/getGoods/{Customer_Id}") public List<Goods>
	 * getRoutes(@PathVariable(value = "Customer_Id") String Customer_Id) { Customer
	 * cust = customerRepo.findByCustomerId(Customer_Id); List<Goods> goods =
	 * cust.getGoods(); return goods; }
	 * 
	 * @ResponseBody
	 * 
	 * @PostMapping("/addNewGood") public boolean addGoods(@RequestBody AddGood
	 * good) { boolean flag = false; Goods gds = new Goods(); Customer cust =
	 * customerRepo.findByCustomerId(good.getCustomerId()); try { List<Goods>
	 * goodList = cust.getGoods(); if (goodList == null) { goodList = new
	 * ArrayList<>(); gds.setGoods_Id(good.getGoods_Id());
	 * gds.setGoods_Item(good.getGoods_Item());
	 * gds.setGoods_Status(good.getGoodsTypeStatus());
	 * gds.setDescription(good.getDescription());
	 * gds.setTemperture_From(good.getTempertureFrom());
	 * gds.setTemperture_To(good.getTempertureTo());
	 * gds.setHumidity_From(good.getHumidityFrom());
	 * gds.setHumidity_To(good.getHumidityTo());
	 * gds.setShock_From(good.getShockFrom()); gds.setShock_To(good.getShockTo());
	 * gds.setTilt_From(good.getTiltFrom()); gds.setTilt_To(good.getTiltTo());
	 * gds.setSmell_From(good.getSmellFrom()); gds.setSmell_To(good.getSmellTo());
	 * goodList.add(gds); Query query = new Query(); query.addCriteria(new
	 * Criteria().andOperator(Criteria.where("Customer_Id").is(good.getCustomerId())
	 * )); Update update1 = new Update(); update1.set("Goods", goodList);
	 * mongoTemplate.updateMulti(query, update1, "Customer"); flag = true; return
	 * flag; } else { for (Goods g : goodList) { if
	 * (g.getGoods_Id().equals(good.getGoods_Id())) { flag = false;
	 * System.out.println("Please change the Goods Id ..."); // return flag; } }
	 * gds.setGoods_Id(good.getGoods_Id()); gds.setGoods_Item(good.getGoods_Item());
	 * gds.setGoods_Status(good.getGoodsTypeStatus());
	 * gds.setDescription(good.getDescription());
	 * gds.setTemperture_From(good.getTempertureFrom());
	 * gds.setTemperture_To(good.getTempertureTo());
	 * gds.setHumidity_From(good.getHumidityFrom());
	 * gds.setHumidity_To(good.getHumidityTo());
	 * gds.setShock_From(good.getShockFrom()); gds.setShock_To(good.getShockTo());
	 * gds.setTilt_From(good.getTiltFrom()); gds.setTilt_To(good.getTiltTo());
	 * gds.setSmell_From(good.getSmellFrom()); gds.setSmell_To(good.getSmellTo());
	 * goodList.add(gds); Query query = new Query(); query.addCriteria(new
	 * Criteria().andOperator(Criteria.where("Customer_Id").is(good.getCustomerId())
	 * )); Update update1 = new Update(); update1.set("Goods", goodList);
	 * mongoTemplate.updateMulti(query, update1, "Customer"); flag = true; return
	 * flag; }
	 * 
	 * } catch (Exception e) {
	 * 
	 * // TODO: handle exception } return flag; }
	 * 
	 * @ResponseBody
	 * 
	 * @PostMapping("/updateGood") public boolean updateGoods(@RequestBody AddGood
	 * good) { boolean flag = false; try { Update update1 = new Update(); Query
	 * query1 = new Query(); query1.addCriteria(new
	 * Criteria().andOperator(Criteria.where("Good.Goods_Id").is(good.getGoods_Id())
	 * )); update1.set("Good.$.Goods_Item", good.getGoods_Item());
	 * update1.set("Good.$.Goods_Status", good.getGoodsTypeStatus());
	 * update1.set("Good.$.Goods_Description", good.getDescription());
	 * update1.set("Good.$.Temperature_From",good.getTempertureFrom());
	 * update1.set("Good.$.Temperature_To", good.getTempertureTo());
	 * update1.set("Good.$.Humidity_From", good.getHumidityFrom());
	 * update1.set("Good.$.Humidity_To", good.getHumidityTo());
	 * update1.set("Good.$.Shock_From", good.getShockFrom());
	 * update1.set("Good.$.Shock_To", good.getShockTo());
	 * update1.set("Good.$.Tilt_From", good.getTiltFrom());
	 * update1.set("Good.$.Tilt_To", good.getTiltTo());
	 * update1.set("Good.$.Smell_From", good.getSmellFrom());
	 * update1.set("Good.$.Smell_To", good.getSmellTo());
	 * mongoTemplate.updateMulti(query1, update1, "Customer"); flag = true; return
	 * flag; } catch (Exception e) { // TODO: handle exception } return flag; }
	 * 
	 * @ResponseBody
	 * 
	 * @GetMapping("/getIncrementedGoodsId/{Customer_Id}") public String
	 * getInGoodsId(@PathVariable(value = "Customer_Id") String Customer_Id) {
	 * 
	 * String lastGoodsId = null; List<String> listGoodsIds = new ArrayList<>();
	 * Customer cust = customerRepo.findByCustomerId(Customer_Id); List<Goods>
	 * gouList = cust.getGoods(); for (Goods g : gouList) { lastGoodsId =
	 * g.getGoods_Id(); listGoodsIds.add(lastGoodsId); }
	 * Collections.reverse(listGoodsIds); String lastId = listGoodsIds.get(0);
	 * Integer lasttoInt = Integer.parseInt(lastId); Integer incInt = lasttoInt + 1;
	 * String incString = incInt.toString(); return incString;
	 * 
	 * }
	 */
}
