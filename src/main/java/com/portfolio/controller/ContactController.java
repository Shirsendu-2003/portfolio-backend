package com.portfolio.controller;

import com.portfolio.dto.ContactRequest;
import com.portfolio.dto.ReplyRequest;
import com.portfolio.model.Contact;
import com.portfolio.service.ContactService;
import com.portfolio.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin
@RequiredArgsConstructor
public class ContactController {

    private final ContactService service;
    private final EmailService emailService;

    @PostMapping
    public Contact save(@RequestBody Contact c) {
        return service.save(c);
    }

    @GetMapping
    public List<Contact> getAll(@RequestParam(required = false) String email) {
        return service.getAll(email);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // ✅ EXPORT PDF
    @GetMapping("/export/pdf")
    public ResponseEntity<byte[]> exportPdf() {

        byte[] pdf = service.generatePdf();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=messages.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

    @PostMapping("/reply")
public ResponseEntity<String> sendReply(@RequestBody ReplyRequest req) {
    emailService.sendReply(req.getEmail(), req.getSubject(), req.getMessage());
    return ResponseEntity.ok("Email sent successfully");
}
}
