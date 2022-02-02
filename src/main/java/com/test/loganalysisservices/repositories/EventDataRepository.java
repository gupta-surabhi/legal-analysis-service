package com.test.loganalysisservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.loganalysisservices.entities.EventData;

@Repository
public interface EventDataRepository extends JpaRepository<EventData, String>{

}
