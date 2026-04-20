// Array de tareas
let tareas = [];

// Cargar tareas al iniciar
window.onload = function () {
    cargarTareas();
};

// Añadir tarea
function agregarTarea() {
    const input = document.getElementById("tareaInput");
    const texto = input.value;

    if (texto === "") return;

    tareas.push(texto); // guardamos en array
    guardarTareas();    // guardamos en localStorage
    mostrarTareas();    // actualizamos la vista

    input.value = "";
}

// Mostrar tareas en pantalla
function mostrarTareas() {
    const lista = document.getElementById("listaTareas");
    lista.innerHTML = ""; // limpiar lista

    tareas.forEach((tarea, index) => {
        const li = document.createElement("li");

        li.textContent = tarea;

        const botonEliminar = document.createElement("button");
        botonEliminar.textContent = "Eliminar";

        botonEliminar.onclick = function () {
            eliminarTarea(index);
        };

        li.appendChild(botonEliminar);
        lista.appendChild(li);
    });
}

// Eliminar tarea
function eliminarTarea(index) {
    tareas.splice(index, 1); // eliminar del array
    guardarTareas();
    mostrarTareas();
}

// Guardar en localStorage
function guardarTareas() {
    localStorage.setItem("tareas", JSON.stringify(tareas));
}

// Cargar desde localStorage
function cargarTareas() {
    const datos = localStorage.getItem("tareas");

    if (datos) {
        tareas = JSON.parse(datos);
    }

    mostrarTareas();
}

document.addEventListener("DOMContentLoaded", function () {

let fecha = new Date();

function renderCalendario() {
    const calendario = document.getElementById("calendario");
    const titulo = document.getElementById("mes-titulo");

    // limpiar días anteriores (pero NO los nombres de días)
    const diasAntiguos = calendario.querySelectorAll(".dia");
    diasAntiguos.forEach(d => d.remove());

    const año = fecha.getFullYear();
    const mes = fecha.getMonth();

    const nombresMeses = [
        "Enero","Febrero","Marzo","Abril","Mayo","Junio",
        "Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"
    ];

    titulo.textContent = `${nombresMeses[mes]} ${año}`;

    const primerDia = new Date(año, mes, 1).getDay();
    const diasMes = new Date(año, mes + 1, 0).getDate();

    let inicio = primerDia === 0 ? 6 : primerDia - 1;

    // espacios vacíos
    for (let i = 0; i < inicio; i++) {
        let vacio = document.createElement("div");
        vacio.classList.add("dia", "vacio");
        calendario.appendChild(vacio);
    }

    // días del mes
    for (let dia = 1; dia <= diasMes; dia++) {
        let div = document.createElement("div");
        div.classList.add("dia");
        div.textContent = dia;
        calendario.appendChild(div);
    }
}

window.mesAnterior = function () {
    fecha.setMonth(fecha.getMonth() - 1);
    renderCalendario();
}

window.mesSiguiente = function () {
    fecha.setMonth(fecha.getMonth() + 1);
    renderCalendario();
}

renderCalendario();

});