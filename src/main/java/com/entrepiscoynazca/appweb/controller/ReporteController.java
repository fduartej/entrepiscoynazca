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
    

    @GetMapping("/jasper/repventas")
    public void generateReporteVentas(HttpServletResponse response) throws IOException, JRException {
        response.setContentType("application/x-download");
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"ventas.pdf\""));
        OutputStream out = response.getOutputStream();
        Connection connection = JdbcTemplate.getDataSource().getConnection();
        File file = ResourceUtils.getFile("classpath:/reports/ReporteDeVentas.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);
        JasperExportManager.exportReportToPdfStream(jasperPrint, out);

    }

}
