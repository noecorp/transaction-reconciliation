<%@ include file="/WEB-INF/view/include.jsp"%>

<table id="hor-minimalist-b" summary="report title">
	<thead>
		<tr>
			<th scope="col">Unmatch Report</th>
		</tr>
	</thead>
</table>
<table id="hor-minimalist-b" summary="file details">
	<thead>
		<tr>
			<th scope="col">${file1name}</th>
			<th scope="col">${file2name}</th>
		</tr>
	</thead>
</table>
<table id="hor-minimalist-b" summary="unmatch report">
	<thead>
		<tr>
			<th scope="col">Date</th>
			<th scope="col">Amount</th>
			<th scope="col">Reference</th>
			<th scope="col">Date</th>
			<th scope="col">Amount</th>
			<th scope="col">Reference</th>
			<th scope="col">Possible Reason for Mismatch</th>
		</tr>
	</thead>
	<tbody>
			<tr>
				<td align="center"></td>
				<td align="center"></td>
				<td align="center"></td>
				<td align="center" style="color: green; font-size: 12px; font-weight: bold">Possible Matching Transactions</td>
				<td align="center"></td>
				<td align="center"></td>
				<td align="center"></td>
			</tr>
		<c:forEach var="transaction" items="${file1possiblematchtransactions}" varStatus="status">
			<tr>
				<td align="center">${transaction.record.transactionDate}</td>
				<td align="center">${transaction.record.transactionAmount}</td>
				<td align="center">${transaction.record.walletReference}</td>
				<td align="center">${file2possiblematchtransactions[status.index].record.transactionDate}</td>
				<td align="center">${file2possiblematchtransactions[status.index].record.transactionAmount}</td>
				<td align="center">${file2possiblematchtransactions[status.index].record.walletReference}</td>
				<td align="center">${mismatchreason[status.index]}</td>
			</tr>
		</c:forEach>
		<tr>
				<td align="center"></td>
				<td align="center"></td>
				<td align="center"></td>
				<td align="center" style="color: red; font-size: 12px; font-weight: bold">Non Matching Transactions</td>
				<td align="center"></td>
				<td align="center"></td>
				<td align="center"></td>
			</tr>
		<c:if test="${iterateover == 'file1'}">
			<c:forEach var="transaction" items="${file1orphantransactions}"	varStatus="status">
				<tr>
					<td align="center">${transaction.record.transactionDate}</td>
					<td align="center">${transaction.record.transactionAmount}</td>
					<td align="center">${transaction.record.walletReference}</td>
					<td align="center">${file2orphantransactions[status.index].record.transactionDate}</td>
					<td align="center">${file2orphantransactions[status.index].record.transactionAmount}</td>
					<td align="center">${file2orphantransactions[status.index].record.walletReference}</td>
					<td align="center"></td>
				</tr>
			</c:forEach>
			<c:forEach begin="${file1orphansize+1}" end="${file2orphansize}" varStatus="loop">
				<tr>
					<td align="center"></td>
					<td align="center"></td>
					<td align="center"></td>
					<td align="center">${file2orphantransactions[loop.index-1].record.transactionDate}</td>
					<td align="center">${file2orphantransactions[loop.index-1].record.transactionAmount}</td>
					<td align="center">${file2orphantransactions[loop.index-1].record.walletReference}</td>
					<td align="center"></td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${iterateover == 'file2'}">
			<c:forEach var="transaction" items="${file2orphantransactions}"	varStatus="status">
				<tr>
					<td align="center">${file1orphantransactions[status.index].record.transactionDate}</td>
					<td align="center">${file1orphantransactions[status.index].record.transactionAmount}</td>
					<td align="center">${file1orphantransactions[status.index].record.walletReference}</td>
					<td align="center">${transaction.record.transactionDate}</td>
					<td align="center">${transaction.record.transactionAmount}</td>
					<td align="center">${transaction.record.walletReference}</td>
					<td align="center"></td>
				</tr>
			</c:forEach>
			<c:forEach begin="${file2orphansize+1}" end="${file1orphansize}" varStatus="loop">
				<tr>
					<td align="center">${file1orphantransactions[loop.index-1].record.transactionDate}</td>
					<td align="center">${file1orphantransactions[loop.index-1].record.transactionAmount}</td>
					<td align="center">${file1orphantransactions[loop.index-1].record.walletReference}</td>
					<td align="center"></td>
					<td align="center"></td>
					<td align="center"></td>
					<td align="center"></td>
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
</table>