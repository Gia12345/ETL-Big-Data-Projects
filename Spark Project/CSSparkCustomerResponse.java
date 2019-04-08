package com.spark.caseStudy.datasets;

//import java.io.Serializable;

public class CustomerResponse {
	private String custSSN;
    private String firstName;
    private String middleName;
    private String lastName;
    private String ccNo;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zip;
    private String phone;
    private String email;
    private String lastUpdated;
	
	public CustomerResponse(String custSSN, String firstName, String middleName, String lastName,
	    		String ccNo, String street, String city, String state, String country, String zip,
	    		String phone, String emaiil, String lastUpdated) {
	        this.custSSN = custSSN;
	        this.firstName = firstName;
	        this.middleName = middleName;
	        this.lastName = lastName;
	        this.ccNo = ccNo;
	        this.street = street;
	        this.city = city;
	        this.state = state;
	        this.country = country;
	        this.zip = zip;
	        this.phone = phone;
	        this.email = email;
	        this.lastUpdated = lastUpdated;
	}

	@Override
	public String toString() {
		return "CustomerResponse [custSSN=" + custSSN + ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", ccNo=" + ccNo + ", street=" + street + ", city=" + city + ", state="
				+ state + ", country=" + country + ", zip=" + zip + ", phone=" + phone + ", email=" + email
				+ ", lastUpdated=" + lastUpdated + "]";
	}

	public String getCustSSN() {
		return custSSN;
	}

	public void setCustSSN(String custSSN) {
		this.custSSN = custSSN;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCcNo() {
		return ccNo;
	}

	public void setCcNo(String ccNo) {
		this.ccNo = ccNo;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

}
