package com.qomar.biodatadiri

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.qomar.biodatadiri.databinding.ActivityEditProfilBinding

class EditProfilActivity : AppCompatActivity() {
    private lateinit var editBinding : ActivityEditProfilBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editBinding = ActivityEditProfilBinding.inflate(layoutInflater)
        setContentView(editBinding.root)
        //menerima data yang dikirimkan dari ProfilActivity.kt
        val intentData = intent.extras
        val namaUser = intentData?.getString("nama")
        //set edittext dengan data yang dikirimkan tadi
        editBinding.edtProfilName.setText(namaUser)
        //memberikan action click ke tombol Simpan
        editBinding.btnEditSave.setOnClickListener { saveData() }
    }
    private fun saveData() {
        val namaEdit = editBinding.edtProfilName.text.toString()
        if (!namaEdit.isEmpty()) {
            //jika editText namaEdit tidak kosong, maka kirimkan value nya ke ProfilActivity, dan beri tanda RESULT_OK
            val result = Intent()
            result.putExtra("nama", namaEdit)
            setResult(Activity.RESULT_OK, result)
        } else {
            //jika editText namaEdit kosong, maka kirimkan tanda
            RESULT_CANCELED
            setResult(Activity.RESULT_CANCELED)
        }
        finish()
    }
}