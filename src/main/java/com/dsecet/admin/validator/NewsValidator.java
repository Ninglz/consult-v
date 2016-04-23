package com.dsecet.admin.validator;

import com.dsecet.foundation.model.query.NewsForm;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class NewsValidator implements Validator{
    @Override
    public boolean supports(Class<?> clazz){
        return NewsForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors){
        NewsForm form = (NewsForm) target;
        // todo add validate

//        if(StringUtils.isBlank(form.getMainTitle())){
//            errors.rejectValue("mainTitle", "err.news.main.title.empty");
//        }else {
//            form.setMainTitle(form.getMainTitle().trim());
//        }

//        if(form.getMainTitle().length() > 30){
//            errors.rejectValue("mainTitle", "err.news.main.title.limit");
//        }

//        if(StringUtils.isBlank(form.getType())){
//            errors.rejectValue("type", "err.news.type.empty");
//        }

       //todo

    }
}
