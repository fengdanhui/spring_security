package com.imooc.wiremock;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * @date 2020/5/18
 */
public class MockServer {

    public static void main(String[] args) throws IOException {
        WireMock.configureFor(8081);
        WireMock.removeAllMappings();
        mock("/order/1", "01");
        mock("/order/2", "02");
    }

    public static void mock(String url, String file) throws IOException {
        ClassPathResource resource = new ClassPathResource("/mock/response/" + file + ".txt");
        String content = StringUtils.join(FileUtils.readLines(resource.getFile(), "UTF-8"), "\n");
        WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo(url)).willReturn(WireMock.aResponse().withBody(content).withStatus(200)));
    }

}
