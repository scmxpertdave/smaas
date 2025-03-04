package com.SCMXPert.sbmongodb.controller;

import java.util.ArrayList;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.SCMXPert.sbmongodb.document.DeviceDataStream;
import com.SCMXPert.sbmongodb.document.Shipments;
import com.SCMXPert.sbmongodb.document.Devices;
import com.SCMXPert.sbmongodb.document.ShipDashboard;
import com.SCMXPert.sbmongodb.document.BusinessPartner;
import com.SCMXPert.sbmongodb.document.Customer;
import com.SCMXPert.sbmongodb.document.ShipmentTransactions;
import com.SCMXPert.sbmongodb.repository.ShipmentsRepository;
import com.SCMXPert.sbmongodb.repository.BusinessPartnerRepository;
import com.SCMXPert.sbmongodb.repository.ShipmentTransactionsRepository;
import com.SCMXPert.sbmongodb.repository.DeviceDataStreamRepository;
import com.SCMXPert.sbmongodb.repository.DevicesRepository;
import com.SCMXPert.sbmongodb.repository.DropDownRepo;
import com.SCMXPert.sbmongodb.repository.CustomerRepository;
//import com.SCMXPert.sbmongodb.repository.ShipmentsRepositoryCustom;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("unused")
@Controller
@CrossOrigin(origins = { "https://www.smaas.live", "https://smaas.live","http://172.17.211.224:3000","http://127.0.0.1:8081","https://www.smaas.org" })
public class SCMXPertMainController {

	private static final String[] NAMES = { "Tom", "Jerry", "Donald" };

	// @Autowired
	// private ShipmentsRepositoryCustom ShipmentsRepositoryCustom;

	@Autowired
	private ShipmentsRepository ShipmentsRepository;
	@Autowired
	private BusinessPartnerRepository BusinessPartnerRepository;
	@Autowired
	private ShipmentTransactionsRepository ShipmentTransactions;
	@Autowired
	private DevicesRepository Devices;
	@Autowired
	private CustomerRepository CustomerRepository;
	@Autowired
	private DeviceDataStreamRepository DeviceDataStreamRepository;
	

