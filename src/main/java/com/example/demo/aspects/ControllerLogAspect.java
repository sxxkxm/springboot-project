package com.example.demo.aspects;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class ControllerLogAspect {
	
	ObjectMapper objectMapper;
	
	private final Logger LOGGER = LoggerFactory.getLogger(ControllerLogAspect.class);
	
	@Autowired
	public ControllerLogAspect(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}
	
	/**
	 * 	Pointcut to print logs.
	 */
    @Pointcut("execution(* com.talhwancompany.thcspringboot.controller..*.*(..))"
    		+ "execution(* com.talhwancompany.thcspringboot.service..*.*(..)) || "
    		+ "execution(* com.talhwancompany.thcspringboot.data.dao..*.*(..)) || "
    		+ "execution(* com.talhwancompany.thcspringboot.data.dto..*.*(..)) || "
    		+ "execution(* com.talhwancompany.thcspringboot.data.repository..*.*(..)) || "
    		+ "execution(* com.talhwancompany.thcspringboot.data.entity..*.*(..))"
    		)
    private void pointCut(){}

    /**
	 *  Function called before the pointcut.
	 *  Prints log parsing json values with Jackson.
	 *  
	 *  TODO handle JsonProcessingException printStackTrace 
	 *  
	 *  @param JoinPoint joinPoint
	 *  @exception JsonProcessingException
	 */
    @Before("pointCut()")
    public void beforeLog(JoinPoint joinPoint) {
    	String paramString = "";
    	Map<String, Object> params = getParams(joinPoint);
    	
    	if(params.size() <= 0) {
    		paramString = " : " + "no parameter";
    	}else {
    		try {
    			paramString = "\n" + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(params);
    		} catch (JsonProcessingException e) {}
    	}
    	
    	LOGGER.info(joinPoint.getSignature().getDeclaringTypeName() + " - " + joinPoint.getSignature().getName() + paramString);
    }

    /**
	 *  Function called after the pointcut.
	 *  Prints log parsing json values with Jackson.
	 *  
	 *  @param JoinPoint joinPoint
	 *  @param Object returnObj
	 *  @exception JsonProcessingException
	 */
    @AfterReturning(value = "pointCut()", returning = "returnObj")
    public void afterLog(JoinPoint joinPoint, Object returnObj) {
    	try {
			LOGGER.info(joinPoint.getSignature().getDeclaringTypeName() + " - " + joinPoint.getSignature().getName() + " return : \n" + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(returnObj));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    }
    
    /**
	 *  Returns the parameters stored in a JoinPoint as a Map
	 *  
	 *  @param JoinPoint joinPoint
	 *  @exception Map<String, Object> params
	 */
    private Map<String, Object> getParams(JoinPoint joinPoint) {
    	CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
    	String[] parameterNames = codeSignature.getParameterNames();
    	Object[] args = joinPoint.getArgs();
    	Map<String, Object> params = new HashMap<>();
    	for (int i = 0; i < parameterNames.length; i++) {
    		params.put(parameterNames[i], args[i]);
    	}
    	return params;
    }
    
}