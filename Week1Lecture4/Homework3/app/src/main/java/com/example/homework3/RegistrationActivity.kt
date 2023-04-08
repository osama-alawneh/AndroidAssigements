package com.example.homework3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.homework3.databinding.ActivityMainBinding
import com.example.homework3.databinding.ActivityRegistrationBinding
import java.io.Serializable

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createNewAccountBtnOnClickEvent();
    }

    private fun createNewAccountBtnOnClickEvent(){
        binding.createAccountBtn.setOnClickListener(){
            val allFieldsAreValid = checkIfAllRequiredFieldsContainsData()
            if(!allFieldsAreValid){
                Toast.makeText(this, "Please make sure to fill email address asd password", Toast.LENGTH_LONG).show();
            }
            val createdUser = createNewUser();
            sendCreatedUserToLoginPage(createdUser);
        }

    }

    private fun getFirstName() : String{
        val firstName = binding.firstNameInput.text;
        if(firstName.isEmpty())
            return ""
        return firstName.toString()
    }
    private fun getLastName() : String{
        val lastName = binding.lastNameInput.text;
        if(lastName.isEmpty())
            return ""
        return lastName.toString()
    }
    private fun getEmailAddress() : String{
        val emailAddress = binding.emailAddressInput.text;
        if(emailAddress.isEmpty())
            return ""
        return emailAddress.toString()
    }
    private fun getPassword() : String{
        val password = binding.passwordInput.text;
        if(password.isEmpty())
            return ""
        return password.toString()
    }
    private fun checkIfAllRequiredFieldsContainsData() : Boolean{
        val emailAddress = binding.emailAddressInput.text;
        val password = binding.passwordInput.text;
        return !(emailAddress.isEmpty() || password.isEmpty())
    }
    private fun createNewUser() : User{
        val firstName = getFirstName();
        val lastName = getLastName();
        val emailAddress = getEmailAddress();
        val password = getPassword();
        val newUser = User(firstName, lastName, emailAddress, password);
        return newUser
    }
    private fun sendCreatedUserToLoginPage(createdUser : User){
        val loginIntent = Intent();
        loginIntent.putExtra("createdUser", createdUser);
        setResult(RESULT_OK, loginIntent);
        finish()
    }
}