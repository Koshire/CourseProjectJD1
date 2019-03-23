package by.itakademy.akulov.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class KnowlegeBaseComment {

    private Integer id;
    private Integer knowlegeBaseId;
    private Integer courseUserId;
    private Timestamp timestamp;
    private String text;
}
