package lastochkin.streamTV.tests;

import com.google.inject.Inject;
import lastochkin.streamTV.helpers.GuiceTestModule;
import lastochkin.streamTV.helpers.TestListener;
import lastochkin.streamTV.wrestlers.Wrestler;
import lastochkin.streamTV.wrestlers.WrestlerAPI;
import lastochkin.streamTV.wrestlers.WrestlerServiceAPI;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Guice;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.model.SeverityLevel;

import java.lang.reflect.Method;

import static org.hamcrest.MatcherAssert.assertThat;

public class APItest {

    WrestlerServiceAPI api = new WrestlerServiceAPI();
    private String wrestlerId;

    @Test(dataProviderClass = WrestlerAPI.class, dataProvider = "wrestlers")
    @Severity(SeverityLevel.BLOCKER)
    public void createWrestlerThroughAPI(WrestlerAPI wrestlerAPI1) {
        wrestlerId = api.createWrestlerThroughAPI(wrestlerAPI1);
        WrestlerAPI actualWrestler = api.readWrestlerThroughAPI(wrestlerId);

        assertThat("Wrestlers was created with mistakes! Expected: " + wrestlerAPI1 + ". But was:" + actualWrestler,
                actualWrestler.equals(wrestlerAPI1));
    }

    @Test(dataProviderClass = WrestlerAPI.class, dataProvider = "wrestlers")
    @Severity(SeverityLevel.BLOCKER)
    public void updateWrestlerThroughAPI(WrestlerAPI wrestlerAPI1, WrestlerAPI wrestlerAPI2) {
        wrestlerId = api.createWrestlerThroughAPI(wrestlerAPI1);
        wrestlerAPI2.setId(wrestlerId);
        api.updateWrestlerThroughAPI(wrestlerAPI2);
        WrestlerAPI actualWrestler = api.readWrestlerThroughAPI(wrestlerId);
        assertThat("Wrestlers was created with mistakes! Expected: " + wrestlerAPI2 + ". But was:" + actualWrestler,
                actualWrestler.equals(wrestlerAPI2));
    }

    @AfterMethod(enabled = false)
    public void cleanDataBase(Method method) {
        api.deletewrestlerThroughAPI(wrestlerId);
    }
}

