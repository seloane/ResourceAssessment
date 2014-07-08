/**
 * 
 */
package session;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import model.UserInfo;
import model.UserInfoModel;
import userInfo.UserInformation;

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
	private UserInformation userInfo;
	private List<UserInformation> userInfoList;
	@Inject
	private Conversation conversation;
	@Inject
	private UserInfoModel userInfoModel;
	boolean isSearch;
	boolean isAddUserInformation;

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
	public UserInformation getUserInfo() {
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

		List<UserInfo> userModel = this.userInfoModel
				.retrieveUserInfo(userInfo);
		this.userInfoList = populateUserInformationList(userModel);

		return "home.xhtml?faces-redirect=true";
	}

	List<UserInformation> populateUserInformationList(List<UserInfo> userModel) {
		List<UserInformation> userInformationList = new ArrayList<UserInformation>();

		if (userModel != null) {
			for (UserInfo user : userModel) {
				UserInformation userInformation = new UserInformation();
				userInformation.setFirstName(user.getFirstName());
				userInformation.setLastName(user.getLastName());
				userInformation.setAddress(user.getAddress());
				userInformation.setCity(user.getCity());
				userInformation.setState(user.getState());
				userInformation.setZip(user.getZip());
				userInformationList.add(userInformation);
			}
		}

		return userInformationList;
	}

	public void initializeUserInfoTable() {
		if (this.userInfoList == null) {
			userInfoList = new ArrayList<UserInformation>();
		}
		if (!isSearch()) {
			List<UserInfo> fullResultList = userInfoModel
					.retrieveUserInfo(new UserInformation());
			this.userInfoList = populateUserInformationList(fullResultList);
		}
		setSearch(false);
		// endConversation();
	}

	public void initializeAddModifyAddress() {
		isAddUserInformation = !(userInfo.getAddress() != null);
	}

	public String navigateToaddUserInfoPage() {
		return "addModifyUserInfo.xhtml?faces-redirect=true";
	}

	public void createUserInfo() {

	}

	public String modifyUserInfo(UserInformation user) {
		return "addModifyUserInfo.xhtml?faces-redirect=true";
	}
	
	public void addUserInfo(UserInformation user){

		if (user != null) {
			if (validateUserInfo(user)) {
				this.userInfo = user;
					if(userInfoModel.addUser(userInfo)){
						System.out.println("Jimmy: successful add");
					}else{
						System.out.println("Jimmy: unsuccessful add");
					}
				
			} else {
				System.out.println("Invalid User Informaiton");
			}
		}else{
			System.out.println("Jimmy: User is null");
		}
	}

	public void modifyingUserInfo(UserInformation user) {
		
		if (user != null) {
			System.out.println("Jimmy: User isn't null");
			if (validateUserInfo(user)) {
				System.out.println("Jimmy: Valid user");
				this.userInfo = user;
				
					userInfoModel.modifyUser(userInfo);
				
				// to be removed
				if (this.userInfoList == null) {
					this.userInfoList = new ArrayList<UserInformation>();
				}
				this.userInfoList.add(user);
				// endConversation();
			} else {
				System.out.println("Invalid User Informaiton");
//				FacesContext.getCurrentInstance().addMessage(arg0, arg1);
			}
		}else{
			System.out.println("Jimmy: User is null");
		}

	}

	private boolean validateUserInfo(UserInformation user) {
		if (user.getFirstName() == null
				|| "".equalsIgnoreCase(user.getFirstName())) {
			System.out.println("Invalid First Name: "+user.getFirstName());
			return false;
		}
		if (user.getLastName() == null
				|| "".equalsIgnoreCase(user.getLastName())) {
			System.out.println("Invalid Last Name: "+user.getLastName());
			return false;
		}
		if (user.getAddress() == null || "".equalsIgnoreCase(user.getAddress())) {
			System.out.println("Invalid Address: "+user.getAddress());
			return false;
		}
		if (user.getState() == null || "".equalsIgnoreCase(user.getState())) {
			System.out.println("Invalid State: "+user.getState());
			return false;
		}
		if (user.getCity() == null || "".equalsIgnoreCase(user.getCity())) {
			System.out.println("Invalid City: "+user.getCity());
			return false;
		}
		if (user.getZip() < 9999 && user.getZip() > 100000) {
			System.out.println("Invalid Zip: "+user.getZip());
			return false;
		}
	

		return true;
	}

	/**
	 * @return the userInfoList
	 */
	public List<UserInformation> getUserInfoList() {
		return userInfoList;
	}

	/**
	 * @param userInfoList
	 *            the userInfoList to set
	 */
	public void setUserInfoList(List<UserInformation> userInfoList) {
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
	 * @return the isAddUserInformation
	 */
	public boolean isAddUserInformation() {
		return isAddUserInformation;
	}

	/**
	 * @param isAddUserInformation the isAddUserInformation to set
	 */
	public void setAddUserInformation(boolean isAddUserInformation) {
		this.isAddUserInformation = isAddUserInformation;
	}

}
