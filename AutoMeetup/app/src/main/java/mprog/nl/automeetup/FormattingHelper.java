package mprog.nl.automeetup;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/** A helper class that deals with correctly formatting strings
 *
 * Created by Simon on 23-1-2017.
 */

public class FormattingHelper {
    public static String dateToISOString(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        return df.format(date);
    }
}
