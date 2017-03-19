package helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestListener extends TestListenerAdapter {
    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onTestSuccess(ITestResult result) {
        LOG.info("+++++++++++++++++Test:" + result.getName() + " - passed succesfull  ++++++++++++++++ ");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LOG.info("..................... Test:" + result.getName() + " - failed ...........................");
    }

    @Override
    public void onTestStart(ITestResult result) {
        LOG.info("..................... Test:" + result.getName() + " - start ...........................");
    }

}
