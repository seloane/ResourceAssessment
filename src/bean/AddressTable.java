/**
 * 
 */
package bean;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import session.AddressProcessor;
import address.UserInfo;

/**
 * @author jtarver
 *
 */
@Named
@ManagedBean
@RequestScoped
public class AddressTable {
	
	private List<UserInfo> userInfoList = new ArrayList<UserInfo>();
	
	AddressTable(){
		
		for (int i = 0; i < 10; i++) {
			UserInfo userInfo = new UserInfo();
			userInfo.setAddress("124 Downda Rd" + i);
			userInfo.setCity("city" + i);
			userInfo.setFirstName("First" + i);
			userInfo.setLastName("Last" + i);
			userInfo.setState("State" + i);
			userInfo.setZip("Zip" + i);

			userInfoList.add(userInfo);
		}
	}
	
	/**
	 * @return the addressList
	 */
	public List<UserInfo> getUserInfoList() {
		return userInfoList;
	}

	/**
	 * @param userInfoList the addressList to set
	 */
	public void setAddressList(List<UserInfo> userInfoList) {
		this.userInfoList = userInfoList;
	}
	
}
//@Inject
//	AddressProcessor addressProcessor;
//	
//	private List<UserInfo> userInfoList;
//
//	/**
//	 * 
//	 */
//	public AddressTable() {
//	}
//
//	@PostConstruct
//	private void init(){
//		addressProcessor.initializeAddressTable();
//		userInfoList = addressProcessor.getUserInfoList();
//	}
//	
//	/**
//	 * @return the addressList
//	 */
//	public List<UserInfo> getUserInfoList() {
//		System.out.println("Jimmy: Getting user info list of size "+userInfoList.size());
//		return userInfoList;
//	}
//
//	/**
//	 * @param userInfoList the addressList to set
//	 */
//	public void setAddressList(List<UserInfo> userInfoList) {
//		this.userInfoList = userInfoList;
//	}
//
//}
