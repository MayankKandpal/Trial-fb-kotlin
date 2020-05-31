package com.example.trial

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dbRef = FirebaseDatabase.getInstance().reference
        val user= ArrayList<Custom>()
        val rcview =findViewById(R.id.rcview) as RecyclerView
        rcview.layoutManager = LinearLayoutManager(this)
        btn.setOnClickListener(View.OnClickListener {
            val note = etvar.text.toString()
            val note1 = etvar1.text.toString()
            dbRef.child("node").push().setValue(note)
            dbRef.child("todo").push().setValue(note1)
        })

        val adapter2 = customAdapter(this, user)
        rcview.adapter = adapter2
        dbRef.child("node").addChildEventListener(object : ChildEventListener {
              override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
               val data = dataSnapshot.value.toString()
              }
           override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {}
               override fun onChildRemoved(dataSnapshot: DataSnapshot) {}
            override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {}
              override fun onCancelled(databaseError: DatabaseError) {}
       })

        dbRef.child("todo").addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                val data2 = dataSnapshot.value.toString()
            }
            override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {}
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}
            override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {}
            override fun onCancelled(databaseError: DatabaseError) {}
        })
        }


    }
