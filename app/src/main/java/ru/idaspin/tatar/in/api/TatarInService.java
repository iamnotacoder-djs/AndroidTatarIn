package ru.idaspin.tatar.in.api;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.idaspin.tatar.in.models.Word;
import rx.Observable;

public interface TatarInService {

    @GET("api/tatar.in/1.0")
    Observable<List<Word>> getWords(@Query("query") String query);

}
