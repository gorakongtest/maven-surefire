package org.apache.maven.surefire.booter;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */


import org.apache.maven.surefire.report.ReporterConfiguration;
import org.apache.maven.surefire.testset.DirectoryScannerParameters;
import org.apache.maven.surefire.testset.TestArtifactInfo;
import org.apache.maven.surefire.testset.TestRequest;

import java.util.Properties;

/**
* @author Kristian Rosenvold
*/
public class Foo
    implements DirectoryScannerParametersAware, TestRequestAware, ProviderPropertiesAware, ReporterConfigurationAware,
    SurefireClassLoadersAware, TestArtifactInfoAware
{
    DirectoryScannerParameters directoryScannerParameters;

    TestRequest testSuiteDefinition;

    Properties providerProperties;

    ReporterConfiguration reporterConfiguration;

    ClassLoader surefireClassLoader;

    ClassLoader testClassLoader;

    TestRequest testRequest;

    TestArtifactInfo testArtifactInfo;

    boolean called = false;

    public void setDirectoryScannerParameters( DirectoryScannerParameters directoryScanner )
    {
        this.directoryScannerParameters = directoryScanner;
        this.called = true;
    }


    public Boolean isCalled()
    {
        return Boolean.valueOf( called);
    }

    public void setTestSuiteDefinition( TestRequest testSuiteDefinition )
    {
        this.testSuiteDefinition = testSuiteDefinition;
        this.called = true;
    }

    public void setProviderProperties( Properties providerProperties )
    {
        this.providerProperties = providerProperties;
        this.called = true;
    }

    public void setReporterConfiguration( ReporterConfiguration reporterConfiguration )
    {
        this.reporterConfiguration = reporterConfiguration;
        this.called = true;
    }

    public void setClassLoaders( ClassLoader surefireClassLoader, ClassLoader testClassLoader )
    {
        this.testClassLoader = testClassLoader;
        this.surefireClassLoader = surefireClassLoader;
        this.called = true;
    }

    public void setTestRequest( TestRequest testRequest1 )
    {
        this.testRequest = testRequest1;
        this.called = true;
    }

    public void setTestArtifactInfo( TestArtifactInfo testArtifactInfo )
    {
        this.testArtifactInfo = testArtifactInfo;
        this.called = true;
    }
}
