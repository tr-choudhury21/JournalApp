package com.journal.journalApp.service;

import com.journal.journalApp.entity.JournalEntry;
import com.journal.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JournalEntryService {


    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntry journalEntry) {

        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public JournalEntry findById(ObjectId id) {
        return journalEntryRepository.findById(id).orElse(null);
    }

    public void deleteById(ObjectId id) {
        journalEntryRepository.deleteById(id);
    }
}
