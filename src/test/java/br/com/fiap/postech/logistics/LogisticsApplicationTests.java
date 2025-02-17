package br.com.fiap.postech.logistics;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class LogisticsApplicationTests {

    @Test
    void contextLoads() {
        /*
         * This test is used to verify that the Spring application context
         * loads without any issues. No assertions are needed because
         * a failure in loading the context will automatically fail the test.
         */
    }

}
