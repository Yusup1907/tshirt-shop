package org.commerce.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.util.UUID;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtil {

    public static String setUUID() {
        try {
            return UUID.randomUUID().toString().replace("-", "").toUpperCase();
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        return null;
    }

    public static Timestamp getAsTimestamp() {
        try {
            return new Timestamp(System.currentTimeMillis());
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        return null;
    }
}
