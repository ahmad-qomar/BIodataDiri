package com.qomar.biodatadiri

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.qomar.biodatadiri.databinding.ActivityProfilBinding

class ProfilActivity : AppCompatActivity() {
    private lateinit var profilBinding: ActivityProfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        profilBinding = ActivityProfilBinding.inflate(layoutInflater)
        setContentView(profilBinding.root)

        ambilData()

        profilBinding.btnEditName.setOnClickListener {
            navigasiKeEditProfil()
        }

        profilBinding.btnEditAll.setOnClickListener {
            navigasiKeEditAll()
        }

        profilBinding.btnCall.setOnClickListener {
            dialPhoneNumber(profilBinding.txtTelp.text.toString())
        }
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
        val REQUEST_CODE_EDIT_ALL = 100
    }

    private fun navigasiKeEditProfil() {
        val intent = Intent(this, EditProfilActivity::class.java)
        val namaUser = profilBinding.txtName.text.toString()
        intent.putExtra("nama", namaUser)
        startActivityForResult(intent, REQUEST_CODE_EDIT_ALL)
    }

    private fun navigasiKeEditAll() {
        val intent = Intent(this, EditAllActivity::class.java)
        val namaUser = profilBinding.txtName.text.toString()
        val genderUser = profilBinding.txtGender.text.toString()
        val emailUser = profilBinding.txtEmail.text.toString()
        val telpUser = profilBinding.txtTelp.text.toString()
        val alamatUser = profilBinding.txtAddress.text.toString()

        // Kirim semua data ke EditAllActivity#
        intent.apply {
            putExtra("nama", namaUser)
            putExtra("gender", genderUser)
            putExtra("email", emailUser)
            putExtra("telp", telpUser)
            putExtra("alamat", alamatUser)
        }

        startActivityForResult(intent, REQUEST_CODE_EDIT_ALL)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_EDIT_ALL) {
            if (resultCode == Activity.RESULT_OK) {
                val namaResult = data?.getStringExtra("nama")
                val genderResult = data?.getStringExtra("gender")
                val emailResult = data?.getStringExtra("email")
                val telpResult = data?.getStringExtra("telp")
                val alamatResult = data?.getStringExtra("alamat")

                profilBinding.txtName.text = namaResult
                profilBinding.txtGender.text = genderResult
                profilBinding.txtEmail.text = emailResult
                profilBinding.txtTelp.text = telpResult
                profilBinding.txtAddress.text = alamatResult
            } else {
                Toast.makeText(this, "Edit failed", Toast.LENGTH_SHORT).show()
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
