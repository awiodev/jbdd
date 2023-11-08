package io.github.awiodev.jbdd.core;

import io.github.awiodev.jbdd.core.definition.ObjectsDatabase;
import io.github.awiodev.jbdd.core.exceptions.NotFoundException;
import io.github.awiodev.jbdd.core.impl.ObjectsMapDatabase;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ObjectsMapDatabaseTest {

    @Nested
    class Contains {

        @Test
        void returnsTrueWhenSessionKeyIsPresent() {
            String sessionId = UUID.randomUUID().toString();
            Map<String, Object> map = new HashMap<>();
            map.put(String.format("%s_key1", sessionId), "");
            map.put(String.format("%s_key2", sessionId), "");
            map.put(String.format("%s_key1", "1"), "");
            map.put(String.format("%s_key2", "2"), "");
            map.put(String.format("%s_key3", "3"), "");

            ObjectsDatabase objectsDatabase = ObjectsMapDatabase.builder()
                .withMap(map)
                .build();

            var actual = objectsDatabase.contains(sessionId, "key2");
            Assertions.assertThat(actual).isTrue();
        }

        @Test
        void returnsFalseWhenSessionKeyIsNotPresent() {
            String sessionId = UUID.randomUUID().toString();
            Map<String, Object> map = new HashMap<>();
            map.put(String.format("%s_key1", sessionId), "");
            map.put(String.format("%s_key2", sessionId), "");
            map.put(String.format("%s_key1", "1"), "");
            map.put(String.format("%s_key2", "2"), "");
            map.put(String.format("%s_key3", "3"), "");

            ObjectsDatabase objectsDatabase = ObjectsMapDatabase.builder()
                .withMap(map)
                .build();

            var actual = objectsDatabase.contains(sessionId, "unknown");
            Assertions.assertThat(actual).isFalse();
        }

        @Test
        void throwsNpeWhenSessionIdIsNull() {
            ObjectsDatabase objectsDatabase = ObjectsMapDatabase.builder()
                .build();

            Assertions.assertThatThrownBy(() ->
                    objectsDatabase.contains(null, "key"))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Session id cannot be null or empty");
        }

        @Test
        void throwsNpeWhenSessionIdIsEmpty() {
            ObjectsDatabase objectsDatabase = ObjectsMapDatabase.builder()
                .build();

            Assertions.assertThatThrownBy(() ->
                    objectsDatabase.contains("", "key"))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Session id cannot be null or empty");
        }

        @Test
        void throwsNpeWhenKeyIsNull() {
            ObjectsDatabase objectsDatabase = ObjectsMapDatabase.builder()
                .build();

            Assertions.assertThatThrownBy(() ->
                    objectsDatabase.contains("sdsdsd", null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Key cannot be null or empty");
        }

        @Test
        void throwsNpeWhenKeyIsEmpty() {
            ObjectsDatabase objectsDatabase = ObjectsMapDatabase.builder()
                .build();

            Assertions.assertThatThrownBy(() ->
                    objectsDatabase.contains("sdsdsd", ""))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Key cannot be null or empty");
        }
    }

    @Nested
    class Save {

        @Test
        void throwsNpeWhenSessionIdIsNull() {
            ObjectsDatabase objectsDatabase = ObjectsMapDatabase.builder()
                .build();

            Assertions.assertThatThrownBy(() ->
                    objectsDatabase.save(null, "key", null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Session id cannot be null or empty");
        }

        @Test
        void throwsNpeWhenSessionIdIsEmpty() {
            ObjectsDatabase objectsDatabase = ObjectsMapDatabase.builder()
                .build();

            Assertions.assertThatThrownBy(() ->
                    objectsDatabase.save("", "key", null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Session id cannot be null or empty");
        }

        @Test
        void throwsNpeWhenKeyIsNull() {
            ObjectsDatabase objectsDatabase = ObjectsMapDatabase.builder()
                .build();

            Assertions.assertThatThrownBy(() ->
                    objectsDatabase.save("sdsdsd", null, null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Key cannot be null or empty");
        }

        @Test
        void throwsNpeWhenKeyIsEmpty() {
            ObjectsDatabase objectsDatabase = ObjectsMapDatabase.builder()
                .build();

            Assertions.assertThatThrownBy(() ->
                    objectsDatabase.save("sdsdsd", "", null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Key cannot be null or empty");
        }

        @Test
        void storesObjectUnderGivenSessionIdAndKey() {
            String sessionId = UUID.randomUUID().toString();
            String key = "mykey";
            String expectedKey = String.format("%s_%s", sessionId, key);
            String value = "value";

            Map<String, Object> map = new HashMap<>();
            ObjectsDatabase objectsDatabase = ObjectsMapDatabase.builder()
                .withMap(map)
                .build();

            objectsDatabase.save(sessionId, key, value);

            Object actual = map.get(expectedKey);
            Assertions.assertThat(actual).isEqualTo(value);
        }
    }

    @Nested
    class Get {

        @Test
        void throwsNpeWhenSessionIdIsNull() {
            ObjectsDatabase objectsDatabase = ObjectsMapDatabase.builder()
                .build();

            Assertions.assertThatThrownBy(() ->
                    objectsDatabase.get(null, "key", String.class))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Session id cannot be null or empty");
        }

        @Test
        void throwsNpeWhenSessionIdIsEmpty() {
            ObjectsDatabase objectsDatabase = ObjectsMapDatabase.builder()
                .build();

            Assertions.assertThatThrownBy(() ->
                    objectsDatabase.get("", "key", String.class))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Session id cannot be null or empty");
        }

        @Test
        void throwsNpeWhenKeyIsNull() {
            ObjectsDatabase objectsDatabase = ObjectsMapDatabase.builder()
                .build();

            Assertions.assertThatThrownBy(() ->
                    objectsDatabase.get("sdsdsd", null, String.class))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Key cannot be null or empty");
        }

        @Test
        void throwsNpeWhenKeyIsEmpty() {
            ObjectsDatabase objectsDatabase = ObjectsMapDatabase.builder()
                .build();

            Assertions.assertThatThrownBy(() ->
                    objectsDatabase.get("sdsdsd", "", String.class))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Key cannot be null or empty");
        }

        @Test
        void throwsNotFoundExceptionWhenDataNotFound() {
            ObjectsDatabase objectsDatabase = ObjectsMapDatabase.builder()
                .build();

            String key = "123213";

            Assertions.assertThatThrownBy(() ->
                    objectsDatabase.get("sdsdsd", key, String.class))
                .isInstanceOf(NotFoundException.class)
                .hasMessage(String.format("Value for provided key: '%s' does not exists!", key));
        }

        @Test
        void retrievesObjectForGivenSessionIdAndKey() {
            String sessionId = UUID.randomUUID().toString();
            String key = "mykey";
            String composedKey = String.format("%s_%s", sessionId, key);
            String value = "value";

            Map<String, Object> map = new HashMap<>();
            map.put(composedKey, value);

            ObjectsDatabase objectsDatabase = ObjectsMapDatabase.builder()
                .withMap(map)
                .build();

            String actual = objectsDatabase.get(sessionId, key, String.class);

            Assertions.assertThat(actual).isEqualTo(value);
        }
    }

    @Nested
    class Delete {

        @Test
        void throwsNpeWhenSessionIdIsNull() {
            ObjectsDatabase objectsDatabase = ObjectsMapDatabase.builder()
                .build();

            Assertions.assertThatThrownBy(() ->
                    objectsDatabase.delete(null, "key"))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Session id cannot be null or empty");
        }

        @Test
        void throwsNpeWhenSessionIdIsEmpty() {
            ObjectsDatabase objectsDatabase = ObjectsMapDatabase.builder()
                .build();

            Assertions.assertThatThrownBy(() ->
                    objectsDatabase.delete("", "key"))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Session id cannot be null or empty");
        }

        @Test
        void throwsNpeWhenKeyIsNull() {
            ObjectsDatabase objectsDatabase = ObjectsMapDatabase.builder()
                .build();

            Assertions.assertThatThrownBy(() ->
                    objectsDatabase.delete("sdsdsd", null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Key cannot be null or empty");
        }

        @Test
        void throwsNpeWhenKeyIsEmpty() {
            ObjectsDatabase objectsDatabase = ObjectsMapDatabase.builder()
                .build();

            Assertions.assertThatThrownBy(() ->
                    objectsDatabase.delete("sdsdsd", ""))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Key cannot be null or empty");
        }

        @Test
        void deletesObjectForGivenSessionIdAndKey() {
            String sessionId = UUID.randomUUID().toString();
            String key = "mykey";
            String composedKey = String.format("%s_%s", sessionId, key);
            String value = "value";

            Map<String, Object> map = new HashMap<>();
            map.put(composedKey, value);

            ObjectsDatabase objectsDatabase = ObjectsMapDatabase.builder()
                .withMap(map)
                .build();

            objectsDatabase.delete(sessionId, key);

            var actual = map.get(composedKey);

            Assertions.assertThat(actual).isNull();
        }
    }

    @Nested
    class DeleteSession {

        @Test
        void throwsNpeWhenSessionIdIsNull() {
            ObjectsDatabase objectsDatabase = ObjectsMapDatabase.builder()
                .build();

            Assertions.assertThatThrownBy(() ->
                    objectsDatabase.deleteSession(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Session id cannot be null or empty");
        }

        @Test
        void throwsNpeWhenSessionIdIsEmpty() {
            ObjectsDatabase objectsDatabase = ObjectsMapDatabase.builder()
                .build();

            Assertions.assertThatThrownBy(() ->
                    objectsDatabase.deleteSession(""))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Session id cannot be null or empty");
        }

        @Test
        void deletesAllSessionObjectForGivenSessionId() {
            String sessionId = UUID.randomUUID().toString();

            String composedKey1 = String.format("%s_%s", sessionId, "1");
            String composedKey2 = String.format("%s_%s", sessionId, "2");
            String composedKey3 = String.format("%s_%s", sessionId, "3");
            String composedKey4 = String.format("%s_%s", sessionId, "4");

            Map<String, Object> map = new HashMap<>();
            map.put(composedKey1, "1");
            map.put(composedKey2, "2");
            map.put(composedKey3, "3");
            map.put(composedKey4, "4");


            map.put("other_1", "1");
            map.put("other_2", "1");
            map.put("other_3", "1");

            ObjectsDatabase objectsDatabase = ObjectsMapDatabase.builder()
                .withMap(map)
                .build();

            objectsDatabase.deleteSession(sessionId);

            Assertions.assertThat(map.size()).isEqualTo(3);
            Assertions.assertThat(map.get("other_1")).isNotNull();
            Assertions.assertThat(map.get("other_2")).isNotNull();
            Assertions.assertThat(map.get("other_3")).isNotNull();
        }
    }

    @Nested
    class DeleteAll {

        @Test
        void deletesAllEntriesFromMap() {
            String sessionId = UUID.randomUUID().toString();

            String composedKey1 = String.format("%s_%s", sessionId, "1");
            String composedKey2 = String.format("%s_%s", sessionId, "2");
            String composedKey3 = String.format("%s_%s", sessionId, "3");
            String composedKey4 = String.format("%s_%s", sessionId, "4");

            Map<String, Object> map = new HashMap<>();
            map.put(composedKey1, "1");
            map.put(composedKey2, "2");
            map.put(composedKey3, "3");
            map.put(composedKey4, "4");


            map.put("other_1", "1");
            map.put("other_2", "1");
            map.put("other_3", "1");

            ObjectsDatabase objectsDatabase = ObjectsMapDatabase.builder()
                .withMap(map)
                .build();

            objectsDatabase.deleteAll();

            Assertions.assertThat(map).isEmpty();
        }
    }

    @Nested
    class Size {

        @Test
        void returnsNumOfAllItems() {
            String sessionId = UUID.randomUUID().toString();

            String composedKey1 = String.format("%s_%s", sessionId, "1");
            String composedKey2 = String.format("%s_%s", sessionId, "2");
            String composedKey3 = String.format("%s_%s", sessionId, "3");
            String composedKey4 = String.format("%s_%s", sessionId, "4");

            Map<String, Object> map = new HashMap<>();
            map.put(composedKey1, "1");
            map.put(composedKey2, "2");
            map.put(composedKey3, "3");
            map.put(composedKey4, "4");


            map.put("other_1", "1");
            map.put("other_2", "1");
            map.put("other_3", "1");

            ObjectsDatabase objectsDatabase = ObjectsMapDatabase.builder()
                .withMap(map)
                .build();

            int actual = objectsDatabase.size();

            Assertions.assertThat(actual).isEqualTo(7);
        }
    }
}
