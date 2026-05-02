package com.portfolio.service;

import com.portfolio.model.Contact;
import com.portfolio.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository repo;

    // SAVE
    public Contact save(Contact c) {
        return repo.save(c);
    }

    // GET
    public List<Contact> getAll(String email) {
        if (email != null && !email.isEmpty()) {
            return repo.findByEmailContainingIgnoreCase(email);
        }
        return repo.findAll();
    }

    // DELETE
    public void delete(Long id) {
        repo.deleteById(id);
    }

    // ✅ PDF GENERATION
    public byte[] generatePdf() {
        try {
            List<Contact> list = repo.findAll();

            Document document = new Document();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            PdfWriter.getInstance(document, out);
            document.open();

            document.add(new Paragraph("Contact Messages\n\n"));

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);

            table.addCell("Name");
            table.addCell("Email");
            table.addCell("Message");
            table.addCell("Date");

            for (Contact c : list) {
                table.addCell(c.getName());
                table.addCell(c.getEmail());
                table.addCell(c.getMessage());
                table.addCell(String.valueOf(c.getCreatedAt()));
            }

            document.add(table);
            document.close();

            return out.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("PDF error");
        }
    }
}