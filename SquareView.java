package com.example.a15square;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;


/*
   Author: Masato Tsujii
   Date: 2/28/23
 */


public class SquareView extends TextView implements View.OnClickListener {
    int num = 10;
    /*
    sets the number value for the SquareView, updates the text, and changes the background
    color based on certain conditions.
     */
    public void setNum(int n) {
        num = n;
        this.setText(String.valueOf(num));
        if (num ==0) {
            this.setBackgroundColor(0x00000000);
            this.setTextColor(0x00000000);
        }
        else {
            this.setText(Color.BLACK);
            for (int i = 0; i < SquareModel.red.length;i++)
                if(num == SquareModel.red[i])
                    this.setBackgroundColor(Color.rgb(0xea,0x4b,0x35));
            for(int i=0;i<SquareModel.blue.length;i++)
                if(num==SquareModel.blue[i])
                    //this.setBackgroundResource(R.drawable.square_view_background_blue);
            this.setBackgroundColor(Color.rgb(0x21,0x7f,0xbc));
            for(int i=0;i<SquareModel.green.length;i++)
                if(num==SquareModel.green[i])
                    this.setBackgroundColor(Color.rgb(0x1b,0xae,0x5d));
            this.setBackgroundColor(Color.BLACK);
        }

    }
    public SquareView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //this.setTextColor(Color.BLACK);
        this.setTextSize(TypedValue.COMPLEX_UNIT_SP,60);
        this.setGravity(Gravity.CENTER);
        setOnClickListener(this);
        if(num==0)
            this.setBackgroundColor(Color.WHITE);
    }


    @Override
    public void onClick(View v) {

        SquareController.play(num);
    }
}

