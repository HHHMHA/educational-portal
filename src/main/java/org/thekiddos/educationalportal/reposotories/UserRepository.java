package org.thekiddos.educationalportal.reposotories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thekiddos.educationalportal.models.Course;
import org.thekiddos.educationalportal.models.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    List<User> findByEnrolledInCoursesContains( Course course );
}
