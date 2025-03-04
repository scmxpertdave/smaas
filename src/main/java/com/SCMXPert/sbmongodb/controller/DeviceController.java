package com.SCMXPert.sbmongodb.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;



//import org.apache.tomcat.jni.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.SCMXPert.sbmongodb.document.Addresses;
import com.SCMXPert.sbmongodb.document.BusinessPartner;
import com.SCMXPert.sbmongodb.document.Customer;
import com.SCMXPert.sbmongodb.document.DeviceCreateTransferDropDown;
import com.SCMXPert.sbmongodb.document.DeviceDataStream;
import com.SCMXPert.sbmongodb.document.DeviceDataStreamdto;
import com.SCMXPert.sbmongodb.document.DeviceTransferTracker;
import com.SCMXPert.sbmongodb.document.DeviceTransferTrackerDto;
import com.SCMXPert.sbmongodb.document.Devices;
import com.SCMXPert.sbmongodb.document.Shipments;
import com.SCMXPert.sbmongodb.repository.BusinessPartnerRepository;
import com.SCMXPert.sbmongodb.repository.CustomerRepository;
import com.SCMXPert.sbmongodb.repository.DeviceDataStreamRepository;
import com.SCMXPert.sbmongodb.repository.DeviceTransferTrackerRepo;
import com.SCMXPert.sbmongodb.repository.DevicesRepository;
import com.SCMXPert.sbmongodb.repository.ShipmentsRepository;

import javax.validation.Valid;

/**
 * @author Uday
 **/

@Controller
@RequestMapping("/SCMXPert")
@CrossOrigin(origins = {  "https://www.smaas.live","https://smaas.live","http://172.17.211.224:3000","http://127.0.0.1:8081","https://www.smaas.org" })

public class DeviceController {

	@Autowired
	DevicesRepository devicerepo;

	@Autowired
	DeviceTransferTrackerRepo devicetrackerrepo;

	@Autowired
	ShipmentsRepository shiprepo;

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	CustomerRepository customerepo;

	@Autowired
	DeviceDataStreamRepository devicedatastreamrepo;

