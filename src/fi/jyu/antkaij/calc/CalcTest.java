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

import org.junit.*;
import static org.junit.Assert.*;

public class CalcTest {

    @Test
    public void testBasic() {
        assertEquals(2, Calc.compute("1+1"));
    }

    @Test(expected=ParseException.class)
    public void testError() {
        Calc.compute("1!");
    }

    @Test(expected=ParseException.class)
    public void testError2() {
        Calc.compute("1)");
    }

    @Test
    public void testParens() {
        assertEquals(20, Calc.compute("(2+3)*4"));
    }


}
