package mprog.nl.automeetup;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;

/**
 * Date picker fragment class as per google's prescription:
 * https://developer.android.com/guide/topics/ui/controls/pickers.html#DatePicker
 *
 * for all interface stuff Xaver Kapeller (stackoverflow) was a HUGE help:
 * http://stackoverflow.com/questions/24558835/how-can-i-pass-the-date-chosen-in-a-date-picker-to-the-activity-which-contains-t
 *
 * Created by Simon on 20-1-2017.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private DatePickerFragmentListener datePickerListener;
    public interface DatePickerFragmentListener {
        public void onDateSet(Date date, String tag);
    }

    public DatePickerFragmentListener getDatePickerListener() {
        return this.datePickerListener;
    }

    public void setDatePickerListener(DatePickerFragmentListener listener) {
        this.datePickerListener = listener;
    }

    protected void notifyDatePickerListener(Date date, String tag) {
        if(this.datePickerListener != null) {
            this.datePickerListener.onDateSet(date, tag);
        }
    }

    public static DatePickerFragment newInstance(DatePickerFragmentListener listener) {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setDatePickerListener(listener);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        Date date = c.getTime();

        // Here we call the listener and pass the date back to it.
        notifyDatePickerListener(date, this.getTag());
    }
}