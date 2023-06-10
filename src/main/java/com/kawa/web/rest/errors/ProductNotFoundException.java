package com.kawa.web.rest.errors;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class ProductNotFoundException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;

    public ProductNotFoundException() {
        super(ErrorConstants.DEFAULT_TYPE, "Product not found", Status.NOT_FOUND);
    }

    public ProductNotFoundException(String message) {
        super(ErrorConstants.DEFAULT_TYPE, message, Status.NOT_FOUND);
    }
}
