package mprog.nl.automeetup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

/** The meeting class holds all info about a meeting
 *
 * Created by Simon on 21-1-2017.
 */

public class Meeting implements java.io.Serializable {
    private Date startDate;
    private Date endDate;
    private Date meetingDate;
    private int meetingDuration;
    private String groupID;
    private String meetingID;


    public Meeting() {
        // Default constructor required for calls to DataSnapshot.getValue(Meeting.class)
    }

    public Meeting(String groupID){
        setGroupID(groupID);
    }

    public void requestMeeting(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        // add meeting object to request database so the python client can handle notifications
        meetingID = database.child("requests").push().getKey();
        database.child("requests").child(meetingID).setValue(this);

        // add meeting to the groups meeting list
        database.child("groups").child(groupID).child("meetings").child(meetingID).setValue(this);
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(Date meetingDate) {
        this.meetingDate = meetingDate;
    }

    public int getMeetingDuration() {
        return meetingDuration;
    }

    public void setMeetingDuration(int meetingDuration) {
        this.meetingDuration = meetingDuration;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getMeetingID() {
        return meetingID;
    }

    public void setMeetingID(String meetingID) {
        this.meetingID = meetingID;
    }
}
