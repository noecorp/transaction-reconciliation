package com.tutuka.reconciliation.model;

/**
 * @author Rishabh
 * 
 *         Wrapper class over TransactionRecords model. Utilized to identify the
 *         possible matching transactions based on transaction amount, wallet
 *         reference and transaction id
 */

public class TransactionRecordsWrapper implements Comparable<TransactionRecordsWrapper> {
	private TransactionRecords record;

	public TransactionRecords getRecord() {
		return record;
	}

	public void setRecord(TransactionRecords record) {
		this.record = record;
	}

	@Override
	public String toString() {
		return "TransactionsPossibleEquator [record=" + record + "]";
	}

	public TransactionRecordsWrapper(TransactionRecords record) {
		this.record = record;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((record == null) ? 0 : record.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransactionRecordsWrapper other = (TransactionRecordsWrapper) obj;
		if (this.record.getTransactionAmount() == other.record.getTransactionAmount()) {
			if (!this.record.getWalletReference().isEmpty() && !other.record.getWalletReference().isEmpty()) {
				if (this.record.getWalletReference().trim()
						.equalsIgnoreCase(other.record.getWalletReference().trim())) {
					return true;
				}
			} else if (this.record.getTransactionId().equalsIgnoreCase(other.record.getTransactionId())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int compareTo(TransactionRecordsWrapper o) {
		int c;

		c = (int) (this.record.getTransactionAmount() - o.record.getTransactionAmount());

		if (c == 0) {
			if (!this.record.getWalletReference().isEmpty() && !o.record.getWalletReference().isEmpty()) {
				c = this.record.getWalletReference().compareTo(o.record.getWalletReference());
			}
		}

		if (c == 0) {
			c = this.record.getTransactionId().compareTo(o.record.getTransactionId());
		}

		return c;
	}

}
