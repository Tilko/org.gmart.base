package org.gmart.base.data.source.temporalState;

public interface Temporal {
	void evolve();
	boolean isStable();
	
	public interface Default extends Temporal {
		default void evolve() {
			//nothing
		}
		default boolean isStable() {
			return true;
		}
	}
	public interface Decorator<T extends Temporal> extends Temporal, interfaces.nodes.Decorator<T>{

		//Temporal.Decorator.DefaultStable<T>
		public interface DefaultStable<T extends Temporal> extends Temporal.Decorator<T> {
			default void evolve() {
				getDecorated().evolve();
			}
			default boolean isStable() {
				return getDecorated().isStable();
			}
		}
		//Temporal.Decorator.DefaultEvolving<T>
		public interface DefaultEvolving<T extends Temporal> extends Temporal.Decorator<T> {
			default void evolve() {
				getDecorated().evolve();
				evolve_internal();
			}
			void evolve_internal();
			default boolean isStable() {
				return getDecorated().isStable() && isStable_internal();
			}
			boolean isStable_internal();
		}
	}
	
	
}
