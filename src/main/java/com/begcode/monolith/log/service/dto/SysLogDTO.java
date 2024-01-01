package com.begcode.monolith.log.service.dto;

import com.begcode.monolith.domain.AbstractAuditingEntity;
import com.begcode.monolith.domain.enumeration.LogType;
import com.begcode.monolith.domain.enumeration.OperateType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.ZonedDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

// jhipster-needle-add-import - JHipster will add getters and setters here, do not remove

/**

 * {@link com.begcode.monolith.log.domain.SysLog}的DTO。
 */
@Schema(description = "系统日志")
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SysLogDTO extends AbstractAuditingEntity<Long, SysLogDTO> {

    private Long id;

    /**
     * 日志类型
     */
    @Schema(description = "日志类型")
    private LogType logType;

    /**
     * 日志内容
     */
    @Schema(description = "日志内容")
    private String logContent;

    /**
     * 操作类型
     */
    @Schema(description = "操作类型")
    private OperateType operateType;

    /**
     * 操作用户账号
     */
    @Schema(description = "操作用户账号")
    private String userid;

    /**
     * 操作用户名称
     */
    @Schema(description = "操作用户名称")
    private String username;

    /**
     * IP
     */
    @Schema(description = "IP")
    private String ip;

    /**
     * 请求java方法
     */
    @Schema(description = "请求java方法")
    private String method;

    /**
     * 请求路径
     */
    @Schema(description = "请求路径")
    private String requestUrl;

    /**
     * 请求参数
     */
    @Schema(description = "请求参数")
    private String requestParam;

    /**
     * 请求类型
     */
    @Schema(description = "请求类型")
    private String requestType;

    /**
     * 耗时
     */
    @Schema(description = "耗时")
    private Long costTime;

    /**
     * 创建者Id
     */
    @Schema(description = "创建者Id")
    private Long createdBy;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private ZonedDateTime createdDate;

    /**
     * 修改者Id
     */
    @Schema(description = "修改者Id")
    private Long lastModifiedBy;

    /**
     * 修改时间
     */
    @Schema(description = "修改时间")
    private ZonedDateTime lastModifiedDate;

    // jhipster-needle-dto-add-field - JHipster will add fields here, do not remove

    public SysLogDTO id(Long id) {
        this.id = id;
        return this;
    }

    public SysLogDTO logType(LogType logType) {
        this.logType = logType;
        return this;
    }

    public SysLogDTO logContent(String logContent) {
        this.logContent = logContent;
        return this;
    }

    public SysLogDTO operateType(OperateType operateType) {
        this.operateType = operateType;
        return this;
    }

    public SysLogDTO userid(String userid) {
        this.userid = userid;
        return this;
    }

    public SysLogDTO username(String username) {
        this.username = username;
        return this;
    }

    public SysLogDTO ip(String ip) {
        this.ip = ip;
        return this;
    }

    public SysLogDTO method(String method) {
        this.method = method;
        return this;
    }

    public SysLogDTO requestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
        return this;
    }

    public SysLogDTO requestParam(String requestParam) {
        this.requestParam = requestParam;
        return this;
    }

    public SysLogDTO requestType(String requestType) {
        this.requestType = requestType;
        return this;
    }

    public SysLogDTO costTime(Long costTime) {
        this.costTime = costTime;
        return this;
    }

    public SysLogDTO createdBy(Long createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public SysLogDTO createdDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public SysLogDTO lastModifiedBy(Long lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;
    }

    public SysLogDTO lastModifiedDate(ZonedDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }
    // jhipster-needle-dto-add-getters-setters - JHipster will add getters and setters here, do not remove

}
