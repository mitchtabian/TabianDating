package codingwithmitch.com.tabiandating;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import codingwithmitch.com.tabiandating.util.Resources;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "LoginActivity";

    //widgets
    private Button mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mLogin = findViewById(R.id.btn_login);
        Log.d(TAG, "onCreate: started.");

        mLogin.setOnClickListener(this);

        setBackgroundImage();
    }

    private void setBackgroundImage(){
        ImageView backgroundView = findViewById(R.id.background);
        Glide.with(this)
                .load(Resources.BACKGROUND_HEARTS)
                .into(backgroundView);
    }


    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick: clicked.");

        if(view.getId() == R.id.btn_login){
            Log.d(TAG, "onClick: logging in the user.");

            // Need to use LoginActivity.this to access context because we're inside an interface
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish(); //make sure to finish the activity so it's removed from the activity stack
        }
    }


}
