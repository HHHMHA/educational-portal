package org.thekiddos.educationalportal.reposotories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thekiddos.educationalportal.models.Course;
import org.thekiddos.educationalportal.models.User;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findAllByInstructor( User instructor );
}
