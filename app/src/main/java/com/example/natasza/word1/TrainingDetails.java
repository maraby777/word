package com.example.natasza.word1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class TrainingDetails extends Activity {


    private Button mChangeLanguageBtn;
    private Button mChangeCategoryBtn;
    private Button mStartBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_details);

        createsViews();
        attachEvents();
    }

    private void attachEvents() {
        mChangeLanguageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnChangeLanguage:
                        break;
                    default:
                        break;
                }
            }
        });

        mChangeCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnChangeCategory:
                        Intent intent = new Intent(TrainingDetails.this, Category.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        });

        mStartBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                switch (v.getId()){
                    case R.id.btnStart:
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void createsViews() {
        mChangeCategoryBtn = (Button) findViewById(R.id.btnChangeCategory);
        mChangeLanguageBtn = (Button) findViewById(R.id.btnChangeLanguage);
        mStartBtn = (Button) findViewById(R.id.btnStart);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_training_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
