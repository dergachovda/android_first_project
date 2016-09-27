package com.example.silverod.myapplication;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {
    private final String PARAM = "count";
    private TextView textView;
    private Counter count;
    private SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        count = Counter.getInstance();
        loadText();

        textView = (TextView) findViewById(R.id.textView);
        textView.setText(count.toString());

        Button btnInc = (Button) findViewById(R.id.btnInc);
        Button btnReset = (Button) findViewById(R.id.btnReset);
        Button btnExit = (Button) findViewById(R.id.btnExit);

        btnInc.setOnClickListener(this);
        btnReset.setOnClickListener(this);
        btnExit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnReset:
                count.setNull();
                break;
            case R.id.btnInc:
                count.setIncrement();
                break;
            case R.id.btnExit:
                finish();
//                System.exit(0);
                break;
        }
        textView.setText(count.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveText();
    }

    private void saveText() {
        sPref = getPreferences(MODE_PRIVATE);
        Editor ed = sPref.edit();
        ed.putString(PARAM, count.toString());
        ed.commit();
        Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show();
    }

    private void loadText() {
        sPref = getPreferences(MODE_PRIVATE);
        String savedText = sPref.getString(PARAM, "");
        if (!savedText.isEmpty()) {
            count.setValue(Integer.valueOf(savedText));
        }
        Toast.makeText(this, "Text loaded", Toast.LENGTH_SHORT).show();
    }
}
