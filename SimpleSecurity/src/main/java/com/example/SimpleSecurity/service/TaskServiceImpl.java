package com.example.SimpleSecurity.service;

import com.example.SimpleSecurity.dto.TaskDTO;
import com.example.SimpleSecurity.entity.Task;
import com.example.SimpleSecurity.mapper.TaskMapper;
import com.example.SimpleSecurity.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public void save(TaskDTO task) {
        taskRepository.save(taskMapper.toEntity(task));
    }

    @Override
    public List<TaskDTO> findByUserid(long user_id) {
        List<Task> task = taskRepository.findByUseridOrderByDueDate(user_id);
        return taskMapper.toDTOList(task);
    }

    @Override
    public TaskDTO findById(long l) {
        return taskMapper.toDTO((taskRepository.findById(l).orElse(new Task())));
    }

    @Override
    public List<TaskDTO> findByUseridCompleted(long user_id) {
        List<Task> task = taskRepository.findByUseridTrueOrderByDueDate(user_id);
        return taskMapper.toDTOList(task);
    }

    @Transactional
    @Override
    public void completeTask(List<String> checked){
        for (String check : checked) {
            TaskDTO taskDTO = findById(Long.parseLong(check));
            if (taskDTO.getId()>0){
                taskDTO.setCompleted(true);
                taskDTO.setCompletedOn(LocalDateTime.now());
                save(taskDTO);
            }
        }
    }

//    private TaskDTO mapToDTO(Task task){
//        return TaskDTO.builder()
//                .id(task.getId())
//                .title(task.getTitle())
//                .dueDate(task.getDueDate())
//                .createdDate(task.getCreatedDate())
//                .completed(task.isCompleted())
//                .completedOn(task.getCompletedOn())
//                .user_id(task.getUser_id())
//                .build();
//    }
//
//    private Task mapToEntity(TaskDTO taskDTO){
//        Task task = Task.builder()
//                .id(taskDTO.getId())
//                .title(taskDTO.getTitle())
//                .completed(taskDTO.isCompleted())
//                .createdDate(taskDTO.getCreatedDate())
//                .dueDate(taskDTO.getDueDate())
//                .completedOn(taskDTO.getCompletedOn())
//                .user_id(taskDTO.getUser_id())
//                .build();
//        return task;
//    }

}
