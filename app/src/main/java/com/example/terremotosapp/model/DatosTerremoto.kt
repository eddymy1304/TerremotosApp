package com.example.terremotosapp.model

class DatosTerremoto {
    fun cargarTerremotos(): MutableList<Terremoto> {
        return mutableListOf<Terremoto>(
            Terremoto("1", "Piura", 2.5, 343344556L, -3.43333345, 80.5555555),
            Terremoto("2", "Huarmey", 3.5, 343344556L, -3.43333345, 80.5555555),
            Terremoto("3", "Iquitos", 4.5, 343344556L, -3.43333345, 80.5555555),
            Terremoto("4", "Chimbote", 6.5, 343344556L, -3.43333345, 80.5555555),
            Terremoto("5", "Ica", 2.6, 343344556L, -3.43333345, 80.5555555),
            Terremoto("6", "Tarma", 2.7, 343344556L, -3.43333345, 80.5555555),
            Terremoto("7", "Iquitos", 2.9, 343344556L, -3.43333345, 80.5555555),
            Terremoto("8", "Tarapoto", 2.1, 343344556L, -3.43333345, 80.5555555),
            Terremoto("9", "Puno", 2.2, 343344556L, -3.43333345, 80.5555555),
            Terremoto("9", "Puno", 2.2, 343344556L, -3.43333345, 80.5555555),
            Terremoto("10", "Paita", 6.5, 343344556L, -3.43333345, 80.5555555),
            Terremoto("11", "Sullana", 3.5, 343344556L, -3.43333345, 80.5555555),
            Terremoto("12", "Matarani", 4.5, 343344556L, -3.43333345, 80.5555555),
            Terremoto("13", "Trujillo", 1.5, 343344556L, -3.43333345, 80.5555555),
            Terremoto("14", "Tumbes", 3.5, 343344556L, -3.43333345, 80.5555555),
            Terremoto("15", "Lima", 0.5, 343344556L, -3.43333345, 80.5555555),
        )
    }
}