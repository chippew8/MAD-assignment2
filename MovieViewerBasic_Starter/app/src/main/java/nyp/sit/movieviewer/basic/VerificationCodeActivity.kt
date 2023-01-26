package nyp.sit.movieviewer.basic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login_screen.*
import kotlinx.android.synthetic.main.activity_verification_code.*

class VerificationCodeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification_code)

        btnVerify.setOnClickListener{
            if(ET_verify.text.toString() == "1234"){
                val loginIntent = Intent(this, SimpleViewListOfMoviesActivity::class.java)
                startActivity(loginIntent)
            }else{
                displayToast("Code Error")
            }
        }

    }

    fun displayToast(message:String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}