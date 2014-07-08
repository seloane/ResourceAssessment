package model;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import userInfo.UserInformation;

@Named
@Stateless
public class UserInfoModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2037395981780542811L;
	@PersistenceContext
	private EntityManager entityManager;

	public UserInfoModel() {
	}

	public boolean addUser(UserInformation userInformation) {

		if (userInformation != null) {
			UserInfo user = populateUserInfo(userInformation);

			entityManager.persist(user);
			entityManager.flush();
			
			return true;
		}
		return false;
	}

	public boolean modifyUser(UserInformation user) {

		if (user != null) {
			return true;
		}
		return false;
	}

	public boolean deleteUser(UserInformation user) {

		if (user != null) {
			return true;
		}
		return false;
	}

	public List<UserInfo> retrieveUserInfo(UserInformation user) {

		TypedQuery<UserInfo> query = populateQueryString(user);
		List<UserInfo> results = query.getResultList();

		return results;
	}

	private TypedQuery<UserInfo> populateQueryString(UserInformation user) {
		StringBuilder query = new StringBuilder()
				.append("select u from UserInfo u");

		boolean hasFirstName = (user.getFirstName() != null && !""
				.equalsIgnoreCase(user.getFirstName()));
		boolean hasLastName = (user.getLastName() != null && !""
				.equalsIgnoreCase(user.getLastName()));

		String firstNameStringAppend = "u.firstName = :firstName";
		String lastNameStringAppend = "u.lastName = :lastName";

		if (hasFirstName && hasLastName) {
			query.append(" where " + firstNameStringAppend + " and "
					+ lastNameStringAppend);
		} else if (hasFirstName) {
			query.append(" where " + firstNameStringAppend);
		} else if (hasLastName) {
			query.append(" where " + lastNameStringAppend);
		}

		TypedQuery<UserInfo> typedQuery = entityManager.createQuery(
				query.toString(), UserInfo.class);
		if (hasFirstName) {
			typedQuery.setParameter("firstName", user.getFirstName());
		}
		if (hasLastName) {
			typedQuery.setParameter("lastName", user.getLastName());
		}
		return typedQuery;
	}
	
	private UserInfo populateUserInfo(UserInformation user){
		UserInfo userInfo = new UserInfo();
		
		if(user!=null){
			userInfo.setFirstName(user.getFirstName());
			userInfo.setLastName(user.getLastName());
			userInfo.setAddress(user.getAddress());
			userInfo.setCity(user.getCity());
			userInfo.setState(user.getState());
			userInfo.setZip(user.getZip());
		}
		
		return userInfo;
	}
	
}
