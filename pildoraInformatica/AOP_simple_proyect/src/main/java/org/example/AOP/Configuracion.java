package org.example.AOP;

import jdk.jfr.Enabled;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration //indica que es un archivo de configuracion
@EnableAspectJAutoProxy //Habilita los aspectos
@ComponentScan("org.example") //donde debe buscar los aspectos
public class Configuracion {
}
