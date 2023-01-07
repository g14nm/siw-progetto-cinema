package it.uniroma3.siw.siwprogettocinema.authentication.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.siwprogettocinema.authentication.model.User;


public interface UserRepository extends CrudRepository<User, Long> {

}