package br.com.jonyfs.springbootkotlinsecurity.user

import br.com.jonyfs.springbootkotlinsecurity.group.Group
import br.com.jonyfs.springbootkotlinsecurity.model.AuditableProjection
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.rest.core.config.Projection

/**
 * Created by jony on 27/05/17.
 */

@Projection(name = "virtual", types = arrayOf(User::class))
interface UserProjection : AuditableProjection {

    fun getFirstName(): String

    fun getLastName(): String

    fun getEmail(): String

    fun getGroups(): Group

    @get:Value("#{target.firstName} #{target.lastName}")
    val fullName: String

}