package nyp.sit.movieviewer.basic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        var adminPattern = "[0-9]{6}+[a-zA-Z]{1}"
        var pemPattern = "[a-zA-Z]{2}+[0-9]{4}"

        btnRegistration.setOnClickListener{
            var successful_register = true
            if(ET_login.text.toString() == ""){
                ET_login.setError("Put in a valid login name")
                successful_register = false
            }
            if(ET_password.text.toString() == ""){
                ET_password.setError("Put in a valid password")
                successful_register = false
            }
            if(ET_email.text.toString() == "" || !ET_email.text.toString().matches(emailPattern.toRegex())){
                ET_email.setError("Put in a valid email")
                successful_register = false
            }
            if(ET_admin.text.toString() == "" || !ET_admin.text.toString().matches(adminPattern.toRegex())){
                ET_admin.setError("Put in a valid admin number")
                successful_register = false
            }
            if(ET_pem.text.toString() == "" || !ET_pem.text.toString().matches(pemPattern.toRegex())){
                ET_pem.setError("Put in a valid Pem group")
                successful_register = false
            }

            if(successful_register){
                var success_message = "Login Name: " + ET_login.text.toString() + "\n Password: " + ET_password.text.toString() + "\n Email: " + ET_email.text.toString() +"\n Admin Number: " + ET_admin.text.toString() +"\n Pem Group: " + ET_pem.text.toString()
                displayToast(success_message)
                val codeIntent = Intent(this, VerificationCodeActivity::class.java)
                startActivity(codeIntent)
            }else{
                displayToast("Unsuccessful Registration")
            }
        }
    }

    fun displayToast(message:String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}