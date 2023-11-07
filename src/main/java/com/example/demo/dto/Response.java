package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Data
@AllArgsConstructor(staticName = "set")
@Component
public class Response {

    @Getter
    @Builder
    private static class Body {
        private int state;
        private String result;
        private String message;
        private Object data;
        private Object error;
    }

    //spring security에서 제공하는 응답형식 기본 템플릿이 있더라구요. 해당 부분으로 변경하려고 합니다.
    public ResponseEntity<?> success(Object data, String msg, HttpStatus status) {
        Body body = Body.builder()
                .state(status.value())
                .data(data)
                .result("success")
                .message(msg)
                .error(Collections.emptyList())
                .build();

        return ResponseEntity.ok(body);
    }

    /**
     * <p> 메세지만 가진 성공 응답을 반환합니다.</p>
     * <pre>
     *     {
     *         "state" : 200,
     *         "result" : success,
     *         "message" : message,
     *         "data" : [],
     *         "error" : []
     *     }
     * </pre>
     *
     * @param msg 응답 바디 message 필드에 포함될 정보
     * @return 응답 객체
     */

    public ResponseEntity<?> success(String msg) { // 잘 이해가 안되시면 오버로딩에 대해서 알아보시길 바랍니다.
        return success(Collections.emptyList(), msg, HttpStatus.OK);
    }

    /**
     * <p> 데이터만 가진 성공 응답을 반환합니다.</p>
     * <pre>
     *     {
     *         "state" : 200,
     *         "result" : success,
     *         "message" : null,
     *         "data" : [{data1}, {data2}...],
     *         "error" : []
     *     }
     * </pre>
     *
     * @param data 응답 바디 message 필드에 포함될 정보
     * @return 응답 객체
     */

    public ResponseEntity<?> success(Object data) {
        return success(data, null, HttpStatus.OK);
    }

    /**
     * <p> 성공 응답만을 반환합니다.</p>
     * <pre>
     *     {
     *         "state" : 200,
     *         "result" : success,
     *         "message" : null,
     *         "data" : [],
     *         "error" : []
     *     }
     * </pre>
     *
     * @return 응답 객체
     */
    public ResponseEntity<?> success() {
        return success(Collections.emptyList(), null, HttpStatus.OK);
    }

    public ResponseEntity<?> fail(Object data, String msg, HttpStatus status) {
        Body body = Body.builder()
                .state(status.value())
                .data(data)
                .result("fail")
                .message(msg)
                .error(Collections.emptyList())
                .build();
        return ResponseEntity.ok(body);
    }

    /**
     * <p> 메세지를 가진 실패 응답을 반환한다. </p>
     * <pre>
     *     {
     *         "state" : HttpStatus Code,
     *         "result" : fail,
     *         "message" : message,
     *         "data" : [],
     *         "error" : [{error1}, {error2}...]
     *     }
     * </pre>
     *
     * @param msg    응답 바디 message 필드에 포함될 정보
     * @param status 응답 바디 status 필드에 포함될 상태 코드
     * @return 응답 객체
     */
    public ResponseEntity<?> fail(String msg, HttpStatus status) {
        return fail(Collections.emptyList(), msg, status);
    }

}
