package com.ssafy.travelog.notice.controller;

import com.ssafy.travelog.notice.dto.NoticeDto;
import com.ssafy.travelog.notice.service.NoticeService;
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
@RequestMapping("/notice")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class NoticeController {
    private NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService service) {
        this.noticeService = service;
    }

    @PostMapping("/article")
    @ApiOperation(value = "공지사항을 등록한다.", response = NoticeDto.class)
    public ResponseEntity<Message> insert(@RequestBody Map<String, String> map) {
        try {
            int ret = noticeService.insert(map);

            if (ret != 0) {
                Message message = new Message();
                HttpHeaders headers = new HttpHeaders();

                headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

                message.setStatus(StatusEnum.OK);
                message.setCode(StatusEnum.OK);
                message.setMessage("요청에 성공하였습니다.");
                message.setData(ret);

                return new ResponseEntity<>(message, headers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @PatchMapping("/article")
    @ApiOperation(value = "공지사항을 수정한다.", response = NoticeDto.class)
    public ResponseEntity<Message> update(@RequestParam(value = "notice-no") int noticeNo, @RequestBody Map<String, String> map) {
        try {
            map.put("notice-no", Integer.toString(noticeNo));
            int ret = noticeService.update(map);

            if (ret != 0) {
                Message message = new Message();
                HttpHeaders headers = new HttpHeaders();

                headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

                message.setStatus(StatusEnum.OK);
                message.setCode(StatusEnum.OK);
                message.setMessage("요청에 성공하였습니다.");
                message.setData(ret);

                return new ResponseEntity<>(message, headers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "공지사항을 삭제한다.", response = NoticeDto.class)
    public ResponseEntity<Message> delete(@RequestParam(value = "notice-no") int noticeNo) {
        try {
            int ret = noticeService.delete(noticeNo);

            if (ret != 0) {
                Message message = new Message();
                HttpHeaders headers = new HttpHeaders();

                headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

                message.setStatus(StatusEnum.OK);
                message.setCode(StatusEnum.OK);
                message.setMessage("요청에 성공하였습니다.");
                message.setData(ret);

                return new ResponseEntity<>(message, headers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @GetMapping("/search")
    @ApiOperation(value = "공지사항을 조건에 맞게 조회한다.", response = NoticeDto.class)
    public ResponseEntity<Message> search(@RequestParam Map<String, String> map) {
        try {
            List<NoticeDto> noticeList = noticeService.search(map);

            if (noticeList != null) {
                Message message = new Message();
                HttpHeaders headers = new HttpHeaders();

                headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

                message.setStatus(StatusEnum.OK);
                message.setCode(StatusEnum.OK);
                message.setMessage("요청에 성공하였습니다.");
                message.setData(noticeList);

                return new ResponseEntity<>(message, headers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @GetMapping("/list")
    @ApiOperation(value = "공지사항 전체를 조회한다.", response = NoticeDto.class)
    public ResponseEntity<Message> searchAll() {
        try {
            List<NoticeDto> noticeList = noticeService.searchAll();

            if (noticeList != null) {
                Message message = new Message();
                HttpHeaders headers = new HttpHeaders();

                headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

                message.setStatus(StatusEnum.OK);
                message.setCode(StatusEnum.OK);
                message.setMessage("요청에 성공하였습니다.");
                message.setData(noticeList);

                return new ResponseEntity<>(message, headers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @GetMapping("/{notice-no}")
    @ApiOperation(value = "공지사항의 세부 정보를 조회한다.", response = NoticeDto.class)
    public ResponseEntity<Message> searchByNo(@PathVariable("notice-no") int noticeNo) {
        try {
            NoticeDto notice = noticeService.searchByNo(noticeNo);

            if (notice != null) {
                Message message = new Message();
                HttpHeaders headers = new HttpHeaders();

                headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

                message.setStatus(StatusEnum.OK);
                message.setCode(StatusEnum.OK);
                message.setMessage("요청에 성공하였습니다.");
                message.setData(notice);

                return new ResponseEntity<>(message, headers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
