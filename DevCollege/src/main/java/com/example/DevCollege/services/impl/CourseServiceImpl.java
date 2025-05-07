package com.example.DevCollege.services.impl;

import com.example.DevCollege.dto.CourseAddUpdateDTO;
import com.example.DevCollege.dto.CourseDto;
import com.example.DevCollege.entity.Course;
import com.example.DevCollege.entity.Enrollment;
import com.example.DevCollege.exception.custom.CourseSlotsHandler;
import com.example.DevCollege.exception.custom.IDNotFound;
import com.example.DevCollege.mapper.CourseAddUpdateMapper;
import com.example.DevCollege.mapper.CourseMapper;
import com.example.DevCollege.repository.CourseRepository;
import com.example.DevCollege.repository.EnrollmentRepository;
import com.example.DevCollege.response.ApiResponse;
import com.example.DevCollege.services.CourseService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {




    @Autowired
    private CourseRepository repository;




    @Autowired
    CourseAddUpdateMapper courseAddUpdateMapper;



    @Autowired
    CourseMapper courseMapper;




    //enrollment repo
    @Autowired
    EnrollmentRepository enrollmentRepository;




    //for generating id in sequence manner
    private String generateCourseId() {
        Course course = repository.findTopByOrderByCourseIdDesc();

        if (course == null) {
            return "CRS0001";
        }
        String lastId = course.getCourseId();
        int numericId = Integer.parseInt(lastId.substring(3));
        return String.format("CRS%04d", numericId + 1);
    }




    //add course
    @Override
    @Transactional
    public ResponseEntity<ApiResponse<?>> addCourseDetails(CourseAddUpdateDTO courseAddUpdateDTO) {

        Course course = courseAddUpdateMapper.courseAddUpdateDtoToCourse(courseAddUpdateDTO);


        String courseId = generateCourseId();

        course.setCourseId(courseId);

        Course saved = repository.save(course);
        if (saved == null) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.failure("Unable to save",
                    "Failed to add course details."));

        } else {

            return ResponseEntity.status(HttpStatus.OK)
                    .body(ApiResponse.success("Successfully added course details for " + courseId));
        }
    }




    //update course details
    @Override
    public ResponseEntity<ApiResponse<?>> updateCourseDetails(String id, CourseAddUpdateDTO courseAddUpdateDTO){

        Course existing = repository.findById(id).orElse(null);

        if (existing == null) {

            throw new IDNotFound("Course Id","Courseid: " + id + " doesn't exist");
        }

        List<Enrollment> enrollment = enrollmentRepository.findByCourse_CourseId(id);

        //if course is linked with student
        if (!enrollment.isEmpty()) {

            if (existing.getNoOfSlot() <= courseAddUpdateDTO.getNoOfSlot()) {

                existing.setName(courseAddUpdateDTO.getName());
                existing.setDescription(courseAddUpdateDTO.getDescription());
                existing.setNoOfSlot(courseAddUpdateDTO.getNoOfSlot());

                repository.save(existing);

                return ResponseEntity.status(HttpStatus.OK)
                        .body(ApiResponse.success("Successfully Updated Course details for " + id));

            } else {

                throw new CourseSlotsHandler("Number of slots","while updating the no of slots should not be less");
            }

        } else {
            //else update all the details (except tag)

            existing.setName(courseAddUpdateDTO.getName());
            existing.setDescription(courseAddUpdateDTO.getDescription());
            existing.setNoOfSlot(courseAddUpdateDTO.getNoOfSlot());
            existing.setFee(courseAddUpdateDTO.getFee());
            existing.setDuration(courseAddUpdateDTO.getDuration());

            repository.save(existing);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(ApiResponse.success("Successfully Updated Course details for " + id));
        }


    }




    @Override
    public ResponseEntity<ApiResponse<?>> deleteCourseDetails(String id) {
        Course course = repository.findById(id).orElse(null);
        if (course == null) {
            //if it is not present

            throw new IDNotFound("Course id","Course id: "+id+" is not present");
        }
        List<Enrollment> courseEnrolled = enrollmentRepository.findByCourse_CourseId(id);
        if (courseEnrolled.isEmpty()) {
            //no course is assigned to student
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ApiResponse.success("Successfully Deleted Course details for course id: " + id));

        } else {

            //check whether it is assigned to student and have status active
            boolean hasActiveEnrollment = courseEnrolled.stream()
                    .anyMatch(e -> e.getStatus().equals("Allocated")
                            || e.getStatus().equals("In Progress"));

            if (hasActiveEnrollment) {

                throw new CourseSlotsHandler("Course Allocation","Failed to delete course details\nCourse has active enrollments.");
            }
        }
        //else delete the course by id
        //delete every enrolled course as it is cancelled or completed
        courseEnrolled.stream()
                .forEach(e -> enrollmentRepository.delete(e));
        //then delete the course by id in course table
        repository.deleteById(id);


        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("Successfully Deleted Course details for course id: " + id));
    }




    @Override
    public ResponseEntity<ApiResponse<CourseDto>> getCourseDetail(String id) {

        Course course = repository.findById(id).orElse(null);
        if (course == null) {

            throw new IDNotFound("Course Id","Course id: " + id + " is not present.");
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body( ApiResponse.success(List.of(courseMapper.courseToCourseDto(course))));
    }




    @Override
    public ResponseEntity<ApiResponse<CourseDto>> getAllCourseDetail() {

        List<CourseDto> courses = repository.findAll()
                .stream()
                .map(c -> courseMapper.courseToCourseDto(c))
                .collect(Collectors.toList());

        if (courses.isEmpty()) {


            throw new IDNotFound("Course Table","No Data Found in Course table");
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body( ApiResponse.success(courses));
    }
}
