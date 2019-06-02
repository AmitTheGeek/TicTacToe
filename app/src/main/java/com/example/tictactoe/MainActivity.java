package com.example.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String playerOneName;
    private String playerTwoName;
    int numberOfGrid = 0;
    public static String PLAYER_ONE_NAME = "player_one_name";
    public static String PLAYER_TWO_NAME = "player_two_name";
    public static String NUM_OF_GRID = "number_of_grid";


    private EditText player1;
    private EditText player2;
    private EditText grid;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    private void init(){
        player1 = findViewById(R.id.player1);
        player2 = findViewById(R.id.player2);
        grid = findViewById(R.id.grid);
        submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerOneName = player1.getText().toString();
                playerTwoName = player2.getText().toString();
                try {
                    numberOfGrid = Integer.parseInt(grid.getText().toString());
                } catch (NumberFormatException e){
                    numberOfGrid = 0;
                }
//
//                showToast(playerOneName, player1);
//                showToast(playerTwoName, player2);
//                showToast(String.valueOf(numberOfGrid), grid);

                if(!playerOneName.isEmpty() && !playerTwoName.isEmpty() && numberOfGrid != 0){
                    Intent intent = new Intent(MainActivity.this, GameActivity.class);
                    intent.putExtra(MainActivity.PLAYER_ONE_NAME, playerOneName);
                    intent.putExtra(MainActivity.PLAYER_TWO_NAME, playerTwoName);
                    intent.putExtra(MainActivity.NUM_OF_GRID, numberOfGrid);
                    startActivity(intent);
                }
            }
        });
    }

    private void showToast(String text, EditText view){
        if(text == null || text == "0") {
            Toast.makeText(this, "Please enter " + view.getHint(), Toast.LENGTH_SHORT).show();
        }
    }
}
