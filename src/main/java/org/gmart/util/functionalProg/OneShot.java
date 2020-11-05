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
package org.gmart.util.functionalProg;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public abstract class OneShot {
	boolean notDone = true;
	public OneShot() {
		super();
	}

	public static RunnableOS oneShot(Runnable action) {
		return new RunnableOS(action);
	}
	public static <T> ConsumerOneShot<T> oneShot(Consumer<T> action) {
		return new ConsumerOneShot<>(action);
	}
	public static <T0,T1> BiConsumerOneShot<T0,T1> oneShot(BiConsumer<T0,T1> action) {
		return new BiConsumerOneShot<>(action);
	}
	public static class RunnableOS extends OneShot implements Runnable {
		Runnable action;
		public RunnableOS(Runnable action) {
			super();
			this.action = action;
		}
		@Override
		public void run() {
			if(notDone) {
				notDone = false;
				action.run();
			}
		}
	}
	public static class ConsumerOneShot<T> extends OneShot implements Consumer<T> {
		Consumer<T> action;
		public ConsumerOneShot(Consumer<T> action) {
			super();
			this.action = action;
		}
		@Override
		public  void accept(T arg) {
			if(notDone) {
				notDone = false;
				action.accept(arg);
			}
		}
	}
	public static class BiConsumerOneShot<T0,T1> extends OneShot implements BiConsumer<T0,T1> {
		BiConsumer<T0,T1> action;
		public BiConsumerOneShot(BiConsumer<T0,T1> action) {
			super();
			this.action = action;
		}
		@Override
		public  void accept(T0 arg0, T1 arg1) {
			if(notDone) {
				notDone = false;
				action.accept(arg0, arg1);
			}
		}
	}
}
