package hu.bead.dinosaurs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.AsyncQueryHandler;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hu.bead.dinosaurs.dao.DinoDatabase;
import hu.bead.dinosaurs.model.Diet;
import hu.bead.dinosaurs.model.Dino;

public class DinoList extends AppCompatActivity {
    DinoDatabase db;
    Dino[] dinos;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dino_list);

        db = Room.databaseBuilder(this, DinoDatabase.class, "dino_database").build();

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                dinos = db.dinoDao().getAll().toArray(new Dino[0]);
            }
        });
        while(dinos == null){
            System.out.println("Loading......");
        }

        recyclerView = findViewById(R.id.recyclerView);
        MyAdapter myAdapter = new MyAdapter(this, dinos);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setTitle(R.string.lbl_list);
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