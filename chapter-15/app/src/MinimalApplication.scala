package app
import scalatags.Text.all.*
import io.getquill.*
import com.zaxxer.hikari.{HikariConfig, HikariDataSource}

object MinimalApplication extends cask.MainRoutes:

  val bootstrap = "https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.css"

  var openConnections = Set.empty[cask.WsChannelActor]

  case class Message(name: String, msg: String)

  val pgDataSource = new org.postgresql.ds.PGSimpleDataSource()
  pgDataSource.setUser("postgres")
  pgDataSource.setPassword("test")
  pgDataSource.setDatabaseName("test")

  val hikariConfig = new HikariConfig()
  hikariConfig.setDataSource(pgDataSource)

  val ctx = new PostgresJdbcContext(LowerCase, new HikariDataSource(hikariConfig))

  import ctx.*

  ctx.executeAction("CREATE TABLE IF NOT EXISTS message (name text, msg text);")

  def messages = ctx.run(query[Message].map(m => (m.name, m.msg)))

  @cask.staticResources("/static")
  def staticResourceRoutes() = "static"

  @cask.get("/")
  def hello() =
    doctype("html")(
      html(
        head(
          link(rel := "stylesheet", href := bootstrap),
          script(src := "/static/app.js")
        ),
        body(
          div( cls := "container")(
            h1("Scala Chat!"),
            div(id := "messageList")(messageList()),
            div(id := "errorDiv", color.red),
            form(onsubmit := "submitForm(); return false;")(
              input(`type` := "text", id := "nameInput", placeholder := "User name"),
              input(`type` := "text", id := "msgInput", placeholder := "Write a message!"),
              input(`type` := "submit")
            )
          )
        )
      )
    )

  @cask.postJson("/")
  def postChatMsg(name: String, msg: String) =
    if name == "" then ujson.Obj("success" -> false, "err" -> "Name cannot be empty")
    else if msg == "" then ujson.Obj("success" -> false, "err" -> "Message cannot be empty")
    else
      ctx.run(query[Message].insertValue(lift(Message(name, msg))))
      for conn <- openConnections do conn.send(cask.Ws.Text(messageList().render))
      ujson.Obj("success" -> true, "err" -> "")

  @cask.websocket("/subscribe")
  def subscribe() =
    cask.WsHandler { connection =>
      connection.send(cask.Ws.Text(messageList().render))
      openConnections += connection
      cask.WsActor {
        case cask.Ws.Close(_, _) => openConnections -= connection
      }
    }

  def messageList() = frag(for (name, msg) <- messages yield p(b(name), " ", msg))

  initialize()
end MinimalApplication
