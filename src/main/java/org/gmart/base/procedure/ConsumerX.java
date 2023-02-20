package org.gmart.base.procedure;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * "X" as "extendable"
 */
public interface ConsumerX<I> extends Consumer<I>{
	default <I2> ConsumerX<I2> to(Function<? super I2, I> adaptor){
		return  i -> accept(adaptor.apply(i));
	}
	
	default Runnable suppliedBy(Supplier<I> supplier) {
		return ()->accept(supplier.get());
	}
}
