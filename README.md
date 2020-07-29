# WebVulnCTF

*WVCTF* is a web platform whose goal is provide a vulnerable web aplication that contains some flags to capture.  

## Build and deploy

Requirements:

 - Java 8 JDK (recommended jdk1.8.0_251 or latest)
 - Apache Maven 3.6.3
 - Node v12 (recommended v12.16.2) with NPM 6.14.x
 
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
