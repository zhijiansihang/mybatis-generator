package com.paulzhangcc.gen;

import com.samskivert.mustache.Mustache;
import io.swagger.codegen.CodegenConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * @author paul
 * @description
 * @date 2019/4/17
 */
public class Test {
    public static void main(String[] args) throws Exception {

        Map<String, Object> map = new HashMap<>();
        map.put("useBeanValidation", "false");
        String execute = Mustache.compiler().compile("{{#useBeanValidation}}\n" +
                "import org.springframework.validation.annotation.Validated;\n" +
                "import javax.validation.Valid;\n" +
                "import javax.validation.constraints.*;\n" +
                "{{/useBeanValidation}}").execute(map);

        String s = "generate -i file:///F:/tools/mybatis-generator/src/test/resources/user.json " +
                "--library spring-boot -l PaulSpring " +
                "--group-id=com.paulzhangcc --artifact-id=test-spring-boot --api-package=com.paulzhangcc.gen.swagger.api --model-package=com.paulzhangcc.gen.swagger.model " +
                "-o /gen/demo --additional-properties useBeanValidation=false,hideGenerationTimestamp=true";
        String[] split = s.split("\\s+");
        com.paulzhangcc.tools.swagger.cmd.SwaggerCodegen.main(split);

/*
        String s1 = "generate -i file:///F:/tools/mybatis-generator/src/test/resources/user.json " +
                "--library spring-cloud -l PaulSpring " +
                "--group-id=com.paulzhangcc --artifact-id=test-spring-boot --api-package=com.paulzhangcc.test.cloudcontroller --model-package=com.paulzhangcc.test.model " +
                "-o /gen/demo " +
                "--additional-properties useBeanValidation=false,generateForOpenFeign=true,hideGenerationTimestamp=true";
        String[] split1 = s1.split("\\s+");
        SwaggerCodegen.main(split1);
*/


        System.setProperty(CodegenConstants.SUPPORTING_FILES, "EncodingUtils.java");
        String s3 = "generate -i file:///F:/tools/mybatis-generator/src/test/resources/user.json " +
                "--library feign -l PaulJava " +
                "--group-id=com.paulzhangcc --artifact-id=test-spring-boot --api-package=com.paulzhangcc.gen.swagger.client --model-package=com.paulzhangcc.gen.swagger.model " +
                "-o /gen/demo " +
                "--additional-properties useBeanValidation=false,generateForOpenFeign=true,hideGenerationTimestamp=true,isGenerateSupportingFiles=Y";
        String[] split3 = s3.split("\\s+");
        com.paulzhangcc.tools.swagger.cmd.SwaggerCodegen.main(split3);

    }

}
