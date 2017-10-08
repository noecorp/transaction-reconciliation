package com.tutuka.reconciliation.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tutuka.reconciliation.model.TransactionRecords;
import com.tutuka.reconciliation.model.TransactionRecordsWrapper;

/**
 * @author Rishabh
 *
 *         This class compares the each possible matching record against all its
 *         attribute to identify the possible reason for not being the exact
 *         match
 * 
 */
public class TransactionMismatchReason {

	@SuppressWarnings("unchecked")
	public static void getMismatchReason(Map<String, Object> model) {
		List<TransactionRecordsWrapper> file1PossibleMatchTransactions = (List<TransactionRecordsWrapper>) model
				.get("file1possiblematchtransactions");
		List<TransactionRecordsWrapper> file2PossibleMatchTransactions = (List<TransactionRecordsWrapper>) model
				.get("file2possiblematchtransactions");
		List<String> mismatchReasons = new ArrayList<>();

		for (int i = 0; i < file1PossibleMatchTransactions.size(); i++) {
			StringBuilder reason = new StringBuilder("");
			TransactionRecords file1record = file1PossibleMatchTransactions.get(i).getRecord();
			TransactionRecords file2record = file2PossibleMatchTransactions.get(i).getRecord();
			if (!file1record.getProfileName().equalsIgnoreCase(file2record.getProfileName())) {
				reason.append("Profile Name/");
			}
			if (!file1record.getTransactionDate().equals(file2record.getTransactionDate())) {
				reason.append("Transaction Date/");
			}
			if (!file1record.getTransactionDescription().equals(file2record.getTransactionDescription())) {
				reason.append("Transaction Description/");
			}
			if (!file1record.getTransactionId().equals(file2record.getTransactionId())) {
				reason.append("Transaction Id/");
			}
			if (!file1record.getTransactionNarrative().equals(file2record.getTransactionNarrative())) {
				reason.append("Transaction Narrative/");
			}
			if (file1record.getTransactionType() != file2record.getTransactionType()) {
				reason.append("Transaction Type/");
			}
			if (!file1record.getWalletReference().equals(file2record.getWalletReference())) {
				reason.append("Wallet Reference/");
			}
			mismatchReasons.add(reason.deleteCharAt(reason.lastIndexOf("/")).toString());
		}

		model.put("mismatchreason", mismatchReasons);
	}

}