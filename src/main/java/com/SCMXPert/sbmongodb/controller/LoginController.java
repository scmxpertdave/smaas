package com.SCMXPert.sbmongodb.controller;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.time.Instant;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.UserDeniedAuthorizationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.SCMXPert.sbmongodb.config.Response;
import com.SCMXPert.sbmongodb.customImpl.JWTMethods;
import com.SCMXPert.sbmongodb.document.BusinessPartner;
import com.SCMXPert.sbmongodb.document.Login;
import com.SCMXPert.sbmongodb.document.Role;
import com.SCMXPert.sbmongodb.document.UnApprovedUsers;
import com.SCMXPert.sbmongodb.document.User;
import com.SCMXPert.sbmongodb.document.UserDto;
import com.SCMXPert.sbmongodb.repository.BusinessPartnerRepository;
import com.SCMXPert.sbmongodb.repository.LoginRepository;
import com.SCMXPert.sbmongodb.repository.RoleRepository;
import com.SCMXPert.sbmongodb.repository.UserDetailsRepository;
import com.SCMXPert.sbmongodb.repository.UserRepository;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Controller
@RequestMapping("/SCMXPert")

@CrossOrigin(origins = { "https://www.smaas.live", "https://smaas.live", "http://172.17.211.224:3000",
		"http://127.0.0.1:8081","https://www.smaas.org" })

public class LoginController {

	@Autowired
	private LoginRepository loginrepo;
	
	@Autowired
	private UserDetailsRepository userdetailsrepo;
	
	@Autowired
	private BusinessPartnerRepository businessPartnerRepository;

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	JWTMethods jwtMethods;
	
//	@Value("${secretKey}")
//	public String secretKey;
//	@Value("${iss}")
//	public String iss;
	@Value("${tokenExpireNo}")
	public int exp;

	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserRepository userrepo;

