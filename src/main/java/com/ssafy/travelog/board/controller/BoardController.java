package com.ssafy.travelog.board.controller;

import com.ssafy.travelog.board.dto.BoardDto;
import com.ssafy.travelog.board.dto.CommentDto;
import com.ssafy.travelog.board.service.BoardService;
import com.ssafy.travelog.util.Message;
import com.ssafy.travelog.util.StatusEnum;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.Comment;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/board")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PATCH})
public class BoardController {
    private BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/write")
    @ApiOperation(value = "글을 작성한다.", response = BoardDto.class)
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
    @ApiOperation(value = "글의 제목과 내용을 가져온다.", response = BoardDto.class)
    public ResponseEntity<Message> getArticle(@PathVariable String articleNo){
        try {
            BoardDto ret = null;
            Map<String, String> map = new HashMap<>();
            map.put("articleNo", articleNo);
            ret = boardService.getArticle(map);
            if (ret != null) {
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

    @GetMapping("/search")
    @ApiOperation(value = "검색 조건에 맞는 글 리스트를 리턴한다.", response = BoardDto.class)
    public ResponseEntity<Message> searchArticle(@RequestParam Map<String, String> map){
        try {
            List<BoardDto> articleList = boardService.search(map);

            if (articleList != null) {
                Message message = new Message();
                HttpHeaders headers = new HttpHeaders();

                headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

                message.setStatus(StatusEnum.OK);
                message.setCode(StatusEnum.OK);
                message.setMessage("요청에 성공하였습니다.");
                message.setData(articleList);

                return new ResponseEntity<>(message, headers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e){
            return exceptionHandling(e);
        }
    }

    @GetMapping("/cmt-list")
    @ApiOperation(value = "댓글을 조회한다.", response = CommentDto.class)
    public ResponseEntity<Message> searchAllComment(@RequestBody Map<String, String> map) {
        try {
            List<CommentDto> commentList = boardService.searchAll(map);

            if (commentList != null) {
                Message message = new Message();
                HttpHeaders headers = new HttpHeaders();

                headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

                message.setStatus(StatusEnum.OK);
                message.setCode(StatusEnum.OK);
                message.setMessage("요청에 성공하였습니다.");
                message.setData(commentList);

                return new ResponseEntity<>(message, headers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @GetMapping("/cmt-write")
    @ApiOperation(value = "댓글을 작성한다.", response = CommentDto.class)
    public ResponseEntity<Message> writeComment(@RequestBody Map<String, String> map) {
        try {
            int ret = boardService.writeComment (map);

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
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @PatchMapping("/cmt-update")
    @ApiOperation(value = "댓글을 수정한다.", response = CommentDto.class)
    public ResponseEntity<Message> updateComment(@RequestBody Map<String, String> map) {
        try {
            int ret = boardService.updateComment(map);

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
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @DeleteMapping("/cmt-delete/{commentId}")
    @ApiOperation(value = "댓글을 삭제한다.", response = CommentDto.class)
    public ResponseEntity<Message> deleteComment(@PathVariable int commentId) {
        try {
            int ret = boardService.deleteComment(commentId);

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
        } catch (Exception e) {
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
