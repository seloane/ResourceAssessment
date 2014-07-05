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
	private UserInfo userInfo;
	private List<UserInfo> userInfoList;
	@Inject
	private Conversation conversation;

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
	 * 
	 */
	public AddressProcessor() {
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
		initializeConversation();
	}

	public void initializeAddressTable() {
		initializeConversation();
		if (userInfoList == null) {
			userInfoList = new ArrayList<UserInfo>();
		}

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

	public void initializeCreateModifyAddress() {
		initializeConversation();
	}

	public void search(String firstName, String lastName) {
		userInfo.setFirstName(firstName);
		userInfo.setLastName(lastName);
	}

	public void setCreateModifyAddress(UserInfo userInfo) {
		this.userInfo = userInfo;
		endConversation();
	}

	public String getFirstName() {
		return userInfo.getFirstName();
	}

	public String getLastName() {
		return userInfo.getLastName();
	}

	public void setFirstName(String firstName) {
		userInfo.setFirstName(firstName);
	}

	public void setLastName(String lastName) {
		userInfo.setLastName(lastName);
	}

	public String getAddress() {
		return userInfo.getAddress();
	}

	public String getState() {
		return userInfo.getState();
	}

	public String getCity() {
		return userInfo.getCity();
	}

	public String getZip() {
		return userInfo.getZip();
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
}
