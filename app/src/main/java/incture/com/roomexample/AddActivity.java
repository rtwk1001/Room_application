package incture.com.roomexample;

import android.app.Activity;
import android.arch.persistence.room.Update;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import incture.com.roomexample.Database.AppDatabase;
import incture.com.roomexample.models.Note;

public class AddActivity extends AppCompatActivity {
EditText inputTitle,inputContent;
Button button,delete;
AppDatabase db;
String type;
Date date;
String title,content;
int noteid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

    inputTitle=(EditText)findViewById(R.id.ettitle);
    inputContent=(EditText)findViewById(R.id.etcontent);
    button=(Button)findViewById(R.id.button);
    delete=(Button)findViewById(R.id.delete);
        final Intent intent=getIntent();
        type=intent.getStringExtra("type");
        if(!type.equalsIgnoreCase("add")){
             noteid= intent.getIntExtra("noteid",0);
            button.setText("Update Note");
            final String title=intent.getStringExtra("title");
            inputTitle.setText(title);
            final String content=intent.getStringExtra("content");
            inputContent.setText(content);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String title=inputTitle.getText().toString();
                    String content=inputContent.getText().toString();
                    date=new Date();
                   Note noteupdate=new Note(title,content,date.toString().substring(4,16));
                    noteupdate.setNoteid(noteid);
                    if(title.isEmpty()||title.length()==0||content.isEmpty()||content.length()==0)
                        Toast.makeText(AddActivity.this,"Fields Can not be Empty",Toast.LENGTH_SHORT).show();
                    else
                    {db.getAppDatabase(AddActivity.this).getnoteDao().updateNote(noteupdate);
                    Toast.makeText(AddActivity.this,"Note Updated",Toast.LENGTH_SHORT).show();
                    Intent intentupdated = new Intent (AddActivity.this,MainActivity.class);
                    startActivity(intentupdated);}

                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    date= new Date();
                    Note notedelete=new Note(title,content,date.toString().substring(4,16));
                    notedelete.setNoteid(noteid);
                    if(title.isEmpty()||title.length()==0||content.isEmpty()||content.length()==0)
                        Toast.makeText(AddActivity.this,"Fields Can not be Empty",Toast.LENGTH_SHORT).show();
                    else
                    { db.getAppDatabase(AddActivity.this).getnoteDao().delete(notedelete);
                    Toast.makeText(AddActivity.this," Note Deleted",Toast.LENGTH_SHORT).show();
                    Intent intentdeleted = new Intent (AddActivity.this,MainActivity.class);
                    startActivity(intentdeleted);}
                }
            });
        }
        else{
            delete.setVisibility(View.GONE);
            button.setText("Add Note");
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String title=inputTitle.getText().toString();
                String content=inputContent.getText().toString();
                Date date= new Date();
                String datestr=date.toString();
                Note note=new Note(title,content,datestr.substring(4,16));
                if(title.isEmpty()||title.length()==0||content.isEmpty()||content.length()==0)
                    Toast.makeText(AddActivity.this,"Fields Can not be Empty",Toast.LENGTH_SHORT).show();
                else
                {  db.getAppDatabase(AddActivity.this).getnoteDao().insertAll(note);
               Intent intent=new Intent(AddActivity.this,MainActivity.class);
               startActivity(intent);}



            }
        });}

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
