package me.astro.pandora;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class RefreshActivity extends Activity {
    private int appWidgetId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            appWidgetId = bundle.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }
    }

    public void refresh(View view) {
        if (appWidgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {
            Toast.makeText(this, "Refreshing...", Toast.LENGTH_LONG).show();
            try {
                new RefreshTask(this, AppWidgetManager.getInstance(this), appWidgetId).execute();
            } catch (Exception e) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }
}
