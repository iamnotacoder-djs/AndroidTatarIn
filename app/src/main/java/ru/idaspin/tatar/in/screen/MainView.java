package ru.idaspin.tatar.in.screen;

import java.util.List;

import ru.idaspin.tatar.in.models.Word;

/**
 * Created by idaspin.
 * Date: 9/9/2017
 * Time: 6:02 AM
 */
interface MainView {

    void setupViews();

    void showDialog(String title, String text, int id);

    void hideDialog();

    void setPreference(String sharedFirst, boolean b);

    void fillViews(String query, List<Word> words);

    void showHelper();
}
