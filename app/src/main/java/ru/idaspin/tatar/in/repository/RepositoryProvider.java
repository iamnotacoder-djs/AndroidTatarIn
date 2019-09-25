package ru.idaspin.tatar.in.repository;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

public final class RepositoryProvider {

    private static TatarInRepository sTatarInRepository;

    private RepositoryProvider() {
    }

    @MainThread
    public static void init() {
        sTatarInRepository = new DefaultRepository();
    }

    @NonNull
    public static TatarInRepository provideTatarInRepository() {
        if (sTatarInRepository == null) {
            sTatarInRepository = new DefaultRepository();
        }
        return sTatarInRepository;
    }
}
