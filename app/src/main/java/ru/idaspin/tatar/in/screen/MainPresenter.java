package ru.idaspin.tatar.in.screen;

import android.text.Editable;

import ru.idaspin.tatar.in.R;
import ru.idaspin.tatar.in.models.Word;
import ru.idaspin.tatar.in.repository.RepositoryProvider;
import ru.idaspin.tatar.in.utils.TatStringFormatterUtil;

/**
 * Created by idaspin.
 * Date: 9/9/2017
 * Time: 6:04 AM
 */
class MainPresenter {

    private final MainView mView;

    private String mQuery = "";
    private Word mWord;

    public MainPresenter(MainView mainActivity) {
        mView = mainActivity;
    }

    void init(boolean aBoolean) {
        mView.setupViews();
        if (aBoolean) {
            mView.setPreference(MainActivity.SHARED_FIRST, false);
            mView.showDialog("Русско-Татарский", "Чтобы перевести слово на татарский язык просто введите его в поле поиска.", 1);
        }
    }

    void onRusLabelClick() {
        mView.showDialog("Русско-Татарский", "Чтобы перевести слово на татарский язык просто введите его в поле поиска.", 0);
    }

    void onTatLabelClick() {
        mView.showDialog("Татарско-Русский", "Для перевода слова на русский ничего переключать не надо. Просто введите запрашиваемое слово по поле поиска.\r\nДля ввода татарских букв вы можете воспользоваться заменой:\r\nƟ - 1\r\nƏ - 2\r\nҖ - 3\r\nҢ - 4\r\nҮ - 5\r\nҺ - 6", 0);
    }

    void onButtonTranslateClick(String text) {
        this.mQuery = text;
        makeRequest();
    }

    void onRestore(String string) {
        this.mQuery = string;
        makeRequest();
    }

    private void makeRequest() {
        if (!mQuery.equals("")) {
            RepositoryProvider.provideTatarInRepository()
                    .getWords(TatStringFormatterUtil.toHtml(mQuery))
                    .doOnError(throwable -> mView.showDialog("Ошибка", throwable.toString(), 0))
                    .subscribe(words -> {
                        if (!words.isEmpty()) {
                            mWord = words.get(0);
                            mView.fillViews(mQuery, words);
                        } else {
                            mView.showDialog("Ошибка", "Ничего не найдено", 0);
                        }
                    });
        }
    }

    void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    void afterTextChanged(Editable s) {
        mQuery = s.toString();
        makeRequest();
    }

    void onDialogOkClick(int id) {
        mView.hideDialog();
        if (id == 1) {
            mView.showDialog("Татарско-Русский", "Для перевода слова на русский ничего переключать не надо. Просто введите запрашиваемое слово в поле поиска.\r\nДля ввода татарских букв вы можете воспользоваться заменой:\r\nƟ - 1\r\nƏ - 2\r\nҖ - 3\r\nҢ - 4\r\nҮ - 5\r\nҺ - 6", 0);
        }
    }

    void onResume() {
        makeRequest();
    }

    void onItemClick(Word item) {
        mView.showDialog("Ваш запрос: " + mQuery, "Русский: " + item.getRus() + "\r\nЧасть речи: " + item.getType() + "\r\nТатарский: " + TatStringFormatterUtil.fromHtml(item.getTat()), 0);
    }

    void onMainTextClick() {
        if (mWord != null) {
            mView.showDialog("Ваш запрос: " + mQuery, "Русский: " + mWord.getRus() + "\r\nЧасть речи: " + mWord.getType() + "\r\nТатарский: " + TatStringFormatterUtil.fromHtml(mWord.getTat()), 0);
        }
    }

    boolean onOptionsItemSelected(int itemId, boolean b) {
        if (itemId == R.id.action_help) {
            mView.showHelper();
            return true;
        } else {
            return b;
        }
    }
}
