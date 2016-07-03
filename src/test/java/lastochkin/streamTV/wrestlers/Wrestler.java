package lastochkin.streamTV.wrestlers;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

/**
 * Created by karhamint on 16.06.16.
 */
public class Wrestler {
    public String lastName;
    public String firstName;
    public String dateOfBirth;
    public String middleName;
    public String regionFirst;
    public String regionSecond;
    public String fstFirst;
    public String fstSecond;
    public String trainerFirst;
    public String trainerSecond;
    public String style;
    public String age;
    public String year;
    public String card;

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getFullName() {
        String fullName = getLastName() + " " + getFirstName() + " " + getMiddleName();
        return fullName;
    }

    public Wrestler(String lastName, String firstName, String dateOfBirth, String middleName,
                    String regionFirst, String regionSecond, String fstFirst, String fstSecond,
                    String trainerFirst, String trainerSecond, String style, String age, String year, String card) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.middleName = middleName;
        this.regionFirst = regionFirst;
        this.regionSecond = regionSecond;
        this.fstFirst = fstFirst;
        this.fstSecond = fstSecond;
        this.trainerFirst = trainerFirst;
        this.trainerSecond = trainerSecond;
        this.style = style;
        this.age = age;
        this.year = year;
        this.card = card;
    }

    public String toString() {
        return "Wrestler{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", middleName='" + middleName + '\'' +
                ", regionFirst='" + regionFirst + '\'' +
                ", regionSecond='" + regionSecond + '\'' +
                ", fstFirst='" + fstFirst + '\'' +
                ", fstSecond='" + fstSecond + '\'' +
                ", trainerFirst='" + trainerFirst + '\'' +
                ", trainerSecond='" + trainerSecond + '\'' +
                ", style='" + style + '\'' +
                ", age='" + age + '\'' +
                ", year='" + year + '\'' +
                ", card='" + card + '\'' +
                '}';
    }

    /*
        @Override
        public int hashCode() {
            int result = lastName != null ? lastName.hashCode() : 0;
            result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
            result = 31 * result + (regionFirst != null ? regionFirst.hashCode() : 0);
            result = 31 * result + (fstFirst != null ? fstFirst.hashCode() : 0);
            result = 31 * result + (style != null ? style.hashCode() : 0);
            result = 31 * result + (year != null ? year.hashCode() : 0);
            result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
            result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
            result = 31 * result + (regionFirst != null ? regionFirst.hashCode() : 0);
            result = 31 * result + (fstSecond != null ? fstSecond.hashCode() : 0);
            result = 31 * result + (age != null ? age.hashCode() : 0);
            return result;
        }
    */
    @DataProvider(name = "wrestlers")
    public static Object[][] dp(Method method) {
        Wrestler wrestler1 = new Wrestler("Lapa", "Wapa", "12-05-1979", "Dellla", "Volynska", "Kyivska",
                "Dinamo", "SK", "Joda", "Kenobi", "FS", "Cadet", "2016", "Recieved");
        Wrestler wrestler2 = new Wrestler("Superman", "Batman", "12-05-1989", "Joker", "Kyiv", "Zaporizka",
                "Kolos", "MON", "ObiVan", "DartaMol", "GR", "Senior", "2017", "Produced");
        Wrestler wrestler3 = new Wrestler("Superman", "Batman", "12-05-1989", "Joker", "Volynska", "Zaporizka",
                "Kolos", "MON", "ObiVan", "DartaMol", "GR", "Senior", "2017", "Produced");
        Wrestler wrestler4 = new Wrestler("Superman", "Batman", "12-05-1989", "Joker", "Kyiv", "Zaporizka",
                "Dinamo", "MON", "ObiVan", "DartaMol", "GR", "Senior", "2017", "Produced");
        Wrestler wrestler5 = new Wrestler("Superman", "Batman", "12-05-1989", "Joker", "Kyiv", "Zaporizka",
                "Kolos", "MON", "ObiVan", "DartaMol", "GR", "Senior", "2016", "Produced");
        Wrestler wrestler6 = new Wrestler("Superman", "Batman", "12-05-1989", "Joker", "Kyiv", "Zaporizka",
                "Kolos", "MON", "ObiVan", "DartaMol", "FW", "Senior", "2017", "Produced");

        if (method.getName().equalsIgnoreCase("checkFilters"))
            return new Object[][]{{wrestler2, wrestler3, wrestler4, wrestler5, wrestler6}};
        else if (method.getName().equalsIgnoreCase("updateAndVerify") ||
                method.getName().equalsIgnoreCase("updateWrestlerThroughAPI"))
            return new Object[][]{{wrestler1, wrestler2}};
        else
            return new Object[][]{{wrestler1}};
    }

}
