package com.example.appmusic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.adapter.MusicAdapter;
import com.example.appmusic.adapter.MusicAdapterFind;
import com.example.appmusic.entity.modify;
import com.example.appmusic.model.MusicFind;
import com.example.appthibanglaixe.R;

import java.util.List;

public class Tab_Dowload_Fragment extends Fragment {

    private RecyclerView recyclerView;
    private MusicAdapterFind musicAdapter;
    private TextView edtSearch;

    public Tab_Dowload_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_dowload, container, false);

        edtSearch = view.findViewById(R.id.edtSeach);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Khởi tạo danh sách ban đầu với toàn bộ bài hát
        List<MusicFind> musicList = getMusicList("");

        // Khởi tạo adapter và thiết lập listener
        /*musicAdapter = new MusicAdapterFind(getContext(), musicList, id -> {
            // Xử lý sự kiện click item tại đây
            Fragment playFragment = new Tab_Play_Fragment();
            Bundle bundle = new Bundle();
            bundle.putInt("MUSIC_ID", id);
            playFragment.setArguments(bundle);

            // Chuyển đổi sang Fragment Play
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, playFragment) // Đảm bảo bạn thay đúng ID
                    .addToBackStack(null)
                    .commit();
        });*/

        musicAdapter = new MusicAdapterFind(getContext(), musicList, id -> {
            // Xử lý sự kiện click item tại đây
            Fragment playFragment = new Tab_Play_Fragment();
            Bundle bundle = new Bundle();
            bundle.putInt("MUSIC_ID", id);
            playFragment.setArguments(bundle);

            // Chuyển đổi sang Fragment Play
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, playFragment) // Đảm bảo bạn thay đúng ID
                    .addToBackStack(null)
                    .commit();
            // Sử dụng NavController để chuyển đến Tab_Play_Fragment

        });

        // Gán adapter cho RecyclerView
        recyclerView.setAdapter(musicAdapter);

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query = s.toString();
                // Lấy danh sách đã lọc theo từ khóa
                List<MusicFind> filteredMusicList = getMusicList(query);
                // Cập nhật danh sách vào adapter
                musicAdapter.updateMusicList(filteredMusicList);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        return view;
    }




    // Phương thức để lấy danh sách bài hát theo từ khóa tìm kiếm
    private List<MusicFind> getMusicList(String query) {
        modify musicModifier = new modify(getContext());
        return musicModifier.searchMusicByTitle(query);
    }
}
