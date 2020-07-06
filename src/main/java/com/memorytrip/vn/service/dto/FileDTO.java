package com.memorytrip.vn.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import com.memorytrip.vn.domain.enumeration.FileType;

/**
 * A DTO for the {@link com.memorytrip.vn.domain.File} entity.
 */
@ApiModel(description = "not an ignored comment")
public class FileDTO implements Serializable {
    
    private Long id;

    private String name;

    private String path;

    private String originName;

    private FileType type;


    private Long timelineId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public FileType getType() {
        return type;
    }

    public void setType(FileType type) {
        this.type = type;
    }

    public Long getTimelineId() {
        return timelineId;
    }

    public void setTimelineId(Long timelineId) {
        this.timelineId = timelineId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FileDTO)) {
            return false;
        }

        return id != null && id.equals(((FileDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FileDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", path='" + getPath() + "'" +
            ", originName='" + getOriginName() + "'" +
            ", type='" + getType() + "'" +
            ", timelineId=" + getTimelineId() +
            "}";
    }
}
