package by.itakademy.akulov.mapper;

import by.itakademy.akulov.dao.CourseTypeDao;
import by.itakademy.akulov.dto.CourseUserDto;
import by.itakademy.akulov.entity.CourseUser;

public class UserMapper implements BaseMapper<CourseUser, CourseUserDto> {

    private static final UserMapper INSTANCE = new UserMapper();
    private CourseTypeDao courseTypeDao = CourseTypeDao.getInstance();

    @Override
    public CourseUserDto mapToDto(CourseUser entity) {
        return CourseUserDto.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .firstName(entity.getFirstName())
                .middleName(entity.getMiddleName())
                .lastName(entity.getLastName())
                .passphrase(entity.getPassphrase())
                .phone(entity.getPhone().toString())
                .role(entity.getRole())
                .build();
    }

    @Override
    public CourseUser mapToEntity(CourseUserDto dto) {
        return CourseUser.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .middleName(dto.getMiddleName())
                .lastName(dto.getLastName())
                .passphrase(dto.getPassphrase())
                .email(dto.getEmail())
                .phone(Long.parseLong(dto.getPhone()))
                .role(dto.getRole())
                .build();
    }

    public static UserMapper getInstance() {
        return INSTANCE;
    }
}
