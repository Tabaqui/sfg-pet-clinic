package ru.crew.motley.sfgpetclinic.model

import org.springframework.data.util.ProxyUtils
import java.io.Serializable
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class AbstractJpaPersistable<T : Serializable> : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: T? = null

    fun setId(id: T) {
        this.id = id
    }

    fun getId(): T? = id

    fun isNew(): Boolean = id == null

    override fun equals(other: Any?): Boolean {
        other ?: return false
        if (this === other) return true
        if (javaClass != ProxyUtils.getUserClass(other)) return false
        other as AbstractJpaPersistable<*>
        return if (null == this.getId())
            false
        else
            this.getId() == other.getId()
    }

    override fun hashCode(): Int {
        return 31
    }

    override fun toString(): String {
        return "Entity of type ${this.javaClass.name} with id: $id"
    }
}