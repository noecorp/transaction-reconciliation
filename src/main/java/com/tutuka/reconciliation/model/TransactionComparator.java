package com.tutuka.reconciliation.model;

import java.util.Comparator;

/**
 * @author Rishabh
 * 
 *         Comparator class to compare the transactions. Transactions are
 *         compared based on transaction amount, wallet reference and
 *         transaction date. This is used to display the sorted non matching
 *         transactions on UI
 */
public class TransactionComparator implements Comparator<TransactionRecords> {

	@Override
	public int compare(TransactionRecords o1, TransactionRecords o2) {

		int c;
		c = (int) (o1.getTransactionAmount() - o2.getTransactionAmount());

		if (c == 0) {
			c = o1.getWalletReference().compareTo(o2.getWalletReference());
		}

		if (c == 0) {
			c = o1.getTransactionDate().compareTo(o2.getTransactionDate());
		}

		return c;
	}

}
