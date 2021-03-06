package lookid_front.lookid.Control

import android.content.Context
import android.content.Context.MODE_PRIVATE
import lookid_front.lookid.Entity.User_Entity

class User_Control(context: Context){
    val sharedPreferences  = context.getSharedPreferences("User_Info", MODE_PRIVATE)
    val editPreferences = sharedPreferences.edit()

    fun set_token(token : String) {editPreferences.putString("token",token).apply()}
    fun set_auto_login(auto_login : Boolean) {editPreferences.putBoolean("auto_login",auto_login).apply()}
    fun set_user(user : User_Entity) {
        editPreferences.putString("name",user.name)
        editPreferences.putString("phone",user.phone)
        editPreferences.putString("email",user.email)
        editPreferences.putString("address",user.address)
        editPreferences.putString("bank_name",user.bank_name)
        editPreferences.putString("bank_number",user.bank_number)
        editPreferences.putString("bank_holder",user.bank_holder)
    }

    fun get_token() : String? { return sharedPreferences.getString("token",null)}
    fun get_auto_login() : Boolean { return sharedPreferences.getBoolean("auto_login",false)}
    fun get_user() : User_Entity?{
        var user = User_Entity(
                "", sharedPreferences.getString("name", null), sharedPreferences.getString("phone", null)
                , sharedPreferences.getString("email", null), sharedPreferences.getString("address", null), sharedPreferences.getString("bank_name", null)
                , sharedPreferences.getString("bank_number", null), sharedPreferences.getString("bank_holder", null)
        )
        return user
    }
}