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
    String id;
    String name;
    String email;
    String[] groups;

    User(String uid, String username, String emailAdress){
        id = uid;
        name = username;
        email = emailAdress;
    }

    void writeNewUserToDatabase(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        // write the user info to the database
        database.child("users").child(id).setValue(this);
        // keep a reference in the database mapping email to uid's
        database.child("email_to_id").child(email.replaceAll("\\.", ",")).setValue(id);
    }
}
