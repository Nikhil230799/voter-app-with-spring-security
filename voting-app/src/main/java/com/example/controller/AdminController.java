package com.example.controller;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.common.Response;
import com.example.entity.Candidates;
import com.example.entity.Users;
import com.example.repository.CandidatesReporsitory;
import com.example.repository.UsersRepository;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/admin")
public class AdminController {

    Response response = null;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private CandidatesReporsitory candidatesReporsitory;

    @GetMapping("/test")
    public ResponseEntity<Response> teString() {
        response = new Response(200, "res", "hellow");
        return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);

    }

    @GetMapping("/userList")
    public ResponseEntity<Response> getUserList() {
        try {
            List<Users> userList = usersRepository.findAll();
            response = new Response(202, "List of all users", userList);
            return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response = new Response(500, e.getMessage(), e);
            return new ResponseEntity<Response>(response, HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/getCandidatesList")
    public ResponseEntity<Response> getCandidatesList() {
        try {
            List<Candidates> list = candidatesReporsitory.findAll();
            response = new Response(200, "users List", list);
            return new ResponseEntity<Response>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setData(e.getMessage());
            response.setResponseCode(500);
            return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/userList/{reportType}")
    public void getMethodName(@PathVariable(required = true) String reportType,
            HttpServletResponse servletResponse) {
        try {
            System.out.println("in reportType------------------" +reportType);
            List<Users> userList = usersRepository.findAll();
            // response = new Response(202, "List of all users", userList);

            servletResponse.setContentType("application/octet-stream");
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=userlist.xlsx";
            servletResponse.setHeader(headerKey, headerValue);

            XSSFWorkbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("user");

            // Create header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Index");
            headerRow.createCell(1).setCellValue("Username");
            headerRow.createCell(2).setCellValue("Email");
            headerRow.createCell(3).setCellValue("Phone NO");
            headerRow.createCell(4).setCellValue("User Status");
            headerRow.createCell(5).setCellValue("User Role");
            headerRow.createCell(6).setCellValue("User Voting status");
            headerRow.createCell(7).setCellValue("User Vote To");

            // Fill data rows
            int rowIndex = 1;
            for (Users user : userList) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(rowIndex);
                row.createCell(1).setCellValue(user.getUsr_username());
                row.createCell(2).setCellValue(user.getUsr_email());
                row.createCell(3).setCellValue(user.getUsr_phoneNo());
                row.createCell(4).setCellValue(user.isUsr_userStatus());
                row.createCell(5).setCellValue(user.getUsr_role());
                row.createCell(6).setCellValue(user.isUsr_voteStatus());
                if (user.getCandidates() != null && user.getCandidates().getCd_name() != null)
                    row.createCell(7).setCellValue(user.getCandidates().getCd_name());
                else
                    row.createCell(7).setCellValue("N/A");
            }

            // Auto-size columns
            for (int i = 0; i < 8; i++) {
                sheet.autoSizeColumn(i);
            }

            // Write the workbook to the response output stream
            workbook.write(servletResponse.getOutputStream());
            workbook.close();

            // return new ResponseEntity<HttpServletResponse>(servletResponse,
            // HttpStatus.ACCEPTED);

        } catch (Exception e) {
            response.setResponseCode(0);
            response.setData(e.getMessage());
            response.setResponseCode(500);
            System.out.println(
                    "-----------------------------------------------------------------------------------------------------------------------------------");
            return;
            
        }
    }

}
