package com.example.a15square;
import android.graphics.Color;

import java.util.Random;


/*
   Author: Masato Tsujii
   Date: 2/28/23
 */


public class SquareController {
    static SquareView[][] button = new SquareView[4][4];

    public static void setGame(SquareView[][] button1) {
        button = button1;
    }

    /*
    This method iterates over the button 2D array and sets the number displayed on each
    SquareView to the corresponding number in the squares 2D array of the SquareModel class.
     */
    public static void show() {
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
                button[i][j].setNum(SquareModel.squares[i][j]);
            }
    }

    /*
     moves the clicked SquareView to the adjacent position if it is empty.
      If the move is successful, the show() method is called to update the numbers
       displayed on the SquareViews.
     */
    public static void play(int num) {
        if (SquareModel.scramble) {
            SquareModel.scramble = false;
            SquareModel.start = true;
        }
        int row = 0;
        int col = 0;
        int m;
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                if (SquareModel.squares[i][j] == num) {
                    row = i;
                    col = j;
                }


    if(row -1>=0 && SquareModel.squares[row -1][col]==0) {
        SquareModel.squares[row - 1][col] = SquareModel.squares[row][col];
        SquareModel.squares[row][col] = 0;
        SquareModel.count++;
    }
     else if(row+1<4&&SquareModel.squares[row+1][col]==0){
        SquareModel.squares[row + 1][col] = SquareModel.squares[row][col];
        SquareModel.squares[row][col] = 0;
        SquareModel.count++;
    }
     else if(col-1>=0&&SquareModel.squares[row][col-1]==0) {
        SquareModel.squares[row][col - 1] = SquareModel.squares[row][col];
        SquareModel.squares[row][col] = 0;
        SquareModel.count++;
    }
     else if(col+1<4&&SquareModel.squares[row][col+1]==0) {
        SquareModel.squares[row][col + 1] = SquareModel.squares[row][col];
        SquareModel.squares[row][col] = 0;
        SquareModel.count++;
    }
     else
            return;
        show();
        //this wil change all the color to green when correct
        if(SquareController.win())
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    button[i][j].setBackgroundColor(Color.GREEN);
                    button[i][j].setTextColor(Color.BLACK);
                }
            }
            SquareModel.start = false;
    }

    //checks to see if the number are in te correct position
    public static boolean win(){

        if(SquareModel.squares[0][0]!=1)return false;
        if(SquareModel.squares[0][1]!=2)return false;
        if(SquareModel.squares[0][2]!=3)return false;
        if(SquareModel.squares[0][3]!=4)return false;
        if(SquareModel.squares[1][0]!=5)return false;
        if(SquareModel.squares[1][1]!=6)return false;
        if(SquareModel.squares[1][2]!=7)return false;
        if(SquareModel.squares[1][3]!=8)return false;
        if(SquareModel.squares[2][0]!=9)return false;
        if(SquareModel.squares[2][1]!=10)return false;
        if(SquareModel.squares[2][2]!=11)return false;
        if(SquareModel.squares[2][3]!=12)return false;
        if(SquareModel.squares[3][0]!=13)return false;
        if(SquareModel.squares[3][1]!=14)return false;
        if(SquareModel.squares[3][2]!=15)return false;
        if(SquareModel.squares[3][3]!=0)return false;
        return true;
    }



     //scrambles the puzzle
     public static void scramble () {
         int [] num = new int [16];
         Random R = new Random();

         for(int i = 0; i < 16; i++) {
             num [i] = i;
         }
         for (int i = 15;i > 0; i ++) {
             int ran = R.nextInt(i);
             int m = num [ran];
             num[ran] = num [i];
             num [i] = m;
         }

         //calculates the reverse num
         int sum = 0;
         for (int i =0;i<16;i++) {
             if(num[i]==0){
                 sum+=i/4+(i+1)%4;
                 continue;
             }
             for(int j=0;j<16-i;j++)
                 if(num[j+i]<num[i])
                     sum++;
         }
         if(sum%2==1){
             int r1=R.nextInt(16);
             if(num[r1]==0)
                 if(++r1>15)
                     r1=0;
             int r2=R.nextInt(16);
             while (r1==r2 || num[r2]==0) {
                 if (++r1 > 15)
                     r2 = 0;
             }
             int m;
             m = num[r1];
             num[r1] = num[r2];
             num[r2] = m;
         }
         for(int i=0;i<16;i++)
             SquareModel.squares[i/4][i%4]=num[i];
         show();
         SquareModel.start=false;
         SquareModel.count=0;
         SquareModel.scramble=true;
     }

}


