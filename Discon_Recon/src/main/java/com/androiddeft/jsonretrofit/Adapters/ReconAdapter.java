package com.androiddeft.jsonretrofit.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androiddeft.jsonretrofit.Models.Recon_data;
import com.androiddeft.jsonretrofit.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReconAdapter extends RecyclerView.Adapter<ReconAdapter.CustomViewHolder> {
    private List<Recon_data> reconData;

    public ReconAdapter(List<Recon_data> reconData) {
        this.reconData = reconData;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.reconnection_list, parent, false);

        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReconAdapter.CustomViewHolder holder, int position) {
        Recon_data recon_data = reconData.get(position);
        holder.accountid.setText(recon_data.getACCOUNT_ID());
        holder.prevraed.setText(recon_data.getPREVREAD());
        holder.conname.setText(recon_data.getCONSUMER_NAME());

    }

    @Override
    public int getItemCount() {
        return reconData.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_account_id)
        TextView accountid;
        @BindView(R.id.txt_prevread)
        TextView prevraed;
        @BindView(R.id.txt_cons_name)
        TextView conname;

        // TextView accountid, prevraed, conname;

        public CustomViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);
//            accountid = view.findViewById(R.id.txt_account_id);
//            prevraed = view.findViewById(R.id.txt_prevread);
//            conname = view.findViewById(R.id.txt_cons_name);
        }
    }
}
