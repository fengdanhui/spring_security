package com.imooc.web.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @date 2020/5/8
 */
@RestController
public class AsyncController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @RequestMapping("/order")
    public String order() throws Exception {
        logger.info("主线程开始");
        Thread.sleep(1000);
        logger.info("主线程返回");
        return "success";
    }

}
