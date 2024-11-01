package com.example.appmusic.activity;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.appmusic.entity.modify;
import com.example.appmusic.adapter.MusicAdapter;
import com.example.appmusic.model.Music;
import com.example.appthibanglaixe.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tab_Favorite_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab_Favorite_Fragment extends Fragment {

    private ListView listView;
    private MusicAdapter musicAdapter;
    private List<Music> musicList;
    private modify musicModifier;
    private ImageView selectedCoverImage, likeBtn;
    private Music selectedMusic;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Tab_Favorite_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab_Uses_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab_Favorite_Fragment newInstance(String param1, String param2) {
        Tab_Favorite_Fragment fragment = new Tab_Favorite_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        // Khởi tạo danh sách nhạc
        musicList = new ArrayList<>();
        musicModifier = new modify(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        // Initialize the views
        listView = view.findViewById(R.id.favoriteSongsListView);
        selectedCoverImage = view.findViewById(R.id.selectedCoverImage);
        likeBtn = view.findViewById(R.id.likeButton);

        // Get favorite songs
        musicList = musicModifier.getFavoriteSongs(getContext());

        // Set up the adapter
        musicAdapter = new MusicAdapter(getContext(), musicList);
        listView.setAdapter(musicAdapter);

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            selectedMusic = musicList.get(position);
            String coverImageName = selectedMusic.getCoverImage();

            if (coverImageName != null && !coverImageName.isEmpty()) {
                // Lấy ID của tài nguyên drawable từ tên
                int resourceId = getResources().getIdentifier(coverImageName, "drawable", getActivity().getPackageName());

                if (resourceId != 0) {
                    selectedCoverImage.setImageResource(resourceId);
                    selectedCoverImage.setVisibility(View.VISIBLE);
                } else {
                    selectedCoverImage.setVisibility(View.GONE);
                }
            } else {
                selectedCoverImage.setVisibility(View.GONE);
            }
        });

        likeBtn.setOnClickListener(v -> {
            if (selectedMusic != null) {
                int musicId = selectedMusic.getId();
                int favoriteValue = 0;

                // Tạo đối tượng modify để gọi phương thức
                modify modifyDb = new modify(getContext());
                modifyDb.updateFavorite(musicId, favoriteValue);

                // Lấy lại danh sách bài hát từ cơ sở dữ liệu
                musicList = modifyDb.getFavoriteSongs(getContext());

                musicAdapter.updateData(musicList);
            } else {
                Toast.makeText(getContext(), "Vui lòng chọn một bài hát trước!", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}