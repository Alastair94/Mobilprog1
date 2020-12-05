package hu.bead.dinosaurs;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

import hu.bead.dinosaurs.dao.DinoDatabase;
import hu.bead.dinosaurs.model.Diet;
import hu.bead.dinosaurs.model.Dino;

public class FormActivity extends AppCompatActivity {

    DinoDatabase db;
    EditText dinoName, dinoWeight, dinoHeight, dinoLength;
    Button upload;
    MediaPlayer mP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        Spinner dropdown = findViewById(R.id.dietSpinner);
        String herbivore = getResources().getString(R.string.herbivore);
        String carnivore = getResources().getString(R.string.carnivore);
        String[] items = new String[]{herbivore, carnivore};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        db = Room.databaseBuilder(this, DinoDatabase.class, "dino_database").build();

        dinoName = findViewById(R.id.dinoName);
        dinoWeight = findViewById(R.id.dinoWeight);
        dinoHeight = findViewById(R.id.dinoHeight);
        dinoLength = findViewById(R.id.dinoLength);
        upload = findViewById(R.id.btn_upload);

        dinoName.addTextChangedListener(watcher);
        dinoWeight.addTextChangedListener(watcher);
        dinoHeight.addTextChangedListener(watcher);
        dinoLength.addTextChangedListener(watcher);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setTitle(R.string.lbl_register);
    }

    public void upload(View view){

        dinoName = findViewById(R.id.dinoName);
        String name = dinoName.getText().toString();
        Spinner dinoDiet = findViewById(R.id.dietSpinner);
        Diet diet;
        switch(dinoDiet.getSelectedItem().toString()){
            case "Húsevő":
            case "Carnivore":
                diet = Diet.CARNIVORE;
                break;
            default:
                diet = Diet.HERBIVORE;
        }
        dinoWeight = findViewById(R.id.dinoWeight);
        int weight = Integer.parseInt(dinoWeight.getText().toString());
        dinoHeight = findViewById(R.id.dinoHeight);
        double height = Double.parseDouble(dinoHeight.getText().toString());
        dinoLength = findViewById(R.id.dinoLength);
        double length = Double.parseDouble(dinoLength.getText().toString());
        int image = R.drawable.nopicture;

        Dino dino = new Dino(name, diet, weight, height, length, image);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                db.dinoDao().insert(dino);
            }
        });

        Intent intent = new Intent(FormActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Data sent successfully", Toast.LENGTH_SHORT).show();
    }

    private final TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(dinoName.getText().toString().trim().isEmpty() || dinoWeight.getText().toString().isEmpty() || dinoHeight.getText().toString().isEmpty() || dinoLength.getText().toString().isEmpty())
                upload.setEnabled(false);
            else if(!upload.isEnabled())
                upload.setEnabled(true);
        }
    };
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}