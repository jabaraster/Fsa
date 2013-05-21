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
import java.util.regex.Pattern;

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
@Constraint(validatedBy = { PhoneNumber.Validator.class })
public @interface PhoneNumber {

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
    class Validator implements ConstraintValidator<PhoneNumber, String> {

        private static final String  CONNECTOR           = "-";                      //$NON-NLS-1$
        private static final Pattern NUMBER_ONLY_CHECKER = Pattern.compile("[0-9]+"); //$NON-NLS-1$
        private static final int     MIN_DIGIT           = 10;
        private static final int     MAX_DIGIT           = 11;

        /**
         * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
         */
        @Override
        public void initialize(@SuppressWarnings("unused") final PhoneNumber pConstraintAnnotation) {
            // 処理なし
        }

        @Override
        public boolean isValid(final String pValue, @SuppressWarnings("unused") final ConstraintValidatorContext pContext) {
            final String value = (pValue == null ? Empty.STRING : pValue.toString()).replaceAll(CONNECTOR, Empty.STRING);
            if (value.length() < MIN_DIGIT || MAX_DIGIT < value.length()) {
                return false;
            }
            return NUMBER_ONLY_CHECKER.matcher(value).matches();
        }
    }
}
