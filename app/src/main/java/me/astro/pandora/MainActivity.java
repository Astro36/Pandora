package me.astro.pandora;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView) findViewById(R.id.launcher_icon_main);
        imageView.setImageBitmap(BitmapFactory.decodeStream(getResources().openRawResource(R.raw.img_pandora)));
    }

    public void toGithub(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/CosmosA/Pandora"));
        intent.setPackage("com.android.chrome");
        startActivity(intent);
    }
}
