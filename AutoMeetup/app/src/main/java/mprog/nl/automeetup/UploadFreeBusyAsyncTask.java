package mprog.nl.automeetup;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.TimeZone;

/**
 * Created by Simon on 23-1-2017.
 */

public class UploadFreeBusyAsyncTask extends AsyncTask<Meeting, Void, String> {
    static final String api_url = "https://www.googleapis.com/calendar/v3/freeBusy?" +
            "key=AIzaSyB2vMmcGL8aQ9n4wN-2Ifh22ux8zVSbal0";

    @Override
    protected String doInBackground(Meeting... meetings) {
        Meeting meeting = meetings[0];

        // form API URL
        URL url = null;
        try {
            url = new URL(api_url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // open connection
        URLConnection con = null;
        HttpURLConnection http = null;
        try {
            con = url.openConnection();
            http = (HttpURLConnection)con;
        } catch (IOException e) {
            e.printStackTrace();
        }

        // set request method to POST
        try {
            http.setRequestMethod("POST");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        http.setDoOutput(true);

        // create POST request body
        // body JSON as string (inserting the searched URL, removing space chars)
        JSONObject outJSON = new JSONObject();
        try {
            outJSON.put("timeMin", FormattingHelper.dateToISOString(meeting.getStartDate()));
            outJSON.put("timeMax", FormattingHelper.dateToISOString(meeting.getEndDate()));
            outJSON.put("timeZone", TimeZone.getDefault().toString());
            outJSON.put("items", "[{\"id\": \"simonilic@hotmail.com\"}]");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // convert JSON to byte array
        //byte[] out = outJSON.toString().getBytes(StandardCharsets.UTF_8);
        byte[] out = "{\"timeMax\":\"2017-02-23T13:15:03-08:00\",\"timeMin\":\"2017-02-01T13:15:03-08:00\",\"items\":[{\"id\":\"simonilic@hotmail.com\"},{\"id\":\"simonilic@hotmail.com\"}],\"timeZone\":\"UTC+01:00\"}"
                .getBytes(StandardCharsets.UTF_8);
        int length = out.length;

        http.setFixedLengthStreamingMode(length);
        http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

        // do POST request
        try(OutputStream os = http.getOutputStream()) {
            http.connect();
            os.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            // get json data as string
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(http.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            bufferedReader.close();
            return stringBuilder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            http.disconnect();
        }

        return "ERROR";
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        Log.d("RESULT", result);
    }
}
