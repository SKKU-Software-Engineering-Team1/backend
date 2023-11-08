package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName="set")
public class ArtResponseDto<D> {
    private boolean result;
    private String message;
    private D data;

    // 성공했을 때 정의하는 메서드
    public static <D> ArtResponseDto<D> setSuccess(String message, D data){
        return ArtResponseDto.set(true, message, data);
    }

    // 실패했을 때 정의하는 메서드
    public static <D> ArtResponseDto<D> setFailed(String message){
        return ArtResponseDto.set(false, message, null);
    }
}
