package spike

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.junit.Test
import org.slf4j.LoggerFactory
import javax.sql.DataSource

class JdbcTest {

    companion object {
        val logger = LoggerFactory.getLogger(JdbcTest::class.java.simpleName)
    }

    var ds: DataSource = initDS()

    fun initDS(): DataSource {
        val config = HikariConfig()
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/dev")
        config.setUsername("dev")
        config.setPassword("test")
        config.addDataSourceProperty("cachePrepStmts", "true")
        config.addDataSourceProperty("prepStmtCacheSize", "250")
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048")
        return HikariDataSource(config)
    }

    @Test
    fun testSelect() {
        for (i in 1..10) {
            val conn = ds.connection
            val stmt = conn.createStatement()
            val rs = stmt.executeQuery("select count(*) from users")
            while (rs.next() == true) {
                logger.info("users: " + rs.getInt(1))
            }
            rs.close()
            stmt.close()
            conn.close()
        }
    }

}