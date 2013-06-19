/**
 * @author Surabhi Seetharam
 */

package com.example.viewpagerexample;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.example.viewpagerexample.AnimationFactory.FlipDirection;

public class DetailFragment extends Fragment	 {
	
	TextView tv;
	ViewAnimator viewAnimator;
	String url;
	int shareSelected=0,narrationSelected=0,navigationSelected=0;
	
	public DetailFragment(String url) {
        this.url = url;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	super.onCreate(savedInstanceState);
        Log.e("Test", "hello");
    }
 
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
 
    }
 
    @SuppressWarnings("deprecation")
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	
        View view = inflater.inflate(R.layout.details, container, false);
        viewAnimator = (ViewAnimator) view.findViewById(R.id.viewFlipper);
        
        Button firstButton = (Button) view.findViewById(R.id.flip);
        final ImageView share = (ImageView) view.findViewById(R.id.share);
        final ImageView narration = (ImageView) view.findViewById(R.id.narration);
        final ImageView navigation = (ImageView) view.findViewById(R.id.navigation);
      //  Button secondButton = (Button) view.findViewById(R.id.button2); 
        
        /**
         * Setting properties of the webview
         */
        WebView webView = (WebView) view.findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
        webView.getSettings().setPluginsEnabled(true);
        
        if(url.contains("pdf") || url.contains("youtube")){
        	webView.loadData(url, "text/html",  "UTF-8");
        }
        else{
        	webView.loadUrl(url);
        }
        
        class MyWebView extends WebViewClient {
            
        	@Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
        		
                view.loadUrl(url);
                return true;
            }
        }
        
        webView.setWebChromeClient(new WebChromeClient());
        MyWebView myWebView = new MyWebView();
        webView.setWebViewClient(myWebView);
        
        /**
         * Bind a click listener to initiate the flip transitions
         */
        firstButton.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                // This is all you need to do to 3D flip
                                AnimationFactory.flipTransition(viewAnimator, FlipDirection.LEFT_RIGHT);
                        }
        });
       
        // The following click listeners are not needed (only here to test that clicks
        // are routed to the correct subview of the view animator).
       
        firstButton.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                // This is all you need to do to 3D flip
                                AnimationFactory.flipTransition(viewAnimator, FlipDirection.LEFT_RIGHT);
                        }
        });
        
        firstButton.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                // This is all you need to do to 3D flip
                                AnimationFactory.flipTransition(viewAnimator, FlipDirection.LEFT_RIGHT);
                        }
        });
        
        /**
         * Bind the click listeners to share,navigation and narration. These will be extended
         * to provide more concrete functionalities in future.
         */
        view.findViewById(R.id.share).setOnClickListener(new OnClickListener() {
			
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {

				if(shareSelected==0){
					Toast.makeText(getActivity().getApplicationContext(), "SHARE", Toast.LENGTH_SHORT).show();
					share.setImageResource(R.drawable.share_selected);
					shareSelected=1;
				}
				else{
					Toast.makeText(getActivity().getApplicationContext(), "SHARE", Toast.LENGTH_SHORT).show();
					share.setImageResource(R.drawable.share_normal);
					shareSelected=0;
				}
				
			}
		});
        
        view.findViewById(R.id.narration).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(narrationSelected==0){
					Toast.makeText(getActivity().getApplicationContext(), "NARRATION", Toast.LENGTH_SHORT).show();
					narration.setImageResource(R.drawable.narration_selected);
					narrationSelected=1;
				}
				else{
					Toast.makeText(getActivity().getApplicationContext(), "NARRATION", Toast.LENGTH_SHORT).show();
					narration.setImageResource(R.drawable.narration_normal);
					narrationSelected=0;
				}
			}
		});
        
        view.findViewById(R.id.navigation).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(navigationSelected==0){
					Toast.makeText(getActivity().getApplicationContext(), "NAVIGATION", Toast.LENGTH_SHORT).show();
					navigation.setImageResource(R.drawable.navigation_selected);
					navigationSelected=1;
				}
				else{
					Toast.makeText(getActivity().getApplicationContext(), "NAVIGATION", Toast.LENGTH_SHORT).show();
					navigation.setImageResource(R.drawable.navigation_normal);
					navigationSelected=0;
				}
			}
		});
        return view;
    }
}
