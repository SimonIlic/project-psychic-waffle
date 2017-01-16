package mprog.nl.automeetup;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class ViewGroupActivity extends AppCompatActivity {
    MeetingGroup group;
    ArrayAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_group);
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

        // get partially constructed group from previous activity
        Bundle inentBundle = getIntent().getExtras();
        group = (MeetingGroup) inentBundle.getSerializable("Group");

        setupGroupInfoUI();
    }

    /** set up title TV and group image TV etc **/
    public void setupGroupInfoUI(){
        EditText titleET = (EditText) findViewById(R.id.groupNameET);
        titleET.setText(group.getName());

        setupListView();
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

    /** context menu for long clicks **/
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    /** handle context menu logic **/
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.action_delete:
                deleteMember(info.position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    /** removes a member from a group **/
    private void deleteMember(int position) {
    }


}