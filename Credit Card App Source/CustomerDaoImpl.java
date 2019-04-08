package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet; 
import java.sql.SQLException;

public class CustomerDaoImpl implements CustomerDao {

	public void printCustomerInfo(ResultSet myRs) throws SQLException {
		System.out.println(myRs.getString("FIRST_NAME") + " " + myRs.getString("MIDDLE_NAME") + " "
				+ myRs.getString("LAST_NAME") + " " + myRs.getString("SSN") + " "
				+ myRs.getString("CREDIT_CARD_NO") + " " + myRs.getString("APT_NO") + " "
				+ myRs.getString("STREET_NAME")+ " " + myRs.getString("CUST_CITY") + " "
				+ myRs.getString("CUST_STATE") + " " + myRs.getString("CUST_COUNTRY") + " "
				+ myRs.getString("CUST_ZIP") + " " + myRs.getString("CUST_PHONE") + " "
				+ myRs.getString("CUST_EMAIL"));
	}
	
	public void printBill(ResultSet myRs, int month, int year) throws SQLException {
		String stringCurrency = OutputFormatter.formatCurrency(myRs.getDouble("BILL"));
		System.out.print("Total bill for month "+month+" and year "+year+" is ");
		System.out.println(stringCurrency);
	}
	
	public void printTransactions(ResultSet myRs) throws SQLException {
		String stringCurrency = OutputFormatter.formatCurrency(myRs.getDouble("TRANSACTION_VALUE"));
		System.out.println(myRs.getString("TRANSACTION_ID")+ " " + myRs.getString("MONTH") + "/"
				+ myRs.getString("DAY") + "/" + myRs.getString("YEAR") + " "  
				+ myRs.getString("CREDIT_CARD_NO") + " " + myRs.getString("CUST_SSN") + " "
				+ myRs.getString("BRANCH_CODE") + " " + myRs.getString("TRANSACTION_TYPE") + " "
				+ stringCurrency);
	}
	
	public void printUpdatedMessage(String SSN, int rows) {
		if (rows == 0) {
			OutputFormatter.printMessage("There is no record for the SSN to update!");
		} else {
			System.out.println(SSN + ": Updated");
		}
	}

