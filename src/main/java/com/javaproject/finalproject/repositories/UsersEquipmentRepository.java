package com.javaproject.finalproject.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.javaproject.finalproject.models.UsersEquipment;

@Repository
public interface UsersEquipmentRepository extends CrudRepository<UsersEquipment, Long>
{
	List<UsersEquipment> findAll();
	List<UsersEquipment> findByUserId(Long id);
}
