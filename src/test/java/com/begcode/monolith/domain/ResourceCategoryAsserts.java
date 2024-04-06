package com.begcode.monolith.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class ResourceCategoryAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertResourceCategoryAllPropertiesEquals(ResourceCategory expected, ResourceCategory actual) {
        assertResourceCategoryAutoGeneratedPropertiesEquals(expected, actual);
        assertResourceCategoryAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertResourceCategoryAllUpdatablePropertiesEquals(ResourceCategory expected, ResourceCategory actual) {
        assertResourceCategoryUpdatableFieldsEquals(expected, actual);
        assertResourceCategoryUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertResourceCategoryAutoGeneratedPropertiesEquals(ResourceCategory expected, ResourceCategory actual) {
        assertThat(expected)
            .as("Verify ResourceCategory auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertResourceCategoryUpdatableFieldsEquals(ResourceCategory expected, ResourceCategory actual) {
        assertThat(expected)
            .as("Verify ResourceCategory relevant properties")
            .satisfies(e -> assertThat(e.getTitle()).as("check title").isEqualTo(actual.getTitle()))
            .satisfies(e -> assertThat(e.getCode()).as("check code").isEqualTo(actual.getCode()))
            .satisfies(e -> assertThat(e.getOrderNumber()).as("check orderNumber").isEqualTo(actual.getOrderNumber()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertResourceCategoryUpdatableRelationshipsEquals(ResourceCategory expected, ResourceCategory actual) {
        assertThat(expected)
            .as("Verify ResourceCategory relationships")
            .satisfies(e -> assertThat(e.getParent()).as("check parent").isEqualTo(actual.getParent()));
    }
}
