package com.example.mypratice_navigationandtoolbar_drawerlayout_6

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.mypratice_navigationandtoolbar_drawerlayout_6.databinding.ActivityMainBinding
import com.example.mypratice_navigationandtoolbar_drawerlayout_6.databinding.DrawerlayoutHeaderBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //加載menu
        binding.navDrawerView.inflateMenu(R.menu.menu_drawer)

        //方法1.用ViewBinding的方式，加載drawerLayout
        val headerLayoutBinding = DrawerlayoutHeaderBinding.inflate(layoutInflater)
        binding.navDrawerView.addHeaderView(headerLayoutBinding.root)

        //方法2.直接用inflateHeaderView()，打氣兼指派HeaderView
        val drawerView = binding.navDrawerView.inflateHeaderView(R.layout.drawerlayout_header)

        //方法3.用傳統方法
        val headerLayout_1 = layoutInflater.inflate(R.layout.drawerlayout_header, binding.navDrawerView, false)
        binding.navDrawerView.addHeaderView(headerLayout_1)

        //方法4.直接在"NavigationView"元件的Headerlayout屬性裡指定


        //取得navController
        val navController = binding.fragmentContainerView.getFragment<NavHostFragment>().navController

        //將ToolBar和navigationView和navController綁定
        //這邊setupWithNavController()加載的第二個參數是drawerLayout，而非appBarConfiguration
        //不放drawerLayout只放navController也可以運作，但是toolBar的navigationIcon會被遮住
        binding.materialToolbar.setupWithNavController(navController)
        binding.navDrawerView.setupWithNavController(navController)


        headerLayoutBinding.headerButton.setOnClickListener {
            Toast.makeText(this, "HeaderLayout Button click", Toast.LENGTH_SHORT).show()
        }
    }
}