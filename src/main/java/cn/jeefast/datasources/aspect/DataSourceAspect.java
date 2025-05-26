package cn.jeefast.datasources.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import cn.jeefast.datasources.DataSourceNames;
import cn.jeefast.datasources.DynamicDataSource;
import cn.jeefast.datasources.annotation.DataSource;

import java.lang.reflect.Method;


/**
 * 多数据源，切面处理类
 */
@Aspect
@Component
public class DataSourceAspect implements Ordered {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private DynamicDataSource dynamicDataSource;

    @Pointcut("@annotation(cn.jeefast.datasources.annotation.DataSource)")
    public void dataSourcePointCut() {

    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        DataSource ds = method.getAnnotation(DataSource.class);
        if(ds == null){
            DynamicDataSource.setDataSource(DataSourceNames.FIRST);
            logger.debug("set datasource is " + DataSourceNames.FIRST);
        }else {
            String dataSourceName = ds.name();
            
            // Always default to FIRST if the requested data source is not available
            if (!DataSourceNames.FIRST.equals(dataSourceName) && !isDataSourceAvailable(dataSourceName)) {
                logger.warn("Requested data source '" + dataSourceName + "' is not available, using default data source instead");
                dataSourceName = DataSourceNames.FIRST;
            }
            
            DynamicDataSource.setDataSource(dataSourceName);
            logger.debug("set datasource is " + dataSourceName);
        }

        try {
            return point.proceed();
        } finally {
            DynamicDataSource.clearDataSource();
            logger.debug("clean datasource");
        }
    }
    
    /**
     * Check if the requested data source is available
     */
    private boolean isDataSourceAvailable(String dataSourceName) {
        // For now, we only check if the data source name is SECOND
        // In a more complex system, we might want to check if the data source is actually available
        return !DataSourceNames.SECOND.equals(dataSourceName) || 
               dynamicDataSource.getResolvedDataSources().containsKey(DataSourceNames.SECOND);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}

