package com.example.flipviewerexample;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.example.flipviewerexample.AnimationFactory.FlipDirection;

public class Flip3DViewTransitionActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
        final ViewAnimator viewAnimator = (ViewAnimator)this.findViewById(R.id.viewFlipper);
        final RelativeLayout relativeLayout = (RelativeLayout)this.findViewById(R.id.relativeLayout1);
        
        final TextView myTextView = new TextView(this);
        myTextView.setText("This is added dynamically");
        myTextView.setTextColor(Color.parseColor("#FF0000"));
        myTextView.setTextSize(20);
        relativeLayout.addView(myTextView);
        
        /*final TextView myTextView1 = new TextView(this);
        myTextView1.setText("This is also added dynamically");
        myTextView1.setTextColor(Color.parseColor("#FF0000"));
        myTextView1.setTextSize(20);
        relativeLayout.addView(myTextView1);*/
       
        /**
         * Bind a click listener to initiate the flip transitions
         */
        viewAnimator.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                // This is all you need to do to 3D flip
                                AnimationFactory.flipTransition(viewAnimator, FlipDirection.LEFT_RIGHT);
                        }
        });
       
        // The following click listeners are not needed (only here to test that clicks
        // are routed to the correct subview of the view animator).
       
        //this.findViewById(R.id.imageView1).setOnClickListener(new OnClickListener(){
        this.findViewById(R.id.relativeLayout1).setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                // This is all you need to do to 3D flip
                                AnimationFactory.flipTransition(viewAnimator, FlipDirection.LEFT_RIGHT);
                                Toast.makeText(Flip3DViewTransitionActivity.this, "Side A Touched", Toast.LENGTH_SHORT).show();
                        }
               
        });
        
      //this.findViewById(R.id.imageView2).setOnClickListener(new OnClickListener(){
        this.findViewById(R.id.relativeLayout2).setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                // This is all you need to do to 3D flip
                                AnimationFactory.flipTransition(viewAnimator, FlipDirection.LEFT_RIGHT);
                                Toast.makeText(Flip3DViewTransitionActivity.this, "Side B Touched", Toast.LENGTH_SHORT).show();
                        }
        });
       
       
    }
}

