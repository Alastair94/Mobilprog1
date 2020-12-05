package hu.bead.dinosaurs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import java.text.Normalizer;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        refreshLanguage();
        setContentView(R.layout.activity_main);

        VideoView videoView = findViewById(R.id.videoView);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.welcome;
        Uri uri = Uri.parse(path);
        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
    }

    public void goToDinoList(View view){
        Intent intent = new Intent(MainActivity.this, DinoList.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void goToForm(View view){
        Intent intent = new Intent(MainActivity.this, FormActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.appbar, menu);
        return true;
    }

    private void refreshLanguage(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String lang = preferences.getString("language", "hu");
        Locale myLocale = new Locale(lang);
        Configuration conf = getResources().getConfiguration();
        conf.locale = myLocale;
        this.getResources().updateConfiguration(conf, this.getResources().getDisplayMetrics());
        invalidateOptionsMenu();
        setTitle(R.string.app_name);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.settings){
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
        if(item.getItemId() == R.id.options){
            Intent intent = new Intent(this, OptionsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}