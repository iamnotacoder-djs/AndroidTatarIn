package ru.idaspin.tatar.in.screen;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.droidbyme.dialoglib.AnimUtils;
import com.droidbyme.dialoglib.DroidDialog;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.idaspin.tatar.in.BuildConfig;
import ru.idaspin.tatar.in.R;
import ru.idaspin.tatar.in.models.Word;
import ru.idaspin.tatar.in.widgets.recycler.BaseRecyclerAdapter;
import ru.idaspin.tatar.in.widgets.recycler.EmptyRecyclerView;
import ru.idaspin.tatar.in.widgets.recycler.WordAdapter;

public class MainActivity extends AppCompatActivity implements MainView, TextWatcher, BaseRecyclerAdapter.OnItemClickListener<Word> {

    private static final String INSTANCE_STATE_SEARCH_BAR = "instance_state_search_bar";
    static final String SHARED_FIRST = "shared_firts";

    @BindView(R.id.main_search_bar) MaterialSearchBar mSearchBar;
    @BindView(R.id.main_recycler) EmptyRecyclerView mRecyclerView;
    @BindView(R.id.main_text_result) TextView mTitle;

    private MainPresenter mPresenter;
    private DroidDialog mDialog;
    private WordAdapter mRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.setDebug(BuildConfig.DEBUG);
        ButterKnife.bind(this);

        mPresenter = new MainPresenter(this);
        mPresenter.init(getPreferences(MODE_PRIVATE).getBoolean(SHARED_FIRST, true));
    }

    @Override
    protected void onDestroy() {
        hideDialog();
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putString(INSTANCE_STATE_SEARCH_BAR, mSearchBar.getText());
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        mSearchBar.setText(savedInstanceState.getString(INSTANCE_STATE_SEARCH_BAR));
        mPresenter.onRestore(savedInstanceState.getString(INSTANCE_STATE_SEARCH_BAR));
        super.onRestoreInstanceState(savedInstanceState);
    }

    @OnClick(R.id.bottom_bar_label_rus)
    public void onRusLabelClick(){
        mPresenter.onRusLabelClick();
    }

    @OnClick(R.id.bottom_bar_label_tat)
    public void onTatLabelClick(){
        mPresenter.onTatLabelClick();
    }

    @OnClick(R.id.main_button_translate)
    public void onButtonTranslateClick(){
        mPresenter.onButtonTranslateClick(mSearchBar.getText());
    }

    @OnClick(R.id.main_text_result)
    public void onMainTextClick(){
        mPresenter.onMainTextClick();
    }

    @Override
    public void setupViews() {
        mSearchBar.addTextChangeListener(this);

        mRecyclerAdapter = new WordAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerAdapter.attachToRecyclerView(mRecyclerView);
        mRecyclerAdapter.setOnItemClickListener(this);
    }

    @Override
    public void showDialog(String title, String text, int id) {
        mDialog = new DroidDialog.Builder(this)
                        .title(title)
                        .content(text)
                        .positiveButton("OK", droidDialog -> mPresenter.onDialogOkClick(id))
                        .animation(AnimUtils.AnimFadeInOut)
                        .color(ContextCompat.getColor(this, R.color.indigo), ContextCompat.getColor(this, R.color.white),
                                ContextCompat.getColor(this, R.color.dark_indigo))
                        .show();
    }

    @Override
    public void hideDialog() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void setPreference(String sharedFirst, boolean b) {
        getPreferences(MODE_ENABLE_WRITE_AHEAD_LOGGING).edit().putBoolean(SHARED_FIRST, false).apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mPresenter.onOptionsItemSelected(item.getItemId(), super.onOptionsItemSelected(item));
    }


    @Override
    public void fillViews(String query, List<Word> words) {
        if (words.get(0).getRus().contains(query)) {
            String t = words.get(0).getTat();
            t = Character.toUpperCase(t.charAt(0)) + t.substring(1);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                mTitle.setText(Html.fromHtml(t, 0));
            } else {
                mTitle.setText(Html.fromHtml(t));
            }
        } else {
            String t = words.get(0).getRus();
            t = Character.toUpperCase(t.charAt(0)) + t.substring(1);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                mTitle.setText(Html.fromHtml(t, 0));
            } else {
                mTitle.setText(Html.fromHtml(t));
            }
        }
        words.remove(0);
        mRecyclerAdapter.putQuery(query);
        mRecyclerAdapter.changeDataSet(words);
    }

    @Override
    public void showHelper() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(getResources().getString(R.string.help_url)));
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        mPresenter.beforeTextChanged(s, start, count, after);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mPresenter.onTextChanged(s, start, before, count);
    }

    @Override
    public void afterTextChanged(Editable s) {
        mPresenter.afterTextChanged(s);
    }

    @Override
    public void onItemClick(@NonNull Word item) {
        mPresenter.onItemClick(item);
    }
}
