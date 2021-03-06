/**
 * 
 */
package bean;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import session.UserInfoProcessor;
import userInfo.UserInformation;

/**
 * @author jtarver
 *
 */
@Named
@ManagedBean
@RequestScoped
public class CreateModifyUserInfo {

	@Inject
	private UserInfoProcessor addressProcessor;
	
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String state;
	private String zip;
	
	/**
	 * 
	 */
	public CreateModifyUserInfo() {
	}

	@PostConstruct
	public void init(){
		UserInformation userInfo = addressProcessor.getUserInfo();
		
		this.firstName = userInfo.getFirstName();
		this.lastName = userInfo.getLastName();
		this.address = userInfo.getAddress();
		this.state = userInfo.getState();
		this.city = userInfo.getCity();
		this.zip = userInfo.getZip();
	}
	
	public String submit(){
		System.out.println("Jimmy: Inside submit method");
		UserInformation userInfo = new UserInformation();
		userInfo.setFirstName(firstName);
		userInfo.setLastName(lastName);
		userInfo.setAddress(address);
		userInfo.setState(state);
		userInfo.setCity(city);
		userInfo.setZip(zip);
		
		addressProcessor.modifyingUserInfo(userInfo);
		
		System.out.println("Jimmy: Done modifying. Time to change the page");
		return "home.xhtml";
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

}
