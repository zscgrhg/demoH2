package com.example.demo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class BtmProps {
    String watchedDir="D:\\codehaus\\demoH2\\btm";
    long pollingInterval = 5 * 1000;
}
