package com.lib.bandaid.widget.easyui.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.lib.bandaid.R;
import com.lib.bandaid.adapter.com.SimpleRvAdapter;
import com.lib.bandaid.widget.dialog.BaseDialogFrg;
import com.lib.bandaid.widget.easyui.xml.ItemXml;

import java.util.List;

public class SimpleDialog extends BaseDialogFrg implements View.OnClickListener, SimpleRvAdapter.IFillData<ItemXml> {

    private List<ItemXml> values;
    private List<Integer> sel;
    private Boolean isMulti;

    private RecyclerView rvList;
    private SimpleRvAdapter simpleAdapter;
    private Button btnExit, btnSubmit;

    public static <T> SimpleDialog newInstance(List<ItemXml> values, List<Integer> sel, boolean isMulti, ICallBack<T> iCallBack) {
        SimpleDialog fragment = new SimpleDialog();
        fragment.setData(values, sel);
        fragment.setIsMulti(isMulti);
        fragment.setCallBack(iCallBack);
        return fragment;
    }

    private void setData(List<ItemXml> values, List<Integer> sel) {
        this.values = values;
        this.sel = sel;
    }

    public void setIsMulti(Boolean isMulti) {
        this.isMulti = isMulti;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_dialog_layout);
    }

    @Override
    protected void initialize() {
        rvList = $(R.id.rvList);
        btnExit = $(R.id.btnExit);
        btnSubmit = $(R.id.btnSubmit);
    }

    @Override
    protected void registerEvent() {
        btnExit.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
    }

    @Override
    protected void initClass() {
        simpleAdapter = new SimpleRvAdapter(rvList, this);
        simpleAdapter.setSelFlags(sel).setMulti(isMulti);
        simpleAdapter.replaceAll(values);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnExit) {
            dismiss();
        }
        if (id == R.id.btnSubmit) {
            dismiss();
            if (iCallBack != null) {
                List<ItemXml> selData = simpleAdapter.getSelData();
                iCallBack.callback(selData);
            }
        }
    }

    @Override
    public String fillData(ItemXml value) {
        return value.getValue();
    }

}
