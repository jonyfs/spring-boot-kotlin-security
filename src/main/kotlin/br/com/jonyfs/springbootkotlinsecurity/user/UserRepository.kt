package br.com.jonyfs.springbootkotlinsecurity.user

/**
 * Created by jony on 25/05/17.
 */


import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(excerptProjection = UserProjection::class)
interface UserRepository : PagingAndSortingRepository<User, Long>