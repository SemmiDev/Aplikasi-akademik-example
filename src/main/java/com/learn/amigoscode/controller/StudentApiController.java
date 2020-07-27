package com.learn.amigoscode.controller;

import com.learn.amigoscode.dao.StudentDao;
import com.learn.amigoscode.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class StudentApiController {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentApiController.class.getName());

    static {
        Student student = new Student();
        student.setNisn("12345");
        student.setName("sammidev");
        student.setMajor("TEKNIK");
        student.setKelas(1);
    }

    @Autowired
    private StudentDao studentDao;

    @GetMapping("/api/studeent")
    @ResponseBody
    public Page<Student> getAll(Pageable page) {
        return studentDao.findAll(page);
    }

    @GetMapping("/api/studentName")
    @ResponseBody
    public Page<Student> getAllByName(@RequestParam(name = "name") String name, Pageable pageable) {
        return studentDao.getStudentByNameContaining(name, pageable);
    }

    @GetMapping("/api/student/{nisn}")
    @ResponseBody
    public Student getByNim(@PathVariable(name = "nisn") Student student) {
        return student;
    }

    @PostMapping("/api/student")
    @ResponseStatus(HttpStatus.CREATED)
    public void insert(@RequestBody @Valid Student student) {
        studentDao.save(student);
    }

    @PutMapping("/api/student/{nisn}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable(name = "nisn") String nisn, @RequestBody @Valid Student student) {
        Student std = studentDao.findById("nisn").get();
        if (std == null) {
            LOGGER.warn("Student nisn {} tidak ditemukan", std);
            return;
        }
        BeanUtils.copyProperties(student, std);
        studentDao.save(std);
    }

    @DeleteMapping("/api/student/{nisn}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("nisn") Student student) {
        if (student != null) {
            studentDao.delete(student);
        }
    }
}
