package incture.com.roomexample.Adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.List;

import incture.com.roomexample.R;
import incture.com.roomexample.models.Note;

/**
 * Created by Ritwik.Jain on 3/20/2018.
 */

public class NoteAdapter  extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {
     private static ClickListener clickListener;
    private List<Note> noteList;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title, content , createdat,imagetext;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            imagetext=(TextView) view.findViewById(R.id.imagetext);
             view.setOnClickListener(this);
            content = (TextView) view.findViewById(R.id.content);
            createdat = (TextView) view.findViewById(R.id.createdon);
        }

        @Override
        public void onClick(View v) {
        clickListener.onItemClick(getAdapterPosition(),v);
        }
    }
    public void setOnItemClickListener(ClickListener clickListener){
        this.clickListener=clickListener;
    }

      public interface ClickListener{
        void onItemClick(int position,View v);
      }
    public NoteAdapter(List<Note> noteList) {
        this.noteList = noteList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.noteelement, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Note note = noteList.get(position);
        holder.title.setText(note.getTitle());
        holder.content.setText(note.getContent());
        holder.createdat.setText(note.getCreatedat().toString());
        String txt=note.getTitle();
        String finaltxt="";
        if(txt.contains(" ")){
            finaltxt=txt.substring(0,1)+txt.substring(txt.indexOf(" ")+1,txt.indexOf(" ")+2);
            finaltxt=finaltxt.toUpperCase();
        }
        else
            finaltxt=txt.substring(0,2).toUpperCase();
        holder.imagetext.setText(finaltxt);

    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }
}