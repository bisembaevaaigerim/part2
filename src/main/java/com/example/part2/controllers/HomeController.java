package com.example.part2.controllers;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.part2.models.Task;

import java.util.HashMap;

@Controller
public class HomeController {
    private HashMap<Long, Task> tasks = new HashMap<>();
    private HashMap<Long, String> descriptions = new HashMap<>();
    private Long counter = 1L;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("tasks", tasks);
        return "index";
    }

    @GetMapping("/add")
    public String addPage() {
        return "add";
    }

    @PostMapping("/add")
    public String addTask(@RequestParam String name, @RequestParam String deadlineDate, @RequestParam String description) {
        Task task = new Task();
        task.setId(counter++);
        task.setName(name);
        task.setDeadlineDate(deadlineDate);
        task.setCompleted(false);
        tasks.put(task.getId(), task);
        descriptions.put(task.getId(), description);
        return "redirect:/";
    }

    @GetMapping("/task/{id}")
    public String details(@PathVariable Long id, Model model) {
        Task task = tasks.get(id);
        model.addAttribute("task", task);
        model.addAttribute("description", descriptions.get(id));
        return "details";
    }

    @PostMapping("/edit/{id}")
    public String editTask(@PathVariable Long id, @RequestParam String name,
                           @RequestParam String deadlineDate,
                           @RequestParam(defaultValue = "false") boolean completed,
                           @RequestParam String description) {
        Task task = tasks.get(id);
        if (task != null) {
            task.setName(name);
            task.setDeadlineDate(deadlineDate);
            task.setCompleted(completed);
            descriptions.put(id, description);
        }
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        tasks.remove(id);
        descriptions.remove(id);
        return "redirect:/";
    }
}