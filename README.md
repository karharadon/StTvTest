How to setup & launch the automation framework

Please clone this repository to your local machine with installed software :

Maven + Java 7 and high + Firefox (Chrome) + driver for browser. To run tests please run in project folder command : mvn test -Dsuite=testNG.xml. To run only UI tests run command : mvn test -Dsuite=UItests.xml To run only API tests run command : mvn test -Dsuite=APItests.xml

To execute tests in chrome add "-Dbrowser=firefox" into command line. E.x. mvn test site -Dsuite=UItests.xml -Dbrowser=firefox. By default it starts chrome.
