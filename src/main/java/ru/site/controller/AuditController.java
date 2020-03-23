package ru.site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.site.RequestTemplate.EmployeeRequest;
import ru.site.RequestTemplate.StaffRequest;
import ru.site.ResponseTemplate.AuditEmployee;
import ru.site.service.AuditService;

import java.util.List;

@RestController
public class AuditController {
    @Autowired
    AuditService auditService;

    @PostMapping("/auditStaff")
    private ResponseEntity<List<AuditEmployee>> getAuditStaff(@RequestBody StaffRequest request) {
        return new ResponseEntity<>(
                auditService.getAuditStaff(request.getFrom(), request.getTo()),
                HttpStatus.OK);
    }

    @PostMapping("/auditEmployee")
    private ResponseEntity<AuditEmployee> getAuditEmployee(@RequestBody EmployeeRequest request) {
        return new ResponseEntity<>(
                auditService.getAuditEmployee(request.getId(), request.getFrom(), request.getTo()),
                HttpStatus.OK);
    }

}
