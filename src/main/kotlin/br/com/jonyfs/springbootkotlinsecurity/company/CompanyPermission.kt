package br.com.jonyfs.springbootkotlinsecurity.company

import br.com.jonyfs.springbootkotlinsecurity.user.User
import java.io.Serializable
import javax.persistence.*
import javax.validation.constraints.NotNull


/**
 * Created by jony on 03/06/17.
 */
@Entity
@Table(uniqueConstraints = arrayOf(UniqueConstraint(columnNames = arrayOf("user_id", "privilege_id", "company_id"))))
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
class CompanyPermission : Serializable {

    @AttributeOverrides(*arrayOf(
            AttributeOverride(
                    name = "user",
                    column = Column(name = "user_id"))
            , AttributeOverride(
            name = "privilege",
            column = Column(name = "privilege_id")),
            AttributeOverride(
                    name = "company",
                    column = Column(name = "company_id"))
    ))
    @EmbeddedId
    @NotNull
    var pk: CompanyPermissionPK? = null

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private val user: User? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CompanyPermission) return false

        if (pk != other.pk) return false

        return true
    }

    override fun hashCode(): Int {
        return pk?.hashCode() ?: 0
    }


}