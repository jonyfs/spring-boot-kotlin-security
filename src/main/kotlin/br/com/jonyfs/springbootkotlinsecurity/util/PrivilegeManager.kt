package br.com.jonyfs.springbootkotlinsecurity.util

import br.com.jonyfs.springbootkotlinsecurity.company.Company
import br.com.jonyfs.springbootkotlinsecurity.company.CompanyPermission
import br.com.jonyfs.springbootkotlinsecurity.company.CompanyPermissionPK
import br.com.jonyfs.springbootkotlinsecurity.group.Group
import br.com.jonyfs.springbootkotlinsecurity.group.GroupPermission
import br.com.jonyfs.springbootkotlinsecurity.group.GroupPermissionPK
import br.com.jonyfs.springbootkotlinsecurity.privilege.Privilege
import br.com.jonyfs.springbootkotlinsecurity.user.User
import br.com.jonyfs.springbootkotlinsecurity.user.UserRepository
import org.springframework.stereotype.Component
import javax.annotation.Resource

/**
 * Created by jony on 03/06/17.
 */
@Component
class PrivilegeManager {

    @Resource
    lateinit var userRepository: UserRepository

    fun add(user: User, privileges: Collection<Privilege>, group: Group) {
        var pk: GroupPermissionPK
        var groupPermission: GroupPermission
        var permissions: MutableList<GroupPermission> = mutableListOf<GroupPermission>()
        for (privilege in privileges) {
            pk = GroupPermissionPK()
            pk.user = user
            pk.privilege = privilege
            pk.group = group
            groupPermission = GroupPermission()
            groupPermission.pk = pk
            permissions.add(groupPermission)
        }

        user.groupPermissions!!.addAll(permissions)

        userRepository.save(user)
    }

    fun add(user: User, privileges: Collection<Privilege>, company: Company) {
        var pk: CompanyPermissionPK
        var groupPermission: CompanyPermission
        var permissions: MutableList<CompanyPermission> = mutableListOf<CompanyPermission>()
        for (privilege in privileges) {
            pk = CompanyPermissionPK()
            pk.user = user
            pk.privilege = privilege
            pk.company = company
            groupPermission = CompanyPermission()
            groupPermission.pk = pk
            permissions.add(groupPermission)
        }

        user.companyPermissions!!.addAll(permissions)

        userRepository.save(user)
    }
}