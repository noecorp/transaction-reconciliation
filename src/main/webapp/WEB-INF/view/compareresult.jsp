<script>
	var toggle = function() {
		var reportdiv = document.getElementById('showreportdiv');
		if (reportdiv.style.display === 'none')
			reportdiv.style.display = 'block'
	}
</script>

<div id="resultsummary">

	<table id="hor-minimalist-b" summary="result title">
		<thead>
			<tr>
				<th scope="col">Comparison result</th>
			</tr>
		</thead>
	</table>
	<table id="hor-minimalist-b" summary="compare result">
		<thead>
			<tr>
				<th scope="col">${file1name}</th>
				<th scope="col">${file2name}</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align="center">Total Records : ${file1records}</td>
				<td align="center">Total Records : ${file2records}</td>
			</tr>
			<tr>
				<td align="center">Matching Records : ${file1match}</td>
				<td align="center">Matching Records : ${file2match}</td>
			</tr>
			<tr>
				<td align="center">Unmatched Records : ${file1unmatch}</td>
				<td align="center">Unmatched Records : ${file2unmatch}</td>
			</tr>
		</tbody>
	</table>
	<table id="hor-minimalist-b" summary="report generation">
		<tbody>
			<tr>
				<td align="center"><input id="reportbutton" type="button" value="Unmatch Report" onclick="toggle();" /></td>
			</tr>
		</tbody>
	</table>
</div>

<div id="showreportdiv" style="display: none">
	<%@ include file="/WEB-INF/view/showreport.jsp"%>
</div>