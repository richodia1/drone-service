package com.droneservice.dto;

import lombok.Data;

import java.util.List;

public @Data class AuditLogDTO {

    private List<AuditDTO> logs;
    private int total;
}
