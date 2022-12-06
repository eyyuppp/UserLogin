package com.eyyuperdogan.userlogin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.eyyuperdogan.userlogin.databinding.ActivityWelcomeUserBinding
class WelcomeUser : AppCompatActivity() {
    lateinit var binding: ActivityWelcomeUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityWelcomeUserBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var userName=intent.getStringExtra("username")
        var password=intent.getStringExtra("password")
        binding.wUserName.setText("Kullanıcı Adı:"+userName.toString())
        binding.wPassword.setText("Şifre :"+password.toString())



       //anasayfaya dön
        binding.btnExT.setOnClickListener() {
            intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }







        //kayıt silme
        binding.btnDelete.setOnClickListener(){
            var id:Int=1
            try {
                database = this.openOrCreateDatabase("Register",Context.MODE_PRIVATE, null)
                database.execSQL("CREATE TABLE IF NOT EXISTS register(ıd INTEGER PRIMARY KEY,username VARCHAR,password VARCHAR )")
                database.execSQL("DELETE FROM register WHERE username=? ", arrayOf(binding.wUserName.toString()))
                var cursor=database.rawQuery("SELECT*FROM register  ",null)
                val usernameıx=cursor.getColumnIndex(binding.wUserName.text.toString())
                val passwordıx=cursor.getColumnIndex(password)
                while (cursor.moveToNext()){
                    val username1=cursor.getString(usernameıx)
                    val password1=cursor.getString(passwordıx)
                    val know1=Know(username1,password1)
                    registerList.add(know1)
                }
                cursor.close()
            }
            catch (e :Exception){
                e.printStackTrace()
            }
            Toast.makeText(this,userName+" "+password+" Kaydı silindi", Toast.LENGTH_LONG).show()
            intent=Intent(this,MainActivity::class.java)
            startActivity(intent)

        }




    }
}