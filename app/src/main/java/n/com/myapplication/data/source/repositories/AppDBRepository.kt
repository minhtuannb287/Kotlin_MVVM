package n.com.myapplication.data.source.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import n.com.myapplication.data.model.User
import n.com.myapplication.data.source.local.dao.AppDatabase
import n.com.myapplication.data.source.local.dao.UserEntity

interface AppDBRepository {

    fun getUsers(): LiveData<List<User>>

    suspend fun insertOrUpdateUser(user: User)

    fun updateAllUser(users: List<User>)

    fun deleteUser(user: User)
}

class AppDBRepositoryImpl
constructor(private val appDB: AppDatabase, private val gson: Gson) : AppDBRepository {

    override fun getUsers(): LiveData<List<User>> {
        return transformUserEntity(appDB.userDao().getUsers())
    }

    override suspend fun insertOrUpdateUser(user: User) {
        withContext(Dispatchers.IO) {
            val userEntity = UserEntity().userToEntity(user, gson)
            appDB.userDao().insertOrUpdateUser(userEntity)
        }
    }

    override fun updateAllUser(users: List<User>) {

    }

    override fun deleteUser(user: User) {
    }

    private fun transformUserEntity(data: LiveData<List<UserEntity>>): LiveData<List<User>> {
        return Transformations.map(data) { result ->
            val list = arrayListOf<User>()
            result?.forEach { entity ->
                list.add(entity.userFromEntity(gson))
            }
            list
        }
    }
}
