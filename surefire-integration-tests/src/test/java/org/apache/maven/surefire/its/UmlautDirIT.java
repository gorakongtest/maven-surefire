package org.apache.maven.surefire.its;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.maven.it.Verifier;
import org.apache.maven.it.util.FileUtils;
import org.apache.maven.it.util.ResourceExtractor;

/**
 * Test a directory with an umlaut
 * 
 * @author <a href="mailto:dfabulich@apache.org">Dan Fabulich</a>
 * 
 */
public class UmlautDirIT
    extends AbstractSurefireIntegrationTestClass
{
    File testDir;

    public void testUmlaut()
        throws Exception
    {
        Verifier verifier = new Verifier( testDir.getAbsolutePath() );
        this.executeGoal( verifier, "test" );
        verifier.verifyErrorFreeLog();
        verifier.resetStreams();

        HelperAssertions.assertTestSuiteResults( 1, 0, 0, 0, testDir );
    }

    public void testUmlautIsolatedClassLoader()
        throws Exception
    {
        Verifier verifier = new Verifier( testDir.getAbsolutePath() );
        ArrayList goals = this.getInitialGoals();
        goals.add( "test" );
        goals.add( "-DuseSystemClassLoader=false" );
        verifier.executeGoals( goals );
        verifier.verifyErrorFreeLog();
        verifier.resetStreams();

        HelperAssertions.assertTestSuiteResults( 1, 0, 0, 0, testDir );
    }

    public void setUp()
        throws IOException
    {
        String tempDirPath = System.getProperty( "maven.test.tmpdir", System.getProperty( "java.io.tmpdir" ) );
        File tempDir = new File( tempDirPath );
        File targetDir = new File( "target" ).getAbsoluteFile();
        if ( targetDir.exists() && targetDir.isDirectory() )
        {
            tempDir = targetDir;
        }
        testDir = new File( tempDir, "/junit-pathWith\u00DCmlaut" );
        FileUtils.deleteDirectory( testDir );
        testDir = ResourceExtractor.extractResourcePath( getClass(), "/junit-pathWithUmlaut", testDir, true );
    }
}