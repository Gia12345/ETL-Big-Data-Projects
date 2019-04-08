package com.spark.caseStudy.datasets;

import java.io.Serializable;

public class CreditcardResponse implements Serializable {
		private String transactionId;
	    private String custCCNo;
	    private String lastUpdated;
	    private String custSSN;
	    private String branchCode;
	    private String tranactionType;
	    private String transactionValue;
		
		public CreditcardResponse(String transactionId, String custCCNo, String lastUpdated, String custSSN,
		    		String branchCode, String tranactionType, String transactionValue) {
		        this.transactionId = transactionId;
		        this.custCCNo = custCCNo;
		        this.lastUpdated = lastUpdated;
		        this.custSSN = custSSN;
		        this.branchCode = branchCode;
		        this.tranactionType = tranactionType;
		        this.transactionValue = transactionValue;
		}

		@Override
		public String toString() {
			return "CreditcardResponse [transactionId=" + transactionId + ", custCCNo=" + custCCNo + ", lastUpdated="
					+ lastUpdated + ", custSSN=" + custSSN + ", branchCode=" + branchCode + ", tranactionType="
					+ tranactionType + ", transactionValue=" + transactionValue + "]";
		}

		public String getTransactionId() {
			return transactionId;
		}

		public void setTransactionId(String transactionId) {
			this.transactionId = transactionId;
		}

		public String getCustCCNo() {
			return custCCNo;
		}

		public void setCustCCNo(String custCCNo) {
			this.custCCNo = custCCNo;
		}

		public String getLastUpdated() {
			return lastUpdated;
		}

		public void setLastUpdated(String lastUpdated) {
			this.lastUpdated = lastUpdated;
		}

		public String getCustSSN() {
			return custSSN;
		}

		public void setCustSSN(String custSSN) {
			this.custSSN = custSSN;
		}

		public String getBranchCode() {
			return branchCode;
		}

		public void setBranchCode(String branchCode) {
			this.branchCode = branchCode;
		}

		public String getTranactionType() {
			return tranactionType;
		}

		public void setTranactionType(String tranactionType) {
			this.tranactionType = tranactionType;
		}

		public String getTransactionValue() {
			return transactionValue;
		}

		public void setTransactionValue(String transactionValue) {
			this.transactionValue = transactionValue;
		}

}
