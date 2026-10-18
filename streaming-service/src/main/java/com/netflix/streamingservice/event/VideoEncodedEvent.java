package com.netflix.streamingservice.event;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class VideoEncodedEvent {
    private String movieId;
    private String hlsUrl;
    private String masterPlaylistKey;
    private boolean success;
    private String errorMessage;
}
