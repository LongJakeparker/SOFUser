package com.example.sofuser.util

import com.example.sofuser.SOFUserApplication
import com.example.sofuser.model.User

object SharedPreferenceManager {

    private val BOOKMARK_USER_OBJECT = "BOOKMARK_USER_OBJECT_"

    fun setBookmarkUser(user: User) {
        val tinyDB = TinyDB(SOFUserApplication.getAppContext())
        tinyDB.putObject(BOOKMARK_USER_OBJECT + user.userId, user)
    }

    fun getBookmarkUser(key: String): User? {
        val tinyDB = TinyDB(SOFUserApplication.getAppContext())
        return tinyDB.getObject(key, User::class.java)
    }

    fun getListBookmarkUser(): ArrayList<User> {
        var result = ArrayList<User>()
        val tinyDB = TinyDB(SOFUserApplication.getAppContext())
        val allEntries = tinyDB.all
        for (entry in allEntries.entries) {
            if (entry.key.contains(BOOKMARK_USER_OBJECT)) {
                if (getBookmarkUser(entry.key) != null) {
                    result.add(getBookmarkUser(entry.key)!!)
                }
            }
        }

        return result
    }

    fun removeBookmarkUser(userId: Int) {
        val tinyDB = TinyDB(SOFUserApplication.getAppContext())
        tinyDB.remove(BOOKMARK_USER_OBJECT + userId)
    }

    fun checkExist(userId: Int): Boolean {
        val tinyDB = TinyDB(SOFUserApplication.getAppContext())
        return tinyDB.isContainKey(BOOKMARK_USER_OBJECT + userId)
    }
}