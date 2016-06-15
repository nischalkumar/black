package org.wizindia.black.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.wizindia.black.domain.Feed;

import java.io.File;

/**
 * Created by hari-om on 6/16/16.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FileDownloadResponse {
    Feed feed;
    File file;
}
