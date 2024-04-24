package id.ac.ui.cs.advprog.requestbarang.enums;

import lombok.Getter;

@Getter
public enum RequestStatus {
    REQUESTING("Requesting"),
    ON_SALE("On Sale");

    private final String value;

    private RequestStatus(String value) {
        this.value = value;
    }

    public static boolean contains(String param) {
        for (RequestStatus requestStatus : RequestStatus.values()) {
            if (requestStatus.name().equals(param)) {
                return true;
            }
        }
        return false;
    }
}
