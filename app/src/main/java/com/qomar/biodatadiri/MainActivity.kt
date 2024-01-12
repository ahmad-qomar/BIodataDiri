package com.qomar.biodatadiri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.qomar.biodatadiri.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        setDataSpinnerGender()

        mainBinding.btnSave.setOnClickListener{ validasiInput() }
    }
    private fun goToProfilActivity() {
        val intent = Intent(this, ProfilActivity::class.java)
        val bundle = Bundle()
        bundle.putString("nama", namaInput)
        bundle.putString("gender", genderInput)
        bundle.putString("email", emailInput)
        bundle.putString("telp", telpInput)
        bundle.putString("alamat", alamatInput)
        intent.putExtras(bundle)
        startActivity(intent)
    }
    private var namaInput : String = ""
    private var emailInput : String = ""
    private var telpInput : String = ""
    private var alamatInput : String = ""
    private var genderInput : String = ""
    private fun validasiInput(){
        namaInput = mainBinding.edtName.text.toString()
        emailInput = mainBinding.edtEmail.text.toString()
        telpInput = mainBinding.edtTelp.text.toString()
        alamatInput = mainBinding.edtAddress.text.toString()
        genderInput = mainBinding.spinnerGender.selectedItem.toString()
        when{
            namaInput.isEmpty() -> mainBinding.edtName.error = "Nama tidak boleh kosong"
                genderInput.equals("Pilih Jenis Kelamin", ignoreCase = true)
            -> tampilToast("Jenis Kelamin harus dipilih")
            emailInput.isEmpty() -> mainBinding.edtEmail.error = "Email tidak boleh kosong"
                telpInput.isEmpty() -> mainBinding.edtTelp.error = "Telp tidak boleh kosong"
                alamatInput.isEmpty() -> mainBinding.edtAddress.error = "Alamat tidak boleh kosong"
            else -> {
                tampilToast("Navigasi ke halaman profil")
                goToProfilActivity()
            }
        }
    }
    private fun tampilToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    private fun setDataSpinnerGender(){
        val adapter = ArrayAdapter.createFromResource(this,
            R.array.jenis_kelamin, android.R.layout.simple_spinner_item)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mainBinding.spinnerGender.adapter = adapter
    }
}