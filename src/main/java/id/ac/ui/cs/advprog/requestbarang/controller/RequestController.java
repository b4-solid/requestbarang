package id.ac.ui.cs.advprog.requestbarang.controller;

import id.ac.ui.cs.advprog.requestbarang.model.RequestModel;
import id.ac.ui.cs.advprog.requestbarang.service.RequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/requests")
public class RequestController {

    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping
    @Async
    public CompletableFuture<ResponseEntity<RequestModel>> createRequest(@RequestBody RequestModel request) {
        return CompletableFuture.supplyAsync(() -> {
            RequestModel savedRequest = requestService.addRequest(request);
            return new ResponseEntity<>(savedRequest, HttpStatus.CREATED);
        });
    }

    @GetMapping("/user/{username}")
    @Async
    public CompletableFuture<ResponseEntity<List<RequestModel>>> findAllUserRequests(@PathVariable String username) {
        return CompletableFuture.supplyAsync(() -> {
            return ResponseEntity.ok(requestService.findByUsername(username));
        });
    }

    @GetMapping("/{requestId}")
    @Async
    public CompletableFuture<ResponseEntity<RequestModel>> findById(@PathVariable Long requestId) {
        return CompletableFuture.supplyAsync(() -> {
            Optional<RequestModel> optionalRequest = requestService.findById(requestId);
            if (optionalRequest.isPresent()) {
                return ResponseEntity.ok(optionalRequest.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        });
    }


    @GetMapping
    @Async
    public CompletableFuture<ResponseEntity<List<RequestModel>>> findAllRequests() {
        return CompletableFuture.supplyAsync(() -> {
            return ResponseEntity.ok(requestService.findAllRequest());
        });
    }

    @PutMapping("/{requestId}")
    @Async
    public CompletableFuture<ResponseEntity<RequestModel>> updateRequest(@PathVariable long requestId, @RequestBody RequestModel updatedRequest) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                updatedRequest.setId(requestId);
                RequestModel result = requestService.updateRequest(updatedRequest);
                return ResponseEntity.ok(result);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.notFound().build();
            }
        });
    }

    @DeleteMapping("/{requestId}")
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
