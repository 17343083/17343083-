package server;
import java.math.BigInteger;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.fisco.bcos.repict.client.*;
import dataP.RegisterData;
import dataP.RegisterDataResponse;
import dataP.GeneralResponse;
import dataP.LoginData;
import dataP.UserProfile;
import dataP.NameData;
import dataP.Data;
import dataP.AddRepict;
import dataP.TransferRepict;
import dataP.Finance;
import dataP.PayBack;
import dataP.payFor;
import dataP.Validate;
import dataP.Setting;

@RestController
public class dataController {

  /*@RequestMapping("/greeting")
  public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
    return new Greeting(counter.incrementAndGet(),
              String.format(template, name));
  }*/
	@RequestMapping("/api/profile")
	  public UserProfile profile() {
		UserProfile u = new UserProfile();
		u.data = new Data();
		u.data.name = "";
		u.data.avatar = "https://img.alicdn.com/tfs/TB1L6tBXQyWBuNjy0FpXXassXXa-80-80.png";
		u.data.department = "";
		u.data.userid = "";
		u.status = "SUCCESS";
		return u;
	  }
  
  @RequestMapping("/api/register")
  public GeneralResponse register(@RequestBody RegisterData d) {
	RepictClient client = new RepictClient(d.name);
	try {
		client.initialize();
		BigInteger c = new BigInteger((d.cType.equals("是") ? "0" : "1"));
		BigInteger b = new BigInteger(d.balance);
		Properties prop = new Properties();
		final Resource contractResource = new ClassPathResource("contract.properties");
		prop.load(contractResource.getInputStream());
		String contractAddress = prop.getProperty("address");
		if(contractAddress == null || contractAddress.trim().equals("")) {
			client.deployRepictAndRecordAddr();
		}
		System.out.println("asdasd");
		
		boolean result = client.setCompany(d.name,b,c,d.password,d.phone,d.email);
		System.out.printf("%s,%d,%d,%s,%s,%s,%s",d.name,b,c,d.password,d.phone,d.email,client.getCompanyAddress(d.name));
		if(result == true) {
			return new GeneralResponse("SUCCESS","");
		}
		else {
			return new GeneralResponse("FAILED","");
		}
	}catch(Exception e) {
		return new GeneralResponse("FAILED","");
	}
  }
  
  @RequestMapping("/api/login")
  public GeneralResponse login(@RequestBody LoginData d) {
	RepictClient client = new RepictClient(d.username);
	try {
		client.initialize();
		//client.deployRepictAndRecordAddr();
		RegisterData rd = client.getCompany(client.getCompanyAddress(d.username));
		if(rd.name.equals(d.username) && rd.password.equals(d.password))
			return new GeneralResponse("SUCCESS","user");
		else {
			return new GeneralResponse("FAILED","");
		}
	}catch(Exception e) {
		return new GeneralResponse("FAILED","");
	}
  }
  
  @RequestMapping("/api/info")
  public RegisterDataResponse info(@RequestBody NameData d) {
	System.out.println(d.username);
	RepictClient client = new RepictClient(d.username);
	try {
		client.initialize();
		//client.deployRepictAndRecordAddr();
		RegisterData rd = client.getCompany(client.getCompanyAddress(d.username));
		RegisterDataResponse rdr = new RegisterDataResponse();
		rdr.status = "SUCCESS";
		rdr.data = new RegisterData();
		rdr.data = rd;
		return rdr;
	}catch(Exception e) {
		RegisterDataResponse rdr = new RegisterDataResponse();
		rdr.status = "FAILED";
		return rdr;
	}
  }
  
  @RequestMapping("/api/setting")
  public GeneralResponse setting(@RequestBody Setting d) {
	System.out.println(d.name);
	RepictClient client = new RepictClient(d.name);
	try {
		client.initialize();
		//client.deployRepictAndRecordAddr();
		boolean result = client.updateCompany(d.name,d.phone,d.email);
		if(result)
			return new GeneralResponse("SUCCESS","user");
		else return new GeneralResponse("FAILED","");
	}catch(Exception e) {
		return new GeneralResponse("FAILED","");
	}
  }
  
