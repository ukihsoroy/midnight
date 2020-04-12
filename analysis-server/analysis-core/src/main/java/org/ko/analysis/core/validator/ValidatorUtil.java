package org.ko.analysis.core.validator;

import com.google.common.base.Strings;
import org.ko.sigma.core.exception.ValidateException;
import org.ko.sigma.core.util.MessageI18nUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class ValidatorUtil {

    private final static Logger logger = LoggerFactory.getLogger(ValidatorUtil.class);

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private static MessageSource messageSource;

    @Autowired(required = true)
    public void intiMessageSource(MessageSource source) {
        ValidatorUtil.messageSource = source;
    }

    /**
     * 验证Model对象
     * @param model 对象数据
     * @throws ValidateException
     */
    public static <T> void validate(T model) throws ValidateException {
        // 执行Bean验证
        Set<ConstraintViolation<T>> set = validator.validate(model, Default.class);
        if (set == null || set.size() == 0) {
            return;
        }
        List<String> validateMsgs =getValidateMsg(set);
        throw new ValidateException(validateMsgs);
    }

    /**
     * 验证Model对象中的某一个属性
     * @param model 对象数据
     * @param propertyName 属性名称
     * @throws ValidateException
     */
    public static <T> void validateProperty(T model, String propertyName) throws ValidateException {
        // 执行Bean验证
        Set<ConstraintViolation<T>> set = validator.validateProperty(model, propertyName, Default.class);
        if (set == null || set.size() == 0) {
            return;
        }
        List<String> validateMsgs = getValidateMsg(set);
        throw new ValidateException(validateMsgs);
    }


    /**
     * 验证Model对象中的某一个属性
     * @param model 对象数据
     * @param propertyNames 属性名称
     * @throws ValidateException
     */
    public static <T> void validateProperties(T model, List<String> propertyNames) throws ValidateException {
        // 执行Bean验证
        if (propertyNames.size() == 0){
            return;
        }
        for(String tmp:propertyNames){
            Set<ConstraintViolation<T>> set = validator.validateProperty(model, tmp, Default.class);
            if (set == null || set.size() == 0) {
                continue;
            }
            List<String> validateMsgs = getValidateMsg(set);
            throw new ValidateException(validateMsgs);
        }

    }

    /**
     * 从验证结果中抽取出验证提示消息(TODO 目前验证错误时，传递的参数还不能支持国际化)
     * @param set 验证结果
     * @return 验证提示消息
     */
    private static <T> List<String> getValidateMsg(Set<ConstraintViolation<T>> set) {
        List<String> validateMsgs = new ArrayList<>();
        for (ConstraintViolation<T> violation : set) {
            String msg = violation.getMessageTemplate();
            if(!Strings.isNullOrEmpty(msg)) {
                String validateMsg;
                if (msg.contains(":")) {
                    String[] paras = msg.split(":");
                    validateMsg = MessageI18nUtil.getI18nMessage(paras[0],
                            paras.length > 1 ? paras[1].split(",") : null);
                } else {
                    validateMsg = messageSource.getMessage(msg, null, LocaleContextHolder.getLocale());
                }
                validateMsgs.add(validateMsg);
            }
        }
        return validateMsgs;
    }
}
