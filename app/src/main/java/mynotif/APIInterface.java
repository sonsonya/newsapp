package mynotif.topstory.api;

import java.util.List;

import mynotif.topstory.model.Item;
import mynotif.topstory.model.ItemResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIInterface {

    @GET("topstories.json")
    Call<List<Integer>> getTopStories();
    @GET("item/{articleid}.json")
    Call<ItemResponse> getStory(@Path("articleid") int id);
    @GET("comment/{articleid}.json")
    Call<ItemResponse> getComment(@Path("articleid") int id);
}
