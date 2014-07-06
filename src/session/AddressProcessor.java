/**
 * 
 */
package session;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import address.UserInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jtarver
 * 
 */
@ConversationScoped
@Named
public class AddressProcessor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6934738281781825344L;
	@Inject
	private UserInfo userInfo;
	private List<UserInfo> userInfoList;
	@Inject
	private Conversation conversation;
	boolean isSearch;
	boolean isCreateUserInfo;

	/**
	 * 
	 */
	public AddressProcessor() {
	}

	private void initializeConversation() {
		if (conversation.isTransient()) {
			conversation.begin();
		}
	}

	private void endConversation() {
		if (!conversation.isTransient()) {
			conversation.end();
		}
	}

	/**
	 * @return the address
	 */
	public UserInfo getUserInfo() {
		return userInfo;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public void initializeAddressLookup() {

	}

	public void search(String firstName, String lastName) {
		initializeConversation();
		if ((firstName != null && !"".equalsIgnoreCase(firstName))
				|| (lastName != null && !"".equalsIgnoreCase(lastName))) {
			userInfo.setFirstName(firstName);
			userInfo.setLastName(lastName);
			setSearch(true);
		} else {
			setSearch(false);
		}
	}

	public void initializeUserInfoTable() {
		userInfoList = new ArrayList<UserInfo>();
		if (!isSearch()) {
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
		} else {
			UserInfo user = new UserInfo();
			user.setFirstName(userInfo.getFirstName());
			user.setLastName(userInfo.getLastName());

			userInfoList.add(userInfo);
			setSearch(false);
		}

	}

	public void initializeCreateModifyAddress() {
	}
	public void creatingNewUserInfo(){
		setCreateUserInfo(true);
	}
	public void createUserInfo(){
		
	}
	
	public void modifyingUserInfo(UserInfo userInfo) {
		initializeConversation();
		this.userInfo = userInfo;
	}

	/**
	 * @return the userInfoList
	 */
	public List<UserInfo> getUserInfoList() {
		return userInfoList;
	}

	/**
	 * @param userInfoList
	 *            the userInfoList to set
	 */
	public void setUserInfoList(List<UserInfo> userInfoList) {
		this.userInfoList = userInfoList;
	}

	/**
	 * @return the isSearch
	 */
	public boolean isSearch() {
		return isSearch;
	}

	/**
	 * @param isSearch
	 *            the isSearch to set
	 */
	public void setSearch(boolean isSearch) {
		this.isSearch = isSearch;
	}

	/**
	 * @return the isCreateUserInfo
	 */
	public boolean isCreateUserInfo() {
		return isCreateUserInfo;
	}

	/**
	 * @param isCreateUserInfo the isCreateUserInfo to set
	 */
	public void setCreateUserInfo(boolean isCreateUserInfo) {
		this.isCreateUserInfo = isCreateUserInfo;
	}
}
