package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NumberListFragment extends Fragment implements View.OnClickListener {
    private static final String ELEMENTS_NUM = "ELEMENTS_NUM";
    private NumberItemAdapter adapter;
    private final List<NumberItem> numbers = new ArrayList<>();
    private int numbersSize;

    public NumberListFragment() {
        super();
    }

    static NumberListFragment newInstance() {
        return new NumberListFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int elementsNum = getResources().getInteger(R.integer.elements_num);
        if (savedInstanceState != null) {
            elementsNum = savedInstanceState.getInt(ELEMENTS_NUM, elementsNum);
        }
        for (int i = 0; i < elementsNum; ++i) {
            numbers.add(createItemToAdd());
            ++numbersSize;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_number_list, parent, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycleview);
        NumberItemAdapter.NumberClicker listener = (NumberItemAdapter.NumberClicker) getActivity();
        adapter = new NumberItemAdapter(numbers, listener);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),
                getResources().getInteger(R.integer.columns_num)));
        view.findViewById(R.id.button).setOnClickListener(this);
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        adapter.clearRefs();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle state) {
        state.putInt(ELEMENTS_NUM, numbersSize);
        super.onSaveInstanceState(state);
    }

    @Override
    public void onClick(View view) {
        addItem();
    }

    private void addItem() {
        NumberItem item = createItemToAdd();
        numbers.add(item);
        ++numbersSize;
        adapter.notifyItemInserted(item.num - 1);
    }

    private NumberItem createItemToAdd() {
        int index = numbers.size() + 1;
        return new NumberItem(index,
                getResources().getColor(index % 2 == 0 ? R.color.red : R.color.blue));
    }

}
