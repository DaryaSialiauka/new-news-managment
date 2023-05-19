package by.it_academy.util;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.time.LocalDate;
import java.util.Objects;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

@Documented
@Retention(RUNTIME)
@Target({ FIELD })
@Constraint(validatedBy = ValidAge.ValidAgeUser.class)
public @interface ValidAge {
	String message() default "Age must be over 18 years old";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	class ValidAgeUser implements ConstraintValidator<ValidAge, LocalDate> {

		@Override
		public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
			if(Objects.isNull(value)) {
				return false;
			}
			LocalDate date = value.plusYears(18);
			return ( date.isBefore(LocalDate.now()) || date.isEqual(LocalDate.now()));
		}

	}

}
