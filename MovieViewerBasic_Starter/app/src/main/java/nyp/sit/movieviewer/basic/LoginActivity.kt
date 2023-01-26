package nyp.sit.movieviewer.basic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login_screen.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        btnLogin.setOnClickListener{
            if(ET_main_login.text.toString() == "testuser" && ET_main_password.text.toString() == "testuser"){
                val loginIntent = Intent(this, SimpleViewListOfMoviesActivity::class.java)
                startActivity(loginIntent)
            }else{
                ET_main_login.setError("Invalid Username")
                ET_main_password.setError("Invalid Password")
                displayToast("Login Error")
            }
        }

        btnRegister.setOnClickListener{
            val RegisterActivityIntent = Intent(this, RegisterActivity::class.java)
            startActivity(RegisterActivityIntent)
        }
    }

    fun displayToast(message:String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }


}