package com.qomar.biodatadiri

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.qomar.biodatadiri.databinding.ActivityEditAllBinding

class EditAllActivity : AppCompatActivity() {
    private lateinit var editBinding: ActivityEditAllBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editBinding = ActivityEditAllBinding.inflate(layoutInflater)
        setContentView(editBinding.root)

        val intentData = intent.extras
        val namaUser = intentData?.getString("nama")
        val genderUser = intentData?.getString("gender")
        val emailUser = intentData?.getString("email")
        val telpUser = intentData?.getString("telp")
        val alamatUser = intentData?.getString("alamat")

        // Set nilai awal pada EditText dan Spinner
        editBinding.edtProfilName.setText(namaUser)
        val genderArray = resources.getStringArray(R.array.jenis_kelamin)
        val genderAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, genderArray)
        editBinding.spinnerGender.adapter = genderAdapter
        // Cek indeks jenis kelamin yang sesuai dan atur spinner
        val genderIndex = genderArray.indexOf(genderUser)
        if (genderIndex != -1) {
            editBinding.spinnerGender.setSelection(genderIndex)
        }

        editBinding.edtEmail.setText(emailUser)
        editBinding.edtTelp.setText(telpUser)
        editBinding.edtAddress.setText(alamatUser)

        editBinding.btnEditSave.setOnClickListener { saveData() }
    }

    private fun saveData() {
        val namaEdit = editBinding.edtProfilName.text.toString()
        val genderEdit = editBinding.spinnerGender.selectedItem.toString()
        val emailEdit = editBinding.edtEmail.text.toString()
        val telpEdit = editBinding.edtTelp.text.toString()
        val alamatEdit = editBinding.edtAddress.text.toString()

        if (!namaEdit.isEmpty()) {
            val result = Intent()
            result.putExtra("nama", namaEdit)
            result.putExtra("gender", genderEdit)
            result.putExtra("email", emailEdit)
            result.putExtra("telp", telpEdit)
            result.putExtra("alamat", alamatEdit)
            setResult(Activity.RESULT_OK, result)
        } else {
            setResult(Activity.RESULT_CANCELED)
        }
        finish()
    }
}
