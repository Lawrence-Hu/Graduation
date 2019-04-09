package utils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.*;

public class ValidatorUtil {
    private static Validator validator = Validation.buildDefaultValidatorFactory()
            .getValidator();

    public static <T> Error validate(T obj){
        Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
        if(set != null && set.size() >0 ){
            for(ConstraintViolation<T> cv : set){
               return new Error(cv.getMessage());
            }
        }
        return new Error(null);
    }
}