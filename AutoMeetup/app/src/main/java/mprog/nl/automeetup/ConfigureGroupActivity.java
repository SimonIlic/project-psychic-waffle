package mprog.nl.automeetup;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ConfigureGroupActivity extends AppCompatActivity {
    MeetingGroup group;
    ArrayAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure_group);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalizeGroup();

                // start viewgroup activity
                Intent intent = new Intent(getBaseContext(), ViewGroupActivity.class);
                intent.putExtra("Group", group);
                intent.putExtra("instantPlan", true);
                startActivity(intent);
            }
        });

        // get partially constructed group from previous activity
        Bundle inentBundle = getIntent().getExtras();
        group = (MeetingGroup) inentBundle.getSerializable("Group");

        setupListView();
    }

    private void finalizeGroup() {
        // validate group name
        EditText groupNameET = (EditText) findViewById(R.id.groupNameET);
        String groupName = groupNameET.getText().toString();
        if (groupName.isEmpty()){
            Toast.makeText(this, "Please provide a name for the meeting group",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        // finalize group
        group.setName(groupName);
        group.createId();
        group.finalizeMembers();
        group.addGroupToDatabase();
    }

    /** set up listview and adapter **/
    public void setupListView(){
        // startup listview
        ListView memberListView = (ListView) findViewById(R.id.memberListView);
        listAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, group.getEmailAddresses());
        memberListView.setAdapter(listAdapter);

        // Register ListView for context menu, implicitly defining item longclick listener
        registerForContextMenu(memberListView);
    }

}
