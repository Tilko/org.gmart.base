/*******************************************************************************
 * Copyright 2020 Gr�goire Martinetti
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
package org.gmart.base.data.storage.property.canvasStyle;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;


/**
 * example of use: as a static field declaration:
 * private static CanvasStyleObjAccess<Graphics2D, Color> colorSetter = new CanvasStyleObjAccess<>(Graphics2D::getColor, Graphics2D::setColor);
 * @author marti
 */
public class CanvasStyleObjAccess<Y,T> {
	Function<Y,T> argGetter;
	BiConsumer<Y, T> argSetter;
	public CanvasStyleObjAccess(Function<Y, T> argGetter, BiConsumer<Y, T> argSetter) {
		super();
		this.argGetter = argGetter;
		this.argSetter = argSetter;
	}
	public void withArg(Y canvas, T arg, Consumer<Y> consumer) {
		if (arg != null) {
			T oldArg = argGetter.apply(canvas);
			argSetter.accept(canvas, arg);
			consumer.accept(canvas);
			argSetter.accept(canvas, oldArg);
		} else {
			consumer.accept(canvas);
		}
	}
	public void withArg(Y canvas, T arg, Runnable consumer) {
		if (arg != null) {
			T oldArg = argGetter.apply(canvas);
			argSetter.accept(canvas, arg);
			consumer.run();
			argSetter.accept(canvas, oldArg);
		} else {
			consumer.run();
		}
	}
//	public static class jjj<Y,T,U extends Consumer<Y>> extends CanvasStyleObjAccess<Y,T> {
//		public jjj(Function<Y, T> argGetter, BiConsumer<Y, T> argSetter, ) {
//			super(argGetter, argSetter);
//		}
//
//		public U withArg(Y canvas, T arg, Consumer<Y> consumer) {
//			return y -> {
//				
//			}
//			if (arg != null) {
//				T oldArg = argGetter.apply(canvas);
//				argSetter.accept(canvas, arg);
//				consumer.accept(canvas);
//				argSetter.accept(canvas, oldArg);
//			} else {
//				consumer.accept(canvas);
//			}
//		}
//	}
	
}