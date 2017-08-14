package com.example.app.sql.annotation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.Resource;


@Configuration
public class SqlConfig {

	@Autowired
	private ApplicationContext applicationContext;
	
	@Bean
	@Scope(scopeName=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public String getSqlString(InjectionPoint injectionPoint) {
		
		Sql sql = AnnotationUtils.getAnnotation(injectionPoint.getAnnotatedElement(), Sql.class);
		if(sql == null) {
			return null;
		}
		
		String sqlPathString = getSqlPathString(injectionPoint);
		if(sqlPathString == null) {
			return null;
		}
		
		String fieldName = getFieldName(injectionPoint);
		return read(sqlPathString + "/" + fieldName + ".sql");
	}

	private String getSqlPathString(InjectionPoint injectionPoint) {
		Class clazz = injectionPoint.getField().getDeclaringClass();
		if(!clazz.isAnnotationPresent(SqlPath.class)){
			return null;
		}
		return ((SqlPath) clazz.getAnnotation(SqlPath.class)).path(); 
	}

	private String read(String path) {
		StringBuilder stringBuilder = new StringBuilder();
		Resource resource = applicationContext.getResource(path);
		
		try {
			InputStream inputStream = resource.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			String line;
			while((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append("\n");
			}
			bufferedReader.close();
		} catch(IOException e) {
			System.out.println("Error reading sql file"+e);
		}
		return stringBuilder.toString();
	}

	private String getFieldName(InjectionPoint injectionPoint) {
		return injectionPoint.getField().getName();
	}
}
