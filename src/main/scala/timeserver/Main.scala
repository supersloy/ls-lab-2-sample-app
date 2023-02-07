//> using scala "3.2.2"
//> using lib "org.http4s::http4s-ember-server:1.0.0-M39"
//> using lib "io.chrisdavenport::cats-effect-time:0.2.0"
package timeserver

import cats.syntax.all.*
import cats.effect.*
import com.comcast.ip4s.*
import fs2.Stream
import fs2.text.utf8
import io.chrisdavenport.cats.effect.time.JavaTime
import java.time.ZoneId
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.*


object Main extends IOApp.Simple:

  val serverLogic: HttpApp[IO] = HttpApp(_ =>
    for
      now <- JavaTime[IO].getLocalDateTime(ZoneId.of("Europe/Moscow"))
      nowStream = Stream(now.toString).covary[IO].through(utf8.encode)
    yield Response(status = Status.Ok, entity = Entity(body = nowStream))
  )

  val server = EmberServerBuilder
      .default[IO]
      .withHost(ipv4"0.0.0.0")
      .withPort(port"8080")
      .withHttpApp(serverLogic)

  override def run: IO[Unit] = server.build.useForever
