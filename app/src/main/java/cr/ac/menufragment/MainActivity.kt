package cr.ac.menufragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.view.MenuItem
import android.view.View

import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView



lateinit var drawerLayout: DrawerLayout

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var toolbar = findViewById<Toolbar>(R.id.toolbar)//Una variable que viene de la clase activity main, llamada toolbar
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)

        var toggle = ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()//para sincronizar el estado

        var navigationView = findViewById<NavigationView>(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener(this)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        val fragment : Fragment
        var title: Int
        when (item.itemId) {
            R.id.nav_camera -> {
                fragment = CamaraFragment.newInstance(getString(R.string.menu_camera))
                title = R.string.menu_camera
            }
            R.id.nav_gallery -> {
                fragment = GaleriaFragment.newInstance(getString(R.string.menu_gallery))
                title = R.string.menu_gallery
            }
            else -> {
            fragment = HomeFragment.newInstance(getString(R.string.homeFragment))
                 title = R.string.homeFragment

            }

        }

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.home_content, fragment)
            .commit()

        setTitle(getString(title))
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
    /*override fun onDrawerSlide(drawerView: View, slideOffset: Float){
        TODO("Not yet implemented")

    }*/
}