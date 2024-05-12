package com.project.empapp.repositories;

import com.project.empapp.models.WorkHour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface WorkHourRepository extends JpaRepository<WorkHour, Long> {

}
