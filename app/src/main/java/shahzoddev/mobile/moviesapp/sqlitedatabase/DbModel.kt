package shahzoddev.mobile.moviesapp.sqlitedatabase

data class DbModel (
    val id: Int = 0,
    val onBoarding: Int = 0,
    val userName: String,
    val email : String,
    val password : String,
    val phone : Int,
    val avatar : Int,
    val watchList : String,
    val history : String,
)