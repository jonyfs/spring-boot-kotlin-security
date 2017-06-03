package br.com.jonyfs.springbootkotlinsecurity.user

import br.com.jonyfs.springbootkotlinsecurity.company.CompanyPermission
import br.com.jonyfs.springbootkotlinsecurity.group.GroupPermission
import br.com.jonyfs.springbootkotlinsecurity.model.BaseEntity
import br.com.jonyfs.springbootkotlinsecurity.util.BCryptPasswordDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import org.hibernate.validator.constraints.NotEmpty
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.OneToMany


/**
 * Created by jony on 25/05/17.
 */
@Entity
class User : BaseEntity() {

    @NotEmpty
    var firstName: String? = null

    @NotEmpty
    var lastName: String? = null

    @NotEmpty
    var email: String? = null

    @JsonDeserialize(using = BCryptPasswordDeserializer::class)
    @NotEmpty
    var password: String? = null

    var enabled: Boolean = false

    var tokenExpired: Boolean = false

    @OneToMany(mappedBy = "user", cascade = arrayOf(CascadeType.ALL))
    var groupPermissions: MutableList<GroupPermission>? = mutableListOf<GroupPermission>()

    @OneToMany(mappedBy = "user", cascade = arrayOf(CascadeType.ALL))
    var companyPermissions: MutableList<CompanyPermission>? = mutableListOf<CompanyPermission>()


}
