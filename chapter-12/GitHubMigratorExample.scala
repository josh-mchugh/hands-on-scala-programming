println("Github Migrator")

// Lazy and do not feel like setting up my own GitHub to test against so I will use httpbin.org
val testIssuePost = requests.post(
  "https://httpbin.org/anything/repos/lihaoyi/test/issues",
  data = ujson.Obj("title" -> "Hello"),
  headers = Map("Authorization" -> "token JJR")
)
//println(s"testIssuePost: $testIssuePost")

val testCommentPost = requests.post(
  "https://httpbin.org/anything/repos/lihaoyi/test/issues/1/comments",
  data = ujson.Obj("body" -> "world"),
  headers = Map("Authorization" -> "token JJR")
)
//println(s"testCommentPost: $testCommentPost")

val upickleIssues = requests.get(
  "https://api.github.com/repos/lihaoyi/upickle/issues",
  params = Map("state" -> "all")
)
//println(s"upickleIssues: $upickleIssues")
val parsedUpickleIssues = ujson.read(upickleIssues)
//println(parsedUpickleIssues.render(indent = 4))
//println(s"upickleIssues.arr.length: ${parsedUpickleIssues.arr.length}")

def fetchPaginated(url: String, params: (String, String)*) =
  var done = false
  var page = 1
  val responses = collection.mutable.Buffer.empty[ujson.Value]
  while !done && page < 5 do
    println(s"page $page...")
    val response = requests.get(
      url,
      params = Map("page" -> page.toString) ++ params
    )
    val parsed = ujson.read(response).arr
    if parsed.length == 0 then done = true
    else responses.appendAll(parsed)
    page += 1

  responses

val issues = fetchPaginated(
  "https://api.github.com/repos/lihaoyi/upickle/issues",
  "state" -> "all"
)
val nonPullRequests = issues.filter(!_.obj.contains("pull_request"))
//println(s"nonPullRequests.length: ${nonPullRequests.length}")

val issueData =
  for issue <- nonPullRequests yield
    (issue("number").num.toInt, issue("title").str, issue("body").str, issue("user")("login").str)
//println(s"issueData: $issueData")

val comments = fetchPaginated("https://api.github.com/repos/lihaoyi/upickle/issues/comments")
val commentData =
  for comment <- comments yield
    (
      comment("issue_url").str
        match
          case s"https://api.github.com/repos/lihaoyi/$repo/issues/$id" => id.toInt
          case _ => 1337
      ,
      comment("user")("login").str,
      comment("body").str
    )
println(s"commentData: $commentData")

val issueNums =
  for (number, title, body, user) <- issueData.sortBy(_(0))
  yield
    println(s"Creating issue $number")
    val resp = requests.post(
      s"https://httpbin.org/anything/repos/lihaoyi/test/issues",
      data = ujson.Obj(
        "title" -> title,
        "body" -> s"$body\nID: $number\nOriginal Author: $user"
      )
    )
    val newIssueNumber = number * 13
    (number, newIssueNumber)

val issueNumMap = issueNums.toMap

for (issueId, user, body) <- commentData; newIssueId <- issueNumMap.get(issueId) do
  println(s"Commenting on issue old_id=$issueId new_id=$newIssueId")
  val resp = requests.post(
    s"https://httpbin.org/anything/repos/lihaoyi/test/issues/$newIssueId/comments",
    data = ujson.Obj("body" -> s"$body\nOriginal Author: $user"),
  )







