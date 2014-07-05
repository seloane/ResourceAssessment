package bean;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import session.AddressProcessor;

@Named
@ManagedBean
@RequestScoped
public class AddressLookup {
	@Inject
	private AddressProcessor addressProcessor;
	String firstName;
	String lastName;

	public AddressLookup() {

	}

	@PostConstruct
	public void init() {		
		addressProcessor.initializeAddressLookup();
	}

	public String search() {
		addressProcessor.search(firstName, lastName);
		return "home?faces-redirect=true";
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
