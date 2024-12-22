package com.estiam.mybestyoutube;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.estiam.mybestyoutube.database.YoutubeVideoDatabase;
import com.estiam.mybestyoutube.pojos.YoutubeVideo;

public class AddYoutubeVideoActivity extends AppCompatActivity {

    private Button btnAdd;
    private Button btnCancel;

    private EditText edtTitre;
    private EditText edtDescription;
    private EditText edtUrl;

    private Spinner sCategorie;

    private final YoutubeVideo youtubeVideo = new YoutubeVideo();

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_youtube_video);

        //afficher le back dans l'actioBar
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDefaultDisplayHomeAsUpEnabled(true);

        //recupère les éléments
        btnAdd=findViewById(R.id.btnAdd);
        btnCancel=findViewById(R.id.btnCancel);
        sCategorie=findViewById(R.id.spinner);
        edtTitre=findViewById(R.id.edtTitre);
        edtDescription=findViewById(R.id.edtDescription);
        edtUrl=findViewById(R.id.edtUrl);

        //créer un ArrayAdapter
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this,
                R.array.categorie_array, android.R.layout.simple_spinner_item);

        //specifier le layout a utiliser quand le choix de la liste apparait
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Appliquer l'Adapter au spinner
        sCategorie.setAdapter(adapter);

        //Enregistrer les données
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtTitre.getText().length()==0 || edtDescription.getText().length()==0 || edtUrl.getText().length()==0){
                    Toast.makeText(AddYoutubeVideoActivity.this, "Tous les champs doivent être remplis", Toast.LENGTH_SHORT).show();
                }
                else {
                    String youtubeVideoTitre=edtTitre.getText().toString();
                    String youtubeVideoDescription=edtDescription.getText().toString();
                    String youtubeVideoUrl=edtUrl.getText().toString();
                    String youtubeVideocategorie= sCategorie.getSelectedItem().toString();
                    youtubeVideo.setTitre(youtubeVideoTitre);
                    youtubeVideo.setDescription(youtubeVideoDescription);
                    youtubeVideo.setUrl(youtubeVideoUrl);
                    youtubeVideo.setCategorie(youtubeVideocategorie);
                    youtubeVideo.setFavori(0);
                    YoutubeVideoDatabase.getDb(getApplicationContext()).youtubeVideoDAO().add(youtubeVideo);
                    finish();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}