package com.example.trial


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_file.view.*


class MainActivity : AppCompatActivity() {
    val SList = arrayListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
     FirebaseDatabase.getInstance().setPersistenceEnabled(true)
      //  val user = ArrayList<Custom>()
        rcview.adapter = adapter
        btn.setOnClickListener {
            uploadData()
        }
        btnR.setOnClickListener{
            val tid = SList.random()
            val query =
                FirebaseDatabase.getInstance().getReference("newnode")
                    .orderByChild("id")
                    .equalTo(tid)
            query.addChildEventListener(object : ChildEventListener{
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onChildMoved(p0: DataSnapshot, p1: String?) {

                }

                override fun onChildChanged(p0: DataSnapshot, p1: String?) {

                }

                override fun onChildAdded(p0: DataSnapshot, p1: String?) {

                    val fetchedData = p0.getValue(Custom::class.java)
                    if (fetchedData!=null){
                        adapter.add(Data(fetchedData))
                    }

                }

                override fun onChildRemoved(p0: DataSnapshot) {

                }

            })
        }


        }



    val adapter = GroupAdapter<GroupieViewHolder>()

    // val adapter2 = customAdapter(this, user)

    public fun uploadData(){
        val ref = FirebaseDatabase.getInstance().getReference("newnode").push()
        val name = etvar.text.toString()
        val name2 = etvar1.text.toString()
        val id = ref.push().key.toString()
        SList.add(id)
        val custom = Custom(name,name2,id)
        ref.setValue(custom)
            .addOnSuccessListener {
                Toast.makeText(this,"Successfully Uploaded",Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this,it.message.toString(),Toast.LENGTH_SHORT).show()
            }
    }
    
    private fun fetchData(){
        val ref = FirebaseDatabase.getInstance().getReference("newnode")
        ref.addChildEventListener(object : ChildEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {

                val fetchedData = p0.getValue(Custom::class.java)
                if (fetchedData!=null){
                    adapter.add(Data(fetchedData))
                }

            }

            override fun onChildRemoved(p0: DataSnapshot) {

            }

        })
    }
}

class Data(val custom: Custom): Item<GroupieViewHolder>(){
    override fun getLayout(): Int {
        return R.layout.item_file
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.tvListView.text = custom.name
        viewHolder.itemView.tvListView1.text = custom.name2
    }

}
