package com.nshinn.marvellimited.persistence.repository

import com.nshinn.marvellimited.persistence.dao.BaseDao

open class BaseRepository<T>(val dao: BaseDao<T>) {

    fun insertAll(objList: List<T>): IntArray {
        return dao.insertAll(objList).map { it.toInt() }.toIntArray()
    }

    fun insert(obj: T): Int {
        return dao.insert(obj).toInt()
    }

    fun update(obj: T) {
        dao.update(obj)
    }

    fun delete(obj: T) {
        dao.delete(obj)
    }

}