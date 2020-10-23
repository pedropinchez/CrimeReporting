package com.example.crimereporting.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {

    private Context ctx;
    private SharedPreferences default_prefence;

    public SharedPref(Context context) {
        this.ctx = context;
        default_prefence = context.getSharedPreferences("crimereporting", Context.MODE_PRIVATE);
    }
        public void setUserId(String userId) {
            default_prefence.edit().putString("userId", userId).apply();
        }

        public String getuserId() {
            return default_prefence.getString("userId", null);
        }
    public void setimage(String image) {
        default_prefence.edit().putString("image", image).apply();
    }

    public String getimage() {
        return default_prefence.getString("image", null);
    }
    public void setpic(String pic) {
        default_prefence.edit().putString("pic", pic).apply();
    }

    public String getpic() {
        return default_prefence.getString("pic", null);
    }
    public void setname(String name) {
        default_prefence.edit().putString("name", name).apply();
    }

    public String getname() {
        return default_prefence.getString("name", null);
    }
    public void setusername(String username) {
        default_prefence.edit().putString("username", username).apply();
    }

    public String getusername() {
        return default_prefence.getString("username", null);
    }
    public void setIsGuest(boolean isGuest){

        default_prefence.edit().putBoolean("isGuest", isGuest).apply();
    }

    public boolean getIsGuest(){
        return default_prefence.getBoolean("isGuest", true);

    }












    public int getCount() {
        return default_prefence.getInt("count", 0);
    }

    public void setCount(int count) {
        default_prefence.edit().putInt("count", count).apply();
    }

  


}
