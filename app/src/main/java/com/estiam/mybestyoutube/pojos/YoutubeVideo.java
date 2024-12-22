package com.estiam.mybestyoutube.pojos;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class YoutubeVideo implements Serializable {

    @PrimaryKey(autoGenerate = true) //Permet de définir l’attribut qui sera la clé primaire de la table. La propriété autoGenerate = true, permetd’attribuer des ID automatiques.
    private long id;

    @ColumnInfo(name="titre") //Permet de définir avec la propriété name un nom de colonne qui sera différent du nom de l’attribut.
    private String titre;

    @ColumnInfo(name="description")
    private String description;

    @ColumnInfo(name="url")
    private String url;

    @ColumnInfo(name="categorie")
    private String categorie;

    @ColumnInfo(name="favori")
    private Integer favori;

    //constructeur
    public YoutubeVideo(){}

    public YoutubeVideo(String titre, String description, String url, String categorie, Integer favori) {
        this.titre = titre;
        this.description = description;
        this.url = url;
        this.categorie = categorie;
        this.favori = favori;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Integer getFavori() {
        return favori;
    }

    public void setFavori(Integer favori) {
        this.favori = favori;
    }
}
