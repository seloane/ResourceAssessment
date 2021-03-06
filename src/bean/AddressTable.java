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
	
	@Inject
	AddressProcessor addressProcessor;
	
	private List<UserInfo> userInfoList = new ArrayList<UserInfo>();
	
	AddressTable(){
		
	}
	
	@PostConstruct
	public void init(){
		addressProcessor.initializeUserInfoTable();
		userInfoList = addressProcessor.getUserInfoList();
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
	
	public String modifyAddress(UserInfo userInfo){
		addressProcessor.modifyingUserInfo(userInfo);
		return "createModifyAddress?faces-redirect=true";
	}
	
}
