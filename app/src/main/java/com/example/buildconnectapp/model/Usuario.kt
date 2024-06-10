package com.example.buildconnectapp.model

data class Usuario(
    val id: String = "",
    val nombre: String = "",
    val correo_electronico: String= "",
    val contraseña: String = "",
    val telefono: String = "",
    val numero_vivienda: String = ""
)
{
    constructor() : this("", "", "", "", "", "")
}
