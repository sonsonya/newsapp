package mynotif.topstory.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mynotif.topstory.R;
import mynotif.topstory.adapter.StoryAdapter;
import mynotif.topstory.api.APIClient;
import mynotif.topstory.api.APIInterface;
import mynotif.topstory.model.Item;
import mynotif.topstory.model.ItemResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TopStory extends AppCompatActivity {

    private LinearLayout mainLayout;
    private TextView LastTitle;
    private RecyclerView recycleStories;

    ArrayList<Item> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_story);

        final LinearLayoutManager layoutmanager = new LinearLayoutManager(TopStory.this);
        layoutmanager.setOrientation(LinearLayoutManager.VERTICAL);

        mainLayout = (LinearLayout) findViewById(R.id.activity_top_story);
        LastTitle = (TextView) findViewById(R.id.LastTitle);
        recycleStories = (RecyclerView) findViewById(R.id.recycleStories);

        recycleStories.setLayoutManager(layoutmanager);
        recycleStories.setHasFixedSize(true);

        getTopStory();
    }

    private void getTopStory() {
        final APIInterface apiInterface =
                APIClient.getClient(this).create(APIInterface.class);
        apiInterface.getTopStories().enqueue(new Callback<List<Integer>>() {
            @Override
            public void onResponse(Call<List<Integer>> call, Response<List<Integer>> response) {
                List<Integer> topStories = response.body();
                for (int i = 0; i < 10; i++) {
                    apiInterface.getStory(topStories.get(i)).enqueue(new Callback<ItemResponse>() {
                        @Override
                        public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
                            String title= response.body().getTitle().toString();
                            String descendants = response.body().getDescendants().toString();
                            String score = response.body().getScore().toString();
                            list.add(new Item(title,descendants,score));
                            StoryAdapter adapter=new StoryAdapter(list);
                            recycleStories.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(Call<ItemResponse> call, Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<Integer>> call, Throwable t) {

            }
        });
    }


}
