# RabbitMQReceiver
Este proyecto es el que recibe los mensajes de una cola de mensajes en RabbitMQ

Este proyecto ha sido desarrollado para ejecutar con jre 8, compilado con el jdk 8, el local probado con jdk1.8.0_65
Gestionando el ciclo de compilacion, empaquetado y test (No tiene JUnit implementado pero lo soportaria) con maven 4.0
Se encuentra implementado con Spring boot y spring mvc para la exposicion de servicios rest que consultan los mensajes leidos como string en la cola de mensajes
adicionalmente aprovecha el Spring Scheduled, para leer la cola de mensajes cada 7 segundos (Se observa en la clase ReadQueue, con la anotacion @Scheduled(fixedRate = 7000))
Parametrizando tanto el puerto de publicacion de los servicios rest, la ip del servidor de rabbitMQ y el mombre de la cola de mensajes mediante el archivo application.properties
El controlador rest que muestra los mensajes que se han sacado de la cola de mensajes es MyRestController, el metodo recibedMessages, para este ejemplo los mensajes
se dejan en el bean MyBeanSingleton, que como premisa en Spring es singleton por instancia del contenedor por lo que es accedido por el consumidor de la cola de mensajes
y por el servicio rest que los muestra en pantalla

Una vez se levantan los servicios mediante la clase Main IniciarViaSpringBoot, se puede dejar el mensaje en la cola de mensajes mediante el consumo de la
url http://{ip_publicacion}:{puerto_publicacion}/recibedMessages, por ejemplo http://localhost:9001/recibedMessages

NOTA, este proyecto presupone que en la ip que se parametrice en el archivo application.properties como ip.cola.mensajes , se encuentre ejecutando un servicio de
RabbitMQ, por otra parte tanto productos como consumidor deben apuntar a esta misma ip o nombre de maquina y a la misma cola de mensajes parametrizada en el
campo nombre.cola.mensajes del mismo archivo.

