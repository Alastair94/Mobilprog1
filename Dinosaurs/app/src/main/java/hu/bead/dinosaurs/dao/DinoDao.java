package hu.bead.dinosaurs.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import hu.bead.dinosaurs.model.Dino;

@Dao
public interface DinoDao {
    @Query("SELECT * FROM dino")
    List<Dino> getAll();

    @Query("SELECT * FROM dino WHERE dino_name = :name")
    Dino getDino(String name);

    @Insert
    void insertAll(Dino... dinos);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Dino dino);

    @Query("DELETE FROM dino")
    void deleteALL();

    @Query("DELETE FROM dino WHERE dino_name = :name")
    void deleteDino(String name);
}
