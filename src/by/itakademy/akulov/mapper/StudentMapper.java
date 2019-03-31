package by.itakademy.akulov.mapper;

import by.itakademy.akulov.dao.CourseStudentDao;
import by.itakademy.akulov.dto.CourseDto;
import by.itakademy.akulov.dto.CourseStudentDto;
import by.itakademy.akulov.dto.CourseUserDto;
import by.itakademy.akulov.entity.CourseStudent;
import by.itakademy.akulov.service.CourseService;
import by.itakademy.akulov.service.UserService;

import java.util.Optional;

public class StudentMapper implements BaseMapper<Optional<CourseStudent>, Optional<CourseStudentDto>> {

    private static final StudentMapper INSTANCE = new StudentMapper();
    private CourseStudentDao courseStudentDao = CourseStudentDao.getInstance();
    private CourseService courseService = CourseService.getInstance();
    private UserService userService = UserService.getInstance();

    @Override
    public Optional<CourseStudentDto> mapToDto(Optional<CourseStudent> entity) {
        CourseStudentDto courseStudentDto = null;
        if (entity.isPresent()) {
            Optional<CourseUserDto> courseUserDto = userService.find(entity.get().getCourseUserId());
            Optional<CourseDto> courseDto = courseService.find(entity.get().getCourseId());
            if (courseDto.isPresent() && courseUserDto.isPresent()) {
                courseStudentDto = CourseStudentDto.builder()
                        .courseDto(courseDto.get())
                        .courseUserDto(courseUserDto.get())
                        .result(entity.get().getResult())
                        .build();
            }
        }
        return Optional.ofNullable(courseStudentDto);
    }

    @Override
    public Optional<CourseStudent> mapToEntity(Optional<CourseStudentDto> dto) {
        CourseStudent courseStudent = null;
        if (dto.isPresent()) {
             courseStudent = CourseStudent.builder()
                    .courseId(dto.get().getCourseDto().getId())
                    .courseUserId(dto.get().getCourseUserDto().getId())
                    .result(dto.get().getResult())
                    .build();
        }
        return Optional.ofNullable(courseStudent);
    }

    public static StudentMapper getInstance() {
        return INSTANCE;
    }
}
