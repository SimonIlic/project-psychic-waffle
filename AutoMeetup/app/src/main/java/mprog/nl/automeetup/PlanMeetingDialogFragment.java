package mprog.nl.automeetup;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/** Plan meeting dialog fragment handles meeting request
 *
 * Created by Simon on 20-1-2017.
 */

public class PlanMeetingDialogFragment extends DialogFragment implements View.OnClickListener{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater and layout view
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View layoutView = inflater.inflate(R.layout.plan_meeting_dialog_layout, null);

        // set button onClickListeners
        Button startDateButton = (Button) layoutView.findViewById(R.id.periodStartButton);
        Button endDateButton = (Button) layoutView.findViewById(R.id.periodEndButton);
        startDateButton.setOnClickListener(this);
        endDateButton.setOnClickListener(this);

        builder.setTitle("Plan Meeting")
                .setView(layoutView)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public void onClick(View v) {
        if (R.id.periodStartButton == v.getId()) {
            Toast.makeText(getActivity(), "Clicked start button", Toast.LENGTH_SHORT).show();
        }
        else if (R.id.periodEndButton == v.getId()) {
            Toast.makeText(getActivity(), "CLicked end button", Toast.LENGTH_SHORT).show();
        }

    }
}
