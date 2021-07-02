package com.entrepiscoynazca.appweb.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;

import net.sf.jasperreports.engine.*;

@Controller
public class ReporteController {
    
    private JdbcTemplate jdbcTemplate;
    private ResourceLoader resourceLoader;

    public ReporteController(JdbcTemplate jdbcTemplate, ResourceLoader resourceLoader){
        this.jdbcTemplate = jdbcTemplate;
        this.resourceLoader = resourceLoader;
    }

    @GetMapping("/jasper/repventas")
    public void generateReporteVentas(HttpServletResponse response)  {
        response.setContentType("application/x-download");
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"ventas.pdf\""));
        try {
            OutputStream out = response.getOutputStream();
            Resource resource = resourceLoader.getResource("classpath:./reports/ReporteDeVentas.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(resource.getInputStream());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, 
                    jdbcTemplate.getDataSource().getConnection());
            JasperExportManager.exportReportToPdfStream(jasperPrint, out);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
