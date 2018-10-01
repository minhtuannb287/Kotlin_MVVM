package n.com.myapplication.di


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import n.com.myapplication.screen.main.MainActivityViewModel
import n.com.myapplication.screen.user.UserViewModel

@Suppress("unused")
@Module
abstract class ViewModelModule {

  @Binds
  @IntoMap
  @ViewModelKey(MainActivityViewModel::class)
  abstract fun bindMainActivityViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(UserViewModel::class)
  abstract fun bindUserViewModel(userViewModel: UserViewModel): ViewModel

  @Binds
  abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory
}