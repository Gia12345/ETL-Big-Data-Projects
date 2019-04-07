sqoop job --meta-connect jdbc:hsqldb:hsql://localhost:16000/sqoop --create creditcardJob -- import --connect jdbc:mysql://localhost/cdw_sapp  --driver com.mysql.jdbc.Driver  -m 1  --delete-target-dir  --target-dir /user/maria_dev/temp_Credit_Card_System/credit_card  --query 'select transaction_id, credit_card_no, concat(lpad(year,4,0), lpad(month,2,0), lpad(day,2,0)) AS TIMEID, cust_ssn, branch_code, transaction_type, transaction_value from CDW_SAPP_CREDITCARD WHERE $CONDITIONS' --fields-terminated-by '\t'

sqoop job --meta-connect jdbc:hsqldb:hsql://localhost:16000/sqoop --create branchJob -- import --connect jdbc:mysql://localhost/cdw_sapp  --driver com.mysql.jdbc.Driver  -m 1  --delete-target-dir  --target-dir /user/maria_dev/temp_Credit_Card_System/branch  --query 'select branch_code, branch_name, branch_street, branch_city, branch_state,  lpad(coalesce(branch_zip, "99999"), 5, "0") branch_zip, concat("(", substring(branch_phone, 1, 3), ")", substring(branch_phone, 4, 3), "-", substring(branch_phone, 7, 4)) branch_new_phone, last_updated from CDW_SAPP_BRANCH WHERE $CONDITIONS' --fields-terminated-by '\t'

sqoop job --meta-connect jdbc:hsqldb:hsql://localhost:16000/sqoop --create customerJob -- import --connect jdbc:mysql://localhost/cdw_sapp  --driver com.mysql.jdbc.Driver  -m 1  --delete-target-dir  --target-dir /user/maria_dev/temp_Credit_Card_System/customer  --query 'select ssn, concat(ucase(substring(first_name, 1, 1)), lower(substring(first_name, 2))) first_name_title, concat(ucase(substring(middle_name, 1, 1)), lower(substring(middle_name, 2))) middle_name_title, concat(ucase(substring(last_name, 1, 1)), lower(substring(last_name, 2))) last_name_title, credit_card_no, concat(apt_no, ",", street_name) address, cust_city, cust_state, cust_country, lpad(coalesce(cust_zip, "99999"), 5, "0") cust_zip, concat(substring(cust_phone, 1, 3), "-", substring(cust_phone, 4, 4)) cust_new_phone, cust_email, last_updated from CDW_SAPP_CUSTOMER WHERE $CONDITIONS;'  --fields-terminated-by '\t'

sqoop job --meta-connect jdbc:hsqldb:hsql://localhost:16000/sqoop --create timeJob -- import --connect jdbc:mysql://localhost/cdw_sapp  --driver com.mysql.jdbc.Driver  -m 1  --delete-target-dir  --target-dir /user/maria_dev/temp_Credit_Card_System/time  --query 'select concat(lpad(year,4,0), lpad(month,2,0), lpad(day,2,0)) AS TIMEID, day Day, month Month, 
	year, transaction_id,
	case when month between 1 and 3 then "Q1"
		when month between 4 and 6 then "Q2"
		when month between 7 and 9 then "Q3"
	else "Q4"
	end Quarter
	from CDW_SAPP_CREDITCARD WHERE $CONDITIONS' --fields-terminated-by '\t'
