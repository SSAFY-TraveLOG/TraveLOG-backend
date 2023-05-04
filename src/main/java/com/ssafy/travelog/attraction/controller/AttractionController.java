package com.ssafy.travelog.attraction.controller;

import com.ssafy.travelog.attraction.dto.AttractionDto;
import com.ssafy.travelog.attraction.service.AttractionService;
import com.ssafy.travelog.util.Message;
import com.ssafy.travelog.util.StatusEnum;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/attraction")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class AttractionController {

    private AttractionService service;

    @Autowired
    public AttractionController(AttractionService service) {
        this.service = service;
    }

    @GetMapping("/search")
    @ApiOperation(value = "검색 조건에 맞는 관광지를 검색한다.", response = AttractionDto.class)
    public ResponseEntity<Message> search(@RequestParam Map<String, String> map) {
        try {
            List<AttractionDto> attractions = service.searchByCondition(map);
            Message message = new Message();
            HttpHeaders headers = new HttpHeaders();

            headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

            if (attractions != null) {
                message.setStatus(StatusEnum.OK);
                message.setCode(StatusEnum.OK);
                message.setMessage("요청에 성공하였습니다.");
                message.setData(attractions);
                return new ResponseEntity<>(message, headers, HttpStatus.OK);
            } else {
                message.setStatus(StatusEnum.NO_CONTENT);
                message.setCode(StatusEnum.NO_CONTENT);
                message.setMessage("요청에 실패하였습니다.");
                message.setData(null);
                return new ResponseEntity<Message>(message, headers, HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @GetMapping("/{content-id}")
    @ApiOperation(value = "content-id를 기반으로 attraction의 상세정보를 확인한다.", response = AttractionDto.class)
    public ResponseEntity<Message> attrDescription(@PathVariable("content-id") int contentId) {
        try {
            AttractionDto attractions = service.attrDescription(contentId);
            Message message = new Message();
            HttpHeaders headers = new HttpHeaders();

            headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

            if (attractions != null) {
                message.setStatus(StatusEnum.OK);
                message.setCode(StatusEnum.OK);
                message.setMessage("요청에 성공하였습니다.");
                message.setData(attractions);
                return new ResponseEntity<>(message, headers, HttpStatus.OK);
            } else {
                message.setStatus(StatusEnum.NO_CONTENT);
                message.setCode(StatusEnum.NO_CONTENT);
                message.setMessage("요청에 실패하였습니다.");
                message.setData(null);
                return new ResponseEntity<Message>(message, headers, HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    private ResponseEntity<Message> exceptionHandling(Exception e) {
        Message message = new Message();
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        message.setStatus(StatusEnum.INTERNAL_SERVER_ERROR);
        message.setCode(StatusEnum.INTERNAL_SERVER_ERROR);
        message.setMessage("요청에 실패하였습니다.");
        message.setData(e.getMessage());

        e.printStackTrace();

        return new ResponseEntity<>(message, headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
