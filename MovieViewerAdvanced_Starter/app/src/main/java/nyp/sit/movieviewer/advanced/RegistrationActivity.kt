package nyp.sit.movieviewer.advanced

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.mobile.client.Callback
import com.amazonaws.mobile.client.UserStateDetails
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserAttributes
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserPool
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.SignUpHandler
import kotlinx.coroutines.*
import nyp.sit.movieviewer.advanced.databinding.ActivityRegistrationBinding
import java.lang.Exception

class RegistrationActivity : AppCompatActivity() {

    private lateinit var registerbinding: ActivityRegistrationBinding

    var appCoroutineScope: CoroutineScope? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        registerbinding = ActivityRegistrationBinding.inflate(layoutInflater)
        val view = registerbinding.root
        setContentView(view)

        appCoroutineScope = CoroutineScope(Job() + Dispatchers.IO)

        AWSMobileClient.getInstance().initialize(this, object: Callback<UserStateDetails> {

            override fun onResult(result: UserStateDetails?){

                Log.d("CognitoLab", result?.userState?.name.toString())
            }

            override fun onError(e: java.lang.Exception?) {
                Log.d("CognitoLab", "There is an error - ${e.toString()}")
            }
        })
    }

    fun runRegister(v: View) {
        var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        var adminPattern = "[0-9]{6}+[a-zA-Z]{1}"
        var pemPattern = "[a-zA-Z]{2}+[0-9]{4}"
        var passwordPattern = "\"^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}\$\""

        var successful_register = true
        if(registerbinding.ETLogin.text.toString() == ""){
            registerbinding.ETLogin.setError("Put in a valid login name")
            successful_register = false
        }
        if(registerbinding.ETPassword.text.toString() == "" || !registerbinding.ETPassword.text.toString().matches(passwordPattern.toRegex())){
            registerbinding.ETPassword.setError("Put in a valid password")
            successful_register = false
        }
        if(registerbinding.ETEmail.text.toString() == "" || !registerbinding.ETEmail.text.toString().matches(emailPattern.toRegex())){
            registerbinding.ETEmail.setError("Put in a valid email")
            successful_register = false
        }
        if(registerbinding.ETAdmin.text.toString() == "" || !registerbinding.ETAdmin.text.toString().matches(adminPattern.toRegex())){
            registerbinding.ETAdmin.setError("Put in a valid admin number")
            successful_register = false
        }
        if(registerbinding.ETPem.text.toString() == "" || !registerbinding.ETPem.text.toString().matches(pemPattern.toRegex())){
            registerbinding.ETPem.setError("Put in a valid Pem group")
            successful_register = false
        }
        if (successful_register) {
            var loginName = registerbinding.ETLogin.text.toString()
            var password = registerbinding.ETPassword.text.toString()
            var pem = registerbinding.ETPem.text.toString()
            var email = registerbinding.ETEmail.text.toString()
            var admin = registerbinding.ETAdmin.text.toString()

            appCoroutineScope?.launch(Dispatchers.IO) {

                //TODO 3: Assign the user details to the respective cognito attributes
                var userPool =
                    CognitoUserPool(v.context, AWSMobileClient.getInstance().configuration)

                var userAttributes = CognitoUserAttributes()
                userAttributes.addAttribute("custom:AdminNumber", admin)
                userAttributes.addAttribute("email", email)
                userAttributes.addAttribute("custom:PemGrp", pem)
                //TODO 4: Sign up user with the attributes and listen for result

                userPool.signUp(
                    loginName,
                    password,
                    userAttributes,
                    null, object : SignUpHandler {
                        override fun onSuccess(
                            user: CognitoUser?,
                            signUpResult: com.amazonaws.services.cognitoidentityprovider.model.SignUpResult?
                        ) {
                            Log.d("CognitoLab", "Sign up success ${signUpResult?.userConfirmed}")
                            var i = Intent(v.context, LoginActivity::class.java)
                            startActivity(i)

                        }

                        override fun onFailure(exception: Exception?) {
                            Log.d(
                                "CognitoLab", "Exception : ${
                                    exception?.message
                                }"
                            )
                        }
                    }
                )

            }
        }
    }


}