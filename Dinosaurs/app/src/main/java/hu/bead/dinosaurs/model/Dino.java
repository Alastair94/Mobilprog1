package hu.bead.dinosaurs.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import java.io.Serializable;

@Entity(tableName = "dino", indices = {@Index(value = {"dino_name"}, unique = true)})
public class Dino implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "dino_name")
    String name;
    public Diet diet;
    int weight;
    double height;
    double length;
    int image;
    public Dino() {
    }

    public Dino(String name, Diet diet, int weight, double height, double length, int image) {
        this.name = name;
        this.diet = diet;
        this.weight = weight;
        this.height = height;
        this.length = length;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Diet getDiet() {
        return diet;
    }

    public void setDiet(Diet diet) {
        this.diet = diet;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Dino{" +
                ", name='" + name + '\'' +
                ", diet=" + diet +
                ", weight=" + weight +
                ", height=" + height +
                ", length=" + length +
                ", image=" + image +
                '}';
    }
}
