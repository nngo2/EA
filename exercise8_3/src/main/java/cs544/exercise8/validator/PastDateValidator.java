package cs544.exercise8.validator;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PastDateValidator implements ConstraintValidator<PastDateConstraint, Date> {

	@Override
	public void initialize(PastDateConstraint date) {
	}

	@Override
	public boolean isValid(Date date, ConstraintValidatorContext cxt) {
		return date != null && date.after(new Date());
	}

}