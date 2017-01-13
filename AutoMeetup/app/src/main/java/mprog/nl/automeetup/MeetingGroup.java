package mprog.nl.automeetup;

import java.util.ArrayList;

/**
 * This
 *
 * Created by Simon on 12-1-2017.
 */

public class MeetingGroup {
    private String name;
    private ArrayList<String> members;
    private ArrayList<String> emailAddresses;
    private String image;

    MeetingGroup(){
        // default constructor required for calls to DataSnapshot.getValue(MeetingGroup.class)

        // init arrayLists
        members = new ArrayList<>();
        emailAddresses = new ArrayList<>();
    }

    void addMember(String email){
        emailAddresses.add(email);
    }

    void removeMember(int index){
        emailAddresses.remove(index);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<String> members) {
        this.members = members;
    }

    public ArrayList<String> getEmailAddresses() {
        return emailAddresses;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}