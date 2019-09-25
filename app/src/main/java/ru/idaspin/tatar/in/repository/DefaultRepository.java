package ru.idaspin.tatar.in.repository;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import ru.arturvasilov.rxloader.RxUtils;
import ru.idaspin.tatar.in.api.ApiFactory;
import ru.idaspin.tatar.in.models.Word;
import rx.Observable;

class DefaultRepository implements TatarInRepository {

    @Override
    public Observable<List<Word>> getWords(String query) {
        return ApiFactory.getTatarInService()
                .getWords(query)
                .flatMap(words -> {
                    Realm.getDefaultInstance().executeTransaction(realm -> {
                        realm.insertOrUpdate(words);
                    });
                    return Observable.just(words);
                })
                .onErrorResumeNext(throwable -> {
                    Realm realm = Realm.getDefaultInstance();
                    RealmResults<Word> words = realm.where(Word.class).contains("rus", query).or().contains("tat", query).findAll();
                    return Observable.just(realm.copyFromRealm(words));
                })
                .compose(RxUtils.async());
    }

}
