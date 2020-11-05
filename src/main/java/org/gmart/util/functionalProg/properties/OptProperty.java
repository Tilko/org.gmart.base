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


import java.util.function.Consumer;

/**
 * A container object which may or may not contain a non-{@code null} value.
 * If a value is present, {@code isPresent()} returns {@code true}. If no
 * value is present, the object is considered <i>empty</i> and
 * {@code isPresent()} returns {@code false}.
 *
 * <p>Additional methods that depend on the presence or absence of a contained
 * value are provided, such as {@link #orElse(Object) orElse()}
 * (returns a default value if no value is present) and
 * {@link #ifPresent(Consumer) ifPresent()} (performs an
 * action if a value is present).
 *
 * <p>This is a <a href="../lang/doc-files/ValueBased.html">value-based</a>
 * class; use of identity-sensitive operations (including reference equality
 * ({@code ==}), identity hash code, or synchronization) on instances of
 * {@code Optional} may have unpredictable results and should be avoided.
 *
 * @apiNote
 * {@code Optional} is primarily intended for use as a method return type where
 * there is a clear need to represent "no result," and where using {@code null}
 * is likely to cause errors. A variable whose type is {@code Optional} should
 * never itself be {@code null}; it should always point to an {@code Optional}
 * instance.
 *
 * @param <T> the type of value
 * @since 1.8
 */
public class OptProperty<T> extends AbstractOptional<T> {
    
    
    /**
     * If non-null, the value; if null, indicates no value is present
     */
    protected T value;
    
    public void set(T value) {
    	this.value = value;
    }
    
    protected T getValue() {
    	return value;
    }
    
    public static <T> OptProperty<T> newProperty(){
    	return new OptProperty<T>();
    }
   
    OptProperty() {
        this.value = null;
    }
    
    

//    /**
//     * Constructs an instance with the described value.
//     *
//     * @param value the non-{@code null} value to describe
//     * @throws NullPointerException if value is {@code null}
//     */
//    private OptProperty(T value) {
//        this.value = Objects.requireNonNull(value);
//    }
}
