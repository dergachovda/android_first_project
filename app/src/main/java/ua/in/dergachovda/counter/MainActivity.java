package ua.in.dergachovda.counter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {
    private TextView textView;
    private Counter count;
    private SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        count = Counter.getInstance();
        loadData();

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
                int inc = count.getValue() + 1;
                count.setValue(inc);
                break;
            case R.id.btnExit:
                finish();
                break;
        }
        textView.setText(count.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveData();
    }

    private void saveData() {
        sPref = getPreferences(MODE_PRIVATE);
        Editor ed = sPref.edit();
        ed.putString(Counter.VALUE_NAME, count.toString());
        ed.commit();
        //Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
    }

    private void loadData() {
        sPref = getPreferences(MODE_PRIVATE);
        String savedText = sPref.getString(Counter.VALUE_NAME, "");
        if (savedText.isEmpty()) {
            return;
        }
        count.setValue(Integer.valueOf(savedText));
        Toast.makeText(this, "Data loaded", Toast.LENGTH_SHORT).show();
    }
}
