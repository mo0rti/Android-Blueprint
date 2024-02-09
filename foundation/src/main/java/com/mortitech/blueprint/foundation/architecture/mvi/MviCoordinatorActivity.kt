package com.mortitech.blueprint.foundation.architecture.mvi

import android.os.Bundle
import androidx.activity.addCallback
import androidx.annotation.IdRes
import androidx.annotation.NavigationRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.viewbinding.ViewBinding
import com.mortitech.blueprint.foundation.architecture.ActivityLayoutInflater
import com.mortitech.blueprint.foundation.navigation.Coordinator
import com.mortitech.blueprint.foundation.navigation.CoordinatorHost
import com.mortitech.blueprint.foundation.navigation.StartDestination
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

abstract class MviCoordinatorActivity<
        VB : ViewBinding,
        CR : Coordinator,
        VS : ViewState,
        VA : ViewAction,
        VE : ViewEvent,
        VM : MviViewModel<VS, VA, VE>>
constructor(
    private val activityLayoutInflater: ActivityLayoutInflater<VB>,
): AppCompatActivity(), MviView<VB, VS, VA, VE, VM>, CoordinatorHost<CR> {

    @get:NavigationRes
    abstract val graphId: Int

    @get:IdRes
    abstract val navHostFragmentId: Int

    @get:IdRes
    abstract val toolbarId: Int?

    protected var toolbar: Toolbar? = null
    protected lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var _binding: VB
    override val binding: VB
        get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = activityLayoutInflater.invoke(layoutInflater)
        setContentView(_binding.root)

        setupNavigationCoordinator()
        setupToolbar()
        setupUI()

        // Observe view state changes and view events
        viewModel.viewState.onEach { onViewStateChanged(it) }.launchIn(lifecycleScope)
        viewModel.viewEvent.onEach { onViewEventReceived(it) }.launchIn(lifecycleScope)
    }

    private fun setupNavigationCoordinator() {
        val host = supportFragmentManager.findFragmentById(navHostFragmentId) as NavHostFragment
        coordinator.navHostFragment = host
        coordinator.activity = this
        coordinator.navController = host.navController

        setupNavigationGraph(graphId = graphId, host = host, startDestination = coordinator.onStart())
        coordinator.navController?.let {
            appBarConfiguration = AppBarConfiguration(it.graph)
            this.onBackPressedDispatcher.addCallback(this) {
                it.navigateUp(appBarConfiguration)
            }
        }
    }

    private fun setupNavigationGraph(
        @NavigationRes graphId: Int,
        host: NavHostFragment,
        startDestination: StartDestination,
    ) {
        val inflater = host.navController.navInflater
        val graph = inflater.inflate(graphId)
        graph.setStartDestination(startDestination.destination)
        host.navController.setGraph(graph, startDestination.args)
    }

    private fun setupToolbar() {
        toolbarId?.let {
            toolbar = findViewById(it)
            setSupportActionBar(toolbar)
            coordinator.navController?.let { navController ->
                setupActionBarWithNavController(navController)
            }
            toolbar?.setNavigationIcon(null)
        }
    }
}
