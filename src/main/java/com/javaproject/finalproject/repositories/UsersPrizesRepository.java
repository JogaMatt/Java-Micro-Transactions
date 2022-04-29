package com.javaproject.finalproject.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.javaproject.finalproject.models.UsersPrizes;

@Repository
public interface UsersPrizesRepository extends CrudRepository<UsersPrizes, Long>
{
	List<UsersPrizes> findAll();
	List<UsersPrizes> findByUserId(Long id);
}
