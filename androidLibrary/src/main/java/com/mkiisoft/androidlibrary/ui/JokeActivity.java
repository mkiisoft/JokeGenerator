package com.mkiisoft.androidlibrary.ui;

import android.Manifest;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mkiisoft.androidlibrary.R;
import com.mkiisoft.androidlibrary.adapter.ColorAdapter;
import com.mkiisoft.androidlibrary.model.ColorModel;
import com.mkiisoft.androidlibrary.utils.Colors;

import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;

import static com.mkiisoft.androidlibrary.utils.Utils.animateColor;
import static com.mkiisoft.androidlibrary.utils.Utils.shareBitmap;

/*
 * Created by mzorilla on 5/22/18.
 */

public class JokeActivity extends AppCompatActivity implements ColorAdapter.ClickListener, EasyPermissions.PermissionCallbacks {

    private static final String JOKE_EXTRA = "joke";
    private static final String RECYCLER_POSITION = "recycler_position";

    private static final int REQUEST_WRITE_PERMISSION = 6688;
    String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private static ArrayList<ColorModel> colors = new ArrayList<>();

    private int recyclerPosition = -1;

    static {
        colors.add(new ColorModel(Color.parseColor(Colors.PINK), Color.WHITE));
        colors.add(new ColorModel(Color.parseColor(Colors.LIME), Color.BLACK));
        colors.add(new ColorModel(Color.parseColor(Colors.BLUE), Color.WHITE));
        colors.add(new ColorModel(Color.parseColor(Colors.ORANGE), Color.WHITE));
        colors.add(new ColorModel(Color.parseColor(Colors.TIEL), Color.WHITE));
        colors.add(new ColorModel(Color.parseColor(Colors.AMBER), Color.BLACK));
        colors.add(new ColorModel(Color.parseColor(Colors.CYAN), Color.WHITE));
        colors.add(new ColorModel(Color.parseColor(Colors.YELLOW), Color.BLACK));
        colors.add(new ColorModel(Color.parseColor(Colors.PURPLE), Color.WHITE));
    }

    private View rootView;
    private View backView;
    private View shareView;

    private TextView jokeText;
    private RecyclerView recyclerColor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        Bundle bundle = getIntent().getExtras();

        if (bundle != null && bundle.getString(JOKE_EXTRA) != null) {
            jokeText = findViewById(R.id.show_joke);
            jokeText.setText(bundle.getString(JOKE_EXTRA));

            rootView = findViewById(R.id.root_view);
            backView = findViewById(R.id.background_square);

            shareView = findViewById(R.id.share);
            shareView.setEnabled(false);

            recyclerColor = findViewById(R.id.recycler_color);
            recyclerColor.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            recyclerColor.setAdapter(new ColorAdapter().setColorList(colors).addListener(this));

            EasyPermissions.requestPermissions(new PermissionRequest.Builder(this, REQUEST_WRITE_PERMISSION, perms)
                            .setRationale(R.string.share_joke)
                            .setPositiveButtonText(R.string.permission_ok)
                            .setNegativeButtonText(R.string.permission_cancel)
                            .build());

            shareView.setOnClickListener(view -> shareBitmap(backView));
        } else {
            finish();
        }
    }

    @Override
    public void onItemClick(ColorModel color, int position) {
        animateColor(rootView, color.getBgColor());
        animateColor(backView, color.getBgColor());
        jokeText.setTextColor(color.getTextColor());

        recyclerPosition = position;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(RECYCLER_POSITION, recyclerPosition);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestoreInstanceState(final Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null && recyclerColor != null) {
            recyclerColor.post(() -> {
                int position = savedInstanceState.getInt(RECYCLER_POSITION);
                if (position >= 0) {
                    recyclerColor.scrollToPosition(position);
                    recyclerColor.postDelayed(() -> {
                        ColorAdapter.ColorHolder colorHolder = ((ColorAdapter.ColorHolder) recyclerColor
                                .findViewHolderForAdapterPosition(position));
                        if (colorHolder != null) {
                            colorHolder.colorView.performClick();
                        }
                    }, 100);
                }
            });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        shareView.setEnabled(true);
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        shareView.setEnabled(false);
    }
}