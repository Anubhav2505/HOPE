package com.example.hope

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.*
import com.google.firebase.firestore.*
import android.content.Intent
import android.net.Uri


class HomeFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private lateinit var appointArrayList: ArrayList<RaiseRequest>
    private lateinit var AppointAdapter: RaiseAdapter
    private lateinit var db: DatabaseReference
    private lateinit var firestore : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
          val ham = view.findViewById<ImageView>(R.id.ham)
        val close = view.findViewById<ImageView>(R.id.viewIn)
        val navigation : NavigationView = view.findViewById(R.id.nav_view)
       val call : ImageView = view.findViewById(R.id.call)
        val number : String = "8920550931"
        ham.setOnClickListener {
            navigation.visibility = if (navigation.visibility == View.VISIBLE){
                View.INVISIBLE
            } else{
                View.VISIBLE
            }
        }

        close.setOnClickListener {
            navigation.visibility = if (navigation.visibility == View.VISIBLE){
                View.INVISIBLE
            } else{
                View.VISIBLE
            }
        }

        call.setOnClickListener {
            // call option
            val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", number, null))
            startActivity(intent)
        }




        val recyclerView = view.findViewById<RecyclerView>(R.id.raise_recyclerView)
        recyclerView.layoutManager= LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        appointArrayList = arrayListOf()

        AppointAdapter = RaiseAdapter(appointArrayList)

        recyclerView.adapter = AppointAdapter


        EventChangeListner()



        return view
    }

    private fun EventChangeListner() {

        firestore= FirebaseFirestore.getInstance()
        firestore.collection("Requests").addSnapshotListener(object: EventListener<QuerySnapshot> {
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if(error!=null){
                    Log.e("Firestore Error",error.message.toString())
                    return
                }
                for (dc: DocumentChange in value?.documentChanges!!){
                    if (dc.type == DocumentChange.Type.ADDED){
                        appointArrayList.add(dc.document.toObject(RaiseRequest::class.java))
                    }
                }
                AppointAdapter.notifyDataSetChanged()

            }

        })

    }

}


