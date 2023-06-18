package com.example.chatapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    // Write a message to the database
    private FirebaseDatabase database;
    private DatabaseReference myRef, messagesRef;
    private EditText inputMessage;
    private TextView textMessage;
    private Button buttonSend;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        db = FirebaseFirestore.getInstance();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");
        messagesRef = database.getReference("messages");

        inputMessage = findViewById(R.id.inputMessage);
        textMessage = findViewById(R.id.textMessage);
        buttonSend = findViewById(R.id.buttonSend);

        for(User user : getUsers()){
            Toast.makeText(this, user.getEmail(), Toast.LENGTH_SHORT).show();
        }

        messagesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot mSnapShot : snapshot.getChildren()){
                    Message message = mSnapShot.getValue(Message.class);
                    textMessage.setText(message.getText());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        buttonSend.setOnClickListener(View -> {
           myRef.setValue(inputMessage.getText().toString());

            Message message = new Message(
                    "",
                    FirebaseAuth.getInstance().getUid(),
                    inputMessage.getText().toString()
            );

            String Key = messagesRef.push().getKey();
            messagesRef.child(Key).setValue(message);
        });
    }

    public List<User> getUsers(){
        List<User> userList = new ArrayList<>();
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                User user = document.toObject(User.class);
                                document.get("name", String.class);
                                userList.add(user);
                            }

                        }else{

                        }
                    }
                });
        return userList;
    }
}