  @RequestMapping("/api/addRepict")
  public GeneralResponse addRepict(@RequestBody AddRepict d) {
	RepictClient client = new RepictClient(d.from);
	try {
		client.initialize();
		//client.deployRepictAndRecordAddr();
		boolean result = client.addRepict(d.from,d.to,d.count,d.expiredTime);
		System.out.println(d.from);
		System.out.println(d.to);
		System.out.println(d.count);
		if(result)
			return new GeneralResponse("SUCCESS","user");
		else return new GeneralResponse("FAILED","");
	}catch(Exception e) {
		return new GeneralResponse("FAILED","");
	}
  }
  
  @RequestMapping("/api/transferRepict")
  public GeneralResponse transferRepict(@RequestBody TransferRepict d) {
	RepictClient client = new RepictClient(d.originFrom);
	try {
		client.initialize();
		//client.deployRepictAndRecordAddr();
		boolean result = client.transferRepict(d.originFrom,d.from,d.to,d.count);
		System.out.println(d.originFrom);
		System.out.println(d.from);
		System.out.println(d.to);
		System.out.println(d.count);
		if(result)
			return new GeneralResponse("SUCCESS","user");
		else return new GeneralResponse("FAILED","");
	}catch(Exception e) {
		return new GeneralResponse("FAILED","");
	}
  }

  @RequestMapping("/api/finance")
  public GeneralResponse finance(@RequestBody Finance d) {
	RepictClient client = new RepictClient(d.name);
	try {
		client.initialize();
		//client.deployRepictAndRecordAddr();
		boolean result = client.finance(d.name,d.from,d.repictFrom,d.count);
		System.out.println(d.name);
		System.out.println(d.from);
		System.out.println(d.repictFrom);
		System.out.println(d.count);
		if(result)
			return new GeneralResponse("SUCCESS","user");
		else return new GeneralResponse("FAILED","");
	}catch(Exception e) {
		return new GeneralResponse("FAILED","");
	}
  }
  
  @RequestMapping("/api/payBack")
  public GeneralResponse payBack(@RequestBody PayBack d) {
	RepictClient client = new RepictClient(d.to);
	try {
		client.initialize();
		//client.deployRepictAndRecordAddr();
		boolean result = client.payBack(d.from,d.to,d.count);
		System.out.println(d.from);
		System.out.println(d.to);
		System.out.println(d.count);
		if(result)
			return new GeneralResponse("SUCCESS","user");
		else return new GeneralResponse("FAILED","");
	}catch(Exception e) {
		return new GeneralResponse("FAILED","");
	}
  }
  
  @RequestMapping("/api/payFor")
  public GeneralResponse payFor(@RequestBody payFor d) {
	RepictClient client = new RepictClient(d.from);
	try {
		client.initialize();
		//client.deployRepictAndRecordAddr();
		boolean result = client.payFor(d.from,d.to,d.count);
		System.out.println(d.from);
		System.out.println(d.to);
		System.out.println(d.count);
		if(result)
			return new GeneralResponse("SUCCESS","user");
		else return new GeneralResponse("FAILED","");
	}catch(Exception e) {
		return new GeneralResponse("FAILED","");
	}
  }
  
  @RequestMapping("/api/validate")
  public GeneralResponse validate(@RequestBody Validate d) {
	RepictClient client = new RepictClient(d.from);
	try {
		client.initialize();
		//client.deployRepictAndRecordAddr();
		boolean result = client.validate(d.name,d.from,d.to,d.count);
		System.out.println(d.name);
		System.out.println(d.from);
		System.out.println(d.to);
		System.out.println(d.count);
		if(result)
			return new GeneralResponse("SUCCESS","user");
		else return new GeneralResponse("FAILED","");
	}catch(Exception e) {
		return new GeneralResponse("FAILED","");
	}
  }
}