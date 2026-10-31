package br.universidade.firebase

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            irParaTelaPrincipal()
        } else {
            iniciarFluxoDeLogin()
        }
    }

    private fun iniciarFluxoDeLogin() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build()
        )

        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
            
        signInLauncher.launch(signInIntent)
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        if (result.resultCode == RESULT_OK) {
            irParaTelaPrincipal()
        } else {
            Toast.makeText(this, "Acesso cancelado ou falhou", Toast.LENGTH_SHORT).show()
            iniciarFluxoDeLogin()
        }
    }

    private fun irParaTelaPrincipal() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
