package by.itakademy.akulov.utils;

import by.itakademy.akulov.entity.BlockList;
import by.itakademy.akulov.entity.Course;
import by.itakademy.akulov.entity.CourseStudent;
import by.itakademy.akulov.entity.CourseTeacher;
import by.itakademy.akulov.entity.CourseType;
import by.itakademy.akulov.entity.CourseUser;
import by.itakademy.akulov.entity.KnowlegeBase;
import by.itakademy.akulov.entity.KnowlegeBaseComment;
import by.itakademy.akulov.entity.Role;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.sql.ResultSet;

@UtilityClass
public class BuildEntity {

    @SneakyThrows
    public static Course buildCource(ResultSet resultSet) {

        return Course.builder()
                .id(resultSet.getInt("course_id"))
                .type(CourseType.builder()
                        .id(resultSet.getInt("course_type_id"))
                        .name(resultSet.getString("course_type_name"))
                        .build())
                .startDate(resultSet.getDate("course_startdate").toLocalDate())
                .duration(resultSet.getInt("course_duration"))
                .name(resultSet.getString("course_name"))
                .description(resultSet.getString("course_description"))
                .plan(resultSet.getString("course_plan"))
                .build();
    }

    @SneakyThrows
    public static Role buildRole(ResultSet resultSet) {

        return Role.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .build();
    }

    @SneakyThrows
    public static CourseType buildCourseType(ResultSet resultSet) {

        return CourseType.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .build();
    }

    @SneakyThrows
    public static BlockList buildBlockList(ResultSet resultSet) {

        return BlockList.builder()
                .courseUserId(resultSet.getInt("user_id"))
                .build();
    }

    @SneakyThrows
    public static CourseStudent buildCourseStudent(ResultSet resultSet) {

        return CourseStudent.builder()
                .courseId(resultSet.getInt("course_id"))
                .courseUserId(resultSet.getInt("user_id"))
                .result(resultSet.getInt("result"))
                .build();
    }

    @SneakyThrows
    public static CourseTeacher buildCourseTeacher(ResultSet resultSet) {

        return CourseTeacher.builder()
                .courseId(resultSet.getInt("course_id"))
                .courseUserId(resultSet.getInt("user_id"))
                .build();
    }

    @SneakyThrows
    public static Course buildCourse(ResultSet resultSet) {

        return Course.builder()
                .id(resultSet.getInt("course_id"))
                .type(CourseType.builder()
                        .id(resultSet.getInt("course_type_id"))
                        .name(resultSet.getString("course_type_name"))
                        .build())
                .startDate(resultSet.getDate("cource_startdate").toLocalDate())
                .duration(resultSet.getInt("course_duration"))
                .name(resultSet.getString("course_name"))
                .description(resultSet.getString("course_description"))
                .plan(resultSet.getString("course_plan"))
                .build();
    }

    @SneakyThrows
    public static CourseUser buildUser(ResultSet resultSet) {

        return CourseUser.builder()
                .id(resultSet.getInt("course_user_id"))
                .role(Role.builder()
                        .id(resultSet.getInt("user_role_id"))
                        .name(resultSet.getString("user_role_name"))
                        .build())
                .firstName(resultSet.getString("first_name"))
                .middleName(resultSet.getString("middle_name"))
                .lastName(resultSet.getString("last_name"))
                .email(resultSet.getString("e_mail"))
                .phone(resultSet.getLong("phone"))
                .passphrase(resultSet.getString("passphrase"))
                .build();
    }

    @SneakyThrows
    public static KnowlegeBase buildKb(ResultSet resultSet) {

        return KnowlegeBase.builder()
                .id(resultSet.getInt("id"))
                .courseId(resultSet.getInt("course_id"))
                .courseUserId(resultSet.getInt("user_id"))
                .timestamp(resultSet.getTimestamp("timestamp"))
                .text(resultSet.getString("text"))
                .build();
    }

    @SneakyThrows
    public static KnowlegeBaseComment buildKbComment(ResultSet resultSet) {

        return KnowlegeBaseComment.builder()
                .id(resultSet.getInt("id"))
                .knowlegeBaseId(resultSet.getInt("knowlege_base_id"))
                .courseUserId(resultSet.getInt("user_id"))
                .timestamp(resultSet.getTimestamp("timestamp"))
                .text(resultSet.getString("text"))
                .build();
    }
}