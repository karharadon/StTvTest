Information about project is in "AboutProject.jpg"

This branch is for older versions of firefox(41)with library guice ( It provides support for dependency injection using annotations to configure Java objects.)

How to setup & launch the automation framework: Please clone this repository to your local machine with installed software : Maven3 + Java + Firefox (Chrome, IE) + drivers for browsers. There is chromedriver in the "/drivers/". But it is for Linux_64. So if you have another system you should point path to your drivers in GuiceTestModule.class. To run all tests please run in project folder command : mvn test -Dsuite=runAlltests.xml. To run only UI tests run command : mvn test -Dsuite=runUItests.xml. To run only API tests run command : mvn test -Dsuite=runAPItests.xml

To execute tests in chrome add "-Dbrowser=firefox" into command line. E.x. mvn test -Dsuite=UItests.xml -Dbrowser=firefox. By default it starts firefox.

P.S.: This project is not finished. It is for training, so there are some extra libraries, annotations, etc. At this moment there are some issues(on my machine): 1. firefox shows too much information in terminal. 2. chrome hangs and show window only after test, but with ready test results:) I am going to fix these issues after Christmas. Maybe all will be good on your machine.

//TODO. Implement launching of different browsers versions from one test with profiles, DesiredCapabilities, etc. (in branch guicefirefox50). Close this branch.
