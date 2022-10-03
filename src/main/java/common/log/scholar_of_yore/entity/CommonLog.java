package common.log.scholar_of_yore.entity;

import lombok.*;

import java.io.Serializable;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CommonLog implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private String requestId;

    private String PSM;

    private String level;

    private String context;

    private Boolean del;

    private Long gmtCreate;

    private Long gmtModified;

    public CommonLog(Long userId, String requestId, String PSM, String level, String context) {
        this.userId = userId;
        this.requestId = requestId;
        this.PSM = PSM;
        this.level = level;
        this.context = context;
    }
}