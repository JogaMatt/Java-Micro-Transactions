package com.javaproject.finalproject.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.javaproject.finalproject.models.Prize;

@Repository
public interface PrizeRepository extends CrudRepository<Prize, Long>
{
	List<Prize> findAll();
}
