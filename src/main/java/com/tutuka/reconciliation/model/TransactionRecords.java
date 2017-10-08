package com.tutuka.reconciliation.model;

import java.util.Date;

/**
 * @author Rishabh
 * 
 *         Model to hold the details of each transaction in the data set file
 */
public class TransactionRecords {

	private String profileName;
	private Date transactionDate;
	private long transactionAmount;
	private String transactionNarrative;
	private String transactionDescription;
	private String transactionId;
	private int transactionType;
	private String walletReference;

	public TransactionRecords(String profileName, Date transactionDate, long transactionAmount,
			String transactionNarrative, String transactionDescription, String transactionId, int transactionType,
			String walletReference) {
		this.profileName = profileName;
		this.transactionDate = transactionDate;
		this.transactionAmount = transactionAmount;
		this.transactionNarrative = transactionNarrative;
		this.transactionDescription = transactionDescription;
		this.transactionId = transactionId;
		this.transactionType = transactionType;
		this.walletReference = walletReference;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public long getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(long transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getTransactionNarrative() {
		return transactionNarrative;
	}

	public void setTransactionNarrative(String transactionNarrative) {
		this.transactionNarrative = transactionNarrative;
	}

	public String getTransactionDescription() {
		return transactionDescription;
	}

	public void setTransactionDescription(String transactionDescription) {
		this.transactionDescription = transactionDescription;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public int getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(int transactionType) {
		this.transactionType = transactionType;
	}

	public String getWalletReference() {
		return walletReference;
	}

	public void setWalletReference(String walletReference) {
		this.walletReference = walletReference;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TransactionRecords [profileName=" + profileName + ", transactionDate=" + transactionDate
				+ ", transactionAmount=" + transactionAmount + ", transactionNarrative=" + transactionNarrative
				+ ", transactionDescription=" + transactionDescription + ", transactionId=" + transactionId
				+ ", transactionType=" + transactionType + ", walletReference=" + walletReference + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((profileName == null) ? 0 : profileName.hashCode());
		result = prime * result + (int) (transactionAmount ^ (transactionAmount >>> 32));
		result = prime * result + ((transactionDate == null) ? 0 : transactionDate.hashCode());
		result = prime * result + ((transactionDescription == null) ? 0 : transactionDescription.hashCode());
		result = prime * result + ((transactionId == null) ? 0 : transactionId.hashCode());
		result = prime * result + ((transactionNarrative == null) ? 0 : transactionNarrative.hashCode());
		result = prime * result + transactionType;
		result = prime * result + ((walletReference == null) ? 0 : walletReference.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransactionRecords other = (TransactionRecords) obj;
		if (profileName == null) {
			if (other.profileName != null)
				return false;
		} else if (!profileName.equals(other.profileName))
			return false;
		if (transactionAmount != other.transactionAmount)
			return false;
		if (transactionDate == null) {
			if (other.transactionDate != null)
				return false;
		} else if (!transactionDate.equals(other.transactionDate))
			return false;
		if (transactionDescription == null) {
			if (other.transactionDescription != null)
				return false;
		} else if (!transactionDescription.equals(other.transactionDescription))
			return false;
		if (transactionId == null) {
			if (other.transactionId != null)
				return false;
		} else if (!transactionId.equals(other.transactionId))
			return false;
		if (transactionNarrative == null) {
			if (other.transactionNarrative != null)
				return false;
		} else if (!transactionNarrative.equals(other.transactionNarrative))
			return false;
		if (transactionType != other.transactionType)
			return false;
		if (walletReference == null) {
			if (other.walletReference != null)
				return false;
		} else if (!walletReference.equals(other.walletReference))
			return false;
		return true;
	}

}
