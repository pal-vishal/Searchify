package com.example.searchify.database
//
//@Database(
//  entities = [AlbumEntity::class, ArtistEntity::class, SongEntity::class, PlaylistEntity::class],
//  version = 1
//)
//@TypeConverters(Converters::class)
//abstract class ArticleDatabase : RoomDatabase() {
//
//  abstract fun musicDao(): MusicDao
//
//  companion object {
//
//    @Volatile
//    private var instance: ArticleDatabase? = null
//    private val LOCK = Any()
//
//    @OptIn(InternalCoroutinesApi::class)
//    operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
//      instance ?: createDatabase(context).also { instance = it }
//    }
//
//    private fun createDatabase(context: Context) =
//      Room.databaseBuilder(
//        context.applicationContext,
//        ArticleDatabase::class.java,
//        "article_db.db"
//      ).build()
//  }
//}