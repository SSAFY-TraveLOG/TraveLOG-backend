package com.ssafy.travelog.plan.controller;

import com.ssafy.travelog.plan.service.PlanService;
import com.ssafy.travelog.util.Message;
import com.ssafy.travelog.util.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.Map;

@RestController
@RequestMapping("/plan")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.DELETE})
public class PlanController {
    private PlanService planService;

    @Autowired
    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @PostMapping()
    public ResponseEntity<Message> createPlan(@RequestBody Map<String, Object> map){
        int ret = 0;
        try{
            ret = planService.createPlan(map);
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
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
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
