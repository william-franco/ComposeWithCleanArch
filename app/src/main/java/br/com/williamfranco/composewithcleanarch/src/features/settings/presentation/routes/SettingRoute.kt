package br.com.williamfranco.composewithcleanarch.src.features.settings.presentation.routes

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.williamfranco.composewithcleanarch.src.features.settings.presentation.view_models.SettingViewModel
import br.com.williamfranco.composewithcleanarch.src.features.settings.presentation.view_models.SettingViewModelImpl
import br.com.williamfranco.composewithcleanarch.src.features.settings.presentation.views.SettingView
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingRoute(
    onBack: () -> Unit,
) {
    val activity = LocalContext.current as ComponentActivity
    val settingViewModel: SettingViewModel = koinViewModel<SettingViewModelImpl>(viewModelStoreOwner = activity)
    val setting by settingViewModel.state.collectAsStateWithLifecycle()

    SettingView(
        setting = setting,
        onBack = onBack,
        onThemeChanged = settingViewModel::changeTheme,
    )
}
