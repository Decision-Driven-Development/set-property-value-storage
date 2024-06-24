/*
 * MIT License
 *
 * Copyright (c) 2024 Decision-Driven Development
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package ru.ewc.spv.core;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * I am a test for the {@link InMemoryStorage} class.
 *
 * @since 0.0.1
 */
final class InMemoryStorageTest {
    @Test
    void shouldGetSetsWithSingleProperties() {
        final InMemoryStorage actual = defaultStorage();
        MatcherAssert.assertThat(
            "The storage property for the first set should be fetched",
            actual.getAsList("1"),
            Matchers.allOf(
                Matchers.iterableWithSize(1),
                Matchers.hasItems(new StoredValue("1", "Property", "Value"))
            )
        );
        MatcherAssert.assertThat(
            "The storage property for the second set should be fetched",
            actual.getAsList("2"),
            Matchers.allOf(
                Matchers.iterableWithSize(1),
                Matchers.hasItems(new StoredValue("2", "NewProp", "NewValue"))
            )
        );
    }

    @Test
    void shouldGetSetWithMultipleProperties() {
        final InMemoryStorage actual = defaultStorage();
        MatcherAssert.assertThat(
            "Should fetch all properties of a set",
            actual.getAsList("3"),
            Matchers.allOf(
                Matchers.iterableWithSize(2),
                Matchers.hasItems(
                    new StoredValue("3", "Property", "ValueTheThird"),
                    new StoredValue("3", "AnotherProp", "AnotherValue")
                )
            )
        );
    }

    private static InMemoryStorage defaultStorage() {
        return storageWith(
            new StoredValue(null, "Property", "Value"),
            new StoredValue(null, "NewProp", "NewValue"),
            new StoredValue(null, "Property", "ValueTheThird"),
            new StoredValue("3", "AnotherProp", "AnotherValue")
        );
    }

    private static InMemoryStorage storageWith(final StoredValue... specifications) {
        final InMemoryStorage target = new InMemoryStorage();
        for (final StoredValue spec : specifications) {
            target.add(spec);
        }
        return target;
    }
}
