package hu.bead.dinosaurs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import hu.bead.dinosaurs.dao.DinoDatabase;
import hu.bead.dinosaurs.model.Diet;
import hu.bead.dinosaurs.model.Dino;

public class OptionsActivity extends AppCompatActivity {
    DinoDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        db = Room.databaseBuilder(this, DinoDatabase.class, "dino_database").build();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setTitle(R.string.options);
    }

    public void addExamples(View view){
        Dino gallimimus = new Dino("Gallimimus", Diet.HERBIVORE, 200, 2, 6, R.drawable.gallimimus);
        Dino pachycephalosaurus = new Dino("Pachycephalosaurus", Diet.HERBIVORE, 515, 1.83, 4.5, R.drawable.pachycephalosaurus);
        Dino parasaurolophus = new Dino("Parasaurolophus", Diet.HERBIVORE, 3150, 4.9, 10.5, R.drawable.parasaurolophus);
        Dino spinosaurus = new Dino("Spinosaurus", Diet.CARNIVORE, 6950, 5.65, 15.5, R.drawable.spinosaurus);
        Dino stegosaurus = new Dino("Stegosaurus", Diet.HERBIVORE, 3084, 2.74, 8.53, R.drawable.stegosaurus);
        Dino trex = new Dino("Tyrannosaurus Rex", Diet.CARNIVORE, 9250, 4.9, 12.3, R.drawable.trex);
        Dino triceratops = new Dino("Triceratops", Diet.HERBIVORE, 9000, 3, 8.45, R.drawable.triceratops);
        Dino velociraptor = new Dino("Velociraptor", Diet.CARNIVORE, 17, 0.5, 2.07, R.drawable.velociraptor);
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                db.dinoDao().insert(gallimimus);
                db.dinoDao().insert(pachycephalosaurus);
                db.dinoDao().insert(parasaurolophus);
                db.dinoDao().insert(spinosaurus);
                db.dinoDao().insert(stegosaurus);
                db.dinoDao().insert(trex);
                db.dinoDao().insert(triceratops);
                db.dinoDao().insert(velociraptor);
            }
        });
        Intent intent = new Intent(this, DinoList.class);
        startActivity(intent);
    }

    public void deleteRows(View view){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                db.dinoDao().deleteALL();
            }
        });
        Intent intent = new Intent(this, DinoList.class);
        startActivity(intent);
    }

    public void dropDatabase(View view){
        String[] s = getApplicationContext().databaseList();
        this.deleteDatabase(s[0]);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

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