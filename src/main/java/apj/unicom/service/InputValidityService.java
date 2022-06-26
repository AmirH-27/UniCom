package apj.unicom.service;

import apj.unicom.data.Response;

@FunctionalInterface
public interface InputValidityService {
    Response isValid();
}
