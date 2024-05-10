package id.ac.ui.cs.advprog.requestbarang.controller;

import id.ac.ui.cs.advprog.requestbarang.model.Request;
import id.ac.ui.cs.advprog.requestbarang.service.RequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping
    @Async
    public CompletableFuture<ResponseEntity<Request>> createRequest(@RequestBody Request request) {
        return CompletableFuture.supplyAsync(() -> {
            Request savedRequest = requestService.addRequest(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedRequest);
        });
    }

    @GetMapping("/{requestId}")
    @Async
    public CompletableFuture<ResponseEntity<Request>> findById(@PathVariable Long requestId) {
        return CompletableFuture.supplyAsync(() -> {
            Request request = requestService.findById(requestId);
            if (request != null) {
                return ResponseEntity.ok(request);
            } else {
                return ResponseEntity.notFound().build();
            }
        });
    }

    @GetMapping
    @Async
    public CompletableFuture<ResponseEntity<List<Request>>> findAllRequests() {
        return CompletableFuture.supplyAsync(() -> {
            List<Request> requests = requestService.findAllRequests();
            if (!requests.isEmpty()) {
                return ResponseEntity.ok(requests);
            } else {
                return ResponseEntity.notFound().build();
            }
        });
    }

    @PutMapping("/edit/{requestId}")
    @Async
    public CompletableFuture<ResponseEntity<Request>> updateRequest(@PathVariable @RequestBody Request updatedRequest) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Request result = requestService.updateRequest(updatedRequest);
                return ResponseEntity.ok(result);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.notFound().build();
            }
        });
    }

    @DeleteMapping("/delete/{requestId}")
    @Async
    public CompletableFuture<ResponseEntity<Void>> deleteRequest(@PathVariable Long requestId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                requestService.deleteRequest(requestId);
                return ResponseEntity.ok().build();
            } catch (Exception e) {
                return ResponseEntity.notFound().build();
            }
        });
    }
}
