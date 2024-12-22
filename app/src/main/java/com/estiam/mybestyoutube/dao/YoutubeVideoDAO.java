package com.estiam.mybestyoutube.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.estiam.mybestyoutube.pojos.YoutubeVideo;

import java.util.List;

@Dao
public interface YoutubeVideoDAO {

    @Query("SELECT * FROM youtubevideo WHERE id=:id")
    public YoutubeVideo find(Long id);

    @Query("SELECT * FROM YoutubeVideo")
    public List<YoutubeVideo> list();

    @Insert
    public void add(YoutubeVideo... todos);//Permet de définir des méthodes qui qui insèrent leurs paramètres dans la table appropriée de la base de données.

    @Update
    public void update(YoutubeVideo... todos);//Permet de définir des méthodes qui mettent à jour des lignes spécifiques dans une table de la base de données.

    @Delete
    public void delete(YoutubeVideo... todos);//Permet de définir des méthodes qui suppriment des lignes spécifiques d'une table de la base de données.
}
