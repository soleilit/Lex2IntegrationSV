package se.solarplexusit.lexportlet.validation;

import se.solarplexusit.lexportlet.dataobjects.SearchCriteria;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class SearchCriteriaValidator implements Validator
{
    public boolean supports(Class givenClass)
    {
        return SearchCriteria.class.isAssignableFrom(givenClass);
    }

    public void validate(Object o, Errors errors)
    {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fromDate", "field.fromDate.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "toDate", "field.toDate.required");
        ValidationUtils.rejectIfEmpty(errors, "infoTypes", "infoTypes.is.empty");

        SearchCriteria sc = (SearchCriteria) o;

        if (sc.getToDate() != null && sc.getFromDate() != null)
        {
            if (sc.getToDate().compareTo(sc.getFromDate()) < 0)
            {
                errors.rejectValue("toDate", "todate.less.than.fromdate");
            }
        }
    }
}
