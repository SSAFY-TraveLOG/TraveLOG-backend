package com.ssafy.travelog.plan.controller;

import com.ssafy.travelog.plan.dto.TravelDto;
import com.ssafy.travelog.plan.service.PlanService;
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

@RestController
@RequestMapping("/plan")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.DELETE})
public class PlanController {
    private PlanService planService;

    @Autowired
    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @GetMapping("/list/{user-id}")
    @ApiOperation(value = "입력받은 사용자의 모든 여행계획을 불러온다.", response = TravelDto.class)
    public ResponseEntity<Message> listPlan(@PathVariable("user-id") int userId) {
        try {
            List<TravelDto> travels = planService.listPlan(userId);
            Message message = new Message();
            HttpHeaders headers = new HttpHeaders();

            headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

            if (travels != null) {
                message.setStatus(StatusEnum.OK);
                message.setCode(StatusEnum.OK);
                message.setMessage("요청에 성공하였습니다.");
                message.setData(travels);
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
