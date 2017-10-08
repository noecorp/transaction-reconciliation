package com.tutuka.reconciliation.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tutuka.reconciliation.exceptions.ProcessingException;
import com.tutuka.reconciliation.model.TransactionComparator;
import com.tutuka.reconciliation.model.TransactionRecords;
import com.tutuka.reconciliation.model.TransactionRecordsWrapper;

/**
 * @author Rishabh
 *
 *         Utility class to process the transactions, compare the data-sets and
 *         generate the non-matching and possible matching report
 */
public class TransactionProcessor {

	/**
	 * @param file
	 *            : Data set file containing transaction details
	 * @param in
	 *            : Input stream for the provided file
	 * @param model
	 *            : map containing the attributes that needs to be passed to JSP
	 * 
	 * @throws ProcessingException
	 * 
	 *             This function reads the file, process the transactions, store
	 *             them in model map to passed across different functions
	 */
	public static void getAllTransactions(String file, Reader in, Map<String, Object> model)
			throws ProcessingException {
		List<TransactionRecords> transactions = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(in);
			String headers = br.readLine();
			DateFormat df = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");

			String line = "";

			while ((line = br.readLine()) != null && !line.isEmpty()) {
				String[] fields = new String[headers.split(",").length];

				for (int i = 0; i < fields.length; i++) {
					fields[i] = "";
				}
				String tempFields[] = line.split(",");

				for (int i = 0; i < tempFields.length; i++) {
					fields[i] = tempFields[i];
				}

				String profileName = fields[0];
				Date transactionDate = df.parse(fields[1]);
				long transactionAmount = Long.valueOf(fields[2]);
				String transactionNarrative = fields[3];
				String transactionDescription = fields[4];
				String transactionId = fields[5];
				int transactionType = Integer.valueOf(fields[6]);
				String walletReference = fields[7];

				TransactionRecords records = new TransactionRecords(profileName, transactionDate, transactionAmount,
						transactionNarrative, transactionDescription, transactionId, transactionType, walletReference);

				transactions.add(records);
			}
			br.close();
		} catch (NumberFormatException | IOException | ParseException e) {
			throw new ProcessingException("Error while processing the transactions " + e.getMessage());
		}

		model.put(file + "transactions", transactions);
		model.put(file + "records", transactions.size());

	}

	/**
	 * @param model
	 *            : map containing the attributes that needs to be passed to JSP
	 * 
	 *            This function process and identify all the non-exact matching
	 *            transactions
	 */
	@SuppressWarnings("unchecked")
	public static void getUnmatchTransactions(Map<String, Object> model) {
		List<TransactionRecords> file1Transactions = (List<TransactionRecords>) model.get("file1transactions");
		List<TransactionRecords> file2Transactions = (List<TransactionRecords>) model.get("file2transactions");
		List<TransactionRecords> file1UnmatchTransactions = new ArrayList<>();
		List<TransactionRecords> file2UnmatchTransactions = new ArrayList<>();
		file1UnmatchTransactions.addAll(file1Transactions);
		file1UnmatchTransactions.removeAll(file2Transactions);
		Collections.sort(file1UnmatchTransactions, new TransactionComparator());

		model.put("file1unmatchset", file1UnmatchTransactions);
		model.put("file1unmatch", file1UnmatchTransactions.size());

		file2UnmatchTransactions.addAll(file2Transactions);
		file2UnmatchTransactions.removeAll(file1Transactions);

		Collections.sort(file2UnmatchTransactions, new TransactionComparator());
		model.put("file2unmatchset", file2UnmatchTransactions);
		model.put("file2unmatch", file2UnmatchTransactions.size());
		getPossibleMatchTransactions(model);

	}

	/**
	 * @param model
	 *            : map containing the attributes that needs to be passed to JSP
	 * 
	 *            This function identifies all the possible closely matching
	 *            transactions and reason for being non-exact match
	 */
	@SuppressWarnings("unchecked")
	public static void getPossibleMatchTransactions(Map<String, Object> model) {
		List<TransactionRecords> file1UnmatchTransactions = (List<TransactionRecords>) model.get("file1unmatchset");
		List<TransactionRecords> file2UnmatchTransactions = (List<TransactionRecords>) model.get("file2unmatchset");
		List<TransactionRecordsWrapper> file1NewUnmatchTransactions = new ArrayList<>();
		List<TransactionRecordsWrapper> file2NewUnmatchTransactions = new ArrayList<>();
		List<TransactionRecordsWrapper> file1PossibleMatchTransactions = new ArrayList<>();
		List<TransactionRecordsWrapper> file2PossibleMatchTransactions = new ArrayList<>();
		List<TransactionRecordsWrapper> file1OrphanTransactions = new ArrayList<>();
		List<TransactionRecordsWrapper> file2OrphanTransactions = new ArrayList<>();

		Iterator<TransactionRecords> iter = file1UnmatchTransactions.iterator();
		while (iter.hasNext()) {
			file1NewUnmatchTransactions.add(new TransactionRecordsWrapper(iter.next()));
		}
		iter = file2UnmatchTransactions.iterator();
		while (iter.hasNext()) {
			file2NewUnmatchTransactions.add(new TransactionRecordsWrapper(iter.next()));
		}
		file1PossibleMatchTransactions.addAll(file1NewUnmatchTransactions);
		file1PossibleMatchTransactions.retainAll(file2NewUnmatchTransactions);

		model.put("file1possiblematchtransactions", file1PossibleMatchTransactions);

		file1OrphanTransactions.addAll(file1NewUnmatchTransactions);
		file1OrphanTransactions.removeAll(file2NewUnmatchTransactions);
		Collections.sort(file1OrphanTransactions);
		model.put("file1orphantransactions", file1OrphanTransactions);
		model.put("file1orphansize", file1OrphanTransactions.size());

		file2PossibleMatchTransactions.addAll(file2NewUnmatchTransactions);
		file2PossibleMatchTransactions.retainAll(file1NewUnmatchTransactions);

		model.put("file2possiblematchtransactions", file2PossibleMatchTransactions);

		file2OrphanTransactions.addAll(file2NewUnmatchTransactions);
		file2OrphanTransactions.removeAll(file1NewUnmatchTransactions);
		Collections.sort(file2OrphanTransactions);
		model.put("file2orphantransactions", file2OrphanTransactions);
		model.put("file2orphansize", file2OrphanTransactions.size());

		TransactionMismatchReason.getMismatchReason(model);

	}

	/**
	 * @param model
	 *            : map containing the attributes that needs to be passed to JSP
	 * 
	 *            This function is used to calculate the size of matching
	 *            transactions
	 */
	public static void getMatchedTransactionsSize(Map<String, Object> model) {
		int match = (int) model.get("file1records") - (int) model.get("file1unmatch");
		model.put("file1match", match);
		match = (int) model.get("file2records") - (int) model.get("file2unmatch");
		model.put("file2match", match);
	}

}