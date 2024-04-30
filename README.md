
Se requiere una aplicación para simular el comportamiento de los clientes o usuarios dentro de un establecimiento (supermercado, banco, empresa de comunicaciones móviles, entidad pública, etc.).  El establecimiento cuenta con varios puntos de atención (cajas o ventanillas).  Para cada usuario que ingresa al establecimiento se debe generar de forma aleatoria alguna información que esté relacionada con la compra o servicio que va a utilizar (cantidad de productos, monto de la transacción, complejidad de la solicitud). 

El ingreso al establecimiento será controlado mediante un hilo, que alimentará estructuras de tipo cola que estarán asociadas a cada punto de atención.  Opcionalmente se podrá manejar el sistema de cola única con varios puntos de atención.
Cada punto de atención será controlado mediante un hilo que solicitará el siguiente elemento de la cola a ser atendido y controlará el tiempo que dura la atención del mismo (de acuerdo a la información usada para su creación), antes de quedar nuevamente disponible.
El programa debe permitir:

Establecer el tiempo total de la simulación
Establecer el número de puntos de atención que serán activados
Presentar información estadística al finalizar la simulación
Tiempo de simulación
Número de puntos de atención habilitados
Cantidad de usuarios que ingresaron al establecimiento
Cantidad de usuarios atendidos
Cantidad de usuarios que quedaron sin atender en la o las colas
