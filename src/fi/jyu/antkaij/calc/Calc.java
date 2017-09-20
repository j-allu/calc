// A sample simple calculator
// Copyright © 2017 Antti-Juhani Kaijanaho

//     Licensed under the Apache License, Version 2.0 (the "License");
//     you may not use this file except in compliance with the License.
//     You may obtain a copy of the License at

//         http://www.apache.org/licenses/LICENSE-2.0

//     Unless required by applicable law or agreed to in writing,
//     software distributed under the License is distributed on an "AS
//     IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
//     express or implied.  See the License for the specific language
//     governing permissions and limitations under the License.

package fi.jyu.antkaij.calc;

import static java.lang.Character.digit;
import static java.lang.Character.isDigit;

public class Calc {

    public static int compute(String s) {
        Calc c = new Calc(s);
        return c.compute();
    }
    
    private int compute() {
        int a = computeTerm();
        while (!endOfInput()) {
            switch (peekChar()) {
            case '+':
                getChar();
                a = a + computeTerm();
                break;
            case '-':
                getChar();
                a = a - computeTerm();
                break;
            default:
                unexpected("'+', '-', or the end of input");
            }
        }
        return a;
    }

    private int computeTerm() {
        int a = computeFactor();
        while (!endOfInput() && (peekChar() == '*' || peekChar() == '/')) {
            char op = getChar();
            int b = computeFactor();
            switch (op) {
            case '*':
                a = a * b;
                break;
            case '/':
                a = a - b;
                break;
            default:
                unexpected("'*', '/', or the end of input");
            }
        }
        return a;
    } 

    private int computeFactor() {
        if (peekChar() == '(') {
            getChar();
            int rv = compute();
            if (getChar() != ')') throw new ParseException("missing ')'");
            return rv;
        }
        return getInt();
    }


    private final char[] input;
    private int inx = 0;

    private Calc(String s) {
        input = s.toCharArray();
    }
    
    private boolean endOfInput() {
        return inx >= input.length;
    }

    private char peekChar() {
        return input[inx];
    }

    private char getChar() {
        return input[inx++];
    }

    private int getInt() {
        int rv = 0;
        do {
            rv = 10 * rv + digit(input[inx], 10);
            inx++;
        } while (inx < input.length &&
                 isDigit(input[inx]));
        return rv;
    }

    private void unexpected(String what) {
        if (endOfInput()) {
            throw new ParseException("premature end of input, expected " +
                                     what);
        } else {
            throw new ParseException("got " + peekChar() + ", expected " +
                                     what);
        }
    }
}
