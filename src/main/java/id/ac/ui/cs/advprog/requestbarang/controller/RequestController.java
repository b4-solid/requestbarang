package id.ac.ui.cs.advprog.requestbarang.controller;

import id.ac.ui.cs.advprog.requestbarang.model.Request;
import id.ac.ui.cs.advprog.requestbarang.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @PostMapping("/create")
    public Request createRequest(@RequestParam String name, @RequestParam String imageLink, @RequestParam double price,
            @RequestParam String storeLink) {
        return requestService.createAndProcessRequest(name, imageLink, price, storeLink, storeLink);
    }
}
