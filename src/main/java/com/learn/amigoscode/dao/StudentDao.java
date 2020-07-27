package com.learn.amigoscode.dao;

import com.learn.amigoscode.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StudentDao extends PagingAndSortingRepository<Student, String> {
    public Page<Student> getStudentByNameContaining(String  name, Pageable pageable);
}
