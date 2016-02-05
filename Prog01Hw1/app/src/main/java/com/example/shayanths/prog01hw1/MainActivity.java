package com.example.shayanths.prog01hw1;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.DecimalFormat;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class MainActivity extends AppCompatActivity {

    Button mButton;
    EditText mEdit;
    TextView mText;
    TextView mText2;
    RadioGroup exercise;
    RadioButton exerciseType;
    TextView mText3;

    static HashMap<String, Integer> exercises = new HashMap<String, Integer>();

    static {
        exercises.put("Pushup", 350);
        exercises.put("Situp", 200);
        exercises.put("Jumping Jacks", 10);
        exercises.put("Jogging", 12);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mButton = (Button) findViewById(R.id.button);
        mEdit = (EditText) findViewById(R.id.editText);
        mText = (TextView) findViewById(R.id.textView5);
        mText2 = (TextView) findViewById(R.id.textView3);
        mText3 = (TextView) findViewById(R.id.textView4);
        exercise = (RadioGroup) findViewById(R.id.radioGroup);


        exercise.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                exerciseType = (RadioButton) findViewById(checkedId);
                if (exerciseType.getText().toString().equals("Pushup") || exerciseType.getText().toString().equals("Situp")) {
                    mText.setText("Reps");
                } else {
                    mText.setText("Minutes");
                }
            }
        });
//        final

        mButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        String input_string = mEdit.getText().toString();
                        double myNum = 0.0;
                        DecimalFormat numberFormat = new DecimalFormat("#.00");

                        try {
                            myNum = new Double(mEdit.getText().toString());
                        } catch (NumberFormatException nfe) {
                            System.out.println("Could not parse " + nfe);
                        }

                        double exercise_val = 100 * (myNum / new Double(exercises.get(exerciseType.getText().toString())));
                        mText2.setText(numberFormat.format(exercise_val));
                        StringBuilder other_exercises = new StringBuilder("");

                        for (String key : exercises.keySet()) {
                            if (!exerciseType.getText().toString().equals(key)) {
                                double conversion = (exercise_val * new Double(exercises.get(key))) / 100;
                                other_exercises.append(key + " : " + (Double.toString(Math.round(conversion))) + "\n");
                            }
                        }

                        mText3.setText("You need to do the following exercises to burn the same amount of calories\n\n" + other_exercises);
                    }
                }
        );
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.shayanths.prog01hw1/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.shayanths.prog01hw1/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
