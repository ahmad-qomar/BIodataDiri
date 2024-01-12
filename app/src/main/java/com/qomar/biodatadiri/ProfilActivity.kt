package com.qomar.biodatadiri

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.qomar.biodatadiri.databinding.ActivityProfilBinding
class ProfilActivity : AppCompatActivity() {
    private lateinit var profilBinding : ActivityProfilBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        profilBinding = ActivityProfilBinding.inflate(layoutInflater)
        setContentView(profilBinding.root)
        ambilData()
        profilBinding.btnEditName.setOnClickListener {
            navigasiKeEditProfil() }
        profilBinding.btnCall.setOnClickListener {
            dialPhoneNumber(profilBinding.txtTelp.text.toString()) }
    }
    private fun ambilData() {
        val bundle = intent.extras
        val nama = bundle?.getString("nama")
        val gender = bundle?.getString("gender")
        val email = bundle?.getString("email")
        val telp = bundle?.getString("telp")
        val alamat = bundle?.getString("alamat")
        profilBinding.txtName.text = nama
        profilBinding.txtGender.text = gender
        profilBinding.txtEmail.text = email
        profilBinding.txtTelp.text = telp
        profilBinding.txtAddress.text = alamat
    }
    companion object {
        val REQUEST_CODE = 100
    }
    private fun navigasiKeEditProfil() {
        val intent = Intent(this, EditProfilActivity::class.java)
        val namaUser = profilBinding.txtName.text.toString()
        intent.putExtra("nama", namaUser)
        startActivityForResult(intent, REQUEST_CODE)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int,
                                  data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE){
            if (resultCode == Activity.RESULT_OK) {
                val result = data?.getStringExtra("nama")
                profilBinding.txtName.text = result
            }else{
                Toast.makeText(this, "Edit failed",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun dialPhoneNumber(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}