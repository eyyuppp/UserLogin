package com.eyyuperdogan.userlogin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.eyyuperdogan.userlogin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.btnGirisYapLogin.setOnClickListener() {
            var username:String=""
            var password:String=""

            val userNameLogin = binding.kullaniciAdiLogin.text.toString()
            val passwordLogin = binding.parolaLogin.text.toString()
            if (userNameLogin.equals("") && passwordLogin.equals("")) {
                Toast.makeText(this, "kullanıcı adı ve şifre boş olamaz!!", Toast.LENGTH_LONG).show()
            } else if (userNameLogin.equals("")) {
                Toast.makeText(this, "kullanıcı adı boş olamaz!!", Toast.LENGTH_LONG).show()
            } else if (passwordLogin.equals("")) {
                Toast.makeText(this, "şifre boş olamaz!!", Toast.LENGTH_LONG).show()
            } else {
                try {
                    database = this.openOrCreateDatabase("Register", Context.MODE_PRIVATE, null)
                    database.execSQL("CREATE TABLE IF NOT EXISTS register(username VARCHAR,password VARCHAR )")
                    var cursor = database.rawQuery("SELECT*FROM register", null)
                    val usernameıx = cursor.getColumnIndex("username")
                    val passwordıx = cursor.getColumnIndex("password")
                    while (cursor.moveToNext()) {
                        username = cursor.getString(usernameıx)
                        password = cursor.getString(passwordıx)
                        val know = Know(username, password)

                        if ((userNameLogin==username) && (passwordLogin==password) ){
                            intent= Intent(applicationContext,WelcomeUser::class.java)
                            intent.putExtra("username",userNameLogin)
                            intent.putExtra("password",passwordLogin)
                            startActivity(intent)
                            break
                        }
                        registerList.add(know)
                    }
                    cursor.close()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                 if ((userNameLogin!=username) || (passwordLogin!=password) ){
                    Toast.makeText(this, "kullanıcıadı veya şifre hatalı", Toast.LENGTH_LONG).show()
                }
            }
        }





        binding.btnKayitOlLogin.setOnClickListener(){
            intent= Intent(applicationContext,Register::class.java)
            startActivity(intent)
        }
    }
}