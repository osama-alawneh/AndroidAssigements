package com.example.homework3

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.homework3.databinding.ActivityMainBinding
import java.io.Serializable
import javax.sql.DataSource

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding;
    lateinit var users: ArrayList<User>;
    var registrationLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val createdUser: User? = result.data?.getSerializableExtra("createdUser") as User;
            if(createdUser != null)
                users.add(createdUser)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        users = initializeUsers()
        singInBtnOnClickEvent();
        createAccountBtnClickEvent();
        forgetPasswordBtnEvent();
    }


    private fun singInBtnOnClickEvent() {

        binding.signInBtn.setOnClickListener() {
            val emailAddress = getEmailAddressValue();
            val password = getPasswordValue();
            val tempUser = User("", "", emailAddress, password)
            if (!userAuthenticated(tempUser)) {
                Toast.makeText(
                    this,
                    "The entered credentials are not authorized, you can create an account first",
                    Toast.LENGTH_LONG
                ).show();
            } else {
                sendDataToShopCategory(tempUser)
            }
        }
    }

    private fun createAccountBtnClickEvent(){
       binding.createAccountBtn.setOnClickListener(){
           moveToCreateAccountActivity();
       }
    }

    private fun forgetPasswordBtnEvent(){
        binding.forgetPasswordText.setOnClickListener(){
            var emailAddress = binding.emailAddressInput.text?.toString();
            if(emailAddress == null)
                Toast.makeText(this, "Please provide an email address", Toast.LENGTH_SHORT).show()
            else{
                var user = users.find { u -> u.emailAddress.equals(emailAddress) }

                user?.let {
                    val intent = Intent(Intent.ACTION_SENDTO)
                    intent.data = Uri.parse("mailto:")
                    intent.putExtra(Intent.EXTRA_EMAIL, user.emailAddress)
                    intent.putExtra(Intent.EXTRA_SUBJECT, "subject")
                    intent.putExtra(Intent.EXTRA_TEXT, user.password)
                    startActivity(intent)
                }
            }
        }
    }

    private fun getEmailAddressValue(): String {
        val emailAddress = binding.emailAddressInput.text.toString();
        return emailAddress;
    }

    private fun getPasswordValue(): String {
        val emailAddress = binding.passwordInput.text.toString();
        return emailAddress;
    }

    private fun initializeUsers(): ArrayList<User> {
        return arrayListOf(
            (User("osama", "alawneh", "h.a.alawneh9@gmail.com", "12345678")),
            (User("ghaith", "alawneh", "g.a.alawneh9@gmail.com", "12345678")),
            (User("laith", "alawneh", "l.a.alawneh9@gmail.com", "12345678")),
            (User("ahmad", "alawneh", "a.a.alawneh9@gmail.com", "12345678")),
            (User("marwan", "alawneh", "m.a.alawneh9@gmail.com", "12345678"))
        )
    }

    private fun userAuthenticated(user: User): Boolean {
        val firstUser = this.users.stream()
            .filter { u -> u.emailAddress.equals(user.emailAddress) && u.password.equals(user.password) }
            .findFirst();

        return firstUser.isPresent
    }

    private fun sendDataToShopCategory(user: User) {
        val shopIntent = Intent(this, ShopCategories::class.java);
        shopIntent.putExtra("user", user as Serializable)
        startActivity(shopIntent)
    }

    private fun moveToCreateAccountActivity(){
        val intent = Intent(this, RegistrationActivity :: class.java)
        registrationLauncher.launch(intent)
    }
}