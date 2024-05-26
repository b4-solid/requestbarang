package id.ac.ui.cs.advprog.requestbarang.factory;

import id.ac.ui.cs.advprog.requestbarang.model.RequestModel;

public interface RequestFactory {
    RequestModel createRequestModel(long id, long productId, String username, int harga, String currency, String name, String deskripsi, String imageLink, String storeLink, Boolean status);
}
