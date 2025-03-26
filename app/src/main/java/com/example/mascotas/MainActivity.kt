package com.example.mascotas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.mascotas.R.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        // Configurar Toolbar
        val toolbar = findViewById<Toolbar>(id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(drawable.ic_menu)

        // Configurar Drawer
        drawerLayout = findViewById(id.drawer_layout)
        val navView = findViewById<NavigationView>(id.nav_view)

        // Configurar Bottom Navigation
        val bottomNavigation = findViewById<BottomNavigationView>(id.bottom_navigation)
        // Cargo este layout para que sea el por defecto
        loadFragment(layout.mascotashome_layout)

        // Configurar toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Manejar clics en Bottom Navigation
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                id.nav_redsocial -> {
                    // Aquí puedes cambiar el fragmento o mostrar contenido de Productos
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                    loadFragment(layout.mascotashome_layout)
                    true
                }

                id.nav_integrantes -> {
                    // Aquí puedes cambiar el fragmento o mostrar contenido de Servicios
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    loadFragment(layout.integrantes_layout)
                    true
                }

                else -> false
            }
        }
        fun loadFragment(fragment: Fragment) {
            supportFragmentManager.beginTransaction()
                .replace(id.fragment_container, fragment)
                .commit()
        }

        // Manejar clics en Navigation Drawer
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                id.nav_perfil -> {
                    // Acción para Perfil
                    loadFragment(layout.perfil_layout)
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                    true
                }

                id.nav_fotos -> {
                    // Acción para Fotos
                    loadFragment(layout.fotos_layout)
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                    true
                }

                id.nav_video -> {
                    // Acción para Soporte
                    loadFragment(layout.video_layout)
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                    true
                }

                id.nav_web -> {
                    // Acción para Soporte
                    loadFragment(layout.web_layout)
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                    true
                }

                id.nav_botones -> {
                    // Acción para Soporte
                    loadFragment(layout.botones_layout)
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                    true
                }

                else -> false
            }
            drawerLayout.closeDrawers()
            true
        }
        //Manejo los clics en las imagenes de las mascotas



    }
    //La clase para poder cargar los layouts
    private fun loadFragment(layoutResId: Int) {
        val fragment = DynamicFragment.newInstance(layoutResId)
        supportFragmentManager.beginTransaction()
            .replace(id.fragment_container, fragment)
            .commit()
    }

    // Para el toogle
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // Cierro el drawer con botón atrás
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    //Utilizo esta clase para poder cargar los layaouts de forma directa
    class DynamicFragment : Fragment() {

        private var layoutResId: Int = 0

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            arguments?.let {
                layoutResId = it.getInt(ARG_LAYOUT_ID)
            }
        }


        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(layoutResId, container, false)

        }

        companion object {
            private const val ARG_LAYOUT_ID = "layout_id"

            fun newInstance(layoutResId: Int): DynamicFragment {
                val fragment = DynamicFragment()
                val args = Bundle()
                args.putInt(ARG_LAYOUT_ID, layoutResId)
                fragment.arguments = args
                return fragment
            }
        }
    }
}