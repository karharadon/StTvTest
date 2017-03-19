package wrestlers;

import com.google.gson.annotations.SerializedName;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class WrestlerAPI {

    @SerializedName("lname")
    private String lastName;

    @SerializedName("fname")
    private String firstName;

    @SerializedName("mname")
    private String middleName;

    @SerializedName("dob")
    private String dateOfBirth;

    @SerializedName("style")
    private String style;

    @SerializedName("region1")
    private String regionFirst;

    @SerializedName("fst1")
    private String fstFirst;

    @SerializedName("expires")
    private String year;

    @SerializedName("lictype")
    private String age;

    @SerializedName("card_state")
    private String card;

    @SerializedName("id_wrestler")
    private String id;

    private WrestlerAPI(String lastName, String firstName, String dateOfBirth, String middleName,
                        String regionFirst, String fstFirst,
                        String style, String age, String year, String card, String id) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.middleName = middleName;
        this.regionFirst = regionFirst;
        this.fstFirst = fstFirst;
        this.style = style;
        this.age = age;
        this.year = year;
        this.card = card;
        this.id = id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString() {
        return "Wrestler{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", middleName='" + middleName + '\'' +
                ", regionFirst='" + regionFirst + '\'' +
                ", fstFirst='" + fstFirst + '\'' +
                ", style='" + style + '\'' +
                ", age='" + age + '\'' +
                ", year='" + year + '\'' +
                ", card='" + card + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    @DataProvider(name = "wrestlers")
    public static Object[][] dp(Method method) {
        WrestlerAPI wrestlerAPI1 = new WrestlerAPI("Lapa", "Wapa", "12-05-1979", "Dellla",
                "2", "2", "2", "2", "2016", "2", null);
        WrestlerAPI wrestlerAPI2 = new WrestlerAPI("Superman", "Batman", "12-05-1989", "Joker",
                "3", "3", "3", "3", "2016", "3", null);

        if (method.getName().equalsIgnoreCase("updateWrestlerThroughAPI"))
            return new Object[][]{{wrestlerAPI1, wrestlerAPI2}};
        else
            return new Object[][]{{wrestlerAPI1}};
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        WrestlerAPI that = (WrestlerAPI) obj;

        return (this.middleName.equals(that.middleName) &&
                this.lastName.equals(that.lastName) &&
                this.firstName.equals(that.firstName) &&
                this.dateOfBirth.equals(that.dateOfBirth) &&
                this.middleName.equals(that.middleName) &&
                this.regionFirst.equals(that.regionFirst) &&
                this.fstFirst.equals(that.fstFirst) &&
                this.style.equals(that.style) &&
                this.age.equals(that.age) &&
                this.year.equals(that.year) &&
                this.card.equals(that.card));
    }
}

