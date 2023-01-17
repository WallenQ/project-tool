package com.wallen.tool.http;

import com.wallen.dto.ResultDTO;
import com.wallen.dto.ResultDataDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * 网络请求
 *
 * @author Wallen
 * @date 2023/1/17 09:55
 */
public class HttpRequestUtil {

    /**
     * POST 请求
     *
     * @param url
     * @param body
     * @param responseType
     * @param <T>
     * @return
     */
    public static <T> T postRequest(String url, String body, Class<T> responseType) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        httpHeaders.add(HttpHeaders.ACCEPT, MimeTypeUtils.APPLICATION_JSON_VALUE);
        HttpEntity<String> entity = new HttpEntity<>(body, httpHeaders);
        ResponseEntity<T> response = new RestTemplate().postForEntity(url, entity, responseType);
        HttpStatus status = response.getStatusCode();
        if (HttpStatus.OK.equals(status)) {
            return response.getBody();
        }
        return null;
    }

    /**
     * GET 请求
     *
     * @param url
     * @param responseType
     * @param <T>
     * @return
     */
    public static <T> T getRequest(String url, Class<T> responseType) {
        ResponseEntity<T> response = new RestTemplate().getForEntity(url, responseType);
        HttpStatus status = response.getStatusCode();
        if (HttpStatus.OK.equals(status)) {
            return response.getBody();
        }
        return null;
    }

    public static void main(String[] args) {
        //测试POST请求
        ResultDTO result = postRequest("http://127.0.0.1:8080/test/plcLoginTest",
                "1673094582,99,001,192.168.0.1,8888,mushiny,v5.0.0,v4.0.0,NORMAL", ResultDTO.class);
        if (result != null) {
            System.out.println("***********POST request***********");
            System.out.println(result);
        }

        //测试GET请求-@PathVariable
        ResultDataDTO<Map<String, String>> resultData = getRequest("http://127.0.0.1:8080/test/getMessage1/message1", ResultDataDTO.class);
        if (resultData != null) {
            System.out.println("***********GET request(@PathVariable)***********");
            System.out.println(resultData);
        }
        //测试GET请求-@RequestParam
        ResultDataDTO<Map<String, String>> resultData2 = getRequest("http://127.0.0.1:8080/test/getMessage2?id=message2", ResultDataDTO.class);
        if (resultData2 != null) {
            System.out.println("***********GET request(@RequestParam)***********");
            System.out.println(resultData2);
        }

    }
}
