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
package org.gmart.util.functionalProg.interfaces;
//package util.functionalProg.interfaces;
//
//import java.util.Map;
//import java.util.Optional;
//import java.util.function.Function;
//
//import util.collections.DicoWithNonNullDefault;
//import util.collections.Dico_RO_MapImpl;
//
//public interface Dico<K,V> extends Function<K,V> {
//	public Optional<V> get(K k);
//	
//	@Override
//	public default V apply(K k) {
//		return this.get(k).orElse(null);
//	}
//	
//	public static <K,V> Dico<K,V> of(Map<K,V> map) {
//		return new Dico_RO_MapImpl<>(map);
//	}
//	public static <K,V> DicoWithNonNullDefault<K,V> of(Function<K,V> getter, V nonNullDefaultValue) {
//		return new DicoWithNonNullDefault<>(getter, nonNullDefaultValue);
//	}
//	public static <K,V> DicoWithNonNullDefault<K,V> of(Map<K,V> defsMap, V nonNullDefaultValue) {
//		return new DicoWithNonNullDefault<>(defsMap, nonNullDefaultValue);
//	}
////	public static <K,V> Dico_RO<K,V> make(HashMap<K,V> defsMap) {
////		return new Dico_RO_MapImpl<>(defsMap);
////	}
//	
//	static final Dico<?,?> EMPTY = new Dico<>(){
//		@Override
//		public Optional<Object> get(Object k) {
//			return Optional.empty();
//		}
//	};
//	public static <K,V> Dico<K,V> emptyDico(){
//		@SuppressWarnings("unchecked")
//		Dico<K,V> r = (Dico<K,V>)EMPTY;
//		return r;
//	}
//}
