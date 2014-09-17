package org.opendaylight.controller.tea-time.generate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class ProviderTemplateProcessor {
  private static String basePackage = "org.opendaylight.controller.tea-time";

  public static void processProviderTemplates(String appName, Set fieldKeys, VelocityEngine ve)  throws Exception{
    processProviderTemplate(appName, fieldKeys, ve);
    processProviderYang(appName, ve);
    processProviderModule(appName, ve);
    processInitialConfig( appName, ve );
  }

  private static void processInitialConfig( String appName, VelocityEngine ve) throws Exception{
      /*  next, get the Template  */
      Template template = ve.getTemplate( "provider/initialConfig_provider.vm" );
      /*  create a context and add data */
      VelocityContext context = new VelocityContext();
      context.put("app", appName);
      /* now render the template into a File */
      String path = "provider/src/main/resources/configuration/initial/05-provider-" + appName + "-sample.xml";
      CodeGeneratorUtil.writeFile(CodeGenerator.PATH_TO_ROOT_DIR, path, context, template);
  }

  private static void processProviderTemplate(String appName, Set fieldKeys, VelocityEngine ve)  throws Exception{
    /*  next, get the Template  */
    Template template = ve.getTemplate( "provider/provider.vm" );
    /*  create a context and add data */
    VelocityContext context = CodeGeneratorUtil.createBasicVelocityContext(appName);
    String lowerApp = appName.toLowerCase();
    context.put("lowerApp", lowerApp);
    List<ProviderField> fields = new ArrayList<>();
    for(Object fieldKey : fieldKeys) {
      String name = (String)fieldKey;
      ProviderField field1 = new ProviderField(name, "set" +CodeGeneratorUtil.capitalizeFirstLetter(name));
      fields.add(field1);
    }
    String capitalAppName = CodeGeneratorUtil.capitalizeFirstLetter(appName);
    context.put("fields", fields);
    context.put("package", basePackage);
    String path = "provider/src/main/java/"+ basePackage.replaceAll("\\.", "/") + "/provider/"+capitalAppName+"Provider.java";
    CodeGeneratorUtil.writeFile( CodeGenerator.PATH_TO_ROOT_DIR, path, context, template);
  }

  private static void processProviderYang(String appName, VelocityEngine ve)  throws Exception {
    Template template = ve.getTemplate( "provider/providerYang.vm" );
    VelocityContext context = CodeGeneratorUtil.createBasicVelocityContext(appName);
    String path = "provider/src/main/yang/"+ appName + "-provider-impl.yang";
    CodeGeneratorUtil.writeFile( CodeGenerator.PATH_TO_ROOT_DIR, path, context, template);
  }

  private static void processProviderModule(String appName, VelocityEngine ve)  throws Exception {
    Template template = ve.getTemplate( "provider/providerModule.vm" );
    VelocityContext context = CodeGeneratorUtil.createBasicVelocityContext(appName);
    context.put("package", basePackage);
    String path = "provider/src/main/java/org/opendaylight/controller/config/yang/config/"
        + appName + "_provider/impl/"+ CodeGeneratorUtil.capitalizeFirstLetter(appName) +"ProviderModule.java";
    CodeGeneratorUtil.writeFile( CodeGenerator.PATH_TO_ROOT_DIR, path, context, template);
  }

}