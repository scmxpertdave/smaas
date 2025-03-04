package com.SCMXPert.sbmongodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.SCMXPert.sbmongodb.document.DeviceDataStream;
import com.SCMXPert.sbmongodb.document.Devices;
import com.SCMXPert.sbmongodb.repository.DevicesRepository;

@RestController
public class DeviceInfoController {

@Autowired
DevicesRepository devicerepo;

	@ResponseBody
	@RequestMapping(value = "/{Device_Id}", method = RequestMethod.GET)
	public Devices getDeviceInfo(@PathVariable(value = "Device_Id") String Device_Id){
		Devices devInfor=devicerepo.findByDevice_Id(Device_Id);
	
	return devInfor;
	}
}
