package me.otmane.spring.context.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("CONTROLLER_IMPLEMENTATION_1")
public class ControllerImpl implements IController {

    @Autowired
    @Qualifier("SERVICE_IMPLEMENTATION_1")
    private IService service1;

    @Autowired
    @Qualifier("SERVICE_IMPLEMENTATION_2")
    private IService service2;

    @Override
    public void callService() {
        callService1();
        callService2();
    }

    void callService1(){
        service1.print();
    }

    void callService2(){
        service2.print();
    }
}
