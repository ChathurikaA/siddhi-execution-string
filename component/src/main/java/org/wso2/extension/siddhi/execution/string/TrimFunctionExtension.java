/*
 * Copyright (c)  2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.extension.siddhi.execution.string;

import org.wso2.siddhi.annotation.Example;
import org.wso2.siddhi.annotation.Extension;
import org.wso2.siddhi.annotation.Parameter;
import org.wso2.siddhi.annotation.ReturnAttribute;
import org.wso2.siddhi.annotation.util.DataType;
import org.wso2.siddhi.core.config.SiddhiAppContext;
import org.wso2.siddhi.core.exception.SiddhiAppRuntimeException;
import org.wso2.siddhi.core.executor.ExpressionExecutor;
import org.wso2.siddhi.core.executor.function.FunctionExecutor;
import org.wso2.siddhi.core.util.config.ConfigReader;
import org.wso2.siddhi.query.api.definition.Attribute;
import org.wso2.siddhi.query.api.exception.SiddhiAppValidationException;

import java.util.Map;

/**
 * trim(string)
 * Returns a copy of the string, with leading and trailing whitespace omitted.
 * Accept Type(s): STRING
 * Return Type(s): STRING
 */

@Extension(
        name = "trim",
        namespace = "str",
        description = "Returns a copy of the string with leading and trailing whitespace omitted",
        parameters = {
                @Parameter(name = "input.string",
                        description = "The input string that needs to be trimmed.",
                        type = {DataType.STRING})
        },
        returnAttributes = @ReturnAttribute(
                description = "This returns a string value after removing the leading and trailing whitespaces",
                type = {DataType.STRING}),
        examples = @Example(description = "This returns a copy of the `input.string` with the leading and/or " +
                "trailing white-spaces omitted. In this scenario, the output is \"AbCDefghiJ KLMN\".",
                syntax = "trim(\"  AbCDefghiJ KLMN  \")")
)
public class TrimFunctionExtension extends FunctionExecutor {

    Attribute.Type returnType = Attribute.Type.STRING;

    @Override
    protected void init(ExpressionExecutor[] attributeExpressionExecutors, ConfigReader configReader,
                        SiddhiAppContext siddhiAppContext) {
        if (attributeExpressionExecutors.length != 1) {
            throw new SiddhiAppValidationException(
                    "Invalid no of arguments passed to str:trim() function, required 1, but found " +
                            attributeExpressionExecutors.length);
        }
        if (attributeExpressionExecutors[0].getReturnType() != Attribute.Type.STRING) {
            throw new SiddhiAppValidationException("Invalid parameter type found for str:trim() function, required "
                    + Attribute.Type.STRING + ", but found " +
                    attributeExpressionExecutors[0].getReturnType().toString());
        }
    }

    @Override
    protected Object execute(Object[] data) {
        return null;  //Since the trim function takes in only 1 parameter, this method does not get called.
        // Hence, not implemented.
    }

    @Override
    protected Object execute(Object data) {
        if (data == null) {
            throw new SiddhiAppRuntimeException("Invalid input given to str:trim() function. " +
                    "The argument cannot be null");
        }
        return data.toString().trim();
    }

    @Override
    public void start() {
        //Nothing to start.
    }

    @Override
    public void stop() {
        //Nothing to stop.
    }

    @Override
    public Attribute.Type getReturnType() {
        return returnType;
    }

    @Override
    public Map<String, Object> currentState() {
        return null;    //No need to maintain a state.
    }

    @Override
    public void restoreState(Map<String, Object> map) {

    }
}
