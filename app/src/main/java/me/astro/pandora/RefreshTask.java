package me.astro.pandora;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.RemoteViews;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

class RefreshTask extends AsyncTask<Void, Void, Void> {
    public static String[] meanings = new String[5];
    public static String[] words = new String[5];
    private int appWidgetId;
    private AppWidgetManager appWidgetManager;
    private Context context;
    private RemoteViews views;

    public RefreshTask(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        this.views = new RemoteViews(context.getPackageName(), R.layout.main_widget);
        this.context = context;
        this.appWidgetManager = appWidgetManager;
        this.appWidgetId = appWidgetId;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            String[] words1 = new String[5];
            String[] meanings1 = new String[5];
            Document document = Jsoup.connect("http://m.wordbook.naver.com/endic/today/words.nhn").get();
            Elements elements = document.select(".lst_li2");

            int i = 0;
            for (Element element : elements.select(".txt_ct")) {
                words1[i] = element.select(".words").text();
                i++;
            }

            i = 0;
            for (Element element : elements.select(".txt_ct2")) {
                if (meanings1[i] == null) {
                    meanings1[i] = "";
                }

                if (element.hasClass("txt_col")) {
                    meanings1[i] += element.select(".txt_col").text();
                } else {
                    meanings1[i] += element.text();
                }
                i++;
            }
            words = words1;
            meanings = meanings1;

            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.list_view);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
