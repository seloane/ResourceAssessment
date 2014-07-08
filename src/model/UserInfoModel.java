package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

	// private static final String PERSISTENCE_UNIT_NAME = "ResourceAssessment";
	// private static EntityManagerFactory factory =
	// Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

	public UserInfoModel() {
	}

	public boolean createNewUser(UserInformation user) {

		if (user != null) {
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

	public TypedQuery<UserInfo> populateQueryString(UserInformation user) {
		StringBuilder query = new StringBuilder()
				.append("select u from UserInfo u");

		boolean hasFirstName = (user.getFirstName() != null && !""
				.equalsIgnoreCase(user.getFirstName()));
		boolean hasLastName = (user.getLastName() != null && !""
				.equalsIgnoreCase(user.getLastName()));

		String firstNameStringAppend = "u.firstName = :firstName";// '"+user.getFirstName()+"'";
		String lastNameStringAppend = "u.lastName = :lastName";//'" + user.getLastName()+ "'";

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

	public List<UserInformation> queryListOfUsers(UserInformation user) {
		List<UserInformation> userList = new ArrayList<UserInformation>();
		userList.add(user);
		return userList;
	}

	public List<UserInformation> queryFullListOfUser() {
		return new ArrayList<UserInformation>();
	}

}
