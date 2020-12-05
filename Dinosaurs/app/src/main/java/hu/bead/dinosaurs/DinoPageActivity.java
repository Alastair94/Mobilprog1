package hu.bead.dinosaurs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import hu.bead.dinosaurs.dao.DinoDatabase;
import hu.bead.dinosaurs.model.Diet;
import hu.bead.dinosaurs.model.Dino;

public class DinoPageActivity extends AppCompatActivity {
    DinoDatabase db;
    Dino dino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dino_page);

        db = Room.databaseBuilder(this, DinoDatabase.class, "dino_database").build();

        Intent intent = getIntent();
        dino = (Dino) intent.getSerializableExtra("dino_name_txt");

        if(savedInstanceState == null){
            FragmentTransaction dinoFragment = getSupportFragmentManager().beginTransaction().replace(R.id.dinoFragment, new DinoPageFragment());
            DinoPageFragment fragment = new DinoPageFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("dino", dino);
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.dinoFragment, fragment).commit();
        }
        setTitle(R.string.lbl_data);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            Intent intent = new Intent(this, DinoList.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void deleteThis(View view){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                db.dinoDao().deleteDino(dino.getName());
            }
        });
        Intent intent = new Intent(this, DinoList.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}