package com.ssafy.travelog.board.controller;

import com.ssafy.travelog.board.dto.CommentDto;
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
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.DELETE})
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
    @ApiOperation(value = "QnA 게시판의 특정 글 제목과 내용을 가져온다.", response = QnaBoardDto.class)
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
    @ApiOperation(value = "글의 제목과 내용을 수정한다.", response = QnaBoardDto.class)
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
    @ApiOperation(value = "글을 삭제한다.", response = QnaBoardDto.class)
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

    @PostMapping("/search")
    @ApiOperation(value = "검색 조건에 맞는 글 리스트를 리턴한다.", response = QnaBoardDto.class)
    public ResponseEntity<Message> searchArticle(@RequestParam Map<String, String> map, @RequestBody Map<String, String> userInfo){
        try {
            List<QnaBoardDto> articleList = boardService.search(map);

            if (articleList != null) {
                Message message = new Message();
                HttpHeaders headers = new HttpHeaders();

                headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

                message.setStatus(StatusEnum.OK);
                message.setCode(StatusEnum.OK);
                message.setMessage("요청에 성공하였습니다.");
                message.setData(articleList);

                if (Integer.parseInt(userInfo.get("userNo")) == 1)
                    return new ResponseEntity<>(message, headers, HttpStatus.OK);

                if (map.get("key") == null) {
                    for (int i = 0; i < articleList.size(); i++) {
                        if (articleList.get(i).getSecret() != 0 &&
                                Integer.parseInt(userInfo.get("userNo")) != articleList.get(i).getUserNo()) {
                            articleList.get(i).setUserNo(-1);
                            articleList.get(i).setUserName("***");
                            articleList.get(i).setSubject("비밀글입니다.");
                            articleList.get(i).setContent("비밀글입니다.");
                        }
                    }
                } else {
                    for (int i = articleList.size() - 1; i >= 0 ; i--) {
                        if (articleList.get(i).getSecret() != 0 &&
                                Integer.parseInt(userInfo.get("userNo")) != articleList.get(i).getUserNo()) {
                            articleList.remove(i);
                        }
                    }
                }

                return new ResponseEntity<>(message, headers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e){
            return exceptionHandling(e);
        }
    }

    @GetMapping("/cmt-list/{articleNo}")
    @ApiOperation(value = "댓글을 조회한다.", response = CommentDto.class)
    public ResponseEntity<Message> searchAllComment(@PathVariable int articleNo) {
        try {
            List<CommentDto> commentList = boardService.searchAll(articleNo);

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

    @PostMapping("/cmt-write")
    @ApiOperation(value = "댓글을 작성한다.", response = CommentDto.class)
    public ResponseEntity<Message> writeComment(@RequestBody Map<String, String> map) {
        try {
            int ret = boardService.writeComment(map);

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
