package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Fragment2 extends Fragment {

    View view;
    GridView gridView;
    GridView gridView2;

    public Fragment2() {
        // Required empty public constructor
    }

    public static Fragment2 newInstance() {
        Fragment2 fragment2 = new Fragment2();
        return fragment2;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_2, container, false);

        gridView = (GridView)view.findViewById(R.id.gridView);
        gridView.setAdapter(new GridAdapter1(getActivity()));
        gridView2 = (GridView)view.findViewById(R.id.gridView2);
        gridView2.setAdapter(new GridAdapter2(getActivity()));
        return view;
    }

    public class GridAdapter1 extends BaseAdapter {
        Context context;

        public GridAdapter1(Context c) {
            context = c;
        }

        Integer[] imgID1 = {
                R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5,
                R.drawable.img6, R.drawable.img7, R.drawable.img8, R.drawable.img9, R.drawable.img10
        };

        @Override
        public int getCount() {
            return imgID1.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            ////////////////////////JSON형식 파싱////////////////////////////////////////////////////
            List<String> parsed_title = new ArrayList<String>();
            List<String> parsed_poster = new ArrayList<String>();
            List<String> parsed_video_id = new ArrayList<String>();

            try{
                InputStream is = getResources().getAssets().open("json/movie_list.json");
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader reader = new BufferedReader(isr);

                StringBuffer buffer = new StringBuffer();
                String line = reader.readLine();
                while (line!=null){
                    buffer.append(line+"\n");
                    line=reader.readLine();
                }

                String jsonData = buffer.toString();

                JSONArray jsonArray = new JSONArray(jsonData);

                //
                for(int j=0; j<jsonArray.length(); j++){
                    JSONObject jo = jsonArray.getJSONObject(j);
                    String j_title = jo.getString("title");
                    String j_poster = jo.getString("poster");
                    String j_video_id = jo.getString("video_id");
                    parsed_title.add(j_title);
                    parsed_poster.add(j_poster);
                    parsed_video_id.add(j_video_id);
                }


            } catch (IOException e) {e.printStackTrace();}
            catch (JSONException e) {e.printStackTrace();}
            ////////////////////////////////////////////////////////////////////////////////////////


            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(500, 800));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(2, 0, 0, 0);
            imageView.setImageResource(imgID1[i]);

            final int pos = i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    View dialogView = View.inflate(context, R.layout.dialog, null);
                    AlertDialog.Builder dlg = new AlertDialog.Builder(context);

                    TextView title = dialogView.findViewById(R.id.movie_title);
                    ImageView poster = dialogView.findViewById(R.id.movie_poster);

                    LinearLayout recipeViewLinearLayout = dialogView.findViewById(R.id.movie_video);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                    YouTubePlayerView video = new YouTubePlayerView(context);
                    video.setLayoutParams(params);
                    recipeViewLinearLayout.addView(video);
                    getLifecycle().addObserver(video);

                    title.setText(parsed_title.get(pos));

                    final Bitmap[] bitmap = new Bitmap[1];
                    Thread uThread = new Thread() {
                        @Override
                        public void run(){
                            try{
                                // 이미지 URL 경로
                                URL url = new URL(parsed_poster.get(pos));

                                // web에서 이미지를 가져와 ImageView에 저장할 Bitmap을 만든다.
                                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                                conn.setDoInput(true); // 서버로부터 응답 수신
                                conn.connect(); //연결된 곳에 접속할 때 (connect() 호출해야 실제 통신 가능함)

                                InputStream is = conn.getInputStream(); //inputStream 값 가져오기
                                bitmap[0] = BitmapFactory.decodeStream(is); // Bitmap으로 변환

                            }catch (MalformedURLException e){e.printStackTrace();}
                            catch (IOException e){e.printStackTrace();}
                        }
                    };

                    uThread.start(); // 작업 Thread 실행

                    try{
                        uThread.join();
                        poster.setImageBitmap(bitmap[0]);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                    video.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                        @Override
                        public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                            super.onReady(youTubePlayer);
                            youTubePlayer.loadVideo(parsed_video_id.get(pos), 0);
                        }
                    });

                    dlg.setView(dialogView);
                    dlg.setNegativeButton("닫기",null);
                    dlg.show();
                }
            });

            return imageView;
        }
    }
    public class GridAdapter2 extends BaseAdapter {

        Context context;
        public GridAdapter2(Context c) {
            context = c;
        }

        Integer[] imgID2 = {
                R.drawable.img11, R.drawable.img12, R.drawable.img13, R.drawable.img14, R.drawable.img15,
                R.drawable.img16, R.drawable.img17, R.drawable.img18, R.drawable.img19, R.drawable.img20
        };

        @Override
        public int getCount() {
            return imgID2.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            List<String> parsed_title = new ArrayList<String>();
            List<String> parsed_poster = new ArrayList<String>();
            List<String> parsed_video_id = new ArrayList<String>();

            try{
                InputStream is = getResources().getAssets().open("json/movie_list.json");
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader reader = new BufferedReader(isr);

                StringBuffer buffer = new StringBuffer();
                String line = reader.readLine();
                while (line!=null){
                    buffer.append(line+"\n");
                    line=reader.readLine();
                }

                String jsonData = buffer.toString();

                JSONArray jsonArray = new JSONArray(jsonData);

                //
                for(int j=0; j<jsonArray.length(); j++){
                    JSONObject jo = jsonArray.getJSONObject(j);
                    String j_title = jo.getString("title");
                    String j_poster = jo.getString("poster");
                    String j_video_id = jo.getString("video_id");
                    parsed_title.add(j_title);
                    parsed_poster.add(j_poster);
                    parsed_video_id.add(j_video_id);
                }


            } catch (IOException e) {e.printStackTrace();}
            catch (JSONException e) {e.printStackTrace();}

            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(500, 800));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(0, 0, 2, 0);
            imageView.setImageResource(imgID2[i]);

            final int pos = 10+i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    View dialogView = View.inflate(context, R.layout.dialog, null);
                    AlertDialog.Builder dlg = new AlertDialog.Builder(context);

                    TextView title = dialogView.findViewById(R.id.movie_title);
                    ImageView poster = dialogView.findViewById(R.id.movie_poster);

                    LinearLayout recipeViewLinearLayout = dialogView.findViewById(R.id.movie_video);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                    YouTubePlayerView video = new YouTubePlayerView(context);
                    video.setLayoutParams(params);
                    recipeViewLinearLayout.addView(video);
                    getLifecycle().addObserver(video);

                    title.setText(parsed_title.get(pos));

                    final Bitmap[] bitmap = new Bitmap[1];
                    Thread uThread = new Thread() {
                        @Override
                        public void run(){
                            try{
                                // 이미지 URL 경로
                                URL url = new URL(parsed_poster.get(pos));

                                // web에서 이미지를 가져와 ImageView에 저장할 Bitmap을 만든다.
                                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                                conn.setDoInput(true); // 서버로부터 응답 수신
                                conn.connect(); //연결된 곳에 접속할 때 (connect() 호출해야 실제 통신 가능함)

                                InputStream is = conn.getInputStream(); //inputStream 값 가져오기
                                bitmap[0] = BitmapFactory.decodeStream(is); // Bitmap으로 변환

                            }catch (MalformedURLException e){e.printStackTrace();}
                            catch (IOException e){e.printStackTrace();}
                        }
                    };

                    uThread.start(); // 작업 Thread 실행

                    try{
                        uThread.join();
                        poster.setImageBitmap(bitmap[0]);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                    video.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                        @Override
                        public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                            super.onReady(youTubePlayer);
                            youTubePlayer.loadVideo(parsed_video_id.get(pos), 0);
                        }
                    });

                    dlg.setView(dialogView);
                    dlg.setNegativeButton("닫기",null);
                    dlg.show();
                }
            });

            return imageView;
        }
    }

}