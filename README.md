# transaction-reconciliation
This project perform a financial reconciliation between two different sets of data

Assumptions for reconciliation:
1. Exact match : 
				If all the provided columns match in both the sets of data, a transaction is considered as exact match

2. Possible match (done only for records which are not exact match) : 
				a. The transaction amount matches
				b. If transaction amount matches, compare the wallet reference. If wallet reference matches, considered as possible match.
				c. If transaction amount matches but wallet reference is missing/empty, compares the transaction id. If transaction id matches, considered as possible match.

3. Non-match :
				If records are neither exact match nor the possible match, its considered as non matching report.

How to deploy and run the application:
1. 	An executable war is added to the project. Download the war and run the following command:

	java -jar transaction-reconciliation-1.0.war
	
	Once the server is started, open the browser and go to : http://localhost:8080/

2. You can also build and deploy the project. To build the project, run the following command at the location of pom.xml file:
	
	mvn clean install
	
	It will generate an executable "transaction-reconciliation-1.0.war" inside the target folder. Run the following command to start the server:
	
	java -jar target\transaction-reconciliation-1.0.war

	Once the server is started, open the browser and go to : http://localhost:8080/
	
	
		