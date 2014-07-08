package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the UserInfo database table.
 * 
 */
@Entity
@Table(name="UserInfo")
@NamedQuery(name="UserInfo.findAll", query="SELECT u FROM UserInfo u")
public class UserInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="UserId", unique=true, nullable=false)
	private int userId;

	@Column(name="Address", nullable=false, length=45)
	private String address;

	@Column(name="City", nullable=false, length=45)
	private String city;

	@Column(name="FirstName", nullable=false, length=45)
	private String firstName;

	@Column(name="LastName", nullable=false, length=45)
	private String lastName;

	@Column(name="State", nullable=false, length=45)
	private String state;

	@Column(name="Zip", nullable=false)
	private int zip;

	public UserInfo() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip() {
		return this.zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

}