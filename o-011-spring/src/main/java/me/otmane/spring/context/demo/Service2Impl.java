package me.otmane.spring.context.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("SERVICE_IMPLEMENTATION_2")
public class Service2Impl implements IService {
    @Override
    public void print() {
        log.info(getClass().getName());
    }
}
