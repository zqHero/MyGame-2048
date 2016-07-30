package com.example.mygame2048;

import android.app.Activity;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends Activity {

	private LinearLayout root=null;
	private TextView tvScore=null;
	private TextView tvBestScore=null;
	private static MainActivity mainActivity=null;
	private AnimLayer animlayer=null;
	private Button btnRestartGame=null;
	private GameView gameView=null;
	
	private int score=0;
	
	public static final String SP_KEY_BEST_SCORE="bestScore";
	
	public MainActivity(){
		mainActivity=this;
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        root=(LinearLayout) findViewById(R.id.container);
        root.setBackgroundColor(0xfffaf8ef);
        
        tvScore=(TextView) findViewById(R.id.tvScore);
        tvBestScore=(TextView) findViewById(R.id.tvBestScore);
        
        gameView=(GameView) findViewById(R.id.gameView);
        
        animlayer=(AnimLayer) findViewById(R.id.animlayer);
        
        btnRestartGame=(Button) findViewById(R.id.btnNewGame);
        btnRestartGame.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				gameView.startGame();
			}
		});
    }
    
    public static MainActivity getMainActivity() {
		return mainActivity;
	}
    
    public AnimLayer getAnimLayer(){
    	return animlayer;
    }
    
    public void clearScore(){
    	score=0;
    	showScore();
    }
    
    public void showScore(){
    	tvScore.setText(score+"");
    }
    
    public void addScore(int s){
    	score+=s;
    	showScore();
    	
    	int maxScore=Math.max(score, getBestScore());
    	saveBestScore(maxScore);
    	showBestScore(maxScore);
    }
    
    public void saveBestScore(int s){
    	Editor e=getPreferences(MODE_PRIVATE).edit();
    	e.putInt(SP_KEY_BEST_SCORE, s);
    	e.commit();
    }
    
    public int getBestScore(){
    	return getPreferences(MODE_PRIVATE).getInt(SP_KEY_BEST_SCORE, 0);
    }
    
    public void showBestScore(int s){
    	tvBestScore.setText(s+"");
    }
}
