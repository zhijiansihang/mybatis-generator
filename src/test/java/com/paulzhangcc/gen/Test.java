package com.paulzhangcc.gen;

import com.paulzhangcc.tools.swagger.cmd.SwaggerCodegen;
import com.samskivert.mustache.Mustache;
import lombok.Builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author paul
 * @description
 * @date 2019/4/17
 */
@Builder
public class Test {
    /**
     *  http://127.0.0.1:7050/v2/api-docs/402835816a34a2d3016a34a482760002
     *  file:///F:/tools/mybatis-generator/src/test/resources/user.json
     */
    private String swaggerJson;
    private String targetPackage;
    private String targetProject;

    public void generatorController(){
        String string = "generate -i" + swaggerJson+" "+
                "--library spring-boot -l PaulSpring " +
                "--api-package="+targetPackage+".gen.swagger.api --model-package="+targetPackage+".gen.swagger.model " +
                "-o "+targetProject+" --additional-properties useBeanValidation=false,hideGenerationTimestamp=true";
        SwaggerCodegen.main(string.split("\\s+"));
    }

    public void generatorFeignClient(){
        String string = "generate -i" + swaggerJson+" "+
                "--library spring-cloud -l PaulSpring " +
                "--api-package="+targetPackage+".gen.swagger.client --model-package="+targetPackage+".gen.swagger.model " +
                "-o "+targetProject+" --additional-properties useBeanValidation=false,hideGenerationTimestamp=true,isOpenFeign=true";
        SwaggerCodegen.main(string.split("\\s+"));
    }

    public void generatorAxiosClient(){
        String string = "generate -i" + swaggerJson+" "+
                "-l typescript-axios " +
                "-o "+targetProject+" --additional-properties isGenerateSupportingFiles=true";
        SwaggerCodegen.main(string.split("\\s+"));
    }



    public static void main(String[] args) throws Exception {
        builder()
                .swaggerJson("file:///F:/tools/mybatis-generator/src/test/resources/user.json")
                .targetProject("F:\\code\\vue-admin-template\\src")
                //.targetProject("F:\\gen\\js2")
                .build().generatorAxiosClient();

    }

    public static void testTemplate() {
        ArrayList<Map<String, String>> ops = new ArrayList<>();
        {
            Map<String, String> map = new HashMap<>();
            map.put("name", "zjf");
            ops.add(map);
        }
        {
            Map<String, String> map = new HashMap<>();
            map.put("name", "paul");
            ops.add(map);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("useBeanValidation", "false");
        map.put("hello", "world");
        map.put("ops", ops);
        map.put("year", "1");
        String execute = Mustache.compiler().compile("{{#useBeanValidation}}{{#ops}}{{name}}-{{year}}\r\n{{/ops}}{{/useBeanValidation}}").execute(map);
        System.out.println(execute);
    }

}
