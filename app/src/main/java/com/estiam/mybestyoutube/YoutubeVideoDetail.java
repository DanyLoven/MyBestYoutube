package com.estiam.mybestyoutube;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.estiam.mybestyoutube.database.YoutubeVideoDatabase;
import com.estiam.mybestyoutube.pojos.YoutubeVideo;
import com.estiam.mybestyoutube.recyclerviewadapter.YoutubeVideoAdapter;

public class YoutubeVideoDetail extends AppCompatActivity {

    private TextView tvDescription;
    private TextView tvCategorie;
    private TextView tvUrl;

    private ImageButton btnPlay;
    private ImageButton btnFavoris;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_video_detail);

        //afficher le back dans l'actioBar
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDefaultDisplayHomeAsUpEnabled(true);

        tvDescription=findViewById(R.id.tvDescription);
        tvCategorie=findViewById(R.id.tvCategorie);
        tvUrl=findViewById(R.id.tvUrl);
        btnPlay=findViewById(R.id.btnPlay);
        btnFavoris=findViewById(R.id.btnFavoris);

        //Recupérer l'intent qui a recupéré cette activity
        Intent intent= getIntent();

        //recupérer les donnés de l'intent
        YoutubeVideo youtubeVideo = (YoutubeVideo) intent.getSerializableExtra(MainActivity.KEY_YOUTUBE_VIDEO);
        tvDescription.setText(String.format("Description :\n%s", youtubeVideo.getDescription()));
        tvCategorie.setText(String.format("Categorie :\n%s", youtubeVideo.getCategorie()));
        tvUrl.setText(String.format("Url :\n%s", youtubeVideo.getUrl()));

        //Définir la vue du boutton favori
        if(youtubeVideo.getFavori()==0){
            btnFavoris.setImageResource(R.drawable.ic_baseline_favorite_border_24);
        }
        else{
            btnFavoris.setImageResource(R.drawable.ic_baseline_favorite_24);
        }

        //Définir la vidéo comme favoris ou pas
        btnFavoris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(youtubeVideo.getFavori()==0){
                    btnFavoris.setImageResource(R.drawable.ic_baseline_favorite_24);
                    youtubeVideo.setFavori(1);
                }
                else{
                    btnFavoris.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                    youtubeVideo.setFavori(0);
                }
                //mis à jour de la valeur du favori
                YoutubeVideoDatabase.getDb(getApplicationContext()).youtubeVideoDAO().update(youtubeVideo);
            }
        });

        //jouer la vidéo, intent vers le site ou l'app youtube
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeVideo.getUrl()));
                try {
                    YoutubeVideoDetail.this.startActivity(webIntent);
                } catch (ActivityNotFoundException ex) {
                    Toast.makeText(YoutubeVideoDetail.this, ex.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}