<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">

<%@ include file="/WEB-INF/view/include.jsp"%>

<head>
<title>Transaction Reconcilation Report</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<link rel="stylesheet" href="../../css/main.css" />

</head>
<body>
	<div id="filechoser">
		<form method="POST" action="/compare" enctype="multipart/form-data">
			<table id="hor-minimalist-b" summary="transaction files">
				<thead>
					<tr>
						<th scope="col">Transaction Compare</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td align="center">Select file 1<input type="file"
							accept=".csv" name="file1" value="file1" /></td>
					</tr>
					<tr>
						<td align="center">Select file 2<input type="file"
							accept=".csv" name="file2" value="file2" /></td>
					</tr>
					<c:if test="${message != null}">
						<tr>
							<td align="center" style="color: red">${message}</td>
						</tr>
					</c:if>
					<tr>
						<td align="center"><input type="submit" name="Compare" value="Compare" /></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<c:if test="${showresult == 'true'}">
		<%@ include file="/WEB-INF/view/compareresult.jsp"%>
	</c:if>
	
</body>
</html>