	@Override
	public void CheckCustomer(String ssn) throws SQLException {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			// 1. Get a connection to database
			myConn = ConnectionManager.getConnection();

			// 2. Create a statement
			if (myConn != null) {
				myStmt = myConn.prepareStatement(SqlFile.query4);

				// 2a. set the parameter;
				myStmt.setString(1, ssn);

				// 3. Execute SQL query
				myRs = myStmt.executeQuery();

				if (myRs.next() == false) {
					OutputFormatter.printMessage("No information for your selection!");
				} else {
					printCustomerInfo(myRs);
				}
				// 4. Process the result set
				while (myRs.next()) { 
					printCustomerInfo(myRs);
				}
			}
		} catch (Exception e) {
			OutputFormatter.printMessage("Error retrieving from database.  Please try again.");
		} finally {
			ConnectionManager.closeObjects(myRs, myStmt, myConn);
		}

	}

	@Override
	public void ModifyCustomerName(String SSN, String FIRST_NAME, String MIDDLE_NAME, String LAST_NAME ) throws SQLException {
   		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			// 1. Get a connection to database
			myConn = ConnectionManager.getConnection();

			if (myConn != null) {
				// 2. Create a statement
				myStmt = myConn.prepareStatement(SqlFile.query5a);

				// 2a. set the parameter;
				myStmt.setString(1, FIRST_NAME);
				myStmt.setString(2, MIDDLE_NAME);
				myStmt.setString(3, LAST_NAME);
				myStmt.setString(4, SSN);

				// 3. Execute SQL query
				int Rows = myStmt.executeUpdate();
				printUpdatedMessage(SSN, Rows);
			}
		}	catch (Exception e) {
//			e.printStackTrace();
			OutputFormatter.printMessage("Error updating database.  Please try again.");
		}  finally {
			ConnectionManager.closeObjects(myRs, myStmt, myConn);
		}

	}

	@Override
	public void ModifyCustomerAdd(String SSN, String APT_NO, String STREET_NAME, String CUST_CITY,
			String CUST_STATE, String CUST_COUNTRY, String CUST_ZIP) throws SQLException {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			// 1. Get a connection to database
			myConn = ConnectionManager.getConnection();

			// 2. Create a statement
			if (myConn != null) {
				myStmt = myConn.prepareStatement(SqlFile.query5b);

				// 2a. set the parameter;
				myStmt.setString(1, APT_NO);
				myStmt.setString(2, STREET_NAME);
				myStmt.setString(3, CUST_CITY);
				myStmt.setString(4, CUST_STATE);
				myStmt.setString(5, CUST_COUNTRY);
				myStmt.setString(6, CUST_ZIP);
				myStmt.setString(7, SSN);

				// 3. Execute SQL query
				int Rows = myStmt.executeUpdate();
				printUpdatedMessage(SSN, Rows);
			}
		} catch (Exception e) {
			OutputFormatter.printMessage("Error updating database.  Please try again.");
		} finally {
			ConnectionManager.closeObjects(myRs, myStmt, myConn);
		}

	}
	
	@Override
	public void ModifyCustomerPhone(String SSN, String Phone) throws SQLException {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			// 1. Get a connection to database
			myConn = ConnectionManager.getConnection();

			if (myConn != null) {
				// 2. Create a statement
				myStmt = myConn.prepareStatement(SqlFile.query5c);

				// 2a. set the parameter;
				myStmt.setString(1, Phone);
				myStmt.setString(2, SSN);

				// 3. Execute SQL query
				int Rows = myStmt.executeUpdate();
				printUpdatedMessage(SSN, Rows);
			}
		} catch (Exception e) {
			OutputFormatter.printMessage("Error updating database.  Please try again.");
		} finally {
			ConnectionManager.closeObjects(myRs, myStmt, myConn);
		}
	}

	@Override
	public void ModifyCustomerEmail(String SSN, String Email) throws SQLException {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			// 1. Get a connection to database
			myConn = ConnectionManager.getConnection();

			// 2. Create a statement
			if (myConn != null) {
				myStmt = myConn.prepareStatement(SqlFile.query5d);

				// 2a. set the parameter;
				myStmt.setString(1, Email);
				myStmt.setString(2, SSN);

				// 3. Execute SQL query
				int Rows = myStmt.executeUpdate();
				printUpdatedMessage(SSN, Rows);
			}
		} catch (Exception e) {
			OutputFormatter.printMessage("Error updating database.  Please try again.");
		} finally {
			ConnectionManager.closeObjects(myRs, myStmt, myConn);
		}

	}

	@Override
	public void GenerateBill(String creditCard, int month, int year) throws SQLException {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			// 1. Get a connection to database
			myConn = ConnectionManager.getConnection();

			// 2. Create a statement
			if (myConn != null) {
				myStmt = myConn.prepareStatement(SqlFile.query6);

				// 2a. set the parameter;
				myStmt.setString(1, creditCard);
				myStmt.setInt(2, month);
				myStmt.setInt(3, year);

				// 3. Execute SQL query
				myRs = myStmt.executeQuery();
				if (myRs.next() == false) {
					OutputFormatter.printMessage("No information for your selection!");
				} else if (myRs.getString("BILL") == null) {
					OutputFormatter.printMessage("No information for your selection!");
				} else {
					printBill(myRs, month, year);
				}
				// 4. Process the result set
				while (myRs.next()) {
					printBill(myRs, month, year);
				}
			}
		} catch (Exception e) {
			OutputFormatter.printMessage("Error retrieving from database.  Please try again.");
		} finally {
			ConnectionManager.closeObjects(myRs, myStmt, myConn);
		}
	}

	@Override
	public void DisplayTrans(String ssn, int frYear, int toYear, 
			int frMonth, int toMonth, int frDay, int toDay) throws SQLException {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			// 1. Get a connection to database
			myConn = ConnectionManager.getConnection();

			// 2. Create a statement
			if (myConn != null) {
				myStmt = myConn.prepareStatement(SqlFile.query7);

				// 2a. set the parameter;
				myStmt.setString(1, ssn);
				myStmt.setInt(2, frYear);
				myStmt.setInt(3, frMonth);
				myStmt.setInt(4, frDay);
				myStmt.setInt(5, toYear);
				myStmt.setInt(6, toMonth);
				myStmt.setInt(7, toDay);

				// 3. Execute SQL query
				myRs = myStmt.executeQuery();
				if (myRs.next() == false) {
					OutputFormatter.printMessage("No information for your selection !");
				} else {
					printTransactions(myRs);
				}
				// 4. Process the result set
				while (myRs.next()) {
					printTransactions(myRs);
				}
			}
		} catch (Exception e) {
			OutputFormatter.printMessage("Error retrieving from database.  Please try again.");
		} finally {
			ConnectionManager.closeObjects(myRs, myStmt, myConn);
		}
	}
}
