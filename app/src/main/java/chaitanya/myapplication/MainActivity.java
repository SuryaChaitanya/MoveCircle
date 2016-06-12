package chaitanya.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;

public class MainActivity extends AppCompatActivity  {
    ImageButton ib;
    private static final int SPEECH_REQUEST_CODE = 0;
    TextView tv;

    Button right,left,up,down;
    int x, y;
    float radius;

    ImageView circle;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        x=100;
        y=100;
        radius=100;

        ib = (ImageButton) findViewById(R.id.imageButton);
        tv = (TextView) findViewById(R.id.textView);
        left=(Button)findViewById(R.id.button1);
        right=(Button)findViewById(R.id.button2);
        up=(Button)findViewById(R.id.button3);
        down=(Button)findViewById(R.id.button4);

        circle = (ImageView) findViewById(R.id.imageView);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {
            List<String> results = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            String spokenText = results.get(0);
            tv.setText("" + spokenText);


            // Do something with spokenText
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    public void click(View v)
    {



        if(v.getId()==R.id.button1)
        {
            Toast.makeText(this, "left button", Toast.LENGTH_LONG).show();
            x=x-10;

        }
        else if(v.getId()==R.id.button2)
        {
            Toast.makeText(this, "right button", Toast.LENGTH_LONG).show();
            x=x+10;

        }
        else if(v.getId()==R.id.button3)
        {
            Toast.makeText(this, "up button", Toast.LENGTH_LONG).show();
            y=y+10;

        }
        else if(v.getId()==R.id.button4)
        {
            Toast.makeText(this, "down button", Toast.LENGTH_LONG).show();
            y=y+10;
        }
        else if(v.getId()==R.id.imageButton)
        {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            // Start the activity, the intent will be populated with the speech text
            startActivityForResult(intent, SPEECH_REQUEST_CODE);
        }

        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);

        Bitmap bmp = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);


        Canvas canvas = new Canvas();
        canvas.drawCircle(x, y, radius, paint);

        circle.setImageBitmap(bmp);
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
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://chaitanya.myapplication/http/host/path")
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
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://chaitanya.myapplication/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

}
