package com.example.demo.dto.Login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModifyDto {

    private String userEmail;
    private String userIntroduction;

    private List<String> userTags;
}
