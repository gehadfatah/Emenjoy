package com.goda.emenjoy.presentation.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.goda.emenjoy.R
import com.goda.emenjoy.presentation.common.replaceFragment
import com.goda.emenjoy.presentation.main.home.HomeFragment_FRAGMENT_TAG
import com.goda.emenjoy.presentation.main.home.newHomeFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.nav_header_home.*
import nl.psdcompany.duonavigationdrawer.widgets.DuoDrawerToggle


class MainActivity : AppCompatActivity() {
    private val homeFragment by lazy { newHomeFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

    /*    val toggle = ActionBarDrawerToggle(
                this,
                drawer_layout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        )
        drawer_layout.(toggle)
        toggle.syncState()
        toggle.isDrawerIndicatorEnabled = true*/
        val drawerToggle =  object :DuoDrawerToggle(this, drawer_layout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close){
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                nav.visibility=View.VISIBLE

            }

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
                nav.visibility=View.GONE
            }
        }
        drawer_layout.setDrawerListener(drawerToggle)

        drawerToggle.syncState()

        if (savedInstanceState == null) replaceFragment(R.id.container, homeFragment, HomeFragment_FRAGMENT_TAG)
    }



    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
