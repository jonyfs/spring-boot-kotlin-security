package br.com.jonyfs.springbootkotlinsecurity.group

import br.com.jonyfs.springbootkotlinsecurity.company.Company
import br.com.jonyfs.springbootkotlinsecurity.model.NamedEntity
import javax.persistence.*


/**
 * Created by jony on 25/05/17.
 */
@Entity
@Table(name = "company_group") // to evict user lacks privilege or object not found on hsqldb
class Group : NamedEntity() {

    @ManyToMany(cascade = arrayOf(CascadeType.ALL))
    @JoinTable(name = "group_companies",
            joinColumns = arrayOf(
                    JoinColumn(name = "goup_id",
                            referencedColumnName = "id")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "company_id",
                    referencedColumnName = "id")))
    var companies: Collection<Company>? = null

}