sealed class Operador(val x: Long) {
    //Funcion abstracta que se encarga de calcular. Retorna un Long
    abstract fun calcular(y: Long): Long

    //Clase sumar heredada de la clase Operaciones
    class sumar(x: Long): Operador(x)
    {
        override fun calcular(y: Long): Long {
            return this.x+y
        }
    }

    class restar(x: Long): Operador(x)
    {
        override fun calcular(y: Long): Long {
            return this.x-y
        }
    }

    class multiplicar(x:Long): Operador(x)
    {
        override fun calcular(y: Long): Long {
            return this.x * y
        }
    }

    class dividir(x:Long): Operador(x)
    {
        override fun calcular(y: Long): Long {
            return this.x / y
        }
    }
}