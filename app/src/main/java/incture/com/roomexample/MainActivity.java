package incture.com.roomexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import incture.com.roomexample.Adapters.NoteAdapter;
import incture.com.roomexample.Database.AppDatabase;
import incture.com.roomexample.models.Note;

public class MainActivity extends AppCompatActivity {
List<Note> notes;
RecyclerView recyclerView;
AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        notes=db.getAppDatabase(MainActivity.this).getnoteDao().getAll();
        NoteAdapter adapter=new NoteAdapter(notes);
        adapter.setOnItemClickListener(new NoteAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent intent= new Intent(MainActivity.this,AddActivity.class);
                intent.putExtra("type","update");
                intent.putExtra("title",notes.get(position).getTitle());
                intent.putExtra("noteid",notes.get(position).getNoteid());
                intent.putExtra("content",notes.get(position).getContent());
                startActivity(intent);
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,AddActivity.class);
                intent.putExtra("type","add");
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

  /*  private static List<Note> getAllNotes() {
       return db.noteDao().getAll();

    }*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
