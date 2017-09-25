package com.example.demo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class BtmProps {
    String watchedDir;
    long pollingInterval = 5 * 1000;
}
