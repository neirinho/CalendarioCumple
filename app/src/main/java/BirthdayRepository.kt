import android.content.ContentValues
import android.content.Context

class BirthdayRepository(context: Context) {
    private val dbHelper = BirthdayDatabaseHelper(context)

    // Función para agregar un cumpleaños
    fun addBirthday(name: String, date: String) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(BirthdayDatabaseHelper.COLUMN_NAME, name)
            put(BirthdayDatabaseHelper.COLUMN_DATE, date)
        }
        db.insert(BirthdayDatabaseHelper.TABLE_NAME, null, values)
        db.close()
    }

    // Función para obtener todos los cumpleaños
    fun getAllBirthdays(): List<Pair<String, String>> {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            BirthdayDatabaseHelper.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )

        val birthdays = mutableListOf<Pair<String, String>>()
        with(cursor) {
            while (moveToNext()) {
                val name = getString(getColumnIndexOrThrow(BirthdayDatabaseHelper.COLUMN_NAME))
                val date = getString(getColumnIndexOrThrow(BirthdayDatabaseHelper.COLUMN_DATE))
                birthdays.add(name to date)
            }
            close()
        }
        db.close()
        return birthdays
    }
}
