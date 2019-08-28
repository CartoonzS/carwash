package ua.slavik.carwash.model.dto.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.slavik.carwash.model.dto.job.JobDTO;
import ua.slavik.carwash.model.enums.Status;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private Long id;
    private int price;
    private int duration;
    private int priority;
    private String name;
    private String description;
    private boolean repeatable;
    private Status status;
    private JobDTO job;
}
