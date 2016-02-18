package org.toxos.activiti.assertions.integration.common.test;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.toxos.activiti.assertions.integration.common.configuration.ActivitiTestConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ActivitiTestConfiguration.class })
public abstract class AbstractProcessAssertionsTest {

    @Autowired
    protected RuntimeService runtimeService;

    @Autowired
    @Rule
    public ActivitiRule activitiRule;

}
