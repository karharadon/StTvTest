package lastochkin.streamTV.wrestlers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jayway.restassured.response.Response;
import lastochkin.streamTV.helpers.ConfigProperties;

import java.util.HashMap;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;

public class WrestlerServiceAPI {
    private String updateUrl = ConfigProperties.getProperty("updateUrl");
    private String username = ConfigProperties.getProperty("username");
    private String password = ConfigProperties.getProperty("password");
    private String loginUrl = ConfigProperties.getProperty("loginUrl");
    private String sessionId = loginThroughAPI();
    private String createUrl = ConfigProperties.getProperty("createUrl");
    private String readUrl = ConfigProperties.getProperty("readUrl");
    private String deleteUrl = ConfigProperties.getProperty("deleteUrl");

    private String loginThroughAPI() {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", username);
        credentials.put("password", password);
        String jsonObject = new Gson().toJson(credentials);
        Response response = given().contentType(JSON).body(jsonObject).
                when().post(loginUrl);
        response.then().statusCode(200);
        assertThat(response.asString(), not(containsString("false")));
        return response.cookie("PHPSESSID");
    }

    public String createWrestlerThroughAPI(WrestlerAPI wrestlerAPI1) {
        String givenSportsman = new Gson().toJson(wrestlerAPI1);
        Response response = given().contentType(JSON).cookie("PHPSESSID", sessionId).body(givenSportsman).
                when().post(createUrl);
        response.then().statusCode(200);
        assertThat(response.asString(), not(containsString("Invalid query")));
        JsonObject result = new Gson().fromJson(response.asString(), JsonObject.class);
        System.out.println("Sportsman was added. id = " + result.get("id").getAsString());
        return result.get("id").getAsString();
    }

    public void updateWrestlerThroughAPI(WrestlerAPI wrestlerAPI2) {
        String givenSportsman = new Gson().toJson(wrestlerAPI2);
        Response response = given().contentType(JSON).cookie("PHPSESSID", sessionId).body(givenSportsman).
                when().post(updateUrl);
        response.then().statusCode(200);
        assertThat(response.asString(), not(containsString("Invalid query")));
    }

    public void deletewrestlerThroughAPI(String wrestlerId) {
        Response response = given().cookie("PHPSESSID", sessionId).
                when().delete(deleteUrl, wrestlerId);
        response.then().statusCode(200);
        assertThat(response.asString(), not(containsString("Invalid query")));
        System.out.println(wrestlerId + " sportsman was deleted successfully");
    }

    public WrestlerAPI readWrestlerThroughAPI(String id) {
        Response response = given().cookie("PHPSESSID", sessionId).
                when().get(readUrl, id);
        response.then().statusCode(200);
        WrestlerAPI wrestler = new Gson().fromJson(response.asString(), WrestlerAPI.class);
        assertThat(response.asString(), not(containsString("Invalid query")));
        System.out.println("Read operation through API was performed for sportsman " + wrestler.toString());
        return wrestler;
    }
}
