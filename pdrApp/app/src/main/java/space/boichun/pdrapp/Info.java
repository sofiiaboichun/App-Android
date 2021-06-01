package space.boichun.pdrapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pgr_info);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button btn_back = (Button) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(Info.this,MainActivity.class);
                    startActivity(intent);finish();
                }catch (Exception e) {
                }
            }
        });
    }

    public void onBackPressed(){
        try{
            Intent intent = new Intent(Info.this,MainActivity.class);
            startActivity(intent);finish();
        }catch (Exception e) {
        }
    }

}