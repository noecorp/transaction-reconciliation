package com.tutuka.reconciliation.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tutuka.reconciliation.exceptions.ProcessingException;
import com.tutuka.reconciliation.utils.TransactionProcessor;

/**
 * @author Rishabh
 * 
 *         Controller class to read and process the 2 different financial data
 *         sets and provide the final reconciliation report of non-matching
 *         transactions
 *
 */
@Controller
public class FileController {

	/**
	 * This method is mapped to root call
	 * 
	 * @return index web page
	 */
	@RequestMapping("/")
	public String index() {
		return "recon";
	}

	/**
	 * @param file1
	 *            : first data set file in *.csv format containing transaction
	 *            details
	 * @param file2
	 *            : second data set file in *.csv format containing transaction
	 *            details
	 * @param redirectAttributes
	 *            : attributes that needs to be redirected in case of page
	 *            redirection
	 * @param model
	 *            : map containing variables that needs to be passed to JSP page
	 *            to process/display
	 * @param req
	 *            : servlet request parameter
	 * @return : returns redirected page or next page to be loaded
	 * 
	 *         This method read and validate the transaction file. On successful
	 *         validation, pass the reconciled result/report to JSP pages
	 */
	@RequestMapping(value = "/compare", method = RequestMethod.POST)
	public String showCompareResult(@RequestParam(value = "file1", required = true) MultipartFile file1,
			@RequestParam(value = "file2", required = true) MultipartFile file2, RedirectAttributes redirectAttributes,
			Map<String, Object> model, final HttpServletRequest req) {
		String page = "recon";

		if (file1.isEmpty() || file2.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select files to upload");
			page = "redirect:/";
			return page;
		} else if (!file1.getOriginalFilename().endsWith(".csv") || !file2.getOriginalFilename().endsWith(".csv")) {
			redirectAttributes.addFlashAttribute("message", "Incorrect file format. Allowed format: .csv");
			page = "redirect:/";
			return page;
		}

		model.put("showresult", "true");

		model.put("file1name", file1.getOriginalFilename());
		model.put("file2name", file2.getOriginalFilename());

		try {
			TransactionProcessor.getAllTransactions("file1", new InputStreamReader(file1.getInputStream()), model);
			TransactionProcessor.getAllTransactions("file2", new InputStreamReader(file2.getInputStream()), model);

			TransactionProcessor.getUnmatchTransactions(model);
			TransactionProcessor.getMatchedTransactionsSize(model);

			if ((int) model.get("file1orphansize") > (int) model.get("file2orphansize")) {
				model.put("iterateover", "file2");
			} else {
				model.put("iterateover", "file1");
			}
			model.remove("file1Transactions");
			model.remove("file2Transactions");
			model.remove("file1unmatchset");
			model.remove("file2unmatchset");
		} catch (ProcessingException | IOException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", e.getMessage());
			page = "redirect:/";
			return page;

		}

		return page;
	}

}