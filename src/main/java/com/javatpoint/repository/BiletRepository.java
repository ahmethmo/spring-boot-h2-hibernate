package com.javatpoint.repository;
import org.springframework.data.repository.CrudRepository;

import com.javatpoint.model.Bilet;

public interface BiletRepository extends CrudRepository<Bilet, Integer>
{
}
