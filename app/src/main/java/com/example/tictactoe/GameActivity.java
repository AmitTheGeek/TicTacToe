package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private String playerOneName;
    private String playerTwoName;
    int numberOfGrid = 0;
    private GridLayout gridLayout;
    private static final String PLAYER_ONE_SYMBOL = "X";
    private static final String PLAYER_TWO_SYMBOL = "O";
    private int[][] grid;
    int numberOfSteps = 0;
    int maxNumberOfSteps;
    private boolean playerOneChance = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gridLayout = findViewById(R.id.grid);
        loadIntent();
    }

    private void loadIntent() {
        Intent intent = getIntent();
        playerOneName = intent.getExtras().getString(MainActivity.PLAYER_ONE_NAME);
        playerTwoName = intent.getExtras().getString(MainActivity.PLAYER_TWO_NAME);
        numberOfGrid = intent.getIntExtra(MainActivity.NUM_OF_GRID, 3);
        grid = new int[numberOfGrid][numberOfGrid];
        maxNumberOfSteps = numberOfGrid * numberOfGrid;
        populateGrid();
    }

    private void populateGrid() {
        gridLayout.setColumnCount(numberOfGrid);
        gridLayout.setRowCount(numberOfGrid);
        gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);

        int height = gridLayout.getHeight() / numberOfGrid;
        int width = gridLayout.getWidth() / numberOfGrid;

        for (int i = 0; i < numberOfGrid; i++) {
            for (int j = 0; j < numberOfGrid; j++) {
                grid[i][j] = -1;
                Button button = new Button(this);
                button.setWidth(width);
                button.setWidth(height);
                button.setText("");
                button.setTag(i + "_" + j);
                button.setGravity(Gravity.CENTER);
                button.setOnClickListener(this);
                gridLayout.addView(button);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getTag() != null) {
            Button btn = (Button) v;
            String[] split = v.getTag().toString().split("_");
            int row = Integer.parseInt(split[0]);
            int col = Integer.parseInt(split[1]);

            if (playerOneChance) {
                grid[row][col] = 1;
                playerOneChance = false;
                btn.setText(PLAYER_ONE_SYMBOL);
            } else {
                grid[row][col] = 0;
                playerOneChance = true;
                btn.setText(PLAYER_TWO_SYMBOL);
            }

            btn.setEnabled(false);
            numberOfSteps++;
            boolean playerWon = checkWinner(row, col);

            if (!playerWon && (numberOfSteps >= maxNumberOfSteps)) {
                Toast.makeText(this, "DRAW !!!", Toast.LENGTH_SHORT).show();
                reset();
            } else if(!(!playerWon || playerOneChance)){
                Toast.makeText(this, playerOneName  + " won", Toast.LENGTH_SHORT).show();
                reset();
            } else if(playerWon){
                Toast.makeText(this,  playerTwoName  + " won", Toast.LENGTH_SHORT).show();
                reset();
            }
        }
    }

    private boolean checkWinner(int row, int column) {
        int temp = grid[row][column];
        for (int i = 0; i < numberOfGrid; i++) {
            if (temp != grid[row][i]) {
                break;
            } else if (i == numberOfGrid - 1) {
                return true;
            }
        }

        for (int i = 0; i < numberOfGrid; i++) {
            if (temp != grid[i][column]) {
                break;
            } else if (i == numberOfGrid - 1) {
                return true;
            }
        }

        for (int i = 0; i < numberOfGrid; i++) {
            if (grid[i][i] != temp) {
            } else if (i == numberOfGrid - 1) {
                return true;
            }
        }

        for (int i = 0; i < numberOfGrid; i++) {
            if (grid[numberOfGrid - 1 - i][i] != temp) {
                break;
            } else if (i == numberOfGrid - 1) {
                return true;
            }
        }
        return false;
    }

    private void reset(){
        populateGrid();
    }
}

