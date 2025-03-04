package com.SCMXPert.sbmongodb.repository;

import java.util.List;
import com.SCMXPert.sbmongodb.document.DeviceDataStream;
import com.SCMXPert.sbmongodb.document.Shipments;

public interface ShipmentDetailsCustom {

	List<Shipments> getShipments(String Customer_id, String Created_By);
	List<Shipments> getShipmentsList(String Customer_id);
	
	float event_status(String Shipment_Id);
	
	List<DeviceDataStream> getDeviceDataStream(String Device_Id, String SensorUTC);

	List<String[]> getlatLong(String Device_Id, String SensorUTC);
	List<String[]> getlatLang(String Device_Id, String SensorUTC);
	DeviceDataStream getDeviceDataStreamSingleDocumentDate(String Device_Id);
	List<DeviceDataStream> getShipmentTransactionDeviceData(String Device_Id);
	
}
