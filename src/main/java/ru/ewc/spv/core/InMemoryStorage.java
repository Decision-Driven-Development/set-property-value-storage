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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * I am an in-memory storage for the stored values.
 *
 * @since 0.0.1
 */
public class InMemoryStorage {
    /**
     * The next set identifier.
     */
    private long next;

    /**
     * The stored values.
     */
    private final List<StoredValue> stored = new ArrayList<>(0);

    /**
     * Adds a new specification to the storage.
     *
     * @param spec The specification to be added.
     */
    public void add(final StoredValue spec) {
        this.stored.add(
            new StoredValue(
                this.setIdFor(spec),
                spec.property(),
                spec.value()
            )
        );
    }

    /**
     * Returns all properties for a given set as a list.
     *
     * @param set The set's identifier.
     * @return A list of properties.
     */
    public List<StoredValue> getAsList(final String set) {
        return this.allPropertiesFor(set).toList();
    }

    /**
     * Returns all properties for a given set.
     *
     * @param set The set's identifier.
     * @return A stream of properties.
     */
    private Stream<StoredValue> allPropertiesFor(final String set) {
        return this.stored.stream()
            .filter(specification -> set.equals(specification.set()));
    }

    /**
     * Sets the identifier for a given specification. Returns a new ID if it's not specified in the
     * spec.
     *
     * @param spec The specification.
     * @return The identifier.
     */
    private String setIdFor(final StoredValue spec) {
        final String result;
        if (spec.set() == null) {
            result = String.valueOf(++this.next);
        } else {
            result = spec.set();
        }
        return result;
    }

}