	@Autowired
	private RoleRepository rolerepo;

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@PostMapping("/Login")
	public String createUser(@RequestBody Login login) {
		/*
		 * Query query = new Query();
		 * query.addCriteria(Criteria.where("customer_id").is(login. getCustomer_id()));
		 * boolean bool =loginrepo.findOne(query); if(bool){
		 */
		/*
		 * if(loginrepo.findBycustomer_id1(login.getCustomer_id()).toString() != null ){
		 * 
		 * return "Already Customer_ID exists"; }else{
		 * System.out.println(loginrepo.findBycustomer_id1(login.getCustomer_id(
		 * )).toString().length()); loginrepo.save(login); return login.toString(); }
		 */
		/*
		 * }else{
		 * 
		 * return "Already With Same Custome_ID user exists"; }
		 */
		return "Already With Same Custome_ID user exists";
	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/getValidation/{email}")
	public List<Login> demo(@PathVariable String email) {

		return loginrepo.findByemail(email);

	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@PostMapping("/LoginAuth")
	public User loginKong(@RequestBody User userdt) {
		
		User user = userrepo.findByUserid(userdt.getUserid());
		User user1=new User();
		if (user.isEnabled() == false) {
			System.out.println("Please Contact Administrator........");
			throw new UserDeniedAuthorizationException("Please Contact Administrator.......");
		}
		else if (user != null) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			if(encoder.matches(userdt.getPassword(), user.getPassword())) {
				System.out.println(user.getUserid());
				System.out.println(userdt.getPassword());
				
				Key key1 = Keys.secretKeyFor(SignatureAlgorithm.HS256);
				System.out.println("key1 "+key1);
				System.out.println(key1);
				//String jws = Jwts.builder().setSubject("Joe").signWith(key).compact();
			//	System.out.println("jws "+jws);
				long now = Instant.now().getEpochSecond();
				long expireValue=now+exp*60;
			//	String tokenExpireValue=String.valueOf( expireValue);
			//	System.out.println("tokenExpireValue "+tokenExpireValue);
			//	System.out.println("iss "+iss);
//				String header = "{\n"
//						+ "\"alg\": \"HS256\",\n"
//						+ "\"typ\": \"JWT\"\n"
//						+ "}";
				String header="{\n"
						+ "\"alg\": \"HS256\",\n"
						+ "\"typ\": \"JWT\"\n"
						+ "}";
//				String claims = "{\n"
//						+ "\"name\": \"John Doe\",\n"
//						+ "\"exp\":"+ expireValue+",\n"
//						+ "\"iss\":"+iss+"\n"
//						+ "}";
				
				
				String claims="{\n"     //remove for original code
						+ "\"name\": \"John Doe\",\n"
						+ "\"exp\": "+expireValue+",\n"
						+ "\"iss\":\"4Gq3iVKqrfWxfpCMZ908Xc9gv0aiP0Q1\"\n"
						+ "}";
				
				
			
				System.out.println("claims "+claims);
			//	String encodedHeader = base64URLEncode( header.getBytes("UTF-8") );
				String encodedHeader="";
				String encodedClaims="";
				String secretKey="3bphBbbOvoyqjfgAPZ8Q8VWSW4IsHLx2";
				try {
					 encodedHeader = Base64.getUrlEncoder().encodeToString(header.getBytes("UTF-8"));
					 encodedClaims =  Base64.getUrlEncoder().encodeToString( claims.getBytes("UTF-8") );
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				String concatenated = encodedHeader + '.' + encodedClaims;
				String signature="";
				try {
					signature=	jwtMethods.hmacSha256(concatenated,secretKey);
				} catch (java.security.InvalidKeyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String jws = concatenated + '.' +signature;
				System.out.println("jws "+jws);
				user1.setUserid(user.getUserid());
				user1.setJwtToken(jws);
				user1.setRoles(user.getRoles());
			//	user1.setExpireValue(tokenExpireValue);			
				return user1;
			}
			
			
		} else {
			throw new UsernameNotFoundException("username not found");
			
		}
		return null;
		
	}
	
	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@PostMapping("/signUp")
	public Response createUser(@RequestBody UserDto userdt) {

		Response response = new Response();
		User userexists = userrepo.findByEmail(userdt.getEmail());
		if (userexists != null) {
			System.out.println("The user alredy exists");
			response.setMessasge("The user alredy exists");
			response.setFlag(false);
			return response;
		} else {
			User user = new User();
			user.setEmail(userdt.getEmail().toLowerCase());
			user.setPassword(bCryptPasswordEncoder.encode(userdt.getPassword()));
			user.setEnabled(true);
			Role userRole = rolerepo.findByRole(userdt.getRole());
			user.setRoles(new HashSet<>(Arrays.asList(userRole)));
			userrepo.save(user);
			response.setMessasge("User Created");
			response.setFlag(true);
		}
		return response;

	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN')")
	@GetMapping("/getAllRoles")
	public List<Role> getAllRoles() {
		return rolerepo.findAll();
	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN')")
	@GetMapping("/getAllUsers")
	public List<User> getAllUsers() {
		return userrepo.findAll();
	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/getUsers")
	public List<User> getUsers() {
		return userrepo.findAll();
	}

//	@ResponseBody
//	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
//	@GetMapping("/getUser/{userid}")
//	public User getUser(@PathVariable String userid) {
//		User usr = userrepo.findByUserid(userid);
//		usr.setPassword(null);
//		usr.setResettoken(null);
//		return usr;
//	}
	
	
	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN', 'BUSINESSPARTNER')")
	@GetMapping("/getUser/{userid}")
    public User getUser(@PathVariable String userid) {
        User usr = userrepo.findByUserid(userid);
        usr.setPassword(null);
        usr.setResettoken(null);
        String userId = userid.split("-")[1]; //B0023, A01, like this
        System.out.println(userId);
        if(userId.startsWith("B"))
        {
            BusinessPartner findByBP_Id = businessPartnerRepository.findByBP_Id(userId);
            System.out.println(findByBP_Id);
            List<String> collect = findByBP_Id.getEvents().stream().map(eve -> eve.getEvent_Status()).collect(Collectors.toList());
            System.out.println("Collections"+collect);
            usr.setEventAccess(collect);
        }
        
        return usr;
    }
	
	/*
	 * @GetMapping("getUsers/{userid}") public List<UserDetails>
	 * getUserdata(@PathVariable String userid) { System.out.print(userid);
	 * List<UserDetails> sp = userdetailsrepo.findByBP_Id(userid.trim()); return sp;
	 * }
	 */

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN')")
	@PostMapping("/addNewRole")
	public Response addNewRole(@RequestBody Role role) {

		Response response = new Response();
		Role roleexists = rolerepo.findByRole(role.getRole());
		if (roleexists != null) {
			System.out.println("The role alredy exists");
			response.setMessasge("The role alredy exists");
			response.setFlag(false);
			return response;
		} else {
			rolerepo.insert(role);
			response.setMessasge("Role Created");
			response.setFlag(true);
			return response;
		}

	}

	@ResponseBody
	@PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN')")
	@PostMapping("/updateUnApprovedUsers")
	public Response updateListofUnApprovedUsers(@RequestBody List<UnApprovedUsers> userEmails) {
		Response response = new Response();
		for (UnApprovedUsers user : userEmails) {
			Query query = new Query();
			query.addCriteria(
					new Criteria().andOperator(Criteria.where("email").is(user.getUserEmail().toLowerCase())));
			Update update = new Update();
			update.set("enabled", true);
			mongoTemplate.updateMulti(query, update, "user");
			response.setMessasge("Users Approved");
			response.setFlag(true);
		}
		return response;
	}

}
