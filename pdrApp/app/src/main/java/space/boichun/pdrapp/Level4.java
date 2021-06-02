package space.boichun.pdrapp;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Level4 extends AppCompatActivity {

    Dialog dialog;
    Dialog dialogEnd;
    public int numLeft;
    public int numRight;
    Array array = new Array();
    Random random = new Random();
    public int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common);

        TextView textLevels = findViewById(R.id.textLevels);
        textLevels.setText(R.string.level04);

        final ImageView img_left = (ImageView)findViewById(R.id.img_left);
        img_left.setClipToOutline(true);

        final ImageView img_right = (ImageView)findViewById(R.id.img_right);
        img_right.setClipToOutline(true);

        final TextView text_left = findViewById(R.id.text_left);
        final TextView text_right = findViewById(R.id.text_right);

        Button btn_back1 = (Button) findViewById(R.id.btn_back1);
        btn_back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(Level4.this,MainActivity.class);
                    startActivity(intent);finish();
                }catch (Exception e) {
                }
            }
        });

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.previewdialog2);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);

        TextView btnclose = (TextView)dialog.findViewById(R.id.btnclose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(Level4.this,TestLevels.class);
                    startActivity(intent);finish();
                }catch (Exception e) {
                }
                dialog.dismiss();
            }
        });

        Button btncont = (Button) dialog.findViewById(R.id.btncont);
        btncont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();

        //new dialog

        dialogEnd = new Dialog(this);
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogEnd.setContentView(R.layout.testenddialog);
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogEnd.setCancelable(false);

        TextView btnclose1 = (TextView)dialog.findViewById(R.id.btnclose);
        btnclose1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(Level4.this,TestLevels.class);
                    startActivity(intent);finish();
                }catch (Exception e) {
                }
                dialogEnd.dismiss();
            }
        });

        Button btncont1 = (Button) dialogEnd.findViewById(R.id.btncont);
        btncont1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(Level4.this,MainActivity.class);
                    startActivity(intent);finish();
                }catch (Exception e) {
                }
                dialogEnd.dismiss();
            }
        });




        //

        final int[] progress = {
                R.id.point1,
                R.id.point2,
                R.id.point3,
                R.id.point4,
                R.id.point5,
                R.id.point6,
                R.id.point7,
                R.id.point8,
                R.id.point9,
                R.id.point10,
        };

        final Animation a = AnimationUtils.loadAnimation(Level4.this,R.anim.alpha);

        numLeft = random.nextInt(10);
        img_left.setImageResource(array.images2[numLeft]);
        text_left.setText(array.texts2[numLeft]);

        numRight = random.nextInt(10);

        while (array.strong[numLeft]==array.strong[numRight]){
            numRight=random.nextInt(10);
        }

        img_right.setImageResource(array.images2[numRight]);
        text_right.setText(array.texts2[numRight]);

        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    img_right.setEnabled(false);
                    if (array.strong[numLeft]>array.strong[numRight]){
                        img_left.setImageResource(R.drawable.img_true);
                    }else{
                        img_left.setImageResource(R.drawable.img_false);
                    }
                }else if (event.getAction()==MotionEvent.ACTION_UP){

                    if (array.strong[numLeft]>array.strong[numRight]){

                        if (count<10){
                            count=count+1;
                        }

                        for (int i=0;i<10;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        for (int i=0;i<count;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }

                    }else{

                        if (count>0){
                            if (count==1){
                                count=0;
                            }else{
                                count=count-1;
                            }
                        }

                        for (int i=0;i<9;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        for (int i=0;i<count;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }

                    }

                    if (count==10){

                        dialogEnd.show();

                    }else{
                        numLeft = random.nextInt(10);
                        img_left.setImageResource(array.images2[numLeft]);
                        text_left.setText(array.texts2[numLeft]);

                        numRight = random.nextInt(10);

                        while (array.strong[numLeft]==array.strong[numRight]){
                            numRight=random.nextInt(10);
                        }

                        img_right.setImageResource(array.images2[numRight]);
                        text_right.setText(array.texts2[numRight]);

                        img_right.setEnabled(true);
                    }

                }

                return true;
            }
        });

        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    img_left.setEnabled(false);
                    if (array.strong[numLeft]<array.strong[numRight]){
                        img_right.setImageResource(R.drawable.img_true);
                    }else{
                        img_right.setImageResource(R.drawable.img_false);
                    }
                }else if (event.getAction()==MotionEvent.ACTION_UP){

                    if (array.strong[numLeft]<array.strong[numRight]){

                        if (count<10){
                            count=count+1;
                        }

                        for (int i=0;i<10;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        for (int i=0;i<count;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }

                    }else{

                        if (count>0){
                            if (count==1){
                                count=0;
                            }else{
                                count=count-1;
                            }
                        }

                        for (int i=0;i<9;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        for (int i=0;i<count;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }

                    }

                    if (count==10){

                        dialogEnd.show();

                    }else{
                        numLeft = random.nextInt(10);
                        img_left.setImageResource(array.images2[numLeft]);
                        text_left.setText(array.texts2[numLeft]);

                        numRight = random.nextInt(10);

                        while (array.strong[numLeft]==array.strong[numRight]){
                            numRight=random.nextInt(10);
                        }

                        img_right.setImageResource(array.images2[numRight]);
                        text_right.setText(array.texts2[numRight]);

                        img_left.setEnabled(true);
                    }

                }

                return true;
            }
        });


    }

    public void onBackPressed(){
        try{
            Intent intent = new Intent(Level4.this,TestLevels.class);
            startActivity(intent);finish();
        }catch (Exception e) {
        }
    }

}