package br.com.jonyfs.springbootkotlinsecurity.group

import br.com.jonyfs.springbootkotlinsecurity.privilege.Privilege
import br.com.jonyfs.springbootkotlinsecurity.user.User
import java.io.Serializable
import javax.persistence.Embeddable

/**
 * Created by jony on 03/06/17.
 */
@Embeddable
class GroupPermissionPK : Serializable {

    //@ManyToOne(optional = false)
    //@JoinColumn(name = "user_id")
    var user: User? = null

    //@ManyToOne(optional = false)
    //@JoinColumn(name = "privilege_id")
    var privilege: Privilege? = null

    //@ManyToOne(optional = false)
    //@JoinColumn(name = "group_id")
    var group: Group? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is GroupPermissionPK) return false

        if (user != other.user) return false
        if (privilege != other.privilege) return false
        if (group != other.group) return false

        return true
    }

    override fun hashCode(): Int {
        var result = user?.hashCode() ?: 0
        result = 31 * result + (privilege?.hashCode() ?: 0)
        result = 31 * result + (group?.hashCode() ?: 0)
        return result
    }


}
