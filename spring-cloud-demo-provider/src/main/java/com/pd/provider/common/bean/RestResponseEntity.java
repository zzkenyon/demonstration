package com.pd.provider.common.bean;

import com.pd.provider.common.HttpException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;

/**
 * @author zhaozhengkang
 * @description
 * @version
 * @date 2020/3/23 16:38
 */
public class RestResponseEntity {

    public static RestResponseBuilder ok(){
        return new DefaultBuilder();
    }

    public static RestResponseBuilder errorBuilder(HttpException e){
        return new DefaultBuilder(e);
    }

    public static ResponseEntity ok(Object body) {
        return ResponseEntity.ok().body(new RestResponseBody(ResponseMeta.SUCCESS_META, body));
    }

    public static ResponseEntity error(HttpException e) {
        ResponseMeta meta = new ResponseMeta(e.getCode(),e.getMsg());
        return ResponseEntity.status(e.getHttpStatus()).body(new RestResponseBody(meta, null));
    }

    public static ResponseEntity error(int code, String msg, HttpStatus httpStatus) {
        ResponseMeta meta = new ResponseMeta(code,msg);
        return ResponseEntity.status(httpStatus).body(new RestResponseBody(meta, null));
    }

    private static class DefaultBuilder implements RestResponseBuilder {

        private final ResponseEntity.BodyBuilder builder;
        private final ResponseMeta meta;


        public DefaultBuilder() {
            this.builder = ResponseEntity.ok();
            this.meta = ResponseMeta.SUCCESS_META;
        }

        public DefaultBuilder(HttpException e) {
            this.builder = ResponseEntity.status(e.getHttpStatus());
            this.meta = new ResponseMeta(e.getCode(), e.getMsg());
        }

        @Override
        public RestResponseBuilder header(String headerName, String... headerValues) {
            builder.header(headerName, headerValues);
            return this;
        }

        @Override
        public RestResponseBuilder headers(@Nullable HttpHeaders headers) {
            builder.headers(headers);
            return this;
        }

        @Override
        public ResponseEntity build() {
            return builder.body(new RestResponseBody(meta, null));
        }

        @Override
        public ResponseEntity body(Object body) {
            return builder.body(new RestResponseBody(meta, body));
        }
    }

    public interface RestResponseBuilder{

        RestResponseBuilder header(String headerName, String... headerValues);

        RestResponseBuilder headers(@Nullable HttpHeaders headers);

        ResponseEntity build();

        ResponseEntity body(Object body);

    }
}
