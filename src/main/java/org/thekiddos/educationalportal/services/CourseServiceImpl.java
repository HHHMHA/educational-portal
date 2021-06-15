package org.thekiddos.educationalportal.services;

import org.springframework.stereotype.Service;
import org.thekiddos.educationalportal.models.Course;
import org.thekiddos.educationalportal.models.User;
import org.thekiddos.educationalportal.reposotories.CourseRepository;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    public CourseServiceImpl( CourseRepository courseRepository ) {
        this.courseRepository = courseRepository;
    }

    @Override
    public void addCourse( User instructor, String name, String description ) {
        if ( !instructor.isInstructor() )
            throw new RuntimeException( "You can't create a course" );

        Course course = new Course();
        course.setName( name );
        course.setDescription( description );
        course.setInstructor( instructor );
        courseRepository.save( course );
    }

    @Override
    public List<Course> getCourses( User instructor ) {
        return courseRepository.findAllByInstructor( instructor );
    }
}
