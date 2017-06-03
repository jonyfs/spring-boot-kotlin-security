package br.com.jonyfs.springbootkotlinsecurity.model

import br.com.jonyfs.springbootkotlinsecurity.user.User
import java.util.*

/**
 * Created by jony on 01/06/17.
 */
interface AuditableProjection {

    fun getId(): String

    fun getCreatedDate(): Date

    fun getCreatedBy(): User

    fun getLastModifiedDate(): Date

    fun getLastModifiedBy(): User

}