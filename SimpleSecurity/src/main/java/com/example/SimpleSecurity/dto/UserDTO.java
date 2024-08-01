package com.example.SimpleSecurity.dto;

import com.example.SimpleSecurity.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private long id;
    private String username;
    private String password;
    private List<Task> tasks;
}
