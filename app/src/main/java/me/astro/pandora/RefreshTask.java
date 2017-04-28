package me.astro.pandora;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.RemoteViews;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

class RefreshTask extends AsyncTask<Void, Void, Void> {
    public static String[] words = new String[5];
    public static String[] meaning = new String[5];
    private RemoteViews views;
    private Context context;
    private AppWidgetManager appWidgetManager;
    private int appWidgetId;

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
            String[] meaning1 = new String[5];
            Document document = Jsoup.connect("http://m.wordbook.naver.com/endic/today/words.nhn").get();
            Elements elements = document.select(".lst_li2");

            int i = 0;
            for (Element element : elements.select(".txt_ct")) {
                words1[i] = element.select(".words").text();
                i++;
            }

            i = 0;
            for (Element element : elements.select(".txt_ct2")) {
                if (meaning1[i] == null) {
                    meaning1[i] = "";
                }

                if (element.hasClass("txt_col")) {
                    Log.d("aaa", element.select(".txt_col").text());
                    meaning1[i] += element.select(".txt_col").text();
                } else {
                    Log.d("aaa", element.text());
                    meaning1[i] += element.text();
                }
                i++;
            }
            words = words1;
            meaning = meaning1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*protected void onPostExecute() {
        try {
            if (words != null) {
                for (int i = 0; i < 5; i++) {
                    int wordViewId = R.id.word_0;
                    int wordDescViewId = R.id.word_0_desc;

                    switch (i) {
                        case 1:
                            wordViewId = R.id.word_1;
                            wordDescViewId = R.id.word_1_desc;
                            break;
                        case 2:
                            wordViewId = R.id.word_2;
                            wordDescViewId = R.id.word_2_desc;
                            break;
                        case 3:
                            wordViewId = R.id.word_3;
                            wordDescViewId = R.id.word_3_desc;
                            break;
                        case 4:
                            wordViewId = R.id.word_4;
                            wordDescViewId = R.id.word_4_desc;
                            break;
                    }

                    views.setTextViewText(wordViewId, words[i]);
                    views.setTextViewText(wordDescViewId, words[i + 5]);
                }
                appWidgetManager.updateAppWidget(appWidgetId, views);
            }
        } catch (Exception e) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
        }
    }*/
}
