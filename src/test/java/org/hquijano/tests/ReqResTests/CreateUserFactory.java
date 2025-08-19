package org.hquijano.tests.ReqResTests;

import org.hquijano.model.CreateUserRequest;
import org.testng.annotations.Factory;

public class CreateUserFactory {
    @Factory
    public Object[] build() {
        return new Object[] {
                new ReqResCreateUserTest(new CreateUserRequest("HQuijano", "Senior QA")),
                new ReqResCreateUserTest(new CreateUserRequest("LCetina", "Project Manager")),
                new ReqResCreateUserTest(new CreateUserRequest("MQuijano", "Developer")),
                new ReqResCreateUserTest(new CreateUserRequest("TQuijano", "Junior QA"))
        };
    }
}
