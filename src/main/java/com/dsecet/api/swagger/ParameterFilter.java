package com.dsecet.api.swagger;

import com.wordnik.swagger.config.DefaultSpecFilter;
import com.wordnik.swagger.model.ApiDescription;
import com.wordnik.swagger.model.Operation;
import com.wordnik.swagger.model.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @author: lxl
 */
public class ParameterFilter extends DefaultSpecFilter{

    @Override
    public boolean isParamAllowed(Parameter parameter, Operation operation, ApiDescription apiDescription, Map<String, List<String>> stringListMap, Map<String, String> stringStringMap, Map<String, List<String>> stringListMap2){
        return parameter.paramAccess().isEmpty() || !parameter.paramAccess().get().equals("internal");
    }
}
