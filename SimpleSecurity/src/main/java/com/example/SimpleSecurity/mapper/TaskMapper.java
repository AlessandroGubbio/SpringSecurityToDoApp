package com.example.SimpleSecurity.mapper;

import com.example.SimpleSecurity.dto.TaskDTO;
import com.example.SimpleSecurity.entity.Task;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    Task toEntity(TaskDTO dto);
    TaskDTO toDTO(Task task);
    List<TaskDTO> toDTOList(List<Task> taskList);
}
