package com.SCMXPert.sbmongodb.document;

import javax.validation.constraints.Pattern;

//import javax.validation.constraints.Pattern;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
public class Users {

	@Id
	@Pattern(message="Invalid Input", regexp = "|.[0-9 ]+")
	private ObjectId id;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String AdminId;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  -]+")
	private String AdminCustomer_Id;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String AdminPartner_Role;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String Admin_Name;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String AdminUserId;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String AdminPassword;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  .@]+")
	private String AdminEmail;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String AdminCellPhoneNumber;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String AdminTelephoneNumber;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String AdminCommunication_Method;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String AdminLanguage;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String AdminTime_Zone;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String AdminDate_Format;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String FinanceAdminId;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String FinancePartnerRole;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String FinanceName;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 .@]+")
	private String FinanceEmailAddress;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String FinanceUserId;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String FinancePassword;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String FinanceTelephoneNumber;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String FinanceCellPhoneNumber;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String FinanceCommMethod;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String FinanceLanguage;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String FinanceTimeZone;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String FinanceDateFromat;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String UserId;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String Password;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String Language;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String Time_Zone;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String Date_Format;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String UserBP_Id;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String Customer_Id;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String Partner_Role;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String UserName;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String AdminName;
	@Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9 .@]+")
	private String Email;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String Communication_Method;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String Name1;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String Customer_Name;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 .@]+")
	private String Email_Id;


	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public void setAdminId(String adminId) {
		AdminId = adminId;
	}

	public String getAdminId() {
		return AdminId;
	}

	public String getAdminCustomer_Id() {
		return AdminCustomer_Id;
	}

	public void setAdminCustomer_Id(String adminCustomer_Id) {
		AdminCustomer_Id = adminCustomer_Id;
	}

	public String getAdminPartner_Role() {
		return AdminPartner_Role;
	}

	public void setAdminPartner_Role(String adminPartner_Role) {
		AdminPartner_Role = adminPartner_Role;
	}

	public String getAdmin_Name() {
		return Admin_Name;
	}

	public void setAdmin_Name(String admin_Name) {
		Admin_Name = admin_Name;
	}

	public String getAdminUserId() {
		return AdminUserId;
	}

	public void setAdminUserId(String adminUserId) {
		AdminUserId = adminUserId;
	}

	public String getAdminPassword() {
		return AdminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		AdminPassword = adminPassword;
	}

	public String getAdminEmail() {
		return AdminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		AdminEmail = adminEmail;
	}

	public String getAdminCellPhoneNumber() {
		return AdminCellPhoneNumber;
	}

	public void setAdminCellPhoneNumber(String adminCellPhoneNumber) {
		AdminCellPhoneNumber = adminCellPhoneNumber;
	}

	public String getAdminTelephoneNumber() {
		return AdminTelephoneNumber;
	}

	public void setAdminTelephoneNumber(String adminTelephoneNumber) {
		AdminTelephoneNumber = adminTelephoneNumber;
	}

	public String getAdminCommunication_Method() {
		return AdminCommunication_Method;
	}

	public void setAdminCommunication_Method(String adminCommunication_Method) {
		AdminCommunication_Method = adminCommunication_Method;
	}

	public String getAdminLanguage() {
		return AdminLanguage;
	}

	public void setAdminLanguage(String adminLanguage) {
		AdminLanguage = adminLanguage;
	}

	public String getAdminTime_Zone() {
		return AdminTime_Zone;
	}

	public void setAdminTime_Zone(String adminTime_Zone) {
		AdminTime_Zone = adminTime_Zone;
	}

	public String getAdminDate_Format() {
		return AdminDate_Format;
	}

	public void setAdminDate_Format(String adminDate_Format) {
		AdminDate_Format = adminDate_Format;
	}

	public String getFinanceAdminId() {
		return FinanceAdminId;
	}

	public void setFinanceAdminId(String financeAdminId) {
		FinanceAdminId = financeAdminId;
	}

	public String getFinancePartnerRole() {
		return FinancePartnerRole;
	}

	public void setFinancePartnerRole(String financePartnerRole) {
		FinancePartnerRole = financePartnerRole;
	}

	public String getFinanceName() {
		return FinanceName;
	}

	public void setFinanceName(String financeName) {
		FinanceName = financeName;
	}

	public String getFinanceEmailAddress() {
		return FinanceEmailAddress;
	}

	public void setFinanceEmailAddress(String financeEmailAddress) {
		FinanceEmailAddress = financeEmailAddress;
	}

	public String getFinanceUserId() {
		return FinanceUserId;
	}

	public void setFinanceUserId(String financeUserId) {
		FinanceUserId = financeUserId;
	}

	public String getFinancePassword() {
		return FinancePassword;
	}

	public void setFinancePassword(String financePassword) {
		FinancePassword = financePassword;
	}

	public String getFinanceTelephoneNumber() {
		return FinanceTelephoneNumber;
	}

	public void setFinanceTelephoneNumber(String financeTelephoneNumber) {
		FinanceTelephoneNumber = financeTelephoneNumber;
	}

	public String getFinanceCellPhoneNumber() {
		return FinanceCellPhoneNumber;
	}

	public void setFinanceCellPhoneNumber(String financeCellPhoneNumber) {
		FinanceCellPhoneNumber = financeCellPhoneNumber;
	}

	public String getFinanceCommMethod() {
		return FinanceCommMethod;
	}

	public void setFinanceCommMethod(String financeCommMethod) {
		FinanceCommMethod = financeCommMethod;
	}

	public String getFinanceLanguage() {
		return FinanceLanguage;
	}

	public void setFinanceLanguage(String financeLanguage) {
		FinanceLanguage = financeLanguage;
	}

	public String getFinanceTimeZone() {
		return FinanceTimeZone;
	}

	public void setFinanceTimeZone(String financeTimeZone) {
		FinanceTimeZone = financeTimeZone;
	}

	public String getFinanceDateFromat() {
		return FinanceDateFromat;
	}

	public void setFinanceDateFromat(String financeDateFromat) {
		FinanceDateFromat = financeDateFromat;
	}

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getLanguage() {
		return Language;
	}

	public void setLanguage(String language) {
		Language = language;
	}

	public String getTime_Zone() {
		return Time_Zone;
	}

	public void setTime_Zone(String time_Zone) {
		Time_Zone = time_Zone;
	}

	public String getDate_Format() {
		return Date_Format;
	}

	public void setDate_Format(String date_Format) {
		Date_Format = date_Format;
	}

	public String getUserBP_Id() {
		return UserBP_Id;
	}

	public void setUserBP_Id(String userBP_Id) {
		UserBP_Id = userBP_Id;
	}

	public String getCustomer_Id() {
		return Customer_Id;
	}

	public void setCustomer_Id(String customer_Id) {
		Customer_Id = customer_Id;
	}

	public String getPartner_Role() {
		return Partner_Role;
	}

	public void setPartner_Role(String partner_Role) {
		Partner_Role = partner_Role;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getAdminName() {
		return AdminName;
	}

	public void setAdminName(String adminName) {
		AdminName = adminName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getCommunication_Method() {
		return Communication_Method;
	}

	public void setCommunication_Method(String communication_Method) {
		Communication_Method = communication_Method;
	}

	public String getName1() {
		return Name1;
	}

	public void setName1(String name1) {
		Name1 = name1;
	}

	public String getCustomer_Name() {
		return Customer_Name;
	}

	public void setCustomer_Name(String customer_Name) {
		Customer_Name = customer_Name;
	}

	public String getEmail_Id() {
		return Email_Id;
	}

	public void setEmail_Id(String email_Id) {
		Email_Id = email_Id;
	}



}
