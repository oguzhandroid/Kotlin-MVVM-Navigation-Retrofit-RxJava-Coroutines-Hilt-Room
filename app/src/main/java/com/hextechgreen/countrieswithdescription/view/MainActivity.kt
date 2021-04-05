package com.hextechgreen.countrieswithdescription.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.hextechgreen.countrieswithdescription.R

class MainActivity : AppCompatActivity() {

    private lateinit var navigationController : NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // İki Fragment arasında geçiş yaptığımızda geri tuşunu kullanabilmek için ;
        navigationController = Navigation.findNavController(this,
            R.id.fragment
        )
        NavigationUI.setupActionBarWithNavController(this,navigationController)
    }
    // İki Fragment arasında geçiş yaptığımızda geri tuşunu kullanabilmek için ;
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navigationController,null)
    }
}
