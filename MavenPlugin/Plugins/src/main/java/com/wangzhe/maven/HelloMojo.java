package com.wangzhe.maven;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;

@Mojo(name="reporter" ,requiresDependencyResolution = ResolutionScope.COMPILE)
public class HelloMojo extends AbstractMojo {

    @Parameter(property = "reporter.className")
    private String className;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        //执行逻辑
    	getLog().info("className: " + className);
    }


}