package com.talentum.dubok.weatherworkd;

import android.test.AndroidTestCase;
import com.talentum.dubok.weatherworkd.model.FizzBuzz;

/*
* TDD TestDrivenDevelopment
* RedGreenRefactoring
* Hacer un test que falle, ejecutar, ver errores, codificar para que pase el test y ejecutar
* */

public class FizzBuzzTests  extends AndroidTestCase{

    public void testFizzBuzzReturns1With1(){
        String returnValue = FizzBuzz.fizzBuzz(1);

        assertEquals("1",returnValue);
    }

    public void testFizzBuzzWith3(){
        String returnValue = FizzBuzz.fizzBuzz(3);

        assertEquals("Fizz",returnValue);
    }

}
