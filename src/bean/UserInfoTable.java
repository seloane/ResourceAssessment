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

import session.UserInfoProcessor;
import userInfo.UserInformation;

/**
 * @author jtarver
 * 
 */
@Named
@ManagedBean
@RequestScoped
public class UserInfoTable {

	@Inject
	UserInfoProcessor addressProcessor;

	private List<UserInformation> userInfoList = new ArrayList<UserInformation>();

	UserInfoTable() {

	}

	@PostConstruct
	public void init() {
		addressProcessor.initializeUserInfoTable();
		userInfoList = addressProcessor.getUserInfoList();
	}

	/**
	 * @return the addressList
	 */
	public List<UserInformation> getUserInfoList() {
		return userInfoList;
	}

	/**
	 * @param userInfoList
	 *            the addressList to set
	 */
	public void setAddressList(List<UserInformation> userInfoList) {
		this.userInfoList = userInfoList;
	}

	public String modifyUserInfo(UserInformation userInfo) {
		return addressProcessor.modifyUserInfo(userInfo);
	}

	public boolean showUserInfoTable() {
		return (this.userInfoList.size() > 0);
	}
}
