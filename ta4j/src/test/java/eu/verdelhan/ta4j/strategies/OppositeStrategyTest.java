/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Marc de Verdelhan & respective authors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package eu.verdelhan.ta4j.strategies;

import eu.verdelhan.ta4j.Operation;
import eu.verdelhan.ta4j.OperationType;
import eu.verdelhan.ta4j.Strategy;
import eu.verdelhan.ta4j.mocks.MockStrategy;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class OppositeStrategyTest {

    private Operation[] enterOps;
    private Operation[] exitOps;
    private Strategy strategy;

    @Before
    public void setUp() {
        enterOps = new Operation[] {
                null,
                new Operation(1, OperationType.BUY), 
                null,
                null,
                new Operation(4, OperationType.BUY), 
                null};
        
        exitOps = new Operation[] {
                null,
                null,
                new Operation(2, OperationType.SELL),
                null,
                new Operation(4, OperationType.SELL),
                new Operation(5, OperationType.SELL)
        };
        strategy = new MockStrategy(enterOps, exitOps);
    }
    
    @Test
    public void shouldEnter() {
        OppositeStrategy opposite = new OppositeStrategy(strategy);

        for (int i = 0; i < enterOps.length; i++) {
            assertNotEquals(opposite.shouldEnter(i), strategy.shouldEnter(i));
        }
    }

    @Test
    public void shouldExit() {
        OppositeStrategy opposite = new OppositeStrategy(strategy);

        for (int i = 0; i < exitOps.length; i++) {
            assertNotEquals(opposite.shouldExit(i), strategy.shouldExit(i));
        }
    }
}
