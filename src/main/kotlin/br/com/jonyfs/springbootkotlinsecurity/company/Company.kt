package br.com.jonyfs.springbootkotlinsecurity.company

import br.com.jonyfs.springbootkotlinsecurity.group.Group
import br.com.jonyfs.springbootkotlinsecurity.model.NamedEntity
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.ManyToMany
import javax.persistence.Table

/**
 * Created by jony on 03/06/17.
 */
@Entity
@Table // to evict user lacks privilege or object not found on hsqldb
class Company : NamedEntity() {

    @ManyToMany(mappedBy = "companies", cascade = arrayOf(CascadeType.ALL))
    var groups: Collection<Group>? = null
}