package com.student.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/issues")
public class IssueController {

    @Autowired
    private IssueRepository issueRepository;

    // CREATE issue
    @PostMapping
    public Issue createIssue(@RequestBody Issue issue) {
        return issueRepository.save(issue);
    }

    // READ all issues
    @GetMapping
    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }

    // READ single issue by ID
    @GetMapping("/{id}")
    public Issue getIssueById(@PathVariable Long id) {
        return issueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Issue not found"));
    }

    // UPDATE issue fields (RCA, resolution, status)
    @PutMapping("/{id}")
    public Issue updateIssue(@PathVariable Long id, @RequestBody Issue updatedIssue) {
        return issueRepository.findById(id).map(issue -> {

            issue.setRca(updatedIssue.getRca());
            issue.setResolution(updatedIssue.getResolution());
            issue.setStatus(updatedIssue.getStatus());

            return issueRepository.save(issue);
        }).orElseThrow(() -> new RuntimeException("Issue not found"));
    }
}
