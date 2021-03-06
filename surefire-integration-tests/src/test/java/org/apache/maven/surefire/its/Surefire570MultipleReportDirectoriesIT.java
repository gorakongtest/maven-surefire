package org.apache.maven.surefire.its;
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


import java.io.File;

/**
 * Test Surefire-570 Multiple report directories
 *
 * @author Kristian Rosenvold
 */
public class Surefire570MultipleReportDirectoriesIT
    extends SurefireVerifierTestClass
{

    public Surefire570MultipleReportDirectoriesIT()
    {
        super( "/surefire-570-multipleReportDirectories" );
    }

    public void testReportWithAggregate()
        throws Exception
    {

        failNever();
        executeTest(); // Hmm. This shouldn't be necessary but is another bug

        reset();

        addGoal( "-Daggregate=true" );
        execute( "surefire-report:report" );
        File siteFile = getSiteFile( "surefire-report.html" );
        assertContainsText( siteFile, "MyModule1ClassTest" );
        assertContainsText( siteFile, "MyModule2ClassTest" );
        assertContainsText( siteFile, "MyDummyClassM1Test" );
    }

    public void testReportWithoutAggregate()
        throws Exception
    {

        failNever();
        executeTest(); // Hmm. This shouldn't be necessary but is another bug

        reset();

        execute( "surefire-report:report" );
        File siteFile = getSiteFile( "module1", "surefire-report.html" );
        assertContainsText( siteFile, "MyModule1ClassTest" );
        assertContainsText( siteFile, "MyDummyClassM1Test" );
    }

}
