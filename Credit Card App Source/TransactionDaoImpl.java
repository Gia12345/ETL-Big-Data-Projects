package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionDaoImpl implements TransactionDao {
	
	public String checkWhiteSpace(String name) {
		if (name.contains(" ")) {
			return name;
		}
		String[] split = name.split("(?=[A-Z])");
		String whiteSpaceName = split[0];
		if (split.length > 1) {
			for (int i=1; i < split.length; i++) {					
				whiteSpaceName = whiteSpaceName + " " + split[i];	
			}
		}
		return whiteSpaceName;
	}

	public void printByZipCode(ResultSet myRs) throws SQLException {
		String stringCurrency = OutputFormatter.formatCurrency(myRs.getDouble("TRANSACTION_VALUE"));
		System.out.println(myRs.getString("MONTH") + "/" + myRs.getString("DAY") + "/" + myRs.getString("YEAR")
				+ " " + myRs.getString("CREDIT_CARD_NO") + "  " + myRs.getString("CUST_SSN")+ "  " + myRs.getString("BRANCH_CODE")
				+ " " + myRs.getString("TRANSACTION_TYPE") + "  " + stringCurrency);
	}
	
	public void printByType(ResultSet myRs, String type) throws SQLException {
		String stringCurrency = OutputFormatter.formatCurrency(myRs.getDouble("Transaction Amount"));
		System.out.println("For category " + type + ", " + "# of Transactions:  " + myRs.getString("# of Transaction") + "  " 
				+ "Transaction Amount:  " + stringCurrency);		
	}
	
	public void printByState(ResultSet myRs) throws SQLException {
		String branchCity = myRs.getString("BRANCH_CITY");
		String whiteSpaceBranchCity = checkWhiteSpace(branchCity);
		String stringCurrency = OutputFormatter.formatCurrency(myRs.getDouble("Transaction Amount"));
		System.out.println("Branch Code:  " + myRs.getString("BRANCH_CODE") + " "
				+ "Branch City:  " + whiteSpaceBranchCity + "		"
				+ "Transactions:  " + myRs.getString("# of Transaction") + "  " 
				+ "Transaction Amount:  " + stringCurrency);
	}

	@Override
	public void getbyZipcode(String zip, int month, int year) throws SQLException {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// 1. Get a connection to database
			myConn = ConnectionManager.getConnection();

			// 2. Create a statement
			if (myConn != null) {
				myStmt = myConn.prepareStatement(SqlFile.query1);

				// 2a. set the parameter;
				myStmt.setString(1, zip);
				myStmt.setInt(2, month);
				myStmt.setInt(3, year);

				// 3. Execute SQL query
				myRs = myStmt.executeQuery();

				if (myRs.next() == false) {
					OutputFormatter.printMessage("No information for your selection!");
				} else {
					printByZipCode(myRs);
				}
				// 4. Process the result set
				while (myRs.next()) {
					printByZipCode(myRs);
				}
			}
		} catch (Exception e) {
			OutputFormatter.printMessage("Error retrieving from database.  Please try again.");
		} finally {
			ConnectionManager.closeObjects(myRs, myStmt, myConn);
		}

	}

	@Override
	public void getbyType(String type) throws SQLException {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			// 1. Get a connection to database
			myConn = ConnectionManager.getConnection();

			// 2. Create a statement
			myStmt = myConn.prepareStatement(SqlFile.query2);

			// 2a. set the parameter;
			myStmt.setString(1, type);

			// 3. Execute SQL query
			myRs = myStmt.executeQuery();

			if (myRs.next() == false) {
				OutputFormatter.printMessage("No information for your selection!");
			} else {
				printByType(myRs, type);
			}
			// 4. Process the result set
			while (myRs.next()) {
				printByType(myRs, type);
			}
		} catch (Exception e) {
			OutputFormatter.printMessage("Error retrieving from database.  Please try again.");
		} finally {
			ConnectionManager.closeObjects(myRs, myStmt, myConn);
		}
	}

	@Override
	public void getbyState(String state) throws SQLException {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			// 1. Get a connection to database
			myConn = ConnectionManager.getConnection();

			// 2. Create a statement
			if (myConn != null) {
				myStmt = myConn.prepareStatement(SqlFile.query3);

				// 2a. set the parameter;
				myStmt.setString(1, state);

				// 3. Execute SQL query
				myRs = myStmt.executeQuery();

				if (myRs.next() == false) {
					OutputFormatter.printMessage("No information for your selection!");
				} else {
					printByState(myRs);
				}
				// 4. Process the result set
				while (myRs.next()) {
					printByState(myRs);
				}
			}
		} catch (Exception e) {
			OutputFormatter.printMessage("Error retrieving from database.  Please try again.");
		} finally {
			ConnectionManager.closeObjects(myRs, myStmt, myConn);
		}

	}
}
