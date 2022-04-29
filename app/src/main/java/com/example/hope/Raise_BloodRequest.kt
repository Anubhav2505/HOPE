package com.example.hope

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class Raise_BloodRequest : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_raise_blood_request)

        // Fetching data from the raise request UI
        var patient_Name = findViewById<EditText>(R.id.raisereq_name)
        var blood_grp = findViewById<Spinner>(R.id.blooodgrpSpinner)
        var req_Raiser = findViewById<EditText>(R.id.raisedBy_name)
        var contact_details = findViewById<EditText>(R.id.contact)
        var location = findViewById<EditText>(R.id.location)
        val isGenuine = findViewById<CheckBox>(R.id.checkBox)
            db = FirebaseFirestore.getInstance()

        val raiseReq = findViewById<Button>(R.id.Bloodreq_btn)

        raiseReq.setOnClickListener {

          val reqInfo = HashMap<String , Any>()
             reqInfo.put("Blood_Group",  blood_grp.selectedItem.toString())
            reqInfo.put("Patient_Name", patient_Name.text.toString())
            reqInfo.put( "Contact_Details",  contact_details.text.toString())
            reqInfo.put( "Location_Details", location.text.toString())

            db.collection("Requests").add(reqInfo).addOnSuccessListener {
                Toast.makeText(this,"Request is Raised",Toast.LENGTH_LONG).show()
            }.addOnFailureListener {
                Toast.makeText(this,"Request Can't be  Raised",Toast.LENGTH_LONG).show()
            }


        }



    }
}