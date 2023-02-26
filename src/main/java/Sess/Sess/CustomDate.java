package Sess.Sess;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class CustomDate {

    static private DateTimeFormatter dtf;

    static public LocalDate getLocalDate() {
        dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        dtf.withLocale(Locale.US);
        return LocalDate.now();
    }

    static public LocalDate getLocaleDate(String date) {
        dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        dtf.withLocale(Locale.US);
        return LocalDate.parse(date, dtf);
    }
}
