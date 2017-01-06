Information about project is in "AboutProject.jpg"

this branch is for older versions of firefox(41)with library guice ( It provides support for dependency injection using annotations to configure Java objects.)

How to setup & launch the automation framework: Please clone this repository to your local machine with installed software : Maven3 + Java + Firefox (Chrome, IE) + drivers for browsers. To run all tests please run in project folder command : mvn test -Dsuite=runAlltests.xml. To run only UI tests run command : mvn test -Dsuite=runUItests.xml. To run only API tests run command : mvn test -Dsuite=runAPItests.xml

To execute tests in chrome add "-Dbrowser=firefox" into command line. E.x. mvn test -Dsuite=UItests.xml -Dbrowser=firefox. By default it starts chrome.

//TODO. Implement launching of different browsers versions from one test with profiles, DesiredCapabilities, etc. (in branch guicefirefox50). Close this branch.
