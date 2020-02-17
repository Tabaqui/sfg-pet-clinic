package ru.crew.motley.sfgpetclinic.services.map

import ru.crew.motley.sfgpetclinic.model.AbstractJpaPersistable

abstract class AbstractMapService<T : AbstractJpaPersistable<ID>, ID : Long> {

    protected val map: MutableMap<ID, T> = mutableMapOf()

    fun findAll(): Set<T> = HashSet(map.values)

    fun findById(id: ID): T? = map[id]

    open fun save(entity: T): T {

        if (entity.getId() == null) {
            entity.setId(getNextId())
        }

        map[entity.getId()!!] = entity
        return entity
    }

    fun deleteById(id: ID) {
        map.remove(id)
    }

    fun delete(entity: T) {
        map.entries.removeIf { entity == it.value }
    }

    private fun getNextId(): ID {
        return if (map.keys.isEmpty())
            1L as ID
        else
            (map.keys.max() ?: 0L + 1L) as ID
    }
}