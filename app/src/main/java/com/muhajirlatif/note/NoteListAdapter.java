package com.muhajirlatif.note;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder> {

    private Listener listener;
    private final LayoutInflater mInflater;
    private List<Note> mNotes;

    public NoteListAdapter(Context context, Listener listener) {
        this.mInflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    public void setNotes(List<Note> mNotes) {
        this.mNotes = mNotes;
        notifyDataSetChanged();
    }

    public List<Note> getNotes() {
        return mNotes;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.note_item, parent, false);

        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, final int position) {
        if (mNotes != null) {
            Note current = mNotes.get(position);

            if (!current.getTitle().isEmpty())
                holder.tvTitle.setText(current.getTitle());
            else
                holder.tvTitle.setText("Untitled");
        }

        holder.cvNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onCLick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mNotes != null)
            return mNotes.size();
        else return 0;
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private CardView cvNote;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            cvNote = itemView.findViewById(R.id.cvNote);
        }
    }

    public interface Listener {
        public void onCLick(int pos);
    }
}
