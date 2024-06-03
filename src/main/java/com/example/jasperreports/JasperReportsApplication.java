package com.example.jasperreports;

import com.example.jasperreports.model.Micro;
import com.example.jasperreports.model.Nutrition;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class JasperReportsApplication {

    public static void main(String[] args) throws JRException, FileNotFoundException {
       // SpringApplication.run(JasperReportsApplication.class, args);
        File file = ResourceUtils.getFile("classpath:nutrition.jrxml");

        String destinationFilePath="r.html";

        Nutrition nutrition1 = new Nutrition("protein","g",60,30);
        Nutrition nutrition2 = new Nutrition("carbs","g",20,41);
        Nutrition nutrition3 = new Nutrition("fat","g",23,12);
        Nutrition nutrition4 = new Nutrition("sugar","g",5,5);
        Nutrition nutrition5 = new Nutrition("vitamin c","g",22,12);

        Micro micro1 = new Micro("protein",20);
        Micro micro2 = new Micro("carb",30);
        Micro micro3 = new Micro("fat",10);
        Micro micro4 = new Micro("sugar",50);
        Micro micro5 = new Micro("vitamin c",100);

        List<Micro>micros = List.of(micro1,micro2,micro3,micro4,micro5);
        JRBeanCollectionDataSource chartMicro_dataSource =
                new JRBeanCollectionDataSource(micros);



        List<Nutrition> nutritionData = List.of(nutrition1,nutrition2,nutrition3,nutrition4,nutrition5);
        JRBeanCollectionDataSource dataSet = new JRBeanCollectionDataSource(nutritionData);



        Map<String,Object> parameters=new HashMap<>();
        parameters.put("first name","saif");
        parameters.put("age",24);
        parameters.put("dob","26/2000");
        parameters.put("parameter_toBeConsumed_inDataset",dataSet);
        parameters.put("micro_parameter",chartMicro_dataSource);
        JasperReport report = JasperCompileManager.compileReport(file.getAbsolutePath());
        JasperPrint print = JasperFillManager.fillReport(report,parameters,dataSet);
        JasperExportManager.exportReportToHtmlFile(print,destinationFilePath);
        System.out.println("report created successfully");
    }

}
