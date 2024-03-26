package issue185.datamodel;


import java.time.Instant;

public interface WithAudit {

    Trace trace();

    record Trace(String userId, Instant timeStamp, String clientIPAddress, String clientDevice) {
    }

}
