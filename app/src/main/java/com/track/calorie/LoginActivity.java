package com.track.calorie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {


    Button signIn, signUp;
    EditText email, pass;

    TextView tx1;
    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signIn = (Button)findViewById(R.id.button);
        email = (EditText)findViewById(R.id.editText);
        pass = (EditText)findViewById(R.id.editText2);

        signUp = (Button)findViewById(R.id.button2);
        tx1 = (TextView)findViewById(R.id.textView3);
        tx1.setVisibility(View.GONE);

        final DatabaseHelper dbHelper = new DatabaseHelper(this);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!emptyValidation()){
                    User user = dbHelper.addUser(new User(email.getText().toString(),pass.getText().toString()));
                    if(user != null)
                    {
                        Bundle mBundle = new Bundle();
                        mBundle.putString("user",user.getEmail());
                        Intent intent = new Intent(LoginActivity.this,UserActivity.class);
                        intent.putExtras(mBundle);
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this,"Welcome "+ user.getEmail(),Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(LoginActivity.this,"User",Toast.LENGTH_SHORT).show();
                        pass.setText("");

                    }
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!emptyValidation()){
                    dbHelper.addUser(new User(email.getText().toString(),pass.getText().toString()));
                    Toast.makeText(LoginActivity.this,"Added user",Toast.LENGTH_SHORT).show();
                    email.setText("");
                    pass.setText("");
                }
                else{
                    Toast.makeText(LoginActivity.this,"Empty Fields",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean emptyValidation(){
        if(TextUtils.isEmpty(email.getText().toString())|| TextUtils.isEmpty(pass.getText().toString())) {
            return true;
        }else
            return false;
    }

}