package com.track.calorie;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


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
                    if(user == null)
                    {
                        Toast.makeText(MainActivity.this,"User",Toast.LENGTH_SHORT).show();
                        pass.setText("");
                    }
                    else{

                        Bundle mBundle = new Bundle();
                        mBundle.putString("user",user.getEmail());
                        Intent intent = new Intent(MainActivity.this,UserActivity.class);
                        intent.putExtras(mBundle);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this,"Welcome "+ user.getEmail(),Toast.LENGTH_SHORT).show();
                }
            }
            }
            //}
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish();
                if(!emptyValidation()){
                    dbHelper.addUser(new User(email.getText().toString(),pass.getText().toString()));
                    Toast.makeText(MainActivity.this,"Added user",Toast.LENGTH_SHORT).show();
                    email.setText("");
                    pass.setText("");
                }
                else{
                    Toast.makeText(MainActivity.this,"Empty Fields",Toast.LENGTH_SHORT).show();
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

                /*
                if(email.getText().toString().equals("admin") && pass.getText().toString().equals("admin")) {

                    Toast.makeText(getApplicationContext(), "Redirecting...",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Wrong Credentials",Toast.LENGTH_SHORT).show();*/

//      tx1.setVisibility(View.VISIBLE);
//tx1.setBackgroundColor(Color.RED);
//counter--;
//tx1.setText(Integer.toString(counter));

                    /*
                    if (counter == 0) {
                        signIn.setEnabled(false);
                    }
                    */