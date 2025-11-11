package com.example.demo.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component // 1. Đánh dấu đây là một Bean để Spring quét và quản lý
public class SystemTasks {

    // Dùng Logger
    private static final Logger log = LoggerFactory.getLogger(SystemTasks.class);

    /**
     * Đánh dấu phương thức này để chạy theo lịch.
     * fixedRate = 30000 nghĩa là chạy lặp lại mỗi 30.000 milliseconds (30 giây)
     * kể từ khi bắt đầu lần chạy trước.
     */
    @Scheduled(fixedRate = 30000) 
    public void logSystemRunning() {
        // 3. Nội dung log ra console
        log.info("System running...");
    }
}