package nyp.sit.movieviewer.basic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnRegistration.setOnClickListener{
            var successful_register = true
            if(ET_login.text.toString() == ""){
                ET_login.setError("Put in a valid login name")
                successful_register = false
            }
            if(ET_password.text.toString() == ""){
                ET_password.setError("Put in a valid login name")
                successful_register = false
            }
            if(ET_email.text.toString() == ""){
                ET_email.setError("Put in a valid login name")
                successful_register = false
            }
            if(ET_admin.text.toString() == ""){
                ET_admin.setError("Put in a valid login name")
                successful_register = false
            }
            if(ET_pem.text.toString() == ""){
                ET_pem.setError("Put in a valid login name")
                successful_register = false
            }

            if(successful_register){
                val codeIntent = Intent(this, VerificationCodeActivity::class.java)
                startActivity(codeIntent)
            }
        }
    }

    fun checkLogin(): Boolean {
        if(ET_login == null){
            ET_login.setError("Put in a Valid Login Name")
            return false
        }
        return true
    }
    fun checkPass() : Boolean{
        if(ET_password == null){
            ET_login.setError("Put in a Valid Password")
            return false
        }
        return true
    }
    fun checkEmail(): Boolean {
        if(ET_email == null){
            ET_login.setError("Put in a Valid Email")
            return false
        }
        return true
    }
    fun checkAdmin(): Boolean {
        if(ET_admin == null){
            ET_login.setError("Put in a Valid Admin Number")
            return false
        }
        return true
    }
    fun checkPem(): Boolean {
        if(ET_pem == null){
            ET_login.setError("Put in a Valid Pem grp")
            return false
        }
        return true
    }



}