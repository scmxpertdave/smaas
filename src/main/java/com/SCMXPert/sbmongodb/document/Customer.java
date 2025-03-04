package com.SCMXPert.sbmongodb.document;

import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.Pattern;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

//import jakarta.validation.constraints.Pattern;

@Document(collection = "Customer")
public class Customer {

	@Id
	private ObjectId id;

	// General Information
	private String Customer_Id; //
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ., ]+")
	private String Name1; //
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String Name2; //
	private String Type;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String External_Number; //
	private String[] Business_Partner_Id; // Customer Bussiness partenr ???
	private String[] Device_Id;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String DUNS; //
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String G10; //
	private String CustomerSector;
	private String CustomerStatus;
	private String Customer_Business;

	@Field(value = "Departments")
	private String[] Departments;
	
	@Field(value = "EventStatus")
	private String[] EventStatus;

	// Customer Address
	@Field(value = "Addresses")
	private List<Addresses> Addresses; //
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 .@-_]+")
	private String WebSite;
	// Communication
	@Field(value = "Contact")
	private List<Contact> Contact; //

	// Finance Information // Added new Fields
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String TaxNumber1;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String TaxNumber2;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String PaymentTerms;
	private String Currency;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String BankName;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String BankRouting;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String BankAccNo;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String DataShardRegion;

	// Finance Contact Person //Added New Fields
	/*
	 * private String FinanceAdminId; private String FinancePartnerRole; private
	 * String FinanceName; private String FinanceEmailAddress; private String
	 * FinanceUserId; private String FinancePassword; private String
	 * FinanceTelephoneNumber; private String FinanceCellPhoneNumber; private
	 * String FinanceCommMethod; private String FinanceLanguage; private String
	 * FinanceTimeZone; private String FinanceDateFromat;
	 */

	@Field(value = "Route")
	private List<Route> Route;
	
	@Field(value = "BusinessPartner")
	private List<BPList> BusinessPartner;
	
	@Field(value = "Goods")
	private List<Goods> Goods;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getCustomer_Id() {
		return Customer_Id;
	}

	public void setCustomer_Id(String customer_Id) {
		Customer_Id = customer_Id;
	}

	public String getName1() {
		return Name1;
	}

	public void setName1(String name1) {
		Name1 = name1;
	}

	public String getName2() {
		return Name2;
	}

	public void setName2(String name2) {
		Name2 = name2;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getExternal_Number() {
		return External_Number;
	}

	public void setExternal_Number(String external_Number) {
		External_Number = external_Number;
	}

	public String[] getBusiness_Partner_Id() {
		return Business_Partner_Id;
	}

	public void setBusiness_Partner_Id(String[] business_Partner_Id) {
		Business_Partner_Id = business_Partner_Id;
	}

	public String[] getDevice_Id() {
		return Device_Id;
	}

	public void setDevice_Id(String[] device_Id) {
		Device_Id = device_Id;
	}

	public String getDUNS() {
		return DUNS;
	}

	public void setDUNS(String dUNS) {
		DUNS = dUNS;
	}

	public String getG10() {
		return G10;
	}

	public void setG10(String g10) {
		G10 = g10;
	}

	public void setCustomerSector(String customerSector) {
		CustomerSector = customerSector;
	}

	public String getCustomerSector() {
		return CustomerSector;
	}

	public void setCustomerStatus(String customerStatus) {
		CustomerStatus = customerStatus;
	}

	public String getCustomerStatus() {
		return CustomerStatus;
	}

	public String[] getDepartments() {
		return Departments;
	}

	public void setDepartments(String[] departments) {
		Departments = departments;
	}

	public List<Addresses> getAddresses() {
		return Addresses;
	}

	public void setAddresses(List<Addresses> addresses) {
		Addresses = addresses;
	}

	public void setWebSite(String webSite) {
		WebSite = webSite;
	}

	public String getWebSite() {
		return WebSite;
	}

	public List<Contact> getContact() {
		return Contact;
	}

	public void setContact(List<Contact> contact) {
		Contact = contact;
	}

	public String getTaxNumber1() {
		return TaxNumber1;
	}

	public void setTaxNumber1(String taxNumber1) {
		TaxNumber1 = taxNumber1;
	}

	public String getTaxNumber2() {
		return TaxNumber2;
	}

	public void setTaxNumber2(String taxNumber2) {
		TaxNumber2 = taxNumber2;
	}

	public String getPaymentTerms() {
		return PaymentTerms;
	}

	public void setPaymentTerms(String paymentTerms) {
		PaymentTerms = paymentTerms;
	}

	public String getCurrency() {
		return Currency;
	}

	public void setCurrency(String currency) {
		Currency = currency;
	}

	public String getBankName() {
		return BankName;
	}

	public void setBankName(String bankName) {
		BankName = bankName;
	}

	public String getBankRouting() {
		return BankRouting;
	}

	public void setBankRouting(String bankRouting) {
		BankRouting = bankRouting;
	}

	public String getBankAccNo() {
		return BankAccNo;
	}

	public void setBankAccNo(String bankAccNo) {
		BankAccNo = bankAccNo;
	}

	public String getDataShardRegion() {
		return DataShardRegion;
	}

	public void setDataShardRegion(String dataShardRegion) {
		DataShardRegion = dataShardRegion;
	}

	public List<Route> getRoute() {
		return Route;
	}

	public void setRoute(List<Route> route) {
		Route = route;
	}

	public List<Goods> getGoods() {
		return Goods;
	}

	public void setGoods(List<Goods> goods) {
		Goods = goods;
	}

	public List<BPList> getBusinessPartner() {
		return BusinessPartner;
	}

	public void setBusinessPartner(List<BPList> businessPartner) {
		BusinessPartner = businessPartner;
	}

	public String getCustomer_Business() {
		return Customer_Business;
	}

	public void setCustomer_Business(String customer_Business) {
		Customer_Business = customer_Business;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", Customer_Id=" + Customer_Id + ", Name1=" + Name1 + ", Name2=" + Name2
				+ ", Type=" + Type + ", External_Number=" + External_Number + ", Business_Partner_Id="
				+ Arrays.toString(Business_Partner_Id) + ", Device_Id=" + Arrays.toString(Device_Id) + ", DUNS=" + DUNS
				+ ", G10=" + G10 + ", CustomerSector=" + CustomerSector + ", CustomerStatus=" + CustomerStatus
				+ ", Customer_Business=" + Customer_Business + ", Departments=" + Arrays.toString(Departments)
				+ ", Addresses=" + Addresses + ", WebSite=" + WebSite + ", Contact=" + Contact + ", TaxNumber1="
				+ TaxNumber1 + ", TaxNumber2=" + TaxNumber2 + ", PaymentTerms=" + PaymentTerms + ", Currency="
				+ Currency + ", BankName=" + BankName + ", BankRouting=" + BankRouting + ", BankAccNo=" + BankAccNo
				+ ", DataShardRegion=" + DataShardRegion + ", Route=" + Route + ", BusinessPartner=" + BusinessPartner
				+ ", Goods=" + Goods + ", getId()=" + getId() + ", getCustomer_Id()=" + getCustomer_Id()
				+ ", getName1()=" + getName1() + ", getName2()=" + getName2() + ", getType()=" + getType()
				+ ", getExternal_Number()=" + getExternal_Number() + ", getBusiness_Partner_Id()="
				+ Arrays.toString(getBusiness_Partner_Id()) + ", getDevice_Id()=" + Arrays.toString(getDevice_Id())
				+ ", getDUNS()=" + getDUNS() + ", getG10()=" + getG10() + ", getCustomerSector()=" + getCustomerSector()
				+ ", getCustomerStatus()=" + getCustomerStatus() + ", getDepartments()="
				+ Arrays.toString(getDepartments()) + ", getAddresses()=" + getAddresses() + ", getWebSite()="
				+ getWebSite() + ", getContact()=" + getContact() + ", getTaxNumber1()=" + getTaxNumber1()
				+ ", getTaxNumber2()=" + getTaxNumber2() + ", getPaymentTerms()=" + getPaymentTerms()
				+ ", getCurrency()=" + getCurrency() + ", getBankName()=" + getBankName() + ", getBankRouting()="
				+ getBankRouting() + ", getBankAccNo()=" + getBankAccNo() + ", getDataShardRegion()="
				+ getDataShardRegion() + ", getRoute()=" + getRoute() + ", getGoods()=" + getGoods()
				+ ", getBusinessPartner()=" + getBusinessPartner() + ", getCustomer_Business()="
				+ getCustomer_Business() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	public String[] getEventStatus() {
		return EventStatus;
	}

	public void setEventStatus(String[] eventStatus) {
		EventStatus = eventStatus;
	}

}
