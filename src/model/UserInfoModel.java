package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import userInfo.UserInfo;

public class UserInfoModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2037395981780542811L;

	public boolean createNewUser(UserInfo user){
		
		if(user!=null){
			return true;
		}
		return false;
	}
	
	public boolean modifyUser(UserInfo user){

		if(user!=null){
			return true;
		}
		return false;
	}
	
	public boolean deleteUser(UserInfo user){

		if(user!=null){
			return true;
		}
		return false;
	}
	
	public UserInfo querySingleUser(UserInfo user){
		return new UserInfo();
	}
	
	public List<UserInfo> queryListOfUsers(UserInfo user){
		List<UserInfo> userList = new ArrayList<UserInfo>();
		userList.add(user);
		return userList;
	}
	
	public List<UserInfo> queryFullListOfUser(){
		return new ArrayList<UserInfo>();
	}

}
