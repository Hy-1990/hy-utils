package com.bigdata.hy_tools.ctrl;

/**
 * @author huyi
 * @date 2020/4/29 4:26 PM
 */

import com.bigdata.hy_tools.annotation.TestAnnotation;
import com.bigdata.hy_tools.dto.HYResult;
import com.bigdata.hy_tools.service.TestService;
import com.bigdata.hy_tools.utils.DownloadExcelUtil;
import com.bigdata.hy_tools.utils.TestUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Api(value = "测试接口", tags = "测试")
@RestController
@CrossOrigin
public class TestController {

    @Resource(name = "TT")
    private String tt;

    private long count_text = 0l;

    @Resource(name = "testUtil")
    private TestUtil testUtil;

    @Autowired
    TestService testService;


    @ApiOperation(value = "测试接口1", httpMethod = "GET")
    @ApiResponse(code = 200, message = "success", response = ResponseEntity.class)
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @TestAnnotation(detail = "hahaha")
    public ResponseEntity test(Integer num, String value) {
        testUtil.setValue(num, value);
        testUtil.getPrint();
        testUtil.init();
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
        System.out.println("haha");
        return new ResponseEntity<>(new HYResult(tt).success(), HttpStatus.OK);
    }

    @ApiOperation(value = "测试接口3", httpMethod = "GET")
    @ApiResponse(code = 200, message = "success", response = ResponseEntity.class)
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/test2", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity test2(HttpServletResponse httpServletResponse) throws Exception {
        String fileName = "test.xlsx";
        httpServletResponse.setContentType("application/octet-stream;charset=UTF-8");
        httpServletResponse.setHeader("Content-Discription", "attachment;fileName" + fileName);
        OutputStream outputStream = httpServletResponse.getOutputStream();
        DownloadExcelUtil.exportExcel(outputStream);
        return new ResponseEntity<>(new HYResult("OK").success(), HttpStatus.OK);
    }
}

