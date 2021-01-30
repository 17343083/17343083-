package org.fisco.bcos.repict.client;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Properties;

import dataP.RegisterData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.fisco.bcos.repict.contract.Repict;
import org.fisco.bcos.repict.contract.Repict.GetCompanyEventResponse;
import org.fisco.bcos.repict.contract.Repict.AddReciptEventResponse;
import org.fisco.bcos.repict.contract.Repict.TransferReciptEventResponse;
import org.fisco.bcos.repict.contract.Repict.FinanceEventResponse;
import org.fisco.bcos.repict.contract.Repict.PayBackEventResponse;
import org.fisco.bcos.repict.contract.Repict.PayForEventResponse;
import org.fisco.bcos.repict.contract.Repict.ValidateEventResponse;
import org.fisco.bcos.repict.contract.Repict.GetReciptEventResponse;
import org.fisco.bcos.repict.contract.Repict.SetCompanyEventResponse;
import org.fisco.bcos.channel.client.Service;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.Keys;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.channel.ChannelEthereumService;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple1;
import org.fisco.bcos.web3j.tuples.generated.Tuple2;
import org.fisco.bcos.web3j.tuples.generated.Tuple6;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class RepictClient {
    static Logger logger = LoggerFactory.getLogger(RepictClient.class);

	private Web3j web3j;

	private Credentials credentials;
	
	public String name;
	
	public RepictClient(String name) {
		this.name = name;
	}

	public Web3j getWeb3j() {
		return web3j;
	}

	public void setWeb3j(Web3j web3j) {
		this.web3j = web3j;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
    }
    
    public void recordRepictAddr(String address) throws FileNotFoundException, IOException {
		Properties prop = new Properties();
		prop.setProperty("address", address);
		final Resource contractResource = new ClassPathResource("contract.properties");
		FileOutputStream fileOutputStream = new FileOutputStream(contractResource.getFile());
		prop.store(fileOutputStream, "contract address");
    }
    
    public String loadRepictAddr() throws Exception {
		// load Repict contact address from contract.properties
		Properties prop = new Properties();
		final Resource contractResource = new ClassPathResource("contract.properties");
		prop.load(contractResource.getInputStream());

		String contractAddress = prop.getProperty("address");
		if (contractAddress == null || contractAddress.trim().equals("")) {
			throw new Exception(" load Repict contract address failed, please deploy it first. ");
		}
		logger.info(" load Repict address from contract.properties, address is {}", contractAddress);
		return contractAddress;
    }
    
    public void initialize() throws Exception {

		// init the Service
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		Service service = context.getBean(Service.class);
		service.run();

		ChannelEthereumService channelEthereumService = new ChannelEthereumService();
		channelEthereumService.setChannelService(service);
		Web3j web3j = Web3j.build(channelEthereumService, 1);

		// init Credentials
		Credentials credentials = Credentials.create(Keys.createEcKeyPair());

		setCredentials(credentials);
		setWeb3j(web3j);

		logger.debug(" web3j is " + web3j + " ,credentials is " + credentials);
    }
    
    private static BigInteger gasPrice = new BigInteger("30000000");
	private static BigInteger gasLimit = new BigInteger("30000000");

	public void deployRepictAndRecordAddr() {

		try {
			Repict repict = Repict.deploy(web3j, credentials, new StaticGasProvider(gasPrice, gasLimit)).send();
			System.out.println(" deploy Repict success, contract address is " + repict.getContractAddress());

			recordRepictAddr(repict.getContractAddress());
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println(" deploy Repict contract failed, error message is  " + e.getMessage());
		}
    }
	
	 public boolean setCompany(String _name, BigInteger _balance, BigInteger _cType, String _password, String _phone, String _email) {
	        try {
				String contractAddress = loadRepictAddr();
	            Repict repict = Repict.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
	            TransactionReceipt r = repict.setCompany(_name,_balance,_cType,_password,_phone,_email).send();
	            List<SetCompanyEventResponse> rl = repict.getSetCompanyEvents(r);
	            System.out.printf("%s,%d,%d,%s%s,%s\n", _name,_balance,_cType,_password,_phone,_email);
	            if(!rl.isEmpty()){
	                //String company = rl.get(0).company;
	            	System.out.println(rl.get(0).company);
	                return true;
	            }else{
	                System.out.println(" event log not found, maybe transaction not exec. ");
	                return false;
	            }
			} catch (Exception e) {
				// e.printStackTrace();
				logger.error(" setCompany exception, error message is {}", e.getMessage());

				System.out.printf(" set company failed, error message is %s\n", e.getMessage());
			}
	        return false;
	    }
	 
	 public String getCompanyAddress(String _name) {
	        try {
				String contractAddress = loadRepictAddr();
	            Repict repict = Repict.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
	            TransactionReceipt r = repict.getCompanyAddress(_name).send();
	            Tuple1<String> result = repict.getGetCompanyAddressOutput(r);
	            System.out.println(result.getValue1());
	            return result.getValue1();
			} catch (Exception e) {
				// e.printStackTrace();
				logger.error(" getCompanyAddress exception, error message is {}", e.getMessage());

				System.out.printf(" get company address failed, error message is %s\n", e.getMessage());
			}
	        return "error";
	    }
	
	 public RegisterData getCompany(String _addr) {
		 try {
				String contractAddress = loadRepictAddr();
	            Repict repict = Repict.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
	            TransactionReceipt r = repict.getCompany(_addr).send();
	            Tuple6<String, BigInteger, BigInteger, String, String, String> result = repict.getGetCompanyOutput(r);
	            RegisterData rd = new RegisterData();
	            rd.name = result.getValue1();
	            rd.cType = result.getValue2().toString();
	            rd.balance = result.getValue3().toString();
	            rd.email = result.getValue4();
	            rd.phone = result.getValue5();
	            rd.password = result.getValue6();
	            return rd;
			} catch (Exception e) {
				// e.printStackTrace();
				logger.error(" getCompany exception, error message is {}", e.getMessage());

				System.out.printf(" get company failed, error message is %s\n", e.getMessage());
			}
		 return new RegisterData();
	 }
	 
	 public boolean updateCompany(String _name, String _phone, String _email) {
	        try {
				String contractAddress = loadRepictAddr();
	            Repict repict = Repict.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
	            TransactionReceipt r = repict.updateCompany(_name,_phone,_email).send();
	            return true;
			} catch (Exception e) {
				// e.printStackTrace();
				logger.error(" updateCompany exception, error message is {}", e.getMessage());

				System.out.printf(" update company failed, error message is %s\n", e.getMessage());
			}
	        return false;
	    }
	 
	public boolean addRepict(String _from, String _to, String _count,String _expiredTime) {
		try {
			String contractAddress = loadRepictAddr();
            Repict repict = Repict.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            TransactionReceipt r = repict.addRecipt(_from,getCompanyAddress(_to),new BigInteger(_count),new BigInteger(_expiredTime)).send();
            List<AddReciptEventResponse> l = repict.getAddReciptEvents(r);
            System.out.println("-----");
            System.out.println(getCompanyAddress(l.get(0).from));
            System.out.println("-----");
            System.out.println(l.get(0).to);
            System.out.println("-----");
            System.out.println(getCompanyAddress(_to));
            System.out.println("-----");
            System.out.println(_count);
            System.out.println("-----");
            return true;
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error(" addRepict exception, error message is {}", e.getMessage());

			System.out.printf(" add repict failed, error message is %s\n", e.getMessage());
		}
        return false;
	}
	
	public boolean transferRepict(String _name, String _from, String _to,String _count) {
		try {
			String contractAddress = loadRepictAddr();
            Repict repict = Repict.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            TransactionReceipt r = repict.transferRecipt(_name,getCompanyAddress(_from),getCompanyAddress(_to),new BigInteger(_count)).send();
            return true;
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error(" tranferRepict exception, error message is {}", e.getMessage());

			System.out.printf(" transfer repict failed, error message is %s\n", e.getMessage());
		}
        return false;
	}
	
	public boolean finance(String _name, String _company, String _reciptFrom,String _count) {
		try {
			String contractAddress = loadRepictAddr();
            Repict repict = Repict.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            TransactionReceipt r = repict.definance(_name,getCompanyAddress(_company),_reciptFrom,new BigInteger("1000")).send();
            
            return true;
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error(" financeRepict exception, error message is {}", e.getMessage());

			System.out.printf(" finance repict failed, error message is %s\n", e.getMessage());
		}
        return false;
	}
	
	public boolean payBack(String _from, String _to,String _count) {
		try {
			String contractAddress = loadRepictAddr();
            Repict repict = Repict.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            TransactionReceipt r = repict.payBack(_to,getCompanyAddress(_from)).send();
            return true;
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error(" payBackRepict exception, error message is {}", e.getMessage());

			System.out.printf(" pay back repict failed, error message is %s\n", e.getMessage());
		}
        return false;
	}
	
	public boolean payFor(String _from, String _to,String _count) {
		try {
			String contractAddress = loadRepictAddr();
            Repict repict = Repict.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            TransactionReceipt r = repict.payFor(_from,getCompanyAddress(_to),new BigInteger(_count)).send();

            return true;
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error(" payForRepict exception, error message is {}", e.getMessage());

			System.out.printf(" pay for repict failed, error message is %s\n", e.getMessage());
		}
        return false;
	}
	
	public boolean validate(String _name,String _from, String _to,String _count) {
		try {
			String contractAddress = loadRepictAddr();
            Repict repict = Repict.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            TransactionReceipt r = repict.validate(_name,getCompanyAddress(_from),getCompanyAddress(_to),new BigInteger(_count)).send();
       
            return true;
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error(" payForRepict exception, error message is {}", e.getMessage());

			System.out.printf(" pay for repict failed, error message is %s\n", e.getMessage());
		}
        return false;
	}
    /*
    public void getCompany(String addr){
        try {
			String contractAddress = loadRepictAddr();

            Repict repict = Repict.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            TransactionReceipt r = repict.getCompany(addr).send();
            List<GetCompanyEventResponse> rl = repict.getGetCompanyEvents(r);
            if(!rl.isEmpty()){
                String name = rl.get(0).name;
                String ctype = rl.get(0).cType.toString();
                String balance = rl.get(0).balance.toString();
                System.out.printf("addr: %s ,name: %s, cType: %s, balance: %s\n",addr,name,ctype,balance);
            }else{
                System.out.println(" event log not found, maybe transaction not exec. ");
            }
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error(" getCompany exception, error message is {}", e.getMessage());

			System.out.printf(" get company failed, error message is %s\n", e.getMessage());
		}
    }


    */
	
    /*public static void main(String[] args) throws Exception {
        RepictClient client = new RepictClient("123");
        client.initialize();
        client.deployRepictAndRecordAddr();
        client.setCompany("company1",new BigInteger("1000"),new BigInteger("1"),"123","123","123");
        client.getCompanyAddress("company1");
    }*/
}

