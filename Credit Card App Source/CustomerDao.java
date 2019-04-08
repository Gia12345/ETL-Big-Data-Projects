package dao;

import java.sql.SQLException;

public interface CustomerDao {

	public void CheckCustomer(String ssn) throws SQLException;

	public void ModifyCustomerName(String SSN, String FIRST_NAME, String MIDDLE_NAME, String LAST_NAME) throws SQLException;
	
	public void ModifyCustomerAdd(String SSN, String APT_NO, String STREET_NAME, String CUST_CITY, String CUST_STATE,
		 	String CUST_COUNTRY, String CUST_ZIP) throws SQLException;
	
	public void ModifyCustomerEmail(String SSN, String Email) throws SQLException;

	public void ModifyCustomerPhone(String SSN, String Phone) throws SQLException;
	
	public void GenerateBill(String creditCard, int month, int year) throws SQLException;

	public void DisplayTrans(String ssn, int frYear, int toYear, int frMonth, int toMonth, int frDay, int toDay)
			throws SQLException;
}
