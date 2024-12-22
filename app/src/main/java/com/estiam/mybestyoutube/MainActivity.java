package com.estiam.mybestyoutube;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.estiam.mybestyoutube.database.YoutubeVideoDatabase;
import com.estiam.mybestyoutube.pojos.YoutubeVideo;
import com.estiam.mybestyoutube.recyclerviewadapter.YoutubeVideoAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String KEY_YOUTUBE_VIDEO = "question";
    private final String TAG = "YoutubeVideoActivity";
    private List<YoutubeVideo> youtubeVideos= new ArrayList<>();
    private RecyclerView rvYoutubeVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //recupère le RecyclerView
        rvYoutubeVideo=findViewById(R.id.rvYoutubeVideo);

        //Créer le layout pour manager le RecyclerView
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getApplicationContext());
        rvYoutubeVideo.setHasFixedSize(true);

        //Lie le LayoutManager avec le RecyclerView
        rvYoutubeVideo.setLayoutManager(layoutManager);
    }

    //Accéder au menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //créer menu à partir de la ressource
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //effectuer une action en fonction de l'item sélectionner
        //test avec un switch l'id de l'item
        switch (item.getItemId()){
            case R.id.add_menu:
                //créer une intent pour ensuite lancer le cheat_activity
                Intent intent=new Intent(getApplicationContext(), AddYoutubeVideoActivity.class);


                //démarrer l'activité
                startActivity(intent);
                return true;

            default:  return super.onOptionsItemSelected(item);

        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
        TodoAsyncTask todoAsyncTask=new TodoAsyncTask();
        todoAsyncTask.execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart() called");
    }

    public class TodoAsyncTask extends AsyncTask<Nullable, Nullable, List<YoutubeVideo>> {

        @Override
        protected List<YoutubeVideo> doInBackground(Nullable... nullables) {
            youtubeVideos= YoutubeVideoDatabase.getDb(getApplicationContext()).youtubeVideoDAO().list();
            return youtubeVideos;
        }

        @Override
        protected void onPostExecute(List<YoutubeVideo> youtubeVideos) {
            super.onPostExecute(youtubeVideos);
            YoutubeVideoAdapter youtubeVideoAdapter= new YoutubeVideoAdapter(youtubeVideos, new YoutubeVideoAdapter.OnItemClickListener() {
                //Acceder à la page des détails
                @Override
                public void onItemClick(YoutubeVideo item) {
                    //créer une intent pour ensuite lancer le cheat_activity
                    Intent intent=new Intent(getApplicationContext(), YoutubeVideoDetail.class);


                    //ajouter dans le intent des données
                    intent.putExtra(KEY_YOUTUBE_VIDEO,item);

                    //démarrer l'activité
                    startActivity(intent);
                }

                //Supprimer un item
                @Override
                public void onItemClickDelete(YoutubeVideo item) {
                    // Create the object of AlertDialog Builder class
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Confirmer la suppression ?");
                    builder.setTitle("Alert !");
                    builder.setCancelable(false);
                    //bouton pour confirmer
                    builder.setPositiveButton("Oui", (DialogInterface.OnClickListener) (dialog, which) -> {
                        //suppression de l'item
                        YoutubeVideoDatabase.getDb(getApplicationContext()).youtubeVideoDAO().delete(item);
                        recreate();
                    });
                    // bouton pour annuler
                    builder.setNegativeButton("Non", (DialogInterface.OnClickListener) (dialog, which) -> {
                        dialog.cancel();
                    });
                    // Creation de la pop-up d'Alert dialog
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            });
            rvYoutubeVideo.setAdapter(youtubeVideoAdapter);
        }
    }

}