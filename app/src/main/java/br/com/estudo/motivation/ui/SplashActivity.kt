package br.com.estudo.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import br.com.estudo.motivation.R
import br.com.estudo.motivation.infra.MotivationConstants
import br.com.estudo.motivation.infra.SecurityPrefences
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(), View.OnClickListener {

    // Inicialização tardia. Variável precisa do valor do contexto para ser inicializada corretamente
    // Acesso ao SharedPreferences
    private lateinit var mSecuretyPreference : SecurityPrefences;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Remove Toolbar
        if (supportActionBar != null) {
            supportActionBar!!.hide();
        }

        mSecuretyPreference = SecurityPrefences(this)

        saveButton.setOnClickListener(this)
        verifyUserName()

    }

    override fun onClick(view: View) {

        val id = view.id

        if (id == R.id.saveButton) {
            handleSave()
        }

    }

    private fun verifyUserName() {
        val name = mSecuretyPreference.getStoreString(MotivationConstants.KEY.PERSON_NAME)
        if (name != "") {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun handleSave() {
        //obtém o nome
        val name: String = editName.text.toString()

        //verifica se o usuario preencheu o nome
        if (name == "") {
            Toast.makeText(this, "Informe seu nome.", Toast.LENGTH_LONG).show()
        } else {
            mSecuretyPreference.storeString(MotivationConstants.KEY.PERSON_NAME, name)
            startActivity(Intent(this, MainActivity::class.java))

            //Impede que seja possivel voltar a Active
            finish()
        }

    }
}