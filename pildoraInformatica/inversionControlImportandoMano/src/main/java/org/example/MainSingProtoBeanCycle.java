package org.example;

import org.example.modelos.beanCycle.EmpleadoBeanCycleModel;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainSingProtoBeanCycle {
    public static void main(String[] args) {
        System.out.println("Start!");

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext_SingletonPrototype_BeanCycle.xml");
        EmpleadoBeanCycleModel secretario = context.getBean("secretarioSingleProto", EmpleadoBeanCycleModel.class);
        EmpleadoBeanCycleModel secretario2 = context.getBean("secretarioSingleProto",EmpleadoBeanCycleModel.class);

        System.out.println("Dir memosty: "+secretario);
        System.out.println("Dir memosty: "+secretario2);

        System.out.println(secretario.getInforme());

        context.close();



    }
}
