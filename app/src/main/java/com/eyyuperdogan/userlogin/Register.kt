package com.eyyuperdogan.userlogin

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.eyyuperdogan.userlogin.databinding.ActivityRegisterBinding
import com.eyyuperdogan.userlogin.databinding.ActivityWelcomeUserBinding
import com.eyyuperdogan.userlogin.databinding.RecyclerRowBinding

lateinit var database: SQLiteDatabase
lateinit var registerList:ArrayList<Know>
private lateinit var holder: Holder
class Register : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        registerList=ArrayList<Know>()
        holder= Holder(registerList)
        binding.recyclerRow.layoutManager=LinearLayoutManager(this)
        binding.recyclerRow.adapter= holder





       //kayıtları listede göstermek
            try {
                database = this.openOrCreateDatabase("Register", Context.MODE_PRIVATE, null)
                database.execSQL("CREATE TABLE IF NOT EXISTS register(ıd INTEGER PRIMARY KEY,username VARCHAR,password VARCHAR )")
                var cursor = database.rawQuery("SELECT*FROM register", null)
                val usernameıx = cursor.getColumnIndex("username")
                val passwordıx = cursor.getColumnIndex("password")
                while (cursor.moveToNext()) {
                    val username = cursor.getString(usernameıx)
                    val password = cursor.getString(passwordıx)
                    val know = Know(username, password)
                    registerList.add(know)
                }
                holder.notifyDataSetChanged()
                cursor.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        



       //kayıt etme
        binding.btnRegister.setOnClickListener(){
            val userName=binding.userName.text.toString()
            val password=binding.password.text.toString()
            if (userName.equals("") && password.equals("")){
                Toast.makeText(this,"kullanıcı adı ve şifre boş olamaz!!",Toast.LENGTH_LONG).show()
            }
            else if (userName.equals("")){
                Toast.makeText(this,"kullanıcı adı boş olamaz!!",Toast.LENGTH_LONG).show()
            }
            else if (password.equals("")){
                Toast.makeText(this,"şifre boş olamaz!!",Toast.LENGTH_LONG).show()
            }
            else {
                try {
                    database = this.openOrCreateDatabase("Register",Context.MODE_PRIVATE, null)
                    database.execSQL("CREATE TABLE IF NOT EXISTS register(username VARCHAR,password VARCHAR )")
                    val sqlString=  "INSERT INTO register(username,password) VALUES (?,?)"
                    val statement= database.compileStatement(sqlString)
                    statement.bindString(1,userName)
                    statement.bindString(2,password)
                    statement.execute()
                    var cursor=database.rawQuery("SELECT*FROM register",null)
                    val usernameıx=cursor.getColumnIndex("username")
                    val passwordıx=cursor.getColumnIndex("password")
                    while (cursor.moveToNext()){
                        val username1=cursor.getString(usernameıx)
                        val password1=cursor.getString(passwordıx)
                        val know1=Know(username1,password1)

                        registerList.add(know1)
                    }
                    holder.notifyDataSetChanged()
                    cursor.close()
                    intent= Intent(applicationContext,WelcomeUser::class.java)
                    val userName=binding.userName.text.toString()
                    val password=binding.password.text.toString()
                    intent.putExtra("username",userName)
                    intent.putExtra("password",password)
                    startActivity(intent)

                }
                catch (e :Exception){
                    e.printStackTrace()
                }
                Toast.makeText(this,"Kayıt Başarılı",Toast.LENGTH_LONG).show()

            }
        }





       //MainActivity ye geçiş yapma
        binding.btnAnasayfa.setOnClickListener(){
            intent= Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
        }
    }
}


