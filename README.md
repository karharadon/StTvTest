This project is not finished. 

How to setup & launch the automation framework:

Please clone this repository to your local machine with installed software :
Maven + Java + Firefox (Chrome, IE) + driver for browser. To run all tests please run in project folder command : mvn test -Dsuite=runAllTests.xml. To run only UI tests run command : mvn test -Dsuite=runUItests.xml. To run only API tests run command : mvn test -Dsuite=runAPItests.xml

To execute tests in chrome add "-Dbrowser=firefox" into command line. E.x. mvn test -Dsuite=UItests.xml -Dbrowser=firefox. By default it starts chrome.
