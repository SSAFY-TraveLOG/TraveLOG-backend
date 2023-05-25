package com.ssafy.travelog.plan.controller;

import com.ssafy.travelog.plan.dto.PlanDto;
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
import java.util.Map;
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

    @PostMapping()
    @ApiOperation(value = "입력받은 데이터로 Plan, Routes, Participants 를 생성한다")
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

    @PatchMapping("/{plan-no}")
    @ApiOperation(value = "입력받은 데이터로 해당 계획의 Plan, Routes, Participants 데이터를 변경한다")
    public ResponseEntity<Message> modifyPlan(@PathVariable("plan-no") int planNo, @RequestBody Map<String, Object> map){
        int ret = 0;
        map.put("planNo",planNo);
        try{
            ret = planService.modifyPlan(map);
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

    @GetMapping("/list/{user-no}")
    @ApiOperation(value = "입력받은 사용자의 모든 여행계획을 불러온다.", response = TravelDto.class)
    public ResponseEntity<Message> listPlan(@PathVariable("user-no") int userNo) {
        try {
            List<TravelDto> travels = planService.listPlan(userNo);
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

    @DeleteMapping("/{plan-no}")
    @ApiOperation(value = "plan, participant, route 테이블에서 입력받은 여행계획의 데이터를 삭제한다.", response = TravelDto.class)
    public ResponseEntity<Message> deletePlan(@PathVariable("plan-no") int planNo) {
        try {
            int ret = planService.deletePlan(planNo);
            if (ret > 0) {
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

    @GetMapping("/{plan-no}")
    @ApiOperation(value = "여행 계획 번호로 여행 계획을 조회한다", response = PlanDto.class)
    public ResponseEntity<Message> getPlan(@PathVariable("plan-no") int planNo) {
        try {
            PlanDto plan = planService.getPlan(planNo);
            if (plan != null) {
                Message message = new Message();
                HttpHeaders headers = new HttpHeaders();

                headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

                message.setStatus(StatusEnum.OK);
                message.setCode(StatusEnum.OK);
                message.setMessage("요청에 성공하였습니다.");
                message.setData(plan);

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
