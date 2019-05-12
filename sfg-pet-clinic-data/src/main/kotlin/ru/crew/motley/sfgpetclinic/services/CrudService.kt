package ru.crew.motley.sfgpetclinic.services

interface CrudService<T, ID> {

    fun findAll() : Set<T>

    fun findById(id: ID): T?

    fun save(entity: T): T

    fun delete(entity: T)

    fun deleteById(id: ID)
}