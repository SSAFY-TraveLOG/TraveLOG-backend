package com.ssafy.travelog.board.controller;

import com.ssafy.travelog.board.dto.BoardDto;
import com.ssafy.travelog.board.dto.QnaBoardDto;
import com.ssafy.travelog.board.service.QnaBoardService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/qna")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class QnaBoardController {
    private QnaBoardService boardService;

    @Autowired
    public QnaBoardController(QnaBoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/write")
    @ApiOperation(value = "QnA 게시판에 글을 작성한다.", response = QnaBoardDto.class)
    public ResponseEntity<Message> writeArticle(@RequestBody Map<String, String> map){
        try {
            int ret = boardService.writeArticle(map);
            if (ret == 1) {
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
        } catch (Exception e){
            return exceptionHandling(e);
        }
    }


    private ResponseEntity<Message> exceptionHandling(Exception e) {
        e.printStackTrace();
        Message message = new Message();
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        message.setStatus(StatusEnum.NOT_FOUND);
        message.setCode(StatusEnum.NOT_FOUND);
        message.setMessage("요청에 실패하였습니다.");
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
}
