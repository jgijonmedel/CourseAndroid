package com.jimmy.courseandroid.utils

data class User(val email: String = "", val password: String = "")

object UserManager {
    private var user: User? = null

    fun setUser(user: User) {
        this.user = user
    }

    fun getInstanceUser(): User {
        if (user != null)
            return user as User
        else {
            throw Exception("El usuario no ha sido asignado")
        }
    }
}