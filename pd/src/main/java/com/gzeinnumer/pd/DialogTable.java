package com.gzeinnumer.pd;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.google.android.material.textfield.TextInputEditText;
import com.gzeinnumer.edf.MyLibDialog;
import com.gzeinnumer.pd.adapter.ColumnAdapter;
import com.gzeinnumer.pd.adapter.TableAdapter;
import com.gzeinnumer.pd.helper.SqliteMaster;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DialogTable extends MyLibDialog {

    private RecyclerView rvTable;
    private RecyclerView rvColumn;
    private Button btnHideDetail;
    private Button btnClose;
    private CardView baseCardView;
    private CardView tvWarning;
    private LinearLayout llColumn;
    private TextInputEditText edSearch;

    private TableAdapter tableAdapter;
    private ColumnAdapter columnAdapter;

    private List<String> listTable;
    private String[] table;

    private SqliteMaster sqliteMaster;
    private SQLiteDatabase db;

    @Override
    public void onStart() {
        super.onStart();

        setCanvasWidth(0.98);
    }

    public static DialogTable newInstance(SQLiteDatabase db, String[] table) {
        return new DialogTable(db, table);
    }

    public DialogTable(SQLiteDatabase db, String[] table) {
        this.db = db;
        this.table = table;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pdb_dialog_table, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvTable = view.findViewById(R.id.rv_table);
        rvColumn = view.findViewById(R.id.rv_colomn);
        btnHideDetail = view.findViewById(R.id.btn_hide_detail);
        baseCardView = view.findViewById(R.id.base_card_view);
        tvWarning = view.findViewById(R.id.tv_warning);
        btnClose = view.findViewById(R.id.btn_close);
        llColumn = view.findViewById(R.id.ll_column);
        edSearch = view.findViewById(R.id.ed_search);

        sqliteMaster = new SqliteMaster(db);

        initData();
        initOnClick();
    }

    private void initData() {
        listTable = sqliteMaster.query(table);

        initRvTable(listTable);
    }

    private void initOnClick() {
        btnHideDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnHideDetail.setVisibility(View.GONE);
                TransitionManager.beginDelayedTransition(baseCardView, new AutoTransition());
                llColumn.setVisibility(View.GONE);
                initData();
            }
        });
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Objects.requireNonNull(getDialog()).dismiss();
            }
        });
    }

    private void initRvTable(List<String> tables) {
        tableAdapter = new TableAdapter(tables,sqliteMaster, position -> {
            List<String> reset =new ArrayList<>();
            reset.add(tables.get(position));
            initRvTable(reset);
            initRvColumn(tables.get(position));
        });
        rvTable.setAdapter(tableAdapter);
        rvTable.setLayoutManager(new LinearLayoutManager(requireContext()));
        rvTable.hasFixedSize();
    }


    private void initRvColumn(String tableName) {
        List<String> allData = sqliteMaster.getData(tableName);

        columnAdapter = new ColumnAdapter(allData);
        rvColumn.setAdapter(columnAdapter);
        rvColumn.setLayoutManager(new LinearLayoutManager(requireContext()));
        rvColumn.hasFixedSize();

        TransitionManager.beginDelayedTransition(baseCardView, new AutoTransition());

        if (allData.size() > 0) {
            tvWarning.setVisibility(View.GONE);
            llColumn.setVisibility(View.VISIBLE);
            btnHideDetail.setVisibility(View.VISIBLE);
        } else {
            tvWarning.setVisibility(View.VISIBLE);
            llColumn.setVisibility(View.GONE);
            btnHideDetail.setVisibility(View.GONE);
            initData();
        }

        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                columnAdapter.getFilter().filter(editable.toString());
            }
        });
    }
}
