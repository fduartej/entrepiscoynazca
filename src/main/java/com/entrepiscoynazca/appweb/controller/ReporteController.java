package com.entrepiscoynazca.appweb.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;

import javax.servlet.http.HttpServletResponse;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;

import net.sf.jasperreports.engine.*;

@Controller
public class ReporteController {
    
    private JdbcTemplate jdbcTemplate;

    public ReporteController(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/jasper/repventas")
    public void generateReporteVentas(HttpServletResponse response)  {
        response.setContentType("application/x-download");
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"ventas.pdf\""));
        try {
            OutputStream out = response.getOutputStream();
            File file = ResourceUtils.getFile("classpath:./reports/ReporteDeVentas.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, 
                    jdbcTemplate.getDataSource().getConnection());
            JasperExportManager.exportReportToPdfStream(jasperPrint, out);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
