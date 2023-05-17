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

    @PostMapping("/view/{articleNo}")
    @ApiOperation(value = "QnA 게시판의 특정 글 제목과 내용을 가져온다.", response = BoardDto.class)
    public ResponseEntity<Message> getArticle(@PathVariable String articleNo, @RequestBody Map<String, String> map){
        try {
            QnaBoardDto ret = null;
            map.put("articleNo", articleNo);
            ret = boardService.getArticle(map);
            if (ret != null) {
                Message message = new Message();
                HttpHeaders headers = new HttpHeaders();

                headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

                if (ret.getSecret() == 0 || (ret.getSecret() == 1 &&
                        (ret.getUserNo() == Integer.parseInt(map.get("userNo")) ||
                         Integer.parseInt(map.get("userNo")) == 1))) {
                    message.setStatus(StatusEnum.OK);
                    message.setCode(StatusEnum.OK);
                    message.setMessage("요청에 성공하였습니다.");
                    message.setData(ret);

                    return new ResponseEntity<>(message, headers, HttpStatus.OK);
                } else {
                    message.setStatus(StatusEnum.NO_CONTENT);
                    message.setCode(StatusEnum.NO_CONTENT);
                    message.setMessage("열람 권한이 없는 게시글입니다.");

                    return new ResponseEntity<>(message, headers, HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e){
            return exceptionHandling(e);
        }
    }

    @PatchMapping("/modify/{articleNo}")
    @ApiOperation(value = "글의 제목과 내용을 수정한다.", response = BoardDto.class)
    public ResponseEntity<Message> modifyArticle(@PathVariable String articleNo, @RequestBody Map<String, String> map){
        try {
            map.put("articleNo", articleNo);
            int ret = boardService.modifyArticle(map);
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

    @DeleteMapping("/delete/{articleNo}")
    @ApiOperation(value = "글을 삭제한다.", response = BoardDto.class)
    public ResponseEntity<Message> deleteArticle(@PathVariable String articleNo){
        try {
            Map<String, String> map = new HashMap<>();
            map.put("articleNo", articleNo);
            int ret = boardService.deleteArticle(map);
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
}
