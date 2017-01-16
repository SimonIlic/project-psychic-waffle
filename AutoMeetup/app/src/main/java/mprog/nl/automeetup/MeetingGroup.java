package mprog.nl.automeetup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * This
 *
 * Created by Simon on 12-1-2017.
 */

public class MeetingGroup implements java.io.Serializable {
    private String name;
    private String id;
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

    /** use firebase to create a unique id for this group
     * return this id on success, else -1 **/
    public String createId() {
        if (id == null) {
            DatabaseReference database = FirebaseDatabase.getInstance().getReference();
            id = database.child("groups").push().getKey();

            return id;
        }
        else{
            return "ERROR: id already exists.";
        }
    }

    public void finalizeMembers(){
        
    }

    void addGroupToDatabase(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        // write the group object to the database

        database.child("groups").child(id).setValue(this);
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}