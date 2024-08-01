package com.example.SimpleSecurity.controller;

import com.example.SimpleSecurity.dto.TaskDTO;
import com.example.SimpleSecurity.entity.User;
import com.example.SimpleSecurity.service.TaskService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@Slf4j
public class HomeController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/home")
    public String getHomePage(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("username", user.getUsername());
        return "home";
    }
    @GetMapping("create")
    public ModelAndView getTask(){
        return new ModelAndView("create");
    }

    @PostMapping("create")
    public ModelAndView createTask(@AuthenticationPrincipal User user, @ModelAttribute("task") TaskDTO task){
        LocalDateTime date = LocalDateTime.now();
        if(task.getDueDate().isBefore(date.toLocalDate())){
            return new ModelAndView("create", "error",
                    "The due Date cannot have already happened!");
        }
        task.setCreatedDate(date);
        task.setUser_id(user.getId());
        taskService.save(task);
        log.info("task created successfully");
        return new ModelAndView("create", "success",
                "The Task was successfully created!");
    }

    @GetMapping("review")
    public ModelAndView review(@AuthenticationPrincipal User user){
        List<TaskDTO> taskDTOList = taskService.findByUserid(user.getId());
        return new ModelAndView("review", "lista", taskDTOList);
    }

    @GetMapping("/viewCompleted")
    public ModelAndView getCompleted(@AuthenticationPrincipal User user){
        List<TaskDTO> taskDTOS = taskService.findByUseridCompleted(user.getId());
        return new ModelAndView("completed", "lista", taskDTOS);
    }

    @PostMapping("/complete")
    public String complete(@RequestParam("checked") List<String> checked){
        taskService.completeTask(checked);
        return "redirect:/review";
    }

}
