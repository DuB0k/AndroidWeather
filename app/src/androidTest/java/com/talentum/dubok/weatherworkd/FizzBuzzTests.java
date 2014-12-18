package com.talentum.dubok.weatherworkd;

import android.test.AndroidTestCase;
import com.talentum.dubok.weatherworkd.model.FizzBuzz;

/*
* TDD TestDrivenDevelopment
* RedGreenRefactoring
* Hacer un test que falle, ejecutar, ver errores, codificar para que pase el test haciendo refactor
* y ejecutar de nuevo Refactor es hacer cambios sin tocar la interfaz externa
* Hacer test es un 20% extra del tiempo de desarrollo del proyecto
***/

public class FizzBuzzTests  extends AndroidTestCase{

    public void testFizzBuzzReturns1With1(){
        String returnValue = FizzBuzz.fizzBuzz(1);

        assertEquals("1",returnValue);
    }

    public void testFizzBuzzReturnsBuzzWith3(){
        String returnValue = FizzBuzz.fizzBuzz(3);

        assertEquals("Fizz",returnValue);
    }

    public void testFizzBuzzReturnsBuzzWith5(){
        String returnValue = FizzBuzz.fizzBuzz(5);

        assertEquals("Buzz",returnValue);
    }

    public void testFizzBuzzReturnsFizzBuzzWith15(){
        String returnValue = FizzBuzz.fizzBuzz(15);

        assertEquals("FizzBuzz",returnValue);
    }

}
