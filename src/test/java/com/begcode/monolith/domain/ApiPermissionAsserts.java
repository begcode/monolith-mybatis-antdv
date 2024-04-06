package com.begcode.monolith.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiPermissionAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertApiPermissionAllPropertiesEquals(ApiPermission expected, ApiPermission actual) {
        assertApiPermissionAutoGeneratedPropertiesEquals(expected, actual);
        assertApiPermissionAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertApiPermissionAllUpdatablePropertiesEquals(ApiPermission expected, ApiPermission actual) {
        assertApiPermissionUpdatableFieldsEquals(expected, actual);
        assertApiPermissionUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertApiPermissionAutoGeneratedPropertiesEquals(ApiPermission expected, ApiPermission actual) {
        assertThat(expected)
            .as("Verify ApiPermission auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertApiPermissionUpdatableFieldsEquals(ApiPermission expected, ApiPermission actual) {
        assertThat(expected)
            .as("Verify ApiPermission relevant properties")
            .satisfies(e -> assertThat(e.getServiceName()).as("check serviceName").isEqualTo(actual.getServiceName()))
            .satisfies(e -> assertThat(e.getName()).as("check name").isEqualTo(actual.getName()))
            .satisfies(e -> assertThat(e.getCode()).as("check code").isEqualTo(actual.getCode()))
            .satisfies(e -> assertThat(e.getDescription()).as("check description").isEqualTo(actual.getDescription()))
            .satisfies(e -> assertThat(e.getType()).as("check type").isEqualTo(actual.getType()))
            .satisfies(e -> assertThat(e.getMethod()).as("check method").isEqualTo(actual.getMethod()))
            .satisfies(e -> assertThat(e.getUrl()).as("check url").isEqualTo(actual.getUrl()))
            .satisfies(e -> assertThat(e.getStatus()).as("check status").isEqualTo(actual.getStatus()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertApiPermissionUpdatableRelationshipsEquals(ApiPermission expected, ApiPermission actual) {
        assertThat(expected)
            .as("Verify ApiPermission relationships")
            .satisfies(e -> assertThat(e.getParent()).as("check parent").isEqualTo(actual.getParent()))
            .satisfies(e -> assertThat(e.getAuthorities()).as("check authorities").isEqualTo(actual.getAuthorities()));
    }
}
