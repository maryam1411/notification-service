package com.notification.service.domain;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationEvent {

    private String purpose;
    private String email;
    private NotificationType notificationType;


}