	@Autowired
	BusinessPartnerRepository businessrepo;

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@PostMapping("/createDeviceTransfer")
	public boolean createDeviceTransfer(@Validated @RequestBody List<@Valid DeviceTransferTrackerDto> tr) {
		boolean flag = false;
		int count = 0;
		DeviceTransferTracker tkr = null;
		try {
			for (DeviceTransferTrackerDto t : tr) {
				count++;
			}
			System.out.println(count);
			for (DeviceTransferTrackerDto trck : tr) {
				Devices dev = devicerepo.findByDevice_Id(trck.getDeviceId());
				if (dev.getDeviceStatusReferred().equals("Detached")) {
					System.out.println(trck.getInternalTransferId());
					tkr = new DeviceTransferTracker();
					tkr.setInternalTransferId(trck.getInternalTransferId());
					tkr.setCustomerId(trck.getCustomerId());
					tkr.setNumberOfDevices(trck.getNumberOfDevices());
					tkr.setPartnerId(trck.getBpId());
					tkr.setTrackingNumber(trck.getTrackingNumber());
					tkr.setCourrierCompany(trck.getCourrierCompany());
					tkr.setTransferDescription(trck.getTransferDescription());
					tkr.setFromOrigin(trck.getFromOrigin());
					tkr.setToDestination(trck.getToDestination());
					tkr.setDevice_Id(trck.getDeviceId());
					tkr.setReceivingPartner(trck.getReceivingPartner());
					tkr.setDestinationAddress(trck.getDestinationAddress());
					tkr.setDateOfTransfer(trck.getDateOfTransfer());
					tkr.setExpectedDate(trck.getExpectedDate());
					tkr.setDeviceStatus("InTransit");
					devicetrackerrepo.insert(tkr);
					Query query = new Query();
					query.addCriteria(new Criteria().andOperator(Criteria.where("Device_Id").is(trck.getDeviceId())));
					Update update = new Update();
					update.set("DeviceStatusReferred", "InTransit");
					mongoTemplate.updateMulti(query, update, "Devices");
					flag = true;

				} else {
					Query query = new Query();
					query.addCriteria(new Criteria()
							.andOperator(Criteria.where("InternalTransferId").is(trck.getInternalTransferId())));
					Update update = new Update();
					update.set("TrackingNumber", trck.getTrackingNumber());
					update.set("CourrierCompany", trck.getCourrierCompany());
					update.set("TransferDescription", trck.getTransferDescription());
					update.set("FromOrigin", trck.getFromOrigin());
					update.set("NumberOfDevices", trck.getNumberOfDevices());
					update.set("ToDestination", trck.getToDestination());
					update.set("ReceivingPartner", trck.getReceivingPartner());
					update.set("DestinationAddress", trck.getDestinationAddress());
					update.set("DateOfTransfer", trck.getDateOfTransfer());
					update.set("ExpectedDate", trck.getExpectedDate());
					mongoTemplate.updateMulti(query, update, "DeviceTransferTracker");
					flag = true;
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return flag;
	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/getAllDevices")
	public List<DeviceTransferTrackerDto> getAvaliableAll() {

		List<DeviceTransferTrackerDto> list = new ArrayList<>();
		DeviceTransferTrackerDto track = null;
		List<Devices> devList = devicerepo.findAll();
		for (Devices dv : devList) {
			track = new DeviceTransferTrackerDto();
			track.setDeviceId(dv.getDeviceId());
			track.setDeviceStatus(dv.getDeviceStatusReferred());
			track.setLocation(dv.getDevice_Location());
			track.setCustomerId(dv.getCustomer_Id());
			
			
		
			List<Shipments> shiplist = shiprepo.findByDevice_Id(dv.getDeviceId());
			Collections.reverse(shiplist);
			for (Shipments sh : shiplist) {
				if(dv.getDeviceStatusReferred().equals("Available")) {
				track.setFromOrigin(sh.getRoute_From());
				track.setToDestination("");
				track.setCustomerId(sh.getCustomer_Id());
				track.setGoodsType("");
				track.setTime(sh.getCreated_Date());
				//track.setLocation(sh.getRoute_From());
				track.setBpId("");
				break;
			}
				else if(dv.getDeviceStatusReferred().equals("InTransit")) {
				track.setFromOrigin(sh.getRoute_From());
				track.setToDestination(sh.getRoute_To());
				track.setCustomerId(sh.getCustomer_Id());
				track.setGoodsType("");
				//track.setTime(sh.getCreated_Date());
				//track.setLocation(sh.getRoute_From());
				track.setBpId(sh.getCreated_By());
				break;
				}
				else if(dv.getDeviceStatusReferred().equals("Detatched")) {
				track.setFromOrigin("");
				track.setToDestination("");
				track.setCustomerId(sh.getCustomer_Id());
				track.setGoodsType("");
				//track.setTime(sh.getCreated_Date());
				//track.setLocation(sh.getRoute_From());
				track.setBpId("");
				break;
				}
				track.setFromOrigin(sh.getRoute_From());
				track.setToDestination(sh.getRoute_To());
				track.setCustomerId(sh.getCustomer_Id());
				track.setGoodsType(sh.getGoods_Desc());
				//track.setTime(sh.getCreated_Date());
				//track.setLocation(sh.getRoute_From());
				track.setBpId(sh.getCreated_By());
				
			}
			List<DeviceDataStream> dataSteamList = devicedatastreamrepo.findByDevice_id(dv.getDeviceId());
			Collections.reverse(dataSteamList);
			
			for (DeviceDataStream d : dataSteamList) {
				System.out.println(d.getBattery_Level());
				System.out.println(Float.valueOf(d.getBattery_Level()));
				
				String maxValueString = "4.2";
				float batteryLevel = Float.parseFloat(d.getBattery_Level());
				float maxValue = Float.parseFloat(maxValueString);
				float percentage = (batteryLevel / maxValue) * 100;
				System.out.println(Math.round(percentage * 100.0) / 100.0);
//			    percentage = (float) (Math.round(percentage * 100.0) / 100.0);
//				String batteryPercentage = Float.toString(percentage);
//				System.out.println(":Battery percentage:");
//				System.out.println(batteryPercentage);
				
///				String batteryinPercent = String.valueOf(100 * Float.valueOf(d.getBattery_Level()) - 318);
///				track.setBattery(d.getBattery_Level());
				track.setBattery(Math.round(percentage * 100.0) / 100.0 +" %");
				track.setLongitude(d.getLongitude());
				track.setLatitude(d.getLatitude());
				break;
			}
			List<DeviceTransferTracker> tlist = devicetrackerrepo.findAll();
			for (DeviceTransferTracker t : tlist) {
				
				if (dv.getDeviceId().equals(t.getDevice_Id())) {
					if(dv.getDeviceStatusReferred().equals("Available")) {
					
					track.setInternalTransferId("");
					track.setTrackingNumber("");
					track.setDestinationAddress("");
												
				}
				
					else if(dv.getDeviceStatusReferred().equals("InTransit")) {
						System.out.println("I am in Intransit");
				track.setInternalTransferId(t.getInternalTransferId());
				track.setTrackingNumber(t.getTrackingNumber());
				track.setDestinationAddress(t.getDestinationAddress());
				
					}
					else if(dv.getDeviceStatusReferred().equals("Detatched")) {
						track.setInternalTransferId("");
						track.setTrackingNumber("");
						track.setDestinationAddress("");
					}
					
			
			}
				else {
					System.out.println("I am in Attached to Shipment");
				track.setInternalTransferId("");
				track.setTrackingNumber("");
				track.setDestinationAddress("");	
				}
			}
			list.add(track);
		}
		return list;

	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@PostMapping("/receiceDeviceTransfer")
	public boolean receiveDeviceTransfer(@Validated @RequestBody List<@Valid DeviceTransferTrackerDto> tr) {
		boolean flag = false;
		try {
			
			for (DeviceTransferTrackerDto trck : tr) {
				System.out.println("receive devices :- "+trck);
				Devices dev = devicerepo.findByDevice_Id(trck.getDeviceId());
				if (dev.getDeviceStatusReferred().equals("InTransit")) {
					Query query = new Query();
					query.addCriteria(new Criteria()
							.andOperator(Criteria.where("InternalTransferId").is(trck.getInternalTransferId())));
					Update update = new Update();
					update.set("TrackingNumber", trck.getTrackingNumber());
					update.set("CourrierCompany", trck.getCourrierCompany());
					update.set("TransferDescription", trck.getTransferDescription());
					update.set("FromOrigin", trck.getFromOrigin());
					update.set("NumberOfDevices", trck.getNumberOfDevices());
					update.set("ToDestination", trck.getToDestination());
					update.set("ReceivingPartner", trck.getReceivingPartner());
					update.set("DestinationAddress", trck.getDestinationAddress());
					update.set("DateOfTransfer", trck.getDateOfTransfer());
					update.set("ExpectedDate", trck.getExpectedDate());
					update.set("PersonReceiving", trck.getPersonReceiving());
					update.set("DeviceStatus", "Available");
					mongoTemplate.updateMulti(query, update, "DeviceTransferTracker");
					Query query1 = new Query();
					query1.addCriteria(new Criteria().andOperator(Criteria.where("Device_Id").is(trck.getDeviceId())));
					Update update1 = new Update();
					update1.set("DeviceStatusReferred", "Available");
					mongoTemplate.updateMulti(query1, update1, "Devices");
					flag = true;
				} else {
					System.out.println("The Device Status is not in InTransit or Either InternalTransferId is null ");
					flag = false;
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return flag;

	}
	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/getDevices/{InternalTransferId}")
	public List<DeviceTransferTrackerDto> getDevicesbyInternalTransferId(@PathVariable(value = "InternalTransferId") String InternalTransferId) {
	System.out.println("sudhasudha");
	List<DeviceTransferTrackerDto> list = new ArrayList<>();
	DeviceTransferTrackerDto internaltracker = null;
	List<DeviceTransferTracker> devList = devicetrackerrepo.findAll();
	System.out.println("Testing the lists");
	for (DeviceTransferTracker dv : devList) {
	System.out.println("splitting list one by one");
	if(dv.getInternalTransferId().equals(InternalTransferId)) {
	System.out.println("After Matching setting the values");
	internaltracker = new DeviceTransferTrackerDto();
	internaltracker.setDeviceId(dv.getDevice_Id());
	internaltracker.setDeviceStatus(dv.getDeviceStatus());
	internaltracker.setCustomerId(dv.getCustomerId());
	internaltracker.setNumberOfDevices(dv.getNumberOfDevices());
	internaltracker.setTrackingNumber(dv.getTrackingNumber());
	internaltracker.setCourrierCompany(dv.getCourrierCompany());
	internaltracker.setTransferDescription(dv.getTransferDescription());
	internaltracker.setFromOrigin(dv.getFromOrigin());
	internaltracker.setToDestination(dv.getToDestination());
	internaltracker.setReceivingPartner(dv.getReceivingPartner());
	internaltracker.setDestinationAddress(dv.getDestinationAddress());
	internaltracker.setDateOfTransfer(dv.getDateOfTransfer());
	internaltracker.setExpectedDate(dv.getExpectedDate());
	list.add(internaltracker);

	}


	}
	return list;

	}

	/*
	 * @ResponseBody
	 * 
	 * @GetMapping("/getDropDown/{BP_Id}") public DeviceCreateTransferDropDown
	 * getDDForTranfer(@PathVariable String BP_Id) {
	 * 
	 * DeviceCreateTransferDropDown dt = new DeviceCreateTransferDropDown(); String
	 * fnlStr = gerenateDeviceInterId(); dt.setInternalTransferId(fnlStr);
	 * BusinessPartner partner = businessrepo.findByBP_Id(BP_Id);
	 * dt.setLocations(partner.getLocations()); return dt;
	 * 
	 * }
	 */
	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/getDropDown/{BP_Id}")
	public DeviceCreateTransferDropDown getDDForTranfer(@PathVariable String BP_Id) {

	DeviceCreateTransferDropDown dt = new DeviceCreateTransferDropDown();
	List<DeviceTransferTracker> devicetransferinfo = devicetrackerrepo.findAll();

	for(DeviceTransferTracker dtf :devicetransferinfo) {



	dt.setInternalTransferId(dtf.getInternalTransferId());
	BusinessPartner partner = businessrepo.findByBP_Id(BP_Id);
	dt.setLocations(partner.getLocation());
	}
	return dt;

	}
	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@PostMapping("/DeviceDataStream")
	public boolean DeviceDataStream(@RequestBody List<DeviceDataStreamdto> newDeviceStream) {
	boolean flag = false;
	// Date date = new Date();
	// SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	//
	// String strDate = formatter.format(date);
	// System.out.println("Date"+date);


	int count = 0;



	DeviceDataStream cust = new DeviceDataStream();
	//DeviceDataStream tkr = null;
	try {
	for (DeviceDataStreamdto DeviceData : newDeviceStream) {
	count++;
	}



	List<DeviceDataStream> data = devicedatastreamrepo.findAll();
	// for (DeviceDataStream ds : data) {
	// for (DeviceDataStreamdto trck : newDeviceStream) {
	//
	// if (ds.getEmployee_Id().equals((trck).getEmployee_Id())) {
	//
	// System.out.println("Employee_Id already exists");
	// flag = false;
	// return flag;
	// }
	// }
	// }

	for(DeviceDataStreamdto trck:newDeviceStream) {
	//
	// if (trck.getEmployee_Id().equals(null)) {
	// System.out.println("i am in Null");
	// flag = false;
	// return flag;
	// } else {
	// System.out.println("I am In Else Condition");
	try {
	// General Info
	//System.out.println("trck values "+trck.getSensorUTC());

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",Locale.ENGLISH);
	//
	SimpleDateFormat format2= new SimpleDateFormat("yyyyMMddhhmmss");

	Date date=format2.parse(trck.getSensorUTC());
	String sensorUTC = formatter.format(date);

	//System.out.println("date "+sensorUTC);

	//String strDate = formatter.format(trck.getSensorUTC()).toString();
	//System.out.println("StrDate::::"+strDate);
	cust = new DeviceDataStream();
	//System.out.println("(trck.getUTC() "+(trck.getUTC()));
	cust.setDevice_Id(trck.getDevice_Id());
	cust.setAddress(trck.getAddress());
	cust.setSensorUTC(sensorUTC);
	cust.setLocationType(trck.getLocationType());
	cust.setLatitude(trck.getLatitude());
	cust.setReportPeriod(trck.getReportPeriod());
	cust.setMaxHumidityThreshold(trck.getMaxHumidityThreshold());
	cust.setReporting_zone(trck.getReporting_zone());
	cust.setIccid(trck.getIccid());
	cust.setShakeThreshold(trck.getShakeThreshold());
	cust.setMinTempThreshold(trck.getMinTempThreshold());
	cust.setSensorPhySampleCycle(trck.getSensorPhySampleCycle());
	cust.setCurrent_Terminal_Zone(trck.getCurrent_Terminal_Zone());
	cust.setReporting_Date(trck.getReporting_date());
	cust.setHumdity_2(trck.getHumdity_2());
	cust.setMessage_Type(trck.getMessageType());
	cust.setMileage(trck.getMileage());
	cust.setAirpressure(trck.getAirpressure());
	cust.setSensorReportCycle(trck.getSensorReportCycle());
	cust.setHumidity_1(trck.getHumidity_1());
	cust.setActive(trck.getActive());
	cust.setDevice_slno(trck.getDevice_slno());
	cust.setLongitude(trck.getLongitude());
	cust.setTiltAngle(trck.getTiltAngle());
	cust.setInternal_temperature(trck.getInternal_temperature());
	cust.setTemp_2(trck.getTemp_2());
	cust.setStop(trck.getStop());
	cust.setReporting_time(trck.getReporting_time());
	cust.setModem_IMEI(trck.getModem_IMEI());
	cust.setBase_cycle(trck.getBase_cycle());
	cust.setDrive(trck.getDrive());
	cust.setStatus(trck.getStatus());
	cust.setMinHumidityThreshold(trck.getMinHumidityThreshold());
	cust.setBoxT(trck.getBoxT());
	cust.setCycle(trck.getCycle());
	cust.setSpeed_in_mph(trck.getSpeed_in_mph());
	cust.setReportDistance(trck.getReport_type());
	cust.setTotal_mileage(trck.getTotal_mileage());
	cust.setLocation_way(trck.getLocation_way());
	cust.setCurrent_terminal_time(trck.getCurrent_terminal_time());
	cust.setBoxRH(trck.getBoxRH());
	cust.setEvent(trck.getEvent());
	cust.setValue(trck.getValue());
	cust.setIp_port(trck.getIp_port());
	cust.setLightSampleCycle(trck.getLightSampleCycle());
	cust.setLightDlt(trck.getLightDlt());
	cust.setLightadc(trck.getLightadc());
	cust.setIp_address(trck.getIp_address());
	cust.setMaxTempThreshold(trck.getMaxTempThreshold());
	cust.setSensorSampleCycle(trck.getSensorSampleCycle());
	cust.setReport_type(trck.getReport_type());
	cust.setNum_of_samples(trck.getNum_of_samples());
	cust.setDevice_oem(trck.getDevice_oem());
	cust.setCurrent_terminal_date(trck.getCurrent_terminal_date());





	devicedatastreamrepo.save(cust);

	flag = true;

	} catch (Exception e) {
	// TODO: handle exception
	}
	}


	} catch (Exception e) {
	// TODO: handle exception
	}
	return flag;

	}
	
	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/getINUseDevices")
	public List<DeviceTransferTrackerDto> getInuse() {

		List<DeviceTransferTrackerDto> list = new ArrayList<>();
		DeviceTransferTrackerDto track = null;
		List<Devices> devList = devicerepo.findAll();
			
		for (Devices dv : devList) {
			if (dv.getDeviceStatusReferred().equals("Attached To Shipment")) {
				System.out.println("Device Status:::::"+dv.getDeviceStatusReferred());
			
			track = new DeviceTransferTrackerDto();
			track.setDeviceId(dv.getDeviceId());
			track.setDeviceStatus(dv.getDeviceStatusReferred());
			List<Shipments> shiplist = shiprepo.findByDevice_Id(dv.getDeviceId());
			Collections.reverse(shiplist);
			for (Shipments sh : shiplist) {
				track.setFromOrigin(sh.getRoute_From());
				track.setToDestination(sh.getRoute_To());
				track.setCustomerId(sh.getCustomer_Id());
				track.setGoodsType(sh.getGoods_Desc());
				track.setTime(sh.getCreated_Date());
				track.setLocation(sh.getRoute_From());
				track.setBpId(sh.getCreated_By());
				break;
			}
			List<DeviceDataStream> dataSteamList = devicedatastreamrepo.findByDevice_id(dv.getDeviceId());
			Collections.reverse(dataSteamList);
			for (DeviceDataStream d : dataSteamList) {
				track.setBattery(d.getBattery_Level());
				track.setLongitude(d.getLongitude());
				track.setLatitude(d.getLatitude());
				break;
			}
			List<DeviceTransferTracker> tlist = devicetrackerrepo.findAll();
			for (DeviceTransferTracker t : tlist) {
				if (dv.getDeviceId().equals(t.getDevice_Id())) {
					track.setInternalTransferId(t.getInternalTransferId());
					track.setTrackingNumber(t.getTrackingNumber());
				}
			}
			}
			list.add(track);
			
		}
		return list;

	}
	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/getInternalTransferId")
	public String gerenateDeviceInterId() {
		String afterSplit = null;
		String did = null;
		List<String> dids = new ArrayList<>();
		List<DeviceTransferTracker> trasferl = devicetrackerrepo.findAll();
		for (DeviceTransferTracker t : trasferl) {
			did = t.getInternalTransferId();
			dids.add(did);
		}
		Collections.reverse(dids);
		String lastId = dids.get(0);
		String[] splitLastId = lastId.split("D");
		for (String s : splitLastId) {
			afterSplit = s;
		}
		Integer splitInt = Integer.parseInt(afterSplit);
		Integer increment = splitInt + 1;
		String incrementString = increment.toString();
		String finalStr = "D";
		finalStr = finalStr.concat(incrementString);
		return finalStr;
	}
	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/getDeviceDataa")
	public List<DeviceDataStream> getDevicedata() {

	List<DeviceDataStream> deviceslist = new ArrayList<DeviceDataStream>();

	List<DeviceDataStream> devList = devicedatastreamrepo.findAll();

	for (DeviceDataStream devdata : devList) {
	String devd = devdata.getDevice_Id();
	System.out.println("devd "+devd);
	try {
	if ((devd.equals(("238948")) || devd.equals(("927115")) || devd.equals(("967498"))
	|| devd.equals(("125736")) || devd.equals(("346798")))) {
	System.out.println("devd in for loop " + devd);
	DeviceDataStream dds = new DeviceDataStream();
	dds.setDevice_Id(devd);
	dds.setAddress(devdata.getAddress());
	dds.setLongitude(devdata.getLongitude());
	dds.setLatitude(devdata.getLatitude());
	dds.setInternal_temperature(devdata.getInternal_temperature());
	dds.setSensorUTC(devdata.getSensorUTC());
	dds.setMessage_Type(devdata.getMessage_Type());
	deviceslist.add(dds);
	//return deviceslist;
	// System.out.println("devList forloop"+devlist);
	}


	} catch (Exception e) {
	// TODO: handle exception
	}
	//deviceslist.add(dds);
	}
	// System.out.println("devList "+deviceslist);

	return deviceslist;

	}
}
