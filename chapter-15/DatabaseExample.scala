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

//val result = ctx.run(query[CountryLanguage])
//println(s"result: $result")

// Filtering
//val result = ctx.run(query[City].filter(_.name == "Singapore"))
//println(s"results: $result")

//val result = ctx.run(query[City].filter(_.id == 3208))
//println(s"results: $result")

//val result = ctx.run(query[City].filter(_.population > 90_000_00))
//println(s"results: $result")

// Lifting
def find(cityId: Int) = ctx.run(query[City].filter(_.id == lift(cityId)).map(_.name))

//val result = find(3208)
//println(s"result: $result")

//val result = find(3209)
//println(s"result: $result")

// Mapping

//val result = ctx.run(query[Country].map(c => (c.name, c.continent)))
//println(s"result: $result")

//val result = ctx.run(query[Country].map(c => (c.name, c.continent, c.population)))
//println(s"result: $result")

//val result = find(3208)
//println(s"result: $result")

// Joins

//val result = ctx.run(
//  query[City]
//    .join(query[Country])
//    .on(_.countryCode == _.code)
//    .filter { case (city, country) => country.continent == "Asia" }
//    .map { case (city, country) => city.name }
//)
//println(s"result: $result")

// Inserts
//ctx.run(query[City].insertValue(City(10000, "test", "TST", "Test Country", 0)))

//val result = ctx.run(query[City].filter(_.population == 0))
//println(s"result: $result")

//val cities = List(
//  City(10001, "testville", "TSV", "Test Country", 0),
//  City(10002, "testopolis", "TSO", "Test Country", 0),
//  City(10003, "testberg", "TSB", "Test Country", 0)
//)

//ctx.run(liftQuery(cities).foreach(e => query[City].insertValue(e)))

//val result = ctx.run(query[City].filter(_.population == 0))
//println(s"result: $result")

// Updates

//ctx.run(
//  query[City]
//    .filter(_.id == 10000)
//    .updateValue(City(10000, "testham", "TST", "Test Country", 0))
//)

//val result = ctx.run(query[City].filter(_.id == 10000))
//println(s"result: $result")

//ctx.run(query[City].filter(_.id == 10000).update(_.name -> "testford"))

//val result = ctx.run(query[City].filter(_.id == 10000))
//println(s"result: $result")

//ctx.run(
//  query[City].filter(_.district == "Test Country").update(_.district -> "Test Borough")
//)

//val result = ctx.run(query[City].filter(_.population == 0))
//println(s"result: $result")

// Transactions

//ctx.transaction {
//  ctx.run(
//    query[City].filter(_.district == "Test Borough").update(_.district -> "Test Country")
//  )
//  throw new Exception()
//}

val result = ctx.run(query[City].filter(_.population == 0))
println(s"result: $result")
