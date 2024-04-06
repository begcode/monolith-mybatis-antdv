package com.begcode.monolith.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class DepartmentAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDepartmentAllPropertiesEquals(Department expected, Department actual) {
        assertDepartmentAutoGeneratedPropertiesEquals(expected, actual);
        assertDepartmentAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDepartmentAllUpdatablePropertiesEquals(Department expected, Department actual) {
        assertDepartmentUpdatableFieldsEquals(expected, actual);
        assertDepartmentUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDepartmentAutoGeneratedPropertiesEquals(Department expected, Department actual) {
        assertThat(expected)
            .as("Verify Department auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDepartmentUpdatableFieldsEquals(Department expected, Department actual) {
        assertThat(expected)
            .as("Verify Department relevant properties")
            .satisfies(e -> assertThat(e.getName()).as("check name").isEqualTo(actual.getName()))
            .satisfies(e -> assertThat(e.getCode()).as("check code").isEqualTo(actual.getCode()))
            .satisfies(e -> assertThat(e.getAddress()).as("check address").isEqualTo(actual.getAddress()))
            .satisfies(e -> assertThat(e.getPhoneNum()).as("check phoneNum").isEqualTo(actual.getPhoneNum()))
            .satisfies(e -> assertThat(e.getLogo()).as("check logo").isEqualTo(actual.getLogo()))
            .satisfies(e -> assertThat(e.getContact()).as("check contact").isEqualTo(actual.getContact()))
            .satisfies(e -> assertThat(e.getCreateUserId()).as("check createUserId").isEqualTo(actual.getCreateUserId()))
            .satisfies(e -> assertThat(e.getCreateTime()).as("check createTime").isEqualTo(actual.getCreateTime()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDepartmentUpdatableRelationshipsEquals(Department expected, Department actual) {
        assertThat(expected)
            .as("Verify Department relationships")
            .satisfies(e -> assertThat(e.getAuthorities()).as("check authorities").isEqualTo(actual.getAuthorities()))
            .satisfies(e -> assertThat(e.getParent()).as("check parent").isEqualTo(actual.getParent()));
    }
}
