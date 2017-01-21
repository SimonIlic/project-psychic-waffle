package mprog.nl.automeetup;

import java.util.Date;

/** The meeting class holds all info about a meeting
 *
 * Created by Simon on 21-1-2017.
 */

public class Meeting {
    private Date startDate;
    private Date endDate;
    private Date meetingDate;
    private int meetingDuration;


    public Meeting() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
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
}
