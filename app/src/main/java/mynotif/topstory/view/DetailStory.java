package mynotif.topstory.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mynotif.topstory.R;
import mynotif.topstory.adapter.StoryAdapter;
import mynotif.topstory.api.APIClient;
import mynotif.topstory.api.APIInterface;
import mynotif.topstory.model.Comment;
import mynotif.topstory.model.Item;
import mynotif.topstory.model.ItemResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailStory extends AppCompatActivity {

    TextView titleStory;
    TextView authorStory;
    TextView dateStory;
    TextView descriptionStory;
    RecyclerView recyclerComments;

    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_story);

        final LinearLayoutManager layoutmanager = new LinearLayoutManager(DetailStory.this);
        layoutmanager.setOrientation(LinearLayoutManager.VERTICAL);

        titleStory = (TextView) findViewById(R.id.titleStory);
        authorStory = (TextView) findViewById(R.id.authorStroy);
        dateStory = (TextView) findViewById(R.id.dateStory);
        descriptionStory = (TextView) findViewById(R.id.decriptionStory);
        recyclerComments = (RecyclerView) findViewById(R.id.recycleComments);

        recyclerComments.setLayoutManager(layoutmanager);
        recyclerComments.setHasFixedSize(true);

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
            id = (String) bd.get("id");
        }
        getDetailStory();

    }

    private void getDetailStory() {
        final APIInterface apiInterface =
                APIClient.getClient(this).create(APIInterface.class);
        apiInterface.getStory(Integer.parseInt(id)).enqueue(new Callback<ItemResponse>() {
            @Override
            public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
                ItemResponse detail = response.body();
                String title = detail.getTitle();
                titleStory.setText(title);
                String author = detail.getBy();
                authorStory.setText("Author : " +author);
                String date = String.valueOf(detail.getTime());
                dateStory.setText("Date : "+date);

                List<Integer> kids = detail.getKids();
                for (int i=0 ; i<kids.size() ; i++){

                }
            }

            @Override
            public void onFailure(Call<ItemResponse> call, Throwable t) {

            }

        });
    }
}
