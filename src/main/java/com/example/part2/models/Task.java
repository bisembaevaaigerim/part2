package com.example.part2.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Task {
    private Long id;
    private String name;
    private String deadlineDate;
    private boolean isCompleted;
}
