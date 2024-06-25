import $ivy.`io.getquill::quill-jdbc:4.8.4`
import $ivy.`org.postgresql:postgresql:42.2.8`
import io.getquill.*
import com.zaxxer.hikari.{HikariConfig, HikariDataSource}

println("Database Example")

val pgDataSource = new org.postgresql.ds.PGSimpleDataSource()
pgDataSource.setUser("postgres")
pgDataSource.setPassword("test")
pgDataSource.setDatabaseName("test")

val hikariConfig = new HikariConfig()
hikariConfig.setDataSource(pgDataSource)

val ctx = new PostgresJdbcContext(LowerCase, new HikariDataSource(hikariConfig))

import ctx._

case class City(
  id: Int,
  name: String,
  countryCode: String,
  district: String,
  population: Int
)

case class Country(
  code: String,
  name: String,
  continent: String,
  region: String,
  surfaceArea: Double,
  indepYear: Option[Int],
  population: Int,
  lifeExpectancy: Option[Double],
  gnp: Option[math.BigDecimal],
  gnpold: Option[math.BigDecimal],
  localName: String,
  governmentForm: String,
  capital: Option[Int],
  code2: String
)

case class CountryLanguage(
  countryCode: String,
  language: String,
  isOfficial: Boolean,
  percentage: Double
)

//val result = ctx.run(query[City])
//println(s"result: $result")

//val result = ctx.run(query[Country])
//println(s"result: $result")

val result = ctx.run(query[CountryLanguage])
println(s"result: $result")
