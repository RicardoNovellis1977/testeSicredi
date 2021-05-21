package com.example.testeeventos.activity

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.example.testeeventos.R
import com.example.testeeventos.model.CheckIn
import com.example.testeeventos.viewmodel.ViewModelCheckIn
import com.google.android.material.textfield.TextInputEditText
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheckInActivity : AppCompatActivity() {

    private val viewModel: ViewModelCheckIn by viewModel()
    private lateinit var nomeCheckIn : TextInputEditText
    private lateinit var emailCheckIn : TextInputEditText
    private lateinit var btnCheckin : Button
    private lateinit var idEvento : String
    private  var nome : String = ""
    private  var email : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_in)
        initViews()
        idEvento = intent.getStringExtra(DetlhesEventoActivity.ID_EVENTO).toString()
        btnCheckin.setOnClickListener {
            nome = nomeCheckIn.text.toString()
            email = emailCheckIn.text.toString()
            onClickConfirmCheckIn()  }

        viewModel.resultCheckinLiveData.observe(this, Observer {
            it?.let { success ->
                if (success) showSuccessCheckin() else showErrorCheckIn()
            }
        })
    }

    fun initViews(){
        nomeCheckIn = findViewById(R.id.tietNome)
        emailCheckIn = findViewById(R.id.tietEmail)
        btnCheckin = findViewById(R.id.btnCheck)
    }

    private fun onClickConfirmCheckIn() {
        val paramsCheckIn = parametrosCheckin()
       if (validateCheckin()) {
            viewModel.postCheckIn(paramsCheckIn)
        }
    }

    private fun parametrosCheckin(): CheckIn {
        return CheckIn(idEvento, nome, email)
    }

    private fun validateCheckin (): Boolean {
        if (nome.isEmpty()) {
            showMessage(applicationContext, getString(R.string.alerta_nome))
            return false
        }
        if (email.isEmpty()) {
           showMessage(applicationContext, getString(R.string.alerta_email))
            return false;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showMessage(applicationContext, getString(R.string.alerta_email_valido))
            return false;
        }
        return true;
    }

    fun showMessage (context: Context?, message : String){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
    private fun showErrorCheckIn() {
        showMessage(applicationContext, getString(R.string.checkin_erro));
    }

    private fun showSuccessCheckin() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.checkin_sucesso))
        builder.setNeutralButton(getString(R.string.dialog_ok), DialogInterface.OnClickListener { dialog, which ->
            startActivity(Intent(this,MainActivity :: class.java))
        })

        val dialog : AlertDialog = builder.create()
        dialog.show()
    }
}