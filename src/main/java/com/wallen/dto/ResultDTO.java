package com.wallen.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 结果信息
 *
 * @author Wallen
 * @date 2023/1/13 15:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDTO implements Serializable {
    /**
     * 返回码:1成功，0失败
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String message;

    public static ResultDTO success() {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(1);
        resultDTO.setMessage("update successful!");
        return resultDTO;
    }
}
