package mprog.nl.automeetup;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A user class
 *
 * Created by Simon on 12-1-2017.
 */

public class User {
    private String id;
    private String fullName;
    private String email;
    private String[] groups;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String uid, String username, String emailAddress){
        this.id = uid;
        this.fullName = username;
        this.email = emailAddress;
    }

    void addUserToDatabase(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        // write the user info to the database
        database.child("users").child(id).setValue(this);
        // keep a reference in the database mapping email to uid's
        database.child("email_to_id").child(email.replaceAll("\\.", ",")).setValue(id);
    }

    /** Getters and Setters **/
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String[] getGroups() {
        return groups;
    }

    public void setGroups(String[] groups) {
        this.groups = groups;
    }
}
