package com.begcode.monolith.domain;

import static com.begcode.monolith.domain.AssertUtils.zonedDataTimeSameInstant;
import static org.assertj.core.api.Assertions.assertThat;

public class UploadFileAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertUploadFileAllPropertiesEquals(UploadFile expected, UploadFile actual) {
        assertUploadFileAutoGeneratedPropertiesEquals(expected, actual);
        assertUploadFileAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertUploadFileAllUpdatablePropertiesEquals(UploadFile expected, UploadFile actual) {
        assertUploadFileUpdatableFieldsEquals(expected, actual);
        assertUploadFileUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertUploadFileAutoGeneratedPropertiesEquals(UploadFile expected, UploadFile actual) {
        assertThat(expected)
            .as("Verify UploadFile auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertUploadFileUpdatableFieldsEquals(UploadFile expected, UploadFile actual) {
        assertThat(expected)
            .as("Verify UploadFile relevant properties")
            .satisfies(e -> assertThat(e.getUrl()).as("check url").isEqualTo(actual.getUrl()))
            .satisfies(e -> assertThat(e.getFullName()).as("check fullName").isEqualTo(actual.getFullName()))
            .satisfies(e -> assertThat(e.getName()).as("check name").isEqualTo(actual.getName()))
            .satisfies(e -> assertThat(e.getThumb()).as("check thumb").isEqualTo(actual.getThumb()))
            .satisfies(e -> assertThat(e.getExt()).as("check ext").isEqualTo(actual.getExt()))
            .satisfies(e -> assertThat(e.getType()).as("check type").isEqualTo(actual.getType()))
            .satisfies(e -> assertThat(e.getPath()).as("check path").isEqualTo(actual.getPath()))
            .satisfies(e -> assertThat(e.getFolder()).as("check folder").isEqualTo(actual.getFolder()))
            .satisfies(e -> assertThat(e.getOwnerEntityName()).as("check ownerEntityName").isEqualTo(actual.getOwnerEntityName()))
            .satisfies(e -> assertThat(e.getOwnerEntityId()).as("check ownerEntityId").isEqualTo(actual.getOwnerEntityId()))
            .satisfies(e -> assertThat(e.getBusinessTitle()).as("check businessTitle").isEqualTo(actual.getBusinessTitle()))
            .satisfies(e -> assertThat(e.getBusinessDesc()).as("check businessDesc").isEqualTo(actual.getBusinessDesc()))
            .satisfies(e -> assertThat(e.getBusinessStatus()).as("check businessStatus").isEqualTo(actual.getBusinessStatus()))
            .satisfies(
                e ->
                    assertThat(e.getCreateAt())
                        .as("check createAt")
                        .usingComparator(zonedDataTimeSameInstant)
                        .isEqualTo(actual.getCreateAt())
            )
            .satisfies(e -> assertThat(e.getFileSize()).as("check fileSize").isEqualTo(actual.getFileSize()))
            .satisfies(e -> assertThat(e.getReferenceCount()).as("check referenceCount").isEqualTo(actual.getReferenceCount()))
            .satisfies(e -> assertThat(e.getCreatedBy()).as("check createdBy").isEqualTo(actual.getCreatedBy()))
            .satisfies(e -> assertThat(e.getCreatedDate()).as("check createdDate").isEqualTo(actual.getCreatedDate()))
            .satisfies(e -> assertThat(e.getLastModifiedBy()).as("check lastModifiedBy").isEqualTo(actual.getLastModifiedBy()))
            .satisfies(e -> assertThat(e.getLastModifiedDate()).as("check lastModifiedDate").isEqualTo(actual.getLastModifiedDate()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertUploadFileUpdatableRelationshipsEquals(UploadFile expected, UploadFile actual) {
        assertThat(expected)
            .as("Verify UploadFile relationships")
            .satisfies(e -> assertThat(e.getCategory()).as("check category").isEqualTo(actual.getCategory()));
    }
}