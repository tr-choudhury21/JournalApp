package com.journal.journalApp.controller;


import com.journal.journalApp.entity.JournalEntry;
import com.journal.journalApp.entity.User;
import com.journal.journalApp.service.JournalEntryService;
import com.journal.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName) {
        User user = userService.findByUsername(userName);
        List<JournalEntry> all = user.getJournalEntries();
        if (!all.isEmpty() && all != null) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry entry, @PathVariable String userName){

        try{
            journalEntryService.saveEntry(entry, userName);
            return new ResponseEntity<>(entry, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/id/{myid}")
    public ResponseEntity<JournalEntry> getEntryById(@PathVariable ObjectId myid){
        Optional<JournalEntry> journalEntry = Optional.ofNullable(journalEntryService.findById(myid));
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/id/{userName}/{myid}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId myid, @PathVariable String userName){
        journalEntryService.deleteById(myid, userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{userName}/{myid}")
    public ResponseEntity<?> updateEntryById(@PathVariable ObjectId myid,
                                             @RequestBody JournalEntry entry,
                                             @PathVariable String userName){
        JournalEntry old = journalEntryService.findById(myid);

        if(old != null){
            old.setTitle(entry.getTitle() != null && entry.getTitle().equals("") ? entry.getTitle() : old.getTitle()) ;
            old.setContent(entry.getContent() != null && entry.getContent().equals("") ? entry.getContent() : old.getContent()); ;
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }




}
