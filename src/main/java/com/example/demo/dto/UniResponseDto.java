package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName="set")
public class UniResponseDto<D> {
    private boolean result;
    private String message;
    private D data;

    // 성공했을 때 정의하는 메서드
    public static <D> UniResponseDto<D> setSuccess(String message, D data){
        return UniResponseDto.set(true, message, data);
    }

    // 실패했을 때 정의하는 메서드
    public static <D> UniResponseDto<D> setFailed(String message){
        return UniResponseDto.set(false, message, null);
    }
}
