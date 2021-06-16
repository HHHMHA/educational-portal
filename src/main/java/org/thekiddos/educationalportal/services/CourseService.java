package org.thekiddos.educationalportal.services;

import org.thekiddos.educationalportal.models.Course;
import org.thekiddos.educationalportal.models.User;

import java.util.List;

public interface CourseService {
    void addCourse( User instructor, String name, String description );

    List<Course> getCourses( User instructor );

    List<Course> getAll();

    void enrollInCourse( Course course, User student );
}
