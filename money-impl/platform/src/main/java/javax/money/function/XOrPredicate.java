/*
 * Copyright (c) 2012, 2013, Credit Suisse (Anatole Tresch), Werner Keil.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package javax.money.function;

import java.util.ArrayList;
import java.util.List;

import javax.money.Predicate;


/**
 * This predicate implements the logic {@code or and xor} operations, where
 * {@code OrPredicate(p1,p2) == p1 || p2} or
 * {@code OrPredicate(p1,p2) == (p1 || p2) && !(p1 && p2)}.
 * 
 * @author Anatole Tresch
 */
final class XOrPredicate<T> implements Predicate<T> {
	/** The child predicates. */
	private List<Predicate<? super T>> predicates = new ArrayList<Predicate<? super T>>();

	/**
	 * Creates an XOR predicate.
	 * 
	 * @param predicates
	 *            The child predicates.
	 */
	@SafeVarargs
	XOrPredicate(Iterable<? extends Predicate<? super T>>... predicates) {
		if (predicates != null) {
			for (Iterable<? extends Predicate<? super T>> iterable : predicates) {
				for (Predicate<? super T> predicate : iterable) {
					this.predicates.add(predicate);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.money.MonetaryFunction#apply(java.lang.Object)
	 */
	@Override
	public Boolean apply(T value) {
		boolean state = false;
		for (Predicate<? super T> predicate : predicates) {
			if (predicate.apply(value)) {
				if (!state) {
					state = true;
				}
				else {
					return Boolean.FALSE;
				}
			}
		}
		return state;
	}

}