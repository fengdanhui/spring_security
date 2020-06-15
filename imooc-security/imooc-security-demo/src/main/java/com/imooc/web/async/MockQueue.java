package com.imooc.web.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 虚拟的消息队列
 * @date 2020/5/14
 */
@Component
public class MockQueue {
    //placeOrder有值，接到了下单的消息
    private String placeOrder;
    //placeOrder有值，接到了订单完成的消息
    private String completOrder;

    Logger logger = LoggerFactory.getLogger(getClass());

    public String getPlaceOrder() {
        return placeOrder;
    }

    public void setPlaceOrder(String placeOrder) throws Exception {
        new Thread(() -> {
            logger.info("接到下单请求");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.placeOrder = placeOrder;
            logger.info("下单请求处理完毕：" + placeOrder);
        }).start();
    }

    public String getCompletOrder() {
        return completOrder;
    }

    public void setCompletOrder(String completOrder) {
        this.completOrder = completOrder;
    }
}
