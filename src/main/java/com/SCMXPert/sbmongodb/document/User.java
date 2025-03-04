
package com.SCMXPert.sbmongodb.document;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.Pattern;

//import javax.validation.constraints.Pattern;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "User")
public class User {

    @Id
    @Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9 ]+")
    private ObjectId id;
//    @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups =true)
    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private String userid;
   
    @Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9&&[@.] ]+")
	private String email;
    @Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9&&[@#$] ]+")
    private String password;
    @Pattern(message="Invalid Input", regexp = "[a-zA-Z ]+")
    private String fullname;
   
    @Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9 ]+")
    private String UserBP_Id;
    private boolean enabled;
    @Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9 ]+")
    private String[] listrole;
    @Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9&&[@#$] ]+")
    private String AdminUserPassword;
    @Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9 ]+")
    private String AdminUserId;
    @Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9&&[@.] ]+")
    private String AdminEmail;
    @Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9 ]+")
    private String FinanceUserId;
    @Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9&&[@.] ]+")
    private String FinanceEmailAddress;
    @Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9&&[@#$] ]+")
    private String FinancePassword;
    public String getAdminUserPassword() {
		return AdminUserPassword;
	}

	public void setAdminUserPassword(String adminUserPassword) {
		AdminUserPassword = adminUserPassword;
	}

	@Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9 ]+")
	private String Role;
   
	

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}

	private String resettoken;
    private String myrole;
    private String jwtToken;
    private String ExpireValue;
    
    public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public String getMyrole() {
		return myrole;
	}

	public void setMyrole(String myrole) {
		this.myrole = myrole;
	}

	public String[] getListrole() {
		return listrole;
	}

	public void setListrole(String[] listrole) {
		this.listrole = listrole;
	}

	@DBRef
    private Set<Role> roles;

  

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	

	public String getUserBP_Id() {
		return UserBP_Id;
	}

	public void setUserBP_Id(String userBP_Id) {
		UserBP_Id = userBP_Id;
	}

	public String getResettoken() {
		return resettoken;
	}

	public void setResettoken(String resettoken) {
		this.resettoken = resettoken;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getAdminUserId() {
		return AdminUserId;
	}

	public void setAdminUserId(String adminUserId) {
		AdminUserId = adminUserId;
	}

	public String getAdminEmail() {
		return AdminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		AdminEmail = adminEmail;
	}

	public String getFinanceUserId() {
		return FinanceUserId;
	}

	public void setFinanceUserId(String financeUserId) {
		FinanceUserId = financeUserId;
	}

	public String getFinanceEmailAddress() {
		return FinanceEmailAddress;
	}

	public void setFinanceEmailAddress(String financeEmailAddress) {
		FinanceEmailAddress = financeEmailAddress;
	}

	public String getFinancePassword() {
		return FinancePassword;
	}

	public void setFinancePassword(String financePassword) {
		FinancePassword = financePassword;
	}

	public String getExpireValue() {
		return ExpireValue;
	}

	public void setExpireValue(String expireValue) {
		ExpireValue = expireValue;
	}
	
   private List<String> eventAccess;
    
    public List<String> getEventAccess() {
        return eventAccess;
    }

    public void setEventAccess(List<String> eventAccess) {
        this.eventAccess = eventAccess;
    }

}
