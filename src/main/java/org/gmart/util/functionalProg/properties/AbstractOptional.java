/*******************************************************************************
 * Copyright 2020 Grégoire Martinetti
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package org.gmart.util.functionalProg.properties;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public abstract class AbstractOptional<T> implements IOptional<T> {
	
	/**
     * Common instance for {@code empty()}.
     */
    private static final AbstractOptional<?> EMPTY = new AbstractOptional<>() {
		@Override
		protected Object getValue() {
			return null;
		}
    };
    
    
    public static <T> AbstractOptional<T> of(Supplier<T> supplier){
    	return new SuppliedOptional<>(supplier);
    }
    
    
    
	
	protected abstract T getValue();
	
	
	/**
     * Returns an empty {@code Optional} instance.  No value is present for this
     * {@code Optional}.
     *
     * @apiNote
     * Though it may be tempting to do so, avoid testing if an object is empty
     * by comparing with {@code ==} against instances returned by
     * {@code Optional.empty()}.  There is no guarantee that it is a singleton.
     * Instead, use {@link #isPresent()}.
     *
     * @param <T> The type of the non-existent value
     * @return an empty {@code Optional}
     */
    public static<T> AbstractOptional<T> empty() {
        @SuppressWarnings("unchecked")
        AbstractOptional<T> t = (AbstractOptional<T>) EMPTY;
        return t;
    }
	
    
    /**
     * Returns an {@code Optional} describing the given non-{@code null}
     * value.
     *
     * @param value the value to describe, which must be non-{@code null}
     * @param <T> the type of the value
     * @return an {@code Optional} with the value present
     * @throws NullPointerException if value is {@code null}
     */
    public static <T> AbstractOptional<T> of(T value) {
        return new AbstractOptional<>() {
			@Override
			protected T getValue() {
				return value;
			}
        };
    }

    /**
     * Returns an {@code Optional} describing the given value, if
     * non-{@code null}, otherwise returns an empty {@code Optional}.
     *
     * @param value the possibly-{@code null} value to describe
     * @param <T> the type of the value
     * @return an {@code Optional} with a present value if the specified value
     *         is non-{@code null}, otherwise an empty {@code Optional}
     */
    public static <T> AbstractOptional<T> ofNullable(T value) {
        return value == null ? empty() : of(value);
    }

    /**
     * If a value is present, returns the value, otherwise throws
     * {@code NoSuchElementException}.
     *
     * @apiNote
     * The preferred alternative to this method is {@link #orElseThrow()}.
     *
     * @return the non-{@code null} value described by this {@code Optional}
     * @throws NoSuchElementException if no value is present
     */
    public T get() {
        T value = getValue();
		if (value == null) {
            throw new NoSuchElementException("No value present");
        }
        return value;
    }
    
    
    
	/**
     * If a value is present, returns {@code true}, otherwise {@code false}.
     *
     * @return {@code true} if a value is present, otherwise {@code false}
     */
    public boolean isPresent() {
        return getValue() != null;
    }

    /**
     * If a value is present, performs the given action with the value,
     * otherwise does nothing.
     *
     * @param action the action to be performed, if a value is present
     * @throws NullPointerException if value is present and the given action is
     *         {@code null}
     */
    public void ifPresent(Consumer<? super T> action) {
        T value = getValue();
		if (value != null) {
            action.accept(value);
        }
    }
    
    /**
     * If a value is present, performs the given action with the value,
     * otherwise performs the given empty-based action.
     *
     * @param action the action to be performed, if a value is present
     * @param emptyAction the empty-based action to be performed, if no value is
     *        present
     * @throws NullPointerException if a value is present and the given action
     *         is {@code null}, or no value is present and the given empty-based
     *         action is {@code null}.
     * @since 9
     */
    public void ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction) {
        T value = getValue();
		if (value != null) {
            action.accept(value);
        } else {
            emptyAction.run();
        }
    }

    /**
     * If a value is present, and the value matches the given predicate,
     * returns an {@code Optional} describing the value, otherwise returns an
     * empty {@code Optional}.
     *
     * @param predicate the predicate to apply to a value, if present
     * @return an {@code Optional} describing the value of this
     *         {@code Optional}, if a value is present and the value matches the
     *         given predicate, otherwise an empty {@code Optional}
     * @throws NullPointerException if the predicate is {@code null}
     */
    public AbstractOptional<T> filter(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        if (!isPresent()) {
            return this;
        } else {
            return predicate.test(getValue()) ? this : empty();
        }
    }

    /**
     * If a value is present, returns an {@code Optional} describing (as if by
     * {@link #ofNullable}) the result of applying the given mapping function to
     * the value, otherwise returns an empty {@code Optional}.
     *
     * <p>If the mapping function returns a {@code null} result then this method
     * returns an empty {@code Optional}.
     *
     * @apiNote
     * This method supports post-processing on {@code Optional} values, without
     * the need to explicitly check for a return status.  For example, the
     * following code traverses a stream of URIs, selects one that has not
     * yet been processed, and creates a path from that URI, returning
     * an {@code Optional<Path>}:
     *
     * <pre>{@code
     *     Optional<Path> p =
     *         uris.stream().filter(uri -> !isProcessedYet(uri))
     *                       .findFirst()
     *                       .map(Paths::get);
     * }</pre>
     *
     * Here, {@code findFirst} returns an {@code Optional<URI>}, and then
     * {@code map} returns an {@code Optional<Path>} for the desired
     * URI if one exists.
     *
     * @param mapper the mapping function to apply to a value, if present
     * @param <U> The type of the value returned from the mapping function
     * @return an {@code Optional} describing the result of applying a mapping
     *         function to the value of this {@code Optional}, if a value is
     *         present, otherwise an empty {@code Optional}
     * @throws NullPointerException if the mapping function is {@code null}
     */
    public <U> AbstractOptional<U> map(Function<? super T, ? extends U> mapper) {
        Objects.requireNonNull(mapper);
        if (!isPresent()) {
            return empty();
        } else {
            return OptProperty.ofNullable(mapper.apply(getValue()));
        }
    }

    /**
     * If a value is present, returns the result of applying the given
     * {@code Optional}-bearing mapping function to the value, otherwise returns
     * an empty {@code Optional}.
     *
     * <p>This method is similar to {@link #map(Function)}, but the mapping
     * function is one whose result is already an {@code Optional}, and if
     * invoked, {@code flatMap} does not wrap it within an additional
     * {@code Optional}.
     *
     * @param <U> The type of value of the {@code Optional} returned by the
     *            mapping function
     * @param mapper the mapping function to apply to a value, if present
     * @return the result of applying an {@code Optional}-bearing mapping
     *         function to the value of this {@code Optional}, if a value is
     *         present, otherwise an empty {@code Optional}
     * @throws NullPointerException if the mapping function is {@code null} or
     *         returns a {@code null} result
     */
    public <U> AbstractOptional<U> flatMap(Function<? super T, ? extends OptProperty<? extends U>> mapper) {
        Objects.requireNonNull(mapper);
        if (!isPresent()) {
            return empty();
        } else {
            @SuppressWarnings("unchecked")
            OptProperty<U> r = (OptProperty<U>) mapper.apply(getValue());
            return Objects.requireNonNull(r);
        }
    }

    
    
    /**
     * If a value is present, returns an {@code Optional} describing the value,
     * otherwise returns an {@code Optional} produced by the supplying function.
     *
     * @param supplier the supplying function that produces an {@code Optional}
     *        to be returned
     * @return returns an {@code Optional} describing the value of this
     *         {@code Optional}, if a value is present, otherwise an
     *         {@code Optional} produced by the supplying function.
     * @throws NullPointerException if the supplying function is {@code null} or
     *         produces a {@code null} result
     * @since 9
     */
    public AbstractOptional<T> or(Supplier<? extends OptProperty<? extends T>> supplier) {
        Objects.requireNonNull(supplier);
        if (isPresent()) {
            return this;
        } else {
            @SuppressWarnings("unchecked")
            OptProperty<T> r = (OptProperty<T>) supplier.get();
            return Objects.requireNonNull(r);
        }
    }

    /**
     * If a value is present, returns a sequential {@link Stream} containing
     * only that value, otherwise returns an empty {@code Stream}.
     *
     * @apiNote
     * This method can be used to transform a {@code Stream} of optional
     * elements to a {@code Stream} of present value elements:
     * <pre>{@code
     *     Stream<Optional<T>> os = ..
     *     Stream<T> s = os.flatMap(Optional::stream)
     * }</pre>
     *
     * @return the optional value as a {@code Stream}
     * @since 9
     */
    public Stream<T> stream() {
        if (!isPresent()) {
            return Stream.empty();
        } else {
            return Stream.of(getValue());
        }
    }

    /**
     * If a value is present, returns the value, otherwise returns
     * {@code other}.
     *
     * @param other the value to be returned, if no value is present.
     *        May be {@code null}.
     * @return the value, if present, otherwise {@code other}
     */
    
   
    public T orElse(T other) {
        T value = getValue();
		return value != null ? value : other;
    }

    /**
     * If a value is present, returns the value, otherwise returns the result
     * produced by the supplying function.
     *
     * @param supplier the supplying function that produces a value to be returned
     * @return the value, if present, otherwise the result produced by the
     *         supplying function
     * @throws NullPointerException if no value is present and the supplying
     *         function is {@code null}
     */
    
    public T orElseGet(Supplier<? extends T> supplier) {
        T value = getValue();
		return value != null ? value : supplier.get();
    }

    /**
     * If a value is present, returns the value, otherwise throws
     * {@code NoSuchElementException}.
     *
     * @return the non-{@code null} value described by this {@code Optional}
     * @throws NoSuchElementException if no value is present
     * @since 10
     */
    
    public T orElseThrow() {
        T value = getValue();
		if (value == null) {
            throw new NoSuchElementException("No value present");
        }
        return value;
    }

    /**
     * If a value is present, returns the value, otherwise throws an exception
     * produced by the exception supplying function.
     *
     * @apiNote
     * A method reference to the exception constructor with an empty argument
     * list can be used as the supplier. For example,
     * {@code IllegalStateException::new}
     *
     * @param <X> Type of the exception to be thrown
     * @param exceptionSupplier the supplying function that produces an
     *        exception to be thrown
     * @return the value, if present
     * @throws X if no value is present
     * @throws NullPointerException if no value is present and the exception
     *          supplying function is {@code null}
     */
 
    public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        T value = getValue();
		if (value != null) {
            return value;
        } else {
            throw exceptionSupplier.get();
        }
    }
	
	
	
	@Override
	public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof IOptional)) {
            return false;
        }

        OptProperty<?> other = (OptProperty<?>) obj;
        T value = getValue();
		return Objects.equals(value, other.getValue());
    }
    
	@Override
    public int hashCode() {
        return Objects.hashCode(getValue());
    }
	@Override
    public String toString() {
        T value = getValue();
		return value != null
            ? String.format("AbstractOptional[%s]", value)
            : "AbstractOptional.empty";
    }
}
