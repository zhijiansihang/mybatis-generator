package com.paulzhangcc.tools.swagger.gen;

import io.swagger.codegen.CodegenModel;
import io.swagger.codegen.CodegenProperty;
import io.swagger.codegen.languages.SpringCodegen;
import io.swagger.models.Model;

import java.util.Map;

/**
 * @author paul
 */
public class PaulSpringCodegen  extends SpringCodegen {
    public PaulSpringCodegen(){
        super();
        embeddedTemplateDir = templateDir = "PaulSpring";
    }
    @Override
    public String getName() {
        return "PaulSpring";
    }

    @Override
    public void processOpts() {
        super.processOpts();
        writePropertyBack(USE_BEANVALIDATION, useBeanValidation);
        apiTemplateFiles.remove("api.mustache");
        apiTemplateFiles.remove("apiController.mustache");
        apiTemplateFiles.put("apiRestController.mustache", "RestController.java");
        apiTemplateFiles.put("apiService.mustache","Service.java");
    }

    @Override
    public void postProcessModelProperty(CodegenModel model, CodegenProperty property) {
        super.postProcessModelProperty(model,property);
        model.imports.remove("ApiModelProperty");
        model.imports.remove("ApiModel");
    }

    @Override
    public CodegenModel fromModel(String name, Model model, Map<String, Model> allDefinitions) {
        CodegenModel codegenModel = super.fromModel(name, model, allDefinitions);
        codegenModel.imports.remove("ApiModel");
        return codegenModel;
    }

}