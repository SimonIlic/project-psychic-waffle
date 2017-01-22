package mprog.nl.automeetup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.security.acl.Group;
import java.util.ArrayList;

/** A group arraylist adapter for displaying groups in the main activity
 *
 * Awesome list adapter tutorial here:
 * http://stackoverflow.com/questions/2265661/how-to-use-arrayadaptermyclass
 *
 * Created by Simon on 22-1-2017.
 */

public class GroupListAdapter extends ArrayAdapter<MeetingGroup> {
    ViewHolder viewHolder;

    private static class ViewHolder {
        private TextView groupTitleView;
        private ImageView groupImageView;
        private TextView nMembersView;
    }

    public GroupListAdapter(Context context, int textViewResourceId, ArrayList<MeetingGroup> items) {
        super(context, textViewResourceId, items);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(R.layout.group_list_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.groupTitleView = (TextView) convertView.findViewById(R.id.groupTitle);
            viewHolder.nMembersView = (TextView) convertView.findViewById(R.id.numberOfMembers);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        MeetingGroup item = getItem(position);
        if (item != null) {
            viewHolder.groupTitleView.setText(item.getName());
            viewHolder.nMembersView.setText(String.valueOf(item.getMembers().size()));
        }

        return convertView;
    }
}