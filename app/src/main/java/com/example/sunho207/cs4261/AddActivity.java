package com.example.sunho207.cs4261;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AddActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText firstname;
    private EditText lastname;
    private EditText phone;
    private EditText email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        mAuth = FirebaseAuth.getInstance();

        firstname = findViewById((R.id.firstname));
        lastname = findViewById((R.id.lastname));
        phone = findViewById((R.id.phone));
        email = findViewById((R.id.email));

        Button add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Map<String, String> map = new HashMap<>();
            FirebaseUser currentUser = mAuth.getCurrentUser();
            map.put("firstname", firstname.getText().toString());
            map.put("lastname", lastname.getText().toString());
            map.put("phone", phone.getText().toString());
            map.put("email", email.getText().toString());
            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
            mDatabase.child(currentUser.getEmail().substring(0,currentUser.getEmail().indexOf('@'))).child(UUID.randomUUID().toString()).setValue(map);
            Toast.makeText(getApplicationContext(),"Contact successfully added",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(AddActivity.this, MainActivity.class);
            startActivity(intent);
            }
        });


    }
}
