package com.tutuka.reconciliation.exceptions;

/**
 * @author Rishabh
 * 
 *         Exception which can occur while processing/reconciling the
 *         transactions
 */
@SuppressWarnings("serial")
public class ProcessingException extends Exception {

	public ProcessingException(String message) {
		super(message);
	}

}
