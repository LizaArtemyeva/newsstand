package ru.vsu.cs.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringMVCDispatcherServletInitializor extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {Config.class};
    }
    //любой юрл который напишет наш пользователь в браузере когда обращается к нашему серверу должен перенаправляться
    //на наш диспетчер сервлет
    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}
