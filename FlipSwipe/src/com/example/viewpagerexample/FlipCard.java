package com.example.viewpagerexample;


import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.WindowManager;
import android.webkit.WebView;

 
public class FlipCard extends FragmentActivity{
    private MyAdapter mAdapter;
    private ViewPager mPager;
    static WebView prevWebview;
    static WebView webview;
    List<String> urlList = new ArrayList<String>();
    List<String> strAssetUriList = new ArrayList<String>();
    List<String> strFolderList = new ArrayList<String>();
    
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prevWebview = (WebView) findViewById(R.id.webview);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mAdapter = new MyAdapter(getSupportFragmentManager());
 
        mPager = (ViewPager) findViewById(R.id.pager);
      
        mPager.setOnPageChangeListener(mAdapter);
        new getResources().execute();
    }
 
    public class MyAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {
    	
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }
 
        @Override
        public int getCount() {
            return urlList.size();
        }
 
        
        @Override
        public Fragment getItem(int position) {
     
        		String url = urlList.get(position);
        		String strAssetUri = strAssetUriList.get(position);
        		String strFolder = strFolderList.get(position);
        		Log.i("url", url);
        		if(url.contains("youtube")){
            	Uri uri = Uri.parse(urlList.get(position));
				String id = uri.getQueryParameter("v");
            	return new DetailFragment("<html><head></head><body margin-left = '0px'margin-top = '0px' leftmargin = '0px' topmargin = '0px' ><iframe width=\"100%\" height=\"100%           \""
						+ "src=\"http://www.youtube.com/embed/"+id+"\""
						+ "?rel=0&autoplay=1\"frameborder=\"0\" allowfullscreen> </iframe></body></html>");
        		}
        		else if(url.contains("pdf")){
        		
        			return new DetailFragment("<iframe src='http://docs.google.com/viewer?embedded=true&" +
                			"url="+ strAssetUri + strFolder + url+"' "+ 
                			"width='100%' height='100%' " + 
    	              		"style='border: none;'></iframe>");
//        			return new DetailFragment("<iframe src='http://docs.google.com/viewer?" +
//                			"url=http://www.naeyc.org/store/files/store/TOC/160.pdf&embedded=true' "+ 
//                			"width='100%' height='100%' " + 
//    	              		"style='border: none;'></iframe>");
        		}
        		else{
        		return new DetailFragment(url);
        		}
        }

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		
			if(prevWebview!=null && prevWebview.getUrl().contains("youtube") ){
				Log.i("youtube", "youtube");
				prevWebview.loadUrl("www.google.com");
			}
			Log.i("scrolled", "scrolled");
		}

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			
		}

    }
    
    
private class getResources extends AsyncTask<Void, String, String> {
	
		String token = "8fd48b78-d777-11e2-ba82-123141016e2a";
		
		@Override
		protected String doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			String responsedata = null;
			String searchKeyword="gravity";
			try {

				Log.i("gooruapi",
						"http://www.goorulearning.org/gooru-search/rest/search/resource?sessionToken="+token+"&query="
								+ searchKeyword
								+ "&pageNum=1&pageSize=10");
				WebService webdata = new WebService(
						"http://www.goorulearning.org/gooru-search/rest/search/resource?sessionToken="+token+"&query="
								+ searchKeyword
								+ "&pageNum=1&pageSize=10");

				responsedata = webdata.webInvoke(null, "", null);

				Log.i("response", "" + responsedata);

			} catch (Exception e) {
				e.printStackTrace();
				responsedata = "Please try again";
			}

			return responsedata;
		}
		
		
		protected void onPostExecute(String result) {
			
			Log.i("LOGGER", "...Done");
			
				try {			        
					JSONObject json = new JSONObject(result);

					if (json.has("category")) {
								
						Log.i("category", "category");
						JSONArray searchResults = json.getJSONArray("searchResults");
						int size = searchResults.length();
						
						for(int i=0;i<size;i++){
							
							JSONObject obj = searchResults.getJSONObject(i);
							String url = obj.getString("url");
							String strAssetUri = obj.getString("assetURI");
							String strFolder = obj.getString("folder");
							urlList.add(url);
							strAssetUriList.add(strAssetUri);
							strFolderList.add(strFolder);
//							Log.i("urlList", urlList.get(0));
						}	
						
				}
			}catch(Exception e){
					
					e.printStackTrace();
				}
				mPager.setAdapter(mAdapter);
				Log.i("result:", ""+result);
				
			}
}
    
}
