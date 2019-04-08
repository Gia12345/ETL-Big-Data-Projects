package com.spark.caseStudy.datasets;

import static org.apache.spark.sql.functions.col;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.RelationalGroupedDataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class Main {

	public static void main(String[] args) {

		String branchPath = "hdfs://192.168.228.131:8020/user/maria_dev/temp_Credit_Card_System/branch/";
		String ccPath = "hdfs://192.168.228.131:8020/user/maria_dev/temp_Credit_Card_System/credit_card/";
		String custPath = "hdfs://192.168.228.131:8020/user/maria_dev/temp_Credit_Card_System/customer/";		
		String p1output = "hdfs://192.168.228.131:8020/user/maria_dev/credit_card_number/p1output/";
		String p2output = "hdfs://192.168.228.131:8020/user/maria_dev/credit_card_number/p2output/";
		String p3output = "hdfs://192.168.228.131:8020/user/maria_dev/credit_card_number/p3output/";
		
		Logger.getLogger("org").setLevel(Level.ERROR);
		SparkConf conf = new SparkConf().setAppName("CreditCard").setMaster("local[*]");
		JavaSparkContext sc = new JavaSparkContext(conf);
		
//1  Which state has the highest number of branches?
		JavaRDD<String> branchData = sc.textFile(branchPath);
		JavaRDD<BranchResponse> branchResponse = branchData.map(line -> {
			String[] splits = line.split("\t");
			return new BranchResponse(splits[0], splits[1], splits[2], splits[3], splits[4], splits[5],
					splits[6], splits[7]);
		});
		SparkSession sessionBranch = SparkSession.builder().
				appName("Branch").master("local[*]").getOrCreate();
		Dataset<BranchResponse> branchDataset = sessionBranch.
				createDataset(branchResponse.rdd(), Encoders.bean(BranchResponse.class));
		RelationalGroupedDataset statesDataset = branchDataset.groupBy(col("branchState"));
		Dataset<Row> countStates = statesDataset.count();
		
		JavaRDD<Row> javaRDDp1 = countStates.orderBy(col("count").desc()).rdd().toJavaRDD();
		javaRDDp1.coalesce(1).saveAsTextFile(p1output);
		
//2  Which customers have the greatest number of transactions on credit cards?		
		JavaRDD<String> ccData = sc.textFile(ccPath);
		JavaRDD<CreditcardResponse> ccResponse = ccData.map(line -> {
			String[] splits = line.split("\t");
			return new CreditcardResponse(splits[0], splits[1], splits[2], splits[3], splits[4], splits[5],
					splits[6]);
		});	
		SparkSession sessionCC = SparkSession.builder().
				appName("Credit Card").master("local[*]").getOrCreate();
		Dataset<CreditcardResponse> ccDataset = sessionCC.createDataset(ccResponse.rdd(),Encoders.bean(CreditcardResponse.class));

		RelationalGroupedDataset transDataset = ccDataset.groupBy(col("custSSN"));
		Dataset<Row> countTrans = transDataset.count();
		JavaRDD<Row> javaRDDp2 = countTrans.orderBy(col("count").desc()).rdd().toJavaRDD();
		javaRDDp2.coalesce(1).saveAsTextFile(p2output);
//3  Which states have the greatest number of credit card transactions?
		Dataset<Row> ccBranches = ccDataset.select(col("branchCode"));
		Dataset<Row> stateBranches = branchDataset.select(col("branchNumber"),col("branchState"));
		Dataset<Row> joined = ccBranches.join(stateBranches, 
				ccBranches.col("branchCode").startsWith(stateBranches.col("branchNumber")), "left_outer");
		RelationalGroupedDataset groupStates = joined.groupBy(col("branchState"));
		Dataset<Row> groupStatesDS = groupStates.count();
		JavaRDD<Row> javaRDDp3 = groupStatesDS.orderBy(col("count").desc()).rdd().toJavaRDD();
		javaRDDp3.coalesce(1).saveAsTextFile(p3output);
// BONUS: Customer
		JavaRDD<String> custData = sc.textFile(custPath);
		JavaRDD<CustomerResponse> custResponse = custData.map(line -> {
			String[] splits = line.split("\t");
			return new CustomerResponse(splits[0], splits[1], splits[2], splits[3], splits[4], splits[5],
					splits[6], splits[7], splits[8], splits[9], splits[10], splits[11], splits[12]);
		});
		SparkSession sessionCust = SparkSession.builder().
				appName("Customer").master("local[*]").getOrCreate();
		Dataset<CustomerResponse> custDataset = sessionCust.createDataset(custResponse.rdd(),Encoders.bean(CustomerResponse.class));
	}
}
