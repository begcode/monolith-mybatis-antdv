package com.begcode.monolith.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.begcode.monolith.config.Constants;
import com.diboot.core.binding.annotation.BindEntity;
import com.diboot.core.binding.annotation.BindEntityList;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.*;
import org.apache.commons.lang3.StringUtils;

/**
 * A user.
 */
@TableName(value = "jhi_user")
public class User extends AbstractAuditingEntity<Long, User> implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @NotNull
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    @TableField(value = "login")
    private String login;

    @JsonIgnore
    @NotNull
    @Size(min = 60, max = 60)
    @TableField(value = "password_hash")
    private String password;

    @Size(max = 50)
    @TableField(value = "first_name")
    private String firstName;

    @Size(max = 50)
    @TableField(value = "last_name")
    private String lastName;

    @Email
    @Size(min = 5, max = 254)
    @TableField(value = "email")
    private String email;

    @TableField(value = "mobile")
    private String mobile;

    @TableField(value = "birthday")
    private ZonedDateTime birthday;

    @NotNull
    @TableField(value = "activated")
    private boolean activated = false;

    @Size(min = 2, max = 10)
    @TableField(value = "lang_key")
    private String langKey;

    @Size(max = 256)
    @TableField(value = "image_url")
    private String imageUrl;

    @Size(max = 20)
    @TableField(value = "activation_key")
    @JsonIgnore
    private String activationKey;

    @Size(max = 20)
    @TableField(value = "reset_key")
    @JsonIgnore
    private String resetKey;

    @TableField(value = "reset_date")
    private Instant resetDate = null;

    @JsonIgnore
    @TableField(exist = false)
    @BindEntityList(
        entity = Authority.class,
        joinTable = "rel_jhi_user__authorities",
        joinColumn = "jhi_user_id",
        inverseJoinColumn = "authorities_id",
        condition = "this.id=rel_jhi_user__authorities.jhi_user_id AND rel_jhi_user__authorities.authorities_id=id"
    )
    private List<Authority> authorities = new ArrayList<>();

    @TableField(value = "department_id")
    private Long departmentId;

    @TableField(value = "department_name")
    private String departmentName;

    @TableField(value = "position_id")
    private Long positionId;

    @TableField(value = "position_name")
    private String positionName;

    @JsonIgnore
    @TableField(exist = false)
    @BindEntity(entity = Department.class, condition = "this.department_id=id")
    private Department department;

    @JsonIgnore
    @TableField(exist = false)
    @BindEntity(entity = Position.class, condition = "this.position_id=id")
    private Position position;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User id(Long id) {
        this.id = id;
        return this;
    }

    public String getLogin() {
        return login;
    }

    // Lowercase the login before saving it in database
    public void setLogin(String login) {
        this.login = StringUtils.lowerCase(login, Locale.ENGLISH);
    }

    public User login(String login) {
        this.setLogin(login);
        return this;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User password(String password) {
        this.setPassword(login);
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public User firstName(String firstName) {
        this.setFirstName(firstName);
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public User lastName(String lastName) {
        this.setLastName(lastName);
        return this;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User email(String email) {
        this.setEmail(email);
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public User mobile(String mobile) {
        this.setMobile(mobile);
        return this;
    }

    public ZonedDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(ZonedDateTime birthday) {
        this.birthday = birthday;
    }

    public User birthday(ZonedDateTime birthday) {
        this.setBirthday(birthday);
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public User imageUrl(String imageUrl) {
        this.setImageUrl(imageUrl);
        return this;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public User activated(boolean activated) {
        this.setActivated(activated);
        return this;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public User activationKey(String activationKey) {
        this.setActivationKey(activationKey);
        return this;
    }

    public String getResetKey() {
        return resetKey;
    }

    public void setResetKey(String resetKey) {
        this.resetKey = resetKey;
    }

    public User resetKey(String resetKey) {
        this.setResetKey(resetKey);
        return this;
    }

    public Instant getResetDate() {
        return resetDate;
    }

    public void setResetDate(Instant resetDate) {
        this.resetDate = resetDate;
    }

    public User resetDate(Instant resetDate) {
        this.setResetDate(resetDate);
        return this;
    }

    public String getLangKey() {
        return langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    public User langKey(String langKey) {
        this.setLangKey(langKey);
        return this;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public User authorities(List<Authority> authorities) {
        this.setAuthorities(authorities);
        return this;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public User departmentId(Long departmentId) {
        this.setDepartmentId(departmentId);
        return this;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public User positionId(Long positionId) {
        this.setPositionId(positionId);
        return this;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public User departmentName(String departmentName) {
        this.setDepartmentName(departmentName);
        return this;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getPositionName() {
        return positionName;
    }

    public User positionName(String positionName) {
        this.setPositionName(positionName);
        return this;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public User department(Department department) {
        this.setDepartment(department);
        return this;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public User position(Position position) {
        this.setPosition(position);
        return this;
    }

    public User addAuthorities(Authority authority) {
        this.authorities.add(authority);
        return this;
    }

    public User removeAuthorities(Authority authority) {
        this.authorities.remove(authority);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        return id != null && id.equals(((User) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "User{" +
            "login='" + login + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", mobile='" + mobile + '\'' +
            ", birthday='" + birthday + '\'' +
            ", imageUrl='" + imageUrl + '\'' +
            ", activated='" + activated + '\'' +
            ", langKey='" + langKey + '\'' +
            ", activationKey='" + activationKey + '\'' +
            "}";
    }
}
