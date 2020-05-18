package com.bigdata.hy_tools.ctrl;

/**
 * @author huyi
 * @date 2020/4/29 4:26 PM
 */

import com.bigdata.hy_tools.dto.HYResult;
import com.bigdata.hy_tools.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Api(value = "测试接口", tags = "测试")
@RestController
@CrossOrigin
public class TestController {

    @Resource(name = "TT")
    private String tt;

    private long count_text = 0l;

    @Autowired
    TestService testService;


    @ApiOperation(value = "测试接口1", httpMethod = "GET")
    @ApiResponse(code = 200, message = "success", response = ResponseEntity.class)
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity test() {


        return new ResponseEntity<>(new HYResult(tt).success(), HttpStatus.OK);
    }

    @ApiOperation(value = "测试接口2", httpMethod = "GET")
    @ApiResponse(code = 200, message = "success", response = ResponseEntity.class)
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/test1", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity test1(Integer num) {
        testService.test(num);
        return new ResponseEntity<>(new HYResult(tt).success(), HttpStatus.OK);
    }
}

