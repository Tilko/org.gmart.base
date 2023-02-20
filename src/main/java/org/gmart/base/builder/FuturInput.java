package org.gmart.base.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * To deal with circular constructor calls:
 * 
static {
	FuturInputManager man = new FuturInputManager();
	FuturInputImpl<B> futur = man.inst();
	//"new A" constructor calls "whenPresent" to set the consumer that will be called by "set(b)" after the creation of "B b". 
	A a = new A(futur); 
	B b = new B(a);
	futur.set(b);
	
	... other connections ...
	
	man.checkConnections();
}
*/
public interface FuturInput<T> {
	void whenPresent(Consumer<T> inputConsumer);
	
	public static <T> FuturInputImpl<T> inst(){
		return new FuturInputImpl<>();
	}
	
	public static class FuturInputImpl<T> implements FuturInput<T> {
		Consumer<T> inputConsumer;
		public FuturInputImpl() {
			super();
		}
		boolean isConnected;
		public void set(T input) {
			isConnected = true;				
			inputConsumer.accept(input);
		}
		@Override
		public void whenPresent(Consumer<T> inputConsumer) {
			this.inputConsumer = inputConsumer;
		}
	}
	
	public static class FuturInputManager {
		List<FuturInputImpl<?>> futurs = new ArrayList<>();
		public <T> FuturInputImpl<T> inst(){
			FuturInputImpl<T> inst = inst();
			futurs.add(inst);
			return inst;
		}
		public void checkConnections() {
			assert futurs.stream().allMatch(f->f.isConnected) : "check the connections with FuturInput<?>";
		}
	}
}