/**
 * 
 */
package session;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.UserInfoModel;
import userInfo.UserInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jtarver
 * 
 */
@ConversationScoped
@Named
public class UserInfoProcessor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6934738281781825344L;
	@Inject
	private UserInfo userInfo;
	private List<UserInfo> userInfoList;
	@Inject
	private Conversation conversation;
	@Inject
	private UserInfoModel userInfoModel;
	boolean isSearch;
	boolean isCreateUserInfo;

	/**
	 * 
	 */
	public UserInfoProcessor() {
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

	public void initializeAddressLookup() {

	}

	public void search(String firstName, String lastName) {
		/** Start conversation to keep state of first and/or last name. */
		initializeConversation();

		/** Query based on first and last name entered. */
		if ((firstName != null && !"".equalsIgnoreCase(firstName))
				|| (lastName != null && !"".equalsIgnoreCase(lastName))) {
			userInfo.setFirstName(firstName);
			userInfo.setLastName(lastName);

			setSearch(true);
		}
	}

	public void initializeUserInfoTable() {
		if (userInfoList == null) {
			userInfoList = new ArrayList<UserInfo>();
		}
		if (isSearch()) {
			this.userInfoList = userInfoModel.queryListOfUsers(userInfo);
		} else {
			this.userInfoList = userInfoModel.queryFullListOfUser();
		}
		setSearch(false);
		endConversation();

	}

	public void initializeCreateModifyAddress() {
	}

	public void creatingNewUserInfo() {
		setCreateUserInfo(true);
	}

	public void createUserInfo() {

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
	 * @param isCreateUserInfo
	 *            the isCreateUserInfo to set
	 */
	public void setCreateUserInfo(boolean isCreateUserInfo) {
		this.isCreateUserInfo = isCreateUserInfo;
	}
}
