package com.journal.journalApp.controller;


import com.journal.journalApp.entity.JournalEntry;
import com.journal.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping()
    public List<JournalEntry> getAll(){

        return journalEntryService.getAll();
    }

    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry entry){
        entry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(entry);
        return entry;
    }

    @GetMapping("/id/{myid}")
    public JournalEntry getEntryById(@PathVariable ObjectId myid){
        return journalEntryService.findById(myid);
    }

    @DeleteMapping("/id/{myid}")
    public boolean deleteEntryById(@PathVariable ObjectId myid){

        journalEntryService.deleteById(myid);

        return true;
    }

    @PutMapping("/id/{id}")
    public JournalEntry updateEntryById(@PathVariable ObjectId myid, @RequestBody JournalEntry entry){
        JournalEntry old = journalEntryService.findById(myid);

        if(old != null){
            old.setTitle(entry.getTitle() != null && entry.getTitle().equals("") ? entry.getTitle() : old.getTitle()) ;
            old.setContent(entry.getContent() != null && entry.getContent().equals("") ? entry.getContent() : old.getContent()); ;
        }

        journalEntryService.saveEntry(old);

        return old;

    }




}
