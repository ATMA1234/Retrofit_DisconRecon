package com.androiddeft.jsonretrofit.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androiddeft.jsonretrofit.Models.Discon_data;
import com.androiddeft.jsonretrofit.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DisconAdapter extends RecyclerView.Adapter<DisconAdapter.CustomViewHolder> {
    private List<Discon_data> disconData;

    public DisconAdapter(List<Discon_data> disconData) {
        this.disconData = disconData;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.disconnection_list, parent, false);
        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Discon_data discon_data = disconData.get(position);
        holder.accountid.setText(discon_data.getACCOUNT_ID());
        holder.arrears.setText(discon_data.getARREARS());
        holder.prevraed.setText(discon_data.getPREVREAD());
        holder.conname.setText(discon_data.getCONSUMER_NAME());
    }

    @Override
    public int getItemCount() {
        return disconData.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_account_id)
        TextView accountid;
        @BindView(R.id.txt_arrears)
        TextView arrears;
        @BindView(R.id.txt_prevread)
        TextView prevraed;
        @BindView(R.id.txt_cons_name)
        TextView conname;
//        TextView accountid, arrears, prevraed, conname;

        public CustomViewHolder(View view) {
            super(view);
//            accountid = view.findViewById(R.id.txt_account_id);
//            arrears = view.findViewById(R.id.txt_arrears);
//            prevraed = view.findViewById(R.id.txt_prevread);
//            conname = view.findViewById(R.id.txt_cons_name);

            ButterKnife.bind(this, view);
        }
    }
}
