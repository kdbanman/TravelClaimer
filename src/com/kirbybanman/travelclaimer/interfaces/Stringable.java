package com.kirbybanman.travelclaimer.interfaces;

/*
 *    Copyright 2015 Kirby Banman
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/**
 * Interface to tease strings out of java enums.  Much fiddling was had with
 * this and SpinnerEnumAdapter.  I'm no longer sure that the type parameter
 * is necessary, but getting generics to play nice with enums is always a
 * giggle.
 * 
 * @author kdbanman
 *
 * @param <E>
 */
public interface Stringable<E> {
	public String toString();
}
