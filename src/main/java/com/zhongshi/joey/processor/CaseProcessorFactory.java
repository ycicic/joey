package com.zhongshi.joey.processor;

import com.zhongshi.joey.entity.enums.ProtocolType;
import com.zhongshi.joey.processor.service.CaseProcessorService;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 用例处理器工厂
 *
 * @author ycc
 */
@Slf4j
public class CaseProcessorFactory {

    private static final Map<ProtocolType, CaseProcessorService> SERVICE_MAP = new HashMap<>();

    static {
        register();
    }

    private static void register() {
        Reflections reflections = new Reflections(CaseProcessorService.class.getPackage().getName());
        Set<Class<?>> processorClasses = reflections.getTypesAnnotatedWith(Protocol.class);
        processorClasses.forEach(processorClass -> {
            Protocol protocol = processorClass.getAnnotation(Protocol.class);
            ProtocolType type = protocol.value();
            try {
                CaseProcessorService caseProcessorService = (CaseProcessorService) processorClass.newInstance();
                SERVICE_MAP.put(type, caseProcessorService);
                log.debug("注册用例处理器[{}: {}]成功", type, processorClass.getSimpleName());
            } catch (Exception e) {
                log.error("注册用例处理器[{}: {}]失败", type, processorClass.getSimpleName(), e);
            }
        });
    }

    protected static CaseProcessorService getService(ProtocolType protocolType) {
        return SERVICE_MAP.get(protocolType);
    }

}
