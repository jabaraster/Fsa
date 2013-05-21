/**
 * 
 */
package jabara.fsa.beanvalidation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import jabara.general.Empty;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;

/**
 * @author jabaraster
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { RequireMaxCharCount.Validator.class })
public @interface RequireMaxCharCount {

    /**
     * @return -
     */
    Class<?>[] groups() default {};

    /**
     * @return -
     */
    String message() default "{javax.validation.constraints.NotNull.message}";

    /**
     * @return -
     */
    Class<? extends Payload>[] payload() default {};

    /**
     * @return -
     */
    int value();

    /**
     * @author jabaraster
     */
    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        NotNull[] value();
    }

    /**
     * @author jabaraster
     */
    class Validator implements ConstraintValidator<RequireMaxCharCount, String> {
        private static final int MIN_CHAR_COUNT = 1;
        private int              maxCharCount;

        /**
         * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
         */
        @Override
        public void initialize(final RequireMaxCharCount pConstraintAnnotation) {
            this.maxCharCount = pConstraintAnnotation.value();
        }

        @Override
        public boolean isValid(final String pValue, @SuppressWarnings("unused") final ConstraintValidatorContext pContext) {
            final String value = pValue == null ? Empty.STRING : pValue.toString();
            final int len = value.length();
            return MIN_CHAR_COUNT <= len && len <= this.maxCharCount;
        }
    }

}
