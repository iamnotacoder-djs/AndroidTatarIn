package ru.idaspin.tatar.in;

import android.app.Application;

import com.google.firebase.analytics.FirebaseAnalytics;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.rx.RealmObservableFactory;
import ru.idaspin.tatar.in.api.ApiFactory;
import ru.idaspin.tatar.in.repository.RepositoryProvider;

public class AppDelegate extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //sContext = this;
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .rxFactory(new RealmObservableFactory())
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);
        FirebaseAnalytics.getInstance(this);
        ApiFactory.recreate();
        RepositoryProvider.init();
    }

}