package ru.crew.motley.sfgpetclinic.services.map

abstract class AbstractMapService<T, ID> {

    protected val map: MutableMap<ID, T> = mutableMapOf()

    fun findAll(): Set<T> = HashSet(map.values)

    fun findById(id: ID): T? = map[id]

    fun save(id: ID, entity: T): T {
        map[id] = entity
        return entity
    }

    fun deleteById(id: ID) {
        map.remove(id)
    }

    fun delete(entity: T) {
        map.entries.removeIf{ entity == it.value}
    }
}