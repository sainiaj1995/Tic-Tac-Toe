package ajay.tictoethree;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int activeplayer=0;
    int[] unplayed={2,2,2,2,2,2,2,2,2};
    int[][] winningPositions={{0,1,2},{3,4,5},{6 ,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameisActive=true;

    public void dropIn(View view){
        ImageView counter =(ImageView)view;

        int tappedCounter =Integer.parseInt(counter.getTag().toString());
        System.out.println(tappedCounter);
        if(unplayed[tappedCounter]==2&& gameisActive) {
            unplayed[tappedCounter]=activeplayer;
            counter.setTranslationY(-1000f);
            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.greenball);
                activeplayer = 1;
            } else {
                counter.setImageResource(R.drawable.redball);
                activeplayer = 0;
            }
            counter.animate().translationYBy(1000f).rotation(3600f).setDuration(300);
            for(int[] wp:winningPositions){
                if(unplayed[wp[0]]==unplayed[wp[1]]&&unplayed[wp[1]]==unplayed[wp[2]]&&unplayed[wp[0]]!=2){
                    System.out.println(unplayed[wp[0]]);
                    TextView textView=(TextView)findViewById(R.id.textView);
                    gameisActive=false;
                    String winner="Red";
                    if(unplayed[wp[0]]==0)
                    {
                        winner="Green";
                    }
                    textView.setText(winner+" has won!!");

                    LinearLayout layout=(LinearLayout)findViewById(R.id.winnermessage);
                    layout.setVisibility(view.VISIBLE);
                }
                else{
                    boolean gameisOver=true;
                    for(int check:unplayed){
                        if(check==2)
                                gameisOver=false;
                    }
                    if(gameisOver){
                        TextView textView=(TextView)findViewById(R.id.textView);
                        textView.setText("It's a Draw!!");
                        LinearLayout layout=(LinearLayout)findViewById(R.id.winnermessage);
                        layout.setVisibility(view.VISIBLE);
                    }
                }

            }

        }
    }
    public void playAgain(View view){
        gameisActive=true;
        LinearLayout layout=(LinearLayout)findViewById(R.id.winnermessage);
        layout.setVisibility(view.INVISIBLE);
        activeplayer=0;
        for(int i=0;i<unplayed.length;i++)
                unplayed[i]=2;
        GridLayout gridLayout=(GridLayout)findViewById(R.id.gridLayout);
        for(int i=0;i<gridLayout.getChildCount();i++)
        {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
