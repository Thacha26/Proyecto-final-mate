## Proyecto-final-mate
# Problema
Crear una programa que realice cambios de coordenadas entre cartesianas, polares, esféricas y cilíndricas.

# Tree

# ¿Qué debe de hacer cada clase?
**App.java**
Es la clase principal, aquí simplemente se mandarán a llamar las conversiones, se hará uso de javaFX por lo que se inicializarán dichos paquetes en esta clase, no se establecen varoles, si no que se interactuará con ellos en una ventana emergente donde el usuario los podrá ingresar sin la necesidad de entrar al código.

**ui/**
Es aquí donde entra lo de javaFX, pues dentro tendrá los paquetes necesarios y el layout de la ventana.

**coordenadas/**
Dentro contiene todas las clases necesarias para poder hacer el cambio de coordenadas.

**Coordenada.java**
En esta clase se creará una interfaz, la cual nos ayudará a poder mezclar coordenadas polares, cilíndricas etc, en una misma lista, pues podremos tratar a las coordenadas como un mismo tipo. Los métodos que la implementen no necesitan saber que tipo de coordenada se mete.

**Cartesianas, Polares, Esféricas, Cilíndricas**
Se "modelarán" los puntos en es espacio.

**Convertidor.java**
Aquí se harán las operaciones.

**tests/**
Para verificar que todo esté correcto y no hayan problemas en las conversiones.