package hu.bead.dinosaurs.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import hu.bead.dinosaurs.model.Dino;

@Database(entities = {Dino.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class DinoDatabase extends RoomDatabase {
    public abstract DinoDao dinoDao();
}
