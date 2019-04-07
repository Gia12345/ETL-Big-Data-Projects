select b.branch_zip, sum(cc.transaction_value) sumtv from cdw_sapp_f_creditcard cc 
	join cdw_sapp_d_branch b on cc.branch_code = b.branch_code group by b.branch_zip
	order by sumtv  desc limit 20;

select t.quarter, sum(cc.transaction_value), cc.transaction_type from cdw_sapp_f_creditcard cc 
	join cdw_sapp_d_time t on cc.transaction_id =  t.transaction_id
	where t.year='2018'
	group by t.quarter, cc.transaction_type;
