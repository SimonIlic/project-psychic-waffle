package mprog.nl.automeetup;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class CreateGroupActivity extends AppCompatActivity {
    private MeetingGroup group;
    private ArrayAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // init a new group
        group = new MeetingGroup();

        // startup listview
        ListView memberListView = (ListView) findViewById(R.id.memberListView);
        ArrayAdapter listAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, group.getEmailAddresses());
        memberListView.setAdapter(listAdapter);
    }

    public void onAddButtonClick(View view) {
        EditText emailET = (EditText) view;
        group.addMember(emailET.getText().toString());
        
        listAdapter.notifyDataSetChanged();
    }
}
