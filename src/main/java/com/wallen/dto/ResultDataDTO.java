package com.wallen.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 结果数据
 *
 * @author Wallen
 * @date 2023/1/13 15:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDataDTO<T> extends ResultDTO {
    /**
     * 返回值
     */
    private T data;

    public ResultDataDTO(Integer code, String message) {
        super.setCode(code);
        super.setMessage(message);
    }

    @Override
    public String toString() {
        return "ResultDataDTO{"
                + "code=" + super.getCode() + ", " + "message=" + super.getMessage() + ", " + "data=" + data
                + '}';
    }
}
