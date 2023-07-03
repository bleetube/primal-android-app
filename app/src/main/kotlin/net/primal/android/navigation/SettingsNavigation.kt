package net.primal.android.navigation

import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import net.primal.android.R
import net.primal.android.core.compose.DemoSecondaryScreen
import net.primal.android.core.compose.LockToOrientationPortrait
import net.primal.android.settings.home.PrimalSettingsSection
import net.primal.android.settings.home.SettingsHomeScreen
import net.primal.android.settings.home.SettingsHomeViewModel
import net.primal.android.settings.keys.KeysScreen
import net.primal.android.settings.keys.KeysViewModel


private fun NavController.navigateToKeys() = navigate(route = "keys_settings")
private fun NavController.navigateToWallet() = navigate(route = "wallet_settings")
private fun NavController.navigateToAppearance() = navigate(route = "appearance_settings")
private fun NavController.navigateToNotifications() = navigate(route = "notifications_settings")
private fun NavController.navigateToNetwork() = navigate(route = "network_settings")
private fun NavController.navigateToFeeds() = navigate(route = "feeds_settings")
private fun NavController.navigateToZaps() = navigate(route = "zaps_settings")

fun NavGraphBuilder.settingsNavigation(
    route: String,
    navController: NavController,
) = navigation(
    route = route,
    startDestination = "home_settings"
) {
    home(
        route = "home_settings",
        onClose = { navController.navigateUp() },
        onSettingsSectionClick = {
            when (it) {
                PrimalSettingsSection.Keys -> navController.navigateToKeys()
                PrimalSettingsSection.Wallet -> navController.navigateToWallet()
                PrimalSettingsSection.Appearance -> navController.navigateToAppearance()
                PrimalSettingsSection.Notifications -> navController.navigateToNotifications()
                PrimalSettingsSection.Network -> navController.navigateToNetwork()
                PrimalSettingsSection.Feeds -> navController.navigateToFeeds()
                PrimalSettingsSection.Zaps -> navController.navigateToZaps()
            }
        }
    )

    keys(route = "keys_settings", navController = navController)
    wallet(route = "wallet_settings", navController = navController)
    appearance(route = "appearance_settings", navController = navController)
    notifications(route = "notifications_settings", navController = navController)
    network(route = "network_settings", navController = navController)
    feeds(route = "feeds_settings", navController = navController)
    zaps(route = "zaps_settings", navController = navController)
}

private fun NavGraphBuilder.home(
    route: String,
    onClose: () -> Unit,
    onSettingsSectionClick: (PrimalSettingsSection) -> Unit,
) = composable(
    route = route,
) {
    val viewModel = hiltViewModel<SettingsHomeViewModel>(it)
    LockToOrientationPortrait()
    SettingsHomeScreen(
        viewModel = viewModel,
        onClose = onClose,
        onSettingsSectionClick = onSettingsSectionClick,
    )
}

private fun NavGraphBuilder.keys(
    route: String,
    navController: NavController,
) = composable(
    route = route,
) {
    val viewModel = hiltViewModel<KeysViewModel>(it)
    LockToOrientationPortrait()
    KeysScreen(
        viewModel = viewModel,
        onClose = { navController.navigateUp() }
    )
}

private fun NavGraphBuilder.wallet(route: String, navController: NavController) = composable(
    route = route,
) {
    LockToOrientationPortrait()
    DemoSecondaryScreen(
        title = stringResource(id = R.string.settings_wallet_title),
        description = "Wallet settings will appear here. Stay tuned.",
        onClose = { navController.navigateUp() },
    )
}

private fun NavGraphBuilder.appearance(route: String, navController: NavController) = composable(
    route = route,
) {
    LockToOrientationPortrait()
    DemoSecondaryScreen(
        title = stringResource(id = R.string.settings_appearance_title),
        description = "Appearance settings will appear here. Stay tuned.",
        onClose = { navController.navigateUp() },
    )
}

private fun NavGraphBuilder.notifications(route: String, navController: NavController) = composable(
    route = route,
) {
    LockToOrientationPortrait()
    DemoSecondaryScreen(
        title = stringResource(id = R.string.settings_notifications_title),
        description = "Notifications settings will appear here. Stay tuned.",
        onClose = { navController.navigateUp() },
    )
}

private fun NavGraphBuilder.network(route: String, navController: NavController) = composable(
    route = route,
) {
    LockToOrientationPortrait()
    DemoSecondaryScreen(
        title = stringResource(id = R.string.settings_network_title),
        description = "Network settings will appear here. Stay tuned.",
        onClose = { navController.navigateUp() },
    )
}

private fun NavGraphBuilder.feeds(route: String, navController: NavController) = composable(
    route = route,
) {
    LockToOrientationPortrait()
    DemoSecondaryScreen(
        title = stringResource(id = R.string.settings_feeds_title),
        description = "Feeds settings will appear here. Stay tuned.",
        onClose = { navController.navigateUp() },
    )
}

private fun NavGraphBuilder.zaps(route: String, navController: NavController) = composable(
    route = route,
) {
    LockToOrientationPortrait()
    DemoSecondaryScreen(
        title = stringResource(id = R.string.settings_zaps_title),
        description = "Zaps settings will appear here. Stay tuned.",
        onClose = { navController.navigateUp() },
    )
}
