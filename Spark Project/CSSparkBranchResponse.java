package com.spark.caseStudy.datasets;

import java.io.Serializable;

public class BranchResponse implements Serializable {
	
    private String branchNumber;
    private String branchName;
    private String branchStreet;
    private String branchCity;
    private String branchState;
    private String branchZip;
    private String branchPhone;
    private String lastUpdate;
	
	public BranchResponse(String branchNumber, String branchName, String branchStreet, String branchCity,
	    		String branchState, String branchZip, String branchPhone, String lastUpdate) {
	        this.branchNumber = branchNumber;
	        this.branchName = branchName;
	        this.branchStreet = branchStreet;
	        this.branchCity = branchCity;
	        this.branchState = branchState;
	        this.branchZip = branchZip;
	        this.branchPhone = branchPhone;
	        this.lastUpdate = lastUpdate;
	    }

		public String getBranchNumber() {
			return branchNumber;
		}

		public void setBranchNumber(String branchNumber) {
			this.branchNumber = branchNumber;
		}

		public String getBranchName() {
			return branchName;
		}

		public void setBranchName(String branchName) {
			this.branchName = branchName;
		}

		public String getBranchStreet() {
			return branchStreet;
		}

		public void setBranchStreet(String branchStreet) {
			this.branchStreet = branchStreet;
		}

		public String getBranchCity() {
			return branchCity;
		}

		public void setBranchCity(String branchCity) {
			this.branchCity = branchCity;
		}

		public String getBranchState() {
			return branchState;
		}

		public void setBranchState(String branchState) {
			this.branchState = branchState;
		}

		public String getBranchZip() {
			return branchZip;
		}

		public void setBranchZip(String branchZip) {
			this.branchZip = branchZip;
		}

		public String getBranchPhone() {
			return branchPhone;
		}

		public void setBranchPhone(String branchPhone) {
			this.branchPhone = branchPhone;
		}

		public String getLastUpdate() {
			return lastUpdate;
		}

		public void setLastUpdate(String lastUpdate) {
			this.lastUpdate = lastUpdate;
		}

		@Override
		public String toString() {
			return "Response [branchNumber=" + branchNumber + ", branchName=" + branchName + ", branchStreet="
					+ branchStreet + ", branchCity=" + branchCity + ", branchState=" + branchState + ", branchZip="
					+ branchZip + ", branchPhone=" + branchPhone + ", lastUpdate=" + lastUpdate + "]";
		}
	}

