package me.astro.pandora;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private static final int mCount = 5;
    private Context mContext;

    public StackRemoteViewsFactory(Context context, Intent intent) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return mCount;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.list_item);
        Log.d("Pandora Debug", RefreshTask.words[position]);
        Log.d("Pandora Debug", RefreshTask.meanings[position]);
        remoteViews.setTextViewText(R.id.list_item_word, RefreshTask.words[position]);
        remoteViews.setTextViewText(R.id.list_item_meaning, RefreshTask.meanings[position]);
        return remoteViews;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }
}

public class WidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new StackRemoteViewsFactory(getApplicationContext(), intent);
    }
}