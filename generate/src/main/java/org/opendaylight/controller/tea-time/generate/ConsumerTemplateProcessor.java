package org.opendaylight.controller.tea-time.generate;
import java.util.Set;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class ConsumerTemplateProcessor {

  private static String basePackage = "org.opendaylight.controller.tea-time";

  public static void processConsumerTemplates(String appName, Set fieldKeys, VelocityEngine ve)  throws Exception{
    processConsumerImpl(appName, ve);
    processConsumerService(appName, ve);
    processConsumerModule(appName, ve);
    processConsumerYang(appName, ve);
    processInitialConfig( appName, ve );
  }

  private static void processInitialConfig( String appName, VelocityEngine ve) throws Exception{
    /*  next, get the Template  */
    Template template = ve.getTemplate( "consumer/initialConfig_consumer.vm" );
    /*  create a context and add data */
    VelocityContext context = new VelocityContext();
    context.put("app", appName);
    /* now render the template into a File */
    String path = "consumer/src/main/resources/configuration/initial/06-consumer-" + appName + "-sample.xml";
    CodeGeneratorUtil.writeFile(CodeGenerator.PATH_TO_ROOT_DIR, path, context, template);
  }

  private static void processConsumerImpl(String appName, VelocityEngine ve)  throws Exception{
    /*  next, get the Template  */
    Template template = ve.getTemplate( "consumer/consumerImpl.vm" );
    /*  create a context and add data */
    VelocityContext context = CodeGeneratorUtil.createBasicVelocityContext(appName);
    context.put("package", basePackage);
    String path = "consumer/src/main/java/"+ basePackage.replaceAll("\\.", "/") + "/consumer/"
        + CodeGeneratorUtil.capitalizeFirstLetter(appName)+"ConsumerImpl.java";
    CodeGeneratorUtil.writeFile( CodeGenerator.PATH_TO_ROOT_DIR, path, context, template);
  }

  private static void processConsumerService(String appName, VelocityEngine ve)  throws Exception{
    /*  next, get the Template  */
    Template template = ve.getTemplate( "consumer/consumerService.vm" );
    /*  create a context and add data */
    VelocityContext context = CodeGeneratorUtil.createBasicVelocityContext(appName);
    context.put("package", basePackage);
    String path = "consumer/src/main/java/"+ basePackage.replaceAll("\\.", "/") + "/consumer/"
        + CodeGeneratorUtil.capitalizeFirstLetter(appName)+"ConsumerService.java";
    CodeGeneratorUtil.writeFile( CodeGenerator.PATH_TO_ROOT_DIR, path, context, template);
  }

  private static void processConsumerModule(String appName, VelocityEngine ve)  throws Exception{
    /*  next, get the Template  */
    Template template = ve.getTemplate( "consumer/consumerModule.vm" );
    /*  create a context and add data */
    VelocityContext context = CodeGeneratorUtil.createBasicVelocityContext(appName);
    context.put("package", basePackage);
    String path = "consumer/src/main/java/org/opendaylight/controller/config/yang/config/"+
        appName +"_consumer/impl/" + CodeGeneratorUtil.capitalizeFirstLetter(appName)+"ConsumerModule.java";
    CodeGeneratorUtil.writeFile( CodeGenerator.PATH_TO_ROOT_DIR, path, context, template);
  }

  private static void processConsumerYang(String appName, VelocityEngine ve)  throws Exception{
    /*  next, get the Template  */
    Template template = ve.getTemplate( "consumer/consumerYang.vm" );
    /*  create a context and add data */
    VelocityContext context = CodeGeneratorUtil.createBasicVelocityContext(appName);
    context.put("package", basePackage);
    String path = "consumer/src/main/yang/"+ appName + "-consumer-impl.yang";
    CodeGeneratorUtil.writeFile( CodeGenerator.PATH_TO_ROOT_DIR, path, context, template);
  }

}