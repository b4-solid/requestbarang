package id.ac.ui.cs.advprog.requestbarang.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.math.BigDecimal;
import java.util.Currency;

import org.springframework.stereotype.Service;
import id.ac.ui.cs.advprog.requestbarang.model.RequestModel;
import id.ac.ui.cs.advprog.requestbarang.repository.RequestRepository;
import id.ac.ui.cs.advprog.requestbarang.service.RequestService;

import org.springframework.web.client.RestTemplate;


@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository repository;
    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/IDR";
    private final RestTemplate restTemplate;

    public RequestServiceImpl(RequestRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    //C(reate)
    @Override
    public RequestModel addRequest(RequestModel request) {
        validateCurrency(request.getCurrency());
        double convertedHarga = convertToIDR(request.getHarga(), request.getCurrency());
        request.setHarga((int) Math.ceil(convertedHarga));
        return repository.save(request);
    }

    //R(ead)
    @Override
    public List<RequestModel> findAllRequest() {
        return repository.findAll();
    }

    @Override
    public Optional<RequestModel> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<RequestModel> findByUsername(String username) {
        return repository.findByUsername(username);
    }


    //U(pdate)
    @Override
    public RequestModel updateRequest(RequestModel request) {
        validateCurrency(request.getCurrency());
        double convertedHarga = convertToIDR(request.getHarga(), request.getCurrency());
        request.setHarga((int) Math.ceil(convertedHarga));
        return repository.save(request);
    }

    //D(elete)
    public void deleteRequest(Long id) {
        repository.deleteById(id);
    }


    //Currency converter
    private void validateCurrency(String currencyCode) {
        try {
            Currency.getInstance(currencyCode);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid currency code");
        }
    }

    @SuppressWarnings("unchecked")
    public double getRate(String currencyCode) {
        Map<String, Object> response = restTemplate.getForObject(API_URL, Map.class);

        Map<String, Double> rates = (Map<String, Double>) response.get("rates");
        if (currencyCode == null) {
            throw new IllegalArgumentException("Currency code cannot be null");
        } else if (rates == null || !rates.containsKey(currencyCode)) {
            throw new IllegalArgumentException("Exchange rate for currency " + currencyCode + " not found");
        } else {
            double plainRate = rates.get(currencyCode);
            BigDecimal scientificNumber = new BigDecimal(plainRate);

            // Convert it to plain string representation
            String plainString = scientificNumber.toPlainString();
            return Double.parseDouble(plainString);
        }
    }

    public double convertToIDR(double price, String currencyCode) {
        if (currencyCode == null || currencyCode.isEmpty() || currencyCode.equalsIgnoreCase("IDR")) {
            return price;
        } else {
            double rate = getRate(currencyCode);
            return price / rate;
        }
    }
}