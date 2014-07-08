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

	public String search(String firstName, String lastName) {
		/** Start conversation to keep state of first and/or last name. */
		initializeConversation();

		/** Query based on first and last name entered. */
		if ((firstName != null && !"".equalsIgnoreCase(firstName))
				|| (lastName != null && !"".equalsIgnoreCase(lastName))) {
			userInfo.setFirstName(firstName);
			userInfo.setLastName(lastName);

			setSearch(true);
		}
		return "home.xhtml?faces-redirect=true";
	}

	public void initializeUserInfoTable() {
		
		if (isSearch()) {
			//To be removed
			if (userInfoList == null) {
				userInfoList = new ArrayList<UserInfo>();
			}
			
			this.userInfoList = userInfoModel.queryListOfUsers(userInfo);
		} else if (this.userInfoList == null){
			userInfoList = new ArrayList<UserInfo>();
			this.userInfoList = userInfoModel.queryFullListOfUser();
		}
		setSearch(false);
		// endConversation();
	}

	public void initializeCreateModifyAddress() {
	}

	public String addUserInfo() {
		setCreateUserInfo(true);

		return "createModifyUserInfo.xhtml?faces-redirect=true";
	}

	public void createUserInfo() {

	}

	public String modifyUserInfo(UserInfo user){
		return "createModifyUserInfo.xhtml?faces-redirect=true";
	}
	
	public void modifyingUserInfo(UserInfo user) {
		System.out.println("Jimmy: Inside modifyingUserInfo method");
		if (user != null) {
			if (validateUserInfo(user)) {
				System.out.println("Jimmy: valid user");
				this.userInfo = user;
				if(isCreateUserInfo()){
					System.out.println("Jimmy: creating user");
					userInfoModel.createNewUser(userInfo);
				}else{
					System.out.println("Jimmy: modifying user");
					userInfoModel.modifyUser(userInfo);
				}
				// to be removed
				if(this.userInfoList==null){
					this.userInfoList = new ArrayList<UserInfo>();
				}
				this.userInfoList.add(user);
//				endConversation();
			}else{
				// Display error message to screen
			}
		}

	}

	private boolean validateUserInfo(UserInfo user) {
System.out.println("Jimmy: Validating user");
		if (user.getFirstName() == null
				|| "".equalsIgnoreCase(user.getFirstName())) {
			return false;
		}
		if (user.getLastName() == null
				|| "".equalsIgnoreCase(user.getFirstName())) {
			return false;
		}
		if (user.getAddress() == null || "".equalsIgnoreCase(user.getAddress())) {
			return false;
		}
		if (user.getState() == null || "".equalsIgnoreCase(user.getState())) {
			return false;
		}
		if (user.getCity() == null || "".equalsIgnoreCase(user.getCity())) {
			return false;
		}
		if (user.getZip() == null || "".equalsIgnoreCase(user.getZip())) {
			return false;
		}

		return true;
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
