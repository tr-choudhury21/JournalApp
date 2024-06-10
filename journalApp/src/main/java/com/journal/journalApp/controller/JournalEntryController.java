package com.journal.journalApp.controller;


import com.journal.journalApp.entity.JournalEntry;
import com.journal.journalApp.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping()
    public List<JournalEntry> getAll(){
        return null;
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry entry){
        journalEntryService.saveEntry(entry);
        return true;
    }

    @GetMapping("/id/{myid}")
    public JournalEntry getEntryById(@PathVariable long myid){
        return null;
    }

    @DeleteMapping("/id/{myid}")
    public JournalEntry deleteEntryById(@PathVariable long myid){
        return null;
    }

    @PutMapping
    public JournalEntry updateEntryById(@PathVariable long myid, @RequestBody JournalEntry entry){
        return null;

    }


}
