package hu.nl.actortemplateapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import hu.nl.actortemplateapp.R;
import hu.nl.actortemplateapp.data_classes.Actor;

/**
 * Created by Dyon on 27-3-2017.
 */

public class InnerRecyclerViewAdapter extends RecyclerView.Adapter<InnerRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Actor> actoren;

    public InnerRecyclerViewAdapter(ArrayList<Actor> actoren) {
        this.actoren = actoren;
        for(int i = 0; i < actoren.size(); i++){ Log.d("Wut", String.valueOf(actoren.get(i))); }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View actorTemplateView = LayoutInflater.from(parent.getContext()).inflate(R.layout.actortemplate_users_row, parent, false);
        return new ViewHolder(actorTemplateView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.personname.setText(actoren.get(position).getNaam());
    }

    @Override
    public int getItemCount() {
        return actoren.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView personname;

        public ViewHolder(View v) {
            super(v);
            personname = (TextView)v.findViewById(R.id.personname);
        }
    }
}
