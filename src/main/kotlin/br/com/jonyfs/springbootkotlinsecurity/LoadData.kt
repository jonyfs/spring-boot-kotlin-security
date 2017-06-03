package br.com.jonyfs.springbootkotlinsecurity

import br.com.jonyfs.springbootkotlinsecurity.company.Company
import br.com.jonyfs.springbootkotlinsecurity.group.Group
import br.com.jonyfs.springbootkotlinsecurity.group.GroupRepository
import br.com.jonyfs.springbootkotlinsecurity.privilege.Privilege
import br.com.jonyfs.springbootkotlinsecurity.user.User
import br.com.jonyfs.springbootkotlinsecurity.util.PrivilegeManager
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct
import javax.annotation.Resource


/**
 * Created by jony on 25/05/17.
 */
@Component
class LoadData {

    @Resource
    lateinit var privilegeManager: PrivilegeManager

    @Resource
    lateinit var groupRepository: GroupRepository

    fun solarEnergyCompanies(): Collection<Company> {
        var company1 = Company()
        company1.name = "Vivint Solar"

        var company2 = Company()
        company2.name = "Dividend Solar"

        var company3 = Company()
        company3.name = "SolarCity"
        var company4 = Company()
        company4.name = "Solar Five"
        var company5 = Company()
        company5.name = "SunLux Energy"

        return listOf(company1, company2, company3, company4, company5)
    }

    fun windEnergyCompanies(): Collection<Company> {
        var company1 = Company()
        company1.name = "Siemens"

        var company2 = Company()
        company2.name = "GE"

        var company3 = Company()
        company3.name = "Vestas"
        var company4 = Company()
        company4.name = "Goldwind"
        var company5 = Company()
        company5.name = "Enercon"

        return listOf(company1, company2, company3, company4, company5)
    }

    @PostConstruct
    fun load() {

        var privilegeCanCreate = Privilege()

        privilegeCanCreate.name = "CREATE"

        var privilegeCanRead = Privilege()

        privilegeCanRead.name = "READ"

        var privilegeCanUpdate = Privilege()

        privilegeCanUpdate.name = "UPDATE"

        var privilegeCanDelete = Privilege()

        privilegeCanDelete.name = "DELETE"


        var solarEnergyCompanies = Group()
        solarEnergyCompanies.name = "Solar Energy Companies"
        solarEnergyCompanies.companies = solarEnergyCompanies()


        var windEnergyCompanies = Group()
        windEnergyCompanies.name = "Wind Energy Companies"
        windEnergyCompanies.companies = windEnergyCompanies()

        groupRepository.save(listOf(solarEnergyCompanies, windEnergyCompanies))


        var user1 = User()
        user1.firstName = "John"
        user1.lastName = "Smith"
        user1.email = "john.smith@test.com"
        user1.password = "XPTO"

        privilegeManager.add(user1, listOf(privilegeCanRead), solarEnergyCompanies)
        privilegeManager.add(user1, listOf(privilegeCanRead, privilegeCanCreate), windEnergyCompanies)


        privilegeManager.add(user1, listOf(privilegeCanRead), solarEnergyCompanies.companies!!.first())
        privilegeManager.add(user1, listOf(privilegeCanRead), solarEnergyCompanies.companies!!.last())

        privilegeManager.add(user1, listOf(privilegeCanRead), windEnergyCompanies.companies!!.first())
        privilegeManager.add(user1, listOf(privilegeCanRead, privilegeCanCreate, privilegeCanUpdate), windEnergyCompanies.companies!!.last())

        var user2 = User()
        user2.firstName = "Joseph"
        user2.lastName = "Smith"
        user2.email = "joseph.smith@test.com"
        user2.password = "XPTO"

        privilegeManager.add(user2, listOf(privilegeCanCreate, privilegeCanRead, privilegeCanUpdate, privilegeCanDelete), solarEnergyCompanies)
        privilegeManager.add(user2, listOf(privilegeCanCreate, privilegeCanRead, privilegeCanUpdate), solarEnergyCompanies)


        privilegeManager.add(user2, listOf(privilegeCanRead, privilegeCanCreate), solarEnergyCompanies.companies!!.first())
        privilegeManager.add(user2, listOf(privilegeCanRead, privilegeCanCreate, privilegeCanUpdate), solarEnergyCompanies.companies!!.last())

        privilegeManager.add(user2, listOf(privilegeCanRead), windEnergyCompanies.companies!!.first())
        privilegeManager.add(user2, listOf(privilegeCanRead, privilegeCanCreate, privilegeCanUpdate), windEnergyCompanies.companies!!.last())


        var user3 = User()
        user3.firstName = "Lucy"
        user3.lastName = "Santos"
        user3.email = "lucy.santos@test.com"
        user3.password = "XPTO"

        privilegeManager.add(user3, listOf(privilegeCanCreate, privilegeCanRead, privilegeCanUpdate, privilegeCanDelete), solarEnergyCompanies)


        privilegeManager.add(user3, listOf(privilegeCanRead, privilegeCanCreate, privilegeCanUpdate), solarEnergyCompanies.companies!!.first())


        var user4 = User()
        user4.firstName = "Jony"
        user4.lastName = "Santos"
        user4.email = "jony.santos@test.com"
        user4.password = "XPTO"

        var userAdmin = User()
        userAdmin.firstName = "Admin"
        userAdmin.lastName = "Admin"
        userAdmin.email = "admin@test.com"
        userAdmin.password = "XPTO"


        for (company in solarEnergyCompanies()) {
            privilegeManager.add(user4, listOf(privilegeCanRead), company)
            privilegeManager.add(userAdmin, listOf(privilegeCanCreate, privilegeCanRead, privilegeCanUpdate, privilegeCanDelete), company)
        }

        for (company in windEnergyCompanies()) {
            privilegeManager.add(userAdmin, listOf(privilegeCanCreate, privilegeCanRead, privilegeCanUpdate, privilegeCanDelete), company)
        }

    }

}