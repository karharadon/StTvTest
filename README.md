Information about project is in "AboutProject.jpg"

How to setup & launch the automation framework:
Please clone this repository to your local machine with installed software :
Maven3 + Java + Firefox (Chrome, IE) + drivers for browsers. To run all tests please run in project folder command : mvn test -Dsuite=runAlltests.xml. To run only UI tests run command : mvn test -Dsuite=runUItests.xml. To run only API tests run command : mvn test -Dsuite=runAPItests.xml

To execute tests in chrome add "-Dbrowser=firefox" into command line. E.x. mvn test -Dsuite=UItests.xml -Dbrowser=firefox. By default it starts chrome.

P.S.: This project is not finished. It is for training, so there are some extra libraries, annotations, etc.
At this moment there some issues: 1. firefox shows too much information in terminal. 2. chrome hangs and show window only after test, but with test test results:)
I am going to fix these issues after Christmas. Maybe all will be good on your environment.
