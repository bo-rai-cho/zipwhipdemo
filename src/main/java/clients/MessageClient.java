package clients;

import model.MessageInfo;
import model.request.EntityUrlEncodedData;
import model.response.MessageInfoResponse;
import org.springframework.web.client.RestOperations;

/**
 * @author Nikolay Ponomarev
 */
public final class MessageClient {

    private final RestOperations restOperations;

    public MessageClient(final RestOperations restOperations) {
        this.restOperations = restOperations;
    }

    /**
     * Send a message to a specific session
     *
     * @param session Session key
     * @param contactId Contact id
     * @param body Message body
     * @param scheduledDate Scheduled date (optional)
     * @return Message Info
     */
    public MessageInfo send(String session, String contactId, String body, String scheduledDate) {

        EntityUrlEncodedData data = EntityUrlEncodedData.session(session)
                .add("contactId", contactId)
                .add("body", body)
                .add("scheduledDate", scheduledDate);

        return restOperations.postForEntity("/message/send", data, MessageInfoResponse.class).getBody().getResponse();
    }
}
