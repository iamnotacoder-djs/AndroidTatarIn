package ru.idaspin.tatar.in.repository;

import java.util.List;

import ru.idaspin.tatar.in.models.Word;
import rx.Observable;

public interface TatarInRepository {

    Observable<List<Word>> getWords(String query);

}
