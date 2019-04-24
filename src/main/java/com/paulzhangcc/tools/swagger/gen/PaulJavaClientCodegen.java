package com.paulzhangcc.tools.swagger.gen;

import io.swagger.codegen.CodegenModel;
import io.swagger.codegen.CodegenProperty;
import io.swagger.codegen.languages.JavaClientCodegen;
import io.swagger.models.Model;

import java.util.Map;

/**
 * @author paul
 * @description
 * @date 2019/4/24
 */
public class PaulJavaClientCodegen extends JavaClientCodegen {
    public PaulJavaClientCodegen() {
        super();
        templateDir = "PaulJava";
        embeddedTemplateDir = "Java";
    }

    @Override
    public String getName() {
        return "PaulJava";
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
