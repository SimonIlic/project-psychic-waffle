package mprog.nl.automeetup;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.util.Date;

/** Plan meeting dialog fragment handles meeting request
 *
 * Created by Simon on 20-1-2017.
 */

public class PlanMeetingDialogFragment extends DialogFragment implements View.OnClickListener,
        DatePickerFragment.DatePickerFragmentListener{
    private View layoutView;
    private Button startDateButton, endDateButton;
    private Meeting meeting;
    private NumberPicker numberPicker;
    String[] nums;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // generate meeting object
        meeting = new Meeting();

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater and layout view
        LayoutInflater inflater = getActivity().getLayoutInflater();
        layoutView = inflater.inflate(R.layout.plan_meeting_dialog_layout, null);

        //
        setButtonOnClickListeners();
        configureNumberPicker();

        builder.setTitle("Plan Meeting")
                .setView(layoutView)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // set the selected meeting duration
                        meeting.setMeetingDuration(Integer.valueOf(nums[numberPicker.getValue()]));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //TODO implement cancel logic
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    /** configure the number picker for meeting duration **/
    private void configureNumberPicker() {
        numberPicker = (NumberPicker) layoutView.findViewById(R.id.numberPicker);
        nums = new String[18];
        for(int i = 2; i - 2 < nums.length; i++) {
            nums[i - 2] = Integer.toString(i * 10);
        }

        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(nums.length - 1);
        numberPicker.setWrapSelectorWheel(false);
        numberPicker.setDisplayedValues(nums);
        numberPicker.setValue(3);
    }

    /** Sets the onClickListeners for the buttons in the dialog to **/
    private void setButtonOnClickListeners() {
        startDateButton = (Button) layoutView.findViewById(R.id.periodStartButton);
        endDateButton = (Button) layoutView.findViewById(R.id.periodEndButton);
        startDateButton.setOnClickListener(this);
        endDateButton.setOnClickListener(this);
    }

    /** On click functions for this dialog **/
    @Override
    public void onClick(View v) {
        if (R.id.periodStartButton == v.getId()) {
            Toast.makeText(getActivity(), "Clicked start button", Toast.LENGTH_SHORT).show();
            DialogFragment meetingPlannerDialogFragment = DatePickerFragment.newInstance(this);
            meetingPlannerDialogFragment.show(getFragmentManager(), "StartDatePicker");
        }
        else if (R.id.periodEndButton == v.getId()) {
            Toast.makeText(getActivity(), "CLicked end button", Toast.LENGTH_SHORT).show();
            DialogFragment meetingPlannerDialogFragment = DatePickerFragment.newInstance(this);
            meetingPlannerDialogFragment.show(getFragmentManager(), "EndDatePicker");
        }

    }

    /** Method is called when a date is picked inside the DatePickerFragment
     * The method will be called with the date from the `DatePicker`. **/
    @Override
    public void onDateSet(Date date, String tag) {
        if (tag.contentEquals("StartDatePicker")){
            startDateButton.setText(date.toString());
            meeting.setStartDate(date);
        }
        else if (tag.contentEquals("EndDatePicker")) {
            endDateButton.setText(date.toString());
            meeting.setEndDate(date);
        }
    }
}
