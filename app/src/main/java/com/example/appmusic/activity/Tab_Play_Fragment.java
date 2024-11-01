package com.example.appmusic.activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.appthibanglaixe.R;
import com.example.appmusic.entity.modify;
import com.example.appmusic.model.MusicFind;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tab_Play_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab_Play_Fragment extends Fragment {
    private MediaPlayer mediaPlayer;
    private boolean isPlaying = false;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Tab_Play_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab_practice_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab_Play_Fragment newInstance(String param1, String param2) {
        Tab_Play_Fragment fragment = new Tab_Play_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_play, container, false);

        ImageButton playPauseButton = view.findViewById(R.id.play_pause_button);
        // Lấy ID từ Bundle
        Bundle bundle = getArguments();
        if (bundle != null) {
            int musicId = bundle.getInt("MUSIC_ID", -1); // Giá trị mặc định là -1 nếu không tìm thấy
            // Tạo đối tượng modify để truy cập cơ sở dữ liệu
            modify musicModifier = new modify(getContext());

            // Lấy thông tin bài hát bằng ID
            MusicFind music = musicModifier.getMusicById(musicId);

            if (music != null) {
                // Ánh xạ các thành phần UI
                TextView songTitleTextView = view.findViewById(R.id.song_title);
                TextView artistAlbumTextView = view.findViewById(R.id.artist_album);
                ImageView coverImageView = view.findViewById(R.id.cover_image);
                TextView startTimeTextView = view.findViewById(R.id.start_time);
                TextView endTimeTextView = view.findViewById(R.id.end_time);

                // Cập nhật UI với thông tin bài hát
                songTitleTextView.setText(music.getSongTitle());
                artistAlbumTextView.setText(music.getArtistAlbum());
                String coverImageName = music.getCoverImage();
                if (coverImageName != null ) {
                    int resourceId = getResources().getIdentifier(coverImageName, "drawable", getActivity().getPackageName());
                    if (resourceId != 0) {
                        coverImageView.setImageResource(resourceId);
                        coverImageView.setVisibility(View.VISIBLE);
                    }
                }

                String test = music.getFilePath();
                System.out.println(test);

                int resId = getResources().getIdentifier(test, "raw", getActivity().getPackageName());

                if (resId != 0) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(getActivity(), resId);
                    mediaPlayer.start();
                } else {
                    System.out.println("File not found in raw directory");
                }

            }

            // Thiết lập sự kiện click cho nút play/pause
            playPauseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String test = music.getFilePath();
                    int resId = getResources().getIdentifier(test, "raw", getActivity().getPackageName());

                    if (isPlaying) {
                        // Dừng phát nhạc
                        mediaPlayer.stop();
                        mediaPlayer.release(); // Giải phóng tài nguyên
                        mediaPlayer = null; // Đặt lại mediaPlayer
                        playPauseButton.setImageResource(R.drawable.play); // Cập nhật biểu tượng
                        isPlaying = false; // Cập nhật trạng thái
                    } else {
                        // Phát nhạc
                        if (resId != 0) {
                            mediaPlayer = MediaPlayer.create(getActivity(), resId);
                            mediaPlayer.start();
                            playPauseButton.setImageResource(R.drawable.heart); // Cập nhật biểu tượng
                            isPlaying = true; // Cập nhật trạng thái
                        } else {
                            System.out.println("File not found in raw directory");
                        }
                    }
                }
            });



        }

        return view;
    }

}