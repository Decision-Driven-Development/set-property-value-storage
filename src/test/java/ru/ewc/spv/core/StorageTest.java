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

public class StorageTest {
    @Test
    void shouldGetSetsWithSingleProperties() {
        final Storage actual = storageWith(
            new StoredValue(null, "Property", "Value"),
            new StoredValue(null, "NewProp", "NewValue")
        );
        MatcherAssert.assertThat(
            "The storage property for the first set should be fetched",
            actual.get("1"),
            Matchers.equalTo(new StoredValue("1", "Property", "Value"))
        );
        MatcherAssert.assertThat(
            "The storage property for the second set should be fetched",
            actual.get("2"),
            Matchers.equalTo(new StoredValue("2", "NewProp", "NewValue"))
        );
    }

    private static Storage storageWith(StoredValue... specifications) {
        final Storage target = new Storage();
        for (StoredValue specification : specifications) {
            target.add(specification);
        }
        return target;
    }
}
