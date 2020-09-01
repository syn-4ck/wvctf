# WebVulnCTF

*WVCTF* is a web platform whose goal is provide a vulnerable web aplication that contains some flags to capture.  

## Build and deploy

Requirements:

 - Java 8 JDK (recommended jdk1.8.0_251 or latest)
 - Apache Maven 3.6.3
 - Node v12 (recommended v12.16.2) with NPM 6.14.x
 - PostgreSQL (v12 recommended) run in the default port (5432) with a empty database 'wvctf', user 'postgres' and password 'root'. For more security configure /src/main/resources/application.properties.
 - MongoDB (v4.2 recommended) run in the default port (27017) with a empty database 'WVCTF', user 'root' and password 'toor' in the authentication database 'admin'. For more security configure /src/main/resources/application.properties.

To build with Maven: 

	Backend: (on <path>/wvctf)
	
		mvn clean install
	
	Frontend: (on <path>/wvctf/src/main/java/com/julianfm/wvctf/frontend)
	
		npm install

To run:

	Backend: (on <path>/wvctf)
	
		mvn spring-boot:run
	
	Frontend: (on <path>/wvctf/src/main/java/com/julianfm/wvctf/frontend)
	
		npm start

For help, you have the script run_script.bat (only for CMD of Windows).