	@ResponseBody
	@RequestMapping("/")
	public String home() {
		String html = "";
		html += "<ul>";
		// html += " <li><a href='/testInsert'>Test Insert</a></li>";
		html += "<a href='/ShipmentDetails'>Shipment Details</a><br>";
		html += " <a href='/Customer'>Customer Details</a><br>";
		html += " <a href='/DeviceDataStream'>DeviceDataStream</a><br>";
		html += " <a href='/Devices'>Devices</a><br>";
		html += " <a href='/Customer'>BusinessPartner</a><br>";
		html += " <a href='/ShipmentTransactions'>ShipmentTransactions</a><br>";
		// html += " <li><a href='/showFullNameLikeTom'>Show All
		// 'Tom'</a></li>";
		// html += " <li><a href='/deleteAllEmployee'>Delete All
		// Employee</a></li>";
		html += "</ul>";
		return html;
	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@RequestMapping("/ShipmentDetails")
	public List<ShipDashboard> ShipmentDetails() {

		List<ShipDashboard> a = new ArrayList<ShipDashboard>();

		return a;
	}

	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping("/ShipmentDetails") public String ShipmentDetails() {
	 * String html1="";
	 * 
	 * List<Shipments> ShipmentDetails = this.ShipmentsRepository.findAll();
	 * 
	 * ObjectMapper objectMapper = new ObjectMapper(); String json1 = ""; for
	 * (Shipments Shp : ShipmentDetails) { // for(i=0;i<)
	 * 
	 * 
	 * try { json1 += objectMapper.writeValueAsString(Shp); } catch
	 * (JsonProcessingException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * //json1 += Shp + "<br>";
	 * 
	 * } html1 += "<ul>"; html1 +=
	 * "<a href='/Particularcustomer/0001'> Customer_Id : 0001 </a><br>"; html1
	 * += "<a href='/Particularcustomer/0002'> Customer_Id : 0002</a><br>";
	 * html1 += "<a href='/Particularcustomer/0003'> Customer_Id: 0003</a><br>";
	 * html1 +=
	 * "<a href='/Particularcustomer/0004'> Customer_Id : 0004</a><br>"; html1
	 * += "<a href='/Allcustomerdetails'> Show all Customer details </a><br>";
	 * html1 += "</ul>"; return html1; }
	 * 
	 * @ResponseBody
	 * 
	 * @RequestMapping("/BusinessPartner") public String BusinessPartner() {
	 * 
	 * List<BusinessPartner> BusinessPartner =
	 * this.BusinessPartnerRepository.findAll(); String html1=""; ObjectMapper
	 * objectMapper = new ObjectMapper(); String json1 = ""; for
	 * (BusinessPartner Shp : BusinessPartner) { // for(i=0;i<)
	 * 
	 * 
	 * try { json1 += objectMapper.writeValueAsString(Shp); } catch
	 * (JsonProcessingException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * //json1 += Shp + "<br>"; // html1 += "<a href=json1</a>json1<br>";
	 * 
	 * }
	 * 
	 * return json1; }
	 * 
	 * @ResponseBody
	 * 
	 * @RequestMapping("/Allcustomerdetails") public String Allcustomerdetails()
	 * {
	 * 
	 * List<Shipments> ShipmentDetails = this.ShipmentsRepository.findAll();
	 * String html1=""; ObjectMapper objectMapper = new ObjectMapper(); String
	 * json1 = ""; for (Shipments Shp : ShipmentDetails) { // for(i=0;i<)
	 * 
	 * 
	 * try { json1 += objectMapper.writeValueAsString(Shp); } catch
	 * (JsonProcessingException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * //json1 += Shp + "<br>"; // html1 += "<a href=json1</a>json1<br>";
	 * 
	 * }
	 * 
	 * return json1; }
	 * 
	 * @ResponseBody
	 * 
	 * @RequestMapping("/Customer") public String Customer() {
	 * 
	 * List<Customer> CustomerDetails = this.CustomerRepository.findAll();
	 * ObjectMapper objectMapper = new ObjectMapper(); String json1 = ""; for
	 * (Customer Cus : CustomerDetails) { // for(i=0;i<)
	 * 
	 * try { json1 += objectMapper.writeValueAsString(Cus); } catch
	 * (JsonProcessingException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * 
	 * }
	 * 
	 * String html = ""; for (Customer emp1 : employees1) { html += emp1 +
	 * "<br>"; }
	 * 
	 * return html;
	 * 
	 * return json1; }
	 * 
	 * @ResponseBody
	 * 
	 * @RequestMapping("/Particularcustomer/0001") public String
	 * Particularcustomer() {
	 * 
	 * List<Shipments> employees =
	 * this.ShipmentsRepository.findBycustomerIdLike("0001");
	 * 
	 * String html = ""; for (Shipments emp : employees) { html += emp + "<br>";
	 * }
	 * 
	 * return html; }
	 * 
	 * @ResponseBody
	 * 
	 * @RequestMapping("/Particularcustomer/0002") public String
	 * Particularcustomer1() {
	 * 
	 * List<Shipments> employees =
	 * this.ShipmentsRepository.findBycustomerIdLike("0002");
	 * 
	 * String html = ""; for (Shipments emp : employees) { html += emp + "<br>";
	 * }
	 * 
	 * return html; }
	 * 
	 * @ResponseBody
	 * 
	 * @RequestMapping("/Particularcustomer/0003") public String
	 * Particularcustomer2() {
	 * 
	 * List<Shipments> employees =
	 * this.ShipmentsRepository.findBycustomerIdLike("0003");
	 * 
	 * String html = ""; for (Shipments emp : employees) { html += emp + "<br>";
	 * }
	 * 
	 * return html; }
	 * 
	 * @ResponseBody
	 * 
	 * @RequestMapping("/Particularcustomer/0004") public String
	 * Particularcustomer3() {
	 * 
	 * List<Shipments> employees =
	 * this.ShipmentsRepository.findBycustomerIdLike("0004");
	 * 
	 * String html = ""; for (Shipments emp : employees) { html += emp + "<br>";
	 * }
	 * 
	 * return html; }
	 * 
	 * @ResponseBody
	 * 
	 * @RequestMapping("/DeviceDataStream") public String DeviceDataStream() {
	 * 
	 * List<DeviceDataStream> DeviceDataStreamDetails =
	 * this.DeviceDataStreamRepository.findAll(); String html1=""; ObjectMapper
	 * objectMapper = new ObjectMapper(); String json1 = ""; for
	 * (DeviceDataStream Shp : DeviceDataStreamDetails) { // for(i=0;i<)
	 * 
	 * 
	 * try { json1 += objectMapper.writeValueAsString(Shp); } catch
	 * (JsonProcessingException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }}
	 * 
	 * //json1 += Shp + "<br>"; // html1 += "<a href=json1</a>json1<br>"; return
	 * json1; }
	 * 
	 * @ResponseBody
	 * 
	 * @RequestMapping("/Devices") public String Devices() {
	 * 
	 * List<Devices> Devices = this.Devices.findAll(); String html1="";
	 * ObjectMapper objectMapper = new ObjectMapper(); String json1 = ""; for
	 * (Devices Shp : Devices) { // for(i=0;i<)
	 * 
	 * 
	 * try { json1 += objectMapper.writeValueAsString(Shp); } catch
	 * (JsonProcessingException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }}
	 * 
	 * //json1 += Shp + "<br>"; // html1 += "<a href=json1</a>json1<br>"; return
	 * json1; }
	 * 
	 * @ResponseBody
	 * 
	 * @RequestMapping("/ShipmentTransactions") public String
	 * ShipmentTransactions() {
	 * 
	 * List<ShipmentTransactions> ShipmentTransactions1 =
	 * this.ShipmentTransactions.findAll(); String html1=""; ObjectMapper
	 * objectMapper = new ObjectMapper(); String json1 = ""; for
	 * (ShipmentTransactions Shp : ShipmentTransactions1) { // for(i=0;i<)
	 * 
	 * 
	 * try { json1 += objectMapper.writeValueAsString(Shp); } catch
	 * (JsonProcessingException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }}
	 * 
	 * //json1 += Shp + "<br>"; // html1 += "<a href=json1</a>json1<br>"; return
	 * json1; }
	 * 
	 * @ResponseBody
	 * 
	 * @RequestMapping("/delete") public String deleteAllEmployee() {
	 * 
	 * this.ShipmentsRepository.deleteAll(); return "Deleted!"; }
	 */

}