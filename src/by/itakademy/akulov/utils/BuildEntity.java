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

import java.sql.ResultSet;

public class BuildEntity {

    @SneakyThrows
    public static Course buildCource(ResultSet resultSet) {

        Course course = null;
        course = Course.builder()
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
        return course;
    }

    @SneakyThrows
    public static Role buildRole(ResultSet resultSet) {

        Role role = null;
        role = Role.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .build();
        return role;
    }

    @SneakyThrows
    public static CourseType buildCourseType(ResultSet resultSet) {

        CourseType courseType = null;
        courseType = CourseType.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .build();
        return courseType;
    }

    @SneakyThrows
    public static BlockList buildBlockList(ResultSet resultSet) {

        BlockList blockList = null;
        blockList = BlockList.builder()
                .courseUserId(resultSet.getInt("user_id"))
                .build();
        return blockList;
    }

    @SneakyThrows
    public static CourseStudent buildCourseStudent(ResultSet resultSet) {

        CourseStudent courseStudent = null;
        courseStudent = CourseStudent.builder()
                .courseId(resultSet.getInt("course_id"))
                .courseUserId(resultSet.getInt("user_id"))
                .result(resultSet.getInt("result"))
                .build();
        return courseStudent;
    }

    @SneakyThrows
    public static CourseTeacher buildCourseTeacher(ResultSet resultSet) {

        CourseTeacher courseTeacher = null;
        courseTeacher = CourseTeacher.builder()
                .courseId(resultSet.getInt("course_id"))
                .courseUserId(resultSet.getInt("user_id"))
                .build();
        return courseTeacher;
    }

    @SneakyThrows
    public static Course buildCourse(ResultSet resultSet) {

        Course course = null;
        course = Course.builder()
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
        return course;
    }

    @SneakyThrows
    public static CourseUser buildUser(ResultSet resultSet) {

        CourseUser courseUser = null;
        courseUser = CourseUser.builder()
                .id(resultSet.getInt("course_user_id"))
                .role(Role.builder()
                        .id(resultSet.getInt("user_role_id"))
                        .name(resultSet.getString("user_role_name"))
                        .build())
                .firstName(resultSet.getString("firstName"))
                .middleName(resultSet.getString("middleName"))
                .lastName(resultSet.getString("last_name"))
                .email(resultSet.getString("e_mail"))
                .phone(resultSet.getLong("phone"))
                .passphrase(resultSet.getString("passphrase"))
                .build();
        return courseUser;
    }

    @SneakyThrows
    public static KnowlegeBase buildKb(ResultSet resultSet) {

        KnowlegeBase knowlegeBase = null;
        knowlegeBase = KnowlegeBase.builder()
                .id(resultSet.getInt("id"))
                .courseId(resultSet.getInt("course_id"))
                .courseUserId(resultSet.getInt("user_id"))
                .timestamp(resultSet.getTimestamp("timestamp"))
                .text(resultSet.getString("text"))
                .build();
        return knowlegeBase;
    }

    @SneakyThrows
    public static KnowlegeBaseComment buildKbComment(ResultSet resultSet) {

        KnowlegeBaseComment knowlegeBaseComment = null;
        knowlegeBaseComment = KnowlegeBaseComment.builder()
                .id(resultSet.getInt("id"))
                .knowlegeBaseId(resultSet.getInt("knowlege_base_id"))
                .courseUserId(resultSet.getInt("user_id"))
                .timestamp(resultSet.getTimestamp("timestamp"))
                .text(resultSet.getString("text"))
                .build();
        return knowlegeBaseComment;
    }
}