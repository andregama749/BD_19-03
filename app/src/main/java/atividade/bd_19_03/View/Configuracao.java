package atividade.bd_19_03.View;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Configuracao extends Application {

    @Override
    public void onCreate(){
        super.onCreate();

        Realm.init(getApplicationContext());
        RealmConfiguration.Builder builder = new RealmConfiguration.Builder();
        builder.name("oficina.realm");
        builder.schemaVersion(0);
        builder.deleteRealmIfMigrationNeeded();
        RealmConfiguration realmConfiguration = builder.build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

}
