package pe.edu.ulima.alumnosfirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dbref = database.getReference("alumnos");

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator iterador =  dataSnapshot.getChildren().iterator();
                while(iterador.hasNext()){
                    Alumno alumno =
                            ((DataSnapshot) iterador.next()).getValue(Alumno.class);
                    Log.i("TAG", alumno.getNombre());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("AlumnosAppFirebase", "Error");
            }

        });
    }
}
