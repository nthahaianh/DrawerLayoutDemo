package com.example.drawerlayoutdemo

import android.app.Dialog
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog.*


class MainActivity : AppCompatActivity() {
    private var drawerToggle: ActionBarDrawerToggle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        drawerToggle = ActionBarDrawerToggle(this, drawer_layout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(drawerToggle!!)

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.search -> {
                    Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show()
                    drawer_layout.closeDrawer(GravityCompat.START)
                    showDialog()
                    true
                }
                R.id.item1 -> {
                    replaceFragment(Fragment1())
                    drawer_layout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.item2 -> {
                    replaceFragment(Fragment2())
                    drawer_layout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.item3 -> {
                    replaceFragment(Fragment3())
                    drawer_layout.closeDrawer(GravityCompat.START)
                    true
                }
            }
            false
        }
    }

    fun onClick(view: View) {
        drawer_layout.openDrawer(Gravity.LEFT)
    }

    private fun replaceFragment(fragment: Fragment){
        val transaction = supportFragmentManager?.beginTransaction()
        transaction.replace(R.id.main_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        drawerToggle!!.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        drawerToggle!!.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        return if (drawerToggle?.onOptionsItemSelected(item)!!) {
            true
        } else super.onOptionsItemSelected(item)
        // Handle your other action bar items...
    }
    private fun showDialog(){
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog)

        val window = dialog.window ?: return

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val windowAttributes = window.attributes
        windowAttributes.gravity = Gravity.CENTER
        window.attributes = windowAttributes

        val btnClose = dialog.btnClose
        val etInput = dialog.etInput

        btnClose.setOnClickListener {
            Toast.makeText(baseContext, etInput.text, Toast.LENGTH_SHORT).show()
            dialog.dismiss() }
        dialog.show()
    }
}