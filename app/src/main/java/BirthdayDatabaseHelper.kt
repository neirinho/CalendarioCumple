import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BirthdayDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "birthday.db" // Nombre del archivo de la base de datos
        private const val DATABASE_VERSION = 1          // Versión de la base de datos
        const val TABLE_NAME = "birthdays"              // Nombre de la tabla
        const val COLUMN_ID = "id"                      // Columna: ID (clave primaria)
        const val COLUMN_NAME = "name"                  // Columna: Nombre
        const val COLUMN_DATE = "birthday_date"         // Columna: Fecha de cumpleaños
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // Comando SQL para crear la tabla
        val createTable = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT NOT NULL,
                $COLUMN_DATE TEXT NOT NULL
            )
        """.trimIndent()
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Si cambias la versión de la base de datos, puedes actualizar la tabla aquí
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
}
